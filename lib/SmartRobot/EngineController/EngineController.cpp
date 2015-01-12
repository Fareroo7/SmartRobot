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
			_taskIndex = 0;
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
			//Task einfügen und index erhöhen.
			task.setID(_index);
			_tasks[_index] = task;

			if(_index <= 250)
			{
				_index ++;
				return ((_index - 1) << 8) | EngineTask::ACKNOWLADGE;
			}
			else
			{
				_index = 0;
				return (_index << 8) | EngineTask::TASK_ID_OVERFLOW;
			}	
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
			//Verschiebe alle folgende Tasks eins vor.
			for(byte index = 0; index <= _index; index++)
			{
				if(_tasks[index].getID() == task.getID())
				{
					for(byte i = index; i < _index; i ++)
					{
						_tasks[i] = _tasks[i + 1];
					}
					_index --;
					if(_taskIndex > index) _taskIndex --;
					return (task.getID() << 8) | EngineTask::ACKNOWLADGE;
				}
			}
			
			return (task.getID() << 8) | EngineTask::TASK_NOT_FOUND;
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
	if(_taskIndex <= _index)
	{
		EngineController::doTask(_tasks[_taskIndex]);
		/*if(_tasks[_taskIndex].getDirectionCode() == EngineTask::FORWARD)
		{
			drive(true, _tasks[_taskIndex].getDutyCycleLeft(), _tasks[_taskIndex].getDutyCycleRight());
			unsigned long out = ((unsigned long)_tasks[_taskIndex].getDuration() << 16) | (_tasks[_taskIndex].getID() << 8) | EngineTask::TASK_COMPLETE;
			_taskIndex ++;
			return  out;
		} 
		else if(_tasks[_taskIndex].getDirectionCode() == EngineTask::BACKWARD)
		{
			drive(false, _tasks[_taskIndex].getDutyCycleLeft(), _tasks[_taskIndex].getDutyCycleRight());
			_taskIndex ++;
			return ((unsigned long)_tasks[_taskIndex - 1].getDuration() << 16) | (_tasks[_taskIndex].getID() << 8) | EngineTask::TASK_COMPLETE;
		} 
		else if(_tasks[_taskIndex].getDirectionCode() == EngineTask::CLOCKWISE)
		{
			turn(true, _tasks[_taskIndex].getDutyCycleLeft(), _tasks[_taskIndex].getDutyCycleRight());
			_taskIndex ++;
			return ((unsigned long)_tasks[_taskIndex].getDuration() << 16) | (_tasks[_taskIndex].getID() << 8) | EngineTask::TASK_COMPLETE;
		} 
		else if(_tasks[_taskIndex].getDirectionCode() == EngineTask::ANTICLOCKWISE)
		{
			turn(false, _tasks[_taskIndex].getDutyCycleLeft(), _tasks[_taskIndex].getDutyCycleRight());
			_taskIndex ++;
			return ((unsigned long)_tasks[_taskIndex].getDuration() << 16) | (_tasks[_taskIndex].getID() << 8) | EngineTask::TASK_COMPLETE;
		}
		else
		{
			return ((unsigned long)0x0000 << 16) | (_tasks[_taskIndex].getID() << 8) | EngineTask::PROTOCOL_ERROR;
		}*/
	}
	else
	{
		return ((unsigned long)0x0000 << 16) | (0xff << 8) | EngineTask::NO_TASK;
	}
		
}

unsigned long EngineController::doTask(EngineTask task)
{
	if(task.getDirectionCode() == EngineTask::FORWARD)
	{
		drive(true, task.getDutyCycleLeft(), task.getDutyCycleRight());
		unsigned long out = ((unsigned long)task.getDuration() << 16) | (task.getID() << 8) | EngineTask::TASK_COMPLETE;
		_taskIndex ++;
		return  out;
	} 
	else if(task.getDirectionCode() == EngineTask::BACKWARD)
	{
		drive(false, task.getDutyCycleLeft(), task.getDutyCycleRight());
		_taskIndex ++;
		return ((unsigned long)task.getDuration() << 16) | (task.getID() << 8) | EngineTask::TASK_COMPLETE;
	} 
	else if(task.getDirectionCode() == EngineTask::CLOCKWISE)
	{
		turn(true, task.getDutyCycleLeft(), task.getDutyCycleRight());
		_taskIndex ++;
		return ((unsigned long)task.getDuration() << 16) | (task.getID() << 8) | EngineTask::TASK_COMPLETE;
	} 
	else if(task.getDirectionCode() == EngineTask::ANTICLOCKWISE)
	{
		turn(false, task.getDutyCycleLeft(), task.getDutyCycleRight());
		_taskIndex ++;
		return ((unsigned long)task.getDuration() << 16) | (task.getID() << 8) | EngineTask::TASK_COMPLETE;
	}
	else
	{
		return ((unsigned long)0x0000 << 16) | (task.getID() << 8) | EngineTask::PROTOCOL_ERROR;
	}
}

byte EngineController::getCurrentTaskID()
{
	return _taskIndex;
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