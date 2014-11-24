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
  int getPinInOne();
  int getPinInTwo();
  int getPinDis();
  void enable();
  void disable();
  void setDutyCycle(bool forward, int dutyCycle);
  
 private:
  int _pinInOne;
  int _pinInTwo;
  int _pinDis;
};

#endif
