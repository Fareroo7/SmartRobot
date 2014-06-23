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
		
		xmlstring.append("<data>\n");
		
		for(Point i:data){
			xmlstring.append("\t<navpoint>\n"
					+ "\t\t<x>"+i.getX()+"</x>\n"
					+ "\t\t<y>"+i.getY()+"</y>\n"
					+ "\t</navpoint>");
		}
		
		xmlstring.append("</data>");
		
		return xmlstring.toString();
	}
	
}
