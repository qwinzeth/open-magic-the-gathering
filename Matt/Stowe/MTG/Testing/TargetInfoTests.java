package Matt.Stowe.MTG.Testing;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Cards.Sets.*;
import Matt.Stowe.MTG.Mechanics.*;

public class TargetInfoTests{
	public static void main(String args[]){
		TargetInfo cantBeBlockedByAnyColor=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		cantBeBlockedByAnyColor.SetColorORFlags(ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLUE|ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK);

		Player fakePlayer=new Player(0, OptionsScreen.CONTROLTYPE_LOCALHUMAN, "FAKE", true, 0, 0, 0, 0);
		Creature childOfNight=Core2015.ChildOfNight(fakePlayer);
		if(!cantBeBlockedByAnyColor.IsValidTarget(childOfNight))
			System.out.println(childOfNight.GetName()+" does not fulfill "+cantBeBlockedByAnyColor.GetCSV());
			
		Creature ornithopter=Core2015.Ornithopter(fakePlayer);
		if(cantBeBlockedByAnyColor.IsValidTarget(ornithopter))
			System.out.println(ornithopter.GetName()+" fulfills "+cantBeBlockedByAnyColor.GetCSV());
			
			
		TargetInfo anyColorAnything=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		anyColorAnything.SetColorORFlags(ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLUE|ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK);
		Instant lightningStrike=Core2015.LightningStrike(fakePlayer);
		if(!anyColorAnything.IsValidTarget(lightningStrike))
			System.out.println(lightningStrike.GetName()+" does not fulfill "+anyColorAnything.GetCSV());
	}
}