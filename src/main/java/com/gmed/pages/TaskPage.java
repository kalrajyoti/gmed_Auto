package com.gmed.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import static com.gmed.helper.DriverFactory.driver;

import java.util.HashMap;
import java.util.List;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.test.ProfileTest;
import com.gmed.test.TaskTest;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.DateUtil;
import com.gmed.utils.SeleniumUtil;
import com.gpm.pages.BillingPage;

public class TaskPage extends BaseAbstractPage {
	/** Logger to log the TaskPage log messages */
	private static Logger logger                = LogManager.getLogger(TaskPage.class);
	public static By TaskMenu                           = By.id("mnuNewTask");
	public static By PopupTitle                         = By.id("Popup_TitleContent");
	public static By taskSubjectTextbox                 = By.id("txtSubject_TextBox");
	public static By selfLinkInTask                     = By.className("LabelClick");
	public static By sendButtonInTask                   = By.id("btnSend_SpanBGColor");
	public static By taskSubject                        = By.id("tblTasksnpTask_Table");
	public static  By totaltrtasktags                   = By.xpath(".//table[@id='tblTasksnpTask_Table']/tbody/tr");
	public static By taskRow                            = By.id("tblTasksnpTask_Table");
	public static By taskPlusSign                       = By.id("btnNewsnpTask_ImageContainer");
	public static By TaskPopupTitle                     = By.id("Popup2_TitleContent");
	public static By addRecipientButton                 = By.id("tblTaskingCollectionAdd");
	public static By recipientTextBox                   = By.id("txtCriteria_TextBox");
	public static By recipientSearchIcon                = By.id("txtCriteriaSearch");
	public static By patientSearch                      = By.id("txtPatientSearch");
	public static By taskPatientTextbox                 = By.id("txtPatient_TextBox");
	public static By messageTextbox                     = By.id("txtMessage_TextBox");
	public static By taskMenuButtons                    = By.xpath("//control[@type='ToolbarButton']");
	public static By reassignButton                     = By.id("btnReassign_SpanBGColor");
	public static By taskStatusDetails                  = By.id("tblTaskingCollection_div");
	public static By totalSenderQueue                   = By.xpath(".//table[@id='tblTaskingReassignCollection_Table']/tbody/tr");
	public static By removeButton                       = By.id("tblTaskingReassignCollectionRemove");
	public static By senderButtonInTask                 = By.id("btnReassign_SpanBGColor");
	public static By taskStatusPlusSign                 = By.id("txtTaskStatusAdd");
	public static By taskHistoryIcon                    = By.id("ctl03_imgBtnTaskHistory");
	public static By taskHistoryContent                 = By.id("tblList_div");
	public static By reasonTextBox                      = By.id("txtInput_TextBox");
	public static By sendButton                         = By.id("btnOk_SpanBGColor");
	public static By outboundTab                        = By.id("tabMainOutbound");
	public static By recallReasonTextBox                = By.id("txtReason_TextBox");
	public static By childtaskText                      = By.id("btnTaskChild");


