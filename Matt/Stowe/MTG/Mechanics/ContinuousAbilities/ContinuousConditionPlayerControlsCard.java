package Matt.Stowe.MTG.Mechanics.ContinuousAbilities;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;

import java.util.Vector;

public class ContinuousConditionPlayerControlsCard implements IContinuousCondition{
	private String cardName;
	
	public ContinuousConditionPlayerControlsCard(String cardname){
		this.cardName=cardname;
	}

	public IContinuousCondition DeepCopy(){
		return new ContinuousConditionPlayerControlsCard(this.cardName);
	}

	public boolean MeetsCondition(Vector<Player> players, int controllerIndex){
		Vector<CardBase> field=players.elementAt(controllerIndex).GetField();
		for(int i=0;i<field.size();i++){
			if(field.elementAt(i).GetName().equals(this.cardName))
				return true;
		}
		return false;
	}
	
	public String GetRulesText(){
		return "you control "+this.cardName;
	}
}
