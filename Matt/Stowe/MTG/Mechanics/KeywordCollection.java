package Matt.Stowe.MTG.Mechanics;

public class KeywordCollection{
	private static final int KEYWORD_SUMMONING_SICKNESS=	0;
	private static final int KEYWORD_LIFELINK=				1;
	private static final int KEYWORD_FLASH=					2;
	private static final int KEYWORD_FLYING=				3;
	private static final int KEYWORD_REACH=					4;
	private static final int KEYWORD_HASTE=					5;
	private static final int KEYWORD_MONSTROUS=				6;
	private static final int KEYWORD_DEFENDER=				7;
	private static final int KEYWORD_TRAMPLE=				8;
	private static final int KEYWORD_UNBLOCKABLE=			9;
	private static final int KEYWORD_VIGILANCE=				10;
	private static final int KEYWORD_FIRSTSTRIKE=			11;
	private static final int KEYWORD_DOUBLESTRIKE=			12;
	private static final int KEYWORD_DEATHTOUCH=			13;
	private static final int KEYWORD_CONVOKE=				14;
	private static final int KEYWORD_INDESTRUCTABLE=		15;
	private static final int KEYWORD_DELVE=					16;
	private static final int KEYWORD_MORPH=					17;
	
	private static final int KEYWORD_COUNT=					18;
	
	private int[] keywordSourceCounts;
	private int[] keywordPreventionCounts;//TODO
	
	private KeywordCollection(int[] keywordSourceCounts, int[] keywordPreventionCounts){
		this.keywordSourceCounts=keywordSourceCounts;
		this.keywordPreventionCounts=keywordPreventionCounts;
	}
	
	public KeywordCollection(){
		this.keywordSourceCounts=new int[KEYWORD_COUNT];
		for(int i=0;i<this.keywordSourceCounts.length;i++)
			this.keywordSourceCounts[i]=0;
	}
	
	public KeywordCollection DeepCopy(){
		int[] keywordSourceCountsDeepCopy=new int[this.keywordSourceCounts.length];
		for(int i=0;i<this.keywordSourceCounts.length;i++){
			keywordSourceCountsDeepCopy[i]=this.keywordSourceCounts[i];
		}
		//TODO: keywordPreventionCounts
		return new KeywordCollection(keywordSourceCountsDeepCopy, null);
	}
	
	public void AddAllKeywords(KeywordCollection other){
		for(int i=0;i<this.keywordSourceCounts.length;i++){
			this.keywordSourceCounts[i]+=other.GetKeywordSourceCounts(i);
		}
	}
	
	public int GetKeywordSourceCounts(int keywordid){
		return this.keywordSourceCounts[keywordid];
	}
	
	private String getStringRepresentation(int keyword){
		switch(keyword){
		case KEYWORD_SUMMONING_SICKNESS:
			return "Sick";
		case KEYWORD_LIFELINK:
			return "Lifelink";
		case KEYWORD_FLASH:
			return "Flash";
		case KEYWORD_FLYING:
			return "Flying";
		case KEYWORD_REACH:
			return "Reach";
		case KEYWORD_HASTE:
			return "Haste";
		case KEYWORD_MONSTROUS:
			return "Monstrous";
		case KEYWORD_DEFENDER:
			return "Defender";
		case KEYWORD_TRAMPLE:
			return "Trample";
		case KEYWORD_UNBLOCKABLE:
			return "Unblockable";
		case KEYWORD_VIGILANCE:
			return "Vigilance";
		case KEYWORD_FIRSTSTRIKE:
			return "First Strike";
		case KEYWORD_DOUBLESTRIKE:
			return "Double Strike";
		case KEYWORD_DEATHTOUCH:
			return "Deathtouch";
		case KEYWORD_CONVOKE:
			return "Convoke";
		case KEYWORD_INDESTRUCTABLE:
			return "Indestructable";
		case KEYWORD_DELVE:
			return "Delve";
		case KEYWORD_MORPH:
			return "Morph";
		default:
			return "UNKNOWN KEYWORD: "+keyword;
		}
	}
	
	public String ToCommaSeparatedString(){
		StringBuilder csv=new StringBuilder();
		for(int i=0;i<this.keywordSourceCounts.length;i++){
			if(this.keywordSourceCounts[i]>0)
				csv.append(this.getStringRepresentation(i)+", ");
		}
		if(csv.length()>0)
			return csv.substring(0, csv.length()-2);
		return "";
	}
	
	public void AddSummoningSickness(){
		this.keywordSourceCounts[KEYWORD_SUMMONING_SICKNESS]=1;
	}
	
	public void RemoveSummoningSickness(){
		this.keywordSourceCounts[KEYWORD_SUMMONING_SICKNESS]=0;
	}
	
	public boolean HasSummoningSickness(){
		return this.keywordSourceCounts[KEYWORD_SUMMONING_SICKNESS]>0;
	}

	public void AddLifelink(){
		this.keywordSourceCounts[KEYWORD_LIFELINK]++;
	}
	
	public void RemoveLifelink(){
		this.keywordSourceCounts[KEYWORD_LIFELINK]--;
	}
	
	public boolean HasLifelink(){
		return this.keywordSourceCounts[KEYWORD_LIFELINK]>0;
	}

	public void AddFlash(){
		this.keywordSourceCounts[KEYWORD_FLASH]++;
	}
	
	public void RemoveFlash(){
		this.keywordSourceCounts[KEYWORD_FLASH]--;
	}
	
	public boolean HasFlash(){
		return this.keywordSourceCounts[KEYWORD_FLASH]>0;
	}

