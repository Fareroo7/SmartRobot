package at.htl.raspitest;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class GpioTest {

	public static void main(String[] args) {
		GpioController c = GpioFactory.getInstance();
		GpioPinDigitalOutput op = c.provisionDigitalOutputPin(RaspiPin.GPIO_02, PinState.LOW);
		GpioPinDigitalInput ip = c.provisionDigitalInputPin(RaspiPin.GPIO_03);
		long receiveTime = 0;
		long startTime = System.nanoTime();
		op.setState(true);
		while (ip.isLow());
		receiveTime = System.nanoTime();
		System.out.println((receiveTime-startTime)/1000+"µs");
	}

}
