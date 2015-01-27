package Matt.Stowe.MTG.Mechanics.ContinuousAbilities;
import Matt.Stowe.MTG.*;

import java.util.Vector;

public interface IContinuousCondition{
	boolean MeetsCondition(Vector<Player> players, int controllerIndex);
	
	IContinuousCondition DeepCopy();
	
	String GetRulesText();
}
