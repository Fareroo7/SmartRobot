package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;


public class test_send {
	
	public static void main(String[] args) {
		Receiver r = new Receiver(50100, 8);
		
		class ReceivedListener implements UDPReceiveListener {

			@Override
			public void onReceive(UDPReceiveEvent e) {
				System.out.println(ByteUtils.bytesToLong(e.getUdpPacket().getData()));
			}

		}

		r.addUDPReceiveListener(new ReceivedListener());
		
		try {
			InetAddress mInetAddress = InetAddress.getByName("10.0.0.37");
			int port = 50006;
//			byte[] data = ByteBuffer.allocate(8).putLong(System.nanoTime()).array();
			byte[] data = {(byte)'s'};
			DatagramPacket packet = new DatagramPacket(data, data.length, mInetAddress, port);
			DatagramSocket socket = new DatagramSocket();
			socket.send(packet);
			
			r.run();
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
