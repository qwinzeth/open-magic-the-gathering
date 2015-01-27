package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectExileTargetCards extends MagicEffect{
	private Vector<Player> players;

	public MagicEffectExileTargetCards(Player controller, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
	}

	private MagicEffectExileTargetCards(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public static MagicEffectExileTargetCards Create(Player controller, int minTargets, int maxTargets, int targetTypeFlags, Duration duration){
		return new MagicEffectExileTargetCards(controller, new TargetInfo(minTargets, maxTargets, targetTypeFlags), duration);
	}

	public static MagicEffectExileTargetCards CreateAutoset(Player controller, TargetInfo autosetTargetInfo, Duration duration){
		return new MagicEffectExileTargetCards(controller, new TargetInfo(autosetTargetInfo), duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		this.players=players;
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=(CardBase)this.TargetData.GetTarget(i);
			for(int p=0;p<players.size();p++){
				players.elementAt(p).PlaceCardInExile(ccard, players);
			}
		}

		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectExileTargetCards(this.GetDeepCopyInfo());
	}
	
	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.TargetData.GetMaxTargetCount()&&this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=(CardBase)this.TargetData.GetTarget(i);
			for(int p=0;p<this.players.size();p++){
				triggeredEffects.addAll(this.players.elementAt(p).UnExileCard(ccard, players));
			}
		}
		return triggeredEffects;
	}

	public String GetRulesText(){
		return "Exile "+this.TargetData.GetCSV()+" "+this.getDurationString()+".";
	}
}
