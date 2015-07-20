
import java.util.ArrayList;
import processing.serial.*;
Serial port;
int[] commands = new int[6];
String[] danceMoves;
 /*
 4 - head pos, 180 left, 90 neutral, 0 right
 2 - left arm pos - 0 down, 180 up
 3 - right arm pos - 0 down, 180 up
 0 - left leg speed - 180 max forward, 90 neutral, 0 max reverse
 1 - right leg speed - 180 max forward, 90 neutral, 0 max reverse
 5 - LEDs
 */
int count;
float prevTime = 0;
void setup() {
  commands[0] = 90;
  commands[1] = 90;
  commands[4] = 90;
  count = 0;
  size(256, 150);
  
  danceMoves = loadStrings("asdf.txt");
  for(String x:danceMoves) println(x);
  println("Available serial ports:");
  // if using Processing 2.1 or later, use Serial.printArray()
  println(Serial.list());
  
  // Uses the first port in this list (number 0).  Change this to
  // select the port corresponding to your Arduino board.  The last
  // parameter (e.g. 9600) is the speed of the communication.  It
  // has to correspond to the value passed to Serial.begin() in your
  // Arduino sketch.
  port = new Serial(this, Serial.list()[0], 9600);
 
  // If you know the name of the port used by the Arduino board, you
  // can specify it directly like this.
  //port = new Serial(this, "COM1", 9600);
}
 
void draw() {
  stopMoving();
  
  String[] info = danceMoves[count].split(" ");
  float time = Float.parseFloat(info[0]);
  int actionType = Integer.parseInt(info[1]);
  int LEDAction = Integer.parseInt(info[2]);
  println(actionType);
  doMove(actionType);
  commands[5] = LEDAction;
  for(int x:commands)
    port.write(x);
  delay((int)((time-prevTime)*1000));
  prevTime = time;
  if(danceMoves.length==count++){
    noLoop();
    println("Done");
  }
}
void doMove(int index){
  switch(index){
    case 0:
      stopMoving();
      break;
    case 1:
      rotateHeadLeft();
      break;
    case 2:
      rotateHeadRight();
      break;
    case 3:
      rotateLeft();
      break;
    case 4:
      rotateRight();
      break;
    case 5:
      leftArmUp();
      break;
    case 6:
      leftArmDown();
      break;
    case 7:
      rightArmUp();
      break;
    case 8:
      rightArmDown();
      break;
    case 9:
      forward();
      break;
    case 10:
      backward();
      break;
  }
}

void stopMoving(){
   commands[0] = 90;
   commands[1] = 90;
}
 
void rotateHeadLeft(){
  if(commands[4]<=90)
    commands[4]+=90;
}
void rotateHeadRight(){
  if(commands[4]>=90)
    commands[4]-=90;
}
void rotateLeft(){
  commands[0] = 50;
  commands[1] = 130;
}
void rotateRight(){
  commands[0] = 130;
  commands[1] = 50;
}
void leftArmUp(){
  commands[2] = 180;
}
void leftArmDown(){
  commands[2] = 0;
}
void rightArmUp(){
  commands[3] = 180;
}
void rightArmDown(){
  commands[3] = 0;
}
void forward(){
  commands[0] = 170;
  commands[1] = 170;
}
void backward(){
  commands[0] = 10;
  commands[1] = 10;
}

