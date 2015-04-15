package at.android.smartrobotapp.helpers;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import at.android.smartrobot.network.UDPController;
import at.android.smartrobot.usb.USBController;

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
			
		case USBController.WHAT_ACCESSORY_OPEN:
			showMessage("USB open");
			break;
			
		case USBController.WHAT_ACCESSORY_OPEN_ERROR:
		case USBController.WHAT_ACCESSORY_RESUME_ERROR:
		case USBController.WHAT_USB_PREMISSION_DENIED:
			showMessage("USB error");
			break;
		
		case 1:
			showMessage(msg.obj + "");
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
