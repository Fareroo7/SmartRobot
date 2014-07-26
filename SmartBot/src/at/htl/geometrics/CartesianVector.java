package at.htl.geometrics;

import at.htl.smartbot.Utils;


/**
 * 
 * This class represent a vector in the cartesian format (x and y value)
 * 
 * @author Jakob Ecker
 * @author Dominik Simon
 *
 */
public class CartesianVector {
	
	private double x;
	private double y;
	
	/**
	 * Constructs a new cartesian vector - object with x and y value
	 * @param x
	 * @param y
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
	 * @return The vector in polar form as PolarVector - Object
	 */
	public PolarVector toPolarVector(){
		double z = Math.sqrt(Utils.sqr(this.x) + Utils.sqr(this.y));
		double phi = Math.atan2(this.y,this.x);
		
		return new PolarVector(z, phi);
	}

	/**
	 * Getter
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * Setter
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Getter
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * Setter
	 * @param x
	 */
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "CartesianVector [x=" + x + ", y=" + y + "]";
	}
	
	
	
}
