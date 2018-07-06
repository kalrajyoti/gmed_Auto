package com.gmed.test;

import static com.gmed.utils.StringUtility.stringContainsCsv;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.base.BaseTestClass;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.ReportPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;

public class MUReport extends BaseTestClass {
		/** Logger to log the MUReport log messages */
		private static Logger logger  = LogManager.getLogger(MUReport.class); 
		/**Assertion to verify different elements of the page */
		private Assertion muAssert = new Assertion();
		/** Login Page reference used to login for logging in for test  */
		private LoginPage loginPageObj;
		
		/** Report page reference used for generating report data in report  module*/
		private ReportPage reportpageobj;
		
		/**contains the MU page data*/
		public static Map<String, String> MUData;
		
		/**These are the variables which are used to store different data for MU Report module*/
//		public static String existingProfileProviderfirstname;
//		public static String existingProfileProviderlastname;
//		public static String muMeasures;
//		
//		/** These are the variables which are present on "MUReport" sheet in the excel*/
//		public static final String PROFILE_PROVIDER_FIRSTNAME 				                   = "providerfirstname";
//		public static final String PROFILE_PROVIDER_LASTNAME 				                   = "providerlastname";
//		public static final String MU_MEASURES 				                                   = "muMeasures";
	
		
		@BeforeClass
		public void initClass() throws Exception{
			logger.info("inside the initClass method for DemographicsTest test class....");
			MUData                                      = ExcelFileUtilty.readExcelSheet("Report");
//			existingProfileProviderfirstname            = MUData.get(PROFILE_PROVIDER_FIRSTNAME);
//			existingProfileProviderlastname             = MUData.get(PROFILE_PROVIDER_LASTNAME);
//			muMeasures                                  = MUData.get(MU_MEASURES);
			loginPageObj                                = new LoginPage();
			leftPanelpageobj                            = new LeftPanelPage();
			reportpageobj                               = new ReportPage();
			reportpageobj.initClass();
			

	}
		@Test(description = "To verify that  MU report can run successfully and verify corrects fields are displayed on MU Report Screen. ",groups = { "MUReport_Regression" },priority=1)
		public void verifyMUReport() throws Exception{
			logger.info("logging into gmed application...");
			loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
			muAssert.assertTrue(loginPageObj.verifyHomePageTitle());
			logger.info("clicking on Report menu & Select Measure Calculation present in left panel...");
			leftPanelpageobj.clickOnReport("clickOnMU");
			logger.info("verify MU report Page is displayed");
			reportpageobj.switchToMuFrame();
			muAssert.assertTrue(reportpageobj.verifyReportScreen("Measure Calculations"));
			logger.info("verify all the fields related to MU report Page is displayed");
			muAssert.assertTrue(reportpageobj.verifyMUReportFields());
			reportpageobj.enterMUDataForReport(ReportPage.existingProfileProviderfirstname,ReportPage.existingProfileProviderlastname);
			muAssert.assertTrue(stringContainsCsv(reportpageobj.verifyMUData(), ReportPage.muMeasures));
			leftPanelpageobj.clickOnLogout();
		}
		@AfterClass()
		public void classTearDown() {
			loginPageObj           = null;
			leftPanelpageobj       = null;
			reportpageobj          = null;
			logger.info("Exiting the classTearDown method for Document test class \n\n");
		}
}
