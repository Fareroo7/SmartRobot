package at.htl.ECP;


/**
 * Represents a ECP Message. All possible parameters can be added and the mandatory parameters Start and Stop are added automatically.
 * @author Jakob Ecker
 *
 */
public class ECP {
	
	

	public static String getECMessage(boolean forward, int left, int right){
		return ECPParameter.START + (forward ? ECPParameter.DIRECTION_FORWARD : ECPParameter.DIRECTION_BACKWARD) + (char)left + (char)right + ECPParameter.END ;
	}
	
	
	
}
