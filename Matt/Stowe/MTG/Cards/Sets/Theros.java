package Matt.Stowe.MTG.Cards.Sets;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;

public class Theros{
// REGION CREATURES
	// REGION RED
	public static Creature BorderlandMinotaur(Player owner){
		Creature borderlandMinotaur=Creature.NewCreature(owner, "Borderland Minotaur", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), 4, 3);
		borderlandMinotaur.GetSubtypes().AddSubtype(SubtypeCollection.MINOTAUR);
		borderlandMinotaur.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return borderlandMinotaur;
	}

	public static Creature IllTemperedCyclops(Player owner){
		Creature illTemperedCyclops=Creature.NewCreature(owner, "Ill-Tempered Cyclops", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 3, 3);
		illTemperedCyclops.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), null, new MagicEffect[]{new MagicEffectMonstrosity(owner, illTemperedCyclops, 3)}));
		illTemperedCyclops.GetKeywords().AddTrample();
		illTemperedCyclops.GetSubtypes().AddSubtype(SubtypeCollection.CYCLOPS);
		return illTemperedCyclops;
	}

	public static Creature MinotaurSkullcleaver(Player owner){
		Creature minotaurSkullcleaver=Creature.NewCreature(owner, "Minotaur Skullcleaver", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 2, 2);
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectAddPTToTargetCreature.Create(owner, 2, 0, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		effects[0].TargetData.SetLockedTarget(0, minotaurSkullcleaver);
		minotaurSkullcleaver.AddEntersTheBattlefieldTrigger(
			new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(minotaurSkullcleaver)}, effects));
		minotaurSkullcleaver.GetKeywords().AddHaste();
		minotaurSkullcleaver.GetSubtypes().AddSubtype(SubtypeCollection.MINOTAUR);
		minotaurSkullcleaver.GetSubtypes().AddSubtype(SubtypeCollection.BERSERKER);
		return minotaurSkullcleaver;
	}

	public static Creature SatyrRambler(Player owner){
		Creature satyrRambler=Creature.NewCreature(owner, "Satyr Rambler", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 2, 1);
		satyrRambler.GetKeywords().AddTrample();
		satyrRambler.GetSubtypes().AddSubtype(SubtypeCollection.SATYR);
		return satyrRambler;
	}
	// ENDREGION RED
	// REGION GREEN
	public static Creature NessianAsp(Player owner){
		Creature nessianAsp=Creature.NewCreature(owner, "Nessian Asp", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 4, 5);
		nessianAsp.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), null, new MagicEffect[]{new MagicEffectMonstrosity(owner, nessianAsp, 4)}));
		nessianAsp.GetKeywords().AddReach();
		nessianAsp.GetSubtypes().AddSubtype(SubtypeCollection.SNAKE);
		return nessianAsp;
	}

	public static Creature NessianCourser(Player owner){
		Creature nessianCourser=Creature.NewCreature(owner, "Nessian Courser", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 3, 3);
		nessianCourser.GetSubtypes().AddSubtype(SubtypeCollection.CENTAUR);
		nessianCourser.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return nessianCourser;
	}

	public static Creature PheresBandCentaurs(Player owner){
		Creature pheresBandCentaurs=Creature.NewCreature(owner, "Pheres-Band Centaurs", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 3, 7);
		pheresBandCentaurs.GetSubtypes().AddSubtype(SubtypeCollection.CENTAUR);
		pheresBandCentaurs.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return pheresBandCentaurs;
	}

	public static Creature VulpineGoliath(Player owner){
		Creature vulpineGoliath=Creature.NewCreature(owner, "Vulpine Goliath", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), 6, 5);
		vulpineGoliath.GetKeywords().AddTrample();
		vulpineGoliath.GetSubtypes().AddSubtype(SubtypeCollection.FOX);
		return vulpineGoliath;
	}
	// ENDREGION GREEN
	// REGION BLUE
	public static Creature HorizonScholar(Player owner){
		Creature horizonScholar=Creature.NewCreature(owner, "Horizon Scholar", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 4, 4);
		horizonScholar.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(horizonScholar)}, new MagicEffect[]{new MagicEffectScryX(owner, 2)}));
		horizonScholar.GetKeywords().AddFlying();
		horizonScholar.GetSubtypes().AddSubtype(SubtypeCollection.SPHINX);
		return horizonScholar;
	}

	public static Creature Omenspeaker(Player owner){
		Creature omenspeaker=Creature.NewCreature(owner, "Omenspeaker", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 1, 3);
		omenspeaker.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(omenspeaker)}, new MagicEffect[]{new MagicEffectScryX(owner, 2)}));
		omenspeaker.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		omenspeaker.GetSubtypes().AddSubtype(SubtypeCollection.WIZARD);
		return omenspeaker;
	}

	public static Creature TritonShorethief(Player owner){
		Creature tritonShorethief=Creature.NewCreature(owner, "Triton Shorethief", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE}), 1, 2);
		tritonShorethief.GetSubtypes().AddSubtype(SubtypeCollection.MERFOLK);
		tritonShorethief.GetSubtypes().AddSubtype(SubtypeCollection.ROGUE);
		return tritonShorethief;
	}
	// ENDREGION BLUE
	// REGION WHITE
	public static Creature DecoratedGriffin(Player owner){
		Creature decoratedGriffin=Creature.NewCreature(owner, "Decorated Griffin", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS,ManaCost.COLOR_FLAG_COLORLESS,ManaCost.COLOR_FLAG_COLORLESS,ManaCost.COLOR_FLAG_COLORLESS,ManaCost.COLOR_FLAG_WHITE}), 2, 3);
		decoratedGriffin.GetKeywords().AddFlying();
		MagicEffect effect=MagicEffectAddDamagePreventorToITargetables.Create(owner, new DamagePreventor(1, DamagePreventor.DAMAGE_TYPE_FLAG_COMBAT, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY)), 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER, MagicEffect.Duration.UNTIL_END_OF_TURN);
		effect.TargetData.SetLockedTarget(0, owner);
		decoratedGriffin.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), null, new MagicEffect[]{effect}));
		decoratedGriffin.GetSubtypes().AddSubtype(SubtypeCollection.GRIFFIN);
		return decoratedGriffin;
	}

	public static Creature SilentArtisan(Player owner){
		Creature silentArtisan=Creature.NewCreature(owner, "Silent Artisan", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS,ManaCost.COLOR_FLAG_COLORLESS,ManaCost.COLOR_FLAG_COLORLESS,ManaCost.COLOR_FLAG_WHITE,ManaCost.COLOR_FLAG_WHITE}), 3, 5);
		silentArtisan.GetSubtypes().AddSubtype(SubtypeCollection.GIANT);
		return silentArtisan;
	}

	public static Creature TravelingPhilosopher(Player owner){
		Creature travelingPhilosopher=Creature.NewCreature(owner, "Traveling Philosopher", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS,ManaCost.COLOR_FLAG_WHITE}), 2, 2);
		travelingPhilosopher.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		travelingPhilosopher.GetSubtypes().AddSubtype(SubtypeCollection.ADVISOR);
		return travelingPhilosopher;
	}

	public static Creature YokedOx(Player owner){
		Creature yokedOx=Creature.NewCreature(owner, "Yoked Ox", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}), 0, 4);
		yokedOx.GetSubtypes().AddSubtype(SubtypeCollection.OX);
		return yokedOx;
	}
	// ENDREGION WHITE
	// REGION BLACK
	public static Creature FelhideMinotaur(Player owner){
		Creature felhideMinotaur=Creature.NewCreature(owner, "Felhide Minotaur", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 2, 3);
		felhideMinotaur.GetSubtypes().AddSubtype(SubtypeCollection.MINOTAUR);
		return felhideMinotaur;
	}

	public static Creature InsatiableHarpy(Player owner){
		Creature insatiableHarpy=Creature.NewCreature(owner, "Insatiable Harpy", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK, ManaCost.COLOR_FLAG_BLACK}), 2, 2);
		insatiableHarpy.GetKeywords().AddLifelink();
		insatiableHarpy.GetKeywords().AddFlying();
		insatiableHarpy.GetSubtypes().AddSubtype(SubtypeCollection.HARPY);
		return insatiableHarpy;
	}
	// ENDREGION BLACK
	// REGION COLORLESS
	public static Creature BronzeSable(Player owner){
		Creature bronzeSable=Creature.NewArtifactCreature(owner, "Bronze Sable", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), 2, 1);
		bronzeSable.GetSubtypes().AddSubtype(SubtypeCollection.SABLE);
		return bronzeSable;
	}

	public static Creature GuardiansOfMeletis(Player owner){
		Creature guardiansOfMeletis=Creature.NewArtifactCreature(owner, "Guardians of Meletis", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), 0, 6);
		guardiansOfMeletis.GetKeywords().AddDefender();
		guardiansOfMeletis.GetSubtypes().AddSubtype(SubtypeCollection.GOLEM);
		return guardiansOfMeletis;
	}
	// ENDREGION COLORLESS
