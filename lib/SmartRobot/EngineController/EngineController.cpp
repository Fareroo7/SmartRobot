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

 void EngineController::start(bool forward, byte dutyCycleLeft, byte dutyCycleRight)
 {
 	_leftFront.setDutyCycle(forward, dutyCycleLeft);
 	_leftMiddle.setDutyCycle(forward, dutyCycleLeft);
 	_leftBack.setDutyCycle(forward, dutyCycleLeft);
 	_rightFront.setDutyCycle(forward, dutyCycleRight);
 	_rightMiddle.setDutyCycle(forward, dutyCycleRight);
 	_rightBack.setDutyCycle(forward, dutyCycleRight);
 }

 void EngineController::stop()
 {
 	_leftFront.disable();
 	_leftMiddle.disable();
 	_leftBack.disable();
 	_rightFront.disable();
 	_rightMiddle.disable();
 	_rightBack.disable();
 }

