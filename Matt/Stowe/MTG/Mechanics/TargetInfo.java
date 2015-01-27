package Matt.Stowe.MTG.Mechanics;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;

import java.util.ArrayList;

public class TargetInfo{
	public static final int TARGET_TYPE_FLAG_CREATURE=		1;
	public static final int TARGET_TYPE_FLAG_PLANESWALKER=	2;
	public static final int TARGET_TYPE_FLAG_ENCHANTMENT=	4;
	public static final int TARGET_TYPE_FLAG_AURA=			8;
	public static final int TARGET_TYPE_FLAG_ARTIFACT=		16;
	public static final int TARGET_TYPE_FLAG_EQUIPMENT=		32;
	public static final int TARGET_TYPE_FLAG_BASICLAND=		128;
	public static final int TARGET_TYPE_FLAG_NONBASICLAND=	256;
	public static final int TARGET_TYPE_FLAG_LAND=			384;
	public static final int TARGET_TYPE_FLAG_TOKEN=			512;
	
	public static final int TARGET_TYPE_FLAG_PERMANENT=		1023;

	public static final int TARGET_TYPE_FLAG_INSTANT=		1024;
	public static final int TARGET_TYPE_FLAG_SORCERY=		2048;

	public static final int TARGET_TYPE_FLAG_LEGENDARY=		4096;

	public static final int TARGET_TYPE_CARD=				8191;

	public static final int TARGET_TYPE_FLAG_PLAYER=		8192;

	public static final int TARGET_TYPE_ANY=				16383;

	public static final int COMBAT_STATE_FLAG_ATTACKING=	1;
	public static final int COMBAT_STATE_FLAG_BLOCKING=		2;
	
	private static final int FACE_DOWN_FLAG=		1;
	private static final int FACE_UP_FLAG=			2;
	
	private boolean needsX;
	private boolean setX;
	private int minTargets;
	public void UpdateRequiredTargets(int targets){this.minTargets=targets;}
	private int maxTargets;
	public void SetNeedsX(){this.needsX=true;}
	public boolean NeedsX(){return this.needsX;}
	public void SetX(int x){
		if(this.needsX){
			this.setX=true;
			this.minTargets=x;
			this.maxTargets=x;
			this.targets=new ITargetable[x];
		}
	}
	private boolean locked;
	public boolean IsLocked(){return this.locked;}

	private TargetInfo autoSetTargetsSource;
	private TargetInfo[] invalidTargets;
	private ITargetable[] targets;
	
	private ZoneOptions validTargetZones;
	public void SetValidTargetZones(ZoneOptions zoneopts){this.validTargetZones=zoneopts;}
	public boolean IsValidZone(int zoneflag){return this.validTargetZones.IsValidZone(zoneflag);}
	
	private int targetTypeORFlags;
	private int targetTypeANDFlags;
	private int targetTypeNOTFlags;
	public void SetTargetTypeNOTFlags(int newflags){this.targetTypeNOTFlags=newflags;}

	private SubtypeCollection targetSubtypeOptions;
	private int combatStateORFlags;
	public void SetCombatStateORFlags(int newflags){this.combatStateORFlags=newflags;}
	
	private int targetColorORFlags;
	public void SetColorORFlags(int newflags){this.targetColorORFlags=newflags;}
	
	private int targetColorNOTFlags;
	public void SetColorNOTFlags(int newflags){this.targetColorNOTFlags=newflags;}
	
	private int targetToughnessMin;
	public void SetToughnessMin(int toughness){this.targetToughnessMin=toughness;}
	
	private int targetPowerMin;
	public void SetPowerMin(int power){this.targetPowerMin=power;}
	
	private int convertedManaCostMin;
	public void SetConvertedManaCostMin(int cost){this.convertedManaCostMin=cost;}

	private int convertedManaCostMax;
	public void SetConvertedManaCostMax(int cost){this.convertedManaCostMax=cost;}
	
	private int minPTCounters;
	public void SetMinPTCounters(int counters){this.minPTCounters=counters;}
	
