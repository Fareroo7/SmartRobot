package at.htl.smartbot;

public class Test {

	public static void main(String[] args) {

		Point m1 = new Point(1, 1);
		Point m2 = new Point(2, 1.5);
		Point m3 = new Point(1.5, 3);
		Trilateration tri=new Trilateration();
		
		tri.trilateration(m1, m2, m3, 1, 0.5, 1.5);

	}

}
