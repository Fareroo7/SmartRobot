package at.htl.smartbot;

import java.util.ArrayList;

/**
 * Stores coordinates of a single Point that provides custom toString and equals
 * 
 * @author Jakob Ecker & Dominik Simon
 * 
 */
public class Point {
	private double pos_x;
	private double pos_y;

	public Point(double x, double y) {
		pos_x = x;
		pos_y = y;
	}

	public Point() {

	}

	public double getX() {
		return pos_x;
	}

	public void setX(double pos_x) {
		this.pos_x = pos_x;
	}

	public double getY() {
		return pos_y;
	}

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

	public double getDistanceTo(Point p) {

		double deltaX = this.pos_x - p.getX();
		double deltaY = this.pos_y - p.getY();

//		if (deltaX != 0.0 && deltaY != 0.0) {
//			return Math.sqrt(Utils.sqr(deltaX)+Utils.sqr(deltaY));
//		}
		
//		if(!this.equals(p)){
//			return Math.sqrt(Utils.sqr(deltaX)+Utils.sqr(deltaY));
////		}
//		
//		return 0.0;
		
		return Math.sqrt(Utils.sqr(deltaX)+Utils.sqr(deltaY));
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

}
