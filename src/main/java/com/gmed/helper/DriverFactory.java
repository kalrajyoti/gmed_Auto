package com.gmed.helper;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.TestNG;

import com.gmed.utils.ConstantsFile;
import com.gmed.utils.GmedConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * <h2>This class creates the required driver for the test, to run the test in a
 * selected browser. Supported Browsers are:</h2>
 * <p>
 * IE<br>
 * @author jyoti kalra
 * @version 1.0
 * @modified 1-02-2017
 */
public class DriverFactory {
	
	public static WebDriver webDriver 				= null;
	public static EventFiringWebDriver driver 		= null;
	public static Actions action 		= null;
	public static WebDriver webDriver1 				= null;
	public static EventFiringWebDriver driver1 		= null;	
	private static Logger logger                     = LogManager.getLogger(DriverFactory.class);
	/**
	 * This method is called by the test classes to create a driver
	 * @param url
	 */
	public static void getDriverInstance(String url) {
		createDriver(url);
		actionClass();
	}
	public static void openPatientPortalURL(String gmailUrl){
		//webDriver1.navigate().to(gmailUrl);
		webDriver1.get(gmailUrl);
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void getAnotherDriverInstance(String url){
		createAnotherDriver(url);
	}
	/** This is the Factory Method used for creating appropriate web driver.
	 * @param URL(Develop/Release/Enhance)
	 * @return driver
	 */
	
	public static EventFiringWebDriver createDriver(String URL )
	{
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability("nativeEvents", false);   
		ieCapabilities.setCapability("ignoreZoomSetting", true);
		ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
		ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
		ieCapabilities.setCapability("disable-popup-blocking", true);
		ieCapabilities.setCapability("enablePersistentHover", true);
		ieCapabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, URL);
		File file = new File("src/test/Resources/IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		webDriver = new InternetExplorerDriver(ieCapabilities);
		return driver = new EventFiringWebDriver(webDriver); 
		
	}
	public static void actionClass(){
		action = new Actions(driver);
	}
	public static EventFiringWebDriver createAnotherDriver(String anotherurl) {
		
		//DesiredCapabilities ieCapabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-extensions");
		options.addArguments("disable-infobars");
	    options.addArguments("--start-maximized");
	    options.addArguments("--disable-web-security");
	    options.addArguments("--no-proxy-server");
	    Map<String, Object> prefs = new HashMap<String, Object>();
	    prefs.put("credentials_enable_service", false);
	    prefs.put("profile.password_manager_enabled", false);
	    options.setExperimentalOption("prefs", prefs);
		File file = new File("src/test/Resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		webDriver1 = new ChromeDriver(options);
		return driver1 = new EventFiringWebDriver(webDriver1);		
	}
	/**
	 * Open the Gmed URL and Maximize the Window
	 * @param url- Gmed URL's(Develop/Release/Enhance)
	 */
	public static void openURL(String url) {
		webDriver.get(url);
		driver.manage().window().maximize();
}
	public static void openGpinURL(String gpinUrl){
		         webDriver1.get(gpinUrl);
				driver1.manage().window().maximize();
				driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	/**
	 * This method takes the screenshot of the current browser instance
	 * 
	 * @param testName
	 */
	public static void takeScreenShot(String currTestName,String browserName) {
		
		String testName = addDate(currTestName);
		@SuppressWarnings("deprecation")
		String outputDIR = TestNG.getDefault().getOutputDirectory();
		 ConstantsFile.newFileNamePath = outputDIR + "\\ScreenShot\\" + currTestName + "\\" + testName + ".jpg";
		if ("true".equals(GmedConfig.getConfig("TAKE_SCREENSHOTS")) && browserName.equalsIgnoreCase("ie")) {

			final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {

				FileUtils.copyFile(scrFile, new File( ConstantsFile.newFileNamePath));
				logger.info("The Screenshot is saved for- " + testName);

			} catch (IOException e) {
				logger.error("The File is not found, in the takeScreenShot method of the driverFactory...");
				e.printStackTrace();
			}
		}
		else{
			
			final File scrFile = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File( ConstantsFile.newFileNamePath));
				logger.info("The Screenshot is saved for- " + testName);

			} catch (IOException e) {
				logger.error("The File is not found, in the takeScreenShot method of the driverFactory...");
				e.printStackTrace();
			}
		}

	}
	/**
	 * This method appends current data parameter to the current test name for
	 * naming the screenshot
	 * 
	 * @param currTestName
	 * @return String with the testname and date-time appended
	 */
	public static String addDate(final String currTestName) {
		Date myDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd-HHmmss");
		return currTestName + "-" + sdf.format(myDate);
	}
	
	/**
	 * This method takes the screenshot of the current browser instance
	 * @param currTestName The name of the test method being executed
	 * @param tr the test result of the test method
	 * @return
	 */
	public static String takeScreenShot( ITestResult tr ) {
		
		
		String tm= tr.getMethod().getMethodName();
		
		String currTestName = addDate(tm);
		
		@SuppressWarnings("deprecation")
		String outputDIR = TestNG.getDefault().getOutputDirectory();
		final String newFileNamePath = outputDIR + "\\ScreenShot\\" + tm + "\\"	+ currTestName + ".jpg";

		final File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(newFileNamePath));
			logger.info("The Screenshot is saved for- " + currTestName);
		} catch (IOException e) {
			logger.error("The File is not found, in the takeScreenShot method of the driverFactory...");
			e.printStackTrace();
		}
		
		return newFileNamePath;
	}
}
