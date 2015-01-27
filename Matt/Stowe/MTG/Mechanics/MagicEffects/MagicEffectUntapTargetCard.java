package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Cards.*;

import java.util.*;

public class MagicEffectUntapTargetCard extends MagicEffect{
	private MagicEffectUntapTargetCard(DeepCopyInfo dcinfo){
		super(dcinfo);
	}
	
	public MagicEffectUntapTargetCard(Player controller, TargetInfo targetinfo){
		super(controller, targetinfo, Duration.NA);
	}
	
	public boolean CanPayAsCost(){
		boolean canpay=true;
		for(int tapIndex=0;canpay&&this.TargetData.GetTarget(tapIndex)!=null;tapIndex++){
			CardBase ccard=((CardBase)this.TargetData.GetTarget(tapIndex));
			canpay=ccard.IsTapped();//&&!ccard.GetKeywords().HasSummoningSickness(); Not sure about this one
		}
		return canpay;
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			((CardBase)this.TargetData.GetTarget(i)).Untap();
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectUntapTargetCard(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return "Untap "+this.TargetData.GetCSV()+".";
	}
}