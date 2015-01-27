package Matt.Stowe.Common;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class GUIFileSelecter{
	private String path;
	private GUIButton btnSelect;
	private GUIButton[] btnFiles;
	private File[] files;
	private int boxX;
	private int boxY;
	private int boxWidth;
	private int boxHeight;
	private File currentDirectory;
	private boolean allowNewFiles;
	private SelectionState state;
	private GUITextInput inputNewFileName;
	
	public String GetSelectedFilePath(){
		return this.path;
	}
	
	private enum SelectionState{
		CHOOSE_A_FILE,
		CHOOSING_FILE,
		FILE_CHOSEN,
		CREATING_NEW_FILE
	}
	
	public GUIFileSelecter(String currentDirectory, boolean allowNew, int x, int y){
		this.btnSelect=new GUIButton(x, y, 150, 16, "Choose a file...");
		this.state=SelectionState.CHOOSE_A_FILE;
		this.boxX=x;
		this.boxY=y;
		this.boxWidth=200;
		this.currentDirectory=new File(currentDirectory);
		this.path=null;
		this.allowNewFiles=allowNew;
	}
	
	public void ChoosingFile(){
		this.path=null;
		this.files=this.currentDirectory.listFiles();
		this.boxHeight=this.files.length*18;
		this.btnFiles=new GUIButton[this.allowNewFiles?this.files.length+1:this.files.length];
		for(int i=0;i<this.files.length;i++){
			this.btnFiles[i]=new GUIButton(this.boxX+2, this.boxY+2+18*i, 146, 16, this.files[i].getName());
		}
		if(this.allowNewFiles)
			this.btnFiles[this.files.length]=new GUIButton(this.boxX+2, this.boxY+2+18*this.files.length, 146, 16, "New...");
		this.state=SelectionState.CHOOSING_FILE;
	}
	
	public void SelectFile(int index){
		if(this.files==null){
			this.ChoosingFile();
		}
		this.path=this.files[index].getAbsolutePath();
		this.btnSelect.setText(this.files[index].getName());
		this.btnFiles=null;
		this.files=null;
		this.state=SelectionState.FILE_CHOSEN;
	}
	
	public boolean IsOpen(){
		return this.state==SelectionState.CHOOSING_FILE||this.state==SelectionState.CREATING_NEW_FILE;
	}
	
	public void Reset(){
		this.btnSelect.setText("Choose a file...");
		this.state=SelectionState.CHOOSE_A_FILE;
		this.path=null;
	}
	
	public void keyPressed(KeyEvent ke){
		if(this.state==SelectionState.CREATING_NEW_FILE){
			if(KeyEvent.VK_ENTER==ke.getKeyCode()&&this.inputNewFileName.GetText().length()>0){
				this.path=this.currentDirectory+"/"+this.inputNewFileName.GetText();
				this.btnSelect.setText(this.inputNewFileName.GetText());
				this.inputNewFileName=null;
				this.btnFiles=null;
				this.files=null;
				try{
					new File(this.path).createNewFile();
					this.state=SelectionState.FILE_CHOSEN;
				}catch(IOException ioe){
					ioe.printStackTrace();
					this.path=null;
					this.state=SelectionState.CHOOSE_A_FILE;
				}
			}else if(KeyEvent.VK_ESCAPE==ke.getKeyCode()){
				this.btnFiles=null;
				this.files=null;
				this.state=SelectionState.CHOOSE_A_FILE;
			}else{
				this.inputNewFileName.keyPressed(ke);
			}
		}
	}
	
	public void mousePressed(MouseEvent me){
		this.btnSelect.handleMousePress(me.getX(), me.getY());
		if(this.btnFiles!=null){
			for(int i=0;i<this.btnFiles.length;i++){
				this.btnFiles[i].handleMousePress(me.getX(), me.getY());
			}
		}
	}

	public void mouseReleased(MouseEvent me){
		this.btnSelect.handleMouseRelease(me.getX(), me.getY());
		if(this.btnFiles!=null){
			for(int i=0;i<this.btnFiles.length;i++){
				this.btnFiles[i].handleMouseRelease(me.getX(), me.getY());
			}
		}
	}
	
	public void mouseClicked(int mx, int my){
		switch(this.state){
		case CHOOSE_A_FILE:
		case FILE_CHOSEN:
			if(this.btnSelect.IsClicked(mx, my)){
				this.ChoosingFile();
			}
		break;
		case CHOOSING_FILE:
			for(int i=0;i<this.files.length;i++){
				if(this.btnFiles[i].IsClicked(mx, my)){
					this.SelectFile(i);
					return;
				}
			}
			if(this.allowNewFiles){
				if(this.btnFiles[this.files.length].IsClicked(mx, my)){
					this.state=SelectionState.CREATING_NEW_FILE;
					this.btnFiles[this.files.length].Visible=false;
					this.inputNewFileName=new GUITextInput(this.btnFiles[this.files.length].GetX(), this.btnFiles[this.files.length].GetY(), 146, 16);
					return;
				}
			}
			this.btnFiles=null;
			this.state=SelectionState.FILE_CHOSEN;
		break;
		}
	}
	
	public void paint(Graphics g){
		this.btnSelect.paint(g);
		
		if(this.btnFiles!=null){
			for(int i=0;i<btnFiles.length;i++){
				this.btnFiles[i].paint(g);
			}
		}
		
		if(this.state==SelectionState.CREATING_NEW_FILE){
			this.inputNewFileName.paint(g);
		}
	}
}