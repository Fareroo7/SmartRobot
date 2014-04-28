package at.htl.smartbot;

public class UltrasonicSource {

	private int POS_X;
	private int POS_Y;
	private int distance;
	private int time_of_last_measure;
	
	public int getPOS_X() {
		return POS_X;
	}
	public void setPOS_X(int pOS_X) {
		POS_X = pOS_X;
	}
	public int getPOS_Y() {
		return POS_Y;
	}
	public void setPOS_Y(int pOS_Y) {
		POS_Y = pOS_Y;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getTime_of_last_measure() {
		return time_of_last_measure;
	}
	public void setTime_of_last_measure(int time_of_last_measure) {
		this.time_of_last_measure = time_of_last_measure;
	}
	
	
	
}
