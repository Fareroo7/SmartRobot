/*
  EngineController.h - Library for the SmartRobot.
  Created by Dominik Simon, November 21, 2014
*/

#ifndef EngineController_h
#define EngineController_h

#include "Arduino.h"
#include "Engine.h"

class EngineController
{
 public:
  EngineController(Engine leftFront, Engine leftMiddle, Engine leftBack, Engine rightFront, Engine rightMiddle, Engine rightBack);
  
  
 private:
  Engine _leftFront;
  Engine _leftMiddle;
  Engine _leftBack;
  Engine _rightFront;
  Engine _rightMiddle;
  Engine _rightBack;
};

#endif