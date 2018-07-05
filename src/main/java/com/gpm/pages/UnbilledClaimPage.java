package com.gpm.pages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;
import static com.gmed.helper.DriverFactory.driver;

public class UnbilledClaimPage extends BaseAbstractPage{
	/** Logger to log the UnbilledClaimPage log messages */
	private static Logger logger                   = LogManager.getLogger(UnbilledClaimPage.class);
	public static By submitButton                                   = By.id("btnSubmitAll_SpanBGColor");
	public static By professionalTab                                = By.id("tbPaperProfessional");
	public static By electroniclTab                                 = By.id("tbElectronic");
	public static By ConfigButton                                   = By.id("btnPrintConfig_SpanBGColor");
	public static By printButton                                    = By.id("btnPrintAll_SpanBGColor");
	public static By totalClaimsQueue                               = By.xpath(".//table[@id='radGridClaims_ctl00']/tbody/tr");
	public static By totalSentClaimsQueue                           = By.xpath(".//table[@id='radGridClaimFile_ctl00']/tbody/tr");
	/**
	 * This method is used to verify elecronic tab is opened
	 */
	public boolean verifyElecronicTab(){
		Boolean isSumitButtonPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.UNBILLED);
		isSumitButtonPresent =SeleniumUtil.getElementWithFluentWait(submitButton)!=null;
		if(isSumitButtonPresent){
			System.out.println("sumit button is present");
			isSumitButtonPresent=true;
		}
		return isSumitButtonPresent;
	}
	/**
	 * This method is used to click on  professional  tab 
	 */
	public void clickOnprofessionalTab() throws InterruptedException{
		sleep(9000);
		SeleniumUtil.getElementWithFluentWait(professionalTab).click();
		SeleniumUtil.waitForProgressBar(Frames.UNBILLED);
		sleep(9000);
	}
	/**
	 * This method is used to click on  Electronic  tab 
	 */
	public void clickOnElectronic(){
		sleep(2000);
		SeleniumUtil.getElementWithFluentWait(electroniclTab).click();
		SeleniumUtil.waitForProgressBar(Frames.UNBILLED);
		sleep(5000);
	}
	/**
	 * This method is used to verify CMS tab is opened
	 */
	public boolean verifyCMSTab(){
		boolean isPrintPresent=false;
		boolean isConfigButtonPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.UNBILLED);
		isPrintPresent =SeleniumUtil.getElementWithFluentWait(printButton)!=null;
		isConfigButtonPresent =SeleniumUtil.getElementWithFluentWait(ConfigButton)!=null;
		if(isPrintPresent && isConfigButtonPresent ){
			System.out.println("sumit button is present");
			isPrintPresent=true;
			isConfigButtonPresent=true;
		}
		return isPrintPresent;
	}
	/**
	 * This method is used to select unbilled row present in queue 
	 */
	public void selectUnBilledRow(String imagename) throws FindFailed, InterruptedException{
		List<WebElement> totaltrrows = driver.findElements(totalClaimsQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(ConstantsFile.patientlastname) && rowtext.contains(ConstantsFile.providerfirstname) && rowtext.contains("PP")){
				System.out.println("unbilled rows are present");
				SeleniumUtil.rightClick(irows);
				Thread.sleep(3000);
				SeleniumUtil.clickOnImageWitScreenInSikuli(imagename);
				Thread.sleep(3000);
				break;
			}
		}
	}
	/**
	 * This method is used to switch into unbilled frame
	 */
	public void switchToUnBilledFrame(){
		SeleniumUtil.switchToParentFrame(Frames.UNBILLED);
	}
	/**
	 * This method is used to click on summit claim button 
	 */
	public void summitClaim(){
		sleep(5000);
		SeleniumUtil.switchToParentFrame(Frames.UNBILLED);
		sleep(5000);
		List<WebElement> totaltrrows = driver.findElements(totalClaimsQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(ConstantsFile.patientlastname) && rowtext.contains(ConstantsFile.providerfirstname)  && rowtext.contains("PP") ){
				System.out.println("unbilled rows are present");
				irows.click();
				SeleniumUtil.waitForProgressBar(Frames.UNBILLED);
				break;	
			}
		}
		SeleniumUtil.getElementWithFluentWait(submitButton).click();
		SeleniumUtil.waitForProgressBar(Frames.UNBILLED);
	}
	/**
	 * This method is used to verify sent claim queue
	 */
	public boolean verifySentClaimQueue(){
		boolean isSentClaimQueuePresent=false;
		List<WebElement> totaltrrows = driver.findElements(totalSentClaimsQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(ConstantsFile.username)){
				System.out.println("sent claims rows are present");
				isSentClaimQueuePresent=true;
				break;	
			}
		}
		return isSentClaimQueuePresent;
	}
}
