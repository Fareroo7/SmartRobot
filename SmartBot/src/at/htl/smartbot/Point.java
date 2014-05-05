package at.htl.smartbot;

public class Point {
	private double pos_x;
	private double pos_y;
	
	public Point(double x, double y){
		pos_x=x;
		pos_y=y;
	}
	public Point(){
		
	}
	
	public double getX() {
		return pos_x;
	}
	public void setX(double pos_x) {
		this.pos_x = pos_x;
	}
	public double getY() {
		return pos_y;
	}
	public void setY(double pos_y) {
		this.pos_y = pos_y;
	}
	@Override
	public String toString() {
		return "Point ("+Utils.round(pos_x)+"|"+Utils.round(pos_y)+")";
	}
	


}