// ENDREGION CREATURES
	
// REGION INSTANTS
	// REGION RED
	public static Sorcery Demolish(Player owner){
		return new Sorcery(owner, "Demolish", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{MagicEffectDestroyTargetPermanents.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_ARTIFACT|TargetInfo.TARGET_TYPE_FLAG_LAND)});
	}

	public static Instant TitansStrength(Player owner){
		return new Instant(owner, "Titan's Strength", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 3, 1, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN), new MagicEffectScryX(owner, 1)});
	}
	// ENDREGION RED
	// REGION GREEN
	// ENDREGION GREEN
	// REGION BLUE
	public static Instant LostInALabyrinth(Player owner){
		return new Instant(owner, "Lost in a Labyrinth", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, -3, 0, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN), new MagicEffectScryX(owner, 1)});
	}
	// ENDREGION BLUE
	// REGION WHITE
	public static Instant BattlewiseValor(Player owner){
		return new Instant(owner, "Battlewise Valor", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 2, 2, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN), new MagicEffectScryX(owner, 1)});
	}

	public static Instant DauntlessOnslaught(Player owner){
		return new Instant(owner, "Dauntless Onslaught", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 2, 2, 0, 2, MagicEffect.Duration.UNTIL_END_OF_TURN)});
	}

	public static Instant RayOfDissolution(Player owner){
		return new Instant(owner, "Ray of Dissolution", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{MagicEffectDestroyTargetPermanents.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_ENCHANTMENT), new MagicEffectGainLife(owner, 3)});
	}
	// ENDREGION WHITE
	// REGION BLACK
	public static Instant LashOfTheWhip(Player owner){
		return new Instant(owner, "Lash of the Whip", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, -4, -4, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN)});
	}
	// ENDREGION BLACK
	// REGION COLORLESS
	// ENDREGION COLORLESS
