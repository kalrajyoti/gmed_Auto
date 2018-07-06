package com.gmed.test;


import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.base.BaseTestClass;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.utils.ExcelFileUtilty;



public class QuickNoteTest extends BaseTestClass {
	/** Logger to log the QuickNoteTest log messages */
	private static Logger logger  = LogManager.getLogger(PatientChartTest.class); 
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	/** MedicalChart Page reference to access the methods like searching patient, opening document */
	private MedicalChartPage medicalpatientpage;
	/**Assertion to verify different elements of the page */
	private Assertion quicknoteassert = new Assertion();
	/** Document Page reference used to access the methods and xpath */
	private DocumentPage documentpage;
	public static Map<String, String> quicknoteData;
	
	/**These are the variables which are used to store different data for medical chart module*/
	public static String existingPatientfirstname;
	public static String existingPatientlastname;
	
	/** These are the variables which are present on "QUICK Note" sheet in the excel*/
	public static final String MEDICAL_PATIENT_FIRSTNAME 				                   = "patientfirstname";
	public static final String MEDICAL_PATIENT_LASTNAME 				                   = "patientlastname";
	public static String PERSONAL_QUICK_NOTE;
	public static String GLOBAL_QUICK_NOTE;
	public static String QUICK_NOTE_TEXT;
	public static String PROCEDURE;
	public static String NURSEVISIT;
	public static String IMAGE_ADDITIONAL_NOTES;
	public static String IMAGE_CREATE_QUICK_NOTE;
	public static String IMAGE_YES;
	public static String IMAGE_PUBLIC_QUICK_NOTE;
	public static String IMAGE_PERSONAL_QUICK_NOTE;
	public static String IMAGE_MANAGE_QUICK_NOTE;
	public static String USER2;
	public static String PASSWORD2;
	public static String SECOND_PATIENT;
	
	
	
	@BeforeClass
	public void initClass() throws Exception {
		quicknoteData = ExcelFileUtilty.readExcelSheet("QuickNote");
		existingPatientfirstname              = quicknoteData.get(MEDICAL_PATIENT_FIRSTNAME);
		existingPatientlastname               =  quicknoteData.get(MEDICAL_PATIENT_LASTNAME);
		PERSONAL_QUICK_NOTE                   = quicknoteData.get("personalquicknote");
		GLOBAL_QUICK_NOTE                     = quicknoteData.get("globalquicknote");
		QUICK_NOTE_TEXT                       = quicknoteData.get("quicknotetext"); 
		PROCEDURE                             = quicknoteData.get("procedure");
		NURSEVISIT                            = quicknoteData.get("procedure1");
		IMAGE_ADDITIONAL_NOTES                = quicknoteData.get("imageadditionalnotes");
		IMAGE_CREATE_QUICK_NOTE               = quicknoteData.get("imagecreatequicknote");
		IMAGE_YES                             = quicknoteData.get("imageyes");
		IMAGE_PUBLIC_QUICK_NOTE               = quicknoteData.get("imagepublicquicknote");
		IMAGE_PERSONAL_QUICK_NOTE             = quicknoteData.get("imagepersonalquicknote");
		IMAGE_MANAGE_QUICK_NOTE               = quicknoteData.get("imagemanagequicknote");
		USER2                                 = quicknoteData.get("user2");
		PASSWORD2                              = quicknoteData.get("password2");
		SECOND_PATIENT                         = quicknoteData.get("secondpatient");
		
		
		
		loginPageObj= new LoginPage();
		medicalpatientpage = new MedicalChartPage();
		leftPanelpageobj = new LeftPanelPage();
		documentpage = new DocumentPage();
	
	 }
	
