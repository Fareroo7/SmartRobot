package at.android.smartrobot.usb;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.android.future.usb.UsbAccessory;
import com.android.future.usb.UsbManager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import at.android.smartrobot.helpers.SmartMessage;
import at.htl.enginecontrol.EngineTask;

/**
 * @author Dominik Simon
 * @version 2.0
 */
public class USBController extends Thread {

	/**
	 * Tag of the Class (for LogCat debugging).
	 */
	public static final String TAG = "USBController";
	
	// Messages
	/**
	 * <b>What:</b> The Application has no USB permission.
	 * <em>This is for the Handler.</em>
	 */
	public static final int WHAT_USB_PREMISSION_DENIED = 200;
	
	/**
	 * <b>What:</b> An Error occurred while trying to reopen the Accessory.
	 * <em>This is for the Handler.</em>
	 */
	public static final int WHAT_ACCESSORY_RESUME_ERROR = 201;
	
	/**
	 * <b>What:</b> The Accessory is open.
	 * <em>This is for the Handler.</em>
	 */
	public static final int WHAT_ACCESSORY_OPEN = 202;
	
	/**
	 * <b>What:</b> An Error occurred while trying to open the Accessory.
	 * <em>This is for the Handler.</em>
	 */
	public static final int WHAT_ACCESSORY_OPEN_ERROR = 203;

	// USB Intent Filter
	private static final String ACTION_USB_PERMISSION = "at.android.smartbot.action.USB_PERMISSION";

	// USB + Intent
	private UsbManager mUsbManager;
	private UsbAccessory mAccessory;
	private PendingIntent mPermissionIntent;
	private boolean mPermissionRequestPending;

	// USB Input-/Outputstream
	private ParcelFileDescriptor mFileDescriptor;
	private FileInputStream mInputStream;
	private FileOutputStream mOutputStream;

	// Android
	private Context mContext;
	private Handler mHandler;

	// USBReceived listeners
	private ArrayList<USBReceiveListener> listeners = new ArrayList<USBReceiveListener>();

