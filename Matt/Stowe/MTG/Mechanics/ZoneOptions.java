package Matt.Stowe.MTG.Mechanics;

public class ZoneOptions{
	public static final int NONE=			0;
	public static final int HAND=			1;
	public static final int BATTLEFIELD=	2;
	public static final int GRAVEYARD=		4;
	public static final int EXILE=			8;
	public static final int LIBRARY=		16;
	public static final int STACK=			32;
	public static final int ANY=			63;
	
	private int zoneFlags;
	
	public ZoneOptions(int flags){
		this.zoneFlags=flags;
	}
	
	public ZoneOptions DeepCopy(){
		return new ZoneOptions(this.zoneFlags);
	}
	
	public boolean IsValidZone(int flags){
		return (this.zoneFlags&flags)!=0;
	}
	
	public boolean IsOnlyBattlefield(){return this.zoneFlags==BATTLEFIELD;}
	
	public String GetCSV(){
		if(this.zoneFlags==ANY)
			return "anywhere";
		StringBuilder zones=new StringBuilder();
		if(this.IsValidZone(HAND))
			zones.append("hand/");
		if(this.IsValidZone(BATTLEFIELD))
			zones.append("field/");
		if(this.IsValidZone(GRAVEYARD))
			zones.append("graveyard/");
		if(this.IsValidZone(EXILE))
			zones.append("exile/");
		if(this.IsValidZone(LIBRARY))
			zones.append("library/");
		if(this.IsValidZone(STACK))
			zones.append("stack/");
			
		if(zones.length()==0)
			return "NONE";
			
		return zones.substring(0, zones.length()-1);
	}
}
