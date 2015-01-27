package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectExtraBlockToTargetCreature extends MagicEffect{
	private int extraBlocks;
	
	public MagicEffectExtraBlockToTargetCreature(Player controller, int extraBlocks, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
		this.extraBlocks=extraBlocks;
	}
	
	private MagicEffectExtraBlockToTargetCreature(DeepCopyInfo dcinfo, int extraBlocks){
		super(dcinfo);
		this.extraBlocks=extraBlocks;
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.AlterMaxBlocks(this.extraBlocks);
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectExtraBlockToTargetCreature(this.GetDeepCopyInfo(), this.extraBlocks);
	}
	
	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			ccreature.AlterMaxBlocks(-this.extraBlocks);
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" can block "+this.extraBlocks+" additional creature "+this.getDurationString()+".";
	}
}
