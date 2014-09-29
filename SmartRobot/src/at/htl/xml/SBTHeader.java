package at.htl.xml;

import java.util.Date;
import at.htl.smartbot.Utils;

/**
 * Contains the Header of a SmartBotTrack consisting of a track-name, the creation date and the date of the last update
 * @author Jakob Ecker
 * @author Dominik Simon
 *
 */
public class SBTHeader {

	private String name;
	private Date creationDate;
	private Date lastUpdate;
	
	/**
	 * Constructs a new SBTHeader with creation Date and name
	 * @param creationDate
	 * @param name
	 */
	public SBTHeader(Date creationDate, String name){
		this.creationDate=creationDate;
		this.lastUpdate=creationDate;
		this.name=name;
	}
	
	/**
	 * Constructs a new SBTHeader with creation date, date of last update and the name
	 * @param creationDate
	 * @param lastUpdate
	 * @param name
	 */
	public SBTHeader(Date creationDate,Date lastUpdate, String name){
		this.creationDate=creationDate;
		this.lastUpdate=lastUpdate;
		this.name=name;
	}
	
	/**
	 * Constructs a new empty SBTHeader
	 */
	public SBTHeader(){}
	
	/**
	 * Setter
	 * @param creationDate
	 */
	protected void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}
	
	/**
	 * Setter
	 * @return
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * Converts the SBTHeader to an xml-String
	 * @return
	 */
	public String toXMLString() {
		String ln = System.lineSeparator();
		return "<head>" + ln
				+ "\t <name>"+name+"</name>" + ln
				+ "\t <creationdate>"+Utils.dateToFormattedString(creationDate)+"</creationdate>" + ln
				+ "\t <lastupdate>"+Utils.dateToFormattedString(lastUpdate)+"</lastupdate>" +ln
				+ "</head>" + ln;
	}
	
	
	
	
}
