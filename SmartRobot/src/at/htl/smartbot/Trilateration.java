package at.htl.smartbot;

import java.util.ArrayList;
import at.htl.geometrics.Circle;
import at.htl.geometrics.Line;
import at.htl.geometrics.Point;
import at.htl.geometrics.Triangle;

/**
 * Provides methods used for trilateration.
 * 
 * @author Jakob Ecker
 * @version 2.0
 */
public class Trilateration {

	/**
	 * Calculates the position, using the distance to three known Points.
	 * @deprecated
	 * @param pos_S1 Point one.
	 * @param pos_S2 Point two.
	 * @param pos_S3 Point three.
	 * @param distance_S1 Distance to Point one.
	 * @param distance_S2 Distance to Point two.
	 * @param distance_S3 Distance to Point three.
	 * @return The Position as {@link Point}.
	 */
	public static Point trilaterate(Point pos_S1, Point pos_S2, Point pos_S3, double distance_S1, double distance_S2, double distance_S3) {

		Triangle triangle;

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

		if (points_of_intersection.size() == 1) {
			return points_of_intersection.get(0);
		}
		if (points_of_intersection.size() == 0) {
			System.out.println("Something goes wrong!");
			return null;
		}


		triangle = Triangle.getSmallestTriangel(points_of_intersection);
		return triangle.getCentroidOfTriangle();
	}

	/**
	 * Calculates the position, using the distance to three known Circles.
	 * @param c1 Circle one.
	 * @param c2 Circle two.
	 * @param c3 Circle three.
	 * @return The Position as {@link Point}.
	 */
	public static Point trilaterate(Circle c1, Circle c2, Circle c3) {
		
		Point[] intersA = c1.getPointsOfIntersection(c2);
		Point[] intersB = c2.getPointsOfIntersection(c3);
		Point[] intersC = c3.getPointsOfIntersection(c1);

		Point pA = null;
		Point pB = null;
		Point pC = null;

		if (intersA[0].equals(intersA[1])) {
			pA = intersA[0];
		} else {
			double[] distances = new double[4];
			distances[0] = intersA[0].getDistanceTo(intersB[0]);
			distances[1] = intersA[0].getDistanceTo(intersB[1]);
			distances[2] = intersA[0].getDistanceTo(intersC[0]);
			distances[3] = intersA[0].getDistanceTo(intersC[1]);

			double[] distances2 = new double[4];
			distances2[0] = intersA[1].getDistanceTo(intersB[0]);
			distances2[1] = intersA[1].getDistanceTo(intersB[1]);
			distances2[2] = intersA[1].getDistanceTo(intersC[0]);
			distances2[3] = intersA[1].getDistanceTo(intersC[1]);

			double smallesDistance = Double.MAX_VALUE;
			int index = 0;
			double smallesDistance2 = Double.MAX_VALUE;
			int index2 = 0;

			for (int i = 0; i < 4; i++) {
				if (distances[i] < smallesDistance) {
					smallesDistance = distances[i];
					index = i;
				}
				if (distances2[i] < smallesDistance2) {
					smallesDistance2 = distances2[i];
					index2 = i;
				}
			}

			if (smallesDistance < smallesDistance2) {
				pA = intersA[0];

				switch (index) {
				case 0:
					pB = intersB[0];
					break;
				case 1:
					pB = intersB[1];
					break;
				case 2:
					pC = intersC[0];
					break;
				case 3:
					pC = intersC[1];
					break;
				}

			} else {
				pA = intersA[1];

				switch (index2) {
				case 0:
					pB = intersB[0];
					break;
				case 1:
					pB = intersB[1];
					break;
				case 2:
					pC = intersC[0];
					break;
				case 3:
					pC = intersC[1];
					break;
				}
			}

		}

		if (pB == null) {
			if (pA.getDistanceTo(intersB[0]) < pA.getDistanceTo(intersB[1])) {
				pB = intersB[0];
			} else {
				pB = intersB[1];
			}
		}
		if (pC == null) {
			if (pA.getDistanceTo(intersC[0]) < pA.getDistanceTo(intersC[1])) {
				pC = intersC[0];
			} else {
				pC = intersC[1];
			}
		}

		if (pA.equals(pB) && pA.equals(pC))
			return pA;

		return new Triangle(pA, pB, pC).getCentroidOfTriangle();
	}

	/**
	 * Calculates the intersectionpoints of two circles.
	 * @deprecated
	 * @param m1 Centre-point Circle one as {@link Point}.
	 * @param m2 Centre-point Circle two as {@link Point}.
	 * @param r1 Radius Circle one.
	 * @param r2 Radius Circle two.
	 * @return 2d array mit Schnittpunkt 1 auf [0] und Schittpunkt 2 auf [1].
	 */
	public static Line getPointsOfIntersectionCrircle(Point m1, Point m2, double r1, double r2) {

		Line distance = new Line(m1, m2);
		double temp_m2_x, x1, x2, y1, y2;
		double temp_m2_y;
		boolean isSwaped = false;

		// Mit freundlicher unterstuetzung von Mag. Harald Tranacher
		temp_m2_x = (m2.getX() - m1.getX());
		temp_m2_y = (m2.getY() - m1.getY());

		// 90 Grad Drehung wenn Punkte auf gleicher X-Achse liegen
		if (0 == temp_m2_x) {
			temp_m2_x = temp_m2_y;
			temp_m2_y = 0;
			isSwaped = true;
		}

		if (r1 + r2 >= distance.getLength()) {

			double a = (Utils.sqr(r1) - Utils.sqr(r2) + Utils.sqr(temp_m2_x) + Utils.sqr(temp_m2_y)) / (2.0 * temp_m2_x);
			double b = -(2.0 * temp_m2_y) / (2.0 * temp_m2_x);
			double p = (2.0 * a * b) / (Utils.sqr(b) + 1.0);
			double q = (Utils.sqr(a) - Utils.sqr(r1)) / (Utils.sqr(b) + 1.0);

			y1 = -(p / 2.0) + Math.sqrt((Utils.sqr(p / 2.0) - q));
			y2 = -(p / 2.0) - Math.sqrt((Utils.sqr(p / 2.0) - q));

			x1 = a + b * y1;
			x2 = a + b * y2;

		} else {
			double vectorlength_to_point = ((distance.getLength() - r1 - r2) / 2.0) + r1;
			double k = (distance.getLength()) / vectorlength_to_point;
			x1 = (temp_m2_x / k);
			x2 = (temp_m2_x / k);
			y1 = (temp_m2_y / k);
			y2 = (temp_m2_y / k);
		}

		// Zurueckdrehen wenn notwendig
		if (isSwaped) {
			// x mit y austauschen und der offset wieder dazuaddiert
			return new Line(new Point(y1 + m1.getX(), x1 + m1.getY()), new Point(y2 + m1.getX(), x2 + m1.getY()));
		}

		return new Line(new Point(x1 + m1.getX(), y1 + m1.getY()), new Point(x2 + m1.getX(), y2 + m1.getY()));
	}

}
