package Matt.Stowe.MTG.Mechanics.ContinuousAbilities;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Cards.*;

import java.util.*;

public class ContinuousConditionPlayerOwnsCardInExile implements IContinuousCondition{
	TargetInfo exileCardInfo;

	public ContinuousConditionPlayerOwnsCardInExile(TargetInfo exileCardInfo){
		this.exileCardInfo=exileCardInfo;
	}

	public IContinuousCondition DeepCopy(){
		return new ContinuousConditionPlayerOwnsCardInExile(this.exileCardInfo.DeepCopy());
	}

	public boolean MeetsCondition(Vector<Player> players, int controllerIndex){
		for(int pi=0;pi<players.size();pi++){
			ArrayList<CardBase> cexiledcards=players.elementAt(pi).GetExile();
			for(int ei=0;ei<cexiledcards.size();ei++){
				if(this.exileCardInfo.IsValidTarget(cexiledcards.get(ei)))
					return true;
			}
		}
		
		return false;
	}
	
	public String GetRulesText(){
		return this.exileCardInfo.GetCSV().replace("1 target","any")+" is in exile.";
	}
}
