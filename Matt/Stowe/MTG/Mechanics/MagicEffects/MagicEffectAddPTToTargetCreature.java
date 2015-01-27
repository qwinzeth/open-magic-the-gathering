package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectAddPTToTargetCreature extends MagicEffect{
	private int powerDelta;
	private int toughnessDelta;
	
	public MagicEffectAddPTToTargetCreature(Player controller, int power, int toughness, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
		this.powerDelta=power;
		this.toughnessDelta=toughness;
	}
	
	private MagicEffectAddPTToTargetCreature(DeepCopyInfo dcinfo, int powerDelta, int toughnessDelta){
		super(dcinfo);
		this.powerDelta=powerDelta;
		this.toughnessDelta=toughnessDelta;
	}
	
	public static MagicEffectAddPTToTargetCreature Create(Player controller, int power, int toughness, int minTargets, int maxTargets, Duration duration){
		return new MagicEffectAddPTToTargetCreature(controller, power, toughness, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE), duration);
	}

	public static MagicEffectAddPTToTargetCreature CreateWithInvalidTargets(Player controller, int power, int toughness, int minTargets, int maxTargets, Duration duration, TargetInfo[] invalidTargets){
		return new MagicEffectAddPTToTargetCreature(controller, power, toughness, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE, invalidTargets), duration);
	}

	public static MagicEffectAddPTToTargetCreature CreateColorRestricted(Player controller, int power, int toughness, int minTargets, int maxTargets, int colorORFlags, Duration duration){
		return new MagicEffectAddPTToTargetCreature(controller, power, toughness, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE, colorORFlags), duration);
	}

	public static MagicEffectAddPTToTargetCreature CreateAutoset(Player controller, int power, int toughness, TargetInfo autosetTargetInfo, Duration duration){
		return new MagicEffectAddPTToTargetCreature(controller, power, toughness, new TargetInfo(autosetTargetInfo), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.AddPowerToughness(this.powerDelta, this.toughnessDelta);
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectAddPTToTargetCreature(this.GetDeepCopyInfo(), this.powerDelta, this.toughnessDelta);
	}
	
	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.AddPowerToughness(-this.powerDelta, -this.toughnessDelta);
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.powerDelta+"/"+this.toughnessDelta+" to "+this.TargetData.GetCSV()+" "+this.getDurationString()+".";
	}
}
