package at.htl.smartbot;

import java.util.Observable;

import at.htl.geometrics.*;

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

	// Observable ?
	public void newValue(long time, CartesianVector acceleration) {
		this.setChanged();

		CartesianVector dv = new CartesianVector(acceleration.getX() * Utils.sqr(time - this.last_update_time), acceleration.getY()
				* Utils.sqr(time - this.last_update_time));
		current_Pos = current_Pos.addVector(dv);
		this.last_update_time = time;

		this.notifyObservers(current_Pos);
	}

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
