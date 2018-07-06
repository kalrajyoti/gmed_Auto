package com.gmed.pages;




import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;


import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;


import com.gmed.utils.ConstantsFile;

import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;
import com.gpm.pages.BillingPage;

public class DirectMessagingPage extends BaseAbstractPage {
	/** Logger to log the DirectMessagingPage log messages */
	private static Logger logger                                     = LogManager.getLogger(DirectMessagingPage.class);
	public  static By recipientTextBox                               = By.id("txtTo_TextBox");
	public  static By directMessageQueue                             = By.id("pnlTable_Div");
	
	
	/**These are the variables which are used to store different data for Direct Message module*/
	
	public static String directMessageDetail;
	public static String recipientAddress;
	public static String providerDirectAddress;
	/**contains the Direct Messaging page data*/
	public static Map<String, String> directMessagingData;
	
	/** These are the variables which are present on "Direct Message" sheet in the excel*/
	public static final String DIRECT_MESSAGE_DETAILS 				                         = "directMessageDetails";
	public static final String RECIPIENT_ADDRESS 				                             = "recipientAddress";
	public static final String PROVIDER_ADDRESS				                                 = "providerName";
	
	
	
	public void initClass() throws Exception{
		logger.info("inside the initClass method for DemographicsTest test class....");
		directMessagingData                              = ExcelFileUtilty.readExcelSheet("Direct_Message");
		directMessageDetail                              = directMessagingData.get(DIRECT_MESSAGE_DETAILS);
		recipientAddress                                 = directMessagingData.get(RECIPIENT_ADDRESS);
		providerDirectAddress                            = directMessagingData.get(PROVIDER_ADDRESS);
	}

	/**
	 * This method is used to check verify the Direct Messaging page is opened when user select "Direct Message"
	 * from the queue management
	 * @return true if  Direct Messaging page is opened
	 */
	public boolean verifyDirectMessagePage(){
		boolean isDirectMessageTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.DIRECT_MESSAGE);
		String tiitle = SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Direct Messages")){
			System.out.println("Direct Messages Page is opened..");
			isDirectMessageTitle=true;
		}
		return isDirectMessageTitle;
	}

	/**
	 * This method is used to verify Direct message details i.e From ,To,Subject & Attachment labels are present
	 * @return true if all labels are present
	 */
	public boolean verifyMessageDetailScreen(){
		boolean isMessageDetailScreenPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String messageText =SeleniumUtil.getElementWithFluentWait(MessagingPage.messageContentInQueue).getText();
		logger.info("Message text is"+messageText);
		if(messageText.contains(directMessageDetail)){
			logger.info("Correct Message Detail screen present..");
			isMessageDetailScreenPresent=true;
		}
		return isMessageDetailScreenPresent;
	}
	/**
	 * This method is used for switching into tool tip frame
	 */
	public void switchIntoTooltipFrame(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for adding Message subject  for creating
	 * new  direct Message & clicking on sent button
	 */
	public void enterDirectMessageSubject() {
		String text = "This is subject for direct Message";
		ConstantsFile.directmessageIntialName = text.concat(ConstantsFile.genData.generateRandomNumber(5));
		SeleniumUtil.getElementWithFluentWait(TaskPage.taskSubjectTextbox).sendKeys(ConstantsFile.directmessageIntialName);
		SeleniumUtil.getElementWithFluentWait(TaskPage.sendButtonInTask).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for adding recipient direct address
	 */
	public void addRecipientAddress(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(recipientTextBox).sendKeys(recipientAddress);
	}
	/**
	 * This method is used for clicking on refresh button until status of direct queue changed from Pending to Sent
	 */
	public void clickOnRefresh(){
		String queueText=SeleniumUtil.getElementWithFluentWait(directMessageQueue).getText();
		System.out.println("queue text is"+queueText);
		String text =recipientAddress+" (Sent)"+"\n"+providerDirectAddress+"   "+ConstantsFile.directmessageIntialName;
		if(!queueText.contains(text)){
			SeleniumUtil.getElementWithFluentWait(ConfigurationPage.refreshButton).click();
			clickOnRefresh();
			
		}
	}

	/**
	 * This method is used to verify direct address queue in Outbound & Status of queue should be sent
	 * @return
	 */
	public boolean verifyDirectAddressQueueStatus(){
		boolean isSentStatusPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.DIRECT_MESSAGE);
		SeleniumUtil.waitForProgressBar(Frames.DIRECT_MESSAGE);
		clickOnRefresh();
		String queueText=SeleniumUtil.getElementWithFluentWait(directMessageQueue).getText();
		System.out.println("queue text is"+queueText);
		String text =recipientAddress+" (Sent)"+"\n"+providerDirectAddress+"   "+ConstantsFile.directmessageIntialName;
		System.out.println("current text is"+text);
		
		if(queueText.contains(text)){
			logger.info("Direct message queue is present in outbound queue & Status is Sent in the queue");
			isSentStatusPresent=true;
			
		}
		return isSentStatusPresent;

	}
	/**
	 * This method is used for switching into left Navigation Direct Message Frame
	 */
	public void switchInLeftNavigationFrameInDirectMessage(){
		SeleniumUtil.switchToParentFrame(Frames.DIRECT_MESSAGEPANEL);
		SeleniumUtil.waitForProgressBar(Frames.DIRECT_MESSAGEPANEL);
	}

}
