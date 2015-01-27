package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectDrawCardsForTargetPlayer extends MagicEffect{
	private int cardsToDrawCount;
	
	private MagicEffectDrawCardsForTargetPlayer(DeepCopyInfo dcinfo, int count){
		super(dcinfo);
		this.cardsToDrawCount=count;
	}

	public MagicEffectDrawCardsForTargetPlayer(Player controller, int count, TargetInfo targetInfo){
		super(controller, targetInfo, Duration.NA);
		this.cardsToDrawCount=count;
	}
	
	public static MagicEffectDrawCardsForTargetPlayer Create(Player controller, int count, int minTargets, int maxTargets){
		return new MagicEffectDrawCardsForTargetPlayer(controller, count, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_PLAYER));
	}
	
	public static MagicEffectDrawCardsForTargetPlayer CreateAutoset(Player controller, int count, TargetInfo targetInfo){
		return new MagicEffectDrawCardsForTargetPlayer(controller, count, new TargetInfo(targetInfo));
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			((Player)this.TargetData.GetTarget(i)).DrawXCards(this.cardsToDrawCount);
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectDrawCardsForTargetPlayer(this.GetDeepCopyInfo(), this.cardsToDrawCount);
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+": Draw "+this.cardsToDrawCount+" card"+(this.cardsToDrawCount>1?"s":"")+".";
	}
}