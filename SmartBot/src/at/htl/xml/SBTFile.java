package at.htl.xml;

public class SBTFile {

	private SBTHeader header;
	private SBTData data;
	
	public SBTFile(){
		
	}
	
	public SBTFile(SBTHeader header, SBTData data){
		this.header = header;
		this.data = data;
	}

	public SBTHeader getHeader() {
		return header;
	}

	public void setHeader(SBTHeader header) {
		this.header = header;
	}

	public SBTData getData() {
		return data;
	}

	public void setData(SBTData data) {
		this.data = data;
	}
	
	public String toXMLString(){
		return header.toXMLString() + data.toXMLString();
	}
	
}
