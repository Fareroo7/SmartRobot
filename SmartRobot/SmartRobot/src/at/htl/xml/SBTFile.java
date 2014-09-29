package at.htl.xml;

/**
 * Consists of the SmartBotTrack Header and the SmartBotTrack Data
 * @author Jakob Ecker
 * @author Dominik Simon
 *
 */
public class SBTFile {

	private SBTHeader header;
	private SBTData data;
	
	/**
	 * Constructs a new empty SBTFile
	 */
	public SBTFile(){
		
	}
	
	/**
	 * 
	 * @param header
	 * @param data
	 */
	public SBTFile(SBTHeader header, SBTData data){
		this.header = header;
		this.data = data;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public SBTHeader getHeader() {
		return header;
	}

	/**
	 * Setter
	 * @return
	 */
	public void setHeader(SBTHeader header) {
		this.header = header;
	}

	/**
	 * Getter
	 * @return
	 */
	public SBTData getData() {
		return data;
	}

	/**
	 * Setter
	 * @return
	 */
	public void setData(SBTData data) {
		this.data = data;
	}
	
	/**
	 * Returns the full file (Header+Data) in xml-format
	 * @return
	 */
	public String toXMLString(){
		return header.toXMLString() + data.toXMLString();
	}
	
}
