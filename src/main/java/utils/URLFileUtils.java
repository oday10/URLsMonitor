package utils;


import java.util.LinkedList;
import java.util.ListIterator;

import main.Main;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.commons.validator.routines.UrlValidator;

/**
 * URLFileUtils Class.
 * @author Oday
 *
 */
public class URLFileUtils {
	
	private static final String URL_IP_SEPARATOR = " ";
	private static final int URL_INDEX = 0;
	private static final int IP_INDEX = 1;
	private static final String DEFAULT_IP_PATTERN = "*";
	

	/**
	 * 
	 * @param lines - The lines to create the objects from.
	 * @return LinkedList<URLFile>.
	 */
	public static LinkedList<URLFile> createURLFilesFromStrings(LinkedList<String> lines){
		
		Main.log.logMessage("Creating URLFiles..");
		
		LinkedList<URLFile> filesList = new LinkedList<URLFile>();
		ListIterator<String> listIterator = lines.listIterator();
		while(listIterator.hasNext()){
			String line = listIterator.next();
			Main.log.logMessage("Processing The Line " + line);
			
			//Assumption: that the URL is given in a url format, e.g %20 instead of white space.
			line = removeExtraSpaces(line);
			
			String[] tokens = line.split(URL_IP_SEPARATOR);
			
			if(tokens.length>1){
				String url = tokens[URL_INDEX];
				String ip = tokens[IP_INDEX];
				if(checkURLValidity(url) && (ip.equals(DEFAULT_IP_PATTERN) || checkIPValidity(ip))){
					filesList.add(new URLFile(url, ip));
				}else{
					Main.log.logMessage("URL Or IP Is Invalid, Skipping Processing The File For " + url + " " + ip);
				}
			}
		}
		
		Main.log.logMessage("Finished Creating URLFiles.");
		return filesList;
	}
	
	/**
	 * This function checks the validity of the IP.
	 * @param ip - To check validity.
	 * @return valid or not.
	 */
	public static boolean checkIPValidity(String ip) {
		InetAddressValidator validator = InetAddressValidator.getInstance();
		return validator.isValid(ip);
	}

	/**
	 * 
	 * @param url - To check validity.
	 * @return valid or not.
	 */
	public static boolean checkURLValidity(String url) {
		String[] schemes = {"http","https"};
		UrlValidator urlValidator = new UrlValidator(schemes);
		return urlValidator.isValid(url);
	}

	/**
	 * This function replaces all the continuous white-spaces with one white space.
	 * @param line - the line to change.
	 * @return a new line that does not contain any neighbor white spaces.
	 */
	public static String removeExtraSpaces(String line){
		return line.trim().replaceAll(" +", " ");
	}
	
	/**
	 * This function receives a urlFile and returns the SHA1 code of the content.
	 * @param urlFile
	 * @return
	 */
	public static String getContentAndCalculateCode(URLFile urlFile) {
		String content = ProtocolUtils.getResponseContent(urlFile);
		String code = EncryptionUtils.calculateSHA_1(content);
		return code;
	}
	

}









