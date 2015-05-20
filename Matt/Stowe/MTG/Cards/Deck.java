package Matt.Stowe.MTG.Cards;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Cards.Sets.*;

import java.io.*;
import java.awt.*;
import java.util.*;

public class Deck extends AnimatedGUIObject{
	private Vector<CardBase> library;
	public ArrayList<CardBase> GetCards(){
		ArrayList<CardBase> allcards=new ArrayList<CardBase>();
		allcards.addAll(this.library);
		return allcards;
	}
	public int GetCardCount(){return this.library.size();}

	public Deck(Vector<CardBase> cards, int x, int y){
		super(x, y, x+CardBase.WIDTH, y+CardBase.HEIGHT);
		this.library=cards;
	}

	public static Vector<CardBase> ReadCardsFromFile(String path, Player owner){
		Vector<CardBase> cards=new Vector<CardBase>();
		boolean unknownCardDetected=false;
		try{
			BufferedReader fin=new BufferedReader(new FileReader(path));
			String nextline=fin.readLine();
			while(nextline!=null){
				String[] splitline=nextline.split("\t");
				if(splitline.length<2)
					break;
				int count=Integer.parseInt(splitline[0]);
				String cardname=splitline[1];
				for(int i=0;i<count;i++){
					CardBase trycard=GetCardByName(cardname, owner);
					if(trycard!=null){
						cards.add(trycard);
					}else{
						unknownCardDetected=true;
					}
				}
				nextline=fin.readLine();
			}
			fin.close();
			if(unknownCardDetected){
				System.exit(1);
			}
			return cards;
		}catch(FileNotFoundException fe){
			fe.printStackTrace();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}
	
	public static CardBase GetCardByName(String cardname, Player owner){
		//BEGIN THEROS
		if(cardname.equals("Silent Artisan")){
			return Theros.SilentArtisan(owner);
		}else if(cardname.equals("Traveling Philosopher")){
			return Theros.TravelingPhilosopher(owner);
		}else if(cardname.equals("Yoked Ox")){
			return Theros.YokedOx(owner);
		}else if(cardname.equals("Decorated Griffin")){
			return Theros.DecoratedGriffin(owner);
		}else if(cardname.equals("Triton Shorethief")){
			return Theros.TritonShorethief(owner);
		}else if(cardname.equals("Omenspeaker")){
			return Theros.Omenspeaker(owner);
		}else if(cardname.equals("Horizon Scholar")){
			return Theros.HorizonScholar(owner);
		}else if(cardname.equals("Felhide Minotaur")){
			return Theros.FelhideMinotaur(owner);
		}else if(cardname.equals("Insatiable Harpy")){
			return Theros.InsatiableHarpy(owner);
		}else if(cardname.equals("Borderland Minotaur")){
			return Theros.BorderlandMinotaur(owner);
		}else if(cardname.equals("Minotaur Skullcleaver")){
			return Theros.MinotaurSkullcleaver(owner);
		}else if(cardname.equals("Ill-Tempered Cyclops")){
			return Theros.IllTemperedCyclops(owner);
		}else if(cardname.equals("Satyr Rambler")){
			return Theros.SatyrRambler(owner);
		}else if(cardname.equals("Nessian Courser")){
			return Theros.NessianCourser(owner);
		}else if(cardname.equals("Pheres-Band Centaurs")){
			return Theros.PheresBandCentaurs(owner);
		}else if(cardname.equals("Nessian Asp")){
			return Theros.NessianAsp(owner);
		}else if(cardname.equals("Vulpine Goliath")){
			return Theros.VulpineGoliath(owner);
		}else if(cardname.equals("Bronze Sable")){
			return Theros.BronzeSable(owner);
		}else if(cardname.equals("Guardians of Meletis")){
			return Theros.GuardiansOfMeletis(owner);
		}else if(cardname.equals("Battlewise Valor")){
			return Theros.BattlewiseValor(owner);
		}else if(cardname.equals("Dauntless Onslaught")){
			return Theros.DauntlessOnslaught(owner);
		}else if(cardname.equals("Ray of Dissolution")){
			return Theros.RayOfDissolution(owner);
		}else if(cardname.equals("Lost in a Labyrinth")){
			return Theros.LostInALabyrinth(owner);
		}else if(cardname.equals("Aqueous Form")){
			return Theros.AqueousForm(owner);
		}else if(cardname.equals("Lash of the Whip")){
			return Theros.LashOfTheWhip(owner);
		}else if(cardname.equals("Demolish")){
			return Theros.Demolish(owner);
		}else if(cardname.equals("Titan's Strength")){
			return Theros.TitansStrength(owner);
		}else if(cardname.equals("Messenger's Speed")){
			return Theros.MessengersSpeed(owner);
		}else if(cardname.equals("Chosen by Heliod")){
			return Theros.ChosenByHeliod(owner);
		}else if(cardname.equals("Feral Invocation")){
			return Theros.FeralInvocation(owner);
		}else if(cardname.equals("Scourgemark")){
			return Theros.Scourgemark(owner);
		}
		// END THEROS
		// BEGIN CORE2015
		else if(cardname.equals("Borderland Marauder")){
			return Core2015.BorderlandMarauder(owner);
		}else if(cardname.equals("Forge Devil")){
			return Core2015.ForgeDevil(owner);
		}else if(cardname.equals("Foundry Street Denizen")){
			return Core2015.FoundryStreetDenizen(owner);
		}else if(cardname.equals("Furnace Whelp")){
			return Core2015.FurnaceWhelp(owner);
		}else if(cardname.equals("Goblin Roughrider")){
			return Core2015.GoblinRoughrider(owner);
		}else if(cardname.equals("Kird Chieftain")){
			return Core2015.KirdChieftain(owner);
		}else if(cardname.equals("Miner's Bane")){
			return Core2015.MinersBane(owner);
		}else if(cardname.equals("Paragon of Fierce Defiance")){
			return Core2015.ParagonOfFierceDefiance(owner);
		}else if(cardname.equals("Scrapyard Mongrel")){
			return Core2015.ScrapyardMongrel(owner);
		}else if(cardname.equals("Shivan Dragon")){
			return Core2015.ShivanDragon(owner);
		}else if(cardname.equals("Thundering Giant")){
			return Core2015.ThunderingGiant(owner);
		}else if(cardname.equals("Torch Fiend")){
			return Core2015.TorchFiend(owner);
		}else if(cardname.equals("Wall of Fire")){
			return Core2015.WallOfFire(owner);
		}else if(cardname.equals("Cone of Flame")){
			return Core2015.ConeOfFlame(owner);
		}else if(cardname.equals("Crowd's Favor")){
			return Core2015.CrowdsFavor(owner);
		}else if(cardname.equals("Heat Ray")){
			return Core2015.HeatRay(owner);
		}else if(cardname.equals("Lava Axe")){
			return Core2015.LavaAxe(owner);
		}else if(cardname.equals("Lightning Strike")){
			return Core2015.LightningStrike(owner);
		}else if(cardname.equals("Stoke the Flames")){
			return Core2015.StokeTheFlames(owner);
		}else if(cardname.equals("Crucible of Fire")){
			return Core2015.CrucibleOfFire(owner);
		}else if(cardname.equals("Inferno Fist")){
			return Core2015.InfernoFist(owner);
		}else if(cardname.equals("Carnivorus Moss-Beast")){
			return Core2015.CarnivorusMossBeast(owner);
		}else if(cardname.equals("Centaur Courser")){
			return Core2015.CentaurCourser(owner);
		}else if(cardname.equals("Elvish Mystic")){
			return Core2015.ElvishMystic(owner);
		}else if(cardname.equals("Garruk's Packleader")){
			return Core2015.GarruksPackleader(owner);
		}else if(cardname.equals("Hornet Queen")){
			return Core2015.HornetQueen(owner);
		}else if(cardname.equals("Kalonian Twingrove")){
			return Core2015.KalonianTwingrove(owner);
		}else if(cardname.equals("Living Totem")){
			return Core2015.LivingTotem(owner);
		}else if(cardname.equals("Paragon of Eternal Wilds")){
			return Core2015.ParagonOfEternalWilds(owner);
		}else if(cardname.equals("Reclamation Sage")){
			return Core2015.ReclamationSage(owner);
		}else if(cardname.equals("Runeclaw Bear")){
			return Core2015.RuneclawBear(owner);
		}else if(cardname.equals("Shaman of Spring")){
			return Core2015.ShamanOfSpring(owner);
		}else if(cardname.equals("Siege Wurm")){
			return Core2015.SiegeWurm(owner);
		}else if(cardname.equals("Soul of Zendikar")){
			return Core2015.SoulOfZendikar(owner);
		}else if(cardname.equals("Sunblade Elf")){
			return Core2015.SunbladeElf(owner);
		}else if(cardname.equals("Venom Sliver")){
			return Core2015.VenomSliver(owner);
		}else if(cardname.equals("Wall of Mulch")){
			return Core2015.WallOfMulch(owner);
		}else if(cardname.equals("Back to Nature")){
			return Core2015.BackToNature(owner);
		}else if(cardname.equals("Feral Incarnation")){
			return Core2015.FeralIncarnation(owner);
		}else if(cardname.equals("Gather Courage")){
			return Core2015.GatherCourage(owner);
		}else if(cardname.equals("Naturalize")){
			return Core2015.Naturalize(owner);
		}else if(cardname.equals("Nissa's Expedition")){
			return Core2015.NissasExpedition(owner);
		}else if(cardname.equals("Overwhelm")){
			return Core2015.Overwhelm(owner);
		}else if(cardname.equals("Titanic Growth")){
			return Core2015.TitanicGrowth(owner);
		}else if(cardname.equals("Aeronaut Tinkerer")){
			return Core2015.AeronautTinkerer(owner);
		}else if(cardname.equals("Amphin Pathmage")){
			return Core2015.AmphinPathmage(owner);
		}else if(cardname.equals("Fugitive Wizard")){
			return Core2015.FugitiveWizard(owner);
		}else if(cardname.equals("Jorubai Murk Lurker")){
			return Core2015.JorubaiMurkLurker(owner);
		}else if(cardname.equals("Kapsho Kitefins")){
			return Core2015.KapshoKitefins(owner);
		}else if(cardname.equals("Mahamoti Djinn")){
			return Core2015.MahamotiDjinn(owner);
		}else if(cardname.equals("Nimbus of the Isles")){
			return Core2015.NimbusOfTheIsles(owner);
		}else if(cardname.equals("Paragon of Gathering Mists")){
			return Core2015.ParagonOfGatheringMists(owner);
		}else if(cardname.equals("AEtherspouts")){
			return Core2015.AEtherspouts(owner);
		}else if(cardname.equals("Cancel")){
			return Core2015.Cancel(owner);
		}else if(cardname.equals("Divination")){
			return Core2015.Divination(owner);
		}else if(cardname.equals("Hydrosurge")){
			return Core2015.Hydrosurge(owner);
		}else if(cardname.equals("Jace's Ingenuity")){
			return Core2015.JacesIngenuity(owner);
		}else if(cardname.equals("Invisibility")){
			return Core2015.Invisibility(owner);
		}else if(cardname.equals("Aegis Angel")){
			return Core2015.AegisAngel(owner);
		}else if(cardname.equals("Ajani Steadfast")){
			return Core2015.AjaniSteadfast(owner);
		}else if(cardname.equals("Ajani's Pridemate")){
			return Core2015.AjanisPridemate(owner);
		}else if(cardname.equals("Avacyn, Guardian Angel")){
			return Core2015.AvacynGuardianAngel(owner);
		}else if(cardname.equals("Boonweaver Giant")){
			return Core2015.BoonweaverGiant(owner);
		}else if(cardname.equals("Constricting Sliver")){
			return Core2015.ConstrictingSliver(owner);
		}else if(cardname.equals("Dauntless River Marshal")){
			return Core2015.DauntlessRiverMarshal(owner);
		}else if(cardname.equals("Geist of the Moors")){
			return Core2015.GeistOfTheMoors(owner);
		}else if(cardname.equals("Heliod's Pilgrim")){
			return Core2015.HeliodsPilgrim(owner);
		}else if(cardname.equals("Hushwing Gryff")){
			return Core2015.HushwingGryff(owner);
		}else if(cardname.equals("Kinsbaile Skirmisher")){
			return Core2015.KinsbaileSkirmisher(owner);
		}else if(cardname.equals("Midnight Guard")){
			return Core2015.MidnightGuard(owner);
		}else if(cardname.equals("Oreskos Swiftclaw")){
			return Core2015.OreskosSwiftclaw(owner);
		}else if(cardname.equals("Paragon of New Dawns")){
			return Core2015.ParagonOfNewDawns(owner);
		}else if(cardname.equals("Preeminent Captain")){
			return Core2015.PreeminentCaptain(owner);
		}else if(cardname.equals("Razorfoot Griffin")){
			return Core2015.RazorfootGriffin(owner);
		}else if(cardname.equals("Resolute Archangel")){
			return Core2015.ResoluteArchangel(owner);
		}else if(cardname.equals("Selfless Cathar")){
			return Core2015.SelflessCathar(owner);
		}else if(cardname.equals("Seraph of the Masses")){
			return Core2015.SeraphOfTheMasses(owner);
		}else if(cardname.equals("Serra Angel")){
			return Core2015.SerraAngel(owner);
		}else if(cardname.equals("Soul of Theros")){
			return Core2015.SoulOfTheros(owner);
		}else if(cardname.equals("Soulmender")){
			return Core2015.Soulmender(owner);
		}else if(cardname.equals("Sungrace Pegasus")){
			return Core2015.SungracePegasus(owner);
		}else if(cardname.equals("Tireless Missionaries")){
			return Core2015.TirelessMissionaries(owner);
		}else if(cardname.equals("Wall of Essence")){
			return Core2015.WallOfEssence(owner);
		}else if(cardname.equals("Warden of the Beyond")){
			return Core2015.WardenOfTheBeyond(owner);
		}else if(cardname.equals("Congregate")){
			return Core2015.Congregate(owner);
		}else if(cardname.equals("Devouring Light")){
			return Core2015.DevouringLight(owner);
		}else if(cardname.equals("Divine Verdict")){
			return Core2015.DivineVerdict(owner);
		}else if(cardname.equals("Ephemeral Shields")){
			return Core2015.EphemeralShields(owner);
		}else if(cardname.equals("Inspired Charge")){
			return Core2015.InspiredCharge(owner);
		}else if(cardname.equals("Mass Calcify")){
			return Core2015.MassCalcify(owner);
		}else if(cardname.equals("Meditation Puzzle")){
			return Core2015.MeditationPuzzle(owner);
		}else if(cardname.equals("Pillar of Light")){
			return Core2015.PillarOfLight(owner);
		}else if(cardname.equals("Raise the Alarm")){
			return Core2015.RaiseTheAlarm(owner);
		}else if(cardname.equals("Return to the Ranks")){
			return Core2015.ReturnToTheRanks(owner);
		}else if(cardname.equals("Sanctified Charge")){
			return Core2015.SanctifiedCharge(owner);
		}else if(cardname.equals("Solemn Offering")){
			return Core2015.SolemnOffering(owner);
		}else if(cardname.equals("Triplicate Spirits")){
			return Core2015.TriplicateSpirits(owner);
		}else if(cardname.equals("Battle Mastery")){
			return Core2015.BattleMastery(owner);
		}else if(cardname.equals("Divine Favor")){
			return Core2015.DivineFavor(owner);
		}else if(cardname.equals("First Response")){
			return Core2015.FirstResponse(owner);
		}else if(cardname.equals("Marked by Honor")){
			return Core2015.MarkedByHonor(owner);
		}else if(cardname.equals("Oppressive Rays")){
			return Core2015.OppressiveRays(owner);
		}else if(cardname.equals("Spectra Ward")){
			return Core2015.SpectraWard(owner);
		}else if(cardname.equals("Spirit Bonds")){
			return Core2015.SpiritBonds(owner);
		}else if(cardname.equals("Blood Host")){
			return Core2015.BloodHost(owner);
		}else if(cardname.equals("Carrion Crow")){
			return Core2015.CarrionCrow(owner);
		}else if(cardname.equals("Child of Night")){
			return Core2015.ChildOfNight(owner);
		}else if(cardname.equals("Leeching Sliver")){
			return Core2015.LeechingSliver(owner);
		}else if(cardname.equals("Necrogen Scudder")){
			return Core2015.NecrogenScudder(owner);
		}else if(cardname.equals("Nightfire Giant")){
			return Core2015.NightfireGiant(owner);
		}else if(cardname.equals("Nightmare")){
			return Core2015.Nightmare(owner);
		}else if(cardname.equals("Paragon of Open Graves")){
			return Core2015.ParagonOfOpenGraves(owner);
		}else if(cardname.equals("Shadowcloak Vampire")){
			return Core2015.ShadowcloakVampire(owner);
		}else if(cardname.equals("Typhoid Rats")){
			return Core2015.TyphoidRats(owner);
		}else if(cardname.equals("Walking Corpse")){
			return Core2015.WalkingCorpse(owner);
		}else if(cardname.equals("Witch's Familiar")){
			return Core2015.WitchsFamiliar(owner);
		}else if(cardname.equals("Zof Shade")){
			return Core2015.ZofShade(owner);
		}else if(cardname.equals("Covenant of Blood")){
			return Core2015.CovenantOfBlood(owner);
		}else if(cardname.equals("Festergloom")){
			return Core2015.Festergloom(owner);
		}else if(cardname.equals("In Garruk's Wake")){
			return Core2015.InGarruksWake(owner);
		}else if(cardname.equals("Sign in Blood")){
			return Core2015.SignInBlood(owner);
		}else if(cardname.equals("Ulcerate")){
			return Core2015.Ulcerate(owner);
		}else if(cardname.equals("Caustic Tar")){
			return Core2015.CausticTar(owner);
		}else if(cardname.equals("Feast on the Fallen")){
			return Core2015.FeastOnTheFallen(owner);
		}else if(cardname.equals("Sliver Hivelord")){
			return Core2015.SliverHivelord(owner);
		}else if(cardname.equals("Ornithopter")){
			return Core2015.Ornithopter(owner);
		}else if(cardname.equals("Will-Forged Golem")){
			return Core2015.WillForgedGolem(owner);
		}else if(cardname.equals("Brawler's Plate")){
			return Core2015.BrawlersPlate(owner);
		}else if(cardname.equals("Meteorite")){
			return Core2015.Meteorite(owner);
		}else if(cardname.equals("Perilous Vault")){
			return Core2015.PerilousVault(owner);
		}else if(cardname.equals("Sacred Armory")){
			return Core2015.SacredArmory(owner);
		}else if(cardname.equals("Soul of New Phyrexia")){
			return Core2015.SoulOfNewPhyrexia(owner);
		}else if(cardname.equals("Tyrant's Machine")){
			return Core2015.TyrantsMachine(owner);
		}else if(cardname.equals("Battlefield Forge")){
			return Core2015.BattlefieldForge(owner);
		}else if(cardname.equals("Caves of Koilos")){
			return Core2015.CavesOfKoilos(owner);
		}else if(cardname.equals("Darksteel Citadel")){
			return Core2015.DarksteelCitadel(owner);
		}else if(cardname.equals("Evolving Wilds")){
			return Core2015.EvolvingWilds(owner);
		}else if(cardname.equals("Llanowar Wastes")){
			return Core2015.LlanowarWastes(owner);
		}else if(cardname.equals("Radiant Fountain")){
			return Core2015.RadiantFountain(owner);
		}else if(cardname.equals("Shivan Reef")){
			return Core2015.ShivanReef(owner);
		}else if(cardname.equals("Yavimaya Coast")){
			return Core2015.YavimayaCoast(owner);
		}
		// END CORE2015
		// BEGIN KHANSOFTARKIR
		else if(cardname.equals("Ainok Tracker")){
			return KhansOfTarkir.AinokTracker(owner);
		}else if(cardname.equals("Bloodfire Expert")){
			return KhansOfTarkir.BloodfireExpert(owner);
		}else if(cardname.equals("Canyon Lurkers")){
			return KhansOfTarkir.CanyonLurkers(owner);
		}else if(cardname.equals("Dragon-Style Twins")){
			return KhansOfTarkir.DragonStyleTwins(owner);
		}else if(cardname.equals("Leaping Master")){
			return KhansOfTarkir.LeapingMaster(owner);
		}else if(cardname.equals("Mardu Heart-Piercer")){
			return KhansOfTarkir.MarduHeartPiercer(owner);
		}else if(cardname.equals("Mardu Warshrieker")){
			return KhansOfTarkir.MarduWarshrieker(owner);
		}else if(cardname.equals("Monastery Swiftspear")){
			return KhansOfTarkir.MonasterySwiftspear(owner);
		}else if(cardname.equals("Summit Prowler")){
			return KhansOfTarkir.SummitProwler(owner);
		}else if(cardname.equals("Arc Lightning")){
			return KhansOfTarkir.ArcLightning(owner);
		}else if(cardname.equals("Hordeling Outburst")){
			return KhansOfTarkir.HordelingOutburst(owner);
		}else if(cardname.equals("Shatter")){
			return KhansOfTarkir.Shatter(owner);
		}else if(cardname.equals("Trumpet Blast")){
			return KhansOfTarkir.TrumpetBlast(owner);
		}else if(cardname.equals("Goblinslide")){
			return KhansOfTarkir.Goblinslide(owner);
		}else if(cardname.equals("Alpine Grizzly")){
			return KhansOfTarkir.AlpineGrizzly(owner);
		}else if(cardname.equals("Archers' Parapet")){
			return KhansOfTarkir.ArchersParapet(owner);
		}else if(cardname.equals("Hooting Mandrills")){
			return KhansOfTarkir.HootingMandrills(owner);
		}else if(cardname.equals("Longshot Squad")){
			return KhansOfTarkir.LongshotSquad(owner);
		}else if(cardname.equals("Pine Walker")){
			return KhansOfTarkir.PineWalker(owner);
		}else if(cardname.equals("Rattleclaw Mystic")){
			return KhansOfTarkir.RattleclawMystic(owner);
		}else if(cardname.equals("Sagu Archer")){
			return KhansOfTarkir.SaguArcher(owner);
		}else if(cardname.equals("Temur Charger")){
			return KhansOfTarkir.TemurCharger(owner);
		}else if(cardname.equals("Tusked Colossodon")){
			return KhansOfTarkir.TuskedColossodon(owner);
		}else if(cardname.equals("Tuskguard Captain")){
			return KhansOfTarkir.TuskguardCaptain(owner);
		}else if(cardname.equals("Woolly Loxodon")){
			return KhansOfTarkir.WoollyLoxodon(owner);
		}else if(cardname.equals("Awaken the Bear")){
			return KhansOfTarkir.AwakenTheBear(owner);
		}else if(cardname.equals("Become Immense")){
			return KhansOfTarkir.BecomeImmense(owner);
		}else if(cardname.equals("Dragonscale Boon")){
			return KhansOfTarkir.DragonscaleBoon(owner);
		}else if(cardname.equals("Incremental Growth")){
			return KhansOfTarkir.IncrementalGrowth(owner);
		}else if(cardname.equals("Seek the Horizon")){
			return KhansOfTarkir.SeekTheHorizon(owner);
		}else if(cardname.equals("Trail of Mystery")){
			return KhansOfTarkir.TrailOfMystery(owner);
		}else if(cardname.equals("Dragon's Eye Savants")){
			return KhansOfTarkir.DragonsEyeSavants(owner);
		}else if(cardname.equals("Embodiment of Spring")){
			return KhansOfTarkir.EmbodimentOfSpring(owner);
		}else if(cardname.equals("Glacial Stalker")){
			return KhansOfTarkir.GlacialStalker(owner);
		}else if(cardname.equals("Jeskai Windscout")){
			return KhansOfTarkir.JeskaiWindscout(owner);
		}else if(cardname.equals("Monastery Flock")){
			return KhansOfTarkir.MonasteryFlock(owner);
		}else if(cardname.equals("Mystic of the Hidden Way")){
			return KhansOfTarkir.MysticOfTheHiddenWay(owner);
		}else if(cardname.equals("Riverwheel Aerialists")){
			return KhansOfTarkir.RiverwheelAerialists(owner);
		}else if(cardname.equals("Scaldkin")){
			return KhansOfTarkir.Scaldkin(owner);
		}else if(cardname.equals("Scion of Glaciers")){
			return KhansOfTarkir.ScionOfGlaciers(owner);
		}else if(cardname.equals("Wetland Sambar")){
			return KhansOfTarkir.WetlandSambar(owner);
		}else if(cardname.equals("Blinding Spray")){
			return KhansOfTarkir.BlindingSpray(owner);
		}else if(cardname.equals("Crippling Chill")){
			return KhansOfTarkir.CripplingChill(owner);
		}else if(cardname.equals("Disdainful Stroke")){
			return KhansOfTarkir.DisdainfulStroke(owner);
		}else if(cardname.equals("Treasure Cruise")){
			return KhansOfTarkir.TreasureCruise(owner);
		}else if(cardname.equals("Weave Fate")){
			return KhansOfTarkir.WeaveFate(owner);
		}else if(cardname.equals("Quiet Contemplation")){
			return KhansOfTarkir.QuietContemplation(owner);
		}else if(cardname.equals("Singing Bell Strike")){
			return KhansOfTarkir.SingingBellStrike(owner);
		}else if(cardname.equals("Abzan Battle Priest")){
			return KhansOfTarkir.AbzanBattlePriest(owner);
		}else if(cardname.equals("Abzan Falconer")){
			return KhansOfTarkir.AbzanFalconer(owner);
		}else if(cardname.equals("Ainok Bond-Kin")){
			return KhansOfTarkir.AinokBondKin(owner);
		}else if(cardname.equals("Alabaster Kirin")){
			return KhansOfTarkir.AlabasterKirin(owner);
		}else if(cardname.equals("Dazzling Ramparts")){
			return KhansOfTarkir.DazzlingRamparts(owner);
		}else if(cardname.equals("Firehoof Cavalry")){
			return KhansOfTarkir.FirehoofCavalry(owner);
		}else if(cardname.equals("High Sentinels of Arashin")){
			return KhansOfTarkir.HighSentinelsOfArashin(owner);
		}else if(cardname.equals("Jeskai Student")){
			return KhansOfTarkir.JeskaiStudent(owner);
		}else if(cardname.equals("Mardu Hateblade")){
			return KhansOfTarkir.MarduHateblade(owner);
		}else if(cardname.equals("Mardu Hordechief")){
			return KhansOfTarkir.MarduHordechief(owner);
		}else if(cardname.equals("Master of Pearls")){
			return KhansOfTarkir.MasterOfPearls(owner);
		}else if(cardname.equals("Sage-Eye Harrier")){
			return KhansOfTarkir.SageEyeHarrier(owner);
		}else if(cardname.equals("Salt Road Patrol")){
			return KhansOfTarkir.SaltRoadPatrol(owner);
		}else if(cardname.equals("Seeker of the Way")){
			return KhansOfTarkir.SeekerOfTheWay(owner);
		}else if(cardname.equals("Timely Hordemate")){
			return KhansOfTarkir.TimelyHordemate(owner);
		}else if(cardname.equals("Venerable Lammasu")){
			return KhansOfTarkir.VenerableLammasu(owner);
		}else if(cardname.equals("War Behemoth")){
			return KhansOfTarkir.WarBehemoth(owner);
		}else if(cardname.equals("Watcher of the Roost")){
			return KhansOfTarkir.WatcherOfTheRoost(owner);
		}else if(cardname.equals("Wingmate Roc")){
			return KhansOfTarkir.WingmateRoc(owner);
		}else if(cardname.equals("Defiant Strike")){
			return KhansOfTarkir.DefiantStrike(owner);
		}else if(cardname.equals("End Hostilities")){
			return KhansOfTarkir.EndHostilities(owner);
		}else if(cardname.equals("Erase")){
			return KhansOfTarkir.Erase(owner);
		}else if(cardname.equals("Feat of Resistance")){
			return KhansOfTarkir.FeatOfResistance(owner);
		}else if(cardname.equals("Kill Shot")){
			return KhansOfTarkir.KillShot(owner);
		}else if(cardname.equals("Rush of Battle")){
			return KhansOfTarkir.RushOfBattle(owner);
		}else if(cardname.equals("Smite the Monstrous")){
			return KhansOfTarkir.SmiteTheMonstrous(owner);
		}else if(cardname.equals("Take Up Arms")){
			return KhansOfTarkir.TakeUpArms(owner);
		}else if(cardname.equals("Brave the Sands")){
			return KhansOfTarkir.BraveTheSands(owner);
		}else if(cardname.equals("Siegecraft")){
			return KhansOfTarkir.Siegecraft(owner);
		}else if(cardname.equals("Suspension Field")){
			return KhansOfTarkir.SuspensionField(owner);
		}else if(cardname.equals("Bellowing Saddlebrute")){
			return KhansOfTarkir.BellowingSaddlebrute(owner);
		}else if(cardname.equals("Disowned Ancestor")){
			return KhansOfTarkir.DisownedAncestor(owner);
		}else if(cardname.equals("Gurmag Swiftwing")){
			return KhansOfTarkir.GurmagSwiftwing(owner);
		}else if(cardname.equals("Krumar Bond-Kin")){
			return KhansOfTarkir.KrumarBondKin(owner);
		}else if(cardname.equals("Mer-Ek Nightblade")){
			return KhansOfTarkir.MerEkNightblade(owner);
		}else if(cardname.equals("Rotting Mastodon")){
			return KhansOfTarkir.RottingMastodon(owner);
		}else if(cardname.equals("Ruthless Ripper")){
			return KhansOfTarkir.RuthlessRipper(owner);
		}else if(cardname.equals("Shambling Attendants")){
			return KhansOfTarkir.ShamblingAttendants(owner);
		}else if(cardname.equals("Sidisi's Pet")){
			return KhansOfTarkir.SidisisPet(owner);
		}else if(cardname.equals("Sultai Scavenger")){
			return KhansOfTarkir.SultaiScavenger(owner);
		}else if(cardname.equals("Unyielding Krumar")){
			return KhansOfTarkir.UnyieldingKrumar(owner);
		}else if(cardname.equals("Murderous Cut")){
			return KhansOfTarkir.MurderousCut(owner);
		}else if(cardname.equals("Throttle")){
			return KhansOfTarkir.Throttle(owner);
		}else if(cardname.equals("Debilitating Injury")){
			return KhansOfTarkir.DebilitatingInjury(owner);
		}else if(cardname.equals("Chief of the Edge")){
			return KhansOfTarkir.ChiefOfTheEdge(owner);
		}else if(cardname.equals("Chief of the Scale")){
			return KhansOfTarkir.ChiefOfTheScale(owner);
		}else if(cardname.equals("Highspire Mantis")){
			return KhansOfTarkir.HighspireMantis(owner);
		}else if(cardname.equals("Utter End")){
			return KhansOfTarkir.UtterEnd(owner);
		}else if(cardname.equals("Abzan Guide")){
			return KhansOfTarkir.AbzanGuide(owner);
		}else if(cardname.equals("Ankle Shanker")){
			return KhansOfTarkir.AnkleShanker(owner);
		}else if(cardname.equals("Armament Corps")){
			return KhansOfTarkir.ArmamentCorps(owner);
		}else if(cardname.equals("Bear's Companion")){
			return KhansOfTarkir.BearsCompanion(owner);
		}else if(cardname.equals("Efreet Weaponmaster")){
			return KhansOfTarkir.EfreetWeaponmaster(owner);
		}else if(cardname.equals("Ivorytusk Fortress")){
			return KhansOfTarkir.IvorytuskFortress(owner);
		}else if(cardname.equals("Mantis Rider")){
			return KhansOfTarkir.MantisRider(owner);
		}else if(cardname.equals("Ponyback Brigade")){
			return KhansOfTarkir.PonybackBrigade(owner);
		}else if(cardname.equals("Sage of the Inward Eye")){
			return KhansOfTarkir.SageOfTheInwardEye(owner);
		}else if(cardname.equals("Siege Rhino")){
			return KhansOfTarkir.SiegeRhino(owner);
		}else if(cardname.equals("Snowhorn Rider")){
			return KhansOfTarkir.SnowhornRider(owner);
		}else if(cardname.equals("Flying Crane Technique")){
			return KhansOfTarkir.FlyingCraneTechnique(owner);
		}else if(cardname.equals("Trap Essence")){
			return KhansOfTarkir.TrapEssence(owner);
		}else if(cardname.equals("Jeskai Ascendancy")){
			return KhansOfTarkir.JeskaiAscendancy(owner);
		}else if(cardname.equals("Temur Ascendancy")){
			return KhansOfTarkir.TemurAscendancy(owner);
		}else if(cardname.equals("Witness of the Ages")){
			return KhansOfTarkir.WitnessOfTheAges(owner);
		}else if(cardname.equals("Abzan Banner")){
			return KhansOfTarkir.AbzanBanner(owner);
		}else if(cardname.equals("Jeskai Banner")){
			return KhansOfTarkir.JeskaiBanner(owner);
		}else if(cardname.equals("Mardu Banner")){
			return KhansOfTarkir.MarduBanner(owner);
		}else if(cardname.equals("Sultai Banner")){
			return KhansOfTarkir.SultaiBanner(owner);
		}else if(cardname.equals("Temur Banner")){
			return KhansOfTarkir.TemurBanner(owner);
		}else if(cardname.equals("Bloodfell Caves")){
			return KhansOfTarkir.BloodfellCaves(owner);
		}else if(cardname.equals("Bloodstained Mire")){
			return KhansOfTarkir.BloodstainedMire(owner);
		}else if(cardname.equals("Blossoming Sands")){
			return KhansOfTarkir.BlossomingSands(owner);
		}else if(cardname.equals("Dismal Backwater")){
			return KhansOfTarkir.DismalBackwater(owner);
		}else if(cardname.equals("Flooded Strand")){
			return KhansOfTarkir.FloodedStrand(owner);
		}else if(cardname.equals("Frontier Bivouac")){
			return KhansOfTarkir.FrontierBivouac(owner);
		}else if(cardname.equals("Jungle Hollow")){
			return KhansOfTarkir.JungleHollow(owner);
		}else if(cardname.equals("Mystic Monastery")){
			return KhansOfTarkir.MysticMonastery(owner);
		}else if(cardname.equals("Nomad Outpost")){
			return KhansOfTarkir.NomadOutpost(owner);
		}else if(cardname.equals("Opulent Palace")){
			return KhansOfTarkir.OpulentPalace(owner);
		}else if(cardname.equals("Polluted Delta")){
			return KhansOfTarkir.PollutedDelta(owner);
		}else if(cardname.equals("Rugged Highlands")){
			return KhansOfTarkir.RuggedHighlands(owner);
		}else if(cardname.equals("Sandsteppe Citadel")){
			return KhansOfTarkir.SandsteppeCitadel(owner);
		}else if(cardname.equals("Scoured Barrens")){
			return KhansOfTarkir.ScouredBarrens(owner);
		}else if(cardname.equals("Swiftwater Cliffs")){
			return KhansOfTarkir.SwiftwaterCliffs(owner);
		}else if(cardname.equals("Thornwood Falls")){
			return KhansOfTarkir.ThornwoodFalls(owner);
		}else if(cardname.equals("Tranquil Cove")){
			return KhansOfTarkir.TranquilCove(owner);
		}else if(cardname.equals("Wind-Scarred Crag")){
			return KhansOfTarkir.WindScarredCrag(owner);
		}else if(cardname.equals("Windswept Heath")){
			return KhansOfTarkir.WindsweptHeath(owner);
		}else if(cardname.equals("Wooded Foothills")){
			return KhansOfTarkir.WoodedFoothills(owner);
		}else if(cardname.equals("Tomb of the Spirit Dragon")){
			return KhansOfTarkir.TombOfTheSpiritDragon(owner);
		}
		// END KHANSOFTARKIR
		// BEGIN DRAGONSOFTARKIR
		else if(cardname.equals("Aerie Bowmasters")){
			return DragonsOfTarkir.AerieBowmasters(owner);
		}
		// END DRAGONSOFTARKIR
		else if(cardname.equals("Mountain")){
			return BasicLand.GenerateMountain(owner);
		}else if(cardname.equals("Forest")){
			return BasicLand.GenerateForest(owner);
		}else if(cardname.equals("Island")){
			return BasicLand.GenerateIsland(owner);
		}else if(cardname.equals("Plains")){
			return BasicLand.GeneratePlains(owner);
		}else if(cardname.equals("Swamp")){
			return BasicLand.GenerateSwamp(owner);
		}else{
			System.out.println("Unknown card: "+cardname);
			return null;
		}
	}

	public ArrayList<CardBase> GetCards(TargetInfo specs){
		ArrayList<CardBase> cards=new ArrayList<CardBase>();
		for(int i=0;i<this.library.size();i++){
			CardBase ccard=this.library.elementAt(i);
			if(specs.IsValidTarget(ccard))
				cards.add(ccard);
		}
		return cards;
	}	
	
	public void Shuffle(){
		for(int i=0;i<this.library.size();i++){
			CardBase t=this.library.elementAt(i);
			int swapindex=(int)(Math.random()*this.library.size());
			if(swapindex==i)
				continue;
			this.library.set(i, this.library.elementAt(swapindex));
			this.library.set(swapindex, t);
		}
	}
	
	public void RemoveCard(CardBase card){
		this.library.remove(card);
	}
	
	public boolean HasCard(CardBase card){
		return this.library.contains(card);
	}

	public CardBase DrawCard(){
		if(this.library.size()==0)
			return null;
		CardBase drawnCard=this.library.remove(this.library.size()-1);
		drawnCard.MoveTo(this.minX, this.minY);
		return drawnCard;
	}
	
	public CardBase[] DrawXCards(int drawAmount){
		int trueDrawAmount=drawAmount;
		if(this.library.size()<drawAmount)
			trueDrawAmount=this.library.size();
		CardBase[] drawingCards=new CardBase[trueDrawAmount];
		for(int i=0;i<trueDrawAmount;i++){
			drawingCards[i]=this.DrawCard();
		}
		return drawingCards;
	}
	
	public void PlaceCardOnBottom(CardBase card){
		this.library.add(0,card);
		card.SetAnimationDestination(this.minX, this.minY, OptionsScreen.AnimationSpeed);
	}
	
	public void PlaceCardOnTop(CardBase card){
		this.library.add(card);
		card.SetAnimationDestination(this.minX, this.minY, OptionsScreen.AnimationSpeed);
	}

	public void paint(Graphics g){
		Painting.FillRectWithBorder(g, this.minX, this.minY, this.GetWidth(), this.GetHeight(), this.library.size()>0?CardBase.FACE_DOWN_COLOR:Color.white, Color.black);
		Painting.FillRectWithBorder(g, this.minX+2, this.minY+2, 30, 16, Color.white, Color.black);
		g.setColor(Color.black);
		g.drawString(""+this.library.size(), this.minX+4, this.minY+16);
	}
}
