package io;

import main.Main;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import utils.ProtocolUtils;



/**
 * HTTPSHandler Class.
 * @author Oday
 *
 */
public class HTTPSHandler {
	
	/**
	 * This function prepares the HttpClient and calls the ProtocolHandler.getResponseContent
	 * in order to get the response body of the given url.
	 * @param urlString
	 * @return the response body of the given url.
	 */
	public static String getResponseContent(String urlString){
		
		Main.log.logMessage("Sending HTTPS Request To " + urlString);
		
		HttpClient httpclient = new DefaultHttpClient();
		httpclient = ProtocolUtils.wrapClient(httpclient);
		
		String content =  ProtocolHandler.getResponseContent(urlString, httpclient);
		
		Main.log.logMessage("Finished Sending HTTPS request to " + urlString);
		
		return content;

	}
	
}
