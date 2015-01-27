package Matt.Stowe.MTG;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.MagicStackElement;
import Matt.Stowe.MTG.Mechanics.MagicEffects.MagicEffect;

import java.awt.Graphics;
import java.util.*;

public interface IPlayerInterface{
	public String GetControlTypeString();
	public boolean wantsToMulligan(BattleState bs);
	public void timerStarted(BattleState bs, Stack<MagicStackElement> theStack);
	public PlayerAction handleInstants();
	public void timerEnded();
	public PlayerAction handleMainPhaseOne(BattleState bs);
	public PlayerAction handleAttackDecision(BattleState bs);
	public PlayerAction declareBlockers(BattleState bs, Creature[] attackers);
	public PlayerAction GetEffectsTargets(BattleState bs, int fieldIndex, MagicEffect[] effects);
	public void updateBattleState(BattleState bs);
	public PlayerAction PayManaCosts(BattleState bs, ManaCost cost);
	public int[] GetIndexesToPutOnBottom(CardBase[] cards);
	public void OrderCards(CardBase[] cards, int[] orderIndexes);
	public int[] ChooseCards(CardBase[] options, int minToChoose, int maxToChoose, String choiceDescription);
	public boolean ChooseYesOrNo(String queryText);
	public boolean WantsToTriggerEffect(MagicEffect effect);
	public int GetColorChoice(MagicEffect effect, int colorflags);
	public PlayerAction ChoosePlayerIndex(BattleState bs, int invalidPlayerIndex, String choiceDescription);
	public void CardRevealed(CardBase card);
	public void HandRevealed(CardBase[] hand, int playerIndex);
	public void animate(long elapsedMillis);
	public void mousePressed(int mx, int my);
	public void mouseReleased(int mx, int my);
	public void mouseClicked(int mx, int my);
	public void keyPressed(int keyCode);
	public void paintFullViewCard(Graphics g);
	public void paint(Graphics g);
}
