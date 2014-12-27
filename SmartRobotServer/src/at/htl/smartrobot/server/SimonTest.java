package at.htl.smartrobot.server;

import java.util.Arrays;
import java.util.Scanner;

import at.htl.smartrobot.server.utils.ByteUtils;
import at.htl.smartrobot.server.utils.Receiver;
import at.htl.smartrobot.server.utils.UDPReceiveEvent;
import at.htl.smartrobot.server.utils.UDPReceiveListener;

public class SimonTest implements UDPReceiveListener {
	
	public static Receiver mReceiver;
	public static int port = 50010;
	public static int packetsize = 4;
	public static Scanner scn;
	public static boolean run = true;
	public static boolean listening = false;
	public final static String help = "h -> Show this help.\n"
			+ "port [INT] -> set Port.\n"
			+ "size [INT] -> set package size.\n"
			+ "start, s -> start listening.\n"
			+ "stop, t -> stop listening.\n"
			+ "exit, q -> exit programm."; 
	
	public static int counter = 0;
	public static long startTimestamp;
	public static long stopTimestamp;
	
	public static void main(String[] args) {
		scn = new Scanner(System.in);
		
		System.out.println("Time sync. V1.0\n"
				+ "Type h for help.");
		System.out.println(help);
		while(run){
			String input;
			while((input = scn.nextLine()) != null){
				String array[] = input.split(" ");
				if(array.length == 1){
					if(array[0].equalsIgnoreCase("start") || array[0].equalsIgnoreCase("s") ){
						if(!listening){
							mReceiver = new Receiver(port, packetsize);
							mReceiver.addUDPReceiveListener(new SimonTest());
							mReceiver.start();
							startTimestamp = System.nanoTime();
							listening = true;
							System.out.println("Start Listenting at Port " + mReceiver.getPort());
						} else {
							System.out.println("Already Listening at Port " + mReceiver.getPort());
						}
					} else if(array[0].equalsIgnoreCase("stop") || array[0].equalsIgnoreCase("t")){
						if(listening){
							mReceiver.interrupt();
							stopTimestamp = System.nanoTime();
							listening = false;
							System.out.println("Stop Listening!");
							System.out.println("Received Packages: " + counter + "\n"
									+ "Listening-Time: " + ((double)(stopTimestamp - startTimestamp) / 1000000000) + "s");
							counter = 0;
						} else {
							System.out.println("Not Listening anymore.\n");
						}
					} else if(array[0].equalsIgnoreCase("exit") || array[0].equalsIgnoreCase("q")){
						System.out.println("Exit Programm!");
						System.exit(0);
					} else {
						System.out.println("Command not found!\nType h for help.");
					}
				} else {
					if(array[0].equalsIgnoreCase("port")){
						port = Integer.parseInt(array[1]);
						System.out.println("Set Port to " + port);
					}else if(array[0].equalsIgnoreCase("size")){
						packetsize = Integer.parseInt(array[1]);
						System.out.println("Set Port to " + packetsize);
					} else {
						System.out.println("Command not found!\nType h for help.");
					}
				}
					
			}
		}
		
	}

	@Override
	public void onReceive(UDPReceiveEvent e) {
		long t = ByteUtils.bytesToLong(e.getUdpPacket().getData());
		System.out.println(e.getTimestamp() - t);
//		System.out.println("Packet:\t" + e.getUdpPacket().getAddress() + ":" + e.getUdpPacket().getPort() + "\t" + Arrays.toString(e.getUdpPacket().getData()) + "\t" + e.getTimestamp());
		counter++;
	}
	
}
