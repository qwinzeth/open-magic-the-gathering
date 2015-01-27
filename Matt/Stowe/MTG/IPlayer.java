package Matt.Stowe.MTG;
import Matt.Stowe.MTG.Cards.CardBase;
import Matt.Stowe.MTG.Mechanics.ITargetable;

import java.util.ArrayList;

public interface IPlayer{
	ArrayList<CardBase> GetAllCards();
	ITargetable GetITargetable();
}
