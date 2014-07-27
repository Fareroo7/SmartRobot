package at.htl.smartbot;

import at.htl.geometrics.Point;

/**
 * Represents a ultrasonic source hardware and stores its position, distance and the time of the last update
 * @author Jakob Ecker
 * @author Dominik Simon
 *
 */
public class UltrasonicSource {

	private Point pos;
	private double distance;
	private double time_of_last_measure;

	/**
	 * Constructs a new UltrasonicSource with the position
	 * @param pos
	 */
	public UltrasonicSource(Point pos) {
		this.pos = pos;
	}

	/**
	 * Constructs a new empty UltrasonicSource
	 */
	public UltrasonicSource(){
		
	}
	
	/**
	 * Getter
	 * @return
	 */
	public Point getPos() {
		return pos;
	}

	/**
	 * Getter
	 * @return
	 */
	public double getPos_X() {
		return pos.getX();
	}

	/**
	 * Getter
	 * @return
	 */
	public double getPos_Y() {
		return pos.getY();
	}

	/**
	 * Getter
	 * @return
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Setter
	 * @return
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/**
	 * Getter
	 * @return
	 */
	public double getTime_of_last_measure() {
		return time_of_last_measure;
	}

	/**
	 * Setter
	 * @return
	 */
	public void setTime_of_last_measure(double time_of_last_measure) {
		this.time_of_last_measure = time_of_last_measure;
	}

}
