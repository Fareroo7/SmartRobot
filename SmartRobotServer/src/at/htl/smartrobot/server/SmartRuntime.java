package at.htl.smartrobot.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.TimerTask;

import java.util.Timer;

import at.htl.smartrobot.server.utils.Receiver;
import at.htl.smartrobot.server.utils.UDPReceiveEvent;
import at.htl.smartrobot.server.utils.UDPReceiveListener;

public class SmartRuntime extends TimerTask implements UDPReceiveListener {

	private int serverPort = 50099;
	private int robotPort = 50100;

	private Receiver mReceiver;

	private InetAddress myAddress;
	private InetAddress mRobotAddress;

	private DatagramSocket robotSocket;

	private DatagramPacket runtimeRequest;

	private long avgRuntime = 0;
	private int mCounter = 0;

	// Log
	private File logFile = new File("./logs.csv");
	private int logCount = 0;
	private BufferedWriter log;

	private long timeAfter;
	private long timeBefore;
	private long timeSum;

	public SmartRuntime() {
		try {
			myAddress = InetAddress.getByName("132.168.88.21");
			mRobotAddress = InetAddress.getByName("132.168.88.22");
			robotSocket = new DatagramSocket();
			mReceiver = new Receiver(serverPort, 1);
			runtimeRequest = new DatagramPacket("m".getBytes(), 1, mRobotAddress, robotPort);
			log = new BufferedWriter(new FileWriter(logFile));
			mReceiver.addUDPReceiveListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SmartRuntime(String serverAddress, String robotAddress, int serverPort, int robotPort) {
		try {
			myAddress = InetAddress.getByName(serverAddress);
			mRobotAddress = InetAddress.getByName(robotAddress);
			this.serverPort = serverPort;
			this.robotPort = robotPort;
			mReceiver = new Receiver(serverPort, 1);
			robotSocket = new DatagramSocket();
			runtimeRequest = new DatagramPacket("m".getBytes(), 1, mRobotAddress, robotPort);
			log = new BufferedWriter(new FileWriter(logFile));
			mReceiver.addUDPReceiveListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		Timer t1 = new Timer();
		t1.schedule(new SmartRuntime("10.130.2.16","10.14.221.141",50042,50100), 0,5000);
	}
	
	@Override
	public void run() {
		avgRuntime = 0;
		timeSum = 0;
		try {
			log.write("n;runtime[ns];new avg runtime [ns]");
			log.newLine();
			timeBefore = System.nanoTime();
			robotSocket.send(runtimeRequest);
			System.out.println("---Start 20 Requesets---");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		String input = new String(e.getUdpPacket().getData());
		switch (input) {
		case "r":
			timeAfter = System.nanoTime();
			System.out.println(">Request "+mCounter+" ack received.");
			if (mCounter < 20) {
				mCounter++;
				try {
					timeBefore = System.nanoTime();
					robotSocket.send(runtimeRequest);
					timeSum += (timeAfter - timeBefore) / 2;
					avgRuntime = timeSum / mCounter;
					log.write(logCount+";"+((timeAfter - timeBefore) / 2)+";"+avgRuntime);
					log.newLine();
					logCount++;
					System.out.println(">Sended next request");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				timeSum += (timeAfter - timeBefore) / 2;
				avgRuntime = timeSum / (mCounter + 1);
				mCounter = 0;
			}

			break;
		}

	}

}
