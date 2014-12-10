package at.android.smartbot;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelFileDescriptor;
import android.widget.Toast;
import at.htl.enginecontrol.EngineTask;

import com.android.future.usb.UsbAccessory;
import com.android.future.usb.UsbManager;

/**
 * 
 * @author Simon Dominik
 * @version 1.0
 */

public class EngineController implements Runnable {

	private static final String ACTION_USB_PERMISSION = "at.android.smartbot.action.USB_PERMISSION";

	private UsbManager mUsbManager;
	private PendingIntent mPermissionIntent;
	private boolean mPermissionRequestPending;

	private UsbAccessory mAccessory;
	private ParcelFileDescriptor mFileDescriptor;
	private FileInputStream mInputStream;
	private FileOutputStream mOutputStream;

	private Context mContext;
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
						showMessage("permission denied for accessory " + accessory);
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

	public EngineController(Context context) {
		mContext = context;
		mUsbManager = UsbManager.getInstance(mContext);
		mPermissionIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(ACTION_USB_PERMISSION), 0);
		IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
		filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
		mContext.registerReceiver(mUsbReceiver, filter);
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

	/**
	 * Sends a String to the Arduino.
	 * 
	 * @param w
	 *            the String to be send.
	 */
	public void send(String w) {
		if (mOutputStream != null) {
			try {
				mOutputStream.write(w.getBytes());
			} catch (IOException e) {
				showMessage("write failed");
			}
		}

	}

	/**
	 * Sends a Byte-Array to the Arduino.
	 * 
	 * @param w
	 *            the Byte-Array to be send.
	 */
	public void send(byte[] w) {
		if (mOutputStream != null) {
			try {
				mOutputStream.write(w);
			} catch (IOException e) {
				showMessage("write failed");
			}
		}

	}

	public void send(EngineTask task) {
		if (mOutputStream != null) {
			try {
				mOutputStream.write(task.getECP());
			} catch (IOException e) {
				showMessage("write failed");
			}
		}
	}

	public void resume() {
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
			showMessage("mAccessory is null");
		}
	}

	public void pause() {
		closeAccessory();
	}

	public void destroy() {
		mContext.unregisterReceiver(mUsbReceiver);
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
			showMessage("accessory opened");
			enableControls(true);
		} else {
			showMessage("accessory open fail");
		}
	}

	private void closeAccessory() {
		enableControls(false);

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

	private void enableControls(boolean b) {
		// TODO write code to disable the GUI

	}

	public void addUSBReceiveListener(USBReceiveListener listener) {
		listeners.add(listener);
	}

	public void removeUSBReceiveListener(USBReceiveListener listener) {
		listeners.remove(listener);
	}

	protected synchronized void notifyUSBReceived(USBReceiveEvent e) {
		for (USBReceiveListener l : listeners) {
			l.onUSBReceive(e);
		}
	}

	/**
	 * Shows a {@link Toast} (only for testing)
	 * 
	 * @param message
	 *            the message to be shown in the Toast
	 */
	private void showMessage(String message) {
		Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
	}

}
