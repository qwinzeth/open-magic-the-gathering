package Matt.Stowe.MTG.BoosterDraft;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Cards.Sets.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class BoosterScreen{
	private enum State{
		DRAFTING,
		DRAFTVIEWCARD,
		DECKBUILDING,
		COMPLETE
	};
	
	private Vector<Player> players;
	private ArrayList<BoosterPack> boosters;
	private Player currentPlayer;
	private BoosterPack currentBooster;
	private ArrayList<CardBase> currentSideboard;
	private ArrayList<GUIClickableText> currentSideboardNames;
	private ArrayList<CardBase> currentDeck;
	private ArrayList<GUIClickableText> currentDeckNames;
	private DeckBuilderCard[] basicLands;
	private GUIButton btnAccept;
	private GUIButton btnViewMode;
	private CardBase cardPreview;
	
	private int round;
	private int rotation;
	private State state;
	public boolean IsCompleted(){return this.state==State.COMPLETE;}

	public BoosterScreen(Vector<Player> players){
		this.players=players;
		this.state=State.DRAFTING;
		this.basicLands=new DeckBuilderCard[5];
		this.basicLands[0]=new DeckBuilderCard(null, "Mountain", 0, 100, 270);
		this.basicLands[1]=new DeckBuilderCard(null, "Forest", 0, 230, 270);
		this.basicLands[2]=new DeckBuilderCard(null, "Island", 0, 360, 270);
		this.basicLands[3]=new DeckBuilderCard(null, "Plains", 0, 490, 270);
		this.basicLands[4]=new DeckBuilderCard(null, "Swamp", 0, 620, 270);
		this.btnAccept=new GUIButton(740, 270, 50, 16, "Accept");
		this.btnViewMode=new GUIButton(200, 52, 40, 16, "View");
	}
	
	public void StartDraft(){
		this.state=State.DRAFTING;
		this.boosters=new ArrayList<BoosterPack>();
		for(int i=0;i<this.players.size();i++){
			this.players.get(i).SetDeck(new Vector<CardBase>());
			for(int j=0;j<3;j++){
				boosters.add(new BoosterPack(50, 100, KhansOfTarkir.MythicRares, KhansOfTarkir.Rares, KhansOfTarkir.Uncommons, KhansOfTarkir.Commons));
			}
		}
		
		this.round=0;
		this.rotation=0;
		this.currentPlayer=this.players.get(0);
		this.updateCurrentBooster();
	}

	private void updateCurrentBooster(){
		if(this.round>=3){
			this.state=State.DECKBUILDING;
			this.updateDeckbuildingPlayer();
			return;
		}
		
		int currentPackIndex=0;
		if(this.round==1){
			currentPackIndex=(this.players.size()*100-this.rotation+this.currentPlayer.GetIndex())%this.players.size();
		}else{
			currentPackIndex=(this.rotation+this.currentPlayer.GetIndex())%this.players.size();
		}
		this.currentBooster=this.boosters.get(this.round*this.players.size()+currentPackIndex);
	}
	
	private void updateDeckbuildingPlayer(){
		this.currentSideboard=new ArrayList<CardBase>();
		this.currentSideboardNames=new ArrayList<GUIClickableText>();
		this.currentDeck=this.currentPlayer.GetDeck().GetCards();
		this.currentDeckNames=new ArrayList<GUIClickableText>();
		this.updateDeckbuildingNames();
	}
	
	private void updateDeckbuildingNames(){
		synchronized(this.currentDeck){
			this.currentDeckNames.clear();
		}
		for(int i=0;i<this.currentDeck.size();i++){
			this.currentDeckNames.add(new GUIClickableText(this.currentDeck.get(i).GetName(), 50+((i%3)*200), 286+i/3*20));
		}
		synchronized(this.currentSideboard){
			this.currentSideboardNames.clear();
		}
		for(int i=0;i<this.currentSideboard.size();i++){
			this.currentSideboardNames.add(new GUIClickableText(this.currentSideboard.get(i).GetName(), 50+((i%3)*200), 70+i/3*20));
		}
	}
	
	private void boosterCardChosen(CardBase chosenCard){
		this.currentPlayer.GetDeck().PlaceCardOnTop(Deck.GetCardByName(chosenCard.GetName(), this.currentPlayer));
		this.currentBooster.RemoveCard(chosenCard);
		int cplayerindex=this.currentPlayer.GetIndex()+1;
		if(cplayerindex>=this.players.size()){
			this.rotation++;
			cplayerindex=0;
		}
		this.currentPlayer=this.players.get(cplayerindex);
		this.updateCurrentBooster();
		if(!this.currentBooster.HasCards()){
			this.round++;
			this.rotation=0;
			this.currentPlayer=this.players.get(0);
			this.updateCurrentBooster();
		}
		if(this.state==State.DRAFTING){
			if(!this.currentPlayer.IsLocalHuman()){
				this.boosterCardChosen(this.currentBooster.GetCardByIndex((int)(Math.random()*this.currentBooster.CardsLeft())));
			}
		}
	}
	
	public void mousePressed(MouseEvent me){
		switch(state){
		case DRAFTING:
		case DRAFTVIEWCARD:
			this.btnViewMode.handleMousePress(me.getX(), me.getY());
		break;
		case DECKBUILDING:
			for(int i=0;this.cardPreview==null&&i<this.currentDeckNames.size();i++){
				if(this.currentDeckNames.get(i).IsClicked(me.getX(), me.getY())){
					this.cardPreview=this.currentDeck.get(i);
				}
			}
			for(int i=0;this.cardPreview==null&&i<this.currentSideboardNames.size();i++){
				if(this.currentSideboardNames.get(i).IsClicked(me.getX(), me.getY())){
					this.cardPreview=this.currentSideboard.get(i);
				}
			}
			for(int i=0;i<this.basicLands.length;i++){
				this.basicLands[i].mousePressed(me);
			}
			this.btnAccept.handleMousePress(me.getX(), me.getY());
		break;
		}
	}
	public void mouseReleased(MouseEvent me){
		switch(state){
		case DRAFTING:
		case DRAFTVIEWCARD:
			this.btnViewMode.handleMouseRelease(me.getX(), me.getY());
		break;
		case DECKBUILDING:
			this.cardPreview=null;
			for(int i=0;i<this.basicLands.length;i++){
				this.basicLands[i].mouseReleased(me);
			}
			this.btnAccept.handleMouseRelease(me.getX(), me.getY());
		break;
		}
	}
	public void mouseClicked(MouseEvent me){
		switch(state){
		case DRAFTING:
			CardBase clickedCard=this.currentBooster.GetClickedCard(me.getX(), me.getY());
			if(clickedCard!=null){
				this.boosterCardChosen(clickedCard);
			}else if(this.btnViewMode.IsClicked(me.getX(), me.getY())){
				this.state=State.DRAFTVIEWCARD;
			}
		break;
		case DRAFTVIEWCARD:
			clickedCard=this.currentBooster.GetClickedCard(me.getX(), me.getY());
			if(this.cardPreview==null){
				this.cardPreview=clickedCard;
			}else{
				this.cardPreview=null;
			}
			
			if(this.btnViewMode.IsClicked(me.getX(), me.getY())){
				this.state=State.DRAFTING;
			}
		break;
		case DECKBUILDING:
			for(int i=0;i<this.currentSideboardNames.size();i++){
				GUIClickableText ccardtext=this.currentSideboardNames.get(i);				
				if(ccardtext.IsClicked(me.getX(), me.getY())){
					CardBase ccard=this.currentSideboard.get(i);
					synchronized(this.currentSideboardNames){
						this.currentSideboard.remove(ccard);
					}
					this.currentDeck.add(ccard);
					this.updateDeckbuildingNames();
					return;
				}
			}

			for(int i=0;i<this.currentDeckNames.size();i++){
				GUIClickableText ccardtext=this.currentDeckNames.get(i);				
				if(ccardtext.IsClicked(me.getX(), me.getY())){
					CardBase ccard=this.currentDeck.get(i);
					synchronized(this.currentDeckNames){
						this.currentDeck.remove(ccard);
					}
					this.currentSideboard.add(ccard);
					this.updateDeckbuildingNames();
					return;
				}
			}

			if(this.btnAccept.IsClicked(me.getX(), me.getY())){
				for(int i=0;i<this.basicLands[0].GetCount();i++){
					this.currentDeck.add(Deck.GetCardByName("Mountain", this.currentPlayer));
				}
				for(int i=0;i<this.basicLands[1].GetCount();i++){
					this.currentDeck.add(Deck.GetCardByName("Forest", this.currentPlayer));
				}
				for(int i=0;i<this.basicLands[2].GetCount();i++){
					this.currentDeck.add(Deck.GetCardByName("Island", this.currentPlayer));
				}
				for(int i=0;i<this.basicLands[3].GetCount();i++){
					this.currentDeck.add(Deck.GetCardByName("Plains", this.currentPlayer));
				}
				for(int i=0;i<this.basicLands[4].GetCount();i++){
					this.currentDeck.add(Deck.GetCardByName("Swamp", this.currentPlayer));
				}
				
				this.basicLands[0].Reset();
				this.basicLands[1].Reset();
				this.basicLands[2].Reset();
				this.basicLands[3].Reset();
				this.basicLands[4].Reset();
				
				Vector<CardBase> playerdeck=new Vector<CardBase>();
				playerdeck.addAll(this.currentDeck);
				this.currentPlayer.SetDeck(playerdeck);
				try{
					BufferedWriter deckWriter=new BufferedWriter(new FileWriter("MTGDecks/draft"+this.currentPlayer.GetIndex()));
					for(int i=0;i<this.currentDeck.size();i++){
						String ccard="1\t"+this.currentDeck.get(i).GetName();
						deckWriter.write(ccard, 0, ccard.length());
						deckWriter.newLine();
					}
					deckWriter.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
					System.out.println("Unable to save draft deck.");
				}
				int cplayerindex=this.currentPlayer.GetIndex()+1;
				if(cplayerindex>=this.players.size()){
					this.state=State.COMPLETE;
				}else{
					this.currentPlayer=this.players.get(cplayerindex);
					this.updateDeckbuildingPlayer();
				}				
				
				return;
			}
			
			for(int i=0;i<this.basicLands.length;i++){
				this.basicLands[i].mouseClicked(me);
			}
		break;
		}
	}

	public void paint(Graphics g){
		switch(this.state){
		case DRAFTING:
		case DRAFTVIEWCARD:
			this.currentBooster.paint(g);
			
			this.btnViewMode.paint(g);
			
			g.setColor(Color.black);
			g.drawString(this.currentPlayer.GetName(), 50, 54);
			g.drawString("Pack "+(this.round+1)+" Pick "+(this.rotation+1), 50, 68);
			if(this.state==State.DRAFTING){
				g.drawString("Selecting", 250, 68);
			}else{
				g.drawString("Viewing", 250, 68);
			}
		break;
		case DECKBUILDING:
			for(int i=0;i<this.basicLands.length;i++){
				this.basicLands[i].paint(g);
			}
			this.btnAccept.paint(g);

			g.setColor(Color.black);
			g.drawString(this.currentPlayer.GetName(), 50, 54);
			g.drawString("Sideboard ("+this.currentSideboard.size()+"):", 40, 68);
			g.drawString("Deck ("+(this.currentDeck.size()+this.basicLands[0].GetCount()+this.basicLands[1].GetCount()+this.basicLands[2].GetCount()+this.basicLands[3].GetCount()+this.basicLands[4].GetCount())+"):", 40, 286);

			synchronized(this.currentSideboard){
				for(int i=0;i<this.currentSideboardNames.size();i++){
					this.currentSideboardNames.get(i).paint(g);
				}
			}

			synchronized(this.currentDeck){
				for(int i=0;i<this.currentDeckNames.size();i++){
					this.currentDeckNames.get(i).paint(g);
				}
			}
		break;
		}
		if(this.cardPreview!=null){
			this.cardPreview.paintFullVersionAt(g, 200, 100);
		}
	}
}
