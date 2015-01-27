package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectAddPTToTargetCreatureForeachPermanent extends MagicEffect{
	private int powerPerCreatureDelta;
	private int toughnessPerCreatureDelta;
	private TargetInfo cardRequirements;
	private ArrayList<MagicEffect> individualEffects;
	
	public MagicEffectAddPTToTargetCreatureForeachPermanent(Player controller, int powerPerCreature, int toughnessPerCreature, TargetInfo targetInfo, Duration duration, TargetInfo cardRequirements){
		super(controller, targetInfo, duration);
		this.powerPerCreatureDelta=powerPerCreature;
		this.toughnessPerCreatureDelta=toughnessPerCreature;
		this.cardRequirements=cardRequirements;
	}
	
	private MagicEffectAddPTToTargetCreatureForeachPermanent(DeepCopyInfo dcinfo, int powerPerCreatureDelta, int toughnessPerCreatureDelta, TargetInfo cardRequirements){
		super(dcinfo);
		this.powerPerCreatureDelta=powerPerCreatureDelta;
		this.toughnessPerCreatureDelta=toughnessPerCreatureDelta;
		this.cardRequirements=cardRequirements;
	}
	
	public static MagicEffectAddPTToTargetCreatureForeachPermanent Create(Player controller, int powerPerCreature, int toughnessPerCreature, int minTargets, int maxTargets, Duration duration, TargetInfo cardRequirements){
		return new MagicEffectAddPTToTargetCreatureForeachPermanent(controller, powerPerCreature, toughnessPerCreature, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE), duration, cardRequirements);
	}

	public static MagicEffectAddPTToTargetCreatureForeachPermanent CreateWithInvalidTargets(Player controller, int powerPerCreature, int toughnessPerCreature, int minTargets, int maxTargets, Duration duration, TargetInfo cardRequirements, TargetInfo[] invalidTargets){
		return new MagicEffectAddPTToTargetCreatureForeachPermanent(controller, powerPerCreature, toughnessPerCreature, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE, invalidTargets), duration, cardRequirements);
	}

	public static MagicEffectAddPTToTargetCreatureForeachPermanent CreateColorRestricted(Player controller, int powerPerCreature, int toughnessPerCreature, int minTargets, int maxTargets, int colorORFlags, Duration duration, TargetInfo cardRequirements){
		return new MagicEffectAddPTToTargetCreatureForeachPermanent(controller, powerPerCreature, toughnessPerCreature, new TargetInfo(minTargets, maxTargets, TargetInfo.TARGET_TYPE_FLAG_CREATURE, colorORFlags), duration, cardRequirements);
	}

	public static MagicEffectAddPTToTargetCreatureForeachPermanent CreateAutoset(Player controller, int powerPerCreature, int toughnessPerCreature, TargetInfo autosetTargetInfo, Duration duration, TargetInfo cardRequirements){
		return new MagicEffectAddPTToTargetCreatureForeachPermanent(controller, powerPerCreature, toughnessPerCreature, new TargetInfo(autosetTargetInfo), duration, cardRequirements);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		int totalPower=0;
		int totalToughness=0;
		for(int pi=0;pi<players.size();pi++){
			Vector<CardBase> controlledCards=players.get(pi).GetField();
			for(int i=0;i<controlledCards.size();i++){
				CardBase ccard=controlledCards.elementAt(i);
				if(this.cardRequirements.IsValidTarget(ccard)){
					totalPower+=this.powerPerCreatureDelta;
					totalToughness+=this.toughnessPerCreatureDelta;
				}
			}
		}
	
		this.individualEffects=new ArrayList<MagicEffect>();
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			Creature ccreature=((Creature)this.TargetData.GetTarget(i));
			MagicEffect individualEffect=MagicEffectAddPTToTargetCreature.Create(this.controller, totalPower, totalToughness, 1, 1, Duration.NA);
			individualEffect.TargetData.SetLockedTarget(0, ccreature);
			triggeredEffects.addAll(individualEffect.Resolve(players));
			this.individualEffects.add(individualEffect);
		}
		super.handleDuration();
		return triggeredEffects;
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.individualEffects.size();i++){
			triggeredEffects.addAll(this.individualEffects.get(i).Undo(players));
		}
		return triggeredEffects;
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectAddPTToTargetCreatureForeachPermanent(this.GetDeepCopyInfo(), this.powerPerCreatureDelta, this.toughnessPerCreatureDelta, this.cardRequirements);
	}
	
	public String GetRulesText(){
		return this.powerPerCreatureDelta+"/"+this.toughnessPerCreatureDelta+" "+this.cardRequirements.GetCSV().replace("1 target","for each")+" to "+this.TargetData.GetCSV()+" "+this.getDurationString()+".";
	}
}
