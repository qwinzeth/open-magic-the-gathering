package Matt.Stowe.MTG.Cards.Sets;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.SpecificEffects.*;
import Matt.Stowe.MTG.Mechanics.ContinuousAbilities.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;

public class Core2015{
	public static String[] MythicRares=new String[]{
		"Ajani Steadfast", "Chandra, Pyromaster", "Garruk, Apex Predator", "Jace, the Living Guildpact", "Liliana Vess", "Nissa, WorldWaker",
		"Perilous Vault", "Sliver Hivelord", "Soul of Innistrad", "Soul of New Phyrexia", "Soul of Ravnica", "Soul of Shandalar", "Soul of Theros",
		"Soul of Zendikar", "The Chain Veil"
	};
	public static String[] Rares=new String[]{"Aegis Angel", "Aetherspouts", "Aggressive Mining", "Avacyn, Guardian Angel", "Avarice Amulet", "Battlefield Forge", "Burning Anger", "Caves of Koilos", "Chasm Skulker", "Chief Engineer", "Chord of Calling", "Crucible of Fire", "Cruel Sadist", "Genesis Hydra", "Goblin Kaboomist", "Goblin Rabblemaster", "Grindclock", "Haunted Plate Mail", "Hoarding Dragon", "Hornet Nest", "Hornet Queen", "Hushwing Gryff", "In Garruk's Wake", "Indulgent Tormentor", "Jalira, Master Polymorphist", "Kalonian Twingrove", "Kurkesh, Onakke Ancient", "Life's Legacy", "Llanowar Wastes", "Mahamoti Djinn", "Mass Calcify", "Master of Predicaments", "Mercurial Pretender", "Necromancer's Stockpile", "Nightmare", "Ob Nixilis, Unshackled", "Obelisk of Urd", "Phyrexian Revoker", "Phytotitan", "Polymorphist's Jest", "Preeminent Captain", "Resolute Archangel", "Return to the Ranks", "Scuttling Doom Engine", "Shield of the Avatar", "Shivan Dragon", "Shivan Reef", "Siege Dragon", "Sliver Hive", "Spectra Ward", "Spirit Bonds", "Stain the Mind", "Stormtide Leviathan", "Terra Stomper", "Urborg, Tomb of Yawgmoth", "Waste Not", "Yavimaya Coast", "Yisan, the Wanderer Bard"};
	public static String[] Uncommons=new String[]{"Act on Impulse", "Ajani's Pridemate", "Altac Bloodseeker", "Ancient Silverback", "Back to Nature", "Battle Mastery", "Belligerent Sliver", "Blood Host", "Boonweaver Giant", "Brawler's Plate", "Brood Keeper", "Caustic Tar", "Circle of Flame", "Cone of Flame", "Congregate", "Constricting Sliver", "Darksteel Citadel", "Dauntless River Marshal", "Devouring Light", "Diffusion Sliver", "Dissipate", "Endless Obedience", "Ensoul Artifact", "Feast on the Fallen", "Feral Incarnation", "First Response", "Frenzied Goblin", "Furnace Whelp", "Gargoyle Sentinel", "Garruk's Packleader", "Gather Courage", "Geist of the Moors", "Gravedigger", "Heat Ray", "Hot Soup", "Illusory Angel", "Into the Void", "Jace's Ingenuity", "Jorubai Murk Lurker", "Juggernaut", "Kapsho Kitefins", "Kird Chieftain", "Leeching Sliver", "Meteorite", "Might Makes Right", "Military Intelligence", "Necrogen Scudder", "Nightfire Giant", "Nissa's Expedition", "Overwhelm", "Paragon of Eternal Wilds", "Paragon of Fierce Defiance", "Paragon of Gathering Mists", "Paragon of New Dawns", "Paragon of Open Graves", "Profane Memento", "Quickling", "Reclamation Sage", "Restock", "Roaring Primadox", "Rogue's Gloves", "Sacred Armory", "Sengir Vampire", "Seraph of the Masses", "Serra Angel", "Shrapnel Blast", "Stab Wound", "Staff of the Death Magus", "Staff of the Flame Magus", "Staff of the Mind Magus", "Staff of the Sun Magus", "Staff of the Wild Magus", "Stoke the Flames", "Sunblade Elf", "Tormod's Crypt", "Turn to Frog", "Ulcerate", "Venom Sliver", "Wall of Essence", "Wall of Frost", "Wall of Limbs", "Wall of Mulch", "Warden of the Beyond", "Xathrid Slyblade"};
	public static String[] Commons=new String[]{"Forest", "Island", "Mountain", "Plains", "Swamp", "Accursed Spirit", "Aeronaut Tinkerer", "Amphin Pathmage", "Black Cat", "Blastfire Bolt", "Borderland Marauder",  "Bronze Sable", "Cancel", "Carnivorous Moss-Beast", "Carrion Crow", "Centaur Courser", "Charging Rhino", "Child of Night", "Chronostutter", "Clear a Path", "Coral Barrier", "Covenant of Blood", "Crippling Blight", "Crowd's Favor", "Divination", "Divine Favor", "Divine Verdict", "Elvish Mystic", "Encrust", "Ephemeral Shields", "Eternal Thirst", "Evolving Wilds", "Festergloom", "Flesh to Dust", "Forge Devil", "Foundry Street Denizen", "Frost Lynx", "Fugitive Wizard", "Generator Servant", "Glacial Crasher", "Goblin Roughrider", "Hammerhand", "Heliod's Pilgrim", "Hunt the Weak", "Hunter's Ambush", "Hydrosurge", "Inferno Fist", "Inspired Charge", "Invasive Species", "Invisibility", "Kinsbaile Skirmisher", "Krenko's Enforcer", "Lava Axe", "Lightning Strike", "Living Totem", "Marked by Honor", "Meditation Puzzle", "Midnight Guard", "Mind Rot", "Mind Sculpt", "Miner's Bane", "Naturalize", "Necrobite", "Necromancer's Assistant", "Negate", "Netcaster Spider", "Nimbus of the Isles", "Oppressive Rays", "Oreskos Swiftclaw", "Ornithopter", "Peel from Reality", "Pillar of Light", "Plummet", "Radiant Fountain", "Raise the Alarm", "Ranger's Guile", "Razorfoot Griffin", "Research Assistant", "Rotfeaster Maggot", "Rummaging Goblin", "Runeclaw Bear", "Sanctified Charge", "Satyr Wayfinder", "Scrapyard Mongrel", "Seismic Strike", "Selfless Cathar", "Shadowcloak Vampire", "Shaman of Spring", "Siege Wurm", "Sign in Blood", "Solemn Offering", "Soulmender", "Statute of Denial", "Sungrace Pegasus", "Thundering Giant", "Tireless Missionaries", "Titanic Growth", "Torch Fiend", "Triplicate Spirits", "Typhoid Rats", "Tyrant's Machine", "Undergrowth Scavenger", "Unmake the Graves", "Verdant Haven", "Vineweft", "Void Snare", "Walking Corpse", "Wall of Fire", "Welkin Tern", "Will-Forged Golem", "Witch's Familiar", "Zof Shade"};

// REGION CREATURES
	// REGION RED
	public static Creature BorderlandMarauder(Player owner){
		Creature borderlandMarauder=Creature.NewCreature(owner, "Borderland Marauder", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 1, 2);
		MagicEffect[] triggerEffects=new MagicEffect[1];
		triggerEffects[0]=MagicEffectAddPTToTargetCreature.Create(owner, 2, 0, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		triggerEffects[0].TargetData.SetLockedTarget(0, borderlandMarauder);
		borderlandMarauder.AddAttacksTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(borderlandMarauder)}, triggerEffects));
		borderlandMarauder.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		borderlandMarauder.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return borderlandMarauder;
	}

	public static Creature ForgeDevil(Player owner){
		Creature forgeDevil=Creature.NewCreature(owner, "Forge Devil", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), 1, 1);
		forgeDevil.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(forgeDevil)}, new MagicEffect[]{MagicEffectDealDamageToTarget.Create(owner, forgeDevil, 1, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), new MagicEffectLoseLife(owner, 1)}));
		forgeDevil.GetSubtypes().AddSubtype(SubtypeCollection.DEVIL);
		return forgeDevil;
	}

	public static Creature FoundryStreetDenizen(Player owner){
		Creature foundryStreetDenizen=Creature.NewCreature(owner, "Foundry Street Denizen", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), 1, 1);
		
		TriggerConditionMatchesTypes condition=new TriggerConditionMatchesTypes(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		condition.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_RED);
		condition.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		MagicEffect effect=MagicEffectAddPTToTargetCreature.Create(owner, 1, 0, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		effect.TargetData.SetLockedTarget(0, foundryStreetDenizen);
		
		foundryStreetDenizen.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionNotEqualsCard(foundryStreetDenizen), condition}, new MagicEffect[]{effect}));
		foundryStreetDenizen.GetSubtypes().AddSubtype(SubtypeCollection.GOBLIN);
		foundryStreetDenizen.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return foundryStreetDenizen;
	}

	public static Creature FurnaceWhelp(Player owner){
		Creature furnaceWhelp=Creature.NewCreature(owner, "Furnace Whelp", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), 2, 2);
		furnaceWhelp.GetKeywords().AddFlying();
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectAddPTToTargetCreature.Create(owner, 1, 0, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		effects[0].TargetData.SetLockedTarget(0, furnaceWhelp);
		furnaceWhelp.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), null, effects));
		furnaceWhelp.GetSubtypes().AddSubtype(SubtypeCollection.DRAGON);
		return furnaceWhelp;
	}

	public static Creature GoblinRoughrider(Player owner){
		Creature goblinRoughrider=Creature.NewCreature(owner, "Goblin Roughrider", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 3, 2);
		goblinRoughrider.GetSubtypes().AddSubtype(SubtypeCollection.GOBLIN);
		goblinRoughrider.GetSubtypes().AddSubtype(SubtypeCollection.KNIGHT);
		return goblinRoughrider;
	}

	public static Creature KirdChieftain(Player owner){
		Creature kirdChieftain=Creature.NewCreature(owner, "Kird Chieftain", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 3, 3);

		MagicEffect effect=MagicEffectAddPTToTargetCreature.Create(owner, 1, 1, 1, 1, MagicEffect.Duration.NA);
		effect.TargetData.SetLockedTarget(0, kirdChieftain);
		
		kirdChieftain.AddContinousEffect(new ContinuousAbility(new IContinuousCondition[]{new ContinuousConditionPlayerControlsCard("Forest")}, new MagicEffect[]{effect}));
		
		MagicEffect[] abilityEffects=new MagicEffect[2];
		abilityEffects[0]=MagicEffectAddPTToTargetCreature.Create(owner, 2, 2, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		abilityEffects[1]=MagicEffectGrantTrampleToTargetCreature.CreateAutoset(owner, abilityEffects[0].TargetData, MagicEffect.Duration.UNTIL_END_OF_TURN);
		
		kirdChieftain.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), null, abilityEffects));
		kirdChieftain.GetSubtypes().AddSubtype(SubtypeCollection.APE);
		return kirdChieftain;
	}

	public static Creature MinersBane(Player owner){
		Creature minersBane=Creature.NewCreature(owner, "Miner's Bane", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), 6, 3);
		MagicEffect[] effects=new MagicEffect[2];
		effects[0]=MagicEffectAddPTToTargetCreature.Create(owner, 1, 0, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		effects[0].TargetData.SetLockedTarget(0, minersBane);
		effects[1]=MagicEffectGrantTrampleToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		effects[1].TargetData.SetLockedTarget(0, minersBane);
		minersBane.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), null, effects));
		minersBane.GetSubtypes().AddSubtype(SubtypeCollection.ELEMENTAL);
		return minersBane;
	}

	public static Creature ParagonOfFierceDefiance(Player owner){
		Creature paragonOfFierceDefiance=Creature.NewCreature(owner, "Paragon of Fierce Defiance", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 2, 2);
		
		MagicEffect abilityCost=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		abilityCost.TargetData.SetLockedTarget(0, paragonOfFierceDefiance);

		TargetInfo[] invalidTargets=new TargetInfo[1];
		invalidTargets[0]=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		invalidTargets[0].SetLockedTarget(0, paragonOfFierceDefiance);

		MagicEffect abilityEffect=MagicEffectGrantHasteToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN, invalidTargets);
		abilityEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_RED);
		abilityEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		paragonOfFierceDefiance.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{abilityCost}, new MagicEffect[]{abilityEffect}));
		
		MagicEffect continuousEffect=MagicEffectAddPTToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, 1, 1, MagicEffect.Duration.NA, invalidTargets);
		continuousEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_RED);
		continuousEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		paragonOfFierceDefiance.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{continuousEffect}));
		
		paragonOfFierceDefiance.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		paragonOfFierceDefiance.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return paragonOfFierceDefiance;
	}

	public static Creature ScrapyardMongrel(Player owner){
		Creature scrapyardMongrel=Creature.NewCreature(owner, "Scrapyard Mongrel", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 3, 3);
		
		MagicEffect[] abilityEffects=new MagicEffect[2];
		abilityEffects[0]=MagicEffectAddPTToTargetCreature.Create(owner, 2, 0, 1, 1, MagicEffect.Duration.NA);
		abilityEffects[0].TargetData.SetLockedTarget(0, scrapyardMongrel);
		abilityEffects[1]=MagicEffectGrantTrampleToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.NA);
		abilityEffects[1].TargetData.SetLockedTarget(0, scrapyardMongrel);
		scrapyardMongrel.AddContinousEffect(new ContinuousAbility(new IContinuousCondition[]{new ContinuousConditionPlayerControlsType(new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_ARTIFACT))}, abilityEffects));
		
		scrapyardMongrel.GetSubtypes().AddSubtype(SubtypeCollection.HOUND);
		return scrapyardMongrel;
	}

	public static Creature ShivanDragon(Player owner){
		Creature shivanDragon=Creature.NewCreature(owner, "Shivan Dragon", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), 5, 5);
		shivanDragon.GetKeywords().AddFlying();
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectAddPTToTargetCreature.Create(owner, 1, 0, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		effects[0].TargetData.SetLockedTarget(0, shivanDragon);
		shivanDragon.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), null, effects));
		shivanDragon.GetSubtypes().AddSubtype(SubtypeCollection.DRAGON);
		return shivanDragon;
	}

	public static Creature SiegeDragon(Player owner){
		Creature siegeDragon=Creature.NewCreature(owner, "Siege Dragon", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), 5, 5);
		siegeDragon.GetKeywords().AddFlying();
	
		//TODO: Set defending player as part of TargetInfo for what to destroy
	
		siegeDragon.GetSubtypes().AddSubtype(SubtypeCollection.DRAGON);
		return siegeDragon;
	}

	public static Creature ThunderingGiant(Player owner){
		Creature thunderingGiant=Creature.NewCreature(owner, "Thundering Giant", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), 4, 3);
		thunderingGiant.GetKeywords().AddHaste();
		thunderingGiant.GetSubtypes().AddSubtype(SubtypeCollection.GIANT);
		return thunderingGiant;
	}

	public static Creature TorchFiend(Player owner){
		Creature torchFiend=Creature.NewCreature(owner, "Torch Fiend", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 2, 1);

		torchFiend.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{new MagicEffectDiscardCard(owner, torchFiend)}, new MagicEffect[]{MagicEffectDestroyTargetPermanents.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_ARTIFACT)}));

		torchFiend.GetSubtypes().AddSubtype(SubtypeCollection.DEVIL);
		return torchFiend;
	}

	public static Creature WallOfFire(Player owner){
		Creature wallOfFire=Creature.NewCreature(owner, "Wall of Fire", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), 0, 5);
		wallOfFire.GetKeywords().AddDefender();
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectAddPTToTargetCreature.Create(owner, 1, 0, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		effects[0].TargetData.SetLockedTarget(0, wallOfFire);
		wallOfFire.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), null, effects));
		wallOfFire.GetSubtypes().AddSubtype(SubtypeCollection.WALL);
		return wallOfFire;
	}
	// ENDREGION RED
	// REGION GREEN
	public static Creature CarnivorusMossBeast(Player owner){
		Creature carnivorusMossBeast=Creature.NewCreature(owner, "Carnivorus Moss-Beast", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), 4, 5);
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectAddPTCountersToTargetCreature.Create(owner, 1, 1, 1);
		effects[0].TargetData.SetLockedTarget(0, carnivorusMossBeast);
		carnivorusMossBeast.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), null, effects));
		
		carnivorusMossBeast.GetSubtypes().AddSubtype(SubtypeCollection.PLANT);
		carnivorusMossBeast.GetSubtypes().AddSubtype(SubtypeCollection.ELEMENTAL);
		carnivorusMossBeast.GetSubtypes().AddSubtype(SubtypeCollection.BEAST);
		return carnivorusMossBeast;
	}

	public static Creature CentaurCourser(Player owner){
		Creature centaurCourser=Creature.NewCreature(owner, "Centaur Courser", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 3, 3);
		
		centaurCourser.GetSubtypes().AddSubtype(SubtypeCollection.CENTAUR);
		centaurCourser.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return centaurCourser;
	}

	public static Creature ElvishMystic(Player owner){
		Creature elvishMystic=Creature.NewCreature(owner, "Elvish Mystic", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_GREEN}), 1, 1);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, elvishMystic);
		elvishMystic.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 0, 1, 0, 0, 0, 0)}));
		
		elvishMystic.GetSubtypes().AddSubtype(SubtypeCollection.ELF);
		elvishMystic.GetSubtypes().AddSubtype(SubtypeCollection.DRUID);
		return elvishMystic;
	}

	public static Creature GarruksPackleader(Player owner){
		Creature garruksPackleader=Creature.NewCreature(owner, "Garruk's Packleader", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 4, 4);
		
		TriggerConditionMatchesTypes condition=new TriggerConditionMatchesTypes(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		condition.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		condition.TargetData.SetPowerMin(3);

		MagicEffect effect=MagicEffectDrawCardsForTargetPlayer.Create(owner, 1, 1, 1);
		effect.TargetData.SetLockedTarget(0, owner);
		effect.SetOptional();
		
		garruksPackleader.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionNotEqualsCard(garruksPackleader), condition}, new MagicEffect[]{effect}));
		
		garruksPackleader.GetSubtypes().AddSubtype(SubtypeCollection.BEAST);
		return garruksPackleader;
	}

	public static Creature HornetQueen(Player owner){
		Creature hornetQueen=Creature.NewCreature(owner, "Hornet Queen", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), 2, 2);
		hornetQueen.GetKeywords().AddFlying();
		hornetQueen.GetKeywords().AddDeathtouch();
		
		hornetQueen.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(hornetQueen)}, new MagicEffect[]{new MagicEffectPlayToken(owner, GreenInsectWithFlyingDeathtouchP1T1(owner), 4)}));
		
		hornetQueen.GetSubtypes().AddSubtype(SubtypeCollection.INSECT);
		return hornetQueen;
	}

	public static Creature KalonianTwingrove(Player owner){
		Creature kalonianTwingrove=Creature.NewCreature(owner, "Kalonian Twingrove", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 0, 0);
		
		TargetInfo cardRequirements=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_BASICLAND);
		cardRequirements.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		cardRequirements.AddORName("Forest");
		MagicEffect pteffect=MagicEffectAddPTToTargetCreatureForeachPermanent.Create(owner, 1, 1, 1, 1, MagicEffect.Duration.NA, cardRequirements);
		pteffect.TargetData.SetLockedTarget(0, kalonianTwingrove);
		kalonianTwingrove.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{pteffect}));
		
		kalonianTwingrove.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(kalonianTwingrove)}, new MagicEffect[]{new MagicEffectPlayToken(owner, GreenTreefolkWarriorWithForestsPT(owner), 1)}));
		
		kalonianTwingrove.GetSubtypes().AddSubtype(SubtypeCollection.TREEFOLK);
		kalonianTwingrove.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return kalonianTwingrove;
	}

	public static Creature LivingTotem(Player owner){
		Creature livingTotem=Creature.NewCreature(owner, "Living Totem", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 2, 3);
		TargetInfo[] invalidTargets=new TargetInfo[1];
		invalidTargets[0]=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		invalidTargets[0].SetLockedTarget(0, livingTotem);
		livingTotem.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(livingTotem)}, new MagicEffect[]{MagicEffectAddPTCountersToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, 1, invalidTargets)}));
		livingTotem.GetKeywords().AddConvoke();
		
		livingTotem.GetSubtypes().AddSubtype(SubtypeCollection.PLANT);
		livingTotem.GetSubtypes().AddSubtype(SubtypeCollection.ELEMENTAL);
		return livingTotem;
	}

	public static Creature ParagonOfEternalWilds(Player owner){
		Creature paragonOfEternalWilds=Creature.NewCreature(owner, "Paragon of Eternal Wilds", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 2, 2);
		
		MagicEffect abilityCost=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		abilityCost.TargetData.SetLockedTarget(0, paragonOfEternalWilds);

		TargetInfo[] invalidTargets=new TargetInfo[1];
		invalidTargets[0]=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		invalidTargets[0].SetLockedTarget(0, paragonOfEternalWilds);

		MagicEffect abilityEffect=MagicEffectGrantTrampleToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN, invalidTargets);
		abilityEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_GREEN);
		abilityEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		paragonOfEternalWilds.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{abilityCost}, new MagicEffect[]{abilityEffect}));
		
		MagicEffect continuousEffect=MagicEffectAddPTToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, 1, 1, MagicEffect.Duration.NA, invalidTargets);
		continuousEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_GREEN);
		continuousEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		paragonOfEternalWilds.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{continuousEffect}));
		
		paragonOfEternalWilds.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		paragonOfEternalWilds.GetSubtypes().AddSubtype(SubtypeCollection.DRUID);
		return paragonOfEternalWilds;
	}

	public static Creature ReclamationSage(Player owner){
		Creature reclamationSage=Creature.NewCreature(owner, "Reclamation Sage", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 2, 1);
		reclamationSage.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(reclamationSage)}, new MagicEffect[]{MagicEffectDestroyTargetPermanents.Create(owner, 0, 1, TargetInfo.TARGET_TYPE_FLAG_ENCHANTMENT|TargetInfo.TARGET_TYPE_FLAG_ARTIFACT)}));
		
		reclamationSage.GetSubtypes().AddSubtype(SubtypeCollection.ELF);
		reclamationSage.GetSubtypes().AddSubtype(SubtypeCollection.SHAMAN);
		return reclamationSage;
	}

	public static Creature RuneclawBear(Player owner){
		Creature runeclawBear=Creature.NewCreature(owner, "Runeclaw Bear", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 2, 2);
		
		runeclawBear.GetSubtypes().AddSubtype(SubtypeCollection.BEAR);
		return runeclawBear;
	}

	public static Creature ShamanOfSpring(Player owner){
		Creature shamanOfSpring=Creature.NewCreature(owner, "Shaman of Spring", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 2, 2);
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectDrawCardsForTargetPlayer.Create(owner, 1, 1, 1);
		effects[0].TargetData.SetLockedTarget(0, owner);
		shamanOfSpring.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(shamanOfSpring)}, effects));
		
		shamanOfSpring.GetSubtypes().AddSubtype(SubtypeCollection.ELF);
		shamanOfSpring.GetSubtypes().AddSubtype(SubtypeCollection.SHAMAN);
		return shamanOfSpring;
	}

	public static Creature SiegeWurm(Player owner){
		Creature siegeWurm=Creature.NewCreature(owner, "Siege Wurm", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), 5, 5);
		siegeWurm.GetKeywords().AddConvoke();
		siegeWurm.GetKeywords().AddTrample();
		
		siegeWurm.GetSubtypes().AddSubtype(SubtypeCollection.WURM);
		return siegeWurm;
	}

	public static Creature SoulOfZendikar(Player owner){
		Creature soulOfZendikar=Creature.NewCreature(owner, "Soul of Zendikar", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), 6, 6);
		
		soulOfZendikar.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), null, new MagicEffect[]{new MagicEffectPlayToken(owner, GreenBeastP3T3(owner), 1)}));
		
		MagicEffect exileCost=MagicEffectExileTargetCards.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.NA);
		exileCost.TargetData.SetLockedTarget(0, soulOfZendikar);
		
		MagicActivatedAbility graveyardAbility=new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{exileCost}, new MagicEffect[]{new MagicEffectPlayToken(owner, GreenBeastP3T3(owner), 1)});
		graveyardAbility.SetSourceZones(new ZoneOptions(ZoneOptions.GRAVEYARD));
		soulOfZendikar.AddActivatedAbility(graveyardAbility);

		soulOfZendikar.GetKeywords().AddReach();
		soulOfZendikar.GetSubtypes().AddSubtype(SubtypeCollection.AVATAR);
		return soulOfZendikar;
	}

	public static Creature SunbladeElf(Player owner){
		Creature sunbladeElf=Creature.NewCreature(owner, "Sunblade Elf", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_GREEN}), 1, 1);

		MagicEffect effect=MagicEffectAddPTToTargetCreature.Create(owner, 1, 1, 1, 1, MagicEffect.Duration.NA);
		effect.TargetData.SetLockedTarget(0, sunbladeElf);
		
		sunbladeElf.AddContinousEffect(new ContinuousAbility(new IContinuousCondition[]{new ContinuousConditionPlayerControlsCard("Plains")}, new MagicEffect[]{effect}));
		
		TargetInfo affectedCreatureInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		affectedCreatureInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		sunbladeElf.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), null, new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, 1, 1, affectedCreatureInfo, MagicEffect.Duration.UNTIL_END_OF_TURN))}));

		sunbladeElf.GetSubtypes().AddSubtype(SubtypeCollection.ELF);
		sunbladeElf.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return sunbladeElf;
	}

	public static Creature VenomSliver(Player owner){
		Creature venomSliver=Creature.NewCreature(owner, "Venom Sliver", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 1, 1);
			
		MagicEffect grantDeathtouch=MagicEffectGrantDeathtouchToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.NA);
		grantDeathtouch.TargetData.AddORSubtype(SubtypeCollection.SLIVER);
		grantDeathtouch.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		venomSliver.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{grantDeathtouch}));

		venomSliver.GetSubtypes().AddSubtype(SubtypeCollection.SLIVER);
		return venomSliver;
	}

	public static Creature WallOfMulch(Player owner){
		Creature wallOfMulch=Creature.NewCreature(owner, "Wall of Mulch", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 0, 4);

		TargetInfo singleWallTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		singleWallTargetInfo.AddORSubtype(SubtypeCollection.WALL);
		singleWallTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		MagicEffect draweffect=MagicEffectDrawCardsForTargetPlayer.Create(owner, 1, 1, 1);
		draweffect.TargetData.SetLockedTarget(0, owner);

		wallOfMulch.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{new MagicEffectDiscardTargetCard(owner, singleWallTargetInfo)}, new MagicEffect[]{draweffect}));
		
		wallOfMulch.GetKeywords().AddDefender();
		wallOfMulch.GetSubtypes().AddSubtype(SubtypeCollection.WALL);
		return wallOfMulch;
	}
	// ENDREGION GREEN
	// REGION BLUE
	public static Creature AeronautTinkerer(Player owner){
		Creature aeronautTinkerer=Creature.NewCreature(owner, "Aeronaut Tinkerer", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 2, 3);

		MagicEffect effect=MagicEffectGrantFlyingToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.NA);
		effect.TargetData.SetLockedTarget(0, aeronautTinkerer);
		aeronautTinkerer.AddContinousEffect(new ContinuousAbility(new IContinuousCondition[]{new ContinuousConditionPlayerControlsType(new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_ARTIFACT))}, new MagicEffect[]{effect}));

		aeronautTinkerer.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		aeronautTinkerer.GetSubtypes().AddSubtype(SubtypeCollection.ARTIFICER);
		return aeronautTinkerer;
	}

	public static Creature AmphinPathmage(Player owner){
		Creature amphinPathmage=Creature.NewCreature(owner, "Amphin Pathmage", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 3, 2);

		amphinPathmage.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), null, new MagicEffect[]{MagicEffectGrantUnblockableToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN)}));

		amphinPathmage.GetSubtypes().AddSubtype(SubtypeCollection.SALAMANDER);
		amphinPathmage.GetSubtypes().AddSubtype(SubtypeCollection.WIZARD);
		return amphinPathmage;
	}

	public static Creature FugitiveWizard(Player owner){
		Creature fugitiveWizard=Creature.NewCreature(owner, "Fugitive Wizard", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE}), 1, 1);
		fugitiveWizard.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		fugitiveWizard.GetSubtypes().AddSubtype(SubtypeCollection.WIZARD);
		return fugitiveWizard;
	}

	public static Creature JorubaiMurkLurker(Player owner){
		Creature jorubaiMurkLurker=Creature.NewCreature(owner, "Jorubai Murk Lurker", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 1, 3);

		MagicEffect effect=MagicEffectAddPTToTargetCreature.Create(owner, 1, 1, 1, 1, MagicEffect.Duration.NA);
		effect.TargetData.SetLockedTarget(0, jorubaiMurkLurker);
		
		jorubaiMurkLurker.AddContinousEffect(new ContinuousAbility(new IContinuousCondition[]{new ContinuousConditionPlayerControlsCard("Swamp")}, new MagicEffect[]{effect}));
		jorubaiMurkLurker.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), null, new MagicEffect[]{MagicEffectGrantLifelinkToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN)}));

		jorubaiMurkLurker.GetSubtypes().AddSubtype(SubtypeCollection.LEECH);
		return jorubaiMurkLurker;
	}

	public static Creature KapshoKitefins(Player owner){
		Creature kapshoKitefins=Creature.NewCreature(owner, "Kapsho Kitefins", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_BLUE}), 3, 3);

		TriggerConditionMatchesTypes entersCondition=new TriggerConditionMatchesTypes(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		entersCondition.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		MagicEffect tapeffect=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		tapeffect.TargetData.TargetMustNotBeControlledByIndex(owner.GetIndex(), owner.GetName());

		kapshoKitefins.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{entersCondition}, new MagicEffect[]{tapeffect}));

		kapshoKitefins.GetKeywords().AddFlying();
		kapshoKitefins.GetSubtypes().AddSubtype(SubtypeCollection.FISH);
		return kapshoKitefins;
	}

	public static Creature MahamotiDjinn(Player owner){
		Creature mahamotiDjinn=Creature.NewCreature(owner, "Mahamoti Djinn", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_BLUE}), 5, 6);
		mahamotiDjinn.GetKeywords().AddFlying();
		mahamotiDjinn.GetSubtypes().AddSubtype(SubtypeCollection.DJINN);
		return mahamotiDjinn;
	}

	public static Creature NimbusOfTheIsles(Player owner){
		Creature nimbusOfTheIsles=Creature.NewCreature(owner, "Nimbus of the Isles", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 3, 3);
		nimbusOfTheIsles.GetKeywords().AddFlying();
		nimbusOfTheIsles.GetSubtypes().AddSubtype(SubtypeCollection.ELEMENTAL);
		return nimbusOfTheIsles;
	}

	public static Creature ParagonOfGatheringMists(Player owner){
		Creature paragonOfGatheringMists=Creature.NewCreature(owner, "Paragon of Gathering Mists", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 2, 2);
		
		MagicEffect abilityCost=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		abilityCost.TargetData.SetLockedTarget(0, paragonOfGatheringMists);

		TargetInfo[] invalidTargets=new TargetInfo[1];
		invalidTargets[0]=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		invalidTargets[0].SetLockedTarget(0, paragonOfGatheringMists);

		MagicEffect abilityEffect=MagicEffectGrantFlyingToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN, invalidTargets);
		abilityEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_BLUE);
		abilityEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		paragonOfGatheringMists.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{abilityCost}, new MagicEffect[]{abilityEffect}));
		
		MagicEffect continuousEffect=MagicEffectAddPTToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, 1, 1, MagicEffect.Duration.NA, invalidTargets);
		continuousEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_BLUE);
		continuousEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		paragonOfGatheringMists.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{continuousEffect}));
		
		paragonOfGatheringMists.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		paragonOfGatheringMists.GetSubtypes().AddSubtype(SubtypeCollection.WIZARD);
		return paragonOfGatheringMists;
	}
	// ENDREGION BLUE
	// REGION WHITE
	public static Creature AegisAngel(Player owner){
		Creature aegisAngel=Creature.NewCreature(owner, "Aegis Angel", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), 5, 5);
		TargetInfo[] invalidTargets=new TargetInfo[1];
		invalidTargets[0]=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		invalidTargets[0].SetLockedTarget(0, aegisAngel);

		MagicEffect[] triggeredEffects=new MagicEffect[]{MagicEffectGrantIndestructableToTargetPermanent.CreateWithInvalidTargets(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PERMANENT, MagicEffect.Duration.UNTIL_CONTROL_LOST, invalidTargets)};
		triggeredEffects[0].SetTargetToCallUndo(aegisAngel);
		
		aegisAngel.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(aegisAngel)}, triggeredEffects));
		aegisAngel.GetKeywords().AddFlying();
		aegisAngel.GetSubtypes().AddSubtype(SubtypeCollection.ANGEL);
		return aegisAngel;
	}

	public static Planeswalker AjaniSteadfast(Player owner){
		Planeswalker ajaniSteadfast=new Planeswalker(owner, "Ajani Steadfast", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 4);
	
		MagicEffect[] firstAbilityEffects=new MagicEffect[4];
		firstAbilityEffects[0]=MagicEffectAddPTToTargetCreature.Create(owner, 1, 1, 0, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		firstAbilityEffects[1]=MagicEffectGrantFirstStrikeToTargetCreature.CreateAutoset(owner, firstAbilityEffects[0].TargetData, MagicEffect.Duration.UNTIL_END_OF_TURN);
		firstAbilityEffects[2]=MagicEffectGrantVigilanceToTargetCreature.CreateAutoset(owner, firstAbilityEffects[0].TargetData, MagicEffect.Duration.UNTIL_END_OF_TURN);
		firstAbilityEffects[3]=MagicEffectGrantLifelinkToTargetCreature.CreateAutoset(owner, firstAbilityEffects[0].TargetData, MagicEffect.Duration.UNTIL_END_OF_TURN);
		ajaniSteadfast.AddActivatedAbility(new MagicActivatedAbility(true, null, new MagicEffect[]{new MagicEffectAdjustLoyalty(owner, ajaniSteadfast, 1)}, firstAbilityEffects));
		
		TargetInfo targetinfoMyCreature=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetinfoMyCreature.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		TargetInfo targetinfoThisAjani=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLANESWALKER);
		targetinfoThisAjani.SetLockedTarget(0, ajaniSteadfast);
		TargetInfo targetinfoMyPlaneswalkerNotThis=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLANESWALKER, new TargetInfo[]{targetinfoThisAjani});
		targetinfoMyPlaneswalkerNotThis.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		ajaniSteadfast.AddActivatedAbility(new MagicActivatedAbility(true, null, new MagicEffect[]{new MagicEffectAdjustLoyalty(owner, ajaniSteadfast, -2)}, new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectAddPTCountersToTargetCreature(owner, 1, targetinfoMyCreature)), new MagicEffectToAllPermanents(new MagicEffectAdjustLoyaltyForTarget(owner, targetinfoMyPlaneswalkerNotThis, 1))}));
		ajaniSteadfast.AddActivatedAbility(new MagicActivatedAbility(true, null, new MagicEffect[]{new MagicEffectAdjustLoyalty(owner, ajaniSteadfast, -7)}, new MagicEffect[]{new MagicEffectAddEmblemToPlayer(owner, EmblemCollection.AJANI_STEADFAST)}));
		ajaniSteadfast.GetSubtypes().AddSubtype(SubtypeCollection.AJANI);
		return ajaniSteadfast;
	}
	
	public static Creature AjanisPridemate(Player owner){
		Creature ajanisPridemate=Creature.NewCreature(owner, "Ajani's Pridemate", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), 2, 2);
		MagicEffect[] triggeredEffects=new MagicEffect[1];
		triggeredEffects[0]=MagicEffectAddPTCountersToTargetCreature.Create(owner, 1, 1, 1);
		triggeredEffects[0].TargetData.SetLockedTarget(0, ajanisPridemate);
		triggeredEffects[0].SetOptional();
		ajanisPridemate.AddLifegainTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(owner)}, triggeredEffects));
		ajanisPridemate.GetSubtypes().AddSubtype(SubtypeCollection.CAT);
		ajanisPridemate.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return ajanisPridemate;
	}

	public static Creature AvacynGuardianAngel(Player owner){
		Creature avacynGuardianAngel=Creature.NewCreature(owner, "Avacyn, Guardian Angel", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), 5, 4);
		avacynGuardianAngel.GetSubtypes().AddSubtype(SubtypeCollection.ANGEL);
		avacynGuardianAngel.GetKeywords().AddFlying();
		avacynGuardianAngel.GetKeywords().AddVigilance();
		
		TargetInfo[] invalidTargets=new TargetInfo[1];
		invalidTargets[0]=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		invalidTargets[0].SetLockedTarget(0, avacynGuardianAngel);

		TargetInfo chosencolorTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		chosencolorTargetInfo.SetColorORFlags(ManaCost.COLOR_PLAYERS_CHOICE);
		chosencolorTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.ANY));
		
		avacynGuardianAngel.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), null, new MagicEffect[]{MagicEffectAddDamagePreventorToITargetables.CreateWithInvalidTargetsRequiresColorDecision(owner, new DamagePreventor(DamagePreventor.DAMAGE_TYPE_FLAG_ANY, chosencolorTargetInfo), 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.UNTIL_END_OF_TURN, invalidTargets)}));
		avacynGuardianAngel.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), null, new MagicEffect[]{MagicEffectAddDamagePreventorToITargetables.CreateRequiresColorDecision(owner, new DamagePreventor(DamagePreventor.DAMAGE_TYPE_FLAG_ANY, chosencolorTargetInfo), 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER, MagicEffect.Duration.UNTIL_END_OF_TURN)}));
		return avacynGuardianAngel;
	}
	
	public static Creature BoonweaverGiant(Player owner){
		Creature boonweaverGiant=Creature.NewCreature(owner, "Boonweaver Giant", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 4, 4);
		TargetInfo boonweavertarget=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		boonweavertarget.SetLockedTarget(0, boonweaverGiant);
		TargetInfo auratargetinfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_AURA);
		auratargetinfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.HAND|ZoneOptions.GRAVEYARD|ZoneOptions.LIBRARY));
		MagicEffect boonweaverEntryEffect=MagicEffectChooseCardForAction.Attach(owner, auratargetinfo, boonweavertarget);
		boonweaverEntryEffect.SetOptional();
		boonweaverGiant.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(boonweaverGiant)}, new MagicEffect[]{boonweaverEntryEffect}));
		boonweaverGiant.GetSubtypes().AddSubtype(SubtypeCollection.GIANT);
		boonweaverGiant.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return boonweaverGiant;
	}
	
	public static Creature ConstrictingSliver(Player owner){
		Creature constrictingSliver=Creature.NewCreature(owner, "Constricting Sliver", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 3, 3);
	
		MagicEffect enterEffect=MagicEffectExileTargetCards.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.UNTIL_LEAVES_FIELD);
		enterEffect.TargetData.TargetMustNotBeControlledByIndex(owner.GetIndex(), owner.GetName());
		enterEffect.SetOptional();
		
		MagicEffect entersAdjustment=MagicEffectGrantTriggerEntersTheBattlefieldToTargetPermanent.Create(owner, new MagicEffect[]{enterEffect}, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.NA);
		entersAdjustment.TargetData.AddORSubtype(SubtypeCollection.SLIVER);
		entersAdjustment.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		constrictingSliver.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{entersAdjustment}));

		constrictingSliver.GetSubtypes().AddSubtype(SubtypeCollection.SLIVER);
		return constrictingSliver;
	}
	
	public static Creature DauntlessRiverMarshal(Player owner){
		Creature dauntlessRiverMarshal=Creature.NewCreature(owner, "Dauntless River Marshal", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 1);

		MagicEffect effect=MagicEffectAddPTToTargetCreature.Create(owner, 1, 1, 1, 1, MagicEffect.Duration.NA);
		effect.TargetData.SetLockedTarget(0, dauntlessRiverMarshal);
		
		dauntlessRiverMarshal.AddContinousEffect(new ContinuousAbility(new IContinuousCondition[]{new ContinuousConditionPlayerControlsCard("Island")}, new MagicEffect[]{effect}));
		dauntlessRiverMarshal.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), null, new MagicEffect[]{MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE)}));
		dauntlessRiverMarshal.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		dauntlessRiverMarshal.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return dauntlessRiverMarshal;
	}

	public static Creature GeistOfTheMoors(Player owner){
		Creature geistOfTheMoors=Creature.NewCreature(owner, "Geist of the Moors", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE,ManaCost.COLOR_FLAG_WHITE}), 3, 1);
		geistOfTheMoors.GetKeywords().AddFlying();
		geistOfTheMoors.GetSubtypes().AddSubtype(SubtypeCollection.SPIRIT);
		return geistOfTheMoors;
	}

	public static Creature HushwingGryff(Player owner){
		Creature hushwingGryff=Creature.NewCreature(owner, "Hushwing Gryff", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 1);
		
		hushwingGryff.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{MagicEffectSuppressTriggers.Create(owner, hushwingGryff, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), MagicEffect.Duration.NA)}));
		
		hushwingGryff.GetKeywords().AddFlash();
		hushwingGryff.GetKeywords().AddFlying();
		hushwingGryff.GetSubtypes().AddSubtype(SubtypeCollection.HIPPOGRIFF);
		return hushwingGryff;
	}
	
	public static Creature HeliodsPilgrim(Player owner){
		Creature heliodsPilgrim=Creature.NewCreature(owner, "Heliod's Pilgrim", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 1, 2);
		TargetInfo targetinfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_AURA);
		targetinfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.LIBRARY));
		MagicEffect heliodsPilgrimEntryEffect=MagicEffectChooseCardForAction.PutInHand(owner, targetinfo);
		heliodsPilgrimEntryEffect.SetOptional();
		heliodsPilgrim.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(heliodsPilgrim)}, new MagicEffect[]{heliodsPilgrimEntryEffect}));
		heliodsPilgrim.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		heliodsPilgrim.GetSubtypes().AddSubtype(SubtypeCollection.CLERIC);
		return heliodsPilgrim;
	}

	public static Creature KinsbaileSkirmisher(Player owner){
		Creature kinsbaileSkirmisher=Creature.NewCreature(owner, "Kinsbaile Skirmisher", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 2);
		kinsbaileSkirmisher.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(kinsbaileSkirmisher)}, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 1, 1, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN)}));
		kinsbaileSkirmisher.GetSubtypes().AddSubtype(SubtypeCollection.KITHKIN);
		kinsbaileSkirmisher.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return kinsbaileSkirmisher;
	}

	public static Creature MidnightGuard(Player owner){
		Creature midnightGuard=Creature.NewCreature(owner, "Midnight Guard", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 3);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, midnightGuard);
		midnightGuard.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionNotEqualsCard(midnightGuard), new TriggerConditionMatchesTypes(TargetInfo.TARGET_TYPE_FLAG_CREATURE)}, new MagicEffect[]{new MagicEffectUntapTargetCard(owner, selfTargetInfo)}));
		midnightGuard.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		midnightGuard.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return midnightGuard;
	}

	public static Creature OreskosSwiftclaw(Player owner){
		Creature oreskosSwiftclaw=Creature.NewCreature(owner, "Oreskos Swiftclaw", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS,ManaCost.COLOR_FLAG_WHITE}), 3, 1);
		oreskosSwiftclaw.GetSubtypes().AddSubtype(SubtypeCollection.CAT);
		oreskosSwiftclaw.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return oreskosSwiftclaw;
	}
	
	public static Creature ParagonOfNewDawns(Player owner){
		Creature paragonOfNewDawns=Creature.NewCreature(owner, "Paragon of New Dawns", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 2);
		
		MagicEffect abilityCost=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		abilityCost.TargetData.SetLockedTarget(0, paragonOfNewDawns);

		TargetInfo[] invalidTargets=new TargetInfo[1];
		invalidTargets[0]=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		invalidTargets[0].SetLockedTarget(0, paragonOfNewDawns);

		MagicEffect abilityEffect=MagicEffectGrantVigilanceToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN, invalidTargets);
		abilityEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_WHITE);
		abilityEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		paragonOfNewDawns.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{abilityCost}, new MagicEffect[]{abilityEffect}));
		
		MagicEffect continuousEffect=MagicEffectAddPTToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, 1, 1, MagicEffect.Duration.NA, invalidTargets);
		continuousEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_WHITE);
		continuousEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		paragonOfNewDawns.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{continuousEffect}));
		
		paragonOfNewDawns.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		paragonOfNewDawns.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return paragonOfNewDawns;
	}
	
	public static Creature PreeminentCaptain(Player owner){
		Creature preeminentCaptain=Creature.NewCreature(owner, "Preeminent Captain", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 2);
		TargetInfo cardFromHandInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		cardFromHandInfo.AddORSubtype(SubtypeCollection.SOLDIER);
		cardFromHandInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.HAND));

		MagicEffect attackEffect=MagicEffectChooseCardForAction.PutOnFieldTappedAndAttacking(owner, cardFromHandInfo);
		attackEffect.SetOptional();
		preeminentCaptain.AddAttacksTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(preeminentCaptain)}, new MagicEffect[]{attackEffect}));
		preeminentCaptain.GetKeywords().AddFirstStrike();
		preeminentCaptain.GetSubtypes().AddSubtype(SubtypeCollection.KITHKIN);
		preeminentCaptain.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return preeminentCaptain;
	}

	public static Creature RazorfootGriffin(Player owner){
		Creature razorfootGriffin=Creature.NewCreature(owner, "Razorfoot Griffin", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 2);
		razorfootGriffin.GetKeywords().AddFirstStrike();
		razorfootGriffin.GetKeywords().AddFlying();
		razorfootGriffin.GetSubtypes().AddSubtype(SubtypeCollection.GRIFFIN);
		return razorfootGriffin;
	}

	public static Creature ResoluteArchangel(Player owner){
		Creature resoluteArchangel=Creature.NewCreature(owner, "Resolute Archangel", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), 4, 4);
		resoluteArchangel.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(resoluteArchangel)}, new MagicEffect[]{new MagicEffectResoluteArchangel(owner)}));
		resoluteArchangel.GetKeywords().AddFlying();
		resoluteArchangel.GetSubtypes().AddSubtype(SubtypeCollection.ANGEL);
		return resoluteArchangel;
	}

	public static Creature SelflessCathar(Player owner){
		Creature selflessCathar=Creature.NewCreature(owner, "Selfless Cathar", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}), 1, 1);

		TargetInfo targetinfoMine=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetinfoMine.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		MagicEffect effect=new MagicEffectAddPTToTargetCreature(owner, 1, 1, targetinfoMine, MagicEffect.Duration.UNTIL_END_OF_TURN);
		selflessCathar.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectDiscardCard(owner, selflessCathar)}, new MagicEffect[]{new MagicEffectToAllPermanents(effect)}));

		selflessCathar.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		selflessCathar.GetSubtypes().AddSubtype(SubtypeCollection.CLERIC);
		return selflessCathar;
	}

	public static Creature SeraphOfTheMasses(Player owner){
		Creature seraphOfTheMasses=Creature.NewCreature(owner, "Seraph of the Masses", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), 0, 0);
		
		TargetInfo cardRequirements=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		cardRequirements.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		MagicEffect pteffect=MagicEffectAddPTToTargetCreatureForeachPermanent.Create(owner, 1, 1, 1, 1, MagicEffect.Duration.NA, cardRequirements);
		pteffect.TargetData.SetLockedTarget(0, seraphOfTheMasses);
		seraphOfTheMasses.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{pteffect}));
		
		seraphOfTheMasses.GetKeywords().AddConvoke();
		seraphOfTheMasses.GetKeywords().AddFlying();
		seraphOfTheMasses.GetSubtypes().AddSubtype(SubtypeCollection.ANGEL);
		return seraphOfTheMasses;
	}

	public static Creature SerraAngel(Player owner){
		Creature serraAngel=Creature.NewCreature(owner, "Serra Angel", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), 4, 4);
		serraAngel.GetKeywords().AddVigilance();
		serraAngel.GetKeywords().AddFlying();
		serraAngel.GetSubtypes().AddSubtype(SubtypeCollection.ANGEL);
		return serraAngel;
	}

	public static Creature SoulOfTheros(Player owner){
		Creature soulOfTheros=Creature.NewCreature(owner, "Soul of Theros", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), 6, 6);

		MagicEffect[] effects=new MagicEffect[3];
		TargetInfo targetinfoMine=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetinfoMine.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		
		MagicEffect firstStrikeEffect=MagicEffectGrantFirstStrikeToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		firstStrikeEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		
		effects[0]=new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, 2, 2, targetinfoMine, MagicEffect.Duration.UNTIL_END_OF_TURN));
		effects[1]=new MagicEffectToAllPermanents(firstStrikeEffect);
		effects[2]=new MagicEffectToAllPermanents(new MagicEffectGrantLifelinkToTargetCreature(owner, targetinfoMine, MagicEffect.Duration.UNTIL_END_OF_TURN));
		soulOfTheros.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), null, effects));
		
		MagicEffect exileCost=MagicEffectExileTargetCards.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.NA);
		exileCost.TargetData.SetLockedTarget(0, soulOfTheros);
		
		MagicActivatedAbility graveyardAbility=new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{exileCost}, effects);
		graveyardAbility.SetSourceZones(new ZoneOptions(ZoneOptions.GRAVEYARD));
		soulOfTheros.AddActivatedAbility(graveyardAbility);

		soulOfTheros.GetKeywords().AddVigilance();
		soulOfTheros.GetSubtypes().AddSubtype(SubtypeCollection.AVATAR);
		return soulOfTheros;
	}

	public static Creature Soulmender(Player owner){
		Creature soulmender=Creature.NewCreature(owner, "Soulmender", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}), 1, 1);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, soulmender);
		soulmender.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectGainLife(owner, 1)}));
		soulmender.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		soulmender.GetSubtypes().AddSubtype(SubtypeCollection.CLERIC);
		return soulmender;
	}

	public static Creature SungracePegasus(Player owner){
		Creature sungracePegasus=Creature.NewCreature(owner, "Sungrace Pegasus", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 1, 2);
		sungracePegasus.GetKeywords().AddLifelink();
		sungracePegasus.GetKeywords().AddFlying();
		sungracePegasus.GetSubtypes().AddSubtype(SubtypeCollection.PEGASUS);
		return sungracePegasus;
	}
	
	public static Creature TirelessMissionaries(Player owner){
		Creature tirelessMissionaries=Creature.NewCreature(owner, "Tireless Missionaries", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 3);
		tirelessMissionaries.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(tirelessMissionaries)}, new MagicEffect[]{new MagicEffectGainLife(owner, 3)}));
		tirelessMissionaries.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		tirelessMissionaries.GetSubtypes().AddSubtype(SubtypeCollection.CLERIC);
		return tirelessMissionaries;
	}

	public static Creature WallOfEssence(Player owner){
		Creature wallOfEssence=Creature.NewCreature(owner, "Wall of Essence", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 0, 4);

		MagicEffect lifegaineffect=new MagicEffectGainLife(owner);
		TriggeredAbilityDamaged damagetrigger=new TriggeredAbilityDamaged(new MagicEffect[]{lifegaineffect});
		damagetrigger.SetDamageTypeORFlags(DamagePreventor.DAMAGE_TYPE_FLAG_COMBAT);
		damagetrigger.RecipientTargetInfo.SetLockedTarget(0, wallOfEssence);

		wallOfEssence.AddTookDamageTrigger(damagetrigger);
		wallOfEssence.GetKeywords().AddDefender();
		wallOfEssence.GetSubtypes().AddSubtype(SubtypeCollection.WALL);
		return wallOfEssence;
	}

	public static Creature WardenOfTheBeyond(Player owner){
		Creature wardenOfTheBeyond=Creature.NewCreature(owner, "Warden of the Beyond", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 2);

		MagicEffect continuousEffect=MagicEffectAddPTToTargetCreature.Create(owner, 2, 2, 1, 1, MagicEffect.Duration.NA);
		continuousEffect.TargetData.SetLockedTarget(0, wardenOfTheBeyond);
		TargetInfo targetInfoNotYours=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		targetInfoNotYours.TargetMustNotBeControlledByIndex(owner.GetIndex(), owner.GetName());
		wardenOfTheBeyond.AddContinousEffect(new ContinuousAbility(new IContinuousCondition[]{new ContinuousConditionPlayerOwnsCardInExile(targetInfoNotYours)}, new MagicEffect[]{continuousEffect}));
		wardenOfTheBeyond.GetKeywords().AddVigilance();
		wardenOfTheBeyond.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		wardenOfTheBeyond.GetSubtypes().AddSubtype(SubtypeCollection.WIZARD);
		return wardenOfTheBeyond;
	}
	// ENDREGION WHITE
	// REGION BLACK
	public static Creature BloodHost(Player owner){
		Creature bloodHost=Creature.NewCreature(owner, "Blood Host", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK, ManaCost.COLOR_FLAG_BLACK}), 3, 3);
		
		TargetInfo anotherCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		anotherCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		MagicEffect ptcountereffect=MagicEffectAddPTCountersToTargetCreature.Create(owner, 1, 1, 1);
		ptcountereffect.TargetData.SetLockedTarget(0, bloodHost);
		bloodHost.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), new MagicEffect[]{new MagicEffectDiscardTargetCard(owner, anotherCreatureTargetInfo)}, new MagicEffect[]{ptcountereffect, new MagicEffectGainLife(owner, 2)}));
		
		bloodHost.GetSubtypes().AddSubtype(SubtypeCollection.VAMPIRE);
		return bloodHost;
	}

	public static Creature CarrionCrow(Player owner){
		Creature carrionCrow=Creature.NewCreature(owner, "Carrion Crow", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 2, 2);

		MagicEffect tapeffect=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		tapeffect.TargetData.SetLockedTarget(0, carrionCrow);
		carrionCrow.AddEntersTheBattlefieldAdjustments(tapeffect);

		carrionCrow.GetKeywords().AddFlying();
		carrionCrow.GetSubtypes().AddSubtype(SubtypeCollection.ZOMBIE);
		carrionCrow.GetSubtypes().AddSubtype(SubtypeCollection.BIRD);
		return carrionCrow;
	}

	public static Creature ChildOfNight(Player owner){
		Creature childOfNight=Creature.NewCreature(owner, "Child of Night", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 2, 1);
		childOfNight.GetKeywords().AddLifelink();
		childOfNight.GetSubtypes().AddSubtype(SubtypeCollection.VAMPIRE);
		return childOfNight;
	}

	public static Creature LeechingSliver(Player owner){
		Creature leechingSliver=Creature.NewCreature(owner, "Leeching Sliver", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 1, 1);
		
		TriggerConditionMatchesTypes sliverControlledByYouCondition=new TriggerConditionMatchesTypes(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		sliverControlledByYouCondition.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		sliverControlledByYouCondition.TargetData.AddORSubtype(SubtypeCollection.SLIVER);
		
		TriggeredAbility attacktrigger=new TriggeredAbility(new TriggerCondition[]{sliverControlledByYouCondition}, new MagicEffect[]{new MagicEffectLoseLifeTargetPlayer(owner, 1, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER))});
		attacktrigger.SetRelationship(0, 0, TriggeredAbility.ConditionEffectRelationship.DEFENDER_IS_TARGET);
		leechingSliver.AddAttacksTrigger(attacktrigger);
		leechingSliver.GetSubtypes().AddSubtype(SubtypeCollection.SLIVER);
		return leechingSliver;
	}

	public static Creature NecrogenScudder(Player owner){
		Creature necrogenScudder=Creature.NewCreature(owner, "Necrogen Scudder", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 3, 3);
		necrogenScudder.GetKeywords().AddFlying();
		necrogenScudder.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(necrogenScudder)}, new MagicEffect[]{new MagicEffectLoseLife(owner, 3)}));
		necrogenScudder.GetSubtypes().AddSubtype(SubtypeCollection.HORROR);
		return necrogenScudder;
	}

	public static Creature NightfireGiant(Player owner){
		Creature nightfireGiant=Creature.NewCreature(owner, "Nightfire Giant", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 4, 3);

		MagicEffect effect=MagicEffectAddPTToTargetCreature.Create(owner, 1, 1, 1, 1, MagicEffect.Duration.NA);
		effect.TargetData.SetLockedTarget(0, nightfireGiant);
		
		nightfireGiant.AddContinousEffect(new ContinuousAbility(new IContinuousCondition[]{new ContinuousConditionPlayerControlsCard("Mountain")}, new MagicEffect[]{effect}));
		nightfireGiant.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), null, new MagicEffect[]{MagicEffectDealDamageToTarget.Create(owner, nightfireGiant, 2, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER|TargetInfo.TARGET_TYPE_FLAG_CREATURE)}));
		nightfireGiant.GetSubtypes().AddSubtype(SubtypeCollection.ZOMBIE);
		nightfireGiant.GetSubtypes().AddSubtype(SubtypeCollection.GIANT);
		return nightfireGiant;
	}

	public static Creature Nightmare(Player owner){
		Creature nightmare=Creature.NewCreature(owner, "Nightmare", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 0, 0);
		
		TargetInfo cardRequirements=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_BASICLAND);
		cardRequirements.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		cardRequirements.AddORName("Swamp");
		MagicEffect pteffect=MagicEffectAddPTToTargetCreatureForeachPermanent.Create(owner, 1, 1, 1, 1, MagicEffect.Duration.NA, cardRequirements);
		pteffect.TargetData.SetLockedTarget(0, nightmare);
		nightmare.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{pteffect}));
		
		nightmare.GetKeywords().AddFlying();
		nightmare.GetSubtypes().AddSubtype(SubtypeCollection.NIGHTMARE);
		nightmare.GetSubtypes().AddSubtype(SubtypeCollection.HORSE);
		return nightmare;
	}

	public static Creature ParagonOfOpenGraves(Player owner){
		Creature paragonOfOpenGraves=Creature.NewCreature(owner, "Paragon of Open Graves", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 2, 2);
		
		MagicEffect abilityCost=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		abilityCost.TargetData.SetLockedTarget(0, paragonOfOpenGraves);

		TargetInfo[] invalidTargets=new TargetInfo[1];
		invalidTargets[0]=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		invalidTargets[0].SetLockedTarget(0, paragonOfOpenGraves);

		MagicEffect abilityEffect=MagicEffectGrantDeathtouchToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN, invalidTargets);
		abilityEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_BLACK);
		abilityEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		paragonOfOpenGraves.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), new MagicEffect[]{abilityCost}, new MagicEffect[]{abilityEffect}));
		
		MagicEffect continuousEffect=MagicEffectAddPTToTargetCreature.CreateWithInvalidTargets(owner, 1, 1, 1, 1, MagicEffect.Duration.NA, invalidTargets);
		continuousEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_BLACK);
		continuousEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		paragonOfOpenGraves.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{continuousEffect}));
		
		paragonOfOpenGraves.GetSubtypes().AddSubtype(SubtypeCollection.SKELETON);
		paragonOfOpenGraves.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return paragonOfOpenGraves;
	}

	public static Creature ShadowcloakVampire(Player owner){
		Creature shadowcloakVampire=Creature.NewCreature(owner, "Shadowcloak Vampire", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 4, 3);

		MagicEffect abilityEffect=MagicEffectGrantFlyingToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		abilityEffect.TargetData.SetLockedTarget(0, shadowcloakVampire);

		shadowcloakVampire.AddActivatedAbility(new MagicActivatedAbility(false, null, new MagicEffect[]{new MagicEffectLoseLife(owner, 2)}, new MagicEffect[]{abilityEffect}));
		shadowcloakVampire.GetSubtypes().AddSubtype(SubtypeCollection.VAMPIRE);
		return shadowcloakVampire;
	}

	public static Creature TyphoidRats(Player owner){
		Creature typhoidRats=Creature.NewCreature(owner, "Typhoid Rats", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLACK}), 1, 1);
		typhoidRats.GetKeywords().AddDeathtouch();
		typhoidRats.GetSubtypes().AddSubtype(SubtypeCollection.RAT);
		return typhoidRats;
	}

	public static Creature WalkingCorpse(Player owner){
		Creature walkingCorpse=Creature.NewCreature(owner, "Walking Corpse", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS,ManaCost.COLOR_FLAG_BLACK}), 2, 2);
		walkingCorpse.GetSubtypes().AddSubtype(SubtypeCollection.ZOMBIE);
		return walkingCorpse;
	}

	public static Creature WitchsFamiliar(Player owner){
		Creature witchsFamiliar=Creature.NewCreature(owner, "Witch's Familiar", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 2, 3);
		witchsFamiliar.GetSubtypes().AddSubtype(SubtypeCollection.FROG);
		return witchsFamiliar;
	}

	public static Creature ZofShade(Player owner){
		Creature zofShade=Creature.NewCreature(owner, "Zof Shade", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 2, 2);
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectAddPTToTargetCreature.Create(owner, 2, 2, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		effects[0].TargetData.SetLockedTarget(0, zofShade);
		zofShade.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), null, effects));
		zofShade.GetSubtypes().AddSubtype(SubtypeCollection.SHADE);
		return zofShade;
	}
	// ENDREGION BLACK
	// REGION MULTICOLORED
	public static Creature SliverHivelord(Player owner){
		Creature sliverHivelord=Creature.NewCreature(owner, "Sliver Hivelord", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_BLACK}), 5, 5);
		sliverHivelord.SetLegendary();
		
		MagicEffect grantIndescructable=MagicEffectGrantIndestructableToTargetPermanent.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.NA);
		grantIndescructable.TargetData.AddORSubtype(SubtypeCollection.SLIVER);
		grantIndescructable.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		sliverHivelord.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{grantIndescructable}));
		
		sliverHivelord.GetSubtypes().AddSubtype(SubtypeCollection.SLIVER);
		return sliverHivelord;
	}
	// ENDREGION MULTICOLORED
	// REGION COLORLESS
	public static Creature GargoyleSentinel(Player owner){
		Creature gargoyleSentinel=Creature.NewArtifactCreature(owner, "Gargoyle Sentinel", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), 3, 3);

		//TODO: Prevent defender

		gargoyleSentinel.GetKeywords().AddDefender();
		return gargoyleSentinel;
	}

	public static Creature Ornithopter(Player owner){
		Creature ornithopter=Creature.NewArtifactCreature(owner, "Ornithopter", "", new ManaCost(new int[0]), 0, 2);
		ornithopter.GetKeywords().AddFlying();
		return ornithopter;
	}

	public static Creature SoulOfNewPhyrexia(Player owner){
		Creature soulOfNewPhyrexia=Creature.NewCreature(owner, "Soul of New Phyrexia", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), 6, 6);

		MagicEffect[] effects=new MagicEffect[1];
		TargetInfo targetinfoMine=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PERMANENT);
		targetinfoMine.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		effects[0]=new MagicEffectToAllPermanents(new MagicEffectGrantIndestructableToTargetPermanent(owner, targetinfoMine, MagicEffect.Duration.UNTIL_END_OF_TURN));
		soulOfNewPhyrexia.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), null, effects));
		
		MagicEffect exileCost=MagicEffectExileTargetCards.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.NA);
		exileCost.TargetData.SetLockedTarget(0, soulOfNewPhyrexia);
		
		MagicActivatedAbility graveyardAbility=new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), new MagicEffect[]{exileCost}, effects);
		graveyardAbility.SetSourceZones(new ZoneOptions(ZoneOptions.GRAVEYARD));
		soulOfNewPhyrexia.AddActivatedAbility(graveyardAbility);

		soulOfNewPhyrexia.GetKeywords().AddTrample();
		soulOfNewPhyrexia.GetSubtypes().AddSubtype(SubtypeCollection.AVATAR);
		return soulOfNewPhyrexia;
	}

	public static Creature WillForgedGolem(Player owner){
		Creature willForgedGolem=Creature.NewArtifactCreature(owner, "Will-Forged Golem", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), 4, 4);
		willForgedGolem.GetKeywords().AddConvoke();
		return willForgedGolem;
	}
	// ENDREGION COLORLESS
