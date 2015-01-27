package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectDealDamageToTarget extends MagicEffect{
	private boolean needsX;
	private boolean setX;
	private int damageAmount;
	
	public MagicEffectDealDamageToTarget(Player controller, CardBase source, int damageAmount, TargetInfo targetInfo, boolean needsX){
		super(controller, targetInfo, Duration.NA);
		this.damageAmount=damageAmount;
		this.source=source;
		this.needsX=needsX;
		this.setX=false;
	}
	
	private MagicEffectDealDamageToTarget(DeepCopyInfo dcinfo, int damageAmount, boolean needsX, boolean setX){
		super(dcinfo);
		this.damageAmount=damageAmount;
		this.needsX=needsX;
		this.setX=setX;
	}

	public static MagicEffectDealDamageToTarget Create(Player controller, CardBase source, int damageAmount, int minTargets, int maxTargets, int targetTypeFlags){
		return new MagicEffectDealDamageToTarget(controller, source, damageAmount, new TargetInfo(minTargets, maxTargets, targetTypeFlags), false);
	}
	
	public static MagicEffectDealDamageToTarget CreateXDamage(Player controller, CardBase source, int minTargets, int maxTargets, int targetTypeFlags){
		return new MagicEffectDealDamageToTarget(controller, source, 0, new TargetInfo(minTargets, maxTargets, targetTypeFlags), true);
	}
	
	public static MagicEffectDealDamageToTarget CreateAutoset(Player controller, CardBase source, int damageAmount, TargetInfo targetInfoAutoset){
		return new MagicEffectDealDamageToTarget(controller, source, damageAmount, new TargetInfo(targetInfoAutoset), false);
	}

	public static MagicEffectDealDamageToTarget CreateWithInvalidTargets(Player controller, CardBase source, int damageAmount, int minTargets, int maxTargets, int targetTypeFlags, TargetInfo[] invalidTargets){
		return new MagicEffectDealDamageToTarget(controller, source, damageAmount, new TargetInfo(minTargets, maxTargets, targetTypeFlags, invalidTargets), false);
	}

	public void SetX(int x){
		if(this.needsX){
			this.setX=true;
			this.damageAmount=x;
		}
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			ITargetable ctarget=this.TargetData.GetTarget(i);
			int damage=ctarget.MarkDamage(this.damageAmount, DamagePreventor.DAMAGE_TYPE_FLAG_NONCOMBAT, this.source);
			for(int p=0;p<players.size();p++){
				triggeredEffects.addAll(players.elementAt(p).TriggerTookDamage(ctarget, damage, DamagePreventor.DAMAGE_TYPE_FLAG_NONCOMBAT, this.source));
			}
		}
		this.setX=false;
		return triggeredEffects;
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectDealDamageToTarget(this.GetDeepCopyInfo(), this.damageAmount, this.needsX, this.setX);
	}

	public String GetRulesText(){
		return "Deal "+(this.needsX&&!this.setX?"X":this.damageAmount)+" damage to "+this.TargetData.GetCSV()+".";
	}
}
