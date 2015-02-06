package at.htl.smartrobot.server;

import java.awt.Desktop;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SmartServerCLI {

	private static final String defaultRobotIP = "192.168.88.252";
	private static final int defaultRobotPort = 50001;

	public static void main(String[] args) {
		try {
			boolean exit = false;
			String robotIP = defaultRobotIP;
			int robotPort = defaultRobotPort;
			SmartServer mServer = new SmartServer(robotIP, robotPort);
			Scanner scn = new Scanner(System.in);
			System.out.println("--- Welcome to SmartServer V1.5---");
			System.out.println("Local IP: " + Inet4Address.getLocalHost().getHostAddress() + ":" + mServer.getPort());
			System.out.println("Robot IP: " + mServer.getRobotAddress() + ":" + mServer.getRobotPort());
			System.out.println("Logfile: " + mServer.log.getLogFilePath());
			System.out.println("----------------------------------");
			showHelpText();

			while (!exit) {
				switch (scn.nextLine()) {
				case "s":
					if (!mServer.isListening()) {
						mServer.startListening();
						System.out.println("SmartServer running on port: " + mServer.getPort());
					} else {
						System.out.println("SmartServer already running on port: " + mServer.getPort());
					}
					break;
				case "t":
					if (mServer.isListening()) {
						mServer.stopListening();
						System.out.println("SmartServer stopped");
					} else {
						System.out.println("SmartServer was not running!");
					}
					break;
				case "c":
					boolean stoppedListening = false;
					if (mServer.isListening()) {
						mServer.stopListening();
						stoppedListening = true;
						System.out.println("SmartServer stopped");
					}
					System.out.println("Please enter the new IP-address of the robot");
					robotIP = scn.nextLine();
					mServer = new SmartServer(robotIP, robotPort);
					System.out.println("IP-address changed");
					if (stoppedListening) {
						mServer.startListening();
						System.out.println("SmartServer running on port: " + mServer.getPort());
						stoppedListening = false;
					}
					break;
				case "p":
					stoppedListening = false;
					if (mServer.isListening()) {
						mServer.stopListening();
						stoppedListening = true;
						System.out.println("SmartServer stopped");
					}
					System.out.println("Please enter new portnumber of the robot");
					robotPort = scn.nextInt();
					mServer = new SmartServer(robotIP, robotPort);
					System.out.println("Port changed");
					if (stoppedListening) {
						mServer.startListening();
						System.out.println("SmartServer running on port: " + mServer.getPort());
						stoppedListening = false;
					}
					break;
				case "l":
					Desktop.getDesktop().open(mServer.log.getLogFile());
					//------------------------
					Runtime.getRuntime().exec("nano "+mServer.log.getLogFilePath()); //Diese zeile bitte auf raspberry testen
					//------------------------
					break;
				case "f":
					System.out.println("Please enter the new absolute or relative file path");
					mServer.setLogFile(scn.nextLine());
					System.out.println("Log file changed");
					break;
				case "i":
					System.out.println("---SmartServer V1.5 system information---");
					System.out.println(mServer.isListening() ? "SmartServer is currently running" : "SmartServer is currently stopped");
					System.out.println("Local IP: " + Inet4Address.getLocalHost().getHostAddress() + ":" + mServer.getPort());
					System.out.println("Robot IP: " + mServer.getRobotAddress() + ":" + mServer.getRobotPort());
					System.out.println("Logfile: " + mServer.log.getLogFilePath());
					System.out.println("-----------------------------------------");
					break;
				case "e":
					exit = true;
					if (mServer.isListening()) {
						mServer.stopListening();
						System.out.println("SmartServer stopped");
					}
					mServer.socket.close();
					mServer.log.close();
					scn.close();
					System.out.println("Exit programm");
					break;
				case "h":
				default:
					showHelpText();
					break;
				}
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void showHelpText() {
		System.out.println("Try following commands: \n" + "s - start server \n" + "t - terminate server \n" + "c - change robot ip \n"
				+ "p - change robot port\n" + "l - open log file\n" + "f - change log file\n" + "i - show system information\n" + "e - exit programm \n"
				+ "h - show help");
	}

}
