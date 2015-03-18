/*
  Transmitter.cpp - Library for SmartRobot.
  Created by Dominik Simon, March 18, 2015.
*/

#include "Arduino.h"
#include "Transmitter.h"

//Constructs a new empty Transmitter. (Used to declare "placeholder").
Transmitter::Transmitter(){}

//Constructs a new Transmitter.
//Parameter:
//  pinData => data ouput-pin.
Transmitter::Transmitter(int pinData)
{
  _pinOut = pinData;
  _pinstate = false;
  pinMode(_pinOut, OUTPUT);
  digitalWrite(_pinOut, _pinstate);
}

//Return the data ouput-pin of the Transmitter.
int Transmitter::getPin()
{
  return _pinOut;
}

 //Set the data ouput-pin high.
void Transmitter::high()
{
  _pinstate = true;
  digitalWrite(_pinOut, _pinstate);
}

 //Set the data ouput-pin low.
void Transmitter::low()
{
  _pinstate = false;
  digitalWrite(_pinOut, _pinstate);
}

 //Toggle the data ouput-pin.
void Transmitter::toggle()
{
  _pinstate = !_pinstate;
  digitalWrite(_pinOut, _pinstate);
}