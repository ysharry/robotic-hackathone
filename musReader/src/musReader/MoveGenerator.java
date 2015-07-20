package musReader;

import java.util.ArrayList;

public class moveGenerator {
	private ArrayList<Action> actionList = null;
	private ArrayList<Float> beatList = null;
	private int listSize; 
	private int headState;
	private int leftState;
	private int rightState;
	private int bodyState;
	
	public moveGenerator(ArrayList<Float> beatList){
		actionList = new ArrayList<Action>();
		this.beatList = beatList;
		listSize = beatList.size();
	}
	
	public ArrayList<Action> generate(){
		int index = 0;
		while(actionList.size() < listSize){
			int actionType = randomize();
			Action a = new Action(actionType, beatList.get(index));
			actionList.add(a);
			index++;
		}
		return actionList;
	}
		
	private int randomize(){
		int catogory = (int) (Math.random()*5);
		if(catogory == 0){
			int sub = (int)(Math.random()*2);
			if (headState == -1){headState++; return 2;}
			else if (headState == 1){headState--; return 1;}
			else if (sub == 0){headState++; return 2;}
			else if (sub == 1){headState--; return 1;}
		} else if(catogory == 1){
			int sub = (int)(Math.random()*2);
			if (bodyState == -1){bodyState++; return 4;}
			else if (bodyState == 1){bodyState--; return 3;}
			else if (sub == 0){bodyState++; return 4;}
			else if (sub == 1){bodyState--; return 3;}
		} else if(catogory == 2){
			int sub = (int)(Math.random()*2);
			if (leftState == -1){leftState++;return 5;}
			else if (leftState == 1){leftState--; return 6;}
			else if (sub == 0){leftState++;return 5;}
			else if (sub == 1){leftState--; return 6;}
		} else if(catogory == 3){
			int sub = (int)(Math.random()*2);
			if (rightState == -1){rightState++;return 7;}
			else if (rightState == 1){rightState--;return 8;}
			else if (sub == 0){rightState++;return 7;}
			else if (sub == 1){rightState--;return 8;}
		}else {
			int sub = (int)(Math.random()*2);
			if (sub == 0){return 9;}
			else {return 10;}
		}
		return 9;
	}
	
	
}
