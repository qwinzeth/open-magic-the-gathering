package Matt.Stowe.Common;

import java.awt.*;

public class BasicTimer{
	private Object mylock;
	private long startMillis;
	private long currentMillis;
	private long finishMillis;
	private boolean paused;
	private double percentComplete;
	private Color fillColor;
	private int x, y, width, height;
	
	public BasicTimer(Color fillColor, int x, int y, int width, int height){
		this.mylock=new Object();
		this.startMillis=0;
		this.finishMillis=1000;
		this.currentMillis=0;
		this.paused=true;
		this.fillColor=fillColor;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	
	public boolean IsPaused(){return this.paused;}

	public void Pause(){this.paused=true;}

	public void Unpause(){
		if(this.IsComplete())
			return;
		long elapsedmillis=System.currentTimeMillis()-this.currentMillis;
		this.startMillis+=elapsedmillis;
		this.finishMillis+=elapsedmillis;
		this.paused=false;
	}

	public void TogglePause(){
		if(this.paused)
			this.Unpause();
		else
			this.Pause();
	}
	
	public boolean IsComplete(){return this.percentComplete==1.0;}

	public void Start(long millis){
		this.startMillis=System.currentTimeMillis();
		this.currentMillis=this.startMillis;
		this.finishMillis=this.startMillis+millis;
		this.percentComplete=0;
		this.paused=false;
	}
	
	public void JumpToFinish(){
		this.percentComplete=1.0;
		this.startMillis=0;
		this.currentMillis=1000;
		this.finishMillis=this.currentMillis;
	}
	
	public void WaitForCompletion(){
		while(!this.IsComplete()){
			try{
				synchronized(this.mylock){
					this.mylock.wait();
				}
			}catch(InterruptedException ie){ie.printStackTrace();}
		}
	}
	
	public void animate(long elapsedMillis){
		if(this.paused||this.IsComplete())
			return;
		this.currentMillis=System.currentTimeMillis();
		if(this.currentMillis>=this.finishMillis){
			this.currentMillis=this.finishMillis;
			synchronized(this.mylock){
				this.mylock.notifyAll();
			}
		}
		this.percentComplete=(double)(this.currentMillis-this.startMillis)/(this.finishMillis-this.startMillis);
	}
	
	public void paintPercentComplete(Graphics g){
		g.setColor(Color.black);
		g.drawString(""+(int)(this.percentComplete*100)+"%", this.x, this.y-2);
	}
	
	public void paintSecondsRemaining(Graphics g){
		g.setColor(Color.black);
		g.drawString(""+((int)Math.ceil(((double)this.finishMillis-this.currentMillis)/1000)), this.x, this.y+height+14);
	}
	
	public void paintBar(Graphics g){
		g.setColor(this.fillColor);
		g.fillRect(this.x, this.y, (int)(this.width*percentComplete), this.height);
		
		g.setColor(Color.black);
		g.drawRect(this.x, this.y, this.width, this.height);
	}
}