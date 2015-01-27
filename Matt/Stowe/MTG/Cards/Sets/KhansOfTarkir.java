package Matt.Stowe.MTG.Cards.Sets;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.SpecificEffects.*;
import Matt.Stowe.MTG.Mechanics.ContinuousAbilities.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;

public class KhansOfTarkir{
	public static String[] MythicRares=new String[]{"Anafenza, the Foremost", "Ashcloud Phoenix", "Clever Impersonator", "Empty the Pits", "Hooded Hydra", "Narset, Enlightened Master", "Pearl Lake Ancient", "Sarkhan, the Dragonspeaker", "See the Unwritten", "Sidisi, Brood Tyrant", "Sorin, Solemn Visitor", "Surrak Dragonclaw", "Ugin's Nexus", "Wingmate Roc", "Zurgo Helmsmasher"};
	public static String[] Rares=new String[]{"Abzan Ascendancy", "Altar of the Brood", "Ankle Shanker", "Avalanche Tusker", "Bloodsoaked Champion", "Bloodstained Mire", "Butcher of the Horde", "Crackling Doom", "Crater's Claws", "Deflecting Palm", "Dig Through Time", "Dragon Throne of Tarkir", "Dragon-Style Twins", "Duneblast", "End Hostilities", "Flooded Strand", "Flying Crane Technique", "Ghostfire Blade", "Grim Haruspex", "Hardened Scales", "Herald of Anafenza", "High Sentinels of Arashin", "Howl of the Horde", "Icy Blast", "Ivorytusk Fortress", "Jeering Instigator", "Jeskai Ascendancy", "Kheru Lich Lord", "Kheru Spellsnatcher", "Mantis Rider", "Mardu Ascendancy", "Master of Pearls", "Meandering Towershell", "Mindswipe", "Necropolis Fiend", "Polluted Delta", "Rakshasa Deathdealer", "Rakshasa Vizier", "Rattleclaw Mystic", "Retribution of the Ancients", "Sage of the Inward Eye", "Sagu Mauler", "Savage Knuckleblade", "Siege Rhino", "Sultai Ascendancy", "Temur Ascendancy", "Thousand Winds", "Trail of Mystery", "Trap Essence", "Utter End", "Villainous Wealth", "Windswept Heath", "Wooded Foothills"};
	public static String[] Uncommons=new String[]{"Abzan Battle Priest", "Abzan Charm", "Abzan Falconer", "Arc Lightning", "Armament Corps", "Bear's Companion", "Become Immense", "Bellowing Saddlebrute", "Blinding Spray", "Brave the Sands", "Briber's Purse", "Burn Away", "Chief of the Edge", "Chief of the Scale", "Cranial Archive", "Dazzling Ramparts", "Dead Drop", "Death Frenzy", "Despise", "Dragon Grip", "Dragon's Eye Savants", "Frontier Bivouac", "Goblinslide", "Gurmag Swiftwing", "Heart-Piercer Bow", "Heir of the Wilds", "Highspire Mantis", "Horde Ambusher", "Hordeling Outburst", "Icefeather Aven", "Incremental Growth", "Jeskai Charm", "Jeskai Elder", "Kheru Bloodsucker", "Kin-Tree Invocation", "Mardu Blazebringer", "Mardu Charm", "Mardu Heart-Piercer", "Mardu Roughrider", "Master the Way", "Mer-Ek Nightblade", "Mistfire Weaver", "Monastery Swiftspear", "Murderous Cut", "Mystic Monastery", "Nomad Outpost", "Opulent Palace", "Pine Walker", "Quiet Contemplation", "Raiders' Spoils", "Ride Down", "Riverwheel Aerialists", "Roar of Challenge", "Ruthless Ripper", "Sandsteppe Citadel", "Scion of Glaciers", "Secret Plans", "Seek the Horizon", "Seeker of the Way", "Set Adrift", "Stubborn Denial", "Sultai Charm", "Sultai Flayer", "Sultai Soothsayer", "Suspension Field", "Swarm of Bloodflies", "Take Up Arms", "Temur Charger", "Temur Charm", "Timely Hordemate", "Tomb of the Spirit Dragon", "Tuskguard Captain", "Venerable Lammasu", "Warden of the Eye", "War-Name Aspirant", "Watcher of the Roost", "Waterwhirl", "Windstorm", "Winterflame", "Witness of the Ages"};
	public static String[] Commons=new String[]{"Forest", "Island", "Mountain", "Plains", "Swamp", "Abomination of Gudul", "Abzan Banner", "Abzan Guide", "Act of Treason", "Ainok Bond-Kin", "Ainok Tracker", "Alabaster Kirin", "Alpine Grizzly", "Archers' Parapet", "Arrow Storm", "Awaken the Bear", "Barrage of Boulders", "Bitter Revelation", "Bloodfell Caves", "Bloodfire Expert", "Bloodfire Mentor", "Blossoming Sands", "Bring Low", "Cancel", "Canyon Lurkers", "Crippling Chill", "Debilitating Injury", "Defiant Strike", "Disdainful Stroke", "Dismal Backwater", "Disowned Ancestor", "Dragonscale Boon", "Dutiful Return", "Efreet Weaponmaster", "Embodiment of Spring", "Erase", "Feat of Resistance", "Feed the Clan", "Firehoof Cavalry", "Force Away", "Glacial Stalker", "Highland Game", "Hooting Mandrills", "Jeskai Banner", "Jeskai Student", "Jeskai Windscout", "Jungle Hollow", "Kheru Dreadmaw", "Kill Shot", "Kin-Tree Warden", "Krumar Bond-Kin", "Leaping Master", "Lens of Clarity", "Longshot Squad", "Mardu Banner", "Mardu Hateblade", "Mardu Hordechief", "Mardu Skullhunter", "Mardu Warshrieker", "Molting Snakeskin", "Monastery Flock", "Mystic of the Hidden Way", "Naturalize", "Ponyback Brigade", "Rakshasa's Secret", "Rite of the Serpent", "Rotting Mastodon", "Rugged Highlands", "Rush of Battle", "Sage-Eye Harrier", "Sagu Archer", "Salt Road Patrol", "Savage Punch", "Scaldkin", "Scoured Barrens", "Scout the Borders", "Shambling Attendants", "Shatter", "Sidisi's Pet", "Siegecraft", "Singing Bell Strike", "Smite the Monstrous", "Smoke Teller", "Snowhorn Rider", "Sultai Banner", "Sultai Scavenger", "Summit Prowler", "Swift Kick", "Swiftwater Cliffs", "Taigam's Scheming", "Temur Banner", "Thornwood Falls", "Throttle", "Tormenting Voice", "Tranquil Cove", "Treasure Cruise", "Trumpet Blast", "Tusked Colossodon", "Unyielding Krumar", "Valley Dasher", "War Behemoth", "Weave Fate", "Wetland Sambar", "Whirlwind Adept", "Wind-Scarred Crag", "Woolly Loxodon"};

	private static void addOutlastAbility(Player owner, Creature creature, ManaCost cost){
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, creature);
		MagicEffectTapTargetCard tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);
		
		MagicEffectAddPTCountersToTargetCreature outlastEffect=new MagicEffectAddPTCountersToTargetCreature(owner, 1, selfTargetInfo);
		
		creature.AddActivatedAbility(new MagicActivatedAbility(true, cost, new MagicEffect[]{tapEffect}, new MagicEffect[]{outlastEffect}));
	}
	
	private static void addProwessTrigger(Player owner, Creature creature){
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, creature);
		
		TargetInfo myNonCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		myNonCreatureTargetInfo.SetTargetTypeNOTFlags(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myNonCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myNonCreatureTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.STACK));
		
		creature.AddSpellCastTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(myNonCreatureTargetInfo)}, new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, 1, 1, selfTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)}));
	}
	
	private static void addMorphAbility(Player owner, Creature creature, ManaCost morphCost){
		creature.GetKeywords().AddMorph();
		creature.AddActivatedAbility(new MagicActivatedAbility(false, morphCost, null, new MagicEffect[]{new MagicEffectMorphTargetCreature(owner, creature)}));
	}
	
	private static void addMorphAbilityRevealCard(Player owner, Creature creature, int colorflags){
		creature.GetKeywords().AddMorph();
		TargetInfo revealTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_CARD);
		revealTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		revealTargetInfo.SetColorORFlags(colorflags);
		revealTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.HAND));
		creature.AddActivatedAbility(new MagicActivatedAbility(false, null, new MagicEffect[]{new MagicEffectRevealTargetCard(owner, revealTargetInfo)}, new MagicEffect[]{new MagicEffectMorphTargetCreature(owner, creature)}));
	}
