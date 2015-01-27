package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectRevealTargetCard extends MagicEffect{
	private MagicEffectRevealTargetCard(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectRevealTargetCard(Player controller, TargetInfo revealTargetInfo){
		super(controller, revealTargetInfo, Duration.NA);
	}

	public boolean CanPayAsCost(){
		Vector<CardBase> hand=this.controller.GetHand();
		for(int i=0;i<hand.size();i++){
			if(this.TargetData.IsValidTarget(hand.get(i))){
				return true;
			}
		}
		return false;
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			for(int p=0;p<players.size();p++){
				players.get(p).CardRevealed((CardBase)this.TargetData.GetTarget(i));
			}
		}
		return triggeredEffects;
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectRevealTargetCard(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return "Reveal "+this.TargetData.GetCSV()+".";
	}
}