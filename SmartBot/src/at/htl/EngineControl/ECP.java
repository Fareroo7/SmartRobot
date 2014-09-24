package at.htl.EngineControl;


/**
 * Defines methodes returning the correct {@link String}s for communication with Engine control arduino
 * @author Dominik Simon
 * @version 2.1
 */
public class ECP {
	
	
	/**
	 * Returns a ECPMessage as {@link String}.
	 * @param forward Directions flag (true = forward and false = backward).
	 * @param left Duty Circle for motors on the left side.
	 * @param right Duty Circle for motors on the right side.
	 * @return A ECPMessage as {@link String}.
	 * @deprecated with version 2.0
	 */
	public static String getECMessage(boolean forward, int left, int right){
		return ECPParameter.START + (forward ? ECPParameter.DIRECTION_FORWARD : ECPParameter.DIRECTION_BACKWARD) + (char)left + (char)right + ECPParameter.END ;
	}
	
	/**
	 * 
	 * Returns the ECP-Message as {@link String} for version 2.1.
	 * @param directionCode The direction code from {@link ECPParameter}.
	 * @param leftDutyCycle Duty Cycle for left engines.
	 * @param rightDutyCycle Duty Cycle for right engines.
	 * @return ECP-Message as {@link String}.
	 */
	public static String getECMessage(byte directionCode, int leftDutyCycle, int rightDutyCycle){
		return ECPParameter.START+(char)directionCode + (char) leftDutyCycle + (char) rightDutyCycle + ECPParameter.END;
	}
	
}
