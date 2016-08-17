package io;

import java.io.IOException;

import main.Main;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicHeader;


/**
 * HTTPHandler Class.
 * @author Oday
 *
 */
public class ProtocolHandler {
	
	/**
	 * 
	 * This function sends HTTP request in order to get the response body of the url.
	 * @param urlString - The url to get it's content.
	 * @param httpClient - The client to send the request via it.
	 * @return
	 */
	public static String getResponseContent(String urlString, HttpClient httpClient){
		
		HttpResponse response = null;
		String responseString = null;
		try {
			HttpGet httpGet = new HttpGet(urlString);
			httpGet.setHeader(new BasicHeader("Pragma", "no-cache"));
			httpGet.setHeader(new BasicHeader("Cache-Control", "no-cache"));
			response = httpClient.execute(httpGet);
			responseString = new BasicResponseHandler().handleResponse(response);
		} catch (ClientProtocolException e) {
			Main.log.logMessage("An Error Encountered While Trying To Send The Request, Got ClientProtocolException.");
		} catch (IOException e) {
			Main.log.logMessage("An Error Encountered While Trying To Send The Request, Got IOException.");
		}
		
		return responseString;
	}
	
}
