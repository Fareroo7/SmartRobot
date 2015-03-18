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
Transmitter::Transmitter(int pindata)
{
  _pinOut = pindata;
  _high = false;
  pinMode(_pinOut, OUTPUT);
  digitalWrite(_pinOut, LOW);
}

//Return the data ouput-pin of the Transmitter.
int Transmitter::getPin()
{
  return _pinOut;
}

 //Set the data ouput-pin high.
void Transmitter::high()
{
  digitalWrite(_pinOut, HIGH);
  _high = true;
}

 //Set the data ouput-pin low.
void Transmitter::low()
{
  digitalWrite(_pinOut, LOW);
  _high = false;
}

 //Toggle the data ouput-pin.
void Transmitter::toggle()
{
  if(_high)
  {
  	digitalWrite(_pinOut, LOW);
  	_high = false;
  }
  else
  {
  	digitalWrite(_pinOut, HIGH);
  	_high = true;
  }
}