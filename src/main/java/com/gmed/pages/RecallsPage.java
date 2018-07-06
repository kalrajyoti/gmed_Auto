package com.gmed.pages;

import static com.gmed.helper.DriverFactory.driver;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;


public class RecallsPage extends BaseAbstractPage {
	private static Logger	logger				= LogManager.getLogger(RecallsPage.class);
	public static By		toMonth				= By.id("dtbDateTo_Month");
	public static By		toDay				= By.id("dtbDateTo_Day");
	public static By		toYear				= By.id("dtbDateTo_Year");
	public static By		patients			= By.id("txtPatient_TextBox");
	public static By		searchButton		= By.id("btnFiltersSearch_Body");
	public static By		provider			= By.id("txtProviderAdd");
	public static By		fromMonth			= By.id("dtbDateFrom_Month");
	public static By		fromDay				= By.id("dtbDateFrom_Day");
	public static By		fromYear			= By.id("dtbDateFrom_Year");
	public static By		plusSignStatus		= By.id("txtStatusAdd");
	public static By		recallRecords		= By.xpath("//table[@id='radRecalls_ctl00']/tbody/tr[contains(@id,'radRecalls_ctl00')]");
	public static String	recallRecordData1	= "//table[@id='radRecalls_ctl00']/tbody/tr[";
	public static String	recallRecordData2	= "]";
	public static By        activityDropDown    = By.id("txtActivityDropDown");
	public static By        recallStatus        = By.id("ddlStatus_Text");
	

	public void addProvider(String providername) {

		switchToParentFrame(Frames.TOOLTIP);
		enterText(DocumentPage.searchmedicationtxtbox, providername);
		click(DocumentPage.searchMedication1);
		sleep(3000);
		click(DocumentPage.select1frompopupframe);
		click(DocumentPage.selectbtnpopupframe);
	}

	public void searchRecallByProviderPatients(String patient, String provider) {
		sleep(3000);
		clearText(toMonth);
		clearText(toDay);
		clearText(toYear);
		clearText(patients);
		enterText(RecallsPage.patients, patient);
		click(RecallsPage.provider);
		addProvider(provider);
		SeleniumUtil.switchToParentFrame(Frames.RECALLS);
		click(RecallsPage.searchButton);
	}

