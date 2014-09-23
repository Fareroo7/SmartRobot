package at.htl.xml;

import java.util.ArrayList;

import at.htl.geometrics.*;
import at.htl.smartbot.Track;

/**
 * Contains the data of an SmartBotTrack (*.sbt) and provides a method to
 * converts the into an XML-String.
 * 
 * @author Jakob Ecker
 * @author Dominik Simon
 */
public class SBTData {

	private ArrayList<Point> data = new ArrayList<Point>();

	/**
	 * Constructs a new SBTData with a track
	 * @param track
	 */
	public SBTData(Track track) {
		this.data = track.getAllPositions();
	}
	
	/**
	 * Constructs a new empty SBTData
	 */
	public SBTData(){}

	/**
	 * Converts the data (a track) to an xml-String
	 * @return
	 */
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

	/**
	 * Getter
	 * @return
	 */
	public ArrayList<Point> getData() {
		return data;
	}

	/**
	 * Setter
	 * @param data
	 */
	public void setData(ArrayList<Point> data) {
		this.data = data;
	}

	
}
