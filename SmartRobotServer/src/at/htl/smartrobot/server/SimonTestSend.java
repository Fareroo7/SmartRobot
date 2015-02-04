package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import at.htl.smartrobot.server.utils.ByteUtils;
import at.htl.smartrobot.server.utils.Receiver;
import at.htl.smartrobot.server.utils.UDPReceiveEvent;
import at.htl.smartrobot.server.utils.UDPReceiveListener;

public class SimonTestSend implements UDPReceiveListener{
	public static InetAddress mInetAddress;
	public static int port;
	public static DatagramSocket socket;
	public static byte[] data = {(byte)'s'};
	public static DatagramPacket packet; 
	public static long sendTime;
	public static long recevieTime;
	public static long serverTime;
	
	public static void main(String[] args) {
//		Receiver mReceiver = new Receiver(5042, Long.SIZE / 8);
//		mReceiver.addUDPReceiveListener(new SimonTestSend());
//		mReceiver.start();
		
		try {
			
			mInetAddress = InetAddress.getByName("192.168.88.251");
			port = 50000;
			
			packet = new DatagramPacket(new byte[]{ 0x01 }, 1, mInetAddress, port);
			socket = new DatagramSocket();
			
			socket.send(packet);
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		recevieTime = System.nanoTime();
		serverTime = ByteUtils.bytesToLong(e.getUdpPacket().getData());
		System.out.println(recevieTime - sendTime + ";");
	}
}
