package Matt.Stowe.MTG;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.Cards.*;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class OptionsScreen{
	public static final int CONTROLTYPE_LOCALHUMAN=	1;
	public static final int CONTROLTYPE_LOCALAI=	2;

	public static int AnimationSpeed=1000;
	public static int TimerDuration=5000;
	public static boolean AutoResolveMana=true;
	
	private GUIButton btnStart;
	private GUIButton btnCancel;
	private Vector<Player> players;
	private ArrayList<GUIFileSelecter> deckSelecters;

	public enum ResponseType{
		NONE,
		CANCEL,
		START
	}
	
	public OptionsScreen(Vector<Player> players){
		this.btnStart=new GUIButton(50, 40, 50, 16, "Start");
		this.btnCancel=new GUIButton(110, 40, 50, 16, "Back");
		this.players=players;
		this.deckSelecters=new ArrayList<GUIFileSelecter>();
		for(int i=0;i<2;i++){
			this.deckSelecters.add(new GUIFileSelecter("MTGDecks", false, 300, 86+i*20));
		}
	}
	
	public void mousePressed(MouseEvent me){
		this.btnStart.handleMousePress(me.getX(), me.getY());
		this.btnCancel.handleMousePress(me.getX(), me.getY());
		for(int i=0;i<this.deckSelecters.size();i++){
			this.deckSelecters.get(i).mousePressed(me);
		}
	}
	
	public void mouseReleased(MouseEvent me){
		this.btnStart.handleMouseRelease(me.getX(), me.getY());
		this.btnCancel.handleMouseRelease(me.getX(), me.getY());
		for(int i=0;i<this.deckSelecters.size();i++){
			this.deckSelecters.get(i).mouseReleased(me);
		}
	}
	
	public ResponseType mouseClicked(MouseEvent me){
		for(int i=0;i<this.deckSelecters.size();i++){
			this.deckSelecters.get(i).mouseClicked(me.getX(), me.getY());
		}

		if(this.btnStart.IsClicked(me.getX(), me.getY())){
			for(int i=0;i<this.players.size();i++){
				Player cp=this.players.elementAt(i);
				cp.SetDeck(Deck.ReadCardsFromFile(this.deckSelecters.get(i).GetSelectedFilePath(), cp));
			}

			return ResponseType.START;
		}else if(this.btnCancel.IsClicked(me.getX(), me.getY())){
			return ResponseType.CANCEL;
		}
		return ResponseType.NONE;
	}
	
	public void paint(Graphics g){
		this.btnStart.paint(g);
		this.btnCancel.paint(g);
	
		g.setColor(Color.black);
		for(int i=0;i<this.players.size();i++){
			Player cplayer=this.players.elementAt(i);
			g.drawString(cplayer.GetName(), 50, 100+i*20);
			g.drawString(cplayer.GetControlTypeString(), 150, 100+i*20);
		}
		
		for(int i=0;i<this.deckSelecters.size();i++){
			GUIFileSelecter deckSelecter=this.deckSelecters.get(i);
			if(!deckSelecter.IsOpen()){
				deckSelecter.paint(g);
			}
		}
		
		for(int i=0;i<this.deckSelecters.size();i++){
			GUIFileSelecter deckSelecter=this.deckSelecters.get(i);
			if(deckSelecter.IsOpen()){
				deckSelecter.paint(g);
			}
		}

		g.drawString("Animation Duration: "+AnimationSpeed+"ms", 50, 384);
		g.drawString("Timer Duration: "+TimerDuration+"ms", 50, 400);
		g.drawString("Autoresolve Mana: "+AutoResolveMana, 50, 416);
	}
}