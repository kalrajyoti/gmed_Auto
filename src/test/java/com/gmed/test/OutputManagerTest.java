package com.gmed.test;

import static com.gmed.helper.DriverFactory.getAnotherDriverInstance;
import static com.gmed.helper.DriverFactory.openPatientPortalURL;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.base.BaseTestClass;
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
import com.gmed.patientportal.PatientPortalLogin;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.GmedConfig;

public class OutputManagerTest extends BaseTestClass {
	/** Logger to log the OutputManagerTest log messages */
	private static Logger logger  = LogManager.getLogger(OutputManagerTest.class); 
	/**Assertion to verify different elements of the page */
	private Assertion outputAssert = new Assertion();
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	/** MedicalChart Page reference for initiating the medical data present in medical page */
	private MedicalChartPage medicalchartpage;
	/** PatientChart Page reference used for various patient data */
	private PatientChart patientpageobj;
	/** Profile Page reference used for verifying demographics data in Profile Screen  */
	private Profile profilepageobj;
	/** OutputManager page reference used for clicking on configuration option present in left panel */
	private OutputManagerPage outputpageobj;

	/** DocumentPage page reference used for documenting   data for  Output Manager module*/
	private DocumentPage documentobj;

	/** Prescription page reference used for clicking on print button*/
	private PrescriptionPage prescriptionobj;

	/** Faxing page reference used for using faxing data*/
	private FaxingPage faxobj;

