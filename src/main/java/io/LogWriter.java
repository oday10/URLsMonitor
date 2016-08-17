package io;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents a log file object.
 * A singleton class, we need just one LogWriter.
 * @author Oday
 *
 */
public class LogWriter 
{
	private Logger logger;
	private static LogWriter instance = null;
	
	/*
	 * Private Constructor, for the matter of singleton.
	 * @param path - path of the log file.
	 */
	private LogWriter(String path){
		
		this.logger = Logger.getLogger("URLsMonitorLog");
		
        // Configure Logger with handler and formatter.
		FileHandler fh = null;
		
		try {
			fh = new FileHandler(path);
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);
		} catch (SecurityException e) {
			System.out.println("An Error Encountered While Trying To Create the Logger Object, Got SecurityException, Will Print To The Console.");
			this.logger = null;
		} catch (IOException e) {
			System.out.println("An Error Encountered While Trying To Create the Logger Object, Got IOException, Will Print To The Console.");
			this.logger = null;
		}  
        
	}
	
	/**
	 * This function returns the singleton object.
	 * @param path - path of the log.
	 * @return LogWriter object.
	 */
	public static LogWriter getInstance(String path){
		if(instance == null){
			instance = new LogWriter(path);
		}
		return instance;
	}
	
	/**
	 * This function logs message to the log.
	 * If the log is null, print to Console.
	 * @param message - message to log.
	 */
	public void logMessage(String message){
		
		if(this.logger != null){
	        this.logger.info(message + "\n");
		}else{
			System.out.println(message + "\n");
		}

	}
	
	
}