	private int targetColorANDFlags;
	private int targetControllerIndex;
	private String targetControllerName;
	public void TargetMustBeControlledByIndex(int controllerIndex, String controllerName){
		this.targetControllerIndex=controllerIndex;
		this.targetControllerName=controllerName;
	}
	
	private int targetNotControllerIndex;
	private String targetNotControllerName;
	public void TargetMustNotBeControlledByIndex(int controllerIndex, String controllerName){
		this.targetNotControllerIndex=controllerIndex;
		this.targetNotControllerName=controllerName;
	}

	private SubtypeCollection subtypesOR;
	public void AddORSubtype(int subtype){
		if(this.subtypesOR==null){
			this.subtypesOR=new SubtypeCollection();
		}
		this.subtypesOR.AddSubtype(subtype);
	}
	
	private SubtypeCollection subtypesNOT;
	public void AddNOTSubtype(int subtype){
		if(this.subtypesNOT==null){
			this.subtypesNOT=new SubtypeCollection();
		}
		this.subtypesNOT.AddSubtype(subtype);
	}	
	
	private ArrayList<String> namesOR;
	public void AddORName(String name){
		if(this.namesOR==null){
			this.namesOR=new ArrayList<String>();
		}
		this.namesOR.add(name);
	}
	
	private TargetInfo mustBeAttachedToInfo;
	public void MustBeAttachedTo(TargetInfo attachedToRequirement){this.mustBeAttachedToInfo=attachedToRequirement;}
	
	private int faceDownORFlags;
	public void MustBeFaceDown(){this.faceDownORFlags=FACE_DOWN_FLAG;}
	
	private class DeepCopyInfo{
		public boolean needsX;
		public boolean setX;
		public int minTargets;
		public int maxTargets;
		public boolean locked;
		public TargetInfo autoSetTargetsSource;
		public TargetInfo[] invalidTargets;
		public ITargetable[] targets;
		public ZoneOptions validTargetZones;
		public int targetTypeORFlags;
		public int targetTypeANDFlags;
		public int targetTypeNOTFlags;
		public SubtypeCollection targetSubtypeOptions;
		public int combatStateORFlags;
		public int targetColorORFlags;
		public int targetColorNOTFlags;
		public int targetToughnessMin;
		public int targetPowerMin;
		public int convertedManaCostMin;
		public int convertedManaCostMax;
		public int minPTCounters;
		public int targetColorANDFlags;
		public int targetControllerIndex;
		public String targetControllerName;
		public int targetNotControllerIndex;
		public String targetNotControllerName;
		public SubtypeCollection subtypesOR;
		public SubtypeCollection subtypesNOT;
		public ArrayList<String> namesOR;
		public TargetInfo mustBeAttachedToInfo;
		public int faceDownORFlags;

		public DeepCopyInfo(boolean needsX, boolean setX, int minTargets, int maxTargets, boolean locked, TargetInfo autoSetTargetsSource,
		TargetInfo[] invalidTargets, ITargetable[] targets, ZoneOptions validTargetZones, int targetTypeORFlags, int targetTypeANDFlags, int targetTypeNOTFlags,
		SubtypeCollection targetSubtypeOptions, int combatStateORFlags, int targetColorORFlags, int targetColorNOTFlags, int targetToughnessMin,
		int targetPowerMin, int convertedManaCostMin, int convertedManaCostMax, int minPTCounters,
		int targetColorANDFlags, int targetControllerIndex, String targetControllerName, int targetNotControllerIndex, String targetNotControllerName,
		SubtypeCollection subtypesOR, SubtypeCollection subtypesNOT, ArrayList<String> namesOR, TargetInfo mustBeAttachedToInfo, int faceDownORFlags){
			this.needsX=needsX;
			this.setX=setX;
			this.minTargets=minTargets;
			this.maxTargets=maxTargets;
			this.locked=locked;
			this.autoSetTargetsSource=autoSetTargetsSource;
			this.invalidTargets=invalidTargets;
			this.targets=targets;
			this.validTargetZones=validTargetZones;
			this.targetTypeORFlags=targetTypeORFlags;
			this.targetTypeANDFlags=targetTypeANDFlags;
			this.targetTypeNOTFlags=targetTypeNOTFlags;
			this.targetSubtypeOptions=targetSubtypeOptions;
			this.combatStateORFlags=combatStateORFlags;
			this.targetColorORFlags=targetColorORFlags;
			this.targetColorNOTFlags=targetColorNOTFlags;
			this.targetToughnessMin=targetToughnessMin;
			this.targetPowerMin=targetPowerMin;
			this.convertedManaCostMin=convertedManaCostMin;
			this.convertedManaCostMax=convertedManaCostMax;
			this.minPTCounters=minPTCounters;
			this.targetColorANDFlags=targetColorANDFlags;
			this.targetControllerIndex=targetControllerIndex;
			this.targetControllerName=targetControllerName;
			this.targetNotControllerIndex=targetNotControllerIndex;
			this.targetNotControllerName=targetNotControllerName;
			this.subtypesOR=subtypesOR;
			this.subtypesNOT=subtypesNOT;
			this.namesOR=namesOR;
			this.mustBeAttachedToInfo=mustBeAttachedToInfo;
			this.faceDownORFlags=faceDownORFlags;
		}
	}
	
