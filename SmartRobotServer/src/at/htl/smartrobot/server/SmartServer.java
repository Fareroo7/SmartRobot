package at.htl.smartrobot.server;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

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

	private static Scanner scn;
	private static boolean run = true;

	public Logger log = new Logger("./log.txt");
	
	public DatagramPacket packet = null;
	public DatagramSocket socket = null;

	public static void main(String args[]) {

		SmartServer s = new SmartServer("192.168.88.252", 50001);
		scn = new Scanner(System.in);

		System.out.println("---SmartServer---");
		showHelpText();
		
		while (run) {
			String input = scn.nextLine();
			switch (input) {
			case "s":
				System.out.println("Start listening on port: " + s.getPort());
				s.startListening();
				break;

			case "t":
				System.out.println("Stop listening");
				s.stopListening();
				break;

			case "e":
				run = false;
				s.socket.close();
				s.log.close();
				break;

			case "h":
			default:
				showHelpText();
				break;
			}
		}
	}

	public SmartServer(String robotIp, int robotPort) {
		try {
			robotAddress = InetAddress.getByName(robotIp);
			packet = new DatagramPacket(new byte[] {RUNTIME_RESPONSE}, 1, robotAddress, robotPort);
			socket = new DatagramSocket();
			this.port = robotPort;
		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
		}
	}
	
	public static void showHelpText(){
		System.out.println("Try following commands:\n" + "s - start server \n" + "t - terminate server \n"
				+ "e - exit programm\n" + "h - show help");
	}

	public void startListening() {
		udpReceiver = new Receiver(port, 1);
		udpReceiver.addUDPReceiveListener(this);
		udpReceiver.start();
	}

	public void stopListening() {
		udpReceiver.removeUDPReceiveListener(this);
		udpReceiver.interrupt();
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
			packet = new DatagramPacket(new byte[] {RUNTIME_RESPONSE}, 1, robotAddress, robotPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public int getRobotPort() {
		return robotPort;
	}

	public void setRobotPort(int robotPort) {
		this.robotPort = robotPort;
		packet = new DatagramPacket(new byte[] {RUNTIME_RESPONSE}, 1, robotAddress, this.robotPort);
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		
		byte data = e.getUdpPacket().getData()[0];
		if(data == RUNTIME_MEASURE){
			try {
				socket.send(packet);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		System.out.println(e.getTimestamp());
		log.write("Timestamp " + e.getTimestamp() + " : Data " + Arrays.toString(e.getUdpPacket().getData()));
	}

}
