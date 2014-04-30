package at.htl.smartbot;

import java.util.ArrayList;
import java.util.Arrays;

public class Triangulation {

	private static final int X = 0;
	private static final int Y = 1;

	public static void main(String[] args) {

		double[] m1 = { 1.0, 1.0 };
		double[] m2 = { 3.0, 1.0 };
		double[][] result = points_of_intersection_crircle(m1, m2, 1.0, 0.5);
		if (result != null) {
			System.out.println(Arrays.toString(result[0]));
			System.out.println(Arrays.toString(result[1]));
		} else
			System.out.println("Fuck");
	}

	public static double[] trilateration(double[] pos_S1, double[] pos_S2,
			double[] pos_S3, double distance_S1, double distance_S2,
			double distance_S3) {

		double[] result = new double[2];
		ArrayList<double[]> points_of_intersection = new ArrayList<double[]>();

		double[][] temp_points_of_intersection = points_of_intersection_crircle(pos_S1, pos_S2,
				distance_S1, distance_S2);
		double[] toAdd = { temp_points_of_intersection[0][X],
				temp_points_of_intersection[0][Y] };
		points_of_intersection.add(toAdd);
		toAdd[X] = temp_points_of_intersection[1][X];
		toAdd[Y] = temp_points_of_intersection[1][Y];
		points_of_intersection.add(toAdd);

		temp_points_of_intersection = points_of_intersection_crircle(pos_S2, pos_S3, distance_S2,
				distance_S3);
		toAdd[X] = temp_points_of_intersection[0][X];
		toAdd[Y] = temp_points_of_intersection[0][Y];
		points_of_intersection.add(toAdd);
		toAdd[X] = temp_points_of_intersection[1][X];
		toAdd[Y] = temp_points_of_intersection[1][Y];
		points_of_intersection.add(toAdd);

		temp_points_of_intersection = points_of_intersection_crircle(pos_S3, pos_S1, distance_S3,
				distance_S1);
		toAdd[X] = temp_points_of_intersection[0][X];
		toAdd[Y] = temp_points_of_intersection[0][Y];
		points_of_intersection.add(toAdd);
		toAdd[X] = temp_points_of_intersection[1][X];
		toAdd[Y] = temp_points_of_intersection[1][Y];
		points_of_intersection.add(toAdd);
		
		

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
	public static double[][] points_of_intersection_crircle(double[] m1, double[] m2, double r1, double r2) {

		double[][] result;
		double distance = Math.sqrt(sqr(m2[X] - m1[X]) + sqr(m2[Y] - m1[Y]));

		// Test output
		/*
		 * System.out.println(distance); System.out.println(r1+r2);
		 */

		if (r1 + r2 > distance) {

			// Mit freundlicher unterstuetzung von Mag. Harald Tranacher
			m2[X] = m2[X] - m1[X];
			m2[Y] = m2[Y] - m1[Y];

			double a = (sqr(r1) - sqr(r2) + sqr(m2[X]) + sqr(m2[Y]))
					/ (2 * m2[X]);
			double b = -(2 * m2[Y]) / (2 * m2[X]);
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

			result = new double[2][2];
			result[0][X] = x1 + m1[X];
			result[0][Y] = y1 + m1[Y];
			result[1][X] = x2 + m1[X];
			result[1][Y] = y2 + m1[Y];
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
