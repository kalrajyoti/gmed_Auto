package com.gmed.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;
import static com.gmed.helper.DriverFactory.driver;

import java.util.HashMap;
import java.util.List;
public class OrdersPage extends BaseAbstractPage {
	/** Logger to log the OrdersPage log messages */
	private static Logger logger                = LogManager.getLogger(OrdersPage.class);
	public static By queuePanel                         = By.id("tblList");
	public static By orderScreenPanel                   = By.id("frmExtenderNew");
	public static By followupOrderTitle                 = By.id("Popup_TitleContent");
	public static By recallDropDown                     = By.id("txtRecallTypeDropDown");
	public static By orderTitle                         = By.id("txtTitle_TextBox");
	public static By trackingCheckbox                   = By.id("chkTracking_Render");
	public static By orderExpirationMonth               = By.id("dtbExpirationDate_Month");
	public static By orderExpirationDay                 = By.id("dtbExpirationDate_Day");
	public static By orderExpirationYear                = By.id("dtbExpirationDate_Year");
	public static By recallType                         = By.xpath("//td[text()='Colonoscopy 1 Year']");
	public static By recallMonth                        = By.id("dtbRecallDate_Month");
	public static By recallDay                          = By.id("dtbRecallDate_Day");
	public static By recallYear                         = By.id("dtbRecallDate_Year");
	public static By status                             = By.id("ddlStatus_Text");
	public static By priority                           = By.id("ddlPriority_Text");
	public static By labOrderType                       = By.id("ddlLabOrderType_Text");
	public static By labName                            = By.id("txtName_TextBox");
	public static By loincCodeSearch                    = By.id("txtLoincCodeSearch");
	public static By loincCodeNametextBox               = By.id("txtCodeName_TextBox");
	public static By loincCodeNameSearch                = By.id("txtCodeNameSearch");
	public static By loincCodeSearchedRslt              = By.xpath("//b[text()='59589-2']");
	public static By performedAt                        = By.id("txtPerformedAt_TextBox");
	public static By reason                             = By.id("txtReason_TextBox");
	public static By additinalWording                   = By.id("txtAdditionalWording_TextBox");
	public static By patientInstructions                = By.id("txtPatientInstructions_TextBox");
	public static By cptCodeSearch                      = By.id("txtCptCodeSearch");
	public static By cptCodeTextBox                     = By.id("txtNameSearchCoding_TextBox");
	public static By cptCodeNameSearch                  = By.id("txtNameSearchCodingSearch");
	public static By cptCodeSearchedRslt                = By.xpath("//td[text()='A4719']");
	public static By selectButton                       = By.xpath("//span[text()='Select']");
	
	public static By snomedCodeSearch                   = By.id("txtSnomedCodeSearch");
	public static By snomedNameTextBox                  = By.id("txtSnomedName_TextBox");
	public static By snomedNameSearch                   = By.id("txtSnomedNameSearch");
	public static By snomedCodeSearchedRslt             = By.xpath("//b[text()='260305007']");
	public static By orderNote                          = By.id("txtOrderNote_TextBox");
	public static By documentTab                        = By.id("tabDocuments_Text");
	public static By plusSignOnDocArea                  = By.id("tblDocumentCollectionAdd");
	public static By selectDocumentChkBox               = By.xpath("//table[@id='tblList_Table']/tbody//input[@class='checkboxSelection']");
	public static By selectButton1                      = By.id("btnSelect_SpanBGColor");
	public static By taskTab                            = By.id("tabTasks_Text");
	public static By plusSignOnTaskArea                 = By.id("tblTaskCollectionAdd");
	public static By selfLinkOnTask                     = By.xpath("//span[text()='Self']");
	public static By sendButtonOnTask                   = By.id("btnSend_SpanBGColor");
	public static By saveOrderButton                    = By.id("btnSave_SpanBGColor");
	public static By physician                          = By.id("txtOrderingPhysician_TextBox");
	public static By person                             = By.id("txtPerson_TextBox");
	public static By diagnoses                          = By.id("tabDiagnoses_Text");
	public static By plusSignOnDiagnosesArea            = By.id("tblDiagnosisCollectionAdd");
	public static By selectDiagnosisCheckbox            = By.xpath("//table[@id='tblList_Table']/tbody/tr//input[@class='checkboxSelection']");
	public static By closeOrder                         = By.id("btnCloseOrder_SpanBGColor");
	
