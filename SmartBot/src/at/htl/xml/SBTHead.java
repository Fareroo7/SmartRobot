package at.htl.xml;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;

import at.htl.smartbot.Utils;

public class SBTHead {

	private String name;
	private Date creationDate;
	private Date lastUpdate;
	
	public SBTHead(Date creationDate, String name){
		this.creationDate=creationDate;
		this.lastUpdate=creationDate;
		this.name=name;
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
		return "<head> \n"
				+ "\t <name>"+name+"</name> \n"
				+ "\t <creationdate>"+Utils.dateFormat(creationDate)+"</creationdate>\n"
				+ "\t <lastupdate>"+Utils.dateFormat(lastUpdate)+"</lastupdate>\n"
				+ "</head>\n";
	}
	
	
	
	
}
