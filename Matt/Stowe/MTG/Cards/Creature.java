package Matt.Stowe.MTG.Cards;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;

import java.awt.*;
import java.util.*;

public class Creature extends CardBase{
	public void SetMorphed(){
		super.SetMorphed();
		this.power=2+this.power-this.basepower;
		this.toughness=2+this.toughness-this.basetoughness;
	}
	public void Morph(){
		super.Morph();
		this.power=this.basepower+this.power-2;
		this.toughness=this.basetoughness+this.toughness-2;
	}

	private boolean hasBeenDeathtouched;
	public void MarkAsDeathtouched(){this.hasBeenDeathtouched=true;}

	private ArrayList<ManaCost> attackCost;
	public ArrayList<ManaCost> GetAttackManaCost(){return this.attackCost;}
	public void AddAttackManaCost(ManaCost newcost){
		this.attackCost.add(newcost);
	}
	public void RemoveAttackManaCost(ManaCost oldcost){
		this.attackCost.remove(oldcost);
	}
	
	private ArrayList<ManaCost> blockCost;
	public ArrayList<ManaCost> GetBlockManaCost(){return this.blockCost;}
	public void AddBlockManaCost(ManaCost newcost){
		this.blockCost.add(newcost);
	}
	public void RemoveBlockManaCost(ManaCost oldcost){
		this.blockCost.remove(oldcost);
	}

	private ArrayList<TargetInfo> cantBeBlockedBy;
	public void AddCantBeBlockedBy(TargetInfo info){this.cantBeBlockedBy.add(info);}
	public void RemoveCantBeBlockedBy(TargetInfo info){this.cantBeBlockedBy.remove(info);}
	public boolean CanBeBlockedBy(ITargetable blocker){
		for(int i=0;i<this.cantBeBlockedBy.size();i++){
			if(this.cantBeBlockedBy.get(i).IsValidTarget(blocker)){
				return false;
			}
		}
		
		return true;
	}
	
	public boolean CanAttack(){
		return !this.GetKeywords().HasSummoningSickness()&&!this.GetKeywords().HasDefender()&&!this.IsTapped();
	}

	public ITargetable TargetedPlayer;
	public boolean isAttacking(){return this.TargetedPlayer!=null;}

	private int maxBlocks;
	public int GetMaxBlocks(){return this.maxBlocks;}
	public void AlterMaxBlocks(int blocks){this.maxBlocks+=blocks;}
	
	private boolean blocking;
	public void UpdateBlocking(Vector<Player> players){
		for(int i=0;i<players.size();i++){
			Creature[] attackers=players.elementAt(i).GetAttackingCreatures();
			for(int a=0;a<attackers.length;a++){
				if(attackers[a].GetBlockers().contains(this)){
					this.blocking=true;
					return;
				}
			}
		}

		this.blocking=false;
	}
	public boolean isBlocking(){
		return this.blocking;
	}
	
	public int GetCombatStateFlags(){
		int flags=0;
		if(this.isAttacking())
			flags|=TargetInfo.COMBAT_STATE_FLAG_ATTACKING;
		if(this.isBlocking())
			flags|=TargetInfo.COMBAT_STATE_FLAG_BLOCKING;
		return flags;
	}
	
	private Vector<Creature> blockers;
	public void addBlocker(Creature blocker){
		this.blockers.add(blocker);
	}
	public void removeBlocker(Creature blocker){
		this.blockers.remove(blocker);
	}
	public Vector<Creature> GetBlockers(){return this.blockers;}

	private int power, basepower;
	public int GetPower(){return this.power+this.powerToughnessCounters;}

	private int toughness, basetoughness;
	public int GetToughness(){return this.toughness+this.powerToughnessCounters;}
	
	private int powerToughnessCounters;
	public void AddPowerToughnessCounters(int amount){
		this.powerToughnessCounters+=amount;
	}
	public int GetPowerToughnessCounters(){return this.powerToughnessCounters;}
	
	private int damageTaken;
	//Called when this creature takes damage
	public int MarkDamage(int damage, int damageTypeFlags, CardBase source){
		int damageDealt=this.damagePreventors.PreventDamage(damage, damageTypeFlags, source);
	
		if(damageDealt<0)
			return 0;
	
		this.damageTaken+=damageDealt;
		return damageDealt;
	}
	//Called when this creature does damage to a player or other creature
	public ArrayList<MagicEffect[]> DealtDamage(int damage, Vector<Player> players){
		if(this.GetKeywords().HasLifelink()){
			MagicEffectGainLife getlife=new MagicEffectGainLife(this.controller, damage);
			return getlife.Resolve(players);
		}
		
		return new ArrayList<MagicEffect[]>();
	}

