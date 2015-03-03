package at.android.smartrobot.network;

import java.net.DatagramPacket;
import java.util.ArrayList;

public class UDPController {

	private int port = 5000;
	private int packageSize = 1024;
	DatagramPacket packet;

	private ArrayList<UDPReceiveListener> listeners = new ArrayList<UDPReceiveListener>();

	public UDPController() {
	}

	public UDPController(int port, int packageSize){
		this.port = port;
		this.packageSize = packageSize;
	}
	
	
}