// REGION RED
	// REGION CREATURES
	public static Creature AinokTracker(Player owner){
		Creature ainokTracker=Creature.NewCreature(owner, "Ainok Tracker", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 3, 3);

		addMorphAbility(owner, ainokTracker, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}));
		
		ainokTracker.GetKeywords().AddFirstStrike();
		ainokTracker.GetSubtypes().AddSubtype(SubtypeCollection.HOUND);
		ainokTracker.GetSubtypes().AddSubtype(SubtypeCollection.SCOUT);
		return ainokTracker;
	}

	public static Creature BloodfireExpert(Player owner){
		Creature bloodfireExpert=Creature.NewCreature(owner, "Bloodfire Expert", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 3, 1);

		addProwessTrigger(owner, bloodfireExpert);
		
		bloodfireExpert.GetSubtypes().AddSubtype(SubtypeCollection.EFREET);
		bloodfireExpert.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return bloodfireExpert;
	}

	public static Creature CanyonLurkers(Player owner){
		Creature canyonLurkers=Creature.NewCreature(owner, "Canyon Lurkers", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 5, 2);

		addMorphAbility(owner, canyonLurkers, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}));
		
		canyonLurkers.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		canyonLurkers.GetSubtypes().AddSubtype(SubtypeCollection.ROGUE);
		return canyonLurkers;
	}

	public static Creature DragonStyleTwins(Player owner){
		Creature dragonStyleTwins=Creature.NewCreature(owner, "Dragon-Style Twins", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), 3, 3);

		addProwessTrigger(owner, dragonStyleTwins);
		
		dragonStyleTwins.GetKeywords().AddDoubleStrike();
		dragonStyleTwins.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		dragonStyleTwins.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return dragonStyleTwins;
	}

	public static Creature LeapingMaster(Player owner){
		Creature leapingMaster=Creature.NewCreature(owner, "Leaping Master", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 2, 1);

		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, leapingMaster);
		
		leapingMaster.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), null, new MagicEffect[]{new MagicEffectGrantFlyingToTargetCreature(owner, selfTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)}));
		
		leapingMaster.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		leapingMaster.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return leapingMaster;
	}

	public static Creature MarduHeartPiercer(Player owner){
		Creature marduHeartPiercer=Creature.NewCreature(owner, "Mardu Heart-Piercer", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 2, 3);

		marduHeartPiercer.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionControllerAttackedThisTurn(marduHeartPiercer)}, new MagicEffect[]{new MagicEffectDealDamageToTarget(owner, marduHeartPiercer, 2, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE|TargetInfo.TARGET_TYPE_FLAG_PLAYER), false)}));

		marduHeartPiercer.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		marduHeartPiercer.GetSubtypes().AddSubtype(SubtypeCollection.ARCHER);
		return marduHeartPiercer;
	}

	public static Creature MarduWarshrieker(Player owner){
		Creature marduWarshrieker=Creature.NewCreature(owner, "Mardu Warshrieker", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), 3, 3);

		marduWarshrieker.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionControllerAttackedThisTurn(marduWarshrieker)}, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 1, 0, 0, 1, 1, 0)}));

		marduWarshrieker.GetSubtypes().AddSubtype(SubtypeCollection.ORC);
		marduWarshrieker.GetSubtypes().AddSubtype(SubtypeCollection.SHAMAN);
		return marduWarshrieker;
	}

	public static Creature MonasterySwiftspear(Player owner){
		Creature monasterySwiftspear=Creature.NewCreature(owner, "Monastery Swiftspear", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED}), 1, 2);

		addProwessTrigger(owner, monasterySwiftspear);

		monasterySwiftspear.GetKeywords().AddHaste();
		monasterySwiftspear.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		monasterySwiftspear.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return monasterySwiftspear;
	}

	public static Creature SummitProwler(Player owner){
		Creature summitProwler=Creature.NewCreature(owner, "Summit Prowler", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), 4, 3);

		summitProwler.GetSubtypes().AddSubtype(SubtypeCollection.YETI);
		return summitProwler;
	}
	// ENDREGION CREATURES
	// REGION INSTANTS AND SORCERIES
	public static Sorcery ArcLightning(Player owner){
		MagicEffect[] damageEffects=new MagicEffect[]{
			new MagicEffectDealDamageToTarget(owner, null, 1, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE|TargetInfo.TARGET_TYPE_FLAG_PLAYER), false),
			new MagicEffectDealDamageToTarget(owner, null, 1, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE|TargetInfo.TARGET_TYPE_FLAG_PLAYER), false),
			new MagicEffectDealDamageToTarget(owner, null, 1, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE|TargetInfo.TARGET_TYPE_FLAG_PLAYER), false)};
		Sorcery arcLightning=new Sorcery(owner, "Arc Lightning", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), damageEffects);
		return arcLightning;
	}

	public static Sorcery HordelingOutburst(Player owner){
		return new Sorcery(owner, "Hordeling Outburst", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{new MagicEffectPlayToken(owner, RedGoblinP1T1(owner), 3)});
	}	

	public static Instant Shatter(Player owner){
		return new Instant(owner, "Shatter", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{new MagicEffectDestroyTargetPermanents(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_ARTIFACT))});
	}

	public static Instant TrumpetBlast(Player owner){
		TargetInfo attackingTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		attackingTargetInfo.SetCombatStateORFlags(TargetInfo.COMBAT_STATE_FLAG_ATTACKING);
		return new Instant(owner, "Trumpet Blast", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, 2, 0, attackingTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN))});
	}
	// ENDREGION INSTANTS AND SORCERIES
	// REGION ENCHANTMENTS
	public static Enchantment Goblinslide(Player owner){
		Enchantment goblinslide=Enchantment.NewEnchantment(owner, "Goblinslide", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}));
		
		TargetInfo myNonCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		myNonCreatureTargetInfo.SetTargetTypeNOTFlags(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myNonCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myNonCreatureTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.STACK));
		
		MagicEffect tokenEffect=new MagicEffectPlayToken(owner, RedGoblinWithHasteP1T1(owner), 1);
		tokenEffect.AddExtraManaCost(new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS}));
		
		goblinslide.AddSpellCastTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(myNonCreatureTargetInfo)}, new MagicEffect[]{tokenEffect}));
		
		return goblinslide;
	}
	// ENDREGION ENCHANTMENTS
// ENDREGION RED
// REGION GREEN
	// REGION CREATURES
	public static Creature AlpineGrizzly(Player owner){
		Creature alpineGrizzly=Creature.NewCreature(owner, "Alpine Grizzly", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 4, 2);
		
		alpineGrizzly.GetSubtypes().AddSubtype(SubtypeCollection.BEAR);
		return alpineGrizzly;
	}

	public static Creature ArchersParapet(Player owner){
		Creature archersParapet=Creature.NewCreature(owner, "Archers' Parapet", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 0, 5);

		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, archersParapet);
		MagicEffect tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);
		
		TargetInfo ownerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		ownerTargetInfo.SetLockedTarget(0, owner);
		TargetInfo opponentTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER, new TargetInfo[]{ownerTargetInfo});
		
		archersParapet.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), new MagicEffect[]{tapEffect}, new MagicEffect[]{new MagicEffectToAllPlayers(new MagicEffectLoseLifeTargetPlayer(owner, 1, opponentTargetInfo))}));
		
		archersParapet.GetKeywords().AddDefender();
		archersParapet.GetSubtypes().AddSubtype(SubtypeCollection.WALL);
		return archersParapet;
	}
	
	public static Creature HootingMandrills(Player owner){
		Creature hootingMandrills=Creature.NewCreature(owner, "Hooting Mandrills", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 4, 4);
		
		hootingMandrills.GetKeywords().AddTrample();
		hootingMandrills.GetKeywords().AddDelve();
		hootingMandrills.GetSubtypes().AddSubtype(SubtypeCollection.APE);
		return hootingMandrills;
	}

	public static Creature LongshotSquad(Player owner){
		Creature longshotSquad=Creature.NewCreature(owner, "Longshot Squad", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 3, 3);
		
		addOutlastAbility(owner, longshotSquad, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}));
		
		TargetInfo myCreatureWithPTCountersTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myCreatureWithPTCountersTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myCreatureWithPTCountersTargetInfo.SetMinPTCounters(1);
		longshotSquad.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectGrantReachToTargetCreature(owner, myCreatureWithPTCountersTargetInfo, MagicEffect.Duration.NA)}));
		
		longshotSquad.GetSubtypes().AddSubtype(SubtypeCollection.HOUND);
		longshotSquad.GetSubtypes().AddSubtype(SubtypeCollection.ARCHER);
		return longshotSquad;
	}
	
	public static Creature PineWalker(Player owner){
		Creature pineWalker=Creature.NewCreature(owner, "Pine Walker", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), 5, 5);

		addMorphAbility(owner, pineWalker, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}));
		
		TargetInfo myCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		TriggeredAbility morphTrigger=new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(myCreatureTargetInfo)}, new MagicEffect[]{new MagicEffectUntapTargetCard(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE))});
		morphTrigger.SetRelationship(0, 0, TriggeredAbility.ConditionEffectRelationship.ACTOR_IS_TARGET);
		pineWalker.AddMorphTrigger(morphTrigger);
		
		pineWalker.GetSubtypes().AddSubtype(SubtypeCollection.ELEMENTAL);
		return pineWalker;
	}

	public static Creature RattleclawMystic(Player owner){
		Creature rattleclawMystic=Creature.NewCreature(owner, "Rattleclaw Mystic", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 2, 1);

		addMorphAbility(owner, rattleclawMystic, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}));
		
		rattleclawMystic.AddMorphTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(rattleclawMystic)}, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 1, 1, 1, 0, 0, 0)}));
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, rattleclawMystic);
		MagicEffectTapTargetCard tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);
		rattleclawMystic.AddActivatedAbility(new MagicActivatedAbility(false, null, new MagicEffect[]{tapEffect}, new MagicEffect[]{MagicEffectAddManaToPool.CreateRequiresColorDecision(owner, ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLUE)}));
		
		rattleclawMystic.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		rattleclawMystic.GetSubtypes().AddSubtype(SubtypeCollection.SHAMAN);
		return rattleclawMystic;
	}

	public static Creature SaguArcher(Player owner){
		Creature saguArcher=Creature.NewCreature(owner, "Sagu Archer", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 2, 5);

		addMorphAbility(owner, saguArcher, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}));
		
		saguArcher.GetKeywords().AddReach();
		saguArcher.GetSubtypes().AddSubtype(SubtypeCollection.NAGA);
		saguArcher.GetSubtypes().AddSubtype(SubtypeCollection.ARCHER);
		return saguArcher;
	}

	public static Creature TemurCharger(Player owner){
		Creature temurCharger=Creature.NewCreature(owner, "Temur Charger", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 3, 1);

		addMorphAbilityRevealCard(owner, temurCharger, ManaCost.COLOR_FLAG_GREEN);
		
		temurCharger.AddMorphTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(temurCharger)}, new MagicEffect[]{new MagicEffectGrantTrampleToTargetCreature(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), MagicEffect.Duration.UNTIL_END_OF_TURN)}));
		
		temurCharger.GetSubtypes().AddSubtype(SubtypeCollection.HORSE);
		return temurCharger;
	}

	public static Creature TuskedColossodon(Player owner){
		Creature tuskedColossodon=Creature.NewCreature(owner, "Tusked Colossodon", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), 6, 5);
		
		tuskedColossodon.GetSubtypes().AddSubtype(SubtypeCollection.BEAST);
		return tuskedColossodon;
	}

	public static Creature TuskguardCaptain(Player owner){
		Creature tuskguardCaptain=Creature.NewCreature(owner, "Tuskguard Captain", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), 2, 3);
		
		addOutlastAbility(owner, tuskguardCaptain, new ManaCost(new int[]{ManaCost.COLOR_FLAG_GREEN}));
		
		TargetInfo myCreatureWithPTCountersTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myCreatureWithPTCountersTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myCreatureWithPTCountersTargetInfo.SetMinPTCounters(1);
		tuskguardCaptain.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectGrantTrampleToTargetCreature(owner, myCreatureWithPTCountersTargetInfo, MagicEffect.Duration.NA)}));
		
		tuskguardCaptain.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		tuskguardCaptain.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return tuskguardCaptain;
	}

	public static Creature WoollyLoxodon(Player owner){
		Creature woollyLoxodon=Creature.NewCreature(owner, "Woolly Loxodon", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), 6, 7);
		
		addMorphAbility(owner, woollyLoxodon, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}));
		
		woollyLoxodon.GetSubtypes().AddSubtype(SubtypeCollection.ELEPHANT);
		woollyLoxodon.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return woollyLoxodon;
	}
	// ENDREGION CREATURES
	// REGION INSTANTS AND SORCERIES
	public static Instant AwakenTheBear(Player owner){
		MagicEffect[] effects=new MagicEffect[]{
			new MagicEffectAddPTToTargetCreature(owner, 3, 3, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), MagicEffect.Duration.UNTIL_END_OF_TURN),
			null};
		effects[1]=new MagicEffectGrantTrampleToTargetCreature(owner, new TargetInfo(effects[0].TargetData), MagicEffect.Duration.UNTIL_END_OF_TURN);
		return new Instant(owner, "Awaken the Bear", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), effects);
	}

	public static Instant BecomeImmense(Player owner){
		Instant becomeImmense=new Instant(owner, "Become Immense", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, 6, 6, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), MagicEffect.Duration.UNTIL_END_OF_TURN)});
		becomeImmense.GetKeywords().AddDelve();
		return becomeImmense;
	}

	public static Instant DragonscaleBoon(Player owner){
		MagicEffect[] effects=new MagicEffect[]{
			new MagicEffectAddPTCountersToTargetCreature(owner, 2, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE)),
			null};
		effects[1]=new MagicEffectUntapTargetCard(owner, new TargetInfo(effects[0].TargetData));
		return new Instant(owner, "Dragonscale Boon", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), effects);
	}

	public static Sorcery IncrementalGrowth(Player owner){
		MagicEffect[] effects=new MagicEffect[3];
		effects[0]=new MagicEffectAddPTCountersToTargetCreature(owner, 1, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE));
		effects[1]=new MagicEffectAddPTCountersToTargetCreature(owner, 2, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, new TargetInfo[]{effects[0].TargetData}));
		effects[2]=new MagicEffectAddPTCountersToTargetCreature(owner, 3, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, new TargetInfo[]{effects[0].TargetData, effects[1].TargetData}));
		return new Sorcery(owner, "Incremental Growth", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_GREEN}), effects);
	}

	public static Sorcery SeekTheHorizon(Player owner){
		TargetInfo targetinfo=new TargetInfo(0, 3, TargetInfo.TARGET_TYPE_FLAG_BASICLAND);
		targetinfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.LIBRARY));

		return new Sorcery(owner, "Seek the Horizon", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{MagicEffectChooseCardForAction.PutInHand(owner, targetinfo)});
	}

	public static Instant Windstorm(Player owner){
		TargetInfo flyingTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		//TODO: Require flying in TargetInfo

		MagicEffect damageEffect=new MagicEffectDealDamageToTarget(owner, null, 0, flyingTargetInfo, true);
		Instant windstorm=new Instant(owner, "Windstorm", "", new ManaCost(new int[]{ManaCost.COLOR_COST_X, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{damageEffect});
		return windstorm;
	}
	// ENDREGION INSTANTS AND SORCERIES
	// REGION ENCHANTMENTS
	public static Enchantment TrailOfMystery(Player owner){
		Enchantment trailOfMystery=Enchantment.NewEnchantment(owner, "Trail of Mystery", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}));
		
		TargetInfo mymorphedcreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mymorphedcreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		mymorphedcreatureTargetInfo.MustBeFaceDown();
		
		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		TargetInfo basiclandtargetinfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_BASICLAND);
		basiclandtargetinfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.LIBRARY));
		MagicEffect searchLibraryEffect=MagicEffectChooseCardForAction.PutInHand(owner, basiclandtargetinfo);
		searchLibraryEffect.SetOptional();

		trailOfMystery.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(mymorphedcreatureTargetInfo)}, new MagicEffect[]{searchLibraryEffect}));
		
		TriggeredAbility morphTrigger=new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(mycreatureTargetInfo)}, new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, 2, 2, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), MagicEffect.Duration.UNTIL_END_OF_TURN)});
		morphTrigger.SetRelationship(0, 0, TriggeredAbility.ConditionEffectRelationship.ACTOR_IS_TARGET);
		trailOfMystery.AddMorphTrigger(morphTrigger);
		
		return trailOfMystery;
	}
	// ENDREGION ENCHANTMENTS
