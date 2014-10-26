package at.htl.smartrobot.server;

import java.util.Arrays;

public class test_receive {

	public static void main(String[] args) {
		Receiver r=new Receiver(50001,1024);
		class ReceivedListener implements UDPReceiveListener{

			@Override
			public void onReceive(UDPReceiveEvent e) {
				System.out.println(Arrays.toString(e.getUdpPacket().getData()));			
			}
			
		}
	}

}