	private DeepCopyInfo getDeepCopyInfo(){
		TargetInfo autosetDeepCopy=null;
		if(this.autoSetTargetsSource!=null){
			autosetDeepCopy=this.autoSetTargetsSource.DeepCopy();
		}
	
		TargetInfo[] invalidTargetsDeepCopy=null;
		if(this.invalidTargets!=null){
			invalidTargetsDeepCopy=new TargetInfo[this.invalidTargets.length];
			for(int i=0;i<invalidTargetsDeepCopy.length;i++){
				invalidTargetsDeepCopy[i]=this.invalidTargets[i].DeepCopy();
			}
		}
		
		ITargetable[] targetsShallowCopy=null;
		if(this.targets!=null){
			targetsShallowCopy=new ITargetable[this.targets.length];
			for(int i=0;i<this.targets.length;i++){
				targetsShallowCopy[i]=this.targets[i];
			}
		}
	
		ArrayList<String> namesShallowCopy=null;
		if(this.namesOR!=null){
			namesShallowCopy=new ArrayList<String>();
			for(int i=0;i<this.namesOR.size();i++){
				namesShallowCopy.add(this.namesOR.get(i));
			}
		}

		TargetInfo mustBeAttachedToInfoDeepCopy=null;
		if(this.mustBeAttachedToInfo!=null){
			mustBeAttachedToInfoDeepCopy=this.mustBeAttachedToInfo.DeepCopy();
		}
		
		return new DeepCopyInfo(this.needsX, this.setX, this.minTargets, this.maxTargets, this.locked, autosetDeepCopy, invalidTargetsDeepCopy, targetsShallowCopy,
			this.validTargetZones, this.targetTypeORFlags, this.targetTypeANDFlags, this.targetTypeNOTFlags, this.targetSubtypeOptions, this.combatStateORFlags, this.targetColorORFlags,
			this.targetColorNOTFlags, this.targetToughnessMin, this.targetPowerMin, this.convertedManaCostMin, this.convertedManaCostMax, this.minPTCounters,
			this.targetColorANDFlags, this.targetControllerIndex,
			this.targetControllerName, this.targetNotControllerIndex, this.targetNotControllerName, this.subtypesOR, this.subtypesNOT, namesShallowCopy,
			mustBeAttachedToInfoDeepCopy, this.faceDownORFlags);
	}
	
	public TargetInfo(){
		this.minTargets=0;
		this.maxTargets=0;
	}
	
	public TargetInfo(int minRequiredTargets, int maxAllowedTargets, int targetTypeORFlag, TargetInfo[] invalidTargets){
		this.init(minRequiredTargets, maxAllowedTargets, targetTypeORFlag, null, ManaCost.COLOR_ANY, invalidTargets);
	}
	
	public TargetInfo(int minRequiredTargets, int maxAllowedTargets, int targetTypeORFlag){
		this.init(minRequiredTargets, maxAllowedTargets, targetTypeORFlag, null, ManaCost.COLOR_ANY, null);
	}
	
