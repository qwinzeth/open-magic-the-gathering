package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectTimerSkip extends MagicEffect{
	public MagicEffectTimerSkip(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectTimerSkip(Player controller){
		super(controller, null, Duration.NA);
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){return new ArrayList<MagicEffect[]>();}

	public MagicEffect DeepCopy(){
		return new MagicEffectTimerSkip(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return this.controller.GetName()+": Timer skip control: this should never be on the stack.";
	}
}