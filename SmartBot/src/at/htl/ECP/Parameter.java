package at.htl.ECP;

/**
 * Defines the parameters of the Engine Control Protocol as constants.
 * @author Jakob Ecker
 *
 */
public class Parameter {
	
	/**
	 * Start Parameter of each Transmission
	 */
	public static final String START = "S";
	/**
	 * Set rotational speed of both sides to the following 4 digits (in RPM)
	 */
	public static final String ENGINES = "B";
	/**
	 * Set rotational speed of the left side to the following 4 digits (in RPM)
	 */
	public static final String ENGINES_LEFT = "L";
	/**
	 * Set rotational speed of the right side to the following 4 digits (in RPM)
	 */
	public static final String ENGINES_RIGTH = "R";
	/**
	 * Increases the rotational speed of both sides by the following 4 digits (in RPM)
	 */
	public static final String INCREASE_RPM = "P";
	/**
	 * Decreases the rotational speed of both sides by the following 4 digits (in RPM)
	 */
	public static final String DECREASE_RPM = "M";
	/**
	 * Set the direction of the engines to forward
	 */
	public static final String DIRECTION_FORWARD = "D+";
	/**
	 * Set the direction of the engines to backward
	 */
	public static final String DIRECTION_BACKWARD = "D-";
	/**
	 * Shows an error. Next 2 digits are an Error-Code.
	 */
	public static final String ERROR = "E";
	/**
	 * Confirms the receiving of an message.
	 */
	public static final String ACKNOWLEDGE = "A";
	/**
	 * End of the Transmission.
	 */
	public static final String END = "T";

}
