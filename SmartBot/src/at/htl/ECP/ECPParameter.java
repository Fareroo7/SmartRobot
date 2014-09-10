package at.htl.ECP;

/**
 * Defines the parameters of the Engine Control Protocol as constants.
 * @author Jakob Ecker
 *
 */
public class ECPParameter {
	
	/**
	 * Start Parameter of each Transmission
	 */
	public static final String START = "S";
	/**
	 * Set rotational speed of the left side to the following 4 digits (in RPM)
	 */
	public static final String ENGINES_LEFT = "L";
	/**
	 * Set rotational speed of the right side to the following 4 digits (in RPM)
	 */
	public static final String ENGINES_RIGTH = "R";
	/**
	 * Set the direction of the engines to forward
	 */
	public static final String DIRECTION_FORWARD = "F";
	/**
	 * Set the direction of the engines to backward
	 */
	public static final String DIRECTION_BACKWARD = "B";
	/**
	 * End of the Transmission.
	 */
	public static final String END = "T";
	
	
	
	
	/**
	 * Code for Acknowladge.
	 */
	public static final int C_ACK = 0x00;
	/**
	 * Error-Code for Transmission faild.
	 */
	public static final int E_TRANSMISSION = 0x01;
	
	/**
	 * Error-Code for Overcurrent.
	 */
	public static final int E_OVERCURRENT = 0x10;

}
