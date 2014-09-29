package at.htl.smartbot;

import at.htl.geometrics.Circle;
import at.htl.geometrics.Point;

/**
 * Represents a ultrasonic source hardware and stores its position, distance and
 * the time of the last update
 * 
 * @author Jakob Ecker
 * @author Dominik Simon
 * 
 */
public class UltrasonicSource {

	private Circle c;
	private double time_of_last_measure;

	/**
	 * Constructs a new UltrasonicSource with the position
	 * 
	 * @param pos
	 */
	public UltrasonicSource(Point pos) {
		this.c.setCentre(pos);
	}

	/**
	 * Constructs a new empty UltrasonicSource
	 */
	public UltrasonicSource() {

	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public Point getPos() {
		return c.getCentre();
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public double getPosX() {
		return c.getCentre().getX();
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public double getPosY() {
		return c.getCentre().getY();
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public double getDistance() {
		return c.getRadius();
	}

	/**
	 * Setter
	 * 
	 * @return
	 */
	public void setDistance(double distance) {
		this.c.setRadius(distance);
	}

	/**
	 * Calculates the distance in m to the Ultrasonic Source using the acoustic velocity calculated by {@link EnvironmentalParameter}
	 * @param duration : the time in ms the acoustic signal need to reach the robot.
	 */
	public void updateDistance(double duration) {
		this.c.setRadius(EnvironmentalParameter.getAcousticVelocity() * (duration/1000));
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public double getLastUpdate() {
		return time_of_last_measure;
	}

	/**
	 * Setter
	 * 
	 * @return
	 */
	public void setLastUpdate(double measureTime) {
		this.time_of_last_measure = measureTime;
	}

}