	AppointmentPage app =new AppointmentPage();
	/**
	 * This method is used for clicking on Task Section present in  patient chart
	 * 
	 */
	public void clickOnTask(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(TaskMenu).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(1000);
	}
	/**
	 * This method is used to verify Task pop up should open when user click on Task Section present in patient chart
	 *
	 */
	public boolean verifyTaskPopupIsDisplayed(){
		boolean isTaskpopupdisplayed=false;
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		WebElement tasktooltiptitle=SeleniumUtil.getElementWithFluentWait(PopupTitle);
		String tooltext = tasktooltiptitle.getText();
		if(tooltext.equalsIgnoreCase("New Task")){
			System.out.println("New Task Popup is dispalyed");
			return isTaskpopupdisplayed=true;
		}
		else{
			System.out.println("No Task Tooltip");
			isTaskpopupdisplayed=false;
		}
		return isTaskpopupdisplayed;
	}
	/**
	 * This method is used for entering the subject for  Task  present in  patient chart
	 * @throws Exception
	 */
	public void clickOnSubject(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String text="testTask";
		ConstantsFile.TaskIntialName=text.concat(ConstantsFile.genData.generateRandomString(7));
		SeleniumUtil.getElementWithFluentWait(taskSubjectTextbox).sendKeys(ConstantsFile.TaskIntialName);
	}
	/**
	 * This method is used for clicking on self link  for  Task  present in  patient chart
	 * 
	 */
	public void clickOnSelfLink() {
		SeleniumUtil.getElementWithFluentWait(selfLinkInTask).click();
		//SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for verify when user click on self link then logged in user name should get populated  for  Task  present in  patient chart
	 * 
	 */
	public boolean verifyloggedInUserNameIsPopulatedInSelfLink(){
		boolean isLoggedInUserNameIsPopulated=false;
		List<WebElement> listvalue =driver.findElements(AppointmentPage.patientrow);
		for(WebElement usernamevalue:listvalue){
			String rowText =usernamevalue.getText();
			if(rowText.contains(ConstantsFile.userfirstname)){
				System.out.println("Logged in User is displayed in the self link");
				return isLoggedInUserNameIsPopulated=true;
			}
		}
		return isLoggedInUserNameIsPopulated;
	}
	/**
	 * This method is used for clicking on send button present on  Task
	 * 
	 */
	public void clickOnSendbutton() {
		SeleniumUtil.getElementWithFluentWait(sendButtonInTask).click();
		//SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for verify when user click on send button present on Task  ,corresponding task with same subject should present on Home Page
	 * 
	 */
	public boolean verifyTaskSubjectInTaskSection(){
		boolean isCreatedTaskDisplayed=false;
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		String Task=SeleniumUtil.getElementWithFluentWait(taskSubject).getText();
		if(Task.contains(ConstantsFile.TaskIntialName) && Task.contains(ConstantsFile.patientIntialname)){
			System.out.println("Task with same subject entered in task subject is displayed in the task section");
			return isCreatedTaskDisplayed=true;
		}
		return isCreatedTaskDisplayed;
	}
	/**
	 * This method is used for selecting created task present in my activity module for signing the document
	 *
	 */
	public void selectTask(){
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		List<WebElement> totaltrrows = driver.findElements(totaltrtasktags);
		for(WebElement irows:totaltrrows){
			irows.click();
			SeleniumUtil.waitForProgressBar(Frames.HOME);
			break;
		}
	}
	/**
	 * This method is used for signing the created task present in my activity module
	 * @throws FindFailed 
	 * 
	 * 
	 */
	public void clickOnSignAndCompleteOnTask() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.TASK);
		SeleniumUtil.clickOnImageWitScreenInSikuli("signandComplete");	
	}
	/**
	 * This method is used for verify when user click on send button present on Task  ,corresponding task with same subject should present on Home Page 
	 * @throws Exception 
	 */
	public boolean verifyTaskForFirstVisitInTaskSection(){
		boolean isFirstVisitTaskDisplayed=false;
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		String Task=SeleniumUtil.getElementWithFluentWait(taskRow).getText();
		if(Task.contains("Review First Visit Documentation")){
			System.out.println("Task  is displayed in the task section");
			return isFirstVisitTaskDisplayed=true;
		}
		return isFirstVisitTaskDisplayed;
	}

	//To verify if the task is created
	public boolean verifyTaskSubjectInTaskSection1(){
		boolean isCreatedTaskDisplayed=false;

		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);	
		SeleniumUtil.getElementWithFluentWait(LeftPanelPage.myactivity).click();
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		String Task=SeleniumUtil.getElementWithFluentWait(taskSubject).getText();
		System.out.println(Task);

