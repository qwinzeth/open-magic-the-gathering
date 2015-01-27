package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.Vector;

public class TriggerConditionOpponentLostLifeLastTurn extends TriggerCondition{
	Player player;

	public TriggerConditionOpponentLostLifeLastTurn(Player player){
		this.player=player;
	}

	public TriggerCondition DeepCopy(){
		return new TriggerConditionOpponentLostLifeLastTurn(this.player);
	}

	public boolean MeetsCondition(ITargetable triggeringObject, Vector<Player> players){
		for(int i=0;i<players.size();i++){
			Player cplayer=players.get(i);
			if(cplayer==this.player)
				continue;
			if(cplayer.LostLifeLastTurn()){
				return true;
			}
		}
		
		return false;
	}
	
	public String GetRulesText(){
		return "an opponent lost life last turn";
	}
}
