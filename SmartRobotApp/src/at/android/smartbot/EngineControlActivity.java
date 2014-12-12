package at.android.smartbot;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import at.android.smartbot.usb.USBReceiveEvent;
import at.android.smartbot.usb.USBReceiveListener;
import at.htl.enginecontrol.EngineControl;
import at.htl.enginecontrol.EngineTask;

public class EngineControlActivity extends ActionBarActivity implements USBReceiveListener {

	private Spinner actionCodeSpinner;
	private Spinner directionCodeSpinner;
	private EditText txtId;
	private EditText txtLeft;
	private EditText txtRight;
	private EditText txtDuration;
	
	private Button btnSend;
	
	private EngineController mEngineController;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_engine_control);
		
		mEngineController = new EngineController(EngineControlActivity.this);
		mEngineController.addUSBReceiveListener(this);
		
		actionCodeSpinner = (Spinner) findViewById(R.id.spinner_action_code);
		ArrayAdapter<CharSequence> ActionCodeAdapter = ArrayAdapter.createFromResource(this, R.array.action_code, R.layout.support_simple_spinner_dropdown_item);
		ActionCodeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		actionCodeSpinner.setAdapter(ActionCodeAdapter);
		
		directionCodeSpinner = (Spinner) findViewById(R.id.spinner_direction_code);
		ArrayAdapter<CharSequence> DirectionCodeAdapter = ArrayAdapter.createFromResource(this, R.array.direction_code, R.layout.support_simple_spinner_dropdown_item);
		DirectionCodeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		directionCodeSpinner.setAdapter(DirectionCodeAdapter);
			
		txtId = (EditText) findViewById(R.id.et_id);
		txtLeft = (EditText) findViewById(R.id.et_dutycycle_left);
		txtRight = (EditText) findViewById(R.id.et_dutycycle_right);
		txtDuration = (EditText) findViewById(R.id.et_duration);
		
		btnSend = (Button) findViewById(R.id.btnSendECP);
		btnSend.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				sendECP();			
			}
			
		});
		
	}
	
	public void sendECP(){
		String actionCode = actionCodeSpinner.getSelectedItem().toString();
		String directionCode = directionCodeSpinner.getSelectedItem().toString();
		
		byte id;
		byte aCode = 0;
		byte dCode = 0;
		byte dutyLeft;
		byte dutyRight;
		int duration;
		
		id = (byte) (Integer.parseInt(txtId.getText().toString()) & 0xff);
		dutyLeft = (byte) (Integer.parseInt(txtLeft.getText().toString()) & 0xff);
		dutyRight = (byte) (Integer.parseInt(txtRight.getText().toString()) & 0xff);
		duration = Integer.parseInt(txtDuration.getText().toString());
		
			
		if(actionCode.equals("New Task")){
			aCode = EngineControl.A_NEW;
		} else if(actionCode.equals("Delete Task")){
			aCode = EngineControl.A_DELETE_ID;
		} else if(actionCode.equals("Update Task")){
			aCode = EngineControl.A_UPDATE_ID;
		} else if(actionCode.equals("Delete All")){
			aCode = EngineControl.A_DELETE_ALL;
		} else if(actionCode.equals("Start up")){
			aCode = EngineControl.A_ACCELERATE;
		} else if(actionCode.equals("Stop")){
			aCode = EngineControl.A_STOP;
		}
		
		if(directionCode.equals("Forward")){
			dCode = EngineControl.DIRECTION_FORWARD;
		} else if(directionCode.equals("Backward")){
			dCode = EngineControl.DIRECTION_BACKWARD;
		} else if(directionCode.equals("Clockwise")){
			dCode = EngineControl.DIRECTION_TURN_CLOCKWISE;
		} else if(directionCode.equals("Anti-Clockwise")){
			dCode = EngineControl.DIRECTION_TURN_ANTICLOCKWISE;
		}
		
//		showMessage(id + " / " + aCode + " / " + dCode + " \n " + dutyLeft + " / " + dutyRight + " / " + duration);
		mEngineController.send(new EngineTask(id, aCode, dCode, dutyLeft, dutyRight, duration));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.engine_control, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}else if (id == R.id.action_back) {
			showMainActivity();
		}
		return super.onOptionsItemSelected(item);
	}

	private void showMainActivity() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (mEngineController != null)
			mEngineController.resume();

	}

	@Override
	public void onPause() {
		super.onPause();
		if (mEngineController != null)
			mEngineController.pause();
	}

	@Override
	public void onDestroy() {
		if (mEngineController != null)
			mEngineController.destroy();
		super.onDestroy();
	}
	
	public void showMessage(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}
	
	private void showMessageFromThread(final String text){
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(EngineControlActivity.this, text, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onUSBReceive(USBReceiveEvent e) {
		showMessageFromThread(e.getData()[1] + " / " + e.getData()[2]);
	}
}
