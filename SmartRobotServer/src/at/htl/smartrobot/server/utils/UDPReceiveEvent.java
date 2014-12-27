package at.htl.smartrobot.server.utils;

import java.net.DatagramPacket;
import java.util.EventObject;

public class UDPReceiveEvent extends EventObject {

	private DatagramPacket udpPacket;

	public UDPReceiveEvent(Object source, DatagramPacket udpPacket) {
		super(source);
		this.udpPacket = udpPacket;
	}

	public DatagramPacket getUdpPacket() {
		return udpPacket;
	}

}
