package io;

import main.Main;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import utils.PropertiesUtils;

public class EmailHandler {
	
	/**
	 * This function sends an email to specific address according to the parameters
	 * sent to it and some configuration value in the config file.
	 * @param to
	 * @param subject
	 * @param content
	 * @return True if the email was sent successfully, else false.
	 */
	public static boolean send(String to, String subject, String content){
		
		String userName = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "emailAddressToSendFrom", "default");
		String password = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "emailAddressPasswordToSendFrom", "default");
		String hostName = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "smtpServerHostName", "default");
		Integer portNumber = new Integer((String)PropertiesUtils.getPropertyWithDefault(Main.config, "smtpServerPortNumber", "587"));
		String sendToName = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "sendToName", "default");
		String sendFromName = (String) PropertiesUtils.getPropertyWithDefault(Main.config, "sendFromName", "default");

		SimpleEmail email = new SimpleEmail();
		try{
			email.setHostName(hostName);
			email.setSmtpPort(portNumber);
			email.setAuthenticator(new DefaultAuthenticator(userName, password));
			email.setSSLOnConnect(true);
			email.addTo(to, sendToName);
			email.setFrom(userName, sendFromName);
			email.setSubject(subject);
			email.setMsg(content);
			email.send();
			
		}catch(EmailException emailExc){
			Main.log.logMessage("An Error Encountered While Trying To Send The Email To " + to + ", Got EmailException.");
			return false;
		}
		Main.log.logMessage("An Email To " + to + " Was Sent Successfully.");
		return true;
	}
}
