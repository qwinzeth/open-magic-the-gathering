package Matt.Stowe.Common;

import java.awt.*;

public class Painting{
	public static int DrawWrappedString(Graphics g, String text, int x, int y, int width, int lineHeight){
		FontMetrics metrics=g.getFontMetrics();
		int startIndex=0;
		int numberOfLines=0;
		while(startIndex<text.length()){
			int endIndex=text.indexOf(" ", startIndex);
			int newEndIndex=endIndex;
			while(newEndIndex!=-1&&metrics.stringWidth(text.substring(startIndex, newEndIndex))<width){
				endIndex=newEndIndex;
				newEndIndex=text.indexOf(" ", endIndex+1);
			}
			if(endIndex==-1||newEndIndex==-1&&metrics.stringWidth(text.substring(startIndex, text.length()))<width){
				endIndex=text.length();
			}
			
			g.drawString(text.substring(startIndex, endIndex), x, y+numberOfLines*lineHeight);
			numberOfLines++;
			startIndex=endIndex+1;
		}
		
		return numberOfLines;
	}

	public static void FillArcWithBorder(Graphics g, int x, int y, int width, int height, int startDegree, int endDegree, Color fillColor, Color borderColor){
		g.setColor(fillColor);
		g.fillArc(x, y, width, height, startDegree, endDegree);
		
		g.setColor(borderColor);
		g.drawArc(x, y, width, height, startDegree, endDegree);
	}
	
	public static void FillOvalWithBorder(Graphics g, int x, int y, int width, int height, Color fillColor, Color borderColor){
		g.setColor(fillColor);
		g.fillOval(x, y, width, height);
		
		g.setColor(borderColor);
		g.drawOval(x, y, width, height);
	}

	public static void FillRectWithBorder(Graphics g, int x, int y, int width, int height, Color fillColor, Color borderColor){
		g.setColor(fillColor);
		g.fillRect(x, y, width, height);
		
		g.setColor(borderColor);
		g.drawRect(x, y, width, height);
	}
	
	public static void FillPolygonWithBorder(Graphics g, int[] xs, int[] ys, Color fillColor, Color borderColor){
		g.setColor(fillColor);
		g.fillPolygon(xs, ys, xs.length);
		
		g.setColor(borderColor);
		g.drawPolygon(xs, ys, xs.length);
	}
}