// ENDREGION CREATURES
	
// REGION INSTANTS AND SORCERIES
	// REGION RED
	public static Sorcery ConeOfFlame(Player owner){
		MagicEffect[] effects=new MagicEffect[3];
		effects[0]=MagicEffectDealDamageToTarget.Create(owner, null, 1, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER|TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		effects[1]=MagicEffectDealDamageToTarget.CreateWithInvalidTargets(owner, null, 2, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER|TargetInfo.TARGET_TYPE_FLAG_CREATURE, new TargetInfo[]{effects[0].TargetData});
		effects[2]=MagicEffectDealDamageToTarget.CreateWithInvalidTargets(owner, null, 3, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER|TargetInfo.TARGET_TYPE_FLAG_CREATURE, new TargetInfo[]{effects[0].TargetData, effects[1].TargetData});
		Sorcery coneOfFlame = new Sorcery(owner, "Cone of Flame", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), effects);
		return coneOfFlame;
	}

	public static Instant CrowdsFavor(Player owner){
		MagicEffect[] effects=new MagicEffect[2];
		effects[0]=MagicEffectGrantFirstStrikeToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		effects[1]=MagicEffectAddPTToTargetCreature.CreateAutoset(owner, 1, 0, effects[0].TargetData, MagicEffect.Duration.UNTIL_END_OF_TURN);
		Instant crowdsFavor = new Instant(owner, "Crowd's Favor", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), effects);
		crowdsFavor.GetKeywords().AddConvoke();
		return crowdsFavor;
	}

	public static Instant HeatRay(Player owner){
		return new Instant(owner, "Heat Ray", "", new ManaCost(new int[]{ManaCost.COLOR_COST_X, ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{MagicEffectDealDamageToTarget.CreateXDamage(owner, null, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE)});
	}

	public static Sorcery LavaAxe(Player owner){
		return new Sorcery(owner, "Lava Axe", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{MagicEffectDealDamageToTarget.Create(owner, null, 5, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER)});
	}

	public static Instant LightningStrike(Player owner){
		return new Instant(owner, "Lightning Strike", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{MagicEffectDealDamageToTarget.Create(owner, null, 3, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER|TargetInfo.TARGET_TYPE_FLAG_CREATURE)});
	}

	public static Instant StokeTheFlames(Player owner){
		Instant stokeTheFlames = new Instant(owner, "Stoke the Flames", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{MagicEffectDealDamageToTarget.Create(owner, null, 4, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER|TargetInfo.TARGET_TYPE_FLAG_CREATURE)});
		stokeTheFlames.GetKeywords().AddConvoke();
		return stokeTheFlames;
	}
	// ENDREGION RED
	// REGION GREEN
	public static Instant BackToNature(Player owner){
		return new Instant(owner, "Back to Nature", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectDestroyTargetPermanents(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_ENCHANTMENT)))});
	}

	public static Sorcery FeralIncarnation(Player owner){
		Sorcery feralIncarnation=new Sorcery(owner, "Feral Incarnation", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{new MagicEffectPlayToken(owner, GreenBeastP3T3(owner), 3)});
		feralIncarnation.GetKeywords().AddConvoke();
		return feralIncarnation;
	}

	public static Instant GatherCourage(Player owner){
		Instant gatherCourage = new Instant(owner, "Gather Courage", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 2, 2, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN)});
		gatherCourage.GetKeywords().AddConvoke();
		return gatherCourage;
	}

	public static Instant Naturalize(Player owner){
		return new Instant(owner, "Naturalize", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{MagicEffectDestroyTargetPermanents.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_ENCHANTMENT|TargetInfo.TARGET_TYPE_FLAG_ARTIFACT)});
	}

	public static Sorcery NissasExpedition(Player owner){
		TargetInfo targetinfo=new TargetInfo(0, 2, TargetInfo.TARGET_TYPE_FLAG_BASICLAND);
		targetinfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.LIBRARY));

		Sorcery nissasExpedition=new Sorcery(owner, "Nissa's Expedition", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{MagicEffectChooseCardForAction.PutOnFieldTapped(owner, targetinfo)});
		nissasExpedition.GetKeywords().AddConvoke();
		return nissasExpedition;
	}
	
	public static Instant Overwhelm(Player owner){
		TargetInfo targetinfoMine=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetinfoMine.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		Instant overwhelm=new Instant(owner, "Overwhelm", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, 3, 3, targetinfoMine, MagicEffect.Duration.UNTIL_END_OF_TURN))});
		overwhelm.GetKeywords().AddConvoke();
		return overwhelm;
	}

	public static Instant TitanicGrowth(Player owner){
		return new Instant(owner, "Titanic Growth", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 4, 4, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN)});
	}
	// ENDREGION GREEN
	// REGION BLUE
	public static Instant AEtherspouts(Player owner){
		return new Instant(owner, "AEtherspouts", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{new MagicEffectAEtherspouts(owner)});
	}

	public static Instant Cancel(Player owner){
		return new Instant(owner, "Cancel", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{new MagicEffectCancelSpell(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_CARD))});
	}

	public static Sorcery Divination(Player owner){
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectDrawCardsForTargetPlayer.Create(owner, 2, 1, 1);
		effects[0].TargetData.SetLockedTarget(0, owner);
		return new Sorcery(owner, "Divination", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), effects);
	}

	public static Instant Hydrosurge(Player owner){
		return new Instant(owner, "Hydrosurge", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, -5, 0, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN)});
	}

	public static Instant JacesIngenuity(Player owner){
		MagicEffect[] effects=new MagicEffect[1];
		effects[0]=MagicEffectDrawCardsForTargetPlayer.Create(owner, 3, 1, 1);
		effects[0].TargetData.SetLockedTarget(0, owner);
		return new Instant(owner, "Jace's Ingenuity", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_BLUE}), effects);
	}
	// ENDREGION BLUE
	// REGION WHITE
	public static Instant Congregate(Player owner){
		return new Instant(owner, "Congregate", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{MagicEffectGainXLifeForEachEligiblePermanent.Create(owner, 2, 1, 1, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE))});
	}

	public static Instant DevouringLight(Player owner){
		MagicEffect effect=MagicEffectExileTargetCards.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.NA);
		effect.TargetData.SetCombatStateORFlags(TargetInfo.COMBAT_STATE_FLAG_ATTACKING|TargetInfo.COMBAT_STATE_FLAG_BLOCKING);
		Instant devouringLight = new Instant(owner, "Devouring Light", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{effect});
		devouringLight.GetKeywords().AddConvoke();
		return devouringLight;
	}

	public static Instant DivineVerdict(Player owner){
		MagicEffect effect=MagicEffectDestroyTargetPermanents.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		effect.TargetData.SetCombatStateORFlags(TargetInfo.COMBAT_STATE_FLAG_ATTACKING|TargetInfo.COMBAT_STATE_FLAG_BLOCKING);
		Instant divineVerdict = new Instant(owner, "Divine Verdict", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{effect});
		return divineVerdict;
	}

	public static Instant EphemeralShields(Player owner){
		Instant ephemeralShields = new Instant(owner, "Ephemeral Shields", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{MagicEffectGrantIndestructableToTargetPermanent.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.UNTIL_END_OF_TURN)});
		ephemeralShields.GetKeywords().AddConvoke();
		return ephemeralShields;
	}

	public static Instant InspiredCharge(Player owner){
		TargetInfo targetinfoMine=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetinfoMine.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		return new Instant(owner, "Inspired Charge", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, 2, 1, targetinfoMine, MagicEffect.Duration.UNTIL_END_OF_TURN))});
	}

	public static Sorcery MassCalcify(Player owner){
		MagicEffect effect=new MagicEffectDestroyTargetPermanents(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE));
		effect.TargetData.SetColorNOTFlags(ManaCost.COLOR_FLAG_WHITE);
		return new Sorcery(owner, "Mass Calcify", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectToAllPermanents(effect)});
	}

	public static Instant MeditationPuzzle(Player owner){
		Instant meditationPuzzle = new Instant(owner, "Meditation Puzzle", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectGainLife(owner, 8)});
		meditationPuzzle.GetKeywords().AddConvoke();
		return meditationPuzzle;
	}

	public static Instant PillarOfLight(Player owner){
		MagicEffect effect=MagicEffectExileTargetCards.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.NA);
		effect.TargetData.SetToughnessMin(4);
		Instant pillarOfLight = new Instant(owner, "Pillar of Light", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{effect});
		return pillarOfLight;
	}

	public static Instant RaiseTheAlarm(Player owner){
		return new Instant(owner, "Raise the Alarm", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectPlayToken(owner, WhiteSoldierP1T1(owner), 2)});
	}

	public static Sorcery ReturnToTheRanks(Player owner){
		TargetInfo cardReqs=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		cardReqs.SetConvertedManaCostMax(2);
		cardReqs.SetNeedsX();
		cardReqs.SetValidTargetZones(new ZoneOptions(ZoneOptions.GRAVEYARD));
		cardReqs.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		MagicEffect effect=new MagicEffectPlaceTargetPermanentOnField(owner, cardReqs);
		Sorcery returnToTheRanks=new Sorcery(owner, "Return to the Ranks", "", new ManaCost(new int[]{ManaCost.COLOR_COST_X, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{effect});
		returnToTheRanks.GetKeywords().AddConvoke();

		return returnToTheRanks;
	}

	public static Instant SanctifiedCharge(Player owner){
		TargetInfo targetinfoMine=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetinfoMine.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		MagicEffect pteffect=new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, 2, 1, targetinfoMine, MagicEffect.Duration.UNTIL_END_OF_TURN));
		
		MagicEffect firstStrikeEffect=MagicEffectGrantFirstStrikeToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		firstStrikeEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		firstStrikeEffect.TargetData.SetColorORFlags(ManaCost.COLOR_FLAG_WHITE);

		MagicEffect firstStrikeToAllEffect=new MagicEffectToAllPermanents(firstStrikeEffect);

		Instant sanctifiedCharge=new Instant(owner, "Sanctified Charge", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{pteffect, firstStrikeToAllEffect});
		return sanctifiedCharge;
	}

	public static Sorcery SolemnOffering(Player owner){
		return new Sorcery(owner, "Solemn Offering", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{MagicEffectDestroyTargetPermanents.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_ENCHANTMENT|TargetInfo.TARGET_TYPE_FLAG_ARTIFACT), new MagicEffectGainLife(owner, 4)});
	}

	public static Sorcery TriplicateSpirits(Player owner){
		Sorcery triplicateSpirits=new Sorcery(owner, "Triplicate Spirits", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectPlayToken(owner, WhiteSpiritWithFlyingP1T1(owner), 3)});
		triplicateSpirits.GetKeywords().AddConvoke();
		return triplicateSpirits;
	}

	// ENDREGION WHITE
	// REGION BLACK
	public static Sorcery CovenantOfBlood(Player owner){
		MagicEffect[] effects=new MagicEffect[2];
		effects[0]=MagicEffectDealDamageToTarget.Create(owner, null, 4, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER|TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		effects[1]=new MagicEffectGainLife(owner, 4);
		Sorcery covenantOfBlood=new Sorcery(owner, "Covenant of Blood", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), effects);
		covenantOfBlood.GetKeywords().AddConvoke();
		return covenantOfBlood;
	}

	public static Sorcery Festergloom(Player owner){
		TargetInfo targetinfoNonblack=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetinfoNonblack.SetColorNOTFlags(ManaCost.COLOR_FLAG_BLACK);
		MagicEffect pteffect=new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, -1, -1, targetinfoNonblack, MagicEffect.Duration.UNTIL_END_OF_TURN));
		return new Sorcery(owner, "Festergloom", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), new MagicEffect[]{pteffect});
	}

	public static Sorcery InGarruksWake(Player owner){
		MagicEffect effect=new MagicEffectDestroyTargetPermanents(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE|TargetInfo.TARGET_TYPE_FLAG_PLANESWALKER));
		effect.TargetData.TargetMustNotBeControlledByIndex(owner.GetIndex(), owner.GetName());
		return new Sorcery(owner, "In Garruk's Wake", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK, ManaCost.COLOR_FLAG_BLACK}), new MagicEffect[]{new MagicEffectToAllPermanents(effect)});
	}

	public static Sorcery SignInBlood(Player owner){
		MagicEffect[] effects=new MagicEffect[2];
		effects[0]=MagicEffectDrawCardsForTargetPlayer.Create(owner, 2, 1, 1);
		effects[1]=MagicEffectDealDamageToTarget.CreateAutoset(owner, null, 2, effects[0].TargetData);
		return new Sorcery(owner, "Sign in Blood", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLACK, ManaCost.COLOR_FLAG_BLACK}), effects);
	}

	public static Instant Ulcerate(Player owner){
		return new Instant(owner, "Ulcerate", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLACK}), new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, -3, -3, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN), new MagicEffectLoseLife(owner, 3)});
	}
	// ENDREGION BLACK
	// REGION COLORLESS
	// ENDREGION COLORLESS
