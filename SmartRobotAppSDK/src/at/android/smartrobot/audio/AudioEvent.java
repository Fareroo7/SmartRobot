package at.android.smartrobot.audio;

import java.util.EventObject;

public class AudioEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	
	private long timestamp;
	private short amplitude;
	
	public AudioEvent(Object source, short amplitude, long timestamp){
		super(source);
		this.amplitude = amplitude;
		this.timestamp = timestamp;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public short getAmplitude() {
		return amplitude;
	}

}
