package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectSetLoyalty extends MagicEffect{
	private Planeswalker planeswalker;
	private int amount;
	
	private MagicEffectSetLoyalty(DeepCopyInfo dcinfo, Planeswalker planeswalker, int amount){
		super(dcinfo);
		this.planeswalker=planeswalker;
		this.amount=amount;
	}
	
	public MagicEffectSetLoyalty(Player controller, Planeswalker planeswalker, int amount){
		super(controller, new TargetInfo(), Duration.NA);
		this.planeswalker=planeswalker;
		this.amount=amount;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		this.planeswalker.SetLoyalty(this.amount);
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectSetLoyalty(this.GetDeepCopyInfo(), this.planeswalker, this.amount);
	}

	public String GetRulesText(){
		return ""+this.amount;
	}
}