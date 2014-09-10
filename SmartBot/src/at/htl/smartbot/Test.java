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
		byte c = (byte)ECP.getECMessage(true, 100, 255).charAt(2);
	
		System.out.println((Integer.toBinaryString(c)));
		
	}
}
