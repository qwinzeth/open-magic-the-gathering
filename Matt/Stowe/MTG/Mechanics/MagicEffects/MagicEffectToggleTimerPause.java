package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectToggleTimerPause extends MagicEffect{
	private MagicEffectToggleTimerPause(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectToggleTimerPause(Player controller){
		super(controller, null, Duration.NA);
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){return new ArrayList<MagicEffect[]>();}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectToggleTimerPause(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return this.controller.GetName()+": Timer control: this should never be on the stack.";
	}
}