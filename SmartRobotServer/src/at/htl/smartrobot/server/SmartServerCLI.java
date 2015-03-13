package at.htl.smartrobot.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SmartServerCLI {

	private static final String defaultRobotIP = "192.168.88.252";
	private static final int defaultRobotPort = 50001;

	public static void main(String[] args) {
		boolean exit = false;
		String robotIP = defaultRobotIP;
		int robotPort = defaultRobotPort;
		SmartServer mServer = new SmartServer(robotIP, robotPort);
		Scanner scn = new Scanner(System.in);
		try {
			System.out.println("--- Welcome to SmartServer Vs---");
			System.out.println("Local IP: " + Inet4Address.getLocalHost().getHostAddress() + ":" + mServer.getPort());
			System.out.println("Robot IP: " + mServer.getRobotAddress() + ":" + mServer.getRobotPort());
			System.out.println("Logfile: " + mServer.log.getLogFilePath());
			System.out.println("----------------------------------");
			showHelpText();

			while (!exit) {
				switch (scn.nextLine().toLowerCase()) {
				case "s":
					if (!mServer.isRunning()) {
						mServer.startService();
						System.out.println("SmartServer running on port: " + mServer.getPort());
//						mServer.log.write("SmartServer started");
					} else {
						System.out.println("SmartServer already running on port: " + mServer.getPort());
					}
					break;
				case "t":
					if (mServer.isRunning()) {
						mServer.stopService();
						System.out.println("SmartServer stopped");
//						mServer.log.write("SmartServer stopped");
					} else {
						System.out.println("SmartServer was not running!");
					}
					break;
				case "c":
					boolean stoppedListening = false;
					if (mServer.isRunning()) {
						mServer.stopService();
						stoppedListening = true;
						System.out.println("SmartServer stopped");
//						mServer.log.write("SmartServer stopped");
					}
					System.out.println("Please enter the new IP-address of the robot");
					robotIP = scn.nextLine();
					mServer = new SmartServer(robotIP, robotPort);
					System.out.println("IP-address changed");
//					mServer.log.write("Robot IP changed to " + robotIP);
					if (stoppedListening) {
						mServer.startService();
						System.out.println("SmartServer running on port: " + mServer.getPort());
//						mServer.log.write("SmartServer started");
						stoppedListening = false;
					}
					break;
				case "p":
					stoppedListening = false;
					if (mServer.isRunning()) {
						mServer.stopService();
						stoppedListening = true;
						System.out.println("SmartServer stopped");
//						mServer.log.write("SmartServer stopped");
					}
					System.out.println("Please enter new portnumber of the robot");
					robotPort = scn.nextInt();
					mServer = new SmartServer(robotIP, robotPort);
					System.out.println("Port changed");
//					mServer.log.write("Robot port changed to " + robotPort);
					if (stoppedListening) {
						mServer.startService();
						System.out.println("SmartServer running on port: " + mServer.getPort());
//						mServer.log.write("SmartServer started");
						stoppedListening = false;
					}
					break;
				case "l":
					showTxtFile(mServer.log.getLogFile());
					// Desktop.getDesktop().open(mServer.log.getLogFile());
					// ------------------------
					// Runtime.getRuntime().exec("nano "+mServer.log.getLogFilePath());
					// //Diese zeile bitte auf raspberry testen
					// ------------------------
					break;
				case "d":
					mServer.log.deleteLogFile();
					System.out.println("The logfile has been deleted");
					break;
				case "f":
					System.out.println("Please enter the new absolute or relative file path");
//					mServer.log.write("Change logfile");
					mServer.setLogFile(scn.nextLine());
					System.out.println("Log file changed");
					break;
				case "i":
					System.out.println("---SmartServer V1.5 system information---");
					System.out.println(mServer.isRunning() ? "SmartServer is currently running" : "SmartServer is currently stopped");
					System.out.println("Local IP: " + Inet4Address.getLocalHost().getHostAddress() + ":" + mServer.getPort());
					System.out.println("Robot IP: " + mServer.getRobotAddress() + ":" + mServer.getRobotPort());
					System.out.println("Logfile: " + mServer.log.getLogFilePath());
					System.out.println("-----------------------------------------");
					break;
				case "o":
					mServer.testOutput();
					System.out.println("Ouput testsignal at pin "+mServer.getRasPiPin());
					break;
				case "e":
					exit = true;
					if (mServer.isRunning()) {
						mServer.stopService();
						System.out.println("SmartServer stopped");
						mServer.log.write("SmartServer stopped");
					}
					scn.close();
//					mServer.log.write("SmartServer shut down");
					System.out.println("Exit programm");
					break;
				case "h":
				default:
					showHelpText();
					break;
				}
			}

		} catch (UnknownHostException e) {
			mServer.log.write(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void showHelpText() {
		System.out.println("Try following commands: \n" + "s - start server \n" + "t - terminate server \n" + "c - change robot ip \n"
				+ "p - change robot port\n" + "l - open log file\n" + "d - delete log file\n" + "f - change log file\n" + "i - show system information\n"
				+ "o - Output a testsignal from Raspberry Pi \n" + "e - exit programm \n" + "h - show help");
	}

	private static boolean showTxtFile(File txt) {
		try {
			BufferedReader bw = new BufferedReader(new FileReader(txt));
			String input;
			System.out.println("--- SmartServer Logfile: " + txt.getName() + "---");
			System.out.println("Path: " + txt.getAbsolutePath());
			int rowNumber = 0;
			while ((input = bw.readLine()) != null) {
				rowNumber++;
				System.out.println(rowNumber + ": " + input);
			}
			bw.close();
			System.out.println("---------------------------------------------");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
