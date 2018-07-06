package com.gmed.pages;

import static com.gmed.helper.DriverFactory.driver;

import java.util.Iterator;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;


import com.gmed.Frames.Frames;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;

public class LoginPage extends com.gmed.base.BaseAbstractPage {
	/** Logger to log the LoginPage log messages */
	private static Logger logger                = LogManager.getLogger(LoginPage.class);
	public static By  usernameTxt              = By.xpath("//INPUT[@id='txtUserName_TextBox']");
	public static By  passwordTxt              = By.id("txtPassword_TextBox");
	public static By loginBtn                  = By.id("btnLogin_Center");
	public static By  popuploginyes            = By.id("btnYes_Left");
	public static By  popuploginno             = By.id("btnNo_Left");
	//public static By  logout                   = By.cssSelector("span[id=btnLogout_SpanBGColor]");
	//public static By locationselectioncheckbox = By.id("4dd2cc47-45fb-479e-a213-2c9d572668b2_checkbox");
	public static By selectbutton              = By.id("btnSelect_SpanBGColor");
	public static By savebutton                = By.id("btnSave_SpanBGColor");
	public static By logoutbutton              = By.id("btnLogout");
	//public static By billingTypeDropDown       = By.id("ddlType_DropDown");
	//Actions action =new Actions(driver);

	/**
	 * This method is used to logging in gGastro Application
	 * @param loginData
	 */
	public void loginToGmedWithGpin( Map<String, String> loginData) {
		//Switch to Login frame
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		logger.info("Entering the username in the Element  "+ConstantsFile.usernamevalue);
		SeleniumUtil.getElementWithFluentWait(usernameTxt).sendKeys(ConstantsFile.usernamevalue);
		logger.info("Entering the Password in the Element  " +ConstantsFile.passwordvalue);
		SeleniumUtil.getElementWithFluentWait(passwordTxt).sendKeys(ConstantsFile.passwordvalue);
		sleep(3000);
		logger.info("Logging into the application...");
		SeleniumUtil.getElementWithFluentWait(loginBtn).click();
		handleAlreadyLoggedUser();	
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
	}

