package at.android.smartrobot.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import at.android.smartrobot.helpers.SmartMessage;

public class UDPController extends Thread {

	public static final String TAG = "UDPController";

	public static final int WHAT_SOCKET_RECEIVE_ERROR = 301;
	public static final int WHAT_SOCKET_IO_ERROR = 302;
	
	public static final byte SEND_RUNTIME_MEASURE = 0x01;
	public static final byte RUNTIME_RESPONSE = 0x02;
	
	private Handler mHandler;

	private DatagramSocket socket;
	private boolean isListening = false;

	private int port = 5000;
	private int packageSize = 8;

	private InetAddress serverAddress;
	private int serverPort = 5001;

	private ArrayList<UDPReceiveListener> listeners = new ArrayList<UDPReceiveListener>();

	public UDPController(int port, int packageSize, String serverIP, int serverPort) throws UnknownHostException,
			SocketException {
		this.port = port;
		this.packageSize = packageSize;
		this.serverAddress = InetAddress.getByName(serverIP);
		this.serverPort = serverPort;
		this.mHandler = new Handler(Looper.getMainLooper());
		this.socket = new DatagramSocket(port);
	}

	public void send(byte b) throws IOException{
		DatagramPacket packet = new DatagramPacket(new byte[] {b}, 1, serverAddress, serverPort);
		socket.send(packet);
	}
	
	public void send(byte[] data) throws IOException {
		DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
		socket.send(packet);
	}

	public void send(char c) throws IOException {
		DatagramPacket packet = new DatagramPacket(new byte[] { (byte) c }, 1, serverAddress, serverPort);
		socket.send(packet);
	}

	public void send(String message) throws IOException {
		DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);
		socket.send(packet);
	}

	public void startListening() {
		isListening = true;
		this.start();
	}
	
	public void stopListening(){
		isListening = false;
	}

	public String getServerAddress() {
		return serverAddress.getHostAddress();
	}

	public void setServerAddress(String serverAddress) throws UnknownHostException {
		this.serverAddress = InetAddress.getByName(serverAddress);
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
		isListening = false;
	}

	public int getPackageSize() {
		return packageSize;
	}

	public void setPackageSize(int packageSize) {
		this.packageSize = packageSize;
	}

	public boolean isListening() {
		return isListening;
	}

	public void addUDPReceiveListener(UDPReceiveListener listener) {
		listeners.add(listener);
	}

	public void removeUDPReceiveListener(UDPReceiveListener listener) {
		listeners.remove(listener);
	}

	protected synchronized void notifyUDPReceived(UDPReceiveEvent e) {
		for (UDPReceiveListener l : listeners) {
			l.onUDPReceive(e);
		}
	}

	@Override
	public void run() {

		try {
			while (isListening) {
				DatagramPacket packet = new DatagramPacket(new byte[packageSize], packageSize);
				socket.receive(packet);
				notifyUDPReceived(new UDPReceiveEvent(getClass(), packet, System.nanoTime()));
			}

		} catch (SocketException e) {
			isListening = false;
			Log.d(TAG, "Socket error");
			mHandler.sendMessage(SmartMessage.create(WHAT_SOCKET_RECEIVE_ERROR, "Socket error"));
		} catch (IOException e) {
			isListening = false;
			Log.d(TAG, "Socket IO error");
			mHandler.sendMessage(SmartMessage.create(WHAT_SOCKET_IO_ERROR, "Socket IO error"));
		} finally {
			socket.close();
		}

	}

}
