package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Cards.*;

import java.util.*;

public class MagicEffectPreventUntappingTargetCard extends MagicEffect{
	private MagicEffectPreventUntappingTargetCard(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectPreventUntappingTargetCard(Player controller, TargetInfo targetInfo){
		super(controller, targetInfo, Duration.NA);
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=((CardBase)this.TargetData.GetTarget(i));
			ccard.AddUntapPrevention();
		}
		return new ArrayList<MagicEffect[]>();
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			CardBase ccard=((CardBase)this.TargetData.GetTarget(i));
			ccard.RemoveUntapPrevention();
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectPreventUntappingTargetCard(this.GetDeepCopyInfo());
	}
	
	public String GetRulesText(){
		return this.TargetData.GetCSV()+" doesn't untap during its controller's untap step.";
	}
}