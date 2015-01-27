package Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class MagicEffectGrantIndestructableToTargetPermanent extends MagicEffect{
	private MagicEffectGrantIndestructableToTargetPermanent(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectGrantIndestructableToTargetPermanent(Player controller, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
	}
	
	public static MagicEffectGrantIndestructableToTargetPermanent Create(Player controller, int minTargets, int maxTargets, int targetTypeFlags, Duration duration){
		return new MagicEffectGrantIndestructableToTargetPermanent(controller, new TargetInfo(minTargets, maxTargets, targetTypeFlags), duration);
	}

	public static MagicEffectGrantIndestructableToTargetPermanent CreateWithInvalidTargets(Player controller, int minTargets, int maxTargets, int targetTypeFlags, Duration duration, TargetInfo[] invalidTargets){
		return new MagicEffectGrantIndestructableToTargetPermanent(controller, new TargetInfo(minTargets, maxTargets, targetTypeFlags, invalidTargets), duration);
	}

	public static MagicEffectGrantIndestructableToTargetPermanent CreateAutoset(Player controller, TargetInfo autosetTargetInfo, Duration duration){
		return new MagicEffectGrantIndestructableToTargetPermanent(controller, new TargetInfo(autosetTargetInfo), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=(CardBase)this.TargetData.GetTarget(i);
			ccard.GetKeywords().AddIndestructable();
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectGrantIndestructableToTargetPermanent(this.GetDeepCopyInfo());
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=(CardBase)this.TargetData.GetTarget(i);
			ccard.GetKeywords().RemoveIndestructable();
		}
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" has indestructable "+this.getDurationString()+".";
	}
}
