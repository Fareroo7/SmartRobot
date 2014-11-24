/*
  EngineTask.cpp - Library for SmartRobot.
  Created by Dominik Simon, November 21, 2014.
*/

#include "Arduino.h"
#include "Engine.h"

/*
   Constructs a new EnginTask from the received data.
*/
Engine::Engine(int pinOne, int pinTwo, int pinDis)
{
  _pinInOne = pinOne;
  _pinInTwo = pinTwo;
  _pinDis = pinDis;
  pinMode(_pinInOne, OUTPUT);
  pinMode(_pinInTwo, OUTPUT);
  pinMode(_pinDis, OUTPUT);
  digitalWrite(_pinDis, HIGH);
}


int Engine::getPinInOne()
{
  return _pinInOne;
}

int Engine::getPinInTwo()
{
  return _pinInTwo;
}

int Engine::getPinDis()
{
  return _pinDis;
}

void Engine::enable()
{
  digitalWrite(_pinDis, LOW);
}

void Engine::disable()
{
  digitalWrite(_pinDis, HIGH);
}

void Engine::setDutyCycle(bool forward, int dutyCycle)
{
  if(forward)
  {
    analogWrite(_pinInTwo, 0);
    analogWrite(_pinInOne, dutyCycle);
  } 
  else
  {
    analogWrite(_pinInOne, 0);
    analogWrite(_pinInTwo, dutyCycle);
  }
}