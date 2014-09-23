package at.htl.geometrics;

import java.util.ArrayList;

/**
 * Represent a triangle with the three corner {@link Point} or three {@link Line}. If the Points are
 * edited the Lines are automatically updated and vice versa. Also the bisecting
 * points of the triangle lines are stored.
 * 
 * @author Jakob Ecker
 * @author Dominik Simon
 * @version 1.0
 */
public class Triangle {

	private Point point_A;
	private Point point_B;
	private Point point_C;

	private Line line_a;
	private Line line_b;
	private Line line_c;

	private Point bisecting_a;
	private Point bisecting_b;
	private Point bisecting_c;

	/**
	 * Constructs a new Triangle-Object from three points.
	 * @param p1 Point one.
	 * @param p2 Point two.
	 * @param p3 Point three.
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		this.point_A = p1;
		this.point_B = p2;
		this.point_C = p3;

		refreshLines(p1, p2, p3);
		sortPoints();
		refreshBisectingPoints();
	}

	/**
	 * Constructs a new Triangle - object form three lines.
	 * @param a Line a.
	 * @param b Line b.
	 * @param c Line c.
	 */
	public Triangle(Line a, Line b, Line c) {

		this.line_a = a;
		this.line_b = b;
		this.line_c = c;

		refreshPoints(a, b, c);
		sortPoints();
		refreshBisectingPoints();
	}

	/**
	 * Constructs a new empty Triangle - object.
	 */
	public Triangle() {
	}

	/**
	 * Sort points for mathematical correct labeling.
	 */
	private void sortPoints() {

		Point temp_A = new Point();
		Point temp_B = new Point();
		Point temp_C = new Point();

		if (point_A.equals(line_a.getPoint1())) {
			if (line_a.getPoint2().equals(point_B)) {
				temp_A = point_C;
			} else {
				temp_A = point_B;
			}
		} else if (point_A.equals(line_a.getPoint2())) {
			if (line_a.getPoint1().equals(point_B)) {
				temp_A = point_C;
			} else {
				temp_A = point_B;
			}
		} else {
			temp_A = point_A;
		}

		if (point_B.equals(line_b.getPoint1())) {
			if (line_b.getPoint2().equals(point_C)) {
				temp_B = point_A;
			} else {
				temp_B = point_C;
			}
		} else if (point_B.equals(line_b.getPoint2())) {
			if (line_b.getPoint1().equals(point_C)) {
				temp_B = point_A;
			} else {
				temp_B = point_C;
			}
		} else {
			temp_B = point_B;
		}

		if (point_C.equals(line_c.getPoint1())) {
			if (line_c.getPoint2().equals(point_A)) {
				temp_C = point_B;
			} else {
				temp_C = point_A;
			}
		} else if (point_C.equals(line_c.getPoint2())) {
			if (line_c.getPoint1().equals(point_A)) {
				temp_C = point_B;
			} else {
				temp_C = point_A;
			}
		} else {
			temp_C = point_C;
		}

		point_A = temp_A;
		point_B = temp_B;
		point_C = temp_C;

	}

	/**
	 * Calculates the bisecting points of all lines
	 */
	private void refreshBisectingPoints() {
		double x = ((line_a.getPoint2().getX() - line_a.getPoint1().getX()) / 2) + line_a.getPoint1().getX();
		double y = ((line_a.getPoint2().getY() - line_a.getPoint1().getY()) / 2) + line_a.getPoint1().getY();
		bisecting_a = new Point(x, y);

		x = ((line_b.getPoint2().getX() - line_b.getPoint1().getX()) / 2) + line_b.getPoint1().getX();
		y = ((line_b.getPoint2().getY() - line_b.getPoint1().getY()) / 2) + line_b.getPoint1().getY();
		bisecting_b = new Point(x, y);

		x = ((line_c.getPoint2().getX() - line_c.getPoint1().getX()) / 2) + line_c.getPoint1().getX();
		y = ((line_c.getPoint2().getY() - line_c.getPoint1().getY()) / 2) + line_c.getPoint1().getY();
		bisecting_c = new Point(x, y);

	}