// ENDREGION GREEN
// REGION BLUE
	// REGION CREATURES
	public static Creature DragonsEyeSavants(Player owner){
		Creature dragonsEyeSavants=Creature.NewCreature(owner, "Dragon's Eye Savants", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 0, 6);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, dragonsEyeSavants);
		
		addMorphAbilityRevealCard(owner, dragonsEyeSavants, ManaCost.COLOR_FLAG_BLUE);
		
		TargetInfo playerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		playerTargetInfo.SetLockedTarget(0, owner);
		TargetInfo opponentTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER, new TargetInfo[]{playerTargetInfo});
		dragonsEyeSavants.AddMorphTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(dragonsEyeSavants)}, new MagicEffect[]{new MagicEffectLookAtHandOfTargetPlayer(owner, opponentTargetInfo)}));
		
		dragonsEyeSavants.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		dragonsEyeSavants.GetSubtypes().AddSubtype(SubtypeCollection.WIZARD);
		return dragonsEyeSavants;
	}
	
	public static Creature EmbodimentOfSpring(Player owner){
		Creature embodimentOfSpring=Creature.NewCreature(owner, "Embodiment of Spring", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE}), 0, 3);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, embodimentOfSpring);
		MagicEffectTapTargetCard tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);
		
		TargetInfo libraryBasicLandTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_BASICLAND);
		libraryBasicLandTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.LIBRARY));
		
		MagicEffect searchEffect=MagicEffectChooseCardForAction.PutOnFieldTapped(owner, libraryBasicLandTargetInfo);
		
		embodimentOfSpring.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN}), new MagicEffect[]{tapEffect, new MagicEffectDiscardCard(owner, embodimentOfSpring)}, new MagicEffect[]{searchEffect}));
		
		embodimentOfSpring.GetSubtypes().AddSubtype(SubtypeCollection.ELEMENTAL);
		return embodimentOfSpring;
	}

	public static Creature GlacialStalker(Player owner){
		Creature glacialStalker=Creature.NewCreature(owner, "Glacial Stalker", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 4, 5);
		
		addMorphAbility(owner, glacialStalker, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}));
		
		glacialStalker.GetSubtypes().AddSubtype(SubtypeCollection.ELEMENTAL);
		return glacialStalker;
	}	

	public static Creature JeskaiWindscout(Player owner){
		Creature jeskaiWindscout=Creature.NewCreature(owner, "Jeskai Windscout", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 2, 1);
		
		addProwessTrigger(owner, jeskaiWindscout);
		
		jeskaiWindscout.GetKeywords().AddFlying();
		jeskaiWindscout.GetSubtypes().AddSubtype(SubtypeCollection.BIRD);
		jeskaiWindscout.GetSubtypes().AddSubtype(SubtypeCollection.SCOUT);
		return jeskaiWindscout;
	}

	public static Creature MonasteryFlock(Player owner){
		Creature monasteryFlock=Creature.NewCreature(owner, "Monastery Flock", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 0, 5);
		
		addMorphAbility(owner, monasteryFlock, new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE}));
		
		monasteryFlock.GetKeywords().AddDefender();
		monasteryFlock.GetKeywords().AddFlying();
		monasteryFlock.GetSubtypes().AddSubtype(SubtypeCollection.BIRD);
		return monasteryFlock;
	}	

	public static Creature MysticOfTheHiddenWay(Player owner){
		Creature mysticOfTheHiddenWay=Creature.NewCreature(owner, "Mystic of the Hidden Way", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS,  ManaCost.COLOR_FLAG_BLUE}), 3, 2);
		
		addMorphAbility(owner, mysticOfTheHiddenWay, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}));
		
		mysticOfTheHiddenWay.GetKeywords().AddUnblockable();
		mysticOfTheHiddenWay.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		mysticOfTheHiddenWay.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return mysticOfTheHiddenWay;
	}	

	public static Creature RiverwheelAerialists(Player owner){
		Creature riverwheelAerialists=Creature.NewCreature(owner, "Riverwheel Aerialists", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 4, 5);
		
		addProwessTrigger(owner, riverwheelAerialists);
		
		riverwheelAerialists.GetKeywords().AddFlying();
		riverwheelAerialists.GetSubtypes().AddSubtype(SubtypeCollection.DJINN);
		riverwheelAerialists.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return riverwheelAerialists;
	}

	public static Creature Scaldkin(Player owner){
		Creature scaldkin=Creature.NewCreature(owner, "Scaldkin", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 2, 2);

		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, scaldkin);

		scaldkin.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), new MagicEffect[]{new MagicEffectDiscardCard(owner, scaldkin)}, new MagicEffect[]{new MagicEffectDealDamageToTarget(owner, scaldkin, 2, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE|TargetInfo.TARGET_TYPE_FLAG_PLAYER), false)}));
		
		scaldkin.GetKeywords().AddFlying();
		scaldkin.GetSubtypes().AddSubtype(SubtypeCollection.ELEMENTAL);
		return scaldkin;
	}

	public static Creature ScionOfGlaciers(Player owner){
		Creature scionOfGlaciers=Creature.NewCreature(owner, "Scion of Glaciers", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_BLUE}), 2, 5);

		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, scionOfGlaciers);

		scionOfGlaciers.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE}), null, new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, 1, -1, selfTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)}));
		
		scionOfGlaciers.GetSubtypes().AddSubtype(SubtypeCollection.ELEMENTAL);
		return scionOfGlaciers;
	}

	public static Creature WetlandSambar(Player owner){
		Creature wetlandSambar=Creature.NewCreature(owner, "Wetland Sambar", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), 2, 1);
		wetlandSambar.GetSubtypes().AddSubtype(SubtypeCollection.ELK);
		return wetlandSambar;
	}
	// ENDREGION CREATURES
	// REGION INSTANTS AND SORCERIES
	public static Instant BlindingSpray(Player owner){
		TargetInfo notmyCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		notmyCreatureTargetInfo.TargetMustNotBeControlledByIndex(owner.GetIndex(), owner.GetName());

		TargetInfo controllerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		controllerTargetInfo.SetLockedTarget(0, owner);
		return new Instant(owner, "Blinding Spray", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, -4, 0, notmyCreatureTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)), new MagicEffectDrawCardsForTargetPlayer(owner, 1, controllerTargetInfo)});
	}	

	public static Instant CripplingChill(Player owner){
		TargetInfo notmyCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		notmyCreatureTargetInfo.TargetMustNotBeControlledByIndex(owner.GetIndex(), owner.GetName());

		TargetInfo controllerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		controllerTargetInfo.SetLockedTarget(0, owner);
		return new Instant(owner, "Crippling Chill", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{new MagicEffectTapAndSkipUntapTargetCard(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE)), new MagicEffectDrawCardsForTargetPlayer(owner, 1, controllerTargetInfo)});
	}	

	public static Instant DisdainfulStroke(Player owner){
		TargetInfo targetinfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_CARD);
		targetinfo.SetConvertedManaCostMin(4);
		return new Instant(owner, "Disdainful Stroke", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{new MagicEffectCancelSpell(owner, targetinfo)});
	}	

	public static Sorcery TreasureCruise(Player owner){
		TargetInfo controllerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		controllerTargetInfo.SetLockedTarget(0, owner);
		Sorcery treasureCruise=new Sorcery(owner, "Treasure Cruise", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{new MagicEffectDrawCardsForTargetPlayer(owner, 3, controllerTargetInfo)});
		treasureCruise.GetKeywords().AddDelve();
		return treasureCruise;
	}

	public static Instant WeaveFate(Player owner){
		TargetInfo controllerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		controllerTargetInfo.SetLockedTarget(0, owner);
		return new Instant(owner, "Weave Fate", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{new MagicEffectDrawCardsForTargetPlayer(owner, 2, controllerTargetInfo)});
	}	
	// ENDREGION INSTANTS AND SORCERIES
	// REGION ENCHANTMENTS
	public static Enchantment QuietContemplation(Player owner){
		Enchantment quietContemplation=Enchantment.NewEnchantment(owner, "Quiet Contemplation", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}));
		
		TargetInfo notmycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		notmycreatureTargetInfo.TargetMustNotBeControlledByIndex(owner.GetIndex(), owner.GetName());
		
		TargetInfo myNonCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		myNonCreatureTargetInfo.SetTargetTypeNOTFlags(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myNonCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myNonCreatureTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.STACK));
		
		MagicEffect tapskipEffect=new MagicEffectTapAndSkipUntapTargetCard(owner, notmycreatureTargetInfo);
		tapskipEffect.AddExtraManaCost(new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS}));
		
		quietContemplation.AddSpellCastTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(myNonCreatureTargetInfo)}, new MagicEffect[]{tapskipEffect}));
		
		return quietContemplation;
	}
	
	public static Enchantment SingingBellStrike(Player owner){
		MagicEffect[] singingBellStrikeEffects=new MagicEffect[]{
			new MagicEffectTapTargetCard(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE)),
			new MagicEffectPreventUntappingTargetCard(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE)),
			null
			};
			
		TargetInfo enchantedCreatureTargetData=new TargetInfo(singingBellStrikeEffects[0].TargetData);
		MagicActivatedAbility untapAbility=new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), null, new MagicEffect[]{new MagicEffectUntapTargetCard(owner, enchantedCreatureTargetData)});
		singingBellStrikeEffects[2]=new MagicEffectGrantActivatedAbilityToTargetPermanent(owner, untapAbility, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), MagicEffect.Duration.NA);
			
		return Enchantment.NewEnchantmentAura(owner, "Singing Bell Strike", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, singingBellStrikeEffects);
	}
	// ENDREGION ENCHANTMENTS
