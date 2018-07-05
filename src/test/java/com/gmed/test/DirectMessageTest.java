package com.gmed.test;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.AutoIT.GpinUserLogin;
import com.gmed.pages.DirectMessagingPage;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.HomePage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MessagingPage;
import com.gmed.pages.OrdersPage;
import com.gmed.pages.TaskPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;

public class DirectMessageTest extends BaseTestClass {
	/** Logger to log the DirectMessageTest log messages */
	private static Logger logger  = LogManager.getLogger(DirectMessageTest.class);

	/**Assertion to verify different elements of the page */
	private Assertion directMessagingAssert = new Assertion();
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;
	
	/** OrdersPage  reference used to creating new direct message for test  */
	private OrdersPage orderPageObj;
	
	/** Direct Messaging  Page reference used to generation Direct Messaging  data  for test  */
	private DirectMessagingPage directMessagePageObj;

	/** Home Page reference used to using home page data for test  */
	private HomePage homePageObj;

	/** Messaging  Page reference used to generation of Messaging data  for test  */
	private MessagingPage messagePageObj;
	
	/**contains the Direct Messaging page data*/
	public static Map<String, String> directMessagingData;
	
	/**These are the variables which are used to store different data for Direct Message module*/
	
	public static String directMessageDetail;
	public static String recipientAddress;
	public static String providerDirectAddress;
	
	/** These are the variables which are present on "Direct Message" sheet in the excel*/
	public static final String DIRECT_MESSAGE_DETAILS 				                         = "directMessageDetails";
	public static final String RECIPIENT_ADDRESS 				                             = "recipientAddress";
	public static final String PROVIDER_ADDRESS				                                 = "providerName";
	
	
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for DemographicsTest test class....");
		directMessagingData                              = ExcelFileUtilty.readExcelSheet("Direct_Message");
		directMessageDetail                              = directMessagingData.get(DIRECT_MESSAGE_DETAILS);
		recipientAddress                                 = directMessagingData.get(RECIPIENT_ADDRESS);
		providerDirectAddress                            = directMessagingData.get(PROVIDER_ADDRESS);
		loginPageObj                                     = new LoginPage();
		leftPanelpageobj                                 = new LeftPanelPage();		
		orderPageObj                                     = new OrdersPage();
		messagePageObj                                   = new MessagingPage();
		directMessagePageObj                             = new DirectMessagingPage();
		homePageObj                                      = new HomePage();
	}
	@Test(description = "To verify sending functionality of direct Messages . ",groups = { "DirectMessaging_Regression" },priority=1)
	public void verifySendingFunctionality() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		directMessagingAssert.assertTrue(loginPageObj.verifyHomePageTitle());
		
		logger.info("click on Direct Messaging from queue management...");
		leftPanelpageobj.clickOnQueueManagement("clickOnDirectMessaging");
		logger.info("Verify Direct Message Page is opened......");
		directMessagingAssert.assertTrue(directMessagePageObj.verifyDirectMessagePage());
		
		logger.info("clicking New Button for creating new Direct message ....");
		orderPageObj.clickOnNewButton();
		logger.info("verify New Message Tool Tip Window is opened..");
		directMessagingAssert.assertTrue(leftPanelpageobj.verifyToolTipTitle("New Message"));
		
		logger.info("verify the direct address from ,To ,Subject & attachment label in message Screen..");
		directMessagingAssert.assertTrue(directMessagePageObj.verifyMessageDetailScreen());
		logger.info("adding recipient to in the direct message toottip window...");
		directMessagePageObj.addRecipientAddress();
		
		logger.info("clicking on  attachment plus sign for direct message..");
		directMessagePageObj.switchIntoTooltipFrame();
		homePageObj.clickOnPlus("txtAttachmentAdd");
		logger.info("selecting attchement from local directory....");
		GpinUserLogin.openProvider();
		directMessagePageObj.switchIntoTooltipFrame();
		logger.info("entering message subject ....");
		directMessagePageObj.enterDirectMessageSubject();
		

		logger.info("selecting outbound option from left navigation panel..");
		directMessagePageObj.switchInLeftNavigationFrameInDirectMessage();
		messagePageObj.clickOnLeftNavigationPanel("Outbound");
		logger.info("verify direct address queue is present in the outbound queue & status should be sent");
		directMessagingAssert.assertTrue(directMessagePageObj.verifyDirectAddressQueueStatus());
		logger.info("logging out from the application");
		leftPanelpageobj.clickOnLogout();		
}
	@AfterClass()
	public void classTearDown(){
		loginPageObj                                = null;
		leftPanelpageobj                            = null;
		orderPageObj                                = null;
		messagePageObj                              = null;
		directMessagePageObj                        = null;
		homePageObj                                 = null;
		logger.info("Exiting the classTearDown method for Direct Message test class \n\n");
	}
}
