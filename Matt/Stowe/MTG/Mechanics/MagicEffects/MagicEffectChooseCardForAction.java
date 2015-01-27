package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectChooseCardForAction extends MagicEffect{
	private enum ACTION{
		PLACE_ON_FIELD,
		PLACE_ON_FIELD_TAPPED,
		PLACE_ON_FIELD_TAPPED_AND_ATTACKING,
		PLACE_IN_HAND,
		ATTACH_TO_PERMANENT,
		DISCARD
	}
	
	private TargetInfo cardRequirements;
	private ACTION action;

	private MagicEffectChooseCardForAction(DeepCopyInfo dcinfo, TargetInfo cardReqs, ACTION action){
		super(dcinfo);
		this.cardRequirements=cardReqs;
		this.action=action;
	}
	
	private MagicEffectChooseCardForAction(Player controller, TargetInfo cardReqs, TargetInfo targetReqsToAttachCardTo, ACTION action){
		super(controller, targetReqsToAttachCardTo, Duration.NA);
		this.cardRequirements=cardReqs;
		this.action=action;
	}
	
	public static MagicEffectChooseCardForAction Attach(Player controller, TargetInfo cardReqs, TargetInfo targetReqsToAttachCardTo){
		return new MagicEffectChooseCardForAction(controller, cardReqs, targetReqsToAttachCardTo, ACTION.ATTACH_TO_PERMANENT);
	}
	
	public static MagicEffectChooseCardForAction PutInHand(Player controller, TargetInfo cardReqs){
		return new MagicEffectChooseCardForAction(controller, cardReqs, new TargetInfo(), ACTION.PLACE_IN_HAND);
	}

	public static MagicEffectChooseCardForAction PutOnField(Player controller, TargetInfo cardReqs){
		return new MagicEffectChooseCardForAction(controller, cardReqs, new TargetInfo(), ACTION.PLACE_ON_FIELD);
	}

	public static MagicEffectChooseCardForAction PutOnFieldTapped(Player controller, TargetInfo cardReqs){
		return new MagicEffectChooseCardForAction(controller, cardReqs, new TargetInfo(), ACTION.PLACE_ON_FIELD_TAPPED);
	}	
	
	public static MagicEffectChooseCardForAction PutOnFieldTappedAndAttacking(Player controller, TargetInfo cardReqs){
		return new MagicEffectChooseCardForAction(controller, cardReqs, new TargetInfo(), ACTION.PLACE_ON_FIELD_TAPPED_AND_ATTACKING);
	}

	public static MagicEffectChooseCardForAction Discard(Player controller, TargetInfo cardReqs){
		return new MagicEffectChooseCardForAction(controller, cardReqs, new TargetInfo(), ACTION.DISCARD);
	}

	public void SetX(int x){
		this.cardRequirements.SetX(x);
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		ArrayList<MagicEffect[]> triggeredEffects=new ArrayList<MagicEffect[]>();
		CardBase[] chosenCards=this.controller.ChooseCards(this.cardRequirements);
		if(chosenCards==null)
			return triggeredEffects;

		switch(this.action){
		case ATTACH_TO_PERMANENT:
			for(int i=0;i<chosenCards.length;i++){
				MagicEffectPlayEnchantmentOnPermanent enchant=new MagicEffectPlayEnchantmentOnPermanent(this.controller, (Enchantment)chosenCards[i], 0);
				enchant.TargetData.SetLockedTarget(0, this.TargetData.GetTarget(i));
				triggeredEffects.addAll(enchant.Resolve(players));
			}
		break;
		case PLACE_IN_HAND:
			for(int i=0;i<chosenCards.length;i++){
				this.controller.PlaceCardInHand(chosenCards[i]);
			}
		break;
		case PLACE_ON_FIELD:
			for(int i=0;i<chosenCards.length;i++){
				MagicEffect playit=new MagicEffectPlayPermanent(this.controller, chosenCards[i]);
				triggeredEffects.addAll(playit.Resolve(players));
			}
		break;
		case PLACE_ON_FIELD_TAPPED:
			for(int i=0;i<chosenCards.length;i++){
				MagicEffect playit=new MagicEffectPlayPermanent(this.controller, chosenCards[i]);
				MagicEffect comesInTapped=MagicEffectTapTargetCard.Create(this.controller, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
				comesInTapped.TargetData.SetLockedTarget(0, chosenCards[i]);
				chosenCards[i].AddEntersTheBattlefieldAdjustments(comesInTapped);
				triggeredEffects.addAll(playit.Resolve(players));
				chosenCards[i].RemoveEntersTheBattlefieldAdjustments(comesInTapped);
			}
		break;
		case PLACE_ON_FIELD_TAPPED_AND_ATTACKING:
			for(int i=0;i<chosenCards.length;i++){
				MagicEffect playit=new MagicEffectPlayPermanent(this.controller, chosenCards[i]);
				MagicEffect comesInTapped=MagicEffectTapTargetCard.Create(this.controller, 1, 1, TargetInfo.TARGET_TYPE_FLAG_CREATURE);
				comesInTapped.TargetData.SetLockedTarget(0, chosenCards[i]);
				chosenCards[i].AddEntersTheBattlefieldAdjustments(comesInTapped);
				triggeredEffects.addAll(playit.Resolve(players));
				chosenCards[i].RemoveEntersTheBattlefieldAdjustments(comesInTapped);
				
				triggeredEffects.addAll(this.controller.AttackOpponentWithCreature(players, (Creature)chosenCards[i]));
			}
		break;
		case DISCARD:
			for(int i=0;i<chosenCards.length;i++){
				MagicEffect discardit=new MagicEffectDiscardCard(this.controller, chosenCards[i]);
				triggeredEffects.addAll(discardit.Resolve(players));
			}
		break;
		default:
			System.out.println("Unhandled action type: "+this.action);
		break;
		}
		
		return triggeredEffects;
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectChooseCardForAction(this.GetDeepCopyInfo(), this.cardRequirements, this.action);
	}

	public String GetRulesText(){
		String actionstring="DO SOMETHING WITH IT?";
		switch(this.action){
		case ATTACH_TO_PERMANENT:
			actionstring="attach it to "+this.TargetData.GetCSV();
		break;
		case PLACE_IN_HAND:
			actionstring="put it into your hand";
		break;
		case PLACE_ON_FIELD:
			actionstring="put it onto the battlefield";
		break;
		case PLACE_ON_FIELD_TAPPED:
			actionstring="put it onto the battlefield tapped";
		break;
		case PLACE_ON_FIELD_TAPPED_AND_ATTACKING:
			actionstring="put it onto the battlefield tapped and attacking";
		break;
		case DISCARD:
			actionstring="discard it";
		break;
		}
		
		String choiceCountString=""+this.cardRequirements.GetRequiredTargetCount();
		if(this.cardRequirements.GetMaxTargetCount()!=this.cardRequirements.GetRequiredTargetCount()){
			choiceCountString+="-"+this.cardRequirements.GetMaxTargetCount();
		}
		
		String baserules="Choose "+this.cardRequirements.GetCSV().replace("1 target", choiceCountString)+" and "+actionstring;
		if(this.cardRequirements.IsValidZone(ZoneOptions.LIBRARY)){
			baserules+=". If you search your library this way, shuffle it.";
		}
		return baserules;
	}
}
