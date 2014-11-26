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
  const static byte START;
  const static byte END;
  const static byte DELETE_ALL;
  const static byte INSERT;
  const static byte DELETE;
  const static byte UPDATE;
  const static byte FORWARD;
  const static byte BACKWARD;
  const static byte CLOCKWISE;
  const static byte ANTICLOCKWISE;
  const static byte ACKNOWLADGE;
  const static byte PROTOCOL_ERROR;
  const static byte OVERCURRENT;
  const static byte BUMPER_FRONT_LEFT;
  const static byte BUMPER_FRONT_CENTER;
  const static byte BUMPER_FRONT_RIGHT;
  const static byte BUMPER_BACK_LEFT;
  const static byte BUMPER_BACK_CENTER;
  const static byte BUMPER_BACK_RIGHT;
  const static byte NO_TASK;
  const static byte TASK_COMPLETE;
  const static byte TASK_ABORT;
  const static byte TASK_ID_OVERFLOW;
  const static byte TASK_NOT_FOUND;

 private:
  byte _id;
  byte _actionCode;
  byte _directionCode;
  byte _dutyCycleLeft;
  byte _dutyCycleRight;
  unsigned int _duration;  
};
#endif
