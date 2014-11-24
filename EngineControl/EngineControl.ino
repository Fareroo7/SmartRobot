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

byte packet[9] = { 0x53, 0x01, 0x02, 0x11, 0xff, 0xff, 0x01, 0x12, 0x54 };

byte id = 0;

Engine one(5, 6, 2);

void setup() {                
  Serial.begin(9600);
  Serial.println("\r\nStart");
  //acc.powerOn();
  Serial.println(one.getPinDis());
  
}

void loop() {
  
  //if(acc.isConnected()){
      //int len  = acc.read(packet, sizeof(packet), 1);
      //if (len > 0) {
         //int error = ECPHandler(packet);

         //sendAck(error);
      //}
  //}
}
/*
int ECPHandler(byte input[]){
  if(input[0] == START && input[8] == END){
      tasks[id].id = id;
      tasks[id].actionCode = input[2];
      tasks[id].directionCode = input[3];
      tasks[id].dutyCycleLeft = input[4];
      tasks[id].dutyCycleRight = input[5];
      tasks[id].duration = (input[6] << 8) | input[7];
      int error = ECPInterpreter(tasks[id]);
      id ++;
      return error;
  }else{
    return PROTOCOL_ERROR;
  }
}

int ECPInterpreter(struct EngineTask task){
 if(task.directionCode == FORWARD){
        // Vorwärtsfahren
        digitalWrite(MOTOR_RIGHT_DIS, LOW);
        digitalWrite(MOTOR_LEFT_DIS, LOW);
        digitalWrite(MOTOR_LEFT_TWO, LOW);
        digitalWrite(MOTOR_RIGHT_TWO, LOW);
        analogWrite(MOTOR_LEFT_ONE, task.dutyCycleLeft);
        analogWrite(MOTOR_RIGHT_ONE, task.dutyCycleRight);
        //Serial.println("Vorwärts");
        //Serial.print("Aktion: ");
        //Serial.println(task.actionCode);
        //Serial.print("Links: ");
        //Serial.println(task.dutyCycleLeft);
        //Serial.print("Recht: ");
        //Serial.println(task.dutyCycleRight);
        //Serial.print("Zeit: ");
        //Serial.println(task.duration);
        return ACKNOWLADGE;
      }else if(task.directionCode == BACKWARD){
        // Rückwärtsfahren
        digitalWrite(MOTOR_RIGHT_DIS, LOW);
        digitalWrite(MOTOR_LEFT_DIS, LOW);
        digitalWrite(MOTOR_LEFT_ONE, LOW);
        digitalWrite(MOTOR_RIGHT_ONE, LOW);
        analogWrite(MOTOR_LEFT_TWO, task.dutyCycleLeft);
        analogWrite(MOTOR_RIGHT_TWO, task.dutyCycleRight);
        //Serial.println("Rückwärts");
        //Serial.print("Aktion: ");
        //Serial.println(task.actionCode);
        //Serial.print("Links: ");
        //Serial.println(task.dutyCycleLeft);
        //Serial.print("Recht: ");
        //Serial.println(task.dutyCycleRight);
        //Serial.print("Zeit: ");
        //Serial.println(task.duration);
        return ACKNOWLADGE;
      }else if(task.directionCode == CLOCKWISE){
        // Drehen im Uhrzeigersinn
        digitalWrite(MOTOR_RIGHT_DIS, LOW);
        digitalWrite(MOTOR_LEFT_DIS, LOW);
        digitalWrite(MOTOR_LEFT_TWO, LOW);
        digitalWrite(MOTOR_RIGHT_ONE, LOW);
        analogWrite(MOTOR_LEFT_ONE, task.dutyCycleLeft);
        analogWrite(MOTOR_RIGHT_TWO, task.dutyCycleRight);
        //Serial.println("Drehen CW");
        //Serial.print("Aktion: ");
        //Serial.println(task.actionCode);
        //Serial.print("Links: ");
        //Serial.println(task.dutyCycleLeft);
        //Serial.print("Recht: ");
        //Serial.println(task.dutyCycleRight);
        //Serial.print("Zeit: ");
        //Serial.println(task.duration);
        return ACKNOWLADGE; 
      }else if(task.directionCode == ANTICLOCKWISE){
        // Drehen gegen Uhrzeigersinn
        digitalWrite(MOTOR_RIGHT_DIS, LOW);
        digitalWrite(MOTOR_LEFT_DIS, LOW);
        digitalWrite(MOTOR_LEFT_ONE, LOW);
        digitalWrite(MOTOR_RIGHT_TWO, LOW);
        analogWrite(MOTOR_LEFT_TWO, task.dutyCycleLeft);
        analogWrite(MOTOR_RIGHT_ONE, task.dutyCycleRight);
        //Serial.println("Drehen ACW");
        //Serial.print("Aktion: ");
        //Serial.println(task.actionCode);
        //Serial.print("Links: ");
        //Serial.println(task.dutyCycleLeft);
        //Serial.print("Recht: ");
        //Serial.println(task.dutyCycleRight);
        //Serial.print("Zeit: ");
        //Serial.println(task.duration);
        return ACKNOWLADGE; 
      }else{
        return PROTOCOL_ERROR;
      } 
}

void sendAck(byte err){
  byte output[] = { START, id, err, END };
  acc.write(output, sizeof(output));
  Serial.print(output[1]);
  Serial.println((char)output[2]);
}
*/
