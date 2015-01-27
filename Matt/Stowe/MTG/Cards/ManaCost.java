package Matt.Stowe.MTG.Cards;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.*;

import java.awt.*;

public class ManaCost{
	public static final int COLOR_FLAG_RED=			1;
	public static final int COLOR_FLAG_GREEN=		2;
	public static final int COLOR_FLAG_BLUE=		4;
	public static final int COLOR_FLAG_WHITE=		8;
	public static final int COLOR_FLAG_BLACK=		16;
	public static final int COLOR_FLAG_COLORLESS=	32;
	public static final int COLOR_ANY=				63;
	public static final int COLOR_COST_X=			1024;
	public static final int COLOR_PLAYERS_CHOICE=	2048;

	private int[] singleCosts;
	public int[] GetSingleCosts(){return this.singleCosts;}	
	private int[] doubleCosts;
	public int[] GetDoubleCosts(){return this.doubleCosts;}
	private int anyCosts;
	public int GetAnyCosts(){return this.anyCosts;}
	private int xCosts;
	public int GetXs(){return this.xCosts;}
	private int colorflags;
	public int GetColors(){
		if(this.colorflags==0)
			return COLOR_FLAG_COLORLESS;
		int minuscolorless=this.colorflags&~COLOR_FLAG_COLORLESS&~COLOR_COST_X;
		if(minuscolorless!=0)
			return minuscolorless;
		return this.colorflags;
	}
	
	public int GetConvertedCost(){
		return this.anyCosts+this.singleCosts.length+this.doubleCosts.length;
	}
	
	public static String GetColorNames(int color, String seperator){
		if(color==COLOR_PLAYERS_CHOICE)
			return "Chosen color";
	
		if(color==COLOR_FLAG_COLORLESS)
			return "Colorless";

		StringBuilder colorstring=new StringBuilder();
		if((color&COLOR_FLAG_RED)!=0)
			colorstring.append("Red"+seperator);
		if((color&COLOR_FLAG_GREEN)!=0)
			colorstring.append("Green"+seperator);
		if((color&COLOR_FLAG_BLUE)!=0)
			colorstring.append("Blue"+seperator);
		if((color&COLOR_FLAG_WHITE)!=0)
			colorstring.append("White"+seperator);
		if((color&COLOR_FLAG_BLACK)!=0)
			colorstring.append("Black"+seperator);
		if((color&COLOR_FLAG_COLORLESS)!=0)
			colorstring.append("Colorless"+seperator);
		
		return colorstring.substring(0, colorstring.length()-seperator.length());
	}
	
	public static Color GetAWTColorByFlag(int cflag){
		switch(cflag){
		case COLOR_FLAG_COLORLESS:
			return new Color(225,225,225);
		case COLOR_FLAG_RED:
			return new Color(255, 128, 128);
		case COLOR_FLAG_GREEN:
			return new Color(128, 255, 128);
		case COLOR_FLAG_BLUE:
			return new Color(128, 255, 255);
		case COLOR_FLAG_WHITE:
			return Color.white;
		case COLOR_FLAG_BLACK:
			return new Color(144, 144, 144);
		default:
			return new Color(255, 255, 128);
		}
	}
	
	private ManaCost(int[] singleCosts, int[] doubleCosts, int anyCosts, int xCosts, int colorflags){
		this.singleCosts=singleCosts;
		this.doubleCosts=doubleCosts;
		this.anyCosts=anyCosts;
		this.xCosts=xCosts;
		this.colorflags=colorflags;
	}
	
	public ManaCost(int[] costs){
		int singles=0;
		int doubles=0;
		this.colorflags=0;
		for(int i=0;i<costs.length;i++){
			this.colorflags|=costs[i];
			switch(costs[i]){
			case COLOR_COST_X:
				this.xCosts++;
			break;
			case COLOR_FLAG_COLORLESS:
				this.anyCosts++;
			break;
			case COLOR_FLAG_RED:
			case COLOR_FLAG_GREEN:
			case COLOR_FLAG_BLUE:
			case COLOR_FLAG_WHITE:
			case COLOR_FLAG_BLACK:
				singles++;
			break;
			default:
				doubles++;
			break;
			}
		}
		
		this.singleCosts=new int[singles];
		this.doubleCosts=new int[doubles];
		singles=0;
		doubles=0;
		for(int i=0;i<costs.length;i++){
			switch(costs[i]){
			case COLOR_FLAG_COLORLESS:
			case COLOR_COST_X:
			break;
			case COLOR_FLAG_RED:
			case COLOR_FLAG_GREEN:
			case COLOR_FLAG_BLUE:
			case COLOR_FLAG_WHITE:
			case COLOR_FLAG_BLACK:
				this.singleCosts[singles++]=costs[i];
			break;
			default:
				this.doubleCosts[doubles++]=costs[i];
			break;
			}
		}
	}
	
