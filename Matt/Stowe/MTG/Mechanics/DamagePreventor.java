package Matt.Stowe.MTG.Mechanics;

import java.util.ArrayList;

public class DamagePreventor{
	public static final int DAMAGE_TYPE_FLAG_COMBAT=	1;
	public static final int DAMAGE_TYPE_FLAG_NONCOMBAT=	2;

	public static final int DAMAGE_TYPE_FLAG_ANY=		3;
	
	private boolean preventAllDamage;
	private int damageToPrevent;
	private int damageTypeORFlags;
	private TargetInfo preventableSourceInfo;
	public void SetColorORFlags(int newflags){this.preventableSourceInfo.SetColorORFlags(newflags);}
	
	private DamagePreventor(boolean preventAllDamage, int damageToPrevent, int damageTypeORFlags, TargetInfo preventableSourceInfo){
		this.preventAllDamage=preventAllDamage;
		this.damageToPrevent=damageToPrevent;
		this.damageTypeORFlags=damageTypeORFlags;
		this.preventableSourceInfo=preventableSourceInfo;
	}
	
	public DamagePreventor(int amount, int damageTypeORFlags, TargetInfo preventableSourceInfo){
		this.preventAllDamage=false;
		this.damageToPrevent=amount;
		this.damageTypeORFlags=damageTypeORFlags;
		this.preventableSourceInfo=preventableSourceInfo;
	}

	public DamagePreventor(int damageTypeORFlags, TargetInfo preventableSourceInfo){
		this.preventAllDamage=true;
		this.damageTypeORFlags=damageTypeORFlags;
		this.preventableSourceInfo=preventableSourceInfo;
	}
	
	public boolean UsedUp(){
		return !this.preventAllDamage&&this.damageToPrevent<=0;
	}
	
	public int PreventDamage(int incomingDamageAmount, int incomingDamageTypeFlags, ITargetable source){
		int preventedDamage=0;
		
		if(this.preventableSourceInfo.IsValidTarget(source)){
			if(this.preventAllDamage||incomingDamageAmount<=this.damageToPrevent){
				preventedDamage=incomingDamageAmount;
			}else{
				preventedDamage=this.damageToPrevent;
			}
		}

		this.damageToPrevent-=preventedDamage;

		return preventedDamage;
	}
	
	public DamagePreventor DeepCopy(){
		return new DamagePreventor(this.preventAllDamage, this.damageToPrevent, this.damageTypeORFlags, this.preventableSourceInfo.DeepCopy());
	}
	
	public static String GetDamageTypeString(int damageTypeFlags){
		if(damageTypeFlags==DAMAGE_TYPE_FLAG_COMBAT){
			return "combat ";
		}else if(damageTypeFlags==DAMAGE_TYPE_FLAG_NONCOMBAT){
			return "noncombat ";
		}
		
		return "";
	}
	
	public String GetRulesText(){
		StringBuilder rules=new StringBuilder("Prevent ");
		if(this.preventAllDamage){
			rules.append("all");
		}else{
			rules.append("the next "+this.damageToPrevent+" ");
		}
		
		rules.append(GetDamageTypeString(this.damageTypeORFlags));
		
		rules.append("damage from "+this.preventableSourceInfo.GetCSV().replace("1 target", "these types:"));
		
		return rules.toString();
	}
}