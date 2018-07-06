package com.gmed.test;

import static com.gmed.utils.StringUtility.stringContainsCsv;

import java.text.ParseException;
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
import com.gmed.pages.AppointmentPage;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.HomePage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.OrdersPage;
import com.gmed.pages.RecallsPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.DateUtil;
import com.gmed.utils.ExcelFileUtilty;

public class OrdersTest extends BaseTestClass {
	/** Logger to log the OrdersTest log messages */
	private static Logger				logger			= LogManager.getLogger(OrdersTest.class);
	/** Login Page reference used to login for logging in for test */
	private LoginPage					loginPageObj;
	/** Assertion to verify different elements of the page */
	private Assertion					ordersassert	= new Assertion();
	/** Page reference is used to call method in specific pages */
	private AppointmentPage             appointmentpage;
	private DocumentPage      documentpage;
	private HomePage          homepage;
	private MedicalChartPage  medicalpatientpage;
	private OrdersPage        orderspage;
	private RecallsPage        recallspage;
	/** This will store the key Value pair and used to read data from excel */
	public static Map<String, String>	documentData;
	public static String str;
	

	/**
	 * Following variable is used to store the value from the Application and
	 * later on used for comparison
	 */
	public static String LABFIELDS;
	public static String PATIENTNAME;
	public static String PATIENTNAME1;
	public static String PROVIDER;
	public static String ACTIVITY;
	public static String ORDERTYPE;
	public static String PROCEDURE;
	public static String ORDERSTATUS;
	public static String ORDERPRIORITY;
	public static String LABORDERTYPE;
	public static String LABNAME;
	public static String LOINCCODE;
	public static String PERFORMEDAT;
	public static String REASON;
	public static String ADDITIONALWORDING;
	public static String PHYSICIAN;
	public static String INSTRUCTION;
	public static String CPTCODE;
	public static String SNOMEDCODE;
	public static String ORDERNOTE;
	public static String FOLLOWUPFIELDS;
	public static String SPECIALTY;
	public static String RECALLTYPE;
	
	
	
	@BeforeClass
	public void initClass() throws Exception {
		documentData = ExcelFileUtilty.readExcelSheet("Orders");
		LABFIELDS = documentData.get("labfields");
		PATIENTNAME = documentData.get("patientname");
		PATIENTNAME1 = documentData.get("patientname1");
		PROVIDER = documentData.get("provider");
		ACTIVITY = documentData.get("activity");
		ORDERTYPE = documentData.get("ordertype");
		PROCEDURE = documentData.get("procedure");
		ORDERSTATUS = documentData.get("orderstatus");
		ORDERPRIORITY = documentData.get("orderpriority");
		LABORDERTYPE = documentData.get("labordertype");
		LABNAME = documentData.get("labname");
		LOINCCODE = documentData.get("loinccode");
		PERFORMEDAT = documentData.get("performedat");
		REASON = documentData.get("reason");
		ADDITIONALWORDING = documentData.get("additionalwording");
		PHYSICIAN = documentData.get("physician");
		INSTRUCTION = documentData.get("instruction");
		CPTCODE = documentData.get("cptcode");
		SNOMEDCODE = documentData.get("snomedcode");
		ORDERNOTE = documentData.get("ordernote");
		FOLLOWUPFIELDS = documentData.get("followupfields");
		SPECIALTY = documentData.get("specialty");
		RECALLTYPE = documentData.get("recalltype");
		
		loginPageObj = new LoginPage();
		appointmentpage = new AppointmentPage();
		documentpage = new DocumentPage();
		leftPanelpageobj = new LeftPanelPage();
		homepage  = new HomePage();
		medicalpatientpage = new MedicalChartPage();
		orderspage = new OrdersPage();
		recallspage = new RecallsPage();
	
	}
	
