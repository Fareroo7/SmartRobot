package at.android.smartbot;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import at.htl.EngineControl.EngineControl;


public class MainActivity extends ActionBarActivity {

	private Accelerometer mAccelerometer;

	private EngineController mEngineControl;
	private Handler mUsbHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				byte[] rec = (byte[]) msg.obj;
				if(rec[1] != 1){
					String out = rec[1] + " / " + rec[2];
					showMessage(out);
				}
				break;
			}
		}

	};
	
	private OnClickListener mControlButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			
				case R.id.btnForward:
					mEngineControl.send(EngineControl.driveStraight(true, 2.0));
//					mEngineControl.send(ECP.getECP(ECP.DIRECTION_FORWARD, seekBar.getProgress(), seekBar.getProgress()));
//					mEngineControl.driveForward(1.5);
//					showMessage("Forward");
					break;
					
				case R.id.btnBackward:
					mEngineControl.send(EngineControl.driveStraight(false, 3.0));
//					mEngineControl.send(ECP.getECP(ECP.DIRECTION_BACKWARD, seekBar.getProgress(), seekBar.getProgress()));
//					mEngineControl.driveBackward(1.5);
//					showMessage("Backward");
					break;
				
				case R.id.btnLeft:
					mEngineControl.send(EngineControl.driveCurve(true, 2.0, Math.PI / 2));
//					showMessage("Left");
					break;
				
				case R.id.btnRight:
					mEngineControl.send(EngineControl.driveCurve(false, 2.0, Math.PI / 2));
//					showMessage("Right");
					break;
				
				case R.id.btnStop:
					mEngineControl.send(EngineControl.abortAll());
//					mEngineControl.stop();
//					showMessage("Stop");
					break;
			}
		}
	};

	private Button btnSend;
	private Button btnAcc;
	
	private Button btnLeft;
	private Button btnRight;
	private Button btnForward;
	private Button btnBackward;
	private Button btnStop;
	
	private SeekBar seekBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mEngineControl = new EngineController(getApplicationContext(), mUsbHandler);
		mAccelerometer = new Accelerometer((SensorManager) this.getSystemService(SENSOR_SERVICE));
		mAccelerometer.register();
		
		btnLeft = (Button) findViewById(R.id.btnLeft);
		btnRight = (Button) findViewById(R.id.btnRight);
		btnForward = (Button) findViewById(R.id.btnForward);
		btnBackward = (Button) findViewById(R.id.btnBackward);
		btnStop = (Button) findViewById(R.id.btnStop);
		
//		seekBar = (SeekBar) findViewById(R.id.seekBarSpeed);
//		
//		btnLeft.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				if(event.getAction() == MotionEvent.ACTION_DOWN){
//					//Button pressed
////					mEngineControl.send(ECP.getECP(ECP.DIRECTION_TURN_ANTICLOCKWISE, seekBar.getProgress(), seekBar.getProgress()));
//				} else if(event.getAction() == MotionEvent.ACTION_UP) { 
//					//Button released
////					mEngineControl.stop();
//				}
//				
//				return false;
//			}
//		});
//		
//		btnRight.setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				if(event.getAction() == MotionEvent.ACTION_DOWN){
//					//Button pressed
////					mEngineControl.send(ECP.getECP(ECP.DIRECTION_TURN_CLOCKWISE, seekBar.getProgress(), seekBar.getProgress()));
//				} else if(event.getAction() == MotionEvent.ACTION_UP) { 
//					//Button released
////					mEngineControl.stop();
//				}
//				
//				return false;
//			}
//		});
		
		btnLeft.setOnClickListener(mControlButtonListener);
		btnRight.setOnClickListener(mControlButtonListener);
		btnForward.setOnClickListener(mControlButtonListener);
		btnBackward.setOnClickListener(mControlButtonListener);
		btnStop.setOnClickListener(mControlButtonListener);

		btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mEngineControl.send(EngineControl.turn(true, Math.PI / 2));
			}
		});

		btnAcc = (Button) findViewById(R.id.btnAccTest);
		btnAcc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showMessage("Accleleration X: " + mAccelerometer.getAccelerationX());

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		} else if (id == R.id.action_trilaterate){
			showTrilaterationActivity();
		} else if (id == R.id.action_engine_control){
			showEngineControlActivity();
		}
		return super.onOptionsItemSelected(item);
	}

	private void showTrilaterationActivity() {
		Intent intent = new Intent(this, TrilateratetionActivity.class);
		startActivity(intent);
	}
	
	private void showEngineControlActivity() {
		Intent intent = new Intent(this, EngineControl.class);
		startActivity(intent);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mEngineControl != null)
			mEngineControl.resume();
		if (mAccelerometer != null)
			mAccelerometer.resume();

	}

	@Override
	public void onPause() {
		super.onPause();
		if (mEngineControl != null)
			mEngineControl.pause();
		if (mAccelerometer != null)
			mAccelerometer.pause();
	}

	@Override
	public void onDestroy() {
		if (mEngineControl != null)
			mEngineControl.destroy();
		if (mAccelerometer != null)
			mAccelerometer.destroy();
		super.onDestroy();
	}

	public void showMessage(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}

}
