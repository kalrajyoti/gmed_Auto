package com.gmed.test;


import static com.gmed.utils.SeleniumUtil.getElementCount;
import static com.gmed.utils.StringUtility.stringContainsSpaceSeparatedCsv;

import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseTestClass;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.ScanningPage;
import com.gmed.utils.ExcelFileUtilty;

public class ScanningTest extends BaseTestClass {

	private static Logger				logger			= LogManager.getLogger(OrdersTest.class);
	/** Page reference used to access the methods of those pages */
	private LoginPage					loginPageObj;
	private ScanningPage				scanningobj;
	/** Assertion to verify different elements of the page */
	private Assertion					scanningassert	= new Assertion();

	/** This is used to read the data from the excel */
	public static Map<String, String>	documentData;
	public static String				PATIENTNAME1;
	public static String				PATIENTNAME2;
	public static String				VERIFYPATIENT;
	public static String				DATE;
	public static String				USER;
	public static String				ACTION;
	public static String				DESCRIPTION;
	
	/** Those Variable is used to store data from application and used for verification */
	public String str;
	public String str1;
	public int count;


	@BeforeClass
	public void initClass() throws Exception {
		documentData = ExcelFileUtilty.readExcelSheet("Scanning");
		PATIENTNAME1 = documentData.get("patientname1");
		PATIENTNAME2 = documentData.get("patientname2");
		VERIFYPATIENT = documentData.get("verifypatient");
		DATE = documentData.get("date");
		USER = documentData.get("user");
		ACTION = documentData.get("action");
		DESCRIPTION = documentData.get("description");

		loginPageObj = new LoginPage();
		leftPanelpageobj = new LeftPanelPage();
		scanningobj = new ScanningPage();
		
	}

	@Test(description = "H: To Verify type of orders created from Plan Section", priority = 1)
	public void verifySaveScanned() throws FindFailed {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Navigating to the Scanning Page");
		leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		
		logger.info("Searching the Scanned records in the Scanning Page");
		scanningobj.searchScannedRecords();
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		
		logger.info("Unlinking previous patient if any and Linking the patient to the Scanned Records");
		scanningobj.selectPatient(PATIENTNAME1);
		scanningobj.clickOnSave();
		switchToParentFrame(Frames.SCANNING);
		
		logger.info("Verifying that the Patinent is Linked successfully to the Scanned records");
		scanningassert.assertTrue(getElementText(ScanningPage.firstScannedDoc).equalsIgnoreCase(PATIENTNAME1));
		leftPanelpageobj.clickOnLogout();
		
	}

	@Test(description = "H: To Verify type of Deletion of Records", priority = 2)
	public void verifyDelete() throws FindFailed {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Navigating to the Scanning Page");
		leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		logger.info("Searching the Scanned records in the Scanning Page");
		scanningobj.searchScannedRecords();
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Unlinking previous patient if any and Linking the patient to the Scanned Records");
		scanningobj.selectPatient(PATIENTNAME2);
		scanningobj.clickOnSave();
		switchToParentFrame(Frames.SCANNING);
		logger.info("Verifying that on clicking No records will not be deleted");
		scanningobj.deleteOperations("clickOnNo1");
		scanningassert.assertTrue(getElementText(ScanningPage.firstScannedDoc).equalsIgnoreCase(VERIFYPATIENT));
		
		logger.info("Verifying that on clicking Yes records will be deleted");
		scanningobj.deleteOperations("clickOnYes1");
		scanningassert.assertFalse(getElementText(ScanningPage.firstScannedDoc).equalsIgnoreCase(VERIFYPATIENT));
		leftPanelpageobj.clickOnLogout();
	}

