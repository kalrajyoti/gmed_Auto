package com.gmed.test;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.TaskPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gpm.pages.BillingPage;
public class TaskTest extends BaseTestClass {
	/** Logger to log the TaskTest log messages */
	private static Logger logger = LogManager.getLogger(TaskTest.class);
	/** Assertion to verify different elements of the page */
	private Assertion taskAssert = new Assertion();
	/** Login Page reference used to login for logging in for test */
	private LoginPage loginPageObj;

	/** Task Page reference used for Task data */
	private TaskPage taskPageObj;

	/** Billing Page reference used for clicking on search button  */
	private BillingPage billingobj;
	/**
	 * MedicalChart Page reference for initiating the medical data present in
	 * medical page
	 */
	private MedicalChartPage medicalchartpage;
	
	/**
	 * Document page reference is used for clicking on yes button function present in 
	 * document page
	 */
	 private DocumentPage documentpageobj;

	/** contains the Profile page data */
	public static Map<String, String> taskData;
	/**
	 * These are the variables which are used to store different data for
	 * medical chart module
	 */
	public static String existingPatientfirstname;
	public static String existingPatientlastname;
	public static String recipientsForTask;
	public static String completePatientName;
	public static String secondUserName;
	public static String secondUserPassword;
	public static String reassignRecipientsForTask;
	public static String reassignDetails;
	public static String snoozeToolTipTitle;
	public static String snoozeText;
	public static String rejectDetails;
	public static String recipientsNames;
	
	/**
	 * These are the variables which are present on "Profile" sheet in the excel
	 */
	public static final String PATIENT_FIRSTNAME           = "patientfirstname";
	public static final String PATIENT_LASTNAME            = "patientlastname";
	public static final String RECIPIENT_NAME              = "taskRecipients";
	public static final String PATIENT_COMPLETE_NAME       = "completePatientName";
	public static final String SECOND_USERNAME             = "username";
	public static final String SECOND_USERNAME_PASSWORD    = "password";
	public static final String REASSIGN_RECIPIENT_NAME     = "reassignRecipients";
	public static final String REASSIGN_DETAILS            = "reassignDetails";
	public static final String TOOL_TIP_TITLE              = "snoozeToolTipTitle";
	public static final String SNOOZE_TEXT                 = "snoozetextintaskhistory";
	public static final String REJECT_DETAILS              = "rejectDetails";
	public static final String RECIPIENS_NAMES              = "recipientsnames";
	

	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception {
		logger.info("inside the initClass method for Task test class....");
		taskData                           = ExcelFileUtilty.readExcelSheet("Task");
		existingPatientfirstname           = taskData.get(PATIENT_FIRSTNAME);
		existingPatientlastname            = taskData.get(PATIENT_LASTNAME);
		recipientsForTask                  = taskData.get(RECIPIENT_NAME);
		completePatientName                = taskData.get(PATIENT_COMPLETE_NAME);
		secondUserName                     = taskData.get(SECOND_USERNAME);
		secondUserPassword                 = taskData.get(SECOND_USERNAME_PASSWORD);
		reassignRecipientsForTask          = taskData.get(REASSIGN_RECIPIENT_NAME);
		reassignDetails                    = taskData.get(REASSIGN_DETAILS);
		snoozeToolTipTitle                 = taskData.get(TOOL_TIP_TITLE);
		snoozeText                         = taskData.get(SNOOZE_TEXT);
		rejectDetails                      = taskData.get(REJECT_DETAILS);
		recipientsNames                    = taskData.get(RECIPIENS_NAMES);
		loginPageObj                       = new LoginPage();
		medicalchartpage                   = new MedicalChartPage();
		leftPanelpageobj                   = new LeftPanelPage();
		taskPageObj                        = new TaskPage();
		billingobj                         = new BillingPage();
		documentpageobj                    = new DocumentPage();

	}

	@Test(description = "To verify that user can reassign any task. ", groups = {"Task_Regression"}, priority = 1)
	public void verifyReassignTask() throws Exception {
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		taskAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		
		logger.info("clicking on My Activities menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("My Activities");
		logger.info("clicking on Task Plus Sign present in the home page...");
		taskPageObj.clickOnTaskPlusSign();
		logger.info("verifying task tool tip window....");
		taskAssert.assertTrue(taskPageObj.verifyPopupInHomePage());
		
		taskPageObj.selectRecipient();
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(existingPatientfirstname,existingPatientlastname);
		taskAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(existingPatientfirstname, existingPatientlastname));
		
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		logger.info("verify correct patient name is populated..");
		taskAssert.assertTrue(taskPageObj.verifyPatientNamePopulatedInTask(),"correct patient name should be present");
		
		logger.info("enter subject & message for creating new Task for patient");
		taskPageObj.enterTaskData();
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
		
		logger.info(" Login with another user which we used as Recipients while creating new Task");
		loginPageObj.loginToGmedWithBreakTheGlassUser(secondUserName,secondUserPassword);
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle(),"Title should contain gMed gGastro");
		
