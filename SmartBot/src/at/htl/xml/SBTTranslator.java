package at.htl.xml;

import java.io.*;

public class SBTTranslator {

	public static boolean sbtExport(File destinationFile, SBTHead header, SBTData data){
		try {
			BufferedWriter sbtExport = new BufferedWriter(new FileWriter(destinationFile));
			sbtExport.write(header.toXMLString());
			sbtExport.write(data.toXMLString());
			sbtExport.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}
