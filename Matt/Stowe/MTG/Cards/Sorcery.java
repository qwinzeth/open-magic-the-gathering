package Matt.Stowe.MTG.Cards;
import Matt.Stowe.MTG.*;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.awt.*;
import java.util.Vector;

public class Sorcery extends CardBase{
	public boolean IsPermanent(){return false;}
	
	public Sorcery(Player controller, String name, String description, ManaCost cost, MagicEffect[] effects){
		super(controller, name, description, TargetInfo.TARGET_TYPE_FLAG_SORCERY, cost, new MagicEffect[effects.length+1]);
		for(int i=0;i<effects.length;i++){
			this.playEffects[i]=effects[i];
			this.playEffects[i].SetSource(this);
		}
		this.playEffects[effects.length]=new MagicEffectDiscardCard(controller, this);
		this.playEffects[effects.length].SetSource(this);
	}
	
	private Sorcery(DeepCopyInfo dcinfo){
		super(dcinfo);
	}
	
	public Sorcery DeepCopy(){
		return new Sorcery(this.GetDeepCopyInfo());
	}
}