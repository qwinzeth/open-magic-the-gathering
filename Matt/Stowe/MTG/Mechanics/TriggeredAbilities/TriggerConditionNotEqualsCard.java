package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.Player;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public class TriggerConditionNotEqualsCard extends TriggerCondition{
	private CardBase trigger;

	public TriggerConditionNotEqualsCard(CardBase trigger){
		this.trigger=trigger;
	}

	public TriggerCondition DeepCopy(){
		return new TriggerConditionNotEqualsCard(this.trigger);
	}

	public boolean MeetsCondition(ITargetable triggeringObject, Vector<Player> players){
		return triggeringObject!=this.trigger;
	}
	
	public String GetRulesText(){
		return "another";
	}
}
