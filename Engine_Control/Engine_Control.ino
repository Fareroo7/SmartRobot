#include <Max3421e.h>
#include <Usb.h>
#include <AndroidAccessory.h>

AndroidAccessory acc("Smartbot",
		     "EC",
		     "Engine Control",
		     "1.0",
		     "http://www.android.com",
		     "4200000123456789");

const char START = 'S';
const char END = 'T';
const char FORWARD = 'F';
const char BACKWARD = 'B';
const int ACKNOWLADGE = 0x01;
const int PROTOCOL_ERROR = 0x02;
const int OVERCURRENT = 0x10;

byte data[5];

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
  if(input[0] == START && input[4] == END){
      if(input[1] == FORWARD){
        // PWM Dutycircle setzten
        Serial.println((char)input[1]);
        Serial.print("Links: ");
        Serial.println(input[2]);
        Serial.print("Recht: ");
        Serial.println(input[3]);
        return ACKNOWLADGE;
      }else if(input[1] == BACKWARD){
        // PWM Dutycircle setzten
        Serial.println((char)input[1]);
        Serial.print("Links: ");
        Serial.println(input[2]);
        Serial.print("Recht: ");
        Serial.println(input[3]);
        return ACKNOWLADGE;
      }else{
        return PROTOCOL_ERROR;  
      }
  }else{
    return PROTOCOL_ERROR;
  }
}

void sendAck(byte err){
  byte output[] = { START, err, END };
  acc.write(output, sizeof(output));
  Serial.print((char)output[0]);
  Serial.print(output[1]);
  Serial.print((char)output[2]);
}

