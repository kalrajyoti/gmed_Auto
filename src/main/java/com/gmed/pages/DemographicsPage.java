package com.gmed.pages;

import static com.gmed.helper.DriverFactory.action;
import static com.gmed.helper.DriverFactory.driver;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.testng.annotations.BeforeClass;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;



import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;




public class DemographicsPage extends BaseAbstractPage {
	/** Logger to log the DemographicsPage log messages */
	public static Logger logger                = LogManager.getLogger(DemographicsPage.class);
	public static By mailID                              = By.id("tblEmailCollectionAdd");
	public static By mailIDTextBox                       = By.id("txtEmailAddress_TextBox");
	public static By portalTab                           = By.id("tabPortal_Text");
	public static By sendInvitation                       = By.id("btnSendInvitation_SpanBGColor");
	//public static By patientInfoTab                       = By.id("tabInsurance_TabPage");
	public static By patientInfoTab1                       = By.id("tabPatientInfo_Text");
	public static By insuranceTab                         = By.id("tabInsurance");
	public static By chartAccessiblePlusSign              = By.id("tblAuthorizedUserCollectionAdd");
	public static By authorizedUserInchart                =By.xpath(".//table[@id='tblAuthorizedUserCollection_Table']/tbody/tr");
	public static By carrierDropDown                                = By.id("txtInsuranceProviderDropDown");
	public static By policyID                                       = By.id("btnCopySsn_ImageContainer");
	public static By guarantorTab                         = By.id("tabGuarantor");
	public static By guarantorFirstName                         = By.id("txtGuarantorFirstName_TextBox");
	public static By guarantorLastName                         = By.id("txtGurantorLastName_TextBox");
	public static By patientssn0                                    = By.id("stbSsn_00");
	public static By patientssn1                                    = By.id("stbSsn_01");
	public static By patientssn2                                    = By.id("stbSsn_02");
	public static By zipAddress                                     = By.id("txtZipCode_TextBox");
	public static By patientMonth                                    = By.id("dtbDob_Month");
	public static By patientDay                                    = By.id("dtbDob_Day");
	public static By patientYear                                    = By.id("dtbDob_Year");
	
	
	

	public static String existingPatientzipaddress;
	public static String existingPatientfirstname;
	public static String existingPatientlastname;
	public static String existingPatientrecordnumber;
	public static String existingPatientssn0;
	public static String existingPatientssn1;
	public static String existingPatientssn2;
	public static String existingPrimaryInsurance;
	public static String existingSecondaryInsurance;
	public static String existingPatientFromAge;
	public static String existingPatientToAge;
	public static String existingPatientdateofbirthmonth;
	public static String existingPatientdateofbirthday;
	public static String existingPatientdateofbirthyear;
	
	/**contains the Demographics page data*/
	public static Map<String, String> demographicsData;
		
	/** These are the variables which are present on "Demographics" sheet in the excel*/
	public static final String PATIENT_FIRSTNAME 				                   = "patientfirstname";
	public static final String PATIENT_LASTNAME 				                   = "patientlastname";
	public static final String PATIENT_RECORDNUMBER 				               = "recordnumber";
	public static final String PATIENT_ZIPADDRESS 				                   = "zipaddress";
	public static final String PATIENT_SSN0 				                       = "ssn0";
	public static final String PATIENT_SSN1 				                       = "ssn1";
	public static final String PATIENT_SSN2 				                       = "ssn2";
	public static final String PRIMARY_INSURANCE 				                   = "primarycarriername";
	public static final String SECONDARY_INSURANCE 				                   = "secondarycarriername";
	public static final String PATIENT_FROM_AGE 				                   = "patientFromAge";
	public static final String PATIENT_TO_AGE 				                       = "patientToAge";
	public static final String PATIENT_DATEOFBIRTHMONTH 				           = "month";
	public static final String PATIENT_DATEOFBIRTHDAY 				               = "day";
	public static final String PATIENT_DATEOFBIRTHYEAR 				               = "year";
	public static final String PATIENT_SSNNUMBER 				                  =  "socialsecurity";
	public static final String PATIENT_AGE 				                           = "patientAge";
	

