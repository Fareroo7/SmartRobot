package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class test_send {
	
	public static void main(String[] args) {
		try {
			InetAddress mInetAddress = InetAddress.getByName("10.0.0.37");
			int port = 50001;
			byte[] data = "Test".getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, mInetAddress, port);
			DatagramSocket socket = new DatagramSocket();
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
	
}
