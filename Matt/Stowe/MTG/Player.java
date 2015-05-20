package Matt.Stowe.MTG;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Cards.Sets.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;
import Matt.Stowe.Common.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Player implements ITargetable, IPlayer{
	public Stack<MagicStackElement> TheStack;

	private EmblemCollection emblems;
	public EmblemCollection GetEmblems(){return this.emblems;}
	
	private ArrayList<TargetInfo> cantBeTargetedBy;
	public void AddCantBeTargetedBy(TargetInfo info){this.cantBeTargetedBy.add(info);}
	public void RemoveCantBeTargetedBy(TargetInfo info){this.cantBeTargetedBy.remove(info);}
	public boolean CanBeTargetedBy(ITargetable source){
		for(int i=0;i<this.cantBeTargetedBy.size();i++){
			if(this.cantBeTargetedBy.get(i).IsValidTarget(source)){
				return false;
			}
		}
		
		return true;
	}

	private ArrayList<TargetInfo> entersTheBattlefieldTriggersSuppressed;
	public void AddEntersTheBattlefieldTriggerSuppression(TargetInfo suppressed){
		this.entersTheBattlefieldTriggersSuppressed.add(suppressed);
	}
	public void RemoveEntersTheBattlefieldTriggerSuppression(TargetInfo suppressed){
		this.entersTheBattlefieldTriggersSuppressed.remove(suppressed);
	}
	
	private ArrayList<MagicEffect> effectsToUndoAtEndOfTurn;
	public void UndoAtEndOfTurn(MagicEffect effect){
		this.effectsToUndoAtEndOfTurn.add(effect);
	}
	
	public void UndoWhenControlIsLost(MagicEffect effect){}
	public void UndoWhenLeavesBattlefield(MagicEffect effect){}
	
	private int myIndex;
	public int GetIndex(){return this.myIndex;}
	private IPlayerInterface playerInterface;
	public String GetControlTypeString(){return this.playerInterface.GetControlTypeString();}
	public boolean IsLocalHuman(){
		return this.playerInterface instanceof LocalHumanPlayerInterface;
	}
	
	private String name;
	public String GetName(){return this.name;}
	private AnimatedGUIObject playingArea;
	private int areaMinX, areaMinY, areaMaxX, areaMaxY;
	public int GetAreaMinX(){return this.areaMinX;}
	public int GetAreaMaxX(){return this.areaMaxX;}
	public int GetAreaMinY(){return this.areaMinY;}	
	public int GetAreaMaxY(){return this.areaMaxY;}
	
	public ITargetable GetITargetable(){return this;}
	public int GetTargetTypeFlags(){
		return TargetInfo.TARGET_TYPE_FLAG_PLAYER;
	}
	
	public int GetColorFlags(){return ManaCost.COLOR_FLAG_COLORLESS;}
	
	private boolean onBottom;
	private Deck deck;
	public Deck GetDeck(){return this.deck;}
	public int GetDeckSize(){return this.deck.GetCardCount();}
	public void SetDeck(Vector<CardBase> cards){
		int dx=this.areaMinX+2;
		int dy=this.onBottom?this.areaMaxY-CardBase.HEIGHT-2:this.areaMinY+2;
		this.deck=new Deck(cards, dx, dy);
	}
	
	private Vector<CardBase> hand;
	private void addCardToHand(CardBase card){
		if(!this.IsLocalHuman())
			card.SetFaceDown(true);
		this.hand.add(card);
	}
	private void removeCardFromHand(CardBase card){
		synchronized(this.hand){
			this.hand.remove(card);
		}
		this.repositionCardsInHand();
	}
	public int GetHandSize(){return this.hand.size();}
	public Vector<CardBase> GetHand(){return this.hand;}
	
	private Vector<CardBase> field;
	public Vector<CardBase> GetField(){return this.field;}
	
	private Vector<CardBase> graveyard;
	public Vector<CardBase> GetGraveyard(){return this.graveyard;}
	
	private class ExileCollection{
		private ArrayList<Integer> exiledCardsOldZones;
		private ArrayList<CardBase> exile;
		public ArrayList<CardBase> GetCards(){return exile;}
		
		public ExileCollection(){
			this.exiledCardsOldZones=new ArrayList<Integer>();
			this.exile=new ArrayList<CardBase>();
		}
		public int GetOldZoneForCard(CardBase card){
			return this.exiledCardsOldZones.get(this.exile.indexOf(card));
		}
		public void add(int oldzone, CardBase card){
			this.exile.add(card);
			this.exiledCardsOldZones.add(oldzone);
		}
		public boolean remove(CardBase card){
			int cardIndex=this.exile.indexOf(card);
			if(cardIndex==-1)
				return false;
				
			this.exile.remove(cardIndex);
			this.exiledCardsOldZones.remove(cardIndex);
			return true;
		}
		public boolean contains(CardBase card){
			return this.exile.contains(card);
		}
	}
	
	private int exileX, exileY;
	private ExileCollection exile;
	public ArrayList<CardBase> GetExile(){return this.exile.GetCards();}
	
	public ArrayList<CardBase> GetAllCards(){
		ArrayList<CardBase> allcards=new ArrayList<CardBase>();
		allcards.addAll(this.GetExile());
		allcards.addAll(this.hand);
		allcards.addAll(this.graveyard);
		allcards.addAll(this.field);
		return allcards;
	}
	
	private int handY;
	private int handX;
	private int handLimit;
	private int landsPlayedThisTurn;
	private int manapoolMountains, manapoolForests, manapoolIslands, manapoolPlains, manapoolSwamps, manapoolColorless;
	private int convokedMountains, convokedForests, convokedIslands, convokedPlains, convokedSwamps, convokedColorless;
	private int getTotalAvailableMana(){
		return this.manapoolMountains+this.convokedMountains+this.manapoolForests+this.convokedForests+
			this.manapoolIslands+this.convokedIslands+this.manapoolPlains+this.convokedPlains+this.manapoolSwamps+this.convokedSwamps+
			this.manapoolColorless+this.convokedColorless;
	}
	private int startingLifePoints;
	public int GetStartingLifePoints(){return this.startingLifePoints;}
	private int lifePoints;
	public int GetLifePoints(){return this.lifePoints;}
	private DamagePreventorManager damagePreventors;
	public int MarkDamage(int damage, int damageTypeFlags, CardBase source){
		if(damage<=0)
			return 0;
		int damageDealt=damage;
		
		if(this.emblems.EmblemCount(EmblemCollection.AJANI_STEADFAST)>0){
			damageDealt=1;
		}

		damageDealt=this.damagePreventors.PreventDamage(damageDealt, damageTypeFlags, source);

		return this.LoseLife(damageDealt);
	}

	private ArrayList<TriggeredAbilityDamaged> tookDamageTriggers;
	public void AddTookDamageTrigger(TriggeredAbilityDamaged trigger){tookDamageTriggers.add(trigger);}
	public void RemoveTookDamageTrigger(TriggeredAbilityDamaged trigger){tookDamageTriggers.remove(trigger);}
	public ArrayList<MagicEffect[]> TriggerTookDamage(ITargetable recipient, int damage, int damageTypeFlags, CardBase source){
		ArrayList<MagicEffect[]> triggeredAbilities=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.tookDamageTriggers.size();i++){
			MagicEffect[] triggeredEffects=this.tookDamageTriggers.get(i).GetTriggeredEffects(recipient, damage, damageTypeFlags, source);
			if(triggeredEffects!=null){
				triggeredAbilities.add(triggeredEffects);
			}
		}
		
		for(int i=0;i<this.field.size();i++){
			triggeredAbilities.addAll(this.field.elementAt(i).TriggerTookDamage(recipient, damage, damageTypeFlags, source));
		}
		return triggeredAbilities;
	}
	
	public int LoseLife(int life){
		if(life<=0)
			return 0;
		this.lostLifeThisTurn=true;
		this.lifePoints-=life;
		return life;
	}
	public int GainLife(int life){
		if(life<=0)
			return 0;
		this.lifePoints+=life;
		return life;
	}
	
	private boolean lostLifeLastTurn;
	private boolean lostLifeThisTurn;
	public boolean LostLifeLastTurn(){return this.lostLifeLastTurn;}
	
	private boolean attackedThisTurn;
	public boolean AttackedThisTurn(){return this.attackedThisTurn;}
	
	public Player(int index, int controlType, String name, boolean onBottom, int minx, int miny, int maxx, int maxy){
		this.entersTheBattlefieldTriggersSuppressed=new ArrayList<TargetInfo>();
		this.myIndex=index;
		switch(controlType){
		case OptionsScreen.CONTROLTYPE_LOCALHUMAN:
			this.playerInterface=new LocalHumanPlayerInterface(index, minx, miny, maxx, maxy, onBottom);
		break;
		case OptionsScreen.CONTROLTYPE_LOCALAI:
			this.playerInterface=new LocalAIPlayerInterface(index);
		break;
		default:
			System.out.println("Unknown control type for player '"+name+"': "+controlType);
		break;
		}
		this.name=name;
		this.onBottom=onBottom;
		this.areaMinX=minx;
		this.areaMinY=miny;
		this.areaMaxX=maxx;
		this.areaMaxY=maxy;
		this.playingArea=new AnimatedGUIObject(minx, miny, maxx, maxy);
		this.hand=new Vector<CardBase>();
		this.field=new Vector<CardBase>();
		this.graveyard=new Vector<CardBase>();
		this.exileX=this.areaMinX-CardBase.WIDTH-2;
		this.exileY=this.onBottom?this.areaMaxY-CardBase.HEIGHT-2:this.areaMinY+2;
		this.exile=new ExileCollection();
		this.handX=this.areaMinX+CardBase.WIDTH+4;
		this.handY=this.onBottom?this.areaMaxY-2-CardBase.HEIGHT:this.areaMinY+2;
		this.handLimit=7;
		this.effectsToUndoAtEndOfTurn=new ArrayList<MagicEffect>();
		this.emblems=new EmblemCollection();
		this.damagePreventors=new DamagePreventorManager();
		this.tookDamageTriggers=new ArrayList<TriggeredAbilityDamaged>();
		this.cantBeTargetedBy=new ArrayList<TargetInfo>();
	}
	
	private BattleState generateBattleState(Vector<Player> players){
		return new BattleState(this.myIndex, players);
	}
	
	private boolean canPayNonmanaCosts(MagicEffect[] costs){
		boolean canpay=true;
		if(costs!=null){
			for(int i=0;canpay&&i<costs.length;i++){
				canpay=costs[i].CanPayAsCost();
			}
		}
		return canpay;
	}
	
	private void updatePlayableCards(boolean sorcerySpeed, Vector<Player> players){
		for(int i=0;i<this.hand.size();i++){
			CardBase ccard=this.hand.elementAt(i);
			if(!sorcerySpeed && ccard.IsSorcerySpeed()){
				ccard.Playable=false;
				continue;
			}
			if(ccard.IsLand()){
				if(this.landsPlayedThisTurn==0){
					ccard.Playable=true;
				}else{
					ccard.Playable=false;
				}
				continue;
			}else{
				ccard.Playable=true;
				ManaCost manacost=ccard.GetManaCost();
				if(manacost!=null){
					int[] possibleConvokedMana=new int[6];
					for(int cmi=0;cmi<possibleConvokedMana.length;cmi++)
						possibleConvokedMana[cmi]=0;
					if(ccard.GetKeywords().HasConvoke()){
						for(int fieldIndex=0;fieldIndex<this.field.size();fieldIndex++){
							CardBase cfieldcard=this.field.elementAt(fieldIndex);
							if(!cfieldcard.IsCreature()||cfieldcard.IsTapped())
								continue;
							this.getConvokedMana(cfieldcard, possibleConvokedMana);
						}
					}
					
					if(ccard.GetKeywords().HasDelve()){
						possibleConvokedMana[5]+=this.graveyard.size();
					}
				
					if(!manacost.MeetsCost(this.manapoolMountains+possibleConvokedMana[0], this.manapoolForests+possibleConvokedMana[1], 
					this.manapoolIslands+possibleConvokedMana[2], this.manapoolPlains+possibleConvokedMana[3],
					this.manapoolSwamps+possibleConvokedMana[4], this.manapoolColorless+possibleConvokedMana[5])){
						if(!ccard.GetKeywords().HasMorph()){
							ccard.Playable=false;
							continue;
						}else{
							ManaCost morphcost=new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS});
							if(!morphcost.MeetsCost(this.manapoolMountains+possibleConvokedMana[0], this.manapoolForests+possibleConvokedMana[1], 
							this.manapoolIslands+possibleConvokedMana[2], this.manapoolPlains+possibleConvokedMana[3],
							this.manapoolSwamps+possibleConvokedMana[4], this.manapoolColorless+possibleConvokedMana[5])){
								ccard.Playable=false;
								continue;
							}
						}
					}
				}

				if(!this.canPayNonmanaCosts(ccard.GetNonmanaPlayCosts())){
					ccard.Playable=false;
					continue;
				}
				
				if(!MagicEffect.HasValidTarget(ccard.GetPlayEffects(), players.toArray(new IPlayer[players.size()]), this.TheStack)){
					ccard.Playable=false;
					continue;
				}
			}
		}
	}
	
	private void updateActivatableCards(boolean sorcerySpeed, Vector<Player> players){
		for(int i=0;i<this.field.size();i++){
			CardBase ccard=this.field.elementAt(i);
			this.updateActivatableCard(ccard, sorcerySpeed, players);
		}

		for(int i=0;i<this.graveyard.size();i++){
			CardBase ccard=this.graveyard.elementAt(i);
			this.updateActivatableCard(ccard, sorcerySpeed, players);
		}
	}
	
	private void updateActivatableCard(CardBase ccard, boolean sorcerySpeed, Vector<Player> players){
		ccard.Activatable=false;
		MagicActivatedAbility[] abilities=ccard.GetActivatedAbilities();
		if(abilities==null)
			return;

		boolean canUseAnyAbility=false;
		boolean usedOneAbilityMaxTimes=false;
		for(int abilityIndex=0;abilityIndex<abilities.length;abilityIndex++){
			if(isActivatableAbility(abilities[abilityIndex], ccard, sorcerySpeed, players)){
				canUseAnyAbility=true;
			}else if(abilities[abilityIndex].UsedMaxTimes()){
				usedOneAbilityMaxTimes=true;
			}
		}
		
		ccard.Activatable=canUseAnyAbility;
		if(ccard.IsPlaneswalker()&&usedOneAbilityMaxTimes){
			ccard.Activatable=false;
		}
	}
	
	private boolean isActivatableAbility(MagicActivatedAbility maa, CardBase source, boolean sorcerySpeed, Vector<Player> players){
		if(maa.UsedMaxTimes()){
			return false;
		}
		
		if(!sorcerySpeed&&maa.IsSorcerySpeed()){
			return false;
		}
		
		if(!maa.GetSourceZones().IsValidZone(this.GetCardZone(source))){
			return false;
		}
		
		if(source.IsFaceDown() && (maa.Effects.length < 1 || !(maa.Effects[0] instanceof MagicEffectMorphTargetCreature))){
			return false;
		}
		
		ArrayList<ManaCost> additionalManaCosts=source.GetActivatedAbilityManaCost();
		ManaCost manacost=maa.ManaCosts;
		if(manacost!=null||additionalManaCosts.size()>0){
			ManaCost adjmanacost=null;
			if(manacost==null)
				adjmanacost=new ManaCost(new int[0]);
			else
				adjmanacost=manacost.DeepCopy();
			for(int i=0;i<additionalManaCosts.size();i++){
				adjmanacost.AddCost(additionalManaCosts.get(i));
			}
			if(!adjmanacost.MeetsCost(this.manapoolMountains, this.manapoolForests, 
			this.manapoolIslands, this.manapoolPlains, this.manapoolSwamps, this.manapoolColorless)){
				return false;
			}
		}

		if(!this.canPayNonmanaCosts(maa.Costs)){
			return false;
		}

		if(!MagicEffect.HasValidTarget(maa.Effects, players.toArray(new IPlayer[players.size()]), this.TheStack)){
			return false;
		}
		
		for(int i=0;i<maa.Effects.length;i++){
			if(maa.Effects[i] instanceof MagicEffectMorphTargetCreature){
				if(!((Creature)source).IsMorphed()){
					return false;
				}
			}
		}
		
		return true;
	}
	
	private void getConvokedMana(CardBase card, int[] runningTotalMana){
		int colorflags=card.GetColorFlags();
		if(colorflags==ManaCost.COLOR_FLAG_RED){
			runningTotalMana[0]++;
		}else if(colorflags==ManaCost.COLOR_FLAG_GREEN){
			runningTotalMana[1]++;
		}else if(colorflags==ManaCost.COLOR_FLAG_BLUE){
			runningTotalMana[2]++;
		}else if(colorflags==ManaCost.COLOR_FLAG_WHITE){
			runningTotalMana[3]++;
		}else if(colorflags==ManaCost.COLOR_FLAG_BLACK){
			runningTotalMana[4]++;
		}else{
			runningTotalMana[5]++;
		}
//System.out.println("RTM: "+runningTotalMana[0]+" "+runningTotalMana[1]+" "+runningTotalMana[2]+" "+runningTotalMana[3]+" "+runningTotalMana[4]+" "+runningTotalMana[5]);
	}
	
	private PlayerResponse handlePlayerAction(PlayerAction chosenAction, boolean sorcerySpeed, Vector<Player> players){
		int playCardIndex=chosenAction.getCardIndexToPlayFromHand();
		int cardIndex=chosenAction.getPermanentIndexToActivate();
		if(playCardIndex>=0&&playCardIndex<this.hand.size()){
			CardBase playCard=this.hand.elementAt(playCardIndex);
			if(playCard.Playable){
				ManaCost manacost=playCard.GetManaCost();
				int x=0;
				if(manacost!=null){
					if(manacost.GetXs()>0){
						x=(this.getTotalAvailableMana()-manacost.GetConvertedCost())/manacost.GetXs();
						manacost=manacost.DeepCopySetX(x);
					}
				}
				if(playCard.IsLand()){
					this.landsPlayedThisTurn++;
					this.removeCardFromHand(playCard);
					this.repositionCardsInHand();
					MagicEffect[] playEffects=new MagicEffect[]{new MagicEffectPlayLand(this, playCard)};
					playCard.SetFaceDown(false);
					return new PlayerResponse(playCard, manacost, playCard.GetNonmanaPlayCosts(), playEffects);
				}else{
					MagicEffect[] playEffects=playCard.GetPlayEffects();
					
					boolean hasValidTargets=true;
					if(playEffects==null){
						playEffects=new MagicEffect[]{new MagicEffectPlayPermanent(this, playCard)};
					}else{
						for(int pei=0;pei<playEffects.length;pei++){
							playEffects[pei].SetX(x);
							playEffects[pei].TargetData.SetX(x);
						}
						MagicEffect.UsePlayerInput(playEffects, chosenAction.GetTargetCardControllerIndexes(),
							chosenAction.GetTargetFieldIndexes(), this.hand, players);
						for(int effectIndex=0;hasValidTargets&&effectIndex<playEffects.length;effectIndex++){
							hasValidTargets=playEffects[effectIndex].TargetsCanBeTargeted();
							if(!hasValidTargets)
								System.out.println("Invalid target(s) for "+playEffects[effectIndex].GetRulesText());
						}

						for(int effectIndex=0;hasValidTargets&&effectIndex<playEffects.length;effectIndex++){
							for(int targetIndex=0;hasValidTargets&&playEffects[effectIndex].TargetData.GetTarget(targetIndex)!=null;targetIndex++){
								hasValidTargets=playEffects[effectIndex].TargetData.IsValidTarget(playEffects[effectIndex].TargetData.GetTarget(targetIndex));
							}
							if(!hasValidTargets){
								System.out.println("Invalid target(s) for "+playEffects[effectIndex].GetRulesText());	
							}
						}
					}

					if(hasValidTargets){
						int[] convokedIndexes=chosenAction.GetConvokedIndexes();
						if(convokedIndexes!=null){
							int[] convokedMana=new int[6];
							for(int i=0;i<convokedMana.length;i++)
								convokedMana[i]=0;

							for(int convokeIndex=0;convokeIndex<convokedIndexes.length;convokeIndex++){
								//TODO: Validate target index
								this.getConvokedMana(this.field.elementAt(convokedIndexes[convokeIndex]), convokedMana);
							}
							
							this.convokedMountains+=convokedMana[0];
							this.convokedForests+=convokedMana[1];
							this.convokedIslands+=convokedMana[2];
							this.convokedPlains+=convokedMana[3];
							this.convokedSwamps+=convokedMana[4];
							this.convokedColorless+=convokedMana[5];
						}
						
						int[] delvedIndexes=chosenAction.GetDelvedIndexes();
						if(delvedIndexes!=null){
							this.convokedColorless+=delvedIndexes.length;
						}
						
						MagicEffect[] nonmanacosts=playCard.GetNonmanaPlayCosts();
						
						boolean morphPlay=false;
						if(playCard.GetKeywords().HasMorph()&&this.playerInterface.ChooseYesOrNo("Do you want to play this creature as a face-down 2/2 creature for 3?")){
							morphPlay=true;
							manacost=new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS});
							nonmanacosts=null;
						}

						if(manacost.MeetsCost(this.manapoolMountains+this.convokedMountains, this.manapoolForests+this.convokedForests, 
						this.manapoolIslands+this.convokedIslands, this.manapoolPlains+this.convokedPlains, this.manapoolSwamps+this.convokedSwamps,
						this.manapoolColorless+this.convokedColorless)){
							if(convokedIndexes!=null){
								for(int convokeIndex=0;convokeIndex<convokedIndexes.length;convokeIndex++){
									//TODO: MagicEffect for triggered abilities
									this.field.elementAt(convokedIndexes[convokeIndex]).Tap();
								}
							}
							
							if(delvedIndexes!=null){
								ArrayList<CardBase> delvecards=new ArrayList<CardBase>();
								for(int delveIndex=0;delveIndex<delvedIndexes.length;delveIndex++){
									delvecards.add(this.graveyard.elementAt(delvedIndexes[delveIndex]));
								}
								for(int delveIndex=0;delveIndex<delvedIndexes.length;delveIndex++){
									//TODO: MagicEffect for triggered abilities
									this.PlaceCardInExile(delvecards.get(delveIndex), players);
								}
							}

							playCard.Playable=false;
							this.removeCardFromHand(playCard);
							this.repositionCardsInHand();

							if(morphPlay){
								((Creature)playCard).SetMorphed();
							}else{
								playCard.SetFaceDown(false);
							}

							return new PlayerResponse(playCard, manacost, nonmanacosts, playEffects);
						}else{
							this.convokedMountains=0;
							this.convokedForests=0;
							this.convokedIslands=0;
							this.convokedPlains=0;
							this.convokedSwamps=0;
							this.convokedColorless=0;
							System.out.println("Invalid convoke/delve/morph attempt.");
						}
					}else{
						for(int effectIndex=0;effectIndex<playEffects.length;effectIndex++){
							for(int targetIndex=0;playEffects[effectIndex].TargetData.GetTarget(targetIndex)!=null;targetIndex++){
								playEffects[effectIndex].TargetData.Reset();
							}
						}
					}
				}
			}else{
				System.out.println("Hand index not Playable: "+playCardIndex);
			}
		}else if(cardIndex>=0&&cardIndex<this.field.size()){
			CardBase activateCard=null;
			switch(chosenAction.GetCardZone()){
			case ZoneOptions.BATTLEFIELD:
				activateCard=this.field.elementAt(cardIndex);
			break;
			case ZoneOptions.GRAVEYARD:
				activateCard=this.graveyard.elementAt(cardIndex);
			break;
			}
			if(activateCard.Activatable){
				int abilityIndex=chosenAction.getPermanentAbilityIndexToActivate();
				MagicActivatedAbility[] abilityOptions=activateCard.GetActivatedAbilities();
				if(abilityIndex>=0&&abilityIndex<abilityOptions.length){
					ArrayList<ManaCost> additionalManaCosts=activateCard.GetActivatedAbilityManaCost();
					if(isActivatableAbility(abilityOptions[abilityIndex], activateCard, sorcerySpeed, players)){
						MagicEffect.UsePlayerInput(abilityOptions[abilityIndex].Effects, chosenAction.GetTargetCardControllerIndexes(),
							chosenAction.GetTargetFieldIndexes(), this.hand, players);
						MagicEffect.UsePlayerInput(abilityOptions[abilityIndex].Costs, chosenAction.GetTargetCardControllerCostIndexes(),
							chosenAction.GetTargetFieldCostIndexes(), this.hand, players);
						boolean hasValidTargets=true;
						for(int effectIndex=0;hasValidTargets&&effectIndex<abilityOptions[abilityIndex].Effects.length;effectIndex++){
							hasValidTargets=abilityOptions[abilityIndex].Effects[effectIndex].TargetsCanBeTargeted();
							if(!hasValidTargets)
								System.out.println("Invalid target(s) for "+abilityOptions[abilityIndex].Effects[effectIndex].GetRulesText());
						}

						if(hasValidTargets){
							abilityOptions[abilityIndex].UsedAbility();
							ManaCost abilityManaCosts=null;
							if(abilityOptions[abilityIndex].ManaCosts!=null){
								abilityManaCosts=abilityOptions[abilityIndex].ManaCosts.DeepCopy();
							}else if(additionalManaCosts.size()>0){
								abilityManaCosts=new ManaCost(new int[0]);
							}
							if(abilityManaCosts!=null){
								int x=0;
								if(abilityManaCosts.GetXs()>0){
									x=(this.getTotalAvailableMana()-abilityManaCosts.GetConvertedCost())/abilityManaCosts.GetXs();
									abilityManaCosts=abilityOptions[abilityIndex].ManaCosts.DeepCopySetX(x);
									for(int aei=0;aei<abilityOptions[abilityIndex].Effects.length;aei++){
										abilityOptions[abilityIndex].Effects[aei].SetX(x);
										abilityOptions[abilityIndex].Effects[aei].TargetData.SetX(x);
									}
								}
								for(int amci=0;amci<additionalManaCosts.size();amci++){
									abilityManaCosts.AddCost(additionalManaCosts.get(amci));
								}
							}
							return new PlayerResponse(null,	abilityManaCosts, abilityOptions[abilityIndex].Costs, abilityOptions[abilityIndex].Effects);
						}
					}else{
						System.out.println("This ability can't be used: "+abilityIndex);
					}
				}else{
					System.out.println("Invalid ability index: "+abilityIndex);
				}
			}else{
				System.out.println("Permanent index not Activatable: "+cardIndex+" in zone "+chosenAction.GetCardZone());
			}
		}
		
		return null;
	}
	
	private void repositionCardsInHand(){
		int cx=this.areaMinX+CardBase.WIDTH+4;
		for(int i=0;i<this.hand.size();i++){
			this.hand.elementAt(i).SetAnimationDestination(cx, this.handY, OptionsScreen.AnimationSpeed);
			cx+=CardBase.WIDTH+2;
		}
	}
	
	private void repositionCardsOnField(){
		int cx=this.areaMinX+CardBase.WIDTH+4;
		int ny=this.onBottom?this.areaMinY+10:this.areaMaxY-10-CardBase.HEIGHT;
		for(int i=0;i<this.field.size();i++){
			CardBase ccard=this.field.elementAt(i);
			if(!ccard.IsAttached()){
				ccard.SetAnimationDestination(cx, ny, OptionsScreen.AnimationSpeed);
				int attachments=1;
				if(ccard.IsCreature()){
					Creature creatureCard=((Creature)ccard);
					for(int equipmentIndex=0;equipmentIndex<creatureCard.Equipments.size();equipmentIndex++){
						creatureCard.Equipments.get(equipmentIndex).SetAnimationDestination(cx, ny-5*attachments++, OptionsScreen.AnimationSpeed);						
					}
				}
				for(int auraIndex=0;auraIndex<ccard.Auras.size();auraIndex++){
					ccard.Auras.get(auraIndex).SetAnimationDestination(cx, ny-5*attachments++, OptionsScreen.AnimationSpeed);
				}
				cx+=CardBase.WIDTH+2;
			}
		}
	}

	public void handleNewBattle(Vector<Player> players){
		this.startingLifePoints=20;
		this.lifePoints=this.startingLifePoints;
		this.manapoolMountains=0;
		this.manapoolForests=0;
		this.manapoolIslands=0;
		this.manapoolPlains=0;
		this.manapoolSwamps=0;
		this.manapoolColorless=0;
		int initialHandSize=7;
		do{
			for(int i=this.hand.size()-1;i>=0;i--){
				CardBase ccard=this.hand.elementAt(i);
				this.removeCardFromHand(ccard);
				this.deck.PlaceCardOnBottom(ccard);
			}
			this.deck.Shuffle();
			this.DrawXCards(initialHandSize--);
		}while(initialHandSize>0&&this.playerInterface.wantsToMulligan(this.generateBattleState(players)));
	}
	
	public void AddManaToPool(int mountains, int forests, int islands, int plains, int swamps, int colorless){
		if(mountains<0){
			if(this.convokedMountains<=-mountains){
				mountains+=this.convokedMountains;
			}else{
				mountains=0;
			}
			this.convokedMountains=0;
		}
	
		if(forests<0){
			if(this.convokedForests<=-forests){
				forests+=this.convokedForests;
			}else{
				forests=0;
			}
			this.convokedForests=0;
		}

		if(islands<0){
			if(this.convokedIslands<=-islands){
				islands+=this.convokedIslands;
			}else{
				islands=0;
			}
			this.convokedIslands=0;
		}

		if(plains<0){
			if(this.convokedPlains<=-plains){
				plains+=this.convokedPlains;
			}else{
				plains=0;
			}
			this.convokedPlains=0;
		}

		if(swamps<0){
			if(this.convokedSwamps<=-swamps){
				swamps+=this.convokedSwamps;
			}else{
				swamps=0;
			}
			this.convokedSwamps=0;
		}

		if(colorless<0){
			if(this.convokedColorless<=-colorless){
				colorless+=this.convokedColorless;
			}else{
				colorless=0;
			}
			this.convokedColorless=0;
		}

		this.manapoolMountains+=mountains;
		this.manapoolForests+=forests;
		this.manapoolIslands+=islands;
		this.manapoolPlains+=plains;
		this.manapoolSwamps+=swamps;
		this.manapoolColorless+=colorless;
	}
	
	public void timerStarted(Vector<Player> players, Stack<MagicStackElement> thestack){
		this.updatePlayableCards(false, players);
		this.updateActivatableCards(false, players);
		this.playerInterface.timerStarted(this.generateBattleState(players), thestack);
	}
	
	public PlayerResponse handleInstants(Vector<Player> players){
		PlayerAction chosenAction=this.playerInterface.handleInstants();
		if(chosenAction.isNoAction()){
			return null;
		}else if(chosenAction.isTimerPauseAction()){
			return new PlayerResponse(null, null, null, new MagicEffect[]{new MagicEffectToggleTimerPause(this)});
		}else if(chosenAction.isTimerSkipAction()){
			return new PlayerResponse(null, null, null, new MagicEffect[]{new MagicEffectTimerSkip(this)});
		}else{
			return this.handlePlayerAction(chosenAction, false, players);
		}
	}
	
	public ArrayList<MagicEffect[]> handleStateBasedActions(Vector<Player> players, ArrayList<Planeswalker> ajanis){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		ArrayList<String> legendarys=new ArrayList<String>();
		ArrayList<String> legendarysToBury=new ArrayList<String>();
		for(int i=0;i<this.field.size();i++){
			CardBase ccard=this.field.elementAt(i);
			ccard.handleStateBasedActions(players);
			if(ccard.IsAura()&&!((Enchantment)ccard).IsAttachedToValidTarget()){
				triggeredEffects.addAll(this.DestroyPermanent(ccard, players));
			}
			
			if(ccard.IsLegendary()){
				if(legendarys.contains(ccard.GetName())){
					legendarysToBury.add(ccard.GetName());
				}else{
					legendarys.add(ccard.GetName());
				}
			}
			
			if(ccard.IsCreature()){
				Creature c=(Creature)ccard;
				if(c.IsDestroyed()){
					triggeredEffects.addAll(this.DestroyPermanent(c, players));
					i--;
				}
			}else if(ccard.IsPlaneswalker()){
				Planeswalker p=(Planeswalker)ccard;
				if(p.GetSubtypes().HasSubtype(SubtypeCollection.AJANI)){
					ajanis.add(p);
				}
				if(p.IsDestroyed()){
					triggeredEffects.addAll(this.DestroyPermanent(p, players));
					i--;
				}
			}
		}
		
		for(int legendaryIndex=legendarysToBury.size()-1;legendaryIndex>=0;legendaryIndex--){
			for(int legendaryFieldIndex=this.field.size()-1;legendaryFieldIndex>=0;legendaryFieldIndex--){
				CardBase clfield=this.field.elementAt(legendaryFieldIndex);
				if(clfield.GetName().equals(legendarysToBury.get(legendaryIndex))){
					triggeredEffects.addAll(this.PlaceCardInGraveyard(clfield, players));
				}
			}
		}
		for(int i=this.hand.size()-1;i>=0;i--){
			CardBase chandcard=this.hand.get(i);
			if(chandcard.IsToken()){
				this.removeCardFromHand(chandcard);
			}
		}
		
		for(int i=this.graveyard.size()-1;i>=0;i--){
			if(this.graveyard.get(i).IsToken()){
				this.graveyard.remove(i);
			}
		}

		ArrayList<CardBase> exiles=this.exile.GetCards();
		for(int i=exiles.size()-1;i>=0;i--){
			CardBase cexilecard=exiles.get(i);
			if(cexilecard.IsToken()){
				this.exile.remove(cexilecard);
			}
		}

		this.repositionCardsInHand();
		this.repositionCardsOnField();
		
		return triggeredEffects;
	}
	
	public void timerEnded(){
		this.playerInterface.timerEnded();
		for(int i=0;i<this.hand.size();i++){
			this.hand.elementAt(i).Playable=false;
			this.hand.elementAt(i).Activatable=false;
		}
		for(int i=0;i<this.field.size();i++){
			this.field.elementAt(i).Playable=false;
			this.field.elementAt(i).Activatable=false;
		}
	}

	public void handleUntapPhase(Player playerWhoseTurnItIs, Vector<Player> players){
		this.lostLifeLastTurn=this.lostLifeThisTurn;
		this.lostLifeThisTurn=false;
		this.attackedThisTurn=false;

		if(playerWhoseTurnItIs!=this)
			return;

		for(int i=0;i<this.field.size();i++){
			CardBase ccard=this.field.elementAt(i);
			if(ccard.UntapsDuringUpkeep()){
				ccard.Untap();
			}else{
				ccard.HandleSkippedUntapping();
			}
		}
	}
	
	public ArrayList<MagicEffect[]> handleUpkeepPhase(Player playerWhoseTurnItIs, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.field.size();i++){
			CardBase fieldcard=this.field.elementAt(i);
			this.handleTriggeredEffectsTargets(triggeredEffects, fieldcard.TriggerUpkeep(playerWhoseTurnItIs, players), players, i);
			if(playerWhoseTurnItIs==this){
				fieldcard.GetKeywords().RemoveSummoningSickness();
			}
		}
		
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> TriggerSpellCast(CardBase cardCast, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.field.size();i++){
			CardBase fieldcard=this.field.elementAt(i);
			this.handleTriggeredEffectsTargets(triggeredEffects, fieldcard.TriggerSpellCast(cardCast, players), players, i);
		}		
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> TriggerMorph(CardBase cardMorphed, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.field.size();i++){
			this.handleTriggeredEffectsTargets(triggeredEffects, this.field.get(i).TriggerMorph(cardMorphed, players), players, i);
		}
		return triggeredEffects;
	}
	
	public void handleDrawPhase(){
		this.landsPlayedThisTurn=0;
		this.DrawXCards(1);
	}
	
	public PlayerResponse handleMainPhaseOne(Vector<Player> players){
		PlayerAction chosenAction=PlayerAction.CreateNoAction();
		while(!chosenAction.isPassAction()){
			this.updatePlayableCards(true, players);
			this.updateActivatableCards(true, players);
			BattleState passedBattleState=this.generateBattleState(players);
			chosenAction=this.playerInterface.handleMainPhaseOne(passedBattleState);
			PlayerResponse ability=this.handlePlayerAction(chosenAction, true, players);
			if(ability!=null)
				return ability;
		}
	
		return null;
	}
	
	public PlayerResponse handleMainPhaseTwo(Vector<Player> players){
		for(int i=0;i<this.field.size();i++){
			if(this.field.elementAt(i).IsCreature()){
				Creature c=(Creature)this.field.elementAt(i);
				c.TargetedPlayer=null;
				c.GetBlockers().clear();
			}
		}

		for(int defendingPlayerIndex=0;defendingPlayerIndex<players.size();defendingPlayerIndex++){
			if(defendingPlayerIndex==this.myIndex)
				continue;
			players.elementAt(defendingPlayerIndex).UpdateBlockingCreatures(players);
		}

		return this.handleMainPhaseOne(players);
	}

	public PlayerResponse PayManaCosts(Vector<Player> players, ManaCost cost){
		PlayerAction chosenAction=PlayerAction.CreateNoAction();
		while(!chosenAction.isPassAction()){
			for(int i=0;i<this.hand.size();i++){
				this.hand.elementAt(i).Playable=false;
			}
			this.updateActivatableCards(false, players);

			BattleState passedBattleState=this.generateBattleState(players);
			chosenAction=this.playerInterface.PayManaCosts(passedBattleState, cost);
			PlayerResponse ability=this.handlePlayerAction(chosenAction, false, players);
			if(ability!=null)
				return ability;
		}
	
		return null;
	}
	
	public boolean declareAttackers(Vector<Player> players){
		for(int i=0;i<this.hand.size();i++){
			this.hand.elementAt(i).Playable=false;
			this.hand.elementAt(i).Activatable=false;
		}
		
		boolean noAvailableAttackers=true;
		for(int i=0;i<this.field.size();i++){
			CardBase currentFieldCard=this.field.elementAt(i);
			currentFieldCard.Activatable=false;
			if(currentFieldCard.IsCreature()){
				if(((Creature)currentFieldCard).CanAttack()){
					currentFieldCard.Activatable=true;
					noAvailableAttackers=false;
				}
			}
		}
		
		if(noAvailableAttackers)
			return false;
		
		PlayerAction chosenAction=PlayerAction.CreateNoAction();
		while(!chosenAction.isPassAction()){
			chosenAction=this.playerInterface.handleAttackDecision(this.generateBattleState(players));
			int attackingCreatureIndex=chosenAction.getAttackingCreatureIndex();
			int attackedPlayerIndex=chosenAction.getAttackedPlayerIndex();
			int attackedPlaneswalkerIndex=-1;
			if(attackingCreatureIndex==-1||attackedPlayerIndex==-1){
				attackingCreatureIndex=chosenAction.getAttackingPlaneswalkerCreatureIndex();
				attackedPlayerIndex=chosenAction.getAttackedPlaneswalkerControllerIndex();
				attackedPlaneswalkerIndex=chosenAction.getAttackedPlaneswalkerIndex();
			}
			
			if(attackingCreatureIndex>=0){//TODO: check CanAttack()
				Creature attacker=(Creature)this.field.elementAt(attackingCreatureIndex);
				if(attackedPlayerIndex>=0){
					Player attackedPlayer=players.elementAt(attackedPlayerIndex);
					if(attackedPlayer!=this){
						if(attackedPlaneswalkerIndex<0){
							attacker.TargetedPlayer=attackedPlayer;
						}else{
							attacker.TargetedPlayer=attackedPlayer.GetField().elementAt(attackedPlaneswalkerIndex);
						}
						if(!attacker.GetKeywords().HasVigilance())
							attacker.Tap();
					}else{
						attacker.TargetedPlayer=null;
						attacker.Untap();
					}
				}
			}
		}
		
		for(int i=0;i<this.field.size();i++){
			if(this.field.elementAt(i).IsCreature()){
				Creature c=(Creature)this.field.elementAt(i);
				if(c.isAttacking()){
					this.attackedThisTurn=true;
					return true;
				}
			}
		}
		return false;
	}
	
	public void declareBlockers(Vector<Player> players, Creature[] attackers){
		for(int i=0;i<this.hand.size();i++){
			this.hand.elementAt(i).Playable=false;
			this.hand.elementAt(i).Activatable=false;
		}
		boolean noAvailableBlockers=true;
		for(int i=0;i<this.field.size();i++){
			CardBase currentFieldCard=this.field.elementAt(i);
			if(currentFieldCard.IsCreature()){
				if(!currentFieldCard.IsTapped()){//TODO: check CanBlock()
					currentFieldCard.Activatable=true;
					noAvailableBlockers=false;
				}
			}else{
				currentFieldCard.Activatable=false;
			}
		}
		
		if(noAvailableBlockers)
			return;

		Vector<Creature> attackingme=new Vector<Creature>();
		for(int i=0;i<attackers.length;i++){
			if(attackers[i].TargetedPlayer==this||this.field.contains(attackers[i].TargetedPlayer))
				attackingme.add(attackers[i]);
		}
		
		if(attackingme.size()==0)
			return;
			
		PlayerAction chosenAction=PlayerAction.CreateNoAction();
		while(!chosenAction.isPassAction()){
			chosenAction=this.playerInterface.declareBlockers(this.generateBattleState(players), attackingme.toArray(new Creature[0]));
			int defenderIndex=chosenAction.getDefendingCreatureIndex();
			if(defenderIndex>=0){
				Creature defendingCreature=(Creature)this.field.elementAt(defenderIndex);
				int maxblocks=defendingCreature.GetMaxBlocks();
				int blocks=0;
				int attackerIndex=chosenAction.getAttackingCreatureToBlockIndex();
				for(int aindex=0;aindex<attackingme.size();aindex++){
					Creature cattacker=attackingme.elementAt(aindex);
					if(cattacker.GetBlockers().contains(defendingCreature))
						blocks++;
					if(attackerIndex<0||blocks>=maxblocks||aindex==attackerIndex){
						cattacker.removeBlocker(defendingCreature);
					}
				}
				if(attackerIndex>=0){
					Creature attackerToBlock=attackingme.elementAt(attackerIndex);
					if((!attackerToBlock.GetKeywords().HasFlying()||defendingCreature.GetKeywords().HasFlying()||defendingCreature.GetKeywords().HasReach())
					&&!attackerToBlock.GetKeywords().HasUnblockable()
					&&attackerToBlock.CanBeBlockedBy(defendingCreature)){
						attackerToBlock.addBlocker(defendingCreature);
					}
				}
			}
		}
	}
	
	public Creature[] GetAttackingCreatures(){
		Vector<Creature> attackers=new Vector<Creature>();
		for(int i=0;i<this.field.size();i++){
			if(this.field.elementAt(i).IsCreature()){
				Creature c=(Creature)this.field.elementAt(i);
				if(c.isAttacking()){
					attackers.add(c);
				}
			}
		}
		return attackers.toArray(new Creature[attackers.size()]);
	}
	
	public Creature[] GetBlockingCreatures(Creature[] attackers){
		ArrayList<Creature> blockers=new ArrayList<Creature>();
		for(int i=0;i<attackers.length;i++){
			Vector<Creature> attackerBlockers=attackers[i].GetBlockers();
			for(int abi=0;abi<attackerBlockers.size();abi++){
				Creature ccreature=attackerBlockers.elementAt(abi);
				if(this.field.contains(ccreature)){
					blockers.add(ccreature);
				}
			}
		}
		return blockers.toArray(new Creature[0]);
	}

	public void UpdateBlockingCreatures(Vector<Player> players){
		for(int i=0;i<this.field.size();i++){
			if(this.field.elementAt(i).IsCreature()){
				((Creature)this.field.elementAt(i)).UpdateBlocking(players);
			}
		}
	}
	
	public int[] GetDamageOrder(Creature attacker){
		Creature[] defenders=new Creature[attacker.GetBlockers().size()];
		for(int i=0;i<defenders.length;i++){
			defenders[i]=attacker.GetBlockers().elementAt(i).DeepCopy();
		}
		int[] order=new int[defenders.length];
		this.playerInterface.OrderCards(defenders, order);
		return order;
	}
	
	public ArrayList<MagicEffect[]> handleHandLimit(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		while(this.hand.size()>this.handLimit){
			CardBase[] deepCopyHand=new CardBase[this.hand.size()];
			for(int i=0;i<deepCopyHand.length;i++)
				deepCopyHand[i]=this.hand.elementAt(i).DeepCopy();
			int cardsToDiscardCount=this.hand.size()-this.handLimit;
			int[] discardIndexes=this.playerInterface.ChooseCards(deepCopyHand, cardsToDiscardCount, cardsToDiscardCount, "Choose which cards to discard.");
			CardBase[] cardsToDiscard=new CardBase[discardIndexes.length];
			for(int i=0;i<discardIndexes.length;i++){
				cardsToDiscard[i]=this.hand.elementAt(discardIndexes[i]);
			}
			for(int i=0;i<cardsToDiscard.length;i++){
				this.removeCardFromHand(cardsToDiscard[i]);
				triggeredEffects.addAll(this.PlaceCardInGraveyard(cardsToDiscard[i], players));
			}
		}
		return triggeredEffects;
	}
	

	public ArrayList<MagicEffect[]> handleEndPhase(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.field.size();i++){
			triggeredEffects.addAll(this.field.elementAt(i).EndPhase(players));
		}
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> handleCleanupStep(Vector<Player> players){
		this.manapoolMountains=0;
		this.manapoolForests=0;
		this.manapoolIslands=0;
		this.manapoolPlains=0;
		this.manapoolSwamps=0;
		this.manapoolColorless=0;
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.effectsToUndoAtEndOfTurn.size();i++){
			triggeredEffects.addAll(this.effectsToUndoAtEndOfTurn.get(i).Undo(players));
		}
		this.effectsToUndoAtEndOfTurn.clear();

		for(int i=0;i<this.field.size();i++){
			triggeredEffects.addAll(this.field.elementAt(i).CleanupPhase(players));
		}
		return triggeredEffects;
	}
	
	public void updateBattleState(Vector<Player> players){
		this.playerInterface.updateBattleState(this.generateBattleState(players));
	}
	
	public boolean WantsToTriggerEffect(MagicEffect effect){
		return this.playerInterface.WantsToTriggerEffect(effect);
	}
	
	public ArrayList<MagicEffect[]> PlacePermanentOnField(CardBase permanent, Vector<Player> players){
		this.removeCardFromHand(permanent);
		this.graveyard.remove(permanent);
		this.exile.remove(permanent);
		this.deck.RemoveCard(permanent);
		if(permanent.IsAura()||permanent.IsArtifact())
			this.field.add(0, permanent);
		else
			this.field.add(permanent);
		this.repositionCardsOnField();
		
		permanent.HandleEntersTheBattlefieldModifications(players);
		
		if(permanent.IsCreature()&&!permanent.GetKeywords().HasHaste()){
			permanent.GetKeywords().AddSummoningSickness();
		}
	
		//Allow Constricting Sliver to give other slivers enter the battlefield triggers
		ArrayList<Planeswalker> fakeit=new ArrayList<Planeswalker>();
		for(int p=0;p<players.size();p++){
			players.elementAt(p).handleStateBasedActions(players, fakeit);
		}

		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int p=0;p<players.size();p++){
			triggeredEffects.addAll(players.elementAt(p).TriggerEntersTheBattlefield(permanent, players));
		}
		return triggeredEffects;
	}
	
	public void PlaceCardInHand(CardBase card){
		this.field.remove(card);
		this.graveyard.remove(card);
		this.exile.remove(card);
		this.deck.RemoveCard(card);
		this.addCardToHand(card);
		this.repositionCardsInHand();
	}
	
	public ArrayList<MagicEffect[]> PlaceCardInGraveyard(CardBase permanent, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		if(this.field.contains(permanent)){
			triggeredEffects.addAll(permanent.Destroy(players));
		}
		this.removeCardFromHand(permanent);
		this.field.remove(permanent);
		this.exile.remove(permanent);
		this.deck.RemoveCard(permanent);
		permanent.SetFaceDown(false);
		this.graveyard.add(permanent);
		permanent.SetAnimationDestination(this.deck.GetX(), this.onBottom?this.deck.GetY()-CardBase.HEIGHT-2:(this.deck.GetY()+this.deck.GetHeight()+2), OptionsScreen.AnimationSpeed);
		triggeredEffects.addAll(this.DestroyAttachedAuras(permanent, players));
		return triggeredEffects;
	}

	public ArrayList<MagicEffect[]> PlaceCardInExile(CardBase card, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		int oldzone=this.GetCardZone(card);
		if(oldzone==0)
			return triggeredEffects;
		this.removeCardFromHand(card);
		this.graveyard.remove(card);
		this.field.remove(card);
		this.deck.RemoveCard(card);
		this.exile.add(oldzone, card);
		card.SetAnimationDestination(this.exileX, this.exileY, OptionsScreen.AnimationSpeed);
		triggeredEffects.addAll(this.DestroyAttachedAuras(card, players));
		triggeredEffects.addAll(card.Destroy(players));
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> AttachEnchantmentToPermanent(Enchantment enchantment, CardBase permanent, Vector<Player> players){
		if(this.field.contains(permanent)){
			permanent.AddEnchantmentAura(enchantment);
		}else{
			boolean hasBeenAttached=false;
			for(int p=0;!hasBeenAttached&&p<players.size();p++){
				Vector<CardBase> oplayerfield=players.elementAt(p).GetField();
				for(int f=0;!hasBeenAttached&&f<oplayerfield.size();f++){
					CardBase cfieldcard=oplayerfield.elementAt(f);
					if(cfieldcard==permanent){
						permanent.AddEnchantmentAura(enchantment);
						hasBeenAttached=true;
					}
				}
			}
		}
		ArrayList<MagicEffect[]> triggeredEffects=this.PlacePermanentOnField(enchantment, players);
		return triggeredEffects;
	}
	
	public void EquipCreature(Artifact equipment, Creature target, Vector<Player> players){
		if(!this.field.contains(target)||!this.field.contains(equipment)){
			return;
		}
		
		int afterLastAuraIndex=0;
		while(this.field.get(afterLastAuraIndex).IsAura())
			afterLastAuraIndex++;
		
		this.field.remove(equipment);
		this.field.add(afterLastAuraIndex, equipment);
		target.Equip(equipment, players);
	}
	
	public void AddDamagePreventor(DamagePreventor dmgprev){
		this.damagePreventors.AddDamagePreventor(dmgprev);
	}
	
	public void RemoveDamagePreventor(DamagePreventor dmgprev){
		this.damagePreventors.RemoveDamagePreventor(dmgprev);
	}

	public boolean IsCardOnField(CardBase card){
		return this.field.contains(card);
	}
	
	public MagicEffect[] GetManaCostAsMagicEffects(ManaCost costs){
		return costs.GetCostAsMagicEffects(this, this.manapoolMountains+this.convokedMountains,
			this.manapoolForests+this.convokedForests, this.manapoolIslands+this.convokedIslands, this.manapoolPlains+this.convokedPlains,
			this.manapoolSwamps+this.convokedSwamps, this.manapoolColorless+this.convokedColorless);
	}
	
	public void DrawXCards(int amount){
		for(int i=0;i<amount;i++){
			CardBase card=this.deck.DrawCard();
			if(card!=null){
				this.addCardToHand(card);
			}
		}
		this.repositionCardsInHand();
	}
	
	public ArrayList<MagicEffect[]> DestroyPermanent(CardBase fieldcard, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		if(!this.field.contains(fieldcard))
			return triggeredEffects;
		if(fieldcard.GetKeywords().HasIndestructable()){
			if(!fieldcard.IsCreature())
				return triggeredEffects;
				
			if(!((Creature)fieldcard).IsDestroyed())
				return triggeredEffects;
		}
			
		triggeredEffects.addAll(this.PlaceCardInGraveyard(fieldcard, players));
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> DestroyAttachedAuras(CardBase fieldcard, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=fieldcard.Auras.size()-1;i>=0;i--){
			Enchantment caura=fieldcard.Auras.get(i);
			for(int p=0;p<players.size();p++)
				triggeredEffects.addAll(players.elementAt(p).DestroyPermanent(caura, players));
		}
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> UnExileCard(CardBase card, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		if(!this.exile.contains(card))
			return triggeredEffects;
			
		int oldzone=this.exile.GetOldZoneForCard(card);
		switch(oldzone){
		case ZoneOptions.HAND:
			this.PlaceCardInHand(card);
		break;
		case ZoneOptions.BATTLEFIELD:
			triggeredEffects.addAll(this.PlacePermanentOnField(card, players));
		break;
		case ZoneOptions.GRAVEYARD:
			triggeredEffects.addAll(this.PlaceCardInGraveyard(card, players));
		break;
		case ZoneOptions.EXILE:
			System.out.println("Exiled from exile?");
		break;
		case ZoneOptions.LIBRARY:
			System.out.println("This card is supposed to unexile back to the library?");
		break;
		default:
			System.out.println("Unknown zone to return to: "+oldzone);
		break;
		}
		
		return triggeredEffects;
	}
	
	public int GetCardZone(CardBase card){
		if(this.field.contains(card)){
			return ZoneOptions.BATTLEFIELD;
		}else if(this.hand.contains(card)){
			return ZoneOptions.HAND;
		}else if(this.graveyard.contains(card)){
			return ZoneOptions.GRAVEYARD;
		}else if(this.exile.contains(card)){
			return ZoneOptions.EXILE;
		}else if(this.deck.HasCard(card)){
			return ZoneOptions.LIBRARY;
		}else{
			return ZoneOptions.NONE;
		}
	}
	
	public CardBase[] ChooseCards(TargetInfo cardRequirements){
		ArrayList<CardBase> allCardOptions=new ArrayList<CardBase>();
		ArrayList<CardBase> deepCopyChoices=new ArrayList<CardBase>();
		
		boolean somethingBesidesLibrary=false;
		if(cardRequirements.IsValidZone(ZoneOptions.HAND)){
			somethingBesidesLibrary=true;
			for(int i=0;i<this.hand.size();i++){
				CardBase ccard=this.hand.get(i);
				if(cardRequirements.IsValidTarget(ccard)){
					allCardOptions.add(ccard);
				}
			}
		}
		if(cardRequirements.IsValidZone(ZoneOptions.BATTLEFIELD)){
			somethingBesidesLibrary=true;
			for(int i=0;i<this.field.size();i++){
				CardBase ccard=this.field.get(i);
				if(cardRequirements.IsValidTarget(ccard)){
					allCardOptions.add(ccard);
				}
			}
		}
		if(cardRequirements.IsValidZone(ZoneOptions.GRAVEYARD)){
			somethingBesidesLibrary=true;
			for(int i=0;i<this.graveyard.size();i++){
				CardBase ccard=this.graveyard.get(i);
				if(cardRequirements.IsValidTarget(ccard)){
					allCardOptions.add(ccard);
				}
			}
		}
		if(cardRequirements.IsValidZone(ZoneOptions.EXILE)){
			somethingBesidesLibrary=true;
			ArrayList<CardBase> exiledCards=this.exile.GetCards();
			for(int i=0;i<exiledCards.size();i++){
				CardBase ccard=exiledCards.get(i);
				if(cardRequirements.IsValidTarget(ccard)){
					allCardOptions.add(ccard);
				}
			}
		}
		boolean shuffleDeck=false;
		if(cardRequirements.IsValidZone(ZoneOptions.LIBRARY)){
			if(!somethingBesidesLibrary||this.playerInterface.ChooseYesOrNo("Do you want to search your library? It will be shuffled if you do.")){
				allCardOptions.addAll(this.deck.GetCards(cardRequirements));
				shuffleDeck=true;
			}
		}
		
		if(allCardOptions.size()==0)
			return null;
		if(allCardOptions.size()<cardRequirements.GetRequiredTargetCount())
			cardRequirements.UpdateRequiredTargets(allCardOptions.size());
		
		for(int i=0;i<allCardOptions.size();i++){
			deepCopyChoices.add(allCardOptions.get(i).DeepCopy());
		}
		
		int[] chosenIndexes=this.playerInterface.ChooseCards(deepCopyChoices.toArray(new CardBase[0]), cardRequirements.GetRequiredTargetCount(), cardRequirements.GetMaxTargetCount(), "Choose");
		CardBase[] chosenCards=new CardBase[chosenIndexes.length];
		for(int i=0;i<chosenIndexes.length;i++){
			chosenCards[i]=allCardOptions.get(chosenIndexes[i]);
		}
		if(shuffleDeck)
			this.deck.Shuffle();
		return chosenCards;
	}
	
	public ArrayList<MagicEffect[]> AttackOpponentWithCreature(Vector<Player> players, Creature attacker){
		PlayerAction chosenAction=this.playerInterface.ChoosePlayerIndex(this.generateBattleState(players), this.myIndex, "Choose an opponent to attack.");
		int attackedPlayerIndex=chosenAction.getAttackedPlayerIndex();
		int attackedPlaneswalkerIndex=-1;
		if(attackedPlayerIndex==-1){
			attackedPlayerIndex=chosenAction.getAttackedPlaneswalkerControllerIndex();
			attackedPlaneswalkerIndex=chosenAction.getAttackedPlaneswalkerIndex();
		}
		
		Player attackedPlayer=players.elementAt(attackedPlayerIndex);
		if(attackedPlaneswalkerIndex<0){
			attacker.TargetedPlayer=attackedPlayer;
		}else{
			attacker.TargetedPlayer=attackedPlayer.GetField().elementAt(attackedPlaneswalkerIndex);
		}
		
		return TriggerAttackersDeclared(new Creature[]{attacker}, players);
	}
	
	private void handleTriggeredEffectsTargets(ArrayList<MagicEffect[]> triggeredEffects, ArrayList<MagicEffect[]> ceffectArrayList, Vector<Player> players, int fieldCardIndex){
		for(int effectsIndex=0;effectsIndex<ceffectArrayList.size();effectsIndex++){
			MagicEffect[] ceffectArray=ceffectArrayList.get(effectsIndex);
			PlayerAction chosenAction=this.playerInterface.GetEffectsTargets(this.generateBattleState(players), fieldCardIndex, ceffectArray);
			MagicEffect.UsePlayerInput(ceffectArray, chosenAction.GetTargetCardControllerIndexes(),
						chosenAction.GetTargetFieldIndexes(), this.hand, players);
			triggeredEffects.add(ceffectArray);
		}
	}
	
	public ArrayList<MagicEffect[]> TriggerEntersTheBattlefield(CardBase cardEntered, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.field.size();i++){
			CardBase cfield=this.field.elementAt(i);
			boolean suppressed=false;
			for(int s=0;!suppressed&&s<this.entersTheBattlefieldTriggersSuppressed.size();s++){
				if(this.entersTheBattlefieldTriggersSuppressed.get(s).IsValidTarget(cfield)){
					suppressed=true;
				}
			}
			if(suppressed)
				continue;
			this.handleTriggeredEffectsTargets(triggeredEffects, cfield.TriggerEntersTheBattlefield(cardEntered, players), players, i);
		}
		
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> TriggerAttackersDeclared(Creature[] attackers, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.field.size();i++){
			triggeredEffects.addAll(this.field.elementAt(i).TriggerAttackersDeclared(attackers, players));
		}
		
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> TriggerGainedLife(Player controller, int lifegained, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.field.size();i++){
			triggeredEffects.addAll(this.field.elementAt(i).TriggerGainedLife(controller, lifegained, players));
		}
		
		return triggeredEffects;
	}

	public void ScryX(int scryAmount){
		CardBase[] peekedCards=this.deck.DrawXCards(scryAmount);
		CardBase[] peekedCopies=new CardBase[peekedCards.length];
		for(int i=0;i<peekedCards.length;i++)
			peekedCopies[i]=peekedCards[i].DeepCopy();
		int[] cardIndexesToPutOnBottom=this.playerInterface.GetIndexesToPutOnBottom(peekedCopies);
		CardBase[] cardsLeft=new CardBase[peekedCards.length-cardIndexesToPutOnBottom.length];
		CardBase[] cardsLeftCopies=new CardBase[cardsLeft.length];
		int cardsLeftIndex=0;
		for(int c=0;c<peekedCards.length;c++){
			boolean keep=true;
			for(int i=0;keep&&i<cardIndexesToPutOnBottom.length;i++){
				if(cardIndexesToPutOnBottom[i]==c)
					keep=false;
			}
			if(keep){
				cardsLeft[cardsLeftIndex]=peekedCards[c];
				cardsLeftCopies[cardsLeftIndex++]=peekedCopies[c];
			}else{
				this.deck.PlaceCardOnBottom(peekedCards[c]);
			}
		}
		int[] orderIndexes=new int[cardsLeft.length];
		if(cardsLeft.length>1){
			this.playerInterface.OrderCards(cardsLeftCopies, orderIndexes);
		}else{
			for(int i=0;i<orderIndexes.length;i++)
				orderIndexes[i]=i;
		}
		
		for(int i=orderIndexes.length-1;i>=0;i--){
			this.deck.PlaceCardOnTop(cardsLeft[orderIndexes[i]]);
		}
	}
	
	public void PlaceCardsOnTopOrBottomOfLibrary(ArrayList<CardBase> cards, Vector<Player> players){
		CardBase[] cardsDeepCopy=new CardBase[cards.size()];
		for(int i=0;i<cards.size();i++){
			CardBase ccard=cards.get(i);
			cardsDeepCopy[i]=ccard.DeepCopy();
		}

		int[] cardIndexesToPutOnBottom=this.playerInterface.GetIndexesToPutOnBottom(cardsDeepCopy);
		CardBase[] cardsToPutOnBottom=new CardBase[cardIndexesToPutOnBottom.length];
		int cardsBottomIndex=0;
		CardBase[] cardsToPutOnTop=new CardBase[cards.size()-cardIndexesToPutOnBottom.length];
		int cardsTopIndex=0;
		for(int i=0;i<cards.size();i++){
			boolean keep=true;
			for(int j=0;keep&&j<cardIndexesToPutOnBottom.length;j++){
				if(cardIndexesToPutOnBottom[j]==i)
					keep=false;
			}
			if(keep){
				cardsToPutOnTop[cardsTopIndex++]=cards.get(i);
			}else{
				cardsToPutOnBottom[cardsBottomIndex++]=cards.get(i);
			}
		}
		
		int[] orderTopIndexes=new int[cardsToPutOnTop.length];
		if(orderTopIndexes.length>1){
			CardBase[] cardsToPutOnTopDeepCopy=new CardBase[cardsToPutOnTop.length];
			for(int dci=0;dci<cardsToPutOnTop.length;dci++)
				cardsToPutOnTopDeepCopy[dci]=cardsToPutOnTop[dci].DeepCopy();
			this.playerInterface.OrderCards(cardsToPutOnTopDeepCopy, orderTopIndexes);
		}else{
			for(int i=0;i<orderTopIndexes.length;i++)
				orderTopIndexes[i]=i;
		}
		
		for(int i=orderTopIndexes.length-1;i>=0;i--){
			CardBase ccard=cardsToPutOnTop[orderTopIndexes[i]];
			ccard.Destroy(players);
			this.field.remove(ccard);
			if(!ccard.IsToken())
				this.deck.PlaceCardOnTop(ccard);
		}
		
		int[] orderBottomIndexes=new int[cardsToPutOnBottom.length];
		if(orderBottomIndexes.length>1){
			CardBase[] cardsToPutOnBottomDeepCopy=new CardBase[cardsToPutOnBottom.length];
			for(int dci=0;dci<cardsToPutOnBottom.length;dci++)
				cardsToPutOnBottomDeepCopy[dci]=cardsToPutOnBottom[dci].DeepCopy();
			this.playerInterface.OrderCards(cardsToPutOnBottomDeepCopy, orderBottomIndexes);
		}else{
			for(int i=0;i<orderBottomIndexes.length;i++)
				orderBottomIndexes[i]=i;
		}

		for(int i=0;i<orderBottomIndexes.length;i++){
			CardBase ccard=cardsToPutOnBottom[orderBottomIndexes[i]];
			ccard.Destroy(players);
			this.field.remove(ccard);
			if(!ccard.IsToken())
				this.deck.PlaceCardOnBottom(ccard);
		}
	}
	
	public int GetColorChoice(MagicEffect effect, int colorflags){
		return this.playerInterface.GetColorChoice(effect, colorflags);
	}
	
	public void CardRevealed(CardBase revealedCard){
		if(revealedCard.GetControllerIndex()!=this.myIndex){
			this.playerInterface.CardRevealed(revealedCard.DeepCopy());
		}
	}
	
	public void HandRevealed(Player opponent){
		Vector<CardBase> ohand=opponent.GetHand();
		CardBase[] opponentHandDeepCopy=new CardBase[ohand.size()];
		boolean[] oldFaceDowns=new boolean[opponentHandDeepCopy.length];
		for(int i=0;i<ohand.size();i++){
			CardBase ccard=ohand.get(i);
			oldFaceDowns[i]=ccard.IsFaceDown();
			ccard.SetFaceDown(false);
			opponentHandDeepCopy[i]=ccard.DeepCopy();
		}
		this.playerInterface.HandRevealed(opponentHandDeepCopy, opponent.GetIndex());
		for(int i=0;i<ohand.size();i++){
			ohand.get(i).SetFaceDown(oldFaceDowns[i]);
		}
	}
	
	public void mousePressed(MouseEvent me){this.playerInterface.mousePressed(me.getX(), me.getY());}
	public void mouseReleased(MouseEvent me){this.playerInterface.mouseReleased(me.getX(), me.getY());}
	public void mouseClicked(MouseEvent me){this.playerInterface.mouseClicked(me.getX(), me.getY());}
	public void keyPressed(KeyEvent ke){this.playerInterface.keyPressed(ke.getKeyCode());}
	
	public boolean IsPlayingAreaClicked(int mx, int my){return this.playingArea.IsClicked(mx, my);}
	
	public void animate(long elapsedMillis){
		synchronized(this.hand){
			for(int i=0;i<this.hand.size();i++){
				this.hand.elementAt(i).StepTowardsOnRails();
			}
		}
		
		synchronized(this.field){
			for(int i=0;i<this.field.size();i++){
				this.field.elementAt(i).StepTowardsOnRails();
			}
		}

		synchronized(this.graveyard){
			for(int i=0;i<this.graveyard.size();i++){
				this.graveyard.elementAt(i).StepTowardsOnRails();
			}
		}
		
		synchronized(this.exile){
			ArrayList<CardBase> exiledCards=this.exile.GetCards();
			for(int i=0;i<exiledCards.size();i++){
				exiledCards.get(i).StepTowardsOnRails();
			}
		}

		this.playerInterface.animate(elapsedMillis);
	}

	public void paintFullViewCard(Graphics g){
		this.playerInterface.paintFullViewCard(g);
	}
	
	public int GetTargetEndpointX(){
		return this.areaMinX+2;
	}
	
	public int GetTargetEndpointY(){
		if(this.onBottom){
			return this.areaMinY+14;
		}else{
			return this.areaMaxY-2;
		}
	}
	
	public void paint(Graphics g){
		this.playingArea.paint(g);

		this.deck.paint(g);
		
		synchronized(this.hand){
			for(int i=0;i<this.hand.size();i++){
				this.hand.elementAt(i).paint(g);
			}
		}
		synchronized(this.field){
			for(int i=0;i<this.field.size();i++){
				this.field.elementAt(i).paint(g);
			}
		}
		synchronized(this.graveyard){
			for(int i=0;i<this.graveyard.size();i++){
				this.graveyard.elementAt(i).paint(g);
			}
		}
		synchronized(this.exile){
			ArrayList<CardBase> exiledCards=this.exile.GetCards();
			for(int i=0;i<exiledCards.size();i++){
				exiledCards.get(i).paint(g);
			}
		}
		
		g.setColor(Color.black);
		int namex=this.GetTargetEndpointX();
		int namey=this.GetTargetEndpointY();
		if(this.onBottom){
			g.drawString(this.name, namex, namey);
			g.drawString(""+this.lifePoints, namex, namey+14);
		}else{
			g.drawString(this.name, namex, namey);
			g.drawString(""+this.lifePoints, namex, namey-14);
		}
		
		int manapoolx=this.areaMaxX-16;
		int manapooly=this.onBottom?this.areaMinY+22:this.areaMaxY-38;
		if(this.manapoolMountains>0){
			Painting.FillOvalWithBorder(g, manapoolx, manapooly, 14, 14, ManaCost.GetAWTColorByFlag(ManaCost.COLOR_FLAG_RED), Color.black);
			g.setColor(Color.black);
			g.drawString(""+this.manapoolMountains, manapoolx+5, manapooly+12);
			manapooly+=this.onBottom?16:-16;
		}
		if(this.manapoolForests>0){
			Painting.FillOvalWithBorder(g, manapoolx, manapooly, 14, 14, ManaCost.GetAWTColorByFlag(ManaCost.COLOR_FLAG_GREEN), Color.black);
			g.setColor(Color.black);
			g.drawString(""+this.manapoolForests, manapoolx+5, manapooly+12);
			manapooly+=this.onBottom?16:-16;
		}
		if(this.manapoolIslands>0){
			Painting.FillOvalWithBorder(g, manapoolx, manapooly, 14, 14, ManaCost.GetAWTColorByFlag(ManaCost.COLOR_FLAG_BLUE), Color.black);
			g.setColor(Color.black);
			g.drawString(""+this.manapoolIslands, manapoolx+5, manapooly+12);
			manapooly+=this.onBottom?16:-16;
		}
		if(this.manapoolSwamps>0){
			Painting.FillOvalWithBorder(g, manapoolx, manapooly, 14, 14, ManaCost.GetAWTColorByFlag(ManaCost.COLOR_FLAG_BLACK), Color.black);
			g.setColor(Color.black);
			g.drawString(""+this.manapoolSwamps, manapoolx+5, manapooly+12);
			manapooly+=this.onBottom?16:-16;
		}
		if(this.manapoolPlains>0){
			Painting.FillOvalWithBorder(g, manapoolx, manapooly, 14, 14, ManaCost.GetAWTColorByFlag(ManaCost.COLOR_FLAG_WHITE), Color.black);
			g.setColor(Color.black);
			g.drawString(""+this.manapoolPlains, manapoolx+5, manapooly+12);
			manapooly+=this.onBottom?16:-16;
		}
		if(this.manapoolColorless>0){
			Painting.FillOvalWithBorder(g, manapoolx, manapooly, 14, 14, ManaCost.GetAWTColorByFlag(ManaCost.COLOR_FLAG_COLORLESS), Color.black);
			g.setColor(Color.black);
			g.drawString(""+this.manapoolColorless, manapoolx+5, manapooly+12);
			manapooly+=this.onBottom?16:-16;
		}
		
		this.playerInterface.paint(g);
	}
}