		logger.info("clicking on My Activities menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("My Activities");
		logger.info("verify task should be created for another login user");
		taskAssert.assertTrue(taskPageObj.verifyTaskIsCreated());
		logger.info("selecting the created task..");
		taskPageObj.selectTask();
		
		logger.info("selecting the Reassign menu in the task");
		taskPageObj.selectTaskMenuItems("Reassign");
		logger.info("adding details for reassigning task");
		taskPageObj.reassignTask();
		taskPageObj.clickOnReassign();
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
		
		logger.info("logging again in gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		taskAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		
		logger.info("clicking on My Activities menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("My Activities");
		logger.info("selecting the created task..");
		taskPageObj.selectTask();
		logger.info("verify Reassign status in Task window");
		taskAssert.assertTrue(taskPageObj.verifyTaskDetails());
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that any sent task should be available in My Task ",groups = { "Task_Regression" },priority=2)
	public void verifyTaskInMyTask() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		logger.info("clicking on My Task  menu  present in Queue Management...");
		leftPanelpageobj.clickOnQueueManagement("clickOnMyTask");
		logger.info("Verify My Task Page is opened...");
		taskAssert.assertTrue(taskPageObj.verifyTaskManagerPage());
		
		logger.info("clicking on outbound tab for verify sent task...");
		taskPageObj.clickOnOutboundTab();
		logger.info("verify that sent task should be available in My Task...");
		taskAssert.assertTrue(taskPageObj.verifyTask());
		logger.info("logging out the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that any sent task should be available in Task Manager ",groups = { "Task_Regression" },priority=3)
	public void verifyTaskInTaskManager() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		logger.info("clicking on Task Manager  menu  present in Queue Management...");
		leftPanelpageobj.clickOnQueueManagement("clickOnTaskManager");
		logger.info("Verify Task ManagerPage is opened...");
		taskAssert.assertTrue(taskPageObj.verifyTaskManagerPage());
		
		logger.info("selecting for patient in Task Manager..");
		taskPageObj.selectPatientInTask(existingPatientfirstname,existingPatientlastname);
		taskAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(existingPatientfirstname,existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		taskPageObj.switchToTaskManagerFrame();
		logger.info("clicking on search button..");
		billingobj.clickOnSearchButton();
		
		logger.info("verify that sent task should be available in Task Manager...");
		taskAssert.assertTrue(taskPageObj.verifyTask());
		logger.info("logging out the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user can reply any task ",groups = { "Task_Regression" },priority=4)
	public void verifyReplyTask() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		logger.info("clicking on Task Manager menu  present in Queue Management...");
		leftPanelpageobj.clickOnQueueManagement("clickOnTaskManager");
		logger.info("Verify Task Manager Page is opened...");
		taskAssert.assertTrue(taskPageObj.verifyTaskManagerPage(), "Page name should be Task Manager");
		
		logger.info("select patient for searching task in Task manager");
		taskPageObj.selectPatientInTask(existingPatientfirstname,existingPatientlastname);
		taskAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(existingPatientfirstname,existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		taskPageObj.switchToTaskManagerFrame();
		logger.info("searching the patient in task");
		billingobj.clickOnSearchButton();
		
		logger.info("Right clicking on searched row & select reply option in Task Manager...");
		taskPageObj.rClickOnSerchedPatientInTask("rclickReplyInTask");
		logger.info("Verify Reply to sender Page is opened...");
		taskAssert.assertTrue(taskPageObj.verifySenderPage(), "correct sender page should be displayed");
		
		logger.info("select sender for task for reply");
		taskPageObj.selectSenderForTask();
		logger.info("enter task info for reply the task");
		taskPageObj.enterSenderData();
		logger.info("logging out the application");
		leftPanelpageobj.clickOnLogout();
		
		logger.info("logging into gmed application with another user...");
		loginPageObj.loginToGmedWithBreakTheGlassUser(secondUserName, secondUserPassword);
		taskAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("clicking on My Activities menu present in left panel...");
		
		leftPanelpageobj.clickLeftNavigationPanelValue("My Activities");
		logger.info("verify Reply subject should display  in Task window");
		taskAssert.assertTrue(taskPageObj.verifyTaskIsCreated());
		logger.info("logging out the application");
		leftPanelpageobj.clickOnLogout();	
	}
	@Test(description = "To verify that user can Snooze task ",groups = { "Task_Regression" },priority=5)
	public void verifySnoozeTask() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithBreakTheGlassUser(secondUserName, secondUserPassword);		
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		logger.info("clicking on My Activities menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("My Activities");
		taskAssert.assertEquals(taskPageObj.verifyTaskIsCreated(), true);
		logger.info("selecting the task..");
		taskPageObj.selectTask();
		taskPageObj.switchToTaskFrame();
		
		logger.info("clicking on Snooze button for completing the task");
		taskPageObj.completeTask("Snooze");
		taskAssert.assertEquals(taskPageObj.verifySnoozePopup(), "Follow Up Date");
		taskPageObj.addSnoozeDetails();
		
		logger.info("clicking on Task Manager menu  present in Queue Management...");
		leftPanelpageobj.clickOnQueueManagement("clickOnTaskManager");
		logger.info("Verify Task Manager Page is opened...");
		taskAssert.assertTrue(taskPageObj.verifyTaskManagerPage(), "Page name should be Task Manager");
		
		logger.info("select patient for searching task in Task manager");
		taskPageObj.selectPatientInTask(existingPatientfirstname,existingPatientlastname);
		taskAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(existingPatientfirstname,existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		taskPageObj.switchToTaskManagerFrame();
		logger.info("searching the patient in task");
		billingobj.clickOnSearchButton();
		
		logger.info("Right clicking on searched row & select view option in Task Manager...");
		taskPageObj.rClickOnSerchedPatientInTask("viewTask");
		logger.info("clicking on task history");
		taskPageObj.clickOnTaskHistory();
		
		logger.info("verify Task is snoozed in task history page");
		taskAssert.assertTrue(taskPageObj.verifySnoozeInTaskHistoryPage());
		logger.info("logging out the application");
		leftPanelpageobj.clickOnLogout();	
	}
	@Test(description = "To verify that user can complete task ",groups = { "Task_Regression" },priority=6)
	public void verifyCompleteTask() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithBreakTheGlassUser(secondUserName, secondUserPassword);	
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		logger.info("clicking on Task Manager  menu  present in Queue Management...");
		leftPanelpageobj.clickOnQueueManagement("clickOnTaskManager");
		logger.info("Verify Task ManagerPage is opened...");
		taskAssert.assertTrue(taskPageObj.verifyTaskManagerPage());
		
		logger.info("selecting for patient in Task Manager..");
		taskPageObj.selectPatientInTask(existingPatientfirstname,existingPatientlastname);
		taskAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(existingPatientfirstname,existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		taskPageObj.switchToTaskManagerFrame();
		logger.info("clicking on search button..");
		billingobj.clickOnSearchButton();
		
		logger.info("Right clicking on searched row & select view option in Task Manager...");
		taskPageObj.rClickOnSerchedPatientInTask("viewTask");
		taskPageObj.switchToCombinedFrame();
		logger.info("clicking on complete button for completing the task");
		taskPageObj.completeTask("Complete");
		taskPageObj.switchToTaskManagerFrame();
		
		logger.info("verifing task is completed in Task Manager...");
		taskAssert.assertTrue(taskPageObj.verifyTaskStatus("Completed"));
		logger.info("logging out the application");
		leftPanelpageobj.clickOnLogout();	
	}
	@Test(description = "To verify that user can delete any task ",groups = { "Task_Regression" },priority=7)
	public void verifyDeleteTask() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithBreakTheGlassUser(secondUserName, secondUserPassword);	
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		logger.info("clicking on Task Manager menu  present in Queue Management...");
		leftPanelpageobj.clickOnQueueManagement("clickOnTaskManager");
		logger.info("Verify Task Manager Page is opened...");
		taskAssert.assertTrue(taskPageObj.verifyTaskManagerPage(), "Page name should be Task Manager");
		
		logger.info("select patient for searching task in Task manager");
		taskPageObj.selectPatientInTask(existingPatientfirstname,existingPatientlastname);
		taskAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(existingPatientfirstname,existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		taskPageObj.switchToTaskManagerFrame();
		logger.info("searching the patient in task");
		billingobj.clickOnSearchButton();
		
		logger.info("Right clicking on searched row & select delete option in Task Manager...");
		taskPageObj.rClickOnSerchedPatientInTask("selectDelete");
		documentpageobj.clickOnYesPopUp();
		logger.info("selecting the delete status");
		taskPageObj.searchPatientWithStatus("Deleted");
		taskPageObj.switchToTaskManagerFrame();
		logger.info("searching the patient in task");
		billingobj.clickOnSearchButton();
		
		logger.info("verify Task is deleted from Task manager..");
		taskAssert.assertTrue(taskPageObj.verifyTaskIsDeleted(), "deleted task row should be display");
		logger.info("logging out the application");
		leftPanelpageobj.clickOnLogout();	
	}
	@Test(description = "To verify that user can reject any task. ", groups = {"Task_Regression"}, priority = 8)
	public void verifyRejectTask() throws Exception {
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		logger.info("clicking on My Activities menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("My Activities");
		logger.info("clicking on Task Plus Sign present in the home page...");
		taskPageObj.clickOnTaskPlusSign();
		logger.info("verifying task tool tip window....");
		taskAssert.assertTrue(taskPageObj.verifyPopupInHomePage());
		taskPageObj.selectRecipient();
		
		logger.info("Searching the patient with first name & last name in medical chart...");
		medicalchartpage.searchExistingPatientWithName(existingPatientfirstname,existingPatientlastname);
		taskAssert.assertTrue(medicalchartpage.verifyPatientIsSerchedWithName(existingPatientfirstname, existingPatientlastname));
		logger.info("selecting the patient...");
		medicalchartpage.selectPatient();
		logger.info("verify correct patient name is populated..");
		taskAssert.assertTrue(taskPageObj.verifyPatientNamePopulatedInTask(),"correct patient name should be present");
		logger.info("enter subject & message for creating new Task for patient");
		taskPageObj.enterTaskData();
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
		
		logger.info(" Login with another user which we used as Recipients while creating new Task");
		loginPageObj.loginToGmedWithBreakTheGlassUser(secondUserName,secondUserPassword);
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle(),"Title should contain gMed gGastro");
		
		logger.info("clicking on My Activities menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("My Activities");
		logger.info("verify task should be created for another login user");
		taskAssert.assertTrue(taskPageObj.verifyTaskIsCreated());
		logger.info("selecting the created task..");
		taskPageObj.selectTask();
		
		logger.info("selecting the Reject menu in the task");
		taskPageObj.selectTaskMenuItems("Reject");
		logger.info("adding details for rejecting task");
		taskPageObj.enterReasonForRejct();
		logger.info("clicking on send button for rejecting task");
		taskPageObj.clickOnSend();
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();
		
		logger.info("logging again in gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		taskAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		
		logger.info("clicking on My Activities menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("My Activities");
		logger.info("selecting the created task..");
		taskPageObj.selectTask();
		logger.info("verify Reject status in Task window");
		taskAssert.assertTrue(taskPageObj.verifyTaskDetails());
		leftPanelpageobj.clickOnLogout();
}
	@Test(description = "To verify user can recall task ",groups = { "Task_Regression" },priority=9)
	public void verifyRecall() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		logger.info("clicking on My Task  menu  present in Queue Management...");
		leftPanelpageobj.clickOnQueueManagement("clickOnMyTask");
		logger.info("Verify My Task Page is opened...");
		taskAssert.assertTrue(taskPageObj.verifyTaskManagerPage());
		
		logger.info("clicking on outbound tab for recalling task...");
		taskPageObj.clickOnOutboundTab();
		
		logger.info("right click in outbound & select recall option");
		taskPageObj.rclickInOutbound("recall");
		taskPageObj.enterReasonForRecall();
		documentpageobj.clickOnOkay();
		
		leftPanelpageobj.clickOnMyActvities();
		logger.info("verify that sent task should be available in My Task...");
		taskAssert.assertTrue(taskPageObj.verifyTaskIsRecalled());
		logger.info("logging out the application");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that user can create child  task. ", groups = {"Task_Regression"}, priority = 10)
	public void verifyChildTask() throws Exception {
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		taskAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		logger.info("clicking on My Activities menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("My Activities");
		taskAssert.assertTrue(taskPageObj.verifyTaskIsCreated());
		logger.info("selecting the task..");
		taskPageObj.selectTask();
		taskPageObj.switchToTaskFrame();
	
		logger.info("clicking on Task button for completing the task");
		taskPageObj.completeTask("Task");
		
		logger.info("verifying task tool tip window....");
		taskAssert.assertTrue(taskPageObj.verifyTaskPopupIsDisplayed());
		logger.info("adding child Task Details..");
		taskPageObj.addChildTaskDetails();
		taskPageObj.enterChildData();
		
		logger.info("clicking on My Activities menu present in left panel...");
		leftPanelpageobj.clickLeftNavigationPanelValue("My Activities");
		logger.info("selecting the created task..");
		taskPageObj.selectTask();
		
		logger.info("selecting the Parent Task menu in the task");
		taskPageObj.selectTaskMenuItems("Parent Task");
        
		
		logger.info("verify child Task is created in task history page");
		taskAssert.assertTrue(taskPageObj.verifyChildTask());
		leftPanelpageobj.clickOnLogout();
	}
	@AfterClass()
	public void classTearDown(){
		loginPageObj                                = null;
		leftPanelpageobj                            = null;
		medicalchartpage                            = null;
		taskPageObj                                 = null;
		billingobj                                  = null;
		documentpageobj                             = null;
		logger.info("Exiting the classTearDown method for Task test class \n\n");
	}
	
}
