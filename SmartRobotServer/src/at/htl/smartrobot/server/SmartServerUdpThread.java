package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
	private boolean isListening = false;

	private DatagramPacket packet = null;
	private DatagramSocket socket = null;

	private boolean doAck = false;
	private UDPReceiveEvent mEvent = null;

	public SmartServerUdpThread(String robotIp, int robotPort) {
		try {
			robotAddress = InetAddress.getByName(robotIp);
			this.robotPort = robotPort;
			packet = new DatagramPacket(new byte[] { RUNTIME_RESPONSE }, 8, robotAddress, robotPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isListening = true;
		while (isListening){
			if (doAck) {
				byte data = mEvent.getUdpPacket().getData()[0];
				if (data == RUNTIME_MEASURE) {
					try {
						socket.send(packet);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				doAck = false;
			}
		}
		super.run();
	}

	public void stopListening() {
		isListening = false;
		socket.close();
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		this.mEvent = e;
		doAck = true;
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
