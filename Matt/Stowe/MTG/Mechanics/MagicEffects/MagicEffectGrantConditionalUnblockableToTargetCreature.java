package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectGrantConditionalUnblockableToTargetCreature extends MagicEffect{
	private TargetInfo cantBeBlockedByInfo;

	private MagicEffectGrantConditionalUnblockableToTargetCreature(DeepCopyInfo dcinfo, TargetInfo cantBeBlockedByInfo){
		super(dcinfo);
		this.cantBeBlockedByInfo=cantBeBlockedByInfo;
	}

	public MagicEffectGrantConditionalUnblockableToTargetCreature(Player controller, TargetInfo targetInfo, TargetInfo cantBeBlockedByInfo, Duration duration){
		super(controller, targetInfo, duration);
		this.cantBeBlockedByInfo=cantBeBlockedByInfo;
	}
	
	public static MagicEffectGrantConditionalUnblockableToTargetCreature Create(Player controller, int minTargets, int maxTargets, TargetInfo cantBeBlockedByInfo, Duration duration){
		return new MagicEffectGrantConditionalUnblockableToTargetCreature(controller, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE), cantBeBlockedByInfo, duration);
	}

	public static MagicEffectGrantConditionalUnblockableToTargetCreature CreateAutoset(Player controller, TargetInfo targetInfo, TargetInfo cantBeBlockedByInfo, Duration duration){
		return new MagicEffectGrantConditionalUnblockableToTargetCreature(controller, new TargetInfo(targetInfo), cantBeBlockedByInfo, duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.AddCantBeBlockedBy(this.cantBeBlockedByInfo);
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectGrantConditionalUnblockableToTargetCreature(this.GetDeepCopyInfo(), this.cantBeBlockedByInfo.DeepCopy());
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.RemoveCantBeBlockedBy(this.cantBeBlockedByInfo);
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" can't be blocked by "+this.cantBeBlockedByInfo.GetCSV().replace("1 target","any")+" "+this.getDurationString()+".";
	}
}
