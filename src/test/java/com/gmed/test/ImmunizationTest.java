package com.gmed.test;



import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.base.BaseTestClass;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.OrdersPage;
import com.gmed.pages.PatientChart;
import com.gmed.pages.Profile;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;




public class ImmunizationTest extends BaseTestClass {
	/** Logger to log the Immunization log messages */
	private static Logger logger  = LogManager.getLogger(ImmunizationTest.class); 
	/**Assertion to verify different elements of the page */
	private Assertion immunizationAssert = new Assertion();
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	
	
	/** MedicalChart Page reference for initiating the medical data present in medical page */
	private MedicalChartPage medicalchartpage;
	
	/** PatientChart Page reference used for various patient data */
	private PatientChart patientpageobj;
	
	/** Profile Page reference used for using profile functions*/
	private Profile profilepageobj;
	/** DocumentPage page reference used for documenting   data for  Immunization module*/
	private DocumentPage documentobj;
	
	/** Order Page reference used for using clicking on new button function for Profile Screen  */
	private OrdersPage orderpageobj;
	
//	/**contains the Immunization page data*/
//	public static Map<String, String> immunizationData;
//	
//	/**These are the variables which are used to store different data for immunization module*/
//	public static String DocumentPage.existingImmunizationPatientfirstname;
//	public static String existingImmunizationPatientlastname;
//	public static String addNewImmunizationForPatient;
//	
//	/** These are the variables which are present on "Immunization" sheet in the excel*/
//	public static final String PATIENT_FIRSTNAME 				                   = "patientfirstname";
//	public static final String PATIENT_LASTNAME 				                   = "patientlastname";
//	public static final String ADD_NEW_IMMUNIZATION 				               = "addnewcustomimmunization";
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for DemographicsTest test class....");
//		immunizationData                                 = ExcelFileUtilty.readExcelSheet("Immunization");
//		DocumentPage.existingImmunizationPatientfirstname             = immunizationData.get(PATIENT_FIRSTNAME);
//		existingImmunizationPatientlastname              = immunizationData.get(PATIENT_LASTNAME);
//		addNewImmunizationForPatient                     = immunizationData.get(ADD_NEW_IMMUNIZATION);
		loginPageObj                                     = new LoginPage();
		leftPanelpageobj                                 = new LeftPanelPage();
		medicalchartpage                                 = new MedicalChartPage();
		patientpageobj                                   = new PatientChart();
		profilepageobj                                   = new Profile();
		documentobj                                      = new DocumentPage();
		orderpageobj                                     = new OrdersPage();
		documentobj.initClass();
	}
	@Test(description = "To verify Record Immunization details in Service . ",groups = { "Immunization_Regression" },priority=1)
	public void verifyImmunizationsInProfile() throws FindFailed{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		immunizationAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		logger.info("clicking on medical chart menu present in left panel...");
		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DocumentPage.existingImmunizationPatientfirstname,DocumentPage.existingImmunizationPatientlastname);
		immunizationAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DocumentPage.existingImmunizationPatientfirstname,DocumentPage.existingImmunizationPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		immunizationAssert.assertEquals(patientpageobj.verifyPatientChartIsOpened1(DocumentPage.existingImmunizationPatientfirstname,DocumentPage.existingImmunizationPatientlastname), true);
		logger.info("verify if existing service is present then delete it");
		profilepageobj.verifyAddedServicesDetails();
		logger.info("create first visit service for the patient  in patient chart screen.....");
		patientpageobj.clickOnProcedure("First Visit");
		logger.info("click on immunization section present in first visit service.....");
		documentobj.clickOnImmunizationSectionInService();
		logger.info("clicking on administer in immunization");
		documentobj.switchInImunizationFrame();
		documentobj.deleteAddedImmunizationDetails();
		logger.info("clicking on new button for adding new Immunization details in service screen.....");
		orderpageobj.clickOnNewButton();
		logger.info("add Immunization details for creating new Immunization data");
		profilepageobj.addImmunizationsInProfileScreen();
		medicalchartpage.clickOnSaveButton();
		logger.info("verify correct immunization details are added in service");
		immunizationAssert.assertTrue(documentobj.verifyImmunizationDetailInService());	
		leftPanelpageobj.clickOnLogout();	
	}

}