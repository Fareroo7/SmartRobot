package at.android.smartrobot.network;

import java.util.EventListener;

public interface UDPReceiveListener extends EventListener {
	void onReceive(UDPReceiveEvent e);
}
