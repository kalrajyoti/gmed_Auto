package com.gmed.pages;

import static com.gmed.helper.DriverFactory.driver;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import com.gmed.Frames.Frames;


import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;

public class HomePage  extends com.gmed.base.BaseAbstractPage {
	/** Logger to log the HomePage log messages */
	private static Logger logger                                  = LogManager.getLogger(HomePage.class);
	public static By messagePlusButtons                           = By.id("btnNewInternalMessage_Image");
	public static By attachmentTextBox                            = By.id("txtAttachment_TextBox");
	public static By MessageToTextBox                             = By.id("txtTo_TextBox");
	public static By plusSignToCreateAptmnt                       = By.id("btnNewsnpAppointment_Image");
	public static By scheduledAppointment                         = By.xpath("//table[@class='tableData']/tbody//span[text()='Scheduled']");
	


	/**
	 * This method is used to click on Message plus button
	 */
	public void clickOnMessagePlusButton(){
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		SeleniumUtil.waitForProgressBar(Frames.HOME);
		logger.info("clicking on Message plus button...");
		SeleniumUtil.getElementWithFluentWait(messagePlusButtons).click();
	}
	/**
	 * This method is used for clicking on Plus button for adding new recipient,user
	 * @param 
	 */
	public void clickOnPlus(String lebelName){
		/*SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);*/
		List<WebElement> noOfTabs = driver.findElements(By.className("TextBoxAddButton"));
		System.out.println("No Of Tabs are:" +noOfTabs.size());
		for(WebElement tab:noOfTabs){
			String tabText=tab.getAttribute("id");
			System.out.println("Tab text is:"+tabText);
			if(tabText.contains(lebelName)){
				tab.click();
				break;
			}
		}
	}

