package at.htl.smartbot;

public class Line {

	@Override
	public String toString() {
		return "Line [" + point1.toString() + ", " + point2.toString()
				+ ", Distance=" + Utils.round(distance) + "]";
	}

	private Point point1;
	private Point point2;

	private double distance;

	public Line(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
		this.distance = Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2)
				+ Math.pow(point2.getY() - point1.getY(), 2));
	}

	public Line() {

	}

	private void refreshDistance() {
		this.distance = Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2)
				+ Math.pow(point2.getY() - point1.getY(), 2));
	}

	public Point getPoint1() {
		return point1;

	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
		refreshDistance();
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
		refreshDistance();
	}

	public double getDistance() {
		return distance;
	}

	public static Point getPointsOfIntersectionLine(Line line1, Line line2) {

		boolean isTurned = false;

		double deltaX1 = line1.getPoint2().getX() - line1.getPoint1().getX();
		double deltaY1 = line1.getPoint2().getY() - line1.getPoint1().getY();
		double deltaX2 = line2.getPoint2().getX() - line2.getPoint1().getX();
		double deltaY2 = line2.getPoint2().getY() - line2.getPoint1().getY();

		if (deltaX1 == 0 || deltaX2 == 0) {
			isTurned = true;
			double tmp = line1.getPoint1().getX();
			line1.getPoint1().setX(line1.getPoint1().getY());
			line1.getPoint1().setY(tmp);

			tmp = line1.getPoint2().getX();
			line1.getPoint2().setX(line1.getPoint2().getY());
			line1.getPoint2().setY(tmp);

			tmp = line2.getPoint1().getX();
			line2.getPoint1().setX(line2.getPoint1().getY());
			line2.getPoint1().setY(tmp);

			tmp = line2.getPoint2().getX();
			line2.getPoint2().setX(line2.getPoint2().getY());
			line2.getPoint2().setY(tmp);

			deltaX1 = line1.getPoint2().getX() - line1.getPoint1().getX();
			deltaY1 = line1.getPoint2().getY() - line1.getPoint1().getY();
			deltaX2 = line2.getPoint2().getX() - line2.getPoint1().getX();
			deltaY2 = line2.getPoint2().getY() - line2.getPoint1().getY();
		}

		double k_line1 = deltaY1 / deltaX1;
		double d_line1 = line1.getPoint1().getY()
				- (k_line1 * line1.getPoint1().getX());

		double k_line2 = deltaY2 / deltaX2;
		double d_line2 = line2.getPoint1().getY()
				- (k_line2 * line2.getPoint1().getX());

		double x = (d_line2 - d_line1) / (k_line1 - k_line2);
		double y = k_line1 * x + d_line1;

		if (isTurned)
			return new Point(y, x);

		return new Point(x, y);
	}

}
