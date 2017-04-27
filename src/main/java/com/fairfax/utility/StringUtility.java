package com.fairfax.utility;

/**
 * Common String utilities
 * @author dileep
 *
 */
public class StringUtility {
	/**
	 * Method to check if a given string is empty
	 * @param aString
	 * @return
	 */
	public static boolean isStringEmpty (String aString) {
		if (aString == null || "".equalsIgnoreCase(aString.trim())) {
			return true;
		}
		
		return false;
	}
}
