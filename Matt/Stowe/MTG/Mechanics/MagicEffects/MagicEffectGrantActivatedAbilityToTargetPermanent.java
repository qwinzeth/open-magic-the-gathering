package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;

import java.util.*;

public class MagicEffectGrantActivatedAbilityToTargetPermanent extends MagicEffect{
	private MagicActivatedAbility activatedAbility;

	private MagicEffectGrantActivatedAbilityToTargetPermanent(DeepCopyInfo dcinfo, MagicActivatedAbility activatedAbility){
		super(dcinfo);
		this.activatedAbility=activatedAbility;
	}

	public MagicEffectGrantActivatedAbilityToTargetPermanent(Player controller, MagicActivatedAbility activatedAbility, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
		this.activatedAbility=activatedAbility;
	}
	
	public static MagicEffectGrantActivatedAbilityToTargetPermanent Create(Player controller, MagicActivatedAbility activatedAbility, int minTargets, int maxTargets, int targetTypeFlags, Duration duration){
		return new MagicEffectGrantActivatedAbilityToTargetPermanent(controller, activatedAbility, new TargetInfo(minTargets, maxTargets, targetTypeFlags), duration);
	}

	public static MagicEffectGrantActivatedAbilityToTargetPermanent CreateAutoset(Player controller, MagicActivatedAbility activatedAbility, TargetInfo targetInfo, Duration duration){
		return new MagicEffectGrantActivatedAbilityToTargetPermanent(controller, activatedAbility, new TargetInfo(targetInfo), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=((CardBase)this.TargetData.GetTarget(i));
			ccard.AddActivatedAbility(this.activatedAbility);
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectGrantActivatedAbilityToTargetPermanent(this.GetDeepCopyInfo(), this.activatedAbility);
	}
	
	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=((CardBase)this.TargetData.GetTarget(i));
			ccard.RemoveActivatedAbility(this.activatedAbility);
		}
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		StringBuilder activatedAbilityRules=new StringBuilder();
		if(this.activatedAbility!=null){
			if(this.activatedAbility.ManaCosts!=null){
				activatedAbilityRules.append(this.activatedAbility.ManaCosts.toString());
			}
			if(this.activatedAbility.Costs!=null){
				for(int costindex=0;costindex<this.activatedAbility.Costs.length;costindex++){
					activatedAbilityRules.append(this.activatedAbility.Costs[costindex].GetRulesText()+(costindex==this.activatedAbility.Costs.length-1?"":", "));
				}
			}
			if(this.activatedAbility.Effects!=null){
				for(int effectindex=0;effectindex<this.activatedAbility.Effects.length;effectindex++){
					activatedAbilityRules.append(this.activatedAbility.Effects[effectindex].GetRulesText()+(effectindex==this.activatedAbility.Effects.length-1?"":", "));
				}
			}
		}
		return this.TargetData.GetCSV()+" has "+activatedAbilityRules.toString();
	}
}
