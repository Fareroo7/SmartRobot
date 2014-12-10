package at.android.smartbot;

import java.util.EventListener;

interface USBReceiveListener extends EventListener{
	void onUSBReceive(USBReceiveEvent e);
}