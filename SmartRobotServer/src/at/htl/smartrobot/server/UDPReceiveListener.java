package at.htl.smartrobot.server;

import java.util.EventListener;

interface UDPReceiveListener extends EventListener{
	void onReceive(UDPReceiveEvent e);
}
