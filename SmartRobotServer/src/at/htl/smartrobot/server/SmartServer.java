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

public class SmartServer{

	private Receiver udpReceiver;

	public Logger log = new Logger("./log.txt");
	private boolean isRunning = false;
	
	private SmartServerGpioThread mGpioThread= null;
	private SmartServerUdpThread mUdpThread= null;
	
	
	public SmartServer(String robotIp, int robotPort) {
		mUdpThread=new SmartServerUdpThread(robotIp, robotPort);
		mGpioThread = new SmartServerGpioThread();
	}

	public void startService() {
		mUdpThread.start();
		mGpioThread.start();
		udpReceiver.addUDPReceiveListener(mGpioThread);
		udpReceiver.addUDPReceiveListener(mUdpThread);
		udpReceiver.start();
		isRunning=true;
	}

	public void stopService() {
		udpReceiver.removeUDPReceiveListener(mGpioThread);
		udpReceiver.removeUDPReceiveListener(mUdpThread);
		udpReceiver.interrupt();
		isRunning = false;
	}

	public int getPort() {
		return mUdpThread.getPort();
	}

	public void setPort(int port) {
		mUdpThread.setPort(port);
	}

	public String getRobotAddress() {
		return mUdpThread.getRobotAddress();
	}

	public void setRobotAddress(String ip) {
		mUdpThread.setRobotAddress(ip);
	}

	public int getRobotPort() {
		return mUdpThread.getRobotPort();
	}

	public void setRobotPort(int robotPort) {
		mUdpThread.setRobotPort(robotPort);
	}

	public void setLogFile(String filepath) {
		log = new Logger(filepath);
	}

	public boolean isRunning() {
		return isRunning;
	}

	public String getRasPiPin() {
		return mGpioThread.getPinName();
	}
	
	public void testOutput(){
		mGpioThread.sendTestSignal(500);
	}

}
