package at.htl.smartbot;

import at.htl.geometrics.*;

/**
 * Test Class
 * 
 * @author Jakob Ecker & Dominik Simon
 * 
 */
public class Test {

	public static void main(String[] args) {

		//Line l = Trilateration.getPointsOfIntersectionCrircleV2(new Point(5,5), new Point(3.5,3.5), 4, 1);
		//System.out.println(l);
		 
		CartesianVector cv = new CartesianVector(2, 0);
		System.out.println(cv.toPolarVector());
		
	}
}
