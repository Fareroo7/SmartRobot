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

byte packet[9] = { 0x53, 0x10, 0x02, 0x11, 0xff, 0xff, 0x01, 0x12, 0x54 };

Engine one(2, 3, 30);
Engine two(4, 5, 31);
Engine three(6, 7, 32);
Engine four(8, 9, 33);
Engine five(10, 11, 34);
Engine six(12, 13, 35);

EngineController controller(one, two, three, four, five, six);

byte taskID = 0;
EngineTask tasks[250];

void setup() {
  //Serial.begin(9600);
  //Serial.println(EngineTask::DELETE_ALL);
}

void loop() {
  
  if(acc.isConnected()){
      int len  = acc.read(packet, sizeof(packet), 1);
      if (len > 0) {
         byte error = addTasktoArray(packet);
         sendAck(taskID ,error);
      }
  }
  
  controller.start(true, 155, 100);
  
}

/*
  add Task to Array and increment the actuall ID and replaced it with the ID of the Task.
*/
int addTasktoArray(byte packet[9]){
  if(EngineTask::check(packet)){
    if(taskID >= 250) taskID = 0;
    tasks[taskID] = EngineTask(packet);
    tasks[taskID].setID(taskID);
    taskID ++;
    return EngineTask::ACKNOWLADGE;
  }else{
    return EngineTask::PROTOCOL_ERROR;
  }
}

void sendAck(byte id, byte err){
  byte output[] = { EngineTask::START, id, err, EngineTask::END };
  acc.write(output, sizeof(output));
}