	/** This method is used to verify Home Page is loaded in gGastro Application
	 * 
	 */
	public boolean verifyHomePageIsLoaded(){	
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		boolean isHomePagePresent= SeleniumUtil.getElementWithFluentWait(logoutbutton) != null;
		if(isHomePagePresent){
			System.out.println("Home page is Loaded");
		}
		return isHomePagePresent;
	}
	/**
	 * This method is used for verify the title of gGastro application
	 * @return true when title is present
	 */
	public  boolean verifyHomePageTitle(){
		String pageTitle=null;
		pageTitle=driver.getTitle();
		System.out.println("The page title is:"+pageTitle);
		if(pageTitle.contains("gMed gGastro")){
			System.out.println("The home page verified..");
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * method for handling when user is already logged in the application
	 * 
	 */
	public void handleAlreadyLoggedUser(){
		/**
		 * wait for number of windows to load
		 */
		SeleniumUtil.waitForNumberOfWindowsToEqual(2);
		String parentWindowHandler = driver.getWindowHandle();
		System.out.println("The window are :"+driver.getWindowHandles().size());// Store your parent window
		if(driver.getWindowHandles().size() == 2){
			System.out.println("Yes No window");
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
			WebElement popPresent= SeleniumUtil.getElementWithFluentWait(popuploginyes);
			if (popPresent != null) 
			{
				popPresent.click();
				sleep(5000);	
			}
			driver.switchTo().window(parentWindowHandler); 
		}
	}

	/**
	 * This method is used to save the created user details
	 * 
	 * 
	 */
	public void clickOnSaveButton() {
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		logger.info("saving the user creation details...");
		SeleniumUtil.getElementWithFluentWait(savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		sleep(5000);
	}

	/**
	 * This method is used to logged with created new user in the gGastro application 
	 */
	public void loggedInWithNewUser(){
		//switching into login frame
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		logger.info("Entering the username in the Element  "+ConstantsFile.username);
		SeleniumUtil.getElementWithFluentWait(usernameTxt).sendKeys(ConstantsFile.username);
		logger.info("Entering the Password in the Element "+ConstantsFile.password);
		SeleniumUtil.getElementWithFluentWait(passwordTxt).sendKeys(ConstantsFile.password);
		sleep(3000);
		logger.info("Logging into the application...");
		SeleniumUtil.getElementWithFluentWait(loginBtn).click();
		handleAlreadyLoggedUser();
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
	}

	/** This method is used to click on save button 
	 * 
	 */
	public void selectSavebutton() {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		sleep(5000);
		SeleniumUtil.getElementWithFluentWait(savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}

	/**
	 * This method is used to logging in gGastro Application
	 * @param loginData
	 */
	public void loginToGmedWithExistingUser( Map<String, String> loginData) {
		SeleniumUtil.focusOnInternetExplorer();
		//Switch to Login frame
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		///Get the data to create new user present in excel
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		ConstantsFile.mainUser =ConstantsFile.loginData.get(ConstantsFile.LOGIN_VALID_USERNAME);
		String password=ConstantsFile.loginData.get(ConstantsFile.LOGIN_VALID_PASSWORD);
		ConstantsFile.mainUser =ConstantsFile.loginData.get(ConstantsFile.LOGIN_VALID_USERNAME);
		logger.info("Entering the username in the Element  "+ConstantsFile.mainUser);
		SeleniumUtil.getElementWithFluentWait(usernameTxt).sendKeys(ConstantsFile.mainUser);
		logger.info("Entering the Password in the Element  " +password);
		SeleniumUtil.getElementWithFluentWait(passwordTxt).sendKeys(password);
		sleep(3000);
		logger.info("Logging into the application...");
		SeleniumUtil.getElementWithFluentWait(loginBtn).click();
		handleAlreadyLoggedUser();	
	}
	/**
	 * This method is used to logging in gGastro Application
	 * @param loginData
	 */
	public void loginToGmedWithBreakTheGlassUser(String user ,String password) {

		//Switch to Login frame
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		logger.info("Entering the username in the Element  "+user);
		SeleniumUtil.getElementWithFluentWait(usernameTxt).sendKeys(user);
		logger.info("Entering the Password in the Element  "+password );
		SeleniumUtil.getElementWithFluentWait(passwordTxt).sendKeys(password);
		sleep(3000);
		logger.info("Logging into the application...");
		SeleniumUtil.getElementWithFluentWait(loginBtn).click();
		handleAlreadyLoggedUser();	
	}
	/**
	 * This method is used to logging in gGastro Application
	 * @param loginData
	 */
	public void loginToGmedForPatientPortal( Map<String, String> loginData) {
		
		SeleniumUtil.focusOnInternetExplorer();
		//Switch to Login frame
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		///Get the data to create new user present in excel
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		String username =ConstantsFile.loginData.get(ConstantsFile.LOGIN_VALID_USERNAME);
		String password=ConstantsFile.loginData.get(ConstantsFile.LOGIN_VALID_PASSWORD);
		logger.info("Entering the username in the Element  "+username);
		SeleniumUtil.getElementWithFluentWait(usernameTxt).sendKeys(username);
		logger.info("Entering the Password in the Element  " +password);
		SeleniumUtil.getElementWithFluentWait(passwordTxt).sendKeys(password);
		sleep(3000);
		logger.info("Logging into the application...");
		SeleniumUtil.getElementWithFluentWait(loginBtn).click();
		handleAlreadyLoggedUser();	
	}
	
	// This method is used to login without passing any parameter.
	public void loginToGmed() {
	
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		System.out.println("Enter username");
		logger.info("Entering the username in the Element  "+ConstantsFile.usernamevalue1);
		SeleniumUtil.getElementWithFluentWait(usernameTxt).sendKeys(ConstantsFile.usernamevalue1);
		System.out.println("Enter Password");
		logger.info("Entering the Password in the Element  " +ConstantsFile.passwordvalue1);
		SeleniumUtil.getElementWithFluentWait(passwordTxt).sendKeys(ConstantsFile.passwordvalue1);
		sleep(3000);
		logger.info("Logging into the application...");
		SeleniumUtil.getElementWithFluentWait(loginBtn).click();
		handleAlreadyLoggedUser();	
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
	}
}