	public TargetInfo(int minRequiredTargets, int maxAllowedTargets, int targetTypeORFlag, int targetColorORFlag, TargetInfo[] invalidTargets){
		this.init(minRequiredTargets, maxAllowedTargets, targetTypeORFlag, null, targetColorORFlag, invalidTargets);
	}
	
	public TargetInfo(int minRequiredTargets, int maxAllowedTargets, int targetTypeORFlag, int targetColorORFlag){
		this.init(minRequiredTargets, maxAllowedTargets, targetTypeORFlag, null, targetColorORFlag, null);
	}
	
	private TargetInfo(DeepCopyInfo dcinfo){
		this.needsX=dcinfo.needsX;
		this.setX=dcinfo.setX;
		this.minTargets=dcinfo.minTargets;
		this.maxTargets=dcinfo.maxTargets;
		this.locked=dcinfo.locked;
		this.autoSetTargetsSource=dcinfo.autoSetTargetsSource;
		this.invalidTargets=dcinfo.invalidTargets;
		this.targets=dcinfo.targets;
		this.validTargetZones=dcinfo.validTargetZones;
		this.targetTypeORFlags=dcinfo.targetTypeORFlags;
		this.targetTypeANDFlags=dcinfo.targetTypeANDFlags;
		this.targetTypeNOTFlags=dcinfo.targetTypeNOTFlags;
		this.targetSubtypeOptions=dcinfo.targetSubtypeOptions;
		this.combatStateORFlags=dcinfo.combatStateORFlags;
		this.targetColorORFlags=dcinfo.targetColorORFlags;
		this.targetColorNOTFlags=dcinfo.targetColorNOTFlags;
		this.targetToughnessMin=dcinfo.targetToughnessMin;
		this.targetPowerMin=dcinfo.targetPowerMin;
		this.convertedManaCostMin=dcinfo.convertedManaCostMin;
		this.convertedManaCostMax=dcinfo.convertedManaCostMax;
		this.minPTCounters=dcinfo.minPTCounters;
		this.targetColorANDFlags=dcinfo.targetColorANDFlags;
		this.targetControllerIndex=dcinfo.targetControllerIndex;
		this.targetControllerName=dcinfo.targetControllerName;
		this.targetNotControllerIndex=dcinfo.targetNotControllerIndex;
		this.targetNotControllerName=dcinfo.targetNotControllerName;
		this.subtypesOR=dcinfo.subtypesOR;
		this.subtypesNOT=dcinfo.subtypesNOT;
		this.namesOR=dcinfo.namesOR;
		this.mustBeAttachedToInfo=dcinfo.mustBeAttachedToInfo;
		this.faceDownORFlags=dcinfo.faceDownORFlags;
	}
	
	private void init(int minRequiredTargets, int maxAllowedTargets, int targetTypeORFlag, SubtypeCollection targetSubtypeOptions,
	int targetColorORFlag, TargetInfo[] invalidTargets){
		this.locked=false;
		this.minTargets=minRequiredTargets;
		this.maxTargets=maxAllowedTargets;
		this.validTargetZones=new ZoneOptions(ZoneOptions.BATTLEFIELD);
		this.targetTypeORFlags=targetTypeORFlag;
		this.targetTypeANDFlags=0;
		this.targetTypeNOTFlags=0;
		this.targetSubtypeOptions=targetSubtypeOptions;
		this.combatStateORFlags=-1;
		this.targetColorORFlags=targetColorORFlag;
		this.targetColorNOTFlags=0;
		this.targetToughnessMin=-1;
		this.targetPowerMin=-1;
		this.convertedManaCostMin=-1;
		this.convertedManaCostMax=-1;
		this.minPTCounters=0;
		this.targetColorANDFlags=0;
		this.targets=new ITargetable[this.maxTargets];
		this.invalidTargets=invalidTargets;
		this.targetControllerIndex=-1;
		this.targetNotControllerIndex=-1;
	}
	
