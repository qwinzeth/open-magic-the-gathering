package Matt.Stowe.MTG.Transferrable;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public class ReadOnlyPlayer implements IPlayer, ITargetable{
	private String name;
	private int endX, endY;
	private int minX, minY, maxX, maxY;
	public int GetMinX(){return this.minX;}
	public int GetMinY(){return this.minY;}
	public int GetWidth(){return this.maxX-this.minX;}
	public int GetHeight(){return this.maxY-this.minY;}
	public boolean IsPlayingAreaClicked(int mx, int my){
		return mx>=this.minX&&mx<=this.maxX&&my>=this.minY&&my<=this.maxY;
	}
	
	public int DeckSize;
	public CardBase[] Hand;
	public CardBase[] Field;
	public CardBase[] Graveyard;
	public CardBase[] Exile;
	public ArrayList<CardBase> GetAllCards(){
		ArrayList<CardBase> allcards=new ArrayList<CardBase>();
		for(int i=0;i<this.Field.length;i++){
			allcards.add(this.Field[i]);
		}
		for(int i=0;i<this.Hand.length;i++){
			allcards.add(this.Hand[i]);
		}
		for(int i=0;i<this.Graveyard.length;i++){
			allcards.add(this.Graveyard[i]);
		}
		for(int i=0;i<this.Exile.length;i++){
			allcards.add(this.Exile[i]);
		}
		return allcards;
	}

	public ReadOnlyPlayer(String name, int endX, int endY, int decksize, CardBase[] hand, CardBase[] field, CardBase[] graveyard, CardBase[] exile,
	int minX, int minY, int maxX, int maxY){
		this.name=name;
		this.endX=endX;
		this.endY=endY;
		this.DeckSize=decksize;
		this.Hand=hand;
		this.Field=field;
		this.Graveyard=graveyard;
		this.Exile=exile;
		this.minX=minX;
		this.minY=minY;
		this.maxX=maxX;
		this.maxY=maxY;
	}
	
	public ITargetable GetITargetable(){return this;}
	public String GetName(){return this.name;}
	public int GetTargetTypeFlags(){
		return TargetInfo.TARGET_TYPE_FLAG_PLAYER;
	}
	public int GetColorFlags(){return ManaCost.COLOR_FLAG_COLORLESS;}
	public int GetTargetEndpointX(){return this.endX;}
	public int GetTargetEndpointY(){return this.endY;}
	public int MarkDamage(int damage, int damageTypeFlags, CardBase source){return 0;}
	public void AddDamagePreventor(DamagePreventor dmgprev){}
	public void RemoveDamagePreventor(DamagePreventor dmgprev){}
	private ArrayList<TargetInfo> cantBeTargetedBy;
	public void AddCantBeTargetedBy(TargetInfo info){}
	public void RemoveCantBeTargetedBy(TargetInfo info){}
	//TODO: Real check
	public boolean CanBeTargetedBy(ITargetable source){return true;}
	public void UndoAtEndOfTurn(MagicEffect effect){}
	public void UndoWhenControlIsLost(MagicEffect effect){}
	public void UndoWhenLeavesBattlefield(MagicEffect effect){}
}
