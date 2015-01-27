package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Cards.*;

import java.util.*;

public class MagicEffectTapTargetCard extends MagicEffect{
	private MagicEffectTapTargetCard(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectTapTargetCard(Player controller, TargetInfo targetInfo){
		super(controller, targetInfo, Duration.NA);
	}
	
	public static MagicEffectTapTargetCard Create(Player controller, int minTargets, int maxTargets, int targetTypeFlags){
		return new MagicEffectTapTargetCard(controller, new TargetInfo(minTargets, maxTargets, targetTypeFlags));
	}
	
	public static MagicEffectTapTargetCard CreateAutoset(Player controller, TargetInfo autosetTargetInfo){
		return new MagicEffectTapTargetCard(controller, new TargetInfo(autosetTargetInfo));
	}

	public boolean CanPayAsCost(){
		boolean canpay=true;
		for(int tapIndex=0;canpay&&this.TargetData.GetTarget(tapIndex)!=null;tapIndex++){
			CardBase ccard=((CardBase)this.TargetData.GetTarget(tapIndex));
			canpay=!ccard.IsTapped()&&!ccard.GetKeywords().HasSummoningSickness();
		}
		return canpay;
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			((CardBase)this.TargetData.GetTarget(i)).Tap();
		}
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectTapTargetCard(this.GetDeepCopyInfo());
	}
	
	public String GetRulesText(){
		return "Tap "+this.TargetData.GetCSV()+".";
	}
}