/*
  EngineController.cpp - Library for SmartRobot.
  Created by Dominik Simon, November 21, 2014.
*/

#include "Arduino.h"
#include "EngineController.h"
#include "Engine.h"


 EngineController::EngineController(Engine leftFront, Engine leftMiddle, Engine leftBack, Engine rightFront, Engine rightMiddle, Engine rightBack)
 {
 	_leftFront = leftFront;
 	_leftMiddle = leftMiddle;
 	_leftBack = leftBack;
 	_rightFront = rightFront;
 	_rightMiddle = rightMiddle;
 	_rightBack = rightBack; 
 }

