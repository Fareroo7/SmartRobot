package at.htl.smartbot;

public class UltrasonicSource {

	private final int POS_X;
	private final int POS_Y;
	private int distance;
	private int time_of_last_measure;
	
	public UltrasonicSource(int pos_x, int pos_y){
		POS_X=pos_x;
		POS_Y=pos_y;
	}
	
	public int getPOS_X() {
		return POS_X;
	}
	
	public int getPOS_Y() {
		return POS_Y;
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
