package com.gmed.pages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static com.gmed.helper.DriverFactory.driver;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;


import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;
import static com.gmed.helper.DriverFactory.action;
public class FaxingPage extends BaseAbstractPage {
	/** Logger to log the FaxingPage log messages */
	private static Logger logger                        = LogManager.getLogger(FaxingPage.class); 
	public static  By addpatientInOutbound              = By.id("txtPatientAdd");
	public static By searchPatientInMedical             = By.id("btnFiltersSearch_TextSpan");
	public static By searchPatientInFaxing             = By.id("txtSearchPatient_TextBox");
	
	/**
	 * This method is used for verify whether when user clicks on faxing button it should go in outbound faxing
	 * 
	 */
	public boolean selectOutBoundRow(){
		SeleniumUtil.waitForProgressBar(Frames.FAXING);
		sleep(9000);
		boolean faxrowpresent=false;
		SeleniumUtil.switchToParentFrame(Frames.FAXING);
		SeleniumUtil.waitForProgressBar(Frames.FAXING);
		List <WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowText = irows.getText();
			if(rowText.contains(ConstantsFile.patientfirstname)){
				System.out.println("fax is displayed");
				faxrowpresent=true;
				break;
			}
		}
		return faxrowpresent;
	}
	/**
	 * This method is used to switch into outbound faxing frame and it will click on search filter
	 * @throws Exception
	 */
	public void switchToOutboundFaxing(){
		SeleniumUtil.switchToParentFrame(Frames.FAXING);
		SeleniumUtil.waitForProgressBar(Frames.FAXING);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.searchFilter).click();
		SeleniumUtil.waitForProgressBar(Frames.FAXING);
	}
	/**
	 * This method is used to switch into outbound faxing frame and it will click on patient plus button
	 * @throws Exception
	 */
	public void clickOnPatientButton(){
		SeleniumUtil.switchToParentFrame(Frames.FAXING);
		SeleniumUtil.getElementWithFluentWait(addpatientInOutbound).click();
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		SeleniumUtil.waitForProgressBar(Frames.FAXING);
	}
	/**
	 * This method is used for select the corresponding patient for the imported  file from the scanning module present in queue management module from the application
	 *  
	 *
	 */
	public void selectPatient(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(ConstantsFile.patientfirstname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(ConstantsFile.patientlastname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.searchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(3000);
		action.moveToElement(SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow));
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for verify whether when user clicks on faxing button it should go in outbound faxing
	 *
	 */
	public boolean selectOutBoundRowForService(){
		boolean faxrowpresent=false;
		SeleniumUtil.switchToParentFrame(Frames.FAXING);
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowText = irows.getText();
			if(rowText.contains(ConstantsFile.providerfirstname)){
				System.out.println("fax is displayed");
				faxrowpresent=true;
				break;
			}
		}
		return faxrowpresent;
	}
	
	/**
	 * For switching in tool tip frame
	 */
	public void switchToToolTipFrame(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
	}
	
	/**
	 * select patient for faxing module
	 */
	public void selectPatientInFaxing(){
		logger.info("selecting the patient...");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.selectbutton).click();
	}
	
	/**
	 * This method is used to switch into outbound faxing frame
	 *
	 */
	public void switchToOutboundFaxingFrame(){
		SeleniumUtil.switchToParentFrame(Frames.FAXING);
		SeleniumUtil.waitForProgressBar(Frames.FAXING);
	}
	/**
	 * This method is used for verify whether when user clicks on faxing button it should go in outbound faxing
	 *
	 */
	public boolean verifyOutboundFaxingQueue(){
		boolean faxrowpresent=false;
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		ConstantsFile.completeUserName =ConstantsFile.loginData.get(ConstantsFile.COMPLETE_USERNAME);
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowText = irows.getText();
			if(rowText.contains(Profile.existingProfileProviderfirstname) && rowText.contains(Profile.existingProfileProviderlastname) && rowText.contains(ConstantsFile.completeUserName)){
				System.out.println("fax is displayed");
				faxrowpresent=true;
				break;
			}
		}
		return faxrowpresent;
	}
	
}
