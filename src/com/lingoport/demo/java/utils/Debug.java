package com.lingoport.demo.java.utils;

/**
 * A start class for a logger; the strings passed to this particular logger need to be ignored by the
 * filters, as they are intended to be consumed by English speaking support and engineering. 
 * 
 * @author Olivier
 *
 */

public class Debug {
	
	/**
	 * Log the string only if the debug flag is turned on.
	 * The strings passed in should *not* be externalized. A filter needs to be applied to this method.
	 * 
	 * @param logString
	 */
	public static void log (String logString) {
		// Do something with that string, like store it in the database with a date stamp and a user ID from the thread
		// for instance.
		System.out.println(logString);
	}

}
