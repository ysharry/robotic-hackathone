package musReader;

public class Action {
  int ActionType;
  float time;
  int LEDCommand;
  public Action(int actionName, float actionTime){
	  ActionType=actionName;
	  time=actionTime;
	  LEDCommand=0;
  }
  
  void setLED(int LED){
	  LEDCommand=LED;
  }
  //getters
  public int getAction(){
	  return ActionType;
  }
  
  public float getTime(){
	  return time;
  }
  
  public int getLED(){
	  return LEDCommand;
  }
}  
