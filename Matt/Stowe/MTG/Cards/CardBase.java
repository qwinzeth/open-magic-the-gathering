package Matt.Stowe.MTG.Cards;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.ContinuousAbilities.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;

import java.awt.*;
import java.util.*;

public abstract class CardBase extends AnimatedGUIObject implements ITargetable{
	public static final int WIDTH=60;
	public static final int HEIGHT=100;
	public static final int FULLWIDTH=240;
	public static final int FULLHEIGHT=400;
	public static final Color FACE_DOWN_COLOR=new Color(128, 64, 32);
	
	private boolean morphed;
	public boolean IsMorphed(){return this.morphed;}
	public void SetMorphed(){
		this.staticKeywords=this.keywords;
		this.keywords=new KeywordCollection();
		this.staticSubtypes=this.subtypes;
		this.subtypes=new SubtypeCollection();
		this.staticContinuousAbilities=this.continuousAbilities;
		this.continuousAbilities=new ArrayList<ContinuousAbility>();
		this.staticActivatedAbilities=this.activatedAbilities;
		this.activatedAbilities=new Vector<MagicActivatedAbility>();
		for(int i=0;i<this.staticActivatedAbilities.size();i++){
			MagicActivatedAbility maa=this.staticActivatedAbilities.get(i);
			if(maa.Effects.length==1&&maa.Effects[0] instanceof MagicEffectMorphTargetCreature){
				this.activatedAbilities.add(maa);
			}
		}
		this.staticEntersTheBattlefieldAdjustments=this.entersTheBattlefieldAdjustments;
		this.entersTheBattlefieldAdjustments=new ArrayList<MagicEffect>();
		this.staticEntersTheBattlefieldTriggers=this.entersTheBattlefieldTriggers;
		this.entersTheBattlefieldTriggers=new ArrayList<TriggeredAbility>();
		this.staticMorphTriggers=this.morphTriggers;
		this.morphTriggers=new ArrayList<TriggeredAbility>();
		this.staticAttacksTriggers=this.attacksTriggers;
		this.attacksTriggers=new ArrayList<TriggeredAbility>();
		this.staticLifegainTriggers=this.lifegainTriggers;
		this.lifegainTriggers=new ArrayList<TriggeredAbility>();
		this.staticUpkeepTriggers=this.upkeepTriggers;
		this.upkeepTriggers=new ArrayList<TriggeredAbility>();
		this.staticSpellCastTriggers=this.spellCastTriggers;
		this.spellCastTriggers=new ArrayList<TriggeredAbility>();
		this.morphed=true;
		this.faceDown=true;
	}
	public void Morph(){
		this.keywords.AddAllKeywords(this.staticKeywords);
		this.staticKeywords=null;
		this.subtypes.AddAllSubtypes(this.staticSubtypes);
		this.staticSubtypes=null;
		this.continuousAbilities.addAll(this.staticContinuousAbilities);
		this.staticContinuousAbilities=null;
		for(int i=0;i<this.staticActivatedAbilities.size();i++){
			MagicActivatedAbility maa=this.staticActivatedAbilities.get(i);
			if(!(maa.Effects.length==1&&maa.Effects[0] instanceof MagicEffectMorphTargetCreature)){
				this.activatedAbilities.add(maa);
			}
		}
		this.staticActivatedAbilities=null;
		this.entersTheBattlefieldAdjustments.addAll(this.staticEntersTheBattlefieldAdjustments);
		this.staticEntersTheBattlefieldAdjustments=null;
		this.entersTheBattlefieldTriggers.addAll(this.staticEntersTheBattlefieldTriggers);
		this.staticEntersTheBattlefieldTriggers=null;
		this.morphTriggers.addAll(this.staticMorphTriggers);
		this.staticMorphTriggers=null;
		this.attacksTriggers.addAll(this.staticAttacksTriggers);
		this.staticAttacksTriggers=null;
		this.lifegainTriggers.addAll(this.staticLifegainTriggers);
		this.staticLifegainTriggers=null;
		this.upkeepTriggers.addAll(this.staticUpkeepTriggers);
		this.staticUpkeepTriggers=null;
		this.spellCastTriggers.addAll(this.staticSpellCastTriggers);
		this.staticSpellCastTriggers=null;
		this.morphed=false;
		this.faceDown=false;
	}

	private ArrayList<TargetInfo> cantBeTargetedBy;
	public void AddCantBeTargetedBy(TargetInfo info){this.cantBeTargetedBy.add(info);}
	public void RemoveCantBeTargetedBy(TargetInfo info){this.cantBeTargetedBy.remove(info);}
	public boolean CanBeTargetedBy(ITargetable source){
		for(int i=0;i<this.cantBeTargetedBy.size();i++){
			if(this.cantBeTargetedBy.get(i).IsValidTarget(source)){
				return false;
			}
		}
		
		return true;
	}

	private ArrayList<ManaCost> activatedAbilityCost;
	public ArrayList<ManaCost> GetActivatedAbilityManaCost(){return this.activatedAbilityCost;}
	public void AddActivatedAbilityManaCost(ManaCost newcost){
		this.activatedAbilityCost.add(newcost);
	}
	public void RemoveActivatedAbilityManaCost(ManaCost oldcost){
		this.activatedAbilityCost.remove(oldcost);
	}

	private ArrayList<MagicEffect> effectsToUndoAtEndOfTurn;
	public void UndoAtEndOfTurn(MagicEffect effect){
		this.effectsToUndoAtEndOfTurn.add(effect);
	}

	private ArrayList<MagicEffect> effectsToUndoWhenControlIsLost;
	public void UndoWhenControlIsLost(MagicEffect effect){
		this.effectsToUndoWhenControlIsLost.add(effect);
	}

	private ArrayList<MagicEffect> effectsToUndoWhenLeavesBattlefield;
	public void UndoWhenLeavesBattlefield(MagicEffect effect){
		this.effectsToUndoWhenLeavesBattlefield.add(effect);
	}
	
	protected String name;
	public String GetName(){
		if(this.morphed){
			return "Morph Creature";
		}
		return this.name;
	}
	protected String description;
	public String GetDescription(){return this.description;}
	private ManaCost cost;
	protected Player owner;
	public int GetOwnerIndex(){return this.owner.GetIndex();}	
	protected Player controller;
	public int GetControllerIndex(){return this.controller.GetIndex();}
	//TODO: update controller
	
