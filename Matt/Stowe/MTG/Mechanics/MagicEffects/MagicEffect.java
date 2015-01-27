package Matt.Stowe.MTG.Mechanics.MagicEffects;
import Matt.Stowe.MTG.*;
import Matt.Stowe.MTG.Cards.*;
import Matt.Stowe.MTG.Mechanics.*;

import java.util.*;

public abstract class MagicEffect{
	protected CardBase source;
	public CardBase GetSource(){return this.source;}
	public void SetSource(CardBase newSource){this.source=newSource;}

	private ITargetable targetToCallUndo;
	public void SetTargetToCallUndo(ITargetable target){
		if(this.targetToCallUndo==null)
			this.targetToCallUndo=target;
	}

	protected Player controller;
	public Player GetController(){return this.controller;}
	public String GetControllerName(){return this.controller.GetName();}
	
	protected Duration duration;
	
	public enum Duration{
		NA,
		UNTIL_END_OF_TURN,
		UNTIL_CONTROL_LOST,
		UNTIL_LEAVES_FIELD
	}
	
	private MagicEffect ifThisThenThat;
	public void SetDependentEffect(MagicEffect dependent){this.ifThisThenThat=dependent;}
	private boolean shouldBePutOnStack;
	public void SetShouldBePutOnStack(boolean putit){
		this.shouldBePutOnStack=putit;
	}
	
	private boolean optional;
	public void SetOptional(){this.optional=true;}
	public boolean IsOptional(){return this.optional;}
	public boolean ShouldBePutOnStack(){
		if(!this.optional)
			return this.shouldBePutOnStack;
		boolean wantsToTrigger=this.controller.WantsToTriggerEffect(this);
		if(this.ifThisThenThat!=null){
			this.ifThisThenThat.SetShouldBePutOnStack(wantsToTrigger);
		}
		return wantsToTrigger;
	}
	
	public TargetInfo TargetData;
	
	private ArrayList<ManaCost> extraManaCosts;
	public void AddExtraManaCost(ManaCost extracost){this.extraManaCosts.add(extracost);}
	public void RemoveExtraManaCost(ManaCost extracost){this.extraManaCosts.remove(extracost);}
	public ArrayList<ManaCost> GetExtraManaCosts(){return this.extraManaCosts;}
	
	protected class DeepCopyInfo{
		public CardBase source;
		public ITargetable targetToCallUndo;
		public Player controller;
		public Duration duration;
		public boolean optional;
		public MagicEffect ifThisThenThat;
		public boolean shouldBePutOnStack;
		public TargetInfo TargetData;
		public ArrayList<ManaCost> extraManaCosts;
		
		public DeepCopyInfo(CardBase source, ITargetable targetToCallUndo, Player controller, Duration duration, boolean optional, MagicEffect ifThisThenThat,
		boolean shouldBePutOnStack, TargetInfo targetData, ArrayList<ManaCost> extraManaCosts){
			this.source=source;
			this.targetToCallUndo=targetToCallUndo;
			this.controller=controller;
			this.duration=duration;
			this.optional=optional;
			this.ifThisThenThat=ifThisThenThat;
			this.shouldBePutOnStack=shouldBePutOnStack;
			this.TargetData=targetData;
			this.extraManaCosts=extraManaCosts;
		}
	}
	
	protected DeepCopyInfo GetDeepCopyInfo(){
		ArrayList<ManaCost> extraManaCostsDeepCopy=new ArrayList<ManaCost>();
		for(int i=0;i<this.extraManaCosts.size();i++){
			extraManaCostsDeepCopy.add(this.extraManaCosts.get(i));
		}
	
		return new DeepCopyInfo(this.source, this.targetToCallUndo, this.controller, this.duration, this.optional, this.ifThisThenThat, this.shouldBePutOnStack,
		this.TargetData.DeepCopy(), extraManaCostsDeepCopy);
	}
	
	public MagicEffect(Player controller, TargetInfo targetinfo, Duration duration){
		this.controller=controller;
		this.TargetData=targetinfo;
		this.duration=duration;
		this.optional=false;
		this.extraManaCosts=new ArrayList<ManaCost>();
		this.shouldBePutOnStack=true;
	}

	protected MagicEffect(DeepCopyInfo dcinfo){
		this.source=dcinfo.source;
		this.targetToCallUndo=dcinfo.targetToCallUndo;
		this.controller=dcinfo.controller;
		this.duration=dcinfo.duration;
		this.optional=dcinfo.optional;
		this.ifThisThenThat=dcinfo.ifThisThenThat;
		this.shouldBePutOnStack=dcinfo.shouldBePutOnStack;
		this.TargetData=dcinfo.TargetData;
		this.extraManaCosts=dcinfo.extraManaCosts;
	}
	
