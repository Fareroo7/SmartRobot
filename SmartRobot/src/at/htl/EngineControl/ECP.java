package at.htl.EngineControl;


/**
 * Defines methodes returning the correct {@link String}s for communication with Engine control arduino
 * @author Dominik Simon
 * @version 2.2
 */
public class ECP {
	
	/**
	 * Start Parameter of each Transmission
	 */
	public static final byte START = 0x53;
	
	/**
	 * End of the Transmission.
	 */
	public static final byte END = 0x54;

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
	
	/**
	 * Returns a ECPMessage as {@link String}.
	 * @param forward Directions flag (true = forward and false = backward).
	 * @param left Duty Circle for motors on the left side.
	 * @param right Duty Circle for motors on the right side.
	 * @return A ECPMessage as {@link String}.
	 * @deprecated with version 2.1
	 */
	public static String getECMessage(boolean forward, int left, int right){
		return START + (forward ? DIRECTION_FORWARD : DIRECTION_BACKWARD) + (char)left + (char)right + END + "";
	}
	
	/**
	 * <b>Only for user output. Use {@link getECP()} for communication with arduino.</b><br>
	 * Returns the ECP-Message as {@link String} for version 3.0.
	 * @param id The ID of the Task.
	 * @param directionCode The direction code from {@link ECPParameter}.
	 * @param leftDutyCycle Duty Cycle for left engines.
	 * @param rightDutyCycle Duty Cycle for right engines.
	 * @param duration Duration of the Task in milliseconds.
	 * @return ECP-Message as {@link String}.
	 */
	public static String getECPtoString(int id, byte directionCode, int leftDutyCycle, int rightDutyCycle, int duration){
		return START + (char)id + (char) directionCode + (char) leftDutyCycle + (char) rightDutyCycle + (char)duration + END + "";
	}
	
	/**
	 * Returns the Engine Control Protocol for communication with the arduino.
	 * @param id The ID of the Task.
	 * @param directionCode The direction code from {@link ECP}.
	 * @param leftDutyCycle Duty Cycle for left engines.
	 * @param rightDutyCycle Duty Cycle for right engines.
	 * @param duration Duration of the Task in milliseconds.
	 * @return Engine Control Protocol as {@link Byte}-Array.
	 */
	public static byte[] getECP(int id, byte directionCode, int leftDutyCycle, int rightDutyCycle, int duration){
		return new byte[] { START, (byte) id, directionCode, (byte) leftDutyCycle, (byte) rightDutyCycle, (byte)duration, END };		
	}
	
	/**
	 * Returns the Engine Control Protocol for communication with the arduino.
	 * @param task The {@link EngineTask} that will be send.
	 * @return The {@link EngineTask} with header and footer for the communication.
	 */
	public static byte[] getECP(EngineTask task){
		return new byte[] {START, task.getId(), task.getDirectionCode(), task.getDutyCircleLeft(), task.getDutyCircleRight(), task.getDuration(), END};
	}
	
}
