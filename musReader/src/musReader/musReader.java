package musReader;

import java.io.*;
import java.util.ArrayList;

public class musReader {
  public static void main(String args[]){
	  musfile data=new musfile("badBoy_onset.txt","badBoy_pitch.txt");
	  data.readBeat();
	  data.readPitch();
	  data.processBeat();
	  data.processPitch();
	  ArrayList<Action> actionList=actionInterpreter(data);
	  writeLEDAction(actionList,data.getLEDPattern());
	  
	  
	  writeToFile(actionList);
	  //interpreter functions -> Arraylist<action object>
	  //call Arduino
	  //end
	  
  }
  
/*
 * function enum
 * stopMoving()  0
 * rotateHeadLeft() 1
 * rotateHeadRight() 2
 * rotateLeft() 3
 * rotateRight() 4
 * leftArmUp() 5
 * leftArmDown() 6
 * rightArmUp() 7
 * rightArmDown() 8
 * forward() 9
 * backward() 10
 */
  public static ArrayList<Action> actionInterpreter(musfile a){
	  //takes in a musfile
	  //translate musfile into Action objects
	  //returns an ArrayList of Action Objects
	  moveGenerator interpreter= new moveGenerator(a.getBeat());
	  return interpreter.generate();
  }
  
  public static void writeLEDAction(ArrayList<Action> actionList, ArrayList<Integer> LED){
	  for (Action action:actionList){
		  action.setLED(LED.get(actionList.indexOf(action)));
	  }
  }
  
  public static void writeToFile(ArrayList<Action> list){
	  try{
		  FileWriter fw = new FileWriter("");
		  BufferedWriter bf = new BufferedWriter(fw);
		  for(int i = 0; i < list.size(); i++){
			  bf.write(Character.toChars(i));
			  bf.write(" ");
			  bf.write(String.valueOf(list.get(i).getTime()));
			  bf.write(" ");
			  bf.write(list.get(i).getAction());
			  bf.write(" ");
			  bf.write(list.get(i).getLED());
			  bf.newLine();
		  }
		  
	  } catch(Exception e){
		  System.out.println(e);
	  } 
  }
  
}
