package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.ArrayList;

public class TriggeredAbilityDamaged{
	private MagicEffect[] effects;
	
	public TargetInfo RecipientTargetInfo;
	private int damageTypeORFlags;
	public void SetDamageTypeORFlags(int dflags){this.damageTypeORFlags=dflags;}
	
	private TriggeredAbilityDamaged(MagicEffect[] effects, TargetInfo RecipientTargetInfo, int damageTypeORFlags){
		this.effects=effects;
		this.RecipientTargetInfo=RecipientTargetInfo;
		this.damageTypeORFlags=damageTypeORFlags;
	}
	
	public TriggeredAbilityDamaged(MagicEffect[] effects){
		this.effects=effects;
		this.RecipientTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		this.damageTypeORFlags=DamagePreventor.DAMAGE_TYPE_FLAG_ANY;
	}
	
	public TriggeredAbilityDamaged DeepCopy(){	
		MagicEffect[] effectsDeepCopy=new MagicEffect[this.effects.length];
		for(int i=0;i<this.effects.length;i++){
			effectsDeepCopy[i]=this.effects[i].DeepCopy();
		}
		return new TriggeredAbilityDamaged(effectsDeepCopy, this.RecipientTargetInfo.DeepCopy(), this.damageTypeORFlags);
	}
	
	public void UpdateTargets(CardBase from, CardBase to){
		for(int i=0;i<this.effects.length;i++){
			this.effects[i].TargetData.UpdateTargets(from, to);
		}
	}
	
	public MagicEffect[] GetTriggeredEffects(ITargetable recipient, int damage, int damageTypeFlags, CardBase source){
		if(this.RecipientTargetInfo.IsLocked()&&recipient!=this.RecipientTargetInfo.GetTarget(0)){
			return null;
		}
		
		if(!this.RecipientTargetInfo.IsValidTarget(recipient)){
			return null;
		}
		
		if((this.damageTypeORFlags&damageTypeFlags)==0){
			return null;
		}
		
		ArrayList<MagicEffect> triggeredeffects=new ArrayList<MagicEffect>();
		for(int i=0;i<this.effects.length;i++){
			MagicEffect ceffectDeepCopy=this.effects[i].DeepCopy();
			if(ceffectDeepCopy.ShouldBePutOnStack()){
				ceffectDeepCopy.SetX(damage);
				triggeredeffects.add(ceffectDeepCopy);
			}
		}
		return triggeredeffects.toArray(new MagicEffect[0]);
	}
	
	public String GetRulesText(){
		StringBuilder allrules=new StringBuilder("When "+this.RecipientTargetInfo.GetCSV()+" takes "+DamagePreventor.GetDamageTypeString(this.damageTypeORFlags)+"damage, ");
			
		for(int e=0;e<this.effects.length;e++){
			if(this.effects[e].IsOptional()){
				allrules.append("optional: ");
			}
			allrules.append(this.effects[e].GetRulesText().replace("X","that much")+" ");
		}
		return allrules.toString();
	}
}
