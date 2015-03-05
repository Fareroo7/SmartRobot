package at.android.smartrobotapp.helpers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import at.android.smartrobot.network.UDPController;

public class SmartHandler extends Handler{
	
	private Context context;
	
	public SmartHandler(Context context) {
		this.context = context;
	}

	@Override
	public void handleMessage(Message msg) {
		
		switch(msg.what){
		
		case UDPController.WHAT_SOCKET_IO_ERROR:
		case UDPController.WHAT_SOCKET_RECEIVE_ERROR:
			showMessage("UDPController error!");
			break;
		
		case 1:
			showMessage("Signal" + msg.obj);
			break;
			
		default:
			showMessage("Error");
			break;
		
		}
		
		super.handleMessage(msg);
	}

	private void showMessage(String message){
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
	
}
