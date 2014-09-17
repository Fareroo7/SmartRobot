package at.htl.smartbot;

import at.htl.ECP.ECP;

/**
 * Test Class
 * 
 * @author Jakob Ecker & Dominik Simon
 * 
 */
public class Test {

	public static void main(String[] args) {
		System.out.println("20°C: "+EnvironmentalParameter.getAcousticVelocity());
		EnvironmentalParameter.setAirTemperature(40);
		System.out.println("40°C: "+EnvironmentalParameter.getAcousticVelocity());
	}
}
