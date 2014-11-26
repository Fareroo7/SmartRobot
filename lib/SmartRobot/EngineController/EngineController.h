/*
  EngineController.h - Library for the SmartRobot.
  Created by Dominik Simon, November 21, 2014
*/

#ifndef EngineController_h
#define EngineController_h

#include "Arduino.h"
#include "Engine.h"
#include "EngineTask.h"
#include "SimpleTimer.h"

class EngineController
{
 public:
  EngineController(Engine leftFront, Engine leftMiddle, Engine leftBack, Engine rightFront, Engine rightMiddle, Engine rightBack);
  void start();
  void stop();
  void drive(bool forward, byte dutyCycleLeft, byte dutyCycleRight);
  void turn(bool clw, byte dutyCycleLeft, byte dutyCycleRight);
  unsigned int handle(EngineTask task);
  unsigned long doNext();
  byte getCurrentTaskID();
  void printTasks();

 private:
  Engine _leftFront;
  Engine _leftMiddle;
  Engine _leftBack;
  Engine _rightFront;
  Engine _rightMiddle;
  Engine _rightBack;
  byte _index;
  byte _taskID;
  EngineTask _tasks[250];
};

#endif