		if(Task.contains(ConstantsFile.TaskIntialName)){
			System.out.println("Task with same subject entered in task subject is displayed in the task section");
			return isCreatedTaskDisplayed=true;
		}
		System.out.println("Fail");
		return isCreatedTaskDisplayed;
	}

	/**
	 * This method is used to verify Task pop up should open when user click on
	 * Task Section present in patient chart
	 *
	 */
	public boolean verifyPopupInHomePage() {
		boolean ispopupdisplayed = false;
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		WebElement tasktooltiptitle = SeleniumUtil.getElementWithFluentWait(TaskPopupTitle);
		String tooltext = tasktooltiptitle.getText();
		if (tooltext.equalsIgnoreCase("New Task")) {
			System.out.println("New Task Popup is dispalyed");
			return ispopupdisplayed = true;
		} 
		else if(tooltext.equalsIgnoreCase("New Message")){
			System.out.println("New Message Popup is dispalyed");
			ispopupdisplayed=true;
		}
		else {
			System.out.println("No Task Tooltip");
			ispopupdisplayed = false;
		}
		return ispopupdisplayed;
	}

	/**
	 * This method is used to Document the following fields: -Recipients:<User >
	 * -Patient<Any patient> -Subject: <Any Text> -Messages
	 */
	public void selectRecipient() {
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
		addRecipientsForTask();
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
		clickOnSearchIcon();

	}

	/**
	 * This method is used for adding recipients For Task module
	 */
	public void addRecipientsForTask() {
		SeleniumUtil.getElementWithFluentWait(addRecipientButton).click();
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		SeleniumUtil.waitForProgressBar(Frames.PATIENT_SEARCHING);
		SeleniumUtil.getElementWithFluentWait(recipientTextBox).sendKeys(TaskTest.recipientsForTask);
		SeleniumUtil.getElementWithFluentWait(recipientSearchIcon).click();
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
				if (rowText.equalsIgnoreCase(TaskTest.recipientsForTask)) {
					System.out.println("corrected user is selected");
					SeleniumUtil.doubleClick(tdElement);
					break;
				}
			}
			break;
		}
	}

	/**
	 * This method is used to click On Search Icon
	 */
	public void clickOnSearchIcon() {
		SeleniumUtil.getElementWithFluentWait(patientSearch).click();
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		SeleniumUtil.waitForProgressBar(Frames.PATIENT_SEARCHING);
	}

	/**
	 * This method is used to verify correct patient name is displayed while
	 * creating new Task
	 * 
	 * @return true if correct patient name is populated in the task
	 */
	public boolean verifyPatientNamePopulatedInTask() {
		boolean isPatientSearch = false;
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
		String patientNameInTask = SeleniumUtil.getElementWithFluentWait(taskPatientTextbox).getAttribute("value");
		System.out.println("Patient name in task is " + patientNameInTask);
		if (patientNameInTask.equalsIgnoreCase(TaskTest.completePatientName)) {
			logger.info("correct patient name is populated in task");
			isPatientSearch = true;
		}
		return isPatientSearch;
	}

	/**
	 * This method is used for adding task subject & message data for creating
	 * new task
	 */
	public void enterTaskData() {
		String text = "test subject";
		String messagetext = "test mesage";
		ConstantsFile.TaskIntialName = text.concat(ConstantsFile.genData.generateRandomString(7));
		SeleniumUtil.getElementWithFluentWait(taskSubjectTextbox).sendKeys(ConstantsFile.TaskIntialName);
		ConstantsFile.TaskIntialMessageName = messagetext.concat(ConstantsFile.genData.generateRandomString(3));
		SeleniumUtil.getElementWithFluentWait(messageTextbox).sendKeys(ConstantsFile.TaskIntialMessageName);
		SeleniumUtil.getElementWithFluentWait(sendButtonInTask).click();
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
	}

	/**
	 * This method is used for verify when user click on send button present on
	 * Task ,corresponding task with same subject should present on Home Page
	 * 
	 */
	public boolean verifyTaskIsCreated() {
		boolean isCreatedTaskDisplayed = false;
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		String Task = SeleniumUtil.getElementWithFluentWait(taskSubject).getText();
		if (Task.contains(ConstantsFile.TaskIntialName) && Task.contains(TaskTest.completePatientName)) {
			System.out.println("Task with same subject entered in task subject is displayed in the task section");
			return isCreatedTaskDisplayed = true;
		}
		return isCreatedTaskDisplayed;
	}

	/**
	 * Select Different task options i.e Reassign ,Reject etc menu form Task
	 * Screen
	 * 
	 * @param menuName
	 *            are Reassign ,Reject,Print etc
	 */
	public void selectTaskMenuItems(String menuName) {
		SeleniumUtil.switchToParentFrame(Frames.TASK);
		List<WebElement> totaltrrows = driver.findElements(taskMenuButtons);
		System.out.println(totaltrrows.size());
		for (WebElement irows : totaltrrows) {
			String rowText = irows.getText();
			System.out.println("the task menus is" + rowText);
			if (rowText.contains(menuName)) {
				irows.click();
				break;
			}
		}
	}
	/**
	 * This method is used to reassign the task
	 */
	public void reassignTask() {
		boolean isUserFound = false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(By.id("tblTaskingReassignCollectionAdd")).click();
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		SeleniumUtil.waitForProgressBar(Frames.CREATION);
		SeleniumUtil.getElementWithFluentWait(By.id("txtCriteria_TextBox"))
		.sendKeys(TaskTest.reassignRecipientsForTask);
		SeleniumUtil.getElementWithFluentWait(By.id("txtCriteriaSearch")).click();
		sleep(5000);
		List<WebElement> searchdiagnosisrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF Recipients IN THIS TABLE = " + searchdiagnosisrow.size());
		int row_num, col_num;
		row_num = 1;
		for (WebElement trElement : searchdiagnosisrow) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
			col_num = 1;
			for (WebElement tdElement : td_collection) {
				String rowText = tdElement.getText();
				System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
				col_num++;
				if (rowText.equalsIgnoreCase(TaskTest.reassignRecipientsForTask)) {
					System.out.println("corrected user is displayed");
					SeleniumUtil.doubleClick(tdElement);
					isUserFound = true;
					break;
				}
			}
			if (isUserFound) {
				break;
			} else {
				row_num++;
			}
		}
	}
	/**
	 * This method is used to click on reassign button
	 */
	public void clickOnReassign(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(reassignButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to verify Reassign status in Task window
	 * @return true if correct status is present
	 */
	public boolean verifyTaskDetails(){
		SeleniumUtil.switchToParentFrame(Frames.TASK);
		SeleniumUtil.waitForProgressBar(Frames.TASK);
		boolean isTaskReassigned=false;
		String taskReassign=SeleniumUtil.getElementWithFluentWait(taskStatusDetails).getText();
		System.out.println("Task reassign detail is.."+taskReassign);
		if(taskReassign.equalsIgnoreCase(TaskTest.reassignDetails) ){
			logger.info("correct reassign  details are added..");
			isTaskReassigned=true;
		}
		else if(taskReassign.equalsIgnoreCase(TaskTest.rejectDetails)){
			logger.info("correct reject details are added..");
			isTaskReassigned=true;
		}
		return isTaskReassigned;
	}
	/**
	 * This method is used to click on task plus sign present in home page screen
	 */
	public void clickOnTaskPlusSign(){
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		SeleniumUtil.waitForProgressBar(Frames.HOME);
		SeleniumUtil.getElementWithFluentWait(taskPlusSign).click();
	}
	/**
	 * This method is used to verify Task Manager Page is opened
	 * @return true if correct page is opened
	 */
	public boolean verifyTaskManagerPage(){
		SeleniumUtil.switchToParentFrame(Frames.TASKMANAGERFRAME);
		SeleniumUtil.waitForProgressBar(Frames.TASKMANAGERFRAME);
		boolean isTaskPageOpened=false;
		String taskPage=SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		System.out.println("Task Page  is.."+taskPage);
		if(taskPage.equalsIgnoreCase("Task Manager")){
			logger.info("Task Page is opened..");
			isTaskPageOpened=true;
		}
		else if(taskPage.equalsIgnoreCase("My Tasks")){
			logger.info("My Task Page is opened..");
			isTaskPageOpened=true;
		}

		return isTaskPageOpened;

	}
	/**
	 * This method is used to select patient in Task Manager page
	 * @param patientname is patient's first name
	 * @param patientlastname is patient's last name
	 */
	public void selectPatientInTask(String patientname,String patientlastname){
		SeleniumUtil.switchToParentFrame(Frames.TASKMANAGERFRAME);
		SeleniumUtil.waitForProgressBar(Frames.TASKMANAGERFRAME);
		SeleniumUtil.getElementWithFluentWait(ScanningPage.addpatient).click();
		SeleniumUtil.waitForProgressBar(Frames.TASKMANAGERFRAME);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		app.searchExistingPatientName(patientname, patientlastname);
	}
	/**
	 * This method is used to select Right click for the searched patient 
	 * @throws FindFailed 
	 *
	 */
	public  void rClickOnSerchedPatientInTask(String optionName) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.TASKMANAGERFRAME);
		SeleniumUtil.waitForProgressBar(Frames.TASKMANAGERFRAME);
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(TaskTest.existingPatientfirstname) && rowtext.contains(TaskTest.existingPatientlastname)){
				System.out.println("corrected patient row is displayed");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWithTargetOffsetInSikuli(optionName);
				break;
			}
		}
	}
	/**
	 * This method is used to verify when user clicks on reply  ,then reply to sender tool tip is opned
	 * @return true if "Reply to Sender" tool tip should be opened
	 */
	public boolean verifySenderPage(){
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
		boolean isSenderPageOpened=false;
		String senderPage=SeleniumUtil.getElementWithFluentWait(MedicalChartPage.patientFormTitle).getText();
		System.out.println("sender Page  is.."+senderPage);
		if(senderPage.equalsIgnoreCase("Reply to Sender")){
			logger.info("Reply to Sender Page is opened..");
			isSenderPageOpened=true;
		}
		return isSenderPageOpened;

	}
	/**
	 * This method is used to switch into Task manager Frame
	 */
	public void switchToTaskManagerFrame(){
		SeleniumUtil.switchToParentFrame(Frames.TASKMANAGERFRAME);
		SeleniumUtil.waitForProgressBar(Frames.TASKMANAGERFRAME);
	}
	/**
	 * This method is used to select the sender to whom reply should be sent
	 */
	public void selectSenderForTask(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		boolean isSenderPresent=false;
		List<WebElement> activitiesValues=driver.findElements(totalSenderQueue);
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : activitiesValues)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF Senders="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(TaskTest.reassignRecipientsForTask)){
					System.out.println("corrected Recipients is present in Task"); 
					tdElement.click();
					SeleniumUtil.getElementWithFluentWait(removeButton).click();
					isSenderPresent=true;
					break;
				}

			} 
			if(isSenderPresent){
				break;
			}
			else{
				row_num++;
			}
		}

	}
	/**
	 * This method is used to select task data while reply task to sender
	 */
	public void enterSenderData(){
		String subject="this is test for reply";
		ConstantsFile.TaskIntialName=subject.concat(ConstantsFile.genData.generateRandomString(7));
		SeleniumUtil.getElementWithFluentWait(taskSubjectTextbox).sendKeys(ConstantsFile.TaskIntialName);
		SeleniumUtil.getElementWithFluentWait(senderButtonInTask).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to switch into Task  Frame
	 */
	public void switchToTaskFrame(){
		SeleniumUtil.switchToParentFrame(Frames.TASK);
		SeleniumUtil.waitForProgressBar(Frames.TASK);
	}
	/**
	 * This method is used to switch into Task manager Frame
	 */
	public void switchToCombinedFrame(){
		SeleniumUtil.switchToParentFrame(Frames.TASKMANAGERFRAME);
		SeleniumUtil.switchToFrame(driver,"DialogBox_Frame");
	}
	/**
	 * This method is used to switch into Task manager & Task creation Frame
	 */
	public void switchToTaskCreationFrame(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
	}
	/**
	 * This method is used to click on complete button for completing the task
	 */
	public void completeTask(String taskMenuName){
		//SeleniumUtil.switchToParentFrame(Frames.TASK);
		List<WebElement> searchPatientrow = driver.findElements(By.xpath(".//table[@class='TaskButtons']/tbody/tr/td[1]/table/tbody/tr/td/control/table"));
		System.out.println(searchPatientrow.size());
		String idValue;
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			System.out.println("row"+rowtext);
			idValue =irows.getAttribute("id");
			System.out.println("id attribute is "+idValue);
			if(rowtext.contains(taskMenuName)){
				SeleniumUtil.getElementWithFluentWait(By.id(idValue)).click();
				break;
			}
		}
	}
	/**
	 * This method is used to verify Task is completed
	 * @throws FindFailed 
	 *
	 */
	public boolean verifyTaskStatus(String statusName){
		boolean isTaskCompleted=false;
		SeleniumUtil.switchToParentFrame(Frames.TASKMANAGERFRAME);
		SeleniumUtil.waitForProgressBar(Frames.TASKMANAGERFRAME);
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(TaskTest.existingPatientfirstname) && rowtext.contains(TaskTest.existingPatientlastname) && rowtext.contains(statusName)){
				System.out.println("corrected patient row is displayed");
				isTaskCompleted=true;
				break;
			}
		}
		return isTaskCompleted;
	}
	/**
	 * This method is used to search the deleted task  with status
	 *
	 */
	public  void searchPatientWithStatus(String statusName){
		SeleniumUtil.getElementWithFluentWait(taskStatusPlusSign).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		List<WebElement> totalStatusRows =driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totalStatusRows){
			String rowtext =irows.getText();
			if(rowtext.contains(statusName)){
				SeleniumUtil.doubleClick(irows);
				break;
			}
		}

	}
	/**
	 * This method is used to verify Task is deleted
	 * @throws FindFailed 
	 *
	 */
	public boolean verifyTaskIsDeleted(){
		boolean isTaskDeleted=false;
		SeleniumUtil.switchToParentFrame(Frames.TASKMANAGERFRAME);
		SeleniumUtil.waitForProgressBar(Frames.TASKMANAGERFRAME);
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(TaskTest.existingPatientfirstname) && rowtext.contains(TaskTest.existingPatientlastname)){
				System.out.println("corrected patient row is displayed");
				isTaskDeleted=true;
				break;
			}
		}
		return isTaskDeleted;
	}
	/**
	 * This method is used to verify when user clicks on snooze button ,"Follow up " pop should displayed
	 * @return True if "Follow up " popup displayed
	 */
	public String verifySnoozePopup(){
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
		String snoozeTitle=SeleniumUtil.getElementWithFluentWait(AppointmentPage.popUpTitle).getText();
		if(snoozeTitle.equalsIgnoreCase(TaskTest.snoozeToolTipTitle)){
			logger.info("Follow up date Tool tip is opned...");
		}
		return snoozeTitle;
	}
	/**
	 * This method is used to verify snooze details i.e Future Date & Time
	 * for snoozing the task
	 */
	public void addSnoozeDetails(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		String currentDate=DateUtil.getFutureDate();
		logger.info("future date is:"+currentDate);
		String time =DateUtil.getFormattedCurrentDateTime();
		logger.info("future time is:"+time);
		HashMap<String,String> getHashmapValues =new HashMap<String,String>();
		getHashmapValues = SeleniumUtil.setFutureDate();
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.monthloc).sendKeys(getHashmapValues.get("month"));
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.dayloc).sendKeys(getHashmapValues.get("day"));
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.yearloc).sendKeys(getHashmapValues.get("year"));
		SeleniumUtil.getElementWithFluentWait(By.id("ttbTime_Hour")).sendKeys(time);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();
	}
	/**
	 * This method is used to click on Task History icon present in Task Screen
	 */
	public void clickOnTaskHistory(){
		SeleniumUtil.switchToParentFrame(Frames.TASKMANAGERFRAME);
		SeleniumUtil.switchToFrame(driver,"DialogBox_Frame");
		String text =SeleniumUtil.getElementWithFluentWait(By.id("ctl03_imgBtnTaskHistory")).getAttribute("alt");
		System.out.println(text);
		SeleniumUtil.getElementWithFluentWait(taskHistoryIcon).click();
	}
	/**
	 * This method is used to verify task is snoozed in task history page
	 * @return
	 */
	public boolean verifySnoozeInTaskHistoryPage(){
		boolean isTaskSnoozed=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		String completeText =SeleniumUtil.getElementWithFluentWait(taskHistoryContent).getText();
		System.out.println("The Task History Text is:"+completeText);
		String currentDate=DateUtil.getCurrentDateInDateFormatted("MM/d/yyyy");
		String futureDate =DateUtil.getFutureDate();
		String verifySnoozetext =TaskTest.snoozeText+futureDate;
		if(completeText.contains(verifySnoozetext)){
			logger.info("Task Is Snoozed in task window");
			isTaskSnoozed=true;
		}
		
		return isTaskSnoozed;
	}
	/**
	 * This method is used to enter reject reason for the task
	 */
	public void enterReasonForRejct(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(reasonTextBox).sendKeys("please reject the task");
	}
	/**
	 * This method is used to click on send button while user reject the task
	 */
	public void clickOnSend(){
		SeleniumUtil.getElementWithFluentWait(sendButton).click();
	}
	public void clickOnOutboundTab(){
		SeleniumUtil.getElementWithFluentWait(outboundTab).click();
	}

	/**
	 * This method is used for selecting created task present in my activity module for signing the document
	 *
	 */
	public boolean verifyTask(){
		boolean isTaskPresentInMyTask=false;
		    SeleniumUtil.switchToParentFrame(Frames.OUTBOUND);
			String Task = SeleniumUtil.getElementWithFluentWait(taskHistoryContent).getText();
			System.out.println("Task History content is"+Task);
			if (Task.contains(TaskTest.recipientsNames) && Task.contains(ConstantsFile.TaskIntialName) && Task.contains(TaskTest.completePatientName) ) {
                  System.out.println("sent task is availabe in my Tasks");
                  isTaskPresentInMyTask=true;
		}
			else if(Task.contains(ConstantsFile.TaskIntialName) && Task.contains(TaskTest.completePatientName)){
				 System.out.println("sent task is availabe in Task Manager");
                 isTaskPresentInMyTask=true;
			}
			return isTaskPresentInMyTask;
	}
	/**
	 * This method is used to select Right click for the searched patient 
	 * @throws FindFailed 
	 *
	 */
	public  void rclickInOutbound(String optionName) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.OUTBOUND);
		 SeleniumUtil.waitForProgressBar(Frames.OUTBOUND);
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:searchPatientrow){
			String rowtext =irows.getText();
			if(rowtext.contains(ConstantsFile.TaskIntialName) && rowtext.contains(TaskTest.existingPatientfirstname) && rowtext.contains(TaskTest.existingPatientlastname)){
				System.out.println("corrected patient row is displayed");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWithTargetOffsetInSikuli(optionName);
				break;
			}
		}
	}
	
	public void enterReasonForRecall(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(recallReasonTextBox).sendKeys("please recall the task");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();
	}
	/**
	 * This method is used for verify task is recalled in Home Page
	 * 
	 */
	public boolean verifyTaskIsRecalled(){
		boolean isCreatedrecalled=false;
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		String task=SeleniumUtil.getElementWithFluentWait(taskSubject).getText();
		if(task.contains("Task recalled:") && task.contains(TaskTest.existingPatientfirstname) && task.contains(TaskTest.existingPatientlastname)){
			System.out.println("Task is recalled");
			return isCreatedrecalled=true;
		}
		return isCreatedrecalled;
	}
	/**
	 * This method is used for adding recipients details
	 * for child task
	 */
	public void addChildTaskDetails(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(addRecipientButton).click();
		
		logger.info("searching  recipients...");
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		SeleniumUtil.waitForProgressBar(Frames.PATIENT_SEARCHING);
		SeleniumUtil.getElementWithFluentWait(recipientTextBox).sendKeys(TaskTest.reassignRecipientsForTask);
		SeleniumUtil.getElementWithFluentWait(recipientSearchIcon).click();
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
				if (rowText.equalsIgnoreCase(TaskTest.reassignRecipientsForTask)) {
					System.out.println("corrected user is selected");
					SeleniumUtil.doubleClick(tdElement);
					break;
				}
			}
			break;
		}
	}
	/**
	 * This method is used for adding child subject in Task
	 */
	public void enterChildData(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(taskSubjectTextbox).sendKeys("this is test for child task");
		SeleniumUtil.getElementWithFluentWait(sendButtonInTask).click();
	}
	/**
	 * This method is used to verify child Task is created
	 * @return true if child task is created
	 */
	public boolean verifyChildTask(){
		boolean isChildTaskCreated=false;
		SeleniumUtil.switchToParentFrame(Frames.TASK);
		String childTaskText=SeleniumUtil.getElementWithFluentWait(childtaskText).getAttribute("text");
			System.out.println("the child task text is" + childTaskText);
			if (childTaskText.contains("Back to child")) {
				logger.info("Child Task Is Created");
				isChildTaskCreated=true;
			}
		return isChildTaskCreated;
	}
}

