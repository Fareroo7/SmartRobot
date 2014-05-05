package at.htl.smartbot;

public class Line {
	
	private static final int X=0;
	private static final int Y=1;
	
	private double[] point1;
	private double[] point2;
	
	private double distance;
	
	public Line(double[]  point1, double[] point2){
		this.point1=point1;
		this.point2=point2;
		this.distance=Math.sqrt(Math.pow(point2[X]-point1[X], 2)+Math.pow(point2[Y]-point1[Y], 2));
	}
	
	private void refreshDistance(){
		this.distance=Math.sqrt(Math.pow(point2[X]-point1[X], 2)+Math.pow(point2[Y]-point1[Y], 2));
	}

	public double[] getPoint1() {
		return point1;
	
	}

	public void setPoint1(double[] point1) {
		this.point1 = point1;
		refreshDistance();
	}

	public double[] getPoint2() {
		return point2;
	}

	public void setPoint2(double[] point2) {
		this.point2 = point2;
		refreshDistance();
	}

	public double getDistance() {
		return distance;
	}

}
