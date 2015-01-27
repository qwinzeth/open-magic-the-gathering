package Matt.Stowe.MTG.Mechanics;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;

import java.util.*;

public interface ITargetable{
	public String GetName();
	public int GetTargetTypeFlags();
	public int GetColorFlags();
	public int GetTargetEndpointX();
	public int GetTargetEndpointY();
	public int MarkDamage(int damage, int damageTypeFlags, CardBase source);
	public void AddDamagePreventor(DamagePreventor dmgprev);
	public void RemoveDamagePreventor(DamagePreventor dmgprev);
	public void AddCantBeTargetedBy(TargetInfo info);
	public void RemoveCantBeTargetedBy(TargetInfo info);
	public boolean CanBeTargetedBy(ITargetable source);
	public void UndoAtEndOfTurn(MagicEffect effect);
	public void UndoWhenControlIsLost(MagicEffect effect);
	public void UndoWhenLeavesBattlefield(MagicEffect effect);
}