package Matt.Stowe.MTG.Cards;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.awt.*;


public class Planeswalker extends CardBase{
	private int baseLoyalty;
	private int loyalty;
	
	public Planeswalker(Player owner, String name, String description, ManaCost cost, int startingLoyalty){
		super(owner, name, description, TargetInfo.TARGET_TYPE_FLAG_PLANESWALKER, cost, null);
		this.baseLoyalty=startingLoyalty;
		this.AddEntersTheBattlefieldAdjustments(new MagicEffectSetLoyalty(owner, this, this.baseLoyalty));
	}
	
	private Planeswalker(DeepCopyInfo dcinfo, int baseLoyalty, int loyalty){
		super(dcinfo);
		this.baseLoyalty=baseLoyalty;
		this.loyalty=loyalty;
	}
	
	public int MarkDamage(int damage, int damageTypeFlags, CardBase source){
		int damageDealt=damage;
		
		if(this.controller.GetEmblems().EmblemCount(EmblemCollection.AJANI_STEADFAST)>0){
			damageDealt=1;
		}
		
		this.AdjustLoyalty(-damageDealt);
		return damageDealt;
	}
	
	public boolean IsDestroyed(){
		return this.loyalty<=0;
	}
	
	public boolean CanAdjust(int amount){
		return this.loyalty+amount>=0;
	}
	
	public void AdjustLoyalty(int amount){
		this.loyalty+=amount;
	}
	
	public void SetLoyalty(int amount){
		this.loyalty=amount;
	}

	public Planeswalker DeepCopy(){
		return new Planeswalker(this.GetDeepCopyInfo(), this.baseLoyalty, this.loyalty);
	}
	
	public int paintFullVersionAt(Graphics g, int x, int y){
		int cy=super.paintFullVersionAt(g, x, y);

		g.setColor(Color.black);
		g.fillRect(x+FULLWIDTH-16, y+FULLHEIGHT-14, 16, 14);
		g.setColor(Color.white);
		g.drawString(""+this.baseLoyalty, x+FULLWIDTH-14, y+FULLHEIGHT-2);
		
		if(this.loyalty>0){
			g.setColor(Color.black);
			g.fillOval(x+FULLWIDTH-16,  y+FULLHEIGHT-30, 14, 14);
			g.setColor(Color.white);
			g.drawString(""+this.loyalty, x+FULLWIDTH-13,  y+FULLHEIGHT-18);
		}
		
		return cy;
	}

	public void paint(Graphics g){
		super.paint(g);
		if(this.faceDown)
			return;
		g.setColor(Color.black);
		g.fillRect(this.maxX-16, this.maxY-14, 16, 14);
		g.setColor(Color.white);
		g.drawString(""+this.baseLoyalty, this.maxX-14, this.maxY-2);
		
		if(this.loyalty>0){
			g.setColor(Color.black);
			g.fillOval(this.maxX-16, this.maxY-30, 14, 14);
			g.setColor(Color.white);
			g.drawString(""+this.loyalty, this.maxX-13, this.maxY-18);
		}
	}
}