	/**
	 * This method is used for adding recipients For Task module
	 */
	public void addUser(String userName) {
		boolean isLoggedInUserPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		SeleniumUtil.waitForProgressBar(Frames.PATIENT_SEARCHING);

		if(userName.equalsIgnoreCase("Automation test")){
			SeleniumUtil.getElementWithFluentWait(TaskPage.recipientTextBox).sendKeys(userName);
		}
		else{
			SeleniumUtil.getElementWithFluentWait(TaskPage.recipientTextBox).sendKeys(userName);
		}
		SeleniumUtil.getElementWithFluentWait(TaskPage.recipientSearchIcon).click();
		sleep(5000);

		List<WebElement> noOfRecipientsrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF RECIPIENTS IN THIS TABLE = " + noOfRecipientsrow.size());
		int row_num, col_num;
		row_num = 1;
		for (WebElement trElement : noOfRecipientsrow) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
			col_num = 1;
			for (WebElement tdElement : td_collection) {
				String rowText = tdElement.getText();
				System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
				col_num++;
				if (rowText.equalsIgnoreCase(userName)) {
					System.out.println("corrected user is selected");
					SeleniumUtil.doubleClick(tdElement);
					isLoggedInUserPresent=true;
					break;
				}

			}
			if(isLoggedInUserPresent){
				break;
			}
			else{
				row_num++;
			}
		}
	}
	public void switchIntoMessageFrame(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
	}
	public boolean verifyMessagingDetails() throws FindFailed{


		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);

		boolean isMessagingDeatilsAdded=false;
		sleep(5000);
		String attachmentText=SeleniumUtil.getElementWithFluentWait(attachmentTextBox).getAttribute("value");
		System.out.println("attchement text"+attachmentText);
		String messageText=SeleniumUtil.getElementWithFluentWait(MessageToTextBox).getAttribute("value");
		System.out.println("message to text"+messageText);
		String subjectText=SeleniumUtil.getElementWithFluentWait(TaskPage.taskSubjectTextbox).getAttribute("value");
		System.out.println("subject text"+subjectText);

		if(messageText.equalsIgnoreCase(MessagingPage.messageToText) && attachmentText.equalsIgnoreCase(MessagingPage.attachementTitle) ){
			logger.info("correct users & Attachement are added");
			isMessagingDeatilsAdded=true;
		}
		else if(messageText.equalsIgnoreCase(MessagingPage.messageToText)  ){
			logger.info("correct reply all user is added..");
			SeleniumUtil.switchToParentFrame(Frames.LOGIN);
			SeleniumUtil.getElementWithFluentWait(MessagingPage.closePopup).click();
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
			isMessagingDeatilsAdded=true;
		}
		else if(messageText.equalsIgnoreCase(MessagingPage.firstLoggedUser)  ){
			logger.info("correct reply user is added..");
			SeleniumUtil.switchToParentFrame(Frames.LOGIN);
			SeleniumUtil.getElementWithFluentWait(MessagingPage.closePopup).click();
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
			isMessagingDeatilsAdded=true;
		}
		else if(subjectText.equalsIgnoreCase("FW: "+ConstantsFile.messageIntialName)  ){
			logger.info("correct forward detail is added..");
			SeleniumUtil.switchToParentFrame(Frames.LOGIN);
			SeleniumUtil.getElementWithFluentWait(MessagingPage.closePopup).click();
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
			isMessagingDeatilsAdded=true;
		}
		else if(subjectText.equalsIgnoreCase("FW: "+ConstantsFile.messageIntialName)  ){
			logger.info("correct forward detail is added..");
			SeleniumUtil.switchToParentFrame(Frames.LOGIN);
			SeleniumUtil.getElementWithFluentWait(MessagingPage.closePopup).click();
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
			isMessagingDeatilsAdded=true;
		}
		else if(subjectText.equalsIgnoreCase(ConstantsFile.messageIntialName)){
			logger.info("To Task operation in Messaging workinh properly");
			SeleniumUtil.switchToParentFrame(Frames.LOGIN);
			SeleniumUtil.getElementWithFluentWait(MessagingPage.closePopup).click();
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
			isMessagingDeatilsAdded=true;
		}
		return isMessagingDeatilsAdded;

	}
	/**
	 * This method is used for adding Message subject  for creating
	 * new Message
	 */
	public void enterMessageSubject() {
		String text = "This is subject for Message";
		String messagetext = "test mesage";
		ConstantsFile.messageIntialName = text.concat(ConstantsFile.genData.generateRandomNumber(5));
		SeleniumUtil.getElementWithFluentWait(TaskPage.taskSubjectTextbox).sendKeys(ConstantsFile.messageIntialName);
		SeleniumUtil.getElementWithFluentWait(TaskPage.sendButtonInTask).click();
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
	}
	/**
	 * This method is used to verify sent message should be received in the message section
	 * @return true if correct message is received
	 */
	public boolean verifyRecievedMessage(){
		boolean ismessageReceived=false;
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		SeleniumUtil.waitForProgressBar(Frames.HOME);
		String receivedtext=SeleniumUtil.getElementWithFluentWait(MessagingPage.messageContent).getText();
		if(receivedtext.contains(ConstantsFile.messageIntialName) && receivedtext.contains(MessagingPage.firstLoggedUser)){
			System.out.println("correct message is receieved..");
			ismessageReceived=true;
		}
		return ismessageReceived;
	}
	
	/**
	 * The method used to click on + sign to create Appointment
	 */
	public void clickPlusAptmntCreation() {
		click(HomePage.plusSignToCreateAptmnt);
		sleep(10000);
	}
	
	/**
	 * The method is used to Right Click on Scheduled Appointment
	 */
	public void rightClickOnScheduledAptmnt() {
		sleep(3000);
		try{
			rightClick(HomePage.scheduledAppointment);
			}
			catch(Exception e) {
				clickOnImage("clickOnYes");
				sleep(5000);
				rightClick(HomePage.scheduledAppointment);
			}
	}
	
	/**
	 * The method is used to bring focus on the Colonoscopy Page
	 * @throws InterruptedException 
	 */
	public void focusOnColonoscopyPage() throws InterruptedException {
	
	try {
		logger.info("Colonoscopy service for today already exists");
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.newbutton).click();
		sleep(20000);
		SeleniumUtil.switchtoTopFrame();
		SeleniumUtil.switchToFrame(driver, "fraDefault");
	     }
	catch(Exception e) {
		logger.info("First Colonoscopy service for today ");
		SeleniumUtil.switchtoTopFrame();
		SeleniumUtil.switchToFrame(driver, "fraDefault");
         }
	 SeleniumUtil.switchToFraDocumentFromHomePage();

	}

}



