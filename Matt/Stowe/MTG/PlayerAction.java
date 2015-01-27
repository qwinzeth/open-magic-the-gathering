package Matt.Stowe.MTG;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

public class PlayerAction{
	private int cardIndex;
	private int card2Index;
	private ActionType actionType;
	private int abilityIndex;
	private int cardZone;
	private int playerIndex;
	private int[][] targetCardControllerCostIndexes;
	private int[][] targetFieldCardCostIndexes;
	private int[][] targetCardControllerIndexes;
	private int[][] targetFieldCardIndexes;
	private int[] convokedIndexes;
	private int[] delvedIndexes;

	private enum ActionType{
		NONE,
		PAUSE_TIMER,
		SKIP_TIMER,
		PASS,
		PLAY_CARD,
		PLAY_CARD_DELVE,
		ACTIVATE_ABILITY,
		DECLARE_ATTACKER,
		DECLARE_PLANESWALKER_ATTACKER,
		DECLARE_BLOCKER,
		DECLARE_TARGETS,
	}
	
	public boolean isNoAction(){return this.actionType==ActionType.NONE;}
	public boolean isPassAction(){return this.actionType==ActionType.PASS;}
	public boolean isTimerPauseAction(){return this.actionType==ActionType.PAUSE_TIMER;}
	public boolean isTimerSkipAction(){return this.actionType==ActionType.SKIP_TIMER;}
	public int getCardIndexToPlayFromHand(){
		if(this.actionType!=ActionType.PLAY_CARD
		&&this.actionType!=ActionType.PLAY_CARD_DELVE)
			return -1;
		return this.cardIndex;
	}
	
	public int GetCardZone(){return this.cardZone;}
	public int[][] GetTargetCardControllerCostIndexes(){return this.targetCardControllerCostIndexes;}
	public int[][] GetTargetFieldCostIndexes(){return this.targetFieldCardCostIndexes;}
	public int[][] GetTargetCardControllerIndexes(){return this.targetCardControllerIndexes;}
	public int[][] GetTargetFieldIndexes(){return this.targetFieldCardIndexes;}
	public int[] GetConvokedIndexes(){return this.convokedIndexes;}
	public int[] GetDelvedIndexes(){return this.delvedIndexes;}

	// REGION ACTIVATE_ABILITY
	public int getPermanentIndexToActivate(){
		if(this.actionType!=ActionType.ACTIVATE_ABILITY)
			return -1;
		return this.cardIndex;
	}
	
	public int getPermanentAbilityIndexToActivate(){
		if(this.actionType!=ActionType.ACTIVATE_ABILITY)
			return -1;
		return this.abilityIndex;
	}
	// ENDREGION ACTIVATE_ABILITY

	// REGION DECLARE_ATTACKER
	public int getAttackingCreatureIndex(){
		if(this.actionType!=ActionType.DECLARE_ATTACKER)
			return -1;
		return this.cardIndex;
	}
	
	public int getAttackedPlayerIndex(){
		if(this.actionType!=ActionType.DECLARE_ATTACKER)
			return -1;
		return this.playerIndex;
	}
	// ENDREGION DECLARE_ATTACKER

	// REGION DECLARE_PLANESWALKER_ATTACKER
	public int getAttackingPlaneswalkerCreatureIndex(){
		if(this.actionType!=ActionType.DECLARE_PLANESWALKER_ATTACKER)
			return -1;
		return this.cardIndex;
	}
	
	public int getAttackedPlaneswalkerControllerIndex(){
		if(this.actionType!=ActionType.DECLARE_PLANESWALKER_ATTACKER)
			return -1;
		return this.playerIndex;
	}

	public int getAttackedPlaneswalkerIndex(){
		if(this.actionType!=ActionType.DECLARE_PLANESWALKER_ATTACKER)
			return -1;
		return this.card2Index;
	}
	// ENDREGION DECLARE_PLANESWALKER_ATTACKER

	// REGION DECLARE_BLOCKER
	public int getDefendingCreatureIndex(){
		if(this.actionType!=ActionType.DECLARE_BLOCKER)
			return -1;
		return this.cardIndex;
	}
	
	public int getAttackingCreatureToBlockIndex(){
		if(this.actionType!=ActionType.DECLARE_BLOCKER)
			return -1;
		return this.card2Index;
	}
	// ENDREGION DECLARE_BLOCKER

	private PlayerAction(ActionType atype, int chosenIndex, int option1Index){
		this.init(atype, ZoneOptions.BATTLEFIELD, chosenIndex, option1Index, null, null, null, null, null, null);
	}
	
	private PlayerAction(ActionType atype, int chosenIndex, int option1Index, int cardZone){
		this.init(atype, cardZone, chosenIndex, option1Index, null, null, null, null, null, null);
	}

	private PlayerAction(ActionType atype, int chosenIndex, int option1Index, int[][] controllerCostIndexes, int[][] targetCostIndexes, int[][] controllerIndexes, int[][] targetIndexes){
		this.init(atype, ZoneOptions.BATTLEFIELD, chosenIndex, option1Index, controllerCostIndexes, targetCostIndexes, controllerIndexes, targetIndexes, null, null);
	}
	
