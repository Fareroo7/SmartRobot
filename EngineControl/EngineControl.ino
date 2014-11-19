#include <Max3421e.h>
#include <Usb.h>
#include <AndroidAccessory.h>

AndroidAccessory acc("Smartbot",
		     "EC",
		     "Engine Control",
		     "1.0",
		     "http://www.android.com",
		     "4200000123456789");
// ----- Engine Control Protocol ----

const char START = 'S';
const char END = 'T';

// ----- Action Codes -----

const byte DELETE_ALL = 0x01;
const byte INSERT = 0x02;
const byte DELETE = 0x03;
const byte UPDATE = 0x04;

// ----- Richtungscodes -----

const byte FORWARD = 0x11;
const byte BACKWARD = 0x22;
const byte CLOCKWISE = 0x12;
const byte ANTICLOCKWISE = 0x21;

// ----- Kontrollcodes -----
const byte ACKNOWLADGE = 0x01;
const byte PROTOCOL_ERROR = 0x02;
const byte OVERCURRENT = 0x10;

const byte BUMPER_FRONT_LEFT = 0x24;
const byte BUMPER_FRONT_CENTER = 0x28;
const byte BUMPER_FRONT_RIGHT = 0x2C;
const byte BUMPER_BACK_LEFT = 0x21;
const byte BUMPER_BACK_CENTER = 0x22;
const byte BUMPER_BACK_RIGHT = 0x23;

const byte NO_TASK = 0x31;
const byte TASK_COMPLETE = 0x32;
const byte TASK_ABORT = 0x33;
const byte TASK_ID_OVERFLOW = 0x34;

// Pin Defines

const int MOTOR_LEFT_ONE = 5;
const int MOTOR_LEFT_TWO = 6;

const int MOTOR_RIGHT_ONE = 10;
const int MOTOR_RIGHT_TWO = 11;

byte packet[9];

byte id = 0;

struct EngineTask {
  byte id;
  byte actionCode;
  byte directionCode;
  byte dutyCycleLeft;
  byte dutyCycleRight;
  unsigned int duration;
};

EngineTask tasks[250];

void setup() {                
  Serial.begin(9600);
  Serial.println("\r\nStart");
  acc.powerOn();
}

void loop() {
  
  if(acc.isConnected()){
      int len  = acc.read(packet, sizeof(packet), 1);
      if (len > 0) {
         int error = ECPHandler(packet);

         sendAck(error);
      }
  }
}

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
        Serial.println("Vorwärts");
        Serial.print("Aktion: ");
        Serial.println(task.actionCode);
        Serial.print("Links: ");
        Serial.println(task.dutyCycleLeft);
        Serial.print("Recht: ");
        Serial.println(task.dutyCycleLeft);
        Serial.print("Zeit: ");
        Serial.println(task.duration);
        return ACKNOWLADGE;
      }else if(task.directionCode == BACKWARD){
        // Rückwärtsfahren
        Serial.println("Rückwärts");
        Serial.print("Aktion: ");
        Serial.println(task.actionCode);
        Serial.print("Links: ");
        Serial.println(task.dutyCycleLeft);
        Serial.print("Recht: ");
        Serial.println(task.dutyCycleLeft);
        Serial.print("Zeit: ");
        Serial.println(task.duration);
        return ACKNOWLADGE;
      }else if(task.directionCode == CLOCKWISE){
        // Drehen im Uhrzeigersinn
        Serial.println("Drehen CW");
        Serial.print("Aktion: ");
        Serial.println(task.actionCode);
        Serial.print("Links: ");
        Serial.println(task.dutyCycleLeft);
        Serial.print("Recht: ");
        Serial.println(task.dutyCycleLeft);
        Serial.print("Zeit: ");
        Serial.println(task.duration);
        return ACKNOWLADGE; 
      }else if(task.directionCode == ANTICLOCKWISE){
        // Drehen gegen Uhrzeigersinn
        Serial.println("Drehen ACW");
        Serial.print("Aktion: ");
        Serial.println(task.actionCode);
        Serial.print("Links: ");
        Serial.println(task.dutyCycleLeft);
        Serial.print("Recht: ");
        Serial.println(task.dutyCycleLeft);
        Serial.print("Zeit: ");
        Serial.println(task.duration);
        return ACKNOWLADGE; 
      }else{
        return PROTOCOL_ERROR;
      } 
}

void sendAck(byte err){
  byte output[] = { START, err, END };
  acc.write(output, sizeof(output));
  Serial.print((char)output[0]);
  Serial.print(output[1]);
  Serial.println((char)output[2]);
}