	public void initClass() throws Exception{
		logger.info("inside the initClass method for DemographicsTest test class....");
		demographicsData                                 = ExcelFileUtilty.readExcelSheet("Demographics");
		existingPatientfirstname                         = demographicsData.get(PATIENT_FIRSTNAME);
		existingPatientlastname                          = demographicsData.get(PATIENT_LASTNAME);
		existingPatientrecordnumber                      = demographicsData.get(PATIENT_RECORDNUMBER);
		existingPatientzipaddress                        = demographicsData.get(PATIENT_ZIPADDRESS);
		existingPatientssn0                              = demographicsData.get(PATIENT_SSN0);
		existingPatientssn1                              = demographicsData.get(PATIENT_SSN1);
		existingPatientssn2                              = demographicsData.get(PATIENT_SSN2);
		existingPrimaryInsurance                         = demographicsData.get(PRIMARY_INSURANCE);
		existingSecondaryInsurance                       = demographicsData.get(SECONDARY_INSURANCE);
		existingPatientFromAge                           = demographicsData.get(PATIENT_FROM_AGE);
		existingPatientToAge                             = demographicsData.get(PATIENT_TO_AGE);
		existingPatientdateofbirthmonth                  = demographicsData.get(PATIENT_DATEOFBIRTHMONTH);
		existingPatientdateofbirthday                    = demographicsData.get(PATIENT_DATEOFBIRTHDAY);
		existingPatientdateofbirthyear                   = demographicsData.get(PATIENT_DATEOFBIRTHYEAR);
	
	}
	
