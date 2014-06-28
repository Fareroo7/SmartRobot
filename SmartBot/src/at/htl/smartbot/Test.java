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

		 Point m1 = new Point(1, 1);
		 Point m2 = new Point(3, 1);
		 Point m3 = new Point(1.5, 3);
		
		 Point t1= new Point(0.15,1.53);
		 Point t2= new Point(2,1.06);
		 Point t3= new Point(2.25,1);
		 Point t4= new Point(2.7,1.4);
		
		System.out.println(Trilateration.trilaterateV2(m1, m2, m3, 1, 0.5, 2));
		
	}
}
