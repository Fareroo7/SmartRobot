package at.htl.smartbot;

import java.awt.Graphics;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.htl.geometrics.Point;

/**
 * Class of useful little methods.
 * 
 * @author Jakob Ecker
 * @author Dominik Simon
 * @version 1.0
 */

public class Utils {
	
	/**
	 * Defines the format of each string that represent a date in our project.
	 * The pattern is "dd.MM.yyyy HH:mm:ss"
	 */
	public static final String DATEPATTERN ="dd.MM.yyyy HH:mm:ss";

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
	 * Checks a String if it is a double (contains only digits and one point)
	 * 
	 * @param s
	 *            String that should be used as double
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

	/**
	 * Draws a coordinate system (x- and y-axis)
	 * @param width of the coordinate system
	 * @param height of the coordinate system
	 * @param origin the point where the two axes meet
	 * @param g the Graphics component used to draw the coordinate system
	 */
	public static void drawCoordinateSystem(int width, int height, Point origin, Graphics g) {
		int x = (int) (Math.round(origin.getX()));
		int y = (int) (Math.round(origin.getY()));
		g.drawLine(x, 0, x, height);
		g.drawLine(0, y, width, y);

	}

	/**
	 * Draws a coordinate system with a tick every step
	 * @param width of the coordinate system
	 * @param height of the coordinate system
	 * @param step the distance between the ticks
	 * @param origin the point where the two axes meet
	 * @param g the Graphics component used to draw the coordinate system
	 */
	public static void drawCoordinateSystem(int width, int height, int step, Point origin, Graphics g) {
		int x = (int) (Math.round(origin.getX()));
		int y = (int) (Math.round(origin.getY()));
		g.drawLine(x, 0, x, height);
		g.drawLine(0, y, width, y);
		
		for(int i = x; i<width; i+=step){
			g.drawLine(i, y-3, i, y+3);
		}
		for(int i=x; i>0;i-=step){
			g.drawLine(i, y-3, i, y+3);
		}
		for(int i=y;i<height;i+=step){
			g.drawLine(x-3, i, x+3, i);
		}
		for(int i=y;i>0;i-=step){
			g.drawLine(x-3, i, x+3, i);
		}
	}
	
	/**
	 * Converts a Date-Object to a String with the format of the DATEPATTERN
	 * @param toFormat
	 * @return
	 */
	public static String dateToFormattedString(Date toFormat){
		return new SimpleDateFormat(DATEPATTERN).format(toFormat);
	}
	
	/**
	 * Converts a String that represents a Date with the format of DATEPATTERN into a Date-Object
	 * @param date
	 * @return
	 */
	public static Date formattedStringToDate(String date){
		SimpleDateFormat formatter = new SimpleDateFormat(DATEPATTERN);
		try{
			return formatter.parse(date);
		}catch(ParseException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Converts radiant into degrees
	 * @param rad
	 * @return
	 */
	public static double radToDeg(double rad){
		return rad*180/Math.PI;
	}
	
	/**
	 * Converts degrees into radiant
	 * @param deg
	 * @return
	 */
	public static double degToRad(double deg){
		return deg*Math.PI/180;
	}
	
	/**
	 * If the parameter has only four digits (<=9999) returns true, else returns false
	 * @param chk
	 * @return
	 */
	public static boolean check4Digits(int chk){
		if(chk<10000){
			return true;
		}else{
			return false;
		}
	}

}
