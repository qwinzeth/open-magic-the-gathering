package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.Player;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public class TriggerConditionEqualsTargetable extends TriggerCondition{
	ITargetable trigger;

	public TriggerConditionEqualsTargetable(ITargetable trigger){
		this.trigger=trigger;
	}

	public TriggerCondition DeepCopy(){
		return new TriggerConditionEqualsTargetable(this.trigger);
	}

	public boolean MeetsCondition(ITargetable triggeringObject, Vector<Player> players){
		return triggeringObject==this.trigger;
	}
	
	public String GetRulesText(){
		if(this.trigger==null)
			return "You";
		return this.trigger.GetName();
	}
}