// ENDREGION BLUE
// REGION WHITE
	// REGION CREATURES
	public static Creature AbzanBattlePriest(Player owner){
		Creature abzanBattlePriest=Creature.NewCreature(owner, "Abzan Battle Priest", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 3, 2);
		
		addOutlastAbility(owner, abzanBattlePriest, new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}));
		
		TargetInfo myCreatureWithPTCountersTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myCreatureWithPTCountersTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myCreatureWithPTCountersTargetInfo.SetMinPTCounters(1);
		abzanBattlePriest.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectGrantLifelinkToTargetCreature(owner, myCreatureWithPTCountersTargetInfo, MagicEffect.Duration.NA)}));
		
		abzanBattlePriest.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		abzanBattlePriest.GetSubtypes().AddSubtype(SubtypeCollection.CLERIC);
		return abzanBattlePriest;
	}

	public static Creature AbzanFalconer(Player owner){
		Creature abzanFalconer=Creature.NewCreature(owner, "Abzan Falconer", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 3);
		
		addOutlastAbility(owner, abzanFalconer, new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}));
		
		TargetInfo myCreatureWithPTCountersTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myCreatureWithPTCountersTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myCreatureWithPTCountersTargetInfo.SetMinPTCounters(1);
		abzanFalconer.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectGrantFlyingToTargetCreature(owner, myCreatureWithPTCountersTargetInfo, MagicEffect.Duration.NA)}));
		
		abzanFalconer.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		abzanFalconer.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return abzanFalconer;
	}

	public static Creature AinokBondKin(Player owner){
		Creature ainokBondKin=Creature.NewCreature(owner, "Ainok Bond-Kin", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 1);
		
		addOutlastAbility(owner, ainokBondKin, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}));
		
		TargetInfo myCreatureWithPTCountersTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myCreatureWithPTCountersTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myCreatureWithPTCountersTargetInfo.SetMinPTCounters(1);
		ainokBondKin.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectGrantFirstStrikeToTargetCreature(owner, myCreatureWithPTCountersTargetInfo, MagicEffect.Duration.NA)}));
		
		ainokBondKin.GetSubtypes().AddSubtype(SubtypeCollection.HOUND);
		ainokBondKin.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return ainokBondKin;
	}

	public static Creature AlabasterKirin(Player owner){
		Creature alabasterKirin=Creature.NewCreature(owner, "Alabaster Kirin", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 3);
		
		alabasterKirin.GetKeywords().AddFlying();
		alabasterKirin.GetKeywords().AddVigilance();
		alabasterKirin.GetSubtypes().AddSubtype(SubtypeCollection.KIRIN);
		return alabasterKirin;
	}

	public static Creature DazzlingRamparts(Player owner){
		Creature dazzlingRamparts=Creature.NewCreature(owner, "Dazzling Ramparts", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 0, 7);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, dazzlingRamparts);
		MagicEffectTapTargetCard tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);

		dazzlingRamparts.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{tapEffect}, new MagicEffect[]{new MagicEffectTapTargetCard(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE))}));

		dazzlingRamparts.GetKeywords().AddDefender();
		dazzlingRamparts.GetSubtypes().AddSubtype(SubtypeCollection.WALL);
		return dazzlingRamparts;
	}

	public static Creature FirehoofCavalry(Player owner){
		Creature firehoofCavalry=Creature.NewCreature(owner, "Firehoof Cavalry", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}), 1, 1);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, firehoofCavalry);
		MagicEffectTapTargetCard tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);

		firehoofCavalry.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED}), null, new MagicEffect[]{new MagicEffectGrantTrampleToTargetCreature(owner, selfTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN), new MagicEffectAddPTToTargetCreature(owner, 2, 0, selfTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)}));

		firehoofCavalry.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		firehoofCavalry.GetSubtypes().AddSubtype(SubtypeCollection.BERSERKER);
		return firehoofCavalry;
	}

	public static Creature HighSentinelsOfArashin(Player owner){
		Creature highSentinelsOfArashin=Creature.NewCreature(owner, "High Sentinels of Arashin", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 3, 4);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, highSentinelsOfArashin);
		TargetInfo cardRequirements=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, new TargetInfo[]{selfTargetInfo});
		cardRequirements.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		cardRequirements.SetMinPTCounters(1);
		highSentinelsOfArashin.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectAddPTToTargetCreatureForeachPermanent(owner, 1, 1, selfTargetInfo, MagicEffect.Duration.NA, cardRequirements)}));

		highSentinelsOfArashin.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), null, new MagicEffect[]{new MagicEffectAddPTCountersToTargetCreature(owner, 1, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE))}));

		highSentinelsOfArashin.GetKeywords().AddFlying();
		highSentinelsOfArashin.GetSubtypes().AddSubtype(SubtypeCollection.BIRD);
		highSentinelsOfArashin.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return highSentinelsOfArashin;
	}
	
	public static Creature JeskaiStudent(Player owner){
		Creature jeskaiStudent=Creature.NewCreature(owner, "Jeskai Student", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 1, 3);
		
		addProwessTrigger(owner, jeskaiStudent);

		jeskaiStudent.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		jeskaiStudent.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return jeskaiStudent;
	}
	
	public static Creature MarduHateblade(Player owner){
		Creature marduHateblade=Creature.NewCreature(owner, "Mardu Hateblade", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}), 1, 1);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, marduHateblade);
		
		marduHateblade.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLACK}), null, new MagicEffect[]{new MagicEffectGrantDeathtouchToTargetCreature(owner, selfTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)}));

		marduHateblade.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		marduHateblade.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return marduHateblade;
	}	

	public static Creature MarduHordechief(Player owner){
		Creature marduHordechief=Creature.NewCreature(owner, "Mardu Hordechief", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 3);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, marduHordechief);
		
		marduHordechief.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionControllerAttackedThisTurn(marduHordechief)}, new MagicEffect[]{new MagicEffectPlayToken(owner, WhiteWarriorP1T1(owner), 1)}));

		marduHordechief.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		marduHordechief.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return marduHordechief;
	}	

	public static Creature MasterOfPearls(Player owner){
		Creature masterOfPearls=Creature.NewCreature(owner, "Master of Pearls", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 2);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, masterOfPearls);
		
		addMorphAbility(owner, masterOfPearls, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}));

		TargetInfo targetinfoMine=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetinfoMine.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		MagicEffect pteffect=new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, 2, 2, targetinfoMine, MagicEffect.Duration.UNTIL_END_OF_TURN));
		masterOfPearls.AddMorphTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(masterOfPearls)}, new MagicEffect[]{pteffect}));
		
		masterOfPearls.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		masterOfPearls.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return masterOfPearls;
	}	

	public static Creature SageEyeHarrier(Player owner){
		Creature sageEyeHarrier=Creature.NewCreature(owner, "Sage-Eye Harrier", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 1, 5);
		
		addMorphAbility(owner, sageEyeHarrier, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}));

		sageEyeHarrier.GetKeywords().AddFlying();
		sageEyeHarrier.GetSubtypes().AddSubtype(SubtypeCollection.BIRD);
		sageEyeHarrier.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return sageEyeHarrier;
	}	

	public static Creature SaltRoadPatrol(Player owner){
		Creature saltRoadPatrol=Creature.NewCreature(owner, "Salt Road Patrol", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 5);
		
		addOutlastAbility(owner, saltRoadPatrol, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}));

		saltRoadPatrol.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		saltRoadPatrol.GetSubtypes().AddSubtype(SubtypeCollection.SCOUT);
		return saltRoadPatrol;
	}

	public static Creature SeekerOfTheWay(Player owner){
		Creature seekerOfTheWay=Creature.NewCreature(owner, "Seeker of the Way", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 2);
		
		addProwessTrigger(owner, seekerOfTheWay);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, seekerOfTheWay);
		
		TargetInfo myNonCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		myNonCreatureTargetInfo.SetTargetTypeNOTFlags(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myNonCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myNonCreatureTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.STACK));
		
		seekerOfTheWay.AddSpellCastTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(myNonCreatureTargetInfo)}, new MagicEffect[]{new MagicEffectGrantLifelinkToTargetCreature(owner, selfTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)}));

		seekerOfTheWay.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		seekerOfTheWay.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return seekerOfTheWay;
	}

	public static Creature TimelyHordemate(Player owner){
		Creature timelyHordemate=Creature.NewCreature(owner, "Timely Hordemate", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 3, 2);
		
		TargetInfo cardReqs=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		cardReqs.SetConvertedManaCostMax(2);
		cardReqs.SetValidTargetZones(new ZoneOptions(ZoneOptions.GRAVEYARD));
		cardReqs.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		MagicEffect effect=new MagicEffectPlaceTargetPermanentOnField(owner, cardReqs);

		timelyHordemate.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionControllerAttackedThisTurn(timelyHordemate)}, new MagicEffect[]{effect}));
		
		timelyHordemate.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		timelyHordemate.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return timelyHordemate;
	}

	public static Creature VenerableLammasu(Player owner){
		Creature venerableLammasu=Creature.NewCreature(owner, "Venerable Lammasu", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 5, 4);
		
		venerableLammasu.GetKeywords().AddFlying();
		venerableLammasu.GetSubtypes().AddSubtype(SubtypeCollection.LAMMASU);
		return venerableLammasu;
	}

	public static Creature WarBehemoth(Player owner){
		Creature warBehemoth=Creature.NewCreature(owner, "War Behemoth", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 3, 6);
		
		addMorphAbility(owner, warBehemoth, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}));

		warBehemoth.GetSubtypes().AddSubtype(SubtypeCollection.BEAST);
		return warBehemoth;
	}	

	public static Creature WatcherOfTheRoost(Player owner){
		Creature watcherOfTheRoost=Creature.NewCreature(owner, "Watcher of the Roost", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), 2, 1);
		
		addMorphAbilityRevealCard(owner, watcherOfTheRoost, ManaCost.COLOR_FLAG_WHITE);
		
		watcherOfTheRoost.AddMorphTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(watcherOfTheRoost)}, new MagicEffect[]{new MagicEffectGainLife(owner, 2)}));

		watcherOfTheRoost.GetKeywords().AddFlying();
		watcherOfTheRoost.GetSubtypes().AddSubtype(SubtypeCollection.BIRD);
		watcherOfTheRoost.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return watcherOfTheRoost;
	}	

	public static Creature WingmateRoc(Player owner){
		Creature wingmateRoc=Creature.NewCreature(owner, "Wingmate Roc", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), 3, 4);
		
		wingmateRoc.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionControllerAttackedThisTurn(wingmateRoc)}, new MagicEffect[]{new MagicEffectPlayToken(owner, WhiteBirdWithFlyingP3T4(owner), 1)}));
		
		TargetInfo ownerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		ownerTargetInfo.SetLockedTarget(0, owner);
		TargetInfo attackersTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		attackersTargetInfo.SetCombatStateORFlags(TargetInfo.COMBAT_STATE_FLAG_ATTACKING);
		wingmateRoc.AddAttacksTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(wingmateRoc)}, new MagicEffect[]{new MagicEffectGainXLifeForEachEligiblePermanent(owner, 1, ownerTargetInfo, attackersTargetInfo)}));
		
		wingmateRoc.GetKeywords().AddFlying();
		wingmateRoc.GetSubtypes().AddSubtype(SubtypeCollection.BIRD);
		return wingmateRoc;
	}
	// ENDREGION CREATURES
	// REGION INSTANTS AND SORCERIES
	public static Instant DefiantStrike(Player owner){
		TargetInfo controllerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		controllerTargetInfo.SetLockedTarget(0, owner);
		return new Instant(owner, "Defiant Strike", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, 1, 0, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), MagicEffect.Duration.UNTIL_END_OF_TURN), new MagicEffectDrawCardsForTargetPlayer(owner, 1, controllerTargetInfo)});
	}

	public static Sorcery EndHostilities(Player owner){
		TargetInfo targetinfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PERMANENT);
		targetinfo.MustBeAttachedTo(new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE));
		return new Sorcery(owner, "End Hostilities", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectDestroyTargetPermanents(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE))), new MagicEffectToAllPermanents(new MagicEffectDestroyTargetPermanents(owner, targetinfo))});
	}

	public static Instant Erase(Player owner){
		return new Instant(owner, "Erase", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectExileTargetCards(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_ENCHANTMENT), MagicEffect.Duration.NA)});
	}

	public static Instant FeatOfResistance(Player owner){
		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		TargetInfo protectionTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		protectionTargetInfo.SetColorORFlags(ManaCost.COLOR_PLAYERS_CHOICE);

		return new Instant(owner, "Feat of Resistance", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectAddPTCountersToTargetCreature(owner, 1, mycreatureTargetInfo), new MagicEffectGrantProtectionToTarget(owner, new TargetInfo(mycreatureTargetInfo), protectionTargetInfo, true, MagicEffect.Duration.UNTIL_END_OF_TURN)});
	}

	public static Instant KillShot(Player owner){
		TargetInfo attackingCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		attackingCreatureTargetInfo.SetCombatStateORFlags(TargetInfo.COMBAT_STATE_FLAG_ATTACKING);
		return new Instant(owner, "Kill Shot", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectDestroyTargetPermanents(owner, attackingCreatureTargetInfo)});
	}

	public static Instant RushOfBattle(Player owner){
		TargetInfo targetinfoMine=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetinfoMine.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		MagicEffect pteffect=new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, 2, 1, targetinfoMine, MagicEffect.Duration.UNTIL_END_OF_TURN));
		
		MagicEffect lifelinkEffect=MagicEffectGrantLifelinkToTargetCreature.Create(owner, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN);
		lifelinkEffect.TargetData.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		lifelinkEffect.TargetData.AddORSubtype(SubtypeCollection.WARRIOR);

		MagicEffect lifelinkToAllEffect=new MagicEffectToAllPermanents(lifelinkEffect);

		return new Instant(owner, "Rush of Battle", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{pteffect, lifelinkToAllEffect});
	}

	public static Instant SmiteTheMonstrous(Player owner){
		TargetInfo creatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		creatureTargetInfo.SetPowerMin(4);
		return new Instant(owner, "Smite the Monstrous", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectDestroyTargetPermanents(owner, creatureTargetInfo)});
	}

	public static Instant TakeUpArms(Player owner){
		return new Instant(owner, "Take Up Arms", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), new MagicEffect[]{new MagicEffectPlayToken(owner, WhiteWarriorP1T1(owner), 3)});
	}
	// ENDREGION INSTANTS AND SORCERIES
	// REGION ENCHANTMENTS
	public static Enchantment BraveTheSands(Player owner){
		Enchantment braveTheSands=Enchantment.NewEnchantment(owner, "Brave the Sands", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}));
		TargetInfo targetinfoMyCreature=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetinfoMyCreature.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		
		braveTheSands.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectGrantVigilanceToTargetCreature(owner, targetinfoMyCreature, MagicEffect.Duration.NA), new MagicEffectExtraBlockToTargetCreature(owner, 1, targetinfoMyCreature, MagicEffect.Duration.NA)}));
		
		return braveTheSands;
	}

	public static Enchantment Siegecraft(Player owner){
		return Enchantment.NewEnchantmentAura(owner, "Siegecraft", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(owner, 2, 4, 1, 1, MagicEffect.Duration.NA)});
	}

	public static Enchantment SuspensionField(Player owner){
		Enchantment suspensionField=Enchantment.NewEnchantment(owner, "Suspension Field", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}));
		
		TargetInfo targetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		targetInfo.SetToughnessMin(3);
		MagicEffect exileEffect=new MagicEffectExileTargetCards(owner, targetInfo, MagicEffect.Duration.UNTIL_LEAVES_FIELD);
		exileEffect.SetOptional();
		exileEffect.SetTargetToCallUndo(suspensionField);
		suspensionField.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(suspensionField)}, new MagicEffect[]{exileEffect}));
		
		return suspensionField;
	}
	// ENDREGION ENCHANTMENTS