	public ManaCost DeepCopySetX(int x){
		int[] dcsingleCosts=new int[this.singleCosts.length];
		for(int i=0;i<this.singleCosts.length;i++){
			dcsingleCosts[i]=this.singleCosts[i];
		}
		int[] dcdoubleCosts=new int[this.doubleCosts.length];
		for(int i=0;i<this.doubleCosts.length;i++){
			dcdoubleCosts[i]=this.doubleCosts[i];
		}
		
		return new ManaCost(dcsingleCosts, dcdoubleCosts, this.anyCosts+x*xCosts, 0, this.colorflags);
	}
	
	public ManaCost DeepCopy(){
		int[] dcsingleCosts=new int[this.singleCosts.length];
		for(int i=0;i<this.singleCosts.length;i++){
			dcsingleCosts[i]=this.singleCosts[i];
		}
		int[] dcdoubleCosts=new int[this.doubleCosts.length];
		for(int i=0;i<this.doubleCosts.length;i++){
			dcdoubleCosts[i]=this.doubleCosts[i];
		}
		
		return new ManaCost(dcsingleCosts, dcdoubleCosts, this.anyCosts, this.xCosts, this.colorflags);
	}

	public void AddCost(ManaCost other){
		if(other==null)
			return;
		int[] singleCostsOther=other.GetSingleCosts();
		int[] combinedSingleCosts=new int[this.singleCosts.length+singleCostsOther.length];
		for(int i=0;i<this.singleCosts.length;i++){
			combinedSingleCosts[i]=this.singleCosts[i];
		}
		for(int i=this.singleCosts.length;i<combinedSingleCosts.length;i++){
			combinedSingleCosts[this.singleCosts.length+i]=singleCostsOther[i];
		}
		this.singleCosts=combinedSingleCosts;

		int[] doubleCostsOther=other.GetDoubleCosts();
		int[] combinedDoubleCosts=new int[this.doubleCosts.length+doubleCostsOther.length];
		for(int i=0;i<this.doubleCosts.length;i++){
			combinedDoubleCosts[i]=this.doubleCosts[i];
		}
		for(int i=this.doubleCosts.length;i<combinedDoubleCosts.length;i++){
			combinedDoubleCosts[this.doubleCosts.length+i]=doubleCostsOther[i];
		}		
		this.doubleCosts=combinedDoubleCosts;

		this.anyCosts+=other.GetAnyCosts();
		this.xCosts+=other.GetXs();
		this.colorflags|=other.GetColors();
	}
	
	public boolean MeetsCost(int mountains, int forests, int islands, int plains, int swamps, int colorless){
		return this.GetCostAsMagicEffects(null, mountains, forests, islands, plains, swamps, colorless)!=null;
	}
	
