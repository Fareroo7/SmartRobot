package at.android.smartrobot.usb;

import java.util.EventObject;

public class USBReceiveEvent extends EventObject{

	private static final long serialVersionUID = 1L;
	private byte[] data;
	
	public USBReceiveEvent(Object source, byte[] data){
		super(source);
		this.data = data;
	}
	
	public byte[] getData(){
		return data;
	}
	
}