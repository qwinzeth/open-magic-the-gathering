package Matt.Stowe.MTG.Mechanics.TriggeredAbilities;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class TriggeredAbility{
	private String rulesText1, rulesText2;
	private TriggerCondition[] conditions;
	private MagicEffect[] effects;
	private Relationship[] relationships;
	public void SetRelationship(int conditionIndex, int effectIndex, ConditionEffectRelationship relationship){
		this.relationships[conditionIndex]=new Relationship(effectIndex, relationship);
		this.updateRulesText();
	}
	
	public enum ConditionEffectRelationship{
		DEFENDER_IS_TARGET, //Means "defending player"
		ACTOR_IS_TARGET
	}
	
	private class Relationship{
		public int EffectIndex;
		public ConditionEffectRelationship RelationshipType;
		
		public Relationship(int effectIndex, ConditionEffectRelationship type){
			this.EffectIndex=effectIndex;
			this.RelationshipType=type;
		}
	}
	
	private TriggeredAbility(TriggerCondition[] conditions, MagicEffect[] effects, Relationship[] relationships){
		this.conditions=conditions;
		this.effects=effects;
		this.relationships=relationships;
		this.updateRulesText();
	}
	
	public TriggeredAbility(TriggerCondition[] conditions, MagicEffect[] effects){
		this.conditions=conditions;
		this.effects=effects;
		this.relationships=new Relationship[this.conditions.length];
		this.updateRulesText();
	}

	public void SetSource(CardBase source){
		for(int i=0;i<this.effects.length;i++){
			this.effects[i].SetSource(source);
		}
	}
	
	public void UpdateTargets(CardBase from, CardBase to){
		for(int i=0;i<this.effects.length;i++){
			this.effects[i].TargetData.UpdateTargets(from, to);
			if(this.effects[i].GetSource()==from)
				this.effects[i].SetSource(to);
		}
	}

	public TriggeredAbility DeepCopy(){
		TriggerCondition[] conditionsDeepCopy=new TriggerCondition[this.conditions.length];
		for(int i=0;i<this.conditions.length;i++){
			conditionsDeepCopy[i]=this.conditions[i].DeepCopy();
		}
	
		MagicEffect[] effectsDeepCopy=new MagicEffect[this.effects.length];
		for(int i=0;i<this.effects.length;i++){
			effectsDeepCopy[i]=this.effects[i].DeepCopy();
		}
		
		Relationship[] relationshipsShallowCopy=new Relationship[this.relationships.length];
		for(int i=0;i<this.relationships.length;i++){
			relationshipsShallowCopy[i]=this.relationships[i];
		}
		
		return new TriggeredAbility(conditionsDeepCopy, effectsDeepCopy, relationshipsShallowCopy);
	}
	
	public MagicEffect[] GetTriggeredEffects(ITargetable triggeringObject, Vector<Player> players){
		if(this.conditions!=null){
			for(int i=0;i<this.conditions.length;i++){
				if(!this.conditions[i].MeetsCondition(triggeringObject, players)){
					return null;
				}
			}
		}
		
		for(int i=0;i<this.relationships.length;i++){
			if(this.relationships[i]==null)
				continue;
				
			switch(this.relationships[i].RelationshipType){
			case DEFENDER_IS_TARGET:
				this.effects[this.relationships[i].EffectIndex].TargetData.SetLockedTarget(0, ((Creature)triggeringObject).TargetedPlayer);
			break;
			case ACTOR_IS_TARGET:
				this.effects[this.relationships[i].EffectIndex].TargetData.SetLockedTarget(0, triggeringObject);
			break;
			default:
				System.out.println("TriggeredAbility: Unhandled relationship type: "+this.relationships[i].RelationshipType);
			break;
			}
		}
		
		ArrayList<MagicEffect> triggeredeffects=new ArrayList<MagicEffect>();
		for(int i=0;i<this.effects.length;i++){
			if(this.effects[i].ShouldBePutOnStack()){
				triggeredeffects.add(this.effects[i]);
			}
		}
		return triggeredeffects.toArray(new MagicEffect[0]);
	}
	
	public String GetRulesText(String action){
		return this.rulesText1+action+this.rulesText2;
	}
	private void updateRulesText(){
		StringBuilder allrules=new StringBuilder();
		if(this.conditions!=null){
			allrules.append("When ");
			for(int c=0;c<this.conditions.length;c++){
				allrules.append(this.conditions[c].GetRulesText()+" ");
			}
			this.rulesText1=allrules.toString();
			allrules.setLength(0);
			allrules.append(", ");
		}
		for(int e=0;e<this.effects.length;e++){
			if(this.effects[e].IsOptional()){
				allrules.append("optional: ");
			}
			String rulestext=this.effects[e].GetRulesText();
			if(this.conditions!=null){
				for(int c=0;c<this.conditions.length;c++){
					if(this.relationships[c]!=null&&this.relationships[c].EffectIndex==e){
						switch(this.relationships[c].RelationshipType){
						case DEFENDER_IS_TARGET:
							rulestext=rulestext.replace("1 target", "defending");
						break;
						case ACTOR_IS_TARGET:
							rulestext=rulestext.replace("1 target", "that");
						break;
						}
					}
				}
			}
			allrules.append(rulestext+" ");
		}
		this.rulesText2=allrules.toString();
	}
}
