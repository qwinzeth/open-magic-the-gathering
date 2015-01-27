package Matt.Stowe.MTG.Mechanics;

import Matt.Stowe.MTG.Mechanics.MagicEffects.MagicEffect;
import Matt.Stowe.MTG.Cards.CardBase;

public class MagicStackElement{
	public MagicEffect[] Effects;
	public CardBase CardToPlay;
	
	public MagicStackElement(MagicEffect[] effects, CardBase cardToPlay){
		this.Effects=effects;
		this.CardToPlay=cardToPlay;
	}
}