	protected void handleDuration(){
		if(this.duration==Duration.NA)
			return;
		if(this.targetToCallUndo==null){
			int targets=0;
			while(this.TargetData.GetTarget(targets)!=null){
				targets++;
			}
			if(targets!=1){
				for(int targetIndex=0;targetIndex<targets;targetIndex++){
					ITargetable ctarget=this.TargetData.GetTarget(targetIndex);
					MagicEffect deepCopyEffect=this.DeepCopy();
					deepCopyEffect.TargetData=new TargetInfo(1, 1, TargetInfo.TARGET_TYPE_ANY);
					deepCopyEffect.TargetData.SetLockedTarget(0, ctarget);
					switch(this.duration){
					case UNTIL_END_OF_TURN:
						ctarget.UndoAtEndOfTurn(deepCopyEffect);
					break;
					case UNTIL_CONTROL_LOST:
						ctarget.UndoWhenControlIsLost(deepCopyEffect);
					break;
					case UNTIL_LEAVES_FIELD:
						ctarget.UndoWhenLeavesBattlefield(deepCopyEffect);
					break;
					}
				}
				return;
			}else{
				this.targetToCallUndo=this.TargetData.GetTarget(0);
			}
		}

		switch(this.duration){
		case UNTIL_END_OF_TURN:
			this.targetToCallUndo.UndoAtEndOfTurn(this);
		break;
		case UNTIL_CONTROL_LOST:
			this.targetToCallUndo.UndoWhenControlIsLost(this);
		break;
		case UNTIL_LEAVES_FIELD:
			this.targetToCallUndo.UndoWhenLeavesBattlefield(this);
		break;
		}
	}
	
	public boolean AnyTargetCanBeTargeted(){
		for(int targetIndex=0;this.TargetData.GetTarget(targetIndex)!=null;targetIndex++){
			if(this.TargetData.GetTarget(targetIndex).CanBeTargetedBy(this.source))
				return true;
		}
		
		return false;
	}
	
	public boolean TargetsCanBeTargeted(){
		for(int targetIndex=0;this.TargetData.GetTarget(targetIndex)!=null;targetIndex++){
			if(!this.TargetData.GetTarget(targetIndex).CanBeTargetedBy(this.source))
				return false;
		}
		
		return true;
	}
	
	public boolean RemoveInvalidTargets(){
		for(int targetIndex=0;this.TargetData.GetTarget(targetIndex)!=null;targetIndex++){
			if(!this.TargetData.GetTarget(targetIndex).CanBeTargetedBy(this.source)){
				this.TargetData.SetTarget(targetIndex, null);
				for(int shiftTargetIndex=targetIndex+1;this.TargetData.GetTarget(shiftTargetIndex)!=null;shiftTargetIndex++){
					this.TargetData.SetTarget(shiftTargetIndex-1, this.TargetData.GetTarget(shiftTargetIndex));
					this.TargetData.SetTarget(shiftTargetIndex, null);
				}
			}
		}
		
		return true;
	}

	public static boolean HasValidTarget(MagicEffect[] effects, IPlayer[] players, Stack<MagicStackElement> thestack){
		boolean hasValidTarget=true;
		
		if(effects!=null){
			for(int effectIndex=0;hasValidTarget&&effectIndex<effects.length;effectIndex++){
				for(int targetIndex=0;hasValidTarget&&targetIndex<effects[effectIndex].TargetData.GetRequiredTargetCount();targetIndex++){
					hasValidTarget=effects[effectIndex].TargetData.NeedsX();
					for(int playerIndex=0;!hasValidTarget&&playerIndex<players.length;playerIndex++){
						if(effects[effectIndex].TargetData.IsValidTarget(players[playerIndex].GetITargetable())){
							hasValidTarget=true;
						}else{
							ArrayList<CardBase> cplayerscards=players[playerIndex].GetAllCards();
							for(int fieldIndex=0;!hasValidTarget&&fieldIndex<cplayerscards.size();fieldIndex++){
								hasValidTarget=effects[effectIndex].TargetData.IsValidTarget(cplayerscards.get(fieldIndex));
							}
							
							if(thestack!=null){
								for(int stackIndex=0;!hasValidTarget&&stackIndex<thestack.size();stackIndex++){
									CardBase ccard=thestack.get(stackIndex).CardToPlay;
									if(ccard!=null)
										hasValidTarget=effects[effectIndex].TargetData.IsValidTarget(ccard);
								}
							}
						}
					}
				}
			}
		}
		return hasValidTarget;
	}
	
