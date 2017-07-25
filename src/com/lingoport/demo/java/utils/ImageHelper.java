package com.lingoport.demo.java.utils;

import javax.swing.ImageIcon;

public class ImageHelper {
	
    /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ImageHelper.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);  //$NON-NLS-L$ 
            return null;
        }
    }


}
