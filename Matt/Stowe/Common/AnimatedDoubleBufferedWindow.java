package Matt.Stowe.Common;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public abstract class AnimatedDoubleBufferedWindow extends JFrame implements MouseListener, KeyListener, Runnable{
	public void InitializeWindow(String title, int width, int height){
		this.setTitle(title);
		this.setSize(width, height);
		this.addMouseListener(this);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		Thread animator=new Thread(this);
		animator.start();
	}
	
	public void run(){
		long lastAnimationCallMillis=System.currentTimeMillis();
		long nextRepaint=0;
		while(true){
			long startframemillis=System.currentTimeMillis();
			
			this.animate(startframemillis-lastAnimationCallMillis);
			
			lastAnimationCallMillis=startframemillis;
			
			if(System.currentTimeMillis()>=nextRepaint){
				repaint();
				nextRepaint+=33;
			}
			long sleepduration=20-System.currentTimeMillis()+startframemillis;
			if(sleepduration>0){
				try{Thread.sleep(sleepduration);}catch(Exception e){e.printStackTrace();}
			}
		}
	}
	
	public abstract void animate(long elapsedMillis);
	
	public void paint(Graphics og){
		Image doublebuffer=this.createImage(this.getWidth(), this.getHeight());
		Graphics g=doublebuffer.getGraphics();

		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		this.paintDoubleBuffered(g);
		
		og.drawImage(doublebuffer,0,0,null);
	}

	public abstract void paintDoubleBuffered(Graphics g);
}