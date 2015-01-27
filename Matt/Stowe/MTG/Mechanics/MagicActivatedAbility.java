package Matt.Stowe.MTG.Mechanics;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

public class MagicActivatedAbility{
	private ZoneOptions zonesToActivateFrom;
	public ZoneOptions GetSourceZones(){return this.zonesToActivateFrom;}
	public void SetSourceZones(ZoneOptions newzones){this.zonesToActivateFrom=newzones;}

	private int maxTimesToUsePerTurn;
	public void SetMaxTimesToUsePerTurn(int newtimes){
		this.maxTimesToUsePerTurn=newtimes;
	}
	
	private int timesUsed;
	public boolean UsedMaxTimes(){
		return this.maxTimesToUsePerTurn>0&&this.timesUsed>=this.maxTimesToUsePerTurn;
	}

	public ManaCost ManaCosts;
	public MagicEffect[] Costs;
	public MagicEffect[] Effects;
	
	private boolean isSorcerySpeed;
	public boolean IsSorcerySpeed(){return this.isSorcerySpeed;}
	
	private MagicActivatedAbility(boolean isSorcerySpeed, ManaCost manacost, MagicEffect[] costs, MagicEffect[] effects, int maxTimesToUsePerTurn,
	ZoneOptions zonesToActivateFrom){
		this.isSorcerySpeed=isSorcerySpeed;
		this.ManaCosts=manacost;
		this.Costs=costs;
		this.Effects=effects;
		this.maxTimesToUsePerTurn=maxTimesToUsePerTurn;
		this.zonesToActivateFrom=zonesToActivateFrom;
	} 
	
	public MagicActivatedAbility(boolean isSorcerySpeed, ManaCost manacost, MagicEffect[] costs, MagicEffect[] effects){
		this.isSorcerySpeed=isSorcerySpeed;
		this.ManaCosts=manacost;
		this.Costs=costs;
		this.Effects=effects;
		this.maxTimesToUsePerTurn=-1;
		this.zonesToActivateFrom=new ZoneOptions(ZoneOptions.BATTLEFIELD);
	}
	
	public void SetSource(CardBase source){
		for(int i=0;i<this.Effects.length;i++){
			this.Effects[i].SetSource(source);
		}
	}

	public MagicActivatedAbility DeepCopy(){
		MagicEffect[] costsDeepCopy=null;
		if(this.Costs!=null){
			costsDeepCopy=new MagicEffect[this.Costs.length];
			for(int i=0;i<this.Costs.length;i++){
				costsDeepCopy[i]=this.Costs[i].DeepCopy();
			}
		}

		MagicEffect[] effectsDeepCopy=new MagicEffect[this.Effects.length];
		for(int i=0;i<this.Effects.length;i++){
			effectsDeepCopy[i]=this.Effects[i].DeepCopy();
		}

		return new MagicActivatedAbility(this.isSorcerySpeed, this.ManaCosts, costsDeepCopy, effectsDeepCopy, this.maxTimesToUsePerTurn,
		this.zonesToActivateFrom.DeepCopy());
	}
	
	public void UsedAbility(){
		this.timesUsed++;
	}
	
	public void ResetTimesUsed(){
		this.timesUsed=0;
	}
}
