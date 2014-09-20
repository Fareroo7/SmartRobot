package at.htl.geometrics;

import at.htl.smartbot.Utils;


/**
 * 
 * This class represent a vector in the cartesian format (x and y value)
 * 
 * @author Jakob Ecker
 * @author Dominik Simon
 * @version 1.0
 */
public class CartesianVector {
	
	private double x;
	private double y;
	
	/**
	 * Constructs a new cartesian vector - object with x and y value
	 * @param x The x value of the cartesian vector
	 * @param y The y value of the cartesian vector
	 */
	public CartesianVector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructs a new empty cartesian vector - object
	 */
	public CartesianVector(){};
	
	/**
	 * Converts the cartesian form into a vector of polar form with magnitude z and angle &phi;
	 * @return The vector in polar form as {@link PolarVector}
	 */
	public PolarVector toPolarVector(){
		double z = Math.sqrt(Utils.sqr(this.x) + Utils.sqr(this.y));
		double phi = Math.atan2(this.y,this.x);
		
		return new PolarVector(z, phi);
	}

	/**
	 * Returns the x value of the cartesian vector.
	 * @return The x value of the cartesian vector
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x value of the cartesian vector
	 * @param x The x value of the cartesian vector
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Returns the y value of the cartesian vector.
	 * @return The y value of the cartesian vector
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y value of the cartesian vector
	 * @param x The y value of the cartesian vector
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Cutsom toString that returns a formatted cartesian vector
	 */
	@Override
	public String toString() {
		return "CartesianVector [x=" + x + ", y=" + y + "]";
	}
	
	
	
}
