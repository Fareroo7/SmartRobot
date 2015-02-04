package at.htl.smartrobot.server.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

	private File logFile=new File("./log.txt"); 
	private BufferedWriter log = null;
	
	public Logger(){
		try {
			log=new BufferedWriter(new FileWriter(logFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Logger(String logFilePath){
		logFile=new File(logFilePath);
		try {
			log=new BufferedWriter(new FileWriter(logFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(String text){
		if(log != null){
			try {
				log.write(text);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(){
		if(log != null){
			try {
				log.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
