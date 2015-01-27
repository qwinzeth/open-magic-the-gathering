package Matt.Stowe.MTG;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.Cards.*;

import java.awt.*;
import java.awt.event.*;

public class DeckBuilderCard{
	public CardBase Card;

	private int x, y;
	public void MoveTo(int newx, int newy){
		this.x=newx;
		this.y=newy;
		this.updateButtonPositions();
	}
	
	private int count;
	public int GetCount(){return this.count;}
	public void Reset(){this.count=0;this.handleCountChange();}

	public String GetName(){return this.Card.GetName();}
	
	private GUIButton btnAdd;
	private GUIButton btnSubtract;
	
	public DeckBuilderCard(Player owner, String name, int count, int x, int y){
		this.init(Deck.GetCardByName(name, owner), count, x, y);
	}
	
	public DeckBuilderCard(Player owner, String name, int x, int y){
		this.init(Deck.GetCardByName(name, owner), 1, x, y);
	}
	
	private void init(CardBase card, int count, int x, int y){
		this.Card=card;
		this.x=x;
		this.y=y;
		this.count=count;
		this.btnAdd=new GUIButton(this.x, this.y, 16, 16, " +");
		this.btnSubtract=new GUIButton(this.x+20, this.y, 16, 16, "  -");
		this.handleCountChange();
	}

	private void handleCountChange(){
		if(this.count>=4&&!this.Card.IsBasicLand()){
			this.btnAdd.Visible=false;
		}else{
			this.btnAdd.Visible=true;
		}
		if(this.count==0){
			this.btnSubtract.Visible=false;
		}else{
			this.btnSubtract.Visible=true;
		}
	}
	
	private void updateButtonPositions(){
		this.btnAdd.MoveTo(this.x, this.y);
		this.btnSubtract.MoveTo(this.x+20, this.y);
	}

	public void mousePressed(MouseEvent me){
		this.btnAdd.handleMousePress(me.getX(), me.getY());
		this.btnSubtract.handleMousePress(me.getX(), me.getY());
	}

	public void mouseReleased(MouseEvent me){
		this.btnAdd.handleMouseRelease(me.getX(), me.getY());
		this.btnSubtract.handleMouseRelease(me.getX(), me.getY());
	}

	public void mouseClicked(MouseEvent me){
		if(this.btnAdd.IsClicked(me.getX(), me.getY())){
			this.count++;
		}else if(this.btnSubtract.IsClicked(me.getX(), me.getY())){
			this.count--;
		}
		this.handleCountChange();
	}

	public void paint(Graphics g){
		this.btnAdd.paint(g);
		this.btnSubtract.paint(g);
		g.setColor(Color.black);
		g.drawString(""+this.count+" "+this.Card.GetName(), this.x+40, this.y+14);
	}
}
