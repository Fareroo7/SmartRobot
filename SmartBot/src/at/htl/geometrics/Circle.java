package at.htl.geometrics;

import at.htl.smartbot.Utils;

public class Circle {
	private Point m;
	private double r;
	
	public Circle(Point m, double r){
		this.m = m;
		this.r = r;
	}
	
	public Circle(){
		
	}
	
	public Point[] getPointsOfIntersection(Circle c) {

		Line distance = new Line(this.m, c.m);
		double temp_m2_x, x1, x2, y1, y2;
		double temp_m2_y;
		boolean isSwaped = false;

		// Mit freundlicher unterstuetzung von Mag. Harald Tranacher
		temp_m2_x = (c.m.getX() - this.m.getX());
		temp_m2_y = (c.m.getY() - this.m.getY());

		// 90° Drehung wenn Punkte auf gleicher X-Achse liegen
		if (0 == temp_m2_x) {
			temp_m2_x = temp_m2_y;
			temp_m2_y = 0;
			isSwaped = true;
		}

		if (this.r + c.r >= distance.getLength()) {

			if (distance.getLength() + c.r < this.r) {

				PolarVector pv = new PolarVector(this.r, distance.getPolarVektor().getPhi());
				pv.setZ(pv.getZ() - ((this.r - distance.getLength() - c.r) / 2));
				CartesianVector cv = pv.toCartesianVector();

				x1 = this.m.getX() + cv.getX();
				y1 = this.m.getY() + cv.getY();
				x2 = x1;
				y2 = y1;

				return new Point[] {new Point(x1, y1), new Point(x2, y2)};
			} else if (distance.getLength() + this.r < c.r) {

				PolarVector pv = new PolarVector(c.r, distance.getPolarVektor().getPhi());
				pv.setZ(pv.getZ() - ((c.r - distance.getLength() - this.r) / 2));
				CartesianVector cv = pv.toCartesianVector();

				x1 = c.m.getX() + cv.getX();
				y1 = c.m.getY() + cv.getY();
				x2 = x1;
				y2 = y1;
				return new Point[] {new Point(x1, y1), new Point(x2, y2)};
			} else {

				double a = (Utils.sqr(this.r) - Utils.sqr(c.r) + Utils.sqr(temp_m2_x) + Utils.sqr(temp_m2_y)) / (2.0 * temp_m2_x);
				double b = -(2.0 * temp_m2_y) / (2.0 * temp_m2_x);
				double p = (2.0 * a * b) / (Utils.sqr(b) + 1.0);
				double q = (Utils.sqr(a) - Utils.sqr(this.r)) / (Utils.sqr(b) + 1.0);

				y1 = -(p / 2.0) + Math.sqrt((Utils.sqr(p / 2.0) - q));
				y2 = -(p / 2.0) - Math.sqrt((Utils.sqr(p / 2.0) - q));

				x1 = a + b * y1;
				x2 = a + b * y2;
			}
		} else {
			double vectorlength_to_point = ((distance.getLength() - this.r - c.r) / 2.0) + this.r;
			double k = (distance.getLength()) / vectorlength_to_point;
			x1 = (temp_m2_x / k);
			x2 = x1;
			y1 = (temp_m2_y / k);
			y2 = y1;
		}

		// Zurückdrehen wenn notwendig
		if (isSwaped) {
			// x mit y austauschen und der offset wieder dazuaddiert
			return new Point[] {new Point(y1 + this.m.getX(), x1 + this.m.getY()), new Point(y2 + this.m.getX(), x2 + this.m.getY())};
		}

		return new Point[] {new Point(x1 + this.m.getX(), y1 + this.m.getY()), new Point(x2 + this.m.getX(), y2 + this.m.getY())};
	}

	public Point getCentre() {
		return m;
	}

	public void setCentre(Point m) {
		this.m = m;
	}

	public double getRadius() {
		return r;
	}

	public void setRadius(double r) {
		this.r = r;
	}

	@Override
	public String toString() {
		return "Circle [centre=" + m + ", radius=" + r + "]";
	}
	
}
