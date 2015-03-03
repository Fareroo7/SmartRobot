package at.android.smartrobot.network;

import java.net.DatagramSocket;
import java.util.ArrayList;

public class UDPController extends Thread {

	private int port = 5000;
	private int packageSize = 1024;
	private DatagramSocket socket;
	private boolean isListening = false;

	private ArrayList<UDPReceiveListener> listeners = new ArrayList<UDPReceiveListener>();

	public UDPController() {
	}

	public UDPController(int port, int packageSize){
		this.port = port;
		this.packageSize = packageSize;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
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
			l.onReceive(e);
		}
	}
	
	@Override
	public void run() {
		
	}
	
}
