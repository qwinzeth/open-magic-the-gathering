package Matt.Stowe.MTG.Cards;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.Common.*;

import java.awt.*;

public class NonbasicLand extends CardBase{
	private int colorFlags;

	public NonbasicLand DeepCopy(){
		return new NonbasicLand(this.GetDeepCopyInfo(), this.colorFlags);
	}

	protected Color GetAWTColor(){
		return ManaCost.GetAWTColorByFlag(this.colorFlags);
	}
	
	public NonbasicLand(Player owner, String name, int colorFlags){
		super(owner, name, "", TargetInfo.TARGET_TYPE_FLAG_NONBASICLAND, null, null);
		this.colorFlags=colorFlags;
	}
	
	private NonbasicLand(DeepCopyInfo dcinfo, int colorFlags){
		super(dcinfo);
		this.colorFlags=colorFlags;
	}
}