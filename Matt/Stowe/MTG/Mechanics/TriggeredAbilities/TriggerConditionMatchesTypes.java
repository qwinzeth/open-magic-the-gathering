package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.Player;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public class TriggerConditionMatchesTypes extends TriggerCondition{
	public TargetInfo TargetData;

	public TriggerConditionMatchesTypes(TargetInfo targetData){
		this.TargetData=targetData;
	}

	public TriggerConditionMatchesTypes(int targetTypeFlags){
		this.TargetData=new TargetInfo(1, 1, targetTypeFlags);
	}

	public TriggerCondition DeepCopy(){
		return new TriggerConditionMatchesTypes(this.TargetData.DeepCopy());
	}

	public boolean MeetsCondition(ITargetable triggeringObject, Vector<Player> players){
		return this.TargetData.IsValidTarget(triggeringObject);
	}
	
	public String GetRulesText(){
//if(this.TargetData==null)System.exit(0);
		return this.TargetData.GetCSV().replace("1 target","");
	}
}
