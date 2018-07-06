package com.gmed.Frames;

import static com.gmed.helper.DriverFactory.driver;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gmed.base.BaseAbstractPage;
import com.gmed.base.BaseTestClass;


import com.gmed.utils.SeleniumUtil;

public class DynamicFramePage extends BaseTestClass{
	/** Logger to log the DynamicFramePage log messages */
	public static Logger logger                = LogManager.getLogger(DynamicFramePage.class);
	
	
	/**
	 * This method is used for switching into the dynamic panchart frame present in colonscopy service
	 * 
	 */
	public static void dynamicFrameForPanchart(){
		if(testName.equalsIgnoreCase("createFutureAppointForColonscopy") || (testName.equalsIgnoreCase("startFirstVisitService") || (testName.equalsIgnoreCase("startColonscopyService")|| (testName.equalsIgnoreCase("verifyFutureAppointmentInProfile"))))){
			SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
			driver.switchTo().parentFrame();
			driver.switchTo().parentFrame();
			final List<WebElement> iframes1 = driver.findElements(By.tagName("iframe"));
			System.out.println("Number of frames in a page :" + iframes1.size());
			logger.info("Number of frames in a page :" + iframes1.size());
			boolean isBeforeFrmpresent = false;
			String parentOfPanchart = "";
			for (WebElement iframe : iframes1) {
				String iframeid = "panScheduler_Frame";
				//String iframeid = "panChart_Frame";
				System.out.println("Frame name :" + iframe.getAttribute("id"));
				if(isBeforeFrmpresent){
					parentOfPanchart = iframe.getAttribute("id");
					break;
				}
				if (iframe.getAttribute("id").equals(iframeid)) {
					isBeforeFrmpresent = true;
				}
			}
			SeleniumUtil.switchToFrame(driver,parentOfPanchart);
		}
		else if(testName.equalsIgnoreCase("checkInService")){
			SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
			driver.switchTo().parentFrame();
			driver.switchTo().parentFrame();
			final List<WebElement> iframes1 = driver.findElements(By.tagName("iframe"));
			System.out.println("Number of frames in a page :" + iframes1.size());
			logger.info("Number of frames in a page :" + iframes1.size());
			boolean isBeforeFrmpresent = false;
			String parentOfPanchart = "";
			for (WebElement iframe : iframes1) {
				String iframeid = "panSuperbill_Frame";
				//String iframeid = "panChart_Frame";
				System.out.println("Frame name :" + iframe.getAttribute("id"));
				if(isBeforeFrmpresent){
					parentOfPanchart = iframe.getAttribute("id");
					break;
				}
				if (iframe.getAttribute("id").equals(iframeid)) {
					isBeforeFrmpresent = true;
				}
			}
			SeleniumUtil.switchToFrame(driver,parentOfPanchart);
		}
		else{
			SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
			driver.switchTo().parentFrame();
			driver.switchTo().parentFrame();
			final List<WebElement> iframes1 = driver.findElements(By.tagName("iframe"));
			System.out.println("Number of frames in a page :" + iframes1.size());
			boolean isBeforeFrmpresent = false;
			String parentOfPanchart = "";
			for (WebElement iframe : iframes1) {
				String iframeid = "panChart_Frame";
				System.out.println("Frame name :" + iframe.getAttribute("id"));
				if(isBeforeFrmpresent){
					parentOfPanchart = iframe.getAttribute("id");
					break;
				}
				if (iframe.getAttribute("id").equals(iframeid)) {
					isBeforeFrmpresent = true;
				}
			}
			SeleniumUtil.switchToFrame(driver,parentOfPanchart);
		}
	}
	/**
	 * This method is used for switching into the dynamic fraTree_Frame frame present in colonscopy service
	 * @throws Exception
	 */
	public static void switchtoFraFrame(){
		SeleniumUtil.switchToFrame(driver, "fraTree_Frame");
	}
	/**This method is used to dynamic frames for appointments 
	 * 
	 * 
	 */
	public void switchInDymFrameForAppointment(){
		final List<WebElement> iframes1 = driver.findElements(By.tagName("iframe"));
		System.out.println("Number of frames in a page :" + iframes1.size());
		boolean isBeforeFrmpresent = false;
		String parentOfPanchart = "";
		for (WebElement iframe : iframes1) {
			String iframeid = "panScheduler_Frame";
			System.out.println("Frame name :" + iframe.getAttribute("id"));
			if(isBeforeFrmpresent){
				parentOfPanchart = iframe.getAttribute("id");
				break;
			}
			if (iframe.getAttribute("id").equals(iframeid)) {
				isBeforeFrmpresent = true;
			}
		}
		SeleniumUtil.switchToFrame(driver,parentOfPanchart);
		DynamicFramePage.switchtoFraFrame();
	}
	public static void verifyDynamicFrameForInterview(){
		DynamicFramePage.dynamicFrameForPanchart();
		final List<WebElement> iframes2 = driver.findElements(By.tagName("iframe"));
		System.out.println("Number of frames in a page :" + iframes2.size());
		boolean isBeforeFrmpresent1 = false;
		String parentOffraNumber = "";
		for (WebElement iframe : iframes2) {
			String iframeid = "fraNumber_Frame";
			System.out.println("Frame name :" + iframe.getAttribute("id"));
			if(isBeforeFrmpresent1){
				parentOffraNumber = iframe.getAttribute("id");
				break;
			}
			if (iframe.getAttribute("id").equals(iframeid)) {
				isBeforeFrmpresent1 = true;
			}
		}
		SeleniumUtil.switchToFrame(driver,parentOffraNumber);
	}
	//To switch to panprofileframe
	public static void switchtopanProfileFrame(){
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
	}
	
	
}
