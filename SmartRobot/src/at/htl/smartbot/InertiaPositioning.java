package at.htl.smartbot;

import java.util.Observable;

import at.htl.geometrics.*;

/**
 * Api to track the position of the bot with time and acceleration - values. This class just works when starting-speed is zero.
 * @author Jakob Ecker
 *@version 1.0
 */
public class InertiaPositioning extends Observable {

	private Point start_Pos;
	private Point current_Pos;

	private long start_time;
	private long last_update_time;

	public InertiaPositioning(long start_time, Point start_position) {
		super();
		this.start_time = start_time;
		this.last_update_time = start_time;
		this.start_Pos = start_position;
		this.current_Pos = this.start_Pos;
	}

	/**
	 * Updates the current position by calculating the covered distance in the
	 * timespan to the last update-time from the acceleration.
	 * 
	 * @param time
	 *            absolut system time in ns. The diffence is calculated
	 *            internal.
	 * @param acceleration
	 *            the acceleration for the timespan in m per s<sup>2</sup>
	 */
	public void newValue(long time, CartesianVector acceleration) {
		this.setChanged();

		CartesianVector dv = new CartesianVector(acceleration.getX() * Utils.sqr((time - this.last_update_time) / 1E9), acceleration.getY()
				* Utils.sqr((time - this.last_update_time) / 1E9));
		current_Pos = current_Pos.addVector(dv);
		this.last_update_time = time;

		this.notifyObservers(current_Pos);
	}

	/**
	 * Resets all values to start-values
	 * @param time new start-time
	 * @param start_position position where the relative positioning starts.
	 */
	public void reset(long time, Point start_position) {
		this.start_Pos = start_position;
		this.current_Pos = start_position;
		this.start_time = time;
		this.last_update_time = time;
	}

	public Point getStart_Pos() {
		return start_Pos;
	}

	public Point getCurrent_Pos() {
		return current_Pos;
	}

	public long getStart_time() {
		return start_time;
	}

	public long getLast_update_time() {
		return last_update_time;
	}

	@Override
	public String toString() {
		return "InertiaPositioning [start position=" + start_Pos + ", start time=" + start_time + ", last position=" + current_Pos + ", last update time="
				+ last_update_time + "]";
	}

}
