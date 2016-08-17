package main;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import utils.EncryptionUtils;
import utils.URLsMonitorUtils;
import utils.PropertiesUtils;
import utils.ProtocolUtils;
import utils.URLFile;
import utils.URLFileUtils;
import io.TXTReader;

/**
 * URLsMonitor Class.
 * @author Oday
 *
 */
public class URLsMonitor extends TimerTask {
	
	private static URLsMonitor instance = null;
	private static Double intervalTimeInMinutes;
	private static int fromMinutesToMilliSeconds = 60000;
	private String urlsFilePath;
	LinkedList<URLFile> urlFiles;

	/*
	 * URLsMonitor Private Constructor.
	 * @param filesPaths
	 */
	private URLsMonitor(){
		
		Main.log.logMessage("Start Creating URLsMonitor Instance..");
		
		URLsMonitor.intervalTimeInMinutes = new Double((String)PropertiesUtils.getPropertyWithDefault(Main.config, "intervalTimeCheckTimeInMinutes", "10"));//10 Minutes Default.
		this.urlsFilePath = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "urlsFilePath", "resources/urls.txt");
		
		
		Main.log.logMessage("URLsMonitor Resources Preparation..");
		
		LinkedList<String> lines = TXTReader.read(this.urlsFilePath);
		this.urlFiles = URLFileUtils.createURLFilesFromStrings(lines);
		
		Main.log.logMessage("URLsMonitor Resources Preparation Finished.");
		
		Main.log.logMessage("URLsMonitor Instance Was Created.");
	}
	
	
	/**
	 * URLsMonitor getInstance for singleton pattern.
	 * @return
	 */
	public static URLsMonitor getInstance(){
		if(instance == null){
			instance = new URLsMonitor();
		}
		return instance;
	}
	
	
	/**
	 * Start method that starts the monitoring.
	 */
	public void start(){
		Main.log.logMessage("Setting The Periodic Check..");
		Timer time = new Timer();
		time.schedule(this, 1, Double.valueOf(URLsMonitor.intervalTimeInMinutes * fromMinutesToMilliSeconds).longValue());
		Main.log.logMessage("Periodic Check Was Set To Run Every " + URLsMonitor.intervalTimeInMinutes + " Minutes");
	}
	
	/**
	 * This function represents a single run of monitoring files.
	 * This function will be called every X time, x can be declared in the config file.
	 * @param urlToURLFile
	 */
	public void run(){
		
		Main.log.logMessage("*********** URLsMonitor Periodic Check Started.. ***********");
		
		ListIterator<URLFile> listIterator = this.urlFiles.listIterator();
		
		while(listIterator.hasNext()){
			
	        URLFile urlFile = listIterator.next();
	        Main.log.logMessage("Checking " + urlFile.toString() + "..");
	        String currentContent = ProtocolUtils.getResponseContent(urlFile);
	        String currentSHA1 = EncryptionUtils.calculateSHA_1(currentContent);
	        
	        if(!currentSHA1.equals(urlFile.getSha1())){
	        	urlFile.setSha1(currentSHA1);
	        	Main.log.logMessage("############# File " + urlFile.toString() + " Was Changed. #############");
	        	URLsMonitorUtils.sendEmailNotification(urlFile);  	
	        }
		}
		
		Main.log.logMessage("*********** URLsMonitor Periodic Check Finished. ***********\n\n");
	}
	
}
