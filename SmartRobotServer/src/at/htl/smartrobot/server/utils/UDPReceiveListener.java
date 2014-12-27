package at.htl.smartrobot.server.utils;

import java.util.EventListener;

public interface UDPReceiveListener extends EventListener{
	void onReceive(UDPReceiveEvent e);
}
