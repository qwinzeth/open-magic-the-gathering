package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectAddPTCountersToTargetCreature extends MagicEffect{
	private int countersToAdd;
	
	public MagicEffectAddPTCountersToTargetCreature(Player controller, int amount, TargetInfo targetInfo){
		super(controller, targetInfo, Duration.NA);
		this.countersToAdd=amount;
	}

	private MagicEffectAddPTCountersToTargetCreature(DeepCopyInfo dcinfo, int amount){
		super(dcinfo);
		this.countersToAdd=amount;
	}

	public static MagicEffectAddPTCountersToTargetCreature Create(Player controller, int amount, int minTargets, int maxTargets){
		return new MagicEffectAddPTCountersToTargetCreature(controller, amount, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE));
	}

	public static MagicEffectAddPTCountersToTargetCreature CreateWithInvalidTargets(Player controller, int amount, int minTargets, int maxTargets, TargetInfo[] invalidTargets){
		return new MagicEffectAddPTCountersToTargetCreature(controller, amount, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE, invalidTargets));
	}

	public static MagicEffectAddPTCountersToTargetCreature CreateAutoset(Player controller, int amount, TargetInfo autosetTargetInfo){
		return new MagicEffectAddPTCountersToTargetCreature(controller, amount, new TargetInfo(autosetTargetInfo));
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			((Creature)this.TargetData.GetTarget(i)).AddPowerToughnessCounters(this.countersToAdd);
		}
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectAddPTCountersToTargetCreature(this.GetDeepCopyInfo(), this.countersToAdd);
	}

	public String GetRulesText(){
		return "Add "+countersToAdd+" 1/1 counters to "+this.TargetData.GetCSV()+".";
	}
}