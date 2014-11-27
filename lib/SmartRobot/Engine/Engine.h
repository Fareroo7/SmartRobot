/*
  EngineTask.h - Library for the SmartRobot.
  Created by Dominik Simon, November 21, 2014.
*/

#ifndef Engine_h
#define Engine_h

#include "Arduino.h"
//This class represents a Engine of the SmartRobot.
//It handle the needed pins for controlling one Engine.
//Also provide some function to controll one Engine.
class Engine
{
 public:
  //Constructs a new empty Engine. (Used to declare "placeholder").
  Engine();
  
  //Constructs a new Engine.
  //Parameter:
  //  pinOne => first Input-Pin.
  //  pinTwo => second Input-Pin.
  //  pinDis => Disable-Pin.
  Engine(int pinOne, int pinTwo, int pinDis);

  //Return the first Input-Pin of the H-bridge.
  int getPinOne();

  //Return the second Input-Pin of the H-bridge.
  int getPinTwo();

  //Return the Disable-Pin of the H-bridge.
  int getPinDis();

  //Enable the Engine (Disable-Pin = low).
  void enable();

  //Disable the Engine (Disable-Pin = high).
  void disable();

  //Set the PWM dutycycle for the Engine.
  //Parameter:
  //  forward => if true the Robot drive forward, else backward.
  //  dutyCycle => the dutycycle of the PWM wich controlls the speed of the motor. (min 0; max 255). 
  void setDutyCycle(bool forward, int dutyCycle);
  
 private:
  //First Input-Pin.
  int _pinOne;

  //Second Input-Pin.
  int _pinTwo;

  //Disable-Pin.
  int _pinDis;
};

#endif