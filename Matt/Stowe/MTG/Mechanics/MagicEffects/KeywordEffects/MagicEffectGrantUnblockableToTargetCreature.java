package Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class MagicEffectGrantUnblockableToTargetCreature extends MagicEffect{
	private MagicEffectGrantUnblockableToTargetCreature(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	private MagicEffectGrantUnblockableToTargetCreature(Player controller, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
	}
	
	public static MagicEffectGrantUnblockableToTargetCreature Create(Player controller, int minTargets, int maxTargets, Duration duration){
		return new MagicEffectGrantUnblockableToTargetCreature(controller, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE), duration);
	}

	public static MagicEffectGrantUnblockableToTargetCreature CreateAutoset(Player controller, TargetInfo targetInfo, Duration duration){
		return new MagicEffectGrantUnblockableToTargetCreature(controller, new TargetInfo(targetInfo), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.GetKeywords().AddUnblockable();
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectGrantUnblockableToTargetCreature(this.GetDeepCopyInfo());
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.GetKeywords().RemoveUnblockable();
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" has unblockable "+this.getDurationString()+".";
	}
}
