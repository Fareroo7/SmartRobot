package at.htl.smartrobot.server;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import at.htl.smartrobot.server.utils.*;
import com.pi4j.io.gpio.*;

public class SmartServer implements UDPReceiveListener {

	// Definitionen
	public static final byte RUNTIME_MEASURE = 0x01;
	public static final byte RUNTIME_RESPONSE = 0x02;

	private int port = 50000;
	private InetAddress robotAddress;
	private Receiver udpReceiver;

	private static Scanner scn;
	private static boolean run = true;

	public Logger log = new Logger("./log.txt");

	public static void main(String args[]) {

		SmartServer s = new SmartServer("192.168.88.250", 50001);
		scn = new Scanner(System.in);

		System.out.println("---SmartServer---");
		System.out.println("Try following commands:\n" + "s - start server \n" + "t - terminate server \n"
				+ "e - exit programm" + "h - show help");

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
				s.log.close();
				break;

			case "h":
			default:
				System.out.println("Try following commands:\n" + "s - start server \n" + "t - terminate server \n"
						+ "h - show help");
				break;
			}
		}
		// SmartServer s = new SmartServer("192.168.88.250", 50001);
		// s.start();
	}

	public SmartServer(String robotIp, int robotPort) {
		try {
			robotAddress = InetAddress.getByName(robotIp);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
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
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		System.out.println(e.getTimestamp());
		log.write(e.getTimestamp() + " : " + new String(e.getUdpPacket().getData()));
	}

}
