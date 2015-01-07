package at.android.smartbot.inertiapos;

import at.htl.geometrics.CartesianVector;

public class Clipper {

	private int clippingSize = 10;
	private CartesianVector[] values;
	private CartesianVector sum;
	private CartesianVector min;
	private CartesianVector max;
	private int pointer = 0;
	private boolean isFull = false;

	public Clipper(int clippingSize) {
		super();
		this.clippingSize = clippingSize;
		this.values = new CartesianVector[this.clippingSize];
		sum = new CartesianVector(0, 0);
		min = new CartesianVector(Double.MAX_VALUE,Double.MAX_VALUE);
		max = new CartesianVector(Double.MIN_VALUE, Double.MIN_VALUE);
	}

	public void add(CartesianVector v) {

		if (isFull) {
			sum.setX(sum.getX() - values[pointer].getX() + v.getX());
			sum.setY(sum.getY() - values[pointer].getY() + v.getY());
		} else {
			sum.setX(sum.getX() + v.getX());
			sum.setY(sum.getY() + v.getY());
		}
		
		if(values[pointer].getX()==min.getX()){
			min.setX(Double.MAX_VALUE);
		}
		if(values[pointer].getY()==min.getY()){
			min.setY(Double.MAX_VALUE);
		}
		if(values[pointer].getX()==max.getX()){
			max.setX(Double.MIN_VALUE);
		}
		if(values[pointer].getY()==max.getY()){
			max.setY(Double.MIN_VALUE);
		}
		
		values[pointer] = v;

		if (pointer >= clippingSize - 1) {
			pointer = 0;
			isFull = true;
		} else {
			pointer++;
		}
	}
	
	private void checkMinMax(CartesianVector v){
		if(v.getX()<min.getX()){
			min.setX(v.getX());
		}
		if(v.getX()>max.getX()){
			max.setX(v.getX());
		}
		if(v.getY()<min.getY()){
			min.setY(v.getY);
		}
		if(v.getY()>max.getY()){
			max.set(v.getY());
		}	
	}

}
