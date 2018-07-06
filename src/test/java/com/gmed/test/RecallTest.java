package com.gmed.test;

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
import com.gmed.pages.FaxingPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.OutputManagerPage;
import com.gmed.pages.PatientChart;
import com.gmed.pages.PrescriptionPage;
import com.gmed.pages.Profile;
import com.gmed.pages.RecallsPage;
import com.gmed.patientportal.PatientPortalLogin;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;

public class RecallTest extends BaseTestClass {
	/** Logger to log the Recall log messages */
	private static Logger logger  = LogManager.getLogger(RecallTest.class); 
	/**Assertion to verify different elements of the page */
	private Assertion recallAssert = new Assertion();
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	/** Configuration Page reference for initiating the Configuration data present in Configuration page */
	private ConfigurationPage configpageobj;
	/** Recall Page reference used for various Recall data */
	private RecallsPage recallpageobj;
	

	/**contains the Output Manager page data*/
	public static Map<String, String> recallData;


	/**contains the demographics Chart page data*/
	public static Map<String, String> demographicsData;

	/**contains the Profile page data*/
	public static Map<String, String> profileData;

	/**These are the variables which are used to store different data for Recall module*/
	public static String cleanupOperationText;
	public static String signOperationText;
	public static String printOperationText;
	public static String faxOperationText;
	public static String existingPatientfirstname;
	public static String existingPatientlastname;
	public static String publishToPortalOperationText;
	public static String documentText;
	public static String sendForSignatureOperationText;
	public static String sendForSignatureText;
	public static String sendForReviewOperationText;
	public static String sendForReviewText;
	
	/** These are the variables which are present on "Recall" sheet in the excel*/
	public static final String CLEAN_UP_TEXT 				                                  = "cleanupOperationText";
	public static final String SIGN_TEXT 				                                      = "signOperationText";
	public static final String PRINT_TEXT 				                                      = "printOperationText";
	public static final String FAX_TEXT 				                                      = "faxOperationText";
	public static final String PATIENT_FIRSTNAME 				                              = "patientfirstname";
	public static final String PATIENT_LASTNAME 				                              = "patientlastname";
	public static final String PUBLISH_TO_PORTAL_TEXT 				                          = "publishOperationText";
	public static final String DOCUMENT_TEXT 				                                  = "documentTextInPP";
	public static final String SEND_FOR_SIGNATURE_TEXT 				                          = "sendForSignatureOperationText";
	public static final String SIGNATURE_TEXT 				                                  = "signText";
	public static final String SEND_FOR_REVIEW_TEXT 				                          = "sendForReviewOperationText";
	public static final String REVIEW_TEXT 				                                      = "reviewText";
	
	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for Prescription test class....");
		recallData                                                                     = ExcelFileUtilty.readExcelSheet("OutputManager");
		demographicsData                                                               = ExcelFileUtilty.readExcelSheet("Demographics");
		profileData                                                                    = ExcelFileUtilty.readExcelSheet("Profile");
		DemographicsPage.existingPatientfirstname                                      = demographicsData.get(DemographicsPage.PATIENT_FIRSTNAME);	
		DemographicsPage.existingPatientlastname                                       = demographicsData.get(DemographicsPage.PATIENT_LASTNAME);
		Profile.existingProfileProviderfirstname                                   = profileData.get(Profile.PROFILE_PROVIDER_FIRSTNAME);
		Profile.existingProfileProviderlastname                                    = profileData.get(Profile.PROFILE_PROVIDER_LASTNAME);
		Profile.userNameForAdvanceDirectiveProfile                                 = profileData.get(Profile.PROFILE_USER_NAME);
		cleanupOperationText                                                           = recallData.get(CLEAN_UP_TEXT) ; 
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
		sendForReviewText                                                              = recallData.get(REVIEW_TEXT);
		loginPageObj                                                                   = new LoginPage();
		leftPanelpageobj                                                               = new LeftPanelPage();
	    configpageobj                                                                  = new ConfigurationPage();
		recallpageobj                                                                  = new RecallsPage();
	}
	@Test(description = "To verify Recall Types from Configurations-",groups = { "Recall_Regression","Configuration_Regression"},priority=1)
	public void verifyRecallTypes() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		recallAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on Configuration menu present in left panel...");
		leftPanelpageobj.selectLeftMainMenu("Configuration");
		configpageobj.switchToConfigurationMainFrame();
		logger.info("clicking on Recall Types in configuration module");
		leftPanelpageobj.clickOnToolBar("Recall Types");	
		logger.info("clicking on New Button for creating new Recall");
		configpageobj.clickOnNew();
		logger.info("Entering recall details for creating new recall");
		recallpageobj.enterRecallDetails();
		logger.info("saving the recall details for creating new recall");
		configpageobj.clickOnSaveButton();	
		recallAssert.assertTrue(recallpageobj.verifyRecallIsCreated());
		logger.info("Verify Recall Status is Active By Default");
		recallAssert.assertTrue(recallpageobj.verifyRecallStatus());
	    logger.info("Logging out from application");
		leftPanelpageobj.clickOnLogout();
		
	}
}
