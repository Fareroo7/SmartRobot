package at.htl.xml;

import java.util.Date;
import at.htl.smartbot.Utils;

public class SBTHeader {

	private String name;
	private Date creationDate;
	private Date lastUpdate;
	
	public SBTHeader(Date creationDate, String name){
		this.creationDate=creationDate;
		this.lastUpdate=creationDate;
		this.name=name;
	}
	
	public SBTHeader(Date creationDate,Date lastUpdate, String name){
		this.creationDate=creationDate;
		this.lastUpdate=lastUpdate;
		this.name=name;
	}
	
	protected void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public Date getCreationDate() {
		return creationDate;
	}

	public String toXMLString() {
		String ln = System.lineSeparator();
		return "<head>" + ln
				+ "\t <name>"+name+"</name>" + ln
				+ "\t <creationdate>"+Utils.dateToFormattedString(creationDate)+"</creationdate>" + ln
				+ "\t <lastupdate>"+Utils.dateToFormattedString(lastUpdate)+"</lastupdate>" +ln
				+ "</head>" + ln;
	}
	
	
	
	
}
