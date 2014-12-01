package at.android.smartbot;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import at.htl.geometrics.Circle;
import at.htl.geometrics.Point;
import at.htl.smartbot.Trilateration;

public class TrilateratetionActivity extends ActionBarActivity {

	private EditText mXOne;
	private EditText mYOne;
	private EditText mROne;
	
	private EditText mXTwo;
	private EditText mYTwo;
	private EditText mRTwo;
	
	private EditText mXThree;
	private EditText mYThree;
	private EditText mRThree;
	
	private Button btnTrilaterate;
	
	private TextView txtPosition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trilateratetion);
		
		mXOne = (EditText) findViewById(R.id.teXOne);
		mYOne = (EditText) findViewById(R.id.teYOne);
		mROne = (EditText) findViewById(R.id.teROne);
		
		mXTwo = (EditText) findViewById(R.id.teXTwo);
		mYTwo = (EditText) findViewById(R.id.teYTwo);
		mRTwo = (EditText) findViewById(R.id.teRTwo);
		
		mXThree = (EditText) findViewById(R.id.teXThree);
		mYThree = (EditText) findViewById(R.id.teYThree);
		mRThree = (EditText) findViewById(R.id.teRThree);
		
		txtPosition = (TextView) findViewById(R.id.txtPosition);
		
		btnTrilaterate = (Button) findViewById(R.id.btnTrilaterate);
		
		btnTrilaterate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Circle cOne = new Circle(new Point(stringToDouble(mXOne.getText().toString()), stringToDouble(mYOne.getText().toString())), stringToDouble(mROne.getText().toString()));
				Circle cTwo = new Circle(new Point(stringToDouble(mXTwo.getText().toString()), stringToDouble(mYTwo.getText().toString())), stringToDouble(mRTwo.getText().toString()));
				Circle cThree = new Circle(new Point(stringToDouble(mXThree.getText().toString()), stringToDouble(mYThree.getText().toString())), stringToDouble(mRThree.getText().toString()));
				
				long before, after;
				
				Point pos = new Point();
				
				before = System.nanoTime();
				pos = Trilateration.trilaterate(cOne, cTwo, cThree);
				after = System.nanoTime();
				
				txtPosition.setText(pos.toString() + "\n" + (after - before) / 1000 + "us");
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trilateratetion, menu);
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
	
	private double stringToDouble(String number){
		if(number.contains(".")){
			//Only parse to Double
			return Double.parseDouble(number);
		}else if(number.contains(",")){
			//Replace , with . and then parse to Double
			number.replace(",", ".");
			return Double.parseDouble(number);
		}else{
			//Append .0 and then parse to Double
			number = number + ".0";
			return Double.parseDouble(number);
		}
	}
}
