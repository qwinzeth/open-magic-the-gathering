package Matt.Stowe.MTG;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.MagicEffect;

import java.awt.Graphics;
import java.util.*;

public class LocalAIPlayerInterface implements IPlayerInterface{
	private int myIndex;
	public LocalAIPlayerInterface(int index){
		this.myIndex=index;
	}
	
	private int timesAnsweredTrue;

	public String GetControlTypeString(){return "AI";}
	public boolean wantsToMulligan(BattleState bs){return false;}
	public void timerStarted(BattleState bs, Stack<MagicStackElement> thestack){this.timesAnsweredTrue=0;}
	public PlayerAction handleInstants(){return PlayerAction.CreateTimerSkipAction();}
	public void timerEnded(){}
	public PlayerAction handleMainPhaseOne(BattleState bs){
		for(int i=0;i<bs.Hand.length;i++){
			if(bs.Hand[i].Playable){
				if(bs.Hand[i].GetKeywords().HasConvoke()){
					//TODO
					continue;
				}else{
					MagicEffect[] costs=bs.Hand[i].GetPlayCosts();
					if(costs!=null){
boolean cantplay=false;
						for(int costIndex=0;costIndex<costs.length;costIndex++){
							if(costs[costIndex].TargetData.GetMaxTargetCount()>0){
								//TODO
								cantplay=true;
								break;
							}
						}
if(cantplay)continue;
					}
					
					MagicEffect[] effects=bs.Hand[i].GetPlayEffects();
					if(effects!=null){
boolean cantplay=false;
						for(int effectIndex=0;effectIndex<effects.length;effectIndex++){
							if(effects[effectIndex].TargetData.GetMaxTargetCount()>0){
								//TODO
								cantplay=true;
								break;
							}
						}
if(cantplay)continue;
					}
				}
				return PlayerAction.CreatePlayCardAction(i);
			}
		}

		for(int i=0;i<bs.Players[this.myIndex].Field.length;i++){
			if(bs.Players[this.myIndex].Field[i].Activatable){
				MagicActivatedAbility[] abilities=bs.Players[this.myIndex].Field[i].GetActivatedAbilities();
				int abilityIndex=(int)(Math.random()*abilities.length);

				MagicEffect[] costs=abilities[abilityIndex].Costs;
				if(costs!=null){
boolean cantplay=false;
					for(int costIndex=0;costIndex<costs.length;costIndex++){
						if(costs[costIndex].TargetData.GetMaxTargetCount()>0){
							//TODO
							cantplay=true;
							break;
						}
					}
if(cantplay)continue;
				}

				MagicEffect[] effects=abilities[abilityIndex].Effects;
				if(effects!=null){
boolean cantplay=false;
					for(int effectIndex=0;effectIndex<effects.length;effectIndex++){
						if(effects[effectIndex].TargetData.GetMaxTargetCount()>0){
							//TODO
							cantplay=true;
							break;
						}
					}
if(cantplay)continue;
				}

				return PlayerAction.CreateActivateAbilityAction(i, abilityIndex, ZoneOptions.BATTLEFIELD);
			}
		}
		return PlayerAction.CreatePassAction();
	}
	public PlayerAction handleAttackDecision(BattleState bs){return PlayerAction.CreatePassAction();}
	public PlayerAction declareBlockers(BattleState bs, Creature[] attackers){return PlayerAction.CreatePassAction();}
	public PlayerAction GetEffectsTargets(BattleState bs, int fieldIndex, MagicEffect[] effects){return PlayerAction.CreatePassAction();}
	public void updateBattleState(BattleState bs){}
	public PlayerAction PayManaCosts(BattleState bs, ManaCost cost){return PlayerAction.CreatePassAction();}
	public int[] GetIndexesToPutOnBottom(CardBase[] cards){return new int[0];}
	public void OrderCards(CardBase[] cards, int[] orderIndexes){}
	public int[] ChooseCards(CardBase[] options, int minToChoose, int maxToChoose, String choiceDescription){
		int[] chosen=new int[minToChoose];
		for(int i=0;i<chosen.length;i++){
			chosen[i]=i;
		}
		return chosen;
	}
	public boolean ChooseYesOrNo(String queryText){
		if(this.timesAnsweredTrue<5){
			this.timesAnsweredTrue++;
			return true;
		}
		this.timesAnsweredTrue=0;
		return false;
	}
	public boolean WantsToTriggerEffect(MagicEffect effect){
		return true;
	}
	public int GetColorChoice(MagicEffect effect, int colorflags){return ManaCost.COLOR_FLAG_RED;}
	public PlayerAction ChoosePlayerIndex(BattleState bs, int invalidPlayerIndex, String choiceDescription){return PlayerAction.CreatePassAction();}
	public void CardRevealed(CardBase card){}
	public void HandRevealed(CardBase[] hand, int playerIndex){}
	public void animate(long elapsedMillis){}
	public void mousePressed(int mx, int my){}
	public void mouseReleased(int mx, int my){}
	public void mouseClicked(int mx, int my){}
	public void keyPressed(int keyCode){}
	public void paintFullViewCard(Graphics g){}
	public void paint(Graphics g){}
}