	private int explicitZoneForDeepCopies;
	public int GetZone(){
		if(this.explicitZoneForDeepCopies!=-1)
			return this.explicitZoneForDeepCopies;
		int zone=this.owner.GetCardZone(this);
		if(zone!=ZoneOptions.NONE)
			return zone;
		zone=this.controller.GetCardZone(this);
		if(zone!=ZoneOptions.NONE)
			return zone;
		//If it doesn't exist in owner or controller, it must be on the stack
		return ZoneOptions.STACK;
	}
	
	private int tokenColor;
	public void SetToken(int colorflags){
		this.typeFlags|=TargetInfo.TARGET_TYPE_FLAG_TOKEN;
		this.tokenColor=colorflags;
		this.explicitZoneForDeepCopies=ZoneOptions.BATTLEFIELD;
	}
	
	private int turnsToSkipUntapping;
	public void SkipNextUntapping(){this.turnsToSkipUntapping=1;}
	public void HandleSkippedUntapping(){
		if(this.turnsToSkipUntapping>0)
			this.turnsToSkipUntapping--;
	}

	private int untapPreventors;
	public void AddUntapPrevention(){this.untapPreventors++;}
	public void RemoveUntapPrevention(){this.untapPreventors--;}

	public boolean UntapsDuringUpkeep(){
		return this.turnsToSkipUntapping==0&&this.untapPreventors<=0;
	}
	
	private KeywordCollection staticKeywords;
	private KeywordCollection keywords;
	public KeywordCollection GetKeywords(){return this.keywords;}
	
	private SubtypeCollection staticSubtypes;
	private SubtypeCollection subtypes;
	public SubtypeCollection GetSubtypes(){return this.subtypes;}
	
	protected ArrayList<MagicEffect> nonmanaPlayCosts;
	public MagicEffect[] GetNonmanaPlayCosts(){
		if(this.nonmanaPlayCosts==null)
			return null;
		return this.nonmanaPlayCosts.toArray(new MagicEffect[this.nonmanaPlayCosts.size()]);
	}

	private ArrayList<ContinuousAbility> staticContinuousAbilities;
	private ArrayList<ContinuousAbility> continuousAbilities;
	public void AddContinousEffect(ContinuousAbility ce){
		ce.SetSource(this);
		this.continuousAbilities.add(ce);
	}
	public void RemoveContinousEffect(ContinuousAbility ce){this.continuousAbilities.remove(ce);}

	private Vector<MagicActivatedAbility> staticActivatedAbilities;
	private Vector<MagicActivatedAbility> activatedAbilities;
	public MagicActivatedAbility[] GetActivatedAbilities(){
		return this.activatedAbilities.toArray(new MagicActivatedAbility[this.activatedAbilities.size()]);
	}
	public void AddActivatedAbility(MagicActivatedAbility maa){
		if(this.IsPlaneswalker())
			maa.SetMaxTimesToUsePerTurn(1);
		maa.SetSource(this);
		this.activatedAbilities.add(maa);
	}
	public void RemoveActivatedAbility(MagicActivatedAbility maa){
		this.activatedAbilities.remove(maa);
	}
	
	private ArrayList<TriggeredAbility> staticEntersTheBattlefieldTriggers;
	private ArrayList<TriggeredAbility> entersTheBattlefieldTriggers;
	public void AddEntersTheBattlefieldTrigger(TriggeredAbility ability){
		ability.SetSource(this);
		this.entersTheBattlefieldTriggers.add(ability);
	}
	public void RemoveEntersTheBattlefieldTrigger(TriggeredAbility ability){
		this.entersTheBattlefieldTriggers.remove(ability);
	}
	
	private ArrayList<TriggeredAbility> staticMorphTriggers;
	private ArrayList<TriggeredAbility> morphTriggers;
	public void AddMorphTrigger(TriggeredAbility ability){
		ability.SetSource(this);
		this.morphTriggers.add(ability);
	}
	public void RemoveMorphTrigger(TriggeredAbility ability){
		this.morphTriggers.remove(ability);
	}
	
	private ArrayList<MagicEffect> staticEntersTheBattlefieldAdjustments;
	private ArrayList<MagicEffect> entersTheBattlefieldAdjustments;
	public void AddEntersTheBattlefieldAdjustments(MagicEffect ability){
		ability.SetSource(this);
		this.entersTheBattlefieldAdjustments.add(ability);
	}
	public void RemoveEntersTheBattlefieldAdjustments(MagicEffect ability){
		this.entersTheBattlefieldAdjustments.remove(ability);
	}

	private ArrayList<TriggeredAbility> staticAttacksTriggers;
	private ArrayList<TriggeredAbility> attacksTriggers;
	public void AddAttacksTrigger(TriggeredAbility ability){
		ability.SetSource(this);
		this.attacksTriggers.add(ability);
	}
	public void RemoveAttacksTrigger(TriggeredAbility ability){
		this.attacksTriggers.remove(ability);
	}

	private ArrayList<TriggeredAbility> staticLifegainTriggers;
	private ArrayList<TriggeredAbility> lifegainTriggers;
	public void AddLifegainTrigger(TriggeredAbility ability){
		ability.SetSource(this);
		this.lifegainTriggers.add(ability);
	}
	public void RemoveLifegainTrigger(TriggeredAbility ability){
		this.lifegainTriggers.remove(ability);
	}
	
	private ArrayList<TriggeredAbility> staticUpkeepTriggers;
	private ArrayList<TriggeredAbility> upkeepTriggers;
	public void AddUpkeepTrigger(TriggeredAbility ability){
		ability.SetSource(this);
		this.upkeepTriggers.add(ability);
	}
	public void RemoveUpkeepTrigger(TriggeredAbility ability){
		this.upkeepTriggers.remove(ability);
	}
	