	public boolean IsDestroyed(){
		if(this.GetToughness()<=0)
			return true;
			
		if(this.GetKeywords().HasIndestructable())
			return false;
		
		if(this.hasBeenDeathtouched)
			return true;
		
		return this.GetToughness()-this.damageTaken<=0;
	}
	
	public ArrayList<MagicEffect[]> EndPhase(Vector<Player> players){
		ArrayList<MagicEffect[]> triggers=super.EndPhase(players);
		return triggers;
	}
	
	public ArrayList<MagicEffect[]> CleanupPhase(Vector<Player> players){
		ArrayList<MagicEffect[]> triggers=super.CleanupPhase(players);
		this.damageTaken=0;
		return triggers;
	}
	
	public void AddPowerToughness(int addPower, int addToughness){
		this.power+=addPower;
		this.toughness+=addToughness;
	}
	
	public ArrayList<Artifact> Equipments;
	public void Equip(Artifact equipment, Vector<Player> players){
		equipment.Equip(this, players);
		this.Equipments.add(equipment);
	}

	private Creature(Player owner, String name, String description, int typeFlags, ManaCost mana, int power, int toughness){
		super(owner, name, description, typeFlags, mana, null);
		this.power=power;
		this.basepower=power;
		this.toughness=toughness;
		this.basetoughness=toughness;
		this.damageTaken=0;
		this.TargetedPlayer=null;
		this.blockers=new Vector<Creature>();
		this.Equipments=new ArrayList<Artifact>();
		this.blocking=false;
		this.maxBlocks=1;
		this.attackCost=new ArrayList<ManaCost>();
		this.blockCost=new ArrayList<ManaCost>();
		this.cantBeBlockedBy=new ArrayList<TargetInfo>();
		this.hasBeenDeathtouched=false;
	}
	
	private Creature(DeepCopyInfo dcinfo, ITargetable targetedPlayer, Vector<Creature> blockers, int power, int basepower,
	int toughness, int basetoughness, int powerToughnessCounters, int damageTaken, ArrayList<Artifact> equipments, boolean blocking,
	int maxBlocks,
	ArrayList<ManaCost> attackCost, ArrayList<ManaCost> blockCost, ArrayList<TargetInfo> cantBeBlockedBy, boolean hasBeenDeathtouched){
		super(dcinfo);
		this.TargetedPlayer=targetedPlayer;
		this.blockers=blockers;
		this.power=power;
		this.basepower=basepower;
		this.toughness=toughness;
		this.basetoughness=basetoughness;
		this.powerToughnessCounters=powerToughnessCounters;
		this.damageTaken=damageTaken;
		this.Equipments=equipments;
		this.blocking=blocking;
		this.maxBlocks=maxBlocks;
		this.attackCost=attackCost;
		this.blockCost=blockCost;
		this.cantBeBlockedBy=cantBeBlockedBy;
		this.hasBeenDeathtouched=hasBeenDeathtouched;
	}
	
	public static Creature NewCreature(Player owner, String name, String description, ManaCost mana, int power, int toughness){
		return new Creature(owner, name, description, TargetInfo.TARGET_TYPE_FLAG_CREATURE, mana, power, toughness);		
	}
	
	public static Creature NewArtifactCreature(Player owner, String name, String description, ManaCost mana, int power, int toughness){
		return new Creature(owner, name, description, TargetInfo.TARGET_TYPE_FLAG_CREATURE|TargetInfo.TARGET_TYPE_FLAG_ARTIFACT, mana, power, toughness);
	}
	
	public Creature DeepCopy(){
		Vector<Creature> shallowCopiedBlockers=new Vector<Creature>();
		for(int i=0;i<this.blockers.size();i++)
			shallowCopiedBlockers.add(this.blockers.elementAt(i));
		ArrayList<Artifact> shallowCopiedEquipments=new ArrayList<Artifact>();
		for(int i=0;i<this.Equipments.size();i++)
			shallowCopiedEquipments.add(this.Equipments.get(i));
		ArrayList<TargetInfo> deepCopiedCantBeBlockedBy=new ArrayList<TargetInfo>();
		for(int i=0;i<this.cantBeBlockedBy.size();i++)
			deepCopiedCantBeBlockedBy.add(this.cantBeBlockedBy.get(i).DeepCopy());
		Creature deepCopied=new Creature(this.GetDeepCopyInfo(), this.TargetedPlayer, shallowCopiedBlockers, this.power, this.basepower,
			this.toughness, this.basetoughness, this.powerToughnessCounters, this.damageTaken, shallowCopiedEquipments, this.blocking,
			this.maxBlocks, this.attackCost, this.blockCost, deepCopiedCantBeBlockedBy, this.hasBeenDeathtouched);
			
		deepCopied.UpdateSelfPointers(this);
		
		return deepCopied;
	}