// ENDREGION WHITE
// REGION BLACK
	// REGION CREATURES
	public static Creature BellowingSaddlebrute(Player owner){
		Creature bellowingSaddlebrute=Creature.NewCreature(owner, "Bellowing Saddlebrute", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 4, 5);
		
		TargetInfo ownerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		ownerTargetInfo.SetLockedTarget(0, owner);
		bellowingSaddlebrute.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionControllerDidntAttackThisTurn(bellowingSaddlebrute)}, new MagicEffect[]{new MagicEffectLoseLifeTargetPlayer(owner, 4, ownerTargetInfo)}));
		
		bellowingSaddlebrute.GetSubtypes().AddSubtype(SubtypeCollection.ORC);
		bellowingSaddlebrute.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return bellowingSaddlebrute;
	}

	public static Creature DisownedAncestor(Player owner){
		Creature disownedAncestor=Creature.NewCreature(owner, "Disowned Ancestor", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLACK}), 0, 4);

		addOutlastAbility(owner, disownedAncestor, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}));
		
		disownedAncestor.GetSubtypes().AddSubtype(SubtypeCollection.SPIRIT);
		disownedAncestor.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return disownedAncestor;
	}

	public static Creature GurmagSwiftwing(Player owner){
		Creature gurmagSwiftwing=Creature.NewCreature(owner, "Gurmag Swiftwing", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 1, 2);

		gurmagSwiftwing.GetKeywords().AddFlying();
		gurmagSwiftwing.GetKeywords().AddFirstStrike();
		gurmagSwiftwing.GetKeywords().AddHaste();
		gurmagSwiftwing.GetSubtypes().AddSubtype(SubtypeCollection.BAT);
		return gurmagSwiftwing;
	}
	
	public static Creature KrumarBondKin(Player owner){
		Creature krumarBondKin=Creature.NewCreature(owner, "Krumar Bond-Kin", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK, ManaCost.COLOR_FLAG_BLACK}), 5, 3);

		addMorphAbility(owner, krumarBondKin, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}));
		
		krumarBondKin.GetSubtypes().AddSubtype(SubtypeCollection.ORC);
		krumarBondKin.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return krumarBondKin;
	}

	public static Creature MerEkNightblade(Player owner){
		Creature merEkNightblade=Creature.NewCreature(owner, "Mer-Ek Nightblade", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 2, 3);
		
		addOutlastAbility(owner, merEkNightblade, new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLACK}));
		
		TargetInfo myCreatureWithPTCountersTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myCreatureWithPTCountersTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myCreatureWithPTCountersTargetInfo.SetMinPTCounters(1);
		merEkNightblade.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectGrantDeathtouchToTargetCreature(owner, myCreatureWithPTCountersTargetInfo, MagicEffect.Duration.NA)}));
		
		merEkNightblade.GetSubtypes().AddSubtype(SubtypeCollection.ORC);
		merEkNightblade.GetSubtypes().AddSubtype(SubtypeCollection.ASSASSIN);
		return merEkNightblade;
	}

	public static Creature RottingMastodon(Player owner){
		Creature rottingMastodon=Creature.NewCreature(owner, "Rotting Mastodon", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 2, 8);
		
		rottingMastodon.GetSubtypes().AddSubtype(SubtypeCollection.ZOMBIE);
		rottingMastodon.GetSubtypes().AddSubtype(SubtypeCollection.ELEPHANT);
		return rottingMastodon;
	}

	public static Creature RuthlessRipper(Player owner){
		Creature ruthlessRipper=Creature.NewCreature(owner, "Ruthless Ripper", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLACK}), 1, 1);

		addMorphAbilityRevealCard(owner, ruthlessRipper, ManaCost.COLOR_FLAG_BLACK);
		
		ruthlessRipper.AddMorphTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(ruthlessRipper)}, new MagicEffect[]{new MagicEffectLoseLifeTargetPlayer(owner, 2, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER))}));
		
		ruthlessRipper.GetKeywords().AddDeathtouch();
		ruthlessRipper.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		ruthlessRipper.GetSubtypes().AddSubtype(SubtypeCollection.ASSASSIN);
		return ruthlessRipper;
	}

	public static Creature ShamblingAttendants(Player owner){
		Creature shamblingAttendants=Creature.NewCreature(owner, "Shambling Attendants", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 3, 5);
		
		shamblingAttendants.GetKeywords().AddDelve();
		shamblingAttendants.GetKeywords().AddDeathtouch();
		shamblingAttendants.GetSubtypes().AddSubtype(SubtypeCollection.ZOMBIE);
		return shamblingAttendants;
	}

	public static Creature SidisisPet(Player owner){
		Creature sidisisPet=Creature.NewCreature(owner, "Sidisi's Pet", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 1, 4);

		addMorphAbility(owner, sidisisPet, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}));
		
		sidisisPet.GetKeywords().AddLifelink();
		sidisisPet.GetSubtypes().AddSubtype(SubtypeCollection.ZOMBIE);
		sidisisPet.GetSubtypes().AddSubtype(SubtypeCollection.APE);
		return sidisisPet;
	}

	public static Creature SultaiScavenger(Player owner){
		Creature sultaiScavenger=Creature.NewCreature(owner, "Sultai Scavenger", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 3, 3);
		
		sultaiScavenger.GetKeywords().AddDelve();
		sultaiScavenger.GetKeywords().AddFlying();
		sultaiScavenger.GetSubtypes().AddSubtype(SubtypeCollection.BIRD);
		sultaiScavenger.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return sultaiScavenger;
	}

	public static Creature UnyieldingKrumar(Player owner){
		Creature unyieldingKrumar=Creature.NewCreature(owner, "Unyielding Krumar", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), 3, 3);

		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, unyieldingKrumar);
		unyieldingKrumar.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE}), null, new MagicEffect[]{new MagicEffectGrantFirstStrikeToTargetCreature(owner, selfTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)}));
		
		unyieldingKrumar.GetSubtypes().AddSubtype(SubtypeCollection.ORC);
		unyieldingKrumar.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return unyieldingKrumar;
	}
	// ENDREGION CREATURES
	// REGION INSTANTS AND SORCERIES
	public static Instant MurderousCut(Player owner){
		Instant murderousCut=new Instant(owner, "Murderous Cut", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), new MagicEffect[]{new MagicEffectDestroyTargetPermanents(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE))});
		murderousCut.GetKeywords().AddDelve();
		return murderousCut;
	}

	public static Instant Throttle(Player owner){
		return new Instant(owner, "Throttle", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, -4, -4, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), MagicEffect.Duration.UNTIL_END_OF_TURN)});
	}
	// ENDREGION INSTANTS AND SORCERIES
	// REGION ENCHANTMENTS
	public static Enchantment DebilitatingInjury(Player owner){
		return Enchantment.NewEnchantmentAura(owner, "Debilitating Injury", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK}), TargetInfo.TARGET_TYPE_FLAG_CREATURE, new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, -2, -2, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE), MagicEffect.Duration.NA)});
	}
	// ENDREGION ENCHANTMENTS
