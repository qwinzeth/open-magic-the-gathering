package Matt.Stowe.MTG.Transferrable;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.*;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.awt.*;
import java.util.Vector;

public class FaceDownCard extends CardBase{	
	public FaceDownCard(Player controller){
		super(controller, "", "", TargetInfo.TARGET_TYPE_ANY, null, null);
		this.faceDown=true;
	}
	
	private FaceDownCard(DeepCopyInfo dcinfo){
		super(dcinfo);
	}
	
	public FaceDownCard DeepCopy(){
		return new FaceDownCard(this.GetDeepCopyInfo());
	}
}
