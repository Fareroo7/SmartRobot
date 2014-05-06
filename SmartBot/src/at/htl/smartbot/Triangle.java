package at.htl.smartbot;

import java.util.ArrayList;

public class Triangle {

	private Point point1;
	private Point point2;
	private Point point3;

	private Line line_a;
	private Line line_b;
	private Line line_c;

	private Point bisecting_a;
	private Point bisecting_b;
	private Point bisecting_c;

	public Triangle(Point p1, Point p2, Point p3) {
		this.point1 = p1;
		this.point2 = p2;
		this.point3 = p3;

		refreshLines(p1, p2, p3);
		refreshBisectingPoints();
	}

	public Triangle(Line a, Line b, Line c) {

		this.line_a = a;
		this.line_b = b;
		this.line_c = c;

		refreshPoints(a, b, c);
		refreshBisectingPoints();
	}

	public void refreshBisectingPoints(){
		double x = ((line_a.getPoint2().getX()-line_a.getPoint1().getX())/2)+line_a.getPoint1().getX();
		double y = ((line_a.getPoint2().getY()-line_a.getPoint1().getY())/2)+line_a.getPoint1().getY();
		bisecting_a=new Point(x,y);
		
		x = ((line_b.getPoint2().getX()-line_b.getPoint1().getX())/2)+line_b.getPoint1().getX();
		y = ((line_b.getPoint2().getY()-line_b.getPoint1().getY())/2)+line_b.getPoint1().getY();
		bisecting_a=new Point(x,y);
		
		x = ((line_c.getPoint2().getX()-line_c.getPoint1().getX())/2)+line_c.getPoint1().getX();
		y = ((line_c.getPoint2().getY()-line_c.getPoint1().getY())/2)+line_c.getPoint1().getY();
		bisecting_a=new Point(x,y);
		
	}

	public void refreshPoints(Line a, Line b, Line c) {
		ArrayList<Point> temp_points = new ArrayList<Point>();

		temp_points.add(a.getPoint1());
		temp_points.add(a.getPoint2());

		temp_points.add(b.getPoint1());
		temp_points.add(b.getPoint2());

		temp_points.add(c.getPoint1());
		temp_points.add(c.getPoint2());

		temp_points = Utils.eliminateRedundance(temp_points);

		if (temp_points.size() == 3) {
			point1 = temp_points.get(0);
			point2 = temp_points.get(1);
			point3 = temp_points.get(2);
		} else {
			System.out.println("These Lines are no Triangle");
		}
	}

	public void refreshLines(Point p1, Point p2, Point p3) {
		this.line_a = new Line(point1, point2);
		this.line_b = new Line(point2, point3);
		this.line_c = new Line(point3, point1);
	}

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
		refreshLines(this.point1, this.point2, this.point3);
		refreshBisectingPoints();
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
		refreshLines(this.point1, this.point2, this.point3);
		refreshBisectingPoints();
	}

	public Point getPoint3() {
		return point3;
	}

	public void setPoint3(Point point3) {
		this.point3 = point3;
		refreshLines(this.point1, this.point2, this.point3);
		refreshBisectingPoints();
	}

	public Line getLine_a() {
		return line_a;
	}

	public void setLine_a(Line line_a) {
		this.line_a = line_a;
		refreshPoints(this.line_a, this.line_b, this.line_c);
		refreshBisectingPoints();
	}

	public Line getLine_b() {
		return line_b;
	}

	public void setLine_b(Line line_b) {
		this.line_b = line_b;
		refreshPoints(this.line_a, this.line_b, this.line_c);
		refreshBisectingPoints();
	}

	public Line getLine_c() {
		return line_c;
	}

	public void setLine_c(Line line_c) {
		this.line_c = line_c;
		refreshPoints(this.line_a, this.line_b, this.line_c);
		refreshBisectingPoints();
	}

	@Override
	public String toString() {
		return "Triangle [A: " + point1.toString() + "\n\r B: " + point2.toString()
				+ "\n\r C: " + point3.toString() + "\n\r a: " + line_a.toString() + "\n\r b: "
				+ line_b.toString() + "\n\r c: " + line_c.toString() + "\n\r bisecting points: "
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
