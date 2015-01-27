package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public class TriggerConditionControllerDidntAttackThisTurn extends TriggerCondition{
	private CardBase cardControlled;

	public TriggerConditionControllerDidntAttackThisTurn(CardBase card){
		this.cardControlled=card;
	}

	public TriggerCondition DeepCopy(){
		return new TriggerConditionControllerDidntAttackThisTurn(this.cardControlled);
	}

	public boolean MeetsCondition(ITargetable triggeringObject, Vector<Player> players){
		return triggeringObject==this.cardControlled&&!players.get(this.cardControlled.GetControllerIndex()).AttackedThisTurn();
	}
	
	public String GetRulesText(){
		StringBuilder rules=new StringBuilder();
		rules.append("you did not attack this turn and "+this.cardControlled.GetName());
		return rules.toString();
	}
}
