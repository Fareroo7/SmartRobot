package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;

import at.htl.smartrobot.server.utils.*;
import com.pi4j.io.gpio.*;

public class SmartServer implements UDPReceiveListener {

	public static final String RUNTIME_MEASURE = "m";

	private Receiver mReceiver;
	
	private int serverPort = 50099;
	private int robotPort = 50100;

	private InetAddress myAddress;
	private InetAddress mRobotAddress;

	private DatagramSocket serverSocket;

	private DatagramPacket runtimeRespond;
	
	
	
	private ArrayList<GpioPinDigitalOutput> pinList = new ArrayList<GpioPinDigitalOutput>();
	private int nextPin = 0;

	public SmartServer() {
		try {
			myAddress = InetAddress.getByName("192.168.88.251");
			mRobotAddress = InetAddress.getByName("192.168.88.250");
			serverSocket = new DatagramSocket();
			mReceiver=new Receiver(serverPort,1);
			runtimeRespond = new DatagramPacket("r".getBytes(), 1, mRobotAddress, robotPort);
		} catch (UnknownHostException | SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SmartServer(String serverAddress, String robotAddress, int serverPort, int robotPort) {
		try {
			myAddress = InetAddress.getByName(serverAddress);
			mRobotAddress = InetAddress.getByName(robotAddress);
			this.serverPort = serverPort;
			this.robotPort = robotPort;
			serverSocket = new DatagramSocket();
			mReceiver=new Receiver(serverPort,1);
			runtimeRespond = new DatagramPacket("r".getBytes(), 1, mRobotAddress, robotPort);
		} catch (UnknownHostException | SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SmartServer server = new SmartServer();
		server.mReceiver.addUDPReceiveListener(server);
	}
	
	public void addOutputPin(GpioPinDigitalOutput pin){
		pinList.add(pin);
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		String input = new String(e.getUdpPacket().getData());

		switch (input) {
		case "m":
			try {
				serverSocket.send(runtimeRespond);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "a":
			pinList.get(nextPin).setState(true);
			try {
				serverSocket.send(new DatagramPacket(("s"+nextPin).getBytes(), ("s"+nextPin).getBytes().length,mRobotAddress,robotPort));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(nextPin<pinList.size()){
				nextPin++;	
			}else{
				nextPin=0;
			}
			break;
		default:
			break;
		}

	}

}