	@Test(description = "H: R-click  History option", priority = 3)
	public void verifyHistoryOption() throws FindFailed {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Navigating to the Scanning Page");
		leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		logger.info("Searching the Scanned records in the Scanning Page");
		scanningobj.searchScannedRecords();
		
		logger.info("Clicking on History Option after Right Click");
		scanningobj.clickHistoryOption();
		switchToParentFrame(Frames.TOOLTIP);
		
		logger.info("Verifying that the History pop up has the required Column details");
		scanningassert.assertTrue(getElementText(ScanningPage.historyDateCol).equalsIgnoreCase(DATE));
		scanningassert.assertTrue(getElementText(ScanningPage.historyUserCol).equalsIgnoreCase(USER));
		scanningassert.assertTrue(getElementText(ScanningPage.historyActionCol).equalsIgnoreCase(ACTION));
		scanningassert.assertTrue(getElementText(ScanningPage.historyDescCol).equalsIgnoreCase(DESCRIPTION));
		leftPanelpageobj.clickOnLogout();
	}
	
	@Test(description = "H: Location from right panel of Queue Management >>Scanning", priority = 4)
	public void verifyLocationAddition() throws FindFailed {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Navigating to the Scanning Page");
		leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		logger.info("Searching the Scanned records in the Scanning Page");
		scanningobj.searchScannedRecords();
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Unlinking previous patient if any and Linking the test patient to the Scanned Records");
		scanningobj.selectPatient(PATIENTNAME1);
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Filling the Scanned Doc details in the right panel and storing the Location Value entered");
		str = scanningobj.rhtpnlEnterDetails();
		switchToParentFrame(Frames.SCANNING);
		
		logger.info("Verifying that the Location entered is saved successfully");
		scanningassert.assertTrue(getElementText(ScanningPage.firstScannedDocLocation).equalsIgnoreCase(str));
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "H: Chart Remarks from right panel of Queue Management >>Scanning", priority = 5)
	public void verifyAddChartRemarks() throws FindFailed {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Navigating to the Scanning Page");
		leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		logger.info("Searching the Scanned records in the Scanning Page");
		scanningobj.searchScannedRecords();
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		
		logger.info("Unlinking previous patient if any and Linking the test patient to the Scanned Records");
		scanningobj.selectPatient(PATIENTNAME1);
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Creating the Chart Remarks through Root Item");
		str = scanningobj.createChartRemarks();
		switchToParentFrame(Frames.TOOLTIP);
		logger.info("Selecting the Configured Chart Remarks");
		scanningobj.selectConfiguredChartRemarks(str);
		scanningobj.clickOnSave();
		switchToParentFrame(Frames.SCANNING);
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Verifying that the Created Chart Remarks is succssfully displayed");
		scanningassert.assertTrue(getElementText(ScanningPage.rhtpnlChart).contains(str+str));
		logger.info("Deleting the Configuredc chart Remarks");
		scanningobj.deleteConfiguredChartRemarks(str);
		leftPanelpageobj.clickOnLogout();
		
	}
	
	@Test(description = "H: Service from right panel of Queue Management >>Scanning", priority = 6)
	public void verifyAddServiceInScanning() throws FindFailed {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Navigating to the Scanning Page");
		leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		logger.info("Searching the Scanned records in the Scanning Page");
		scanningobj.searchScannedRecords();
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Unlinking previous patient if any and Linking the test patient to the Scanned Records");
		scanningobj.selectPatient(PATIENTNAME1);
		switchToParentFrame(Frames.SCANNINGPATIENT);
		str1 = scanningobj.selectServiceOnScanning();
		switchToParentFrame(Frames.SCANNING);
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Verifying that Service is saved successfully with the Scanned document");
		scanningassert.assertTrue(stringContainsSpaceSeparatedCsv(str1,getAttributeValue(ScanningPage.serviceTextBox, "value")));
		leftPanelpageobj.clickOnLogout();
	}
	
