/*
  EngineTask.cpp - Library for SmartRobot.
  Created by Dominik Simon, November 21, 2014.
*/

#include "Arduino.h"
#include "EngineTask.h"

// ----- Engine Control Protocol ----

const byte EngineTask::START = 0x53;
const byte EngineTask::END = 0x54;

// ----- Action Codes -----

const byte EngineTask::DELETE_ALL = 0x01;
const byte EngineTask::INSERT = 0x02;
const byte EngineTask::DELETE = 0x03;
const byte EngineTask::UPDATE = 0x04;

// ----- Richtungscodes -----

const byte EngineTask::FORWARD = 0x11;
const byte EngineTask::BACKWARD = 0x22;
const byte EngineTask::CLOCKWISE = 0x12;
const byte EngineTask::ANTICLOCKWISE = 0x21;

// ----- Kontrollcodes -----
const byte EngineTask::ACKNOWLADGE = 0x01;
const byte EngineTask::PROTOCOL_ERROR = 0x02;
const byte EngineTask::OVERCURRENT = 0x10;

const byte EngineTask::BUMPER_FRONT_LEFT = 0x24;
const byte EngineTask::BUMPER_FRONT_CENTER = 0x28;
const byte EngineTask::BUMPER_FRONT_RIGHT = 0x2C;
const byte EngineTask::BUMPER_BACK_LEFT = 0x21;
const byte EngineTask::BUMPER_BACK_CENTER = 0x22;
const byte EngineTask::BUMPER_BACK_RIGHT = 0x23;

const byte EngineTask::NO_TASK = 0x31;
const byte EngineTask::TASK_COMPLETE = 0x32;
const byte EngineTask::TASK_ABORT = 0x33;
const byte EngineTask::TASK_ID_OVERFLOW = 0x34;
const byte EngineTask::TASK_NOT_FOUND = 0x35;

/*
   Constructs a new EngineTask from the received data.
*/
EngineTask::EngineTask(byte data[9])
{
  _id = data[1];
  _actionCode = data[2];
  _directionCode = data[3];
  _dutyCycleLeft = data[4];
  _dutyCycleRight = data[5];
  _duration = data[6] << 8 | data[7];
}

/*
  Constructs a new empty EngineTask.
*/
EngineTask::EngineTask() {}

/*
  Return the ID of the task.
*/
byte EngineTask::getID()
{
  return _id; 
}

/*
  Sets the ID of the Task.
*/
void EngineTask::setID(byte id)
{
  _id = id;
}

/*
  Return the ActionCode of the Task.
*/
byte EngineTask::getActionCode()
{
  return _actionCode;
}

/*
  Set the ActionCode of the Task.
*/
void EngineTask::setActionCode(byte actionCode)
{
  _actionCode = actionCode;
}

/*
  Return the DirectionCode of the Task.
*/
byte EngineTask::getDirectionCode()
{
  return _directionCode;
}

/*
  Set the DirectionCode of the Task.
*/
void EngineTask::setDirectionCode(byte directionCode)
{
  _directionCode = directionCode;
}

/*
  Return the DutyCycle for the left Engine of the Task.
*/
byte EngineTask::getDutyCycleLeft() 
{
  return _dutyCycleLeft;
}

/*
  Set the DutyCycle for the left Engine of the Task.
*/
void EngineTask::setDutyCycleLeft(byte dutyCycleLeft)
{
  _dutyCycleLeft = dutyCycleLeft;
}

/*
  Return the DutyCycle for the right Engine of the Task.
*/
byte EngineTask::getDutyCycleRight() 
{
  return _dutyCycleRight;
}

/*
  Set the DutyCycle for the right Engine of the Task.
*/
void EngineTask::setDutyCycleRight(byte dutyCycleRight)
{
  _dutyCycleRight = dutyCycleRight;
}

/*
  Return the Duration of the Task.
*/
unsigned int EngineTask::getDuration()
{
  return _duration;
}

/*
  Set the Duration of the Task.
*/
void EngineTask::setDuration(unsigned int duration)
{
  _duration = duration;
}

/*
  Return the high byte of the Duration of the Task.
*/
byte EngineTask::getDurationHighByte()
{
  return (_duration >> 8) & 0xff;
}

/*
  Return the low byte of the Duration of the Task.
*/

byte EngineTask::getDurationLowByte()
{
  return _duration & 0xff;
}

/*
  Set the Duration of the Task.
*/
void EngineTask::setDuration(byte highByte, byte lowByte)
{
  _duration = (highByte << 8) | lowByte;
}

/*
  Return true if the received data is a EngineControlPacket, 
  otherwise false.
*/
bool EngineTask::check(byte data[9])
{
  return (data[0] == START && data[8] == END); 
}