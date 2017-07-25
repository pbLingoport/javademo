package com.lingoport.demo.java.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Encoding {
	
	// Encoding possibilities  
	public static final String ASCII = "ASCII";  //$NON-NLS-L$ 
	public static final String UTF8 = "UTF-8";   //$NON-NLS-L$
	public static final String UTF16 = "UTF-16"; //$NON-NLS-L$

	/*
	 * Write out a string to a file using a specified encoding
	 */
	public static void writeOutput(String filename, String str, String encoding) {

		try {
			FileOutputStream fos = new FileOutputStream(filename);
			Writer out = new OutputStreamWriter(fos, encoding);
			out.write(str);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Read in a string from a file using a specified encoding
	 */
	public static String readInput(String filename, String encoding) {

		StringBuffer buffer = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(filename);
			InputStreamReader isr = new InputStreamReader(fis, encoding);
			Reader in = new BufferedReader(isr);
			int ch;
			while ((ch = in.read()) > -1) {
				buffer.append((char) ch);
			}
			in.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
