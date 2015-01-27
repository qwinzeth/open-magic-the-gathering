package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.Player;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public class TriggerConditionNotMatchesTypes extends TriggerCondition{
	private TargetInfo TargetData;

	public TriggerConditionNotMatchesTypes(TargetInfo targetData){
		this.TargetData=targetData;
	}

	public TriggerConditionNotMatchesTypes(int targetTypeFlags){
		this.TargetData=new TargetInfo(1, 1, targetTypeFlags);
	}

	public TriggerCondition DeepCopy(){
		return new TriggerConditionNotMatchesTypes(this.TargetData.DeepCopy());
	}

	public boolean MeetsCondition(ITargetable triggeringObject, Vector<Player> players){
		return !this.TargetData.IsValidTarget(triggeringObject);
	}
	
	public String GetRulesText(){
		return "not "+this.TargetData.GetCSV().replace("1 target","");
	}
}
