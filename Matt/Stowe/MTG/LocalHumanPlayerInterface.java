package Matt.Stowe.MTG;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Transferrable.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class LocalHumanPlayerInterface implements IPlayerInterface{
	private static final int OPTIONS_TEXT_WIDTH=200;
	private static final int OPTIONS_TEXT_HEIGHT=42;

	private Stack<MagicStackElement> theStack;
	private int paintingAreaMinX, paintingAreaMinY, paintingAreaMaxX, paintingAreaMaxY;
	private int myIndex;
	private Object phaselock;
	private GUIButton btnPass;
	private GUIButton btnMulligan;
	private GUIButton btnPause;
	private GUIButton btnCancel;
	private GUIButton btnConfirm;
	private GUIButton btnYes;
	private GUIButton btnNo;
	private boolean decisionYesNo;
	private GUIButton[] btnColors;
	private int decisionColor;
	private String textColorChoice;
	private GUIButton[] btnOptions;
	private String optionsText;
	private String textYesNo;
	private int optionsTextX, optionsTextY;
	private boolean choiceMade;
	private boolean mulligan;
	private DecisionType currentDecisionType;
	private PlayerAction currentDecision;
	private BattleState battleState;
	private final static Object battleStateLock=new Object();
	private int selectedFieldIndex;
	private Creature[] attackers;
	private int cardIndexFromHandNeedsCostTargets;
	private int cardIndexOnFieldNeedsCostTargets;
	private int cardIndexFromHandNeedsTargets;
	private int cardIndexOnFieldNeedsTargets;
	private MagicEffect[] effectsNeedsCostTargets;
	private MagicEffect[] effectsNeedsTargets;
	private int targetEffectsIndex;
	private int targetFieldIndex;
	private int[][] targetFieldControllerIndexes;
	private int[][] targetFieldIndexes;
	private int[][] costtargetFieldControllerIndexes;
	private int[][] costtargetFieldIndexes;
	private CardBase[] releventCards;
	private Vector<CardBase> cardsToKeep;
	private Vector<CardBase> cardsToPutOnBottom;
	private int[] ordering;
	private int indexToSwap;
	private CardBase fullviewCard;
	private boolean viewingMode;
	private int viewingZone;
	private int graveyardX, graveyardY;
	private MagicActivatedAbility[] abilityOptions;
	private int cardIndexPendingAbilityChoice;
	private int cardZonePendingAbilityChoice;
	private int chosenAbilityIndex;
	private ChoiceSelector convokeSelector;
	private ChoiceSelector delveSelector;
	private ChoiceSelector cardSelector;
	private String cardSelectionDescription;
	private int cardSelectorMin, cardSelectorMax;
	private String textChoosePlayer;
	private int invalidPlayerChoiceIndex;
	private ManaCost manaCostToPay;
	private String textAck;

	private enum DecisionType{
		NONE,
		TIMER,
		MULLIGAN,
		MAIN,
		DECLARE_ATTACKERS,
		DECLARE_BLOCKERS,
		DECLARE_TARGETS,
		PAY_MANA,
		KEEP_OR_PLACEONBOTTOM,
		ORDER_CARDS,
		CHOOSE_CARDS,
		YES_NO,
		COLOR_CHOICE,
		PLAYER_CHOICE,
		ACK
	}

	public LocalHumanPlayerInterface(int index, int paintingAreaMinX, int paintingAreaMinY, int paintingAreaMaxX, int paintingAreaMaxY, boolean onBottom){
		this.myIndex=index;
		this.paintingAreaMinX=paintingAreaMinX;
		this.paintingAreaMinY=paintingAreaMinY;
		this.paintingAreaMaxX=paintingAreaMaxX;
		this.paintingAreaMaxY=paintingAreaMaxY;
		this.phaselock=new Object();
		int by=onBottom?paintingAreaMinY+2:paintingAreaMaxY-18;
		this.btnPass=new GUIButton(paintingAreaMaxX-50, by, 48, 16, "Pass");
		this.btnMulligan=new GUIButton(paintingAreaMaxX-120, by, 68, 16, "Mulligan");
		this.btnPause=new GUIButton(paintingAreaMaxX-120, by, 48, 16, "Pause");
		this.btnCancel=new GUIButton(paintingAreaMaxX-120, by, 48, 16, "Cancel");
		this.btnConfirm=new GUIButton(paintingAreaMaxX-50, by, 48, 16, "Confirm");
		this.btnYes=new GUIButton(this.paintingAreaMinX+12, this.paintingAreaMaxY-70, 48, 16, "Yes");
		this.btnNo=new GUIButton(this.paintingAreaMinX+62, this.paintingAreaMaxY-70, 48, 16, "No");
		this.btnColors=new GUIButton[5];
		for(int i=0;i<this.btnColors.length;i++){
			this.btnColors[i]=new GUIButton(this.paintingAreaMinX+12+i*50, this.paintingAreaMaxY-70, 48, 16, ManaCost.GetColorNames(1<<i, ""));
			this.btnColors[i].Visible=false;
		}
		this.btnOptions=new GUIButton[5];
		for(int i=0;i<this.btnOptions.length;i++){
			this.btnOptions[i]=new GUIButton(paintingAreaMinX+22*i, by, 20, 16, ""+(i+1));
			this.btnOptions[i].Visible=false;
		}
		this.optionsTextX=paintingAreaMinX+2;
		this.optionsTextY=onBottom?by+18:by-OPTIONS_TEXT_HEIGHT-4;
		this.btnPass.Visible=false;
		this.btnMulligan.Visible=false;
		this.btnPause.Visible=false;
		this.btnCancel.Visible=false;
		this.btnConfirm.Visible=false;
		this.btnYes.Visible=false;
		this.btnNo.Visible=false;
		this.currentDecisionType=DecisionType.NONE;
		this.selectedFieldIndex=-1;
		this.cardsToKeep=new Vector<CardBase>();
		this.cardsToPutOnBottom=new Vector<CardBase>();
		this.viewingMode=false;
		this.viewingZone=ZoneOptions.BATTLEFIELD;
		this.convokeSelector=new ChoiceSelector();
		this.delveSelector=new ChoiceSelector();
		this.cardSelector=new ChoiceSelector();
	}
	
	private void waitForUserChoice(){
		this.choiceMade=false;
		while(!this.choiceMade){
			synchronized(this.phaselock){
				try{this.phaselock.wait();}catch(InterruptedException ie){ie.printStackTrace();}
			}
		}
		this.currentDecisionType=DecisionType.NONE;
	}
	
	private void adjustCardsToKeep(){
		for(int i=0;i<this.cardsToKeep.size();i++){
			this.cardsToKeep.elementAt(i).SetAnimationDestination(this.paintingAreaMinX+12+(i*CardBase.WIDTH+2), this.paintingAreaMaxY-CardBase.HEIGHT-12, OptionsScreen.AnimationSpeed);
		}
	}
	
	private void adjustCardsToPutOnBottom(){
		for(int i=0;i<this.cardsToPutOnBottom.size();i++){
			this.cardsToPutOnBottom.elementAt(i).SetAnimationDestination(this.paintingAreaMinX+12+(i*CardBase.WIDTH+2), this.paintingAreaMinY+26, OptionsScreen.AnimationSpeed);
		}
	}
	
	private void adjustCardsToOrder(){
		for(int i=0;i<this.releventCards.length;i++){
			this.releventCards[this.ordering[i]].SetAnimationDestination(this.paintingAreaMinX+12+(i*CardBase.WIDTH+2), this.paintingAreaMaxY-CardBase.HEIGHT-12, OptionsScreen.AnimationSpeed);
		}
	}
	
	private void adjustCardsToChoose(){
		int bx=0;
		int tx=0;
		for(int i=0;i<this.releventCards.length;i++){
			if(this.cardSelector.IsSelected(i)){
				this.releventCards[i].SetAnimationDestination(this.paintingAreaMinX+12+((tx++)*CardBase.WIDTH+2), this.paintingAreaMinY+26, OptionsScreen.AnimationSpeed);
			}else{
				this.releventCards[i].SetAnimationDestination(this.paintingAreaMinX+12+((bx++)*CardBase.WIDTH+2), this.paintingAreaMaxY-CardBase.HEIGHT-12, OptionsScreen.AnimationSpeed);
			}
		}
	}
	
	private void adjustCardsInGraveyard(){
		CardBase[] mygraveyard=this.battleState.Players[this.myIndex].Graveyard;
		for(int i=0;i<mygraveyard.length;i++){
			mygraveyard[i].SetAnimationDestination(this.paintingAreaMinX+12+(i*CardBase.WIDTH+2), this.paintingAreaMaxY-CardBase.HEIGHT-12, OptionsScreen.AnimationSpeed);
		}
	}
	
	private void resumeNormalButtonDisplay(){
		if(this.currentDecisionType==DecisionType.MAIN){
			this.btnPass.Visible=true;
		}else{
			this.btnPause.Visible=true;
		}
		this.btnCancel.Visible=false;
		this.btnConfirm.Visible=false;
		this.btnYes.Visible=false;
		this.btnNo.Visible=false;
		for(int i=0;i<this.btnColors.length;i++){
			this.btnColors[i].Visible=false;
		}
	}
	
	private boolean handleCostTargetRequirements(MagicEffect[] costs){
		this.targetEffectsIndex=-1;
		if(costs!=null){
			for(int costIndex=0;this.targetEffectsIndex==-1&&costIndex<costs.length;costIndex++){
				if(costs[costIndex].TargetData.GetMaxTargetCount()>0){
					this.targetEffectsIndex=costIndex;
				}
			}
		}
		
		if(this.targetEffectsIndex>-1){
			this.btnPause.Visible=false;
			this.btnPass.Visible=false;
			this.btnCancel.Visible=true;
			this.effectsNeedsCostTargets=costs;
			this.cardIndexFromHandNeedsCostTargets=-1;
			this.cardIndexOnFieldNeedsCostTargets=-1;
			this.targetFieldIndex=0;
			this.costtargetFieldControllerIndexes=new int[costs.length][];
			this.costtargetFieldControllerIndexes[this.targetEffectsIndex]=new int[costs[this.targetEffectsIndex].TargetData.GetMaxTargetCount()];
			this.costtargetFieldIndexes=new int[costs.length][];
			this.costtargetFieldIndexes[this.targetEffectsIndex]=new int[costs[this.targetEffectsIndex].TargetData.GetMaxTargetCount()];
			if(this.targetFieldIndex>=costs[this.targetEffectsIndex].TargetData.GetRequiredTargetCount())
				this.btnConfirm.Visible=true;
			else
				this.btnConfirm.Visible=false;
		}
		
		return this.targetEffectsIndex>-1;
	}
	
	private boolean handleTargetRequirements(MagicEffect[] effects){
		this.targetEffectsIndex=-1;
		if(effects!=null){
			for(int effectIndex=0;this.targetEffectsIndex==-1&&effectIndex<effects.length;effectIndex++){
				if(effects[effectIndex].TargetData.GetMaxTargetCount()>0
				&&MagicEffect.HasValidTarget(effects, this.battleState.Players, this.theStack)){
					this.targetEffectsIndex=effectIndex;
				}
			}
		}

		if(this.targetEffectsIndex>-1){
			this.btnPause.Visible=false;
			this.btnPass.Visible=false;
			this.btnCancel.Visible=this.currentDecisionType!=DecisionType.DECLARE_TARGETS;
			this.effectsNeedsTargets=effects;
			this.cardIndexFromHandNeedsTargets=-1;
			this.cardIndexOnFieldNeedsTargets=-1;
			this.targetFieldIndex=0;
			this.targetFieldControllerIndexes=new int[effects.length][];
			if(effects[this.targetEffectsIndex].TargetData.NeedsX()){
				this.targetFieldControllerIndexes[this.targetEffectsIndex]=new int[100];//TODO: rework this flow so there is no hard limit
			}else{
				this.targetFieldControllerIndexes[this.targetEffectsIndex]=new int[effects[this.targetEffectsIndex].TargetData.GetMaxTargetCount()];
			}
			this.targetFieldIndexes=new int[effects.length][];
			if(effects[this.targetEffectsIndex].TargetData.NeedsX()){
				this.targetFieldIndexes[this.targetEffectsIndex]=new int[this.targetFieldControllerIndexes[this.targetEffectsIndex].length];
			}else{
				this.targetFieldIndexes[this.targetEffectsIndex]=new int[effects[this.targetEffectsIndex].TargetData.GetMaxTargetCount()];
			}
			if(this.targetFieldIndex>=effects[this.targetEffectsIndex].TargetData.GetRequiredTargetCount())
				this.btnConfirm.Visible=true;
			else
				this.btnConfirm.Visible=false;
		}
		
		return this.targetEffectsIndex>-1;
	}
	
	private void toggleCostSelection(int playerIndex, int fieldIndex){
		boolean unselect=false;
		for(int checkPreviousTargetsIndex=0;checkPreviousTargetsIndex<this.targetFieldIndex;checkPreviousTargetsIndex++){
			if(this.costtargetFieldControllerIndexes[this.targetEffectsIndex][checkPreviousTargetsIndex]==playerIndex
			&&this.costtargetFieldIndexes[this.targetEffectsIndex][checkPreviousTargetsIndex]==fieldIndex){
				unselect=true;
				for(int shiftIndex=checkPreviousTargetsIndex;shiftIndex<this.targetFieldIndex-1;shiftIndex++){
					this.costtargetFieldControllerIndexes[this.targetEffectsIndex][shiftIndex]=this.costtargetFieldControllerIndexes[this.targetEffectsIndex][shiftIndex+1];
					this.costtargetFieldIndexes[this.targetEffectsIndex][shiftIndex]=this.costtargetFieldIndexes[this.targetEffectsIndex][shiftIndex+1];
				}
				this.targetFieldIndex--;
			}
		}
		if(!unselect&&this.targetFieldIndex<this.costtargetFieldIndexes[this.targetEffectsIndex].length){
			CardBase effectSource=null;
			if(this.cardIndexOnFieldNeedsCostTargets>-1)
				effectSource=this.battleState.Players[this.myIndex].Field[this.cardIndexOnFieldNeedsCostTargets];
			else if(this.cardIndexFromHandNeedsCostTargets>-1)
				effectSource=this.battleState.Hand[this.cardIndexFromHandNeedsCostTargets];
			if(fieldIndex==-1){
				if(this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidTarget(this.battleState.Players[playerIndex])){
					this.costtargetFieldControllerIndexes[this.targetEffectsIndex][this.targetFieldIndex]=playerIndex;
					this.costtargetFieldIndexes[this.targetEffectsIndex][this.targetFieldIndex++]=fieldIndex;
				}
			}else{
				//TODO: multiple valid zones
				CardBase ccard=null;
				if(fieldIndex<this.battleState.Players[playerIndex].Field.length&&this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.BATTLEFIELD))
					ccard=this.battleState.Players[playerIndex].Field[fieldIndex];
				else if(fieldIndex<this.battleState.Players[playerIndex].Graveyard.length&&this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.GRAVEYARD))
					ccard=this.battleState.Players[playerIndex].Graveyard[fieldIndex];
				else if(fieldIndex<this.battleState.Players[playerIndex].Hand.length&&this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.HAND))
					ccard=this.battleState.Players[playerIndex].Hand[fieldIndex];
				else if(fieldIndex<this.battleState.Players[playerIndex].Exile.length&&this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.EXILE))
					ccard=this.battleState.Players[playerIndex].Exile[fieldIndex];
				else if(fieldIndex<this.theStack.size()&&this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.STACK))
					ccard=theStack.get(fieldIndex).CardToPlay;

				if(ccard!=null&&this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidTarget(ccard)){
					this.costtargetFieldControllerIndexes[this.targetEffectsIndex][this.targetFieldIndex]=playerIndex;
					this.costtargetFieldIndexes[this.targetEffectsIndex][this.targetFieldIndex++]=fieldIndex;
				}
			}
		}
	}

	private void toggleSelection(int playerIndex, int fieldIndex){
		boolean unselect=false;
		for(int checkPreviousTargetsIndex=0;checkPreviousTargetsIndex<this.targetFieldIndex;checkPreviousTargetsIndex++){
			if(this.targetFieldControllerIndexes[this.targetEffectsIndex][checkPreviousTargetsIndex]==playerIndex
			&&this.targetFieldIndexes[this.targetEffectsIndex][checkPreviousTargetsIndex]==fieldIndex){
				unselect=true;
				for(int shiftIndex=checkPreviousTargetsIndex;shiftIndex<this.targetFieldIndex-1;shiftIndex++){
					this.targetFieldControllerIndexes[this.targetEffectsIndex][shiftIndex]=this.targetFieldControllerIndexes[this.targetEffectsIndex][shiftIndex+1];
					this.targetFieldIndexes[this.targetEffectsIndex][shiftIndex]=this.targetFieldIndexes[this.targetEffectsIndex][shiftIndex+1];
				}
				this.targetFieldIndex--;
			}
		}
		if(!unselect&&this.targetFieldIndex<this.targetFieldIndexes[this.targetEffectsIndex].length){
			CardBase effectSource=null;
			if(this.cardIndexOnFieldNeedsTargets>-1)
				effectSource=this.battleState.Players[this.myIndex].Field[this.cardIndexOnFieldNeedsTargets];
			else if(this.cardIndexFromHandNeedsTargets>-1)
				effectSource=this.battleState.Hand[this.cardIndexFromHandNeedsTargets];
			if(fieldIndex==-1){
				if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidTarget(this.battleState.Players[playerIndex])
				&&this.battleState.Players[playerIndex].CanBeTargetedBy(effectSource)){
					this.targetFieldControllerIndexes[this.targetEffectsIndex][this.targetFieldIndex]=playerIndex;
					this.targetFieldIndexes[this.targetEffectsIndex][this.targetFieldIndex++]=fieldIndex;
				}
			}else{
				//TODO: multiple valid zones
				CardBase ccard=null;
				if(fieldIndex<this.battleState.Players[playerIndex].Field.length&&this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.BATTLEFIELD))
					ccard=this.battleState.Players[playerIndex].Field[fieldIndex];
				else if(fieldIndex<this.battleState.Players[playerIndex].Graveyard.length&&this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.GRAVEYARD))
					ccard=this.battleState.Players[playerIndex].Graveyard[fieldIndex];
				else if(fieldIndex<this.battleState.Players[playerIndex].Hand.length&&this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.HAND))
					ccard=this.battleState.Players[playerIndex].Hand[fieldIndex];
				else if(fieldIndex<this.battleState.Players[playerIndex].Exile.length&&this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.EXILE))
					ccard=this.battleState.Players[playerIndex].Exile[fieldIndex];
				else if(fieldIndex<this.theStack.size()&&this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.STACK))
					ccard=theStack.get(fieldIndex).CardToPlay;
				if(ccard!=null&&this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidTarget(ccard)
				&&ccard.CanBeTargetedBy(effectSource)){
					this.targetFieldControllerIndexes[this.targetEffectsIndex][this.targetFieldIndex]=playerIndex;
					this.targetFieldIndexes[this.targetEffectsIndex][this.targetFieldIndex++]=fieldIndex;
				}
			}
		}
	}
	
	public String GetControlTypeString(){return "Local Human";}
	
	public void updateBattleState(BattleState bs){
		synchronized(this.battleStateLock){
			//TODO: Stop selections
			this.battleState=bs;
			if(this.battleState.Players[this.myIndex].Graveyard.length>0){
				this.graveyardX=this.battleState.Players[this.myIndex].Graveyard[0].GetX();
				this.graveyardY=this.battleState.Players[this.myIndex].Graveyard[0].GetY();
			}
		}
	}
	
	public boolean wantsToMulligan(BattleState bs){
		this.updateBattleState(bs);
		this.btnPass.Visible=true;
		this.btnMulligan.Visible=true;
		this.currentDecisionType=DecisionType.MULLIGAN;
		this.waitForUserChoice();
		return this.mulligan;
	}

	public void timerStarted(BattleState bs, Stack<MagicStackElement> thestack){
		this.updateBattleState(bs);
		this.theStack=thestack;
		this.currentDecisionType=DecisionType.TIMER;
		this.btnPause.Visible=true;
		this.choiceMade=false;
	}

	public PlayerAction handleInstants(){
		if(this.choiceMade){
			this.choiceMade=false;
			return this.currentDecision;
		}
		return PlayerAction.CreateNoAction();
	}

	public void timerEnded(){
		this.theStack=null;
		this.btnPause.Visible=false;
		this.currentDecisionType=DecisionType.NONE;
	}

	public PlayerAction PayManaCosts(BattleState bs, ManaCost cost){
		this.updateBattleState(bs);
		this.manaCostToPay=cost;
		this.btnPass.Visible=true;
		this.currentDecisionType=DecisionType.PAY_MANA;
		this.waitForUserChoice();
		return this.currentDecision;
	}

	public PlayerAction handleMainPhaseOne(BattleState bs){
		this.updateBattleState(bs);
		this.btnPass.Visible=true;
		this.currentDecisionType=DecisionType.MAIN;
		this.waitForUserChoice();
		return this.currentDecision;
	}

	public PlayerAction handleAttackDecision(BattleState bs){
		this.updateBattleState(bs);
		this.btnPass.Visible=true;
		this.currentDecisionType=DecisionType.DECLARE_ATTACKERS;
		this.selectedFieldIndex=-1;
		this.waitForUserChoice();
		return this.currentDecision;
	}
	
	public PlayerAction declareBlockers(BattleState bs, Creature[] attackers){
		this.updateBattleState(bs);
		this.attackers=attackers;
		this.btnPass.Visible=true;
		this.currentDecisionType=DecisionType.DECLARE_BLOCKERS;
		this.selectedFieldIndex=-1;
		this.waitForUserChoice();
		return this.currentDecision;
	}
	
	public PlayerAction GetEffectsTargets(BattleState bs, int fieldIndex, MagicEffect[] effects){
		this.updateBattleState(bs);
		this.currentDecisionType=DecisionType.DECLARE_TARGETS;
		if(this.handleTargetRequirements(effects)){
			this.cardIndexOnFieldNeedsTargets=fieldIndex;
			this.waitForUserChoice();
		}else{
			this.currentDecision=PlayerAction.CreateNoAction();
			this.currentDecisionType=DecisionType.NONE;
		}
		return this.currentDecision;
	}

	public int[] GetIndexesToPutOnBottom(CardBase[] cards){
		this.btnPause.Visible=false;
		this.btnConfirm.Visible=true;
		this.releventCards=cards;
		this.cardsToKeep.clear();
		this.cardsToPutOnBottom.clear();
		for(int i=0;i<cards.length;i++){
			cardsToKeep.add(cards[i]);
		}
		this.adjustCardsToKeep();
		this.adjustCardsToPutOnBottom();
		this.currentDecisionType=DecisionType.KEEP_OR_PLACEONBOTTOM;
		this.waitForUserChoice();
		int[] indexesToToss=new int[this.cardsToPutOnBottom.size()];
		int indexToToss=0;
		for(int i=0;indexToToss<indexesToToss.length&&i<this.releventCards.length;i++){
			for(int j=0;j<this.cardsToPutOnBottom.size();j++){
				if(this.cardsToPutOnBottom.elementAt(j)==this.releventCards[i])
					indexesToToss[indexToToss++]=i;
			}
		}
		return indexesToToss;
	}
	
	public void OrderCards(CardBase[] cards, int[] orderIndexes){
		for(int i=0;i<orderIndexes.length;i++)
			orderIndexes[i]=i;
		if(orderIndexes.length<2){
			return;
		}
		this.btnPause.Visible=false;
		this.btnConfirm.Visible=true;
		this.ordering=orderIndexes;
		this.releventCards=cards;
		this.adjustCardsToOrder();
		this.indexToSwap=-1;
		this.currentDecisionType=DecisionType.ORDER_CARDS;
		this.waitForUserChoice();
		this.releventCards=null;
		this.ordering=null;
	}
	
	public int[] ChooseCards(CardBase[] options, int minToChoose, int maxToChoose, String choiceDescription){
		this.cardSelectionDescription=choiceDescription;
		this.cardSelector.StartSelecting(-2);
		this.releventCards=options;
		this.cardSelectorMin=minToChoose;
		this.cardSelectorMax=maxToChoose;
		this.btnPause.Visible=false;
		this.adjustCardsToChoose();
		int[] chosenCardIndexes=new int[0];
		do{
			this.btnConfirm.Visible=true;
			this.currentDecisionType=DecisionType.CHOOSE_CARDS;
			this.waitForUserChoice();
			chosenCardIndexes=this.cardSelector.GetChoices();
		}while(chosenCardIndexes.length<minToChoose||chosenCardIndexes.length>maxToChoose);
		this.releventCards=null;
		return chosenCardIndexes;
	}

	public boolean ChooseYesOrNo(String queryText){
		this.textYesNo=queryText;
		this.btnPause.Visible=false;
		this.btnPass.Visible=false;
		this.btnYes.Visible=true;
		this.btnNo.Visible=true;
		this.currentDecisionType=DecisionType.YES_NO;
		this.waitForUserChoice();
		this.resumeNormalButtonDisplay();
		return this.decisionYesNo;
	}
	
	public boolean WantsToTriggerEffect(MagicEffect effect){
		return this.ChooseYesOrNo("Do you want to put this on the stack? "+effect.GetRulesText());
	}
	
	public int GetColorChoice(MagicEffect effect, int colorflags){
		this.textColorChoice="Choose a color for: "+effect.GetRulesText();
		this.btnPause.Visible=false;
		this.btnPass.Visible=false;
		for(int i=0;i<this.btnColors.length;i++){
			if((colorflags&(1<<i))!=0)
				this.btnColors[i].Visible=true;
		}
		this.currentDecisionType=DecisionType.COLOR_CHOICE;
		this.waitForUserChoice();
		this.resumeNormalButtonDisplay();
		return this.decisionColor;
	}
	
	public PlayerAction ChoosePlayerIndex(BattleState bs, int invalidPlayerIndex, String choiceDescription){
		this.updateBattleState(bs);
		this.textChoosePlayer=choiceDescription;
		this.invalidPlayerChoiceIndex=invalidPlayerIndex;
		this.btnPause.Visible=false;
		this.btnPass.Visible=false;
		this.currentDecisionType=DecisionType.PLAYER_CHOICE;
		this.waitForUserChoice();
		this.resumeNormalButtonDisplay();
		return this.currentDecision;
	}
	
	public void CardRevealed(CardBase card){
		this.fullviewCard=card;
		this.viewingMode=true;
		this.textAck=this.battleState.Players[card.GetControllerIndex()].GetName()+" has revealed "+card.GetName();
		this.currentDecisionType=DecisionType.ACK;
		this.btnPass.Visible=true;
		this.waitForUserChoice();
		this.resumeNormalButtonDisplay();
		return;
	}
	
	public void HandRevealed(CardBase[] hand, int playerIndex){
		this.battleState.Players[playerIndex].Hand=hand;
		this.textAck=this.battleState.Players[playerIndex].GetName()+" has revealed their hand";
		this.currentDecisionType=DecisionType.ACK;
		this.btnPass.Visible=true;
		this.waitForUserChoice();
		this.resumeNormalButtonDisplay();
		return;
	}
	
	public void animate(long elapsedMillis){
		switch(this.currentDecisionType){
		case KEEP_OR_PLACEONBOTTOM:
		synchronized(this.cardsToKeep){
			for(int i=0;i<this.cardsToKeep.size();i++){
				this.cardsToKeep.elementAt(i).StepTowardsOnRails();
			}
		}

		synchronized(this.cardsToPutOnBottom){
			for(int i=0;i<this.cardsToPutOnBottom.size();i++){
				this.cardsToPutOnBottom.elementAt(i).StepTowardsOnRails();
			}
		}
		break;
		case ORDER_CARDS:
		case CHOOSE_CARDS:
			for(int i=0;i<this.releventCards.length;i++){
				this.releventCards[i].StepTowardsOnRails();
			}
		break;
		}
		
		if(this.battleState!=null&&this.viewingZone==ZoneOptions.GRAVEYARD){
			CardBase[] mygraveyard=this.battleState.Players[this.myIndex].Graveyard;
			for(int i=0;i<mygraveyard.length;i++){
				mygraveyard[i].StepTowardsOnRails();
			}
		}
	}

	public void mousePressed(int mx, int my){
		this.btnPass.handleMousePress(mx, my);
		this.btnMulligan.handleMousePress(mx, my);
		this.btnPause.handleMousePress(mx, my);
		this.btnCancel.handleMousePress(mx, my);
		this.btnConfirm.handleMousePress(mx, my);
		this.btnYes.handleMousePress(mx, my);
		this.btnNo.handleMousePress(mx, my);
		for(int i=0;i<this.btnOptions.length;i++){
			this.btnOptions[i].handleMousePress(mx, my);
		}
		for(int i=0;i<this.btnColors.length;i++){
			this.btnColors[i].handleMousePress(mx, my);
		}
	}

	public void mouseReleased(int mx, int my){
		this.btnPass.handleMouseRelease(mx, my);
		this.btnMulligan.handleMouseRelease(mx, my);
		this.btnPause.handleMouseRelease(mx, my);
		this.btnCancel.handleMouseRelease(mx, my);
		this.btnConfirm.handleMouseRelease(mx, my);
		this.btnYes.handleMouseRelease(mx, my);
		this.btnNo.handleMouseRelease(mx, my);
		for(int i=0;i<this.btnOptions.length;i++){
			this.btnOptions[i].handleMouseRelease(mx, my);
		}
		for(int i=0;i<this.btnColors.length;i++){
			this.btnColors[i].handleMouseRelease(mx, my);
		}
	}

	public void keyPressed(int keyCode){
		if(this.currentDecisionType==DecisionType.TIMER && keyCode==KeyEvent.VK_SPACE){
			this.currentDecision=PlayerAction.CreateTimerSkipAction();
			this.choiceMade=true;
		}
		if(keyCode==KeyEvent.VK_V)
			this.viewingMode=!this.viewingMode;
	}
	
	private void cardClickCheck(CardBase ccard, int mx, int my, int cardIndex, int cardZone){
		if(ccard.Activatable&&ccard.IsClicked(mx, my)){
			if(ccard.GetActivatedAbilities().length>1){
				if(ccard.IsFaceDown()){
					for(int abilityindex=0;!this.choiceMade&&abilityindex<ccard.GetActivatedAbilities().length;abilityindex++){
						if(ccard.GetActivatedAbilities()[abilityindex].Effects.length>0
						&&ccard.GetActivatedAbilities()[abilityindex].Effects[0] instanceof MagicEffectMorphTargetCreature){
							this.currentDecision=PlayerAction.CreateActivateAbilityAction(cardIndex, abilityindex, cardZone);
							this.choiceMade=true;
						}
					}
				}else{
					this.cardIndexPendingAbilityChoice=cardIndex;
					this.cardZonePendingAbilityChoice=cardZone;
					this.abilityOptions=ccard.GetActivatedAbilities();
					this.optionsText="Choose which activated ability to use.";
					for(int ao=0;ao<this.abilityOptions.length;ao++){
						this.btnOptions[ao].Visible=true;
					}
				}
			}else if(this.handleCostTargetRequirements(ccard.GetActivatedAbilities()[0].Costs)){
				if(cardZone==ZoneOptions.BATTLEFIELD){
					this.cardIndexOnFieldNeedsCostTargets=cardIndex;
				}else{
					System.out.println("TODO: Non-field abilities with cost targets.");
				}
				this.chosenAbilityIndex=0;
			}else if(this.handleTargetRequirements(ccard.GetActivatedAbilities()[0].Effects)){
				if(cardZone==ZoneOptions.BATTLEFIELD){
					this.cardIndexOnFieldNeedsTargets=cardIndex;
				}else{
					System.out.println("TODO: Non-field abilities with targets.");
				}
				this.chosenAbilityIndex=0;
			}else{
				this.currentDecision=PlayerAction.CreateActivateAbilityAction(cardIndex, 0, cardZone);
				this.choiceMade=true;
			}
		}
	}

	private void viewGraveyard(){
		this.viewingZone=ZoneOptions.GRAVEYARD;
		this.adjustCardsInGraveyard();
	}

	public void mouseClicked(int mx, int my){
		if(this.viewingMode){
			this.viewingMode=false;
			if(this.fullviewCard!=null){
				this.fullviewCard=null;
			}else if(this.battleState!=null){
				if(this.battleState.Players!=null){
					for(int playerIndex=0;playerIndex<this.battleState.Players.length;playerIndex++){
						for(int fieldIndex=0;fieldIndex<this.battleState.Players[playerIndex].Field.length&&!this.viewingMode;fieldIndex++){
							if(this.battleState.Players[playerIndex].Field[fieldIndex].IsClicked(mx, my)){
								this.fullviewCard=this.battleState.Players[playerIndex].Field[fieldIndex];
								this.viewingMode=true;
							}
						}
						
						if(playerIndex==this.myIndex)
							continue;

						for(int handIndex=0;handIndex<this.battleState.Players[playerIndex].Hand.length&&!this.viewingMode;handIndex++){
							if(this.battleState.Players[playerIndex].Hand[handIndex].IsClicked(mx, my)){
								this.fullviewCard=this.battleState.Players[playerIndex].Hand[handIndex];
								this.viewingMode=true;
							}
						}
					}
				}
				
				for(int i=0;i<this.battleState.Hand.length;i++){
					if(this.battleState.Hand[i].IsClicked(mx, my)){
						this.fullviewCard=this.battleState.Hand[i];
						this.viewingMode=true;
					}
				}

				CardBase[] mygraveyard=this.battleState.Players[this.myIndex].Graveyard;
				for(int i=0;i<mygraveyard.length;i++){
					if(mygraveyard[i].IsClicked(mx, my)){
						this.fullviewCard=mygraveyard[i];
						this.viewingMode=true;
					}
				}
				
				Stack<MagicStackElement> stack=this.theStack;
				if(stack!=null){
					for(int i=0;i<stack.size();i++){
						MagicStackElement celement=stack.get(i);
						if(celement!=null){
							CardBase cardToPlay=celement.CardToPlay;
							if(cardToPlay!=null&&cardToPlay.IsClicked(mx, my)){
								this.fullviewCard=cardToPlay;
								this.viewingMode=true;
							}
						}
					}
				}
			}
			return;
		}
		
		if(this.viewingZone==ZoneOptions.GRAVEYARD){
			CardBase[] mygraveyard=this.battleState.Players[this.myIndex].Graveyard;
			boolean clickedcard=false;
			for(int i=0;!clickedcard&&i<mygraveyard.length;i++){
				if(mygraveyard[i].IsClicked(mx, my)){
					clickedcard=true;
				}
			}

			if(!clickedcard){
				this.viewingZone=ZoneOptions.BATTLEFIELD;
				for(int i=0;i<mygraveyard.length;i++){
					mygraveyard[i].MoveTo(this.graveyardX, this.graveyardY);
				}
				return;
			}
		}
	
		boolean handledClick=false;
		
		switch(this.currentDecisionType){
		case MULLIGAN:
			if(this.btnPass.IsClicked(mx, my)){
				this.mulligan=false;
				this.choiceMade=true;
			}else if(this.btnMulligan.IsClicked(mx, my)){
				this.mulligan=true;
				this.choiceMade=true;
			}
		break;
		case MAIN:
		case TIMER:
		case DECLARE_TARGETS:
		case PAY_MANA:
			if(this.delveSelector.IsSelecting()){
				if(this.btnCancel.IsClicked(mx, my)){
					this.delveSelector.Reset();
					this.resumeNormalButtonDisplay();
				}else if(this.btnConfirm.IsClicked(mx, my)){
					if(this.battleState.Hand[this.delveSelector.GetSourceIndex()].GetKeywords().HasConvoke()){
						this.delveSelector.StopSelecting();
						this.convokeSelector.StartSelecting(this.delveSelector.GetSourceIndex());
					}else if(this.handleTargetRequirements(this.battleState.Hand[this.delveSelector.GetSourceIndex()].GetPlayEffects())){
						this.cardIndexFromHandNeedsTargets=this.delveSelector.GetSourceIndex();
						this.delveSelector.StopSelecting();
					}else{
						this.currentDecision=PlayerAction.CreatePlayCardConvokedDelvedAction(this.delveSelector.GetSourceIndex(), this.convokeSelector.GetChoices(), this.delveSelector.GetChoices());
						this.choiceMade=true;
						this.delveSelector.Reset();
					}
				}else{
					boolean graveyardcardClicked=false;
					for(int graveyardIndex=0;graveyardIndex<this.battleState.Players[this.myIndex].Graveyard.length&&!graveyardcardClicked;graveyardIndex++){
						CardBase graveyardcard=this.battleState.Players[this.myIndex].Graveyard[graveyardIndex];
						if(graveyardcard.IsClicked(mx, my)){
							graveyardcardClicked=true;
							this.delveSelector.ToggleSelection(graveyardIndex);
						}
					}

					if(true/* TODO: spell can be cast check */)
						this.btnConfirm.Visible=true;
					else
						this.btnConfirm.Visible=false;
				}
			}else if(this.convokeSelector.IsSelecting()){
				if(this.btnCancel.IsClicked(mx, my)){
					this.delveSelector.Reset();
					this.convokeSelector.Reset();
					this.resumeNormalButtonDisplay();
				}else if(this.btnConfirm.IsClicked(mx, my)){
					if(this.handleTargetRequirements(this.battleState.Hand[this.convokeSelector.GetSourceIndex()].GetPlayEffects())){
						this.cardIndexFromHandNeedsTargets=this.convokeSelector.GetSourceIndex();
						this.convokeSelector.StopSelecting();
					}else{
						this.currentDecision=PlayerAction.CreatePlayCardConvokedDelvedAction(this.convokeSelector.GetSourceIndex(), this.convokeSelector.GetChoices(), this.delveSelector.GetChoices());
						this.choiceMade=true;
						this.convokeSelector.Reset();
						this.delveSelector.Reset();
					}
				}else{
					boolean fieldcardClicked=false;
					for(int fieldIndex=0;fieldIndex<this.battleState.Players[this.myIndex].Field.length&&!fieldcardClicked;fieldIndex++){
						CardBase fieldcard=this.battleState.Players[this.myIndex].Field[fieldIndex];
						if(fieldcard.IsCreature()&&!fieldcard.IsTapped()&&fieldcard.IsClicked(mx, my)){
							fieldcardClicked=true;
							this.convokeSelector.ToggleSelection(fieldIndex);
						}
					}

					if(true/* TODO: spell can be cast check */)
						this.btnConfirm.Visible=true;
					else
						this.btnConfirm.Visible=false;
				}
			}else if(this.effectsNeedsCostTargets!=null){
				if(this.btnCancel.IsClicked(mx, my)){
					this.targetEffectsIndex--;
					if(this.targetEffectsIndex<0){
						this.effectsNeedsCostTargets=null;
						this.resumeNormalButtonDisplay();
					}else{
						this.targetFieldIndex=0;
						while(this.targetFieldIndex<this.costtargetFieldIndexes[this.targetEffectsIndex].length&&this.costtargetFieldIndexes[this.targetEffectsIndex][this.targetFieldIndex]!=-1){
							this.targetFieldIndex++;
						}

						if(this.targetFieldIndex>=this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.GetRequiredTargetCount())
							this.btnConfirm.Visible=true;
						else
							this.btnConfirm.Visible=false;
					}
				}else if(this.btnConfirm.IsClicked(mx, my)){
					for(int setNulls=this.targetFieldIndex;setNulls<this.costtargetFieldControllerIndexes[this.targetEffectsIndex].length;setNulls++){
						this.costtargetFieldControllerIndexes[this.targetEffectsIndex][setNulls]=-1;
						this.costtargetFieldIndexes[this.targetEffectsIndex][setNulls]=-1;
					}

					this.targetEffectsIndex++;
					this.targetFieldIndex=0;
					while(this.targetEffectsIndex<this.costtargetFieldIndexes.length
					&&this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.GetMaxTargetCount()==0){
						this.targetEffectsIndex++;
					}

					if(this.targetEffectsIndex>=this.costtargetFieldIndexes.length){
						if(this.cardIndexFromHandNeedsCostTargets>=0){
							if(this.handleTargetRequirements(this.battleState.Hand[this.cardIndexFromHandNeedsCostTargets].GetPlayEffects())){
								this.cardIndexFromHandNeedsTargets=this.cardIndexFromHandNeedsCostTargets;
							}else{
								this.currentDecision=PlayerAction.CreatePlayCardConvokedDelvedWithTargetsAction(this.cardIndexFromHandNeedsTargets, this.costtargetFieldControllerIndexes, this.costtargetFieldIndexes, null, null, this.convokeSelector.GetChoices(), this.delveSelector.GetChoices());
								this.choiceMade=true;
							}
							this.effectsNeedsCostTargets=null;
							this.cardIndexFromHandNeedsCostTargets=-1;
						}else if(this.cardIndexOnFieldNeedsCostTargets>=0){
							if(this.handleTargetRequirements(this.battleState.Players[this.myIndex].Field[this.cardIndexOnFieldNeedsCostTargets].GetActivatedAbilities()[this.chosenAbilityIndex].Effects)){
								this.cardIndexOnFieldNeedsTargets=this.cardIndexOnFieldNeedsCostTargets;
							}else{
								this.currentDecision=PlayerAction.CreateActivateAbilityWithTargetsAction(this.cardIndexOnFieldNeedsCostTargets, this.chosenAbilityIndex, this.costtargetFieldControllerIndexes, this.costtargetFieldIndexes, null, null);
								this.choiceMade=true;
							}
							this.effectsNeedsCostTargets=null;
							this.cardIndexOnFieldNeedsCostTargets=-1;
						}
					}else{
						this.costtargetFieldControllerIndexes[this.targetEffectsIndex]=new int[this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.GetMaxTargetCount()];
						this.costtargetFieldIndexes[this.targetEffectsIndex]=new int[this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.GetMaxTargetCount()];

						if(this.targetFieldIndex>=this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.GetRequiredTargetCount())
							this.btnConfirm.Visible=true;
						else
							this.btnConfirm.Visible=false;
					}
				}else{
					for(int playerIndex=0;playerIndex<this.battleState.Players.length;playerIndex++){
						boolean fieldcardClicked=false;
						
						if(this.theStack!=null&&this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.STACK)){
							for(int fieldIndex=0;fieldIndex<this.theStack.size()&&!this.choiceMade&&!fieldcardClicked;fieldIndex++){
								CardBase ccard=this.theStack.get(fieldIndex).CardToPlay;
								if(ccard==null)
									continue;
								if(ccard.IsClicked(mx, my)){
									fieldcardClicked=true;
									this.toggleCostSelection(playerIndex, fieldIndex);
								}
							}
						}

						if(this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.GRAVEYARD)){
							for(int fieldIndex=0;fieldIndex<this.battleState.Players[playerIndex].Graveyard.length&&!this.choiceMade;fieldIndex++){
								if(this.battleState.Players[playerIndex].Graveyard[fieldIndex].IsClicked(mx, my)){
									fieldcardClicked=true;
									this.toggleCostSelection(playerIndex, fieldIndex);
								}
							}
						}

						if(this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.HAND)){
							for(int fieldIndex=0;fieldIndex<this.battleState.Players[playerIndex].Hand.length&&!this.choiceMade;fieldIndex++){
								if(this.battleState.Players[playerIndex].Hand[fieldIndex].IsClicked(mx, my)){
									fieldcardClicked=true;
									this.toggleCostSelection(playerIndex, fieldIndex);
								}
							}
						}

						if(this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.EXILE)){
							for(int fieldIndex=0;fieldIndex<this.battleState.Players[playerIndex].Exile.length&&!this.choiceMade;fieldIndex++){
								if(this.battleState.Players[playerIndex].Exile[fieldIndex].IsClicked(mx, my)){
									fieldcardClicked=true;
									this.toggleCostSelection(playerIndex, fieldIndex);
								}
							}
						}
						
						if(this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.BATTLEFIELD)){
							for(int fieldIndex=0;fieldIndex<this.battleState.Players[playerIndex].Field.length&&!this.choiceMade;fieldIndex++){
								if(this.battleState.Players[playerIndex].Field[fieldIndex].IsClicked(mx, my)){
									fieldcardClicked=true;
									this.toggleCostSelection(playerIndex, fieldIndex);
								}
							}
						}
						
						if(!fieldcardClicked&&this.battleState.Players[playerIndex].IsPlayingAreaClicked(mx, my)){
							this.toggleCostSelection(playerIndex, -1);
						}
						
						if(this.targetFieldIndex>=this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.GetRequiredTargetCount())
							this.btnConfirm.Visible=true;
						else
							this.btnConfirm.Visible=false;
					}
				}
			}else if(this.effectsNeedsTargets!=null){
				if(this.btnCancel.IsClicked(mx, my)){
					this.targetEffectsIndex--;
					if(this.targetEffectsIndex<0){
						this.effectsNeedsTargets=null;
						this.resumeNormalButtonDisplay();
					}else{
						this.targetFieldIndex=0;
						while(this.targetFieldIndex<this.targetFieldIndexes[this.targetEffectsIndex].length&&this.targetFieldIndexes[this.targetEffectsIndex][this.targetFieldIndex]!=-1){
							this.targetFieldIndex++;
						}

						if(this.targetFieldIndex>=this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.GetRequiredTargetCount())
							this.btnConfirm.Visible=true;
						else
							this.btnConfirm.Visible=false;
					}
				}else if(this.btnConfirm.IsClicked(mx, my)){
					for(int setNulls=this.targetFieldIndex;setNulls<this.targetFieldControllerIndexes[this.targetEffectsIndex].length;setNulls++){
						this.targetFieldControllerIndexes[this.targetEffectsIndex][setNulls]=-1;
						this.targetFieldIndexes[this.targetEffectsIndex][setNulls]=-1;
					}

					this.targetEffectsIndex++;
					this.targetFieldIndex=0;
					while(this.targetEffectsIndex<this.targetFieldIndexes.length
					&&this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.GetMaxTargetCount()==0){
						this.targetEffectsIndex++;
					}

					if(this.targetEffectsIndex>=this.targetFieldIndexes.length){
						if(this.currentDecisionType==DecisionType.DECLARE_TARGETS){
							this.currentDecision=PlayerAction.CreateDeclareTargetsAction(this.targetFieldControllerIndexes, this.targetFieldIndexes);
						}else if(this.cardIndexFromHandNeedsTargets>=0){
							this.currentDecision=PlayerAction.CreatePlayCardConvokedDelvedWithTargetsAction(this.cardIndexFromHandNeedsTargets, this.costtargetFieldControllerIndexes, this.costtargetFieldIndexes, this.targetFieldControllerIndexes, this.targetFieldIndexes, this.convokeSelector.GetChoices(), this.delveSelector.GetChoices());
						}else if(this.cardIndexOnFieldNeedsTargets>=0){
							this.currentDecision=PlayerAction.CreateActivateAbilityWithTargetsAction(this.cardIndexOnFieldNeedsTargets, this.chosenAbilityIndex, this.costtargetFieldControllerIndexes, this.costtargetFieldIndexes, this.targetFieldControllerIndexes, this.targetFieldIndexes);
						}
						this.effectsNeedsTargets=null;
						this.choiceMade=true;
					}else{
						this.targetFieldControllerIndexes[this.targetEffectsIndex]=new int[this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.GetMaxTargetCount()];
						this.targetFieldIndexes[this.targetEffectsIndex]=new int[this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.GetMaxTargetCount()];

						if(this.targetFieldIndex>=this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.GetRequiredTargetCount())
							this.btnConfirm.Visible=true;
						else
							this.btnConfirm.Visible=false;
					}
				}else{
					for(int playerIndex=0;playerIndex<this.battleState.Players.length;playerIndex++){
						boolean fieldcardClicked=false;

						if(this.theStack!=null){
							for(int fieldIndex=0;fieldIndex<this.theStack.size()&&!this.choiceMade&&!fieldcardClicked;fieldIndex++){
								CardBase ccard=this.theStack.get(fieldIndex).CardToPlay;
								if(ccard==null)
									continue;
								if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.STACK)&&ccard.IsClicked(mx, my)){
									fieldcardClicked=true;
									this.toggleSelection(playerIndex, fieldIndex);
								}
							}
						}

						for(int fieldIndex=0;fieldIndex<this.battleState.Players[playerIndex].Graveyard.length&&!this.choiceMade&&!fieldcardClicked;fieldIndex++){
							if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.GRAVEYARD)
							&&this.battleState.Players[playerIndex].Graveyard[fieldIndex].IsClicked(mx, my)){
								fieldcardClicked=true;
								this.toggleSelection(playerIndex, fieldIndex);
							}
						}

						for(int fieldIndex=0;fieldIndex<this.battleState.Players[playerIndex].Hand.length&&!this.choiceMade&&!fieldcardClicked;fieldIndex++){
							if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.HAND)
							&&this.battleState.Players[playerIndex].Hand[fieldIndex].IsClicked(mx, my)){
								fieldcardClicked=true;
								this.toggleSelection(playerIndex, fieldIndex);
							}
						}

						for(int fieldIndex=0;fieldIndex<this.battleState.Players[playerIndex].Exile.length&&!this.choiceMade&&!fieldcardClicked;fieldIndex++){
							if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.EXILE)
							&&this.battleState.Players[playerIndex].Exile[fieldIndex].IsClicked(mx, my)){
								fieldcardClicked=true;
								this.toggleSelection(playerIndex, fieldIndex);
							}
						}
						
						for(int fieldIndex=0;fieldIndex<this.battleState.Players[playerIndex].Field.length&&!this.choiceMade&&!fieldcardClicked;fieldIndex++){
							if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.BATTLEFIELD)
							&&this.battleState.Players[playerIndex].Field[fieldIndex].IsClicked(mx, my)){
								fieldcardClicked=true;
								this.toggleSelection(playerIndex, fieldIndex);
							}
						}

						if(!fieldcardClicked&&this.battleState.Players[playerIndex].IsPlayingAreaClicked(mx, my)){
							this.toggleSelection(playerIndex, -1);
						}
						
						if(this.targetFieldIndex>=this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.GetRequiredTargetCount())
							this.btnConfirm.Visible=true;
						else
							this.btnConfirm.Visible=false;
					}
				}
			}else if(this.abilityOptions!=null){
				for(int i=0;i<this.abilityOptions.length;i++){
					if(this.btnOptions[i].IsClicked(mx, my)){
						if(this.handleCostTargetRequirements(abilityOptions[i].Costs)){
							this.cardIndexOnFieldNeedsCostTargets=this.cardIndexPendingAbilityChoice;
							this.chosenAbilityIndex=i;
						}else if(this.handleTargetRequirements(abilityOptions[i].Effects)){
							this.cardIndexOnFieldNeedsTargets=this.cardIndexPendingAbilityChoice;
							this.chosenAbilityIndex=i;
						}else{
							this.currentDecision=PlayerAction.CreateActivateAbilityAction(this.cardIndexPendingAbilityChoice, i, this.cardZonePendingAbilityChoice);
							this.choiceMade=true;
						}
					}
					
					this.btnOptions[i].Visible=false;
					this.optionsText=null;
				}
				this.abilityOptions=null;
			}else if(this.btnPass.IsClicked(mx, my)){
				this.currentDecision=PlayerAction.CreatePassAction();
				this.choiceMade=true;
			}else if(this.btnPause.IsClicked(mx, my)){
				this.currentDecision=PlayerAction.CreateTimerTogglePauseAction();
				this.choiceMade=true;
			}else{
				for(int i=0;i<this.battleState.Hand.length&&!this.choiceMade;i++){
					if(this.battleState.Hand[i].Playable&&this.battleState.Hand[i].IsClicked(mx, my)){
						if(this.battleState.Hand[i].GetKeywords().HasDelve()){
							this.delveSelector.StartSelecting(i);
							this.btnPause.Visible=false;
							this.btnPass.Visible=false;
							this.btnCancel.Visible=true;
							//TODO: check if spell can be cast
							this.btnConfirm.Visible=true;
							this.viewGraveyard();
						}else if(this.battleState.Hand[i].GetKeywords().HasConvoke()){
							this.convokeSelector.StartSelecting(i);
							this.btnPause.Visible=false;
							this.btnPass.Visible=false;
							this.btnCancel.Visible=true;
							//TODO: check if spell can be cast
							this.btnConfirm.Visible=true;
						}else if(this.handleCostTargetRequirements(this.battleState.Hand[i].GetPlayCosts())){
							this.cardIndexFromHandNeedsCostTargets=i;
						}else if(this.handleTargetRequirements(this.battleState.Hand[i].GetPlayEffects())){
							this.cardIndexFromHandNeedsTargets=i;
						}else{
							this.currentDecision=PlayerAction.CreatePlayCardAction(i);
							this.choiceMade=true;
						}
					}
//System.out.println("Hand Card #"+i+": Playable: "+this.battleState.Hand[i].Playable+" Activatable: "+this.battleState.Hand[i].Activatable+" IsClicked("+mx+","+my+"): "+this.battleState.Hand[i].IsClicked(mx, my));
				}

				if(this.viewingZone==ZoneOptions.BATTLEFIELD){
					for(int i=0;i<this.battleState.Players[this.myIndex].Field.length&&!this.choiceMade;i++){
						this.cardClickCheck(this.battleState.Players[this.myIndex].Field[i], mx, my, i, ZoneOptions.BATTLEFIELD);
					}
				}
				
				if(this.viewingZone==ZoneOptions.GRAVEYARD){
					CardBase[] mygraveyard=this.battleState.Players[this.myIndex].Graveyard;
					for(int i=0;i<mygraveyard.length;i++){
						this.cardClickCheck(mygraveyard[i], mx, my, i, ZoneOptions.GRAVEYARD);
					}
				}
//System.out.println("Permanent #"+i+": Activatable: "+this.battleState.Players[this.myIndex].Field[i].Activatable+" IsClicked("+mx+","+my+"): "+this.battleState.Players[this.myIndex].Field[i].IsClicked(mx, my));
			}
		break;
		case DECLARE_ATTACKERS:
			if(this.btnPass.IsClicked(mx, my)){
				this.currentDecision=PlayerAction.CreatePassAction();
				this.choiceMade=true;
			}else if(this.selectedFieldIndex<0){
				for(int i=0;i<this.battleState.Players[this.myIndex].Field.length&&!this.choiceMade;i++){
					if(this.battleState.Players[this.myIndex].Field[i].Activatable&&this.battleState.Players[this.myIndex].Field[i].IsClicked(mx, my)){
						this.selectedFieldIndex=i;
					}
				}
			}else{
				for(int i=0;i<this.battleState.Players.length;i++){
					ReadOnlyPlayer cplayer=this.battleState.Players[i];
					for(int f=0;!this.choiceMade&&f<cplayer.Field.length;f++){
						if(cplayer.Field[f].IsPlaneswalker()&&cplayer.Field[f].IsClicked(mx, my)){
							this.currentDecision=PlayerAction.CreateAttackPlaneswalkerAction(this.selectedFieldIndex, i, f);
							this.selectedFieldIndex=-1;
							this.choiceMade=true;
						}
					}
					if(!this.choiceMade&&cplayer.IsPlayingAreaClicked(mx, my)){
						this.currentDecision=PlayerAction.CreateAttackAction(this.selectedFieldIndex, i);
						this.selectedFieldIndex=-1;
						this.choiceMade=true;
					}
				}
			}
		break;
		case DECLARE_BLOCKERS:
			if(this.btnPass.IsClicked(mx, my)){
				this.currentDecision=PlayerAction.CreatePassAction();
				this.choiceMade=true;
			}else if(this.selectedFieldIndex<0){
				for(int i=0;i<this.battleState.Players[this.myIndex].Field.length&&!this.choiceMade;i++){
					if(this.battleState.Players[this.myIndex].Field[i].Activatable&&this.battleState.Players[this.myIndex].Field[i].IsClicked(mx, my)){
						this.selectedFieldIndex=i;
					}
				}
			}else{
				for(int i=0;i<this.attackers.length;i++){
					if(this.attackers[i].IsClicked(mx, my)){
						this.currentDecision=PlayerAction.CreateBlockAction(this.selectedFieldIndex, i);
						this.selectedFieldIndex=-1;
						this.choiceMade=true;
					}
				}
				if(!this.choiceMade){
					this.currentDecision=PlayerAction.CreateBlockAction(this.selectedFieldIndex, -1);
					this.selectedFieldIndex=-1;
					this.choiceMade=true;
				}
			}
		break;
		case KEEP_OR_PLACEONBOTTOM:
			if(this.btnConfirm.IsClicked(mx, my)){
				this.currentDecision=PlayerAction.CreateNoAction();
				this.choiceMade=true;
			}else{
				handledClick=false;
				for(int i=0;!handledClick&&i<this.cardsToPutOnBottom.size();i++){
					CardBase cardtotoss=this.cardsToPutOnBottom.elementAt(i);
					if(cardtotoss.IsClicked(mx, my)){
						synchronized(this.cardsToPutOnBottom){
							this.cardsToKeep.add(cardtotoss);
							this.cardsToPutOnBottom.removeElementAt(i);
						}
						handledClick=true;
					}
				}
				for(int i=0;!handledClick&&i<this.cardsToKeep.size();i++){
					CardBase cardtokeep=this.cardsToKeep.elementAt(i);
					if(cardtokeep.IsClicked(mx, my)){
						synchronized(this.cardsToKeep){
							this.cardsToPutOnBottom.add(cardtokeep);
							this.cardsToKeep.removeElementAt(i);
						}
						handledClick=true;
					}
				}
				if(handledClick){
					this.adjustCardsToKeep();
					this.adjustCardsToPutOnBottom();
				}
			}
		break;
		case ORDER_CARDS:
			if(this.btnConfirm.IsClicked(mx, my)){
				this.currentDecision=PlayerAction.CreateNoAction();
				this.choiceMade=true;
			}else{
				handledClick=false;
				for(int i=0;!handledClick&&i<this.releventCards.length;i++){
					if(this.releventCards[this.ordering[i]].IsClicked(mx, my)){
						if(this.indexToSwap==-1){
							this.indexToSwap=i;
						}else{
							int t=this.ordering[i];
							this.ordering[i]=this.ordering[this.indexToSwap];
							this.ordering[this.indexToSwap]=t;
							this.indexToSwap=-1;
							this.adjustCardsToOrder();
						}
						handledClick=true;
					}
				}
			}
		break;
		case CHOOSE_CARDS:
			if(this.btnConfirm.IsClicked(mx, my)){
				this.currentDecision=PlayerAction.CreateNoAction();
				this.choiceMade=true;
			}else{
				handledClick=false;
				for(int i=0;!handledClick&&i<this.releventCards.length;i++){
					if(this.releventCards[i].IsClicked(mx, my)){
						this.cardSelector.ToggleSelection(i);
						this.adjustCardsToChoose();
						handledClick=true;
					}
				}
			}
		break;
		case YES_NO:
			if(this.btnYes.IsClicked(mx, my)){
				this.decisionYesNo=true;
				this.currentDecision=PlayerAction.CreateNoAction();
				this.choiceMade=true;
			}else if(this.btnNo.IsClicked(mx, my)){
				this.decisionYesNo=false;
				this.currentDecision=PlayerAction.CreateNoAction();
				this.choiceMade=true;
			}
		break;
		case COLOR_CHOICE:
			for(int i=0;i<this.btnColors.length;i++){
				if(this.btnColors[i].IsClicked(mx, my)){
					this.decisionColor=1<<i;
					this.currentDecision=PlayerAction.CreateNoAction();
					this.choiceMade=true;
				}
			}
		break;
		case PLAYER_CHOICE:
			for(int i=0;i<this.battleState.Players.length;i++){
				if(i==this.invalidPlayerChoiceIndex)
					continue;

				ReadOnlyPlayer cplayer=this.battleState.Players[i];
				for(int f=0;!this.choiceMade&&f<cplayer.Field.length;f++){
					if(cplayer.Field[f].IsPlaneswalker()&&cplayer.Field[f].IsClicked(mx, my)){
						this.currentDecision=PlayerAction.CreateAttackPlaneswalkerAction(-1, i, f);
						this.choiceMade=true;
					}
				}
				if(!this.choiceMade&&cplayer.IsPlayingAreaClicked(mx, my)){
					this.currentDecision=PlayerAction.CreateAttackAction(-1, i);
					this.choiceMade=true;
				}
			}
		break;
		case ACK:
			if(this.btnPass.IsClicked(mx, my)){
				this.currentDecision=PlayerAction.CreatePassAction();
				this.choiceMade=true;
			}
		break;
		}
		
		if(this.choiceMade){
			this.btnPass.Visible=false;
			this.btnMulligan.Visible=false;
			this.btnCancel.Visible=false;
			this.btnConfirm.Visible=false;
			synchronized(this.phaselock){
				this.phaselock.notifyAll();
			}
		}else if(!handledClick){
			if(this.battleState!=null){
				if(this.viewingZone!=ZoneOptions.GRAVEYARD){
					if(this.battleState.Players[this.myIndex].Graveyard.length>0&&this.battleState.Players[this.myIndex].Graveyard[0].IsClicked(mx ,my)){
						this.viewGraveyard();
						return;
					}
				}
			}
		}
	}
	
	private void paintCardBorder(Graphics g, Color c, CardBase selcard){
		g.setColor(c);
		g.drawRect(selcard.GetX()-3, selcard.GetY()-3, selcard.GetWidth()+6, selcard.GetHeight()+6);
		g.drawRect(selcard.GetX()-4, selcard.GetY()-4, selcard.GetWidth()+8, selcard.GetHeight()+8);
	}

	private void paintPlayerBorder(Graphics g, Color c, ReadOnlyPlayer player){
		g.setColor(c);
		g.drawRect(player.GetMinX()+1, player.GetMinY()+1, player.GetWidth()+-2, player.GetHeight()-2);
		g.drawRect(player.GetMinX()+2, player.GetMinY()+2, player.GetWidth()+-4, player.GetHeight()-4);
	}
	
	public void paintFullViewCard(Graphics g){
		if(this.viewingMode&&this.fullviewCard!=null){
			this.fullviewCard.paintFullVersionAt(g, 300, 100);
		}else if(this.abilityOptions!=null){
			switch(this.cardZonePendingAbilityChoice){
			case ZoneOptions.BATTLEFIELD:
				this.battleState.Players[this.myIndex].Field[this.cardIndexPendingAbilityChoice].paintFullVersionAt(g, 300, 100);
			break;
			case ZoneOptions.GRAVEYARD:
				this.battleState.Players[this.myIndex].Graveyard[this.cardIndexPendingAbilityChoice].paintFullVersionAt(g, 300, 100);
			break;
			}
		}
	}
	
	public void paint(Graphics g){
		if(this.battleState!=null&&this.selectedFieldIndex>=0&&this.selectedFieldIndex<this.battleState.Players[this.myIndex].Field.length){
			this.paintCardBorder(g, Color.red, this.battleState.Players[this.myIndex].Field[this.selectedFieldIndex]);
		}else if(this.effectsNeedsCostTargets!=null){
			if(this.cardIndexFromHandNeedsCostTargets>=0){
				this.paintCardBorder(g, Color.magenta, this.battleState.Hand[this.cardIndexFromHandNeedsCostTargets]);
			}else if(this.cardIndexOnFieldNeedsCostTargets>=0){
				this.paintCardBorder(g, Color.magenta, this.battleState.Players[this.myIndex].Field[this.cardIndexOnFieldNeedsCostTargets]);
			}
			for(int i=0;i<this.targetFieldIndex;i++){
				if(costtargetFieldIndexes[this.targetEffectsIndex][i]==-1){
					this.paintPlayerBorder(g, Color.red, this.battleState.Players[costtargetFieldControllerIndexes[this.targetEffectsIndex][i]]);
				}else{
					CardBase selectedTarget=null;
					if(this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.BATTLEFIELD)){
						selectedTarget=this.battleState.Players[costtargetFieldControllerIndexes[this.targetEffectsIndex][i]].Field[costtargetFieldIndexes[this.targetEffectsIndex][i]];
					}else if(this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.GRAVEYARD)){
						selectedTarget=this.battleState.Players[costtargetFieldControllerIndexes[this.targetEffectsIndex][i]].Graveyard[costtargetFieldIndexes[this.targetEffectsIndex][i]];
					}else if(this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.HAND)){
						selectedTarget=this.battleState.Players[costtargetFieldControllerIndexes[this.targetEffectsIndex][i]].Hand[costtargetFieldIndexes[this.targetEffectsIndex][i]];
					}else if(this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.EXILE)){
						selectedTarget=this.battleState.Players[costtargetFieldControllerIndexes[this.targetEffectsIndex][i]].Exile[costtargetFieldIndexes[this.targetEffectsIndex][i]];
					}else if(this.effectsNeedsCostTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.STACK)){
						selectedTarget=this.theStack.get(costtargetFieldControllerIndexes[this.targetEffectsIndex][i]).CardToPlay;
					}
					this.paintCardBorder(g, Color.red, selectedTarget);
				}
			}
			g.setColor(Color.black);
			g.drawString("Choose cost target(s)", 300, 46);
		}else if(this.effectsNeedsTargets!=null){
			if(this.cardIndexFromHandNeedsTargets>=0){
				this.paintCardBorder(g, Color.magenta, this.battleState.Hand[this.cardIndexFromHandNeedsTargets]);
			}else if(this.cardIndexOnFieldNeedsTargets>=0){
				this.paintCardBorder(g, Color.magenta, this.battleState.Players[this.myIndex].Field[this.cardIndexOnFieldNeedsTargets]);
			}
			for(int i=0;i<this.targetFieldIndex;i++){
				if(targetFieldIndexes[this.targetEffectsIndex][i]==-1){
					this.paintPlayerBorder(g, Color.red, this.battleState.Players[targetFieldControllerIndexes[this.targetEffectsIndex][i]]);
				}else{
					CardBase selectedTarget=null;
					if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.BATTLEFIELD)){
						selectedTarget=this.battleState.Players[targetFieldControllerIndexes[this.targetEffectsIndex][i]].Field[targetFieldIndexes[this.targetEffectsIndex][i]];
					}else if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.GRAVEYARD)){
						selectedTarget=this.battleState.Players[targetFieldControllerIndexes[this.targetEffectsIndex][i]].Graveyard[targetFieldIndexes[this.targetEffectsIndex][i]];
					}else if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.HAND)){
						selectedTarget=this.battleState.Players[targetFieldControllerIndexes[this.targetEffectsIndex][i]].Hand[targetFieldIndexes[this.targetEffectsIndex][i]];
					}else if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.EXILE)){
						selectedTarget=this.battleState.Players[targetFieldControllerIndexes[this.targetEffectsIndex][i]].Exile[targetFieldIndexes[this.targetEffectsIndex][i]];
					}else if(this.effectsNeedsTargets[this.targetEffectsIndex].TargetData.IsValidZone(ZoneOptions.STACK)){
						selectedTarget=this.theStack.get(targetFieldIndexes[this.targetEffectsIndex][i]).CardToPlay;
					}
					this.paintCardBorder(g, Color.red, selectedTarget);
				}
			}
			g.setColor(Color.black);
			g.drawString("Choose target(s)", 300, 46);
		}else if(this.abilityOptions!=null){
			g.setColor(Color.black);
			g.drawString("Choose ability.", 300, 46);
		}else if(this.delveSelector.IsSelecting()){
			ArrayList<Integer> choices=this.delveSelector.GetChoicesArrayList();
			this.paintCardBorder(g, Color.magenta, this.battleState.Hand[this.delveSelector.GetSourceIndex()]);
			for(int choiceIndex=0;choiceIndex<choices.size();choiceIndex++){
				this.paintCardBorder(g, Color.red, this.battleState.Players[this.myIndex].Graveyard[choices.get(choiceIndex)]);
			}
			g.setColor(Color.black);
			g.drawString("Choose targets for delve.", 300, 46);
		}else if(this.convokeSelector.IsSelecting()){
			ArrayList<Integer> choices=this.convokeSelector.GetChoicesArrayList();
			this.paintCardBorder(g, Color.magenta, this.battleState.Hand[this.convokeSelector.GetSourceIndex()]);
			for(int choiceIndex=0;choiceIndex<choices.size();choiceIndex++){
				this.paintCardBorder(g, Color.red, this.battleState.Players[this.myIndex].Field[choices.get(choiceIndex)]);
			}
			g.setColor(Color.black);
			g.drawString("Choose targets for convoke.", 300, 46);
		}
		switch(this.currentDecisionType){
		case PAY_MANA:
			g.setColor(Color.black);
			g.drawString("Pay ", 300, 46);
			this.manaCostToPay.paint(g, 330, 32);
		break;
		case KEEP_OR_PLACEONBOTTOM:
			Painting.FillRectWithBorder(g, this.paintingAreaMinX+10, this.paintingAreaMinY+10, this.paintingAreaMaxX-this.paintingAreaMinX-20, this.paintingAreaMaxY-this.paintingAreaMinY-20, Color.white, Color.black);
			g.setColor(Color.black);
			g.drawLine(this.paintingAreaMinX+10, (this.paintingAreaMinY+this.paintingAreaMaxY)/2, this.paintingAreaMaxX-10, (this.paintingAreaMinY+this.paintingAreaMaxY)/2);
			g.drawString("Place on bottom", this.paintingAreaMinX+12, this.paintingAreaMinY+24);
			g.drawString("Keep", this.paintingAreaMinX+12, (this.paintingAreaMinY+this.paintingAreaMaxY)/2+14);
			for(int i=0;i<this.releventCards.length;i++)
				this.releventCards[i].paint(g);
		break;
		case ORDER_CARDS:
			Painting.FillRectWithBorder(g, this.paintingAreaMinX+10, this.paintingAreaMinY+10, this.paintingAreaMaxX-this.paintingAreaMinX-20, this.paintingAreaMaxY-this.paintingAreaMinY-20, Color.white, Color.black);
			g.setColor(Color.black);
			g.drawString("Choose the order.", this.paintingAreaMinX+12, this.paintingAreaMinY+24);
			for(int i=0;i<this.releventCards.length;i++){
				this.releventCards[this.ordering[i]].paint(g);
				if(this.indexToSwap==i)
					this.paintCardBorder(g, Color.magenta, this.releventCards[this.ordering[i]]);
				g.setColor(Color.black);
				g.drawString(""+(i+1), this.releventCards[this.ordering[i]].GetX(), this.releventCards[this.ordering[i]].GetY()-6);
			}
		break;
		case CHOOSE_CARDS:
			Painting.FillRectWithBorder(g, this.paintingAreaMinX+10, this.paintingAreaMinY+10, this.paintingAreaMaxX-this.paintingAreaMinX-20, this.paintingAreaMaxY-this.paintingAreaMinY-20, Color.white, Color.black);
			g.setColor(Color.black);
			g.drawLine(this.paintingAreaMinX+10, (this.paintingAreaMinY+this.paintingAreaMaxY)/2, this.paintingAreaMaxX-10, (this.paintingAreaMinY+this.paintingAreaMaxY)/2);
			g.drawString("Chosen (Min: "+this.cardSelectorMin+" Max: "+this.cardSelectorMax+")", this.paintingAreaMinX+12, this.paintingAreaMinY+24);
			g.drawString("Not Chosen", this.paintingAreaMinX+12, (this.paintingAreaMinY+this.paintingAreaMaxY)/2+14);
			g.drawString(this.cardSelectionDescription, 300, 46);
			for(int i=0;i<this.releventCards.length;i++){
				this.releventCards[i].paint(g);
			}
		break;
		case YES_NO:
			Painting.FillRectWithBorder(g, this.paintingAreaMinX+10, this.paintingAreaMinY+50, this.paintingAreaMaxX-this.paintingAreaMinX-20, this.paintingAreaMaxY-this.paintingAreaMinY-100, Color.white, Color.black);
			g.setColor(Color.black);
			Painting.DrawWrappedString(g, this.textYesNo, this.paintingAreaMinX+12, this.paintingAreaMinY+64, this.paintingAreaMaxX-this.paintingAreaMinX-20, 14);
		break;
		case COLOR_CHOICE:
			Painting.FillRectWithBorder(g, this.paintingAreaMinX+10, this.paintingAreaMinY+50, this.paintingAreaMaxX-this.paintingAreaMinX-20, this.paintingAreaMaxY-this.paintingAreaMinY-100, Color.white, Color.black);
			g.setColor(Color.black);
			Painting.DrawWrappedString(g, this.textColorChoice, this.paintingAreaMinX+12, this.paintingAreaMinY+64, this.paintingAreaMaxX-this.paintingAreaMinX-20, 14);
		break;
		case PLAYER_CHOICE:
			g.setColor(Color.black);
			g.drawString(this.textChoosePlayer, 300, 46);
		break;
		case ACK:
			g.setColor(Color.black);
			g.drawString(this.textAck, 300, 46);
		break;
		}

		if(this.viewingZone==ZoneOptions.GRAVEYARD){
			Painting.FillRectWithBorder(g, this.paintingAreaMinX+10, this.paintingAreaMinY+10, this.paintingAreaMaxX-this.paintingAreaMinX-20, this.paintingAreaMaxY-this.paintingAreaMinY-20, Color.white, Color.black);
			CardBase[] mygraveyard=this.battleState.Players[this.myIndex].Graveyard;
			for(int i=0;i<mygraveyard.length;i++){
				mygraveyard[i].paint(g);
			}
		}
		
		this.btnPass.paint(g);
		this.btnMulligan.paint(g);
		this.btnPause.paint(g);
		this.btnCancel.paint(g);
		this.btnConfirm.paint(g);
		this.btnYes.paint(g);
		this.btnNo.paint(g);
		for(int i=0;i<this.btnOptions.length;i++){
			this.btnOptions[i].paint(g);
		}
		for(int i=0;i<this.btnColors.length;i++){
			this.btnColors[i].paint(g);
		}
		String otext=this.optionsText;
		if(otext!=null){
			Painting.FillRectWithBorder(g, this.optionsTextX, this.optionsTextY, OPTIONS_TEXT_WIDTH, OPTIONS_TEXT_HEIGHT, Color.white, Color.black);
			g.setColor(Color.black);
			Painting.DrawWrappedString(g, otext, this.optionsTextX+2, this.optionsTextY+14, OPTIONS_TEXT_WIDTH, 14);
		}
	}
}
