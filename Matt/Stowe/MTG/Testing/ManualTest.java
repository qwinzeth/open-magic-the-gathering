package Matt.Stowe.MTG.Testing;
import Matt.Stowe.MTG.*;
import Matt.Stowe.Common.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;
import Matt.Stowe.MTG.Mechanics.MagicEffects.*;
import Matt.Stowe.MTG.Mechanics.TriggeredAbilities.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class ManualTest extends AnimatedDoubleBufferedWindow{
	private Vector<Player> players;
	private Battle battle;

	public ManualTest(String decktotest){
		this.players=new Vector<Player>();
		this.battle=new Battle(this.players);

		super.InitializeWindow("MTG Manual Test", 800, 600);

		this.players.add(new Player(0, OptionsScreen.CONTROLTYPE_LOCALHUMAN, "H1", true, 30, this.getHeight()/2+15, this.getWidth()-60, this.getHeight()-20));
		this.players.add(new Player(1, OptionsScreen.CONTROLTYPE_LOCALHUMAN, "H2", false, 30, 50, this.getWidth()-60, this.getHeight()/2+15));
		for(int j=0;j<this.players.size();j++){
			Player cplayer=this.players.elementAt(j);
			cplayer.SetDeck(Deck.ReadCardsFromFile(decktotest, cplayer));
		}
		
		this.battle.NewBattle();
		
		for(int j=0;j<this.players.size();j++){
			Player cplayer=this.players.elementAt(j);
			
			Creature test=Creature.NewCreature(cplayer, "TEST", "", new ManaCost(new int[]{ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK}), 1, 1);
			test.AddActivatedAbility(new MagicActivatedAbility(false, null, null, new MagicEffect[]{new MagicEffectAdjustLoyaltyForTarget(cplayer, new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_FLAG_PLANESWALKER), 1)}));
			MagicEffect abilityeffect=MagicEffectExileTargetCards.Create(cplayer, 1, 2, TargetInfo.TARGET_TYPE_FLAG_CREATURE, MagicEffect.Duration.UNTIL_END_OF_TURN);
			abilityeffect.SetTargetToCallUndo(cplayer);
			test.AddActivatedAbility(new MagicActivatedAbility(false, null, null, new MagicEffect[]{abilityeffect}));
			test.GetSubtypes().AddSubtype(SubtypeCollection.SLIVER);
			//test.AddEntersTheBattlefieldTrigger(new TriggeredAbility(new TriggerCondition[]{new TriggerConditionEqualsTargetable(test)}, new MagicEffect[]{MagicEffectAddPTToTargetCreature.Create(cplayer, 1, 1, 1, 1, MagicEffect.Duration.UNTIL_END_OF_TURN)}));
			//test.GetKeywords().AddIndestructable();
			
			//cplayer.PlaceCardInHand(test);
			cplayer.PlacePermanentOnField(test, this.players);

			cplayer.PlacePermanentOnField(Deck.GetCardByName("Plains", cplayer), this.players);
			cplayer.PlacePermanentOnField(Deck.GetCardByName("Island", cplayer), this.players);
			cplayer.PlacePermanentOnField(Deck.GetCardByName("Mountain", cplayer), this.players);
			cplayer.PlacePermanentOnField(Deck.GetCardByName("Swamp", cplayer), this.players);
			cplayer.PlacePermanentOnField(Deck.GetCardByName("Swamp", cplayer), this.players);
			cplayer.PlacePermanentOnField(Deck.GetCardByName("Forest", cplayer), this.players);
			cplayer.PlacePermanentOnField(Deck.GetCardByName("Forest", cplayer), this.players);
			cplayer.PlacePermanentOnField(Deck.GetCardByName("Child of Night", cplayer), this.players);
			cplayer.PlacePermanentOnField(Deck.GetCardByName("Oreskos Swiftclaw", cplayer), this.players);
		}
	}
	
	public static void main(String args[]){
		ManualTest m=new ManualTest("Matt/Stowe/MTG/Testing/Decks/"+(args.length>0?args[0]:"enchant")+".dat");
	}

	public void animate(long elapsedMillis){
		this.battle.animate(elapsedMillis);
	}
	public void mousePressed(MouseEvent me){
		this.battle.mousePressed(me);
	}
	public void mouseReleased(MouseEvent me){
		this.battle.mouseReleased(me);
	}
	public void mouseClicked(MouseEvent me){
		this.battle.mouseClicked(me);
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
	public void keyPressed(KeyEvent ke){
		this.battle.keyPressed(ke);
	}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}

	public void paintDoubleBuffered(Graphics g){
		this.battle.paint(g);
	}
}
