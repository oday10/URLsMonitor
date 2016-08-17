package utils;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptionUtils {
	private static final String DEFAULT_SHA1_FOR_EXCEPTION_GETTING_CONTENT = "EXCEPTION_GETTING_CONTENT";

	/**
	 * This function calculates the SHA-1 encoding and returns a HEX representation of the encoded code.
	 * @param content - the content to calculate the encode for.
	 * @return the encoded key.
	 */
	public static String calculateSHA_1(String content){
		if(content != null){
			return DigestUtils.sha1Hex(content);
		}
		return DEFAULT_SHA1_FOR_EXCEPTION_GETTING_CONTENT;
	}
}
