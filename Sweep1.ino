/* Sweep
 by BARRAGAN <http://barraganstudio.com> 
 This example code is in the public domain.

 modified 8 Nov 2013
 by Scott Fitzgerald
 http://arduino.cc/en/Tutorial/Sweep
*/ 

#include <Servo.h> 
 
Servo FreeRot1;  // create servo object to control a servo 
Servo FreeRot2;                // twelve servo objects can be created on most boards
Servo servo1;
Servo servo2;
Servo servo3;
int vel = 1;    // variable to store the servo position 
int vel2 = 180;
int pos = 0;
 
void setup() 
{ 
  FreeRot1.attach(8);
  FreeRot2.attach(9);  // attaches the servo on pin 10 to the servo object 
  servo1.attach(10); 
  servo2.attach(11);
  servo3.attach(12);
} 
 
void loop() 
{ 
                // in steps of 1 degree 
    for (pos = 0; pos <180 ; pos++)
    {
      servo1.write(pos);
      servo2.write(pos/2);
      servo3.write(pos/3);
      delay(15);
    }
     
    for (pos = 180; pos>0; pos--)
   {
      servo1.write(pos);
      servo2.write(pos/2);
      servo3.write(pos/3);
      delay(15);
   } 

    
} 
