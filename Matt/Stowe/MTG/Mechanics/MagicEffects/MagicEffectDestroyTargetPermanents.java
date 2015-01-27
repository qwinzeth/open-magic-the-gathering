package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectDestroyTargetPermanents extends MagicEffect{
	public MagicEffectDestroyTargetPermanents(Player controller, TargetInfo targetInfo){
		super(controller, targetInfo, Duration.NA);
	}

	private MagicEffectDestroyTargetPermanents(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public static MagicEffectDestroyTargetPermanents Create(Player controller, int minTargets, int maxTargets, int targetTypeFlags){
		return new MagicEffectDestroyTargetPermanents(controller, new TargetInfo(minTargets, maxTargets, targetTypeFlags));
	}

	public static MagicEffectDestroyTargetPermanents CreateAutoset(Player controller, TargetInfo autosetTargetInfo){
		return new MagicEffectDestroyTargetPermanents(controller, new TargetInfo(autosetTargetInfo));
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			for(int p=0;p<players.size();p++){
				players.elementAt(p).DestroyPermanent((CardBase)this.TargetData.GetTarget(i), players);
			}
		}
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectDestroyTargetPermanents(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return "Destroy "+this.TargetData.GetCSV()+".";
	}
}
