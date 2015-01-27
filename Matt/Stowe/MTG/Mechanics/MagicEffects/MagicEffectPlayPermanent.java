package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectPlayPermanent extends MagicEffect{
	private CardBase permanent;
	
	private MagicEffectPlayPermanent(DeepCopyInfo dcinfo, CardBase permanent){
		super(dcinfo);
		this.permanent=permanent;
	}

	public MagicEffectPlayPermanent(Player controller, CardBase permanent){
		super(controller, new TargetInfo(), Duration.NA);
		this.permanent=permanent;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		return this.controller.PlacePermanentOnField(this.permanent, players);		
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectPlayPermanent(this.GetDeepCopyInfo(), this.permanent);
	}

	public String GetRulesText(){
		return permanent.GetName()+".";
	}
}