package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectEquip extends MagicEffect{
	private Artifact equipment;
	
	private MagicEffectEquip(DeepCopyInfo dcinfo, Artifact equipment){
		super(dcinfo);
		this.equipment=equipment;
	}
	
	public MagicEffectEquip(Player controller, Artifact equipment){
		super(controller, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), Duration.NA);
		this.equipment=equipment;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		this.controller.EquipCreature(this.equipment, (Creature)this.TargetData.GetTarget(0), players);
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectEquip(this.GetDeepCopyInfo(), this.equipment);
	}

	public String GetRulesText(){
		return "Equip "+this.equipment.GetName()+" to target creature.";
	}
}