	public MagicEffect[] GetCostAsMagicEffects(Player player, int mountains, int forests, int islands, int plains, int swamps, int colorless){
		int originalmountains=mountains;
		int originalforests=forests;
		int originalislands=islands;
		int originalplains=plains;
		int originalswamps=swamps;
		int originalcolorless=colorless;
		for(int i=0;i<this.singleCosts.length;i++){
			switch(this.singleCosts[i]){
			case COLOR_FLAG_RED:
				mountains--;
			break;
			case COLOR_FLAG_GREEN:
				forests--;
			break;
			case COLOR_FLAG_BLUE:
				islands--;
			break;
			case COLOR_FLAG_WHITE:
				plains--;
			break;
			case COLOR_FLAG_BLACK:
				swamps--;
			break;
			}
		}
		
		if(mountains<0||forests<0||islands<0||plains<0||swamps<0)
			return null;
		
		int[] firstColor=new int[this.doubleCosts.length];
		int[] currentColors=new int[this.doubleCosts.length];
		for(int i=0;i<this.doubleCosts.length;i++){
			if((this.doubleCosts[i]&COLOR_FLAG_RED)!=0){
				firstColor[i]=COLOR_FLAG_RED;
				currentColors[i]=COLOR_FLAG_RED;
			}else if((this.doubleCosts[i]&COLOR_FLAG_GREEN)!=0){
				firstColor[i]=COLOR_FLAG_GREEN;
				currentColors[i]=COLOR_FLAG_GREEN;
			}else if((this.doubleCosts[i]&COLOR_FLAG_BLUE)!=0){
				firstColor[i]=COLOR_FLAG_BLUE;
				currentColors[i]=COLOR_FLAG_BLUE;
			}else if((this.doubleCosts[i]&COLOR_FLAG_WHITE)!=0){
				firstColor[i]=COLOR_FLAG_WHITE;
				currentColors[i]=COLOR_FLAG_WHITE;
			}else if((this.doubleCosts[i]&COLOR_FLAG_BLACK)!=0){
				firstColor[i]=COLOR_FLAG_BLACK;
				currentColors[i]=COLOR_FLAG_BLACK;
			}else{
				System.out.println("UNKNOWN DOUBLE COST: "+this.doubleCosts[i]);
			}
		}
/*System.out.print("Firstcolors: ");
for(int di=0;di<this.doubleCosts.length;di++){
	System.out.print(firstColor[di]+", ");
}
System.out.println();*/
		while(this.doubleCosts.length>0){
			for(int vcheck=0;vcheck<this.doubleCosts.length;vcheck++){
				switch(currentColors[vcheck]){
				case COLOR_FLAG_RED:
					mountains--;
				break;
				case COLOR_FLAG_GREEN:
					forests--;
				break;
				case COLOR_FLAG_BLUE:
					islands--;
				break;
				case COLOR_FLAG_WHITE:
					plains--;
				break;
				case COLOR_FLAG_BLACK:
					swamps--;
				break;
				}
			}
			
			if(mountains>=0&&forests>=0&&islands>=0&&plains>=0&&swamps>=0)
				break;

			for(int vcheck=0;vcheck<this.doubleCosts.length;vcheck++){
				switch(currentColors[vcheck]){
				case COLOR_FLAG_RED:
					mountains++;
				break;
				case COLOR_FLAG_GREEN:
					forests++;
				break;
				case COLOR_FLAG_BLUE:
					islands++;
				break;
				case COLOR_FLAG_WHITE:
					plains++;
				break;
				case COLOR_FLAG_BLACK:
					swamps++;
				break;
				}
			}
			
			for(int i=0;i<this.doubleCosts.length;i++){
				if(currentColors[i]==firstColor[i]){
					if((this.doubleCosts[i]&COLOR_FLAG_RED)!=0&&firstColor[i]!=COLOR_FLAG_RED){
						currentColors[i]=COLOR_FLAG_RED;
					}else if((this.doubleCosts[i]&COLOR_FLAG_GREEN)!=0&&firstColor[i]!=COLOR_FLAG_GREEN){
						currentColors[i]=COLOR_FLAG_GREEN;
					}else if((this.doubleCosts[i]&COLOR_FLAG_BLUE)!=0&&firstColor[i]!=COLOR_FLAG_BLUE){
						currentColors[i]=COLOR_FLAG_BLUE;
					}else if((this.doubleCosts[i]&COLOR_FLAG_WHITE)!=0&&firstColor[i]!=COLOR_FLAG_WHITE){
						currentColors[i]=COLOR_FLAG_WHITE;
					}else if((this.doubleCosts[i]&COLOR_FLAG_BLACK)!=0&&firstColor[i]!=COLOR_FLAG_BLACK){
						currentColors[i]=COLOR_FLAG_BLACK;
					}
					break;
				}else{
					currentColors[i]=firstColor[i];
					if(i==currentColors.length-1)
						return null;
				}
			}
/*System.out.print("currentcolors: ");
for(int di=0;di<this.doubleCosts.length;di++){
	System.out.print(currentColors[di]+", ");

}
System.out.println();*/
		}
		
		for(int i=0;i<this.anyCosts;i++){
			if(colorless>0){
				colorless--;
				continue;
			}
			if(mountains>0){
				mountains--;
				continue;
			}
			if(forests>0){
				forests--;
				continue;
			}
			if(islands>0){
				islands--;
				continue;
			}
			if(plains>0){
				plains--;
				continue;
			}
			if(swamps>0){
				swamps--;
				continue;
			}
			
			return null;
		}
		
		return new MagicEffect[]{new MagicEffectAddManaToPool(player, mountains-originalmountains, forests-originalforests, islands-originalislands, plains-originalplains, swamps-originalswamps, colorless-originalcolorless)};
	}
	
