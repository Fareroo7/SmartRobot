package at.android.smartrobot.network;

import java.net.DatagramPacket;
import java.util.EventObject;

public class UDPReceiveEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	private DatagramPacket packet;
	private long timestamp;

	public UDPReceiveEvent(Object source, DatagramPacket packet, long timestamp) {
		super(source);
		this.packet = packet;
		this.timestamp = timestamp;		
	}

	public DatagramPacket getPacket() {
		return packet;
	}

	public long getTimestamp() {
		return timestamp;
	}

}
