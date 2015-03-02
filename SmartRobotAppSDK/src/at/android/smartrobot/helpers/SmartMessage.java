package at.android.smartrobot.helpers;

import android.os.*;

/**
 * This is a helper for creating Messages for android handler.
 * 
 * @see Handler
 * @author Dominik Simon
 * @version 1.0
 */
public class SmartMessage {
	
	/**
	 * The key for the message bundle data.
	 */
	public static final String BUNDLE_MESSAGE_KEY = "MESSAGE";
	
	/**
	 * Creates a new Message.
	 * @param what What type of message.
	 * @param message The message data.
	 * @return Message object.
	 */
	public static Message create(int what, String message){
		Message m = new Message();
		Bundle b = new Bundle();
		b.putString(BUNDLE_MESSAGE_KEY, message);
		m.setData(b);
		return m;
	}

}
