package at.android.smartrobot.audio;

import java.util.EventObject;

public class AudioEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	private long timestamp;
	
	public AudioEvent(Object source, long timestamp){
		super(source);
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

}
