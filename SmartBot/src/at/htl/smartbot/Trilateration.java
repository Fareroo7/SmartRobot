package at.htl.smartbot;

import java.util.ArrayList;

public class Trilateration {

	private static final int X = 0;
	private static final int Y = 1;

	public static Point trilaterate(Point pos_S1, Point pos_S2, Point pos_S3,
			double distance_S1, double distance_S2, double distance_S3) {

		double[] result = new double[2];

		ArrayList<Point> points_of_intersection = new ArrayList<Point>();
		ArrayList<Line> distances_btw_points = new ArrayList<Line>();

		Line temp_points_of_intersection = points_of_intersection_crircle(
				pos_S1, pos_S2, distance_S1, distance_S2);
		points_of_intersection.add(temp_points_of_intersection.getPoint1());
		points_of_intersection.add(temp_points_of_intersection.getPoint2());
		System.out.println(temp_points_of_intersection.toString());

		temp_points_of_intersection = points_of_intersection_crircle(pos_S2,
				pos_S3, distance_S2, distance_S3);
		points_of_intersection.add(temp_points_of_intersection.getPoint1());
		points_of_intersection.add(temp_points_of_intersection.getPoint2());
		System.out.println(temp_points_of_intersection.toString());
		
		temp_points_of_intersection = points_of_intersection_crircle(pos_S1,
				pos_S3, distance_S1, distance_S3);
		points_of_intersection.add(temp_points_of_intersection.getPoint1());
		points_of_intersection.add(temp_points_of_intersection.getPoint2());
		System.out.println(temp_points_of_intersection.toString());

		/*
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
	public static Line points_of_intersection_crircle(Point m1, Point m2, double r1,
			double r2) {

		Line distance = new Line(m1, m2);
		Line result = new Line();
		double temp_m2_x;
		double temp_m2_y;

		// Test output
//		System.out.println();
//		System.out.println(distance);
//		System.out.println(r1 + r2);

		if (r1 + r2 > distance.getDistance()) {

			// Mit freundlicher unterstuetzung von Mag. Harald Tranacher
			temp_m2_x=(m2.getX() - m1.getX());
			temp_m2_y=(m2.getY() - m1.getY());

			double a = (Utils.sqr(r1) - Utils.sqr(r2) + Utils.sqr(temp_m2_x) + Utils.sqr(temp_m2_y))
					/ (2 * temp_m2_x);
			double b = -(2 * temp_m2_y) / (2 * temp_m2_x);
			double p = (2 * a * b) / (Utils.sqr(b) + 1);
			double q = (Utils.sqr(a) - Utils.sqr(r1)) / (Utils.sqr(b) + 1);

			// Test output
			/*
			 * System.out.println(a); System.out.println(b);
			 * System.out.println(p); System.out.println(q);
			 */

			double y1 = -(p / 2) + Math.sqrt((Utils.sqr(p / 2) - q));
			double y2 = -(p / 2) - Math.sqrt((Utils.sqr(p / 2) - q));

			double x1 = a + b * y1;
			double x2 = a + b * y2;

			Point intersection1 = new Point(x1 + m1.getX(), y1 + m1.getY());
			Point intersection2 = new Point(x2 + m1.getX(), y2 + m1.getY());
			result = new Line(intersection1, intersection2);
		} else {
//			System.out.println("Schei***");
			result = null;
		}

		return result;
	}
	
}
