package Matt.Stowe.MTG.BoosterDraft;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Cards.Sets.*;

import java.util.ArrayList;
import java.awt.*;

public class BoosterPack{
	private int x,y;	
	private ArrayList<CardBase> cards;
	public int CardsLeft(){return this.cards.size();}
	
	public BoosterPack(int x, int y, String[] MythicRares, String[] Rares, String[] Uncommons, String[] Commons){
		this.x=x;
		this.y=y;
		this.cards=new ArrayList<CardBase>();
		Player fakeplayer=new Player(0, OptionsScreen.CONTROLTYPE_LOCALHUMAN, "you", true, 0, 0, 50, 50);
		if(Math.random()*8<1){
			CardBase mythicRare=null;
			while(mythicRare==null){
				mythicRare=Deck.GetCardByName(MythicRares[(int)Math.floor(Math.random()*MythicRares.length)], fakeplayer);
			}
			this.cards.add(mythicRare);
		}else{
			CardBase rare=null;
			while(rare==null){
				rare=Deck.GetCardByName(Rares[(int)Math.floor(Math.random()*Rares.length)], fakeplayer);
			}
			this.cards.add(rare);
		}
		
		for(int i=0;i<3;i++){
			CardBase uncommon=null;
			while(uncommon==null){
				uncommon=Deck.GetCardByName(Uncommons[(int)Math.floor(Math.random()*Uncommons.length)], fakeplayer);
			}
			this.cards.add(uncommon);
		}
		
		for(int i=0;i<11;i++){
			CardBase common=null;
			while(common==null){
				common=Deck.GetCardByName(Commons[5+(int)Math.floor(Math.random()*(Commons.length-5))], fakeplayer);
			}
			this.cards.add(common);
		}

		/*CardBase land=null;
		while(land==null){
			land=Deck.GetCardByName(Commons[(int)Math.floor(Math.random()*5)], fakeplayer);
		}
		this.cards.add(land);*/

		for(int i=0;i<this.cards.size();i++){
			this.cards.get(i).MoveTo(this.x+((i%8)*(CardBase.WIDTH+2)), this.y+(i/8)*(CardBase.HEIGHT+2));
		}
	}
	
	public CardBase GetClickedCard(int mx, int my){
		for(int i=0;i<this.cards.size();i++){
			CardBase ccard=this.cards.get(i);
			if(ccard.IsClicked(mx, my)){
				return ccard;
			}
		}
		
		return null;
	}
	
	public CardBase GetCardByIndex(int index){
		return this.cards.get(index);
	}
	
	public void RemoveCard(CardBase card){
		synchronized(this.cards){
			this.cards.remove(card);
		}
	}
	
	public boolean HasCards(){
		return this.cards.size()>0;
	}
	
	public void paint(Graphics g){
		synchronized(this.cards){
			for(int i=0;i<this.cards.size();i++){
				this.cards.get(i).paint(g);
			}
		}
	}
}
