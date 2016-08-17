package utils;

import utils.ProtocolUtils.Protocol;
import main.Main;

/**
 * This class represents a file URL with IP (or *).
 * @author Oday
 *
 */
public class URLFile {
	private String originalURL;
	private String ip;
	private Protocol protocol;
	private String sha1;
	private String url;
	//I do not need the content, I just need the SHA-1, space optimization.
	
	/**
	 * URLFile Constructor.
	 * @param originalURL - File url.
	 * @param ip - File IP.
	 */
	public URLFile(String originalURL, String ip){
		
		this.originalURL = originalURL;
		this.ip = ip;
		this.protocol = ProtocolUtils.getProtocol(this.originalURL);
		if(!ip.equals("*")){
			this.url = ProtocolUtils.calculateSpecificURL(originalURL, ip);
		}else{
			this.url = this.originalURL;
		}
		this.sha1 = URLFileUtils.getContentAndCalculateCode(this);
		Main.log.logMessage("URLFile " + this.toString() + " Was Created.");
	}

	/**
	 * 
	 * @return originalURL
	 */
	public String getOriginalURL() {
		return originalURL;
	}

	/**
	 * Set originalURL.
	 * @param originalURL
	 */
	public void setOriginalURL(String originalURL) {
		this.originalURL = originalURL;
	}

	/**
	 * 
	 * @return ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Set ip.
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 *
	 * @return protocol
	 */
	public Protocol getProtocol() {
		return protocol;
	}

	/**
	 * Set protocol.
	 * @param protocol
	 */
	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	/**
	 * 
	 * @return sha1.
	 */
	public String getSha1() {
		return sha1;
	}

	/**
	 * Set sha1.
	 * @param sha1
	 */
	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}

	/**
	 * 
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Set url.
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * Returns a string that represents the URLFile (Url and IP).
	 */
	public String toString(){
		return this.originalURL + " " + this.ip;
	}
	
	

}