// ENDREGION INSTANTS AND SORCERIES
	
// REGION ENCHANTMENTS
	// REGION RED
	public static Enchantment CrucibleOfFire(Player owner){
		Enchantment crucibleOfFire=Enchantment.NewEnchantment(owner, "Crucible of Fire", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}));
		MagicEffect continuousEffect=MagicEffectAddPTToTargetCreature.Create(owner, 3, 3, 1, 1, MagicEffect.Duration.NA);
		continuousEffect.TargetData.AddORSubtype(SubtypeCollection.DRAGON);
		continuousEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		crucibleOfFire.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{continuousEffect}));
		return crucibleOfFire;
	}

	public static Enchantment InfernoFist(Player owner){
		Enchantment infernoFist=Enchantment.NewEnchantmentAura(owner, "Inferno Fist", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 2, 0, 1, 1, MagicEffect.Duration.NA)});
		
		infernoFist.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{new MagicEffectDiscardCard(owner, infernoFist)}, new MagicEffect[]{MagicEffectDealDamageToTarget.Create(owner, infernoFist, 2, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER|TargetInfo.TARGET_TYPE_FLAG_CREATURE)}));
		
		return infernoFist;
	}
	// ENDREGION RED
	// REGION GREEN
	// ENDREGION GREEN
	// REGION BLUE
	public static Enchantment Invisibility(Player owner){
		TargetInfo cantBeBlockedByInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		cantBeBlockedByInfo.AddNOTSubtype(SubtypeCollection.WALL);
		MagicEffect effectUnblockableWall=MagicEffectGrantConditionalUnblockableToTargetCreature.Create(owner, 1, 1, cantBeBlockedByInfo, MagicEffect.Duration.NA);
		Enchantment invisibility=Enchantment.NewEnchantmentAura(owner, "Invisibility", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_BLUE}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{effectUnblockableWall});
		return invisibility;
	}
	// ENDREGION BLUE
	// REGION WHITE
	public static Enchantment BattleMastery(Player owner){
		return Enchantment.NewEnchantmentAura(owner, "Battle Mastery", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectGrantDoubleStrikeToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.NA)});
	}
	
	public static Enchantment DivineFavor(Player owner){
		Enchantment divineFavor=Enchantment.NewEnchantmentAura(owner, "Divine Favor", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 1, 3, 1, 1, MagicEffect.Duration.NA)});
		divineFavor.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(divineFavor)}, new MagicEffect[]{new MagicEffectGainLife(owner, 3)}));
		return divineFavor;
	}

	public static Enchantment FirstResponse(Player owner){
		Enchantment firstResponse=Enchantment.NewEnchantment(owner, "First Response", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}));
		firstResponse.AddUpkeepTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionLostLifeLastTurn(owner)}, new MagicEffect[]{new MagicEffectPlayToken(owner, WhiteSoldierP1T1(owner), 1)}));
		return firstResponse;
	}

	public static Enchantment MarkedByHonor(Player owner){
		return Enchantment.NewEnchantmentAura(owner, "Marked by Honor", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 2, 2, 1, 1, MagicEffect.Duration.NA), MagicEffectGrantVigilanceToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.NA)});
	}

	public static Enchantment OppressiveRays(Player owner){
		return Enchantment.NewEnchantmentAura(owner, "Oppressive Rays", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectAddAttackManaCostToTargetCreature.Create(owner, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), 1, 1, MagicEffect.Duration.NA), MagicEffectAddBlockManaCostToTargetCreature.Create(owner, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), 1, 1, MagicEffect.Duration.NA), new MagicEffectAddActivatedAbilityManaCostToTargetPermanent(owner, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), MagicEffect.Duration.NA)});
	}

	public static Enchantment SpectraWard(Player owner){
		TargetInfo anyColorCreature=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		anyColorCreature.SetColorORFlags(ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLUE|ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK);

		TargetInfo anyColorAnything=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		anyColorAnything.SetColorORFlags(ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLUE|ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK);
		anyColorAnything.SetValidTargetZones(new ZoneOptions(ZoneOptions.ANY));
		
		return Enchantment.NewEnchantmentAura(owner, "Spectra Ward", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 2, 2, 1, 1, MagicEffect.Duration.NA), MagicEffectGrantConditionalUnblockableToTargetCreature.Create(owner, 1, 1, anyColorCreature, MagicEffect.Duration.NA), MagicEffectGrantConditionalShroudToTargetCreature.Create(owner, 1, 1, anyColorAnything, MagicEffect.Duration.NA), MagicEffectAddDamagePreventorToITargetables.Create(owner, new DamagePreventor(DamagePreventor.DAMAGE_TYPE_FLAG_ANY, anyColorAnything), 1, 1, TargetInfo.TARGET_TYPE_ANY, MagicEffect.Duration.NA)});
	}

	public static Enchantment SpiritBonds(Player owner){
		Enchantment spiritBonds=Enchantment.NewEnchantment(owner, "Spirit Bonds", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}));
		TriggerConditionMatchesTypes creatureControlledByYouCondition=new TriggerConditionMatchesTypes(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		creatureControlledByYouCondition.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		MagicEffect tokenEffect=new MagicEffectPlayToken(owner, WhiteSpiritWithFlyingP1T1(owner), 1);	
		tokenEffect.AddExtraManaCost(new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}));
		spiritBonds.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{creatureControlledByYouCondition, new TriggerConditionNotMatchesTypes(TargetInfo.TARGET_TYPE_FLAG_TOKEN)}, new MagicEffect[]{tokenEffect}));
		
		TargetInfo singleSpiritTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		singleSpiritTargetInfo.AddORSubtype(SubtypeCollection.SPIRIT);
		singleSpiritTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		
		MagicEffect sacEffect=MagicEffectGrantIndestructableToTargetPermanent.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.UNTIL_END_OF_TURN);
		sacEffect.TargetData.AddNOTSubtype(SubtypeCollection.SPIRIT);
		spiritBonds.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectDiscardTargetCard(owner, singleSpiritTargetInfo)}, new MagicEffect[]{sacEffect}));
		
		return spiritBonds;
	}
	// ENDREGION WHITE
	// REGION BLACK
	public static Enchantment CausticTar(Player owner){
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		MagicActivatedAbility ability=new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{MagicEffectDealDamageToTarget.Create(owner, null, 3, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER)});
		MagicEffect[] auraEffects=new MagicEffect[1];
		auraEffects[0]=MagicEffectGrantActivatedAbilityToTargetPermanent.Create(owner, ability, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND, MagicEffect.Duration.NA);

		Enchantment causticTar=Enchantment.NewEnchantmentAura(owner, "Caustic Tar", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK, ManaCost.COLOR_FLAG_BLACK}), TargetInfo.TARGET_TYPE_FLAG_LAND, auraEffects);
		nonmanaCosts[0]=MagicEffectTapTargetCard.CreateAutoset(owner, auraEffects[0].TargetData);
		return causticTar;
	}

	public static Enchantment FeastOnTheFallen(Player owner){
		Enchantment feastOnTheFallen=Enchantment.NewEnchantment(owner, "Feast on the Fallen", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}));
		MagicEffect ptcountereffect=MagicEffectAddPTCountersToTargetCreature.Create(owner, 1, 1, 1);
		ptcountereffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		feastOnTheFallen.AddUpkeepTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionOpponentLostLifeLastTurn(owner)}, new MagicEffect[]{ptcountereffect}));
		return feastOnTheFallen;
	}
	// ENDREGION BLACK
	// REGION COLORLESS
	// ENDREGION COLORLESS
