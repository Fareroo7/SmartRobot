package at.htl.geometrics;

import java.util.ArrayList;

import at.htl.smartbot.Utils;

/**
 * Represents a point with x and y coordinate
 * 
 * @author Jakob Ecker
 * @author Dominik Simon
 * 
 */
public class Point {
	private double pos_x;
	private double pos_y;

	/**
	 * Constructs a new Point-Object with x and y coordinate
	 * 
	 * @param x
	 * @param y
	 */
	public Point(double x, double y) {
		pos_x = x;
		pos_y = y;
	}

	/**
	 * Constructs a new empty Point-Object
	 */
	public Point() {
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public double getX() {
		return pos_x;
	}

	/**
	 * Setter
	 * 
	 * @param pos_y
	 */
	public void setX(double pos_x) {
		this.pos_x = pos_x;
	}

	/**
	 * Getter
	 * 
	 * @return
	 */
	public double getY() {
		return pos_y;
	}

	/**
	 * Setter
	 * 
	 * @param pos_y
	 */
	public void setY(double pos_y) {
		this.pos_y = pos_y;
	}

	@Override
	public String toString() {
		return "Point (" + Utils.round(pos_x) + "|" + Utils.round(pos_y) + ")";
	}

	/**
	 * Returns true if x and y coordinates are equal
	 * 
	 * @param p
	 *            second point to compare
	 * @return true if equal else false
	 */
	public boolean equals(Point p) {
		return this.pos_x == p.getX() && this.pos_y == p.getY();
	}

	/**
	 * Calculates the distance to another Point
	 * 
	 * @param p
	 * @return Distance to Point p
	 */
	public double getDistanceTo(Point p) {
		return Math.sqrt(Utils.sqr(this.getDeltaX(p)) + Utils.sqr(this.getDeltaY(p)));
	}

	/**
	 * Removes points from a list that have same coordinates
	 * 
	 * @param points
	 *            List to remove the redundant points
	 * @return ArrayList of Points without redundant points
	 */
	public static ArrayList<Point> eliminateRedundance(ArrayList<Point> points) {

		// "Entkoppeln" - da sonst nur die Speicheradresse und nicht der Inhalt
		// kopiert wird;
		ArrayList<Point> result = new ArrayList<Point>();
		for (Point i : points) {
			result.add(i);
		}

		for (int i = 0; i < result.size(); i++) {

			for (int z = i + 1; z < result.size(); z++) {
				if (result.get(i).equals(result.get(z))) {
					result.remove(z);
					z--;
				}
			}
		}

		return result;
	}

	/**
	 * Checks a list of points for redundant points. If there is a point that is
	 * more than 2 times in the list only this point is returned, else a new
	 * list without redundancy is returned.
	 * 
	 * @deprecated
	 * @param points
	 * @return
	 */
	public static ArrayList<Point> checkOneIntersectionPoint(ArrayList<Point> points) {

		// "Entkoppeln" - da sonst nur die Speicheradresse und nicht der Inhalt
		// kopiert wird;
		ArrayList<Point> result = new ArrayList<Point>();
		for (Point i : points) {
			result.add(i);
		}

		for (int i = 0; i < result.size(); i++) {

			// <Test>
			int redundanceCount = 0;
			// </Test>

			for (int z = i + 1; z < result.size(); z++) {
				if (result.get(i).equals(result.get(z))) {
					result.remove(z);
					redundanceCount++;
					z--;
				}
			}
			// <Test>
			if (redundanceCount > 2) {
				result = new ArrayList<Point>();
				result.add(points.get(i));
				return result;
			}
			// </Test>
		}

		return result;
	}

	/**
	 * Calculates the distance in the x-axis to another Point
	 * 
	 * @param p
	 * @return Distance in x-axis to Point p
	 */
	public double getDeltaX(Point p) {
		return p.getX() - this.pos_x;
	}

	/**
	 * Calculates the distance in the y-axis to another Point
	 * 
	 * @param p
	 * @return Distance in y-axis to Point p
	 */
	public double getDeltaY(Point p) {
		return p.getY() - this.pos_y;
	}

}
