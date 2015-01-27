package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectMonstrosity extends MagicEffect{
	private Creature monster;
	private int amount;
	
	private MagicEffectMonstrosity(DeepCopyInfo dcinfo, Creature monster, int amount){
		super(dcinfo);
		this.monster=monster;
		this.amount=amount;
	}
	
	public MagicEffectMonstrosity(Player controller, Creature monster, int amount){
		super(controller, new TargetInfo(), Duration.NA);
		this.monster=monster;
		this.amount=amount;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		if(!this.monster.GetKeywords().HasMonstrous()){
			this.monster.AddPowerToughnessCounters(this.amount);
			this.monster.GetKeywords().AddMonstrous();
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffectMonstrosity DeepCopy(){
		return new MagicEffectMonstrosity(this.GetDeepCopyInfo(), this.monster, this.amount);
	}
	
	public String GetRulesText(){
		return this.monster.GetName()+" monstrosity "+this.amount+".";
	}
}