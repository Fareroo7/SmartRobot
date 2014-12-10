package at.android.smartbot.usb;

import java.util.ArrayList;

public class USBReceiver {
	
	private ArrayList<USBReceiveListener> listeners = new ArrayList<USBReceiveListener>();
	
	public void addUSBReceiveListener(USBReceiveListener listener) {
		listeners.add(listener);
	}

	public void removeUSBReceiveListener(USBReceiveListener listener) {
		listeners.remove(listener);
	}

	public synchronized void notifyUSBReceived(USBReceiveEvent e) {
		for (USBReceiveListener l : listeners) {
			l.onUSBReceive(e);
		}
	}
}