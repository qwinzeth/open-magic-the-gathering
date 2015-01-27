package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public class MagicEffectAddManaToPool extends MagicEffect{
	private int colorless, forests, mountains, islands, swamps, plains;
	private int requiredColorFlags;
	public void SetRequiresColorDecision(int flags){
		this.requiredColorFlags=flags;
	}
	
	private MagicEffectAddManaToPool(DeepCopyInfo dcinfo, int mountains, int forests, int islands, int plains, int swamps, int colorless, int requiredColorFlags){
		super(dcinfo);
		this.colorless=colorless;
		this.forests=forests;
		this.mountains=mountains;
		this.islands=islands;
		this.swamps=swamps;
		this.plains=plains;
		this.requiredColorFlags=requiredColorFlags;
	}
	
	public MagicEffectAddManaToPool(Player controller, int mountains, int forests, int islands, int plains, int swamps, int colorless){
		super(controller, new TargetInfo(), Duration.NA);
		this.colorless=colorless;
		this.forests=forests;
		this.mountains=mountains;
		this.islands=islands;
		this.swamps=swamps;
		this.plains=plains;
		this.requiredColorFlags=-1;
	}
	
	public static MagicEffectAddManaToPool CreateRequiresColorDecision(Player controller, int colorflags){
		MagicEffectAddManaToPool needsColor=new MagicEffectAddManaToPool(controller, 0, 0, 0, 0, 0, 0);
		needsColor.SetRequiresColorDecision(colorflags);
		return needsColor;
	}
	
	public ArrayList<MagicEffect[]> Resolve(Vector<Player> players){
		if(this.requiredColorFlags>-1){
			int chosenColor=this.controller.GetColorChoice(this, this.requiredColorFlags);
			switch(chosenColor){
			case ManaCost.COLOR_FLAG_RED:this.mountains++;break;
			case ManaCost.COLOR_FLAG_GREEN:this.forests++;break;
			case ManaCost.COLOR_FLAG_BLUE:this.islands++;break;
			case ManaCost.COLOR_FLAG_WHITE:this.plains++;break;
			case ManaCost.COLOR_FLAG_BLACK:this.swamps++;break;
			case ManaCost.COLOR_FLAG_COLORLESS:this.colorless++;break;
			default:
				System.out.println("Invalid color chosen: "+chosenColor);
			}
		}
	
		this.controller.AddManaToPool(this.mountains, this.forests, this.islands, this.plains, this.swamps, this.colorless);
		return new ArrayList<MagicEffect[]>();
	}
	
	public MagicEffect DeepCopy(){
		return new MagicEffectAddManaToPool(this.GetDeepCopyInfo(), this.mountains, this.forests, this.islands, this.plains, this.swamps, this.colorless, this.requiredColorFlags);
	}

	public String GetRulesText(){
		if(this.requiredColorFlags>-1){
			return "Add "+ManaCost.GetColorNames(this.requiredColorFlags, " or ")+" to mana pool.";
		}
		return "Add "+(this.mountains>0?this.mountains+"R ":"")+(this.forests>0?this.forests+"G ":"")+(this.islands>0?this.islands+"U ":"")+(this.plains>0?this.plains+"W ":"")+ (this.swamps>0?this.swamps+"B ":"")+(this.colorless>0?this.colorless+"C ":"")+"to mana pool.";
	}
}