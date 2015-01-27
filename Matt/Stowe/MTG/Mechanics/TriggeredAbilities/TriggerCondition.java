package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.Player;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public abstract class TriggerCondition{
	protected TriggerCondition(){}

	public abstract boolean MeetsCondition(ITargetable triggeringObject, Vector<Player> players);
	
	public abstract TriggerCondition DeepCopy();
	
	public abstract String GetRulesText();
}
