package at.htl.ECP;


/**
 * Represents a ECP Message. All possible parameters can be added and the mandatory parameters Start and Stop are added automatically.
 * @author Jakob Ecker
 *
 */
public class ECPMessage {

	private StringBuffer message = new StringBuffer();

	public static String getECString(boolean forward, int left, int right){
		return ECPParameter.START + (forward ? ECPParameter.DIRECTION_FORWARD : ECPParameter.DIRECTION_BACKWARD) + (char)left + (char)right + ECPParameter.END ;
	}
	
	
	
}