	private PatientPortalLogin patientLogin;

//	/**contains the Output Manager page data*/
//	public static Map<String, String> outputData;
//
//
//	/**contains the demographics Chart page data*/
//	public static Map<String, String> demographicsData;
//
//	/**contains the Profile page data*/
//	public static Map<String, String> profileData;
//
//	/**These are the variables which are used to store different data for Output Manager module*/
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
//	/** These are the variables which are present on "Output Manager" sheet in the excel*/
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
//		outputData                                                                     = ExcelFileUtilty.readExcelSheet("OutputManager");
//		demographicsData                                                               = ExcelFileUtilty.readExcelSheet("Demographics");
//		profileData                                                                    = ExcelFileUtilty.readExcelSheet("Profile");
//		DemographicsTest.existingPatientfirstname                                      = demographicsData.get(DemographicsTest.PATIENT_FIRSTNAME);	
//		DemographicsTest.existingPatientlastname                                       = demographicsData.get(DemographicsTest.PATIENT_LASTNAME);
//		ProfileTest.existingProfileProviderfirstname                                   = profileData.get(ProfileTest.PROFILE_PROVIDER_FIRSTNAME);
//		ProfileTest.existingProfileProviderlastname                                    = profileData.get(ProfileTest.PROFILE_PROVIDER_LASTNAME);
//		ProfileTest.userNameForAdvanceDirectiveProfile                                 = profileData.get(ProfileTest.PROFILE_USER_NAME);
//		cleanupOperationText                                                           = outputData.get(CLEAN_UP_TEXT) ; 
//		signOperationText                                                              = outputData.get(SIGN_TEXT) ;
//		printOperationText                                                             = outputData.get(PRINT_TEXT) ;
//		faxOperationText                                                               = outputData.get(FAX_TEXT) ;
//		existingPatientfirstname                                                       = outputData.get(PATIENT_FIRSTNAME);
//		existingPatientlastname                                                        = outputData.get(PATIENT_LASTNAME);
//		publishToPortalOperationText                                                   = outputData.get(PUBLISH_TO_PORTAL_TEXT);
//		documentText                                                                   = outputData.get(DOCUMENT_TEXT);
//		sendForSignatureOperationText                                                  = outputData.get(SEND_FOR_SIGNATURE_TEXT);
//		sendForSignatureText                                                           = outputData.get(SIGNATURE_TEXT);
//		sendForReviewOperationText                                                     = outputData.get(SEND_FOR_REVIEW_TEXT);
//		sendForReviewText                                                              = outputData.get(REVIEW_TEXT);
		loginPageObj                                                                   = new LoginPage();
		leftPanelpageobj                                                               = new LeftPanelPage();
		medicalchartpage                                                               = new MedicalChartPage();
		outputpageobj                                                                  = new OutputManagerPage();
		patientpageobj                                                                 = new PatientChart();
		profilepageobj                                                                 = new Profile();
		documentobj                                                                    = new DocumentPage();
		prescriptionobj                                                                = new PrescriptionPage();
		faxobj                                                                         = new FaxingPage();
		patientLogin                                                                   = new PatientPortalLogin();
		outputpageobj.initClass();
	}

	@BeforeGroups(groups = { "Patient_Portal" })
	public void opendPatientPortal() {
		String patientPortalUrl =GmedConfig.getConfig("patientPortalLogin");
		System.out.println(patientPortalUrl);
		getAnotherDriverInstance(patientPortalUrl);
		openPatientPortalURL(patientPortalUrl);
	}


	@Test(description = "To veify the 'Clean Up' from Output Manager ",groups = { "OutputManger_Regression","Smoke"},priority=1)
	public void verifyCleanUp() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		outputAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		outputAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		outputAssert.assertEquals(patientpageobj.verifyPatientChartIsOpened1(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		medicalchartpage.dismissGuideline();

		
		logger.info("creating First visit service in patient chart...");
		patientpageobj.clickOnProcedure("First Visit");
		leftPanelpageobj.clickOnNewInToolTip();
		outputpageobj.switchToTemplateFrame();
		logger.info("Clicking on Process button present on Top of Template menus");
		leftPanelpageobj.clickOnToolBarMenus("Process");
		logger.info("Verify Output Manager Page is Opened...");
		outputAssert.assertTrue(leftPanelpageobj.verifyToolTipTitle("Output Manager"));	


		profilepageobj.switchToTooltip();
		logger.info("Clicking on Configuration tab of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Configuration ");
		outputpageobj.switchToOutputManagerFrame();
		logger.info("Perform Clean Up Operation on Visit Note Template ");
		outputpageobj.selectTemplateDocument("chkCleanUp","verifyCleanUp");

		profilepageobj.switchToTooltip();
		logger.info("Clicking on Summary Tab Of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Summary ");
		outputAssert.assertEquals(outputpageobj.verifySummaryTab(), true);

		outputpageobj.switchToTemplateFrame();

		outputAssert.assertEquals(outputpageobj.verifyCleanUpOperation(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify 'Sign' from Output Manager ",groups = { "OutputManger_Regression","Smoke"},priority=2)
	public void verifySignOperation() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		outputAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		outputAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		outputAssert.assertEquals(patientpageobj.verifyPatientChartIsOpened1(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		medicalchartpage.dismissGuideline();

		logger.info("creating First visit service in patient chart...");
		patientpageobj.clickOnProcedure("First Visit");
		leftPanelpageobj.clickOnNewInToolTip();
		outputpageobj.switchToTemplateFrame();

		logger.info("clicking on chief Complaint section present in first visit service");
		outputpageobj.clickOnChiefComplaint();
		logger.info("Verify Chief user list is opened in left panel");
		outputAssert.assertTrue(documentobj.verifyChiefUserListIsOpened());
		logger.info("Selecting chief value from the user list");
		documentobj.selectChiefValueFromUserList();
		logger.info("varify chief value is documented in the template");
		outputAssert.assertTrue(documentobj.verifyChiefValueIsDocumentedInUserList());
		logger.info("Clicking on Provider Section Present in First visit Service");
		outputpageobj.clickOnProvider();
		profilepageobj.switchToTooltip();
		outputpageobj.addProvider(Profile.existingProfileProviderfirstname, Profile.existingProfileProviderlastname);


		outputpageobj.switchToTemplateFrame();
		logger.info("Clicking on Coding button present on Top of Template menus");
		leftPanelpageobj.clickOnToolBarMenus("Coding");
		outputAssert.assertTrue(leftPanelpageobj.verifyToolTipTitle("Coding"));	
		documentobj.clickOnNPP();
		medicalchartpage.clickOnSaveButton();

		outputpageobj.switchToTemplateFrame();
		logger.info("Clicking on Process button present on Top of Template menus");
		leftPanelpageobj.clickOnToolBarMenus("Process");
		logger.info("Verify Output Manager Page is Opened...");
		outputAssert.assertTrue(leftPanelpageobj.verifyToolTipTitle("Output Manager"));	


		profilepageobj.switchToTooltip();
		logger.info("Clicking on Configuration tab of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Configuration ");
		outputpageobj.switchToOutputManagerFrame();
		logger.info("Perform sign Operation on Visit Note Template ");
		outputpageobj.selectTemplateDocument("chkSign","verifySignOperation");

		profilepageobj.switchToTooltip();
		logger.info("Clicking on Summary Tab Of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Summary ");
		outputAssert.assertEquals(outputpageobj.verifySummaryTab(), true);
		logger.info("clicking on Execute button for signing the document");
		documentobj.clickonExecute();

		outputpageobj.switchToTemplateFrame();
		logger.info("Verify Document is signed in the visit note Template");
		outputAssert.assertEquals(outputpageobj.verifyDocumentIsSigned(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify 'Print' from Output Manager ",groups = { "OutputManger_Regression","Smoke"},priority=3)
	public void verifyPrintOperation() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		outputAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		outputAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		outputAssert.assertEquals(patientpageobj.verifyPatientChartIsOpened1(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		medicalchartpage.dismissGuideline();

		logger.info("creating First visit service in patient chart...");
		patientpageobj.clickOnProcedure("First Visit");
		leftPanelpageobj.clickOnNewInToolTip();
		outputpageobj.switchToTemplateFrame();


		logger.info("Clicking on Process button present on Top of Template menus");
		leftPanelpageobj.clickOnToolBarMenus("Process");
		logger.info("Verify Output Manager Page is Opened...");
		outputAssert.assertTrue(leftPanelpageobj.verifyToolTipTitle("Output Manager"));	


		profilepageobj.switchToTooltip();
		logger.info("Clicking on Configuration tab of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Configuration ");
		outputpageobj.switchToOutputManagerFrame();
		logger.info("Perform Print Operation on Visit Note Template ");
		outputpageobj.selectTemplateDocument("print","verifyPrintOperation");

		profilepageobj.switchToTooltip();
		logger.info("Clicking on Summary Tab Of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Summary ");
		outputAssert.assertEquals(outputpageobj.verifySummaryTab(), true);

		prescriptionobj.clickPrint();  
		logger.info("Clicking on History button present on Top of Template menus");
		outputpageobj.switchToTemplateFrame();
		leftPanelpageobj.clickOnToolBarMenus("History");
		profilepageobj.switchToTooltip();
		logger.info("Verify Document is Printed in the visit note Template");
		outputAssert.assertEquals(outputpageobj.verifyPrintOperation(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify 'Fax' from Output Manager ",groups = { "OutputManger_Regression","Smoke"},priority=4)
	public void verifyFaxOperation() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		outputAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		outputAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		outputAssert.assertEquals(patientpageobj.verifyPatientChartIsOpened1(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		medicalchartpage.dismissGuideline();

		logger.info("creating First visit service in patient chart...");
		patientpageobj.clickOnProcedure("First Visit");
		leftPanelpageobj.clickOnNewInToolTip();
		outputpageobj.switchToTemplateFrame();


		logger.info("Clicking on Process button present on Top of Template menus");
		leftPanelpageobj.clickOnToolBarMenus("Process");
		logger.info("Verify Output Manager Page is Opened...");
		outputAssert.assertTrue(leftPanelpageobj.verifyToolTipTitle("Output Manager"));	


		profilepageobj.switchToTooltip();
		logger.info("Clicking on Configuration tab of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Configuration ");
		outputpageobj.switchToOutputManagerFrame();
		logger.info("Perform Fax Operation on Visit Note Template ");
		outputpageobj.selectTemplateDocument("fax","verifyFaxOperation");
		logger.info("select Provider for Faxing...");
		outputpageobj.selectProviderForFaxOperation(Profile.existingProfileProviderfirstname, Profile.existingProfileProviderlastname);
		profilepageobj.switchToTooltip();
		logger.info("Clicking on Summary Tab Of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Summary ");
		logger.info("Verify Fax Operation In Summary Tab");
		outputAssert.assertEquals(outputpageobj.verifySummaryTab(), true);

		logger.info("Clicking on Outbound Faxing queue");
		leftPanelpageobj.clickOnQueueManagement("clickOnOutboundFaxing");
		faxobj.switchToOutboundFaxingFrame();
		logger.info("Verify Document is Faxed in the visit note Template");
		outputAssert.assertTrue(faxobj.verifyOutboundFaxingQueue());
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To veify 'Publish to Portal' from Output Manager ",groups = { "OutputManger_Regression","Smoke","Patient_Portal"},priority=5)
	public void verifyPublishToPortal() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		outputAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(OutputManagerPage.existingPatientfirstname,OutputManagerPage.existingPatientlastname);
		outputAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(OutputManagerPage.existingPatientfirstname,OutputManagerPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		outputAssert.assertEquals(patientpageobj.verifyPatientChartIsOpened1(OutputManagerPage.existingPatientfirstname,OutputManagerPage.existingPatientlastname), true);
		medicalchartpage.dismissGuideline();

		logger.info("creating First visit service in patient chart...");
		patientpageobj.clickOnProcedure("First Visit");
		leftPanelpageobj.clickOnNewInToolTip();
		outputpageobj.switchToTemplateFrame();


		logger.info("Clicking on Process button present on Top of Template menus");
		leftPanelpageobj.clickOnToolBarMenus("Process");
		logger.info("Verify Output Manager Page is Opened...");
		outputAssert.assertTrue(leftPanelpageobj.verifyToolTipTitle("Output Manager"));	
		profilepageobj.switchToTooltip();
		logger.info("Clicking on Configuration tab of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Configuration ");
		outputpageobj.switchToOutputManagerFrame();
		logger.info("Perform Publish to Portal Operation on Visit Note Template ");
		outputpageobj.selectTemplateDocument("chkPortal","verifyPublishToPortal");
		profilepageobj.switchToTooltip();
		logger.info("Clicking on Summary Tab Of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Summary ");
		logger.info("Verify Publish to Portal Operation In Summary Tab");
		outputAssert.assertEquals(outputpageobj.verifySummaryTab(), true);

		logger.info("Login to Patient Portal");
		patientLogin.loginWithExistingUser();
		logger.info("verify Patient Portal Home Page Displayed...");
		outputAssert.assertTrue(patientLogin.verifyHomePageIsLoaded());
		logger.info("verify document send from gGastro should be display in patient portal home page...");
		outputAssert.assertTrue(patientLogin.verifyDocumentInPP());
		logger.info("clicking on My Document Menu present in left Menus");
		patientLogin.selectLeftNavigationMenu("My Documents");
		logger.info("verify document send from gGastro should be display in document queue...");
		outputAssert.assertTrue(patientLogin.verifyDocumentInMyDocuments());
		logger.info("Logging out from Patient Portal");
		patientLogin.clickOnLogOff();	
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To veify 'Send for Signature' from Output Manager",groups = { "OutputManger_Regression","Smoke"},priority=6)
	public void verifySendForSignature() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		outputAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		outputAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		outputAssert.assertEquals(patientpageobj.verifyPatientChartIsOpened1(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		medicalchartpage.dismissGuideline();

		logger.info("creating First visit service in patient chart...");
		patientpageobj.clickOnProcedure("First Visit");
		leftPanelpageobj.clickOnNewInToolTip();
		outputpageobj.switchToTemplateFrame();


		logger.info("Clicking on Provider Section Present in First visit Service");
		outputpageobj.clickOnProvider();
		profilepageobj.switchToTooltip();
		outputpageobj.addProvider(Profile.existingProfileProviderfirstname, Profile.existingProfileProviderlastname);
		outputpageobj.switchToTemplateFrame();
		logger.info("Clicking on Process button present on Top of Template menus");
		leftPanelpageobj.clickOnToolBarMenus("Process");
		logger.info("Verify Output Manager Page is Opened...");
		outputAssert.assertTrue(leftPanelpageobj.verifyToolTipTitle("Output Manager"));	


		profilepageobj.switchToTooltip();
		logger.info("Clicking on Configuration tab of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Configuration ");
		outputpageobj.switchToOutputManagerFrame();
		logger.info("Perform sign Operation on Visit Note Template ");
		outputpageobj.selectTemplateDocument("input_SendForSignature","verifySendForSignature");
		outputpageobj.switchToProviderFrame();
		outputpageobj.selectProvider();
		profilepageobj.switchToTooltip();
		logger.info("Clicking on Summary Tab Of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Summary ");
		outputAssert.assertEquals(outputpageobj.verifySummaryTab(), true);

		leftPanelpageobj.selectLeftMainMenu("My Activities");
		logger.info("Verify Document is send for signature");
		outputAssert.assertTrue(outputpageobj.verifySignatureOperation());
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To veify 'Send for Review' from Output Manager",groups = { "OutputManger_Regression","Smoke"},priority=7)
	public void verifySendForReview() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		outputAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on medical chart menu present in left panel...");

		medicalchartpage.clickOnMedicalChartFromLeftPanel();
		medicalchartpage.switchToMedicalFrame();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname);
		outputAssert.assertEquals(medicalchartpage.verifyPatientIsSerchedWithName(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		logger.info("selecting the patient...");
		medicalchartpage.switchToMedicalFrame();
		medicalchartpage.selectPatient();
		outputAssert.assertEquals(patientpageobj.verifyPatientChartIsOpened1(DemographicsPage.existingPatientfirstname,DemographicsPage.existingPatientlastname), true);
		medicalchartpage.dismissGuideline();

		logger.info("creating First visit service in patient chart...");
		patientpageobj.clickOnProcedure("First Visit");
		leftPanelpageobj.clickOnNewInToolTip();
		outputpageobj.switchToTemplateFrame();


		logger.info("Clicking on Provider Section Present in First visit Service");
		outputpageobj.clickOnProvider();
		profilepageobj.switchToTooltip();
		outputpageobj.addProvider(Profile.existingProfileProviderfirstname, Profile.existingProfileProviderlastname);
		outputpageobj.switchToTemplateFrame();
		logger.info("Clicking on Process button present on Top of Template menus");
		leftPanelpageobj.clickOnToolBarMenus("Process");
		logger.info("Verify Output Manager Page is Opened...");
		outputAssert.assertTrue(leftPanelpageobj.verifyToolTipTitle("Output Manager"));	


		profilepageobj.switchToTooltip();
		logger.info("Clicking on Configuration tab of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Configuration ");
		outputpageobj.switchToOutputManagerFrame();
		logger.info("Perform Send for Review Operation on Visit Note Template ");
		outputpageobj.selectTemplateDocument("input_SendForReview","verifySendForReview");
		outputpageobj.switchToProviderFrame();
		outputpageobj.selectProvider();
		profilepageobj.switchToTooltip();
		logger.info("Clicking on Summary Tab Of Output Manager");
		leftPanelpageobj.switchToDifferentTab(" Summary ");
		outputAssert.assertEquals(outputpageobj.verifySummaryTab(), true);

		leftPanelpageobj.selectLeftMainMenu("My Activities");
		logger.info("Verify Document is send for signature");
		outputAssert.assertTrue(outputpageobj.verifySignatureOperation());
		leftPanelpageobj.clickOnLogout();
	}
	
}
