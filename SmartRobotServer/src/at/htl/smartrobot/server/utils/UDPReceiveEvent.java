package at.htl.smartrobot.server.utils;

import java.net.DatagramPacket;
import java.util.EventObject;

public class UDPReceiveEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	private DatagramPacket udpPacket;
	private long timestamp;

	public UDPReceiveEvent(Object source, DatagramPacket udpPacket, long timestamp) {
		super(source);
		this.udpPacket = udpPacket;
		this.timestamp = timestamp;
	}

	public DatagramPacket getUdpPacket() {
		return udpPacket;
	}
	
	public long getTimestamp(){
		return timestamp;
	}

}
