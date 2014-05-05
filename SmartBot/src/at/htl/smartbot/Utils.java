package at.htl.smartbot;

import java.util.ArrayList;

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
	 * Removes points from a list that have same coordinates 
	 * @param points List to remove the redundant points
	 * @return ArrayList of Points without redundant points
	 */
	public static ArrayList<Point> eliminateRedundance(ArrayList<Point> points) {

		ArrayList<Point> result = new ArrayList<Point>();
		for(Point i:points){
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

	public static Point getCentroidOfSmallestTriangle(ArrayList<Point> points) {

		ArrayList<Line> lines = new ArrayList<Line>();

		Line min1 = new Line();
		Line min2 = new Line();
		Line min3 = new Line();

		Point half_min1 = new Point();
		Point half_min2 = new Point();
		Point half_min3 = new Point();

		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i));
			for (int z = i + 1; z < points.size(); z++) {
				lines.add(new Line(points.get(i), points.get(z)));
			}
		}

		for (int z = 0; z < 3; z++) {
			double tmp_min = Double.MAX_VALUE;
			int index_of_min = 0;
			for (int i = 0; i < lines.size(); i++) {
				if (tmp_min > lines.get(i).getDistance()) {
					tmp_min = lines.get(i).getDistance();
					index_of_min = i;
				}
			}
			switch (z) {
			case 0:
				min1 = lines.get(index_of_min);
				break;
			case 1:
				min2 = lines.get(index_of_min);
				break;
			case 2:
				min3 = lines.get(index_of_min);
				break;
			}
			lines.remove(index_of_min);
		}

		System.out.println("1: " + min1 + " 2: " + min2 + " 3: " + min3);

		return null;
	}
}
