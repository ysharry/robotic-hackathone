// I haven't tested this code, so there are probably still bugs in it. But it should hopefully be okay?
// if not, I'll try to be here on time. good luck.

#include <Servo.h>

const int leg1 = 8;
const int leg2 = 9;
const int arm1 = 10;
const int arm2 = 11;
const int head = 12;
const int redLED = 13;
const int greenLED = 14;
const int blueLED = 15;

Servo leg1Servo;
Servo leg2Servo;
Servo arm1Servo;
Servo arm2Servo;
Servo headServo;

int leg1Velocity = 90;
int leg2Velocity = 90;
int arm1Pos = 0;
int arm2Pos = 0;
int headPos = 0;

void setup() {
    // initialize serial:
    Serial.begin(9600);
    leg1Servo.attach(leg1);
    leg2Servo.attach(leg2);
    arm1Servo.attach(arm1);
    arm2Servo.attach(arm2);
    headServo.attach(head);
    leg1Servo.write(leg1Velocity);
    leg2Servo.write(leg2Velocity);
    arm1Servo.write(arm1Pos);
    arm2Servo.write(arm2Pos);
    headServo.write(headPos);
}

void loop() {
    int command[10] = {0,0,0,0,0,0,0,0,0,0};
    int read = 0;
    // read until there aren't any more things to read
    // mod read by the command array size, 1 for testing
    // restructure variables as needed
    while (Serial.available() > 0) {
        command[read] = Serial.parseInt();
        // safety feature to break out of the loop
        if (read >= 10){
            break;
        }
    }
    while (true){
      //Serial.print(command[0]);
        // if there's a difference between current velocity/position,
        // update each item in order, and then delay all. Can add separate
        // delays later with something like booleans to control whether
        // an item should update at any given repetition or something
        // you cs kids figure that out.
        
        // leg 1, roomba motor
        if (leg1Velocity != command[0]){
            if (leg1Velocity < command[0]){
                leg1Servo.write(++leg1Velocity);
            }
            else if (leg1Velocity > command[0]){
                leg1Servo.write(--leg1Velocity);
            }
        }
        // leg 2, roomba motor
        if (leg2Velocity != command[1]){
            if (leg2Velocity < command[1]){
                leg2Servo.write(++leg2Velocity);
            }
            else if (leg2Velocity > command[1]){
                leg2Servo.write(--leg2Velocity);
            }
        }
        
        // arm 1, positional servo
        if (arm1Pos != command[2]){
            if (arm1Pos < command[2]){
                arm1Servo.write(++arm1Pos);
            }
            else if (arm1Pos > command[2]){
                arm1Servo.write(--arm1Pos);
            }
        }
        
        
        // arm 2, positional servo
        if (arm2Pos != command[3]){
            if (arm2Pos < command[3]){
                arm2Servo.write(++arm2Pos);
            }
            else if (arm2Pos > command[3]){
                arm2Servo.write(--arm2Pos);
            }
        }
        
        // head, positional servo
        if (headPos != command[4]){
            if (headPos < command[4]){
                headServo.write(++headPos);
            }
            else if (headPos > command[4]){
                headServo.write(--headPos);
            }
        }
        
        //should probably add a solenoid here or something to make the entire robot bounce
        
        // if ALL of the things match what they were told to do, then break and read the next command
        // I really hope it gets here and doesn't crash
        if (leg1Velocity == command[0] && leg2Velocity == command[1] && arm1Pos == command[2] && arm2Pos == command[3] && headPos == command[4]){
           Serial.print("Finished"); 
           break;
        }
        
        // and then finally do the delay here to make everything not super jumpy
        delay(15);
    }
}
