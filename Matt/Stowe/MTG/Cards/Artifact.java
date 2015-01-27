package Matt.Stowe.MTG.Cards;
import Matt.Stowe.MTG.*;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.awt.*;
import java.util.*;

public class Artifact extends CardBase{
	private ManaCost equipCost;
	private MagicEffect[] equipEffects;
	private Creature equippedCreature;
	public Creature GetEquippedCreature(){return this.equippedCreature;}

	private Artifact(Player controller, String name, String description, int typeFlags, ManaCost cost, ManaCost equipCost, MagicEffect[] equipEffects){
		super(controller, name, description, typeFlags, cost, null);
		this.equipEffects=equipEffects;
		if(this.equipEffects!=null){
			for(int i=0;i<this.equipEffects.length;i++){
				this.equipEffects[i].SetSource(this);
			}
		}

		this.equipCost=equipCost;
	}
	
	public static Artifact NewArtifact(Player controller, String name, String description, ManaCost cost){
		return new Artifact(controller, name, description, TargetInfo.TARGET_TYPE_FLAG_ARTIFACT, cost, null, null);
	}
	
	public static Artifact NewArtifactEquipment(Player controller, String name, String description, ManaCost cost, ManaCost equipCost, MagicEffect[] nonmanaEquipCost, MagicEffect[] equipEffects){
		Artifact equipment=new Artifact(controller, name, description, TargetInfo.TARGET_TYPE_FLAG_ARTIFACT|TargetInfo.TARGET_TYPE_FLAG_EQUIPMENT, cost, equipCost, equipEffects);
		equipment.AddActivatedAbility(new MagicActivatedAbility(true, equipCost, nonmanaEquipCost, new MagicEffect[]{new MagicEffectEquip(controller, equipment)}));
		return equipment;
	}

	private Artifact(DeepCopyInfo dcinfo, ManaCost equipCost, MagicEffect[] equipEffects, Creature equippedCreature){
		super(dcinfo);
		this.equipCost=equipCost;
		this.equipEffects=equipEffects;
		if(this.equipEffects!=null){
			for(int i=0;i<this.equipEffects.length;i++){
				this.equipEffects[i].SetSource(this);
			}
		}
		this.equippedCreature=equippedCreature;
	}
	
	public Artifact DeepCopy(){
		MagicEffect[] deepCopyEquipEffects=null;
		if(this.equipEffects!=null){
			deepCopyEquipEffects=new MagicEffect[this.equipEffects.length];
			for(int i=0;i<this.equipEffects.length;i++){
				deepCopyEquipEffects[i]=this.equipEffects[i].DeepCopy();
			}
		}

		return new Artifact(this.GetDeepCopyInfo(), this.equipCost, deepCopyEquipEffects, this.equippedCreature);
	}
	
	public void Equip(Creature target, Vector<Player> players){
		this.UnEquip(players);
		this.equippedCreature=target;
		if(this.equipEffects!=null&&target!=null){
			for(int i=0;i<this.equipEffects.length;i++){
				MagicEffect ceffect=this.equipEffects[i];
				ceffect.TargetData.SetTarget(0, target);
				ceffect.Resolve(players);
			}
		}
	}

	public ArrayList<MagicEffect[]> UnEquip(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		if(this.IsAttached()){
			this.equippedCreature.Equipments.remove(this);
			if(this.equipEffects!=null){
				for(int i=0;i<this.equipEffects.length;i++){
					triggeredEffects.addAll(this.equipEffects[i].Undo(players));
				}
			}
			this.equippedCreature=null;
		}
		return triggeredEffects;
	}
	
	public boolean IsAttached(){
		return this.equippedCreature!=null;
	}
	
	public ArrayList<MagicEffect[]> Destroy(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=super.Destroy(players);
		triggeredEffects.addAll(this.UnEquip(players));
		return triggeredEffects;
	}
	
	public int paintFullVersionAt(Graphics g, int x, int y){
		int cy=super.paintFullVersionAt(g, x, y);
		
		if(this.faceDown)
			return cy;
		
		if(this.equipEffects!=null){
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<this.equipEffects.length;i++){
				sb.append(this.equipEffects[i].GetRulesText().replace("1 target","equipped")+" ");
			}
			
			cy+=14*Painting.DrawWrappedString(g, sb.toString(), x+2, cy+14, FULLWIDTH-4, 14);
		}
		
		return cy;
	}
}