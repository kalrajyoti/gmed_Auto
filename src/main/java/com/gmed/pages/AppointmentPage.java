package com.gmed.pages;



import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import static com.gmed.helper.DriverFactory.action;
import static com.gmed.helper.DriverFactory.driver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.testng.ITestResult;
import org.testng.TestNG;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.test.BaseTestClass;
import com.gmed.test.DemographicsTest;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;
import com.gpm.pages.BillingPage;

public class AppointmentPage extends BaseAbstractPage{
	/** Logger to log the AppointmentPage log messages */
	private static Logger logger                             = LogManager.getLogger(AppointmentPage.class);
	public static By appointmenttextbox                       =By.id("txtPatientNameDropDown");
	public static By patientnametextbox                       =By.id("txtName_TextBox");
	public static By searchbutton                             =By.id("btnFiltersSearch_TextSpan");
	public static By activitydropdown                         =By.id("txtActivityDropDown");
	public static By providerplusbutton                       =By.id("tblProvidersCollectionAdd");
	public static By providersearchtextbox                    =By.id("txtSearch_TextBox");
	public static By providersearchbutton                     =By.id("txtSearchSearch");
	public static By selectbutton                             =By.id("btnSelect");
	public static By savebutton                               =By.id("btnSave_SpanBGColor");
	public static By popupyes                                 =By.id("btnYes_Center");
	public static By popupno                                  =By.id("btnNo_Left") ;
	public static By logoutbutton                             =By.id("btnLogout");
	public static By totaltrtags                              =By.xpath(".//table[@id='tblList_Table']/tbody/tr");
	public static By startappointmentbutton                   =By.id("btnStart_SpanBGColor");
	public static By tooltipnewbutton                         =By.id("btnNew_SpanBGColor");
	public static By refferringphysicianelement               =By.id("snpReferringPhysician");
	public static By refferringphysiciantable                 =By.id("snpReferringPhysician_Table");
	public static By patientname                              =By.id("lblPatientName");
	public static By providername                             =By.id("snpProvider_Table");
	public static By patientnamelabal                         =By.id("lbllabelPatientName");
	public static By patientfirstname                         =By.id("txtFirstName_TextBox");
	public static By patientlastname                          =By.id("txtLastName_TextBox");
	public static By shudulerbutton                           =By.id("tabScheduler_Text");
	public static By schduletext                              =By.xpath(".//*[contains(text(),'Scheduled')]");
	public static By viewCharttext                            =By.id("btnViewChart_SpanBGColor");
	public static By patientrow                               =By.className("tableRowBorder");
	public static By monthloc                                 =By.id("dtbDate_Month");
	public static By dayloc                                   =By.id("dtbDate_Day");
	public static By yearloc                                  =By.id("dtbDate_Year");
	public static By hourloc                                  =By.id("ttbTime_Hour");
	public static By minloc                                   =By.id("ttbTime_Minute");
	public static By secloc                                   =By.id("ttbTime_AMPM");
	public static By searchFilter                             =By.id("txtSearchPatientMore");
	public static By monthlocvalue                            =By.id("dtbDateFrom_Month");
	public static By daylocvalue                              =By.id("dtbDateFrom_Day");
	public static By yearlocvalue                             =By.id("dtbDateFrom_Year");
	public static By patienttextvalue                         =By.id("txtPatientName");
	public static By activitytextvalue                        =By.id("txtActivity");
	public static By paymentbutton                            =By.id("btnPayment_SpanBGColor");
	public static By copaymentAmount                          =By.id("dtbAmount_FormattedDecimalTextBox");
	public static By otherAmount                              =By.id("dtbOtherAmount_FormattedDecimalTextBox");
	public static By paidByDropDown                           =By.id("ddlPaidBy_DropDown");
	public static By copaymentType                            =By.id("ddlCopaymentType_DropDown");
	public static By checkinButton                            =By.id("btnCheckIn_SpanBGColor");
	public static By popUpTitle                               =By.id("Popup_TitleContent");
	public static By paidText                                 =By.id("ddlPaidBy_Text");
	public static By coPaymentText                            =By.id("ddlCopaymentType_Text");
	public static By applybutton                               =By.id("btnApply_SpanBGColor");
	
    