	private PlayerAction(ActionType atype, int chosenIndex, int option1Index, int[][] controllerCostIndexes, int[][] targetCostIndexes, int[][] controllerIndexes, int[][] targetIndexes, int[] convokeIndexes, int[] delveIndexes){
		this.init(atype, ZoneOptions.BATTLEFIELD, chosenIndex, option1Index, controllerCostIndexes, targetCostIndexes, controllerIndexes, targetIndexes, convokeIndexes, delveIndexes);
	}

	private void init(ActionType atype, int cardZone, int chosenIndex, int option1Index, int[][] controllerCostIndexes, int[][] targetCostIndexes, int[][] controllerIndexes, int[][] targetIndexes, int[] convokeIndexes, int[] delveIndexes){
		this.actionType=atype;
		this.cardZone=cardZone;
		switch(this.actionType){
		case PLAY_CARD:
		case PLAY_CARD_DELVE:
			this.cardIndex=chosenIndex;
		break;
		case ACTIVATE_ABILITY:
			this.cardIndex=chosenIndex;
			this.abilityIndex=option1Index;
		break;
		case DECLARE_ATTACKER:
			this.cardIndex=chosenIndex;
			this.playerIndex=option1Index;
		break;
		case DECLARE_PLANESWALKER_ATTACKER:
			this.cardIndex=chosenIndex;
			this.playerIndex=controllerIndexes[0][0];
			this.card2Index=targetIndexes[0][0];
		break;
		case DECLARE_BLOCKER:
			this.cardIndex=chosenIndex;
			this.card2Index=option1Index;
		}
		
		this.targetCardControllerCostIndexes=controllerCostIndexes;
		this.targetFieldCardCostIndexes=targetCostIndexes;
		this.targetCardControllerIndexes=controllerIndexes;
		this.targetFieldCardIndexes=targetIndexes;
		this.convokedIndexes=convokeIndexes;
		this.delvedIndexes=delveIndexes;
	}
	
	public static PlayerAction CreateNoAction(){
		return new PlayerAction(ActionType.NONE, -1, -1);
	}
	
	public static PlayerAction CreatePassAction(){
		return new PlayerAction(ActionType.PASS, -1, -1);
	}
	
	public static PlayerAction CreatePlayCardAction(int handIndex){
		return new PlayerAction(ActionType.PLAY_CARD, handIndex, -1);
	}
	
	public static PlayerAction CreatePlayCardConvokedDelvedAction(int handIndex, int[] convokeIndexes, int[] delveIndexes){
		return new PlayerAction(ActionType.PLAY_CARD, handIndex, -1, null, null, null, null, convokeIndexes, delveIndexes);
	}
	
	public static PlayerAction CreatePlayCardConvokedDelvedWithTargetsAction(int handIndex, int[][] controllerCostIndexes, int[][] targetCostIndexes, int[][] controllerIndexes, int[][] targetIndexes, int[] convokeIndexes, int[] delveIndexes){
		return new PlayerAction(ActionType.PLAY_CARD, handIndex, -1, controllerCostIndexes, targetCostIndexes, controllerIndexes, targetIndexes, convokeIndexes, delveIndexes);
	}

	public static PlayerAction CreateActivateAbilityAction(int permanentIndex, int abilityIndex, int cardZone){
		return new PlayerAction(ActionType.ACTIVATE_ABILITY, permanentIndex, abilityIndex, cardZone);
	}
	
	public static PlayerAction CreateActivateAbilityWithTargetsAction(int permanentIndex, int abilityIndex, int[][] controllerCostIndexes, int[][] targetCostIndexes, int[][] controllerIndexes, int[][] targetIndexes){
		return new PlayerAction(ActionType.ACTIVATE_ABILITY, permanentIndex, abilityIndex, controllerCostIndexes, targetCostIndexes, controllerIndexes, targetIndexes);
	}

	public static PlayerAction CreateTimerTogglePauseAction(){
		return new PlayerAction(ActionType.PAUSE_TIMER, -1, -1);
	}
	
	public static PlayerAction CreateTimerSkipAction(){
		return new PlayerAction(ActionType.SKIP_TIMER, -1, -1);
	}

	public static PlayerAction CreateAttackAction(int attackingCreatureIndex, int targetPlayerIndex){
		return new PlayerAction(ActionType.DECLARE_ATTACKER, attackingCreatureIndex, targetPlayerIndex);
	}

	public static PlayerAction CreateAttackPlaneswalkerAction(int attackingCreatureIndex, int targetPlayerIndex, int targetFieldIndex){
		int[][] controller=new int[1][1];
		controller[0][0]=targetPlayerIndex;
		int[][] planeswalker=new int[1][1];
		planeswalker[0][0]=targetFieldIndex;
		return new PlayerAction(ActionType.DECLARE_PLANESWALKER_ATTACKER, attackingCreatureIndex, -1, null, null, controller, planeswalker);
	}

	public static PlayerAction CreateBlockAction(int defendingCreatureIndex, int attackingCreatureIndex){
		return new PlayerAction(ActionType.DECLARE_BLOCKER, defendingCreatureIndex, attackingCreatureIndex);
	}
	
	public static PlayerAction CreateDeclareTargetsAction(int[][] controllerIndexes, int[][] targetIndexes){
		return new PlayerAction(ActionType.DECLARE_TARGETS, -1, -1, null, null, controllerIndexes, targetIndexes);
	}	
}
