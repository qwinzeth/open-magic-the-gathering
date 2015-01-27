package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;

import java.util.*;

public class MagicEffectGrantTriggerEntersTheBattlefieldToTargetPermanent extends MagicEffect{
	private TriggeredAbility[] triggeredAbility;
	private MagicEffect[] triggeredEffects;

	private MagicEffectGrantTriggerEntersTheBattlefieldToTargetPermanent(DeepCopyInfo dcinfo, MagicEffect[] triggeredEffects){
		super(dcinfo);
		this.triggeredEffects=triggeredEffects;
	}
	
	private MagicEffectGrantTriggerEntersTheBattlefieldToTargetPermanent(Player controller, MagicEffect[] triggeredEffects, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
		this.triggeredEffects=triggeredEffects;
	}

	public static MagicEffectGrantTriggerEntersTheBattlefieldToTargetPermanent Create(Player controller, MagicEffect[] triggeredEffects, int minTargets, int maxTargets, int targetTypeFlags, Duration duration){
		return new MagicEffectGrantTriggerEntersTheBattlefieldToTargetPermanent(controller, triggeredEffects, new TargetInfo(minTargets, maxTargets, targetTypeFlags), duration);
	}

	public static MagicEffectGrantTriggerEntersTheBattlefieldToTargetPermanent CreateAutoset(Player controller, MagicEffect[] triggeredEffects, TargetInfo targetInfo, Duration duration){
		return new MagicEffectGrantTriggerEntersTheBattlefieldToTargetPermanent(controller, triggeredEffects, new TargetInfo(targetInfo), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		if(this.triggeredAbility!=null){
			System.out.println("LOST OLD ABILITIES: "+this.triggeredAbility.length);
		}
		ArrayList<TriggeredAbility> triggeredAbilities=new ArrayList<TriggeredAbility>();
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=(CardBase)this.TargetData.GetTarget(i);
			MagicEffect[] triggeredEffectsDeepCopy=new MagicEffect[this.triggeredEffects.length];
			for(int tedpi=0;tedpi<this.triggeredEffects.length;tedpi++){
				triggeredEffectsDeepCopy[tedpi]=this.triggeredEffects[tedpi].DeepCopy();
				triggeredEffectsDeepCopy[tedpi].SetTargetToCallUndo(ccard);
			}
			TriggeredAbility ctriggeredAbility=new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(ccard)}, triggeredEffectsDeepCopy);
			triggeredAbilities.add(ctriggeredAbility);
			ccard.AddEntersTheBattlefieldTrigger(ctriggeredAbility);
		}
		
		super.handleDuration();
		this.triggeredAbility=triggeredAbilities.toArray(new TriggeredAbility[0]);
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffectGrantTriggerEntersTheBattlefieldToTargetPermanent DeepCopy(){
		MagicEffect[] triggeredEffectsDeepCopy=null;
		if(this.triggeredEffects!=null){
			triggeredEffectsDeepCopy=new MagicEffect[this.triggeredEffects.length];
			for(int i=0;i<this.triggeredEffects.length;i++){
				triggeredEffectsDeepCopy[i]=triggeredEffects[i].DeepCopy();
			}
		}

		return new MagicEffectGrantTriggerEntersTheBattlefieldToTargetPermanent(this.GetDeepCopyInfo(), triggeredEffectsDeepCopy);
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;i<this.triggeredAbility.length;i++){
			CardBase ccard=(CardBase)this.TargetData.GetTarget(i);
			ccard.RemoveEntersTheBattlefieldTrigger(this.triggeredAbility[i]);
		}
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		StringBuilder triggeredAbilityRules=new StringBuilder();
		if(this.triggeredAbility!=null){
			for(int i=0;i<this.triggeredAbility.length;i++){
				triggeredAbilityRules.append(this.triggeredAbility[i].GetRulesText("enters the battlefield"));
			}
		}else{
			triggeredAbilityRules.append("When this card enters the battlefield, ");
			for(int e=0;e<this.triggeredEffects.length;e++){
				if(this.triggeredEffects[e].IsOptional())
					triggeredAbilityRules.append("optional: ");
				triggeredAbilityRules.append(this.triggeredEffects[e].GetRulesText()+" ");
			}
		}
		return this.TargetData.GetCSV()+" has "+triggeredAbilityRules;
	}
}
