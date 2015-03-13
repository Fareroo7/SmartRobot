package at.htl.smartrobot.server;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import at.htl.smartrobot.server.utils.Receiver;
import at.htl.smartrobot.server.utils.UDPReceiveEvent;
import at.htl.smartrobot.server.utils.UDPReceiveListener;

public class SmartServerGpioThread extends Thread implements UDPReceiveListener {

	private Receiver udpReceiver;
	private boolean isListening = false;

	private GpioController gpio = null;
	private GpioPinDigitalOutput pin = null;
	private int signalDuration = 50;

	public SmartServerGpioThread() {

	}

	@Override
	public void run() {
		udpReceiver.addUDPReceiveListener(this);
		if (udpReceiver.isInterrupted())
			udpReceiver.start();
		gpio = GpioFactory.getInstance();
		pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW);
		isListening = true;
		while(isListening);
		super.run();
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		pin.pulse(signalDuration);
	}
	
	public void stopListening(){
		udpReceiver.removeUDPReceiveListener(this);
		gpio.shutdown();
		isListening = false;
	}

}
