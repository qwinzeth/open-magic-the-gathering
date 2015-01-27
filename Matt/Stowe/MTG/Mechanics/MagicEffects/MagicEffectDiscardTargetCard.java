package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectDiscardTargetCard extends MagicEffect{
	private MagicEffectDiscardTargetCard(DeepCopyInfo dcinfo){
		super(dcinfo);
	}
	
	public MagicEffectDiscardTargetCard(Player controller, TargetInfo targetInfo){
		super(controller, targetInfo, Duration.NA);
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=(CardBase)this.TargetData.GetTarget(i);
			for(int p=0;p<players.size();p++){
				Player cplayer=players.elementAt(p);
				if(cplayer.GetCardZone(ccard)!=0){
					triggeredEffects.addAll(cplayer.PlaceCardInGraveyard(ccard, players));
					break;
				}
			}
		}
		return triggeredEffects;
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectDiscardTargetCard(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return "Discard "+this.TargetData.GetCSV()+".";
	}
}