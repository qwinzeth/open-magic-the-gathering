package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectLookAtHandOfTargetPlayer extends MagicEffect{
	private MagicEffectLookAtHandOfTargetPlayer(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectLookAtHandOfTargetPlayer(Player controller, TargetInfo playerTargetInfo){
		super(controller, playerTargetInfo, Duration.NA);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			this.controller.HandRevealed((Player)this.TargetData.GetTarget(i));
		}
		return triggeredEffects;
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectLookAtHandOfTargetPlayer(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return "Look at hand of "+this.TargetData.GetCSV()+".";
	}
}