	public TargetInfo(TargetInfo autoSetTargetsSource){
		this.autoSetTargetsSource=autoSetTargetsSource;
		this.init(0, 0, TARGET_TYPE_ANY, null, ManaCost.COLOR_ANY, null);
	}
	
	public TargetInfo DeepCopy(){
		return new TargetInfo(this.getDeepCopyInfo());
	}
	
	public void UpdateTargets(CardBase from, CardBase to){
		if(!this.locked)
			return;
			
		for(int i=0;i<this.targets.length;i++){
			if(this.targets[i]==from){
				this.targets[i]=to;
			}
		}
	}
	
	public int GetRequiredTargetCount(){
		if(this.locked||this.needsX&&!this.setX)
			return 0;

		return this.minTargets;
	}
	
	public int GetMaxTargetCount(){
		if(this.locked)
			return 0;

		return this.maxTargets;
	}
	
	public void SetTarget(int index, ITargetable target){
		this.targets[index]=target;
	}
	
	public void SetLockedTarget(int index, ITargetable target){
		this.targets[index]=target;
		this.locked=true;
	}

	public void Reset(){
		if(this.locked||this.targets==null)
			return;
		
		for(int i=0;i<this.targets.length;i++){
			this.targets[i]=null;
		}
		
		this.setX=false;
	}
	
	public ITargetable GetTarget(int index){
		if(this.autoSetTargetsSource!=null)
			return this.autoSetTargetsSource.GetTarget(index);
		
		if(this.targets==null)
			return null;
		
		if(index<0||index>=this.targets.length)
			return null;
		
		return this.targets[index];
	}
	
	private String getTargetTypeString(int typeflags){
		StringBuilder sb=new StringBuilder();
		if(typeflags==TARGET_TYPE_ANY){
			sb.append("anything");
		}else if(typeflags==TARGET_TYPE_FLAG_PERMANENT){
			sb.append("permanent");
		}else if(typeflags==TARGET_TYPE_CARD){
			sb.append("spell");
		}else{
			if((typeflags&TARGET_TYPE_FLAG_TOKEN)!=0){
				sb.append("token");
			}
			if((typeflags&TARGET_TYPE_FLAG_LEGENDARY)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("legendary");
			}
			if((typeflags&TARGET_TYPE_FLAG_ARTIFACT)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("artifact");
			}
			if((typeflags&TARGET_TYPE_FLAG_EQUIPMENT)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("equipment");
			}
			if((typeflags&TARGET_TYPE_FLAG_ENCHANTMENT)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("enchantment");
			}
			if((typeflags&TARGET_TYPE_FLAG_AURA)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("aura");
			}
			if((typeflags&TARGET_TYPE_FLAG_CREATURE)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("creature");
			}
			if((typeflags&TARGET_TYPE_FLAG_PLANESWALKER)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("planeswalker");
			}
			if((typeflags&TARGET_TYPE_FLAG_BASICLAND)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("basic land");
			}
			if((typeflags&TARGET_TYPE_FLAG_NONBASICLAND)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("nonbasic land");
			}
			if((typeflags&TARGET_TYPE_FLAG_PLAYER)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("player");
			}
			if((typeflags&TARGET_TYPE_FLAG_INSTANT)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("instant");
			}
			if((typeflags&TARGET_TYPE_FLAG_SORCERY)!=0){
				if(sb.length()>0)
					sb.append(" or ");
				sb.append("sorcery");
			}
		}
		
		return sb.toString();
	}
	
