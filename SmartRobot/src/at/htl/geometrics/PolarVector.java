package at.htl.geometrics;

import at.htl.smartbot.Utils;

/**
 * Represents a vector in polar form (magnitude z and angle &phi;).
 * @author Jakob Ecker
 * @author Domink Simon
 * @version 1.0
 */
public class PolarVector {
	private double z;
	private double phi;
	
	/**
	 * Constructs a new polar vector with magnitude z and angle &phi;.
	 * @param z Magnitude z.
	 * @param phi Angle &phi;.
	 */
	public PolarVector(double z, double phi){
		this.z = z;
		this.phi = phi;
	}
	
	/**
	 * Constructs a new empty PolarVector - object.
	 */
	public PolarVector(){};
	
	
	/**
	 * Converts the polar form into a vector of cartesian form with magnitude z and angle &phi;.
	 * @return The vector in cartesian form as {@link CartesianVector}.
	 */
	public CartesianVector toCartesianVector(){
		double x = this.z * Math.cos(this.phi);
		double y = this.z * Math.sin(this.phi);
		return new CartesianVector(x, y);
	}

	/**
	 * Returns the magnitude z of the PolarVector.
	 * @return Magnitude z.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Sets the magnitude z of the PolarVector.
	 * @param z Magnitude z.
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Returns the angle &phi; of the PolarVector.
	 * @return Angle &phi;.
	 */
	public double getPhi() {
		return phi;
	}

	/**
	 * Sets the angle &phi; of the PolarVector.
	 * @param phi Angle &phi;.
	 */
	public void setPhi(double phi) {
		this.phi = phi;
	}
	
	/**
	 * Custom toString that returns a formatted PolarVector.
	 */
	@Override
	public String toString() {
		return "PolarVector [z="+z+", phi="+ Utils.radToDeg(phi)+"]";
	}
	
}
