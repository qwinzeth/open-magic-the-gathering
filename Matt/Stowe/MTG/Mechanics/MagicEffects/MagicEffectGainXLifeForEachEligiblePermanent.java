package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectGainXLifeForEachEligiblePermanent extends MagicEffect{
	private int lifeToGain;
	private TargetInfo eligibility;

	private MagicEffectGainXLifeForEachEligiblePermanent(DeepCopyInfo dcinfo, int lifeToGain, TargetInfo eligibility){
		super(dcinfo);
		this.lifeToGain=lifeToGain;
		this.eligibility=eligibility;
	}
	
	public MagicEffectGainXLifeForEachEligiblePermanent(Player controller, int lifeToGain, TargetInfo targetinfo, TargetInfo eligibility){
		super(controller, targetinfo, Duration.NA);
		this.lifeToGain=lifeToGain;
		this.eligibility=eligibility;
	}
	
	public static MagicEffectGainXLifeForEachEligiblePermanent Create(Player controller, int lifeToGain, int minTargets, int maxTargets, TargetInfo eligibility){
		return new MagicEffectGainXLifeForEachEligiblePermanent(controller, lifeToGain,
			new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_PLAYER), eligibility);
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		int lifeGained=0;
		
		for(int i=0;i<players.size();i++){
			Vector<CardBase> cfield=players.elementAt(i).GetField();
			for(int j=0;j<cfield.size();j++){
				if(this.eligibility.IsValidTarget(cfield.elementAt(j))){
					lifeGained+=this.lifeToGain;
				}
			}
		}
		
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			MagicEffectGainLife gainlifeEffect=new MagicEffectGainLife(((Player)this.TargetData.GetTarget(i)), lifeGained);
			triggeredEffects.addAll(gainlifeEffect.Resolve(players));
		}
		return triggeredEffects;
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectGainXLifeForEachEligiblePermanent(this.GetDeepCopyInfo(), this.lifeToGain, this.eligibility);
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" gains "+this.lifeToGain+" life "+this.eligibility.GetCSV().replace("1 target", "for each")+" on the battlefield.";
	}
}
