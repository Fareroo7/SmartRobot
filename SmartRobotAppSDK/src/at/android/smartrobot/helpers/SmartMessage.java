package at.android.smartrobot.helpers;

import android.os.*;

public class SmartMessage {
	
	public static final String BUNDLE_MESSAGE_KEY = "MESSAGE";
	
	public static Message create(int what, String message){
		Message m = new Message();
		Bundle b = new Bundle();
		b.putString(BUNDLE_MESSAGE_KEY, message);
		m.setData(b);
		return m;
	}

}
