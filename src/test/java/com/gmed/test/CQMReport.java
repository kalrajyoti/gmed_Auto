package com.gmed.test;

import static com.gmed.utils.StringUtility.stringContainsCsv;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.Profile;
import com.gmed.pages.ReportPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;

public class CQMReport extends BaseTestClass {
		/** Logger to log the MUReport log messages */
		private static Logger logger  = LogManager.getLogger(CQMReport.class); 
		/**Assertion to verify different elements of the page */
		private Assertion cqmAssert = new Assertion();
		/** Login Page reference used to login for logging in for test  */
		private LoginPage loginPageObj;
		
		/** Report page reference used for generating report data in report  module*/
		private ReportPage reportpageobj;
		/** Document page reference used for generating report data in report  module*/
		private DocumentPage docupageobj;
	
		
		/**contains the MU page data*/
		public static Map<String, String> CQMData;
		
		/**These are the variables which are used to store different data for MU Report module*/
		public static String existingProfileProviderfirstname;
		public static String existingProfileProviderlastname;
		public static String cqmMeasures;
		public static String destinationFolder;
		
		/** These are the variables which are present on "MUReport" sheet in the excel*/
		public static final String PROVIDER_FIRSTNAME 				                   = "providerfirstname";
		public static final String PROVIDER_LASTNAME 				                   = "providerlastname";
		public static final String CQM_MEASURES 				                       = "cqmMeasures";
		public static final String DESTINATION_FOLDER 				                   = "destinationfolder";
	
		
		@BeforeClass
		public void initClass() throws Exception{
			logger.info("inside the initClass method for DemographicsTest test class....");
			CQMData                                     = ExcelFileUtilty.readExcelSheet("Report");
			existingProfileProviderfirstname            = CQMData.get(PROVIDER_FIRSTNAME);
			existingProfileProviderlastname             = CQMData.get(PROVIDER_LASTNAME);
			cqmMeasures                                 = CQMData.get(CQM_MEASURES);
			destinationFolder                           = CQMData.get(DESTINATION_FOLDER);
			loginPageObj                                = new LoginPage();
			leftPanelpageobj                            = new LeftPanelPage();
			reportpageobj                               = new ReportPage();
			docupageobj                                 = new DocumentPage();
			
			

	}
		@Test(description = "To verify that  CQM report can run successfully and verify corrects fields are displayed on CQM Report Screen. ",groups = { "CQMReport_Regression" },priority=1)
		public void verifyCQMReport() throws Exception{
			logger.info("logging into gmed application...");
			loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
			cqmAssert.assertTrue(loginPageObj.verifyHomePageTitle());
			logger.info("clicking on Report menu & Select CQM Report present in left panel...");
			leftPanelpageobj.clickOnReport("clickOnCQM");
			logger.info("verify CQM report Page is displayed");
			reportpageobj.switchToCQMFrame();
			cqmAssert.assertTrue(reportpageobj.verifyReportScreen("Clinical Quality Measures"));
			logger.info("select provider ,desination & date range in the cqm report");
			reportpageobj.switchToCQMFrame();
			logger.info("adding report details for generationg data..");
			reportpageobj.addReportDetails(existingProfileProviderfirstname,existingProfileProviderlastname);
			docupageobj.clickOnOkay();
			logger.info("verify all the results show for selected  measures for selected provider.");
			cqmAssert.assertTrue(stringContainsCsv(reportpageobj.verifyCQMData(), cqmMeasures));
			logger.info("logging out from the application");
			leftPanelpageobj.clickOnLogout();
		}
		@AfterClass()
		public void classTearDown() {
			loginPageObj           = null;
			leftPanelpageobj       = null;
			reportpageobj          = null;
			docupageobj            = null;
			logger.info("Exiting the classTearDown method for Document test class \n\n");
		}
}
