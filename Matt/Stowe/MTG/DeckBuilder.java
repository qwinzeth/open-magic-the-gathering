package Matt.Stowe.MTG;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Cards.Sets.*;
import Matt.Stowe.Common.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DeckBuilder{
	private ArrayList<DeckBuilderCard> deck;
	private ArrayList<GUIClickableText> availableCards;
	private GUIButton btnExit;
	private GUIButton btnSave;
	private GUIButton btnPreviousColor;
	private GUIButton btnNextColor;
	private GUIButton btnPreviousSet;
	private GUIButton btnNextSet;
	private CardBase cardPreview;
	private GUIFileSelecter deckLoader;
	private String currentDeckName;
	private String displayMessage;
	private int lands;
	private int creatures;
	private int others;
	private String currentSetName;
	private Player player;
	
	private static final String[] SETNAMES=new String[]{"Theros", "Core 2015", "Khans of Tarkir"};
	private static final String[] COLORNAMES=new String[]{"Red", "Green", "Blue", "White", "Black", "Multicolored/Colorless"};
	private int setIndex;
	private int colorIndex;
	
	public DeckBuilder(){
		this.player=new Player(0, OptionsScreen.CONTROLTYPE_LOCALHUMAN, "you", true, 0, 0, 50, 50);
		this.btnExit=new GUIButton(30, 50, 50, 16, "Exit");
		this.btnSave=new GUIButton(350, 50, 50, 16, "Save");
		this.btnPreviousColor=new GUIButton(700, 50, 40, 16, "Prev");
		this.btnNextColor=new GUIButton(750, 50, 40, 16, "Next");
		this.btnPreviousSet=new GUIButton(660, 30, 60, 16, "Prev Set");
		this.btnNextSet=new GUIButton(730, 30, 60, 16, "Next Set");
		this.displayMessage=null;
		this.lands=0;
		this.creatures=0;
		this.others=0;
		
		this.deck=new ArrayList<DeckBuilderCard>(70);
		this.deckLoader=new GUIFileSelecter("MTGDecks", true, 160, 50);
		this.availableCards=new ArrayList<GUIClickableText>();

		this.setIndex=SETNAMES.length-1;
		this.loadCards();
		this.deckLoader.SelectFile(0);
		this.handleDeckLoading();
	}

	private void loadCards(){
		this.currentSetName=SETNAMES[this.setIndex]+" "+COLORNAMES[this.colorIndex];
		if("Theros".equals(SETNAMES[this.setIndex])){
			this.LoadTheros();
		}else if("Core 2015 Red".equals(this.currentSetName)){
			this.LoadCore2015Red();
		}else if("Core 2015 Green".equals(this.currentSetName)){
			this.LoadCore2015Green();
		}else if("Core 2015 Blue".equals(this.currentSetName)){
			this.LoadCore2015Blue();
		}else if("Core 2015 White".equals(this.currentSetName)){
			this.LoadCore2015White();
		}else if("Core 2015 Black".equals(this.currentSetName)){
			this.LoadCore2015Black();
		}else if("Core 2015 Multicolored/Colorless".equals(this.currentSetName)){
			this.LoadCore2015Colorless();
		}else if("Khans of Tarkir Red".equals(this.currentSetName)){
			this.LoadKhansOfTarkirRed();
		}else if("Khans of Tarkir Green".equals(this.currentSetName)){
			this.LoadKhansOfTarkirGreen();
		}else if("Khans of Tarkir Blue".equals(this.currentSetName)){
			this.LoadKhansOfTarkirBlue();
		}else if("Khans of Tarkir White".equals(this.currentSetName)){
			this.LoadKhansOfTarkirWhite();
		}else if("Khans of Tarkir Black".equals(this.currentSetName)){
			this.LoadKhansOfTarkirBlack();
		}else if("Khans of Tarkir Multicolored/Colorless".equals(this.currentSetName)){
			this.LoadKhansOfTarkirColorless();
		}
	}

	private void LoadTheros(){
		this.availableCards.clear();
// REGION RED
	// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Borderland Minotaur", 0, 0));
		this.availableCards.add(new GUIClickableText("Minotaur Skullcleaver", 0, 0));
		this.availableCards.add(new GUIClickableText("Ill-Tempered Cyclops", 0, 0));
		this.availableCards.add(new GUIClickableText("Satyr Rambler", 0, 0));
	// ENDREGION CREATURES
	// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Demolish", 0, 0));
		this.availableCards.add(new GUIClickableText("Titan's Strength", 0, 0));
	// ENDREGION INSTANTS
	// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Messenger's Speed", 0, 0));
	// ENDREGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Mountain", 0, 0));
// ENDREGION RED
// REGION GREEN
	// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Nessian Asp", 0, 0));
		this.availableCards.add(new GUIClickableText("Nessian Courser", 0, 0));
		this.availableCards.add(new GUIClickableText("Pheres-Band Centaurs", 0, 0));
		this.availableCards.add(new GUIClickableText("Vulpine Goliath", 0, 0));
	// ENDREGION CREATURES
	// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Feral Invocation", 0, 0));
	// ENDREGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Forest", 0, 0));
// ENDREGION GREEN

// REGION BLUE
	// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Horizon Scholar", 0, 0));
		this.availableCards.add(new GUIClickableText("Omenspeaker", 0, 0));
		this.availableCards.add(new GUIClickableText("Triton Shorethief", 0, 0));
	// ENDREGION CREATURES
	// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Lost in a Labyrinth", 0, 0));
	// ENDREGION INSTANTS
	// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Aqueous Form", 0, 0));
	// ENDREGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Island", 0, 0));
// ENDREGION BLUE

// REGION WHITE
	// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Decorated Griffin", 0, 0));
		this.availableCards.add(new GUIClickableText("Silent Artisan", 0, 0));
		this.availableCards.add(new GUIClickableText("Traveling Philosopher", 0, 0));
		this.availableCards.add(new GUIClickableText("Yoked Ox", 0, 0));
	// ENDREGION CREATURES
	// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Battlewise Valor", 0, 0));
		this.availableCards.add(new GUIClickableText("Dauntless Onslaught", 0, 0));
		this.availableCards.add(new GUIClickableText("Ray of Dissolution", 0, 0));
	// ENDREGION INSTANTS
	// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Chosen by Heliod", 0, 0));
	// ENDREGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Plains", 0, 0));
// ENDREGION WHITE

// REGION BLACK
	// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Insatiable Harpy", 0, 0));
		this.availableCards.add(new GUIClickableText("Felhide Minotaur", 0, 0));
	// ENDREGION CREATURES
	// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Lash of the Whip", 0, 0));
	// ENDREGION INSTANTS
	// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Scourgemark", 0, 0));
	// ENDREGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Swamp", 0, 0));
// ENDREGION BLACK

// REGION COLORLESS
	// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Bronze Sable", 0, 0));
		this.availableCards.add(new GUIClickableText("Guardians of Meletis", 0, 0));
	// ENDREGION CREATURES
// ENDREGION COLORLESS
		this.finalizeSetLoading();
	}
	
	private void LoadCore2015Red(){
		this.availableCards.clear();
	// REGION CREATURES
	this.availableCards.add(new GUIClickableText("Borderland Marauder", 0, 0));
	this.availableCards.add(new GUIClickableText("Forge Devil", 0, 0));
	this.availableCards.add(new GUIClickableText("Foundry Street Denizen", 0, 0));
	this.availableCards.add(new GUIClickableText("Furnace Whelp", 0, 0));
	this.availableCards.add(new GUIClickableText("Goblin Roughrider", 0, 0));
	this.availableCards.add(new GUIClickableText("Kird Chieftain", 0, 0));
	this.availableCards.add(new GUIClickableText("Miner's Bane", 0, 0));
	this.availableCards.add(new GUIClickableText("Paragon of Fierce Defiance", 0, 0));
	this.availableCards.add(new GUIClickableText("Scrapyard Mongrel", 0, 0));
	this.availableCards.add(new GUIClickableText("Shivan Dragon", 0, 0));
	this.availableCards.add(new GUIClickableText("Thundering Giant", 0, 0));
	this.availableCards.add(new GUIClickableText("Torch Fiend", 0, 0));
	this.availableCards.add(new GUIClickableText("Wall of Fire", 0, 0));
	// ENDREGION CREATURES
	// REGION INSTANTS AND SORCERIES
	this.availableCards.add(new GUIClickableText("Cone of Flame", 0, 0));
	this.availableCards.add(new GUIClickableText("Crowd's Favor", 0, 0));
	this.availableCards.add(new GUIClickableText("Heat Ray", 0, 0));
	this.availableCards.add(new GUIClickableText("Lava Axe", 0, 0));
	this.availableCards.add(new GUIClickableText("Lightning Strike", 0, 0));
	this.availableCards.add(new GUIClickableText("Stoke the Flames", 0, 0));
	// ENDREGION INSTANTS AND SORCERIES
	// REGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Crucible of Fire", 0, 0));
	this.availableCards.add(new GUIClickableText("Inferno Fist", 0, 0));
	// ENDREGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Mountain", 0, 0));
	this.finalizeSetLoading();
	}
	
	private void LoadCore2015Green(){
		this.availableCards.clear();
	// REGION CREATURES
	this.availableCards.add(new GUIClickableText("Carnivorus Moss-Beast", 0, 0));
	this.availableCards.add(new GUIClickableText("Centaur Courser", 0, 0));
	this.availableCards.add(new GUIClickableText("Elvish Mystic", 0, 0));
	this.availableCards.add(new GUIClickableText("Garruk's Packleader", 0, 0));
	this.availableCards.add(new GUIClickableText("Hornet Queen", 0, 0));
	this.availableCards.add(new GUIClickableText("Kalonian Twingrove", 0, 0));
	this.availableCards.add(new GUIClickableText("Living Totem", 0, 0));
	this.availableCards.add(new GUIClickableText("Paragon of Eternal Wilds", 0, 0));
	this.availableCards.add(new GUIClickableText("Reclamation Sage", 0, 0));
	this.availableCards.add(new GUIClickableText("Runeclaw Bear", 0, 0));
	this.availableCards.add(new GUIClickableText("Shaman of Spring", 0, 0));
	this.availableCards.add(new GUIClickableText("Siege Wurm", 0, 0));
	this.availableCards.add(new GUIClickableText("Soul of Zendikar", 0, 0));
	this.availableCards.add(new GUIClickableText("Sunblade Elf", 0, 0));
	this.availableCards.add(new GUIClickableText("Venom Sliver", 0, 0));
	this.availableCards.add(new GUIClickableText("Wall of Mulch", 0, 0));
	// ENDREGION CREATURES
	// REGION INSTANTS
	this.availableCards.add(new GUIClickableText("Back to Nature", 0, 0));
	this.availableCards.add(new GUIClickableText("Feral Incarnation", 0, 0));
	this.availableCards.add(new GUIClickableText("Gather Courage", 0, 0));
	this.availableCards.add(new GUIClickableText("Naturalize", 0, 0));
	this.availableCards.add(new GUIClickableText("Nissa's Expedition", 0, 0));
	this.availableCards.add(new GUIClickableText("Overwhelm", 0, 0));
	this.availableCards.add(new GUIClickableText("Titanic Growth", 0, 0));
	// ENDREGION INSTANTS
	// REGION ENCHANTMENTS
	// ENDREGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Forest", 0, 0));
	this.finalizeSetLoading();
	}

	private void LoadCore2015Blue(){
		this.availableCards.clear();

	// REGION CREATURES
	this.availableCards.add(new GUIClickableText("Aeronaut Tinkerer", 0, 0));
	this.availableCards.add(new GUIClickableText("Amphin Pathmage", 0, 0));
	this.availableCards.add(new GUIClickableText("Fugitive Wizard", 0, 0));
	this.availableCards.add(new GUIClickableText("Kapsho Kitefins", 0, 0));
	this.availableCards.add(new GUIClickableText("Jorubai Murk Lurker", 0, 0));
	this.availableCards.add(new GUIClickableText("Mahamoti Djinn", 0, 0));
	this.availableCards.add(new GUIClickableText("Nimbus of the Isles", 0, 0));
	this.availableCards.add(new GUIClickableText("Paragon of Gathering Mists", 0, 0));
	// ENDREGION CREATURES
	// REGION INSTANTS
	this.availableCards.add(new GUIClickableText("AEtherspouts", 0, 0));
	this.availableCards.add(new GUIClickableText("Cancel", 0, 0));
	this.availableCards.add(new GUIClickableText("Divination", 0, 0));
	this.availableCards.add(new GUIClickableText("Hydrosurge", 0, 0));
	this.availableCards.add(new GUIClickableText("Jace's Ingenuity", 0, 0));
	// ENDREGION INSTANTS
	// REGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Invisibility", 0, 0));
	// ENDREGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Island", 0, 0));
	this.finalizeSetLoading();
}

	private void LoadCore2015White(){
		this.availableCards.clear();

	// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Aegis Angel", 0, 0));
		this.availableCards.add(new GUIClickableText("Ajani Steadfast", 0, 0));
		this.availableCards.add(new GUIClickableText("Ajani's Pridemate", 0, 0));
		this.availableCards.add(new GUIClickableText("Avacyn, Guardian Angel", 0, 0));
		this.availableCards.add(new GUIClickableText("Boonweaver Giant", 0, 0));
		this.availableCards.add(new GUIClickableText("Constricting Sliver", 0, 0));
		this.availableCards.add(new GUIClickableText("Dauntless River Marshal", 0, 0));
		this.availableCards.add(new GUIClickableText("Geist of the Moors", 0, 0));
		this.availableCards.add(new GUIClickableText("Heliod's Pilgrim", 0, 0));
		this.availableCards.add(new GUIClickableText("Hushwing Gryff", 0, 0));
		this.availableCards.add(new GUIClickableText("Kinsbaile Skirmisher", 0, 0));
		this.availableCards.add(new GUIClickableText("Midnight Guard", 0, 0));
		this.availableCards.add(new GUIClickableText("Oreskos Swiftclaw", 0, 0));
		this.availableCards.add(new GUIClickableText("Paragon of New Dawns", 0, 0));
		this.availableCards.add(new GUIClickableText("Preeminent Captain", 0, 0));
		this.availableCards.add(new GUIClickableText("Razorfoot Griffin", 0, 0));
		this.availableCards.add(new GUIClickableText("Resolute Archangel", 0, 0));
		this.availableCards.add(new GUIClickableText("Selfless Cathar", 0, 0));
		this.availableCards.add(new GUIClickableText("Seraph of the Masses", 0, 0));
		this.availableCards.add(new GUIClickableText("Serra Angel", 0, 0));
		this.availableCards.add(new GUIClickableText("Soul of Theros", 0, 0));
		this.availableCards.add(new GUIClickableText("Soulmender", 0, 0));
		this.availableCards.add(new GUIClickableText("Sungrace Pegasus", 0, 0));
		this.availableCards.add(new GUIClickableText("Tireless Missionaries", 0, 0));
		this.availableCards.add(new GUIClickableText("Wall of Essence", 0, 0));
		this.availableCards.add(new GUIClickableText("Warden of the Beyond", 0, 0));
	// ENDREGION CREATURES
	// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Congregate", 0, 0));
		this.availableCards.add(new GUIClickableText("Devouring Light", 0, 0));
		this.availableCards.add(new GUIClickableText("Divine Verdict", 0, 0));
		this.availableCards.add(new GUIClickableText("Ephemeral Shields", 0, 0));
		this.availableCards.add(new GUIClickableText("Inspired Charge", 0, 0));
		this.availableCards.add(new GUIClickableText("Mass Calcify", 0, 0));
		this.availableCards.add(new GUIClickableText("Meditation Puzzle", 0, 0));
		this.availableCards.add(new GUIClickableText("Pillar of Light", 0, 0));
		this.availableCards.add(new GUIClickableText("Raise the Alarm", 0, 0));
		this.availableCards.add(new GUIClickableText("Return to the Ranks", 0, 0));
		this.availableCards.add(new GUIClickableText("Sanctified Charge", 0, 0));
		this.availableCards.add(new GUIClickableText("Solemn Offering", 0, 0));
		this.availableCards.add(new GUIClickableText("Triplicate Spirits", 0, 0));
	// ENDREGION INSTANTS
	// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Battle Mastery", 0, 0));
		this.availableCards.add(new GUIClickableText("Divine Favor", 0, 0));
		this.availableCards.add(new GUIClickableText("First Response", 0, 0));
		this.availableCards.add(new GUIClickableText("Marked by Honor", 0, 0));
		this.availableCards.add(new GUIClickableText("Oppressive Rays", 0, 0));
		this.availableCards.add(new GUIClickableText("Spectra Ward", 0, 0));
		this.availableCards.add(new GUIClickableText("Spirit Bonds", 0, 0));
	// ENDREGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Plains", 0, 0));
	this.finalizeSetLoading();
	}

	private void LoadCore2015Black(){
		this.availableCards.clear();
	// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Blood Host", 0, 0));
		this.availableCards.add(new GUIClickableText("Carrion Crow", 0, 0));
		this.availableCards.add(new GUIClickableText("Child of Night", 0, 0));
		this.availableCards.add(new GUIClickableText("Leeching Sliver", 0, 0));
		this.availableCards.add(new GUIClickableText("Necrogen Scudder", 0, 0));
		this.availableCards.add(new GUIClickableText("Nightfire Giant", 0, 0));
		this.availableCards.add(new GUIClickableText("Nightmare", 0, 0));
		this.availableCards.add(new GUIClickableText("Paragon of Open Graves", 0, 0));
		this.availableCards.add(new GUIClickableText("Shadowcloak Vampire", 0, 0));
		this.availableCards.add(new GUIClickableText("Typhoid Rats", 0, 0));
		this.availableCards.add(new GUIClickableText("Walking Corpse", 0, 0));
		this.availableCards.add(new GUIClickableText("Witch's Familiar", 0, 0));
		this.availableCards.add(new GUIClickableText("Zof Shade", 0, 0));
	// ENDREGION CREATURES
	// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Covenant of Blood", 0, 0));
		this.availableCards.add(new GUIClickableText("Festergloom", 0, 0));
		this.availableCards.add(new GUIClickableText("In Garruk's Wake", 0, 0));
		this.availableCards.add(new GUIClickableText("Sign in Blood", 0, 0));
		this.availableCards.add(new GUIClickableText("Ulcerate", 0, 0));
	// ENDREGION INSTANTS
	// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Caustic Tar", 0, 0));
		this.availableCards.add(new GUIClickableText("Feast on the Fallen", 0, 0));
	// ENDREGION ENCHANTMENTS
	this.availableCards.add(new GUIClickableText("Swamp", 0, 0));
		this.finalizeSetLoading();
	}

	private void LoadCore2015Colorless(){
		this.availableCards.clear();
	// REGION CREATURES
	this.availableCards.add(new GUIClickableText("Sliver Hivelord", 0, 0));
	this.availableCards.add(new GUIClickableText("Bronze Sable", 0, 0));
	this.availableCards.add(new GUIClickableText("Ornithopter", 0, 0));
	this.availableCards.add(new GUIClickableText("Soul of New Phyrexia", 0, 0));
	this.availableCards.add(new GUIClickableText("Will-Forged Golem", 0, 0));
	// ENDREGION CREATURES
	// REGION ARTIFACTS
	this.availableCards.add(new GUIClickableText("Brawler's Plate", 0, 0));
	this.availableCards.add(new GUIClickableText("Meteorite", 0, 0));
	this.availableCards.add(new GUIClickableText("Perilous Vault", 0, 0));
	this.availableCards.add(new GUIClickableText("Sacred Armory", 0, 0));
	this.availableCards.add(new GUIClickableText("Tyrant's Machine", 0, 0));
	// ENDREGION ARTIFACTS
// REGION LANDS
	this.availableCards.add(new GUIClickableText("Battlefield Forge", 0, 0));
	this.availableCards.add(new GUIClickableText("Caves of Koilos", 0, 0));
	this.availableCards.add(new GUIClickableText("Darksteel Citadel", 0, 0));
	this.availableCards.add(new GUIClickableText("Evolving Wilds", 0, 0));
	this.availableCards.add(new GUIClickableText("Llanowar Wastes", 0, 0));
	this.availableCards.add(new GUIClickableText("Radiant Fountain", 0, 0));
	this.availableCards.add(new GUIClickableText("Shivan Reef", 0, 0));
	this.availableCards.add(new GUIClickableText("Yavimaya Coast", 0, 0));
// ENDREGION LANDS
		this.finalizeSetLoading();
	}
	
	public void LoadKhansOfTarkirRed(){
		this.availableCards.clear();
// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Ainok Tracker", 0, 0));
		this.availableCards.add(new GUIClickableText("Bloodfire Expert", 0, 0));
		this.availableCards.add(new GUIClickableText("Canyon Lurkers", 0, 0));
		this.availableCards.add(new GUIClickableText("Dragon-Style Twins", 0, 0));
		this.availableCards.add(new GUIClickableText("Leaping Master", 0, 0));
		this.availableCards.add(new GUIClickableText("Mardu Heart-Piercer", 0, 0));
		this.availableCards.add(new GUIClickableText("Mardu Warshrieker", 0, 0));
		this.availableCards.add(new GUIClickableText("Monastery Swiftspear", 0, 0));
		this.availableCards.add(new GUIClickableText("Summit Prowler", 0, 0));
// ENDREGION CREATURES
// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Arc Lightning", 0, 0));
		this.availableCards.add(new GUIClickableText("Hordeling Outburst", 0, 0));
		this.availableCards.add(new GUIClickableText("Shatter", 0, 0));
		this.availableCards.add(new GUIClickableText("Trumpet Blast", 0, 0));
// ENDREGION INSTANTS
// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Goblinslide", 0, 0));
// ENDREGION ENCHANTMENTS
// REGION LANDS
// ENDREGION LANDS
		this.availableCards.add(new GUIClickableText("Mountain", 0, 0));
		this.finalizeSetLoading();
	}

	public void LoadKhansOfTarkirGreen(){
		this.availableCards.clear();
// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Alpine Grizzly", 0, 0));
		this.availableCards.add(new GUIClickableText("Archers' Parapet", 0, 0));
		this.availableCards.add(new GUIClickableText("Hooting Mandrills", 0, 0));
		this.availableCards.add(new GUIClickableText("Longshot Squad", 0, 0));
		this.availableCards.add(new GUIClickableText("Pine Walker", 0, 0));
		this.availableCards.add(new GUIClickableText("Rattleclaw Mystic", 0, 0));
		this.availableCards.add(new GUIClickableText("Sagu Archer", 0, 0));
		this.availableCards.add(new GUIClickableText("Temur Charger", 0, 0));
		this.availableCards.add(new GUIClickableText("Tusked Colossodon", 0, 0));
		this.availableCards.add(new GUIClickableText("Tuskguard Captain", 0, 0));
		this.availableCards.add(new GUIClickableText("Woolly Loxodon", 0, 0));
// ENDREGION CREATURES
// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Awaken the Bear", 0, 0));
		this.availableCards.add(new GUIClickableText("Become Immense", 0, 0));
		this.availableCards.add(new GUIClickableText("Dragonscale Boon", 0, 0));
		this.availableCards.add(new GUIClickableText("Incremental Growth", 0, 0));
		this.availableCards.add(new GUIClickableText("Naturalize", 0, 0));
		this.availableCards.add(new GUIClickableText("Seek the Horizon", 0, 0));
// ENDREGION INSTANTS
// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Trail of Mystery", 0, 0));
// ENDREGION ENCHANTMENTS
// REGION LANDS
// ENDREGION LANDS
		this.availableCards.add(new GUIClickableText("Forest", 0, 0));
		this.finalizeSetLoading();
	}

	public void LoadKhansOfTarkirBlue(){
		this.availableCards.clear();
// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Dragon's Eye Savants", 0, 0));
		this.availableCards.add(new GUIClickableText("Embodiment of Spring", 0, 0));
		this.availableCards.add(new GUIClickableText("Glacial Stalker", 0, 0));
		this.availableCards.add(new GUIClickableText("Jeskai Windscout", 0, 0));
		this.availableCards.add(new GUIClickableText("Monastery Flock", 0, 0));
		this.availableCards.add(new GUIClickableText("Mystic of the Hidden Way", 0, 0));
		this.availableCards.add(new GUIClickableText("Riverwheel Aerialists", 0, 0));
		this.availableCards.add(new GUIClickableText("Scaldkin", 0, 0));
		this.availableCards.add(new GUIClickableText("Scion of Glaciers", 0, 0));
		this.availableCards.add(new GUIClickableText("Wetland Sambar", 0, 0));
// ENDREGION CREATURES
// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Blinding Spray", 0, 0));
		this.availableCards.add(new GUIClickableText("Cancel", 0, 0));
		this.availableCards.add(new GUIClickableText("Crippling Chill", 0, 0));
		this.availableCards.add(new GUIClickableText("Disdainful Stroke", 0, 0));
		this.availableCards.add(new GUIClickableText("Treasure Cruise", 0, 0));
		this.availableCards.add(new GUIClickableText("Weave Fate", 0, 0));
// ENDREGION INSTANTS
// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Quiet Contemplation", 0, 0));
		this.availableCards.add(new GUIClickableText("Singing Bell Strike", 0, 0));
// ENDREGION ENCHANTMENTS
// REGION LANDS
// ENDREGION LANDS
		this.availableCards.add(new GUIClickableText("Island", 0, 0));
		this.finalizeSetLoading();
	}

	public void LoadKhansOfTarkirWhite(){
		this.availableCards.clear();
// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Abzan Battle Priest", 0, 0));
		this.availableCards.add(new GUIClickableText("Abzan Falconer", 0, 0));
		this.availableCards.add(new GUIClickableText("Ainok Bond-Kin", 0, 0));
		this.availableCards.add(new GUIClickableText("Alabaster Kirin", 0, 0));
		this.availableCards.add(new GUIClickableText("Dazzling Ramparts", 0, 0));
		this.availableCards.add(new GUIClickableText("Firehoof Cavalry", 0, 0));
		this.availableCards.add(new GUIClickableText("High Sentinels of Arashin", 0, 0));
		this.availableCards.add(new GUIClickableText("Jeskai Student", 0, 0));
		this.availableCards.add(new GUIClickableText("Mardu Hateblade", 0, 0));
		this.availableCards.add(new GUIClickableText("Mardu Hordechief", 0, 0));
		this.availableCards.add(new GUIClickableText("Master of Pearls", 0, 0));
		this.availableCards.add(new GUIClickableText("Sage-Eye Harrier", 0, 0));
		this.availableCards.add(new GUIClickableText("Salt Road Patrol", 0, 0));
		this.availableCards.add(new GUIClickableText("Seeker of the Way", 0, 0));
		this.availableCards.add(new GUIClickableText("Timely Hordemate", 0, 0));
		this.availableCards.add(new GUIClickableText("Venerable Lammasu", 0, 0));
		this.availableCards.add(new GUIClickableText("War Behemoth", 0, 0));
		this.availableCards.add(new GUIClickableText("Watcher of the Roost", 0, 0));
		this.availableCards.add(new GUIClickableText("Wingmate Roc", 0, 0));
// ENDREGION CREATURES
// REGION INSTANTS AND SORCERIES
		this.availableCards.add(new GUIClickableText("Defiant Strike", 0, 0));
		this.availableCards.add(new GUIClickableText("End Hostilities", 0, 0));
		this.availableCards.add(new GUIClickableText("Erase", 0, 0));
		this.availableCards.add(new GUIClickableText("Feat of Resistance", 0, 0));
		this.availableCards.add(new GUIClickableText("Kill Shot", 0, 0));
		this.availableCards.add(new GUIClickableText("Rush of Battle", 0, 0));
		this.availableCards.add(new GUIClickableText("Smite the Monstrous", 0, 0));
		this.availableCards.add(new GUIClickableText("Take Up Arms", 0, 0));
// ENDREGION INSTANTS AND SORCERIES
// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Brave the Sands", 0, 0));
		this.availableCards.add(new GUIClickableText("Siegecraft", 0, 0));
		this.availableCards.add(new GUIClickableText("Suspension Field", 0, 0));
// ENDREGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Plains", 0, 0));
		this.finalizeSetLoading();
	}

	public void LoadKhansOfTarkirBlack(){
		this.availableCards.clear();
// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Bellowing Saddlebrute", 0, 0));
		this.availableCards.add(new GUIClickableText("Disowned Ancestor", 0, 0));
		this.availableCards.add(new GUIClickableText("Gurmag Swiftwing", 0, 0));
		this.availableCards.add(new GUIClickableText("Krumar Bond-Kin", 0, 0));
		this.availableCards.add(new GUIClickableText("Mer-Ek Nightblade", 0, 0));
		this.availableCards.add(new GUIClickableText("Rotting Mastodon", 0, 0));
		this.availableCards.add(new GUIClickableText("Ruthless Ripper", 0, 0));
		this.availableCards.add(new GUIClickableText("Shambling Attendants", 0, 0));
		this.availableCards.add(new GUIClickableText("Sidisi's Pet", 0, 0));
		this.availableCards.add(new GUIClickableText("Sultai Scavenger", 0, 0));
		this.availableCards.add(new GUIClickableText("Unyielding Krumar", 0, 0));
// ENDREGION CREATURES
// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Throttle", 0, 0));
		this.availableCards.add(new GUIClickableText("Murderous Cut", 0, 0));
// ENDREGION INSTANTS
// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Debilitating Injury", 0, 0));
// ENDREGION ENCHANTMENTS
// REGION LANDS
// ENDREGION LANDS
		this.availableCards.add(new GUIClickableText("Swamp", 0, 0));
		this.finalizeSetLoading();
	}

	public void LoadKhansOfTarkirColorless(){
		this.availableCards.clear();
// REGION CREATURES
		this.availableCards.add(new GUIClickableText("Chief of the Edge", 0, 0));
		this.availableCards.add(new GUIClickableText("Chief of the Scale", 0, 0));
		this.availableCards.add(new GUIClickableText("Highspire Mantis", 0, 0));
		this.availableCards.add(new GUIClickableText("Abzan Guide", 0, 0));
		this.availableCards.add(new GUIClickableText("Ankle Shanker", 0, 0));
		this.availableCards.add(new GUIClickableText("Armament Corps", 0, 0));
		this.availableCards.add(new GUIClickableText("Bear's Companion", 0, 0));
		this.availableCards.add(new GUIClickableText("Efreet Weaponmaster", 0, 0));
		this.availableCards.add(new GUIClickableText("Ivorytusk Fortress", 0, 0));
		this.availableCards.add(new GUIClickableText("Mantis Rider", 0, 0));
		this.availableCards.add(new GUIClickableText("Ponyback Brigade", 0, 0));
		this.availableCards.add(new GUIClickableText("Sage of the Inward Eye", 0, 0));
		this.availableCards.add(new GUIClickableText("Siege Rhino", 0, 0));
		this.availableCards.add(new GUIClickableText("Snowhorn Rider", 0, 0));
		this.availableCards.add(new GUIClickableText("Witness of the Ages", 0, 0));
// ENDREGION CREATURES
// REGION INSTANTS
		this.availableCards.add(new GUIClickableText("Utter End", 0, 0));
		this.availableCards.add(new GUIClickableText("Flying Crane Technique", 0, 0));
		this.availableCards.add(new GUIClickableText("Trap Essence", 0, 0));
// ENDREGION INSTANTS
// REGION ENCHANTMENTS
		this.availableCards.add(new GUIClickableText("Jeskai Ascendancy", 0, 0));
		this.availableCards.add(new GUIClickableText("Temur Ascendancy", 0, 0));
// ENDREGION ENCHANTMENTS
// REGION ARTIFACTS
		this.availableCards.add(new GUIClickableText("Abzan Banner", 0, 0));
		this.availableCards.add(new GUIClickableText("Jeskai Banner", 0, 0));
		this.availableCards.add(new GUIClickableText("Mardu Banner", 0, 0));
		this.availableCards.add(new GUIClickableText("Sultai Banner", 0, 0));
		this.availableCards.add(new GUIClickableText("Temur Banner", 0, 0));
// ENDREGION ARTIFACTS
// REGION LANDS
		this.availableCards.add(new GUIClickableText("Bloodfell Caves", 0, 0));
		this.availableCards.add(new GUIClickableText("Bloodstained Mire", 0, 0));
		this.availableCards.add(new GUIClickableText("Blossoming Sands", 0, 0));
		this.availableCards.add(new GUIClickableText("Dismal Backwater", 0, 0));
		this.availableCards.add(new GUIClickableText("Flooded Strand", 0, 0));
		this.availableCards.add(new GUIClickableText("Frontier Bivouac", 0, 0));
		this.availableCards.add(new GUIClickableText("Jungle Hollow", 0, 0));
		this.availableCards.add(new GUIClickableText("Mystic Monastery", 0, 0));
		this.availableCards.add(new GUIClickableText("Nomad Outpost", 0, 0));
		this.availableCards.add(new GUIClickableText("Opulent Palace", 0, 0));
		this.availableCards.add(new GUIClickableText("Polluted Delta", 0, 0));
		this.availableCards.add(new GUIClickableText("Rugged Highlands", 0, 0));
		this.availableCards.add(new GUIClickableText("Sandsteppe Citadel", 0, 0));
		this.availableCards.add(new GUIClickableText("Scoured Barrens", 0, 0));
		this.availableCards.add(new GUIClickableText("Swiftwater Cliffs", 0, 0));
		this.availableCards.add(new GUIClickableText("Thornwood Falls", 0, 0));
		this.availableCards.add(new GUIClickableText("Tranquil Cove", 0, 0));
		this.availableCards.add(new GUIClickableText("Wind-Scarred Crag", 0, 0));
		this.availableCards.add(new GUIClickableText("Windswept Heath", 0, 0));
		this.availableCards.add(new GUIClickableText("Wooded Foothills", 0, 0));
		this.availableCards.add(new GUIClickableText("Tomb of the Spirit Dragon", 0, 0));
// ENDREGION LANDS
		this.finalizeSetLoading();
	}

	private void finalizeSetLoading(){
		for(int i=0;i<this.availableCards.size();i++){
			GUIClickableText ctext=this.availableCards.get(i);
			ctext.MoveTo(this.getAvailableXFromIterator(i), this.getAvailableYFromIterator(i));
			for(int d=0;d<this.deck.size();d++){
				DeckBuilderCard ccard=this.deck.get(d);
				if(ctext.Text.equals(ccard.GetName())){
					ctext.Visible=false;
					break;
				}
			}
		}
		
	}
	
	public void keyPressed(KeyEvent ke){
		this.deckLoader.keyPressed(ke);
		this.handleDeckLoading();
	}
	
	public void mousePressed(MouseEvent me){
		if(!this.deckLoader.IsOpen()){
			int mx=me.getX();
			int my=me.getY();
			for(int i=0;i<this.availableCards.size();i++){
				GUIClickableText clickedCard=this.availableCards.get(i);
				if(clickedCard.IsClicked(mx, my)){
					this.cardPreview=Deck.GetCardByName(this.availableCards.get(i).Text, this.player);
					this.cardPreview.MoveTo(350,300);
				}
			}

			for(int i=0;i<this.deck.size();i++){
				this.deck.get(i).mousePressed(me);
				int x=this.getDeckXFromIterator(i);
				int y=this.getDeckYFromIterator(i);
				if(mx>=x+40&&mx<=x+190&&my>=y&&my<=y+18){
					this.cardPreview=this.deck.get(i).Card;
					this.cardPreview.MoveTo(350,300);
				}
			}
			
			this.btnExit.handleMousePress(mx, my);
			this.btnSave.handleMousePress(mx, my);
			this.btnPreviousColor.handleMousePress(mx, my);
			this.btnNextColor.handleMousePress(mx, my);
			this.btnPreviousSet.handleMousePress(mx, my);
			this.btnNextSet.handleMousePress(mx, my);
		}
		this.deckLoader.mousePressed(me);
	}

	public void mouseReleased(MouseEvent me){
		this.cardPreview=null;
		for(int i=0;i<this.deck.size();i++){
			this.deck.get(i).mouseReleased(me);
		}
		
		this.btnExit.handleMouseRelease(me.getX(), me.getY());
		this.btnSave.handleMouseRelease(me.getX(), me.getY());
		this.btnPreviousColor.handleMouseRelease(me.getX(), me.getY());
		this.btnNextColor.handleMouseRelease(me.getX(), me.getY());
		this.btnPreviousSet.handleMouseRelease(me.getX(), me.getY());
		this.btnNextSet.handleMouseRelease(me.getX(), me.getY());
		this.deckLoader.mouseReleased(me);
	}

	public boolean mouseClicked(MouseEvent me){
		int mx=me.getX();
		int my=me.getY();
		boolean exiting=false;
		if(!this.deckLoader.IsOpen()){
			for(int i=0;i<this.availableCards.size();i++){
				GUIClickableText clickedCard=this.availableCards.get(i);
				if(clickedCard.IsClicked(mx, my)){
					this.deck.add(new DeckBuilderCard(this.player, clickedCard.Text, this.getDeckXFromIterator(this.deck.size()), this.getDeckYFromIterator(this.deck.size())));
					clickedCard.Visible=false;
				}
			}

			boolean moveDeckCards=false;
			for(int i=0;i<this.deck.size();i++){
				DeckBuilderCard card=this.deck.get(i);
				card.mouseClicked(me);
				if(card.GetCount()<=0){
					String cardname=null;
					synchronized(this.deck){
						cardname=this.deck.remove(i--).GetName();
					}
					for(int acindex=0;acindex<this.availableCards.size();acindex++){
						GUIClickableText availableCard=this.availableCards.get(acindex);
						if(availableCard.Text.equals(cardname)){
							availableCard.Visible=true;
							break;
						}
					}
					moveDeckCards=true;
				}
			}
			
			this.updateCardCounts();
			
			if(moveDeckCards){
				for(int i=0;i<this.deck.size();i++){
					this.deck.get(i).MoveTo(this.getDeckXFromIterator(i), this.getDeckYFromIterator(i));
				}
			}

			if(this.btnSave.IsClicked(me.getX(), me.getY())){
				try{
					BufferedWriter deckWriter=new BufferedWriter(new FileWriter(this.currentDeckName));
					for(int i=0;i<this.deck.size();i++){
						String ccard=""+this.deck.get(i).GetCount()+"\t"+this.deck.get(i).GetName();
						deckWriter.write(ccard, 0, ccard.length());
						deckWriter.newLine();
					}
					deckWriter.close();
					this.displayMessage="Deck saved.";
				}catch(Exception e){
					e.printStackTrace();
					this.displayMessage="Error saving deck: "+e.getMessage();
				}
			}else{
				this.displayMessage=null;
			}

			if(this.btnNextColor.IsClicked(me.getX(), me.getY())){
				this.colorIndex=(this.colorIndex+1)%this.COLORNAMES.length;
				this.loadCards();
			}else if(this.btnPreviousColor.IsClicked(me.getX(), me.getY())){
				this.colorIndex=(this.colorIndex+this.COLORNAMES.length-1)%this.COLORNAMES.length;
				this.loadCards();
			}else if(this.btnNextSet.IsClicked(me.getX(), me.getY())){
				this.setIndex=(this.setIndex+1)%this.SETNAMES.length;
				this.loadCards();
			}else if(this.btnPreviousSet.IsClicked(me.getX(), me.getY())){
				this.setIndex=(this.setIndex+this.SETNAMES.length-1)%this.SETNAMES.length;
				this.loadCards();
			}
			
			exiting=this.btnExit.IsClicked(me.getX(), me.getY());
		}
		
		this.deckLoader.mouseClicked(me.getX(), me.getY());
		this.handleDeckLoading();
		
		return exiting;
	}
	
	private void updateCardCounts(){
		this.lands=0;
		this.creatures=0;
		this.others=0;
		for(int i=0;i<this.deck.size();i++){
			DeckBuilderCard card=this.deck.get(i);
			if(card.Card.IsCreature()){
				this.creatures+=card.GetCount();
			}else if(card.Card.IsLand()){
				this.lands+=card.GetCount();
			}else{
				this.others+=card.GetCount();
			}
		}
	}

	private void handleDeckLoading(){
		String selectedFile=this.deckLoader.GetSelectedFilePath();
		if(selectedFile!=null&&!selectedFile.equals(this.currentDeckName)){
			this.currentDeckName=selectedFile;
			for(int i=0;i<this.availableCards.size();i++){
				this.availableCards.get(i).Visible=true;
			}
			synchronized(this.deck){
				this.deck.clear();
			}
			try{
				BufferedReader fin=new BufferedReader(new FileReader(selectedFile));
				String nextline=fin.readLine();
				while(nextline!=null){
					String[] splitline=nextline.split("\t");
					if(splitline.length<2)
						break;
					int count=Integer.parseInt(splitline[0]);
					String cardname=splitline[1];
					this.deck.add(new DeckBuilderCard(this.player, cardname, count, this.getDeckXFromIterator(this.deck.size()), this.getDeckYFromIterator(this.deck.size())));
					for(int i=0;i<this.availableCards.size();i++){
						GUIClickableText ctext=this.availableCards.get(i);
						if(ctext.Text.equals(cardname)){
							ctext.Visible=false;
							break;
						}
					}
					nextline=fin.readLine();
				}
				fin.close();
			}catch(FileNotFoundException fe){
				fe.printStackTrace();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		this.updateCardCounts();
	}
	
	private int getAvailableXFromIterator(int i){
		return 30+((i%4)*150);
	}
	
	private int getAvailableYFromIterator(int i){
		return 80+(i/4)*20;
	}

	private int getDeckXFromIterator(int i){
		return 30+((i%3)*200);
	}

	private int getDeckYFromIterator(int i){
		return 400+(i/3)*20;
	}

	public void paint(Graphics g){
		g.setColor(Color.black);
		
		g.drawString("Total: "+(this.creatures+this.lands+this.others), 30, 398);
		g.drawString("Creatures: "+this.creatures, 130, 398);
		g.drawString("Lands: "+this.lands, 230, 398);
		g.drawString("Others: "+this.others, 330, 398);
		
		g.drawString(this.currentSetName, 510, 64);
		
		synchronized(this.availableCards){
			for(int i=0;i<this.availableCards.size();i++){
				this.availableCards.get(i).paint(g);
			}
		}
		
		synchronized(this.deck){
			for(int i=0;i<this.deck.size();i++){
				this.deck.get(i).paint(g);
			}
		}
		
		if(this.cardPreview!=null){
			this.cardPreview.paintFullVersionAt(g, 200, 100);
		}
		
		this.deckLoader.paint(g);
		this.btnSave.paint(g);
		this.btnExit.paint(g);
		this.btnPreviousColor.paint(g);
		this.btnNextColor.paint(g);
		this.btnPreviousSet.paint(g);
		this.btnNextSet.paint(g);
		
		if(this.displayMessage!=null){
			g.drawString(this.displayMessage, 410, 64);
		}
	}
}
