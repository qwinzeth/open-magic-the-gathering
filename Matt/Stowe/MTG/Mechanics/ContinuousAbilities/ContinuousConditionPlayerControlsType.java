package Matt.Stowe.MTG.Mechanics.ContinuousAbilities;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public class ContinuousConditionPlayerControlsType implements IContinuousCondition{
	private TargetInfo cardRequirements;
	
	public ContinuousConditionPlayerControlsType(TargetInfo cardRequirements){
		this.cardRequirements=cardRequirements;
	}

	public IContinuousCondition DeepCopy(){
		return new ContinuousConditionPlayerControlsType(this.cardRequirements.DeepCopy());
	}

	public boolean MeetsCondition(Vector<Player> players, int controllerIndex){
		Vector<CardBase> field=players.elementAt(controllerIndex).GetField();
		for(int i=0;i<field.size();i++){
			if(this.cardRequirements.IsValidTarget(field.elementAt(i)))
				return true;
		}
		return false;
	}
	
	public String GetRulesText(){
		return "you control "+this.cardRequirements.GetCSV().replace("1 target", "a");
	}
}
