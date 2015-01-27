package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectPlaceTargetPermanentOnField extends MagicEffect{
	private MagicEffectPlaceTargetPermanentOnField(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectPlaceTargetPermanentOnField(Player controller, TargetInfo targetinfo){
		super(controller, targetinfo, Duration.NA);
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			this.controller.PlacePermanentOnField((CardBase)this.TargetData.GetTarget(i), players);
		}
		return triggeredEffects;
	}

	public void SetX(int x){
		this.TargetData.SetX(x);
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectPlaceTargetPermanentOnField(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return "Place "+this.TargetData.GetCSV()+" on the battlefield.";
	}
}