	private ArrayList<TriggeredAbility> staticSpellCastTriggers;
	private ArrayList<TriggeredAbility> spellCastTriggers;
	public void AddSpellCastTrigger(TriggeredAbility ability){
		ability.SetSource(this);
		this.spellCastTriggers.add(ability);
	}
	public void RemoveSpellCastTrigger(TriggeredAbility ability){
		this.spellCastTriggers.remove(ability);
	}
	
	protected MagicEffect[] playCosts;
	public MagicEffect[] GetPlayCosts(){return this.playCosts;}

	protected MagicEffect[] playEffects;
	public MagicEffect[] GetPlayEffects(){return this.playEffects;}

	public ArrayList<Enchantment> Auras;
	
	protected boolean faceDown;
	public void SetFaceDown(boolean faceDown){this.faceDown=faceDown;}
	public boolean IsFaceDown(){return this.faceDown;}
	
	private boolean tapped;
	public boolean IsTapped(){return this.tapped;}
	public void Tap(){this.tapped=true;}
	public void Untap(){this.tapped=false;}
	
	private int typeFlags;
	public int GetTargetTypeFlags(){return this.typeFlags;}

	public void SetLegendary(){this.typeFlags|=TargetInfo.TARGET_TYPE_FLAG_LEGENDARY;}
	
