package Matt.Stowe.MTG.Testing;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Cards.Sets.*;
import Matt.Stowe.Common.*;

import java.awt.*;
import java.awt.event.*;

public class CardGUITest extends AnimatedDoubleBufferedWindow{
	private CardBase[] cards;

	public CardGUITest(){
		cards=new CardBase[7];
		cards[0]=BasicLand.GenerateForest(null);
		cards[0].MoveTo(100,300);
		cards[1]=BasicLand.GenerateMountain(null);
		cards[1].MoveTo(170,300);
		cards[2]=BasicLand.GenerateIsland(null);
		cards[2].MoveTo(240,300);
		cards[3]=BasicLand.GeneratePlains(null);
		cards[3].MoveTo(310,300);
		cards[4]=BasicLand.GenerateSwamp(null);
		cards[4].MoveTo(380,300);
		
		cards[5]=Theros.TritonShorethief(null);
		cards[5].MoveTo(100, 410);
		cards[6]=Creature.NewArtifactCreature(null, "Weird", "Not real", new ManaCost(new int[]{
			ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_GREEN,
			ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_BLUE,
			ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK,
			ManaCost.COLOR_FLAG_RED,ManaCost.COLOR_FLAG_GREEN,
			ManaCost.COLOR_FLAG_BLACK
		}), 10, 20);
		cards[6].MoveTo(170, 410);
		
		super.InitializeWindow("MTG", 800, 600);
	}
	
	public static void main(String args[]){
		CardGUITest m=new CardGUITest();
	}

	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseClicked(MouseEvent me){
		for(int i=0;i<this.cards.length;i++)
			if(this.cards[i].IsClicked(me.getX(), me.getY()))
				this.cards[i].Tap();
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void keyPressed(KeyEvent ke){}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}

	public void animate(long elapsedMillis){}
	
	public void paintDoubleBuffered(Graphics g){
		for(int i=0;i<this.cards.length;i++)
			this.cards[i].paint(g);
	}
}