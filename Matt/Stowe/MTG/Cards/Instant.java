package Matt.Stowe.MTG.Cards;
import Matt.Stowe.MTG.*;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.awt.*;
import java.util.Vector;

public class Instant extends CardBase{
	public boolean IsSorcerySpeed(){return false;}
	public boolean IsPermanent(){return false;}
	
	public Instant(Player controller, String name, String description, ManaCost cost, MagicEffect[] effects){
		super(controller, name, description, TargetInfo.TARGET_TYPE_FLAG_INSTANT, cost, new MagicEffect[effects.length+1]);
		for(int i=0;i<effects.length;i++){
			this.playEffects[i]=effects[i];
			this.playEffects[i].SetSource(this);
		}
		this.playEffects[effects.length]=new MagicEffectDiscardCard(controller, this);
		this.playEffects[effects.length].SetSource(this);
	}
	
	private Instant(DeepCopyInfo dcinfo){
		super(dcinfo);
	}
	
	public Instant DeepCopy(){
		return new Instant(this.GetDeepCopyInfo());
	}
}
