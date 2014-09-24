package at.htl.EngineControl;

/**
 * Defines the parameters of the Engine Control Protocol as constants.
 * 
 * @author Dominik Simon
 * @version 2.1
 */
public class ECPParameter {

	/**
	 * Start Parameter of each Transmission
	 */
	public static final String START = "S";

	/**
	 * Set the direction of the engines to forward
	 */
	public static final byte DIRECTION_FORWARD = 0x11;

	/**
	 * Set the direction of the engines to backward
	 */
	public static final byte DIRECTION_BACKWARD = 0x22;

	/**
	 * Set the direction of the left engine to forward and the right engine to
	 * backward.
	 */
	public static final byte DIRECTION_TURN_CLOCKWISE = 0X12;

	/**
	 * Set the direction of the left engine to backward and the right engine to
	 * forward.
	 */
	public static final byte DIRECTION_TURN_ANTICLOCKWISE = 0x21;

	/**
	 * End of the Transmission.
	 */
	public static final String END = "T";

	/**
	 * Code for Acknowladge.
	 */
	public static final byte C_ACK = 0x01;

	/**
	 * Error-Code for Transmission faild.
	 */
	public static final byte E_TRANSMISSION = 0x02;

	/**
	 * Error-Code for Overcurrent.
	 */
	public static final byte E_OVERCURRENT = 0x10;
	
	/**
	 * Error-Code for Bumper active in front left
	 */
	public static final byte E_BUMPER_FRONT_LEFT = 0x24;
	
	/**
	 * Error-Code for Bumper active in front center
	 */
	public static final byte E_BUMPER_FRONT_CENTER = 0x28;
	
	/**
	 * Error-Code for Bumper active in front right
	 */
	public static final byte E_BUMPER_FRONT_RIGHT = 0x2C;
	
	/**
	 * Error-Code for Bumper active in back left
	 */
	public static final byte E_BUMPER_BACK_LEFT = 0x21;
	
	/**
	 * Error-Code for Bumper active in back center
	 */
	public static final byte E_BUMPER_BACK_CENTER = 0x22;
	
	/**
	 * Error-Code for Bumper active in back right
	 */
	public static final byte E_BUMPER_BACK_RIGHT = 0x23;

}
