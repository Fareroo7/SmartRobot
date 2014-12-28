package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import at.htl.smartrobot.server.utils.ByteUtils;

public class SimonTestSend {
	public static InetAddress mInetAddress;
	public static int port;
	public static DatagramSocket socket;
	public static byte[] data = {(byte)'s'};
	public static DatagramPacket packet; 
	
	public static void main(String[] args) {
		for(int i = 0; i < 100; i++){
			try {
				
				mInetAddress = InetAddress.getByName("10.0.0.51");
				port = 50010;
				
				packet = new DatagramPacket(ByteUtils.longToBytes(System.nanoTime()), Long.SIZE / 8, mInetAddress, port);
				socket = new DatagramSocket();
			
//				sendTime = System.nanoTime();
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
}