	@Test(description = "H: To Verify type of orders created from Plan Section", priority=1)
	public void verifyOrderType() throws Exception {
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		switchToParentFrame(Frames.HOME);
		logger.info("Clicking on plus sign at right of Home page to create an appointment");
		homepage.clickPlusAptmntCreation();
		switchToParentFrame(Frames.TASK);
		logger.info("Entering the Pateint Name in the Appointment");
        appointmentpage.enterPatientInAptmnt(PATIENTNAME);
        switchToParentFrame(Frames.TASK);
        logger.info("Entering the Provider Name in the Appointment");
        appointmentpage.enterProviderInAptmnt(PROVIDER);
        switchToParentFrame(Frames.TASK);
		logger.info("Entering the Activity Name in the Appointment");
		appointmentpage.enterActivityInAptmnt(ACTIVITY);
		switchToParentFrame(Frames.TASK);
		logger.info("Saving the Appointment");
		appointmentpage.clickOnSaveButtonofUserPage();
		clickOnImage("clickOnYes");
		switchToParentFrame(Frames.HOME);
		sleep(3000);
		homepage.rightClickOnScheduledAptmnt();
		clickOnImage("clickOnStartService");
		sleep(5000);
		logger.info("Navigating to Colonoscopy page which opens after starting the service");
		homepage.focusOnColonoscopyPage();
		logger.info("Opening up the new order pop up from the colonoscopy document");
		documentpage.openNewOrder();
		logger.info("Verifying that order pop up is opened as per the selection made");
		ordersassert.assertTrue(documentpage.getElementText(DocumentPage.newOrderPopUp).contains(ORDERTYPE));
		switchToParentFrame(Frames.LOGIN);
		logger.info("Closing the pop up");
		click(DocumentPage.popupCloseButton);
		clickOnImage("clickOnYes");
		logger.info("Logging Out...");
		leftPanelpageobj.clickOnLogout();
		
	}
	
