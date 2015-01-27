package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public class TriggerConditionControllerAttackedThisTurn extends TriggerCondition{
	private CardBase cardControlled;

	public TriggerConditionControllerAttackedThisTurn(CardBase card){
		this.cardControlled=card;
	}

	public TriggerCondition DeepCopy(){
		return new TriggerConditionControllerAttackedThisTurn(this.cardControlled);
	}

	public boolean MeetsCondition(ITargetable triggeringObject, Vector<Player> players){
		return triggeringObject==this.cardControlled&&players.get(this.cardControlled.GetControllerIndex()).AttackedThisTurn();
	}
	
	public String GetRulesText(){
		StringBuilder rules=new StringBuilder();
		rules.append("you attacked this turn and "+this.cardControlled.GetName());
		return rules.toString();
	}
}