	public String GetTargetTypeString(){
		StringBuilder sb=new StringBuilder();
		int previousDescriptionLength=0;
		//Combat state
		if(this.combatStateORFlags>-1){
			if((this.combatStateORFlags&COMBAT_STATE_FLAG_ATTACKING)!=0){
				sb.append("attacking ");
			}
			if((this.combatStateORFlags&COMBAT_STATE_FLAG_BLOCKING)!=0){
				if(sb.length()-previousDescriptionLength>0)
					sb.append("or ");
				sb.append("blocking ");
			}
		}
		
		//color
		if(this.targetColorORFlags!=ManaCost.COLOR_ANY){
			sb.append(ManaCost.GetColorNames(this.targetColorORFlags, " or ")+" ");
		}
		if(this.targetColorNOTFlags!=0){
			sb.append("non"+ManaCost.GetColorNames(this.targetColorNOTFlags, " and non")+" ");
		}

		if(this.faceDownORFlags==FACE_DOWN_FLAG){
			sb.append("face down ");
		}else if(this.faceDownORFlags==FACE_UP_FLAG){
			sb.append("face up ");
		}
		
		if(this.subtypesOR!=null){
			sb.append(this.subtypesOR.ToSpaceSeparatedString()+" ");
		}
		
		if(this.subtypesNOT!=null){
			sb.append("(not "+this.subtypesNOT.ToSpaceSeparatedString()+") ");
		}
		
		if(this.namesOR!=null){
			previousDescriptionLength=sb.length();
			for(int i=0;i<this.namesOR.size();i++){
				sb.append(this.namesOR.get(i)+" or ");
			}
			if(sb.length()>previousDescriptionLength){
				sb.setLength(sb.length()-3);
			}
		}
		
		if(this.mustBeAttachedToInfo!=null){
			sb.append(" attached to"+this.mustBeAttachedToInfo.GetCSV().replace("1 target", "")+" ");
		}
		
		previousDescriptionLength=sb.length();
		//Begin type
		sb.append(this.getTargetTypeString(this.targetTypeORFlags));
		
		if(this.targetTypeNOTFlags>0){
			sb.append("(not "+this.getTargetTypeString(this.targetTypeNOTFlags)+")");
		}
		
		if(this.targetToughnessMin>-1){
			sb.append(" with toughness "+this.targetToughnessMin+" or greater");
		}
		
		if(this.targetPowerMin>-1){
			sb.append(" with power "+this.targetPowerMin+" or greater");
		}
		
		if(this.convertedManaCostMin>-1){
			sb.append(" with converted mana cost "+this.convertedManaCostMin+" or greater");
		}
		
		if(this.convertedManaCostMax>-1){
			sb.append(" with converted mana cost "+this.convertedManaCostMax+" or less");
		}
		
		if(this.minPTCounters>0){
			sb.append(" with at least "+this.minPTCounters+" +1/+1 counter on it");
		}
		
		if(!this.validTargetZones.IsOnlyBattlefield()){
			sb.append(" from the "+this.validTargetZones.GetCSV());
		}
		
		return sb.toString();
	}
	
