package at.htl.xml;

import java.util.ArrayList;

import at.htl.geometrics.*;

public class SBTData {
	
	private ArrayList<Point> data=new ArrayList<Point>();
	
	public SBTData(ArrayList<Point> track){
		this.data=track;
	}
	
	public String toXMLString(){
		StringBuffer xmlstring = new StringBuffer();
		String ln = System.getProperty("line.separator");
		
		xmlstring.append("<data>" + ln);
		
		for(Point i:data){
			xmlstring.append("\t<navpoint>" + ln
					+ "\t\t<x>"+i.getX()+"</x>" + ln
					+ "\t\t<y>"+i.getY()+"</y>" + ln
					+ "\t</navpoint>" + ln);
		}
		
		xmlstring.append("</data>" + ln);
		
		return xmlstring.toString();
	}
	
}
