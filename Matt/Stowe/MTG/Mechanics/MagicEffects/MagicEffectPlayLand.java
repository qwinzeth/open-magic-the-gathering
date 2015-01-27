package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectPlayLand extends MagicEffect{
	private CardBase land;
	
	private MagicEffectPlayLand(DeepCopyInfo dcinfo, CardBase land){
		super(dcinfo);
		this.land=land;
	}

	public MagicEffectPlayLand(Player controller, CardBase land){
		super(controller, new TargetInfo(), Duration.NA);
		this.land=land;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		return this.controller.PlacePermanentOnField(this.land, players);
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectPlayLand(this.GetDeepCopyInfo(), this.land);
	}
	
	public String GetRulesText(){
		return land.GetName()+".";
	}
}