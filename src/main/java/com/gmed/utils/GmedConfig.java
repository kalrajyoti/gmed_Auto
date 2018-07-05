package com.gmed.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GmedConfig {

	private  static Properties propObj = null;
	private final static String fileName = "gmedconfig.properties";

	public GmedConfig(){

	}
	/**
	 * 
	 * @param configName-Name of configName mentioned in gmedconfig.properties file
	 * @return Value of key
	 */
	public static String getConfig(String configName) {
		if(propObj==null){
			init();
		}
		return propObj.getProperty(configName);
	}


	private static void init() {
		try {
			propObj= new Properties();
			propObj.load(new FileInputStream(new File(System.getProperty("user.dir")+ "/src/test/resources/" +fileName)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}

