package Matt.Stowe.MTG.Mechanics;

public class SubtypeCollection{
	public static final int GIANT=		0;
	public static final int AJANI=		1;
	public static final int ANGEL=		2;
	public static final int CAT=		3;
	public static final int HUMAN=		4;
	public static final int GRIFFIN=	5;
	public static final int HIPPOGRIFF=	6;
	public static final int PEGASUS=	7;
	public static final int KITHKIN=	8;
	public static final int SALAMANDER=	9;
	public static final int SOLDIER=	10;
	public static final int MONK=		11;
	public static final int WARRIOR=	12;
	public static final int CLERIC=		13;
	public static final int WIZARD=		14;
	public static final int ARTIFICER=	15;
	public static final int SPIRIT=		16;
	public static final int SLIVER=		17;
	public static final int AVATAR=		18;
	public static final int DJINN=		19;
	public static final int LEECH=		20;
	public static final int WALL=		21;
	public static final int FISH=		22;
	public static final int VAMPIRE=	23;
	public static final int ZOMBIE=		24;
	public static final int BIRD=		25;
	public static final int HORROR=		26;
	public static final int NIGHTMARE=	27;
	public static final int HORSE=		28;
	public static final int SKELETON=	29;
	public static final int RAT=		30;
	public static final int FROG=		31;
	public static final int SHADE=		32;
	public static final int DRAGON=		33;
	public static final int DEVIL=		34;
	public static final int GOBLIN=		35;
	public static final int KNIGHT=		36;
	public static final int APE=		37;
	public static final int ELEMENTAL=	38;
	public static final int HOUND=		39;
	public static final int BEAR=		40;
	public static final int ELF=		41;
	public static final int SHAMAN=		42;
	public static final int WURM=		43;
	public static final int PLANT=		44;
	public static final int DRUID=		45;
	public static final int CENTAUR=	46;
	public static final int BEAST=		47;
	public static final int INSECT=		48;
	public static final int TREEFOLK=	49;
	public static final int ADVISOR=	50;
	public static final int OX=			51;
	public static final int SPHINX=		52;
	public static final int MERFOLK=	53;
	public static final int ROGUE=		54;
	public static final int BERSERKER=	55;
	public static final int CYCLOPS=	56;
	public static final int SATYR=		57;
	public static final int SNAKE=		58;
	public static final int FOX=		59;
	public static final int SABLE=		60;
	public static final int GOLEM=		61;
	public static final int HARPY=		62;
	public static final int MINOTAUR=	63;
	public static final int KIRIN=		64;
	public static final int SCOUT=		65;
	public static final int LAMMASU=	66;
	public static final int ELK=		67;
	public static final int ORC=		68;
	public static final int BAT=		69;
	public static final int ASSASSIN=	70;
	public static final int ELEPHANT=	71;
	public static final int EFREET=		72;
	public static final int ARCHER=		73;
	public static final int YETI=		74;
	public static final int MOUNTAIN=	75;
	public static final int FOREST=		76;
	public static final int ISLAND=		77;
	public static final int PLAINS=		78;
	public static final int SWAMP=		79;
	public static final int RHINO=		80;
	public static final int NAGA=		81;

	private static final int TOTAL=		82;
	
	private int[] subtypes;
	
	private SubtypeCollection(int[] subtypes){
		this.subtypes=subtypes;
	}
	
	public SubtypeCollection(){
		this.subtypes=new int[TOTAL];
		for(int i=0;i<this.subtypes.length;i++)
			this.subtypes[i]=0;
	}
	
	public SubtypeCollection DeepCopy(){
		int[] subtypesDeepCopy=new int[this.subtypes.length];
		for(int i=0;i<this.subtypes.length;i++){
			subtypesDeepCopy[i]=this.subtypes[i];
		}
		return new SubtypeCollection(subtypesDeepCopy);
	}
	
	public static boolean SharesAnySubtype(SubtypeCollection a, SubtypeCollection b){
		boolean matchesAnyType=false;
		for(int sti=0;!matchesAnyType&&sti<TOTAL;sti++){
			matchesAnyType=a.HasSubtype(sti)&&b.HasSubtype(sti);
		}
		return matchesAnyType;
	}
	
	public void AddAllSubtypes(SubtypeCollection other){
		for(int i=0;i<this.subtypes.length;i++){
			this.subtypes[i]+=other.GetSubtypeCount(i);
		}
	}
	
	public void AddSubtype(int subtype){
		this.subtypes[subtype]++;
	}
	
