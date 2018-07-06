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
import com.gpm.pages.CollectionPage;

public class MedicalChartPage extends BaseAbstractPage {
	/** Logger to log the LoginPage log messages */
	private static Logger logger                        = LogManager.getLogger(MedicalChartPage.class); 
	public static By  medicalcharttext                              = By.id("tabMedicalCharts_Text");	
	public static By newbutton                                      = By.id("btnNew_SpanBGColor");	
	public static By firstname                                      = By.id("txtFirstName_TextBox");
	public static By lastname                                       = By.id("txtLastName_TextBox");
	public static By patientdDobMonth                               = By.id("dtbDob_Month");
	public static By patientdDobDay                                 = By.id("dtbDob_Day");
	public static By patientdDobYear                                = By.id("dtbDob_Year");
	public static By patientrecordnumber                            = By.id("txtOrganizationRecordNumber_TextBox");
	public static By patientrecordnumberInSearch                    = By.id("txtRecordNumber_TextBox");	
	public static By savebutton                                     = By.id("btnSave_SpanBGColor");
	public static By dismissGuidelinebutton                         = By.id("btnHideNotification_Table");
	public static By patientnametextbox                             = By.id("txtName_TextBox");
	public static By searchPatientInMedical                         = By.id("btnFiltersSearch_TextSpan");
	public static By selectbutton                                   = By.id("btnSelect_SpanBGColor");
	public static By homeAddressLine1                               = By.id("txtHomeAddressLine1_TextBox");
	public static By insuranceTab                                   = By.id("tabInsurance");	
	public static By billingTab                                     = By.id("tabBilling");
	public static By alertCheckbox                                  = By.id("chkAlert_Render");
	public static By billingNotes                                   = By.id("txtBillingNotes_TextBox");
	public static By billingGroupDropDown                           = By.id("txtBillingGroupDropDown");
	public static By patientMobile00                                = By.id("ptbCellPhone_00");
	public static By patientMobile01                                = By.id("ptbCellPhone_01");
	public static By patientMobile02                                = By.id("ptbCellPhone_02");
	public static By accountNumberSign                              = By.id("tblAccountNumbersCollectionAdd");
	public static By accountNumberTextBox                           = By.id("txtAccountNumber_TextBox");
	public static By policyIdTextBox                                = By.id("txtPolicyId_TextBox");
	public static By patientNo00                                    = By.id("ptbPhone_00");
	public static By patientNo01                                    = By.id("ptbPhone_01");
	public static By patientNo02                                    = By.id("ptbPhone_02");
	public static By patientFormTitle                               = By.id("Popup_TitleContent");
	public static By patientDetailFormTitle                         = By.id("lblTemplateName");
	public static By addressLine2                                   = By.id("txtHomeAddressLine2_TextBox");
	public static By popupClose                                     = By.id("Popup_CloseButton");
	public static By selectStatusPlusSign                           = By.id("txtPatientStatusAdd");
	public static By checkSelectButton                              = By.id("btnSelect");
	public static By importPatient                                  = By.id("btnImport_SpanBGColor");
	//---------
	public static By chartTitleTextBox                              = By.id("txtTitle_TextBox");
	public static By chartDescTextBox                               = By.id("txtDescription_TextBox");
	public static By saveButton1                                    = By.id("btnSave");
	public static By textMessage                                    = By.id("txtMessage_TextBox");
	public static By buttonView                                     = By.id("btnViews");
	public static By mnuNewChartNote                                 = By.id("mnuNewChartNote");
	public static By mnuNewPatientPortal                             = By.id("mnuNewPatientPortalMessage");
	public static By searchResult                                    = By.xpath("//table[@class='tableData']/tbody/tr/td[1]");
	public static By searchResult1                                   = By.xpath("//*[text() = 'Bhuwan']");
	public static By AVGeneralCheckBox                                = By.xpath("//li/ul/li[@class='rtLI']/ul[@class='rtUL']/li[@class='rtLI']/div[@class='rtTop']/label/input[@class='rtChk']");
	public static String xpath1                                       = "//table[@class='ToolbarMenuNoBorder']/tbody/tr[" ;
	public static String xpath2                                       = "]/control/td[3]" ;
	public static By menunewoption                                  = By.id("mnuViewsNew");

	/**contains the medical page data*/
	public static Map<String, String> medicalData;
	
	/**These are the variables which are used to store different data for medical chart module*/
	public static String existingPatientfirstname;
	public static String existingPatientlastname;
	public static String existingpatientssn0;
	public static String existingpatientssn1;
	public static String existingpatientssn2;
	public static String existingpatientdateofbirthmonth;
	public static String existingpatientdateofbirthday;
	public static String existingpatientdateofbirthyear;
	public static String existingpatientrecordnumber;
	public static String existingpatientphonenumber0;
	public static String existingpatientphonenumber1;
	public static String existingpatientphonenumber2;
	public static String existingpatientaccountnumber;
	
