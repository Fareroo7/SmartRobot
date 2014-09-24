package at.htl.ECP;


/**
 * Represents a ECP Message. All possible parameters can be added and the mandatory parameters Start and Stop are added automatically.
 * @author Dominik Simon
 * @version 1.0
 */
public class ECP {
	
	
	/**
	 * Returns a ECPMessage as {@link String}.
	 * @param forward Directions flag (true = forward and false = backward).
	 * @param left Duty Circle for motors on the left side.
	 * @param right Duty Circle for motors on the right side.
	 * @return A ECPMessage as {@link String}.
	 */
	public static String getECMessage(boolean forward, int left, int right){
		return ECPParameter.START + (forward ? ECPParameter.DIRECTION_FORWARD : ECPParameter.DIRECTION_BACKWARD) + (char)left + (char)right + ECPParameter.END ;
	}
	
	
	
}
