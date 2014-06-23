package at.htl.xml;

import java.io.*;
import java.util.Date;
import java.util.StringTokenizer;

import at.htl.smartbot.Utils;

public class SBTTranslator {

	public static boolean sbtExport(File destinationFile, SBTHead header, SBTData data) {
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

	public static SBTHead sbtImportHeader(File sourceFile) throws IOException {

		BufferedReader sbtImport = new BufferedReader(new FileReader(sourceFile));
		String input;
		boolean head = false, stop = false;
		String name=null;
		Date creationDate=null, lastUpdate=null;

		while ((input = sbtImport.readLine()) != null && !stop) {

			StringTokenizer cruncher = new StringTokenizer("<", ">");

			while (cruncher.hasMoreTokens() && !stop) {

				String piece = cruncher.nextToken();

				if (piece.equals("head")) {
					head = true;
				} else if (piece.equals("/head")) {
					head = false;
					stop = true;
				} else if (head && piece.equals("name")) {
					name = cruncher.nextToken();
					if (!cruncher.nextToken().equals("/name")) {
						System.err.println("Tag-Ende fehlt");
					}
				} else if (head && piece.equals("creationdate")) {
					creationDate = Utils.formattedStringToDate(cruncher.nextToken());
					if (!cruncher.nextToken().equals("/creationdate")) {
						System.err.println("Tag-Ende fehlt");
					}
				} else if (head && piece.equals("lastupdate")) {
					lastUpdate = Utils.formattedStringToDate(cruncher.nextToken());
					if (!cruncher.nextToken().equals("/lastupdate")) {
						System.err.println("Tag-Ende fehlt");
					}
				}
			}
		}
		return new SBTHead(creationDate,lastUpdate,name);
	}
}
