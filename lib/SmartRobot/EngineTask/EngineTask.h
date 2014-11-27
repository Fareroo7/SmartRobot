/*
  EngineTask.h - Library for the SmartRobot.
  Created by Dominik Simon, November 21, 2014
*/

#ifndef EngineTask_h
#define EngineTask_h

#include "Arduino.h"

//This class handels our Engine Control Protokol (short ECP), 
//and provide a function to check if the received packet is a ECP-Packet.
//For more Information read the definition of our ECP.
class EngineTask
{
 public:
  //Constructs a new empty EngineTask. (used to declare a array).
  EngineTask();

  //Constructs a new EngineTask.
  //Parameter:
  //  data[9] => the received packet (musst have a length of 9).
  EngineTask(byte data[9]);

  //Return true if the received packet is a ECP-packet.
  static bool check(byte data[9]);

  //Return the ID of the EngineTask.
  byte getID();

  //Set the ID of the EngineTask.
  void setID(byte id);

  //Return the ActionCode of the EngineTask.
  byte getActionCode();

  //Set the ActionCode of the EngineTask.
  void setActionCode(byte actionCode);

  //Return the DirectionCode of the EngineTask.
  byte getDirectionCode();

  //Set the DirectionCode of the EngineTask.
  void setDirectionCode(byte directionCode);

  //Return the left DutyCycle of the EngineTask.
  byte getDutyCycleLeft();

  //Set the left DutyCycle of the EngineTask.  
  void setDutyCycleLeft(byte dutyCycleLeft);

  //Return the right DutyCycle of the EngineTask.
  byte getDutyCycleRight();

  //Set the right DutyCycle of the EngineTask.
  void setDutyCycleRight(byte dutyCycleRight);

  //Return the Duration of the EngineTask.
  unsigned int getDuration();

  //Set the Duration of the EngineTask.
  void setDuration(unsigned int duration);

  //Return the high byte of the Duration of the EngineTask.
  byte getDurationHighByte();

  //Return the low byte of the Duration of the EngineTask.
  byte getDurationLowByte();

  //Set the Duration of the EngineTask.
  void setDuration(byte highByte, byte lowByte);

  // ----- Engine Control Protocol ----
  const static byte START;
  const static byte END;

  // ----- Action Codes -----
  const static byte DELETE_ALL;
  const static byte INSERT;
  const static byte DELETE;
  const static byte UPDATE;

  // ----- Richtungscodes -----
  const static byte FORWARD;
  const static byte BACKWARD;
  const static byte CLOCKWISE;
  const static byte ANTICLOCKWISE;

  // ----- Kontrollcodes -----
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
  //The ID of the EngineTask.
  byte _id;

  //The ActionCode of the EngineTask.
  byte _actionCode;

  //The DirectionCode of the EngineTask.
  byte _directionCode;

  //The left Dutycycle of the EngineTask.
  byte _dutyCycleLeft;

  //The right Dutycycle of the EngineTask.
  byte _dutyCycleRight;

  //The Duration of the EngineTask.
  unsigned int _duration;  
};
#endif
