package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import at.htl.smartrobot.server.utils.ByteUtils;
import at.htl.smartrobot.server.utils.Logger;
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
	public static long receiveTime;
	public static int counter = 1;
	
	static Logger log = new Logger("./log.csv");
	
	public static void main(String[] args) {
		Receiver mReceiver = new Receiver(50001, 1);
		mReceiver.addUDPReceiveListener(new SimonTestSend());
		mReceiver.start();
		
		try {
			
			mInetAddress = InetAddress.getByName("192.168.88.251");
			port = 50000;
			
			packet = new DatagramPacket(new byte[]{ 0x01 }, 1, mInetAddress, port);
			socket = new DatagramSocket();
			
			log.write("Timestamp;Anzahl;Laufzeit [ns];");
			
			for(int i = 0; i < 100; i++){
				socket.send(packet);
				sendTime = System.nanoTime();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			log.close();
		
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
		receiveTime = e.getTimestamp();
		log.write(";" + counter + ";" + (receiveTime - sendTime) / 2 + ";");
		counter++;
		System.out.println((receiveTime - sendTime) / 2);
	}
}
