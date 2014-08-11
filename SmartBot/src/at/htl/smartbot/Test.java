package at.htl.smartbot;

import java.io.File;
import java.io.IOException;
import java.util.*;
import at.htl.geometrics.*;
import at.htl.xml.*;

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
		
		int t=1234;
		String b = Integer.toString(t);
		System.out.println(b);
		System.out.println(b.charAt(4));
		
	}
}
