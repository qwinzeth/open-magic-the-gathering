package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;

import java.util.*;

public class MagicEffectGrantTriggerAttacksToTargetCreature extends MagicEffect{
	private TriggeredAbility[] triggeredAbility;
	private MagicEffect[] triggeredEffects;

	private MagicEffectGrantTriggerAttacksToTargetCreature(DeepCopyInfo dcinfo, MagicEffect[] triggeredEffects, TriggeredAbility[] triggeredAbility){
		super(dcinfo);
		this.triggeredEffects=triggeredEffects;
		this.triggeredAbility=triggeredAbility;
	}
	
	private MagicEffectGrantTriggerAttacksToTargetCreature(Player controller, MagicEffect[] triggeredEffects, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
		this.triggeredEffects=triggeredEffects;
	}

	public static MagicEffectGrantTriggerAttacksToTargetCreature Create(Player controller, MagicEffect[] triggeredEffects, int minTargets, int maxTargets, Duration duration){
		return new MagicEffectGrantTriggerAttacksToTargetCreature(controller, triggeredEffects, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE), duration);
	}

	public static MagicEffectGrantTriggerAttacksToTargetCreature CreateAutoset(Player controller, MagicEffect[] triggeredEffects, TargetInfo targetInfo, Duration duration){
		return new MagicEffectGrantTriggerAttacksToTargetCreature(controller, triggeredEffects, new TargetInfo(targetInfo), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<TriggeredAbility> triggeredAbilities=new ArrayList<TriggeredAbility>();
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=(Creature)this.TargetData.GetTarget(i);
			TriggeredAbility ctriggeredAbility=new TriggeredAbility(new TriggerCondition[]{new TriggerConditionIsAttacking(ccreature)}, this.triggeredEffects);
			triggeredAbilities.add(ctriggeredAbility);
			ccreature.AddAttacksTrigger(ctriggeredAbility);
		}
		
		super.handleDuration();
		this.triggeredAbility=triggeredAbilities.toArray(new TriggeredAbility[0]);
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffectGrantTriggerAttacksToTargetCreature DeepCopy(){
		MagicEffect[] triggeredEffectsDeepCopy=null;
		if(this.triggeredEffects!=null){
			triggeredEffectsDeepCopy=new MagicEffect[this.triggeredEffects.length];
			for(int i=0;i<this.triggeredEffects.length;i++){
				triggeredEffectsDeepCopy[i]=triggeredEffects[i].DeepCopy();
			}
		}
		
		TriggeredAbility[] triggeredAbilityDeepCopy=null;
		if(this.triggeredAbility!=null){
			System.out.println("Nonnull triggeredAbility in MagicEffectGrantTriggerAttacksToTargetCreature");
		}
		return new MagicEffectGrantTriggerAttacksToTargetCreature(this.GetDeepCopyInfo(), triggeredEffectsDeepCopy, triggeredAbilityDeepCopy);
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;i<this.triggeredAbility.length;i++){
			Creature ccreature=(Creature)this.TargetData.GetTarget(i);
			ccreature.RemoveAttacksTrigger(this.triggeredAbility[i]);
		}
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		StringBuilder triggeredAbilityRules=new StringBuilder();
		if(this.triggeredAbility!=null){
			for(int i=0;i<this.triggeredAbility.length;i++){
				triggeredAbilityRules.append(this.triggeredAbility[i].GetRulesText("attacks"));
			}
		}else{
			triggeredAbilityRules.append("When this creature attacks, ");
			for(int e=0;e<this.triggeredEffects.length;e++){
				triggeredAbilityRules.append(this.triggeredEffects[e].GetRulesText()+" ");
			}
		}
		return this.TargetData.GetCSV()+" has "+triggeredAbilityRules;
	}
}