	public void searchRecallByStatus(String status) {
		sleep(3000);
		click(plusSignStatus);
		switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(By.xpath("//td[text()='" + status + "']")));
		SeleniumUtil.switchToParentFrame(Frames.RECALLS);
		click(RecallsPage.searchButton);
	}

	public boolean verifyRecallStatus(String recalltype, String recalldate, String recallstatus) {
		sleep(3000);
		
		for (int i = 1; i <= SeleniumUtil.getElementCount(recallRecords); i++) {
			try {
				
				if (getElementText(By.xpath(recallRecordData1+i+recallRecordData2)).contains(recalltype) && getElementText(By.xpath(recallRecordData1+i+recallRecordData2)).contains(recalldate) 
						&& getElementText(By.xpath("//table[@id='radRecalls_ctl00']/tbody/tr["+i+"]")).contains(recallstatus)) {
						 
					return true;
				} else {
					if (i == SeleniumUtil.getElementCount(recallRecords)) {
						return false;
					}
				}
			} catch (Exception e) {
				System.out.println("Exception  " + i+"test" +e);
				if (i == SeleniumUtil.getElementCount(recallRecords)) {
					return false;
				}
			}
		}
		return false;
	}
	/**
	 * This method is used for adding recall details for creating new recall from configuration
	 * @throws FindFailed
	 */
	public void enterRecallDetails() throws FindFailed{
	logger.info("Entering random recall Name");
	enterRecallName();
	logger.info("Selecting colonoscopy activity for  recall");
	selectRecallActivity();
	logger.info("configure order with recall Types");
	configureOrderWithRecall();
	logger.info("Select the created new Order");
	selectCreatedOrder();
	logger.info("Selecting Time Frame for  recall");
	selectTimeFrame();
	}
	/**
	 * This method is used for giving random name for recall
	 */
	public void enterRecallName(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		ConstantsFile.ColonIntialname = ConstantsFile.ColonIntialname.concat(ConstantsFile.genData.generateStringWithAllobedSplChars(3, "@")).concat(ConstantsFile.genData.generateRandomNumber(2));
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.searchusertextbox).sendKeys(ConstantsFile.ColonIntialname);
	}
	/**
	 * This method is used for select any activity for recall
	 */
	public void selectRecallActivity(){
		SeleniumUtil.getElementWithFluentWait(activityDropDown).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		List<WebElement> colonscopyvalue= driver.findElements(By.xpath(".//*[starts-with(text(),'Colonoscopy')]"));
		System.out.println(colonscopyvalue.size());
		for(WebElement m:colonscopyvalue){
			String rowText=m.getText();
			System.out.println(rowText);
			if(rowText.equalsIgnoreCase("Colonoscopy")){
				m.click();
				break;
			}
		}
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for selecting Time Frame for Recall
	 */
	public void selectTimeFrame(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.addTextBoxValue("ddlUnit_Text", "7");
		SeleniumUtil.addTextBoxValue("ddlUnitType_Text", "month(s)");
	}
	/**
	 * This method is used for configuring order with recall Type
	 * @throws FindFailed
	 */
	public void configureOrderWithRecall() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.getElementWithFluentWait(By.id("tblUserListItemsCollectionAdd")).click();
		SeleniumUtil.switchToParentFrame(Frames.USERLIST);
		SeleniumUtil.waitForProgressBar(Frames.USERLIST);
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton).click();
		SeleniumUtil.clickOnImageWitScreenInSikuli("selectRootItem");
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.addTextBoxValue("ddlType_Text", "Follow Up");
		String text="Followup order";
		ConstantsFile.orderIntialName=text.concat(ConstantsFile.genData.generateRandomString(7));
		SeleniumUtil.getElementWithFluentWait(OrdersPage.orderTitle).sendKeys(ConstantsFile.orderIntialName);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(Keys.TAB);
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		
	}
	/**
	 * This method is used for selecting the created new order
	 */
	public void selectCreatedOrder(){
		SeleniumUtil.switchToParentFrame(Frames.USERLIST);
		SeleniumUtil.waitForProgressBar(Frames.USERLIST);
		WebElement colonscopyvalue= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'"+ ConstantsFile.orderIntialName +"')]"));
		SeleniumUtil.scrolltoWebElement(colonscopyvalue);
		SeleniumUtil.doubleClick(colonscopyvalue);
		
	}
	/**
	 * This method is used to verify New Recall is Created in the queue
	 * @return true if new recall is created
	 */
	public boolean verifyRecallIsCreated(){
		boolean isRecallCreated=false;
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		List <WebElement> recallnamevalues =driver.findElements(AppointmentPage.totaltrtags);
		System.out.println(recallnamevalues.size());
		for(WebElement recallrow:recallnamevalues){
			String rowText=recallrow.getText();
			if(rowText.contains(ConstantsFile.ColonIntialname) && rowText.contains("7 months") ){
				logger.info("correct recall is created");
				isRecallCreated=true;
				break;
			}
	}
		return isRecallCreated;
	
	}
	/**
	 * This method is used to verify Recall Status Is active
	 * @return
	 */
	public boolean verifyRecallStatus(){	
		boolean checkStatus=false;
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		String recallStatusValue=SeleniumUtil.getElementWithFluentWait(recallStatus).getAttribute("value");
		System.out.println("Recall text is :" +recallStatusValue);
		if(recallStatusValue.equalsIgnoreCase("Active")){
			System.out.println("Recall Status is active");
			checkStatus=true;
		}
		return checkStatus;
	}
}