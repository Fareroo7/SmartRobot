package at.htl.smartbot;

import java.awt.Graphics;

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
	 * 
	 * @param s
	 *            String that shoud be used as double
	 * @return True if parameter is a Double
	 */
	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void drawCoordinateSystem(int width, int height, Point origin, Graphics g) {
		int x = (int) (Math.round(origin.getX()));
		int y = (int) (Math.round(origin.getY()));
		g.drawLine(x, 0, x, height);
		g.drawLine(0, y, width, y);

	}

	public static void drawCoordinateSystem(int width, int height, int step, Point origin, Graphics g) {
		int x = (int) (Math.round(origin.getX()));
		int y = (int) (Math.round(origin.getY()));
		g.drawLine(x, 0, x, height);
		g.drawLine(0, y, width, y);

		for (int i = 0; (i * step) < width && (i * step) > 0; i++) {
			g.drawLine(i * step + x, y - 2, i * step + x, y + 2);
			g.drawLine(x - i * step, y - 2, x - i * step, y + 2);
		}
		for (int i = 0; (i * step) < height && (i * step) > 0; i++) {
			g.drawLine(x - 2, i * step + y, x + 2, i * step + y);
			g.drawLine(x - 2, y - i * step, x + 2, y - i * step);
		}
	}

}
