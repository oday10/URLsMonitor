package main;

import io.LogWriter;

import java.io.File;
import java.util.Date;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import utils.PropertiesUtils;

public class Main {
	
	private static final String PROPERTIES_FILE_NAME = "resources" + File.separator + "config.properties";
	public static PropertiesConfiguration config;
	public static LogWriter log;
	
	/**
	 * 
	 * @param args
	 * 
	 */
	public static void main(String[] args)
	{
		//Configurations file.
        config = new PropertiesConfiguration();
        try {
			config.load(PROPERTIES_FILE_NAME);
		} catch (ConfigurationException e) {
			System.out.println("Could not load the configurations file, will always use the default values.");
		}
        
        //Log file.
        String logDirectoryPath = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "logFileDirectoryPath", "logsDir");
        String logPrefix = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "outputLogFilePrefix", "Log-");
        String logSuffix = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "outputLogFileSuffix", ".txt");
        
        try{
            File logDir = new File(logDirectoryPath);
            if (!logDir.exists()) {
            	logDir.mkdirs();
            }
        }catch(Exception ex){
        	//Nothing to handle here, delegate it to the LogWriter.
        }
        
        String logFilePath = logDirectoryPath + File.separator + logPrefix + (new Date()).getTime() + logSuffix;
        Main.log = LogWriter.getInstance(logFilePath);
        
        Main.log.logMessage("Created LogWriter Object Successfully.");
        
        //URLsMonitor object.
		URLsMonitor urlsMonitor = URLsMonitor.getInstance();
		urlsMonitor.start();
		
	}
	
}