	/** These are the variables which are present on "Medical chart" sheet in the excel*/
	public static final String MEDICAL_PATIENT_FIRSTNAME 				                   = "patientfirstname";
	public static final String MEDICAL_PATIENT_LASTNAME 				                   = "patientlastname";
	public static final String MEDICAL_PATIENT_SSN0 				                       = "ssn0";
	public static final String MEDICAL_PATIENT_SSN1 				                       = "ssn1";
	public static final String MEDICAL_PATIENT_SSN2 				                       = "ssn2";
	public static final String MEDICAL_PATIENT_DATEOFBIRTHMONTH 				           = "month";
	public static final String MEDICAL_PATIENT_DATEOFBIRTHDAY 				               = "day";
	public static final String MEDICAL_PATIENT_DATEOFBIRTHYEAR 				               = "year";
	public static final String MEDICAL_PATIENT_RECORDNUMBER 			                   = "recordnumber";
	public static final String MEDICAL_PATIENT_PHONENUMBER0 			                   = "phonenumber0";
	public static final String MEDICAL_PATIENT_PHONENUMBER1 			                   = "phonenumber1";
	public static final String MEDICAL_PATIENT_PHONENUMBER2 			                   = "phonenumber2";
	public static final String MEDICAL_PATIENT_ACCOUNTNUMBER 			                   = "accountnumber";
	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for medical chart test class....");
		medicalData                     = ExcelFileUtilty.readExcelSheet("MedicalChart");
		existingPatientfirstname        = medicalData.get(MEDICAL_PATIENT_FIRSTNAME);
		existingPatientlastname         = medicalData.get(MEDICAL_PATIENT_LASTNAME);
		existingpatientssn0             = medicalData.get(MEDICAL_PATIENT_SSN0);
		existingpatientssn1             = medicalData.get(MEDICAL_PATIENT_SSN1);
		existingpatientssn2             = medicalData.get(MEDICAL_PATIENT_SSN2);
		existingpatientdateofbirthmonth = medicalData.get(MEDICAL_PATIENT_DATEOFBIRTHMONTH);
		existingpatientdateofbirthday   = medicalData.get(MEDICAL_PATIENT_DATEOFBIRTHDAY);
		existingpatientdateofbirthyear  = medicalData.get(MEDICAL_PATIENT_DATEOFBIRTHYEAR);
		existingpatientrecordnumber     = medicalData.get(MEDICAL_PATIENT_RECORDNUMBER);
		existingpatientphonenumber0     = medicalData.get(MEDICAL_PATIENT_PHONENUMBER0);
		existingpatientphonenumber1     = medicalData.get(MEDICAL_PATIENT_PHONENUMBER1);
		existingpatientaccountnumber    = medicalData.get(MEDICAL_PATIENT_ACCOUNTNUMBER);
		existingpatientphonenumber2     = medicalData.get(MEDICAL_PATIENT_PHONENUMBER2);
	
	}
	
	/**
	 * This method is used to create new patient with the below mentioned details:
	 * <p>first name</p>
	 * <p>last name</p>
	 * <p>Date Of Birth</p>
	 * <p>SSN Number</p>
	 * <p>Record Number</p>
	 * <p>providers </p>
	 * <p>referring physicians section </p>
	 * 
	 */
	public static void createNewPatient() {
		//switching into configuration frame
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on medical chart menu to create new patient...");
		SeleniumUtil.getElementWithFluentWait(medicalcharttext).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		staticSleep(3000);
		//switching into medical chart main page frame
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
		logger.info("clicking on new patient button..");
		SeleniumUtil.getElementWithFluentWait(newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		//switching into medical chart subpage frame
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_SUBPAGE);
		//create the random patient first name
		ConstantsFile.patientfirstname = ConstantsFile.patientIntialname.concat(ConstantsFile.genData.generateRandomNumber(2));
		logger.info("entering the first name...." +ConstantsFile.patientfirstname);
		SeleniumUtil.getElementWithFluentWait(firstname).sendKeys(ConstantsFile.patientfirstname);
		ConstantsFile.patientlastname = ConstantsFile.genData.generateRandomString(3);
		logger.info("entering the last name...."+ConstantsFile.patientlastname);
		SeleniumUtil.getElementWithFluentWait(lastname).sendKeys(ConstantsFile.patientlastname);
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_SUBPAGE);
		staticSleep(3000);
		SeleniumUtil.getElementWithFluentWait(patientdDobMonth).sendKeys("12");
		SeleniumUtil.getElementWithFluentWait(patientdDobDay).sendKeys("12");
		SeleniumUtil.getElementWithFluentWait(patientdDobYear).sendKeys("2012");
		ConstantsFile.SSN0=ConstantsFile.genData.generateRandomNumber(3);
		ConstantsFile.SSN1=ConstantsFile.genData.generateRandomNumber(2);
		ConstantsFile.SSN2=ConstantsFile.genData.generateRandomNumber(4);
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn0).sendKeys(ConstantsFile.SSN0);
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn1).sendKeys(ConstantsFile.SSN1);
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn2).sendKeys(ConstantsFile.SSN2);
		ConstantsFile.MRN=ConstantsFile.genData.generateRandomAlphaNumeric(9);
		SeleniumUtil.getElementWithFluentWait(patientrecordnumber).sendKeys(ConstantsFile.MRN);			
	}
	/**
	 * This method is used to save patient details while creating new patient
	 */
	public void savePatientDetails(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_SUBPAGE);
		logger.info("saving the patient details...");
		SeleniumUtil.getElementWithFluentWait(savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_SUBPAGE);
		ConstantsFile.isPatientCreated = true;
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
	 * This method is used to search the created patient
	 *
	 */
	public  boolean searchPatientNameInMedical(){
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on medical chart menu to create new patient...");
		SeleniumUtil.getElementWithFluentWait(medicalcharttext).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		sleep(3000);
		//switching into medical chart main page frame
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(ConstantsFile.patientfirstname);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(ConstantsFile.patientlastname);
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);
		SeleniumUtil.getElementWithFluentWait(selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		return ConstantsFile.isPatientChartPresent;
	}
	/**
	 * This method is user to create new patient with the below mentioned details:
	 * <p>first name</p>
	 * <p>last name</p>
	 * <p>providers </p>
	 * <p>referring physicians section </p>
	 * 
	 */
	public static void createNewPatientWithoutInsurance() {
		//switching into configuration frame
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on medical chart menu to create new patient...");
		SeleniumUtil.getElementWithFluentWait(medicalcharttext).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//switching into medical chart main page frame
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
		logger.info("clicking on new patient button..");
		SeleniumUtil.getElementWithFluentWait(newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		//switching into medical chart subpage frame
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_SUBPAGE);
		//create the random patient first name
		ConstantsFile.patientfirstnamewithoutIns = ConstantsFile.patientIntialname.concat(ConstantsFile.genData1.generateRandomNumber(3));
		logger.info("entering the first name...." +ConstantsFile.patientfirstnamewithoutIns);
		SeleniumUtil.getElementWithFluentWait(firstname).sendKeys(ConstantsFile.patientfirstnamewithoutIns);
		ConstantsFile.patientlastnamewithoutIns = ConstantsFile.genData1.generateRandomString(3);
		logger.info("entering the last name...."+ConstantsFile.patientlastnamewithoutIns);
		SeleniumUtil.getElementWithFluentWait(lastname).sendKeys(ConstantsFile.patientlastnamewithoutIns);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SeleniumUtil.getElementWithFluentWait(patientdDobMonth).sendKeys("12");
		SeleniumUtil.getElementWithFluentWait(patientdDobDay).sendKeys("12");
		SeleniumUtil.getElementWithFluentWait(patientdDobYear).sendKeys("1996");
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn0).sendKeys(ConstantsFile.genData1.generateRandomNumber(3));
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn1).sendKeys(ConstantsFile.genData1.generateRandomNumber(2));
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn2).sendKeys(ConstantsFile.genData1.generateRandomNumber(4));
		ConstantsFile.MRNWithoutIns=ConstantsFile.genData1.generateRandomAlphaNumeric(5);
		SeleniumUtil.getElementWithFluentWait(patientrecordnumber).sendKeys(ConstantsFile.MRNWithoutIns);	
	}
	/**
	 * This method is used to enter patient details while creating new patient
	 */
	public void createPatientAddress(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_SUBPAGE);
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.zipAddress).sendKeys("10003");
		SeleniumUtil.getElementWithFluentWait(homeAddressLine1).sendKeys("350 5th Avenue");
	}
	

	/**
	 * This method is used to click on insurance tab for patient
	 * 
	 */
	public void clickOnBillingTab(){
		SeleniumUtil.switchToParentFrame(Frames.BILLING);
		SeleniumUtil.getElementWithFluentWait(billingTab).click();	
		SeleniumUtil.waitForProgressBar(Frames.BILLING);
	}
	/**
	 * This method is used to enable billing alerts  for patient
	 * 
	 */
	public void enableBillingSettings(){
		SeleniumUtil.switchToParentFrame(Frames.BILLING);
		SeleniumUtil.getElementWithFluentWait(alertCheckbox).click();
		SeleniumUtil.waitForProgressBar(Frames.BILLING);
		SeleniumUtil.getElementWithFluentWait(billingNotes).sendKeys(ConstantsFile.billingnotes);
	}
	/**
	 * This method is used to select billing group  for patient
	 * 
	 */
	public void selectBillingDropDown(){
		SeleniumUtil.getElementWithFluentWait(billingGroupDropDown).click();
		SeleniumUtil.waitForProgressBar(Frames.BILLING);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.filteredrrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to click on insurance tab for patient
	 * 
	 */
	public void clickOnInsuranceTab(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_SUBPAGE);
		WebElement insurancetabButton =SeleniumUtil.getElementWithFluentWait(insuranceTab);
		action.moveToElement(insurancetabButton).doubleClick().build().perform();
		try {
			System.out.println("hndle mrn");
			PatientChart.handleSameMRN();
		} catch (FindFailed e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sleep(5000);	
	}
	/**
	 * This method is used to click on Medical chart Present in left panel
	 *
	 */
	public void clickOnMedicalChartFromLeftPanel(){
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on medical chart menu to create new patient...");
		SeleniumUtil.getElementWithFluentWait(medicalcharttext).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		sleep(3000);
	}
	/**
	 * This method is used to search the created patient with Name
	 *
	 */
	public  void searchPatientWithName(){
		//switching into medical chart main page frame
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(ConstantsFile.patientfirstname);
		//SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys("Tomcat93");
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(ConstantsFile.patientlastname);
		//SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys("nUO");
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);	
	}
	
	/**
	 * This method is used to search the created patient with Name
	 *
	 */
	public  void searchExistingPatientWithName(String patientfirstname,String patientlastname){
		//switching into medical chart main page frame
		//SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(patientfirstname);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(patientlastname);
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		//SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);	
	}
	/**
	 * This method is used to search the created patient with Name
	 *
	 */
	public  boolean verifyPatientIsSerchedWithName(String patientfirstname,String patientlastname){
		boolean isPatientSearched =false;
		sleep(5000);
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF ROWS IN THIS TABLE = "+searchPatientrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : searchPatientrow)
		{
		    List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
		    System.out.println("NUMBER OF COLUMNS="+td_collection.size());
		    col_num=1;
		    for(WebElement tdElement : td_collection)
		    {
		    	String rowText=tdElement.getText();
		        System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
		        col_num++;
		        if(rowText.equalsIgnoreCase(patientfirstname)){
		        	System.out.println("corrected patient row is displayed");
		        	tdElement.click();
		        	isPatientSearched=true;
		        	break;
		    }
		    
		} 
		    row_num++;
		}
		return isPatientSearched;
	}
	/**
	 * This method is used to enter patient phone number while creating new patient
	 */
	public void enterPatientPhoneDetails(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_SUBPAGE);
		ConstantsFile.mobile00 =ConstantsFile.genData1.generateRandomNumber(3);
		ConstantsFile.mobile01 =ConstantsFile.genData1.generateRandomNumber(3);
		ConstantsFile.mobile02 =ConstantsFile.genData1.generateRandomNumber(4);
		SeleniumUtil.getElementWithFluentWait(patientMobile00).sendKeys(ConstantsFile.mobile00);
		SeleniumUtil.getElementWithFluentWait(patientMobile01).sendKeys(ConstantsFile.mobile01);
		SeleniumUtil.getElementWithFluentWait(patientMobile02).sendKeys(ConstantsFile.mobile02);
	}
	/**
	 * This method is used to click on account number plus button present in the end of page while creating new patient
	 */
	public void clickOnAccountPlusSign(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_SUBPAGE);
		SeleniumUtil.getElementWithFluentWait(accountNumberSign).click();
	}

	/**
	 * This method is used to enter patient account Number number while creating new patient
	 */
	public void enterPatientAccountDetails(){
		clickOnAccountPlusSign();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		ConstantsFile.accountNumber =ConstantsFile.genData1.generateRandomNumber(10);
		SeleniumUtil.getElementWithFluentWait(accountNumberTextBox).sendKeys(ConstantsFile.accountNumber);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();
	}
	public void clearFliter(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
		SeleniumUtil.getElementWithFluentWait(CollectionPage.clearButton).click();
	}
	/**
	 * This method is used to search the created patient with SSN
	 *
	 */
	public  void searchPatientWithSSN(){
		clearFliter();
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn0).sendKeys(ConstantsFile.SSN0);
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn1).sendKeys(ConstantsFile.SSN1);
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn2).sendKeys(ConstantsFile.SSN2);
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);
	}
	/**
	 * This method is used to search the created patient with SSN
	 *
	 */
	public  void searchExistingPatientWithSSN(){
		clearFliter();
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn0).sendKeys(MedicalChartPage.existingpatientssn0);
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn1).sendKeys(MedicalChartPage.existingpatientssn1);
		SeleniumUtil.getElementWithFluentWait(DemographicsPage.patientssn2).sendKeys(MedicalChartPage.existingpatientssn2);
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);
	}
	/**
	 * This method is used to search the created patient with SSN
	 *
	 */
	public  boolean verifyPatientIsSerchedWithSSN(){
		boolean isPatientSearchedwithSSN =false;
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(MedicalChartPage.existingpatientssn0) && rowtext.contains(MedicalChartPage.existingpatientssn1) && rowtext.contains(MedicalChartPage.existingpatientssn2)){
				System.out.println("corrected patient with SSN row is displayed");
				isPatientSearchedwithSSN=true;
				break;
			}
		}
		return isPatientSearchedwithSSN;
	}
	/**
	 * This method is used to search the created patient with SSN
	 *
	 */
	public  void searchExistingPatientWithDOB(){
		clearFliter();
		SeleniumUtil.getElementWithFluentWait(patientdDobMonth).sendKeys(MedicalChartPage.existingpatientdateofbirthmonth);
		SeleniumUtil.getElementWithFluentWait(patientdDobDay).sendKeys(MedicalChartPage.existingpatientdateofbirthday);
		SeleniumUtil.getElementWithFluentWait(patientdDobYear).sendKeys(MedicalChartPage.existingpatientdateofbirthyear);
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);
	}
	/**
	 * This method is used to search the created patient with Date Of Birth
	 *
	 */
	public  boolean verifyPatientIsSerchedWithDOB(){
		boolean isPatientSearchedwithDOB =false;
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(MedicalChartPage.existingpatientdateofbirthmonth)  && rowtext.contains(MedicalChartPage.existingpatientdateofbirthday) && rowtext.contains(MedicalChartPage.existingpatientdateofbirthyear)){
				System.out.println("corrected patient with Date of Birth row is displayed");
				isPatientSearchedwithDOB=true;
				break;
			}
		}
		return isPatientSearchedwithDOB;
	}
	/**
	 * This method is used to search the created patient with SSN
	 *
	 */
	public  void searchPatientWithPolicyId(){
		clearFliter();
		SeleniumUtil.getElementWithFluentWait(policyIdTextBox).sendKeys(MedicalChartPage.existingpatientssn0);
		SeleniumUtil.getElementWithFluentWait(policyIdTextBox).sendKeys(MedicalChartPage.existingpatientssn1);
		SeleniumUtil.getElementWithFluentWait(policyIdTextBox).sendKeys(MedicalChartPage.existingpatientssn2);
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);
	}
	/**
	 * This method is used to search the created patient with Policy ID
	 *
	 */
	public  boolean verifyPatientIsSerchedWithPolicyID(){
		boolean isPatientSearchedwithPolicyID =false;
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(MedicalChartPage.existingpatientssn0) && rowtext.contains(MedicalChartPage.existingpatientssn1) && rowtext.contains(MedicalChartPage.existingpatientssn2)){
				System.out.println("corrected patient with policy Id row is displayed");
				isPatientSearchedwithPolicyID=true;
				break;
			}
		}
		return isPatientSearchedwithPolicyID;
	}

	/**
	 * This method is used to search the created patient with Record Number
	 *
	 */
	public  void searchExistingPatientWithRecordNumber(){
		clearFliter();
		SeleniumUtil.getElementWithFluentWait(patientrecordnumberInSearch).sendKeys(MedicalChartPage.existingpatientrecordnumber);
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);
	}
	/**
	 * This method is used to search the created patient with Record Number
	 *
	 */
	public  boolean verifyPatientIsSerchedWithRecordNumber(){
		boolean isPatientSearchedwithRecordNumber =false;
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(MedicalChartPage.existingpatientrecordnumber)){
				System.out.println("corrected patient with record number row is displayed");
				isPatientSearchedwithRecordNumber=true;
				break;
			}
		}
		return isPatientSearchedwithRecordNumber;
	}
	/**
	 * This method is used to search the created patient with Phone Number
	 *
	 */
	public  void searchPatientWithPhoneNumber(){
		clearFliter();
		SeleniumUtil.getElementWithFluentWait(patientNo00).sendKeys(MedicalChartPage.existingpatientphonenumber0);
		SeleniumUtil.getElementWithFluentWait(patientNo01).sendKeys(MedicalChartPage.existingpatientphonenumber1);
		SeleniumUtil.getElementWithFluentWait(patientNo02).sendKeys(MedicalChartPage.existingpatientphonenumber2);
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);
	}
	/**
	 * This method is used to search the created patient with Account Number
	 *
	 */
	public  void searchPatientWithAccountNumber(){
		clearFliter();
		SeleniumUtil.getElementWithFluentWait(accountNumberTextBox).sendKeys(MedicalChartPage.existingpatientaccountnumber);
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);
	}

	/**
	 * This method is used to select Right click for the searched patient 
	 * @throws FindFailed 
	 *
	 */
	public  void rClickOnSerchedPatient(String optionName) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(MedicalChartPage.existingPatientfirstname) && rowtext.contains(MedicalChartPage.existingPatientlastname)){
				System.out.println("corrected patient row is displayed");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWithTargetOffsetInSikuli(optionName);
				break;
			}
		}
	}
	/**
	 * This method is used to verify when user R click on View Option Patient View Form Should be Opened
	 *
	 */
	public boolean verifyPatientViewFormIsOpened(){
		boolean isPatientViewFormOpen =false;
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
		String patientFormTitleName = SeleniumUtil.getElementWithFluentWait(patientFormTitle).getText();
		if(patientFormTitleName.equalsIgnoreCase("Patient View Form")){
			logger.info("Patient View Form Is Opened");
			SeleniumUtil.getElementWithFluentWait(popupClose).click();
			isPatientViewFormOpen=true;	
		}	
		return isPatientViewFormOpen;
	}
	/**
	 * This method is used to verify when user R click on View Option Patient View Form Should be Opened
	 *
	 */
	public boolean verifyPatientDetailsFormIsOpened(){
		boolean isPatientFormOpen =false;
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_SUBPAGE);
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_SUBPAGE);
		String patientFormTitleName = SeleniumUtil.getElementWithFluentWait(patientDetailFormTitle).getText();
		if(patientFormTitleName.equalsIgnoreCase("Patient Form")){
			logger.info("Patient  Form Is Opened");
			isPatientFormOpen=true;	
		}	
		return isPatientFormOpen;
	}
	/**
	 * This method is used to modify random fields in the patient details form 
	 *
	 */
	public void modifyPatientDetails(){
		boolean isPatientFormOpen =false;
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_SUBPAGE);
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_SUBPAGE);
		SeleniumUtil.getElementWithFluentWait(addressLine2).clear();
		SeleniumUtil.getElementWithFluentWait(addressLine2).sendKeys("test");
	}
	/**
	 * This method is used to verify when user modify some fiels in patient creation form & check again that it should display modified Data
	 *
	 */
	public boolean verifyModifiedDataForPatient(){
		boolean isPatientDataModified =false;
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_SUBPAGE);
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_SUBPAGE);
		String patientModifiedAddress = SeleniumUtil.getElementWithFluentWait(addressLine2).getAttribute("value");
		if(patientModifiedAddress.equalsIgnoreCase("test")){
			logger.info("Patient Modified Data is Displayed");
			//SeleniumUtil.getElementWithFluentWait(addressLine2).sendKeys(Keys.ESCAPE);
			//SeleniumUtil.getElementWithFluentWait(addressLine2).clear();
			
			isPatientDataModified=true;	
		}	
		return isPatientDataModified;
	}

	/**
	 * This method is used to search the created patient with status
	 *
	 */
	public  void searchPatientWithStatus(String statusName){
		//switching into medical chart main page frame
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		SeleniumUtil.getElementWithFluentWait(selectStatusPlusSign).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		List<WebElement> totalStatusRows =driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totalStatusRows){
			String rowtext =irows.getText();
			if(rowtext.contains(statusName)){
				irows.click();
			}
		}
			SeleniumUtil.getElementWithFluentWait(selectbutton).click();
			SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
			SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
			SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();	
		}
	
	/**
	 * This method is used to verify when user RClick on Undelete option then the searched patient should disappear
	 *
	 */
	public  boolean verifyRowIsDisappeared(){
		boolean isRowDisappear =false;
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(MedicalChartPage.existingPatientfirstname) && rowtext.contains(MedicalChartPage.existingPatientlastname)){
				System.out.println(" patient row is not Disappered when patient is restored");
				isRowDisappear=false;
				break;
			}
		}
		logger.info("patient row is Disappered when patient is restored");
		isRowDisappear=true;
		return isRowDisappear;
	}
	
	/**
	 * This method is used to switch into medical frame 
	 *
	 */
	public void switchToMedicalFrame(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
	}
	/**
	/**
	 * This method is used to select the searched patient in medical chart
	 *
	 */
	public void selectPatient(){
		SeleniumUtil.getElementWithFluentWait(selectbutton).click();
	}
	/**
	 * This method is used to verify select button is disable for protected patient
	 *
	 */
	public boolean verfiySelectIsDisable(){
		boolean isSelectButtonDisable=false;
	//Boolean selectButton =	SeleniumUtil.getElementWithFluentWait(selectbutton).getAttribute("enabled")
		String selectButton =	SeleniumUtil.getElementWithFluentWait(checkSelectButton).getAttribute("enabled");
		System.out.println(selectButton);
	if(selectButton.equalsIgnoreCase("false")){
		logger.info("Select Button is disabled");
		isSelectButtonDisable=true;
	}
		return isSelectButtonDisable;
	}
	/**
	 * This method is used to import the patient  in medical chart
	 *
	 */
	public void importPatient(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
		SeleniumUtil.getElementWithFluentWait(importPatient).click();	
	}
	/**
	 * This method is used to click on consolidate Patient  in medical chart
	 *
	 */
	public void consolidatePatient(){
		SeleniumUtil.switchToParentFrame(com.gmed.Frames.Frames.CREATION);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();	
	}
	/**
	 * This method is used for save the details
	 * 
	 */
	public void clickOnSaveButton(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to search the created patient
	 *
	 */
	public  boolean searchPatientNamewithoutInsMedical(){
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on medical chart menu to create new patient...");
		SeleniumUtil.getElementWithFluentWait(medicalcharttext).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		sleep(3000);
		//switching into medical chart main page frame
		SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(ConstantsFile.patientfirstnamewithoutIns);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(ConstantsFile.patientlastnamewithoutIns);
		SeleniumUtil.getElementWithFluentWait(searchPatientInMedical).click();
		ConstantsFile.isPatientChartPresent =true;
		sleep(3000);
		SeleniumUtil.getElementWithFluentWait(selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICALCHART_MAINPAGE);
		return ConstantsFile.isPatientChartPresent;
	}
	
	/**
	 * Searching the Patient by Name and click on it.
	 * @param patientname
	 */
	public void searchPatientByName(String patientname) {
		
	       logger.info("Inside searchPatientByName method.");
	        SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
	        SeleniumUtil.getElementWithFluentWait(MedicalChartPage.medicalcharttext).click();
	        SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
	        SeleniumUtil.getElementWithFluentWait(MedicalChartPage.patientnametextbox).sendKeys(patientname);
	        SeleniumUtil.getElementWithFluentWait(MedicalChartPage.searchPatientInMedical).click();
	        SeleniumUtil.elewait(MedicalChartPage.searchResult);
	        WebElement searchresult = SeleniumUtil.getElementWithFluentWait(MedicalChartPage.searchResult);
	        SeleniumUtil.doubleClick(searchresult);
		}
	
	/**
	 * This method is used to click on Colonoscopy under the New Button on Patient Chart Page
	 * 
	 */
	public void clickOnProcedure(String procedurename){
		logger.info("Inside the clickOnProcedure Method");
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.getElementWithFluentWait(newbutton).click();
		SeleniumUtil.switchToParentFrame(Frames.PROCEDURE);
		WebElement FirstVisitvalue= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ procedurename +"')]"));
		SeleniumUtil.scrolltoWebElement(FirstVisitvalue);
		FirstVisitvalue.click();
		SeleniumUtil.waitForProgressBar(Frames.PROCEDURE);
		
		try {
			logger.info("Colonoscopy for today already exists");
			SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
			SeleniumUtil.getElementWithFluentWait(newbutton).click();
			SeleniumUtil.switchtoTopFrame();
			DynamicFramePage.dynamicFrameForPanchart();
			DynamicFramePage.switchtoFraFrame();
		}
		catch(Exception e) {
			
		}
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void clickOnMenuItem(By menuitem){
		logger.info("Inside the clickOnMenuItem Method");
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.getElementWithFluentWait(newbutton).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(menuitem).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//This method will verify if the ChartNote is created and if created then it is displayed correctly within Document list page
	public boolean verifyChartNotecreated(String string) {
	logger.info("Inside the verifyChartNoteCreated Method");
    SeleniumUtil.switchtoTopFrame();
    DynamicFramePage.dynamicFrameForPanchart();
	DynamicFramePage.switchtoFraFrame();
	String text = SeleniumUtil.getElementWithFluentWait(DocumentPage.docElementTask).getText();
	System.out.println(text);
	if(text.contains(string)){
		System.out.println("Pass");
		return true;
	}
	System.out.println("Fail");
	return false;

		}
	
	// This method will Click on any of the Named Chart View from the dropdown based on the value passed as Parameter
	public void clickOnChartWithName(String viewtext) {
		logger.info("Inside the clickOnChartView1 Method");
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		int i = 1;
		while(!(SeleniumUtil.getElementWithFluentWait(By.xpath(xpath1+i+xpath2)).getText().equalsIgnoreCase(viewtext))){
			System.out.println(SeleniumUtil.getElementWithFluentWait(By.xpath(xpath1+i+xpath2)).getText());
			i++;
		}
		SeleniumUtil.getElementWithFluentWait(By.xpath(xpath1+i+xpath2)).click();
	}
	
	/**
	 *  
	 * This method will verify if the Actual Chart view in application is as per the expected one.
	 */
	
	public boolean verifyChartView(String chartview, boolean bool) {
		logger.info("Inside the Method verifyChartView");
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		String text = SeleniumUtil.getElementWithFluentWait(DocumentPage.docElementChart).getText();
		boolean result = false;
		if((text.contains(chartview) ^ bool)) {
			result = true;
			System.out.println(result);
			return result;
		}
		System.out.println(result);
		return result;
		}
	
	/**
	 * This method will click on Chart View Tab then click on the New button from the dropdown.
	 */
	public void clickOnChartViewNewOption(){
		logger.info("Inside the clickOnChartView Method");
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		logger.info("Clciking on ChartView Tab");
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.buttonView).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("Clicking on New Button");
		SeleniumUtil.getElementWithFluentWait(menunewoption).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will click on Chart View Tab then click on the New button from the dropdown.
	 */
	public void enterChartTitle() {
    SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
    ConstantsFile.charttitle = "AutomatedNote".concat((ConstantsFile.genData.generateRandomString(3)));
    SeleniumUtil.getElementWithFluentWait(MedicalChartPage.chartTitleTextBox).sendKeys(ConstantsFile.charttitle);
    SeleniumUtil.getElementWithFluentWait(MedicalChartPage.chartDescTextBox ).sendKeys(ConstantsFile.charttitle);
    SeleniumUtil.getElementWithFluentWait(MedicalChartPage.saveButton1).click();
	}
	
	/**
	 * Switching from PanTask Frame to FraPatientFrame
	 */
	
	public void switchToFraPatientFrame() {
        SeleniumUtil.switchtoTopFrame();
        SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
        SeleniumUtil.getElementWithFluentWait(MedicalChartPage.medicalcharttext).click();
        SeleniumUtil.switchToParentFrame(Frames.MEDICALCHART_MAINPAGE);
	}
	
	/**
	 * Entering the mail subject and description in the Mail
	 */
	public void enterMailSubject() {
        SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
        ConstantsFile.mailsubject = "Subject".concat(ConstantsFile.genData.generateRandomString(3));
        SeleniumUtil.getElementWithFluentWait(TaskPage.taskSubjectTextbox ).sendKeys(ConstantsFile.mailsubject);
        SeleniumUtil.getElementWithFluentWait(MedicalChartPage.textMessage).sendKeys(ConstantsFile.mailsubject);
        SeleniumUtil.getElementWithFluentWait(MedicalChartPage.saveButton1).click();
	}
	
	/**
	 * Switching to TopFrame
	 */
	public void toTopFrame() {
		SeleniumUtil.switchtoTopFrame();
	}
	
	/**
	 * Entering the Chart Name
	 */
	public void enterChartName() {
        SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
        ConstantsFile.chartname = "Chart".concat(ConstantsFile.genData.generateRandomString(3));
        SeleniumUtil.getElementWithFluentWait(MedicalChartPage.patientnametextbox).sendKeys(ConstantsFile.chartname);
        SeleniumUtil.getElementWithFluentWait(MedicalChartPage.AVGeneralCheckBox).click();
        SeleniumUtil.getElementWithFluentWait(MedicalChartPage.checkSelectButton).click();
        SeleniumUtil.getElementWithFluentWait(MedicalChartPage.saveButton1).click();
	}
	
	/**
	 * Switching to FraFrame
	 */
	public void toFraFrame() {
		   DynamicFramePage.dynamicFrameForPanchart();
   		   DynamicFramePage.switchtoFraFrame();
	}
	/**
	 * Clicking on Chart View
	 */
	public void clickOnChartView() {
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.buttonView).click();
	}
	
	/**
	 * Switching to PopUp Frame
	 */
	public void toPopUpFrame() {
	    SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
        SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
            try {
    			Thread.sleep(1000);
    		    } catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		    }
	}
	/**
	 * Clicking on DismissToday button
	 */
	public void dismissGuideline() {
		SeleniumUtil.switchtoTopFrame();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		try{
			SeleniumUtil.getElementWithFluentWait(dismissGuidelinebutton).click();
		}
		catch(Exception e){
			System.out.println("No tool tip is found");
		}
	  }
}

