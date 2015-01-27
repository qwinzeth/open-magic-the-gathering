package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectCancelSpell extends MagicEffect{
	public MagicEffectCancelSpell(Player controller, TargetInfo targetInfo){
		super(controller, targetInfo, Duration.NA);
		this.TargetData.SetValidTargetZones(new ZoneOptions(ZoneOptions.STACK));
	}

	private MagicEffectCancelSpell(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		Stack<MagicStackElement> thestack=players.get(0).TheStack;
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ctarget=(CardBase)this.TargetData.GetTarget(i);
			for(int s=thestack.size()-1;s>=0;s--){
				if(thestack.get(s).CardToPlay==ctarget){
					thestack.remove(s);
				}
			}
			triggeredEffects.addAll(players.get(ctarget.GetOwnerIndex()).PlaceCardInGraveyard(ctarget, players));
		}
		return triggeredEffects;
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectCancelSpell(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return "Counter "+this.TargetData.GetCSV()+".";
	}
}
