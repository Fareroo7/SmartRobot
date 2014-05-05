package at.htl.smartbot;

import java.util.ArrayList;
import java.util.Arrays;

public class Trilateration {

	private static final int X = 0;
	private static final int Y = 1;

	public static double[] trilateration(Point pos_S1, Point pos_S2,
			Point pos_S3, double distance_S1, double distance_S2,
			double distance_S3) {

		double[] result = new double[2];

		ArrayList<Point> points_of_intersection = new ArrayList<Point>();
		ArrayList<Line> distances_btw_points = new ArrayList<Line>();

		Line temp_points_of_intersection = points_of_intersection_crircle(
				pos_S1, pos_S2, distance_S1, distance_S2);
		points_of_intersection.add(temp_points_of_intersection.getPoint1());
		points_of_intersection.add(temp_points_of_intersection.getPoint2());
		System.out.println(temp_points_of_intersection.toString());

		Line temp_points_of_intersection2 = points_of_intersection_crircle(
				pos_S2, pos_S3, distance_S2, distance_S3);
		points_of_intersection.add(temp_points_of_intersection2.getPoint1());
		points_of_intersection.add(temp_points_of_intersection2.getPoint2());
		System.out.println(temp_points_of_intersection.toString());

		/*
		 * temp_points_of_intersection = points_of_intersection_crircle(pos_S3,
		 * pos_S1, distance_S3, distance_S1); toAdd[X] =
		 * temp_points_of_intersection[0][X]; toAdd[Y] =
		 * temp_points_of_intersection[0][Y]; points_of_intersection.add(toAdd);
		 * toAdd[X] = temp_points_of_intersection[1][X]; toAdd[Y] =
		 * temp_points_of_intersection[1][Y]; points_of_intersection.add(toAdd);
		 * 
		 * for (int i = 0; i < points_of_intersection.size(); i++) {
		 * System.out.println(Arrays.toString(points_of_intersection.get(i)));
		 * for (int z = 0; z < points_of_intersection.size(); z++) { if
		 * (points_of_intersection.get(i).equals( points_of_intersection.get(z))
		 * && i != z) { points_of_intersection.remove(z); i--; z--; } } }
		 * 
		 * /* for (int i = 0; i < points_of_intersection.size(); i++) { int
		 * count = 0; double current_x1 = points_of_intersection.get(i)[X];
		 * double current_y1 = points_of_intersection.get(i)[Y]; for (int z =
		 * i+1; z < points_of_intersection.size(); z++) { double current_x2 =
		 * points_of_intersection.get(z)[X]; double current_y2 =
		 * points_of_intersection.get(z)[Y]; distances_btw_points.get(i)[count]
		 * = Math .sqrt(sqr(current_x2 - current_x1) + sqr(current_y2 -
		 * current_y1)); count++; } }
		 * 
		 * for(int i=0;i<distances_btw_points.size();i++){
		 * 
		 * }
		 */

		return null;
	}

	/**
	 * Methode berechnet die Schnittpunkte 2er Kreise
	 * 
	 * @param m1
	 *            Mittelpunkt Kreis 1 (0:X, 1:Y)
	 * @param m2
	 *            Mittelpunkt Kreis 2 (0:X, 1:Y)
	 * @param r1
	 *            Radius Kreis 1
	 * @param r2
	 *            Radius Kreis 2
	 * @return 2d array mit Schnittpunkt 1 auf [0] und Schittpunkt 2 auf [1]
	 *         (0:X, 1:Y)
	 */
	public static Line points_of_intersection_crircle(Point m1, Point m2,
			double r1, double r2) {

		Line distance = new Line(m1, m2);
		Line result = new Line();

		// Test output
		/*
		 * System.out.println(distance); System.out.println(r1+r2);
		 */

		if (r1 + r2 > distance.getDistance()) {

			// Mit freundlicher unterstuetzung von Mag. Harald Tranacher
			m2.setX(m2.getX() - m1.getX());
			m2.setY(m2.getY() - m1.getY());

			double a = (sqr(r1) - sqr(r2) + sqr(m2.getX()) + sqr(m2.getY()))
					/ (2 * m2.getX());
			double b = -(2 * m2.getY()) / (2 * m2.getX());
			double p = (2 * a * b) / (sqr(b) + 1);
			double q = (sqr(a) - sqr(r1)) / (sqr(b) + 1);

			// Test output
			/*
			 * System.out.println(a); System.out.println(b);
			 * System.out.println(p); System.out.println(q);
			 */

			double y1 = -(p / 2) + Math.sqrt((sqr(p / 2) - q));
			double y2 = -(p / 2) - Math.sqrt((sqr(p / 2) - q));

			double x1 = a + b * y1;
			double x2 = a + b * y2;

			Point intersection1 = new Point(x1 + m1.getX(), y1 + m1.getY());
			Point intersection2 = new Point(x2 + m1.getX(), y1 + m1.getY());
			result = new Line(intersection1, intersection2);
		} else {
			result = null;
		}

		return result;
	}

	/**
	 * Squares the base
	 * 
	 * @param base
	 * @return base to the power of 2
	 */
	public static double sqr(double base) {
		return Math.pow(base, 2);
	}
}
