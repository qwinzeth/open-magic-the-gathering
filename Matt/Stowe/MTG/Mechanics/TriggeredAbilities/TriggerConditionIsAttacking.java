package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.Player;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public class TriggerConditionIsAttacking extends TriggerCondition{
	private Creature trigger;

	public TriggerConditionIsAttacking(Creature trigger){
		this.trigger=trigger;
	}

	public TriggerCondition DeepCopy(){
		return new TriggerConditionIsAttacking(this.trigger);
	}

	public boolean MeetsCondition(ITargetable triggeringObject, Vector<Player> players){
		return triggeringObject==this.trigger&&this.trigger.isAttacking();
	}
	
	public String GetRulesText(){
		return this.trigger.GetName();
	}
}
