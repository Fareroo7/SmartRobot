package at.android.smartbot;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Accelerometer implements SensorEventListener {

	private double accX;
	private double accY;
	private double accZ;

	private double accXout;
	private double accYout;
	private double accZout;

	private double minX;
	private double minY;
	private double minZ;

	private double maxX;
	private double maxY;
	private double maxZ;

	private int valueCount = 0;

	private boolean accChange = false;

	private SensorManager sManager;
	private Sensor mAccSensor;

	public Accelerometer(SensorManager mSensorManager) {
		sManager = mSensorManager;
		mAccSensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	public void register() {
		sManager.registerListener(this, mAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	public void pause() {
		sManager.unregisterListener(this);
	}

	public void resume() {
		sManager.registerListener(this, mAccSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}

	public void destroy() {
		pause();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			getAccelerometer(event);
		}

	}

	private void getAccelerometer(SensorEvent e) {
		float[] values = e.values;

		accX += values[0];
		accY += values[1];
		accZ += values[2];

		if (valueCount == 0) {
			minX = values[0];
			minY = values[1];
			minZ = values[2];

			maxX = values[0];
			maxY = values[1];
			maxZ = values[2];
		} else {
			if (minX > values[0]) {
				minX = values[0];
			}
			if (maxX < values[0]) {
				maxX = values[0];
			}
			if (minY > values[1]) {
				minY = values[1];
			}
			if (maxY < values[1]) {
				maxY = values[1];
			}
			if (minZ > values[2]) {
				minZ = values[2];
			}
			if (maxZ < values[2]) {
				maxZ = values[2];
			}
		}

		if (valueCount == 9) {
			accChange = true;

			accX -= (minX + maxX);
			accY -= (minY + maxY);
			accZ -= (minZ + maxZ);

			accXout = accX / 8;
			accYout = accY / 8;
			accZout = accZ / 8;

			accX = 0;
			accY = 0;
			accZ = 0;

			accChange = false;
			valueCount = 0;
		}

	}

	public double getAccelerationX() {
		if (accChange) {
			return 404;
		} else {
			return accX;
		}
	}

	public double getAccelerationY() {
		if (accChange) {
			return 404;
		} else {
			return accY;
		}
	}

	public double getAccelerationZ() {
		if (accChange) {
			return 404;
		} else {
			return accZ;
		}
	}

}
