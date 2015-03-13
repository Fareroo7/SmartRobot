package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import at.htl.smartrobot.server.utils.ByteUtils;
import at.htl.smartrobot.server.utils.Logger;
import at.htl.smartrobot.server.utils.Receiver;
import at.htl.smartrobot.server.utils.UDPReceiveEvent;
import at.htl.smartrobot.server.utils.UDPReceiveListener;

public class SmartServerUdpThread extends Thread implements UDPReceiveListener {

	// Definitionen
	public static final byte RUNTIME_MEASURE = 0x01;
	public static final byte RUNTIME_RESPONSE = 0x02;
	public static final byte SERVER_EXECUTIONTIME = 0x03;

	private int port = 50000;
	private InetAddress robotAddress;
	private int robotPort = 50001;
	private Receiver udpReceiver;
	private boolean isListening = false;

	public DatagramPacket packet = null;
	public DatagramSocket socket = null;

	public SmartServerUdpThread(String robotIp, int robotPort) {
		try {
			robotAddress = InetAddress.getByName(robotIp);
			this.robotPort = robotPort;
			packet = new DatagramPacket(new byte[] { RUNTIME_RESPONSE }, 8, robotAddress, robotPort);
			socket = new DatagramSocket();
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		udpReceiver = new Receiver(port, 1);
		udpReceiver.addUDPReceiveListener(this);
		if (udpReceiver.isInterrupted())
			udpReceiver.start();
		isListening = true;
		while(isListening);
		super.run();
	}
	
	public void stopListening() {
		udpReceiver.removeUDPReceiveListener(this);
		udpReceiver.interrupt();
		isListening = false;
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		byte data = e.getUdpPacket().getData()[0];
		if (data == RUNTIME_MEASURE) {
			try {
				socket.send(packet);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
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
	
	public boolean isListening() {
		return isListening;
	}
	
}
