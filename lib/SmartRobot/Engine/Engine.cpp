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
  _pinOne = pinOne;
  _pinTwo = pinTwo;
  _pinDis = pinDis;
  pinMode(_pinOne, OUTPUT);
  pinMode(_pinTwo, OUTPUT);
  pinMode(_pinDis, OUTPUT);
  digitalWrite(_pinDis, HIGH);
}

Engine::Engine(){}

int Engine::getPinOne()
{
  return _pinOne;
}

int Engine::getPinTwo()
{
  return _pinTwo;
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
    digitalWrite(_pinTwo, LOW);
    analogWrite(_pinOne, dutyCycle);
  } 
  else
  {
    digitalWrite(_pinOne, LOW);
    analogWrite(_pinTwo, dutyCycle);
  }
}