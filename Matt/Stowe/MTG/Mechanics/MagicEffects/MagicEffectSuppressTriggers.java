package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectSuppressTriggers extends MagicEffect{
	private TargetInfo suppressedPermanents;

	private MagicEffectSuppressTriggers(DeepCopyInfo dcinfo, TargetInfo suppressedPermanents){
		super(dcinfo);
		this.suppressedPermanents=suppressedPermanents;
	}

	private MagicEffectSuppressTriggers(Player controller, TargetInfo sourceTarget, TargetInfo suppressedPermanents, Duration duration){
		super(controller, sourceTarget, duration);
		this.suppressedPermanents=suppressedPermanents;
	}
	
	public static MagicEffectSuppressTriggers Create(Player controller, CardBase source, TargetInfo suppressedPermanents, Duration duration){
		TargetInfo sourceTarget=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		sourceTarget.SetLockedTarget(0, source);
		
		return new MagicEffectSuppressTriggers(controller, sourceTarget, suppressedPermanents, duration);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;i<players.size();i++){
			Player cplayer=players.elementAt(i);
			cplayer.AddEntersTheBattlefieldTriggerSuppression(this.suppressedPermanents);
		}
		super.handleDuration();
		return new ArrayList<MagicEffect[]>();
	}

	public MagicEffect DeepCopy(){
		return new MagicEffectSuppressTriggers(this.GetDeepCopyInfo(), this.suppressedPermanents.DeepCopy());
	}

	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){
		for(int i=0;i<players.size();i++){
			Player cplayer=players.elementAt(i);
			cplayer.RemoveEntersTheBattlefieldTriggerSuppression(this.suppressedPermanents);
		}
		return new ArrayList<MagicEffect[]>();
	}
	
	public String GetRulesText(){
		return this.suppressedPermanents.GetCSV().replace("1 target","")+" entering the battlefield don't cause abilities to trigger.";
	}
}
