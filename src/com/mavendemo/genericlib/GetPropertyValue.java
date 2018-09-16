package com.mavendemo.genericlib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GetPropertyValue {
	final static String finalPath=".\\config.properties";
	/**
	 * @description getValue()will read the data from getproperties.file
	 * @param key
 	 * @return String
	 */
	public static String getValue(String key)
	{
		String value=null;
		Properties prop=new Properties();

			try {
				prop.load(new FileInputStream(new File(finalPath)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		 value=prop.getProperty(key);
		
		
		return value;
		
}
}
