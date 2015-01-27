package Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class MagicEffectGrantDoubleStrikeToTargetCreature extends MagicEffect{
	private MagicEffectGrantDoubleStrikeToTargetCreature(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectGrantDoubleStrikeToTargetCreature(Player controller, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
	}
	
	public static MagicEffectGrantDoubleStrikeToTargetCreature Create(Player controller, int minTargets, int maxTargets, Duration duration){
		return new MagicEffectGrantDoubleStrikeToTargetCreature(controller, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE), duration);
	}

	public static MagicEffectGrantDoubleStrikeToTargetCreature CreateAutoset(Player controller, TargetInfo autosetTargetInfo, Duration duration){
		return new MagicEffectGrantDoubleStrikeToTargetCreature(controller, new TargetInfo(autosetTargetInfo), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=(Creature)this.TargetData.GetTarget(i);
			ccreature.GetKeywords().AddDoubleStrike();
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectGrantDoubleStrikeToTargetCreature(this.GetDeepCopyInfo());
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=(Creature)this.TargetData.GetTarget(i);
			ccreature.GetKeywords().RemoveDoubleStrike();
		}
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" has double strike "+this.getDurationString()+".";
	}
}
