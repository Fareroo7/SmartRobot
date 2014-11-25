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
	}
	else if(task.getID() == 0xff)
	{
		//Allgemeine ID
		if(task.getActionCode() == EngineTask::DELETE_ALL){
			//Alle Task löschen wenn lastTask = 0 keine Tasks.
			_lastID = 0;
			_taskID = 0;
			return (task.getID() << 8) | EngineTask::ACKNOWLADGE;
		} 
		else if(task.getActionCode() == EngineTask::INSERT)
		{
			//Task einfügen.
			if(_lastID > 250) _lastID = 0;
			_lastID ++;
			_tasks[_lastID] = task;
			return (_lastID << 8) | EngineTask::ACKNOWLADGE;
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
			for(byte id = task.getID(); id < _lastID; id ++)
			{
				_tasks[id] = _tasks[id + 1];
				_tasks[id].setID(id);
			}
			return (task.getID() << 8) | EngineTask::ACKNOWLADGE;
		}
		else if(task.getActionCode() == EngineTask::UPDATE)
		{
			//Bearbeite Task.
			byte id = task.getID();
			_tasks[id] = task;
			return (task.getID() << 8) | EngineTask::ACKNOWLADGE;
		}
		else
		{
			return (task.getID() << 8) | EngineTask::PROTOCOL_ERROR;
		}
	}
}

unsigned long EngineController::doNext()
{
	if(_tasks[_taskID].getDirectionCode() == EngineTask::EngineTask::FORWARD)
	{
		
	}
}

byte EngineController::getCurrentTaskID()
{
	return _taskID;
}