	@Test(description = "To verify Apply Quick notes", priority = 1)
	public void verifyApplyQuickNote() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(existingPatientfirstname+" "+existingPatientlastname);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
	    medicalpatientpage.clickOnProcedure(PROCEDURE);
	    logger.info("Clicking on Additional Notes to add notes");
	    documentpage.clickOnSections(DocumentPage.additionalNote, IMAGE_ADDITIONAL_NOTES);
	    documentpage.enterText(DocumentPage.additionalNoteTextBox, QUICK_NOTE_TEXT);
		//documentpage.enterTextUsingRobot(QUICK_NOTE_TEXT);
		logger.info("Clicking on Quick Note button to open dropdown");
	    documentpage.clickOnQuickNote();
	    logger.info("Click on create quick note option from dropdown");
        documentpage.clickOnQuickNoteItems(IMAGE_CREATE_QUICK_NOTE);
        logger.info("creating a public quick Note with name");
	    documentpage.creatQuickNote(DocumentPage.publicRadioButton,GLOBAL_QUICK_NOTE);
        documentpage.clickOnQuickNote();
        logger.info("Click on create quick note option from dropdown");
        documentpage.clickOnQuickNoteItems(IMAGE_CREATE_QUICK_NOTE);
        logger.info("creating a personal quick Note with name");
        documentpage.creatQuickNote(DocumentPage.personalRadioButton,PERSONAL_QUICK_NOTE);
        documentpage.clickOnQuickNote();
        logger.info("Click on Created Quick Note to verify if it is created");
        quicknoteassert.assertEquals(documentpage.clickOnQuickNoteItems(IMAGE_PUBLIC_QUICK_NOTE), true);
        documentpage.clickOnQuickNoteItems(IMAGE_YES );
	    logger.info("Clicking on Refresh");
        documentpage.refresh();
		/*documentpage.clickOnQuickNote();
	    documentpage.clickOnQuickNoteItems(IMAGE_MANAGE_QUICK_NOTE);
        documentpage.deleteQuickNote(GLOBAL_QUICK_NOTE);*/
		leftPanelpageobj.clickOnLogout();
	    
      }
	
	@Test(description = "Apply Global Quick Note", dependsOnMethods = { "verifyApplyQuickNote" }, priority = 2)
	public void verifyApplyGlobalQuickNote() {
		logger.info("Logging into application");
		loginPageObj.loginToGmedWithBreakTheGlassUser(USER2, PASSWORD2);
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(SECOND_PATIENT);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
	    medicalpatientpage.clickOnProcedure(PROCEDURE);
		logger.info("Clicking on Quick Note button to open dropdown");
	    documentpage.clickOnQuickNote();
	    logger.info("Click on global Quick Note");
	    documentpage.clickOnQuickNoteItems(IMAGE_PUBLIC_QUICK_NOTE);
	    //documentpage.clickOnQuickNoteItems(IMAGE_YES );
	   // logger.info("Clicking on Refresh");
       // documentpage.refresh();
        documentpage.clickOnSections(DocumentPage.additionalNote, IMAGE_ADDITIONAL_NOTES);
        logger.info("Verifying that Global Quick Note is available to other user and can be applied");
        quicknoteassert.assertEquals(documentpage.verifyText(DocumentPage.additionalNoteTextBox, QUICK_NOTE_TEXT), true);
        logger.info("Clicking on Quick Note button to open dropdown");
	    documentpage.clickOnQuickNote();
	    logger.info("Verifying that Personal Note should not be available to other User");
	    quicknoteassert.assertEquals(documentpage.clickOnQuickNoteItems(IMAGE_PERSONAL_QUICK_NOTE), false);
	    logger.info("Closing the Service Document");
        documentpage.closeColonoscopyService();
        logger.info("Opening the Nurse Visit Service Document");
        medicalpatientpage.clickOnProcedure(NURSEVISIT);
    	logger.info("Clicking on Quick Note button to open dropdown");
	    documentpage.clickOnQuickNote();
	    logger.info("Verifying that the Global Note is not available in other Document");
	    quicknoteassert.assertEquals(documentpage.clickOnQuickNoteItems(IMAGE_PUBLIC_QUICK_NOTE), false);
	    leftPanelpageobj.clickOnLogout();
	    
	}
	
	@Test(description = "Apply Personal Quick Note", dependsOnMethods = { "verifyApplyQuickNote" }, priority = 3)
	public void verifyApplyPersonalQuickNote() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(SECOND_PATIENT);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
	    medicalpatientpage.clickOnProcedure(PROCEDURE);
		logger.info("Clicking on Quick Note button to open dropdown");
	    documentpage.clickOnQuickNote();
	    logger.info("Click on Personal Quick Note");
	    documentpage.clickOnQuickNoteItems(IMAGE_PERSONAL_QUICK_NOTE);
	    //documentpage.clickOnQuickNoteItems(IMAGE_YES );
	   // logger.info("Clicking on Refresh");
       // documentpage.refresh();
        documentpage.clickOnSections(DocumentPage.additionalNote, IMAGE_ADDITIONAL_NOTES);
        logger.info("Verifying that Personal Quick Note is available to same user and can be applied");
        quicknoteassert.assertEquals(documentpage.verifyText(DocumentPage.additionalNoteTextBox, QUICK_NOTE_TEXT), true);
        logger.info("Clicking on Quick Note button to open dropdown");
	    documentpage.clickOnQuickNote();
	    logger.info("Verifying that Global Note should be available to same User");
	    quicknoteassert.assertEquals(documentpage.clickOnQuickNoteItems(IMAGE_PUBLIC_QUICK_NOTE), true);
	    documentpage.clickOnQuickNoteItems(IMAGE_YES );
	    logger.info("Closing the Service Document");
        documentpage.closeColonoscopyService();
        logger.info("Opening the Nurse Visit Service Document");
        medicalpatientpage.clickOnProcedure(NURSEVISIT);
    	logger.info("Clicking on Quick Note button to open dropdown");
	    documentpage.clickOnQuickNote();
	    logger.info("Verifying that the Personal Note is not available in other Document");
	    quicknoteassert.assertEquals(documentpage.clickOnQuickNoteItems(IMAGE_PERSONAL_QUICK_NOTE), false);
	    logger.info("Closing the Nurse Visit Service Document");
        documentpage.closeColonoscopyService();
        logger.info("Clicking on Colonoscopy under the New Button");
	    medicalpatientpage.clickOnProcedure(PROCEDURE);
		documentpage.clickOnQuickNote();
		logger.info("Clicking on Manage Quick Noye buton");
	    documentpage.clickOnQuickNoteItems(IMAGE_MANAGE_QUICK_NOTE);
	    logger.info("Deleting Personal Note");
        documentpage.deleteQuickNote(GLOBAL_QUICK_NOTE);
        logger.info("Clicking Yes on the Pop Up");
        documentpage.clickOnQuickNoteItems(IMAGE_YES );
        documentpage.deleteQuickNote(PERSONAL_QUICK_NOTE);
        documentpage.clickOnQuickNoteItems(IMAGE_YES );
        documentpage.refresh();
        leftPanelpageobj.clickOnLogout();
	    
	}	
}