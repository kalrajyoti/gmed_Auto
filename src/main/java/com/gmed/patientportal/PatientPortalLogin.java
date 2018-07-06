package com.gmed.patientportal;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.driver1;
import static com.gmed.helper.DriverFactory.action;
import java.util.List;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.pages.AppointmentPage;
import com.gmed.pages.OutputManagerPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.DateUtil;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;
public class PatientPortalLogin extends BaseAbstractPage   {
	public static By firstName                         =By.id("FirstName");
	public static By lastName                          =By.id("LastName");
	public static By dateOfBirth                       =By.id("DateOfBirth");
	public static By portalPinNumber                   =By.id("PortalPinNumber");
	public static By userName                          =By.id("UserName");
	public static By password                          =By.id("PasswordModel_Password");
	public static By passwordConfirmation              =By.id("PasswordModel_ReEnterPassword");
	public static By securityQuestionDropDown          =By.id("SecurityQuestion");
	public static By securityQuestionAnswer            =By.id("SecurityQuestionAnswer");
	public static By acceptCheckboxButton              =By.xpath("//*[@id='AcceptTerms']");
	public static By registerButton                    =By.xpath("//*[@id='RegisterForm']/p/input");
	public static By homeButton                        =By.xpath("//*[@id='navigation-menu']/li[1]/a");
	public static By logoffButton                      =By.xpath("//*[@id='navigation-menu']/li[7]/a");
	public static By userNameTextBox                   =By.xpath("//*[@id='UserName']");
	public static By passwordTextBox                   =By.xpath("//*[@id='Password']");
	public static By signUp                            =By.xpath("//*[@id='form0']/input[2]");
	public static By sendButton                        =By.xpath("//*[@id='sendNewButton']");
	public static By typeDropDown                      =By.id("Type");
	public static By locationValue                     =By.id("LocationId");
	public static By subjectTitle                      =By.xpath("//*[@id='Subject']");
	public static By messageText                       =By.xpath("//*[@id='Message']");
	public static By sendMessageButton                 =By.xpath("//*[@id='messageSendNew']");
	public static By selectMessageLeftMenu             =By.xpath("//*[@id='navigation-menu']/li[2]/a");
	public static By sentMessageLeftMenu               =By.xpath("//*[@id='navigation-menu']/li[2]/ul/li[2]/a/span");
	public static By sentMessageQueue                  =By.xpath("//*[@id='sentMessageContainer']");
	public static By addPatientButton                  =By.id("txtPatientAdd");
	public static By updateInformationButton           =By.xpath("//*[@id='main-content']/div[2]/div/div[5]/a/span[3]");
	public static By updateInfoHeading                 =By.xpath("//*[@id='main-content']/div[1]/div[1]/div/h4");
	public static By raceCheckbox                      =By.xpath("//input[@type='checkbox']");
	public static By sendTopButton                     =By.xpath("//*[@id='btnSendTop']");
	public static By acceptAllButton                   =By.id("btnAccept_SpanBGColor");
	public static By documentTextOnHome                =By.xpath("//*[@id='DocumentsTableContainer']/div[1]");
	public static By leftMenus                         =By.xpath("//*[@id='navigation-menu']/li");
	public static By documentText                      =By.id("wrapper");
	
	AppointmentPage app = new AppointmentPage();
	/**
	 * This method is used to register patient with valid mail id in patient portal sign up page
	 * @param pinNo
	 */

