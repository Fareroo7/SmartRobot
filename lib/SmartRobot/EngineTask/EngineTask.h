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
  EngineTask(byte data[9]);
  EngineTask();
  static bool check(byte data[9]);
  byte getID();
  void setID(byte id);
  byte getActionCode();
  void setActionCode(byte actionCode);
  byte getDirectionCode();
  void setDirectionCode(byte directionCode);
  byte getDutyCycleLeft();
  void setDutyCycleLeft(byte dutyCycleLeft);
  byte getDutyCycleRight();
  void setDutyCycleRight(byte dutyCycleRight);
  unsigned int getDuration();
  void setDuration(unsigned int duration);
  byte getDurationHighByte();
  byte getDurationLowByte();
  void setDuration(byte highByte, byte lowByte);

 private:
  byte _id;
  byte _actionCode;
  byte _directionCode;
  byte _dutyCycleLeft;
  byte _dutyCycleRight;
  unsigned int _duration;  
};
#endif
