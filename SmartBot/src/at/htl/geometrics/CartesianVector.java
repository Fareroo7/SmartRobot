package at.htl.geometrics;

import at.htl.smartbot.Utils;

public class CartesianVector {
	
	private double x;
	private double y;
	
	public CartesianVector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public PolarVektor toPolarVektor(){
		double z = Math.sqrt(Utils.sqr(this.x) + Utils.sqr(this.y));
		double phi = Math.tan(this.y/this.x);
		return new PolarVektor(z, phi);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
