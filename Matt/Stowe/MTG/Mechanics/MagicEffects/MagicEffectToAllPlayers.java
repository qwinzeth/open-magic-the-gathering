package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectToAllPlayers extends MagicEffect{
	private MagicEffect baseEffect;

	private MagicEffectToAllPlayers(DeepCopyInfo dcinfo, MagicEffect baseEffect){
		super(dcinfo);
		this.baseEffect=baseEffect;
	}

	public MagicEffectToAllPlayers(MagicEffect baseEffect){
		super(baseEffect.GetController(), new TargetInfo(), Duration.NA);
		this.baseEffect=baseEffect;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<MagicEffect> individualEffects=new ArrayList<MagicEffect>();
		for(int pi=0;pi<players.size();pi++){
			Player cplayer=players.get(pi);
			if(!this.baseEffect.TargetData.IsValidTarget(cplayer)){
				continue;
			}
			MagicEffect individualEffect=this.baseEffect.DeepCopy();
			individualEffect.TargetData.SetLockedTarget(0, cplayer);
			individualEffects.add(individualEffect);
		}

		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<individualEffects.size();i++){
			triggeredEffects.addAll(individualEffects.get(i).Resolve(players));
		}
		return triggeredEffects;
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectToAllPlayers(this.GetDeepCopyInfo(), this.baseEffect);
	}

	public String GetRulesText(){
		return this.baseEffect.GetRulesText().replace("1 target", "all");
	}
}
