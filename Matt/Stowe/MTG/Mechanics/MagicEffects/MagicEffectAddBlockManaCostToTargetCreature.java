package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectAddBlockManaCostToTargetCreature extends MagicEffect{
	private ManaCost manacost;
	
	private MagicEffectAddBlockManaCostToTargetCreature(Player controller, ManaCost manacost, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
		this.manacost=manacost;
	}
	
	private MagicEffectAddBlockManaCostToTargetCreature(DeepCopyInfo dcinfo, ManaCost manacost){
		super(dcinfo);
		this.manacost=manacost;
	}
	
	public static MagicEffectAddBlockManaCostToTargetCreature Create(Player controller, ManaCost manacost, int minTargets, int maxTargets, Duration duration){
		return new MagicEffectAddBlockManaCostToTargetCreature(controller, manacost, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE), duration);
	}

	public static MagicEffectAddBlockManaCostToTargetCreature CreateWithInvalidTargets(Player controller, ManaCost manacost, int minTargets, int maxTargets, Duration duration, TargetInfo[] invalidTargets){
		return new MagicEffectAddBlockManaCostToTargetCreature(controller, manacost, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE, invalidTargets), duration);
	}

	public static MagicEffectAddBlockManaCostToTargetCreature CreateColorRestricted(Player controller, ManaCost manacost, int minTargets, int maxTargets, int colorORFlags, Duration duration){
		return new MagicEffectAddBlockManaCostToTargetCreature(controller, manacost, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE, colorORFlags), duration);
	}

	public static MagicEffectAddBlockManaCostToTargetCreature CreateAutoset(Player controller, ManaCost manacost, TargetInfo autosetTargetInfo, Duration duration){
		return new MagicEffectAddBlockManaCostToTargetCreature(controller, manacost, new TargetInfo(autosetTargetInfo), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.AddBlockManaCost(this.manacost);
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectAddBlockManaCostToTargetCreature(this.GetDeepCopyInfo(), this.manacost);
	}
	
	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.RemoveBlockManaCost(this.manacost);
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" must pay "+this.manacost.toString()+" to block "+this.getDurationString()+".";
	}
}