	public String toString(){
		StringBuilder result=new StringBuilder();
		for(int i=0;i<this.xCosts;i++){
			result.append("X ");
		}
		
		if(this.singleCosts.length+this.doubleCosts.length+this.anyCosts+this.xCosts==0
		||this.anyCosts>0){
			result.append(this.anyCosts+" ");
		}
		
		for(int i=0;i<this.doubleCosts.length;i++){
			if((this.doubleCosts[i]&COLOR_FLAG_RED)!=0){
				result.append("R");
			}
			if((this.doubleCosts[i]&COLOR_FLAG_GREEN)!=0){
				result.append("G");
			}
			if((this.doubleCosts[i]&COLOR_FLAG_BLUE)!=0){
				result.append("U");
			}
			if((this.doubleCosts[i]&COLOR_FLAG_WHITE)!=0){
				result.append("W");
			}
			if((this.doubleCosts[i]&COLOR_FLAG_BLACK)!=0){
				result.append("B");
			}
			result.append(" ");
		}

		for(int i=0;i<this.singleCosts.length;i++){
			switch(this.singleCosts[i]){
			case COLOR_FLAG_RED:
				result.append("R ");
			break;
			case COLOR_FLAG_GREEN:
				result.append("G ");
			break;
			case COLOR_FLAG_BLUE:
				result.append("U ");
			break;
			case COLOR_FLAG_WHITE:
				result.append("W ");
			break;
			case COLOR_FLAG_BLACK:
				result.append("B ");
			break;
			default:
				result.append("?:"+this.singleCosts[i]+" ");
			break;
			}
		}

		return result.toString();
	}

	public int paint(Graphics g, int x, int y){
		int cx=x;
		
		for(int i=0;i<this.xCosts;i++){
			Painting.FillOvalWithBorder(g, cx, y, 14, 14, this.GetAWTColorByFlag(COLOR_FLAG_COLORLESS), Color.black);
			g.setColor(Color.black);
			g.drawString("X", cx+4, y+12);
			cx+=16;
		}
		
		if(this.singleCosts.length+this.doubleCosts.length+this.anyCosts+this.xCosts==0
		||this.anyCosts>0){
			Painting.FillOvalWithBorder(g, cx, y, 14, 14, this.GetAWTColorByFlag(COLOR_FLAG_COLORLESS), Color.black);
			g.setColor(Color.black);
			g.drawString(""+this.anyCosts, cx+4, y+12);
			cx+=16;
		}

		for(int i=0;i<this.doubleCosts.length;i++){
			int startAngle=45;
			if((this.doubleCosts[i]&COLOR_FLAG_RED)!=0){
				Painting.FillArcWithBorder(g, cx, y, 14, 14, startAngle, 180, GetAWTColorByFlag(COLOR_FLAG_RED), Color.black);
				startAngle=225;
			}
			if((this.doubleCosts[i]&COLOR_FLAG_GREEN)!=0){
				Painting.FillArcWithBorder(g, cx, y, 14, 14, startAngle, 180, GetAWTColorByFlag(COLOR_FLAG_GREEN), Color.black);
				startAngle=225;
			}
			if((this.doubleCosts[i]&COLOR_FLAG_BLUE)!=0){
				Painting.FillArcWithBorder(g, cx, y, 14, 14, startAngle, 180, GetAWTColorByFlag(COLOR_FLAG_BLUE), Color.black);
				startAngle=225;
			}
			if((this.doubleCosts[i]&COLOR_FLAG_WHITE)!=0){
				Painting.FillArcWithBorder(g, cx, y, 14, 14, startAngle, 180, GetAWTColorByFlag(COLOR_FLAG_WHITE), Color.black);
				startAngle=225;
			}
			if((this.doubleCosts[i]&COLOR_FLAG_BLACK)!=0){
				Painting.FillArcWithBorder(g, cx, y, 14, 14, startAngle, 180, GetAWTColorByFlag(COLOR_FLAG_BLACK), Color.black);
				startAngle=225;
			}
			cx+=16;
		}
		for(int i=0;i<this.singleCosts.length;i++){
			Painting.FillOvalWithBorder(g, cx, y, 14, 14, GetAWTColorByFlag(this.singleCosts[i]), Color.black);
			cx+=16;
		}
		
		return cx;
	}
}
