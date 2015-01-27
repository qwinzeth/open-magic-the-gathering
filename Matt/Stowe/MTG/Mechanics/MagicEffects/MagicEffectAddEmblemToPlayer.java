package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectAddEmblemToPlayer extends MagicEffect{
	private int emblemType;

	private MagicEffectAddEmblemToPlayer(DeepCopyInfo dcinfo, int emblemType){
		super(dcinfo);
		this.emblemType=emblemType;
	}
	
	public MagicEffectAddEmblemToPlayer(Player controller, int emblemType){
		super(controller, new TargetInfo(), Duration.NA);
		this.emblemType=emblemType;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		this.controller.GetEmblems().AddEmblem(this.emblemType);
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectAddEmblemToPlayer(this.GetDeepCopyInfo(), this.emblemType);
	}

	public String GetRulesText(){
		return "You get an emblem with \""+EmblemCollection.GetEmblemString(this.emblemType)+"\"";
	}
}