package com.gmed.test;

import static com.gmed.helper.DriverFactory.getAnotherDriverInstance;
import static com.gmed.helper.DriverFactory.openPatientPortalURL;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.base.BaseTestClass;
import com.gmed.pages.ConfigurationPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.PatientChart;
import com.gmed.pages.PatientInterviewForm;
import com.gmed.pages.RecallsPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.GmedConfig;

public class PifTest extends BaseTestClass {
	/** Logger to log the Recall log messages */
	private static Logger logger  = LogManager.getLogger(RecallTest.class); 
	/**Assertion to verify different elements of the page */
	private Assertion pifAssert = new Assertion();
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	/** Configuration Page reference for initiating the Configuration data present in Configuration page */
	private ConfigurationPage configpageobj;
	/** PIF Page reference used for various PIF data */
	private PatientInterviewForm pifobj;
	
	/** MedicalChart Page reference for initiating the medical data present in medical page */
	private MedicalChartPage medicalchartpage;
	/** PatientChart Page reference used for various patient data */
	private PatientChart patientpageobj;
	
//	/**contains the pifr page data*/
//	public static Map<String, String> pifData;
//
//
//	/**contains the demographics Chart page data*/
//	public static Map<String, String> demographicsData;
//
//	/**contains the Profile page data*/
//	public static Map<String, String> profileData;
//
//	/**These are the variables which are used to store different data for Recall module*/
//	public static String cleanupOperationText;
//	public static String signOperationText;
//	public static String printOperationText;
//	public static String faxOperationText;
//	public static String existingPatientfirstname;
//	public static String existingPatientlastname;
//	public static String publishToPortalOperationText;
//	public static String documentText;
//	public static String sendForSignatureOperationText;
//	public static String sendForSignatureText;
//	public static String sendForReviewOperationText;
//	public static String sendForReviewText;
//	
//	/** These are the variables which are present on "Recall" sheet in the excel*/
//	public static final String CLEAN_UP_TEXT 				                                  = "cleanupOperationText";
//	public static final String SIGN_TEXT 				                                      = "signOperationText";
//	public static final String PRINT_TEXT 				                                      = "printOperationText";
//	public static final String FAX_TEXT 				                                      = "faxOperationText";
//	public static final String PATIENT_FIRSTNAME 				                              = "patientfirstname";
//	public static final String PATIENT_LASTNAME 				                              = "patientlastname";
//	public static final String PUBLISH_TO_PORTAL_TEXT 				                          = "publishOperationText";
//	public static final String DOCUMENT_TEXT 				                                  = "documentTextInPP";
//	public static final String SEND_FOR_SIGNATURE_TEXT 				                          = "sendForSignatureOperationText";
//	public static final String SIGNATURE_TEXT 				                                  = "signText";
//	public static final String SEND_FOR_REVIEW_TEXT 				                          = "sendForReviewOperationText";
//	public static final String REVIEW_TEXT 				                                      = "reviewText";
	
	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for Prescription test class....");
//		pifData                                                                        = ExcelFileUtilty.readExcelSheet("OutputManager");
//		demographicsData                                                               = ExcelFileUtilty.readExcelSheet("Demographics");
//		profileData                                                                    = ExcelFileUtilty.readExcelSheet("Profile");
//		DemographicsTest.existingPatientfirstname                                      = demographicsData.get(DemographicsTest.PATIENT_FIRSTNAME);	
//		DemographicsTest.existingPatientlastname                                       = demographicsData.get(DemographicsTest.PATIENT_LASTNAME);
//		ProfileTest.existingProfileProviderfirstname                                   = profileData.get(ProfileTest.PROFILE_PROVIDER_FIRSTNAME);
//		ProfileTest.existingProfileProviderlastname                                    = profileData.get(ProfileTest.PROFILE_PROVIDER_LASTNAME);
//		ProfileTest.userNameForAdvanceDirectiveProfile                                 = profileData.get(ProfileTest.PROFILE_USER_NAME);
/*		cleanupOperationText                                                           = recallData.get(CLEAN_UP_TEXT) ; 
		signOperationText                                                              = recallData.get(SIGN_TEXT) ;
		printOperationText                                                             = recallData.get(PRINT_TEXT) ;
		faxOperationText                                                               = recallData.get(FAX_TEXT) ;
		existingPatientfirstname                                                       = recallData.get(PATIENT_FIRSTNAME);
		existingPatientlastname                                                        = recallData.get(PATIENT_LASTNAME);
		publishToPortalOperationText                                                   = recallData.get(PUBLISH_TO_PORTAL_TEXT);
		documentText                                                                   = recallData.get(DOCUMENT_TEXT);
		sendForSignatureOperationText                                                  = recallData.get(SEND_FOR_SIGNATURE_TEXT);
		sendForSignatureText                                                           = recallData.get(SIGNATURE_TEXT);
		sendForReviewOperationText                                                     = recallData.get(SEND_FOR_REVIEW_TEXT);
		sendForReviewText                                                              = recallData.get(REVIEW_TEXT);*/
		loginPageObj                                                                   = new LoginPage();
		leftPanelpageobj                                                               = new LeftPanelPage();
	    configpageobj                                                                  = new ConfigurationPage();
	    pifobj                                                                         = new PatientInterviewForm();
	    medicalchartpage                                                               = new MedicalChartPage();
	    patientpageobj                                                                 = new PatientChart();
	    pifobj.initClass();
	}
	@BeforeGroups(groups = { "Global_PIF" })
	public void launchGlobalPIF() throws FindFailed {
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		pifAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on Configuration menu present in left panel...");
		leftPanelpageobj.selectLeftMainMenu("Configuration");
		configpageobj.switchToConfigurationMainFrame();
		logger.info("clicking on Recall Types in configuration module");
		leftPanelpageobj.clickOnToolBar("Interview"+'\n'+"Forms");	
		pifobj.verifyGlobalPifPresent();
		if(PatientInterviewForm.isGlobalPifPresent){
			System.out.println("Global PIf Is Already Present");
		}
		else if(PatientInterviewForm.isGlobalPifPresent==false){
			System.out.println("Launch Global PiF");
			pifobj.launchGlobalPif();
			}
		}


	@Test(description = "The purpose of this test case is to create a new PIF for all locations ",groups = { "PIF_Regression","Global_PIF"},priority=1)
	public void verifyRecallTypes() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		pifAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName("automation","patient");
		pifAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName("automation","patient"), true);
		/*leftPanelpageobj.selectLeftMainMenu("Configuration");
		configpageobj.switchToConfigurationMainFrame();
		logger.info("clicking on Recall Types in configuration module");
		leftPanelpageobj.clickOnToolBar("Recall Types");	
		logger.info("clicking on New Button for creating new Recall");
		configpageobj.clickOnNew();
		logger.info("Entering recall details for creating new recall");
		//recallpageobj.enterRecallDetails();
		logger.info("saving the recall details for creating new recall");
		configpageobj.clickOnSaveButton();	
		//pifAssert.assertTrue(recallpageobj.verifyRecallIsCreated());
		logger.info("Verify Recall Status is Active By Default");*/
		//pifAssert.assertTrue(recallpageobj.verifyRecallStatus());
	    logger.info("Logging out from application");
		leftPanelpageobj.clickOnLogout();
		
	}

}