// ENDREGION ENCHANTMENTS

// REGION ARTIFACTS
	public static Artifact BrawlersPlate(Player owner){
		return Artifact.NewArtifactEquipment(owner, "Brawler's Plate", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), null, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 2, 2, 1, 1, MagicEffect.Duration.NA), MagicEffectGrantTrampleToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.NA)});
	}

	public static Artifact Meteorite(Player owner){
		Artifact meteorite=Artifact.NewArtifact(owner, "Meteorite", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}));
		
		MagicEffect tapme=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		tapme.TargetData.SetLockedTarget(0, meteorite);
	
		meteorite.AddActivatedAbility(new MagicActivatedAbility(false, null, new MagicEffect[]{tapme}, new MagicEffect[]{MagicEffectAddManaToPool.CreateRequiresColorDecision(owner, ManaCost.COLOR_ANY&~ManaCost.COLOR_FLAG_COLORLESS)}));
		return meteorite;
	}

	public static Artifact PerilousVault(Player owner){
		Artifact perilousVault=Artifact.NewArtifact(owner, "Perilous Vault", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}));
		
		MagicEffect[] nonmanacosts=new MagicEffect[2];
		nonmanacosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		nonmanacosts[0].TargetData.SetLockedTarget(0, perilousVault);
		nonmanacosts[1]=MagicEffectExileTargetCards.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_ARTIFACT, MagicEffect.Duration.NA);
		nonmanacosts[1].TargetData.SetLockedTarget(0, perilousVault);
	
		TargetInfo exiledTypes=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PERMANENT);
		exiledTypes.SetTargetTypeNOTFlags(TargetInfo.TARGET_TYPE_FLAG_LAND);
		MagicEffect exileNonlandsEffect=new MagicEffectExileTargetCards(owner, exiledTypes, MagicEffect.Duration.NA);
		perilousVault.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), nonmanacosts, new MagicEffect[]{new MagicEffectToAllPermanents(exileNonlandsEffect)}));
		return perilousVault;
	}

	public static Artifact SacredArmory(Player owner){
		Artifact sacredArmory=Artifact.NewArtifact(owner, "Sacred Armory", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}));
		sacredArmory.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), null, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 1, 0, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN)}));
		return sacredArmory;
	}

	public static Artifact TyrantsMachine(Player owner){
		Artifact tyrantsMachine=Artifact.NewArtifact(owner, "Tyrant's Machine", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}));
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, tyrantsMachine);
		tyrantsMachine.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), nonmanaCosts, new MagicEffect[]{MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE)}));
		return tyrantsMachine;
	}
