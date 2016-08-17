package utils;

import io.HTTPHandler;
import io.HTTPSHandler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;


import main.Main;

public class ProtocolUtils {
	
	public static final int HTTP_PORT = 80;
	public static final int HTTPS_PORT = 443;
	public static enum Protocol {HTTP, HTTPS};

	/**
	 * This function takes a url and ip and replaces the domain with the IP.
	 * @param originalURL
	 * @param ip
	 * @return
	 */
	public static String calculateSpecificURL(String originalURL, String ip) {
		String newURL = originalURL;
		try {
			URL urlObject = new URL(originalURL);
			String protocol = urlObject.getProtocol();
			int port = (protocol.equals("http"))? HTTP_PORT: HTTPS_PORT;
			newURL = new URL(protocol, ip, port, urlObject.getPath()).toString();
		} catch (MalformedURLException e) {
			Main.log.logMessage("An Error Encountered While Trying To calculateSpecificURL, Got IOException, will use the originalURL: " + originalURL + ".");
			return originalURL;
		}
		return newURL;
	}
	

	/**
	 * The function returns the protocol of the given url.
	 * @param url
	 * @return
	 */
	public static Protocol getProtocol(String url){
		URL urlObject;
		try {
			urlObject = new URL(url);
		} catch (MalformedURLException e) {
			Main.log.logMessage("URL is Malformed in URLFileUtils.getProtocol");
			return null;
		}
		String protocol = urlObject.getProtocol();
		return (protocol.equals("http"))? Protocol.HTTP: Protocol.HTTPS;
	}
	
	
	/**
	 * This function returns the content of the given url.
	 * @param urlFile
	 * @return
	 */
	public static String getResponseContent(URLFile urlFile){
		String urlString = urlFile.getUrl();
		if(urlFile.getProtocol() == Protocol.HTTPS){
			return HTTPSHandler.getResponseContent(urlString);
		}else{
			return HTTPHandler.getResponseContent(urlString);
		}
	}
	
	
	
	/**
	 * For secure certificate.
	 * @param baseClient
	 * @return
	 */
	public static HttpClient wrapClient(HttpClient baseClient) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] xcs,
						String string) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			
			X509HostnameVerifier verifier = new X509HostnameVerifier() {

				//@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}

				//@Override
				public void verify(String arg0, SSLSocket arg1) throws IOException {
					
				}

				//@Override
				public void verify(String arg0, X509Certificate arg1)
						throws SSLException {
					
				}

				//@Override
				public void verify(String arg0, String[] arg1, String[] arg2)
						throws SSLException {
					
				}
				 

	        };
			
	        ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(verifier);
            ClientConnectionManager ccm = baseClient.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, HTTPS_PORT));
            return new DefaultHttpClient(ccm, baseClient.getParams());
            
		} catch (Exception ex) {
			Main.log.logMessage("Could Not Wrap HttpClient For HTTPS Request.");
			return baseClient;
		}
	}
	

	
}
