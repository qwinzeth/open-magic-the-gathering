package Matt.Stowe.MTG.Mechanics.MagicEffects.KeywordEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class MagicEffectGrantProtectionToTarget extends MagicEffect{
	private TargetInfo protectedFromInfo;
	private MagicEffect[] protectionEffects;
	private boolean chooseColor;

	private MagicEffectGrantProtectionToTarget(DeepCopyInfo dcinfo, TargetInfo protectedFromInfo, MagicEffect[] protectionEffects, boolean chooseColor){
		super(dcinfo);
		this.protectedFromInfo=protectedFromInfo;
		this.protectionEffects=protectionEffects;
		this.chooseColor=chooseColor;
	}

	public MagicEffectGrantProtectionToTarget(Player controller, TargetInfo targetInfo, TargetInfo protectedFromInfo, boolean chooseColor, Duration duration){
		super(controller, targetInfo, duration);
		this.protectedFromInfo=protectedFromInfo;
		this.protectedFromInfo.SetValidTargetZones(new ZoneOptions(ZoneOptions.ANY));
		this.protectionEffects=new MagicEffect[]{
			new MagicEffectGrantConditionalUnblockableToTargetCreature(controller, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY), protectedFromInfo, duration),
			new MagicEffectGrantConditionalShroudToTargetCreature(controller, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY), protectedFromInfo, duration),
			new MagicEffectAddDamagePreventorToITargetables(controller, new DamagePreventor(DamagePreventor.DAMAGE_TYPE_FLAG_ANY, protectedFromInfo),
				false, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY), duration)
		};
		this.chooseColor=chooseColor;
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		for(int i=0;this.TargetData.GetTarget(i)!=null;i++){
			ITargetable ctarget=this.TargetData.GetTarget(i);
			for(int j=0;j<this.protectionEffects.length;j++){
				MagicEffect deepCopiedEffect=this.protectionEffects[j].DeepCopy();
				deepCopiedEffect.TargetData.SetLockedTarget(0, ctarget);
				deepCopiedEffect.Resolve(players);
			}
		}
		return new ArrayList<MagicEffect[]>();
	}

	public void MakeRequiredDecisions(){
		if(this.chooseColor){
			this.protectedFromInfo.SetColorORFlags(this.controller.GetColorChoice(this, ManaCost.COLOR_ANY));
		}
	}

	public MagicEffect DeepCopy(){
		MagicEffect[] protectionEffectsDeepCopy=new MagicEffect[this.protectionEffects.length];
		for(int i=0;i<this.protectionEffects.length;i++){
			protectionEffectsDeepCopy[i]=this.protectionEffects[i].DeepCopy();
		}
		return new MagicEffectGrantProtectionToTarget(this.GetDeepCopyInfo(), this.protectedFromInfo.DeepCopy(), protectionEffectsDeepCopy, this.chooseColor);
	}

	public String GetRulesText(){
		return this.TargetData.GetCSV()+" has protection from "+this.protectedFromInfo.GetCSV().replace("1 target","any")+" "+this.getDurationString()+".";
	}
}
