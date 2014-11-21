/*
  EngineTask.h - Library for the SmartRobot.
  Created by Dominik Simon, November 21, 2014
*/

#ifndef EngineTask_h
#define EngineTask_h

#include "Arduino.h"

class EngineTask
{
 public:
  EngineTask(byte data[8]);
  boolean check(byte data[8]);
  byte getID();
  void setID(byte id);
  
 private:
  byte _id;
  byte _actionCode;
  byte _directionCode;
  byte _dutyCycleLeft;
  byte _dutyCycleRight;
  unsigned int _duration;  
};
#endif
