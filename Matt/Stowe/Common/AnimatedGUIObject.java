package Matt.Stowe.Common;

import java.awt.*;

public class AnimatedGUIObject{
	private int startingX;
	private int startingY;
	protected int destinationX;
	protected int destinationY;
	
	protected boolean animating;
	protected long finishAnimationMillis;
	protected long startAnimationMillis;
	protected int minX;
	protected int minY;
	protected int maxX;
	protected int maxY;
	
	public int GetX(){return this.minX;}
	public int GetY(){return this.minY;}
	public int GetWidth(){return this.maxX-this.minX;}
	public int GetHeight(){return this.maxY-this.minY;}
	
	public boolean Visible;

	public AnimatedGUIObject(int minx, int miny, int maxx, int maxy){
		this.minX=minx;
		this.minY=miny;
		this.maxX=maxx;
		this.maxY=maxy;
		this.destinationX=this.minX;
		this.destinationY=this.minY;
		this.animating=false;
		this.Visible=true;
	}
	
	public boolean SetAnimationDestination(int newx, int newy, int duration){
		this.destinationX=newx;
		this.destinationY=newy;
		this.startingX=this.minX;
		this.startingY=this.minY;
		this.startAnimationMillis=System.currentTimeMillis();
		this.finishAnimationMillis=this.startAnimationMillis+duration;
		this.animating=(this.minX!=this.destinationX||this.minY!=this.destinationY)&&this.startAnimationMillis<this.finishAnimationMillis;
//System.out.println("Going from ("+this.startingX+","+this.startingY+") to ("+this.destinationX+","+this.destinationY+") in "+duration+" milliseconds. animating="+this.animating);
		return this.animating;
	}
	
	public void MoveTo(int newx, int newy){
		int xdist=this.maxX-this.minX;
		this.minX=newx;
		this.maxX=newx+xdist;
		
		int ydist=this.maxY-this.minY;
		this.minY=newy;
		this.maxY=newy+ydist;
	}

	public boolean StepTowardsOnRails(){
		if(!this.animating)
			return false;
		long currentSystemMillis=System.currentTimeMillis();
		if(currentSystemMillis>this.finishAnimationMillis)
			currentSystemMillis=this.finishAnimationMillis;
		double fractionDone=(double)(currentSystemMillis-this.startAnimationMillis)/(this.finishAnimationMillis-this.startAnimationMillis);
		
		this.MoveTo((int)Math.round(this.startingX+(this.destinationX-this.startingX)*fractionDone), (int)Math.round(this.startingY+(this.destinationY-this.startingY)*fractionDone));
		this.animating=currentSystemMillis!=this.finishAnimationMillis;
		return this.animating;
	}
	
	public void JumpToAnimationEnd(){
		this.animating=false;
		this.MoveTo(this.destinationX, this.destinationY);
	}
	
	public boolean IsClicked(int x, int y){
		return x>=this.minX&&x<=this.maxX&&y>=this.minY&&y<=this.maxY&&!this.animating&&this.Visible;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.drawRect(this.minX, this.minY, this.GetWidth(), this.GetHeight());
	}
}