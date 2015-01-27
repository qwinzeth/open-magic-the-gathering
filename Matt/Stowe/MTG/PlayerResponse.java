package Matt.Stowe.MTG;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.MagicStackElement;
import Matt.Stowe.MTG.Mechanics.MagicEffects.MagicEffect;

public class PlayerResponse{
	public CardBase CardToPlay;
	public ManaCost ManaCosts;
	public MagicEffect[] Costs;
	public MagicEffect[] Effects;
	
	public PlayerResponse(CardBase cardToPlay, ManaCost manaCosts, MagicEffect[] costs, MagicEffect[] effects){
		this.CardToPlay=cardToPlay;
		this.ManaCosts=manaCosts;
		this.Costs=costs;
		this.Effects=effects;
	}
	
	public MagicStackElement GetMagicStackElement(){
		return new MagicStackElement(this.Effects, this.CardToPlay);
	}
}
