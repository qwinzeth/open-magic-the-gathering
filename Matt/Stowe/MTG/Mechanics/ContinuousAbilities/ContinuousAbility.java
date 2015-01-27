package Matt.Stowe.MTG.Mechanics.ContinuousAbilities;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class ContinuousAbility{
	private IContinuousCondition[] conditions;
	private MagicEffect[] effects;
	private ArrayList<MagicEffect[]> currentlyActiveEffects;
	
	private ContinuousAbility(IContinuousCondition[] conditions, MagicEffect[] effects, ArrayList<MagicEffect[]> currentlyActiveEffects){
		this.conditions=conditions;
		this.effects=effects;
		this.currentlyActiveEffects=currentlyActiveEffects;
	}

	public ContinuousAbility(IContinuousCondition[] conditions, MagicEffect[] effects){
		this.conditions=conditions;
		this.effects=effects;
		this.currentlyActiveEffects=new ArrayList<MagicEffect[]>();
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

	public ContinuousAbility DeepCopy(){
		IContinuousCondition[] conditionsDeepCopy=null;
		if(this.conditions!=null){
			conditionsDeepCopy=new IContinuousCondition[this.conditions.length];
			for(int i=0;i<this.conditions.length;i++){
				conditionsDeepCopy[i]=this.conditions[i].DeepCopy();
			}
		}
		
		MagicEffect[] effectsDeepCopy=new MagicEffect[this.effects.length];
		for(int i=0;i<this.effects.length;i++){
			effectsDeepCopy[i]=this.effects[i].DeepCopy();
		}

		ArrayList<MagicEffect[]> currentlyActiveEffectsDeepCopy=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.currentlyActiveEffects.size();i++){
			MagicEffect[] caeffects=this.currentlyActiveEffects.get(i);
			MagicEffect[] caeffectsDeepCopy=new MagicEffect[caeffects.length];
			for(int j=0;j<caeffects.length;j++){
				caeffectsDeepCopy[j]=caeffects[j].DeepCopy();
			}
			currentlyActiveEffectsDeepCopy.add(caeffectsDeepCopy);
		}
		
		return new ContinuousAbility(conditionsDeepCopy, effectsDeepCopy, currentlyActiveEffectsDeepCopy);
	}
	
	public ArrayList<MagicEffect[]> Reset(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int caei=0;caei<this.currentlyActiveEffects.size();caei++){
			MagicEffect[] cactiveeffects=this.currentlyActiveEffects.get(caei);
			for(int ei=0;ei<cactiveeffects.length;ei++){
				triggeredEffects.addAll(cactiveeffects[ei].Undo(players));
			}
		}
		this.currentlyActiveEffects.clear();		
		return triggeredEffects;
	}
	
	public void CheckConditions(Vector<Player> players, int controllerIndex){
		if(this.conditions!=null){
			for(int i=0;i<this.conditions.length;i++){
				if(!this.conditions[i].MeetsCondition(players, controllerIndex)){
					this.Reset(players);
					return;
				}
			}
		}

		this.Reset(players);
		for(int pi=0;pi<players.size();pi++){
			Vector<CardBase> cplayerfield=players.elementAt(pi).GetField();
			for(int fi=0;fi<cplayerfield.size();fi++){
				CardBase cfieldcard=cplayerfield.elementAt(fi);
				if(this.effects[0].TargetData.GetRequiredTargetCount()>0&&!this.effects[0].TargetData.IsValidTarget(cfieldcard))
					continue;
				if(this.effects[0].TargetData.IsLocked()&&cfieldcard!=this.effects[0].TargetData.GetTarget(0))
					continue;
				MagicEffect[] deepCopiedEffects=new MagicEffect[this.effects.length];
				for(int i=0;i<this.effects.length;i++){
					deepCopiedEffects[i]=this.effects[i].DeepCopy();
					if(this.effects[i].TargetData.GetRequiredTargetCount()>0)
						deepCopiedEffects[i].TargetData.SetLockedTarget(0, cfieldcard);
					deepCopiedEffects[i].Resolve(players);
				}
				this.currentlyActiveEffects.add(deepCopiedEffects);
			}
		}
	}
	
	public String GetRulesText(){
		StringBuilder allrules=new StringBuilder();
		for(int e=0;e<this.effects.length;e++){
			if(this.effects[e].IsOptional()){
				allrules.append("optional: ");
			}
			String replacewith="all";
			if(this.effects[0].TargetData.IsLocked())
				replacewith=this.effects[0].TargetData.GetTarget(0).GetName();
			allrules.append(this.effects[e].GetRulesText().replaceFirst("1 target", replacewith)+" ");
		}

		if(this.conditions!=null){
			allrules.append("as long as ");
			for(int c=0;c<this.conditions.length;c++){
				allrules.append(this.conditions[c].GetRulesText()+" ");
			}
		}
		return allrules.toString();
	}
}
