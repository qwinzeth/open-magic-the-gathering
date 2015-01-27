package Matt.Stowe.MTG.Testing;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Cards.Sets.*;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.BoosterDraft.*;
import Matt.Stowe.Common.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class BoosterDraftDeckbuildingTest extends AnimatedDoubleBufferedWindow{
	private BoosterScreen boosterScreen;

	public BoosterDraftDeckbuildingTest(){
		Vector<Player> players=new Vector<Player>();
		players.add(new Player(0, OptionsScreen.CONTROLTYPE_LOCALHUMAN, "H1", true, 30, this.getHeight()/2+15, this.getWidth()-60, this.getHeight()-20));
		players.add(new Player(1, OptionsScreen.CONTROLTYPE_LOCALHUMAN, "H2", true, 30, this.getHeight()/2+15, this.getWidth()-60, this.getHeight()-20));
		this.boosterScreen=new BoosterScreen(players);
		this.boosterScreen.StartDraft();
	
		super.InitializeWindow("MTG Booster Draft Deckbuilding Test", 800, 600);
		
		for(int r=0;r<3;r++){
			for(int i=0;i<15;i++){
				for(int j=0;j<players.size();j++){
					this.boosterScreen.mouseClicked(new MouseEvent(this, 0, 0, 0, 51+((i%8)*(CardBase.WIDTH+2)), 101+(i/8)*(CardBase.HEIGHT+2), 1, false));
				}
			}
		}
	}
	
	public static void main(String args[]){
		BoosterDraftDeckbuildingTest m=new BoosterDraftDeckbuildingTest();
	}

	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseClicked(MouseEvent me){
		this.boosterScreen.mouseClicked(me);
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void keyPressed(KeyEvent ke){}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}

	public void animate(long elapsedMillis){
		
	}
	
	public void paintDoubleBuffered(Graphics g){
		if(this.boosterScreen.IsCompleted()){
			g.drawString("Draft complete, ready to battle.", 100, 100);
			return;
		}
		this.boosterScreen.paint(g);
	}
}