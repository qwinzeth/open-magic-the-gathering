package Matt.Stowe.MTG;

import java.util.ArrayList;

public class ChoiceSelector{
	private int sourceIndex;
	private ArrayList<Integer> choices;
	public ArrayList<Integer> GetChoicesArrayList(){return this.choices;}
	
	private boolean selecting;
	
	public ChoiceSelector(){
		this.sourceIndex=-1;
		this.choices=new ArrayList<Integer>();
		this.selecting=false;
	}

	public int GetSourceIndex(){return this.sourceIndex;}
	
	public void ToggleSelection(int index){
		if(!this.choices.remove(new Integer(index))){
			this.choices.add(new Integer(index));
		}
	}
	
	public boolean IsSelected(int index){
		return this.choices.contains(index);
	}
	
	public int[] GetChoices(){
		if(this.sourceIndex==-1)
			return null;
	
		int[] retval=new int[this.choices.size()];
		for(int i=0;i<retval.length;i++)
			retval[i]=this.choices.get(i);
		return retval;
	}
	
	public boolean IsSelecting(){return this.selecting;}
	
	public void StartSelecting(int sourceIndex){
		this.sourceIndex=sourceIndex;
		this.choices.clear();
		this.selecting=true;
	}
	
	public void StopSelecting(){
		this.selecting=false;
	}
	
	public void Reset(){
		this.sourceIndex=-1;
		this.choices.clear();
		this.selecting=false;
	}
}