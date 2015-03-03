package at.android.smartrobot.audio;

import java.util.ArrayList;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

public class AudioController extends Thread {

	public static final int SAMPLE_RATE = 44100;

	private AudioRecord audioRecorder;
	private short[] buffer;
	private boolean isRecording = false;
	
	private ArrayList<AudioEventListener> listeners = new ArrayList<AudioEventListener>();

	public AudioController() {
		int bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO,
				AudioFormat.ENCODING_PCM_16BIT);
		buffer = new short[bufferSize];
		audioRecorder = new AudioRecord(MediaRecorder.AudioSource.DEFAULT, SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO,
				AudioFormat.ENCODING_PCM_16BIT, bufferSize);
	}
	
	public void addSignalReceiveListener(AudioEventListener listener) {
		listeners.add(listener);
	}

	public void removeSignalReceiveListener(AudioEventListener listener) {
		listeners.remove(listener);
	}

	private synchronized void notifyUSBReceived(AudioEvent e) {
		for (AudioEventListener l : listeners) {
			l.onSignalReceive(e);
		}
	}
	
	public void startListening(){
		isRecording = true;
		this.start();
	}
	
	public void stopListening(){
		isRecording = false;
	}

	@Override
	public void run() {
		while(isRecording){
			int readSize = audioRecorder.read(buffer, 0, buffer.length);
			
		}
	}
	
	

}
