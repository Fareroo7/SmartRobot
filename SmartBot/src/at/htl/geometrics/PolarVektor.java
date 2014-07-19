package at.htl.geometrics;

public class PolarVektor {
	private double z;
	private double phi;
	
	public PolarVektor(double z, double phi){
		this.z = z;
		this.phi = phi;
	}
	
	public CartesianVector toCartesianVector(){
		double x = this.z * Math.cos(this.phi);
		double y = this.z * Math.sin(this.phi);
		return new CartesianVector(x, y);
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getPhi() {
		return phi;
	}

	public void setPhi(double phi) {
		this.phi = phi;
	}
	
	
	
}
