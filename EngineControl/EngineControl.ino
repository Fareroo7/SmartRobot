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

// Pin Defines

const int MOTOR_LEFT_ONE = 5;
const int MOTOR_LEFT_TWO = 6;

const int MOTOR_RIGHT_ONE = 10;
const int MOTOR_RIGHT_TWO = 11;

byte data[5];

struct EngineTask {
  byte id;
  byte actionCode;
  byte directionCode;
  byte dutyCycleLeft;
  byte dutyCycleRight;
  unsigned int duration;
};

EngineTask tasks[254];

void setup() {                
  Serial.begin(9600);
  Serial.println("\r\nStart");
  acc.powerOn();
}

void loop() {
  
  if(acc.isConnected()){
      int len  = acc.read(data, sizeof(data), 1);
      if (len > 0) {
         int error = ECPHandler(data);

         sendAck(error);
      }
  }
}

int ECPHandler(byte input[]){
  if(input[0] == START && input[8] == END){
      tasks[input[1]].id = input[1];
      tasks[input[1]].actionCode = input[2];
      tasks[input[1]].directionCode = input[3];
      tasks[input[1]].dutyCycleLeft = input[4];
      tasks[input[1]].dutyCycleRight = input[5];
      tasks[input[1]].duration = (input[6] << 8) | input[7];
      return ACKNOWLADGE;
  }else{
    return PROTOCOL_ERROR;
  }
}

int ECPInterpreter(byte input[]){
 if(input[1] == FORWARD){
        // Vorwärtsfahren
        Serial.println("Vorwärts");
        Serial.print("Links: ");
        Serial.println(input[2]);
        Serial.print("Recht: ");
        Serial.println(input[3]);
        return ACKNOWLADGE;
      }else if(input[1] == BACKWARD){
        // Rückwärtsfahren
        Serial.println("Rückwärts");
        Serial.print("Links: ");
        Serial.println(input[2]);
        Serial.print("Recht: ");
        Serial.println(input[3]);
        return ACKNOWLADGE;
      }else if(input[1] == CLOCKWISE){
        // Drehen im Uhrzeigersinn
        Serial.println("Drehen CW");
        Serial.print("Links: ");
        Serial.println(input[2]);
        Serial.print("Recht: ");
        Serial.println(input[3]);
        return ACKNOWLADGE; 
      }else if(input[1] == ANTICLOCKWISE){
        // Drehen gegen Uhrzeigersinn
        Serial.println("Drehen ACW");
        Serial.print("Links: ");
        Serial.println(input[2]);
        Serial.print("Recht: ");
        Serial.println(input[3]);
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
