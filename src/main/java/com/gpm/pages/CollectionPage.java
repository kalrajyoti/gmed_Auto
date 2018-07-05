package com.gpm.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.pages.AppointmentPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;

import java.util.List;
public class CollectionPage extends BaseAbstractPage{
	/** Logger to log the CollectionPage log messages */
	private static Logger logger                   = LogManager.getLogger(CollectionPage.class);
	public static By collectionCheckbox            = By.id("chkSuppressCollections");
	public static By flagForCollectionButton       = By.id("btnFlagForCollections_SpanBGColor");
	public static By searchFilter                                   = By.id("txtSearchMore");
	public static By clearButton			                        = By.id("btnFiltersClear_Center");
	public static By patientDropDown                                = By.id("txtPatientDropDown");
	public static By expandButton			                        = By.id("radGridCollections_ctl00_ctl04_GECBtnExpandColumn");
	public static By totalCollectionQueue                           = By.xpath(".//table[@id='radGridCollections_ctl00']/tbody/tr");
	AppointmentPage app = new AppointmentPage();
	
	
	/**
	 * This method is used to verify supress collection should be unchecked
	 * 
	 */
	public boolean verifySuppressCollectionisUnchecked(){
		boolean isSupressCollectionChecked=false;
		String checkboxvalue=SeleniumUtil.getElementWithFluentWait(collectionCheckbox).getAttribute("stateName");
		System.out.println(checkboxvalue);
		if(checkboxvalue.equalsIgnoreCase("unchecked")){
			System.out.println("supress collection checkbox is unchecked");
			isSupressCollectionChecked=true;
		}
		return isSupressCollectionChecked;
	}
	/**
	 * This method is used to click on flag for collection button
	 * 
	 */
	public void clickOnFlagForCollection(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		SeleniumUtil.getElementWithFluentWait(flagForCollectionButton).click();
	}

public void searchPatientWithoutIns(){
	sleep(5000);
	SeleniumUtil.getElementWithFluentWait(searchFilter).click();
	SeleniumUtil.waitForProgressBar(Frames.COLLECTION);
	SeleniumUtil.getElementWithFluentWait(clearButton).click();
	SeleniumUtil.waitForProgressBar(Frames.COLLECTION);
	SeleniumUtil.getElementWithFluentWait(patientDropDown).click();
	SeleniumUtil.waitForProgressBar(Frames.COLLECTION);
	SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
	app.searchPatientNameWithoutIns();
}
/**
 * This method is used to switch into Collection Frame 
 */
public void switchToCollectionFrame(){
	SeleniumUtil.switchToParentFrame(Frames.COLLECTION);
}
/**
 * This method is used to click On expand Button present  in Collection Frame 
 */
public void clickOnExpand(){
	sleep(3000);
	SeleniumUtil.getElementWithFluentWait(expandButton).click();
	SeleniumUtil.waitForProgressBar(Frames.COLLECTION);
}
/**
 * This method is used to verify collection queue 
 */
public boolean verifyCollectionQueue(String menuName){
	//SeleniumUtil.switchToParentFrame(Frames.BILLED);
	sleep(3000);
	boolean isCollectionQueuePresent=false;
	List<WebElement> totaltrrows = driver.findElements(totalCollectionQueue);
	System.out.println(totaltrrows.size());
	for(WebElement irows:totaltrrows){
		String rowtext =irows.getText();
		System.out.println(rowtext);
		if(rowtext.contains(ConstantsFile.MRNWithoutIns)){
			System.out.println("billed claims rows are present");
			SeleniumUtil.rightClick(irows);
			sleep(3000);
			List<WebElement> totaltrrows1 = driver.findElements(By.xpath("//div[@id='radMenu_detached']/ul/li"));
			System.out.println(totaltrrows1.size());
			for(WebElement irows1:totaltrrows1){
				String rowtext1 =irows1.getText();
				System.out.println(rowtext1);
				if(rowtext1.contains(menuName)){
					WebElement e1= driver.findElement(By.xpath(".//*[contains(text(),'"+ menuName +"')]"));
					action.moveToElement(e1).click().build().perform();
					isCollectionQueuePresent=true;
					break;	
				}
			}
		}
	}
	return isCollectionQueuePresent;
}
/**
 * This method is used to verify collection queue  in billing screen
 * 
 */
public boolean verifyCollectionQueue(){
	boolean isCollectionQueuePresent=false;
	SeleniumUtil.switchToFrame(driver, "fraLedger_Frame");
	List <WebElement> totalrows =driver.findElements(AppointmentPage.totaltrtags);
	System.out.println(totalrows.size());
	for(WebElement providersrow:totalrows){
		String rowText =providersrow.getText();
		System.out.println(rowText);
		if(rowText.contains(ConstantsFile.username) && rowText.contains("Patient sent to collections queue"))
		{
			System.out.println("collection queue is present in the ledger entry");
			isCollectionQueuePresent=true;
			break;
		}
	}
	return isCollectionQueuePresent;
}
}