	public boolean IsValidTarget(ITargetable target){
String debugname="Defiant Strike DISABLE";
if(target.GetName().equals(debugname))System.out.println("BEGIN TARGET VALIDATION");
		if(this.invalidTargets!=null){
			for(int invalidTargetInfoIndex=0;invalidTargetInfoIndex<this.invalidTargets.length;invalidTargetInfoIndex++){
				for(int invalidTargetIndex=0;this.invalidTargets[invalidTargetInfoIndex].GetTarget(invalidTargetIndex)!=null;invalidTargetIndex++){
					if(target.GetName().equals(this.invalidTargets[invalidTargetInfoIndex].GetTarget(invalidTargetIndex).GetName())
					&&target.GetTargetEndpointX()==(this.invalidTargets[invalidTargetInfoIndex].GetTarget(invalidTargetIndex).GetTargetEndpointX())
					&&target.GetTargetEndpointY()==(this.invalidTargets[invalidTargetInfoIndex].GetTarget(invalidTargetIndex).GetTargetEndpointY())){
						return false;
					}
				}
			}
		}

		if(this.targetControllerIndex>-1){
			if(!(target instanceof CardBase))
				return false;
			if(((CardBase)target).GetControllerIndex()!=this.targetControllerIndex){
//System.out.println(target.GetName()+" is controlled by "+((CardBase)target).GetControllerIndex()+" instead of "+this.targetControllerIndex);
				return false;
			}
		}
if(target.GetName().equals(debugname))System.out.println("TARGET CONTROLLER COMPATIBLE WITH "+this.targetControllerIndex);
		if(this.targetNotControllerIndex>-1){
			if(!(target instanceof CardBase))
				return false;
			if(((CardBase)target).GetControllerIndex()==this.targetNotControllerIndex)
				return false;
		}
if(target.GetName().equals(debugname))System.out.println("TARGET NOT CONTROLLER COMPATIBLE WITH "+this.targetNotControllerIndex);

		if(this.combatStateORFlags>-1){
			if(!(target instanceof Creature))
				return false;
				
			int combatflags=((Creature)target).GetCombatStateFlags();
			if((this.combatStateORFlags&combatflags)==0){
				return false;
			}
		}

if(target.GetName().equals(debugname))System.out.println("TARGET COMBATSTATE COMPATIBLE WITH "+this.combatStateORFlags);
		if(this.targetToughnessMin>-1){
			if(!(target instanceof Creature))
				return false;
			
			if(((Creature)target).GetToughness()<this.targetToughnessMin)
				return false;
		}
if(target.GetName().equals(debugname))System.out.println("CHECK ");

		if(this.targetPowerMin>-1){
			if(!(target instanceof Creature))
				return false;
				
			if(((Creature)target).GetPower()<this.targetPowerMin)
				return false;
		}
if(target.GetName().equals(debugname))System.out.println("CHECK ");

		if(this.convertedManaCostMin>-1){
			if(!(target instanceof CardBase))
				return false;
				
			if(((CardBase)target).GetManaCost()!=null&&((CardBase)target).GetManaCost().GetConvertedCost()<this.convertedManaCostMin)
				return false;
		}
if(target.GetName().equals(debugname))System.out.println("CHECK ");
		
		if(this.convertedManaCostMax>-1){
			if(!(target instanceof CardBase))
				return false;
				
			if(((CardBase)target).GetManaCost()!=null&&((CardBase)target).GetManaCost().GetConvertedCost()>this.convertedManaCostMax)
				return false;
		}
if(target.GetName().equals(debugname))System.out.println("CHECK ");

		if(this.minPTCounters>0){
			if(!(target instanceof Creature))
				return false;
			
			if(((Creature)target).GetPowerToughnessCounters()<this.minPTCounters)
				return false;
		}
if(target.GetName().equals(debugname))System.out.println("CHECK ");
		
		if(this.subtypesOR!=null){
if(target.GetName().equals(debugname))System.out.println("Validating "+this.subtypesOR.ToSpaceSeparatedString());
			if(!(target instanceof CardBase)){
if(target.GetName().equals(debugname))System.out.println(target.GetName()+" is not an instanceof CardBase");
				return false;
			}
			if(!SubtypeCollection.SharesAnySubtype(this.subtypesOR, ((CardBase)target).GetSubtypes())){
if(target.GetName().equals(debugname))System.out.println(this.subtypesOR.ToSpaceSeparatedString()+" shares nothing with "+target.GetName()+": "+((CardBase)target).GetSubtypes().ToSpaceSeparatedString());
				return false;
			}
		}
if(target.GetName().equals(debugname)&&this.subtypesOR!=null)System.out.println("TARGET SUBTYPES COMPATIBLE WITH "+this.subtypesOR.ToSpaceSeparatedString());

		if(this.subtypesNOT!=null){
			if(!(target instanceof CardBase))
				return false;
			if(SubtypeCollection.SharesAnySubtype(this.subtypesNOT, ((CardBase)target).GetSubtypes()))
				return false;
		}
if(target.GetName().equals(debugname))System.out.println("CHECK SUBNOT");

		if(this.namesOR!=null){
			boolean namematch=false;
			for(int i=0;!namematch&&i<this.namesOR.size();i++){
				if(this.namesOR.get(i).equals(target.GetName())){
					namematch=true;
				}
			}
			if(!namematch)
				return false;
		}
if(target.GetName().equals(debugname))System.out.println("CHECK NAMESOR");

		if(this.mustBeAttachedToInfo!=null){
			boolean validated=false;
			if(target instanceof Enchantment){
				if(!this.mustBeAttachedToInfo.IsValidTarget(((Enchantment)target).GetTargetPermanent())){
					return false;
				}else{
					validated=true;
				}
			}
				
			if(target instanceof Artifact){
				if(!this.mustBeAttachedToInfo.IsValidTarget(((Artifact)target).GetEquippedCreature())){
					return false;
				}else{
					validated=true;
				}
			}
			
			if(!validated)
				return false;
		}
if(target.GetName().equals(debugname))System.out.println("CHECK MUSTBEATTACHEDTO");
		
		if(target instanceof CardBase){
			CardBase cardtarget=((CardBase)target);
			if(!this.validTargetZones.IsValidZone(cardtarget.GetZone())){
if(target.GetName().equals(debugname))System.out.println("Invalid zone: "+((CardBase)target).GetZone());
				return false;
			}
			
			if(this.faceDownORFlags==FACE_DOWN_FLAG&&!cardtarget.IsFaceDown()
			||this.faceDownORFlags==FACE_UP_FLAG&&cardtarget.IsFaceDown()){
				return false;
			}
		}
//if(target.GetName().equals(debugname))System.out.println("FINAL TARGET VALIDATION...");

		int targetTypeFlags=target.GetTargetTypeFlags();
		int targetColorFlags=target.GetColorFlags();
if(target.GetName().equals(debugname))System.out.println(targetTypeFlags+" type "+this.targetTypeORFlags+" NOT "+this.targetTypeNOTFlags+", "+targetColorFlags+" color "+this.targetColorORFlags);
		return (this.targetTypeORFlags&targetTypeFlags)!=0
			&&(this.targetTypeANDFlags&targetTypeFlags)==this.targetTypeANDFlags
			&&(this.targetColorORFlags==ManaCost.COLOR_ANY||(this.targetColorORFlags&targetColorFlags)!=0)
			&&(this.targetColorNOTFlags&targetColorFlags)==0
			&&(this.targetColorANDFlags&targetColorFlags)==this.targetColorANDFlags
			&&(this.targetTypeNOTFlags&targetTypeFlags)==0;
	}
	
