package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectDiscardCard extends MagicEffect{
	private CardBase cardToDiscard;
	
	private MagicEffectDiscardCard(DeepCopyInfo dcinfo, CardBase cardToDiscard){
		super(dcinfo);
		this.cardToDiscard=cardToDiscard;
	}
	
	public MagicEffectDiscardCard(Player controller, CardBase cardToDiscard){
		super(controller, new TargetInfo(), Duration.NA);
		this.cardToDiscard=cardToDiscard;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		return this.controller.PlaceCardInGraveyard(this.cardToDiscard, players);
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectDiscardCard(this.GetDeepCopyInfo(), this.cardToDiscard);
	}

	public String GetRulesText(){
		return "Discard "+cardToDiscard.GetName()+".";
	}
}