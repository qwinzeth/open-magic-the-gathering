package Matt.Stowe.MTG.Cards;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.Common.*;

import java.awt.*;

public class BasicLand extends CardBase{
	private int color;

	public BasicLand DeepCopy(){
		return new BasicLand(this.GetDeepCopyInfo(), this.color);
	}

	protected Color GetAWTColor(){
		return ManaCost.GetAWTColorByFlag(this.color);
	}
	
	private BasicLand(Player owner, String name, int color){
		super(owner, name, "", TargetInfo.TARGET_TYPE_FLAG_BASICLAND, null, null);
		this.color=color;
	}
	
	private BasicLand(DeepCopyInfo dcinfo, int color){
		super(dcinfo);
		this.color=color;
	}
	
	public static BasicLand GenerateMountain(Player controller){
		BasicLand newmountain=new BasicLand(controller, "Mountain", ManaCost.COLOR_FLAG_RED);
		newmountain.GetSubtypes().AddSubtype(SubtypeCollection.MOUNTAIN);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(controller, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, newmountain);
		newmountain.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(controller, 1, 0, 0, 0, 0, 0)}));
		return newmountain;
	}

	public static BasicLand GenerateForest(Player controller){
		BasicLand newforest=new BasicLand(controller, "Forest", ManaCost.COLOR_FLAG_GREEN);
		newforest.GetSubtypes().AddSubtype(SubtypeCollection.FOREST);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(controller, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, newforest);
		newforest.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts,new MagicEffect[]{new MagicEffectAddManaToPool(controller, 0, 1, 0, 0, 0, 0)}));
		return newforest;
	}

	public static BasicLand GenerateIsland(Player controller){
		BasicLand newisland=new BasicLand(controller, "Island", ManaCost.COLOR_FLAG_BLUE);
		newisland.GetSubtypes().AddSubtype(SubtypeCollection.ISLAND);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(controller, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, newisland);
		newisland.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(controller, 0, 0, 1, 0, 0, 0)}));
		return newisland;
	}

	public static BasicLand GeneratePlains(Player controller){
		BasicLand newplains=new BasicLand(controller, "Plains", ManaCost.COLOR_FLAG_WHITE);
		newplains.GetSubtypes().AddSubtype(SubtypeCollection.PLAINS);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(controller, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, newplains);
		newplains.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(controller, 0, 0, 0, 1, 0, 0)}));
		return newplains;
	}

	public static BasicLand GenerateSwamp(Player controller){
		BasicLand newswamp=new BasicLand(controller, "Swamp", ManaCost.COLOR_FLAG_BLACK);
		newswamp.GetSubtypes().AddSubtype(SubtypeCollection.SWAMP);
		MagicEffect[] nonmanaCosts=new MagicEffect[1];
		nonmanaCosts[0]=MagicEffectTapTargetCard.Create(controller, 1, 1, TargetInfo.TARGET_TYPE_FLAG_LAND);
		nonmanaCosts[0].TargetData.SetLockedTarget(0, newswamp);
		newswamp.AddActivatedAbility(new MagicActivatedAbility(false, null, nonmanaCosts, new MagicEffect[]{new MagicEffectAddManaToPool(controller, 0, 0, 0, 0, 1, 0)}));
		return newswamp;
	}
}