// ENDREGION BLACK
// REGION DUALCOLORED
	// REGION CREATURES
	public static Creature ChiefOfTheEdge(Player owner){
		Creature chiefOfTheEdge=Creature.NewCreature(owner, "Chief of the Edge", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_BLACK}), 3, 2);

		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, chiefOfTheEdge);

		TargetInfo myWarriorCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, new TargetInfo[]{selfTargetInfo});
		myWarriorCreatureTargetInfo.AddORSubtype(SubtypeCollection.WARRIOR);
		myWarriorCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		chiefOfTheEdge.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, 1, 0, myWarriorCreatureTargetInfo, MagicEffect.Duration.NA)}));

		chiefOfTheEdge.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		chiefOfTheEdge.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return chiefOfTheEdge;
	}

	public static Creature ChiefOfTheScale(Player owner){
		Creature chiefOfTheScale=Creature.NewCreature(owner, "Chief of the Scale", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_BLACK}), 2, 3);

		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, chiefOfTheScale);

		TargetInfo myWarriorCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, new TargetInfo[]{selfTargetInfo});
		myWarriorCreatureTargetInfo.AddORSubtype(SubtypeCollection.WARRIOR);
		myWarriorCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		chiefOfTheScale.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, 0, 1, myWarriorCreatureTargetInfo, MagicEffect.Duration.NA)}));

		chiefOfTheScale.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		chiefOfTheScale.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return chiefOfTheScale;
	}

	public static Creature HighspireMantis(Player owner){
		Creature highspireMantis=Creature.NewCreature(owner, "Highspire Mantis", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_RED}), 3, 3);

		highspireMantis.GetKeywords().AddFlying();
		highspireMantis.GetKeywords().AddTrample();
		highspireMantis.GetSubtypes().AddSubtype(SubtypeCollection.INSECT);
		return highspireMantis;
	}
	// ENDREGION CREATURES
	// REGION INSTANTS AND SORCERIES
	public static Instant UtterEnd(Player owner){
		TargetInfo nonlandTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PERMANENT);
		nonlandTargetInfo.SetTargetTypeNOTFlags(TargetInfo.TARGET_TYPE_FLAG_LAND);
		return new Instant(owner, "Utter End", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_BLACK}), new MagicEffect[]{new MagicEffectExileTargetCards(owner, nonlandTargetInfo, MagicEffect.Duration.NA)});
	}
	// ENDREGION INSTANTS AND SORCERIES
