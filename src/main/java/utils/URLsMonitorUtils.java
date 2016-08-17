package utils;

import io.EmailHandler;
import main.Main;

public class URLsMonitorUtils {
	
	/**
	 * This function sends an alert email to the monitoring admin with the information about the file.
	 * @param urlFile
	 * @return
	 */
	public static boolean sendEmailNotification(URLFile urlFile) {
		String to = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "emailAddressToSendTo", "oday.sayed@gmail.com");
		String subject = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "emailSubjectToSend", "Files Changed");
		String messageStart = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "emailMessageStart", "The following file was changed: ");
		return EmailHandler.send(to, subject, messageStart + " " + urlFile.toString());
	}
}
