package com.gmed.base;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.getAnotherDriverInstance;
import static com.gmed.helper.DriverFactory.getDriverInstance;
import static com.gmed.helper.DriverFactory.openGpinURL;
import static com.gmed.helper.DriverFactory.openPatientPortalURL;
import static com.gmed.helper.DriverFactory.openURL;

import java.lang.reflect.Method;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.gmed.helper.DriverFactory;
import com.gmed.pages.LeftPanelPage;

import com.gmed.utils.ConstantsFile;
import com.gmed.utils.GmedConfig;

public class BaseTestClass extends BaseAbstractPage{
	/** Logger to log the BaseTestClass log messages */
	private static Logger logger = LogManager.getLogger(BaseTestClass.class);
	public static String testName;
	protected LeftPanelPage leftPanelpageobj=null;

	/**
	 * The  method will be run only once before all tests in this suite have run.
	 */


	@BeforeSuite
	@Parameters({ "GPINURL" })
	public void initBeforeSuite(String parmvalue) {
		logger.info("initialisation of Gmed Environment...");
		String url =GmedConfig.getConfig("gGastroURL");
		System.out.println(url);
		getDriverInstance(url);
		openURL(url);
		if(parmvalue.equalsIgnoreCase("patientPortalSignUp")){
		logger.info("initialisation of "+ parmvalue + " Environment...");
		String patientPortalUrl =GmedConfig.getConfig("patientPortalSignUp");
		System.out.println(patientPortalUrl);
		getAnotherDriverInstance(patientPortalUrl);
		openPatientPortalURL(patientPortalUrl);
		}
		else{
		logger.info("initialisation of "+ parmvalue + " Environment...");
		/*String gPin =GmedConfig.getConfig(parmvalue);
	    getAnotherDriverInstance(gPin);
	    openGpinURL(gPin);
	    GpinUserLogin.logintoGpin();*/	
	}

	}
	@BeforeMethod
	@Parameters({ "GGASTROLLOGIN" })
	public static void init(Method method,String urlValue) {
		logger.info("\n Inside the init() method ");
		testName = method.getName();
		String url = "https://gmedstaging64.gmed.com/B630/Gcare/";
		String portalUrl =GmedConfig.getConfig(urlValue);
		System.out.println(portalUrl);
		if(!(ConstantsFile.isTestPass) && portalUrl.equalsIgnoreCase("https://gmedstaging64.gmed.com/B630/Gcare/")){
			getDriverInstance(portalUrl);
			openURL(portalUrl);
			Actions action = new Actions(driver);
			ConstantsFile.isTestPass=true;
		}
		else if (!(ConstantsFile.isTestPass) && urlValue.equalsIgnoreCase("https://demo.mygportal.com")){
			getAnotherDriverInstance(urlValue);
			openGpinURL(urlValue);
		}
		logger.info("Exiting the init() method ");
	}

	@AfterMethod
	@Parameters({ "BROWSERSCREENSHOT" })
	public void afterEachTest(ITestResult result,String parmvalue) throws Exception {
		String testNamevalue = result.getTestClass().getRealClass().getName() + "." + result.getMethod().getMethodName(); 
		leftPanelpageobj.handleException(testNamevalue,result);
		if (parmvalue.equalsIgnoreCase("ie") && (result.getStatus() == ITestResult.SKIP || result.getStatus() == ITestResult.FAILURE)) {
			Logger log = LogManager.getLogger(result.getTestClass().getRealClass());
			DriverFactory.takeScreenShot(testNamevalue,"ie");
			ConstantsFile.isTestPass = false;
			log.error("Test Failed: " + testNamevalue);
			log.info("Screenshot: " + ConstantsFile.newFileNamePath);
			log.info("Screenshot if any exception is present " + ConstantsFile.newFileNamePathForException);
			driver.quit();
			Optional.ofNullable(result.getThrowable()).ifPresent(t -> {
				log.error("Exception Thrown: ", t);
			});
		}
		else if(parmvalue.equalsIgnoreCase("chrome")   &&  (result.getStatus() == ITestResult.SKIP || result.getStatus() == ITestResult.FAILURE)){
			Logger log = LogManager.getLogger(result.getTestClass().getRealClass());
			DriverFactory.takeScreenShot(testNamevalue,"chrome");
			ConstantsFile.isTestPass = false;
			log.error("Test Failed: " + testNamevalue);
			log.info("Screenshot: " + ConstantsFile.newFileNamePath);
			driver.close(); 
			driver.quit();
			Optional.ofNullable(result.getThrowable()).ifPresent(t -> {
				log.error("Exception Thrown: ", t);
			});
		}
	}

	/**
	 * The  method will be run only once after all tests in this suite have run.
	 */
	@AfterSuite
	@Parameters({ "CleanupSuite" })
	public void cleanupSuite(String browserUrl) 
	{
		if(browserUrl.equalsIgnoreCase("gGastro")){
			driver.quit();
			//driver1.quit();
		}
		else{
			//driver1.quit();
		}
	}


}
