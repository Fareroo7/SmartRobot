package at.htl.smartrobot.server.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {

	private File logFile=new File("./log.txt"); 
	
	public Logger(){
	}
	
	public Logger(String logFilePath){
		logFile=new File(logFilePath);
	}
	
	public String getLogFilePath(){
		return logFile.getAbsolutePath();
	}
	
	public File getLogFile(){
		return logFile;
	}
	
	public void write(String text){
		try {
			BufferedWriter log = new BufferedWriter(new FileWriter(logFile,true));
			log.write("["+new Date()+"] - " + text);
			log.newLine();
			log.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
