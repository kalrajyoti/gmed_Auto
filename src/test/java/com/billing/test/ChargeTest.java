package com.billing.test;

import static com.gmed.utils.StringUtility.stringContainsCsv;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.base.BaseTestClass;
import com.gmed.pages.ConfigurationPage;
import com.gmed.pages.DemographicsPage;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.PatientChart;
import com.gmed.pages.PrescriptionPage;
import com.gmed.pages.Profile;
import com.gmed.pages.ReportPage;

import com.gmed.test.CQMReport;
import com.gmed.test.DemographicsTest;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gpm.pages.BillingPage;
import com.gpm.pages.PostedChargePage;

public class ChargeTest extends BaseTestClass {
	/** Logger to log the Charge log messages */
	private static Logger logger  = LogManager.getLogger(ChargeTest.class); 
	/**Assertion to verify different elements of the page */
	private Assertion chargeAssert = new Assertion();
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	
	/** MedicalChart Page reference for initiating the medical data present in medical page */
	private MedicalChartPage medicalchartpage;
	/** PatientChart Page reference used for various patient data */
	private PatientChart patientchartobj;
	
	/** PostedCharge Page reference used for various charges data */
	private PostedChargePage chargesPageobj;
	
	/** billing Page reference used for various billing data */
	private BillingPage billingPageobj;
//	
//	/**contains the Prescription page data*/
//	public static Map<String, String> chargeData;
//
//
//	/**contains the demographics Chart page data*/
//	public static Map<String, String> demographicsData;
//
//	/**contains the Profile page data*/
//	public static Map<String, String> preData2;
//
//
//
//	/**These are the variables which are used to store different data for Charges module*/
//	public static String chargesHeaders;
//	public static String billNumber;
//	
//	/** These are the variables which are present on "Billing" sheet in the excel*/
//	public static final String CHARGE_HEADER 				                                   = "chargesColums";
//	public static final String BILL_NUMBER 				                                       = "billNumber";

	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for charges test class....");
//		chargeData                                                                     = ExcelFileUtilty.readExcelSheet("Billing");
//		demographicsData                                                               = ExcelFileUtilty.readExcelSheet("Demographics");
//		DemographicsPage.existingPatientfirstname                                      = demographicsData.get(DemographicsPage.PATIENT_FIRSTNAME);	
//		DemographicsPage.existingPatientlastname                                       = demographicsData.get(DemographicsPage.PATIENT_LASTNAME);
//		chargesHeaders                                                                 = chargeData.get(CHARGE_HEADER);
//		billNumber                                                                     = chargeData.get(BILL_NUMBER);
		loginPageObj                                                                   = new LoginPage();
		leftPanelpageobj                                                               = new LeftPanelPage();
		medicalchartpage                                                               = new MedicalChartPage();
		patientchartobj                                                                = new PatientChart();
		chargesPageobj                                                                 = new PostedChargePage();
		billingPageobj                                                                 = new BillingPage();
		chargesPageobj.initClass();
}
@Test(description = "To verify List of Charges colums in the charges queue",groups = { "charges_Regression","Smoke"},priority=1)
	public void verifyChargesQueueHeaders() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		chargeAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		chargeAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		chargeAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened1(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		medicalchartpage.dismissGuideline();
		
		logger.info("clicking on billing drop down menu present in left panel");
		patientchartobj.clickOnBillingDropDown();
		logger.info("select the account inquiry option from drop down");
		patientchartobj.clickOnAccountInquiry();
		chargesPageobj.switchToAccountInquiryFrame();
		chargesPageobj.switchToChargeFrame();
		logger.info("Verify Charges headers in charges queue ");
		chargeAssert.assertTrue(stringContainsCsv(chargesPageobj.verifyChargesHeaders(), PostedChargePage.chargesHeaders));
		logger.info("logging out from application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify search filter in charges queue",groups = { "charges_Regression","Smoke"},priority=2)
	public void verifyChargesSearchFilter() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		chargeAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		chargeAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		chargeAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened1(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		medicalchartpage.dismissGuideline();
		
		logger.info("clicking on billing drop down menu present in left panel");
		patientchartobj.clickOnBillingDropDown();
		logger.info("select the account inquiry option from drop down");
		patientchartobj.clickOnAccountInquiry();
		chargesPageobj.switchToAccountInquiryFrame();
		chargesPageobj.switchToChargeFrame();
		logger.info("clicking on search fliter...");
		chargesPageobj.clickOnSearchFilter();
		logger.info("enter bill number");
		chargesPageobj.enterBillNumber();
		logger.info("clicking on search fliter...");
		billingPageobj.clickOnSearchButton();
		logger.info("Verify Charges row in charges queue ");
		chargeAssert.assertTrue(chargesPageobj.verifyChargesQueue());
		logger.info("logging out from application");
		leftPanelpageobj.clickOnLogout();
	}
}
