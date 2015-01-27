package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectPlayEnchantmentOnPermanent extends MagicEffect{
	private Enchantment enchantment;
	
	private MagicEffectPlayEnchantmentOnPermanent(DeepCopyInfo dcinfo, Enchantment enchantment){
		super(dcinfo);
		this.enchantment=enchantment;
	}

	public MagicEffectPlayEnchantmentOnPermanent(Player controller, Enchantment enchantment, int targetType){
		super(controller, new TargetInfo(1, 1, targetType), Duration.NA);
		this.enchantment=enchantment;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		return this.controller.AttachEnchantmentToPermanent(this.enchantment, (CardBase)this.TargetData.GetTarget(0), players);
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectPlayEnchantmentOnPermanent(this.GetDeepCopyInfo(), this.enchantment);
	}

	public String GetRulesText(){
		return "Attach "+this.enchantment.GetName()+" to "+this.TargetData.GetCSV()+".";
	}
}