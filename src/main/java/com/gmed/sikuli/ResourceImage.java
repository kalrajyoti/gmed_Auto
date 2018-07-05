package com.gmed.sikuli;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ResourceImage {
	/** Logger to log the ResourceImage log messages */
	private static Logger logger                             = LogManager.getLogger(ResourceImage.class);
	
	public static String named(String imageName){
		return System.getProperty("user.dir")+"\\Images\\"+imageName+".png";	
	}
}
