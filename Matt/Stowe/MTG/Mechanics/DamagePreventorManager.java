package Matt.Stowe.MTG.Mechanics;

import java.util.ArrayList;

public class DamagePreventorManager{
	private ArrayList<DamagePreventor> damagePreventors;
	
	private DamagePreventorManager(ArrayList<DamagePreventor> damagePreventors){
		this.damagePreventors=damagePreventors;
	}
	
	public DamagePreventorManager(){
		this.damagePreventors=new ArrayList<DamagePreventor>();
	}
	
	public DamagePreventorManager DeepCopy(){
		ArrayList<DamagePreventor> damagePreventorsDeepCopy=new ArrayList<DamagePreventor>();
		for(int i=0;i<this.damagePreventors.size();i++){
			damagePreventorsDeepCopy.add(this.damagePreventors.get(i));
		}
		return new DamagePreventorManager(damagePreventorsDeepCopy);
	}
	
	public void AddDamagePreventor(DamagePreventor dmgprev){
		this.damagePreventors.add(dmgprev);
	}
	
	public void RemoveDamagePreventor(DamagePreventor dmgprev){
		this.damagePreventors.remove(dmgprev);
	}
	
	public int PreventDamage(int incomingDamageAmount, int incomingDamageTypeFlags, ITargetable source){
		int preventedDamage=0;

		for(int i=0;preventedDamage<incomingDamageAmount&&i<this.damagePreventors.size();i++){
			DamagePreventor cdmgprev=this.damagePreventors.get(i);
			preventedDamage+=cdmgprev.PreventDamage(incomingDamageAmount-preventedDamage, incomingDamageTypeFlags, source);
			if(cdmgprev.UsedUp()){
				this.damagePreventors.remove(i--);
			}
		}
		
		return incomingDamageAmount-preventedDamage;
	}
}
