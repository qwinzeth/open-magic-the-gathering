package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectScryX extends MagicEffect{
	private int scryAmount;
	
	private MagicEffectScryX(DeepCopyInfo dcinfo, int scryAmount){
		super(dcinfo);
		this.scryAmount=scryAmount;
	}
	
	public MagicEffectScryX(Player controller, int scryAmount){
		super(controller, new TargetInfo(), Duration.NA);
		this.scryAmount=scryAmount;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		this.controller.ScryX(this.scryAmount);
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectScryX(this.GetDeepCopyInfo(), this.scryAmount);
	}

	public String GetRulesText(){
		return "Scry "+this.scryAmount+".";
	}
}