#include <Servo.h>

const int leg1Pin = 8;

Servo leg1Servo;

int leg1Velocity = 90;

int command[1] = {90};

void setup(){
    Serial.begin(9600);
    leg1Servo.attach(leg1Pin);
    leg1Servo.write(leg1Velocity);
}

void loop(){
    while (Serial.available() > 0){
        for (int i = 0; i < 1; i++){
            command[i] = Serial.parseInt();
        }
    }
    Serial.println(command[0]);
}
            
