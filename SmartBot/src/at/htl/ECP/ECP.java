package at.htl.ECP;


/**
 * Represents a ECP Message. All possible parameters can be added and the mandatory parameters Start and Stop are added automatically.
 * @author Dominik Simon
 * @version 1.0
 */
public class ECP {
	
	

	public static String getECMessage(boolean forward, int left, int right){
		return ECPParameter.START + (forward ? ECPParameter.DIRECTION_FORWARD : ECPParameter.DIRECTION_BACKWARD) + (char)left + (char)right + ECPParameter.END ;
	}
	
	
	
}
