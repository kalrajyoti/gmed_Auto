package com.gmed.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.stream.Stream;

public class StringUtility {

    /**
     * Checks that each element in a string array is contained in a string
     * @return true or false
     */
    public static boolean arrayElementsContainedInString(String array[], String string) {
        logger().info("Checking that " + array + " is contained in " + string);
        return Stream.of(array).allMatch(s -> StringUtils.containsIgnoreCase(string, s));
    }

    private static Logger logger() {
        
    	return LogManager.getLogger(StringUtility.class);
    	
    }

    /**
     * This method will verify if the Text is available in the String array or not 1920
     *
     * @return
     */
    public static boolean arrayContainsString(String str[], String str2) {
        return ArrayUtils.contains(str, str2);
    }

    /**
     * This method is used to verify if the arr1 contains all the text of arr2 irrespective of their indexes.
     */
    public static boolean arrayContainsAll(String[] compareTo, String[] discovered) {
    	return Stream.of(compareTo).allMatch(x-> Arrays.toString(discovered).contains(x.trim()));
    	
    }

    /**
     * This method is used to verify that the fullString contains each of the entries in commaSeparated, when split.
     * This helps verify a comma separated list regardless of order.
     */
    public static boolean stringContainsCsv(String fullString, String commaSeparated) {
        logger().info("Checking that each entry in " + commaSeparated + " is contained in " + fullString);
        return Stream.of(commaSeparated.split(",")).allMatch(fullString::contains);
    }
    /**
     * This method is used for replacing dynamic text present in xpath
     * @param xpathValue Actual xpath value
     * @param subtitutionValue Text which need to be substitute
     * @return xpath value 
     */
    public static By DynamicXpath(String xpathValue,String ...subtitutionValue){
 	   return By.xpath(String.format(xpathValue, subtitutionValue));
    }
    
    /**
     * This method is used to verify that the fullString contains each of the entries in space separated, when split.
     * This helps verify a space separated list regardless of order.
     */
    public static boolean stringContainsSpaceSeparatedCsv(String fullString, String spaceSeparated) {
        logger().info("Checking that each entry in " + spaceSeparated + " is contained in " + fullString);
        return Stream.of(spaceSeparated.split(" ")).allMatch(fullString::contains);
    }
}
