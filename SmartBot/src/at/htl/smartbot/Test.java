package at.htl.smartbot;

import java.util.ArrayList;

/**
 * Test Class
 * 
 * @author Jakob Ecker & Dominik Simon
 * 
 */
public class Test {

	public static void main(String[] args) {

		// Point m1 = new Point(1, 1);
		// Point m2 = new Point(3, 1);
		// Point m3 = new Point(1.5, 3);
		//
		// Point t1= new Point(0.15,1.53);
		// Point t2= new Point(2,1.06);
		// Point t3= new Point(2.25,1);
		// Point t4= new Point(2.7,1.4);
		//
		// // Trilateration.trilaterate(m1, m2, m3, 1, 0.5, 2);
		//
		// Triangle test = new Triangle(m1,m2,m3);
		//
		// System.out.println(test.toString());

		// Point p = Line.getPointsOfIntersectionLine(new Line(new Point(1, 1),
		// new Point(6, 4)), new Line(new Point(2, 4), new Point(6, 2)));
		// System.out.println(p.toString());

//		ArrayList<Point> points = new ArrayList<Point>();
//		points.add(new Point(2, 1));
//		points.add(new Point(3, 3));
//		points.add(new Point(6, 2));
//
//		Triangle t = Triangle.getSmallestTriangel(points);
//		
//		Point s = Triangle.getCentroidOfSmallestTriangle(t);
//		
//		System.out.println(s.toString());
		
		Point s1 = new Point(2,1);
		Point s2 = new Point(3,3);
		Point s3 = new Point(6,2);
		
		Point position = Trilateration.trilaterate(s1, s2, s3, 2.0, 1.0, 2.5);
		
		System.out.println(position.toString());

	}
}
