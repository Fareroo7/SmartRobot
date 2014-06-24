package at.htl.xml;

import java.util.ArrayList;

import at.htl.geometrics.*;
import at.htl.smartbot.Track;

public class SBTData {

	private ArrayList<Point> data = new ArrayList<Point>();
	
	public SBTData(Track track){
		this.data = track.getAllPositions();
	}

	public String toXMLString() {
		StringBuffer xmlstring = new StringBuffer();
		String ln = System.lineSeparator();

		xmlstring.append("<data>" + ln);

		for (Point i : data) {
			xmlstring.append("\t<navpoint>" + ln + "\t\t<x>" + i.getX() + "</x>" + ln + "\t\t<y>" + i.getY() + "</y>" + ln + "\t</navpoint>" + ln);
		}

		xmlstring.append("</data>");

		return xmlstring.toString();
	}

}
