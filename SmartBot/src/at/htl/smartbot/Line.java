package at.htl.smartbot;

public class Line {
	
	@Override
	public String toString() {
		return "Line ["+point1.toString()+", " + point2.toString() + ", Distance="
				+ Utils.round(distance) + "]";
	}

	private Point point1;
	private Point point2;
	
	private double distance;
	
	public Line(Point  point1, Point point2){
		this.point1=point1;
		this.point2=point2;
		this.distance=Math.sqrt(Math.pow(point2.getX()-point1.getX(), 2)+Math.pow(point2.getY()-point1.getY(), 2));
	}
	public Line(){
		
	}
	
	private void refreshDistance(){
		this.distance=Math.sqrt(Math.pow(point2.getX()-point1.getX(), 2)+Math.pow(point2.getY()-point1.getY(), 2));
	}

	public Point getPoint1() {
		return point1;
	
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
		refreshDistance();
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
		refreshDistance();
	}

	public double getDistance() {
		return distance;
	}

}
