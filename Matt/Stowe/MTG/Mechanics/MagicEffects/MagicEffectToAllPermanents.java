package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectToAllPermanents extends MagicEffect{
	private MagicEffect baseEffect;

	private MagicEffectToAllPermanents(DeepCopyInfo dcinfo, MagicEffect baseEffect){
		super(dcinfo);
		this.baseEffect=baseEffect;
	}

	public MagicEffectToAllPermanents(MagicEffect baseEffect){
		super(baseEffect.GetController(), new TargetInfo(), Duration.NA);
		this.baseEffect=baseEffect;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<MagicEffect> individualEffects=new ArrayList<MagicEffect>();
		for(int pi=0;pi<players.size();pi++){
			Vector<CardBase> controlledCards=players.get(pi).GetField();
			for(int i=0;i<controlledCards.size();i++){
				CardBase ccard=controlledCards.elementAt(i);
				if(!this.baseEffect.TargetData.IsValidTarget(ccard)){
					continue;
				}
				MagicEffect individualEffect=this.baseEffect.DeepCopy();
				individualEffect.TargetData.SetLockedTarget(0, ccard);
				individualEffects.add(individualEffect);
			}
		}

		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<individualEffects.size();i++){
			triggeredEffects.addAll(individualEffects.get(i).Resolve(players));
		}
		return triggeredEffects;
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectToAllPermanents(this.GetDeepCopyInfo(), this.baseEffect);
	}

	public String GetRulesText(){
		return this.baseEffect.GetRulesText().replace("1 target", "all");
	}
}
