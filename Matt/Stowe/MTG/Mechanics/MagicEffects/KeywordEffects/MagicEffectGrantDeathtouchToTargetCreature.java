package Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class MagicEffectGrantDeathtouchToTargetCreature extends MagicEffect{
	private MagicEffectGrantDeathtouchToTargetCreature(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectGrantDeathtouchToTargetCreature(Player controller, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
	}
	
	public static MagicEffectGrantDeathtouchToTargetCreature Create(Player controller, int minTargets, int maxTargets, Duration duration){
		return new MagicEffectGrantDeathtouchToTargetCreature(controller, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE), duration);
	}

	public static MagicEffectGrantDeathtouchToTargetCreature CreateAutoset(Player controller, TargetInfo autosetTargetInfo, Duration duration){
		return new MagicEffectGrantDeathtouchToTargetCreature(controller, new TargetInfo(autosetTargetInfo), duration);
	}

	public static MagicEffectGrantDeathtouchToTargetCreature CreateWithInvalidTargets(Player controller, int minTargets, int maxTargets, Duration duration, TargetInfo[] invalidTargets){
		return new MagicEffectGrantDeathtouchToTargetCreature(controller, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE, invalidTargets), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=(Creature)this.TargetData.GetTarget(i);
			ccreature.GetKeywords().AddDeathtouch();
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectGrantDeathtouchToTargetCreature(this.GetDeepCopyInfo());
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=(Creature)this.TargetData.GetTarget(i);
			ccreature.GetKeywords().RemoveDeathtouch();
		}
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" has deathtouch "+this.getDurationString()+".";
	}
}
