package at.android.smartrobotapp;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import at.android.smartrobot.network.UDPController;
import at.android.smartrobot.network.UDPReceiveEvent;
import at.android.smartrobot.network.UDPReceiveListener;

public class SmartActivity extends ActionBarActivity implements UDPReceiveListener {

	//UI
	public Button btnSend;
	
	//Connections
	//public USBController usbController = null;
	public UDPController udpController = null;
	
	//Handler
	public SmartHandler handler = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart);
		
		handler = new SmartHandler(getApplicationContext());
		
		initUI();
		
		try {
			//usbController = new USBController(getApplicationContext());
			udpController = new UDPController(50001, 8, "10.0.0.17", 50000);
		} catch (UnknownHostException | SocketException e) {
			//TODO
			e.printStackTrace();
		}
		
		udpController.addUDPReceiveListener(this);
		udpController.startListening();
		
		btnSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					udpController.send('A');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}

	private void initUI() {
		btnSend = (Button) findViewById(R.id.btnSend);
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		Toast.makeText(getApplicationContext(), "...", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		if(udpController != null) udpController.stopListening();
		super.onDestroy();
	}
	
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.smart, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
