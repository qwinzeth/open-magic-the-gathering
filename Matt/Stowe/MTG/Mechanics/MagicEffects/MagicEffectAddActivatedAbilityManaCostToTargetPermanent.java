package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectAddActivatedAbilityManaCostToTargetPermanent extends MagicEffect{
	private ManaCost manacost;
	
	public MagicEffectAddActivatedAbilityManaCostToTargetPermanent(Player controller, ManaCost manacost, TargetInfo targetInfo, Duration duration){
		super(controller, targetInfo, duration);
		this.manacost=manacost;
	}
	
	private MagicEffectAddActivatedAbilityManaCostToTargetPermanent(DeepCopyInfo dcinfo, ManaCost manacost){
		super(dcinfo);
		this.manacost=manacost;
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=((CardBase)this.TargetData.GetTarget(i));
			ccard.AddActivatedAbilityManaCost(this.manacost);
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectAddActivatedAbilityManaCostToTargetPermanent(this.GetDeepCopyInfo(), this.manacost);
	}
	
	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=((CardBase)this.TargetData.GetTarget(i));
			ccard.RemoveActivatedAbilityManaCost(this.manacost);
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" must pay an extra "+this.manacost.toString()+" to activate abilities "+this.getDurationString()+".";
	}
}
