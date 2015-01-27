package Matt.Stowe.Common;

import java.awt.*;

public class GUIClickableText extends AnimatedGUIObject{
	public String Text;
	
	public GUIClickableText(String text, int x, int y){
		super(x, y, x+text.length()*5, y+16);
		this.Text=text;
	}
	
	public void paint(Graphics g){
		if(!this.Visible)
			return;

		g.setColor(Color.black);
		g.drawString(this.Text, this.minX, this.maxY-2);
	}
}