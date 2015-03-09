package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import at.htl.smartrobot.server.utils.*;

import com.pi4j.io.gpio.*;

public class SmartServer implements UDPReceiveListener {

	// Definitionen
	public static final byte RUNTIME_MEASURE = 0x01;
	public static final byte RUNTIME_RESPONSE = 0x02;

	private int port = 50000;
	private InetAddress robotAddress;
	private int robotPort = 50001;
	private Receiver udpReceiver;
	private boolean isListening = false;

	public Logger log = new Logger("./log.txt");

	public DatagramPacket packet = null;
	public DatagramSocket socket = null;

	public GpioController gpio = null;
	public GpioPinDigitalOutput pin = null;

	public SmartServer(String robotIp, int robotPort) {
		try {
			robotAddress = InetAddress.getByName(robotIp);
			packet = new DatagramPacket(new byte[] { RUNTIME_RESPONSE }, 1, robotAddress, robotPort);
			socket = new DatagramSocket();
			this.robotPort = robotPort;
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
	}

	public void startListening() {
		udpReceiver = new Receiver(port, 1);
		udpReceiver.addUDPReceiveListener(this);
		udpReceiver.start();
		gpio = GpioFactory.getInstance();
		pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW);
		isListening = true;
	}

	public void stopListening() {
		udpReceiver.removeUDPReceiveListener(this);
		udpReceiver.interrupt();
		gpio.shutdown();
		isListening = false;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getRobotAddress() {
		return robotAddress.getHostAddress();
	}

	public void setRobotAddress(String ip) {
		try {
			this.robotAddress = InetAddress.getByName(ip);
			packet = new DatagramPacket(new byte[] { RUNTIME_RESPONSE }, 1, robotAddress, robotPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public int getRobotPort() {
		return robotPort;
	}

	public void setRobotPort(int robotPort) {
		this.robotPort = robotPort;
		packet = new DatagramPacket(new byte[] { RUNTIME_RESPONSE }, 1, robotAddress, this.robotPort);
	}

	public void setLogFile(String filepath) {
		log = new Logger(filepath);
	}

	public boolean isListening() {
		return isListening;
	}

	public void sendSignal() {
		pin.pulse(50);
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		long executionTimeSendSignal = 0;
		long executionTime = 0;
		byte data = e.getUdpPacket().getData()[0];
		if (data == RUNTIME_MEASURE) {
			try {
				long before = System.nanoTime();
				sendSignal();
				executionTimeSendSignal = System.nanoTime() - before;
				socket.send(packet);
				executionTime = System.nanoTime()-before;
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		System.out.println("Response Executiontime: "+ (executionTime / 1000) + " Signal Exec.: " + (executionTimeSendSignal / 1000) + " Send Ack.: " + ((executionTime - executionTimeSendSignal) / 1000));
		log.write("Timestamp " + e.getTimestamp() + " : Data " + Arrays.toString(e.getUdpPacket().getData())+" Respondexecution: "+executionTime);
	}

	public String getRasPiPin() {
		return pin.getName();
	}

}
