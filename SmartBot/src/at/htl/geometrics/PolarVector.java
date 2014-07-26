package at.htl.geometrics;

import at.htl.smartbot.Utils;

/**
 * Represents a vector in polar form (magnitude z and angle &phi;)
 * @author Jakob Ecker
 * @author Domink Simon
 */
public class PolarVector {
	private double z;
	private double phi;
	
	/**
	 * Constructs a new polar vector with magnitude z and angle &phi;
	 * @param z
	 * @param phi
	 */
	public PolarVector(double z, double phi){
		this.z = z;
		this.phi = phi;
	}
	
	/**
	 * Constructs a new empty polar vector
	 */
	public PolarVector(){};
	
	public CartesianVector toCartesianVector(){
		double x = this.z * Math.cos(this.phi);
		double y = this.z * Math.sin(this.phi);
		return new CartesianVector(x, y);
	}

	/**
	 * Getter
	 * @return
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Setter
	 * @param z
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * Getter
	 * @return
	 */
	public double getPhi() {
		return phi;
	}

	/**
	 * Setter
	 * @param z
	 */
	public void setPhi(double phi) {
		this.phi = phi;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "PolarVector [z="+z+", phi="+ Utils.radToDeg(phi)+"]";
	}
	
	
	
	
}
