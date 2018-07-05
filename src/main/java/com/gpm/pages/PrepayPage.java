package com.gpm.pages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.pages.AppointmentPage;
import com.gmed.pages.ConfigurationPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;

public class PrepayPage extends BaseAbstractPage{
	/** Logger to log the PrepayPage log messages */
	private static Logger logger                                    = LogManager.getLogger(PrepayPage.class);
	public static By prepayAmount			                        = By.id("txtAmount_FormattedDecimalTextBox");
	public static By appointmentDropDown			                = By.id("txtAppointmentDropDown");
	public static By prepayQueue                                    = By.xpath(".//table[@id='radGridPrepay_ctl00']/tbody/tr");	
	/**
	 * This method is used to click on new button to create any appointment in Scheduler module 
	 * 
	 */
	public void clickOnNewButtonInPrePay(){
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.PREPAY);
		sleep(3000);	
	}
	/**
	 * This method is used to verify Insurance Payment and Adjustment  Page is opened
	 */
	public boolean verifyPrepayPage(){
		boolean isPrepayTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.PREPAYINSIDE);
		String tiitle = SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Prepay (estimates)")){
			System.out.println("Prepay (estimates) page is opened");
			isPrepayTitle=true;
		}
		return isPrepayTitle;
	}
	/**
	 * This method is used to switch into Prepay inside  frame
	 */
	public void switchToPrepayInsideFrame(){
		SeleniumUtil.switchToParentFrame(Frames.PREPAYINSIDE);
	}
	/**
	 * This method is used to enter the prePay Amount
	 */
	public void clickOnPrepayAmount(){
		SeleniumUtil.getElementWithFluentWait(prepayAmount).sendKeys("400");
	}
	/**
	 * This method is used to select the appointment for the prePay Amount
	 */
	public void selectAppintment(){
		SeleniumUtil.getElementWithFluentWait(appointmentDropDown).click();
		SeleniumUtil.waitForProgressBar(Frames.PREPAYINSIDE);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to switch into Prepay Main  frame
	 */
	public void switchToPrepayFrame(){
		SeleniumUtil.switchToParentFrame(Frames.PREPAY);
	}
	/**
	 * This method is used to verify prepay queue 
	 */
	public boolean verifyPrepayQueue(){
		boolean isPrepayQueuePresent=false;
		SeleniumUtil.switchToParentFrame(Frames.PREPAY);
		List<WebElement> totaltrrows = driver.findElements(prepayQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(ConstantsFile.patientlastname) && rowtext.contains(ConstantsFile.providercompletelastname)){
				System.out.println("prepay  rows is present");
				isPrepayQueuePresent=true;
				break;	
			}
		}
		return isPrepayQueuePresent;
	}
}
