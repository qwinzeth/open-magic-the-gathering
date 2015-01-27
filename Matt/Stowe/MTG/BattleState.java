package Matt.Stowe.MTG;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Transferrable.*;

import java.util.*;

public class BattleState{
	public CardBase[] Hand;
	public ReadOnlyPlayer[] Players;
	
	public BattleState(int myindex, Vector<Player> players){
		Vector<CardBase> hand=players.get(myindex).GetHand();
		this.Hand=new CardBase[hand.size()];
		for(int i=0;i<hand.size();i++)
			this.Hand[i]=hand.elementAt(i).DeepCopy();
		
		this.Players=new ReadOnlyPlayer[players.size()];
		for(int p=0;p<this.Players.length;p++){
			Player cplayer=players.elementAt(p);
			CardBase[] cplayerhand=null;
			if(myindex==p){
				cplayerhand=this.Hand;
			}else{
				cplayerhand=new CardBase[cplayer.GetHandSize()];
				for(int i=0;i<cplayerhand.length;i++){
					cplayerhand[i]=new FaceDownCard(cplayer);
				}
			}
			Vector<CardBase> cplayerfieldvector=cplayer.GetField();
			CardBase[] cplayerfield=new CardBase[cplayerfieldvector.size()];
			for(int i=0;i<cplayerfield.length;i++){
				cplayerfield[i]=cplayerfieldvector.elementAt(i).DeepCopy();
			}
			Vector<CardBase> cplayergraveyardvector=cplayer.GetGraveyard();
			CardBase[] cplayergraveyard=new CardBase[cplayergraveyardvector.size()];
			for(int i=0;i<cplayergraveyard.length;i++){
				cplayergraveyard[i]=cplayergraveyardvector.elementAt(i).DeepCopy();
			}
			ArrayList<CardBase> cplayerexilevector=cplayer.GetExile();
			CardBase[] cplayerexile=new CardBase[cplayerexilevector.size()];
			for(int i=0;i<cplayerexile.length;i++){
				cplayerexile[i]=cplayerexilevector.get(i).DeepCopy();
			}
			
			this.Players[p]=new ReadOnlyPlayer(cplayer.GetName(), cplayer.GetTargetEndpointX(), cplayer.GetTargetEndpointY(), cplayer.GetDeckSize(),
				cplayerhand, cplayerfield, cplayergraveyard, cplayerexile,
				cplayer.GetAreaMinX(), cplayer.GetAreaMinY(), cplayer.GetAreaMaxX(), cplayer.GetAreaMaxY());
		}
	}
}
