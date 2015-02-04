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

public class SmartRuntime implements UDPReceiveListener {

	
	private int port = 50001;
	private InetAddress robotAddress;
	private Receiver udpReceiver;
	
	
	
	@Override
	public void onReceive(UDPReceiveEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
