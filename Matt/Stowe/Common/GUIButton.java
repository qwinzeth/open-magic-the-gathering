package Matt.Stowe.Common;

import java.awt.*;

public class GUIButton{
	private int x, y, width, height;
	public int GetX(){return this.x;}
	public int GetY(){return this.y;}
	public void MoveTo(int newx, int newy){
		this.x=newx;
		this.y=newy;
	}

	private String text;
	public void setText(String newtext){
		this.text=newtext;
	}
	
	private Color bgcolor;
	public boolean Visible;
	
	public GUIButton(int x, int y, int width, int height, String text){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.text=text;
		this.bgcolor=Color.white;
		this.Visible=true;
	}
	
	public boolean IsClicked(int mx, int my){
		return this.Visible&&mx>=this.x&&mx<=this.x+this.width&&my>=this.y&&my<=this.y+this.height;
	}
	
	public boolean handleMousePress(int mx, int my){
		if(!this.Visible)
			return false;
		if(this.IsClicked(mx, my)){
			this.bgcolor=new Color(225, 225, 225);
			return true;
		}
		return false;
	}
	
	public boolean handleMouseRelease(int mx, int my){
		if(!this.Visible)
			return false;
		this.bgcolor=Color.white;
		return this.IsClicked(mx, my);
	}
	
	public void paint(Graphics g){
		if(!this.Visible)
			return;
		Painting.FillRectWithBorder(g, this.x, this.y, this.width, this.height, this.bgcolor, Color.black);
		
		g.setColor(Color.black);
		g.drawString(this.text, this.x+2, this.y+14);
	}
}