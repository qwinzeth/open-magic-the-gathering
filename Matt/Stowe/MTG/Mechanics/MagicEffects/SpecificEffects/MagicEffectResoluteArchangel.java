package Matt.Stowe.MTG.Mechanics.MagicEffects.SpecificEffects;

import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class MagicEffectResoluteArchangel extends MagicEffect{
	private MagicEffectResoluteArchangel(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectResoluteArchangel(Player controller){
		super(controller, new TargetInfo(), Duration.NA);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		int lifeToGain=this.controller.GetStartingLifePoints()-this.controller.GetLifePoints();
		if(lifeToGain<1)
			return new ArrayList<MagicEffect[]>();
		
		MagicEffect gainlife=new MagicEffectGainLife(this.controller, lifeToGain);
		return gainlife.Resolve(players);
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectResoluteArchangel(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return "If your life total is less than your starting life total, it becomes equal to your starting life total.";
	}

}