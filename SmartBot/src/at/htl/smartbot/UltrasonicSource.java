package at.htl.smartbot;

public class UltrasonicSource {

	private final double[] pos = new double[2];
	private double distance;
	private double time_of_last_measure;
	
	public UltrasonicSource(int pos_x, int pos_y){
		pos[0]=pos_x;
		pos[1]=pos_y;
	}
	
	
	public double[] getPos() {
		return pos;
	}
	
	public double getPos_X(){
		return pos[0];
	}
	
	public double getPos_Y(){
		return pos[1];
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
