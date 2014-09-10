package at.htl.smartbot;

import java.util.Arrays;

import at.htl.ECP.ECPMessage;
import at.htl.geometrics.*;

/**
 * Test Class
 * 
 * @author Jakob Ecker & Dominik Simon
 * 
 */
public class Test {

	public static void main(String[] args) {
		byte c = (byte)ECPMessage.getECString(true, 100, 255).charAt(2);
	
		System.out.println((Integer.toBinaryString((int)c & 0xFF)));
		
	}
}
