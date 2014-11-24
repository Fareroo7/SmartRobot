/*
  EngineTask.cpp - Library for SmartRobot.
  Created by Dominik Simon, November 21, 2014.
*/

#include "Arduino.h"
#include "EngineTask.h"

// ----- Engine Control Protocol ----

#define START 0x53
#define END 0x54

// ----- Action Codes -----

#define DELETE_ALL 0x01
#define INSERT 0x02
#define DELETE 0x03
#define UPDATE 0x04

// ----- Richtungscodes -----

#define FORWARD 0x11
#define BACKWARD 0x22
#define CLOCKWISE 0x12
#define ANTICLOCKWISE 0x21

// ----- Kontrollcodes -----
#define ACKNOWLADGE 0x01
#define PROTOCOL_ERROR 0x02
#define OVERCURRENT 0x10

#define BUMPER_FRONT_LEFT 0x24
#define BUMPER_FRONT_CENTER 0x28
#define BUMPER_FRONT_RIGHT 0x2C
#define BUMPER_BACK_LEFT 0x21
#define BUMPER_BACK_CENTER 0x22
#define BUMPER_BACK_RIGHT 0x23

#define NO_TASK 0x31
#define TASK_COMPLETE 0x32
#define TASK_ABORT 0x33
#define TASK_ID_OVERFLOW 0x34

/*
   Constructs a new EnginTask from the received data.
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

EngineTask::EngineTask() {}

/*
  Return true if the received data is a EngineControlPacket, 
  otherwise false.
*/
bool EngineTask::check(byte data[9])
{
  //return (data[0] == START && data[8] == END); 
  return true;
}

/*
  Return the ID of the task
*/
byte EngineTask::getID()
{
  return _id; 
}

/*
  Sets the ID of the Task
*/
void EngineTask::setID(byte id)
{
  _id = id;
}

/*
  Return the ActionCode of the Task
*/
byte EngineTask::getActionCode()
{
  return _actionCode;
}

/*
  Set the ActionCode of the Task
*/
void EngineTask::setActionCode(byte actionCode)
{
  _actionCode = actionCode;
}