	/**
	 * This method is used to verify when user create a new order order queue specific to patient  is displayed in the bottom half screen in patient
	 * 
	 */
	public boolean verifyOrderQueueIsDisplayed(){
		boolean isorderQueueDisplayed=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		WebElement orderqueuepanel=SeleniumUtil.getElementWithFluentWait(queuePanel);
		if(orderqueuepanel.isDisplayed()){
			System.out.println("order queue specfic to patient  is displayed in the bottom half screen in patient");
			isorderQueueDisplayed=true;
		}
		return isorderQueueDisplayed;
	}
	/**
	 * This method is used for clicking on New button for creating a Follow up order
	 *
	 */
	public void clickOnNewButton(){
		sleep(3000);
		WebElement newbuttonval = SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton);
		newbuttonval.click();
	}
	/**
	 * This method is used for verify order screen is displayed
	 * 
	 */
	public boolean verifyOrderScreen(){
		boolean isOrderScreenIsPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement orderscreenpanel =SeleniumUtil.getElementWithFluentWait(orderScreenPanel);
		if(orderscreenpanel.isDisplayed()){
			System.out.println("The Screen with order category and order List is displayed");
			isOrderScreenIsPresent=true;
		}
		return isOrderScreenIsPresent;
	}
	/**
	 * This method is used for creating a follow up type order order
	 * 
	 */
	public void clickOnFollowupOnLeftpanel(String leftnavigationvalue){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		List<WebElement>  trtags =driver.findElements(By.xpath(".//table[@class='ToolbarMenuNoBorder']/tbody/tr"));
		for(WebElement irows:trtags){
			String rowText=irows.getText();
			if(rowText.contains(leftnavigationvalue)){
				irows.click();
				SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
				break;
			}
		}
	}
	/**
	 * This method is used for verify The Followup order screen is displayed
	 * 
	 */
	public boolean verifyFollowupOrderScreen(){
		boolean isFollowupOrderScreenIsPresent=false;
		driver.switchTo().parentFrame();
		WebElement followuporderTitlepresent =SeleniumUtil.getElementWithFluentWait(followupOrderTitle);
		if(followuporderTitlepresent.isDisplayed()){
			System.out.println("The Followup order screen is displayed");
			isFollowupOrderScreenIsPresent=true;
		}
		return isFollowupOrderScreenIsPresent;
	}
	/**
	 * This method is used for selecting Recall Type when user is creating Follow up order
	 * 
	 */
	public void clickOnRecallType(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(recallDropDown).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement>  trtags =driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:trtags){
			irows.click();
			SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
			break;
		}
	}
	/**
	 * This method is used for selecting title when user is creating Follow up order
	 *
	 */
	public void clickOnTitle() {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String text="Followup order";
		ConstantsFile.orderIntialName=text.concat(ConstantsFile.genData.generateRandomString(7));
		SeleniumUtil.getElementWithFluentWait(orderTitle).sendKeys(ConstantsFile.orderIntialName);
	}
	/**
	 * This method is used for track the  Follow up order
	 *
	 */
	public void clickOnTrackOrder(){
		WebElement trackordercheckbox =SeleniumUtil.getElementWithFluentWait(trackingCheckbox);
		SeleniumUtil.scrolltoWebElement(trackordercheckbox);
		trackordercheckbox.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);

	}
	/**
	 * This method is used for selecting the future date for tracking the  Follow up order
	 *
	 */
	public void selectFutureDate() {
		HashMap<String,String> getHashmapValues =new HashMap<String,String>();
		getHashmapValues = SeleniumUtil.setFutureDate();
		SeleniumUtil.getElementWithFluentWait(orderExpirationMonth).sendKeys(getHashmapValues.get("month"));
		SeleniumUtil.getElementWithFluentWait(orderExpirationDay).sendKeys(getHashmapValues.get("day"));
		SeleniumUtil.getElementWithFluentWait(orderExpirationYear).sendKeys(getHashmapValues.get("year"));
	}
	
	/**
	 * The method is used to add Recall Type to Order
	 */
	public void addRecallType() {
		click(OrdersPage.recallDropDown);
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		click(OrdersPage.recallType);
	}
	/**
	 * The method is used to enter the Order Title
	  */
	public void enterOrderTitle(String orderTitle) {
		enterText(OrdersPage.orderTitle,orderTitle);
	}
	/**
	 * The method is used to enter Lab Order Type
		 */
	
	public void selectLabOrderType(String locator, String labordertype) {
		click(OrdersPage.labOrderType);
        SeleniumUtil.removeReadOnly(locator, labordertype);
	}
	/**
	 * The method is used to select Lab Name
	 */
	public void selectLabName(String labname) {
		enterText(OrdersPage.labName, labname);
	}
	
	/**
	 * The method is used to select Loinc code to Lab Order
	 * @param loinccode
	 */
	public void enterLoincCode(String loinccode) {
		click(OrdersPage.loincCodeSearch);
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		enterText(OrdersPage.loincCodeNametextBox, loinccode);
		click(OrdersPage.loincCodeNameSearch);
		sleep(5000);
		click(OrdersPage.loincCodeSearchedRslt);
	}
	
	public void enterPerformedAt(String city) {
		enterText(OrdersPage.performedAt,city);
	}
	
	public void enterReason(String reason){
		enterText(OrdersPage.reason,reason);
	}
	
	public void enterAdditionalWording(String wording) {
	    enterText(OrdersPage.additinalWording, wording);
	}
	
	public void enterPatientInstructions(String instructions) {
		enterText(OrdersPage.patientInstructions,instructions);
	}
	
	public void enterCPTCode(String cptcode) {
	click(OrdersPage.cptCodeSearch);
	SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
	enterText(OrdersPage.cptCodeTextBox, cptcode);
	click(OrdersPage.cptCodeNameSearch);
	sleep(5000);
	click(OrdersPage.cptCodeSearchedRslt);
	sleep(5000);
	click(OrdersPage.selectButton);
	}
	
	public void enterSnomedCode(String snomedcode) {
	click(OrdersPage.snomedCodeSearch);
	SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
	enterText(OrdersPage.snomedNameTextBox,snomedcode);
	click(OrdersPage.snomedNameSearch);
	sleep(5000);
	click(OrdersPage.snomedCodeSearchedRslt);
	}
	
	public void enterOrderNote(String ordernote) {
		enterText(OrdersPage.orderNote, ordernote);
	}
	/**
	 * The method is used to attach a document in the document section at bottom
	 */
	public void attachDocument() {
		click(OrdersPage.documentTab);
		click(OrdersPage.plusSignOnDocArea);
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		click(OrdersPage.selectDocumentChkBox);
		click(OrdersPage.selectButton1);
	}
	
	/**
	 * The method is used to create a task from the task section at bottom
	 */
	public void createTask(){
		click(OrdersPage.taskTab);
		click(OrdersPage.plusSignOnTaskArea);
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		click(OrdersPage.selfLinkOnTask);
		click(OrdersPage.sendButtonOnTask);
	}
	
	public void selectDiagnoses() {
	click(OrdersPage.diagnoses);
	click(OrdersPage.plusSignOnDiagnosesArea);
	SeleniumUtil.switchToParentFrame(Frames.CREATION);
	click(OrdersPage.selectDiagnosisCheckbox);
	click(OrdersPage.selectButton1);
	}
	
	/**
	 * The method is used to save the order
	 */
	public void saveOrder() {
		click(OrdersPage.saveOrderButton);
	}
	
	public void deleteOrder() {
		rightClick(DocumentPage.planSection);
		clickOnImage("rclickDelete");
		clickOnImage("clickOnYes1");
		sleep(3000);
	}
	
	public void enterPerson(String person) {
		enterText(OrdersPage.person, person);
	}
	
	public void createOrder(String img, String ordertitle){
		clickOnImage(img);
		switchToParentFrame(Frames.TOOLTIP);
		logger.info("Adding Recall Type Information to the Order");
        addRecallType();
		switchToParentFrame(Frames.TOOLTIP);
		enterOrderTitle(ordertitle);
		saveOrder();
	}
	
    public void closingOrderWithRecall(String str, String imgname){
	    SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(By.xpath("//table[@id='tblList_Table']/tbody//td[text()='"+str+"']")));
		sleep(3000);
		switchToParentFrame(Frames.TOOLTIP);
		click(closeOrder);
		clickOnImage(imgname);
		clickOnImage("clickOnYes1");
  }
}
