package com.lingoport.demo.java.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * For the demonstration of Globalyzer, use the following configuration for String externalization
 *  Class name: I18nUtils
 *  Package : com.lingoport.demo.java.utils
 *  Properties filename: com.lingoport.demo.java.resources.exampleResources.properties
 *  
 *  
 *  Globalyzer finds references to Locale and flags them: in this demo, those references are fine, so 
 *  the issues should be ignored.
 *  
 *  
 * @author Olivier
 *
 */

public class I18nUtils {
	
    static ResourceBundle resources = null;
	private static Locale locale = new Locale("en", "US");  
	
	
	public static String getString(String key) {
		return getResourceBundle().getString(key);
	}
	
	/**
	 * gets the current locale for the application
	 * @return
	 */
	public static Locale getLocale() {
		return locale;	
	}
	
	public static void setLocale(Locale locale) {   
		I18nUtils.locale = locale;
		
	}
	
	static ResourceBundle getResourceBundle() {
		
		if (resources != null) {
			return resources;
		}
     	
		try {
//			resources = ResourceBundle.getBundle("com/lingoport/demo/java/resources.exampleResources", locale);
			resources = newBundle("com/lingoport/demo/java/resources/exampleResources",locale);
		} catch (Exception mre) {
			mre.printStackTrace();
			System.exit(1);
		}
		
		return resources;

	}
	
    public static ResourceBundle newBundle (String baseName, Locale locale)
        throws IllegalAccessException, InstantiationException, IOException {
    // The below is a copy of the default implementation.
    String bundleName = toBundleName(baseName, locale);
    String resourceName = toResourceName(bundleName, "properties");
    ResourceBundle bundle = null;
    InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourceName);
    if (stream != null) {
        try {
            // Only this line is changed to make it to read properties files as UTF-8.
            bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
        } finally {
            stream.close();
        }
    }
    return bundle;
}

	private static String toResourceName(String bundleName, String string) {
		return bundleName + "." + string;
	}

	private static String toBundleName(String baseName, Locale locale) {
		if (locale.equals(new Locale("en", "US"))) 
			return baseName;
		else
			return baseName +"_" + locale.toString();
	}

}
