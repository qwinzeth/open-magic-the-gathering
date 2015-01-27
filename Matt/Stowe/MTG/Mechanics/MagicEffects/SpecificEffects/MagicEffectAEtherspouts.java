package Matt.Stowe.MTG.Mechanics.MagicEffects.SpecificEffects;

import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class MagicEffectAEtherspouts extends MagicEffect{
	private MagicEffectAEtherspouts(DeepCopyInfo dcinfo){
		super(dcinfo);
	}

	public MagicEffectAEtherspouts(Player controller){
		super(controller, new TargetInfo(), Duration.NA);
	}

	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<Creature> allAttackers=new ArrayList<Creature>();
		for(int i=0;i<players.size();i++){
			Creature[] attackers=players.get(i).GetAttackingCreatures();
			for(int j=0;j<attackers.length;j++){
				allAttackers.add(attackers[j]);
			}
		}
		
		for(int i=0;i<players.size();i++){
			Player cplayer=players.get(i);
			ArrayList<CardBase> creaturesOwned=new ArrayList<CardBase>();
			for(int j=0;j<allAttackers.size();j++){
				Creature ccreature=allAttackers.get(j);
				if(ccreature.GetOwnerIndex()==i){
					creaturesOwned.add(ccreature);
				}
			}
			
			if(creaturesOwned.size()>0){
				cplayer.PlaceCardsOnTopOrBottomOfLibrary(creaturesOwned, players);
			}
		}
		
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectAEtherspouts(this.GetDeepCopyInfo());
	}

	public String GetRulesText(){
		return "For each attacking creature, its owner puts it on the top or bottom of his or her library.";
	}

}