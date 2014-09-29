package at.htl.xml;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import at.htl.geometrics.Point;
import at.htl.smartbot.Track;
import at.htl.smartbot.Utils;

/**
 * This class provides methods to export/import SmartBotTracks(*.sbt)
 * @author Jakob Ecker
 * @author Dominik Simon
 *
 */
public class SBTTranslator {

	/**
	 * Writes the SBTFile as xml-Sting to the destination file
	 * @param destinationFile
	 * @param sbt
	 * @throws IOException
	 */
	public static void exportSBT(File destinationFile, SBTFile sbt) throws IOException {
		BufferedWriter sbtExport = new BufferedWriter(new FileWriter(destinationFile));
		sbtExport.write(sbt.toXMLString());
		sbtExport.close();
	}

	/**
	 * Imports only the Header of an *.sbt-File 
	 * @param sourceFile
	 * @return SBTHeader of the source file
	 * @throws IOException
	 */
	public static SBTHeader importSBTHeader(File sourceFile) throws IOException {

		BufferedReader sbtImport = new BufferedReader(new FileReader(sourceFile));
		String input;
		boolean head = false, stop = false, nameFlag = false, creationdate = false, lastupdate = false;
		String name = null;
		Date creationDate = null, lastUpdate = null;

		while ((input = sbtImport.readLine()) != null && !stop) {
			StringTokenizer cruncher = new StringTokenizer(input, "<>");
			while (cruncher.hasMoreTokens() && !stop) {
				String piece = cruncher.nextToken();
				if (piece.equals("/head")) {
					head = false;
					stop = true;
				} else if (piece.equals("/name") && head) {
					nameFlag = false;
				} else if (piece.equals("/creationdate") && head) {
					creationdate = false;
				} else if (piece.equals("/lastupdate") && head) {
					lastupdate = false;
				}

				if (nameFlag) {
					name = piece;
				} else if (creationdate) {
					creationDate = Utils.formattedStringToDate(piece);
				} else if (lastupdate) {
					lastUpdate = Utils.formattedStringToDate(piece);
				}

				if (piece.equals("head")) {
					head = true;
				} else if (piece.equals("name") && head) {
					nameFlag = true;
				} else if (piece.equals("creationdate") && head) {
					creationdate = true;
				} else if (piece.equals("lastupdate") && head) {
					lastupdate = true;
				}

			}
		}
		sbtImport.close();
		return new SBTHeader(creationDate, lastUpdate, name);
	}

	/**
	 * Imports only the data of an *.sbt-File
	 * @param sourceFile
	 * @return SBTData of the source file
	 * @throws IOException
	 */
	public static SBTData importSBTData(File sourceFile) throws IOException {

		BufferedReader sbtImport = new BufferedReader(new FileReader(sourceFile));

		ArrayList<Point> importedPoints = new ArrayList<Point>();
		double x = 0;
		double y = 0;

		boolean data = false, navPoint = false, xFlag = false, yFlag = false, stop = false;
		String input = null;

		while ((input = sbtImport.readLine()) != null && !stop) {

			StringTokenizer cruncher = new StringTokenizer(input, "<>");

			while (cruncher.hasMoreTokens() && !stop) {

				String piece = cruncher.nextToken();

				if (piece.equals("/data")) {
					data = false;
					stop = true;
				} else if (piece.equals("/navpoint") && data) {
					importedPoints.add(new Point(x, y));
					navPoint = false;
				} else if (piece.equals("/x") && navPoint) {
					xFlag = false;
				} else if (piece.equals("/y") && navPoint) {
					yFlag = false;
				}

				if (xFlag) {
					x = Double.parseDouble(piece);
				} else if (yFlag) {
					y = Double.parseDouble(piece);
				}

				if (piece.equals("data")) {
					data = true;
				} else if (piece.equals("navpoint") && data) {
					navPoint = true;
				} else if (piece.equals("x") && navPoint) {
					xFlag = true;
				} else if (piece.equals("y") && navPoint) {
					yFlag = true;
				}

			}

		}
		sbtImport.close();
		return new SBTData(new Track(importedPoints));
	}

	/**
	 * Imports an SBTFile
	 * @param sourceFile
	 * @return
	 * @throws IOException
	 */
	public static SBTFile importSBTFile(File sourceFile) throws IOException {
		return new SBTFile(importSBTHeader(sourceFile), importSBTData(sourceFile));
	}
}
