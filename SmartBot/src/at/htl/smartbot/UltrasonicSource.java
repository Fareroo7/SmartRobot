package at.htl.smartbot;

/**
 * Class that stores Position of and distance to an ultrasonic nois source. Additionally the time of the last distance measurement is stored
 * @author Jakob Ecker , Dominik Simon
 *
 */
public class UltrasonicSource {

	private final Point pos;
	private double distance;
	private double time_of_last_measure;

	public UltrasonicSource(Point pos) {
		this.pos = pos;
	}

	public Point getPos() {
		return pos;
	}

	public double getPos_X() {
		return pos.getX();
	}

	public double getPos_Y() {
		return pos.getY();
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getTime_of_last_measure() {
		return time_of_last_measure;
	}

	public void setTime_of_last_measure(double time_of_last_measure) {
		this.time_of_last_measure = time_of_last_measure;
	}

}
