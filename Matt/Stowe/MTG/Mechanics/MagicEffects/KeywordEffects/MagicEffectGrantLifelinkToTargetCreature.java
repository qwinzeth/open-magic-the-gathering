package Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class MagicEffectGrantLifelinkToTargetCreature extends MagicEffect{
	private MagicEffectGrantLifelinkToTargetCreature(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectGrantLifelinkToTargetCreature(Player controller, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
	}
	
	public static MagicEffectGrantLifelinkToTargetCreature Create(Player controller, int minTargets, int maxTargets, Duration duration){
		return new MagicEffectGrantLifelinkToTargetCreature(controller, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE), duration);
	}

	public static MagicEffectGrantLifelinkToTargetCreature CreateAutoset(Player controller, TargetInfo autosetTargetInfo, Duration duration){
		return new MagicEffectGrantLifelinkToTargetCreature(controller, new TargetInfo(autosetTargetInfo), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.GetKeywords().AddLifelink();
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectGrantLifelinkToTargetCreature(this.GetDeepCopyInfo());
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.GetKeywords().RemoveLifelink();
		}
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" has lifelink "+this.getDurationString()+".";
	}
}
