package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.sql.Time;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class test_send {
	
	public static long sendTime;
	public static long receiveTime;
	public static long timeOffset;
	
	public static InetAddress mInetAddress;
	public static int port;
	public static DatagramSocket socket;
	public static byte[] data = {(byte)'s'};
	public static DatagramPacket packet; 
	
	public static void main(String[] args) {
		Receiver r = new Receiver(50100, 8);
		
		class ReceivedListener implements UDPReceiveListener {

			@Override
			public void onReceive(UDPReceiveEvent e) {
				receiveTime = System.nanoTime();
				long clientTime = ByteUtils.bytesToLong(e.getUdpPacket().getData());
				
				timeOffset = receiveTime - (clientTime + ((receiveTime - sendTime) / 2));
				
				System.out.println("gesendet: \t" + sendTime);
				System.out.println("empfangen: \t" + receiveTime);
				System.out.println("clientTime: \t" + clientTime);
				System.out.println("offset: \t" + timeOffset);
				
			}

		}

		r.addUDPReceiveListener(new ReceivedListener());
		
		class SendTask extends TimerTask {

			@Override
			public void run() {
				try {
					
					mInetAddress = InetAddress.getByName("10.22.2.208");
					port = 50006;
					
					packet = new DatagramPacket(data, data.length, mInetAddress, port);
					socket = new DatagramSocket();
					
					sendTime = System.nanoTime();
					
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
		
		try {
			InetAddress mInetAddress = InetAddress.getByName("10.22.2.208");
			int port = 50006;
//			byte[] data = ByteBuffer.allocate(8).putLong(System.nanoTime()).array();
			packet = new DatagramPacket(data, data.length, mInetAddress, port);
			socket = new DatagramSocket();
			
			sendTime = System.nanoTime();
			
			socket.send(packet);
			
			Timer timer = new Timer();
			
			timer.schedule(new SendTask(), 500, 500);
			
			socket.close();
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
