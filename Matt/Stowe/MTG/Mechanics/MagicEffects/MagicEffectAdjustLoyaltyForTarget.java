package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectAdjustLoyaltyForTarget extends MagicEffect{
	private int amount;

	private MagicEffectAdjustLoyaltyForTarget(DeepCopyInfo dcinfo, int amount){
		super(dcinfo);
		this.amount=amount;
	}

	public MagicEffectAdjustLoyaltyForTarget(Player controller, TargetInfo targetdata, int amount){
		super(controller, targetdata, Duration.NA);
		this.amount=amount;
	}
	
	public boolean CanPayAsCost(){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			if(!((Planeswalker)this.TargetData.GetTarget(i)).CanAdjust(this.amount)){
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			((Planeswalker)this.TargetData.GetTarget(i)).AdjustLoyalty(this.amount);
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectAdjustLoyaltyForTarget(this.GetDeepCopyInfo(), this.amount);
	}

	public String GetRulesText(){
		return "Adjust loyalty for "+this.TargetData.GetCSV()+" by "+this.amount;
	}
}