	@Test(description = "H: To Verify Lab Order created from Plan Section", priority=2)
	public void verifyLabOrderCreation() throws ParseException{
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(PATIENTNAME1);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.planSection);
		documentpage.click(DocumentPage.planSection);
		switchToFrame("panUserList_Frame");
		switchToFrame("fraPlan_Frame");
		click(DocumentPage.newbutton);
		logger.info("Clicking on Lab Order");
		clickOnImage("clickOnLab");
		switchToParentFrame(Frames.TOOLTIP);
		ordersassert.assertTrue(stringContainsCsv(documentpage.getElementText(DocumentPage.allvisibletext), LABFIELDS));
		logger.info("Adding Recall Type Information to the Order");
        orderspage.addRecallType();
        switchToParentFrame(Frames.TOOLTIP);
		logger.info("Verifying Recall Date is as per the Recall Type selected");
		ordersassert.assertTrue(DateUtil.getFormattedDate(DateUtil.getFutureYear(),"MM/dd/yyyy").equalsIgnoreCase(orderspage.getAttributeValue(OrdersPage.recallMonth,"value")+"/"+orderspage.getAttributeValue(OrdersPage.recallDay, "value")+"/"+orderspage.getAttributeValue(OrdersPage.recallYear, "value")));
		orderspage.enterOrderTitle(LABNAME);
		logger.info("Verifying Default Status and Priority of Order is correct");
		ordersassert.assertTrue(orderspage.getAttributeValue(OrdersPage.status, "value").equalsIgnoreCase(ORDERSTATUS));
		ordersassert.assertTrue(orderspage.getAttributeValue(OrdersPage.priority, "value").equalsIgnoreCase(ORDERPRIORITY));
		orderspage.selectLabOrderType("ddlLabOrderType_Text", LABORDERTYPE);
		orderspage.selectLabName(LABNAME);
        orderspage.enterLoincCode(LOINCCODE);
        switchToParentFrame(Frames.TOOLTIP);
		orderspage.enterPerformedAt(PERFORMEDAT);
		orderspage.enterReason(REASON);
		orderspage.enterAdditionalWording(ADDITIONALWORDING);
		ordersassert.assertTrue(orderspage.getAttributeValue(OrdersPage.physician, "value").contains(PHYSICIAN));
		orderspage.enterPatientInstructions(INSTRUCTION);
        orderspage.enterCPTCode(CPTCODE);
        switchToParentFrame(Frames.TOOLTIP);
        orderspage.enterSnomedCode(SNOMEDCODE);
        switchToParentFrame(Frames.TOOLTIP);
		orderspage.enterOrderNote(ORDERNOTE);
		logger.info("Attaching the document in documnet Tab");
		orderspage.attachDocument();
		switchToParentFrame(Frames.TOOLTIP);
		orderspage.selectDiagnoses();
		switchToParentFrame(Frames.TOOLTIP);
		logger.info("Creating a task");
        orderspage.createTask();
        switchToParentFrame(Frames.TOOLTIP);
		orderspage.saveOrder();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that the created oder is displayed in the  Service document");
		ordersassert.assertTrue(getElementText(DocumentPage.planSection).equalsIgnoreCase(LABNAME));
		logger.info("Detleting the Order");
		orderspage.deleteOrder();
		ordersassert.assertFalse(getElementText(DocumentPage.planSection).equalsIgnoreCase(LABNAME));
		leftPanelpageobj.clickOnLogout();
		
	}
	
	@Test(description = "H: To Verify Follow-Up Order created from Plan Section", priority=3)
	public void verifyFollowupOrderCreation() throws ParseException{
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(PATIENTNAME1);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		documentpage.scroll(DocumentPage.planSection);
		documentpage.click(DocumentPage.planSection);
		switchToFrame("panUserList_Frame");
		switchToFrame("fraPlan_Frame");
		click(DocumentPage.newbutton);
		logger.info("Clicking on FollowUp Order");
		clickOnImage("clickOnFollowup");
		switchToParentFrame(Frames.TOOLTIP);
		ordersassert.assertTrue(stringContainsCsv(documentpage.getElementText(DocumentPage.allvisibletext), FOLLOWUPFIELDS));
		logger.info("Adding Recall Type Information to the Order");
        orderspage.addRecallType();
		switchToParentFrame(Frames.TOOLTIP);
		logger.info("Verifying Recall Date is as per the Recall Type selected");
		ordersassert.assertTrue(DateUtil.getFormattedDate(DateUtil.getFutureYear(),"MM/dd/yyyy").equalsIgnoreCase(orderspage.getAttributeValue(OrdersPage.recallMonth,"value")+"/"+orderspage.getAttributeValue(OrdersPage.recallDay, "value")+"/"+orderspage.getAttributeValue(OrdersPage.recallYear, "value")));
		orderspage.enterOrderTitle("Followup Order Created");
		logger.info("Verifying Default Status and Priority of Order is correct");
		ordersassert.assertTrue(orderspage.getAttributeValue(OrdersPage.status, "value").equalsIgnoreCase(ORDERSTATUS));
		ordersassert.assertTrue(orderspage.getAttributeValue(OrdersPage.priority, "value").equalsIgnoreCase(ORDERPRIORITY));
		orderspage.selectLabName(SPECIALTY);
 		orderspage.enterPerformedAt(PERFORMEDAT);
		orderspage.enterPerson("Test Person");
		orderspage.enterReason(REASON);
		orderspage.enterAdditionalWording(ADDITIONALWORDING);
		ordersassert.assertTrue(orderspage.getAttributeValue(OrdersPage.physician, "value").contains(PHYSICIAN));
		orderspage.enterPatientInstructions(INSTRUCTION);
        orderspage.enterCPTCode(CPTCODE);
		switchToParentFrame(Frames.TOOLTIP);
        orderspage.enterSnomedCode(SNOMEDCODE);
		switchToParentFrame(Frames.TOOLTIP);
		orderspage.enterOrderNote(ORDERNOTE);
		logger.info("Attaching the document in documnet Tab");
		orderspage.attachDocument();
		switchToParentFrame(Frames.TOOLTIP);
		logger.info("Select the diagonsis for patient");
		orderspage.selectDiagnoses();
		switchToParentFrame(Frames.TOOLTIP);
		logger.info("Creating a task");
        orderspage.createTask();
		switchToParentFrame(Frames.TOOLTIP);
		orderspage.saveOrder();
		documentpage.switchToFraDocumentFrame();
		logger.info("Verifying that the created oder is displayed in the  Service document");
		ordersassert.assertTrue(getElementText(DocumentPage.planSection).equalsIgnoreCase(SPECIALTY));
		leftPanelpageobj.clickOnLogout();
		
	}
	
	@Test(description = "H: To verify Order and Recall dependency on closing an Order", priority=4)
	public void verifyRecallDependency() throws FindFailed, ParseException{
		logger.info("Logging into application");
		loginPageObj.loginToGmed();
		logger.info("Searching the Patient by Name");
		medicalpatientpage.searchPatientByName(PATIENTNAME1);
		logger.info("This is dismissing the pop up for reminder");
		medicalpatientpage.dismissGuideline();
		logger.info("Clicking on Colonoscopy under the New Button");
		medicalpatientpage.clickOnProcedure(PROCEDURE);
		documentpage.switchToFraDocumentFrame();
		scroll(DocumentPage.planSection);
		click(DocumentPage.planSection);
		switchToFrame("panUserList_Frame");
		switchToFrame("fraPlan_Frame");
		click(DocumentPage.newbutton);
		logger.info("Clicking on FollowUp Order");
		str = ConstantsFile.genData.generateRandomString(8);
		orderspage.createOrder("clickOnFollowup", str);
        leftPanelpageobj.clickOnQueueManagement("clickOnOrder");
        switchToParentFrame(Frames.ORDER);
		orderspage.closingOrderWithRecall(str,"clickOnNo");
		leftPanelpageobj.clickOnQueueManagement("clickOnRecalls");
		switchToParentFrame(Frames.RECALLS);
		recallspage.searchRecallByProviderPatients(PATIENTNAME1, PROVIDER);
		ordersassert.assertTrue(recallspage.verifyRecallStatus(RECALLTYPE, DateUtil.getFormattedDate(DateUtil.getFutureYear(),"M/d/yyyy"), "Open"));
		switchToParentFrame(Frames.LEFTPANEL);
	    click(MedicalChartPage.medicalcharttext);
       	documentpage.switchToFraDocumentFrame();
		scroll(DocumentPage.planSection);
		click(DocumentPage.planSection);
		switchToFrame("panUserList_Frame");
		switchToFrame("fraPlan_Frame");
		click(DocumentPage.newbutton);
		click(DocumentPage.newbutton);
		logger.info("Clicking on FollowUp Order");
		str = ConstantsFile.genData.generateRandomString(8);
		orderspage.createOrder("clickOnFollowup", str);
        leftPanelpageobj.clickOnQueueManagement("clickOnOrder");
        switchToParentFrame(Frames.ORDER);
		orderspage.closingOrderWithRecall(str,"clickOnYes");
		leftPanelpageobj.clickOnQueueManagement("clickOnRecalls");
		switchToParentFrame(Frames.RECALLS);
		recallspage.searchRecallByStatus("Order Closed");
		ordersassert.assertTrue(recallspage.verifyRecallStatus(RECALLTYPE, DateUtil.getFormattedDate(DateUtil.getFutureYear(),"M/d/yyyy"), "Closed"));
		leftPanelpageobj.clickOnLogout();
		
}
	
	@AfterClass()
	public void classTearDown() {
		appointmentpage = null;
		loginPageObj = null;
		leftPanelpageobj = null;
		documentpage = null;
		homepage = null;
		medicalpatientpage = null;
		orderspage = null;
		recallspage= null;
		logger.info("Exiting the classTearDown method for Document test class \n\n");
	}

	

}

