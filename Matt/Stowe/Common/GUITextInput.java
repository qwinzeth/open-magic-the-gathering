package Matt.Stowe.Common;

import java.awt.*;
import java.awt.event.*;

public class GUITextInput{
	private int cursor;
	private StringBuilder userInput;
	private int boxX;
	private int boxY;
	private int boxWidth;
	private int boxHeight;
	
	public String GetText(){
		return this.userInput.toString();
	}

	public GUITextInput(int x, int y, int width, int height){
		this.boxX=x;
		this.boxY=y;
		this.boxWidth=width;
		this.boxHeight=height;
		this.cursor=0;
		this.userInput=new StringBuilder();
	}
	
	public void keyPressed(KeyEvent ke){
		switch(ke.getKeyCode()){
		case KeyEvent.VK_BACK_SPACE:
			if(this.cursor>0){
				this.userInput.deleteCharAt(--this.cursor);
			}
		break;
		case KeyEvent.VK_DELETE:
			if(this.cursor<this.userInput.length()){
				this.userInput.deleteCharAt(this.cursor);
			}
		break;
		case KeyEvent.VK_LEFT:
			if(this.cursor>0)
				this.cursor--;
		break;
		case KeyEvent.VK_RIGHT:
			if(this.cursor<this.userInput.length())
				this.cursor++;
		break;
		default:
			char charTyped=ke.getKeyChar();
			if(Character.isLetter(charTyped))
				this.userInput.insert(this.cursor++, charTyped);
		break;
		}
	}
	
	public void paint(Graphics g){
		Painting.FillRectWithBorder(g, this.boxX, this.boxY, this.boxWidth, this.boxHeight, Color.white, Color.black);
		
		g.setColor(Color.black);
		FontMetrics metrics=g.getFontMetrics();
		int cursorX=this.boxX+2+metrics.stringWidth(this.userInput.substring(0, this.cursor));
		g.drawLine(cursorX, this.boxY+2, cursorX, this.boxY+this.boxHeight-2);
		
		g.drawString(this.GetText(), this.boxX+2, this.boxY+14);
	}
}