// ENDREGION INSTANTS
	
// REGION ENCHANTMENTS
	// REGION RED
	public static Enchantment MessengersSpeed(Player owner){
		return Enchantment.NewEnchantmentAura(owner, "Messenger's Speed", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectGrantTrampleToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.NA), MagicEffectGrantHasteToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.NA)});
	}	
	// ENDREGION RED
	// REGION GREEN
	public static Enchantment FeralInvocation(Player owner){
		Enchantment feralInvocation=Enchantment.NewEnchantmentAura(owner, "Feral Invocation", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 2, 2, 1, 1, MagicEffect.Duration.NA)});
		feralInvocation.GetKeywords().AddFlash();
		return feralInvocation;
	}
	// ENDREGION GREEN
	// REGION BLUE
	public static Enchantment AqueousForm(Player owner){
		Enchantment aqueousForm=Enchantment.NewEnchantmentAura(owner, "Aqueous Form", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectGrantUnblockableToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.NA), MagicEffectGrantTriggerAttacksToTargetCreature.Create(owner, new MagicEffect[]{new MagicEffectScryX(owner, 1)}, 1, 1, MagicEffect.Duration.NA)});
		return aqueousForm;
	}
	// ENDREGION BLUE
	// REGION WHITE
	public static Enchantment ChosenByHeliod(Player owner){
		Enchantment chosenByHeliod=Enchantment.NewEnchantmentAura(owner, "Chosen by Heliod", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 0, 2, 1, 1, MagicEffect.Duration.NA)});
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectDrawCardsForTargetPlayer.Create(owner, 1, 1, 1);
		effects[0].TargetData.SetLockedTarget(0, owner);
		chosenByHeliod.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(chosenByHeliod)}, effects));
		return chosenByHeliod;
	}
	// ENDREGION WHITE
	// REGION BLACK
	public static Enchantment Scourgemark(Player owner){
		Enchantment scourgemark=Enchantment.NewEnchantmentAura(owner, "Scourgemark", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 1, 0, 1, 1, MagicEffect.Duration.NA)});
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectDrawCardsForTargetPlayer.Create(owner, 1, 1, 1);
		effects[0].TargetData.SetLockedTarget(0, owner);
		scourgemark.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(scourgemark)}, effects));
		return scourgemark;
	}
	// ENDREGION BLACK
	// REGION COLORLESS
	// ENDREGION COLORLESS
// ENDREGION ENCHANTMENTS
}
