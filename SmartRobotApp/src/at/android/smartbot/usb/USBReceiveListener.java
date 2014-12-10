package at.android.smartbot.usb;

import java.util.EventListener;

public interface USBReceiveListener extends EventListener{
	void onUSBReceive(USBReceiveEvent e);
}