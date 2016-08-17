package utils;

import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesUtils {
	

	/**
	 * This function returns an Object that represents the value of the given key.
	 * If the given key was not found, it returns the default value object.
	 * @param config - the configurations object.
	 * @param property - the property key.
	 * @param defaultValue - the default value to return if the given key was not found.
	 * @return
	 */
	public static Object getPropertyWithDefault(PropertiesConfiguration config, String property, Object defaultValue){
		
		if(config != null){
			Object retVal = config.getProperty(property);
			if(retVal != null){
				return retVal;
			}
		}
		return defaultValue;
	}
}
