package com.gmed.test;

import com.gmed.base.BaseTestClass;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static com.gmed.utils.StringUtility.*;

public class DocumentTest extends BaseTestClass {
	/** Logger to log the QuickNoteTest log messages */
	private static Logger				logger			= LogManager.getLogger(PatientChartTest.class);
	/** Login Page reference used to login for logging in for test */
	private LoginPage					loginPageObj;
	/**
	 * MedicalChart Page reference to access the methods like searching patient,
	 * opening document
	 */
	private MedicalChartPage			medicalpatientpage;
	/** Assertion to verify different elements of the page */
	private Assertion					documentassert	= new Assertion();
	/** Document Page reference used to access the methods and xpath */
	private DocumentPage				documentpage;
	public static Map<String, String>	documentData;
//	/**
//	 * Following variable is used to store the value from the Application and
//	 * later on used for comparison
//	 */
//	public static String				APPVALUE;
//	public static String[]				STRARRAY;
//
//	/**
//	 * These are the variables which are present on "Document" sheet in the
//	 * excel
//	 */
//
//	public static String				FIELDS;
//	public static String				PROCEDURE;
//	public static String				PATIENTNAME;
//	public static String				DOB;
//	public static String				GENDER;
//	public static String				ACCOUNTNUM;
//	public static String				INSTRUMENTNAME;
//	public static String				INSTRUMENTNUM;
//	public static String				INSTRUMENTTYPE;
//	public static String				IMAGE_DELETE;
//	public static String				IMAGE_YES;
//	public static String				LIVE_VIDEO;
//	public static String				COMMON_FINDINGS;
//	public static String				GENERAL_FINDINGS;
//	public static String				SPECIMENS;
//	public static String				TIME_TRACKER;
//	public static String				IMAGE_REMOVE;
//	public static String				ASACLASS_TEXT;
//	public static String				ADMINISTERED_MEDICATION;
//	public static String				WEIGHT;
//	public static String				HEIGHT;
//	public static String				LIMITATIONTEXT;
//	public static String				REFERRALTITLE;
//	public static String				IMAGE_REFERRAL;
//	public static String				INTERVENTIONOPTION;
//	public static String				FINDINGSOPTION;
//	public static String				PATHOLOGYTEXT;
//	public static String				AUTHENTICATOR;
//	public static String				AUTHENTICATORPWD;
//	public static String                UNLOCKPWD;
//	public static String                IMPRESSIONTEXT;

	@BeforeClass
	public void initClass() throws Exception {
		documentData = ExcelFileUtilty.readExcelSheet("Document");
//		FIELDS = documentData.get("fields");
//		PROCEDURE = documentData.get("procedure");
//		PATIENTNAME = documentData.get("searchedpatient");
//		DOB = documentData.get("dob");
//		GENDER = documentData.get("gender");
//		ACCOUNTNUM = documentData.get("accountnum");
//		INSTRUMENTNAME = documentData.get("instrumentname");
//		INSTRUMENTNUM = documentData.get("instrumentnum");
//		INSTRUMENTTYPE = documentData.get("instrumenttype");
//		IMAGE_DELETE = documentData.get("clickondelete");
//		IMAGE_YES = documentData.get("clickonyes");
//		LIVE_VIDEO = documentData.get("livevideo");
//		COMMON_FINDINGS = documentData.get("commonfindings");
//		GENERAL_FINDINGS = documentData.get("generalfindings");
//		SPECIMENS = documentData.get("specimens");
//		TIME_TRACKER = documentData.get("timetracker");
//		IMAGE_REMOVE = documentData.get("clickremovefromdocument");
//		ASACLASS_TEXT = documentData.get("asaclasstext");
//		ADMINISTERED_MEDICATION = documentData.get("administeredmedication");
//		WEIGHT = documentData.get("weight");
//		HEIGHT = documentData.get("height");
//		LIMITATIONTEXT = documentData.get("limitationtext");
//		REFERRALTITLE = documentData.get("referraltitle");
//		IMAGE_REFERRAL = documentData.get("referralimage");
//		INTERVENTIONOPTION = documentData.get("interventiontext");
//		FINDINGSOPTION = documentData.get("findingstext");
//		PATHOLOGYTEXT = documentData.get("pathologytext");
//		AUTHENTICATOR = documentData.get("authenticator");
//		AUTHENTICATORPWD = documentData.get("authenticatorpwd");
//		UNLOCKPWD = documentData.get("unlockpwd");
//		IMPRESSIONTEXT = documentData.get("impressiontext");

		loginPageObj = new LoginPage();
		medicalpatientpage = new MedicalChartPage();
		leftPanelpageobj = new LeftPanelPage();
		documentpage = new DocumentPage();
		documentpage.initClass();

	}

