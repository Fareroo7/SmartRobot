package at.htl.smartbot;

/**
 * Test Class
 * @author Jakob Ecker & Dominik Simon
 *
 */
public class Test {

	public static void main(String[] args) {

		Point m1 = new Point(1, 1);
		Point m2 = new Point(2.5, 1);
		Point m3 = new Point(1.5, 3);
		Point m4 = new Point(1.1,1);
		
		Trilateration.trilaterate(m1, m2, m3, 1, 0.5, 2);
		
		System.out.println(m1.equals(m4));

	}

}