// ENDREGION DUALCOLORED
// REGION TRICOLORED
	//REGION CREATURES
	public static Creature AbzanGuide(Player owner){
		Creature abzanGuide=Creature.NewCreature(owner, "Abzan Guide", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_BLACK, ManaCost.COLOR_FLAG_GREEN}), 4, 4);

		addMorphAbility(owner, abzanGuide, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_BLACK, ManaCost.COLOR_FLAG_GREEN}));
		
		abzanGuide.GetKeywords().AddLifelink();
		abzanGuide.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		abzanGuide.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return abzanGuide;
	}

	public static Creature AnkleShanker(Player owner){
		Creature ankleShanker=Creature.NewCreature(owner, "Ankle Shanker", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_BLACK}), 2, 2);

		TargetInfo mycreaturesTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreaturesTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		ankleShanker.AddAttacksTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(ankleShanker)}, new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectGrantDeathtouchToTargetCreature(owner, mycreaturesTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)), new MagicEffectToAllPermanents(new MagicEffectGrantFirstStrikeToTargetCreature(owner, mycreaturesTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN))}));

		ankleShanker.GetKeywords().AddHaste();
		ankleShanker.GetSubtypes().AddSubtype(SubtypeCollection.GOBLIN);
		ankleShanker.GetSubtypes().AddSubtype(SubtypeCollection.BERSERKER);
		return ankleShanker;
	}

	public static Creature ArmamentCorps(Player owner){
		Creature armamentCorps=Creature.NewCreature(owner, "Armament Corps", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_BLACK}), 4, 4);

		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		armamentCorps.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(armamentCorps)}, new MagicEffect[]{new MagicEffectAddPTCountersToTargetCreature(owner, 1, mycreatureTargetInfo), new MagicEffectAddPTCountersToTargetCreature(owner, 1, mycreatureTargetInfo.DeepCopy())}));

		armamentCorps.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		armamentCorps.GetSubtypes().AddSubtype(SubtypeCollection.SOLDIER);
		return armamentCorps;
	}

	public static Creature BearsCompanion(Player owner){
		Creature bearsCompanion=Creature.NewCreature(owner, "Bear's Companion", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_BLUE}), 2, 2);

		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		bearsCompanion.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(bearsCompanion)}, new MagicEffect[]{new MagicEffectPlayToken(owner, GreenBearP4T4(owner), 1)}));

		bearsCompanion.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		bearsCompanion.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return bearsCompanion;
	}

	public static Creature EfreetWeaponmaster(Player owner){
		Creature efreetWeaponmaster=Creature.NewCreature(owner, "Efreet Weaponmaster", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_BLUE}), 4, 3);

		addMorphAbility(owner, efreetWeaponmaster, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_BLUE}));
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		selfTargetInfo.SetLockedTarget(0, efreetWeaponmaster);

		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE, new TargetInfo[]{selfTargetInfo});
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		efreetWeaponmaster.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(efreetWeaponmaster)}, new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, 3, 0, mycreatureTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)}));
		efreetWeaponmaster.AddMorphTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(efreetWeaponmaster)}, new MagicEffect[]{new MagicEffectAddPTToTargetCreature(owner, 3, 0, mycreatureTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)}));
		
		efreetWeaponmaster.GetKeywords().AddFirstStrike();
		efreetWeaponmaster.GetSubtypes().AddSubtype(SubtypeCollection.EFREET);
		efreetWeaponmaster.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return efreetWeaponmaster;
	}

	public static Creature IvorytuskFortress(Player owner){
		Creature ivorytuskFortress=Creature.NewCreature(owner, "Ivorytusk Fortress", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_BLACK}), 5, 7);

		TargetInfo ownerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		ownerTargetInfo.SetLockedTarget(0, owner);
		TargetInfo notownerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER, new TargetInfo[]{ownerTargetInfo});

		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		mycreatureTargetInfo.SetMinPTCounters(1);
		ivorytuskFortress.AddUpkeepTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(notownerTargetInfo)}, new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectUntapTargetCard(owner, mycreatureTargetInfo))}));

		ivorytuskFortress.GetSubtypes().AddSubtype(SubtypeCollection.ELEPHANT);
		return ivorytuskFortress;
	}

	public static Creature MantisRider(Player owner){
		Creature mantisRider=Creature.NewCreature(owner, "Mantis Rider", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_RED}), 3, 3);

		mantisRider.GetKeywords().AddFlying();
		mantisRider.GetKeywords().AddVigilance();
		mantisRider.GetKeywords().AddHaste();
		mantisRider.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		mantisRider.GetSubtypes().AddSubtype(SubtypeCollection.MONK);
		return mantisRider;
	}

	public static Creature PonybackBrigade(Player owner){
		Creature ponybackBrigade=Creature.NewCreature(owner, "Ponyback Brigade", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_BLACK}), 2, 2);

		addMorphAbility(owner, ponybackBrigade, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_BLACK}));
		
		ponybackBrigade.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(ponybackBrigade)}, new MagicEffect[]{new MagicEffectPlayToken(owner, RedGoblinP1T1(owner), 3)}));
		ponybackBrigade.AddMorphTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(ponybackBrigade)}, new MagicEffect[]{new MagicEffectPlayToken(owner, RedGoblinP1T1(owner), 3)}));
		
		ponybackBrigade.GetSubtypes().AddSubtype(SubtypeCollection.GOBLIN);
		ponybackBrigade.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return ponybackBrigade;
	}

	public static Creature SageOfTheInwardEye(Player owner){
		Creature sageOfTheInwardEye=Creature.NewCreature(owner, "Sage of the Inward Eye", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_RED}), 3, 4);

		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());

		TargetInfo myNonCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		myNonCreatureTargetInfo.SetTargetTypeNOTFlags(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myNonCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myNonCreatureTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.STACK));
		sageOfTheInwardEye.AddSpellCastTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(myNonCreatureTargetInfo)}, new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectGrantLifelinkToTargetCreature(owner, mycreatureTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN))}));

		sageOfTheInwardEye.GetKeywords().AddFlying();
		sageOfTheInwardEye.GetSubtypes().AddSubtype(SubtypeCollection.DJINN);
		sageOfTheInwardEye.GetSubtypes().AddSubtype(SubtypeCollection.WIZARD);
		return sageOfTheInwardEye;
	}

	public static Creature SiegeRhino(Player owner){
		Creature siegeRhino=Creature.NewCreature(owner, "Siege Rhino", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_BLACK, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_GREEN}), 4, 5);

		TargetInfo ownerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		ownerTargetInfo.SetLockedTarget(0, owner);
		TargetInfo notownerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER, new TargetInfo[]{ownerTargetInfo});
		siegeRhino.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(siegeRhino)}, new MagicEffect[]{new MagicEffectToAllPlayers(new MagicEffectLoseLifeTargetPlayer(owner, 3, notownerTargetInfo)), new MagicEffectGainLife(owner, 3)}));

		siegeRhino.GetKeywords().AddTrample();
		siegeRhino.GetSubtypes().AddSubtype(SubtypeCollection.RHINO);
		return siegeRhino;
	}

	public static Creature SnowhornRider(Player owner){
		Creature snowhornRider=Creature.NewCreature(owner, "Snowhorn Rider", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_BLUE}), 5, 5);

		addMorphAbility(owner, snowhornRider, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_BLUE}));
		
		snowhornRider.GetKeywords().AddTrample();
		snowhornRider.GetSubtypes().AddSubtype(SubtypeCollection.HUMAN);
		snowhornRider.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return snowhornRider;
	}

// ENDREGION CREATURES
	// REGION INSTANTS AND SORCERIES
	public static Instant FlyingCraneTechnique(Player owner){
		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		return new Instant(owner, "Flying Crane Technique", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectUntapTargetCard(owner, mycreatureTargetInfo)), new MagicEffectToAllPermanents(new MagicEffectGrantFlyingToTargetCreature(owner, mycreatureTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)), new MagicEffectToAllPermanents(new MagicEffectGrantDoubleStrikeToTargetCreature(owner, mycreatureTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN))});
	}

	public static Instant TrapEssence(Player owner){
		return new Instant(owner, "Trap Essence", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_BLUE}), new MagicEffect[]{new MagicEffectCancelSpell(owner, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE)), new MagicEffectAddPTCountersToTargetCreature(owner, 2, new TargetInfo(0, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE))});
	}
	// ENDREGION INSTANTS AND SORCERIES
	// REGION ENCHANTMENTS
	public static Enchantment MarduAscendancy(Player owner){
		Enchantment marduAscendancy=Enchantment.NewEnchantment(owner, "Mardu Ascendancy", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_BLACK}));
		
		TargetInfo mynontokenCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mynontokenCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		mynontokenCreatureTargetInfo.SetTargetTypeNOTFlags(TargetInfo.TARGET_TYPE_FLAG_TOKEN);

		marduAscendancy.AddAttacksTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(mynontokenCreatureTargetInfo)}, new MagicEffect[]{/*TODO: Play tapped and attacking token*/}));

		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		marduAscendancy.AddActivatedAbility(new MagicActivatedAbility(false, null, new MagicEffect[]{new MagicEffectDiscardCard(owner, marduAscendancy)}, new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, 0, 3, mycreatureTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN))}));

		return marduAscendancy;
	}

	public static Enchantment TemurAscendancy(Player owner){
		Enchantment temurAscendancy=Enchantment.NewEnchantment(owner, "Temur Ascendancy", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_BLUE}));
		
		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		
		temurAscendancy.AddContinousEffect(new ContinuousAbility(null, new MagicEffect[]{new MagicEffectGrantHasteToTargetCreature(owner, mycreatureTargetInfo, MagicEffect.Duration.NA)}));
		
		TargetInfo mypowerCreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mypowerCreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		mypowerCreatureTargetInfo.SetPowerMin(4);
		
		TargetInfo ownerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		ownerTargetInfo.SetLockedTarget(0, owner);
		MagicEffect optionalDrawEffect=new MagicEffectDrawCardsForTargetPlayer(owner, 1, ownerTargetInfo);
		optionalDrawEffect.SetOptional();
		temurAscendancy.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(mypowerCreatureTargetInfo)}, new MagicEffect[]{optionalDrawEffect}));

		return temurAscendancy;
	}
	
	public static Enchantment JeskaiAscendancy(Player owner){
		Enchantment jeskaiAscendancy=Enchantment.NewEnchantment(owner, "Jeskai Ascendancy", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_BLUE}));
		
		TargetInfo mycreatureTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		mycreatureTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		
		TargetInfo myNonCreatureStackTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
		myNonCreatureStackTargetInfo.SetTargetTypeNOTFlags(TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		myNonCreatureStackTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		myNonCreatureStackTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.STACK));
		jeskaiAscendancy.AddSpellCastTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(myNonCreatureStackTargetInfo)}, new MagicEffect[]{new MagicEffectToAllPermanents(new MagicEffectAddPTToTargetCreature(owner, 1, 1, mycreatureTargetInfo, MagicEffect.Duration.UNTIL_END_OF_TURN)), new MagicEffectToAllPermanents(new MagicEffectUntapTargetCard(owner, mycreatureTargetInfo))}));
		
		TargetInfo ownerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		ownerTargetInfo.SetLockedTarget(0, owner);
		MagicEffect optionalDrawEffect=new MagicEffectDrawCardsForTargetPlayer(owner, 1, ownerTargetInfo);
		optionalDrawEffect.SetOptional();
		
		TargetInfo handTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_CARD);
		handTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.HAND));
		MagicEffect optionalDiscardEffect=MagicEffectChooseCardForAction.Discard(owner, handTargetInfo);
		optionalDrawEffect.SetDependentEffect(optionalDiscardEffect);
		jeskaiAscendancy.AddSpellCastTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionMatchesTypes(myNonCreatureStackTargetInfo)}, new MagicEffect[]{optionalDrawEffect, optionalDiscardEffect}));

		return jeskaiAscendancy;
	}
	// ENDREGION ENCHANTMENTS
// ENDREGION TRICOLORED
// REGION COLORLESS
	// REGION CREATURES
	public static Creature WitnessOfTheAges(Player owner){
		Creature witnessOfTheAges=Creature.NewCreature(owner, "Witness of the Ages", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), 4, 4);

		addMorphAbility(owner, witnessOfTheAges, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}));
		
		witnessOfTheAges.GetSubtypes().AddSubtype(SubtypeCollection.GOLEM);
		return witnessOfTheAges;
	}
	// ENDREGION CREATURES
	// REGION ARTIFACTS
	private static Artifact createBanner(Player owner, String name, ManaCost drawEffectManaCost){
		Artifact banner=Artifact.NewArtifact(owner, name, "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}));
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_ARTIFACT);
		selfTargetInfo.SetLockedTarget(0, banner);
		MagicEffect tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);
		
		banner.AddActivatedAbility(new MagicActivatedAbility(false, null, new MagicEffect[]{tapEffect}, new MagicEffect[]{MagicEffectAddManaToPool.CreateRequiresColorDecision(owner, drawEffectManaCost.GetColors())}));

		TargetInfo controllerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		controllerTargetInfo.SetLockedTarget(0, owner);
		
		banner.AddActivatedAbility(new MagicActivatedAbility(false, drawEffectManaCost, new MagicEffect[]{tapEffect, new MagicEffectDiscardCard(owner, banner)}, new MagicEffect[]{new MagicEffectDrawCardsForTargetPlayer(owner, 1, controllerTargetInfo)}));
		return banner;
	}

	public static Artifact AbzanBanner(Player owner){
		return createBanner(owner, "Abzan Banner", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_BLACK, ManaCost.COLOR_FLAG_GREEN}));
	}

	public static Artifact JeskaiBanner(Player owner){
		return createBanner(owner, "Jeskai Banner", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_BLUE}));
	}

	public static Artifact MarduBanner(Player owner){
		return createBanner(owner, "Mardu Banner", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_BLACK}));
	}

	public static Artifact SultaiBanner(Player owner){
		return createBanner(owner, "Sultai Banner", new ManaCost(new int[]{ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_BLACK}));
	}

	public static Artifact TemurBanner(Player owner){
		return createBanner(owner, "Temur Banner", new ManaCost(new int[]{ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_RED}));
	}