	/**
	 * This method is used to enter valid mail id of  the patient for registering in patient portal
	 * 
	 */
	public void enterValidEmailId(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.getElementWithFluentWait(mailID).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		String patientMailId =ConstantsFile.loginData.get(ConstantsFile.VALID_PATIENT_MAILID);
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
	 * This method is used to click on insurance tab for patient
	 * 
	 */
	public void clickOnInsuranceTab(){
		WebElement insurancetabButton =SeleniumUtil.getElementWithFluentWait(insuranceTab);
		action.moveToElement(insurancetabButton).doubleClick().build().perform();
		try {
			System.out.println("hndle mrn");
			LeftPanelPage.handleSameMRN();
		} catch (FindFailed e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sleep(5000);	
	}
	/**
	 * This method is used to click on chart accessible  plus sign present in demographics
	 * 
	 */
	public void clickOnChartAccessibleSign(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.getElementWithFluentWait(chartAccessiblePlusSign).click();
	}
	/**
	 * This method is used to click on chart accessible  plus sign present in demographics
	 * 
	 */
	public void addUserInChartAccessible(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("search the logged in user by first name & last name");
		//get the  data in the Demogrphics sheet present in the excel
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Demographics");
		ConstantsFile.breakTheGlassuser1=ConstantsFile.loginData.get(ConstantsFile.BREAK_THE_GLASS_USER1);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(ConstantsFile.breakTheGlassuser1);
		logger.info("clicking on search button...");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.searchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(5000);
		logger.info("select the filtered row...");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		logger.info("select the user for adding user in chart Accessible...");
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to save patient details in demographics Module
	 * 
	 */
	public void savePatientInDemographicsPresentInPatientChart(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();
	}
	/**
	 * This method is used to verify Demographics Module is opened  in the patient chart
	 * 
	 */
	public boolean verifyDemographicsModuleOpened(){
		boolean isPatientInfoTabPresent=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		String patientInfoText=SeleniumUtil.getElementWithFluentWait(patientInfoTab1).getText();
		if(patientInfoText.equalsIgnoreCase(" Patient Info ")){
			System.out.println("patient Info Tab should be opned");
			isPatientInfoTabPresent=true;
		}
		return isPatientInfoTabPresent;
}
	/**
	 * This method is used to verify modified  Demographics  data is added in patient info tab 
	 * 
	 */
	public boolean verifyModifiedDataInDemographics(){
		boolean isDataAdded=false;	
		String patientss0value=SeleniumUtil.getElementWithFluentWait(patientssn0).getAttribute("value");
		String patientss1value=SeleniumUtil.getElementWithFluentWait(patientssn1).getAttribute("value");
		String patientss2value=SeleniumUtil.getElementWithFluentWait(patientssn2).getAttribute("value");
		String patientZipAddress =SeleniumUtil.getElementWithFluentWait(zipAddress).getAttribute("value");
		String patientMailAddress =SeleniumUtil.getElementWithFluentWait(mailIDTextBox).getAttribute("value");
		
		if(patientss0value.contains(existingPatientssn0) && patientss1value.contains(existingPatientssn1) && patientss2value.contains(existingPatientssn2) &&  patientZipAddress.contains(existingPatientzipaddress) &&  patientMailAddress.contains(PatientChart.patientMailId)){
			System.out.println("All the modified data is present");
			isDataAdded=true;
		}
		return isDataAdded;
}
	/**
	 * This method is used to verify Users should be added in chart Accessible Tab present in Demographics Module
	 * 
	 */
	public boolean verifyChartAccessibleTab(){
		boolean isUserAddedInChartAccessable=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		///Get the data to create new user present in excel
		List<WebElement> totaltrrows = driver.findElements(authorizedUserInchart);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowText= irows.getText();
			if(rowText.contains(ConstantsFile.breakTheGlassuser1) || rowText.contains(ConstantsFile.mainUser)){
				logger.info("user are added in chart accessable ");
				isUserAddedInChartAccessable=true;
			}
		}
		return isUserAddedInChartAccessable;
	}
	/**
	 * This method is used to remove Users  in chart Accessible Tab present in Demographics Module
	 * @throws FindFailed 
	 * 
	 */
	public void removeUserInChart() throws FindFailed{
		
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		///Get the data to create new user present in excel
		List<WebElement> totaltrrows = driver.findElements(authorizedUserInchart);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowText= irows.getText();
			SeleniumUtil.rightClick(irows);
			SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("deleteUser");
		}
		
	}
	/**
	 * This method is used to add record number in Demographics Module
	 * @throws FindFailed 
	 * 
	 */
	public void addRecordNumberInDemographics(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.patientrecordnumber).clear();
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.patientrecordnumber).sendKeys(existingPatientrecordnumber);
		
	}
	/**
	 * This method is used to add zip address in Demographics Module
	 * @throws FindFailed 
	 * 
	 */
	public void addZipAddressInDemographics(){
		SeleniumUtil.getElementWithFluentWait(zipAddress).clear();
		SeleniumUtil.getElementWithFluentWait(zipAddress).sendKeys(existingPatientzipaddress);	
	}
	/**
	 * This method is used to add random data zip address,recordnumber in Demographics Module
	 * 
	 * 
	 */
	public void addRamdomFieldsDataInDemographics(){
		addRecordNumberInDemographics();
		addZipAddressInDemographics();
		addSSNInDemographics();
		addDateOfBirthInDemographics();
	}
	/**
	 * This method is used to add zip address in Demographics Module
	 * @throws FindFailed 
	 * 
	 */
	public void addSSNInDemographics(){
		
		SeleniumUtil.getElementWithFluentWait(patientssn0).clear();
		SeleniumUtil.getElementWithFluentWait(patientssn0).sendKeys(existingPatientssn0);
		SeleniumUtil.getElementWithFluentWait(patientssn1).sendKeys(existingPatientssn1);
		SeleniumUtil.getElementWithFluentWait(patientssn2).sendKeys(existingPatientssn2);	
	}
	/**
	 * This method is used to add date of birth address in Demographics Module
	 * @throws FindFailed 
	 * 
	 */
	public void addDateOfBirthInDemographics(){
		SeleniumUtil.getElementWithFluentWait(patientMonth).sendKeys(existingPatientdateofbirthmonth);
		SeleniumUtil.getElementWithFluentWait(patientDay).sendKeys(existingPatientdateofbirthday);
		SeleniumUtil.getElementWithFluentWait(patientYear).sendKeys(existingPatientdateofbirthyear);	
	}

	/**
	 * This method is used to click on insurance tab for patient
	 * 
	 */
	public void enterPrimaryInsuranceDetails(){
		sleep(5000);
		//SeleniumUtil.switchToParentFrame(Frames.INSURANCE);
		SeleniumUtil.getElementWithFluentWait(carrierDropDown).click();
		//SeleniumUtil.waitForProgressBar(Frames.INSURANCE);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(existingPrimaryInsurance);
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.searchPatientInMedical).click();
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.filteredrrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);	
	}
	/**This method will verify if existing primary & secondary insurance are added in demographics module ,then delete the existing insurance details
	 * 
	 * @throws FindFailed
	 */
	public void verifyAddedInsurancesDetail() throws FindFailed{
		sleep(5000);
		/*DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");*/
		driver.switchTo().frame("fraInsurance_Frame");
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowtext =irows.getText();
			System.out.println("row text is"+rowtext);
			if(rowtext.contains(existingPrimaryInsurance) || rowtext.contains(existingSecondaryInsurance)){
				System.out.println("Primary & Secondary insurances are added in demographics");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("selectDelete");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");			
			}
			else{
				System.out.println("No existing insurance are present");
			}
			
		}
		
	}
	/**
	 * This method is used to copy ssn number in Policy Id Text box on insurance tab for patient
	 * 
	 */
	public void copySocialSecurityNumber(){
		//SeleniumUtil.switchToParentFrame(Frames.INSURANCE);
		SeleniumUtil.getElementWithFluentWait(policyID).click();
		//SeleniumUtil.waitForProgressBar(Frames.INSURANCE);
		logger.info("saving insurance details...");
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		//SeleniumUtil.waitForProgressBar(Frames.INSURANCE);
	}
	/**
	 * This method is used to switch into demographics Frame  
	 */
	public void switchToDemographicsFrame(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
	}
	
	/**
	 * This method is used to click on insurance tab for patient
	 * @throws FindFailed 
	 * 
	 */
	public void enterSecondaryInsuranceDetails() throws FindFailed{
		sleep(5000);
		clickOnNewButtonInDemographics();
		selectSecondaryInsuranceInDemographics();
		//SeleniumUtil.switchToParentFrame(Frames.INSURANCE);
		SeleniumUtil.getElementWithFluentWait(carrierDropDown).click();
		//SeleniumUtil.waitForProgressBar(Frames.INSURANCE);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(existingSecondaryInsurance);
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.searchPatientInMedical).click();
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.filteredrrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		//selectMedicareInsuranceInDemographics();
	}
	/**
	 * This method is used to click on new Button in demographics  
	 */
	public void clickOnNewButtonInDemographics(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		driver.switchTo().frame("fraInsurance_Frame");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton).click();
	}
	/**
	 * This method is used to click on new Button in demographics  
	 * @throws FindFailed 
	 */
	public void selectSecondaryInsuranceInDemographics() throws FindFailed{
		switchToDemographicsInsuranceFrame();
		SeleniumUtil.getElementWithFluentWait(By.id("ddlInsurancePlanType_Text")).sendKeys(Keys.ARROW_DOWN);
		//SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("selectCarrierType");
		//SeleniumUtil.clickOnImageWitScreenInSikuli("selectSecondary");
		
	}
	/**
	 * This method is used to click on new Button in demographics  
	 * @throws FindFailed 
	 */
	public void selectMedicareInsuranceInDemographics() throws FindFailed{
		switchToDemographicsInsuranceFrame();
		SeleniumUtil.getElementWithFluentWait(By.id("ddlMedicareSupplemental_DropDown")).click();
		//SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("selectMedicare");
		SeleniumUtil.clickOnImageWitScreenInSikuli("selectNoFault");
		switchToDemographicsInsuranceFrame();
	}
	
	/**This method will verify primary & secondary insurance are added in demographics module
	 * 
	 * @throws FindFailed
	 */
	public boolean verifyInsurancesDetail() throws FindFailed{
		sleep(5000);
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		driver.switchTo().frame("fraInsurance_Frame");
		boolean isInsurancesAreAdded=false;
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowtext =irows.getText();
			System.out.println("row text is"+rowtext);
			if(rowtext.contains(existingPrimaryInsurance) || rowtext.contains(existingSecondaryInsurance)){
				System.out.println("Primary & Secondary insurances are added in demographics");
				//SeleniumUtil.rightClick(irows);
				//SeleniumUtil.clickOnImageWitScreenInSikuli("selectDelete");
				isInsurancesAreAdded=true;
			}
		}
		return isInsurancesAreAdded;
	}
	/**
	 * This method is used to switch into demographics Insurance Frame  
	 */
	public void switchToDemographicsInsuranceFrame(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		driver.switchTo().frame("fraInsurance_Frame");
		driver.switchTo().frame("DialogBox_Frame");
		
	}
	/**
	 * This method is used to click on insurance tab for patient
	 * 
	 */
	public void clickOnGuarantorTab(){
		switchToDemographicsFrame();
		SeleniumUtil.getElementWithFluentWait(guarantorTab).click();	
}
	/**
	 * This method is used to verify Guarantor data in  Guarantor tab for patient
	 * 
	 */
	public boolean verifyGuarantorData(){
		boolean isGuarantorDataPresent=false;
		String guarantorFirstname = SeleniumUtil.getElementWithFluentWait(guarantorFirstName).getAttribute("value");
		String guarantorLastname = SeleniumUtil.getElementWithFluentWait(guarantorLastName).getAttribute("value");
		if(guarantorFirstname.contains(existingPatientfirstname)&& guarantorLastname.contains(existingPatientlastname)){
			System.out.println("Guarantor Data is present");
			isGuarantorDataPresent=true;
		}
		return isGuarantorDataPresent;
	}
	/**
	 * This method is used to switch into demographics Insurance inside Frame  
	 */
	public void switchToDemographicsInsideInsuranceFrame(){
		driver.switchTo().frame("DialogBox_Frame");
		
	}
	/**
	 * This method is used to verify insurance info tab Demographics Module is opened  in the patient chart
	 * 
	 */
	public boolean verifyInsuranceTabOpened(){
		boolean isPatientInfoTabPresent=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		String patientInfoText=SeleniumUtil.getElementWithFluentWait(insuranceTab).getText();
		if(patientInfoText.equalsIgnoreCase(" Insurance ")){
			System.out.println("patient Info Tab should be opned");
			isPatientInfoTabPresent=true;
		}
		return isPatientInfoTabPresent;
}
}