	@Test(description = "To verify Colonoscopy and its sections", priority = 2)
	public void verifyColonoscopySection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		logger.info("Switching to Colonoscopy document after opening it");
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying all the fields are available in the Colonoscopy document");
		documentassert.assertTrue(stringContainsCsv(documentpage.getElementText(DocumentPage.allvisibletext), DocumentPage.FIELDS));
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "H:To verify Patient Demographics in Colonoscopy", priority = 3)
	public void verifyPatientDemographics() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		logger.info("Switching to Colonoscopy document after opening it");
		documentpage.switchToFraDocumentFrame();
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.date).contains(new SimpleDateFormat("M/dd/YYYY").format(new Date())));
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.gender, DocumentPage.GENDER));
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.accountnum, DocumentPage.ACCOUNTNUM));
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.patientName, DocumentPage.PATIENTNAME));
		documentassert.assertTrue(documentpage.verifyFieldEditable(DocumentPage.patientName));
		documentassert.assertTrue(documentpage.verifyFieldEditable(DocumentPage.dob));
		documentassert.assertTrue(documentpage.verifyFieldEditable(DocumentPage.gender));
		documentassert.assertTrue(documentpage.verifyFieldEditable(DocumentPage.date));
		documentassert.assertTrue(documentpage.verifyFieldEditable(DocumentPage.accountnum));
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "To verify staff details in Colonoscopy", priority = 1)
	public void verifyStaffDetails() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		logger.info("Switching to Colonoscopy document after opening it");
		documentpage.switchToFraDocumentFrame();

		logger.info("Verifying the we can select multiple referring physician and then able to delete them.After that we can select a single physician");
		logger.info("Clicking on ReferringPhysician Section");
		documentpage.selectMultipleStaffDetails(DocumentPage.referringphysician, DocumentPage.select1frompopupframe, DocumentPage.select2frompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Deleting both the referring physician from the colonoscopy");
		documentpage.deleteStaffDetails(DocumentPage.referringphysician1, DocumentPage.IMAGE_DELETE, DocumentPage.IMAGE_YES);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		documentpage.deleteStaffDetails(DocumentPage.referringphysician1, DocumentPage.IMAGE_DELETE, DocumentPage.IMAGE_YES);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that both the physician is deleted");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.referringphysician).trim().isEmpty());
		logger.info("Selecting a referring Physician");
		documentpage.selectSingleStaffDetails(DocumentPage.referringphysician, DocumentPage.select1frompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that the referring physician is selected and there in the colonoscopy");
		documentassert.assertFalse(documentpage.getElementText(DocumentPage.referringphysician).trim().isEmpty());

		logger.info("Verifying that we can select a pcp then delete it and then again select it");
		logger.info("Selecting a pcp from the pop up");
		documentpage.selectSingleStaffDetails(DocumentPage.pcp, DocumentPage.pcpselectfrompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Deleting the pcp from the document");
		documentpage.deleteStaffDetails(DocumentPage.pcp1, DocumentPage.IMAGE_DELETE);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that PCP is deleted successfully from the Document");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.pcp).trim().isEmpty());
		logger.info("Selecting the PCP from the pop up");
		documentpage.selectSingleStaffDetails(DocumentPage.pcp, DocumentPage.pcpselectfrompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that the PCP is selected successfully and available in the document");
		documentassert.assertFalse(documentpage.getElementText(DocumentPage.pcp).trim().isEmpty());

		logger.info("Verifying that we can select mulitple consultant in consulting then delete it and then select one");
		logger.info("Selecting 2 consultant from the pop up");
		documentpage.selectMultipleStaffDetails(DocumentPage.consulting, DocumentPage.select1frompopupframe, DocumentPage.select2frompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Deleting the consultant from the document");
		documentpage.deleteStaffDetails(DocumentPage.consultant1, DocumentPage.IMAGE_DELETE);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		documentpage.deleteStaffDetails(DocumentPage.consultant1, DocumentPage.IMAGE_DELETE);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that both the consultant is deleted succeddfully from the document");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.consulting).trim().isEmpty());
		logger.info("Selecting a consultant from the pop up");
		documentpage.selectSingleStaffDetails(DocumentPage.consulting, DocumentPage.select1frompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that consultant is added successfully in the document");
		documentassert.assertFalse(documentpage.getElementText(DocumentPage.consulting).trim().isEmpty());

		logger.info("Verifying that we can select a anesthesia provider then delete it and then can again select it");
		logger.info("Selecting an anesthesia provider from the pop up");
		documentpage.selectSingleStaffDetails(DocumentPage.anesthesiaprovider, DocumentPage.select1frompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Deleting an anesthesia provider");
		documentpage.deleteStaffDetails(DocumentPage.anesthesiaprovider1, DocumentPage.IMAGE_DELETE);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verify that anesthesia provider is deleted successfully from the document.");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.anesthesiaprovider).trim().isEmpty());
		logger.info("Adding an anesthesia provider to document");
		documentpage.selectSingleStaffDetails(DocumentPage.anesthesiaprovider, DocumentPage.select1frompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that the anesthesia provider is added successfully to document");
		documentassert.assertFalse(documentpage.getElementText(DocumentPage.anesthesiaprovider).trim().isEmpty());

		logger.info("Verifying that we can select multiple nurse then delete both of them and then select one in document");
		logger.info("Adding two nurse to the document");
		documentpage.selectMultipleStaffDetails(DocumentPage.nurse, DocumentPage.select1frompopupframe, DocumentPage.select2frompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Deleting both the nurse from the document");
		documentpage.deleteStaffDetails(DocumentPage.nurse1, DocumentPage.IMAGE_DELETE);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		documentpage.deleteStaffDetails(DocumentPage.nurse1, DocumentPage.IMAGE_DELETE);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that bith the nurse is deleted successfully");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.nurse).trim().isEmpty());
		logger.info("Adding a nurse to the Document");
		documentpage.selectSingleStaffDetails(DocumentPage.nurse, DocumentPage.select1frompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that nurse is added successfully in the document");
		documentassert.assertFalse(documentpage.getElementText(DocumentPage.nurse).trim().isEmpty());

		logger.info("Verifying that we can add multiple staff to the document then delete them then add one in the document");
		logger.info("Adding 2 staff to the document");
		documentpage.selectMultipleStaffDetails(DocumentPage.staff, DocumentPage.select1frompopupframe, DocumentPage.select2frompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Deleting both the staff from the document");
		documentpage.deleteStaffDetails(DocumentPage.staff1, DocumentPage.IMAGE_DELETE);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		logger.info("Deleting the Staff from the document");
		documentpage.deleteStaffDetails(DocumentPage.staff1, DocumentPage.IMAGE_DELETE);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying staff is deleted successfully from the document");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.staff).trim().isEmpty());
		logger.info("Selecting a staff to the document");
		documentpage.selectSingleStaffDetails(DocumentPage.staff, DocumentPage.select1frompopupframe, DocumentPage.selectbtnpopupframe);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that the staff is added to the document");
		documentassert.assertFalse(documentpage.getElementText(DocumentPage.staff).trim().isEmpty());

		logger.info("Verifying that we can add asa class to the document");
		logger.info("Adding asa class to the document");
		documentpage.selectASAClass(DocumentPage.asaclass, DocumentPage.asaradiobtnrhtpnl, DocumentPage.savebtnrhtpnl);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that asa class is added successfully");
		documentassert.assertFalse(documentpage.getElementText(DocumentPage.asaclass).trim().isEmpty());
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "H: To verify Instrument section in Colonoscopy", priority = 4)
	public void verifyInstrumentSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		logger.info("Switching to Colonoscopy document after opening it");
		documentpage.switchToFraDocumentFrame();
		logger.info("Selecting an Instruments");
		String instrumenttext = documentpage.selectInstrument();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verrifying that the instrument is selected in the document");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.instruments).contains(instrumenttext));
		documentpage.switchToFrame("panUserList_Frame");
		documentpage.switchToFrame("panList_Frame");
		logger.info("Creating a new Instrument from roght Panel");
		documentpage.createNewInstrument(DocumentPage.INSTRUMENTNAME, DocumentPage.INSTRUMENTNUM, DocumentPage.INSTRUMENTTYPE);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verrifying that the instrument is created and selected in the document");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.instruments).contains(DocumentPage.INSTRUMENTNAME));
		logger.info("Logging Out");
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "H: To verify Instrument section in Colonoscopy", priority = 5)
	public void verifyImage() {

		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		logger.info("Switching to Colonoscopy document after opening it");
		documentpage.switchToFraDocumentFrame();
		logger.info("Inserting an Image on Document");
		documentpage.insertImageOnDocument(DocumentPage.IMAGE_YES);
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.livevideo, DocumentPage.LIVE_VIDEO));
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.commonfindings, DocumentPage.COMMON_FINDINGS));
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.generalfindings, DocumentPage.GENERAL_FINDINGS));
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.specimens, DocumentPage.SPECIMENS));
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.timetracker1, DocumentPage.TIME_TRACKER));
		logger.info("Closing the Image Recorder screen");
		documentpage.click(DocumentPage.cancel);
		documentpage.switchToFraDocumentFrame();
		logger.info("Getting the count of image on the document");
		documentpage.verifyImageCount(DocumentPage.imagesondocument);
		logger.info("Removing the Image from the document");
		documentpage.deleteStaffDetails(DocumentPage.rclickonimage, DocumentPage.IMAGE_REMOVE);
		documentpage.refresh();
		logger.info("Verifying that the image is removed from the document");
		documentassert.assertTrue(documentpage.verifyImageCount(DocumentPage.imagesondocument));
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "To verify ASA Class Section", priority = 6)
	public void verifyASAClass() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		logger.info("Switching to Colonoscopy document after opening it");
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that we can add asa class to the document");
		logger.info("Adding asa class to the document");
		documentpage.selectASAClass(DocumentPage.asaclass, DocumentPage.asaradiobtnrhtpnl, DocumentPage.savebtnrhtpnl);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that asa class is added successfully and correctly reflected in Document");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.asaclass).contains(DocumentPage.ASACLASS_TEXT));
		leftPanelpageobj.clickOnLogout();
	}

	@Test(description = "H:  To verify HPI Section", priority = 7)
	public void verifyHPISection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		logger.info("Switching to Colonoscopy document after opening it");
		documentpage.switchToFraDocumentFrame();
		logger.info("Selecting the HPI from the Right Panel and entering free text into it");
		documentpage.selectHPIOnDocument();
		logger.info("Verifying that the selecting HPI and free text is there in the hpi section of Document");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.hpisection).length() > 30);
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.hpisection).contains("testing"));
		documentpage.deleteStaffDetails(DocumentPage.hpisection, "clickOnDeleteAll", DocumentPage.IMAGE_YES);
		documentpage.refresh();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying data of HPI section is deleted successfully");
		documentassert.assertFalse(documentpage.getElementText(DocumentPage.hpisection).length() > 15);
		documentassert.assertFalse(documentpage.getElementText(DocumentPage.hpisection).contains("testing"));
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "H:  To verify Administered Medication Section", priority = 8)
	public void verifyAdministeredMedication() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		logger.info("Switching to Colonoscopy document after opening it");
		documentpage.switchToFraDocumentFrame();
		logger.info("Adding the administered medication to colonoscopy");
		documentpage.addAdministeredMedication(DocumentPage.ADMINISTERED_MEDICATION);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that Administered Medication is added successfully in the document");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.administmedication).contains(DocumentPage.ADMINISTERED_MEDICATION));
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "H:  To verify Indication Section", priority = 9)
	public void verifyIndicationSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		logger.info("Switching to Colonoscopy document after opening it");
		documentpage.switchToFraDocumentFrame();
		logger.info("Adding indications to the document and storing its value in APPDATA variable for comparison");
		DocumentPage.APPVALUE = documentpage.addIndications();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying if the indication is added successfully");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.indicationData).contains(DocumentPage.APPVALUE));
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "H:  To verify Vitals Sign Section", priority = 10)
	public void verifyVitalsSignSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		logger.info("Adding vital sign to the document");
		documentpage.addVitalSign(DocumentPage.WEIGHT, DocumentPage.HEIGHT);
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that the vitals has been added to the document");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.weightvalue).contains(DocumentPage.WEIGHT));
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.heightvalue).contains(DocumentPage.HEIGHT));
		documentassert.assertFalse(SeleniumUtil.getElementWithFluentWait(DocumentPage.bmivalue).getText().trim().isEmpty());
		leftPanelpageobj.clickOnLogout();
	}

	@Test(description = "H:  To verify Physical Exam Section", priority = 11)
	public void verifyPhysicalExamSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		logger.info("Enter the data in the Physical Section");
		DocumentPage.STRARRAY = documentpage.addPhysicalSection();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that the All normal data is entered in the Physical section of the document");
		documentassert.assertTrue(arrayContainsAll(DocumentPage.STRARRAY, documentpage.getElementsText(DocumentPage.physicalsectiondata)));
		leftPanelpageobj.clickOnLogout();
	}

	@Test(description = "H:  To verify Procedure Section", priority = 12)
	public void verifyProcedureSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		logger.info("Adding Narrative to the Document and returning the selected narrative in string array");
		DocumentPage.STRARRAY = documentpage.addNarrativeToDocument();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that the Document is displaying the narratives as per the selection");
		documentassert.assertTrue(arrayContainsString(DocumentPage.STRARRAY, documentpage.getElementText(DocumentPage.narrativeprocpriorcolonsurg)));
		documentassert.assertTrue(arrayContainsString(DocumentPage.STRARRAY, documentpage.getElementText(DocumentPage.narrativeprocriskassessment)));
		documentassert.assertTrue(arrayContainsString(DocumentPage.STRARRAY, documentpage.getElementText(DocumentPage.narrativeprocinfotitle)));
		documentassert.assertTrue(arrayContainsString(DocumentPage.STRARRAY, documentpage.getElementText(DocumentPage.narrativeprocanesthesiatype)));
		documentassert.assertTrue(arrayContainsString(DocumentPage.STRARRAY, documentpage.getElementText(DocumentPage.narrativeprocadministby)));
		documentassert.assertTrue(arrayContainsString(DocumentPage.STRARRAY, documentpage.getElementText(DocumentPage.narrativeprocsiteintroduc)));
		leftPanelpageobj.clickOnLogout();
	}

	@Test(description = "H:  To verify Limitations/Complications section", priority = 13)
	public void verifyLimitationSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.limitationsection);
		documentpage.click(DocumentPage.limitationsection);
		logger.info("Checking the No Limitation Checkbox on the Right Panel");
		documentpage.checkNoLimitationCheckBox();
		documentpage.switchToFraDocumentFrame();
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.limitationsection);
		logger.info("Verifying that no Limitation text is populated in the No Limitation Section of the document");
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.limitationsection, DocumentPage.LIMITATIONTEXT));
		leftPanelpageobj.clickOnLogout();
	}

	@Test(description = "H:  To verify Findings section", priority = 14)
	public void verifyFindingsSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		logger.info("Scrolling to the Findings Section");
		documentpage.scroll(DocumentPage.findingssection);
		logger.info("Verifying that Impression Section is Blank");
		documentassert.assertEquals(documentpage.getElementText(DocumentPage.impressionsection1).trim().isEmpty(), true);
		documentpage.click(DocumentPage.findingssection);
		logger.info("Adding Finding from the right panel into the document");
		DocumentPage.STRARRAY = documentpage.addFindingsToDocument(DocumentPage.selecteditemsonfindings1);
		String[] STRARRAY1 = documentpage.addFindingsToDocument(DocumentPage.selecteditemsonfindings2);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.findingssection);
		logger.info("Verifying that the Findings is added to the Document Section as per the Selection");
		documentassert.assertTrue(arrayElementsContainedInString(DocumentPage.STRARRAY, documentpage.getElementText(DocumentPage.elementtextonfindings)));
		documentassert.assertTrue(arrayElementsContainedInString(STRARRAY1, documentpage.getElementText(DocumentPage.elementtextonfindings)));
		logger.info("Verifying that the impression section get updated after the findings section updates");
		documentassert.assertTrue(!documentpage.getElementText(DocumentPage.impressionsection1).trim().isEmpty());
		leftPanelpageobj.clickOnLogout();
	}

	@Test(description = "H:To verify Other Intervension section", priority = 15)
	public void verifyInterventionSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.interventionsection);
		documentpage.click(DocumentPage.interventionsection);
		logger.info("Adding intervention to the document and storing it in the String Array for verification later on");
		DocumentPage.STRARRAY = documentpage.addInterventionSection(DocumentPage.selecteditemsonfindings1);
		String[] STRARRAY1 = documentpage.addInterventionSection(DocumentPage.selecteditemsonfindings2);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.interventionsection);
		logger.info("Verifying that Intermention Section get updated as per selection");
		documentassert.assertTrue(arrayElementsContainedInString(DocumentPage.STRARRAY, documentpage.getElementText(DocumentPage.interventionsection)));
		documentassert.assertTrue(arrayElementsContainedInString(STRARRAY1, documentpage.getElementText(DocumentPage.interventionsection)));
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "H:  To verify Plan Section", priority = 16)
	public void verifyPlanSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.planSection);
		documentpage.click(DocumentPage.planSection);
		logger.info("Adding the referral order into the document");
		documentpage.addReferralOrder(DocumentPage.REFERRALTITLE, DocumentPage.IMAGE_REFERRAL);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.planSection);
		logger.info("Verifying that the add oder is displayed in the plan section of document or not");
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.planSection, DocumentPage.REFERRALTITLE));
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "H:  To verify Samples section", priority = 17)
	public void verifySampleSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		logger.info("Adding Sample to the Document");
		documentpage.addSample(DocumentPage.REFERRALTITLE);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.samplesection);
		logger.info("Verifying that the Sample is added to the Document");
		documentassert.assertTrue(stringContainsCsv(documentpage.getElementText(DocumentPage.samplesection), DocumentPage.INTERVENTIONOPTION));
		documentassert.assertTrue(stringContainsCsv(documentpage.getElementText(DocumentPage.samplesection), DocumentPage.FINDINGSOPTION));
		documentassert.assertTrue(stringContainsCsv(documentpage.getElementText(DocumentPage.samplesection), DocumentPage.REFERRALTITLE));
		logger.info("Verifying that the Pathology section is updated");
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.pathologysection, DocumentPage.PATHOLOGYTEXT));
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "H:  To verify Samples section", priority = 18)
	public void verifyAdditionalNotesSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		logger.info("Adding the Additional Note to the document");
		DocumentPage.APPVALUE = documentpage.addAdditionalNote();
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.additionalNoteTextBox);
		logger.info("Verifying that the additional notes is correctly updated on the Document");
		documentassert.assertTrue(documentpage.verifyText(DocumentPage.additionalNoteTextBox, "testing " + DocumentPage.APPVALUE));
		leftPanelpageobj.clickOnLogout();

	}

	@Test(description = "H: Veirfy Sign Functionality from Thumbnails", priority = 19)
	public void verifySignFunctionality() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		logger.info("Adding a Provider with Signature to the document");
		documentpage.addEndoscopist();
		documentpage.switchToFraDocumentFrame();
		documentpage.signDocument(DocumentPage.AUTHENTICATOR, DocumentPage.AUTHENTICATORPWD);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.additionalNoteTextBox);
		documentassert.assertTrue(documentpage.verifyElementCount(DocumentPage.providersign));
		leftPanelpageobj.clickOnLogout();

	}
	
	@Test(description = "H: Verify Lock Functionality from Thumbnails", priority = 20)
	public void verifyLockFunctionality() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		logger.info("Locking the selected document");
		documentpage.lockSelectedDoc();
		logger.info("Verifying the document is locked successfully");
		documentassert.assertTrue(documentpage.verifyElementCount(DocumentPage.lockicon));
		logger.info("Locking all the document");
		System.out.println("password "+DocumentPage.UNLOCKPWD);
		documentpage.lockAllDoc();
		documentassert.assertTrue(documentpage.verifyElementCount(DocumentPage.lockicon));
		documentpage.unlockAllDoc(DocumentPage.UNLOCKPWD);
		documentassert.assertFalse(documentpage.verifyElementCount(DocumentPage.lockicon));
		leftPanelpageobj.clickOnLogout();
			
	}

	@Test(description = "H:  To verify Impression section", priority = 21)
	public void verifyImpressionSection() {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(DocumentPage.PATIENTNAME);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(DocumentPage.PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		logger.info("Scrolling to the Findings Section");
		documentpage.scroll(DocumentPage.findingssection);
		documentpage.click(DocumentPage.findingssection);
		logger.info("Adding Finding from the right panel into the document");
		documentpage.addFindingsToDocument(DocumentPage.selecteditemsonfindings1);
	    documentpage.addFindingsToDocument(DocumentPage.selecteditemsonfindings2);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.findingssection);
		logger.info("Verifying that the impression section get updated as per Findings Section");
		documentassert.assertTrue(documentpage.getElementText(DocumentPage.impressionsection1).contains(DocumentPage.IMPRESSIONTEXT));
		leftPanelpageobj.clickOnLogout();
	}
	@AfterClass()
	public void classTearDown() {
		medicalpatientpage = null;
		loginPageObj = null;
		leftPanelpageobj = null;
		documentpage = null;
		logger.info("Exiting the classTearDown method for Document test class \n\n");
	}

}