	/**
	 * Calculates the new Points in case that a Line changed.
	 * @param a Line a
	 * @param b Line b 
	 * @param c Line c
	 */
	private void refreshPoints(Line a, Line b, Line c) {

		ArrayList<Point> temp_points = new ArrayList<Point>();

		temp_points.add(a.getPoint1());
		temp_points.add(a.getPoint2());

		temp_points.add(b.getPoint1());
		temp_points.add(b.getPoint2());

		temp_points.add(c.getPoint1());
		temp_points.add(c.getPoint2());

		temp_points = Point.eliminateRedundance(temp_points);

		if (temp_points.size() == 3) {
			point_A = temp_points.get(0);
			point_B = temp_points.get(1);
			point_C = temp_points.get(2);
		} else {
			System.out.println("These Lines are no Triangle");
		}
	}

	/**
	 * Calculates the new Lines in case a Point changed.
	 */
	private void refreshLines(Point p1, Point p2, Point p3) {
		this.line_a = new Line(point_A, point_B);
		this.line_b = new Line(point_B, point_C);
		this.line_c = new Line(point_C, point_A);
	}

	/**
	 * Returns the Point A of the Triangle.
	 * @return Point A as {@link Point}.
	 */
	public Point getPointA() {
		return point_A;
	}

	/**
	 * Sets the Point A of the Triangle.
	 * @param point_A New {@link Point}.
	 */
	public void setPointA(Point point_A) {
		this.point_A = point_A;
		refreshLines(this.point_A, this.point_B, this.point_C);
		sortPoints();
		refreshBisectingPoints();
	}

	/**
	 * Returns the Point B of the Triangle.
	 * @return Point B as {@link Point}
	 */
	public Point getPointB() {
		return point_B;
	}

	/**
	 * Sets the Point B of the Triangle.
	 * @param point_B New {@link Point}.
	 */
	public void setPointB(Point point_B) {
		this.point_B = point_B;
		refreshLines(this.point_A, this.point_B, this.point_C);
		sortPoints();
		refreshBisectingPoints();
	}

	/**
	 * Returns the Point C of the Triangle.
	 * @return Point C as {@link Point}
	 */
	public Point getPointC() {
		return point_C;
	}

	/**
	 * Sets the Point C of the Triangle.
	 * @param point_C New {@link Point}.
	 */
	public void setPointC(Point point_C) {
		this.point_C = point_C;
		refreshLines(this.point_A, this.point_B, this.point_C);
		sortPoints();
		refreshBisectingPoints();
	}

	/**
	 * Returns the Line A of the Triangle.
	 * @return Line A as {@link Line}.
	 */
	public Line getLineA() {
		return line_a;
	}

	/**
	 * Sets the Line A of the Triangle.
	 * @param line_a New {@link Line}.
	 */
	public void setLineA(Line line_a) {
		this.line_a = line_a;
		refreshPoints(this.line_a, this.line_b, this.line_c);
		sortPoints();
		refreshBisectingPoints();
	}

	/**
	 * Returns the Line B of the Triangle.
	 * @return Line B as {@link Line}.
	 */
	public Line getLineB() {
		return line_b;
	}

	/**
	 * Sets the Line B of the Triangle.
	 * @param line_b New {@link Line}.
	 */
	public void setLineB(Line line_b) {
		this.line_b = line_b;
		refreshPoints(this.line_a, this.line_b, this.line_c);
		sortPoints();
		refreshBisectingPoints();
	}

	/**
	 * Returns the Line C of the Triangle.
	 * @return Line C as {@link Line}.
	 */
	public Line getLineC() {
		return line_c;
	}

	/**
	 * Sets the Line C of the Triangle.
	 * @param line_c New {@link Line}.
	 */
	public void setLineC(Line line_c) {
		this.line_c = line_c;
		refreshPoints(this.line_a, this.line_b, this.line_c);
		sortPoints();
		refreshBisectingPoints();
	}

	/**
	 * Returns the Bisecting - Line A of the Triangle.
	 * @return Bisecting - Line A as {@link Line}.
	 */
	public Point getBisectingA() {
		return bisecting_a;
	}

	/**
	 * Returns the Bisecting - Line B of the Triangle.
	 * @return Bisecting - Line B as {@link Line}.
	 */
	public Point getBisectingB() {
		return bisecting_b;
	}

