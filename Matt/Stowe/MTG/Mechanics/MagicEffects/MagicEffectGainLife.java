package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectGainLife extends MagicEffect{
	private boolean needsX;
	private boolean setX;
	private int amount;
	
	private MagicEffectGainLife(DeepCopyInfo dcinfo, int amount, boolean needsX, boolean setX){
		super(dcinfo);
		this.amount=amount;
		this.needsX=needsX;
		this.setX=setX;
	}

	public MagicEffectGainLife(Player controller, int amount){
		super(controller, new TargetInfo(), Duration.NA);
		this.amount=amount;
	}

	public MagicEffectGainLife(Player controller){
		super(controller, new TargetInfo(), Duration.NA);
		this.needsX=true;
	}

	public void SetX(int x){
		if(this.needsX){
			this.setX=true;
			this.amount=x;
		}
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		int lifegained=this.controller.GainLife(this.amount);
		
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		if(lifegained>0){
			for(int p=0;p<players.size();p++){
				triggeredEffects.addAll(players.elementAt(p).TriggerGainedLife(this.controller, lifegained, players));
			}
		}
		this.setX=false;
		return triggeredEffects;
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectGainLife(this.GetDeepCopyInfo(), this.amount, this.needsX, this.setX);
	}

	public String GetRulesText(){
		String xstring=""+(this.needsX&&!this.setX?"X":this.amount);
		if(this.controller==null)
			return "You gain "+xstring+" life.";
		return this.controller.GetName()+" gains "+xstring+" life.";
	}
}
