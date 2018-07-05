package com.gmed.pages;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;

public class ConfigurationPage extends BaseAbstractPage {
	/** Logger to log the ConfigurationPage log messages */
	private static Logger logger                = LogManager.getLogger(ConfigurationPage.class);
	public static By configuration              = By.id("tabConfiguration_Text");
	public static By users                      = By.id("btnUser_Table");
	public static By newbutton                  = By.id("btnNew_SpanBGColor");
	public static By firstname                  = By.id("txtFirstName_TextBox");
	public static By lastname                   = By.id("txtLastName_TextBox");
	public static By usernamelogin              = By.id("txtLogin_TextBox");
	public static By passwordtextbox            = By.id("txtPassword_TextBox");
	public static By confirmtextbox             = By.id("txtPasswordConfirmation_TextBox");
	public static By rolePlusbutton             = By.id("txtRoleAdd");
	public static By searchusertextbox          = By.id("txtName_TextBox");
	public static By rolelist                   = By.xpath("//div[@id='ctl00_trePermissions']/ul/li/div/span[@class='rtUnchecked']");
	public static By refreshButton              = By.id("btnRefresh_SpanBGColor");
	public static By locationplusbutton         = By.id("tblLocationCollectionAdd");
	public static By searchbutton               = By.id("btnSearch_SpanBGColor");
	public static By listrow                    = By.xpath(".//table[@id='tblList_Table']/tbody/tr");
	public static By providericon               = By.id("btnProvider_Table");
	public static By addressbutton              = By.id("tblPhoneCollectionAdd");
	public static By useraddbutton              = By.id("txtUserAdd");
	public static By filteredrrow               = By.className("tableRowBorder");
	public static By selectButtonInProvider     = By.id("btnSelectImage_TextSpan");
	public static By bussinessUnitBUDropDown    = By.id("txtBusinessUnitDropDown");
	public static By bussinessUnitDropDown      = By.id("txtProfessionalBillingBusinessUnitDropDown");
	public static By identificationButton       = By.id("tblIdentificationCollectionAdd");
	public static By NPITEXTBOX                 = By.id("txtNumber_TextBox");
	public static By addressButton              = By.id("tblAddressCollectionAdd");
	public static By line1Address               = By.id("txtLine1_TextBox");
	public static By activityicon               = By.id("btnActivity_ImageContainer");
	public static By serviceDropDown            = By.id("txtServiceTypeDropDown");
	public static By addplusbutton              = By.id("tblLocationsCollectionAdd");
	public static By templatebutton             = By.id("btnTemplateConfiguration_ImageContainer");
	public static By locationPlusButton         = By.id("tblLocationProviderCollectionAdd");
	public static By templatePlusButton         = By.id("tblTemplateCollectionAdd");
	public static By keywordTextBox             = By.id("txtKeywords_TextBox");
	public static By addButton                  = By.id("btnAdd_SpanBGColor");
	public static By saveButton                 = By.id("btnSave_SpanBGColor");
	public static By search                     = By.id("btnFiltersSearch_Table");
	public static By ReportButton               = By.id("btnPublishDefaultReports_ImageContainer");
	public static By okayButton                 = By.xpath("//*[@id='btnOK_Center']");
	public static By gQuicButton                = By.id("btnGIQuicExport_ImageContainer");
	public static By runReport                  = By.id("btnRunReport_SpanBGColor");
	public static By outPutManager              = By.id("btnOutputManager_ImageContainer");
	public static By preferences                = By.id("btnPreferences_ImageContainer");
	public static By gPmCheckbox                = By.id("chkGgastroPlusPM");
	public static By schedulerCheckbox          = By.id("chkGgastroPlusScheduler");
	public static By locationButton             = By.id("btnLocation_ImageContainer");
	public static By backButton                 = By.id("btnBack_ImageContainer");
	public static By billingType                = By.id("tblBusinessUnitsPerLocationAdd");
	public static By addressTextBox             = By.id("txtLine1_TextBox");
	public static By cityTextBox                = By.id("txtCity_TextBox");
	public static By zipTextBox                 = By.id("txtZipCode_TextBox");
	public static By locationNPI                = By.id("txtNpi_IntegerTextBox");
	public static By placeOfService             = By.id("txtPlaceOfServiceDropDown");
	public static By guidlinesButton            = By.id("btnGuideline_ImageContainer");