// ENDREGION ARTIFACTS
// REGION LANDS
	public static NonbasicLand BattlefieldForge(Player owner){
		NonbasicLand battlefieldForge=new NonbasicLand(owner, "Battlefield Forge", ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_WHITE);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, battlefieldForge);
		battlefieldForge.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 0, 0, 0, 0, 0, 1)}));
		
		MagicEffectDealDamageToTarget damageEffect=MagicEffectDealDamageToTarget.Create(owner, battlefieldForge, 1, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		damageEffect.TargetData.SetLockedTarget(0, owner);
		battlefieldForge.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{MagicEffectAddManaToPool.CreateRequiresColorDecision(owner, ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_WHITE), damageEffect}));
		return battlefieldForge;
	}

	public static NonbasicLand CavesOfKoilos(Player owner){
		NonbasicLand cavesOfKoilos=new NonbasicLand(owner, "Caves of Koilos", ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, cavesOfKoilos);
		cavesOfKoilos.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 0, 0, 0, 0, 0, 1)}));
		
		MagicEffectDealDamageToTarget damageEffect=MagicEffectDealDamageToTarget.Create(owner, cavesOfKoilos, 1, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		damageEffect.TargetData.SetLockedTarget(0, owner);
		cavesOfKoilos.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{MagicEffectAddManaToPool.CreateRequiresColorDecision(owner, ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK), damageEffect}));
		return cavesOfKoilos;
	}

	public static NonbasicLand DarksteelCitadel(Player owner){
		NonbasicLand darksteelCitadel=new NonbasicLand(owner, "Darksteel Citadel", ManaCost.COLOR_FLAG_COLORLESS);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, darksteelCitadel);
		darksteelCitadel.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 0, 0, 0, 0, 0, 1)}));
		darksteelCitadel.GetKeywords().AddIndestructable();
		return darksteelCitadel;
	}

	public static NonbasicLand EvolvingWilds(Player owner){
		NonbasicLand evolvingWilds=new NonbasicLand(owner, "Evolving Wilds", ManaCost.COLOR_FLAG_COLORLESS);
		MagicEffect[] nonmanaCosts=new MagicEffect[2];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, evolvingWilds);
		nonmanaCosts[1]=new MagicEffectDiscardTargetCard(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND));
		nonmanaCosts[1].TargetData.SetLockedTarget(0, evolvingWilds);
		
		TargetInfo targetinfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_BASICLAND);
		targetinfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.LIBRARY));
		evolvingWilds.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{MagicEffectChooseCardForAction.PutOnFieldTapped(owner, targetinfo)}));
		return evolvingWilds;
	}

	public static NonbasicLand LlanowarWastes(Player owner){
		NonbasicLand llanowarWastes=new NonbasicLand(owner, "Llanowar Wastes", ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLACK);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, llanowarWastes);
		llanowarWastes.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 0, 0, 0, 0, 0, 1)}));
		
		MagicEffectDealDamageToTarget damageEffect=MagicEffectDealDamageToTarget.Create(owner, llanowarWastes, 1, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		damageEffect.TargetData.SetLockedTarget(0, owner);
		llanowarWastes.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{MagicEffectAddManaToPool.CreateRequiresColorDecision(owner, ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLACK), damageEffect}));
		return llanowarWastes;
	}

	public static NonbasicLand RadiantFountain(Player owner){
		NonbasicLand radiantFountain=new NonbasicLand(owner, "Radiant Fountain", ManaCost.COLOR_FLAG_COLORLESS);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, radiantFountain);
		radiantFountain.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 0, 0, 0, 0, 0, 1)}));
		radiantFountain.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(radiantFountain)}, new MagicEffect[]{new MagicEffectGainLife(owner, 2)}));
		return radiantFountain;
	}

	public static NonbasicLand ShivanReef(Player owner){
		NonbasicLand shivanReef=new NonbasicLand(owner, "Shivan Reef", ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_BLUE);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, shivanReef);
		shivanReef.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 0, 0, 0, 0, 0, 1)}));
		
		MagicEffectDealDamageToTarget damageEffect=MagicEffectDealDamageToTarget.Create(owner, shivanReef, 1, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		damageEffect.TargetData.SetLockedTarget(0, owner);
		shivanReef.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{MagicEffectAddManaToPool.CreateRequiresColorDecision(owner, ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_BLUE), damageEffect}));
		return shivanReef;
	}

	public static NonbasicLand YavimayaCoast(Player owner){
		NonbasicLand yavimayaCoast=new NonbasicLand(owner, "Yavimaya Coast", ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLUE);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(owner, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, yavimayaCoast);
		yavimayaCoast.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 0, 0, 0, 0, 0, 1)}));
		
		MagicEffectDealDamageToTarget damageEffect=MagicEffectDealDamageToTarget.Create(owner, yavimayaCoast, 1, 1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		damageEffect.TargetData.SetLockedTarget(0, owner);
		yavimayaCoast.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{MagicEffectAddManaToPool.CreateRequiresColorDecision(owner, ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLUE), damageEffect}));
		return yavimayaCoast;
	}