	public String GetTypesAsString(){
		StringBuilder types=new StringBuilder();
		if(this.IsLegendary())
			types.append("Legendary ");
		if(this.IsToken())
			types.append("Token ");
		if(this.IsArtifact())
			types.append("Artifact ");
		if(this.IsEquipment())
			types.append("Equipment ");
		if(this.IsEnchantment())
			types.append("Enchantment ");
		if(this.IsCreature())
			types.append("Creature ");
		if(this.IsPlaneswalker())
			types.append("Planeswalker ");
		if(this.IsInstant())
			types.append("Instant ");
		if(this.IsSorcery())
			types.append("Sorcery ");
		if(this.IsAura())
			types.append("Aura ");
		if(this.IsBasicLand())
			types.append("Basic Land ");
		else if(this.IsNonbasicLand())
			types.append("Land ");

		return types.toString();
	}
	public boolean IsCreature(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_CREATURE)!=0;
	}
	public boolean IsPlaneswalker(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_PLANESWALKER)!=0;
	}
	public boolean IsInstant(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_INSTANT)!=0;
	}
	public boolean IsSorcery(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_SORCERY)!=0;
	}
	public boolean IsArtifact(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_ARTIFACT)!=0;
	}
	public boolean IsEquipment(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_EQUIPMENT)!=0;
	}
	public boolean IsEnchantment(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_ENCHANTMENT)!=0;
	}
	public boolean IsAura(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_AURA)!=0;
	}
	public boolean IsBasicLand(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_BASICLAND)!=0;
	}
	public boolean IsNonbasicLand(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_NONBASICLAND)!=0;
	}
	public boolean IsLand(){
		return this.IsBasicLand()||this.IsNonbasicLand();
	}
	public boolean IsToken(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_TOKEN)!=0;
	}
	public boolean IsLegendary(){
		return (this.typeFlags&TargetInfo.TARGET_TYPE_FLAG_LEGENDARY)!=0;
	}
	
	public boolean Playable;
	public boolean Activatable;
	public boolean IsSorcerySpeed(){return !this.GetKeywords().HasFlash();}
	public boolean IsPermanent(){return true;}

	protected Color GetAWTColor(){
		if(this.GetManaCost()==null)
			return ManaCost.GetAWTColorByFlag(this.tokenColor);
		return ManaCost.GetAWTColorByFlag(this.GetManaCost().GetColors());
	}
	public int GetColorFlags(){
		if(this.GetManaCost()==null)
			return this.tokenColor;
		return this.GetManaCost().GetColors();
	}
	
	public ManaCost GetManaCost(){
		if(this.morphed)
			return null;
		return this.cost;
	}
	public int GetPower(){return 0;}
	public int GetToughness(){return 0;}
	public int MarkDamage(int damage, int damageTypeFlags, CardBase source){return 0;}

	private ArrayList<TriggeredAbilityDamaged> tookDamageTriggers;
	public void AddTookDamageTrigger(TriggeredAbilityDamaged trigger){tookDamageTriggers.add(trigger);}
	public void RemoveTookDamageTrigger(TriggeredAbilityDamaged trigger){tookDamageTriggers.remove(trigger);}
	public ArrayList<MagicEffect[]> TriggerTookDamage(ITargetable recipient, int damage, int damageTypeFlags, CardBase source){
		ArrayList<MagicEffect[]> triggeredAbilities=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.tookDamageTriggers.size();i++){
			MagicEffect[] triggeredEffects=this.tookDamageTriggers.get(i).GetTriggeredEffects(recipient, damage, damageTypeFlags, source);
			if(triggeredEffects!=null){
				triggeredAbilities.add(triggeredEffects);
			}
		}
		return triggeredAbilities;
	}

	protected DamagePreventorManager damagePreventors;
	
	public int GetTargetEndpointX(){return this.minX+this.GetWidth()/2;}
	public int GetTargetEndpointY(){return this.minY+this.GetHeight()/2;}
	
	protected DeepCopyInfo GetDeepCopyInfo(){
		ArrayList<MagicEffect> nonmanaPlayCostsDeepCopy=null;
		if(this.nonmanaPlayCosts!=null){
			nonmanaPlayCostsDeepCopy=new ArrayList<MagicEffect>();
			for(int i=0;i<this.nonmanaPlayCosts.size();i++)
				nonmanaPlayCostsDeepCopy.add(this.nonmanaPlayCosts.get(i).DeepCopy());
		}

		Vector<MagicActivatedAbility> activatedAbilitiesDeepCopy=null;
		if(this.activatedAbilities!=null){
			activatedAbilitiesDeepCopy=new Vector<MagicActivatedAbility>();
			for(int i=0;i<this.activatedAbilities.size();i++){
				activatedAbilitiesDeepCopy.add(this.activatedAbilities.elementAt(i).DeepCopy());
			}
		}
		
		ArrayList<ContinuousAbility> continuousAbilitiesDeepCopy=new ArrayList<ContinuousAbility>();
		for(int i=0;i<this.continuousAbilities.size();i++)
			continuousAbilitiesDeepCopy.add(this.continuousAbilities.get(i).DeepCopy());
			
		
		ArrayList<MagicEffect> entersTheBattlefieldAdjustmentsDeepCopy=new ArrayList<MagicEffect>();
		for(int i=0;i<this.entersTheBattlefieldAdjustments.size();i++){
			entersTheBattlefieldAdjustmentsDeepCopy.add(this.entersTheBattlefieldAdjustments.get(i).DeepCopy());
		}
			
		ArrayList<TriggeredAbility> entersTheBattlefieldTriggersDeepCopy=new ArrayList<TriggeredAbility>();
		for(int i=0;i<this.entersTheBattlefieldTriggers.size();i++){
			entersTheBattlefieldTriggersDeepCopy.add(this.entersTheBattlefieldTriggers.get(i).DeepCopy());
		}

		ArrayList<TriggeredAbility> morphTriggersDeepCopy=new ArrayList<TriggeredAbility>();
		for(int i=0;i<this.morphTriggers.size();i++){
			morphTriggersDeepCopy.add(this.morphTriggers.get(i).DeepCopy());
		}
		
		ArrayList<TriggeredAbility> attacksTriggersDeepCopy=new ArrayList<TriggeredAbility>();
		for(int i=0;i<this.attacksTriggers.size();i++){
			attacksTriggersDeepCopy.add(this.attacksTriggers.get(i).DeepCopy());
		}
		
		ArrayList<TriggeredAbility> lifegainTriggersDeepCopy=new ArrayList<TriggeredAbility>();
		for(int i=0;i<this.lifegainTriggers.size();i++)
			lifegainTriggersDeepCopy.add(this.lifegainTriggers.get(i).DeepCopy());
			
		ArrayList<TriggeredAbility> upkeepTriggersDeepCopy=new ArrayList<TriggeredAbility>();
		for(int i=0;i<this.upkeepTriggers.size();i++)
			upkeepTriggersDeepCopy.add(this.upkeepTriggers.get(i).DeepCopy());
		
		ArrayList<TriggeredAbility> spellCastTriggersDeepCopy=new ArrayList<TriggeredAbility>();
		for(int i=0;i<this.spellCastTriggers.size();i++)
			spellCastTriggersDeepCopy.add(this.spellCastTriggers.get(i).DeepCopy());
		
		ArrayList<MagicEffect> effectsToUndoAtEndOfTurnDeepCopy=new ArrayList<MagicEffect>();
		for(int i=0;i<this.effectsToUndoAtEndOfTurn.size();i++)
			effectsToUndoAtEndOfTurnDeepCopy.add(this.effectsToUndoAtEndOfTurn.get(i).DeepCopy());

		ArrayList<MagicEffect> effectsToUndoWhenControlIsLostDeepCopy=new ArrayList<MagicEffect>();
		for(int i=0;i<this.effectsToUndoWhenControlIsLost.size();i++)
			effectsToUndoWhenControlIsLostDeepCopy.add(this.effectsToUndoWhenControlIsLost.get(i).DeepCopy());

		ArrayList<MagicEffect> effectsToUndoWhenLeavesBattlefieldDeepCopy=new ArrayList<MagicEffect>();
		for(int i=0;i<this.effectsToUndoWhenLeavesBattlefield.size();i++)
			effectsToUndoWhenLeavesBattlefieldDeepCopy.add(this.effectsToUndoWhenLeavesBattlefield.get(i).DeepCopy());

		MagicEffect[] playEffectsDeepCopy=null;
		if(this.playEffects!=null){
			playEffectsDeepCopy=new MagicEffect[this.playEffects.length];
			for(int i=0;i<this.playEffects.length;i++)
				playEffectsDeepCopy[i]=this.playEffects[i].DeepCopy();
		}

		MagicEffect[] playCostsDeepCopy=null;
		if(this.playCosts!=null){
			playCostsDeepCopy=new MagicEffect[this.playCosts.length];
			for(int i=0;i<this.playCosts.length;i++)
				playCostsDeepCopy[i]=this.playCosts[i].DeepCopy();
		}

		ArrayList<Enchantment> AurasDeepCopy=new ArrayList<Enchantment>();
		for(int i=0;i<this.Auras.size();i++)
			AurasDeepCopy.add(this.Auras.get(i).DeepCopy());

		ArrayList<ManaCost> activatedAbilityCostDeepCopy=new ArrayList<ManaCost>();
		for(int i=0;i<this.activatedAbilityCost.size();i++){
			activatedAbilityCostDeepCopy.add(this.activatedAbilityCost.get(i));
		}

		ArrayList<TargetInfo> cantBeTargetedByDeepCopy=new ArrayList<TargetInfo>();
		for(int i=0;i<this.cantBeTargetedBy.size();i++){
			cantBeTargetedByDeepCopy.add(this.cantBeTargetedBy.get(i).DeepCopy());
		}
		
		ArrayList<TriggeredAbilityDamaged> tookDamageTriggersDeepCopy=new ArrayList<TriggeredAbilityDamaged>(this.tookDamageTriggers.size());
		for(int i=0;i<this.tookDamageTriggers.size();i++){
			tookDamageTriggersDeepCopy.add(this.tookDamageTriggers.get(i).DeepCopy());
		}

		return new DeepCopyInfo(this.destinationX, this.destinationY, this.owner, this.controller, this.name, this.description, this.cost,
			this.tokenColor, this.turnsToSkipUntapping, this.untapPreventors,
			this.keywords.DeepCopy(), this.subtypes.DeepCopy(), nonmanaPlayCostsDeepCopy, activatedAbilitiesDeepCopy, continuousAbilitiesDeepCopy, entersTheBattlefieldTriggersDeepCopy,
			morphTriggersDeepCopy,
			entersTheBattlefieldAdjustmentsDeepCopy, attacksTriggersDeepCopy, lifegainTriggersDeepCopy, upkeepTriggersDeepCopy, spellCastTriggersDeepCopy,
			effectsToUndoAtEndOfTurnDeepCopy, effectsToUndoWhenControlIsLostDeepCopy,
			effectsToUndoWhenLeavesBattlefieldDeepCopy, this.damagePreventors.DeepCopy(), tookDamageTriggersDeepCopy,
			playEffectsDeepCopy, playCostsDeepCopy, AurasDeepCopy, this.faceDown, this.tapped, this.typeFlags, this.Playable, this.Activatable, activatedAbilityCostDeepCopy,
			cantBeTargetedByDeepCopy, this.morphed, this.GetZone());
	}
	
	protected class DeepCopyInfo{
		public DeepCopyInfo(int x, int y, Player owner, Player controller, String name, String description, ManaCost cost, int tokenColor, int turnsToSkipUntapping,
		int untapPreventors,
		KeywordCollection keywords, SubtypeCollection subtypes, ArrayList<MagicEffect> nonmanaPlayCosts, Vector<MagicActivatedAbility> activatedAbilities,
		ArrayList<ContinuousAbility> continuousAbilities,
		ArrayList<TriggeredAbility> entersTheBattlefieldTriggers, ArrayList<TriggeredAbility> morphTriggers,
		ArrayList<MagicEffect> entersTheBattlefieldAdjustments, ArrayList<TriggeredAbility> attacksTriggers,
		ArrayList<TriggeredAbility> lifegainTriggers, ArrayList<TriggeredAbility> upkeepTriggers, ArrayList<TriggeredAbility> spellCastTriggers,
		ArrayList<MagicEffect> effectsToUndoAtEndOfTurn, ArrayList<MagicEffect> effectsToUndoWhenControlIsLost,
		ArrayList<MagicEffect> effectsToUndoWhenLeavesBattlefield, DamagePreventorManager damagePreventors, ArrayList<TriggeredAbilityDamaged> tookDamageTriggers,
		MagicEffect[] playEffects, MagicEffect[] playCosts, ArrayList<Enchantment> auras,
		boolean faceDown, boolean tapped, int typeFlags, boolean playable, boolean activatable, ArrayList<ManaCost> activatedAbilityCost,
		ArrayList<TargetInfo> cantBeTargetedBy, boolean morphed, int explicitZoneForDeepCopies){
			this.x=x;
			this.y=y;
			this.owner=owner;
			this.controller=controller;
			this.name=name;
			this.description=description;
			this.cost=cost;
			this.tokenColor=tokenColor;
			this.turnsToSkipUntapping=turnsToSkipUntapping;
			this.untapPreventors=untapPreventors;
			this.keywords=keywords;
			this.subtypes=subtypes;
			this.nonmanaPlayCosts=nonmanaPlayCosts;
			this.activatedAbilities=activatedAbilities;
			this.continuousAbilities=continuousAbilities;
			this.entersTheBattlefieldTriggers=entersTheBattlefieldTriggers;
			this.morphTriggers=morphTriggers;
			this.entersTheBattlefieldAdjustments=entersTheBattlefieldAdjustments;
			this.attacksTriggers=attacksTriggers;
			this.lifegainTriggers=lifegainTriggers;
			this.upkeepTriggers=upkeepTriggers;
			this.spellCastTriggers=spellCastTriggers;
			this.effectsToUndoAtEndOfTurn=effectsToUndoAtEndOfTurn;
			this.effectsToUndoWhenControlIsLost=effectsToUndoWhenControlIsLost;
			this.effectsToUndoWhenLeavesBattlefield=effectsToUndoWhenLeavesBattlefield;
			this.damagePreventors=damagePreventors;
			this.tookDamageTriggers=tookDamageTriggers;
			this.playEffects=playEffects;
			this.playCosts=playCosts;
			this.auras=auras;
			this.equipments=equipments;
			this.faceDown=faceDown;
			this.tapped=tapped;
			this.typeFlags=typeFlags;
			this.playable=playable;
			this.activatable=activatable;
			this.activatedAbilityCost=activatedAbilityCost;
			this.cantBeTargetedBy=cantBeTargetedBy;
			this.morphed=morphed;
			this.explicitZoneForDeepCopies=explicitZoneForDeepCopies;
		}
	
		public int x;
		public int y;
		public Player owner;
		public Player controller;
		public String name;
		public String description;
		public ManaCost cost;
		public int tokenColor;
		public int turnsToSkipUntapping;
		public int untapPreventors;
		public KeywordCollection keywords;
		public SubtypeCollection subtypes;
		public ArrayList<MagicEffect> nonmanaPlayCosts;
		public Vector<MagicActivatedAbility> activatedAbilities;
		public ArrayList<ContinuousAbility> continuousAbilities;
		public ArrayList<TriggeredAbility> entersTheBattlefieldTriggers;
		public ArrayList<TriggeredAbility> morphTriggers;
		public ArrayList<MagicEffect> entersTheBattlefieldAdjustments;
		public ArrayList<TriggeredAbility> attacksTriggers;
		public ArrayList<TriggeredAbility> lifegainTriggers;
		public ArrayList<TriggeredAbility> upkeepTriggers;
		public ArrayList<TriggeredAbility> spellCastTriggers;
		public ArrayList<MagicEffect> effectsToUndoAtEndOfTurn;
		public ArrayList<MagicEffect> effectsToUndoWhenControlIsLost;
		public ArrayList<MagicEffect> effectsToUndoWhenLeavesBattlefield;
		public DamagePreventorManager damagePreventors;
		public ArrayList<TriggeredAbilityDamaged> tookDamageTriggers;
		public MagicEffect[] playEffects;
		public MagicEffect[] playCosts;
		public ArrayList<Enchantment> auras;
		public ArrayList<Artifact> equipments;
		public boolean faceDown;
		public boolean tapped;
		public int typeFlags;
		public boolean playable;
		public boolean activatable;
		public ArrayList<ManaCost> activatedAbilityCost;
		public ArrayList<TargetInfo> cantBeTargetedBy;
		public boolean morphed;
		public int explicitZoneForDeepCopies;
	}
	
	protected CardBase(Player owner, String name, String description, int typeFlags, ManaCost cost, MagicEffect[] playeffects){
		super(0, 0, WIDTH, HEIGHT);
		this.owner=owner;
		this.controller=owner;
		this.name=name;
		this.description=description;
		this.cost=cost;
		this.tokenColor=ManaCost.COLOR_FLAG_COLORLESS;
		this.turnsToSkipUntapping=0;
		this.untapPreventors=0;
		this.tapped=false;
		this.Playable=false;
		this.Activatable=false;
		this.typeFlags=typeFlags;
		this.activatedAbilities=new Vector<MagicActivatedAbility>();
		this.continuousAbilities=new ArrayList<ContinuousAbility>();
		this.playEffects=playeffects;
		this.keywords=new KeywordCollection();
		this.subtypes=new SubtypeCollection();
		this.lifegainTriggers=new ArrayList<TriggeredAbility>();
		this.upkeepTriggers=new ArrayList<TriggeredAbility>();
		this.spellCastTriggers=new ArrayList<TriggeredAbility>();
		this.Auras=new ArrayList<Enchantment>();
		this.entersTheBattlefieldTriggers=new ArrayList<TriggeredAbility>();
		this.morphTriggers=new ArrayList<TriggeredAbility>();
		this.entersTheBattlefieldAdjustments=new ArrayList<MagicEffect>();
		this.attacksTriggers=new ArrayList<TriggeredAbility>();
		this.effectsToUndoAtEndOfTurn=new ArrayList<MagicEffect>();
		this.effectsToUndoWhenControlIsLost=new ArrayList<MagicEffect>();
		this.effectsToUndoWhenLeavesBattlefield=new ArrayList<MagicEffect>();
		this.damagePreventors=new DamagePreventorManager();
		this.tookDamageTriggers=new ArrayList<TriggeredAbilityDamaged>();
		this.activatedAbilityCost=new ArrayList<ManaCost>();
		this.cantBeTargetedBy=new ArrayList<TargetInfo>();
		this.morphed=false;
		this.explicitZoneForDeepCopies=-1;
	}
	
	protected CardBase(DeepCopyInfo dcinfo){
		super(dcinfo.x, dcinfo.y, dcinfo.x+WIDTH, dcinfo.y+HEIGHT);
		this.owner=dcinfo.owner;
		this.controller=dcinfo.controller;
		this.name=dcinfo.name;
		this.description=dcinfo.description;
		this.cost=dcinfo.cost;
		this.tokenColor=dcinfo.tokenColor;
		this.turnsToSkipUntapping=dcinfo.turnsToSkipUntapping;
		this.untapPreventors=dcinfo.untapPreventors;
		this.keywords=dcinfo.keywords;
		this.subtypes=dcinfo.subtypes;
		this.nonmanaPlayCosts=dcinfo.nonmanaPlayCosts;
		this.activatedAbilities=dcinfo.activatedAbilities;
		this.continuousAbilities=dcinfo.continuousAbilities;
		this.entersTheBattlefieldTriggers=dcinfo.entersTheBattlefieldTriggers;
		this.morphTriggers=dcinfo.morphTriggers;
		this.entersTheBattlefieldAdjustments=dcinfo.entersTheBattlefieldAdjustments;
		this.attacksTriggers=dcinfo.attacksTriggers;
		this.lifegainTriggers=dcinfo.lifegainTriggers;
		this.upkeepTriggers=dcinfo.upkeepTriggers;
		this.spellCastTriggers=dcinfo.spellCastTriggers;
		this.effectsToUndoAtEndOfTurn=dcinfo.effectsToUndoAtEndOfTurn;
		this.effectsToUndoWhenControlIsLost=dcinfo.effectsToUndoWhenControlIsLost;
		this.effectsToUndoWhenLeavesBattlefield=dcinfo.effectsToUndoWhenLeavesBattlefield;
		this.damagePreventors=dcinfo.damagePreventors;
		this.tookDamageTriggers=dcinfo.tookDamageTriggers;
		this.playEffects=dcinfo.playEffects;
		this.playCosts=dcinfo.playCosts;
		this.Auras=dcinfo.auras;
		this.faceDown=dcinfo.faceDown;
		this.tapped=dcinfo.tapped;
		this.typeFlags=dcinfo.typeFlags;
		this.Playable=dcinfo.playable;
		this.Activatable=dcinfo.activatable;
		this.activatedAbilityCost=dcinfo.activatedAbilityCost;
		this.cantBeTargetedBy=dcinfo.cantBeTargetedBy;
		this.morphed=dcinfo.morphed;
		this.explicitZoneForDeepCopies=dcinfo.explicitZoneForDeepCopies;
	}

	protected void UpdateSelfPointers(CardBase original){
		for(int i=0;i<this.tookDamageTriggers.size();i++){
			this.tookDamageTriggers.get(i).UpdateTargets(original, this);
		}
		for(int i=0;i<this.upkeepTriggers.size();i++){
			this.upkeepTriggers.get(i).UpdateTargets(original, this);
		}
		for(int i=0;i<this.spellCastTriggers.size();i++){
			this.spellCastTriggers.get(i).UpdateTargets(original, this);
		}
		for(int i=0;i<this.lifegainTriggers.size();i++){
			this.lifegainTriggers.get(i).UpdateTargets(original, this);
		}
		for(int i=0;i<this.attacksTriggers.size();i++){
			this.attacksTriggers.get(i).UpdateTargets(original, this);
		}
		for(int i=0;i<this.entersTheBattlefieldAdjustments.size();i++){
			this.entersTheBattlefieldAdjustments.get(i).TargetData.UpdateTargets(original, this);
		}
		for(int i=0;i<this.entersTheBattlefieldTriggers.size();i++){
			this.entersTheBattlefieldTriggers.get(i).UpdateTargets(original, this);
		}
		for(int i=0;i<this.morphTriggers.size();i++){
			this.morphTriggers.get(i).UpdateTargets(original, this);
		}
		for(int i=0;i<this.activatedAbilities.size();i++){
			MagicEffect[] ceffects=this.activatedAbilities.get(i).Effects;
			for(int j=0;j<ceffects.length;j++){
				ceffects[j].TargetData.UpdateTargets(original, this);
			}
		}
		for(int i=0;i<this.continuousAbilities.size();i++){
			this.continuousAbilities.get(i).UpdateTargets(original, this);
		}
	}

	public boolean IsAttached(){return false;}
	
	public void AddEnchantmentAura(Enchantment enchantment){
		enchantment.SetTargetPermanent(this);
		this.Auras.add(enchantment);
	}
	
	public void HandleEntersTheBattlefieldModifications(Vector<Player> players){
		for(int i=0;i<this.entersTheBattlefieldAdjustments.size();i++){
			this.entersTheBattlefieldAdjustments.get(i).Resolve(players);
		}
	}

	public ArrayList<MagicEffect[]> TriggerEntersTheBattlefield(CardBase cardEntered, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();

		for(int i=0;i<this.entersTheBattlefieldTriggers.size();i++){
			MagicEffect[] teffects=this.entersTheBattlefieldTriggers.get(i).GetTriggeredEffects(cardEntered, players);
			if(teffects!=null){
				triggeredEffects.add(teffects);
			}
		}
		
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> TriggerMorph(CardBase cardMorphed, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();

		for(int i=0;i<this.morphTriggers.size();i++){
			MagicEffect[] teffects=this.morphTriggers.get(i).GetTriggeredEffects(cardMorphed, players);
			if(teffects!=null){
				triggeredEffects.add(teffects);
			}
		}
		
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> TriggerAttackersDeclared(Creature[] attackers, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();

		for(int i=0;i<this.attacksTriggers.size();i++){
			for(int a=0;a<attackers.length;a++){
				MagicEffect[] teffects=this.attacksTriggers.get(i).GetTriggeredEffects(attackers[a], players);
				if(teffects!=null){
					triggeredEffects.add(teffects);
				}
			}
		}
		
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> TriggerGainedLife(Player controller, int lifegained, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();

		for(int i=0;i<this.lifegainTriggers.size();i++){
			MagicEffect[] teffects=this.lifegainTriggers.get(i).GetTriggeredEffects(controller, players);
			if(teffects!=null){
				triggeredEffects.add(teffects);
			}
		}
		
		return triggeredEffects;
	}

	public ArrayList<MagicEffect[]> TriggerUpkeep(Player playerWhoseTurnItIs, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();

		for(int i=0;i<this.upkeepTriggers.size();i++){
			MagicEffect[] teffects=this.upkeepTriggers.get(i).GetTriggeredEffects(playerWhoseTurnItIs, players);
			if(teffects!=null){
				triggeredEffects.add(teffects);
			}
		}

		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> TriggerSpellCast(CardBase castCard, Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();

		for(int i=0;i<this.spellCastTriggers.size();i++){
			MagicEffect[] teffects=this.spellCastTriggers.get(i).GetTriggeredEffects(castCard, players);
			if(teffects!=null){
				triggeredEffects.add(teffects);
			}
		}

		return triggeredEffects;
	}
	
	public void handleStateBasedActions(Vector<Player> players){
		for(int i=0;i<this.continuousAbilities.size();i++){
			this.continuousAbilities.get(i).CheckConditions(players, this.controller.GetIndex());
		}
	}

	public ArrayList<MagicEffect[]> Destroy(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		this.tapped=false;
		for(int i=0;i<this.effectsToUndoWhenControlIsLost.size();i++){
			triggeredEffects.addAll(this.effectsToUndoWhenControlIsLost.get(i).Undo(players));
		}
		this.effectsToUndoWhenControlIsLost.clear();
		for(int i=0;i<this.effectsToUndoAtEndOfTurn.size();i++){
			triggeredEffects.addAll(this.effectsToUndoAtEndOfTurn.get(i).Undo(players));
		}
		this.effectsToUndoAtEndOfTurn.clear();
		for(int i=0;i<this.effectsToUndoWhenLeavesBattlefield.size();i++){
			triggeredEffects.addAll(this.effectsToUndoWhenLeavesBattlefield.get(i).Undo(players));
		}
		this.effectsToUndoWhenLeavesBattlefield.clear();
		for(int i=0;i<this.continuousAbilities.size();i++){
			triggeredEffects.addAll(this.continuousAbilities.get(i).Reset(players));
		}
		if(this.IsMorphed())
			this.Morph();
		return triggeredEffects;
	}
	
	public ArrayList<MagicEffect[]> EndPhase(Vector<Player> players){
		return new ArrayList<MagicEffect[]>();//TODO: Triggered Effects "at end of turn"
	}
	
	public ArrayList<MagicEffect[]> CleanupPhase(Vector<Player> players){
		for(int i=0;i<this.activatedAbilities.size();i++){
			this.activatedAbilities.get(i).ResetTimesUsed();
		}
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		for(int i=0;i<this.effectsToUndoAtEndOfTurn.size();i++){
			triggeredEffects.addAll(this.effectsToUndoAtEndOfTurn.get(i).Undo(players));
		}
		this.effectsToUndoAtEndOfTurn.clear();
		return triggeredEffects;
	}

	public void AddDamagePreventor(DamagePreventor dmgprev){
		this.damagePreventors.AddDamagePreventor(dmgprev);
	}
	
	public void RemoveDamagePreventor(DamagePreventor dmgprev){
		this.damagePreventors.RemoveDamagePreventor(dmgprev);
	}

	public abstract CardBase DeepCopy();
	
	public int paintFullVersionAt(Graphics g, int x, int y){
		if(this.faceDown&&!this.morphed){
			Painting.FillRectWithBorder(g, x, y, FULLWIDTH, FULLHEIGHT, FACE_DOWN_COLOR, Color.black);
			return y;
		}

		FontMetrics metrics=g.getFontMetrics();

		Painting.FillRectWithBorder(g, x, y, FULLWIDTH, FULLHEIGHT, this.GetAWTColor(), Color.black);
		int cy=y+14;
		g.setColor(Color.black);
		g.drawString(this.GetName(), x+2, cy);
		cy+=14;
		if(this.GetManaCost()!=null){
			this.GetManaCost().paint(g, x+2, cy-12);
		}
		cy+=14;
		String subtypestring=this.GetSubtypes().ToSpaceSeparatedString();
		if(subtypestring.length()>0)
			subtypestring="-"+subtypestring;
		g.drawString(this.GetTypesAsString()+" "+subtypestring, x+2, cy);
		cy+=28;
		if(this.tapped)
			g.drawString("Tapped", x+2, cy);
		cy+=14;
		cy+=14*Painting.DrawWrappedString(g, this.GetKeywords().ToCommaSeparatedString(), x+2, y+56, FULLWIDTH-4, 14);
		
		for(int i=0;i<this.continuousAbilities.size();i++){
			cy+=14*Painting.DrawWrappedString(g, this.continuousAbilities.get(i).GetRulesText(), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		for(int i=0;i<this.activatedAbilities.size();i++){
			int cx=x+2;
			MagicActivatedAbility ability=this.activatedAbilities.elementAt(i);
			if(this.morphed&&ability.Effects.length==1&&ability.Effects[0] instanceof MagicEffectMorphTargetCreature)
				continue;
			if(ability.ManaCosts!=null){
				cx=ability.ManaCosts.paint(g, cx, cy);
				if(ability.Costs!=null){
					g.setColor(Color.black);
					g.drawString(", ", cx, cy+14);
					cx+=metrics.stringWidth(", ");
				}
			}
			if(ability.Costs!=null){
				for(int costindex=0;costindex<ability.Costs.length;costindex++){
					String rulestext=ability.Costs[costindex].GetRulesText()+(costindex==ability.Costs.length-1?"":", ");
					g.drawString(rulestext, cx, cy+14);
					cx+=metrics.stringWidth(rulestext);
				}
			}
			
			g.drawString(": ", cx, cy+14);
			cx+=metrics.stringWidth(": ");
			
			if(cx>x+FULLWIDTH/2){
				cx=x+20;
				cy+=14;
			}
			
			for(int effectindex=0;effectindex<ability.Effects.length;effectindex++){
				String rulestext=ability.Effects[effectindex].GetRulesText()+(effectindex==ability.Effects.length-1?"":", ");
				cy+=14*Painting.DrawWrappedString(g, rulestext, cx, cy+14, FULLWIDTH-cx+x-2, 14);
			}
			
			String abilityzones=ability.GetSourceZones().GetCSV();
			if(!"field".equals(abilityzones)){
				cy+=14*Painting.DrawWrappedString(g, "Activate this ability from your "+abilityzones, cx, cy+14, FULLWIDTH-cx+x-2, 14);
			}
			
			if(ability.IsSorcerySpeed()){
				cy+=14*Painting.DrawWrappedString(g, "Activate only as a sorcery.", cx, cy+14, FULLWIDTH-cx+x-2, 14);
			}
			
			cy+=14;
		}
		
		for(int i=0;i<this.entersTheBattlefieldAdjustments.size();i++){
			MagicEffect centersAdjustEffect=this.entersTheBattlefieldAdjustments.get(i);
			StringBuilder centersAdjustRules=new StringBuilder("Enters the battlefield with: ");
			if(centersAdjustEffect.IsOptional()){
				centersAdjustRules.append("Optional: ");
			}
			centersAdjustRules.append(centersAdjustEffect.GetRulesText());
			cy+=14*Painting.DrawWrappedString(g, centersAdjustRules.toString(), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		for(int i=0;i<this.entersTheBattlefieldTriggers.size();i++){
			cy+=14*Painting.DrawWrappedString(g, this.entersTheBattlefieldTriggers.get(i).GetRulesText("enters the battlefield"), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		for(int i=0;i<this.morphTriggers.size();i++){
			cy+=14*Painting.DrawWrappedString(g, this.morphTriggers.get(i).GetRulesText("is turned face up"), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		for(int i=0;i<this.attacksTriggers.size();i++){
			cy+=14*Painting.DrawWrappedString(g, this.attacksTriggers.get(i).GetRulesText("attacks"), x+2, cy+14, FULLWIDTH-4, 14);
		}

		for(int i=0;i<this.lifegainTriggers.size();i++){
			cy+=14*Painting.DrawWrappedString(g, this.lifegainTriggers.get(i).GetRulesText("gains life"), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		for(int i=0;i<this.upkeepTriggers.size();i++){
			cy+=14*Painting.DrawWrappedString(g, this.upkeepTriggers.get(i).GetRulesText("at the beginning of each upkeep"), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		for(int i=0;i<this.spellCastTriggers.size();i++){
			cy+=14*Painting.DrawWrappedString(g, this.spellCastTriggers.get(i).GetRulesText("is cast"), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		for(int i=0;i<this.tookDamageTriggers.size();i++){
			cy+=14*Painting.DrawWrappedString(g, this.tookDamageTriggers.get(i).GetRulesText(), x+2, cy+14, FULLWIDTH-4, 14);
		}

		if(this.playEffects!=null){
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<this.playEffects.length;i++){
				sb.append(this.playEffects[i].GetRulesText()+" ");
			}
			
			cy+=14*Painting.DrawWrappedString(g, sb.toString(), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		if(this.playCosts!=null){
			StringBuilder sb=new StringBuilder("Additional costs: ");
			for(int i=0;i<this.playCosts.length;i++){
				sb.append(this.playCosts[i].GetRulesText()+" ");
			}
			
			cy+=14*Painting.DrawWrappedString(g, sb.toString(), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		return cy;
	}
	
	public void paint(Graphics g){
		if(this.faceDown&&!this.morphed){
			Painting.FillRectWithBorder(g, this.minX, this.minY, this.GetWidth(), this.GetHeight(), FACE_DOWN_COLOR, Color.black);
			return;
		}
		Painting.FillRectWithBorder(g, this.minX, this.minY, this.GetWidth(), this.GetHeight(), this.GetAWTColor(), Color.black);
		if(this.GetManaCost()!=null){
			this.GetManaCost().paint(g, this.minX+2, this.minY+16);
		}
		g.setColor(Color.black);
		g.drawString(this.GetName(), this.minX+2, this.minY+14);
		if(this.tapped)
			g.drawString("Tapped", this.minX+2, this.minY+42);
		Painting.DrawWrappedString(g, this.GetKeywords().ToCommaSeparatedString(), this.minX+2, this.minY+56, WIDTH-4, 14);

		if(this.Playable||this.Activatable){
			g.setColor(new Color(255, 225, 0));
			g.drawRect(this.minX-1, this.minY-1, this.GetWidth()+2, this.GetHeight()+2);
			g.drawRect(this.minX-2, this.minY-2, this.GetWidth()+4, this.GetHeight()+4);
		}
	}
}