	public void AddFlying(){
		this.keywordSourceCounts[KEYWORD_FLYING]++;
	}
	
	public void RemoveFlying(){
		this.keywordSourceCounts[KEYWORD_FLYING]--;
	}
	
	public boolean HasFlying(){
		return this.keywordSourceCounts[KEYWORD_FLYING]>0;
	}

	public void AddReach(){
		this.keywordSourceCounts[KEYWORD_REACH]++;
	}
	
	public void RemoveReach(){
		this.keywordSourceCounts[KEYWORD_REACH]--;
	}
	
	public boolean HasReach(){
		return this.keywordSourceCounts[KEYWORD_REACH]>0;
	}

	public void AddHaste(){
		this.keywordSourceCounts[KEYWORD_HASTE]++;
	}
	
	public void RemoveHaste(){
		this.keywordSourceCounts[KEYWORD_HASTE]--;
	}
	
	public boolean HasHaste(){
		return this.keywordSourceCounts[KEYWORD_HASTE]>0;
	}

	public void AddMonstrous(){
		this.keywordSourceCounts[KEYWORD_MONSTROUS]++;
	}
	
	public void RemoveMonstrous(){
		this.keywordSourceCounts[KEYWORD_MONSTROUS]--;
	}
	
	public boolean HasMonstrous(){
		return this.keywordSourceCounts[KEYWORD_MONSTROUS]>0;
	}

	public void AddDefender(){
		this.keywordSourceCounts[KEYWORD_DEFENDER]++;
	}
	
	public void RemoveDefender(){
		this.keywordSourceCounts[KEYWORD_DEFENDER]--;
	}
	
	public boolean HasDefender(){
		return this.keywordSourceCounts[KEYWORD_DEFENDER]>0;
	}

	public void AddTrample(){
		this.keywordSourceCounts[KEYWORD_TRAMPLE]++;
	}
	
	public void RemoveTrample(){
		this.keywordSourceCounts[KEYWORD_TRAMPLE]--;
	}
	
	public boolean HasTrample(){
		return this.keywordSourceCounts[KEYWORD_TRAMPLE]>0;
	}

	public void AddUnblockable(){
		this.keywordSourceCounts[KEYWORD_UNBLOCKABLE]++;
	}
	
	public void RemoveUnblockable(){
		this.keywordSourceCounts[KEYWORD_UNBLOCKABLE]--;
	}
	
	public boolean HasUnblockable(){
		return this.keywordSourceCounts[KEYWORD_UNBLOCKABLE]>0;
	}

	public void AddVigilance(){
		this.keywordSourceCounts[KEYWORD_VIGILANCE]++;
	}
	
	public void RemoveVigilance(){
		this.keywordSourceCounts[KEYWORD_VIGILANCE]--;
	}
	
	public boolean HasVigilance(){
		return this.keywordSourceCounts[KEYWORD_VIGILANCE]>0;
	}

	public void AddFirstStrike(){
		this.keywordSourceCounts[KEYWORD_FIRSTSTRIKE]++;
	}
	
	public void RemoveFirstStrike(){
		this.keywordSourceCounts[KEYWORD_FIRSTSTRIKE]--;
	}
	
	public boolean HasFirstStrike(){
		return this.keywordSourceCounts[KEYWORD_FIRSTSTRIKE]>0||this.keywordSourceCounts[KEYWORD_DOUBLESTRIKE]>0;
	}

	public void AddDoubleStrike(){
		this.keywordSourceCounts[KEYWORD_DOUBLESTRIKE]++;
	}
	
	public void RemoveDoubleStrike(){
		this.keywordSourceCounts[KEYWORD_DOUBLESTRIKE]--;
	}
	
	public boolean HasDoubleStrike(){
		return this.keywordSourceCounts[KEYWORD_DOUBLESTRIKE]>0;
	}

	public void AddDeathtouch(){
		this.keywordSourceCounts[KEYWORD_DEATHTOUCH]++;
	}
	
	public void RemoveDeathtouch(){
		this.keywordSourceCounts[KEYWORD_DEATHTOUCH]--;
	}
	
	public boolean HasDeathtouch(){
		return this.keywordSourceCounts[KEYWORD_DEATHTOUCH]>0;
	}

	public void AddConvoke(){
		this.keywordSourceCounts[KEYWORD_CONVOKE]++;
	}
	
	public void RemoveConvoke(){
		this.keywordSourceCounts[KEYWORD_CONVOKE]--;
	}
	
	public boolean HasConvoke(){
		return this.keywordSourceCounts[KEYWORD_CONVOKE]>0;
	}

	public void AddIndestructable(){
		this.keywordSourceCounts[KEYWORD_INDESTRUCTABLE]++;
	}
	
	public void RemoveIndestructable(){
		this.keywordSourceCounts[KEYWORD_INDESTRUCTABLE]--;
	}
	
	public boolean HasIndestructable(){
		return this.keywordSourceCounts[KEYWORD_INDESTRUCTABLE]>0;
	}

	public void AddDelve(){
		this.keywordSourceCounts[KEYWORD_DELVE]++;
	}
	
	public void RemoveDelve(){
		this.keywordSourceCounts[KEYWORD_DELVE]--;
	}
	
	public boolean HasDelve(){
		return this.keywordSourceCounts[KEYWORD_DELVE]>0;
	}

	public void AddMorph(){
		this.keywordSourceCounts[KEYWORD_MORPH]++;
	}
	
	public void RemoveMorph(){
		this.keywordSourceCounts[KEYWORD_MORPH]--;
	}
	
	public boolean HasMorph(){
		return this.keywordSourceCounts[KEYWORD_MORPH]>0;
	}
}
