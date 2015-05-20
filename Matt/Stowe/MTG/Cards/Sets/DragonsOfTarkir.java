package Matt.Stowe.MTG.Cards.Sets;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.SpecificEffects.*;
import Matt.Stowe.MTG.Mechanics.ContinuousAbilities.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;

public class DragonsOfTarkir{
	public static String[] MythicRares=new String[]{};
	public static String[] Rares=new String[]{};
	public static String[] Uncommons=new String[]{};
	public static String[] Commons=new String[]{"Forest", "Island", "Mountain", "Plains", "Swamp"};

	private static void addMegamorphAbility(Player owner, Creature creature, ManaCost morphCost){
		creature.GetKeywords().AddMorph();

		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, creature);
		MagicEffectAddPTCountersToTargetCreature addPTCountersEffect=new MagicEffectAddPTCountersToTargetCreature(owner, 1, selfTargetInfo);

		creature.AddActivatedAbility(new MagicActivatedAbility(false, morphCost, null, new MagicEffect[]{new MagicEffectMorphTargetCreature(owner, creature), addPTCountersEffect}));
	}

// REGION GREEN
	// REGION CREATURES
	public static Creature AerieBowmasters(Player owner){
		Creature aerieBowmasters=Creature.NewCreature(owner, "Aerie Bowmasters", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), 3, 4);

		addMegamorphAbility(owner, aerieBowmasters, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}));
		
		aerieBowmasters.GetKeywords().AddReach();
		aerieBowmasters.GetSubtypes().AddSubtype(SubtypeCollection.HOUND);
		aerieBowmasters.GetSubtypes().AddSubtype(SubtypeCollection.ARCHER);
		return aerieBowmasters;
	}
	// ENDREGION CREATURES
// ENDREGION GREEN
}
