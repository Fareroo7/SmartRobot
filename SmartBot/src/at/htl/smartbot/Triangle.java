package at.htl.smartbot;

import java.util.ArrayList;

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

	private Line bisector_A;
	private Line bisector_B;
	private Line bisector_C;

	public Triangle(Point p1, Point p2, Point p3) {
		this.point_A = p1;
		this.point_B = p2;
		this.point_C = p3;

		refreshLines(p1, p2, p3);
		sortPoints();
		refreshBisectingPoints();
	}

	public Triangle(Line a, Line b, Line c) {

		this.line_a = a;
		this.line_b = b;
		this.line_c = c;

		refreshPoints(a, b, c);
		sortPoints();
		refreshBisectingPoints();
	}

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
		}else{
			temp_A=point_A;
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
		}else{
			temp_B=point_B;
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
		}else{
			temp_C=point_C;
		}

		point_A = temp_A;
		point_B = temp_B;
		point_C = temp_C;

	}
	
	private void refreshBesectors(){
		
	}

	private void refreshBisectingPoints() {
		double x = ((line_a.getPoint2().getX() - line_a.getPoint1().getX()) / 2)
				+ line_a.getPoint1().getX();
		double y = ((line_a.getPoint2().getY() - line_a.getPoint1().getY()) / 2)
				+ line_a.getPoint1().getY();
		bisecting_a = new Point(x, y);

		x = ((line_b.getPoint2().getX() - line_b.getPoint1().getX()) / 2)
				+ line_b.getPoint1().getX();
		y = ((line_b.getPoint2().getY() - line_b.getPoint1().getY()) / 2)
				+ line_b.getPoint1().getY();
		bisecting_b = new Point(x, y);

		x = ((line_c.getPoint2().getX() - line_c.getPoint1().getX()) / 2)
				+ line_c.getPoint1().getX();
		y = ((line_c.getPoint2().getY() - line_c.getPoint1().getY()) / 2)
				+ line_c.getPoint1().getY();
		bisecting_c = new Point(x, y);

	}

	private void refreshPoints(Line a, Line b, Line c) {
		ArrayList<Point> temp_points = new ArrayList<Point>();

		temp_points.add(a.getPoint1());
		temp_points.add(a.getPoint2());

		temp_points.add(b.getPoint1());
		temp_points.add(b.getPoint2());

		temp_points.add(c.getPoint1());
		temp_points.add(c.getPoint2());

		temp_points = Utils.eliminateRedundance(temp_points);

		if (temp_points.size() == 3) {
			point_A = temp_points.get(0);
			point_B = temp_points.get(1);
			point_C = temp_points.get(2);
		} else {
			System.out.println("These Lines are no Triangle");
		}
	}

	private void refreshLines(Point p1, Point p2, Point p3) {
		this.line_a = new Line(point_A, point_B);
		this.line_b = new Line(point_B, point_C);
		this.line_c = new Line(point_C, point_A);
	}

	public Point getPoint_A() {
		return point_A;
	}

	public void setPoint_A(Point point_A) {
		this.point_A = point_A;
		refreshLines(this.point_A, this.point_B, this.point_C);
		sortPoints();
		refreshBisectingPoints();
	}

	public Point getPoint_B() {
		return point_B;
	}

	public void setPoint_B(Point point_B) {
		this.point_B = point_B;
		refreshLines(this.point_A, this.point_B, this.point_C);
		sortPoints();
		refreshBisectingPoints();
	}

	public Point getPoint_C() {
		return point_C;
	}

	public void setPoint_C(Point point_C) {
		this.point_C = point_C;
		refreshLines(this.point_A, this.point_B, this.point_C);
		sortPoints();
		refreshBisectingPoints();
	}

	public Line getLine_a() {
		return line_a;
	}

	public void setLine_a(Line line_a) {
		this.line_a = line_a;
		refreshPoints(this.line_a, this.line_b, this.line_c);
		sortPoints();
		refreshBisectingPoints();
	}

	public Line getLine_b() {
		return line_b;
	}

	public void setLine_b(Line line_b) {
		this.line_b = line_b;
		refreshPoints(this.line_a, this.line_b, this.line_c);
		sortPoints();
		refreshBisectingPoints();
	}

	public Line getLine_c() {
		return line_c;
	}

	public void setLine_c(Line line_c) {
		this.line_c = line_c;
		refreshPoints(this.line_a, this.line_b, this.line_c);
		sortPoints();
		refreshBisectingPoints();
	}

	public Line getBisector_A() {
		return bisector_A;
	}

	public Line getBisector_B() {
		return bisector_B;
	}

	public Line getBisector_C() {
		return bisector_C;
	}

	@Override
	public String toString() {
		return "Triangle [ \n A: " + point_A.toString() + "\n B: "
				+ point_B.toString() + "\n C: " + point_C.toString() + "\n a: "
				+ line_a.toString() + "\n b: " + line_b.toString() + "\n c: "
				+ line_c.toString() + "\n bisecting points: "
				+ bisecting_a.toString() + " , " + bisecting_b.toString()
				+ " , " + bisecting_c.toString() + "]";
	}

	public Point getBisecting_a() {
		return bisecting_a;
	}

	public Point getBisecting_b() {
		return bisecting_b;
	}

	public Point getBisecting_c() {
		return bisecting_c;
	}

}
