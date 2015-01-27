package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectPlayToken extends MagicEffect{
	private CardBase token;
	private int tokenCount;
	
	private MagicEffectPlayToken(DeepCopyInfo dcinfo, CardBase token, int tokenCount){
		super(dcinfo);
		this.token=token;
		this.tokenCount=tokenCount;
	}

	public MagicEffectPlayToken(Player controller, CardBase token, int tokenCount){
		super(controller, new TargetInfo(), Duration.NA);
		this.token=token;
		this.tokenCount=tokenCount;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.tokenCount;i++){
			triggeredEffects.addAll(this.controller.PlacePermanentOnField(this.token.DeepCopy(), players));
		}
		return triggeredEffects;
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectPlayToken(this.GetDeepCopyInfo(), this.token, this.tokenCount);
	}

	public String GetRulesText(){
		return this.getExtraCostsString()+"Put "+this.tokenCount+" "+token.GetDescription()+" onto the battlefield.";
	}
}