	/**
	 * Returns the Bisecting - Line C of the Triangle.
	 * @return Bisecting - Line C as {@link Line}.
	 */
	public Point getBisectingC() {
		return bisecting_c;
	}
	
	/**
	 * Custom toString that returns a formatted Triangle.
	 */
	
	@Override
	public String toString() {
		return "Triangle [\nA=" + point_A + ", \nB=" + point_B + ", \nC=" + point_C + ", \na=" + line_a + ", \nb=" + line_b + ", \nc=" + line_c + ", \na/2="
				+ bisecting_a + ", \nb/2=" + bisecting_b + ", \nc/2=" + bisecting_c + "\n]";
	}

	/**
	 * Returns the smallest possible Triangle within a list of lines.
	 * @param points List of lines where the smallest triangle is needed.
	 * @return Smallest possible Triangle.
	 */
	public static Triangle getSmallestTriangel(ArrayList<Point> points) {

		ArrayList<Line> lines = new ArrayList<Line>();

		Line min1 = new Line();
		Line min2 = new Line();

		for (int i = 0; i < points.size(); i++) {
			for (int z = i + 1; z < points.size(); z++) {
				lines.add(new Line(points.get(i), points.get(z)));
			}
		}

		for (int z = 0; z < 2; z++) {
			double tmp_min = Double.MAX_VALUE;
			int index_of_min = 0;
			for (int i = 0; i < lines.size(); i++) {
				if (tmp_min > lines.get(i).getLength()) {
					tmp_min = lines.get(i).getLength();
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
			}
			lines.remove(index_of_min);
		}

		Line min3 = null;

		if (min1.getPoint1().equals(min2.getPoint1())) {
			min3 = new Line(min1.getPoint2(), min2.getPoint2());
		} else if (min1.getPoint1().equals(min2.getPoint2())) {
			min3 = new Line(min1.getPoint2(), min2.getPoint1());
		} else if (min1.getPoint2().equals(min2.getPoint1())) {
			min3 = new Line(min1.getPoint1(), min2.getPoint2());
		} else if (min1.getPoint2().equals(min2.getPoint2())) {
			min3 = new Line(min1.getPoint1(), min2.getPoint1());
		} else {
			System.out.println("These Lines are not a Triangle");
		}

		return new Triangle(min1, min2, min3);
	}

	/**
	 * Calculates the Centroid of the Triangel.
	 * @deprecated
	 * @param triangle The Triangle where the Centroid is needed.
	 * @return Centroid of the Triangle as {@link Point}.
	 */
	public static Point getCentroidOfTriangleExternal(Triangle triangle) {

		Line bisectionA = new Line(triangle.getPointA(), triangle.getBisectingA());
		Line bisectionB = new Line(triangle.getPointB(), triangle.getBisectingB());
		Line bisectionC = new Line(triangle.getPointC(), triangle.getBisectingC());

		Point s1 = Line.getPointOfIntersectionLine(bisectionA, bisectionB);
		Point s2 = Line.getPointOfIntersectionLine(bisectionB, bisectionC);
		Point s3 = Line.getPointOfIntersectionLine(bisectionC, bisectionA);

		if (s1.getDistanceTo(s2) < 1.0E-9 && s2.getDistanceTo(s3) < 1.0E-9 && s3.getDistanceTo(s1) < 1.0E-9)
			return s1;

		return null;
	}
	
	/**
	 * Calculates the Centroid of the Triangel.
	 * @return Centroid as {@link Point}.
	 */
	public Point getCentroidOfTriangle() {

		Line bisectionA = new Line(this.getPointA(), this.getBisectingA());
		Line bisectionB = new Line(this.getPointB(), this.getBisectingB());
		Line bisectionC = new Line(this.getPointC(), this.getBisectingC());

		Point s1 = Line.getPointOfIntersectionLine(bisectionA, bisectionB);
		Point s2 = Line.getPointOfIntersectionLine(bisectionB, bisectionC);
		Point s3 = Line.getPointOfIntersectionLine(bisectionC, bisectionA);

		if (s1.getDistanceTo(s2) < 1.0E-9 && s2.getDistanceTo(s3) < 1.0E-9 && s3.getDistanceTo(s1) < 1.0E-9)
			return s1;

		return null;
	}
}
