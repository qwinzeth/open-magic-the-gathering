package Matt.Stowe.MTG;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.BoosterDraft.*;
import Matt.Stowe.MTG.Cards.*;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class Main extends AnimatedDoubleBufferedWindow{
	private Vector<Player> players;
	private Vector<Player> draftPlayers;
	private Battle battle;
	private ScreenType currentScreen;
	private OptionsScreen optionsScreen;
	private DeckBuilder deckBuildingScreen;
	private BoosterScreen boosterScreen;
	private GUIButton btnBattle;
	private GUIButton btnDeckbuilding;
	private GUIButton btnBooster;
	
	private enum ScreenType{
		MAIN,
		BATTLE,
		BOOSTER,
		OPTIONS,
		DECKBUILDER
	}

	public Main(){
		this.currentScreen=ScreenType.MAIN;
		this.players=new Vector<Player>();
		this.battle=new Battle(this.players);
		this.optionsScreen=new OptionsScreen(this.players);
		this.deckBuildingScreen=new DeckBuilder();
		this.draftPlayers=new Vector<Player>();
		this.boosterScreen=new BoosterScreen(this.draftPlayers);
		this.btnBattle=new GUIButton(50, 50, 50, 16, "Play");
		this.btnDeckbuilding=new GUIButton(50, 80, 100, 16, "Deckbuilding");
		this.btnBooster=new GUIButton(50, 110, 100, 16, "Booster Draft");
	
		super.InitializeWindow("MTG", 800, 600);

		this.draftPlayers.add(new Player(0, OptionsScreen.CONTROLTYPE_LOCALHUMAN, "H1", true, 30, this.getHeight()/2+15, this.getWidth()-60, this.getHeight()-20));
		this.draftPlayers.add(new Player(1, OptionsScreen.CONTROLTYPE_LOCALHUMAN, "H2", false, 30, 50, this.getWidth()-60, this.getHeight()/2+15));
		for(int i=2;i<8;i++){
			this.draftPlayers.add(new Player(i, OptionsScreen.CONTROLTYPE_LOCALAI, "AI"+(i+1), false, 30, 50, this.getWidth()-60, this.getHeight()/2+15));
		}
		
		this.players.add(this.draftPlayers.get(0));
		this.players.add(this.draftPlayers.get(1));
	}

	public static void main(String args[]){
		Main m=new Main();
	}
	
	public void animate(long elapsedMillis){
		switch(this.currentScreen){
		case BATTLE:
			this.battle.animate(elapsedMillis);
		break;
		}
	}

	public void mousePressed(MouseEvent me){
		switch(this.currentScreen){
		case MAIN:
			this.btnBattle.handleMousePress(me.getX(), me.getY());
			this.btnDeckbuilding.handleMousePress(me.getX(), me.getY());
			this.btnBooster.handleMousePress(me.getX(), me.getY());
		break;
		case DECKBUILDER:
			this.deckBuildingScreen.mousePressed(me);
		break;
		case OPTIONS:
			this.optionsScreen.mousePressed(me);
		break;
		case BOOSTER:
			this.boosterScreen.mousePressed(me);
		break;
		case BATTLE:
			this.battle.mousePressed(me);
		break;
		}
	}
	public void mouseReleased(MouseEvent me){
		switch(this.currentScreen){
		case MAIN:
			this.btnBattle.handleMouseRelease(me.getX(), me.getY());
			this.btnDeckbuilding.handleMouseRelease(me.getX(), me.getY());
			this.btnBooster.handleMouseRelease(me.getX(), me.getY());
		break;
		case DECKBUILDER:
			this.deckBuildingScreen.mouseReleased(me);
		break;
		case OPTIONS:
			this.optionsScreen.mouseReleased(me);
		break;
		case BOOSTER:
			this.boosterScreen.mouseReleased(me);
		break;
		case BATTLE:
			this.battle.mouseReleased(me);
		break;
		}
	}
	public void mouseClicked(MouseEvent me){
		switch(this.currentScreen){
		case MAIN:
			if(this.btnBattle.IsClicked(me.getX(), me.getY())){
				this.currentScreen=ScreenType.OPTIONS;
			}else if(this.btnDeckbuilding.IsClicked(me.getX(), me.getY())){
				this.currentScreen=ScreenType.DECKBUILDER;
			}else if(this.btnBooster.IsClicked(me.getX(), me.getY())){
				this.boosterScreen.StartDraft();
				this.currentScreen=ScreenType.BOOSTER;
			}
		break;
		case DECKBUILDER:
			if(this.deckBuildingScreen.mouseClicked(me)){
				this.currentScreen=ScreenType.MAIN;
			}
		break;
		case OPTIONS:
			switch(this.optionsScreen.mouseClicked(me)){
			case START:				
				this.battle.NewBattle();
				this.currentScreen=ScreenType.BATTLE;
			break;
			case CANCEL:
				this.currentScreen=ScreenType.MAIN;
			break;
			}
		break;
		case BOOSTER:
			this.boosterScreen.mouseClicked(me);
			if(this.boosterScreen.IsCompleted()){
				this.battle.NewBattle();
				this.currentScreen=ScreenType.BATTLE;
			}
		break;
		case BATTLE:
			this.battle.mouseClicked(me);
		break;
		}
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void keyPressed(KeyEvent ke){
		switch(this.currentScreen){
		case DECKBUILDER:
			this.deckBuildingScreen.keyPressed(ke);
		break;
		case BATTLE:
			this.battle.keyPressed(ke);
		break;
		}
	}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}

	public void paintDoubleBuffered(Graphics g){
		switch(this.currentScreen){
		case MAIN:
			this.btnBattle.paint(g);
			this.btnDeckbuilding.paint(g);
			this.btnBooster.paint(g);
		break;
		case DECKBUILDER:
			this.deckBuildingScreen.paint(g);
		break;
		case OPTIONS:
			this.optionsScreen.paint(g);
		break;
		case BOOSTER:
			this.boosterScreen.paint(g);
		break;
		case BATTLE:
			this.battle.paint(g);
		break;
		}
	}
}