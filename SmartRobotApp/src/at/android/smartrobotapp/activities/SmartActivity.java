package at.android.smartrobotapp.activities;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import at.android.smartrobot.audio.AudioController;
import at.android.smartrobot.audio.AudioEvent;
import at.android.smartrobot.audio.AudioEventListener;
import at.android.smartrobot.network.UDPController;
import at.android.smartrobot.network.UDPReceiveEvent;
import at.android.smartrobot.network.UDPReceiveListener;
import at.android.smartrobot.usb.USBReceiveEvent;
import at.android.smartrobot.usb.USBReceiveListener;
import at.android.smartrobotapp.helpers.SmartHandler;

public class SmartActivity extends ActionBarActivity implements UDPReceiveListener, USBReceiveListener,
		AudioEventListener {

	public static final String TAG = "SmartActivity";

	// UI
	public Button btnSend;

	// Connections
	// public USBController usbController;
	public UDPController udpController;
	public AudioController audioController;

	// Handler
	public SmartHandler handler = null;

	public boolean isWaitingForSignal = false;
	public long timeSendRequest;
	public long timeReceiveAcknowlage;
	public long timeReceiveSignal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart);

		handler = new SmartHandler(getApplicationContext());

		initConnections();

		initUI();

		btnSend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					udpController.send(UDPController.SEND_RUNTIME_MEASURE);
					timeSendRequest = System.nanoTime();
					isWaitingForSignal = true;
				} catch (IOException e) {
					handler.sendEmptyMessage(0);
				}
			}
		});

	}

	private void initUI() {
		btnSend = (Button) findViewById(R.id.btnSend);
	}

	private void initConnections() {
		try {
			// usbController = new USBController(getApplicationContext());
			udpController = new UDPController(50001, 8, "192.168.88.248", 50000);
		} catch (UnknownHostException | SocketException e) {
			// TODO
			e.printStackTrace();
		}

		udpController.addUDPReceiveListener(this);
		udpController.startListening();

		audioController = new AudioController();
		audioController.addSignalReceiveListener(this);
		audioController.startListening();
	}

	@Override
	protected void onDestroy() {
		if (udpController != null)
			udpController.stopListening();
		if (audioController != null)
			audioController.stopListening();
		super.onDestroy();
	}

	@Override
	public void onUSBReceive(USBReceiveEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUDPReceive(UDPReceiveEvent e) {
		timeReceiveAcknowlage = e.getTimestamp();
	}

	@Override
	public void onSignalReceive(AudioEvent e) {
		if (isWaitingForSignal) {
			timeReceiveSignal = e.getTimestamp();
			isWaitingForSignal = false;

			long sendezeit = timeSendRequest + ((timeReceiveAcknowlage - timeSendRequest) / 2);

			long laufzeit = timeReceiveSignal - sendezeit;

			laufzeit /= 1000;
			laufzeit /= 1000;
			
			Message m = new Message();
			m.what = 1;
			m.obj = laufzeit;
			handler.sendMessage(m);
		}
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.smart, menu);
	// return true;
	// }

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }
}
