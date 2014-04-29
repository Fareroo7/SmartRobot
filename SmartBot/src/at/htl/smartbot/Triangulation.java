package at.htl.smartbot;

import java.util.Arrays;

public class Triangulation {

	private static final int X = 0;
	private static final int Y = 1;

	public static void main(String[] args) {

		double[] m1 = { 1.0, 1.0 };
		double[] m2 = { 2.0, 2.0 };
		double[][] result = sp(m1, m2, 1.0, 2.0);
		System.out.println(Arrays.toString(result[0]));
		System.out.println(Arrays.toString(result[1]));
	}

	public static double[][] sp(double[] m1, double[] m2, double r1, double r2) {

		m2[X] = m2[X] - m1[X];
		m2[Y] = m2[Y] - m1[Y];

		double a = (sqr(r1) - sqr(r2) + sqr(m2[X]) + sqr(m2[Y])) / (2 * m2[X]);
		double b = -(2 * m2[Y]) / (2 * m2[X]);
		double p = (2 * a * b) / (sqr(b) + 1);
		double q = (sqr(a) - sqr(r1)) / (sqr(b) + 1);
		
		
	//	Control output
	/*	System.out.println(a);
		System.out.println(b);
		System.out.println(p);
		System.out.println(q); */

		double y1 = -(p / 2) + Math.sqrt((sqr(p / 2) - q));
		double y2 = -(p / 2) - Math.sqrt((sqr(p / 2) - q));

		double x1 = a + b * y1;
		double x2 = a + b * y2;

		double[][] result = new double[2][2];
		result[0][X] = x1+m1[X];
		result[0][Y] = y1+m1[Y];
		result[1][X] = x2+m1[X];
		result[1][Y] = y2+m1[Y];

		return result;
	}

	public static double sqr(double base) {
		return Math.pow(base, 2);
	}
}
