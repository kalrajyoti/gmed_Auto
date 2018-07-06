package com.gmed.test;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.AutoIT.GpinUserLogin;
import com.gmed.base.BaseTestClass;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.HomePage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MessagingPage;
import com.gmed.pages.TaskPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;

public class MessagingTest extends BaseTestClass {
	/** Logger to log the MessagingTest log messages */
	private static Logger logger  = LogManager.getLogger(MessagingTest.class);

	/**Assertion to verify different elements of the page */
	private Assertion messagingAssert = new Assertion();
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	
	/** Home Page reference used to creating new message for test  */
	private HomePage homePageObj;
	
	/** Messaging  Page reference used to generation Messaging data  for test  */
	private MessagingPage messagePageObj;
	/** Document page reference used for generating report data in report  module*/
	private DocumentPage docupageobj;
	
	/** Task Page reference used to verifying popup title for test  */
	private TaskPage taskPageObj;
	
	/**contains the Messaging page data*/
	public static Map<String, String> messagingData;
//	
//	/**These are the variables which are used to store different data for Messaging module*/
//	public static String firstLoggedUser;
//	public static String secondLoggedUser;
//	public static String attachementTitle;
//	public static String messageToText;
//	public static String chartNoteDescription1;
//	public static String chartNoteDescription2;
//	/** These are the variables which are present on "Messaging" sheet in the excel*/
//	public static final String USER1 				                                          = "user1";
//	public static final String USER2 				                                          = "user2";
//	public static final String ATTACHMENT_TITLE 				                              = "attachementTitle";
//	public static final String MESSAGE_TO_TEXT 				                                  = "messageToText";
//	public static final String CHART_DESCRIPTION_TEXT1 				                          = "chartNoteValue1";
//	public static final String CHART_DESCRIPTION_TEXT2 				                          = "chartNoteValue2";
	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for DemographicsTest test class....");
//		messagingData                                    = ExcelFileUtilty.readExcelSheet("Messaging");
//		firstLoggedUser                                  = messagingData.get(USER1);
//		secondLoggedUser                                 = messagingData.get(USER2);
//		attachementTitle                                 = messagingData.get(ATTACHMENT_TITLE);
//		messageToText                                    = messagingData.get(MESSAGE_TO_TEXT);
//		chartNoteDescription1                            = messagingData.get(CHART_DESCRIPTION_TEXT1);
//		chartNoteDescription2                            = messagingData.get(CHART_DESCRIPTION_TEXT2);
		loginPageObj                                     = new LoginPage();
		leftPanelpageobj                                 = new LeftPanelPage();		
		homePageObj                                      = new HomePage();
		taskPageObj                                      = new TaskPage();
		docupageobj                                      = new DocumentPage();
		messagePageObj                                   = new MessagingPage();
		messagePageObj.initClass();
	}
	@Test(description = "To verify basic functionality of Messages . ",groups = { "Messaging_Regression" },priority=1)
	public void verifyMessageFunctionality() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		messagingAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		homePageObj.clickOnMessagePlusButton();
		logger.info("verifying New Message tool tip window....");
		messagingAssert.assertTrue(taskPageObj.verifyPopupInHomePage());
		
		logger.info("clicking message to text box for adding first user....");
		homePageObj.switchIntoMessageFrame();
		homePageObj.clickOnPlus("txtToAdd");
		logger.info("searching & adding first user....");
		homePageObj.addUser(MessagingPage.firstLoggedUser);
		logger.info("clicking message to text box for adding second user....");
		homePageObj.switchIntoMessageFrame();
		homePageObj.clickOnPlus("txtToAdd");
		logger.info("searching & adding second user....");
		homePageObj.addUser(MessagingPage.secondLoggedUser);
		logger.info("clicking on Attachement plus button....");
		homePageObj.switchIntoMessageFrame();
		homePageObj.clickOnPlus("txtAttachmentAdd");
		logger.info("selecting attchement from local directory....");
		GpinUserLogin.openProvider();
		homePageObj.switchIntoMessageFrame();
		logger.info("entering message subject ....");
		homePageObj.enterMessageSubject();
		logger.info("verify correct message is received..");
		messagingAssert.assertTrue(homePageObj.verifyRecievedMessage());
		
		logger.info("double clicking the created Message..");
		messagePageObj.doubleClickRecievedMessage();
		logger.info("Verify Messaging module queue is opened....");
		messagingAssert.assertTrue(messagePageObj.verifyMessagingQueue());
		
		logger.info(" clicking reply option present in header of the created Message..");
		messagePageObj.clickOnMessageToolBar("Reply");
		logger.info(" verifying reply popup details..");
		messagingAssert.assertTrue(homePageObj.verifyMessagingDetails());
		
		logger.info(" clicking reply all option present in header of the created Message..");
		messagePageObj.clickOnMessageToolBar("Reply All");
		logger.info(" verifying reply all popup details..");
		messagingAssert.assertTrue(homePageObj.verifyMessagingDetails());
		
		logger.info(" clicking Forward option present in header of the created Message..");
		messagePageObj.clickOnMessageToolBar("Forward");
		logger.info(" verifying Forward popup details..");
		messagingAssert.assertTrue(homePageObj.verifyMessagingDetails());
		logger.info(" clicking Mark as unread option present in header of the created Message..");
		messagePageObj.clickOnMessageToolBar("Mark As Unread");
		
		logger.info(" clicking To Chart Note option present in header of the created Message..");
		messagePageObj.clickOnMessageToolBar("To Chart Note");
		logger.info(" verifying To Chart Note popup details..");
		messagingAssert.assertTrue(messagePageObj.verifyChartNoteDetails());
		
		logger.info(" clicking To Task Note option present in header of the created Message..");
		messagePageObj.clickOnMessageToolBar("To Task");
		logger.info(" verifying To Task popup details..");
		messagingAssert.assertTrue(messagePageObj.verifyTaskDetails());
		
		logger.info(" clicking To Delete option present in header of the created Message..");
		messagePageObj.clickOnMessageToolBar("Delete");
		logger.info(" clicking on No Option..");
		leftPanelpageobj.clickOnNo();
		
		logger.info("selecting sent option from left navigation panel..");
		messagePageObj.switchInLeftNavigationFrameInMessage();
		messagePageObj.clickOnLeftNavigationPanel("Sent");
		logger.info("verify sent box is opened from left navigation panel..");
		messagingAssert.assertTrue(messagePageObj.verifySentBox());
		logger.info("Verify Messaging module queue is opened....");
		messagingAssert.assertTrue(messagePageObj.verifyMessagingQueue());
		
		logger.info(" clicking To Delete option present in header of the sent Message..");
		messagePageObj.clickOnMessageToolBar("Delete");
		logger.info(" clicking on Yes Option..");
		docupageobj.clickOnYesPopUp();
		
		logger.info("selecting Deleted option from left navigation panel..");
		messagePageObj.switchInLeftNavigationFrameInMessage();
		messagePageObj.clickOnLeftNavigationPanel("Deleted");
		logger.info("verify Deleted box is opened from left navigation panel..");
		messagingAssert.assertTrue(messagePageObj.verifyDeleteBox());
		logger.info("Verify Messaging module queue is opened....");
		messagingAssert.assertTrue(messagePageObj.verifyMessagingQueue());
		logger.info("clicking Restore option present in header of the delete Message queue");
		messagePageObj.clickOnMessageToolBar("Restore");
		logger.info("selecting inbox option from left navigation panel..");
		messagePageObj.switchInLeftNavigationFrameInMessage();
		messagePageObj.clickOnLeftNavigationPanel("Inbox");
		logger.info("Verify Messaging module queue is opened....");
		messagingAssert.assertTrue(messagePageObj.verifyMessagingQueue());
		leftPanelpageobj.clickOnLogout();
		
		
		
		
}
}
