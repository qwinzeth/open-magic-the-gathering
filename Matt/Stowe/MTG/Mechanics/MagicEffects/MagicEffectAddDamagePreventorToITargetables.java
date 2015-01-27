package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectAddDamagePreventorToITargetables extends MagicEffect{
	private DamagePreventor dmgprev;
	private boolean chooseColor;
	
	public MagicEffectAddDamagePreventorToITargetables(Player controller, DamagePreventor damagePreventor, boolean chooseColor, TargetInfo targetinfo, Duration duration){
		super(controller, targetinfo, duration);
		this.dmgprev=damagePreventor;
		this.chooseColor=chooseColor;
	}
	
	private MagicEffectAddDamagePreventorToITargetables(DeepCopyInfo dcinfo, DamagePreventor dmgprev, boolean chooseColor){
		super(dcinfo);
		this.dmgprev=dmgprev;
		this.chooseColor=chooseColor;
	}
	
	public static MagicEffectAddDamagePreventorToITargetables Create(Player controller, DamagePreventor damagePreventor, int minTargets, int maxTargets, int targetTypeFlags, Duration duration){
		return new MagicEffectAddDamagePreventorToITargetables(controller, damagePreventor, false, new TargetInfo(minTargets, maxTargets, targetTypeFlags), duration);
	}
	
	public static MagicEffectAddDamagePreventorToITargetables CreateRequiresColorDecision(Player controller, DamagePreventor damagePreventor, int minTargets, int maxTargets, int targetTypeFlags, Duration duration){
		return new MagicEffectAddDamagePreventorToITargetables(controller, damagePreventor, true, new TargetInfo(minTargets, maxTargets, targetTypeFlags), duration);
	}

	public static MagicEffectAddDamagePreventorToITargetables CreateWithInvalidTargets(Player controller, DamagePreventor damagePreventor, int minTargets, int maxTargets, int targetTypeFlags, Duration duration, TargetInfo[] invalidTargets){
		return new MagicEffectAddDamagePreventorToITargetables(controller, damagePreventor, false, new TargetInfo(minTargets, maxTargets, targetTypeFlags, invalidTargets), duration);
	}
	
	public static MagicEffectAddDamagePreventorToITargetables CreateWithInvalidTargetsRequiresColorDecision(Player controller, DamagePreventor damagePreventor, int minTargets, int maxTargets, int targetTypeFlags, Duration duration, TargetInfo[] invalidTargets){
		return new MagicEffectAddDamagePreventorToITargetables(controller, damagePreventor, true, new TargetInfo(minTargets, maxTargets, targetTypeFlags, invalidTargets), duration);
	}

	public static MagicEffectAddDamagePreventorToITargetables CreateAutoset(Player controller, DamagePreventor damagePreventor, TargetInfo targetinfo, Duration duration){
		return new MagicEffectAddDamagePreventorToITargetables(controller, damagePreventor, false, new TargetInfo(targetinfo), duration);
	}

	public void MakeRequiredDecisions(){
		if(this.chooseColor){
			this.dmgprev.SetColorORFlags(this.controller.GetColorChoice(this, ManaCost.COLOR_ANY));
		}
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			ITargetable ctarget=this.TargetData.GetTarget(i);
			ctarget.AddDamagePreventor(this.dmgprev);
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectAddDamagePreventorToITargetables(this.GetDeepCopyInfo(), this.dmgprev.DeepCopy(), this.chooseColor);
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			this.TargetData.GetTarget(i).RemoveDamagePreventor(this.dmgprev);
		}
		
		return new ArrayList<MagicEffect[]>();
	}

	public String GetRulesText(){
		return this.TargetData.GetCSV()+" gets "+this.dmgprev.GetRulesText();
	}
}
