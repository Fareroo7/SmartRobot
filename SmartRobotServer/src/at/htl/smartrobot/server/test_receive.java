package at.htl.smartrobot.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class test_receive {

	public static void main(String[] args) {
		Receiver r = new Receiver(50006, 1);
		
		class ReceivedListener implements UDPReceiveListener {

			@Override
			public void onReceive(UDPReceiveEvent e) {
				switch((char) e.getUdpPacket().getData()[0]){
				case 's':
					
					try {
						
						DatagramPacket p=new DatagramPacket("A".getBytes(),1,e.getUdpPacket().getAddress(),50100);
						DatagramSocket s=new DatagramSocket();
						s.send(p);
						s.close();
						
					} catch (SocketException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}

		}

		r.addUDPReceiveListener(new ReceivedListener());
		r.run();
	}

}
