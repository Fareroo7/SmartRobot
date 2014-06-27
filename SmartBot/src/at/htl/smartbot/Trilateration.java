package at.htl.smartbot;

import java.util.ArrayList;

import at.htl.geometrics.Line;
import at.htl.geometrics.Point;
import at.htl.geometrics.Triangle;

/**
 * Provides methods used for trilateration.
 * 
 * @author Jakob
 * 
 */
public class Trilateration {

	/**
	 * Calculates the position
	 * 
	 * @param pos_S1
	 * @param pos_S2
	 * @param pos_S3
	 * @param distance_S1
	 * @param distance_S2
	 * @param distance_S3
	 * @return
	 */
	public static Point trilaterate(Point pos_S1, Point pos_S2, Point pos_S3, double distance_S1, double distance_S2, double distance_S3) {

		Triangle triangle;
		boolean onePoint = false;

		ArrayList<Point> points_of_intersection = new ArrayList<Point>();

		Line temp_points_of_intersection = getPointsOfIntersectionCrircle(pos_S1, pos_S2, distance_S1, distance_S2);
		points_of_intersection.add(temp_points_of_intersection.getPoint1());
		points_of_intersection.add(temp_points_of_intersection.getPoint2());

		temp_points_of_intersection = getPointsOfIntersectionCrircle(pos_S2, pos_S3, distance_S2, distance_S3);
		points_of_intersection.add(temp_points_of_intersection.getPoint1());
		points_of_intersection.add(temp_points_of_intersection.getPoint2());

		temp_points_of_intersection = getPointsOfIntersectionCrircle(pos_S1, pos_S3, distance_S1, distance_S3);
		points_of_intersection.add(temp_points_of_intersection.getPoint1());
		points_of_intersection.add(temp_points_of_intersection.getPoint2());

		points_of_intersection = Point.checkOneIntersectionPoint(points_of_intersection);
		// points_of_intersection =
		// Point.eliminateRedundance(points_of_intersection);

		// for(Point i:points_of_intersection){
		// System.out.println(i);
		// }

		if (points_of_intersection.size() == 1) {
			return points_of_intersection.get(0);
		}
		if (points_of_intersection.size() == 0) {
			System.out.println("Something goes wrong!");
			return null;
		}

		// System.out.println("--- trilaterate  points of intersection");
		// for (Point i : points_of_intersection) {
		// System.out.println(i);
		// }

		triangle = Triangle.getSmallestTriangel(points_of_intersection);
		// triangle = Triangle.getSmallestTriangelArea(points_of_intersection);
		// Error when Points
		// System.out.println(triangle);
		return Triangle.getCentroidOfTriangle(triangle);
	}

	public Point trilaterationV2(Point m1, Point m2, Point m3, double r1, double r2, double r3) {

		Line a = getPointsOfIntersectionCrircle(m1, m2, r1, r2);
		Line b = getPointsOfIntersectionCrircle(m2, m3, r2, r3);
		Line c = getPointsOfIntersectionCrircle(m3, m1, r3, r1);

		Point pA = null;
		Point pB = null;
		Point pC = null;

		if (a.getPoint1().equals(a.getPoint2())) {
			pA = a.getPoint1();
		} else {
			double[] distances = new double[4];
			distances[0] = a.getPoint1().getDistanceTo(b.getPoint1());
			distances[1] = a.getPoint1().getDistanceTo(b.getPoint2());
			distances[2] = a.getPoint1().getDistanceTo(c.getPoint1());
			distances[3] = a.getPoint1().getDistanceTo(c.getPoint2());
			
			double[] distances2 = new double[4];
			distances2[0] = a.getPoint2().getDistanceTo(b.getPoint1());
			distances2[1] = a.getPoint2().getDistanceTo(b.getPoint2());
			distances2[2] = a.getPoint2().getDistanceTo(c.getPoint1());
			distances2[3] = a.getPoint2().getDistanceTo(c.getPoint2());

			double smallesDistance = Double.MAX_VALUE;
			int index = 0 ;
			double smallesDistance2 = Double.MAX_VALUE;
			int index2 = 0 ;
			
			for (int i = 0; i < 4; i++) {
				if(distances[i] < smallesDistance){
					smallesDistance = distances[i];
					index = i;
				}
				if(distances2[i] < smallesDistance2){
					smallesDistance2 = distances2[i];
					index2 = i;
				}
			}
			
			if(smallesDistance < smallesDistance2){
			
			}

		}

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
	public static Line getPointsOfIntersectionCrircle(Point m1, Point m2, double r1, double r2) {

		Line distance = new Line(m1, m2);
		double temp_m2_x, x1, x2, y1, y2;
		double temp_m2_y;
		boolean isSwaped = false;

		// Mit freundlicher unterstuetzung von Mag. Harald Tranacher
		temp_m2_x = (m2.getX() - m1.getX());
		temp_m2_y = (m2.getY() - m1.getY());

		// 90° Drehung wenn Punkte auf gleicher X-Achse liegen
		if (0 == temp_m2_x) {
			temp_m2_x = temp_m2_y;
			temp_m2_y = 0;
			isSwaped = true;
		}

		// Test output
		// System.out.println();
		// System.out.println(distance);
		// System.out.println(r1 + r2);

		if (r1 + r2 >= distance.getDistance()) {

			double a = (Utils.sqr(r1) - Utils.sqr(r2) + Utils.sqr(temp_m2_x) + Utils.sqr(temp_m2_y)) / (2.0 * temp_m2_x);
			double b = -(2.0 * temp_m2_y) / (2.0 * temp_m2_x);
			double p = (2.0 * a * b) / (Utils.sqr(b) + 1.0);
			double q = (Utils.sqr(a) - Utils.sqr(r1)) / (Utils.sqr(b) + 1.0);

			// Test output
			/*
			 * System.out.println(a); System.out.println(b);
			 * System.out.println(p); System.out.println(q);
			 */

			y1 = -(p / 2.0) + Math.sqrt((Utils.sqr(p / 2.0) - q));
			y2 = -(p / 2.0) - Math.sqrt((Utils.sqr(p / 2.0) - q));

			x1 = a + b * y1;
			x2 = a + b * y2;

		} else {
			double vectorlength_to_point = ((distance.getDistance() - r1 - r2) / 2.0) + r1;
			double k = (distance.getDistance()) / vectorlength_to_point;
			x1 = (temp_m2_x / k);
			x2 = (temp_m2_x / k);
			y1 = (temp_m2_y / k);
			y2 = (temp_m2_y / k);
		}

		// Zurückdrehen wenn notwendig
		if (isSwaped) {
			// x mit y austauschen und der offset wieder dazuaddiert
			return new Line(new Point(y1 + m1.getX(), x1 + m1.getY()), new Point(y2 + m1.getX(), x2 + m1.getY()));
		}

		return new Line(new Point(x1 + m1.getX(), y1 + m1.getY()), new Point(x2 + m1.getX(), y2 + m1.getY()));
	}
}