	public void RemoveSubtype(int subtype){
		this.subtypes[subtype]--;
	}
	
	public boolean HasSubtype(int subtype){
		return this.subtypes[subtype]>0;
	}
	
	public int GetSubtypeCount(int subtype){
		return this.subtypes[subtype];
	}
	
	public String ToSpaceSeparatedString(){
		StringBuilder alltypes=new StringBuilder();
		for(int i=0;i<this.subtypes.length;i++){
			if(this.subtypes[i]>0)
				alltypes.append(" "+GetSubtypeString(i));
		}
		if(alltypes.length()==0)
			return "";
		return alltypes.toString();
	}
	
	public static String GetSubtypeString(int subtype){
		switch(subtype){
		case GIANT:
			return "Giant";
		case AJANI:
			return "Ajani";
		case ANGEL:
			return "Angel";
		case CAT:
			return "Cat";
		case HUMAN:
			return "Human";
		case GRIFFIN:
			return "Griffin";
		case HIPPOGRIFF:
			return "Hippogriff";
		case PEGASUS:
			return "Pegasus";
		case KITHKIN:
			return "Kithkin";
		case SALAMANDER:
			return "Salamander";
		case SOLDIER:
			return "Soldier";
		case MONK:
			return "Monk";
		case WARRIOR:
			return "Warrior";
		case CLERIC:
			return "Cleric";
		case WIZARD:
			return "Wizard";
		case ARTIFICER:
			return "Artificer";
		case SPIRIT:
			return "Spirit";
		case SLIVER:
			return "Sliver";
		case AVATAR:
			return "Avatar";
		case DJINN:
			return "Djinn";
		case LEECH:
			return "Leech";
		case WALL:
			return "Wall";
		case FISH:
			return "Fish";
		case VAMPIRE:
			return "Vampire";
		case ZOMBIE:
			return "Zombie";
		case BIRD:
			return "Bird";
		case HORROR:
			return "Horror";
		case NIGHTMARE:
			return "Nightmare";
		case HORSE:
			return "Horse";
		case SKELETON:
			return "Skeleton";
		case RAT:
			return "Rat";
		case FROG:
			return "Frog";
		case SHADE:
			return "Shade";
		case DRAGON:
			return "Dragon";
		case DEVIL:
			return "Devil";
		case GOBLIN:
			return "Goblin";
		case KNIGHT:
			return "Knight";
		case APE:
			return "Ape";
		case ELEMENTAL:
			return "Elemental";
		case HOUND:
			return "Hound";
		case BEAR:
			return "Bear";
		case ELF:
			return "Elf";
		case SHAMAN:
			return "Shaman";
		case WURM:
			return "Wurm";
		case PLANT:
			return "Plant";
		case DRUID:
			return "Druid";
		case CENTAUR:
			return "Centaur";
		case BEAST:
			return "Beast";
		case INSECT:
			return "Insect";
		case TREEFOLK:
			return "Treefolk";
		case ADVISOR:
			return "Advisor";
		case OX:
			return "Ox";
		case SPHINX:
			return "Sphinx";
		case MERFOLK:
			return "Merfolk";
		case ROGUE:
			return "Rogue";
		case BERSERKER:
			return "Berserker";
		case CYCLOPS:
			return "Cyclops";
		case SATYR:
			return "Satyr";
		case SNAKE:
			return "Snake";
		case FOX:
			return "Fox";
		case SABLE:
			return "Sable";
		case GOLEM:
			return "Golem";
		case HARPY:
			return "Harpy";
		case MINOTAUR:
			return "Minotaur";
		case KIRIN:
			return "Kirin";
		case SCOUT:
			return "Scout";
		case LAMMASU:
			return "Lammasu";
		case ELK:
			return "Elk";
		case ORC:
			return "Orc";
		case BAT:
			return "Bat";
		case ASSASSIN:
			return "Assassin";
		case ELEPHANT:
			return "Elephant";
		case EFREET:
			return "Efreet";
		case ARCHER:
			return "Archer";
		case YETI:
			return "Yeti";
		case MOUNTAIN:
			return "Mountain";
		case FOREST:
			return "Forest";
		case ISLAND:
			return "Island";
		case PLAINS:
			return "Plains";
		case SWAMP:
			return "Swamp";
		case RHINO:
			return "Rhino";
		case NAGA:
			return "Naga";
		default:
			return "UNKNOWN SUBTYPE: "+subtype;
		}
	}
}
