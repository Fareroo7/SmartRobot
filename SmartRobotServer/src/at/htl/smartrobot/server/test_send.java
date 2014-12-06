package at.htl.smartrobot.server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class test_send {
	
	public static long timeSend;
	public static long timeReceived;
	public static long timeOffset;

	
	public static InetAddress mInetAddress;
	public static int port;
	public static DatagramSocket socket;
	public static byte[] data = {(byte)'s'};
	public static DatagramPacket packet; 
	
	public static BufferedWriter bw;
	
	public static ArrayList<Long> offsets = new ArrayList<Long>();
	
	public static int counter = 1;
	
	public static void main(String[] args) {

		Receiver r = new Receiver(50100, 4);
		try {
			bw = new BufferedWriter(new FileWriter(new File("./offsets.csv")));
			bw.write("Counter;Offset Time");
			bw.newLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		class ReceivedListener implements UDPReceiveListener {

			@Override
			public void onReceive(UDPReceiveEvent e) {
//				receiveTime = System.nanoTime();
				if(e.getUdpPacket().getData()[0] == 'A'){
				
//					timeOffset = receiveTime - sendTime;
					
	//				System.out.println("gesendet: \t" + sendTime);
	//				System.out.println("empfangen: \t" + receiveTime);
	//				System.out.println("clientTime: \t" + clientTime);
	//				System.out.println("offset: \t" + timeOffset);
					
					System.out.println(counter);
					
					try {
						bw.write(counter+";"+timeOffset);
						bw.newLine();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					offsets.add(timeOffset);
					
					counter ++;
				}
				
			}

		}

		r.addUDPReceiveListener(new ReceivedListener());
		
		class SendTask extends TimerTask {

			@Override
			public void run() {
				if(counter <= 1000){
					try {
						
						mInetAddress = InetAddress.getByName("127.0.0.1");
						port = 50006;
						
						packet = new DatagramPacket(data, data.length, mInetAddress, port);
						socket = new DatagramSocket();
						
//						sendTime = System.nanoTime();
						
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
				}else{
					
					long min = Long.MAX_VALUE;
					long max = Long.MIN_VALUE;
					long result = 0;
					
					for(long offset : offsets){
						if(offset < min){
							min = offset;
						}
						if(offset > max){
							max = offset;
						}
						result += offset;
					}
					
					result -= min;
					result -= max;
					
					result /= 998;
					
					System.out.println("MIN: \t" + min);
					System.out.println("MAX: \t" + max);
					System.out.println("AVG: \t" + result);
					
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					this.cancel();
				}
			}
		}
		
		Timer timer = new Timer();
		
		timer.schedule(new SendTask(), 100, 100);
		
		socket.close();
		r.run();
		
	}
	
}
