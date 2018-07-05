package com.gmed.test;

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
import com.gmed.pages.ReportPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;

public class MIPSReport extends BaseTestClass {
	/** Logger to log the MUReport log messages */
	private static Logger logger  = LogManager.getLogger(MIPSReport.class); 
	/**Assertion to verify different elements of the page */
	private Assertion mipsAssert = new Assertion();
	/** Login Page reference used to login for logging in for test  */
	private LoginPage loginPageObj;

	/** Report page reference used for generating report data in report  module*/
	private ReportPage reportpageobj;
	/** Document page reference used for generating report data in report  module*/
	private DocumentPage docupageobj;

	/**contains the MU page data*/
	public static Map<String, String> MIPSData;

	/**These are the variables which are used to store different data for MU Report module*/
	public static String Providerfirstname;
	public static String Providerlastname;
	public static String mipsReportName;
	public static String messageRecipientsName;

	/** These are the variables which are present on "MUReport" sheet in the excel*/
	public static final String PROVIDER_FIRSTNAME 				                   = "providerfirstname";
	public static final String PROVIDER_LASTNAME 				                   = "providerlastname";
	public static final String MIPS_REPORT_NAME 				                   = "mipsReportName";
	public static final String MESSAGE_RECIPIENTS_NAME 				               = "messageRecipients";


	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for DemographicsTest test class....");
		MIPSData                                    = ExcelFileUtilty.readExcelSheet("Report");
		Providerfirstname                           = MIPSData.get(PROVIDER_FIRSTNAME);
		Providerlastname                            = MIPSData.get(PROVIDER_LASTNAME);
		mipsReportName                              = MIPSData.get(MIPS_REPORT_NAME);
		messageRecipientsName                       = MIPSData.get(MESSAGE_RECIPIENTS_NAME);
		loginPageObj                                = new LoginPage();
		leftPanelpageobj                            = new LeftPanelPage();
		reportpageobj                               = new ReportPage();
		docupageobj                                 = new DocumentPage();


	}
	@Test(description = "To verify that  MU report can run successfully and verify corrects fields are displayed on MU Report Screen. ",groups = { "MUReport_Regression" },priority=1)
	public void verifyMIPSReport() throws Exception{
		logger.info("logging into gmed application...");
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		mipsAssert.assertTrue(loginPageObj.verifyHomePageTitle());

		logger.info("clicking on Report menu & Select MIPS present in left panel...");
		leftPanelpageobj.clickOnReport("clickOnMIPS");
		logger.info("verify MIPS report Page is displayed");
		reportpageobj.switchToMipsFrame();
		mipsAssert.assertTrue(reportpageobj.verifyReportScreen("Merit-Based Incentive Payment System (MIPS)"));

		docupageobj.clickOnOkay();
		logger.info("clicking on New Button for creating New MIPS Report");
		reportpageobj.clickOnMipsToolBar("New");
		logger.info("Adding report Name for MIPS Report");
		reportpageobj.addReportName();
		logger.info("selecting the Provider for Report");
		reportpageobj.addProviderForReport(Providerfirstname,Providerlastname);
		logger.info("verify Reporting Year should be current Year");
		mipsAssert.assertTrue(reportpageobj.verifyReportingYear());
		logger.info("adding message Recipient for the MIPS Report");
		reportpageobj.addMessageRecipient();

		logger.info("Clicking on Quality Tab for MIPS");
		reportpageobj.clickOnMipsTab(" Quality ");
		reportpageobj.switchInQualityFrame();
		logger.info("Selecting all the measures in Quality Tab");	
		reportpageobj.selectMeasures();

		logger.info("Clicking on ACI Tab for MIPS");
		reportpageobj.clickOnMipsTab(" Advancing Care Information ");
		reportpageobj.switchIntoACIMeasureFrame();
		logger.info("Selecting all the measures in ACI Tab");
		reportpageobj.selectCheckBoxMeasures();

		logger.info("Clicking on Improvement Activities Tab for MIPS");
		reportpageobj.clickOnMipsTab(" Improvement Activities ");
		logger.info("Selecting some measures in IA Tab");
		reportpageobj.selectCheckBoxMeasures();

		logger.info("Saving the MIPS Report");
		reportpageobj.clickOnMipsToolBar("Save");
		docupageobj.clickOnOkay();

		logger.info("clicking on Calculate Now Button for generating the data");
		reportpageobj.clickOnMipsToolBar("Calculate Now");
		docupageobj.clickOnYesPopUp();
		logger.info("clicking on Refresh...");
		reportpageobj.clickOnRefresh();
		docupageobj.clickOnOkay();

		logger.info("clicking on Message menu  present in Queue Management...");
		leftPanelpageobj.clickOnQueueManagement("clickOnMessage");
		logger.info("Verify Automatic message is generated in Message queue");
		mipsAssert.assertTrue(reportpageobj.verifyAutoMaticMipsMessage());
		docupageobj.clickOnOkay();
		leftPanelpageobj.clickOnLogout();
	}
	@AfterClass()
	public void classTearDown() {
		loginPageObj           = null;
		leftPanelpageobj       = null;
		reportpageobj          = null;
		docupageobj            = null;
		logger.info("Exiting the classTearDown method for MIPS Report test class \n\n");
	}
}
