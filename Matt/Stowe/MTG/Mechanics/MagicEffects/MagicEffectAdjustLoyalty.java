package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectAdjustLoyalty extends MagicEffect{
	private Planeswalker planeswalker;
	private int amount;

	private MagicEffectAdjustLoyalty(DeepCopyInfo dcinfo, Planeswalker planeswalker, int amount){
		super(dcinfo);
		this.planeswalker=planeswalker;
		this.amount=amount;
	}

	public MagicEffectAdjustLoyalty(Player controller, Planeswalker planeswalker, int amount){
		super(controller, new TargetInfo(), Duration.NA);
		this.planeswalker=planeswalker;
		this.amount=amount;
	}
	
	public boolean CanPayAsCost(){
		return this.planeswalker.CanAdjust(this.amount);
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		this.planeswalker.AdjustLoyalty(this.amount);
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectAdjustLoyalty(this.GetDeepCopyInfo(), this.planeswalker, this.amount);
	}

	public String GetRulesText(){
		return ""+this.amount;
	}
}