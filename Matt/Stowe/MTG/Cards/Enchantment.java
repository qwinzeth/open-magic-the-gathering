package Matt.Stowe.MTG.Cards;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.awt.*;
import java.util.*;

public class Enchantment extends CardBase{
	private MagicEffect[] auraEffects;

	private CardBase targetPermanent;
	public CardBase GetTargetPermanent(){return this.targetPermanent;}
	public void SetTargetPermanent(CardBase c){
		this.targetPermanent=c;
		if(this.auraEffects!=null){
			for(int i=0;i<this.auraEffects.length;i++){
				MagicEffect ceffect=this.auraEffects[i];
				ceffect.TargetData.SetTarget(0, c);
				ceffect.Resolve(null);
			}
		}
	}

	private Enchantment(Player owner, String name, String description, int typeFlags, ManaCost mana, MagicEffect[] playEffects, MagicEffect[] auraEffects){
		super(owner, name, description, typeFlags, mana, playEffects);
		this.auraEffects=auraEffects;
		if(this.auraEffects!=null){
			for(int i=0;i<this.auraEffects.length;i++){
				this.auraEffects[i].SetSource(this);
			}
		}
	}

	private Enchantment(DeepCopyInfo dcinfo, MagicEffect[] auraEffects, CardBase targetPermanent){
		super(dcinfo);
		this.auraEffects=auraEffects;
		if(this.auraEffects!=null){
			for(int i=0;i<this.auraEffects.length;i++){
				this.auraEffects[i].SetSource(this);
			}
		}
		this.targetPermanent=targetPermanent;
	}
	
	public static Enchantment NewEnchantment(Player owner, String name, String description, ManaCost mana){
		return new Enchantment(owner, name, description, TargetInfo.TARGET_TYPE_FLAG_ENCHANTMENT, mana, null, null);
	}
	
	public static Enchantment NewEnchantmentAura(Player owner, String name, String description, ManaCost mana, int targetType, MagicEffect[] auraEffects){
		Enchantment aura=new Enchantment(owner, name, description, TargetInfo.TARGET_TYPE_FLAG_ENCHANTMENT|TargetInfo.TARGET_TYPE_FLAG_AURA, mana, new MagicEffect[1], auraEffects);
		aura.GetPlayEffects()[0]=new MagicEffectPlayEnchantmentOnPermanent(owner, aura, targetType);
		aura.GetPlayEffects()[0].SetSource(aura);
		return aura;
	}
	
	public boolean IsAttached(){
		return this.targetPermanent!=null;
	}
	
	public boolean IsAttachedToValidTarget(){
		if(this.targetPermanent==null)
			return false;
		return this.GetPlayEffects()[0].TargetData.IsValidTarget(this.targetPermanent);
	}

	public Enchantment DeepCopy(){
		MagicEffect[] deepCopyAuraEffects=null;
		if(this.auraEffects!=null){
			deepCopyAuraEffects=new MagicEffect[this.auraEffects.length];
			for(int i=0;i<this.auraEffects.length;i++){
				deepCopyAuraEffects[i]=this.auraEffects[i].DeepCopy();
			}
		}

		return new Enchantment(this.GetDeepCopyInfo(), deepCopyAuraEffects, this.targetPermanent);
	}
	
	public ArrayList<MagicEffect[]> Destroy(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=super.Destroy(players);
		if(this.IsAttached()){
			this.targetPermanent.Auras.remove(this);
			if(this.auraEffects!=null){
				for(int i=0;i<this.auraEffects.length;i++){
					triggeredEffects.addAll(this.auraEffects[i].Undo(players));
				}
			}
		}
		return triggeredEffects;
	}
	
	public int paintFullVersionAt(Graphics g, int x, int y){
		int cy=super.paintFullVersionAt(g, x, y);
		
		if(this.faceDown)
			return cy;
		
		if(this.auraEffects!=null){
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<this.auraEffects.length;i++){
				sb.append(this.auraEffects[i].GetRulesText().replace("1 target","enchanted")+" ");
			}
			
			cy+=14*Painting.DrawWrappedString(g, sb.toString(), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		return cy;
	}
}