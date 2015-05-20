package Matt.Stowe.MTG;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.Common.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Battle implements Runnable{
	private static final int STACK_TEXT_X=		300;
	private static final int STACK_TEXT_WIDTH=	200;

	private Object mylock;
	private TurnPhase phase;
	private Vector<Player> players;
	private int currentPlayerIndex;
	private BasicTimer phaseTimer;
	private Stack<MagicStackElement> theStack;
	
	private boolean doesntUseStack(MagicEffect effect){
		return (effect instanceof MagicEffectPlayLand)
			||(effect instanceof MagicEffectMorphTargetCreature);
	}
	
	private void pushEffectsOntoTheStack(MagicStackElement element){
		ArrayList<MagicEffect> effectsDeepCopied=new ArrayList<MagicEffect>();
		for(int i=0;i<element.Effects.length;i++){
			effectsDeepCopied.add(element.Effects[i].DeepCopy());
		}
		for(int i=0;i<element.Effects.length;i++){
			element.Effects[i].TargetData.Reset();
		}
		for(int i=effectsDeepCopied.size()-1;i>=0;i--){
			MagicEffect ceffect=effectsDeepCopied.get(i);
			if(!this.payExtraManaCosts(ceffect.GetExtraManaCosts(), ceffect.GetController())){
				effectsDeepCopied.remove(i);
			}
		}
		MagicEffect[] effectsToPush=effectsDeepCopied.toArray(new MagicEffect[0]);
		if(effectsToPush.length>0){
			this.theStack.push(new MagicStackElement(effectsToPush, element.CardToPlay));
			if(this.doesntUseStack(effectsToPush[0])){
				this.resolveTheStack(this.theStack.size()-1);
			}
			if(element.CardToPlay!=null&&!element.CardToPlay.IsLand()){
				ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
				for(int i=0;i<this.players.size();i++){
					triggeredEffects.addAll(this.players.get(i).TriggerSpellCast(element.CardToPlay, this.players));
				}
				for(int i=0;i<triggeredEffects.size();i++){
					this.pushEffectsOntoTheStack(new MagicStackElement(triggeredEffects.get(i), null));
				}
			}
			this.moveCardsToPlayOnStack();
		}
	}

	private void moveCardsToPlayOnStack(){
		for(int i=0;i<this.theStack.size();i++){
			int cy=200+(this.theStack.size()-1-i)*(CardBase.HEIGHT+2);
			MagicStackElement celement=this.theStack.elementAt(i);
			if(celement.CardToPlay!=null){
				celement.CardToPlay.SetAnimationDestination(STACK_TEXT_X+STACK_TEXT_WIDTH+2, cy, OptionsScreen.AnimationSpeed);
			}
		}
	}
	
	private boolean[] playersSkippingTimer;

	private enum TurnPhase{
		MULLIGAN,
		UNTAP,
		UPKEEP,
		DRAW,
		MAIN1,
		COMBAT_BEGIN,
		DECLARE_ATTACKERS,
		DECLARE_BLOCKERS,
		COMBAT_DAMAGE,
		COMBAT_END,
		MAIN2,
		END
	}
	
	public Battle(Vector<Player> players){
		this.players=players;
		this.currentPlayerIndex=-1;
		this.mylock=new Object();
		this.phaseTimer=new BasicTimer(Color.blue, 100, 32, 40, 12);
		this.phaseTimer.JumpToFinish();
		this.theStack=new Stack<MagicStackElement>();

		Thread controlFlow=new Thread(this);
		controlFlow.start();
	}

	public void NewBattle(){
		this.playersSkippingTimer=new boolean[this.players.size()];
		this.currentPlayerIndex=(int)(Math.random()*this.players.size());
		for(int i=0;i<players.size();i++){
			players.get(i).TheStack=this.theStack;
		}
		this.phase=TurnPhase.MULLIGAN;
		synchronized(this.mylock){
			this.mylock.notifyAll();
		}
	}
	
	private void payCosts(Player payor, PlayerResponse newPlayerActivity){
		if(newPlayerActivity!=null){
			if(newPlayerActivity.ManaCosts!=null){
				MagicEffect[] costs=payor.GetManaCostAsMagicEffects(newPlayerActivity.ManaCosts);
				for(int i=0;i<costs.length;i++){
					costs[i].Resolve(this.players);
				}
			}
			if(newPlayerActivity.Costs!=null){
				MagicEffect[] costs=newPlayerActivity.Costs;
				for(int i=0;i<costs.length;i++){
					costs[i].Resolve(this.players);
				}
			}
		}
	}
	
	private void startPlayerTimers(){
		for(int i=0;i<this.players.size();i++){
			this.playersSkippingTimer[i]=false;
			this.players.elementAt(i).timerStarted(this.players, this.theStack);
		}
	}
	
	private void endPlayerTimersExceptIndex(int exceptIndex){
		for(int i=0;i<this.players.size();i++){
			if(i==exceptIndex)
				continue;
			this.players.elementAt(i).timerEnded();
		}

	}
	
	private void allowPlayersToRespond(){
		this.handleStateBasedActions();
		boolean allmana=false;
		if(this.theStack.size()>0&&OptionsScreen.AutoResolveMana){
			MagicStackElement celement=this.theStack.peek();
			MagicEffect[] nextEffects=celement.Effects;
			allmana=celement.CardToPlay==null;
			for(int i=0;i<nextEffects.length&&allmana;i++){
				if(!(nextEffects[i] instanceof MagicEffectAddManaToPool))
					allmana=false;
			}
			if(allmana)
				return;
		}

		this.phaseTimer.Start(OptionsScreen.TimerDuration);
		this.startPlayerTimers();
		int pausedPlayerIndex=-1;
		while(!this.phaseTimer.IsComplete()&&!allmana){
			for(int i=0;i<this.players.size();i++){
				if(i!=pausedPlayerIndex&&pausedPlayerIndex>-1)
					continue;
				Player respondingPlayer=this.players.elementAt(i);
				PlayerResponse response=respondingPlayer.handleInstants(this.players);
				if(response!=null){
					if(response.Effects.length==1 && response.Effects[0] instanceof MagicEffectToggleTimerPause){
						this.phaseTimer.TogglePause();
						if(this.phaseTimer.IsPaused()){
							pausedPlayerIndex=i;
							this.endPlayerTimersExceptIndex(i);
						}else{
							pausedPlayerIndex=-1;
							this.startPlayerTimers();
						}
					}else if(response.Effects.length==1 && response.Effects[0] instanceof MagicEffectTimerSkip){
						this.playersSkippingTimer[i]=true;
						boolean readyToSkip=true;
						for(int checkTimerSkipIndex=0;readyToSkip&&checkTimerSkipIndex<this.playersSkippingTimer.length;checkTimerSkipIndex++){
							if(!this.playersSkippingTimer[checkTimerSkipIndex])
								readyToSkip=false;
						}
						if(readyToSkip)
							this.phaseTimer.JumpToFinish();
					}else{
						this.payCosts(respondingPlayer, response);
						this.pushEffectsOntoTheStack(response.GetMagicStackElement());
						this.phaseTimer.Start(OptionsScreen.TimerDuration);
						pausedPlayerIndex=-1;
						this.startPlayerTimers();

						allmana=true;
						for(int manaCheckEffectIndex=0;manaCheckEffectIndex<response.Effects.length&&allmana;manaCheckEffectIndex++){
							if(!(response.Effects[manaCheckEffectIndex] instanceof MagicEffectAddManaToPool))
								allmana=false;
						}
					}
				}
			}
			try{Thread.sleep(33);}catch(InterruptedException ie){ie.printStackTrace();}
		}
		this.endPlayerTimersExceptIndex(-1);
	}
	
	private void resolveTheStack(int resolveUntil){
		while(this.theStack.size()>resolveUntil){
			MagicStackElement currentStackElement;
			synchronized(this.theStack){
				currentStackElement=this.theStack.pop();
			}
			this.moveCardsToPlayOnStack();
			boolean noStack=currentStackElement.Effects.length==1;
			//TODO: Counter the spell if all targets are invalid: 2012 Comp rules 608.2b
			boolean allTargetsInvalid=true;
			boolean hasTargets=false;
			for(int i=0;allTargetsInvalid&&i<currentStackElement.Effects.length;i++){
				if(currentStackElement.Effects[i].TargetData.GetRequiredTargetCount()>0){
					hasTargets=true;
					if(currentStackElement.Effects[i].AnyTargetCanBeTargeted()){
						allTargetsInvalid=false;
					}
				}
			}
			
			if(!hasTargets||!allTargetsInvalid){
				for(int i=0;i<currentStackElement.Effects.length;i++){
					noStack=noStack&&this.doesntUseStack(currentStackElement.Effects[i]);
					currentStackElement.Effects[i].RemoveInvalidTargets();
					ArrayList<MagicEffect[]> triggeredEffects=currentStackElement.Effects[i].Resolve(this.players);
				
					for(int teindex=0;teindex<triggeredEffects.size();teindex++){
						this.pushEffectsOntoTheStack(new MagicStackElement(triggeredEffects.get(teindex), null));
						noStack=false;
					}
				}
			}
			this.handleStateBasedActions();
			if(!noStack)
				this.allowPlayersToRespond();
		}
	}
	
	private void handleStateBasedActions(){
		for(int i=0;i<this.players.size();i++)
			this.players.elementAt(i).updateBattleState(this.players);

		ArrayList<Planeswalker> ajanis=new ArrayList<Planeswalker>();

		for(int i=0;i<this.players.size();i++){
			this.players.elementAt(i).handleStateBasedActions(this.players, ajanis);
		}

		if(ajanis.size()>1){
			for(int ajaniIndex=0;ajaniIndex<ajanis.size();ajaniIndex++){
				for(int pindex=0;pindex<this.players.size();pindex++){
					this.players.elementAt(pindex).DestroyPermanent(ajanis.get(ajaniIndex), this.players);
				}
			}
		}
	}
	
	private void creatureDealtDamage(Creature dealer, int damage){
		ArrayList<MagicEffect[]> damageTriggeredEffects=dealer.DealtDamage(damage, this.players);
		for(int triggeredEffectIndex=0;triggeredEffectIndex<damageTriggeredEffects.size();triggeredEffectIndex++){
			this.pushEffectsOntoTheStack(new MagicStackElement(damageTriggeredEffects.get(triggeredEffectIndex), null));
		}
	}
	
	private boolean payExtraManaCosts(ArrayList<ManaCost> manacosts, Player controller){
		for(int amci=0;amci<manacosts.size();amci++){
			ManaCost manaCost=manacosts.get(amci);
			if(manaCost!=null){
				PlayerResponse effectPayment=controller.PayManaCosts(this.players, manaCost);
				while(effectPayment!=null){
					this.payCosts(controller, effectPayment);
					int resolveUntil=this.theStack.size();
					this.pushEffectsOntoTheStack(new MagicStackElement(effectPayment.Effects, null));
					this.resolveTheStack(resolveUntil);
					effectPayment=controller.PayManaCosts(this.players, manaCost);
				}
				MagicEffect[] manacostEffect=controller.GetManaCostAsMagicEffects(manaCost);
				if(manacostEffect==null){
					return false;
				}else{
					for(int i=0;i<manacostEffect.length;i++){
						manacostEffect[i].Resolve(this.players);
					}
				}
			}
		}
		
		return true;
	}
	
	public void run(){
		while(true){
			while(this.currentPlayerIndex<0){
				synchronized(this.mylock){
					try{this.mylock.wait();}catch(InterruptedException ie){ie.printStackTrace();}
				}
			}
			
			Player currentPlayer=this.players.elementAt(this.currentPlayerIndex);
			
			switch(this.phase){
			case MULLIGAN:
				for(int i=0;i<this.players.size();i++)
					this.players.elementAt((this.currentPlayerIndex+i)%this.players.size()).handleNewBattle(this.players);
				if(this.players.size()<3)
					this.phase=TurnPhase.MAIN1;
				else
					this.phase=TurnPhase.DRAW;
				this.handleStateBasedActions();
			break;
			case UNTAP:
				for(int i=0;i<this.players.size();i++){
					this.players.elementAt(i).handleUntapPhase(currentPlayer, this.players);
				}
				this.phase=TurnPhase.UPKEEP;
			break;
			case UPKEEP:
				for(int i=0;i<this.players.size();i++){
					ArrayList<MagicEffect[]> triggeredEffects=this.players.elementAt(i).handleUpkeepPhase(currentPlayer, this.players);
					for(int j=0;j<triggeredEffects.size();j++){
						this.pushEffectsOntoTheStack(new MagicStackElement(triggeredEffects.get(j), null));
					}
				}
				if(this.theStack.size()>0){
					this.allowPlayersToRespond();
					this.resolveTheStack(0);
				}
				this.phase=TurnPhase.DRAW;
			break;
			case DRAW:
				currentPlayer.handleDrawPhase();
				this.phase=TurnPhase.MAIN1;
			break;
			case MAIN1:
				PlayerResponse newPlayerActivity=currentPlayer.handleMainPhaseOne(this.players);
				while(newPlayerActivity!=null){
					this.payCosts(currentPlayer, newPlayerActivity);
					this.pushEffectsOntoTheStack(newPlayerActivity.GetMagicStackElement());
					if(newPlayerActivity.Effects==null||!this.doesntUseStack(newPlayerActivity.Effects[0])){
						this.allowPlayersToRespond();
					}
					this.resolveTheStack(0);
					newPlayerActivity=currentPlayer.handleMainPhaseOne(this.players);
				}
				this.phase=TurnPhase.COMBAT_BEGIN;
			break;
			case COMBAT_BEGIN:
				this.allowPlayersToRespond();
				this.resolveTheStack(0);
				this.phase=TurnPhase.DECLARE_ATTACKERS;
			break;
			case DECLARE_ATTACKERS:
				if(!currentPlayer.declareAttackers(this.players)){
					this.phase=TurnPhase.MAIN2;
				}else{
					Creature[] attackers=currentPlayer.GetAttackingCreatures();
					for(int ai=0;ai<attackers.length;ai++){
						ArrayList<ManaCost> attackerManaCosts=attackers[ai].GetAttackManaCost();
						if(!this.payExtraManaCosts(attackerManaCosts, currentPlayer)){
							attackers[ai].TargetedPlayer=null;
							attackers[ai].Untap();
						}
					}
				
					for(int attackTriggerPlayerIndex=0;attackTriggerPlayerIndex<this.players.size();attackTriggerPlayerIndex++){
						ArrayList<MagicEffect[]> triggeredEffects=this.players.elementAt(attackTriggerPlayerIndex).TriggerAttackersDeclared(currentPlayer.GetAttackingCreatures(), this.players);
						for(int teindex=0;teindex<triggeredEffects.size();teindex++){
							this.pushEffectsOntoTheStack(new MagicStackElement(triggeredEffects.get(teindex), null));
						}
					}
					this.allowPlayersToRespond();
					this.resolveTheStack(0);
					this.phase=TurnPhase.DECLARE_BLOCKERS;
				}
			break;
			case DECLARE_BLOCKERS:
				Creature[] incomingattackers=currentPlayer.GetAttackingCreatures();
				for(int defendingPlayerIndex=0;defendingPlayerIndex<this.players.size();defendingPlayerIndex++){
					if(defendingPlayerIndex==this.currentPlayerIndex)
						continue;
					Player cplayer=this.players.elementAt(defendingPlayerIndex);
					cplayer.declareBlockers(this.players, incomingattackers);
					
					Creature[] blockers=cplayer.GetBlockingCreatures(incomingattackers);
					for(int bi=0;bi<blockers.length;bi++){
						ArrayList<ManaCost> blockerManaCosts=blockers[bi].GetBlockManaCost();
						if(!this.payExtraManaCosts(blockerManaCosts, cplayer)){
							for(int aindex=0;aindex<incomingattackers.length;aindex++){
								incomingattackers[aindex].removeBlocker(blockers[bi]);
							}
						}
					}
					
					cplayer.UpdateBlockingCreatures(this.players);
				}
				this.allowPlayersToRespond();
				this.resolveTheStack(0);
				this.phase=TurnPhase.COMBAT_DAMAGE;
			break;
			case COMBAT_DAMAGE:
				Creature[] attackers=currentPlayer.GetAttackingCreatures();
				int[][] attackersDamageOrders=new int[attackers.length][];
				for(int aindex=0;aindex<attackers.length;aindex++){
					attackersDamageOrders[aindex]=currentPlayer.GetDamageOrder(attackers[aindex]);
				}

				for(int firstStrikeIndex=0;firstStrikeIndex<2;firstStrikeIndex++){
					for(int aindex=0;aindex<attackers.length;aindex++){
						if(attackers[aindex].IsDestroyed())
							continue;
						boolean attackerDoesDamage=true;
						if(firstStrikeIndex==0){
							if(!attackers[aindex].GetKeywords().HasFirstStrike()){
								attackerDoesDamage=false;
							}
						}else{
							if(attackers[aindex].GetKeywords().HasFirstStrike()&&!attackers[aindex].GetKeywords().HasDoubleStrike()){
								attackerDoesDamage=false;
							}
						}
						int damageLeftToDeal=attackers[aindex].GetPower();
						if(damageLeftToDeal<=0){
							attackerDoesDamage=false;
						}
						
						if(attackers[aindex].GetBlockers().size()==0){
							if(attackerDoesDamage){
								int damageDealt=attackers[aindex].TargetedPlayer.MarkDamage(damageLeftToDeal, DamagePreventor.DAMAGE_TYPE_FLAG_COMBAT, attackers[aindex]);

								for(int damagedTriggerPlayerIndex=0;damagedTriggerPlayerIndex<this.players.size();damagedTriggerPlayerIndex++){
									ArrayList<MagicEffect[]> triggeredEffects=this.players.elementAt(damagedTriggerPlayerIndex).TriggerTookDamage(attackers[aindex].TargetedPlayer, damageLeftToDeal, DamagePreventor.DAMAGE_TYPE_FLAG_COMBAT, attackers[aindex]);
									for(int teindex=0;teindex<triggeredEffects.size();teindex++){
										this.pushEffectsOntoTheStack(new MagicStackElement(triggeredEffects.get(teindex), null));
									}
								}
								this.creatureDealtDamage(attackers[aindex], damageDealt);
							}
						}else{
							int[] order=attackersDamageOrders[aindex];
							for(int dmgindex=0;dmgindex<order.length;dmgindex++){
								Creature blocker=attackers[aindex].GetBlockers().elementAt(order[dmgindex]);

								Player blockerController=null;
								for(int playerIndexBlockerCheck=0;blockerController==null&&playerIndexBlockerCheck<this.players.size();playerIndexBlockerCheck++){
									if(this.players.elementAt(playerIndexBlockerCheck).IsCardOnField(blocker)){
										blockerController=this.players.elementAt(playerIndexBlockerCheck);
									}
								}
								if(blockerController==null)
									continue;

								if(!blocker.IsDestroyed()&&attackerDoesDamage&&damageLeftToDeal>0){
									int damage=damageLeftToDeal;
									if(damage>blocker.GetToughness()&&(dmgindex<order.length-1||attackers[aindex].GetKeywords().HasTrample()))
										damage=blocker.GetToughness();
									int damageDealtByAttacker=blocker.MarkDamage(damage, DamagePreventor.DAMAGE_TYPE_FLAG_COMBAT, attackers[aindex]);

									for(int damagedTriggerPlayerIndex=0;damagedTriggerPlayerIndex<this.players.size();damagedTriggerPlayerIndex++){
										ArrayList<MagicEffect[]> triggeredEffects=this.players.elementAt(damagedTriggerPlayerIndex).TriggerTookDamage(blocker, damageDealtByAttacker, DamagePreventor.DAMAGE_TYPE_FLAG_COMBAT, attackers[aindex]);
										for(int teindex=0;teindex<triggeredEffects.size();teindex++){
											this.pushEffectsOntoTheStack(new MagicStackElement(triggeredEffects.get(teindex), null));
										}
									}
									this.creatureDealtDamage(attackers[aindex], damageDealtByAttacker);
									damageLeftToDeal-=damage;
									if(damageDealtByAttacker>0&&attackers[aindex].GetKeywords().HasDeathtouch()){
										blocker.MarkAsDeathtouched();
									}
								}

								boolean blockerDoesDamage=true;
								if(firstStrikeIndex==0){
									if(!blocker.GetKeywords().HasFirstStrike()){
										blockerDoesDamage=false;
									}
								}else{
									if(blocker.GetKeywords().HasFirstStrike()&&!blocker.GetKeywords().HasDoubleStrike()){
										blockerDoesDamage=false;
									}
								}

								if(blockerDoesDamage){
									int damageDealtByBlocker=attackers[aindex].MarkDamage(blocker.GetPower(), DamagePreventor.DAMAGE_TYPE_FLAG_COMBAT, blocker);

									for(int damagedTriggerPlayerIndex=0;damagedTriggerPlayerIndex<this.players.size();damagedTriggerPlayerIndex++){
										ArrayList<MagicEffect[]> triggeredEffects=this.players.elementAt(damagedTriggerPlayerIndex).TriggerTookDamage(attackers[aindex], damageDealtByBlocker, DamagePreventor.DAMAGE_TYPE_FLAG_COMBAT, blocker);
										for(int teindex=0;teindex<triggeredEffects.size();teindex++){
											this.pushEffectsOntoTheStack(new MagicStackElement(triggeredEffects.get(teindex), null));
										}
									}
									this.creatureDealtDamage(blocker, damageDealtByBlocker);
									if(damageDealtByBlocker>0&&blocker.GetKeywords().HasDeathtouch()){
										attackers[aindex].MarkAsDeathtouched();
									}
								}
							}
							if(attackers[aindex].GetKeywords().HasTrample()&&damageLeftToDeal>0&&attackerDoesDamage&&attackers[aindex].TargetedPlayer!=null){
								int damageDealt=attackers[aindex].TargetedPlayer.MarkDamage(damageLeftToDeal, DamagePreventor.DAMAGE_TYPE_FLAG_COMBAT, attackers[aindex]);

								for(int damagedTriggerPlayerIndex=0;damagedTriggerPlayerIndex<this.players.size();damagedTriggerPlayerIndex++){
									ArrayList<MagicEffect[]> triggeredEffects=this.players.elementAt(damagedTriggerPlayerIndex).TriggerTookDamage(attackers[aindex].TargetedPlayer, damageDealt, DamagePreventor.DAMAGE_TYPE_FLAG_COMBAT, attackers[aindex]);
									for(int teindex=0;teindex<triggeredEffects.size();teindex++){
										this.pushEffectsOntoTheStack(new MagicStackElement(triggeredEffects.get(teindex), null));
									}
								}
								this.creatureDealtDamage(attackers[aindex], damageDealt);
							}
						}
					}
				}
				this.handleStateBasedActions();
				this.allowPlayersToRespond();
				this.resolveTheStack(0);
				this.phase=TurnPhase.MAIN2;
			break;
			case MAIN2:
				PlayerResponse newPlayerActivity2=currentPlayer.handleMainPhaseTwo(this.players);
				while(newPlayerActivity2!=null){
					this.payCosts(currentPlayer, newPlayerActivity2);
					this.pushEffectsOntoTheStack(newPlayerActivity2.GetMagicStackElement());
					if(newPlayerActivity2.Effects==null||this.doesntUseStack(newPlayerActivity2.Effects[0])){
						this.allowPlayersToRespond();
					}
					this.resolveTheStack(0);
					newPlayerActivity2=currentPlayer.handleMainPhaseTwo(this.players);
				}
				this.phase=TurnPhase.END;
			break;
			case END:
				// End Step
				ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
				for(int i=0;i<this.players.size();i++){
					triggeredEffects.addAll(this.players.elementAt(i).handleEndPhase(this.players));
				}
				for(int teindex=0;teindex<triggeredEffects.size();teindex++){
					this.pushEffectsOntoTheStack(new MagicStackElement(triggeredEffects.get(teindex), null));
				}
				this.allowPlayersToRespond();
				this.resolveTheStack(0);
				
				// Cleanup Step
				do{
					triggeredEffects.clear();
					triggeredEffects.addAll(this.players.elementAt(this.currentPlayerIndex).handleHandLimit(this.players));
					for(int i=0;i<this.players.size();i++){
						triggeredEffects.addAll(this.players.elementAt(i).handleCleanupStep(this.players));
					}
					for(int teindex=0;teindex<triggeredEffects.size();teindex++){
						this.pushEffectsOntoTheStack(new MagicStackElement(triggeredEffects.get(teindex), null));
					}
					if(triggeredEffects.size()>0){
						this.allowPlayersToRespond();
						this.resolveTheStack(0);
					}

					this.handleStateBasedActions();
				}while(triggeredEffects.size()>0);
				
				this.currentPlayerIndex=(this.currentPlayerIndex+1)%this.players.size();
				this.phase=TurnPhase.UNTAP;
			break;
			}
		}
	}
	
	public void mousePressed(MouseEvent me){
		for(int i=0;i<this.players.size();i++){
			this.players.elementAt(i).mousePressed(me);
		}
	}

	public void mouseReleased(MouseEvent me){
		for(int i=0;i<this.players.size();i++){
			this.players.elementAt(i).mouseReleased(me);
		}
	}

	public void mouseClicked(MouseEvent me){
		for(int i=0;i<this.players.size();i++){
			this.players.elementAt(i).mouseClicked(me);
		}
	}
	
	public void keyPressed(KeyEvent ke){
		for(int i=0;i<this.players.size();i++){
			this.players.elementAt(i).keyPressed(ke);
		}	
	}

	public void animate(long elapsedMillis){
		for(int i=0;i<this.players.size();i++){
			this.players.elementAt(i).animate(elapsedMillis);
		}
		
		for(int i=0;i<this.theStack.size();i++){
			MagicStackElement cstackElement=this.theStack.get(i);
			if(cstackElement!=null){
				CardBase cardToPlay=cstackElement.CardToPlay;
				if(cardToPlay!=null){
					cardToPlay.StepTowardsOnRails();
				}
			}
		}
		
		this.phaseTimer.animate(elapsedMillis);
	}

	public void paint(Graphics g){
		if(this.phase==null)
			return;
		g.setColor(Color.black);
		switch(this.phase){
		case MULLIGAN:
			g.drawString("Mulligans", 20, 46);
		break;
		case UNTAP:
		case UPKEEP:
		case DRAW:
			g.drawString("Upkeep", 20, 46);
		break;
		case MAIN1:
			g.drawString("Main I", 20, 46);
		break;
		case COMBAT_BEGIN:
			g.drawString("Combat Begin", 20, 46);
		break;
		case DECLARE_ATTACKERS:
			g.drawString("Attack", 20, 46);
		break;
		case DECLARE_BLOCKERS:
			g.drawString("Block", 20, 46);
		break;
		case COMBAT_DAMAGE:
			g.drawString("Combat Damage", 20, 46);
		break;
		case COMBAT_END:
			g.drawString("Combat End", 20, 46);
		break;
		case MAIN2:
			g.drawString("Main II", 20, 46);
		break;
		case END:
			g.drawString("End", 20, 46);
		break;
		}
		
		g.drawString("Current player: "+this.players.elementAt(this.currentPlayerIndex).GetName(), 150, 46);
	
		for(int i=0;i<this.players.size();i++){
			this.players.elementAt(i).paint(g);
		}
		
		if(!this.phaseTimer.IsComplete())
			this.phaseTimer.paintBar(g);
		
		synchronized(this.theStack){
			for(int i=this.theStack.size()-1;i>=0;i--){
				int cy=200+(this.theStack.size()-1-i)*(CardBase.HEIGHT+2);
				Painting.FillRectWithBorder(g, STACK_TEXT_X, cy, STACK_TEXT_WIDTH, CardBase.HEIGHT, Color.white, Color.black);
				MagicStackElement celement=this.theStack.elementAt(i);
				if(celement.CardToPlay!=null)
					celement.CardToPlay.paint(g);
				g.setColor(Color.black);
				MagicEffect[] effects=celement.Effects;
				String allrules=effects.length>0?effects[0].GetControllerName()+":":"";
				for(int effectIndex=0;effectIndex<effects.length;effectIndex++){
					allrules=allrules+" "+effects[effectIndex].GetRulesText();
					for(int targetIndex=0;effects[effectIndex].TargetData.GetTarget(targetIndex)!=null;targetIndex++){
						ITargetable ctarget=effects[effectIndex].TargetData.GetTarget(targetIndex);
						if(ctarget==null)
							continue;
						g.drawLine(400, cy+25, ctarget.GetTargetEndpointX(), ctarget.GetTargetEndpointY());
					}
				}
				Painting.DrawWrappedString(g, allrules, 302, cy+14, 196, 14);
			}
		}
		
		for(int i=0;i<this.players.size();i++){
			this.players.elementAt(i).paintFullViewCard(g);
		}
	}
}
