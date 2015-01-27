package Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class MagicEffectGrantReachToTargetCreature extends MagicEffect{
	private MagicEffectGrantReachToTargetCreature(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectGrantReachToTargetCreature(Player controller, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.GetKeywords().AddReach();
		}

		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectGrantReachToTargetCreature(this.GetDeepCopyInfo());
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.GetKeywords().RemoveReach();
		}
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" has reach "+this.getDurationString()+".";
	}
}
