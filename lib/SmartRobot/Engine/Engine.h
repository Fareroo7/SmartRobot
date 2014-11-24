/*
  EngineTask.h - Library for the SmartRobot.
  Created by Dominik Simon, November 21, 2014
*/

#ifndef Engine_h
#define Engine_h

#include "Arduino.h"

class Engine
{
 public:
  Engine(int pinOne, int pinTwo, int pinDis);
  Engine();
  int getPinOne();
  int getPinTwo();
  int getPinDis();
  void enable();
  void disable();
  void setDutyCycle(bool forward, int dutyCycle);
  
 private:
  int _pinOne;
  int _pinTwo;
  int _pinDis;
};

#endif