	public void registerPatient(String pinNo){
		SeleniumUtil.focusOnChrome();
		SeleniumUtil.getElementWithFluentWaitInChrome(firstName).sendKeys(ConstantsFile.patientfirstnamewithoutIns);
		SeleniumUtil.getElementWithFluentWaitInChrome(firstName).sendKeys(Keys.TAB);
		SeleniumUtil.getElementWithFluentWaitInChrome(lastName).sendKeys(ConstantsFile.patientlastnamewithoutIns);
		SeleniumUtil.getElementWithFluentWaitInChrome(dateOfBirth).sendKeys("12121996");
		SeleniumUtil.getElementWithFluentWaitInChrome(portalPinNumber).sendKeys(pinNo.trim());
		SeleniumUtil.getElementWithFluentWaitInChrome(portalPinNumber).sendKeys(Keys.TAB);
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		ConstantsFile.portalusername =ConstantsFile.loginData.get(ConstantsFile.LOGIN_VALID_USERNAME).concat(ConstantsFile.genData.generateRandomNumber(4));
		ConstantsFile.portalpassword=ConstantsFile.loginData.get(ConstantsFile.PORTAL_LOGIN_VALID_PASSWORD);
		System.out.println("Entering the username in the Element  "+ConstantsFile.portalusername);
		SeleniumUtil.getElementWithFluentWaitInChrome(userName).sendKeys(ConstantsFile.portalusername);
		System.out.println("Entering the Password in the Element  " +ConstantsFile.portalpassword);
		SeleniumUtil.getElementWithFluentWaitInChrome(password).sendKeys(ConstantsFile.portalpassword);
		SeleniumUtil.getElementWithFluentWaitInChrome(passwordConfirmation).sendKeys(ConstantsFile.portalpassword);
		Select dropdown = new Select(driver1.findElement(securityQuestionDropDown));
		dropdown.selectByIndex(2);
		SeleniumUtil.getElementWithFluentWaitInChrome(securityQuestionAnswer).sendKeys("test");
		sleep(5000);
		WebElement acceptCheckbox=SeleniumUtil.getElementWithFluentWaitInChrome(acceptCheckboxButton);
		SeleniumUtil.scrolltoWebElementInChrome(acceptCheckbox);
		SeleniumUtil.getElementWithFluentWaitInChrome(acceptCheckboxButton).click();
		SeleniumUtil.getElementWithFluentWaitInChrome(registerButton).click();	
	}

