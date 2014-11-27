#include <SimpleTimer.h>
#include <EngineController.h>
#include <EngineTask.h>
#include <Engine.h>
#include <Max3421e.h>
#include <Usb.h>
#include <AndroidAccessory.h>

AndroidAccessory acc("Smartbot",
		     "EC",
		     "Engine Control",
		     "1.0",
		     "http://www.android.com",
		     "4200000123456789");

byte packet[9] = {EngineTask::START, 0xff, EngineTask::INSERT, EngineTask::FORWARD, 150, 100, 50, 140, EngineTask::END };

Engine one(2, 3, 30);
Engine two(4, 5, 31);
Engine three(6, 7, 32);
Engine four(8, 9, 33);
Engine five(10, 11, 34);
Engine six(12, 13, 35);

EngineController controller(one, two, three, four, five, six);

SimpleTimer timer;

void setup() {
  Serial.begin(9600);
  EngineTask testTask(packet);
  sendAckTest(controller.handle(testTask));
  sendAckTest(controller.handle(testTask));
  sendAckTest(controller.handle(testTask));
  sendAckTest(controller.handle(testTask));
  sendAckTest(controller.handle(testTask));
  sendAckTest(controller.handle(testTask));
  sendAckTest(controller.handle(testTask));
  sendAckTest(controller.handle(testTask));
  testTask.setID(2);
  testTask.setActionCode(EngineTask::UPDATE);
  testTask.setDutyCycleLeft(50);
  sendAckTest(controller.handle(testTask));
  testTask.setActionCode(EngineTask::DELETE);
  sendAckTest(controller.handle(testTask));
  Serial.println(controller.getCurrentTaskID());
  controller.printTasks();
  outputReturn(controller.doNext());
  Serial.println(controller.getCurrentTaskID());
  outputReturn(controller.doNext());
  Serial.println(controller.getCurrentTaskID());
  outputReturn(controller.doNext());
  Serial.println(controller.getCurrentTaskID());
  outputReturn(controller.doNext());
  Serial.println(controller.getCurrentTaskID());
  controller.printTasks();
}

void loop() {
  
  if(acc.isConnected()){
      int len  = acc.read(packet, sizeof(packet), 1);
      if (len > 0) {
         unsigned int error = controller.handle(EngineTask(packet));
         sendAck(error);
      }
  }
  
}

void sendAck(unsigned int data){
  byte output[] = { EngineTask::START, (data >> 8) & 0xff, data & 0xff, EngineTask::END };
  acc.write(output, sizeof(output));
}

void sendAckTest(unsigned int data){
  byte output[] = { EngineTask::START, (data >> 8) & 0xff, data & 0xff, EngineTask::END };
  Serial.print((data >> 8) & 0xff);
  Serial.print(", ");
  Serial.println(data & 0xff);
}

void sendAck(byte id, byte err){
  byte output[] = { EngineTask::START, id, err, EngineTask::END };
  acc.write(output, sizeof(output));
}

void outputReturn(unsigned long data){
  Serial.print((data >> 16));
  Serial.print(", ");
  Serial.print((data >> 8) & 0xff);
  Serial.print(", ");
  Serial.println(data & 0xff);
}

