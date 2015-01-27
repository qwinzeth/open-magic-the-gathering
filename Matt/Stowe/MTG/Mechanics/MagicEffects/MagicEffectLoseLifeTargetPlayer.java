package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectLoseLifeTargetPlayer extends MagicEffect{
	private int amount;
	
	private MagicEffectLoseLifeTargetPlayer(DeepCopyInfo dcinfo, int amount){
		super(dcinfo);
		this.amount=amount;
	}

	public MagicEffectLoseLifeTargetPlayer(Player controller, int amount, TargetInfo targetinfo){
		super(controller, targetinfo, Duration.NA);
		this.amount=amount;
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			ITargetable ctarget=this.TargetData.GetTarget(i);
			//TODO: replace null with source
			int damage=ctarget.MarkDamage(this.amount, DamagePreventor.DAMAGE_TYPE_FLAG_NONCOMBAT, null);
			//TODO: distinguish between damage and life loss
			//foreach player player.TriggerLostLife(this.controller, damage, DamagePreventor.DAMAGE_TYPE_FLAG_NONCOMBAT, null);
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffectLoseLifeTargetPlayer DeepCopy(){
		return new MagicEffectLoseLifeTargetPlayer(this.GetDeepCopyInfo(), this.amount);
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" loses "+this.amount+" life.";
	}
}