	List<WebElement> rolesvalue;
	/**
	 * This method is used to click on configuration present in left panel as gPin user in the gGastro application
	 * 
	 */
	public void clickOnConfiguration(){
		//switching in left panel frame
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu present on LeftPanel... ");
		SeleniumUtil.getElementWithFluentWait(configuration).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
	}
	/**
	 * This method is used to click on new button in the gGastro application
	 * 
	 */
	public void clickOnNew(){
		//switching in  user page frame
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		logger.info("clicking on new button to create new user");
		SeleniumUtil.getElementWithFluentWait(newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERPAGE);
	}


	/**
	 * This method is used to  create new user in the gGastro
	 * 
	 */
	public void enterLoginDetailsForUserCreation(){
		clickOnConfiguration();
		//switching in configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on users button to create new user present in configuration");
		SeleniumUtil.getElementWithFluentWait(users).click();
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
		sleep(3000);
		clickOnNew();
		//switching in  user creation page frame
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		//Get the data to create new user present in excel
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("UserCreationForm");
		//Get the first name present in the excel & concat with random number
		ConstantsFile.userfirstname = ConstantsFile.loginData.get(ConstantsFile.USER_FIRST_NAME).concat(ConstantsFile.genData.generateRandomNumber(4));
		//Get the last name present in the excel & concat with random number
		ConstantsFile.usercompletelastname = ConstantsFile.loginData.get(ConstantsFile.USER_LAST_NAME).concat(ConstantsFile.genData.generateRandomNumber(5));
		SeleniumUtil.getElementWithFluentWait(firstname).sendKeys(ConstantsFile.userfirstname);
		SeleniumUtil.getElementWithFluentWait(lastname).sendKeys(ConstantsFile.usercompletelastname);
		//create random username for user creation
		ConstantsFile.username = ConstantsFile.loginData.get(ConstantsFile.USER_NAME).concat(ConstantsFile.genData.generateRandomNumber(2).concat(ConstantsFile.genData.generateStringWithAllobedSplChars(2, "@#@&")));
		logger.info("entering the random username...");
		SeleniumUtil.getElementWithFluentWait(usernamelogin).sendKeys(ConstantsFile.username);
		logger.info("entering the password...");
		ConstantsFile.password = ConstantsFile.loginData.get(ConstantsFile.PASSWORD);
		SeleniumUtil.getElementWithFluentWait(passwordtextbox).sendKeys(ConstantsFile.password);
		logger.info("entering the confirmation password..");
		SeleniumUtil.getElementWithFluentWait(confirmtextbox).sendKeys(ConstantsFile.password);
	}
	/**
	 * This method will create new role (system administrator role) for the user which have all permissions
	 * 
	 */
	public void createNewRole(){
		//Get the data to create new user present in excel
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("UserCreationForm");
		logger.info("clicking on role button....");
		SeleniumUtil.getElementWithFluentWait(rolePlusbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		//switching into role frame
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("creating new role for user...");
		SeleniumUtil.getElementWithFluentWait(newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(3000);
		ConstantsFile.sysadminrole = ConstantsFile.loginData.get(ConstantsFile.USER_ROLE).concat(ConstantsFile.genData.generateRandomNumber(5));
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(ConstantsFile.sysadminrole);
		rolesvalue =driver.findElements(rolelist);
		for(WebElement roles:rolesvalue){
			roles.click();
			//SeleniumUtil.waitForProgressBar(Frames.CREATION);
		}
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.CREATION);
	}

	/**
	 * This method will select the created role(sys-admin with all rights) during user creation
	 * 
	 */
	public void selectCreatedRole(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(refreshButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(5000);
		logger.info("selecting the System Administrator role" +ConstantsFile.sysadminrole);
		WebElement rolevalues = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.sysadminrole +"')]"));
		SeleniumUtil.scrolltoWebElement(rolevalues);
		rolevalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method will verify correct role should be selected for the user
	 * @return true when all roles should be present
	 *  
	 */
	public boolean verfiyRoleList(){
		if(rolesvalue.size()>0){
			logger.info("Role list verfied...");
			return true;
		}
		else{
			logger.info("No role list present");
			return false;
		}
	}
	/** This method will create new location for user
	 * 
	 */
	public void createNewLocation(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		logger.info("clicking on location plus button...");
		SeleniumUtil.getElementWithFluentWait(locationplusbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("clicking on new button...");
		SeleniumUtil.getElementWithFluentWait(newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		ConstantsFile.Fulllocationname = ConstantsFile.locationname.concat(ConstantsFile.genData.generateRandomNumber(5));
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(ConstantsFile.Fulllocationname);
		logger.info("saving the location...");
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.CREATION);
	}
	/**
	 * This method is used to verify the location during creation of new user
	 * @return true when locations are present in the location pop
	 * 
	 */
	public boolean selectLocation() {
		boolean locationvalue=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("selecting the automation location" +ConstantsFile.Fulllocationname);
		WebElement locationvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.Fulllocationname +"')]"));
		SeleniumUtil.scrolltoWebElement(locationvalues);
		locationvalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		locationvalue=true;
		return locationvalue;
	}
	/**
	 * This method is used to verify locations should be present in the location pop up
	 * @return true when multiples locations are present in the location popup
	 */
	public boolean verifyLocationPopup(){
		if(ConstantsFile.Fulllocationname!=null){
			logger.info("Multples locations are present in the location popup");
			return true;
		}
		else{
			System.out.println("No Location present in the list");
			return false;
		}
	}
	/**
	 * This method is used to verify whether new user is created in the application
	 * @return true when user is present while searching in the user searching filter
	 */
	public boolean verifyUserIsCreated(){
		Boolean isUserCreated=false;
		//switching in  user page frame
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(ConstantsFile.userfirstname);
		SeleniumUtil.getElementWithFluentWait(searchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERPAGE);
		sleep(3000);
		String userrowvalue = SeleniumUtil.getElementWithFluentWait(listrow).getText();
		if(userrowvalue!=null ){
			isUserCreated=true;
		}
		else{
			System.out.println("user is not created successfully");
			isUserCreated= false;
		}
		return isUserCreated;
	}
	/**
	 * This method is used to verify whether new user is created in the application
	 * @return true when user is present while searching in the user searching filter
	 */
	public boolean verifyProviderIsCreated(){
		Boolean isUserCreated=false;
		//switching in  user page frame
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		SeleniumUtil.getElementWithFluentWait(By.id("txtSearchMore")).click();
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(ConstantsFile.PROVIDER_FIRST_NAME);
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(ConstantsFile.PROVIDER_LAST_NAME);
		SeleniumUtil.getElementWithFluentWait(searchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERPAGE);
		sleep(3000);
		String userrowvalue = SeleniumUtil.getElementWithFluentWait(listrow).getText();
		if(userrowvalue!=null ){
			isUserCreated=true;
		}
		else{
			System.out.println("Provider is not created successfully");
			isUserCreated= false;
		}
		return isUserCreated;
	}
	/**
	 * This method is used to create new provider and to Map with loggedin User
	 * @throws FindFailed 
	 * @throws Exception
	 */
	public static void createNewProviderandAssociate() throws FindFailed {
		//switching into configuration frame to create new provider
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu...");
		SeleniumUtil.getElementWithFluentWait(configuration).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		//switching into configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on provider button...");
		SeleniumUtil.getElementWithFluentWait(providericon).click();
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
		//switching into userpage frame 
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		logger.info("clicking on new button present on provider page..");
		SeleniumUtil.getElementWithFluentWait(newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERPAGE);
		//switching into provider creation frame
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		//get the provider data in the providerlist sheet present in the excel
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("ProviderList");
		//get the provider first name from the excel
		ConstantsFile.providerfirstname = ConstantsFile.loginData.get(ConstantsFile.PROVIDER_FIRST_NAME);
		//create the random last name for the proivider
		String partialproviderlastname = ConstantsFile.genData.generateStringWithAllobedSplChars(3, "@");
		ConstantsFile.providercompletelastname = ConstantsFile.loginData.get(ConstantsFile.PROVIDER_LAST_NAME).concat(partialproviderlastname);
		logger.info("entering the first name for the provider...");
		SeleniumUtil.getElementWithFluentWait(firstname).sendKeys(ConstantsFile.providerfirstname);
		logger.info("entering the last name for the provider...");
		SeleniumUtil.getElementWithFluentWait(lastname).sendKeys(ConstantsFile.providercompletelastname);
		SeleniumUtil.getElementWithFluentWait(addressbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("clickOnType1");
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnFax");
		SeleniumUtil.getElementWithFluentWait(By.id("ptbNumber_00")).sendKeys("111");
		SeleniumUtil.getElementWithFluentWait(By.id("ptbNumber_01")).sendKeys("111");
		SeleniumUtil.getElementWithFluentWait(By.id("ptbNumber_02")).sendKeys("1111");
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		logger.info("Mapping the new user with created provider...");
		SeleniumUtil.getElementWithFluentWait(useraddbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		//switching into user searching frame
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("search the logged in user by first name & last name");
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(ConstantsFile.userfirstname);
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(ConstantsFile.usercompletelastname);
		logger.info("clicking on search button...");
		SeleniumUtil.getElementWithFluentWait(searchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		staticSleep(5000);
		logger.info("select the filtered row...");
		SeleniumUtil.getElementWithFluentWait(filteredrrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		logger.info("select the user to map with the created provider...");
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		ConstantsFile.isUserAssociated=true;
		//again switching back to provider creation frame
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		logger.info("clicking on location plus button..");
		SeleniumUtil.getElementWithFluentWait(locationplusbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		//switching into location frame to select the location
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("select the location...");
		List<WebElement> locationlistvalue = driver.findElements(listrow);
		System.out.println("The size of location are:"+locationlistvalue.size());
		if(locationlistvalue.size()>0){
			logger.info("selecting the automation location" +ConstantsFile.Fulllocationname);
			WebElement locationvalues= driver.findElement(By.xpath(".//*[contains(text(),'"+ ConstantsFile.Fulllocationname +"')]"));
			SeleniumUtil.scrolltoWebElement(locationvalues);
			locationvalues.click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		}
	}
	/** This method is used to save details for provider
	 * 
	 */
	public void clickOnSaveForProvider() {
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		logger.info("saving the provider details...");
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		sleep(5000);
	}
	/** This method is used to select signature for provider
	 * 
	 */
	public void selectSignatureForProvider(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		WebElement selectbuttonvalue =SeleniumUtil.getElementWithFluentWait(selectButtonInProvider);
		SeleniumUtil.scrolltoWebElement(selectbuttonvalue);
		selectbuttonvalue.click();
		//SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		sleep(5000);
	}
	/** This method is used to verify user is associated with provider 
	 * 
	 */
	public boolean verifyUserIsMappedWithProvider(){
		if(ConstantsFile.isUserAssociated=true){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * This method is used to select professional Business Unit of the Provider
	 */
	public void selectBUForProvider(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.getElementWithFluentWait(bussinessUnitDropDown).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement Buvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.buUnit +"')]"));
		Buvalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to select professional Business Unit of the Provider
	 */
	public void selectBUForLocation(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.getElementWithFluentWait(bussinessUnitBUDropDown).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement Buvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.buUnit +"')]"));
		Buvalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to selectIdentification  for the Provider
	 */
	public void selectIdentificationForProvider(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.getElementWithFluentWait(identificationButton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(NPITEXTBOX).sendKeys("1234567890");
	}
	/**
	 * This method is used to select Billing address for provider
	 * @throws FindFailed 
	 */
	public void selectBillingAddressForProvider() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.getElementWithFluentWait(addressButton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("dropdown");
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnBilling");
		SeleniumUtil.getElementWithFluentWait(line1Address).sendKeys("test");
	}
	/**
	 * This method is used to click on my activities menu
	 * 
	 */
	public void clickOnActvities(){
		//switching into configuration frame to create new provider
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu...");
		//SeleniumUtil.highlightElement(driver,configuration);
		SeleniumUtil.getElementWithFluentWait(configuration).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		//switching into configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on provider button...");
		SeleniumUtil.getElementWithFluentWait(activityicon).click();
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
	}
	/**
	 * This method is used to click on new button present on activities creation Page
	 * 
	 */
	public void clickOnnewButtonOnActivitiesPage(){
		//switching into userpage frame 
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE_FRAME);
		logger.info("clicking on new button present on activities page..");
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERPAGE_FRAME);
	}
	/**
	 * This method is used for creating new Colonscopy activity on activities creation Page
	 * 
	 */
	public void createNewColonscopyActivity(){
		//switching into provider creation frame
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		//create the random patient first name
		ConstantsFile.ColonIntialname = ConstantsFile.ColonIntialname.concat(ConstantsFile.genData.generateStringWithAllobedSplChars(3, "@")).concat(ConstantsFile.genData.generateRandomNumber(2));
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.searchusertextbox).sendKeys(ConstantsFile.ColonIntialname);
		SeleniumUtil.getElementWithFluentWait(serviceDropDown).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		//SeleniumUtil.getElementWithFluentWait(By.id("mnuNewTask")).click();
		WebElement colonscopyvalue= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'Colonoscopy')]"));
		SeleniumUtil.scrolltoWebElement(colonscopyvalue);
		colonscopyvalue.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for selecting location 
	 * 
	 */
	public void selectLocationInSchuduler(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.getElementWithFluentWait(addplusbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("selecting the automation location" +ConstantsFile.Fulllocationname);
		WebElement locationvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.Fulllocationname +"')]"));
		SeleniumUtil.scrolltoWebElement(locationvalues);
		locationvalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to save the created user details
	 * @throws Exception 
	 * 
	 */
	public void clickOnSaveButton() {
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		logger.info("saving the user creation details...");
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		sleep(5000);
	}
	/**
	 * This method is used to click on template button present on configuration Page
	 * 
	 */
	public void clickOnTemplate() throws Exception{
		//switching into configuration frame to create new provider
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu...");
		//SeleniumUtil.highlightElement(driver,configuration);
		SeleniumUtil.getElementWithFluentWait(configuration).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		//switching into configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on template button...");
		SeleniumUtil.getElementWithFluentWait(templatebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
	}
	/**
	 * This method is used to configure template for colonoscopy on configuration Page
	 * 
	 */
	public void configTemplateForColonscopy(){
		//switching into userpage frame 
		SeleniumUtil.switchToParentFrame(Frames.TEMPLATEPAGE);
		WebElement colonscopyvalue= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'Colonoscopy')]"));
		SeleniumUtil.scrolltoWebElement(colonscopyvalue);
		colonscopyvalue.click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATEPAGE);
		sleep(9000);
		logger.info("clicking on new button present on template page..");
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATEPAGE);
		SeleniumUtil.switchToParentFrame(Frames.TEMPLATECREATIONPAGE);
		//create the random patient first name
		ConstantsFile.ColonIntialTemplatename = ConstantsFile.ColonIntialTemplatename.concat(ConstantsFile.genData.generateStringWithAllobedSplChars(2, "@")).concat(ConstantsFile.genData.generateRandomNumber(2));
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(ConstantsFile.ColonIntialTemplatename);
		SeleniumUtil.getElementWithFluentWait(locationPlusButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATECREATIONPAGE);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement locationvalues=SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.Fulllocationname +"')]"));
		SeleniumUtil.scrolltoWebElement(locationvalues);
		locationvalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.TEMPLATECREATIONPAGE);
		SeleniumUtil.getElementWithFluentWait(templatePlusButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATECREATIONPAGE);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(keywordTextBox).sendKeys("Colonoscopy");
		SeleniumUtil.getElementWithFluentWait(search).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		List<WebElement> templates = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println(templates.size());
		for(WebElement tdvalue:templates){
			String rowText=tdvalue.getText();
			WebElement tdvalue1 =tdvalue.findElement(By.xpath(".//td[2]"));
			String tdvalue1Text=tdvalue1.getText();
			logger.info("base template is.."+tdvalue1Text);
			WebElement tdvalue2 =tdvalue.findElement(By.xpath(".//td[3]"));
			String tdvalue2Text=tdvalue2.getText();
			if(tdvalue1Text.trim().equalsIgnoreCase("") && tdvalue2Text.equalsIgnoreCase("Colonoscopy") ){
				tdvalue.click();
				break;
			}
		}
		//SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(addButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.TEMPLATECREATIONPAGE);
	}
	/**
	 * This method is used to configure  PreProcedure Nursing Note for colonscopy
	 * @throws Exception
	 */
	public void configPreProcedureNursingNoteForColonscopy(){
		SeleniumUtil.switchToParentFrame(Frames.TEMPLATECREATIONPAGE);
		SeleniumUtil.getElementWithFluentWait(templatePlusButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATECREATIONPAGE);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(keywordTextBox).sendKeys("pre-procedure");
		SeleniumUtil.getElementWithFluentWait(search).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(3000);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(addButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.TEMPLATECREATIONPAGE);
		SeleniumUtil.getElementWithFluentWait(saveButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATECREATIONPAGE);
	}
	/**
	 * This method is used for creating new First visit activity on activities creation Page
	 * 
	 */
	public void createNewFirstVisitActivity(){
		//switching into provider creation frame
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		//create the random patient first name
		ConstantsFile.FirstVisitIntialname = ConstantsFile.FirstVisitIntialname.concat(ConstantsFile.genData.generateStringWithAllobedSplChars(3, "@")).concat(ConstantsFile.genData.generateRandomNumber(2));
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(ConstantsFile.FirstVisitIntialname);
		SeleniumUtil.getElementWithFluentWait(serviceDropDown).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		//SeleniumUtil.getElementWithFluentWait(By.id("mnuNewTask")).click();
		WebElement firstvisitvalue= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'First Visit')]"));
		SeleniumUtil.scrolltoWebElement(firstvisitvalue);
		firstvisitvalue.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to config  template for FirstVisit
	 * 
	 */
	public void configTemplateForFirstVisit(){
		//switching into userpage frame 
		SeleniumUtil.switchToParentFrame(Frames.TEMPLATEPAGE);
		WebElement colonscopyvalue= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'First Visit')]"));
		SeleniumUtil.scrolltoWebElement(colonscopyvalue);
		colonscopyvalue.click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATEPAGE);
		logger.info("clicking on new button present on template page..");
		SeleniumUtil.getElementWithFluentWait(newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATEPAGE);
		SeleniumUtil.switchToParentFrame(Frames.TEMPLATECREATIONPAGE);
		//create the random patient first name
		ConstantsFile.FirstVisitIntialTemplatename = ConstantsFile.FirstVisitIntialTemplatename.concat(ConstantsFile.genData.generateStringWithAllobedSplChars(2, "@")).concat(ConstantsFile.genData.generateRandomNumber(2));
		SeleniumUtil.getElementWithFluentWait(searchusertextbox).sendKeys(ConstantsFile.FirstVisitIntialTemplatename);
		SeleniumUtil.getElementWithFluentWait(locationPlusButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATECREATIONPAGE);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement locationvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.Fulllocationname +"')]"));
		SeleniumUtil.scrolltoWebElement(locationvalues);
		locationvalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.TEMPLATECREATIONPAGE);
		SeleniumUtil.getElementWithFluentWait(templatePlusButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATECREATIONPAGE);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(keywordTextBox).sendKeys("First Visit");
		SeleniumUtil.getElementWithFluentWait(search).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(addButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.TEMPLATECREATIONPAGE);
		SeleniumUtil.getElementWithFluentWait(saveButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TEMPLATECREATIONPAGE);
	}
	/**
	 * This method is used to click on Report button present on configuration Page
	 * 
	 */
	public void clickOnReport(){
		//switching into configuration frame to create new provider
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu...");
		SeleniumUtil.getElementWithFluentWait(configuration).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		//switching into configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on report button...");
		WebElement reporticon = SeleniumUtil.getElementWithFluentWait(ReportButton);
		SeleniumUtil.scrolltoWebElement(reporticon);
		reporticon.click();
		//SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
	}
	/**
	 * method for handling Handle Report Model Popup
	 * 
	 */

	public boolean verifyReportIsPublish(){
		/**
		 * wait for number of windows to load
		 */
		SeleniumUtil.waitForNumberOfWindowsToEqualInReport(2);
		sleep(60000);
		ConstantsFile.isYesNo = false;
		String parentWindowHandler = driver.getWindowHandle();
		System.out.println("The window are :"+driver.getWindowHandles().size());// Store your parent window
		if(driver.getWindowHandles().size() == 2){
			System.out.println("Report is publish popup present");
			String subWindowHandler = null;
			/**
			 *  get all window handles
			 */
			Set<String> handles = driver.getWindowHandles(); 
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()){
				subWindowHandler = iterator.next();
			}
			/**
			 * switch to popup window
			 */
			driver.switchTo().window(subWindowHandler); 

			/**
			 * perform operations on popup
			 */

			sleep(1500);
			WebElement popPresent= SeleniumUtil.getElementWithFluentWait(okayButton);
			if (popPresent != null) 
			{
				popPresent.click();
				ConstantsFile.isOkayPresent = true;
				sleep(5000);
			}
			driver.switchTo().window(parentWindowHandler); 
		}
		return ConstantsFile.isOkayPresent;
	}
	/**
	 * This method is used to click on my gQuic report 
	 * 
	 */
	public void clickOnGquicReport(){
		//switching into configuration frame to create new provider
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu...");
		SeleniumUtil.getElementWithFluentWait(configuration).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		//switching into configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on gquic button...");
		SeleniumUtil.getElementWithFluentWait(gQuicButton).click();
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
	}
	/**
	 * This method is used to verify gQuic exception report page is opened
	 * 
	 */
	public boolean verifyGquicExceptionReportPage() throws Exception{
		boolean isReportpageOpened=false;
		SeleniumUtil.switchToParentFrame(Frames.ReportINSIDE);
		isReportpageOpened=SeleniumUtil.getElementWithFluentWait(runReport)!=null;
		if(isReportpageOpened){
			System.out.println("Report Page is Opened");
			SeleniumUtil.getElementWithFluentWait(runReport).click();
			SeleniumUtil.waitForProgressBar(Frames.ReportINSIDE);
			isReportpageOpened=true;
		}
		SeleniumUtil.clickOnImageWitScreenInSikuli("orderByLastName");
		return isReportpageOpened;
	}
	/**
	 * This method is used to click on template button present on configuration Page
	 * @throws Exception
	 */
	public void clickOnOutPutManager() throws Exception{
		//switching into configuration frame to create new provider
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu...");
		//SeleniumUtil.highlightElement(driver,configuration);
		SeleniumUtil.getElementWithFluentWait(configuration).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		//switching into configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on output manager button...");
		SeleniumUtil.getElementWithFluentWait(outPutManager).click();
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
	}
	/**
	 * This method is used to click on Preferences button present on configuration Page
	 * 
	 */
	public void clickOnPreferences(){
		//switching into left panel frame for click on preferences button in the configuration
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu...");
		SeleniumUtil.getElementWithFluentWait(configuration).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		//switching into configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on Preferences button...");
		SeleniumUtil.getElementWithFluentWait(preferences).click();	
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
	}
	/**
	 * This method is used to enable gPm checkbox  present in  Preferences page
	 * 
	 */
	public void enableGpm(){
		//switching into preferences main Page frame
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		String checkGpmCheckbox =SeleniumUtil.getElementWithFluentWait(gPmCheckbox).getAttribute("stateName");
		if(checkGpmCheckbox.equalsIgnoreCase("checked")){
			System.out.println("gPm checkbox is already enabled");
		}
		else{
			System.out.println("gPm checkbox is not enabled enabled");
			SeleniumUtil.getElementWithFluentWait(gPmCheckbox).click();
			SeleniumUtil.waitForProgressBar(Frames.USERPAGE);
		}
	}
	/**
	 * This method is used to verify  gPm/Enable Enterprise Scheduler checkboxs  are enabled in  Preferences page
	 * 
	 */
	public boolean verifyGpmEnableSettings(){
		boolean isGpmSettingsEnabled=false;
		//switching into preferences main Page frame
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		String checkGpmCheckbox =SeleniumUtil.getElementWithFluentWait(gPmCheckbox).getAttribute("stateName");
		String checkSchedulerCheckbox =SeleniumUtil.getElementWithFluentWait(schedulerCheckbox).getAttribute("stateName");
		if(checkGpmCheckbox.equalsIgnoreCase("checked") && checkSchedulerCheckbox.equalsIgnoreCase("checked")){
			System.out.println("gPm & Enterprise Schedule checkbox are  enabled");
			return isGpmSettingsEnabled=true;
		}
		else{
			System.out.println("gPm & Enterprise Schedule checkbox are not enabled");
			return isGpmSettingsEnabled=false;
		}				
	}
	/**
	 * This method is used to save  gPm settings Preferences page
	 * 
	 */
	public void clickOnSaveInPreferences(){
		SeleniumUtil.getElementWithFluentWait(saveButton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERPAGE);
	}
	/**
	 * This method is used to move back on the configuration page
	 * 
	 */
	public void clickOnBackInConfiguration(){
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		SeleniumUtil.getElementWithFluentWait(backButton).click();
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);

	}
	/**
	 * This method is used to save  gPm settings Preferences page
	 * 
	 */
	public void checkLocationSettings(){
		//switching into configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on location button...");
		SeleniumUtil.getElementWithFluentWait(locationButton).click();
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		logger.info("selecting the  location" +ConstantsFile.Fulllocationname);
		WebElement locationvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.Fulllocationname +"')]"));
		SeleniumUtil.scrolltoWebElement(locationvalues);
		//locationvalues.click();
		//Actions action = new Actions(driver);
		action.moveToElement(locationvalues).doubleClick().build().perform();
	}
	/**
	 * This method is used to select billingType  gPm settings Preferences page
	 * 
	 */
	public void selectBillingType(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		/*ConstantsFile.buUnit = SeleniumUtil.getElementWithFluentWait(bussinessUnitTextBox).getAttribute("value");*/
		SeleniumUtil.getElementWithFluentWait(billingType).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement Buvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.buUnit +"')]"));
		Buvalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		enterLocationAdress();
	}
	/**
	 * This method is used to enter location address for the created location
	 * 
	 */

	public void enterLocationAdress(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.getElementWithFluentWait(addressTextBox).sendKeys("228 Park Ave S");
		SeleniumUtil.getElementWithFluentWait(cityTextBox).sendKeys("New York");
		SeleniumUtil.getElementWithFluentWait(zipTextBox).sendKeys("10003");
		SeleniumUtil.getElementWithFluentWait(locationNPI).sendKeys("1234556677");
		selectPlaceOfsevice();	
	}
	public void selectPlaceOfsevice(){
		SeleniumUtil.getElementWithFluentWait(placeOfService).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement placeOfService= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'Office')]"));
		placeOfService.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);	
	}
	/**
	 * This method is used to click on guideline button present in configuration page
	 * 
	 */
	public void clickOnRecommendationsSection(){
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		SeleniumUtil.getElementWithFluentWait(guidlinesButton).click();
	}
	public void switchToConfigurationMainFrame(){
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
	}
	public void switchToConfigurationInsideFrame(){
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		SeleniumUtil.waitForProgressBar(Frames.USERPAGE);
	}
	public void switchToModulePageFrame(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		
	}

}
