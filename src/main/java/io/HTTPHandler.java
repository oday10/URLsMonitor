package io;

import main.Main;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;



/**
 * HTTPHandler Class.
 * @author Oday
 *
 */
public class HTTPHandler {
	
	/**
	 * This function calls the ProtocolHandler.getResponseContent function with a default HttpClient.
	 * @param urlString
	 * @return the response body of the given url.
	 */
	public static String getResponseContent(String urlString){
		
		Main.log.logMessage("Sending HTTP Request To " + urlString);
		
		HttpClient httpclient = new DefaultHttpClient();
		String content =  ProtocolHandler.getResponseContent(urlString, httpclient);
		
		Main.log.logMessage("Finished Sending HTTP request to " + urlString);
		
		return content;

	}
	
}
