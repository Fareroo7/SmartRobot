/*
  EngineTask.cpp - Library for SmartRobot.
  Created by Dominik Simon, November 21, 2014.
*/

#include "Arduino.h"
#include "Engine.h"

//Constructs a new empty Engine. (Used to declare "placeholder").
Engine::Engine(){}

//Constructs a new Engine.
//Parameter:
//  pinOne => first Input-Pin.
//  pinTwo => second Input-Pin.
//  pinDis => Disable-Pin.
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

//Return the first Input-Pin of the H-bridge.
int Engine::getPinOne()
{
  return _pinOne;
}

//Return the second Input-Pin of the H-bridge.
int Engine::getPinTwo()
{
  return _pinTwo;
}

//Return the Disable-Pin of the H-bridge.
int Engine::getPinDis()
{
  return _pinDis;
}

 //Enable the Engine (Disable-Pin = low).
void Engine::enable()
{
  digitalWrite(_pinDis, LOW);
}

//Disable the Engine (Disable-Pin = high).
void Engine::disable()
{
  digitalWrite(_pinDis, HIGH);
}

//Set the PWM dutycycle for the Engine.
//Parameter:
//  forward => if true the Robot drive forward, else backward.
//  dutyCycle => the dutycycle of the PWM wich controlls the speed of the motor. (min 0; max 255). 
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