	/**
	 * This method is used to click on scheduler for the patient  to create new appointment for the searched patient
	 * 
	 */
	public boolean clickOnScheduler(){
		//switching into configuration frame
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on scheduler  menu to schedule any appointment...");
		SeleniumUtil.getElementWithFluentWait(shudulerbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		ConstantsFile.isschdulerisOpened=true;
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		sleep(3000);
		return ConstantsFile.isschdulerisOpened;
	}
	/**
	 * This method is used to verify Scheduler module is opened
	 * 
	 */
	public boolean verifySchdulerModuleIsOpened(){
		if(ConstantsFile.isschdulerisOpened=true){
			System.out.println("schduler module is opened");
			return true;
		}
		else{
			System.out.println("schduler module is not opened");
			return false;
		}
	}
	/**
	 * This method is used to click on new button to create any appointment in Scheduler module 
	 * 
	 */
	public void clickOnNewButton(){
		SeleniumUtil.switchToParentFrame(Frames.SCHEDULER);
		SeleniumUtil.getElementWithFluentWait(tooltipnewbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.SCHEDULER);
		sleep(3000);
	}
	/**
	 * This method is used to search patient name to create new appointment for the searched patient
	 * 
	 */
	public  void searchPatientName() {
		logger.info("searching the patient name....");
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(ConstantsFile.patientfirstname);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(ConstantsFile.patientlastname);
		logger.info("clicking on search button...");
		SeleniumUtil.getElementWithFluentWait(searchbutton).click();
		//SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		ConstantsFile.isPatientisSearched=true;
		sleep(3000);
		logger.info("selecting the patient...");
		SeleniumUtil.getElementWithFluentWait(selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.PATIENT_SEARCHING);
	}
	/**
	 * This method is used to click on patient name drop down for searching patient for  creating new appointment
	 * 
	 */
	public void clickonPatientNameDropDown(){
		// switching in Appointment frame to create new appointment
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		logger.info("clicking on patient name drop down...");
		SeleniumUtil.getElementWithFluentWait(appointmenttextbox).click();
		SeleniumUtil.waitForProgressBar(Frames.APPOINTMENT_CREATION);
	}
	/**
	 * This method is used to verify patient screen is open for searching  patient 
	 * 
	 */
	public boolean verifyPatientScreenInAppointment(){
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		WebElement patienttextbox = SeleniumUtil.getElementWithFluentWait(patientnametextbox);
		if(patienttextbox!=null){
			System.out.println("Patient screen is displayed");
			ConstantsFile.patientscreenInAppointment= true;
		}
		else{
			System.out.println("Patient screen is not displayed");
			ConstantsFile.patientscreenInAppointment =false;
		}
		return ConstantsFile.patientscreenInAppointment;	
	}
	/**
	 * This method is used to verify patient is searched in patient screen
	 * 
	 */
	public boolean verifyPatientIsSearched(){
		if(ConstantsFile.isPatientisSearched=true){
			System.out.println("Patient is searched..");
			return true;
		}
		else{
			System.out.println("Patient is not searched..");
			return false;
		}
	}
	/**
	 * This method is used to select future date
	 * 
	 */
	public static void setFutureDate(){
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		HashMap<String,String> getHashmapValues =new HashMap<String,String>();
		getHashmapValues = SeleniumUtil.setFutureDate();
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		SeleniumUtil.getElementWithFluentWait(monthloc).sendKeys(getHashmapValues.get("month"));
		SeleniumUtil.getElementWithFluentWait(dayloc).sendKeys(getHashmapValues.get("day"));
		SeleniumUtil.getElementWithFluentWait(yearloc).sendKeys(getHashmapValues.get("year"));
	}
	public void setTime(){
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		HashMap<String,String> getHashmapTimeValues =new HashMap<String,String>();
		getHashmapTimeValues = SeleniumUtil.getTimeInHourMinSecond();
		SeleniumUtil.getElementWithFluentWait(hourloc).sendKeys(getHashmapTimeValues.get("HourKey"));
		SeleniumUtil.getElementWithFluentWait(minloc).sendKeys(getHashmapTimeValues.get("minKey"));
		SeleniumUtil.getElementWithFluentWait(secloc).sendKeys(getHashmapTimeValues.get("secKey"));	
	}
	/**
	 * This method is used to set activity for the searched patient
	 * @throws Exception 
	 */
	public  void setActivity(String actvityvalue) throws Exception{
		//Click on Activity drop down text box
		Thread.sleep(3000);
		logger.info("clicking on activity drop down menu...");
		SeleniumUtil.getElementWithFluentWait(activitydropdown).click();
		SeleniumUtil.waitForProgressBar(Frames.APPOINTMENT_CREATION);
		//For switching in the activity drop down Frame  
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		WebElement activevalue;
		if(actvityvalue.contains("Colonoscopy")){
			activevalue= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.ColonIntialname +"')]"));
		}
		else{
			activevalue= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.FirstVisitIntialname +"')]"));
		}
		if(!(activevalue==null))
		{
			System.out.println("Activity found");
			activevalue.click();
			SeleniumUtil.waitForProgressBar(Frames.PATIENT_SEARCHING);
		}
	}
	/**
	 * This method is used to set the provider name for the searched patient
	 * 
	 */
	public  void setProvider(){
		//To switch  in the Appointment page
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		sleep(1000);
		//Get the provider name which is generated dynamically
		String providervalue=ConstantsFile.providercompletelastname;
		//Click on the provider tool tip 
		SeleniumUtil.getElementWithFluentWait(providerplusbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.APPOINTMENT_CREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(providersearchtextbox).sendKeys(providervalue);
		SeleniumUtil.getElementWithFluentWait(providersearchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(1500);
		List <WebElement> providernamevalues =driver.findElements(totaltrtags);
		System.out.println(providernamevalues);
		for(WebElement providersrow:providernamevalues){
			System.out.println(providersrow.getText());
			if(providersrow.getText().contains(providervalue))
				providersrow.click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		}
		SeleniumUtil.getElementWithFluentWait(selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to click on search filter to search for future appointments 
	 * 
	 */
	public void clickOnSearchFilter(){
		SeleniumUtil.switchToParentFrame(Frames.SCHEDULER);
		SeleniumUtil.getElementWithFluentWait(searchFilter).click();
		SeleniumUtil.waitForProgressBar(Frames.SCHEDULER);
	}
	/**
	 * This method is used to select future month ,day and year 
	 * 
	 */
	public void selectFuturevalue(){
		HashMap<String,String> getHashmapValues =new HashMap<String,String>();
		getHashmapValues = SeleniumUtil.setFutureDate();
		//SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		SeleniumUtil.getElementWithFluentWait(monthlocvalue).sendKeys(getHashmapValues.get("month"));
		SeleniumUtil.getElementWithFluentWait(daylocvalue).sendKeys(getHashmapValues.get("day"));
		SeleniumUtil.getElementWithFluentWait(yearlocvalue).sendKeys(getHashmapValues.get("year"));

	}
	/**
	 * This method is used to select on find button to search the future appointment  
	 * 
	 */
	public void clickOnFindButton(){
		SeleniumUtil.getElementWithFluentWait(searchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.SCHEDULER);
	}
	/**
	 * This method is used to select the appropriate row for the created appointment in the appointment section
	 * 
	 */
	public void selectRow(){
		SeleniumUtil.switchToParentFrame(Frames.SCHEDULER);
		List<WebElement> totaltrrows = driver.findElements(totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			if(rowtext.contains("Scheduled")){
				irows.click();
				SeleniumUtil.waitForProgressBar(Frames.SCHEDULER);
				break;
			}
		}
	}
	/**This method will click on view chart in scheduler module
	 * 
	 * @throws FindFailed
	 */
	public void clickOnViewChart(String patientFirstName,String PatientLastName) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.SCHEDULER);
		List<WebElement> totaltrrows = driver.findElements(totaltrtags);
		String patientname = patientFirstName+" "+PatientLastName;
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			if(rowtext.contains("Scheduled") && rowtext.contains(patientname)){
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("clickOnViewChart");
				break;
			}
		}
	}
	/**This method will click on start service  in scheduler module
	 * 
	 * @throws FindFailed
	 */
	public void selectAppointmentRow(String servicename) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.SCHEDULER);
		List<WebElement> totaltrrows = driver.findElements(totaltrtags);
		String patientname = ConstantsFile.patientfirstname+" "+ConstantsFile.patientlastname;
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowtext =irows.getText();
			if(rowtext.contains(servicename) && rowtext.contains(patientname)){
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("H_AppointmenetChooseStart");
				break;
			}
		}
	}
	/**This method is used to dynamic frames for appointments 
	 * 
	 * 
	 *//*
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
	}*/
	/**This method is used to view future appointment in profile of patient in the medical chart  
	 * 
	 * 
	 */
	public boolean viewFutureAppointment(){
		boolean sameAppointment=false;
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		WebElement futuretext = SeleniumUtil.getElementWithFluentWait(patientrow);
		if(futuretext!=null){
			System.out.println("future appointment is created");
			sameAppointment=true;;
		}
		return sameAppointment;
	}
	/**This method is used to  save the created appointment 
	 * 
	 * 
	 */
	public void clickOnSaveButtonofUserPage(){
		logger.info("saving the appointment details...");
		SeleniumUtil.getElementWithFluentWait(savebutton).click();
		//SeleniumUtil.waitForProgressBar(Frames.APPOINTMENT_CREATION);
		sleep(5000);
	}
	
	/**
	 * This method is used to check the  patient name & activity for any appointment 
	 * @return string
	 *  
	 */
	public String checkAppointmentText(String activityvalue){
		String appointmenttextvalue=null;
		String appointmentactivityname;
		SeleniumUtil.switchToParentFrame(Frames.SCHEDULER);
		//Get the patient name and activity from the excel
		String appointmentpatientname =ConstantsFile.patientfirstname+" "+ConstantsFile.patientlastname;
		if(activityvalue.equalsIgnoreCase("Colonoscopy")){
			appointmentactivityname =ConstantsFile.ColonIntialname;
		}
		else{
			appointmentactivityname =ConstantsFile.FirstVisitIntialname;
		}
		String providername =ConstantsFile.providerfirstname+" "+ConstantsFile.providercompletelastname;
		String appointmentloctionname =ConstantsFile.Fulllocationname;
		String e1text = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'" + appointmentpatientname +"')]")).getText();
		String e2text = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'" + appointmentactivityname +"')]")).getText();
		String e3text = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'" + providername +"')]")).getText();
		String e4text = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'" + appointmentloctionname +"')]")).getText();
		appointmenttextvalue = e1text + " " +e2text+" "+e3text+" "+e4text;
		System.out.println(appointmenttextvalue);
		return appointmenttextvalue;
	}
	/**
	 * This method is used to verify the required fields while creating any appointment
	 * @return string
	 * 
	 */
	public boolean verifyRequiredFieldvaldation(){
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		String currAppointmentPatientValidationTxt = SeleniumUtil.getElementWithFluentWait(patienttextvalue).getAttribute("text");
		System.out.println("the patient validation text is :" +currAppointmentPatientValidationTxt);
		String currAppointmentActivityValidationTxt= SeleniumUtil.getElementWithFluentWait(activitytextvalue).getAttribute("text");
		System.out.println("the activity validation text is :" +currAppointmentActivityValidationTxt);
		if(currAppointmentPatientValidationTxt !=null && currAppointmentPatientValidationTxt.length()>0){
			return ConstantsFile.requiredFieldValidationInAppointment=true;
		}
		else{
			ConstantsFile.requiredFieldValidationInAppointment=false;
			logger.error("patient name and Activity can't be blank...");
		}
		return ConstantsFile.requiredFieldValidationInAppointment;
	}
	
	/**This method will click on payment button present when user checkin the  service  in scheduler module
	 * 
	 * 
	 */
	public void clickOnPayment(){
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		SeleniumUtil.getElementWithFluentWait(paymentbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.APPOINTMENT_CREATION);
	}
	/**This method will enter co-payment Amount  in checkin page
	 * 
	 */
	public void enterCopaymentAmount(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		sleep(5000);
		WebElement copaymentelement=SeleniumUtil.getElementWithFluentWait(copaymentAmount);
		sleep(3000);
		action.moveToElement(copaymentelement).doubleClick().sendKeys("40").build().perform();
		WebElement otherpayment =SeleniumUtil.getElementWithFluentWait(otherAmount);
		action.moveToElement(otherpayment).doubleClick().sendKeys("0").build().perform();	
	}
	/**This method will select the payment method as cash in checkin page
	 * 
	 */
	public void selectPaymentMethod() throws FindFailed{
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("clickOnPaymentMethodDropDown");
		SeleniumUtil.clickOnImageWitScreenInSikuli("selectCash");
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("clickOnCopaymentDropDown");
		SeleniumUtil.clickOnImageWitScreenInSikuli("selectCoPaymentType");	
	}
	/**This method will select the payment method as cash in checkin page
	 * 
	 */
	public void clickOnCheckInButton(){
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		SeleniumUtil.getElementWithFluentWait(checkinButton).click();
		SeleniumUtil.waitForProgressBar(Frames.APPOINTMENT_CREATION);
	}
	/**This method will click on start service  in scheduler module
	 * 
	 * @throws FindFailed
	 */
	public void checkInAppointmentRow(String servicename) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.SCHEDULER);
		List<WebElement> totaltrrows = driver.findElements(totaltrtags);
		//String patientname = ConstantsFile.patientfirstname+" "+ConstantsFile.patientlastname;
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowtext =irows.getText();
			if(rowtext.contains("Scheduled")){
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("clickOnCheckIn");
				break;

			}
		}
	}
	/**
	 * This method is used to verify check In page is opened
	 */
	public boolean verifyCheckInPageIsOpened(){
		boolean isCheckInPageOpened=false;
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		String tiitle = SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Check-In")){
			System.out.println("Check-In page is opened..");
			isCheckInPageOpened=true;
		}
		return isCheckInPageOpened;
	}
	/**
	 * This method is used to verify copayment pop up  is opened
	 */
	public boolean verifyCoPaymentPopupIsOpened(){
		boolean isCopaymentPopUpOpened=false;
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		String tiitle = SeleniumUtil.getElementWithFluentWait(popUpTitle).getText();
		System.out.println(tiitle);
		if(tiitle.equalsIgnoreCase("Copayment")){
			System.out.println("Copayment popup is opened..");
			isCopaymentPopUpOpened=true;
		}
		return isCopaymentPopUpOpened;
	}
	/**
	 * This method is used to verify payment method && copayment is populated correctly 
	 */
	public boolean verifyPaymentMethodIsPopulated(){
		boolean isPaymentIsPopulated=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String paymentMethodtiitle = SeleniumUtil.getElementWithFluentWait(paidText).getAttribute("value");
		String coPaymentType = SeleniumUtil.getElementWithFluentWait(coPaymentText).getAttribute("value");
		if(paymentMethodtiitle.equalsIgnoreCase("Cash") && coPaymentType.equalsIgnoreCase("Procedure") ){
			System.out.println("Cash method  && copayment is populated..");
			isPaymentIsPopulated=true;
		}
		return isPaymentIsPopulated;
	}
	/**
	 * This method is used to verify appointment status change to be check in when user checkIn the appointment
	 */
	public boolean verifyAppointmentStatus(){
		boolean isAppointmentStatus=false;
		SeleniumUtil.switchToParentFrame(Frames.SCHEDULER);
		List<WebElement> totaltrrows = driver.findElements(totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			if(rowtext.contains("Checked In")){
				System.out.println("Appointment status is changed to checkin");
				isAppointmentStatus=true;
			}
		}
		return isAppointmentStatus;
	}
	/**
	 * This method is used to verify superbill is created for the check In Appointment 
	 */
	public boolean verifySuperbillQueue(){
		boolean isSuperbillQueue=false;
		SeleniumUtil.switchToParentFrame(Frames.Superbill);
		List<WebElement> totaltrrows = driver.findElements(totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			if(rowtext.contains(ConstantsFile.MRN)){
				System.out.println("superbill is created for the checkin appointment");
				isSuperbillQueue=true;
			}
		}
		return isSuperbillQueue;
	}
	/**
	 * This method is used to search patient name to create new appointment for the searched patient
	 * 
	 */
	public  void searchPatientNameWithoutIns() {
		logger.info("searching the patient name....");
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(ConstantsFile.patientfirstnamewithoutIns);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(ConstantsFile.patientlastnamewithoutIns);
		logger.info("clicking on search button...");
		SeleniumUtil.getElementWithFluentWait(searchbutton).click();
		ConstantsFile.isPatientisSearched=true;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("selecting the patient...");
		SeleniumUtil.getElementWithFluentWait(selectbutton).click();
	}
	/**This method is used to  save the created appointment 
	 * 
	 * 
	 */
	public void applyButton(){
		logger.info("saving the appointment details...");
		SeleniumUtil.getElementWithFluentWait(applybutton).click();
		SeleniumUtil.waitForProgressBar(Frames.REFUNDINSIDE);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**This method is used for taking screenshot of Exception which occurs in form of Alerts/Separate window
	 * 
	 */
	public  void handleException(String currTestName ,ITestResult result){
		/**
		 * wait for number of windows to load
		 */
		SeleniumUtil.waitForNumberOfWindowsToEqual(2);
		String parentWindowHandler = driver.getWindowHandle();
		System.out.println("The window are :"+driver.getWindowHandles().size());// Store your parent window
		if(driver.getWindowHandles().size() == 2){
			System.out.println("Exception in form of alert is present in the application");
			String subWindowHandler = null; 
			/**
			 * get all window handles
			 */
			Set<String> handles = driver.getWindowHandles(); 
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()){
				subWindowHandler = iterator.next();
			}
			/**
			 * switch to popup window
			 */ 
			driver.switchTo().window(subWindowHandler);	
			String title =driver.getTitle();
			System.out.println(title);
			if (title.contains("Exception")) 
			{
				System.out.println("Exception occured so  taking the screenshot of pop up window");
				String outputDIR = TestNG.getDefault().getOutputDirectory();
				try {
					sleep(4000);
					ConstantsFile.newFileNamePathForException = outputDIR + "\\ScreenShot\\" +currTestName +".jpg";
					BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					ImageIO.write(image, "png", new File( ConstantsFile.newFileNamePathForException));
					System.out.println("Exception screenshot is :"+ ConstantsFile.newFileNamePathForException);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			result.setStatus(ITestResult.FAILURE);
		}
		driver.switchTo().window(parentWindowHandler); 

	}

/**
	 * This method is used to verify Scheduler module is opened
	 * 
	 */
	public boolean verifyAppointmentPageIsOpened(){
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		SeleniumUtil.waitForProgressBar(Frames.APPOINTMENT_CREATION);
		String appointmentPageTitle =SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		if(appointmentPageTitle.equalsIgnoreCase("Appointment")){
			System.out.println("schduler module is opened");
			return true;
		}
		else{
			System.out.println("schduler module is not opened");
			return false;
		}
}
	/**
	 * This method is used to search patient name to create new appointment for the searched patient
	 * 
	 */
	public  void searchExistingPatientName(String patientname,String patientlastname) {
		logger.info("searching the patient name....");
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(patientname);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(patientlastname);
		logger.info("clicking on search button...");
		SeleniumUtil.getElementWithFluentWait(searchbutton).click();
		//SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		ConstantsFile.isPatientisSearched=true;
		sleep(3000);
	}
	/**
	 * This method is used to select existing activity for the searched patient
	 * @throws Exception 
	 */
	public  void setExistingActivity() throws Exception{
		//Click on Activity drop down text box
		SeleniumUtil.switchToParentFrame(Frames.APPOINTMENT_CREATION);
		Thread.sleep(3000);
		logger.info("clicking on activity drop down menu...");
		SeleniumUtil.getElementWithFluentWait(activitydropdown).click();
		SeleniumUtil.waitForProgressBar(Frames.APPOINTMENT_CREATION);
		//For switching in the activity drop down Frame  
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		SeleniumUtil.getElementWithFluentWait(patientrow).click();
	}
	
	//To verify portal message is created
	public boolean verifyPortalMessageCreated(String sub) {
        DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		DynamicFramePage.switchtopanProfileFrame();
		boolean result = false;
		List<WebElement> totaltrrows = driver.findElements(totaltrtags);
	       for(WebElement irows:totaltrrows){
			String text = irows.getText();
			System.out.println(irows.getText());
			if(text.contains(sub)){
				result = true;
				break;
			}
			
		
		}
		System.out.println(result);
		return result;


	}

	/**
	 * The method is used to enter Patient Name in the appointment
	 */
	public void enterPatientInAptmnt(String patientname) {
	click(AppointmentPage.appointmenttextbox);
    SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
    SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(patientname);
    SeleniumUtil.getElementWithFluentWait(AppointmentPage.searchbutton).click();
    SeleniumUtil.elewait(MedicalChartPage.searchResult);
    WebElement searchresult = SeleniumUtil.getElementWithFluentWait(MedicalChartPage.searchResult);
    SeleniumUtil.doubleClick(searchresult);
	}
    
	/**
	 * The method is used to enter Provider in the appointment
	 */
	public void enterProviderInAptmnt(String providername) {
	SeleniumUtil.getElementWithFluentWait(AppointmentPage.providerplusbutton).click();
    SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
    SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(providername);
	SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchbutton).click();
	SeleniumUtil.elewait(MedicalChartPage.searchResult);
    SeleniumUtil.getElementWithFluentWait(MedicalChartPage.searchResult).click();;
   	SeleniumUtil.getElementWithFluentWait(AppointmentPage.selectbutton).click();
	}
	/**
	 * The method is used to enter Activity in the appointment
	 */
	public void enterActivityInAptmnt(String activityname) {
	SeleniumUtil.getElementWithFluentWait(AppointmentPage.activitydropdown).click();
	SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
	SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'" + activityname +"')]")).click();
	}
	


}