	public ArrayList<MagicEffect[]> Destroy(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=super.Destroy(players);
		this.blockers.clear();
		this.TargetedPlayer=null;
		this.powerToughnessCounters=0;
		this.damageTaken=0;
		for(int i=this.Equipments.size()-1;i>=0;i--){
			triggeredEffects.addAll(this.Equipments.get(i).UnEquip(players));
		}
		this.blocking=false;
		return triggeredEffects;
	}
	
	public int paintFullVersionAt(Graphics g, int x, int y){
		int cy=super.paintFullVersionAt(g, x, y);
		
		if(this.faceDown){
			if(!this.IsMorphed()){
				return cy;
			}
			Painting.FillRectWithBorder(g, x+FULLWIDTH-32, y+FULLHEIGHT-14, 32, 14, Color.white, Color.black);
			g.setColor(this.GetPower()==2&&(this.GetToughness()-this.damageTaken)==2?Color.black:Color.blue);
			g.drawString(""+this.GetPower()+"/"+(this.GetToughness()-this.damageTaken), x+FULLWIDTH-30, y+FULLHEIGHT-2);
		}else{
			Painting.FillRectWithBorder(g, x+FULLWIDTH-32, y+FULLHEIGHT-14, 32, 14, Color.white, Color.black);
			g.setColor(this.GetPower()==this.basepower&&(this.GetToughness()-this.damageTaken)==this.basetoughness?Color.black:Color.blue);
			g.drawString(""+this.GetPower()+"/"+(this.GetToughness()-this.damageTaken), x+FULLWIDTH-30, y+FULLHEIGHT-2);
		}
			
		if(this.powerToughnessCounters!=0){
			Painting.FillOvalWithBorder(g, x+FULLWIDTH-16, y+FULLHEIGHT-30, 14, 14, Color.yellow, Color.black);
			g.setColor(Color.black);
			g.drawString(""+this.powerToughnessCounters, x+FULLWIDTH-13, y+FULLHEIGHT-18);
		}
		
		return cy;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		if(this.faceDown){
			if(!this.IsMorphed()){
				return;
			}
			Painting.FillRectWithBorder(g, this.maxX-32, this.maxY-14, 32, 14, Color.white, Color.black);
			g.setColor(this.GetPower()==2&&(this.GetToughness()-this.damageTaken)==2?Color.black:Color.blue);
			g.drawString(""+this.GetPower()+"/"+(this.GetToughness()-this.damageTaken), this.maxX-30, this.maxY-2);
		}else{
			Painting.FillRectWithBorder(g, this.maxX-32, this.maxY-14, 32, 14, Color.white, Color.black);
			g.setColor(this.GetPower()==this.basepower&&(this.GetToughness()-this.damageTaken)==this.basetoughness?Color.black:Color.blue);
			g.drawString(""+this.GetPower()+"/"+(this.GetToughness()-this.damageTaken), this.maxX-30, this.maxY-2);
		}
		
		if(this.powerToughnessCounters!=0){
			Painting.FillOvalWithBorder(g, this.maxX-16, this.maxY-30, 14, 14, Color.yellow, Color.black);
			g.setColor(Color.black);
			g.drawString(""+this.powerToughnessCounters, this.maxX-13, this.maxY-18);
		}
		
		if(this.TargetedPlayer!=null){
			g.setColor(Color.red);
			if(this.blockers.size()==0){
				g.drawLine(this.minX+this.GetWidth()/2, this.minY+this.GetHeight()/2, this.TargetedPlayer.GetTargetEndpointX(), this.TargetedPlayer.GetTargetEndpointY());
			}else{
				for(int i=0;i<this.blockers.size();i++){
					Creature cblocker=this.blockers.elementAt(i);
					g.drawLine(this.minX+this.GetWidth()/2, this.minY+this.GetHeight()/2, cblocker.GetTargetEndpointX(), cblocker.GetTargetEndpointY());
				}
			}
		}		
	}
}
