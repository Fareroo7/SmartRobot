package at.htl.smartrobot.server;

import at.htl.smartrobot.server.utils.Receiver;
import at.htl.smartrobot.server.utils.UDPReceiveEvent;
import at.htl.smartrobot.server.utils.UDPReceiveListener;

public class RespondServer implements UDPReceiveListener{

	public static void main(String[] args) {
		Receiver mReceiver = new Receiver(5042, Long.SIZE / 8);
		mReceiver.addUDPReceiveListener(new SimonTestSend());
		mReceiver.start();
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		
		
	}

}