// ENDREGION ARTIFACTS
// ENDREGION COLORLESS
// REGION LANDS
	private static NonbasicLand createDualLand(Player owner, String name, int manaflags){
		NonbasicLand dualland=new NonbasicLand(owner, name, manaflags);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		selfTargetInfo.SetLockedTarget(0, dualland);

		MagicEffect tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);
		
		dualland.AddEntersTheBattlefieldAdjustments(tapEffect);
		dualland.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(dualland)}, new MagicEffect[]{new MagicEffectGainLife(owner, 1)}));
		dualland.AddActivatedAbility(new MagicActivatedAbility(false, null, new MagicEffect[]{tapEffect}, new MagicEffect[]{MagicEffectAddManaToPool.CreateRequiresColorDecision(owner, manaflags)}));
		
		return dualland;
	}
	
	private static NonbasicLand createTriLand(Player owner, String name, int manaflags){
		NonbasicLand triland=new NonbasicLand(owner, name, manaflags);
		
		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		selfTargetInfo.SetLockedTarget(0, triland);

		MagicEffect tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);
		
		triland.AddEntersTheBattlefieldAdjustments(tapEffect);
		triland.AddActivatedAbility(new MagicActivatedAbility(false, null, new MagicEffect[]{tapEffect}, new MagicEffect[]{MagicEffectAddManaToPool.CreateRequiresColorDecision(owner, manaflags)}));
		
		return triland;
	}

	private static NonbasicLand createFetchLand(Player owner, String name, int manaflags){
		NonbasicLand fetchland=new NonbasicLand(owner, name, manaflags);

		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		selfTargetInfo.SetLockedTarget(0, fetchland);

		MagicEffect tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);
		MagicEffect sacEffect=new MagicEffectDiscardCard(owner, fetchland);
		MagicEffect lifelossEffect=new MagicEffectLoseLife(owner, 1);
		
		TargetInfo fetchTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		fetchTargetInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.LIBRARY));
		if((manaflags&ManaCost.COLOR_FLAG_RED)!=0)
			fetchTargetInfo.AddORSubtype(SubtypeCollection.MOUNTAIN);
		if((manaflags&ManaCost.COLOR_FLAG_GREEN)!=0)
			fetchTargetInfo.AddORSubtype(SubtypeCollection.FOREST);
		if((manaflags&ManaCost.COLOR_FLAG_BLUE)!=0)
			fetchTargetInfo.AddORSubtype(SubtypeCollection.ISLAND);
		if((manaflags&ManaCost.COLOR_FLAG_WHITE)!=0)
			fetchTargetInfo.AddORSubtype(SubtypeCollection.PLAINS);
		if((manaflags&ManaCost.COLOR_FLAG_BLACK)!=0)
			fetchTargetInfo.AddORSubtype(SubtypeCollection.SWAMP);
		
		fetchland.AddActivatedAbility(new MagicActivatedAbility(false, null, new MagicEffect[]{tapEffect, sacEffect, lifelossEffect}, new MagicEffect[]{MagicEffectChooseCardForAction.PutOnField(owner, fetchTargetInfo)}));
		
		return fetchland;
	}

	public static NonbasicLand BloodfellCaves(Player owner){
		return createDualLand(owner, "Bloodfell Caves", ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_BLACK);
	}

	public static NonbasicLand BloodstainedMire(Player owner){
		return createFetchLand(owner, "Bloodstained Mire", ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_BLACK);
	}

	public static NonbasicLand BlossomingSands(Player owner){
		return createDualLand(owner, "Blossoming Sands", ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_WHITE);
	}

	public static NonbasicLand DismalBackwater(Player owner){
		return createDualLand(owner, "Dismal Backwater", ManaCost.COLOR_FLAG_BLUE|ManaCost.COLOR_FLAG_BLACK);
	}
	
	public static NonbasicLand FloodedStrand(Player owner){
		return createFetchLand(owner, "Flooded Strand", ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLUE);
	}

	public static NonbasicLand FrontierBivouac(Player owner){
		return createTriLand(owner, "Frontier Bivouac", ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_BLUE|ManaCost.COLOR_FLAG_GREEN);
	}

	public static NonbasicLand JungleHollow(Player owner){
		return createDualLand(owner, "Jungle Hollow", ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLACK);
	}

	public static NonbasicLand MysticMonastery(Player owner){
		return createTriLand(owner, "Mystic Monastery", ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLUE);
	}

	public static NonbasicLand NomadOutpost(Player owner){
		return createTriLand(owner, "Nomad Outpost", ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK);
	}

	public static NonbasicLand OpulentPalace(Player owner){
		return createTriLand(owner, "Opulent Palace", ManaCost.COLOR_FLAG_BLUE|ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLACK);
	}

	public static NonbasicLand PollutedDelta(Player owner){
		return createFetchLand(owner, "Polluted Delta", ManaCost.COLOR_FLAG_BLUE|ManaCost.COLOR_FLAG_BLACK);
	}

	public static NonbasicLand RuggedHighlands(Player owner){
		return createDualLand(owner, "Rugged Highlands", ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_RED);
	}

	public static NonbasicLand SandsteppeCitadel(Player owner){
		return createTriLand(owner, "Sandsteppe Citadel", ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLACK);
	}

	public static NonbasicLand ScouredBarrens(Player owner){
		return createDualLand(owner, "Scoured Barrens", ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK);
	}

	public static NonbasicLand SwiftwaterCliffs(Player owner){
		return createDualLand(owner, "Swiftwater Cliffs", ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_BLUE);
	}

	public static NonbasicLand ThornwoodFalls(Player owner){
		return createDualLand(owner, "Thornwood Falls", ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_BLUE);
	}

	public static NonbasicLand TombOfTheSpiritDragon(Player owner){
		NonbasicLand tombOfTheSpiritDragon=new NonbasicLand(owner, "Tomb of the Spirit Dragon", ManaCost.COLOR_FLAG_COLORLESS);

		TargetInfo selfTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		selfTargetInfo.SetLockedTarget(0, tombOfTheSpiritDragon);

		MagicEffect tapEffect=new MagicEffectTapTargetCard(owner, selfTargetInfo);
		tombOfTheSpiritDragon.AddActivatedAbility(new MagicActivatedAbility(false, null, new MagicEffect[]{tapEffect}, new MagicEffect[]{new MagicEffectAddManaToPool(owner, 0, 0, 0, 0, 0, 1)}));

		TargetInfo ownerTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLAYER);
		ownerTargetInfo.SetLockedTarget(0, owner);
		TargetInfo eligibilityTargetInfo=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
		eligibilityTargetInfo.SetColorORFlags(ManaCost.COLOR_FLAG_COLORLESS);
		eligibilityTargetInfo.TargetMustBeControlledByIndex(owner.GetIndex(), owner.GetName());
		tombOfTheSpiritDragon.AddActivatedAbility(new MagicActivatedAbility(false, new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), new MagicEffect[]{tapEffect}, new MagicEffect[]{new MagicEffectGainXLifeForEachEligiblePermanent(owner, 1, ownerTargetInfo, eligibilityTargetInfo)}));

		return tombOfTheSpiritDragon;
	}

	public static NonbasicLand TranquilCove(Player owner){
		return createDualLand(owner, "Tranquil Cove", ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLUE);
	}

	public static NonbasicLand WindScarredCrag(Player owner){
		return createDualLand(owner, "Wind-Scarred Crag", ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_RED);
	}

	public static NonbasicLand WindsweptHeath(Player owner){
		return createFetchLand(owner, "Windswept Heath", ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_WHITE);
	}

	public static NonbasicLand WoodedFoothills(Player owner){
		return createFetchLand(owner, "Wooded Foothills", ManaCost.COLOR_FLAG_GREEN|ManaCost.COLOR_FLAG_RED);
	}
// ENDREGION LANDS
// REGION TOKENS
	public static Creature RedGoblinP1T1(Player owner){
		Creature token=Creature.NewCreature(owner, "Goblin", "1/1 red Goblin creature token", null, 1, 1);
		token.SetToken(ManaCost.COLOR_FLAG_RED);
		token.GetSubtypes().AddSubtype(SubtypeCollection.GOBLIN);
		return token;
	}

	public static Creature RedGoblinWithHasteP1T1(Player owner){
		Creature token=Creature.NewCreature(owner, "Goblin", "1/1 red Goblin creature token with haste", null, 1, 1);
		token.SetToken(ManaCost.COLOR_FLAG_RED);
		token.GetKeywords().AddHaste();
		token.GetSubtypes().AddSubtype(SubtypeCollection.GOBLIN);
		return token;
	}

	public static Creature GreenBearP4T4(Player owner){
		Creature token=Creature.NewCreature(owner, "Bear", "4/4 green Bear creature token", null, 4, 4);
		token.SetToken(ManaCost.COLOR_FLAG_GREEN);
		token.GetSubtypes().AddSubtype(SubtypeCollection.BEAR);
		return token;
	}

	public static Creature WhiteWarriorP1T1(Player owner){
		Creature token=Creature.NewCreature(owner, "Warrior", "1/1 white Warrior creature token", null, 1, 1);
		token.SetToken(ManaCost.COLOR_FLAG_WHITE);
		token.GetSubtypes().AddSubtype(SubtypeCollection.WARRIOR);
		return token;
	}
	
	public static Creature WhiteBirdWithFlyingP3T4(Player owner){
		Creature token=Creature.NewCreature(owner, "Bird", "3/4 white Bird creature token with flying", null, 3, 4);
		token.SetToken(ManaCost.COLOR_FLAG_WHITE);
		token.GetKeywords().AddFlying();
		token.GetSubtypes().AddSubtype(SubtypeCollection.BIRD);
		return token;
	}	
// ENDREGION TOKENS
}