	/**
	 * This method is used click on Home Page present in patient portal  page
	 */
	public void clickOnHome(){
		//SeleniumUtil.waitForElementToVisible(homeButton);
		sleep(5000);
		SeleniumUtil.getElementWithFluentWaitInChrome(homeButton).click();	
	}
	/**
	 * This method is used to verify home page of patient portal is loaded
	 */
	public  boolean verifyHomePageIsLoaded(){
		sleep(5000);
		String pageTitle=null;
		pageTitle=driver1.getTitle();
		System.out.println("The page title is:"+pageTitle);
		if(pageTitle.contains("Home")){
			System.out.println("The home page verified..");
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * This method is used to click on log off button present in patient portal page
	 */
	public void clickOnLogOff(){
		sleep(5000);
		SeleniumUtil.getElementWithFluentWaitInChrome(logoffButton).click();
	}
	/**
	 * This method is used to login in patient portal login page 
	 */
	public void loginToPatientPortal(){
		SeleniumUtil.getElementWithFluentWaitInChrome(userNameTextBox).sendKeys(ConstantsFile.portalusername);
		SeleniumUtil.getElementWithFluentWaitInChrome(passwordTextBox).sendKeys(ConstantsFile.portalpassword);
		SeleniumUtil.getElementWithFluentWaitInChrome(signUp).click();
	}
	/**
	 * This method is used to click on send message button present in patient portal  page 
	 */
	public void clickOnSendMessage(){
		sleep(7000);
		SeleniumUtil.getElementWithFluentWaitInChrome(sendButton).click();
	}
	/**
	 * This method is used to verify Clinical Type is selected by default in send message page present in patient portal page 
	 */
	public boolean verifyClinicalIsSelected(){
		boolean isClinicalValueSelected=false;
		String dropdownText=SeleniumUtil.getElementWithFluentWaitInChrome(typeDropDown).getAttribute("value");
		System.out.println(dropdownText);
		if(dropdownText.equalsIgnoreCase("Clinical")){
			System.out.println("Clinical drop down is selected by default");
			isClinicalValueSelected=true;
		}
		return isClinicalValueSelected;
		
	}
	/**
	 * This method is used to select valid location in patient portal page 
	 */
	public void selectLocation(){
		sleep(5000);
		Select dropdown = new Select(driver1.findElement(locationValue));
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		String portalLocation =ConstantsFile.loginData.get(ConstantsFile.PORTAL_VALID_LOCATION);
		dropdown.selectByVisibleText(portalLocation);	
	}
	/**
	 * This method is used to select Test subject while sending message from  patient portal page 
	 */
	public void selectTestSubject(){
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		String portalSubject =ConstantsFile.loginData.get(ConstantsFile.PORTAL_SUBJECT_TITLE);
		SeleniumUtil.getElementWithFluentWaitInChrome(subjectTitle).sendKeys(portalSubject);
		SeleniumUtil.getElementWithFluentWaitInChrome(messageText).sendKeys("This is test message sent by" +ConstantsFile.portalusername + "user");
	}
	/**
	 * This method is used to click On send message button on patient portal
	 */
	public void clickOnSendMessageButton(){
		SeleniumUtil.getElementWithFluentWaitInChrome(sendMessageButton).click();
	}
	/**
	 * This method is used to click On message menu present in left navigation menu
	 */
	public void selectMessageFromLeftNavigation(){
		sleep(5000);
		SeleniumUtil.getElementWithFluentWaitInChrome(selectMessageLeftMenu).click();
	}
	/**
	 * This method is used to click On sent message menu present in left navigation menu
	 */
	public void clickOnSentMessageLeftNavigation(){
		sleep(5000);
		SeleniumUtil.getElementWithFluentWaitInChrome(sentMessageLeftMenu).click();
	}
	/**
	 * This method is used to verify sent message is present in the sent message queue in the left navigation menu
	 * @return true if queue is present in sent messages
	 */
	public boolean verifySentMessageQueue(){
		sleep(5000);
		boolean isSentQueuePresent=false;
		List <WebElement> sentQueueText=driver1.findElements(sentMessageQueue);
		System.out.println(sentQueueText.size());
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		String portalSubjectText =ConstantsFile.loginData.get(ConstantsFile.PORTAL_SUBJECT_TITLE);
		for(WebElement e1:sentQueueText){
		String rowText=e1.getText();
		System.out.println(rowText);
			if(rowText.contains("To: Patient Portal Manager") && rowText.contains(portalSubjectText)){
			System.out.println("sent message queue is present");
			isSentQueuePresent=true;
		}
		}
		return isSentQueuePresent;	
	}
	/**
	 * This method is used to switch into patient portal frame 
	 */
	public void switchToPatientPortalFrame(){
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPORTAL);
	}
	/**
	 * This method is used to select patient in patient portal Manager queue in gmed 
	 */
	public void selectPatientInGmed(){
		SeleniumUtil.getElementWithFluentWait(addPatientButton).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		app.searchPatientNameWithoutIns();	
	}
	/**
	 * This method is used to verify inbound queue is present  in patient portal Manager queue in gmed 
	 */
	public boolean verifyInboundQueueInPatientPortal() throws Exception{
		boolean isInboundQueueIsDisplayedInQueueManagement=false;
		Thread.sleep(2000);
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPORTAL);
		List<WebElement>  trtags =driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:trtags){
			String rowText=irows.getText();
			if(rowText.contains(ConstantsFile.patientlastnamewithoutIns)){
				return isInboundQueueIsDisplayedInQueueManagement=true;
			}
		}
		return isInboundQueueIsDisplayedInQueueManagement;
	}
	/**
	 * This method is used to click on Update Information option present for updating the any patient information in  patient portal Page.
	 */
	public void clickOnUpdateInformation(){
		sleep(6000);
		SeleniumUtil.getElementWithFluentWaitInChrome(updateInformationButton).click();
	}
	/**
	 * This method is used to verify when user clicks on Update Information option ,Update Clinical Information Page should be open in  patient portal Page.
	 */
	public boolean verifyUpdateClinicalInformationPage(){
		boolean isClinicalPageOpened=false;
		String updateInformationPageHeading=SeleniumUtil.getElementWithFluentWaitInChrome(updateInfoHeading).getText();
		System.out.println(updateInformationPageHeading);
		if(updateInformationPageHeading.equalsIgnoreCase("Update Clinical Information")){
			System.out.println("Update Clinical Information page is opened");
			isClinicalPageOpened=true;
		}
		return isClinicalPageOpened;
	}
	/**
	 * This method is used to select any checkbox value from Race  to update information  in  patient portal Page.
	 */
	public void selectAnyRaceCheckbox(){
		List<WebElement> els = driver1.findElements(raceCheckbox);
		System.out.println(els.size());
		for ( WebElement el : els ) {
			String rowText =el.getText();
			System.out.println(rowText);
			if ( !el.isSelected() ) {
				el.click();
				break;
			}
		}
	}
	/**
	 * This method is used to select any radio button value from Ethnicity  to update information  in  patient portal Page.
	 */
	public void selectEthnicityRadioButton(){
		sleep(9000);
		List<WebElement> els = driver1.findElements(By.xpath("//input[@name='ethnicity']"));
		System.out.println(els.size());
		for ( WebElement el : els ) {
			sleep(4000);
			if ( !el.isSelected() ) {
				el.click();
				//continue;

				break;
			}
		}
	}
	/**
	 * This method is used to click on Send button present on the top for sending updated information in gGastro.
	 */
	public void clickOnSendTopButton(){
		selectLocation();
		SeleniumUtil.getElementWithFluentWaitInChrome(sendTopButton).click();
	}
	/**
	 * This method is used to click on inbound option present under Update folder for checking the update information sent from patient portal.
	 */
	public void selectInBoundFromUpdateFolder(String menuName){
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		SeleniumUtil.switchToFrame(driver, "fraPortalManager_Frame");
		List<WebElement> els1 = driver.findElements(By.className("rtIn"));
		System.out.println(els1.size());
		for ( WebElement el : els1 ) {
			String rowText =el.getText();
			System.out.println(rowText);
			if(rowText.contains(menuName)){
				el.click();
			}
		}

	}
	/**
	 * This method is used to verify inbound queue is present  in patient portal Manager queue in gmed 
	 */
	public boolean verifyInboundQueueInUpdateRequest() throws Exception{
		boolean isInboundQueueInUpdateRequest=false;
		Thread.sleep(2000);
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPORTAL);
		List<WebElement>  trtags =driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:trtags){
			String rowText=irows.getText();
			if(rowText.contains(ConstantsFile.patientlastnamewithoutIns) && rowText.contains(ConstantsFile.patientfirstnamewithoutIns) ){
			
			action.moveToElement(irows).doubleClick().build().perform();
				isInboundQueueInUpdateRequest=true;
			}
		}
		return isInboundQueueInUpdateRequest;
	}
	/**
	 * This method is used to click on Accept all button present in patient portal Manager queue in gmed 
	 */
	public void clickOnAcceptAll(){
		sleep(7000);
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPORTALINSIDE);
		SeleniumUtil.getElementWithFluentWait(acceptAllButton).click();
	}
	/**
	 * This method is used to login in patient portal login page 
	 */
	public void loginWithExistingUser(){
		
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		///Get the data to create new user present in excel
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		ConstantsFile.portalUser =ConstantsFile.loginData.get(ConstantsFile.PORTAL_VALID_USERNAME);
		ConstantsFile.portalpasswordDetails =ConstantsFile.loginData.get(ConstantsFile.PORTAL_VALID_PASSWORD);
		SeleniumUtil.getElementWithFluentWaitInChrome(userNameTextBox).sendKeys(ConstantsFile.portalUser);
		SeleniumUtil.getElementWithFluentWaitInChrome(passwordTextBox).sendKeys(ConstantsFile.portalpasswordDetails);
		SeleniumUtil.getElementWithFluentWaitInChrome(signUp).click();
	}
	/**
	 * This method is used to verify document is publish on patient portal
	 */
	public boolean verifyDocumentInPP(){
		boolean isDocumentPresentInHomePage=false;
		String documentValue=SeleniumUtil.getElementWithFluentWaitInChrome(documentTextOnHome).getText();
		System.out.println("Document value is"+documentValue);
		String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
		String currentValue=OutputManagerPage.documentText+currentDate;
		if(documentValue.contains(currentValue) ){
			System.out.println("Document is publish on home Page");
			isDocumentPresentInHomePage=true;
		}
		return isDocumentPresentInHomePage;
		
	}
	/**
	 * This method is used for selecting left navigation menus present in left panel
	 * @param menuName
	 */
	public void selectLeftNavigationMenu(String menuName){
		List<WebElement>  totalMenus =driver1.findElements(leftMenus);
		System.out.println("The size of left Navigation Menu"+totalMenus.size());
		for(WebElement menuNames:totalMenus){
			String rowText=menuNames.getText();
			System.out.println(rowText);
			if(rowText.equalsIgnoreCase(menuName)){
				menuNames.click();
				sleep(3000);
				break;
			}
		}
	}
	/**
	 * This method is used to verify documents is publish in my document menu present in patient portal
	 * @return
	 */
		public boolean verifyDocumentInMyDocuments(){
			sleep(5000);
			boolean isDocumentPresent=false;
			String documentValue=SeleniumUtil.getElementWithFluentWaitInChrome(documentText).getText();
			System.out.println("Document value is"+documentValue);
			String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
			String currentValue=OutputManagerPage.documentText+currentDate;
			if(documentValue.contains(currentValue) ){
				System.out.println("Document is publish on Document Page");
				isDocumentPresent=true;
			}
			return isDocumentPresent;
			
		}
		
		
	}

