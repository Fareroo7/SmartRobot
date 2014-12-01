package at.android.smartbot;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EngineControlActivity extends ActionBarActivity {

	private Spinner actionCodeSpinner;
	private Spinner directionCodeSpinner;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_engine_control);
		
		actionCodeSpinner = (Spinner) findViewById(R.id.spinner_action_code);
		ArrayAdapter<CharSequence> ActionCodeAdapter = ArrayAdapter.createFromResource(this, R.array.action_code, R.layout.support_simple_spinner_dropdown_item);
		ActionCodeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		actionCodeSpinner.setAdapter(ActionCodeAdapter);
		
		directionCodeSpinner = (Spinner) findViewById(R.id.spinner_direction_code);
		ArrayAdapter<CharSequence> DirectionCodeAdapter = ArrayAdapter.createFromResource(this, R.array.direction_code, R.layout.support_simple_spinner_dropdown_item);
		DirectionCodeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
		directionCodeSpinner.setAdapter(DirectionCodeAdapter);
		
		
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
}
