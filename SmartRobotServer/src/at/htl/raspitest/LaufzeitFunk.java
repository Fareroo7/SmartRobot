package at.htl.raspitest;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class LaufzeitFunk {
	
	public static void main(String[] args){
		final GpioController gc = GpioFactory.getInstance();
		final GpioPinDigitalInput ip = gc.provisionDigitalInputPin(RaspiPin.GPIO_02);
		final GpioPinDigitalOutput op = gc.provisionDigitalOutputPin(RaspiPin.GPIO_01);
		
		ip.addListener(new GpioPinListenerDigital() {
			
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent arg0) {
				if(arg0.getState()==PinState.HIGH){
					op.pulse(20);
				}	
			}
		});
		while(true);
	}

	
}
