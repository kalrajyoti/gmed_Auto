package com.gmed.pages;

import static com.gmed.helper.DriverFactory.driver;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;

import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;

public class MessagingPage extends BaseAbstractPage {
	/** Logger to log the MessagingPage log messages */
	private static Logger logger                                  = LogManager.getLogger(MessagingPage.class);
	public static By messageContent                               = By.id("tblListInternalMessage_div");
	public static By messageQueue                                 = By.xpath(".//table[@id='tblListInternalMessage_Table']/tbody/tr");
	public static By closePopup                                   = By.id("Popup_CloseButton");
	public static By chartTitle                                   = By.id("txtTitle_TextBox");
	public static By messageContentInQueue                        = By.id("pnlEditTemplate_Div");
	
	
	
	/**contains the Messaging page data*/
	public static Map<String, String> messagingData;
	
	/**These are the variables which are used to store different data for Messaging module*/
	public static String firstLoggedUser;
	public static String secondLoggedUser;
	public static String attachementTitle;
	public static String messageToText;
	public static String chartNoteDescription1;
	public static String chartNoteDescription2;
	/** These are the variables which are present on "Messaging" sheet in the excel*/
	public static final String USER1 				                                          = "user1";
	public static final String USER2 				                                          = "user2";
	public static final String ATTACHMENT_TITLE 				                              = "attachementTitle";
	public static final String MESSAGE_TO_TEXT 				                                  = "messageToText";
	public static final String CHART_DESCRIPTION_TEXT1 				                          = "chartNoteValue1";
	public static final String CHART_DESCRIPTION_TEXT2 				                          = "chartNoteValue2";
	