	private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (ACTION_USB_PERMISSION.equals(action)) {
				synchronized (this) {
					UsbAccessory accessory = UsbManager.getAccessory(intent);
					if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
						openAccessory(accessory);
					} else {
						mHandler.sendMessage(SmartMessage.create(WHAT_USB_PREMISSION_DENIED,
								"permission denied for accessory"));
						Log.d(TAG, "permission denied for accessory");
					}
					mPermissionRequestPending = false;
				}
			} else if (UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action)) {
				UsbAccessory accessory = UsbManager.getAccessory(intent);
				if (accessory != null && accessory.equals(mAccessory)) {
					closeAccessory();
				}
			}
		}
	};

	/**
	 * Default constructor.
	 * @param context The Application Context, is needed to register a Broadcastreceiver.
	 */
	public USBController(Context context) {
		mContext = context;
		mHandler = new Handler(Looper.getMainLooper());
		mUsbManager = UsbManager.getInstance(context);
		mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
		IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
		filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
		mContext.registerReceiver(mUsbReceiver, filter);
	}
	
	/**
	 * Consructor for passing the accessory over Intents.
	 * @param context The Application Context, is needed to register a Broadcastreceiver.
	 * @param accessory USB Accessory that is atteched.
	 */
	public USBController(Context context, UsbAccessory accessory){
		mContext = context;
		mHandler = new Handler(Looper.getMainLooper());
		mUsbManager = UsbManager.getInstance(context);
		mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
		IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
		filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
		mContext.registerReceiver(mUsbReceiver, filter);
		openAccessory(accessory);
	}

	/**
	 * Register a listener to be invoke when the USB received something
	 * 
	 * @param listener
	 *            The listener
	 */
	public void addUSBReceiveListener(USBReceiveListener listener) {
		listeners.add(listener);
	}

	/**
	 * Unregister the listener
	 * 
	 * @param listener
	 *            The listener
	 */
	public void removeUSBReceiveListener(USBReceiveListener listener) {
		listeners.remove(listener);
	}

	private synchronized void notifyUSBReceived(USBReceiveEvent e) {
		for (USBReceiveListener l : listeners) {
			l.onUSBReceive(e);
		}
	}

	/**
	 * Sends a String to an attached USB Accessory.
	 * @param message The message to be send.
	 * @throws IOException
	 */
	public void send(String message) throws IOException {
		if (mOutputStream != null)
			mOutputStream.write(message.getBytes());
	}

	/**
	 * Sends a Array of bytes to an attached USB Accessory.
	 * @param data The data to be send.
	 * @throws IOException
	 */
	public void send(byte[] data) throws IOException {
		if (mOutputStream != null)
			mOutputStream.write(data);
	}

	/**
	 * Sends an {@link EngineTask} to an attached USB Accessory.  
	 * @param task The {@link EngineTask} to be send.
	 * @throws IOException
	 */
	public void send(EngineTask task) throws IOException {
		if (mOutputStream != null)
			mOutputStream.write(task.getECP());
	}
	
	/**
	 * Starts to listening, if something is received from USB and fires then a {@link USBReceiveEvent} 
	 */
	public void startListening(){
		this.start();
	}

	/**
	 * Stops listening.
	 */
	public void stopListenting() {
		if (mInputStream != null)
			try {
				mInputStream.close();
			} catch (IOException e) {
			}
	}

	/**
	 * Call in the Application onResume method, to open the attached Accessory.
	 */
	public void onResume() {
		if (mInputStream != null && mOutputStream != null) {
			return;
		}
		
		UsbAccessory[] accessories = mUsbManager.getAccessoryList();
		UsbAccessory accessory = (accessories == null ? null : accessories[0]);
		if (accessory != null) {
			if (mUsbManager.hasPermission(accessory)) {
				openAccessory(accessory);
			} else {
				synchronized (mUsbReceiver) {
					if (!mPermissionRequestPending) {
						mUsbManager.requestPermission(accessory, mPermissionIntent);
						mPermissionRequestPending = true;
					}
				}
			}
		} else {
			mHandler.sendMessage(SmartMessage.create(WHAT_ACCESSORY_RESUME_ERROR, "Accessory is null!"));
			Log.d(TAG, "Accessory is null!");
		}
	}

	/**
	 * Call in the Application onPause method, to close the open Accessory.
	 */
	public void onPause() {
		closeAccessory();
	}

	/**
	 * Call in the Application onDestroy method, to close the open Accessory and unregister the Broadcastreceiver.
	 */
	public void onDestroy() {
		mContext.unregisterReceiver(mUsbReceiver);
		closeAccessory();
	}

	private void openAccessory(UsbAccessory accessory) {
		mFileDescriptor = mUsbManager.openAccessory(accessory);
		if (mFileDescriptor != null) {
			mAccessory = accessory;
			FileDescriptor fd = mFileDescriptor.getFileDescriptor();
			mInputStream = new FileInputStream(fd);
			mOutputStream = new FileOutputStream(fd);
			Thread thread = new Thread(null, this, "EC");
			thread.start();
			mHandler.sendMessage(SmartMessage.create(WHAT_ACCESSORY_OPEN, "Accessory open"));
			Log.d(TAG, "Accessory open");
		} else {
			mHandler.sendMessage(SmartMessage.create(WHAT_ACCESSORY_OPEN_ERROR, "Accessory open failed"));
			Log.d(TAG, "Accessory open failed");
		}
	}

	private void closeAccessory() {
		try {
			if (mFileDescriptor != null) {
				mFileDescriptor.close();
			}
		} catch (IOException e) {
		} finally {
			mFileDescriptor = null;
			mAccessory = null;
		}
	}

	@Override
	public void run() {
		int ret = 0;
		byte[] buffer = new byte[16];

		while (ret >= 0) {
			try {
				ret = mInputStream.read(buffer);
			} catch (IOException e) {
				break;
			}
			notifyUSBReceived(new USBReceiveEvent(getClass(), buffer));
		}

	}

}