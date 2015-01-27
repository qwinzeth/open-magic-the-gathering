package Matt.Stowe.MTG.Testing;
import Matt.Stowe.MTG.Cards.*;

public class ManaCostTests{
	public static void main(String args[]){
		ManaCost mc=new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_COLORLESS});
		
		System.out.println("true?="+mc.MeetsCost(1, 0, 1, 0, 0, 0));
		System.out.println("true?="+mc.MeetsCost(3, 0, 1, 0, 0, 0));
		System.out.println("false?="+mc.MeetsCost(0, 1, 1, 0, 0, 0));
		System.out.println("true?="+mc.MeetsCost(1, 0, 0, 0, 0, 1));
		System.out.println("true?="+mc.MeetsCost(2, 0, 0, 0, 0, 0));
		System.out.println("false?="+mc.MeetsCost(0, 0, 0, 0, 0, 8));
		System.out.println("false?="+mc.MeetsCost(0, 0, 0, 0, 8, 0)+"\n");

		mc=new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED, ManaCost.COLOR_FLAG_GREEN});
		
		System.out.println("false?="+mc.MeetsCost(1, 0, 1, 0, 0, 0));
		System.out.println("false?="+mc.MeetsCost(0, 1, 1, 0, 0, 0));
		System.out.println("false?="+mc.MeetsCost(1, 0, 0, 0, 0, 1));
		System.out.println("false?="+mc.MeetsCost(2, 0, 0, 0, 0, 0));
		System.out.println("false?="+mc.MeetsCost(0, 0, 0, 0, 0, 8));
		System.out.println("false?="+mc.MeetsCost(0, 0, 0, 0, 8, 0));
		System.out.println("true?="+mc.MeetsCost(1, 1, 0, 0, 0, 0)+"\n");
		
		mc=new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_BLUE});
		
		System.out.println("true?="+mc.MeetsCost(1, 1, 0, 0, 0, 0));
		System.out.println("true?="+mc.MeetsCost(1, 0, 1, 0, 0, 0));
		System.out.println("true?="+mc.MeetsCost(0, 1, 1, 0, 0, 0));
		System.out.println("true?="+mc.MeetsCost(2, 0, 0, 0, 0, 0));
		System.out.println("false?="+mc.MeetsCost(0, 2, 0, 0, 0, 0));
		System.out.println("false?="+mc.MeetsCost(0, 0, 2, 0, 0, 0));
		System.out.println("false?="+mc.MeetsCost(1, 0, 0, 0, 0, 1));
		System.out.println("false?="+mc.MeetsCost(1, 0, 0, 1, 1, 1)+"\n");

		mc=new ManaCost(new int[]{ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_GREEN, ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_BLUE, ManaCost.COLOR_FLAG_RED|ManaCost.COLOR_FLAG_WHITE, ManaCost.COLOR_FLAG_WHITE|ManaCost.COLOR_FLAG_BLACK});
		System.out.println("true?="+mc.MeetsCost(0, 1, 1, 1, 1, 0));
		System.out.println("true?="+mc.MeetsCost(0, 1, 1, 2, 0, 0));
		System.out.println("true?="+mc.MeetsCost(3, 0, 0, 0, 1, 0));
		System.out.println("false?="+mc.MeetsCost(4, 0, 0, 0, 0, 0)+"\n");
	}
}