// ENDREGION LANDS
// REGION TOKENS
	public static Creature GreenBeastP3T3(Player owner){
		Creature token=Creature.NewCreature(owner, "Beast", "3/3 green Beast creature token", null, 3, 3);
		token.SetToken(ManaCost.COLOR_FLAG_GREEN);
		token.GetSubtypes().AddSubtype(SubtypeCollection.BEAST);
		return token;
	}

	public static Creature GreenInsectWithFlyingDeathtouchP1T1(Player owner){
		Creature token=Creature.NewCreature(owner, "Insect", "1/1 green Insect creature token with flying and deathtouch", null, 1, 1);
		token.SetToken(ManaCost.COLOR_FLAG_GREEN);
		token.GetKeywords().AddFlying();
		token.GetKeywords().AddDeathtouch();
		token.GetSubtypes().AddSubtype(SubtypeCollection.INSECT);
		return token;
	}

	public static Creature GreenTreefolkWarriorWithForestsPT(Player owner){
		Creature token=Creature.NewCreature(owner, "Treefolk Warrior", "*/* green Treefolk Warrior creature token with \"This creature's power and toughness are each equal to the number of Forests you control.\"", null, 0, 0);
		token.SetToken(ManaCost.COLOR_FLAG_GREEN);

		TargetInfo cardRequirements=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_BASICLAND);
		cardRequirements.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		cardRequirements.AddORName("Forest");
		MagicEffect pteffect=MagicEffectAddPTToTargetCreatureForeachPermanent.Create(owner, 1, 1, 1, 1, MagicEffect.Duration.NA, cardRequirements);
		pteffect.TargetData.SetLockedTarget(0, token);
		token.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{pteffect}));

		token.GetSubtypes().AddSubtype(SubtypeCollection.TREEFOLK);
		token.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return token;
	}

	public static Creature WhiteSoldierP1T1(Player owner){
		Creature token=Creature.NewCreature(owner, "Soldier", "1/1 white Soldier creature token", null, 1, 1);
		token.SetToken(ManaCost.COLOR_FLAG_WHITE);
		token.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return token;
	}

	public static Creature WhiteSpiritWithFlyingP1T1(Player owner){
		Creature token=Creature.NewCreature(owner, "Spirit", "1/1 white Spirit creature token with flying", null, 1, 1);
		token.SetToken(ManaCost.COLOR_FLAG_WHITE);
		token.GetKeywords().AddFlying();
		token.GetSubtypes().AddSubtype(SubtypeCollection.SPIRIT);
		return token;
	}
// ENDREGION TOKENS
}
