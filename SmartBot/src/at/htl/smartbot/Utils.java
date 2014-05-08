package at.htl.smartbot;

/**
 * Class of useful little methods.
 * 
 * @author Jakob Ecker & Dominik Simon
 * 
 */

public class Utils {

	/**
	 * Rounds to two decimals.
	 * 
	 * @param toRound
	 *            The value which will be rounded.
	 * @return Rounded value.
	 */
	public static double round(double toRound) {
		return Math.round(toRound * 100.0) / 100.0;
	}

	/**
	 * Squares the Parameter.
	 * 
	 * @param toSquare
	 *            Value which will be squared.
	 * @return Squared value.
	 */
	public static double sqr(double toSquare) {
		return Math.pow(toSquare, 2);
	}

	/**
	 * Checks a String if it is a double (contains only digits and a point)
	 * PS: keine Ahnung was falsch ist
	 * @param s String that shoud be used as double
	 * @return True if parameter is a Double
	 */
	public static boolean isDouble(String s) {
		try{
			Double.parseDouble(s);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}

}
