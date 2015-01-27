package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectMorphTargetCreature extends MagicEffect{
	private MagicEffectMorphTargetCreature(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectMorphTargetCreature(Player controller, Creature morphTarget){
		super(controller, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), Duration.NA);
		this.TargetData.SetLockedTarget(0, morphTarget);
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		Creature ccreature=((Creature)this.TargetData.GetTarget(0));
		ccreature.Morph();
		
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<players.size();i++){
			triggeredEffects.addAll(players.get(i).TriggerMorph(ccreature, players));
		}
		return triggeredEffects;
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectMorphTargetCreature(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return "Morph "+this.TargetData.GetCSV()+".";
	}
}