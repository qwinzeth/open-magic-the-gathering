package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectGrantConditionalShroudToTargetCreature extends MagicEffect{
	private TargetInfo cantBeTargetedByInfo;

	private MagicEffectGrantConditionalShroudToTargetCreature(DeepCopyInfo dcinfo, TargetInfo cantBeTargetedByInfo){
		super(dcinfo);
		this.cantBeTargetedByInfo=cantBeTargetedByInfo;
	}

	public MagicEffectGrantConditionalShroudToTargetCreature(Player controller, TargetInfo targetInfo, TargetInfo cantBeTargetedByInfo, Duration duration){
		super(controller, targetInfo, duration);
		this.cantBeTargetedByInfo=cantBeTargetedByInfo;
	}
	
	public static MagicEffectGrantConditionalShroudToTargetCreature Create(Player controller, int minTargets, int maxTargets, TargetInfo cantBeTargetedByInfo, Duration duration){
		return new MagicEffectGrantConditionalShroudToTargetCreature(controller, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE), cantBeTargetedByInfo, duration);
	}

	public static MagicEffectGrantConditionalShroudToTargetCreature CreateAutoset(Player controller, TargetInfo targetInfo, TargetInfo cantBeTargetedByInfo, Duration duration){
		return new MagicEffectGrantConditionalShroudToTargetCreature(controller, new TargetInfo(targetInfo), cantBeTargetedByInfo, duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			ITargetable ctarget=this.TargetData.GetTarget(i);
			ctarget.AddCantBeTargetedBy(this.cantBeTargetedByInfo);
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectGrantConditionalShroudToTargetCreature(this.GetDeepCopyInfo(), this.cantBeTargetedByInfo.DeepCopy());
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			ITargetable ctarget=this.TargetData.GetTarget(i);
			ctarget.RemoveCantBeTargetedBy(this.cantBeTargetedByInfo);
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" can't be targeted by "+this.cantBeTargetedByInfo.GetCSV().replace("1 target","any")+" "+this.getDurationString()+".";
	}
}
