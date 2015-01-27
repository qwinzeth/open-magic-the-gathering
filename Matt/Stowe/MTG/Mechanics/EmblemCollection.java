package Matt.Stowe.MTG.Mechanics;

public class EmblemCollection{
	public static final int AJANI_STEADFAST=	0;
	public static final int TOTAL=				1;
	
	private int[] emblems;
	
	public EmblemCollection(){
		this.emblems=new int[TOTAL];
		for(int i=0;i<this.emblems.length;i++)
			this.emblems[i]=0;
	}
	
	public void AddEmblem(int type){
		this.emblems[type]++;
	}
	
	public int EmblemCount(int type){
		return this.emblems[type];
	}
	
	public static String GetEmblemString(int type){
		switch(type){
		case AJANI_STEADFAST:
			return "If a source would deal damage to you or a planeswalker you control, prevent all but 1 of that damage.";
		default:
			return "UNKNOWN EMBLEM TYPE="+type;
		}
	}
}