	public void MakeRequiredDecisions(){}
	
	public static void UsePlayerInput(MagicEffect[] effects, int[][] targetCardControllerIndexes, int[][] targetFieldIndexes, Vector<CardBase> hand, Vector<Player> players){
		if(effects==null)
			return;
		Stack<MagicStackElement> thestack=players.get(0).TheStack;
		for(int effectIndex=0;effectIndex<effects.length;effectIndex++){
			effects[effectIndex].MakeRequiredDecisions();
//System.out.println("effect: "+effects[effectIndex].GetRulesText()+" has made decisions.");
			if(targetCardControllerIndexes==null||targetFieldIndexes[effectIndex]==null)
				continue;
//System.out.println("Nonnull target indexes...");
			if(targetCardControllerIndexes[effectIndex]!=null&&targetFieldIndexes[effectIndex]!=null){
				for(int targetIndex=0;targetIndex<effects[effectIndex].TargetData.GetMaxTargetCount();targetIndex++){
//System.out.println("targetCardControllerIndexes[effectIndex][targetIndex]="+targetCardControllerIndexes[effectIndex][targetIndex]+", targetFieldIndexes[effectIndex]="+targetFieldIndexes[effectIndex][targetIndex]);
					if(targetCardControllerIndexes[effectIndex][targetIndex]==-1){
						continue;
					}
					
					if(targetFieldIndexes[effectIndex][targetIndex]!=-1){
						if(effects[effectIndex].TargetData.IsValidZone(ZoneOptions.BATTLEFIELD)){
							effects[effectIndex].TargetData.SetTarget(targetIndex,
								players.elementAt(targetCardControllerIndexes[effectIndex][targetIndex])
								.GetField().elementAt(targetFieldIndexes[effectIndex][targetIndex]));
						}else if(effects[effectIndex].TargetData.IsValidZone(ZoneOptions.GRAVEYARD)){
							effects[effectIndex].TargetData.SetTarget(targetIndex,
								players.elementAt(targetCardControllerIndexes[effectIndex][targetIndex])
								.GetGraveyard().elementAt(targetFieldIndexes[effectIndex][targetIndex]));
						}else if(effects[effectIndex].TargetData.IsValidZone(ZoneOptions.HAND)){
							effects[effectIndex].TargetData.SetTarget(targetIndex,
								players.elementAt(targetCardControllerIndexes[effectIndex][targetIndex])
								.GetHand().elementAt(targetFieldIndexes[effectIndex][targetIndex]));
						}else if(effects[effectIndex].TargetData.IsValidZone(ZoneOptions.EXILE)){
							effects[effectIndex].TargetData.SetTarget(targetIndex,
								players.elementAt(targetCardControllerIndexes[effectIndex][targetIndex])
								.GetExile().get(targetFieldIndexes[effectIndex][targetIndex]));
						}else if(effects[effectIndex].TargetData.IsValidZone(ZoneOptions.STACK)){
							effects[effectIndex].TargetData.SetTarget(targetIndex, thestack.get(targetFieldIndexes[effectIndex][targetIndex]).CardToPlay);
						}
					}else{
						effects[effectIndex].TargetData.SetTarget(targetIndex,
							players.elementAt(targetCardControllerIndexes[effectIndex][targetIndex]));
					}
				}
			}
		}
	}

	public boolean CanPayAsCost(){return true;}
	
	public void SetX(int x){}
	
	public abstract ArrayList<MagicEffect[]> Resolve(Vector<Player> players);
	
	public abstract MagicEffect DeepCopy();
	
	public ArrayList<MagicEffect[]> Undo(Vector<Player> players){return new ArrayList<MagicEffect[]>();}
	
	public abstract String GetRulesText();

	protected String getExtraCostsString(){
		StringBuilder extraManaCostsString=new StringBuilder();
		for(int i=0;i<this.extraManaCosts.size();i++){
			extraManaCostsString.append("If you pay "+this.extraManaCosts.get(i).toString()+", ");
		}
		return extraManaCostsString.toString();
	}
	
	protected String getDurationString(){
		switch(this.duration){
		case UNTIL_END_OF_TURN:
			return "until end of turn";
		case UNTIL_CONTROL_LOST:
			if(targetToCallUndo!=null){
				return "until control of "+targetToCallUndo.GetName()+" is lost";
			}else{
				return "until control is lost";
			}
		case UNTIL_LEAVES_FIELD:
			if(targetToCallUndo!=null){
				return "until "+targetToCallUndo.GetName()+" leaves the battlefield";
			}else{
				return "until this card leaves the battlefield";
			}
		}
		
		return "";
	}
}
