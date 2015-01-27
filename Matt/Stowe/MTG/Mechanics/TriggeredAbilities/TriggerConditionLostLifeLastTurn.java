package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public class TriggerConditionLostLifeLastTurn extends TriggerCondition{
	Player player;

	public TriggerConditionLostLifeLastTurn(Player player){
		this.player=player;
	}

	public TriggerCondition DeepCopy(){
		return new TriggerConditionLostLifeLastTurn(this.player);
	}

	public boolean MeetsCondition(ITargetable triggeringObject, Vector<Player> players){
		return this.player.LostLifeLastTurn();
	}
	
	public String GetRulesText(){
		StringBuilder rules=new StringBuilder();
		if(this.player==null)
			rules.append("You");
		else
			rules.append(this.player.GetName());
		rules.append(" lost life last turn");
		return rules.toString();
	}
}
