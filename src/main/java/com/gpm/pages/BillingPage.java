package com.gpm.pages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.pages.AppointmentPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;

import static com.gmed.helper.DriverFactory.driver;

import java.util.List;
public class BillingPage extends BaseAbstractPage{
	/** Logger to log the BillingPage log messages */
	private static Logger logger                                    = LogManager.getLogger(BillingPage.class);
	public static By billingMenu                                    = By.id("tabBilling");
	public static By titleHeading                                   = By.id("tdHeaderText");
	public static By billingTab                                     = By.id("tabBilling");
	public static By closeButton                                    =By.id("PopupMain_CloseButton");
	public static By patientTextBox			                        = By.id("txtPatient_TextBox");
	AppointmentPage app = new AppointmentPage();
	
	/** This method is used to click on biling menu present in left panel
	 * 
	 */
	public void clickOnBilling(){
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu...");
		SeleniumUtil.getElementWithFluentWait(billingMenu).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
	}
	/**
	 * This method is used to click on different menus present in billing screen
	 * @param navigationName is menu item name
	 */
	public void clickOnBillingLeftNavigation(String navigationName){
		SeleniumUtil.switchToParentFrame(Frames.Billing);
		List <WebElement> leftnavigationvalues=driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:leftnavigationvalues){
			String rowText= irows.getText();
			System.out.println(rowText);
			if(rowText.contains(navigationName)){
				irows.click();
				SeleniumUtil.waitForProgressBar(Frames.Billing);
				break;
			}
		}
	}
	/**
	 * This method is used to check that superbill link is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifySuperbillLink(){
		boolean isSuperbillTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.Superbill);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Superbills")){
			System.out.println("superbill list is open & it's accessible");
			isSuperbillTitle=true;
		}
		return isSuperbillTitle;
	}
	/**
	 * This method is used to check that charges link is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyPostedChargesLink(){
		boolean isChargesTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.Charges);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Charges")){
			System.out.println("ChargesTitle list is open & it's accessible");
			isChargesTitle=true;
		}
		return isChargesTitle;
	}
	/**
	 * This method is used to check that unbilled claim link is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyUnbilledClaimLink(){
		boolean isUnbilledTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.UNBILLED);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Unbilled Claims")){
			System.out.println("unbilled claim list is open & it's accessible");
			isUnbilledTitle=true;
		}
		return isUnbilledTitle;
	}
	/**
	 * This method is used to check that Billed claim link is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyBilledClaimLink(){
		boolean isBilledTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.BILLED);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Billed Claims")){
			System.out.println("Billed claim list is open & it's accessible");
			isBilledTitle=true;
		}
		return isBilledTitle;
	}
	/**
	 * This method is used to check that Payment link is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyPaymentLink(){
		boolean isPaymentTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.PAYMENT);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Payments")){
			System.out.println("Payment list is open & it's accessible");
			isPaymentTitle=true;
		}
		return isPaymentTitle;
	}
	/**
	 * This method is used to check that My Claims is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyMyClaimsLink(){
		boolean isMyClaimsTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.MYCLAIMS);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("My Claims")){
			System.out.println("My Claims list is open & it's accessible");
			isMyClaimsTitle=true;
		}
		return isMyClaimsTitle;
	}
	/**
	 * This method is used to check that Online Payment is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyOnlinePaymentLink(){
		boolean isOnlinePaymentTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.ONLINEPAYMENT);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Online Payments")){
			System.out.println("Online Payment list is open & it's accessible");
			isOnlinePaymentTitle=true;
		}
		return isOnlinePaymentTitle;
	}
	/**
	 * This method is used to check that Refunds is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyRefundsLink(){
		boolean isRefundsTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.REFUND);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Refunds")){
			System.out.println("Refunds list is open & it's accessible");
			isRefundsTitle=true;
		}
		return isRefundsTitle;
	}
	/**
	 * This method is used to check that Manage Batch is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyBatchLink(){
		boolean isBatchesTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.BATCH);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Batches")){
			System.out.println("Batches list is open & it's accessible");
			isBatchesTitle=true;
		}
		return isBatchesTitle;
	}
	/**
	 * This method is used to check that Sent Claims is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifySentClaimLink(){
		boolean isSentClaimsTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.SENTCLAIM);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Sent Claims")){
			System.out.println("Sent Claims list is open & it's accessible");
			isSentClaimsTitle=true;
		}
		return isSentClaimsTitle;
	}
	/**
	 * This method is used to check that Collections is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyCollectionsLink(){
		boolean isCollectionsTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.COLLECTION);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Collections")){
			System.out.println("Collections list is open & it's accessible");
			isCollectionsTitle=true;
		}
		return isCollectionsTitle;
	}
	/**
	 * This method is used to check that Collections is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyPatientswithCreditsLink(){
		boolean isPatientswithCreditsTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.CREDIT);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.contains("Patients with Credits")){
			System.out.println("Patients with Credits list is open & it's accessible");
			isPatientswithCreditsTitle=true;
		}
		return isPatientswithCreditsTitle;
	}
	/**
	 * This method is used to check that Prepay is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyPrepayLink(){
		boolean isPrepayTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.PREPAY);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Prepay (estimates)")){
			System.out.println("Prepay  list is open & it's accessible");
			isPrepayTitle=true;
		}
		return isPrepayTitle;
	}
	/**
	 * This method is used to check that Prepay is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyManageERALink(){
		boolean isManageERATitle=false;
		SeleniumUtil.switchToParentFrame(Frames.ERA);
		String tiitle = SeleniumUtil.getElementWithFluentWait(titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Received ERA files")){
			System.out.println("Manage ERA list is open & it's accessible");
			isManageERATitle=true;
		}
		return isManageERATitle;
	}
	/**
	 * This method is used to click on billing Tab 
	 */
	public void clickOnBillingTabInPatient(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		driver.switchTo().frame("panProfileDemographics_Frame");
		SeleniumUtil.getElementWithFluentWait(billingTab).click();	
	}
	/**
	 * This method is used to click on search button 
	 */
	public void clickOnSearchButton(){
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.searchbutton).click();
		//SeleniumUtil.waitForProgressBar(Frames.COLLECTION);
	}
	/**
	 * This method is used to click On Close button of billing alert 
	 * @return string
	 * 
	 */
	public void clickOnCloseButtonInBillingAlerts(){
		sleep(5000);
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.getElementWithFluentWait(closeButton).click();
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
	}
	/**
	 * This method is used to click on search filter for searching the patient in superbill queue
	 */
	public void searchPatientInBilling(){
		sleep(5000);
		SeleniumUtil.getElementWithFluentWait(CollectionPage.searchFilter).click();
		//SeleniumUtil.waitForProgressBar(Frames.Superbill);
		SeleniumUtil.getElementWithFluentWait(CollectionPage.clearButton).click();
		//SeleniumUtil.waitForProgressBar(Frames.Superbill);
		SeleniumUtil.getElementWithFluentWait(CollectionPage.patientDropDown).click();
		//SeleniumUtil.waitForProgressBar(Frames.Superbill);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		app.searchPatientName();
	}
	/**
	 * This method is used to switch into payment page frame  
	 */
	public void switchToPaymentPage(){
		SeleniumUtil.switchToParentFrame(Frames.PAYMENT);
	}
	/**
	 * This method is used to search patient name in Payments and Adjustment  Page 
	 */
	public void searchPatient(){
		SeleniumUtil.getElementWithFluentWait(CollectionPage.patientDropDown).click();
		SeleniumUtil.waitForProgressBar(Frames.PATIENTPAYMENTINDIDEPAGE);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		app.searchPatientName();
	}
	/**
	 * This method is used to verify patient name is populated correctly
	 */
	public boolean verifyPatientNameIsPopulalted(){
		boolean isPatientIsPopulated=false;
		String patientName = SeleniumUtil.getElementWithFluentWait(patientTextBox).getAttribute("value");
		if(patientName.contains(ConstantsFile.patientfirstname)){
			System.out.println("Patient Name is Populated");
			isPatientIsPopulated=true;
		}
		return isPatientIsPopulated;
	}
	/**
	 * This method is used to Add Patient for insurance payement with sikuli 
	 */
	public void searchPatientForInsurancePaymentAndRefund() throws FindFailed{
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("insurnaceRefundPatient");
		SeleniumUtil.clickOnImageWitScreenInSikuli("addNewPatient");
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		app.searchPatientName();
	}
	/**
	 * This method is used to switch into insurance Frame  
	 */
	public void switchToInsuranceFrame(){
		SeleniumUtil.switchToParentFrame(Frames.INSURANCE);
	}
}