	@Test(description = "H: Procedure from right panel of Queue Management >>Scanning", priority = 7)
	public void verifyAddProcedureInScanning() throws FindFailed {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Navigating to the Scanning Page");
		leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		logger.info("Searching the Scanned records in the Scanning Page");
		scanningobj.searchScannedRecords();
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Unlinking previous patient if any and Linking the test patient to the Scanned Records");
		scanningobj.selectPatient(PATIENTNAME1);
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Selecting the Procedure with the Scanned Document");
		str1 = scanningobj.selectProcedureOnScanning();
		switchToParentFrame(Frames.SCANNING);
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Verifying that Procedure is saved successfully with the Scanned document");
		scanningassert.assertTrue(stringContainsSpaceSeparatedCsv(str1,getAttributeValue(ScanningPage.procedureTextBox, "value")));
		leftPanelpageobj.clickOnLogout();
	}
	
	@Test(description = "H: Dx from right panel of Queue Management >>Scanning", priority = 8)
	public void verifyAddDxStudiesInScanning() throws FindFailed {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Navigating to the Scanning Page");
		leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		logger.info("Searching the Scanned records in the Scanning Page");
		scanningobj.searchScannedRecords();
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Unlinking previous patient if any and Linking the test patient to the Scanned Records");
		scanningobj.selectPatient(PATIENTNAME1);
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Selecting the Procedure with the Scanned Document");
		str1 = scanningobj.selectDxstudyOnScanning();
		switchToParentFrame(Frames.SCANNING);
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Verifying that Procedure is saved successfully with the Scanned document");
		scanningassert.assertTrue(stringContainsSpaceSeparatedCsv(getAttributeValue(ScanningPage.dxstudyTextBox, "value"),str1));
		leftPanelpageobj.clickOnLogout();
	}
	
	@Test(description = "H: Orders from right panel of Queue Management >>Scanning", priority = 9)
	public void verifyAddOrder() throws FindFailed {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Navigating to the Scanning Page");
		leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		logger.info("Searching the Scanned records in the Scanning Page");
		scanningobj.searchScannedRecords();
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Unlinking previous patient if any and Linking the test patient to the Scanned Records");
		scanningobj.selectPatient(PATIENTNAME1);
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Selecting the Procedure with the Scanned Document");
		count = scanningobj.selectOrdersOnScanning();
		switchToParentFrame(Frames.SCANNING);
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		System.out.println("Element Count  "+count+ "  Orders Section "+ getElementCount(ScanningPage.ordersSection));
		scanningassert.assertTrue(getElementCount(ScanningPage.ordersSection)==(count-1));
        leftPanelpageobj.clickOnLogout();
	}
	
	@Test(description = "H: Diagonses from right panel of Queue Management >>Scanning", priority = 10)
	public void verifyAddDiagonses() throws FindFailed {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Navigating to the Scanning Page");
		leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		logger.info("Searching the Scanned records in the Scanning Page");
		scanningobj.searchScannedRecords();
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Unlinking previous patient if any and Linking the test patient to the Scanned Records");
		scanningobj.selectPatient(PATIENTNAME1);
		switchToParentFrame(Frames.SCANNINGPATIENT);
		logger.info("Selecting the Procedure with the Scanned Document");
		count = scanningobj.selectDiagonsesOnScanning();
		switchToParentFrame(Frames.SCANNING);
		
		logger.info("Selecting the First Scanned Records");
		scanningobj.selectImportedRow();
		switchToParentFrame(Frames.SCANNINGPATIENT);
		System.out.println("Element Count  "+count+ "  Orders Section "+ getElementCount(ScanningPage.diagnosesSection ));
		scanningassert.assertTrue(getElementCount(ScanningPage.diagnosesSection )==(count-1));
        leftPanelpageobj.clickOnLogout();
	}
	
	
	
	@AfterClass()
	public void classTearDown() {
		scanningobj = null;
		loginPageObj = null;
		leftPanelpageobj = null;

		logger.info("Exiting the classTearDown method for Document test class \n\n");
	}

}
