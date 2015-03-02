package at.android.smartrobot.usb;

import java.util.EventListener;

public interface USBReceiveListener extends EventListener{
	void onUSBReceive(USBReceiveEvent e);
}