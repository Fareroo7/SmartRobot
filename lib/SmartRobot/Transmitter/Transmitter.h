/*
  Transmitter.h - Library for the SmartRobot.
  Created by Dominik Simon, March 18, 2015.
*/

#ifndef Transmitter_h
#define Transmitter_h

#include "Arduino.h"
//This class represents a Transmitter of the SmartRobot.
//It handle the needed pins for controlling the Transmitter.
//Also provide some function to controll the Transmitter.
class Transmitter
{
 public:
  //Constructs a new empty Transmitter. (Used to declare "placeholder").
  Transmitter();
  
//Constructs a new Transmitter.
//Parameter:
//  pinData => data ouput-pin.
  Transmitter(int pinData);

  //Return the first Input-Pin of the H-bridge.
  int getPin();

  //Send a high signal over the Trasmitter.
  void high();

  //Send a low singal over the Transmitter.
  void low();

  //Toggle the signal, which is send over the Transmitter.
  void toggle();

  //Send a pulse-signal over the Transmitter.
  void pulse(int time);
  
 private:
  //Data ouput-pin.
  int _pinOut;

  //State of the output-pin (true = high and v.v.).
  bool _pinstate;
};

#endif