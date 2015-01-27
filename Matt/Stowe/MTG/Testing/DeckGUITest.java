package Matt.Stowe.MTG.Testing;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Cards.Sets.*;
import Matt.Stowe.MTG.*;
import Matt.Stowe.Common.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class DeckGUITest extends AnimatedDoubleBufferedWindow{
	private Deck deck;
	private Vector<CardBase> drawnCards;
	private int nextX;
	private int nextY;

	public DeckGUITest(){
		this.nextX=100;
		this.nextY=210;
		Vector<CardBase> cards=new Vector<CardBase>();
		this.drawnCards=new Vector<CardBase>();
		cards.add(BasicLand.GenerateForest(null));
		cards.add(BasicLand.GenerateMountain(null));
		cards.add(BasicLand.GenerateIsland(null));
		cards.add(BasicLand.GeneratePlains(null));
		cards.add(BasicLand.GenerateSwamp(null));
		cards.add(Theros.SilentArtisan(null));
		cards.add(Theros.TravelingPhilosopher(null));
		cards.add(Theros.YokedOx(null));
		cards.add(Creature.NewCreature(null, "Weird", "Not real", new ManaCost(new int[]{
			ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_GREEN,
			ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_BLUE,
			ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK,
			ManaCost.COLOR_FLAG_RED,ManaCost.COLOR_FLAG_GREEN,
			ManaCost.COLOR_FLAG_BLACK
		}), 10, 20));
		cards.add(Creature.NewCreature(null, "Bronze Sable", "derp", new ManaCost(new int[]{ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS, ManaCost.COLOR_FLAG_COLORLESS}), 2, 1));

		this.deck=new Deck(cards, 100, 100);
		this.deck.Shuffle();
		
		super.InitializeWindow("MTG", 800, 600);
	}
	
	public static void main(String args[]){
		DeckGUITest m=new DeckGUITest();
	}

	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseClicked(MouseEvent me){
		if(this.deck.IsClicked(me.getX(), me.getY())){
			CardBase drawnCard=this.deck.DrawCard();
			if(drawnCard==null){
				synchronized(this.drawnCards){
				for(int i=this.drawnCards.size()-1;i>=0;i--){
					this.deck.PlaceCardOnBottom(this.drawnCards.remove(i));
				}
				}
				this.deck.Shuffle();
				this.nextX=100;
				this.nextY=210;
				return;
			}
			drawnCard.SetAnimationDestination(this.nextX, this.nextY, OptionsScreen.AnimationSpeed);
			this.nextX+=CardBase.WIDTH+5;
			if(this.nextX>600){
				this.nextX=100;
				this.nextY+=CardBase.HEIGHT+5;
			}
			this.drawnCards.add(drawnCard);
		}
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void keyPressed(KeyEvent ke){}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}

	public void animate(long elapsedMillis){
		synchronized(this.drawnCards){
		for(int i=0;i<this.drawnCards.size();i++)
			this.drawnCards.elementAt(i).StepTowardsOnRails();
		}
	}
	
	public void paintDoubleBuffered(Graphics g){
		this.deck.paint(g);
		for(int i=0;i<this.drawnCards.size();i++)
			this.drawnCards.elementAt(i).paint(g);
	}
}