package com.gmed.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import static com.gmed.helper.DriverFactory.driver;

import java.util.List;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;

import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;

public class PatientChart extends BaseAbstractPage {
	/** Logger to log the PatientChart log messages */
	private static Logger logger                = LogManager.getLogger(PatientChart.class); 
	public static By importButton                        = By.id("btnImport_SpanBGColor");
	public static By clickOnSendButton                  = By.id("btnSendForSignoff_SpanBGColor");
	public static By newbutton                           = By.id("btnNew_SpanBGColor");
	public static By dropDownClass                       = By.className("ToolbarDropDownImage");
	public static By patientInfoTab                      = By.id("tabPatientInfo_Text");
	public static By dismissGuidelinebutton             = By.id("btnHideNotification_Table");
	public static By mailID                              = By.id("tblEmailCollectionAdd");
	public static By mailIDTextBox                       = By.id("txtEmailAddress_TextBox");
	public static By portalTab                           = By.id("tabPortal_Text");
	public static By sendInvitation                       = By.id("btnSendInvitation_SpanBGColor");
	public static By patientChartHeader                   =By.id("tabTree");
	public static By emailIdTextBox                       =By.id("txtEmailAddress_TextBox");
	public static String patientMailId;
	/**
	 * This method is used to verify the patient chart is opened
	 *
	 */
	public boolean verifyPatientChartIsOpened(){
		if(ConstantsFile.isPatientChartPresent =true){
			System.out.println("patient chart is opened");
			return true;
		}
		else{
			System.out.println("patient chart is not opened");
			return false;
		}
	}
	/**
	 * This method is used for click on Import file from the patient chart
	 * 
	 */
	public void clickOnImportFile(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.getElementWithFluentWait(importButton).click();
	}
	public static void handleSameMRN() throws FindFailed{
		/**
		 * wait for number of windows to load
		 */
		SeleniumUtil.waitForNumberOfWindowsToEqual(2);
		String parentWindowHandler = driver.getWindowHandle();
		System.out.println("The window are :"+driver.getWindowHandles().size());// Store your parent window
		if(driver.getWindowHandles().size() == 2){
			System.out.println("Yes No window");
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
		}
	}
	/**
	 * This method is used for verify when user click on self link then logged in user name should get populated  for  Task  present in  patient chart
	 * 
	 */
	public boolean verifyloggedInUserNameIsPopulatedInSelfLink(){
		boolean isLoggedInUserNameIsPopulated=false;
		List<WebElement> listvalue =driver.findElements(AppointmentPage.patientrow);
		for(WebElement usernamevalue:listvalue){
			String rowText =usernamevalue.getText();
			if(rowText.contains(ConstantsFile.userfirstname)){
				System.out.println("Logged in User is displayed in the self link");
				return isLoggedInUserNameIsPopulated=true;
			}
		}
		return isLoggedInUserNameIsPopulated;
	}
	/**
	 * This method is used for click on send on signature present in  Task for the imported  file from the scanning module present in queue management module from the application
	 *
	 */
	public void clickOnSendForSignature(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(clickOnSendButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for click on procedure present in medical chart
	 * 
	 */
	public void clickOnProcedure(String procedurename){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.getElementWithFluentWait(newbutton).click();
		SeleniumUtil.switchToParentFrame(Frames.PROCEDURE);
		WebElement FirstVisitvalue= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ procedurename +"')]"));
		SeleniumUtil.scrolltoWebElement(FirstVisitvalue);
		FirstVisitvalue.click();
		SeleniumUtil.waitForProgressBar(Frames.PROCEDURE);
		sleep(1000);
	}
	/**
	 * This method is used to click in billing drop down present in patient chart
	 */
	public void clickOnBillingDropDown(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		List<WebElement> buttons = driver.findElements(dropDownClass);
		WebElement query_enquirymode = buttons.get(3);
		query_enquirymode.click();
	}
	/**
	 * This method is used to click in billing drop down present in patient chart
	 * @throws FindFailed 
	 */
	public void clickOnAccountInquiry() throws FindFailed{
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnAccountInquiry");
	}
	/**
	 * This method is used to verify patient demographics should be open in the bottom half of the page when user click on patient hyperlink in account inquiry page.
	 */
	public boolean verifyPatientInfoTab(){
		boolean isPatientInfoTabPresent=false;
		sleep(5000);
		driver.switchTo().frame("panProfileDemographics_Frame");
		String patientInfoText=SeleniumUtil.getElementWithFluentWait(patientInfoTab).getText();
		if(patientInfoText.equalsIgnoreCase(" Patient Info ")){
			System.out.println("patient Info Tab should be opned");
			SeleniumUtil.getElementWithFluentWait(patientInfoTab).sendKeys(Keys.ESCAPE);
			isPatientInfoTabPresent=true;
		}
		return isPatientInfoTabPresent;
	}
	/**
	 * This method is used to select billing group  for patient
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnSuperBill() throws FindFailed{
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnSuperbill");
		sleep(5000);
	}
	/**This method is used to verify patient is created successfully
	 * 
	 * @return true if patient is created 
	 */
	public boolean verifyPatientIsCreated(){
		if(ConstantsFile.isPatientCreated = true){
			System.out.println("Patient is created");
			//switching to tool tip frame to dismiss the guidllines tool tip
			SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
			try{
			SeleniumUtil.getElementWithFluentWait(dismissGuidelinebutton).click();
			}
			catch(Exception e){
				System.out.println("No tool tip is found");
			}
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * This method is used to enter valid mail id of  the patient for registering in patient portal
	 * 
	 */
	public void enterValidEmailId(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.getElementWithFluentWait(emailIdTextBox).clear();
		SeleniumUtil.getElementWithFluentWait(mailID).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		 patientMailId =ConstantsFile.loginData.get(ConstantsFile.VALID_PATIENT_MAILID);
		SeleniumUtil.getElementWithFluentWait(mailIDTextBox).sendKeys(patientMailId);
	}
	/**
	 * This method is used to click on portal tab present in demographics
	 * 
	 */
	public void clickOnPortalTab(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.getElementWithFluentWait(portalTab).click();
	}
	/**
	 * This method is used to click on send invitation button present in demographics
	 * 
	 */
	public void clickOnSendInvitation(){
		sleep(5000);
		SeleniumUtil.getElementWithFluentWait(sendInvitation).click();
	}

	public boolean verifySendPortalButtonIsEnabled(){
		sleep(5000);
		driver.switchTo().frame("frmPortal_Frame");
		boolean isSendButtonEnable =SeleniumUtil.getElementWithFluentWait(sendInvitation).isEnabled() !=false;
		if(isSendButtonEnable){
			System.out.println("Send button is enabled");
		}
		return isSendButtonEnable;
	}
	/**
	 * This method is used to verify the patient chart is opened
	 *
	 */
	public boolean verifyPatientChartIsOpened1(String patientFirstName ,String patientLastName){
		boolean isPatientChartOpened =false;
		DynamicFramePage.dynamicFrameForPanchart();
		String patientName =SeleniumUtil.getElementWithFluentWait(patientChartHeader).getAttribute("text");
		System.out.println(patientName);
		if(patientName.contains(patientFirstName) && patientName.contains(patientLastName)){
			System.out.println("patient chart is opened");
			isPatientChartOpened =true;
		}
		return isPatientChartOpened;
}
	
}