	public String GetCSV(){
		String alltargets="";
		boolean targetsNamed=false;
		for(int i=0;this.GetTarget(i)!=null;i++){
			alltargets=alltargets+(i==0?"":", ")+this.GetTarget(i).GetName();
			targetsNamed=true;
		}
		if(!targetsNamed&&this.autoSetTargetsSource!=null){
			alltargets="same target";
			targetsNamed=true;
		}
		if(!targetsNamed){
			if(this.needsX&&!this.setX){
				alltargets="X target "+this.GetTargetTypeString();
			}else if(this.GetRequiredTargetCount()==this.GetMaxTargetCount()){
				alltargets=""+this.GetRequiredTargetCount()+" target "+this.GetTargetTypeString();
			}else{
				alltargets=""+this.GetRequiredTargetCount()+" to "+this.GetMaxTargetCount()+" target "+this.GetTargetTypeString();
			}
			if(this.invalidTargets!=null&&this.invalidTargets.length>0){
				StringBuilder invalidTargetStrings=new StringBuilder(" except ");
				for(int invalidTargetInfoIndex=0;invalidTargetInfoIndex<this.invalidTargets.length;invalidTargetInfoIndex++){
					for(int invalidTargetIndex=0;this.invalidTargets[invalidTargetInfoIndex].GetTarget(invalidTargetIndex)!=null;invalidTargetIndex++){
						if(invalidTargetStrings.length()>8){
							invalidTargetStrings.append(", ");
						}
						invalidTargetStrings.append(this.invalidTargets[invalidTargetInfoIndex].GetTarget(invalidTargetIndex).GetName());
					}
				}
				alltargets=alltargets+invalidTargetStrings.toString();
			}
			
			if(this.targetControllerIndex>-1){
				alltargets=alltargets+" controlled by "+this.targetControllerName;
			}
			
			if(this.targetNotControllerIndex>-1){
				alltargets=alltargets+" not controlled by "+this.targetNotControllerName;
			}
		}
		
		return alltargets;
	}	
}
