package at.android.smartbot;

import java.util.EventObject;

public class USBReceiveEvent extends EventObject{

	private byte[] data;
	
	public USBReceiveEvent(Object source, byte[] data){
		super(source);
		this.data = data;
	}
	
	public byte[] getData(){
		return data;
	}
	
}