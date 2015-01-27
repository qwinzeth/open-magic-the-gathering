package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectLoseLife extends MagicEffect{
	private int amount;
	
	private MagicEffectLoseLife(DeepCopyInfo dcinfo, int amount){
		super(dcinfo);
		this.amount=amount;
	}

	public MagicEffectLoseLife(Player controller, int amount){
		super(controller, new TargetInfo(), Duration.NA);
		this.amount=amount;
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		//TODO: replace null with source
		int damage=this.controller.MarkDamage(this.amount, DamagePreventor.DAMAGE_TYPE_FLAG_NONCOMBAT, null);
		//TODO: distinguish between damage and life loss
		return new ArrayList<MagicEffect[]>();//foreach player player.TriggerLostLife(this.controller, damage, DamagePreventor.DAMAGE_TYPE_FLAG_NONCOMBAT, null);
	}
	
	public MagicEffectLoseLife DeepCopy(){
		return new MagicEffectLoseLife(this.GetDeepCopyInfo(), this.amount);
	}
	
	public String GetRulesText(){
		if(this.controller==null)
			return "You lose "+this.amount+" life.";
		return this.controller.GetName()+" loses "+this.amount+" life.";
	}
}