	public void initClass() throws Exception{
		logger.info("inside the initClass method for DemographicsTest test class....");
		messagingData                                    = ExcelFileUtilty.readExcelSheet("Messaging");
		firstLoggedUser                                  = messagingData.get(USER1);
		secondLoggedUser                                 = messagingData.get(USER2);
		attachementTitle                                 = messagingData.get(ATTACHMENT_TITLE);
		messageToText                                    = messagingData.get(MESSAGE_TO_TEXT);
		chartNoteDescription1                            = messagingData.get(CHART_DESCRIPTION_TEXT1);
		chartNoteDescription2                            = messagingData.get(CHART_DESCRIPTION_TEXT2);
	}
	/**
	 * This method is used for double clicking on the created message
	 */
	public void doubleClickRecievedMessage(){
		sleep(3000);
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		SeleniumUtil.waitForProgressBar(Frames.HOME);
		List<WebElement> searchPatientrow = driver.findElements(messageQueue);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(ConstantsFile.messageIntialName) && rowtext.contains(MessagingPage.firstLoggedUser)){
				System.out.println("corrected Message row is displayed");
				SeleniumUtil.doubleClick(irows);	
				break;
			}
		}
	}

	/**
	 * This method is used to select Right click for the searched patient 
	 * @throws FindFailed 
	 *
	 */
	public  void rClickInMessage(String optionName) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		SeleniumUtil.waitForProgressBar(Frames.HOME);
		List<WebElement> searchPatientrow = driver.findElements(messageQueue);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(ConstantsFile.messageIntialName) && rowtext.contains(MessagingPage.firstLoggedUser)){
				System.out.println("corrected patient row is displayed");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWithTargetOffsetInSikuli(optionName);
				break;
			}
		}
	}
	/**
	 * This method is used to verify when user select  "To Chart Note" option from the header, it should work properly
	 * @return true if Chart note function is working properly
	 * @throws FindFailed
	 */
	public boolean verifyChartNoteDetails() throws FindFailed{
		boolean isChartNoteDeatilsAdded=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		String chartNoteTitleText=SeleniumUtil.getElementWithFluentWait(chartTitle).getAttribute("value");
		System.out.println("chartnote details are:"+chartNoteTitleText);
		if(chartNoteTitleText.contains(ConstantsFile.messageIntialName)){
			logger.info("Chart note function is working properly...");
			SeleniumUtil.switchToParentFrame(Frames.LOGIN);
			SeleniumUtil.getElementWithFluentWait(closePopup).click();
			//SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
			isChartNoteDeatilsAdded=true;
		}
		return isChartNoteDeatilsAdded;
	}
	/**
	 * This method is used to verify when user select"To task" option ,it should work properly
	 * @return true if To Task function is working properly
	 * @throws FindFailed
	 */
	public boolean verifyToTaskDetails() throws FindFailed{
		boolean isChartNoteDeatilsAdded=false;
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);	
		String taskSubjectText=SeleniumUtil.getElementWithFluentWait(TaskPage.taskSubjectTextbox).getAttribute("value");
		System.out.println("Task subject details are:"+taskSubjectText);
		if(taskSubjectText.equalsIgnoreCase(ConstantsFile.messageIntialName)){
			logger.info("Chart note function is working properly...");
			SeleniumUtil.getElementWithFluentWait(closePopup).click();
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
			isChartNoteDeatilsAdded=true;
		}
		return isChartNoteDeatilsAdded;
	}
	/**
	 * This method is used to verify queue should be open of the messaging module when user double click of the created message present in the home page
	 * @return ture if queue is opened
	 */
	public boolean verifyMessagingQueue(){
		SeleniumUtil.switchToParentFrame(Frames.MESSAGE);
		SeleniumUtil.waitForProgressBar(Frames.MESSAGE);
		boolean isMessagePresent=false;
		String messageSubject=SeleniumUtil.getElementWithFluentWait(messageContentInQueue).getText();
		System.out.println("Message Subject Is"+messageSubject);
		if(messageSubject.contains(ConstantsFile.messageIntialName) && messageSubject.contains(MessagingPage.firstLoggedUser)){
			System.out.println("Message is opened in the message queue ");
			isMessagePresent=true;
		}
		return isMessagePresent;
	}
	/**
	 * This method is used for clicking on various Tool bar present in Messaging module
	 */
	public void clickOnMessageToolBar(String toolBarName){
		SeleniumUtil.switchToParentFrame(Frames.MESSAGE);
		SeleniumUtil.waitForProgressBar(Frames.MESSAGE);
		sleep(5000);
		List<WebElement> noOfMenus = driver.findElements(ReportPage.toolBarInMips);
		System.out.println("No Of ToolBar Menu is:" +noOfMenus);
		for(WebElement menuName:noOfMenus){
			String menuText=menuName.getText();
			String altText=menuName.getAttribute("alt");
			System.out.println("Tool Bar text is:"+menuText);
			System.out.println("Alt text is:"+altText);
			if(menuText.equalsIgnoreCase(toolBarName)  ){
				menuName.click();
				break;
			}
			else if(altText!=null && altText.equalsIgnoreCase(toolBarName)){
				menuName.click();
				break;
			}

		}
	}
	public void switchInLeftNavigationFrameInMessage(){
		{
			SeleniumUtil.switchToParentFrame(Frames.MESSAGEPANEL);
			SeleniumUtil.waitForProgressBar(Frames.MESSAGEPANEL);
	}
		
	}

	/**
	 * This method is used to click on left navigation menu panel present in messaging moudle
	 * @param panelName are Inbox,Unread,Dent & Deleted
	 */
	public void clickOnLeftNavigationPanel(String panelName){
		
		List<WebElement> noOfMenus = driver.findElements(By.className("rtIn"));
		System.out.println("No Of left Navigation Menu is:" +noOfMenus.size());
		for(WebElement menu:noOfMenus){
			String menuText=menu.getText();
			System.out.println("Left Navigation  text is:"+menuText);
			if(menuText.equalsIgnoreCase(panelName)){
				menu.click();
				break;
			}
		}
	}
	/**
	 * This method is used to verify when user clicks on sent box present in left navigation panel
	 * then sent box panel should open
	 * @return true if  sent box panel should open
	 */
	public boolean verifySentBox()
	{
		SeleniumUtil.switchToParentFrame(Frames.MESSAGE);
		SeleniumUtil.waitForProgressBar(Frames.MESSAGE);
		boolean isMessagesent=false;
		String messageSubject=SeleniumUtil.getElementWithFluentWait(By.id("tblList_div")).getText();
		System.out.println("Message Subject Is"+messageSubject);
		if(messageSubject.contains("Subject / Recipient Date /")){
			System.out.println("sent box is open ");
			isMessagesent=true;
		}
		return isMessagesent;
	}
	/**
	 * This method is used to verify when user clicks on Delete box present in left navigation panel
	 * then sent box panel should open
	 * @return true if  delete box panel should open
	 */
	public boolean verifyDeleteBox()
	{
		SeleniumUtil.switchToParentFrame(Frames.MESSAGE);
		SeleniumUtil.waitForProgressBar(Frames.MESSAGE);
		boolean isMessagesent=false;
		String messageSubject=SeleniumUtil.getElementWithFluentWait(By.id("tblList_div")).getText();
		System.out.println("Message Subject Is"+messageSubject);
		if(messageSubject.contains("Subject / Sender / Recipient Date / /")){
			System.out.println("sent box is open ");
			isMessagesent=true;
		}
		return isMessagesent;
	}

	/**
	 * This method is used to verify when user select on "To Task" option, it should work properly
	 * @return true if To Task function is working properly
	 * @throws FindFailed
	 */
	public boolean verifyTaskDetails() throws FindFailed{
		boolean isTaskCreated=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		String chartNoteTitleText=SeleniumUtil.getElementWithFluentWait(TaskPage.taskSubjectTextbox).getAttribute("value");
		System.out.println("chartnote details are:"+chartNoteTitleText);
		if(chartNoteTitleText.contains(ConstantsFile.messageIntialName)){
			logger.info("To Task function is working properly...");
			SeleniumUtil.switchToParentFrame(Frames.LOGIN);
			SeleniumUtil.getElementWithFluentWait(closePopup).click();
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
			isTaskCreated=true;
		}
		return isTaskCreated;
	}
}
