package at.htl.smartrobot.server.utils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.event.EventListenerList;

public class Receiver extends Thread {

	private ArrayList<UDPReceiveListener> listeners = new ArrayList<UDPReceiveListener>();

	private int port = 50000;
	private int packageSize = 1024;
	DatagramSocket socket;

	public Receiver() {
	}

	public Receiver(int port, int packageSize) {
		this.port = port;
		this.packageSize = packageSize;
	}
	
	public void setPort(int port){
		this.port = port;
	}
	
	public int getPort(){
		return port;
	}
	
	public void setPackageSize(int packageSize){
		this.packageSize = packageSize;
	}
	
	public int getPackageSize(){
		return packageSize;
	}

	@Override
	public void run() {
		try {
			
			socket = new DatagramSocket(port);

			while (!this.isInterrupted()) {
				DatagramPacket packet = new DatagramPacket(new byte[packageSize], packageSize);
				socket.receive(packet);
				notifyUDPReceived(new UDPReceiveEvent(this, packet, System.nanoTime()));

			}

			if(!socket.isClosed())socket.close();

		} catch (SocketException e) {
			System.err.println("Interrupted!");
			interrupt();
		} catch (IOException e) {
			System.err.println("Interrupted!");
			interrupt();
		}
		super.run();
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
	public void interrupt() {
		if(!socket.isClosed()) socket.close();
		super.interrupt();
	}

}
