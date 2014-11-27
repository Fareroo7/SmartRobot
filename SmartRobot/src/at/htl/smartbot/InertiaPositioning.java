package at.htl.smartbot;

import at.htl.geometrics.*;

public class InertiaPositioning {

	boolean start = true;

	private Point start_Pos;
	private Point current_Pos;

	private long start_time;
	private long last_update_time;

	private double start_speed;

	public InertiaPositioning(long start_time, Point start_position, double start_speed) {
		super();
		this.start_time = start_time;
		this.start_Pos = start_position;
		this.current_Pos = this.start_Pos;
		this.start_speed = start_speed;
	}

	
	//do not use, speed has a direction to - kan bock mea heit :)
	public void newValue(long time, CartesianVector acceleration) {
		double x,y;
		if (start) {
			x = acceleration.getX() * Utils.sqr(time - this.last_update_time) + start_speed*(time-this.start_time);
			y = acceleration.getY() * Utils.sqr(time - this.last_update_time) + start_speed*(time-this.start_time);
		} else {
			x = acceleration.getX() * Utils.sqr(time - this.last_update_time);
			y = acceleration.getY() * Utils.sqr(time - this.last_update_time);
		}
	}
}
