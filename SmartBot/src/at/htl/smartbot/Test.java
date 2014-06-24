package at.htl.smartbot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import at.htl.geometrics.Point;
import at.htl.xml.SBTData;
import at.htl.xml.SBTFile;
import at.htl.xml.SBTHeader;
import at.htl.xml.SBTTranslator;

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

		// ArrayList<Point> points = new ArrayList<Point>();
		// points.add(new Point(2, 1));
		// points.add(new Point(3, 3));
		// points.add(new Point(6, 2));
		//
		// Triangle t = Triangle.getSmallestTriangel(points);
		//
		// Point s = Triangle.getCentroidOfSmallestTriangle(t);
		//
		// System.out.println(s.toString());

		// Point s1 = new Point(1,1);
		// Point s2 = new Point(1,4);
		// Point s3 = new Point(4,1);
		//
		// Point position = Trilateration.trilaterate(s1, s2, s3, 2.0, 2.0,
		// 2.5);
		//
		// System.out.println(position.toString());
		//
		// Line l1 = new Line(new Point(1, 0), new Point(1.1,3));
		// Line l2 = new Line(new Point(0.5,1), new Point(3,3));
		//
		// Point s = Line.getPointsOfIntersectionLine(l1, l2);
		//
		// System.out.println(s.toString());
//
		Point t1 = new Point(1, 1);
		Point t2 = new Point(2, 1);
		Point t3 = new Point(2, 2);
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(t1);
		list.add(t2);
		list.add(t3);
		Track track1 = new Track(list);

		SBTFile sbt = new SBTFile(new SBTHeader(new Date(), new Date(), "test"), new SBTData(track1));
		try {
			SBTTranslator.exportSBT(new File("./test.sbt"), sbt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
}
