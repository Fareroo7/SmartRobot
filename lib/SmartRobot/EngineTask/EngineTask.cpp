/*
  EngineTask.cpp - Library for SmartRobot.
  Created by Dominik Simon, November 21, 2014.
*/

#include "Arduino.h"
#include "EngineTask.h"

// ----- Engine Control Protocol ----

const char START = 'S';
const char END = 'T';

// ----- Action Codes -----

const byte DELETE_ALL = 0x01;
const byte INSERT = 0x02;
const byte DELETE = 0x03;
const byte UPDATE = 0x04;

// ----- Richtungscodes -----

const byte FORWARD = 0x11;
const byte BACKWARD = 0x22;
const byte CLOCKWISE = 0x12;
const byte ANTICLOCKWISE = 0x21;

// ----- Kontrollcodes -----
const byte ACKNOWLADGE = 0x01;
const byte PROTOCOL_ERROR = 0x02;
const byte OVERCURRENT = 0x10;

const byte BUMPER_FRONT_LEFT = 0x24;
const byte BUMPER_FRONT_CENTER = 0x28;
const byte BUMPER_FRONT_RIGHT = 0x2C;
const byte BUMPER_BACK_LEFT = 0x21;
const byte BUMPER_BACK_CENTER = 0x22;
const byte BUMPER_BACK_RIGHT = 0x23;

const byte NO_TASK = 0x31;
const byte TASK_COMPLETE = 0x32;
const byte TASK_ABORT = 0x33;
const byte TASK_ID_OVERFLOW = 0x34;

/*
   Constructs a new EnginTask from the received data.
*/
EngineTask::EngineTask(byte data[8])
{
  _id = data[1];
  _actionCode = data[2];
  _directionCode = data[3];
  _dutyCycleLeft = data[4];
  _dutyCycleRight = data[5];
  _duration = data[6];
}

/*
  Return true if the received data is a EngineControlPacket, 
  otherwise false.
*/
boolean static check(byte data[8])
{
  return (data[0] == 'S' && data[7] == 'T'); 
}

/*
  Return the ID of the task
*/
byte EngineTask::getID()
{
  return _id; 
}

void EngineTask::setID(byte id)
{
  _id = id;
}
