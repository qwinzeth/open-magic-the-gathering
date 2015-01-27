package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Cards.*;

import java.util.*;

public class MagicEffectTapAndSkipUntapTargetCard extends MagicEffect{
	private MagicEffectTapAndSkipUntapTargetCard(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectTapAndSkipUntapTargetCard(Player controller, TargetInfo targetInfo){
		super(controller, targetInfo, Duration.NA);
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
			CardBase ccard=((CardBase)this.TargetData.GetTarget(i));
			ccard.Tap();
			ccard.SkipNextUntapping();
		}
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectTapAndSkipUntapTargetCard(this.GetDeepCopyInfo());
	}
	
	public String GetRulesText(){
		return "Tap "+this.TargetData.GetCSV()+". It doesn't untap during its controller's next untap step.";
	}
}