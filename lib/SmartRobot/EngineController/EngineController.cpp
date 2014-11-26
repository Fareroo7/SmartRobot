/*
  EngineController.cpp - Library for SmartRobot.
  Created by Dominik Simon, November 21, 2014.
*/

#include "Arduino.h"
#include "EngineController.h"
#include "Engine.h"
#include "EngineTask.h"
#include "SimpleTimer.h"


 EngineController::EngineController(Engine leftFront, Engine leftMiddle, Engine leftBack, Engine rightFront, Engine rightMiddle, Engine rightBack)
 {
 	_leftFront = leftFront;
 	_leftMiddle = leftMiddle;
 	_leftBack = leftBack;
 	_rightFront = rightFront;
 	_rightMiddle = rightMiddle;
 	_rightBack = rightBack; 
 }

 void EngineController::start()
 {
 	_leftFront.enable();
 	_leftMiddle.enable();
 	_leftBack.enable();
 	_rightFront.enable();
 	_rightMiddle.enable();
 	_rightBack.enable();
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

void EngineController::drive(bool forward, byte dutyCycleLeft, byte dutyCycleRight)
{
	_leftFront.setDutyCycle(forward, dutyCycleLeft);
 	_leftMiddle.setDutyCycle(forward, dutyCycleLeft);
 	_leftBack.setDutyCycle(forward, dutyCycleLeft);
 	_rightFront.setDutyCycle(forward, dutyCycleRight);
 	_rightMiddle.setDutyCycle(forward, dutyCycleRight);
 	_rightBack.setDutyCycle(forward, dutyCycleRight);
}

void EngineController::turn(bool clw, byte dutyCycleLeft, byte dutyCycleRight)
{
	_leftFront.setDutyCycle(clw, dutyCycleLeft);
 	_leftMiddle.setDutyCycle(clw, dutyCycleLeft);
 	_leftBack.setDutyCycle(clw, dutyCycleLeft);
 	_rightFront.setDutyCycle(!clw, dutyCycleRight);
 	_rightMiddle.setDutyCycle(!clw, dutyCycleRight);
 	_rightBack.setDutyCycle(!clw, dutyCycleRight);
}

unsigned int EngineController::handle(EngineTask task)
{
	if(task.getID() == 0xfe)
	{
		//Task sofort ausführen
		if(task.getActionCode() == EngineTask::DELETE_ALL){
			//Alle Task löschen wenn index = 0 keine Tasks.
			_index = 0;
			_taskID = 0;
			return (task.getID() << 8) | EngineTask::ACKNOWLADGE;
		}
		else{
			return (task.getID() << 8) | EngineTask::PROTOCOL_ERROR;
		}
	}
	else if(task.getID() == 0xff)
	{
		//Allgemeine ID
		if(task.getActionCode() == EngineTask::INSERT)
		{
			//Task einfügen.
			if(_index > 250) _index = 0;
			task.setID(_index);
			_tasks[_index] = task;
			_index ++;
			return ((_index - 1) << 8) | EngineTask::ACKNOWLADGE;
		}
		else
		{
			return (task.getID() << 8) | EngineTask::PROTOCOL_ERROR;
		}
	}
	else
	{
		// Task mit bestimmter ID bearbeiten
		if(task.getActionCode() == EngineTask::DELETE)
		{
			//Lösche Task.
			//Verschiebe alle folgende Tasks eins auf.
			byte i = 255;
			for(byte index = 0; index <= _index; index++)
			{
				if(_tasks[index].getID() == task.getID())
				{
					i = index;
					break;
				}
			}
			for(byte index = i; index < _index; index ++)
			{
				_tasks[index] = _tasks[index + 1];
			}
			if(i != 255)
			{
				_index --;
				return (task.getID() << 8) | EngineTask::ACKNOWLADGE;
			} else 
			{
				return (task.getID() << 8) | EngineTask::TASK_NOT_FOUND;
			}
		}
		else if(task.getActionCode() == EngineTask::UPDATE)
		{
			//Aktualisire Task.
			for(byte index = 0; index < _index; index++)
			{
				if(_tasks[index].getID() == task.getID())
				{
					_tasks[index] = task;
					return (task.getID() << 8) | EngineTask::ACKNOWLADGE;
				}
			}
			return (task.getID() << 8) | EngineTask::TASK_NOT_FOUND;
		}
		else
		{
			return (task.getID() << 8) | EngineTask::PROTOCOL_ERROR;
		}
	}
}

unsigned long EngineController::doNext()
{
	for(byte index; index < _index; index++)
	{
		if(_tasks[index].getID() == _taskID){
			if(_tasks[index].getDirectionCode() == EngineTask::FORWARD)
			{
				
			} 
			else if(_tasks[index].getDirectionCode() == EngineTask::BACKWARD)
			{

			} 
			else if(_tasks[index].getDirectionCode() == EngineTask::CLOCKWISE)
			{

			} 
			else if(_tasks[index].getDirectionCode() == EngineTask::ANTICLOCKWISE)
			{

			}
			else
			{
				
			}
		}
	}
}

byte EngineController::getCurrentTaskID()
{
	return _taskID;
}

void EngineController::printTasks()
{
	Serial.println("--- Tasks Output ---");
	for(byte index = 0; index < _index; index++)
	{
		Serial.print(_tasks[index].getID());
		Serial.print(", ");
		Serial.print(_tasks[index].getActionCode());
		Serial.print(", ");
		Serial.print(_tasks[index].getDirectionCode());
		Serial.print(", ");
		Serial.print(_tasks[index].getDutyCycleLeft());
		Serial.print(", ");
		Serial.print(_tasks[index].getDutyCycleRight());
		Serial.print(", ");
		Serial.println(_tasks[index].getDuration());
	}
}