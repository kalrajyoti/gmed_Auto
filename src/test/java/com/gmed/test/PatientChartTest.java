package com.gmed.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import com.gmed.AutoIT.GpinUserLogin;
import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.pages.*;
import com.gmed.utils.ConstantsFile;
//import com.gmed.pages.BillingPage;
//import com.gmed.pages.MedicalChartPatientPage;
//import com.gmed.pages.ColonscopyservicePage;
//import com.gmed.pages.ConfigurationPage;
//import com.gmed.pages.Frames;
//import com.gmed.pages.GpinUserLogin;
//import com.gmed.pages.LoginPage;
//import com.gmed.pages.MedicalChartPatientPage;
import com.gmed.utils.SeleniumUtil;
import com.gpm.pages.AccountInquiryPage;
import com.gpm.pages.BillingPage;

public class PatientChartTest extends BaseTestClass1 {
		/** Logger to log the GpmSmokeTest log messages */
		private static Logger logger  = LogManager.getLogger(PatientChartTest.class); 
		private LoginPage loginPageObj;
		private MedicalChartPage medicalpatientpage;
		private Assertion patientchartassert = new Assertion();
		private AppointmentPage appointmentpageobj;
		//--
		private TaskPage taskpage;
		private DocumentPage documentpage;
		private ReportPage reportpageobj;
		
		
		@BeforeClass
		public void initClass() throws Exception {
			loginPageObj= new LoginPage();
			appointmentpageobj= new AppointmentPage();
			medicalpatientpage = new MedicalChartPage();
			//--
			taskpage = new  TaskPage();
			leftPanelpageobj = new LeftPanelPage();
			documentpage = new DocumentPage();
			reportpageobj = new ReportPage();
		 }
		
		@Test (description = "To verify functionality of New button available on the Patient Chart", priority = 3)
		public void verifyNewButton() throws Exception {
		
		    loginPageObj.loginToGmed();
			medicalpatientpage.searchPatientByName(ConstantsFile.patientchartpatientname);
			medicalpatientpage.dismissGuideline();
			
		    logger.info("Verifying if the colonoscopy can be created or not using new button on patient chart");
			logger.info("Clicking on Colonoscopy under the New Button");
		    medicalpatientpage.clickOnProcedure("Colonoscopy");
		    logger.info("Clicking on Indication Section in service document");
		    documentpage.clickOnIndicationInColonscopy();
		    logger.info("Clicking on Findings Section in service document");
		    documentpage.clickOnFindings();
		    logger.info("Closing the Service Document");
            documentpage.closeColonoscopyService();
            logger.info("Verifying if the Colonoscopy is created");
            patientchartassert.assertEquals(documentpage.verifyColonoscopycreated(), true);
            
            logger.info("Verifying ChartNote is working from from the New button");
            logger.info("Clicking on Chart Item under the new button");
            medicalpatientpage.clickOnMenuItem(MedicalChartPage.mnuNewChartNote);
            logger.info("Entering the Chart Title");
            medicalpatientpage.enterChartTitle();
            logger.info("Verifying if the chart note is created");
         	patientchartassert.assertEquals(medicalpatientpage.verifyChartNotecreated(ConstantsFile.charttitle), true);
    	
            logger.info("Verfiying new task can be created from the New button"); 
            logger.info("Clicking on the Task under new button");
            medicalpatientpage.clickOnMenuItem(TaskPage.TaskMenu);
            logger.info("Clicking on the subject and entering subject");
            taskpage.clickOnSubject();
            logger.info("Clicking on the Self Link");
            taskpage.clickOnSelfLink();
            logger.info("Clicking on the Send Button");
            taskpage.clickOnSendbutton();
            patientchartassert.assertEquals(taskpage.verifyTaskSubjectInTaskSection1(), true); 
	        
            logger.info("Verifying that portal communication is working fine from new button");
            logger.info("Switching to FraPatient Frame");
            medicalpatientpage.switchToFraPatientFrame();
            logger.info("Clicking on the Portal Message under New Button");
            medicalpatientpage.clickOnMenuItem(MedicalChartPage.mnuNewPatientPortal);
            logger.info("Entering the Mail Subject");
            medicalpatientpage.enterMailSubject();
            logger.info("Switching to Top Frame");
            medicalpatientpage.toTopFrame();
            logger.info("Clicking on the Portal Communications on Left Panel");
            leftPanelpageobj.clickLeftNavigationPanelValue("Portal Communications");
            logger.info("Verifying Portal Message is created");
    	    patientchartassert.assertEquals(appointmentpageobj.verifyPortalMessageCreated(ConstantsFile.mailsubject), true);
    	    medicalpatientpage.toTopFrame();
    	    logger.info("Clicking on LogOut");
    	    leftPanelpageobj.clickOnLogout();
    		
}
		

		
		@Test (description = "To verify Chart View functionality from Patient Chart", priority = 2)
		    public void verifyChartView() throws InterruptedException {
			logger.info("Logging into Gmed");
			loginPageObj.loginToGmed();
			logger.info("Searching Patient");
			medicalpatientpage.searchPatientByName(ConstantsFile.patientchartpatientname);
			logger.info("Clicking on ChartView on the Top Right");
			medicalpatientpage.clickOnChartViewNewOption();
			logger.info("Enteering the Chart Name");
            medicalpatientpage.enterChartName();
            logger.info("Switching to Fra Frame");
            medicalpatientpage.toFraFrame();
            logger.info("Clicking on the Chart View");
            medicalpatientpage.clickOnChartView();
            medicalpatientpage.toPopUpFrame();
            medicalpatientpage.toFraFrame();
            logger.info("Clicking on the Chart View");
            medicalpatientpage.clickOnChartView();
            logger.info("Clicking on ChartWithName from the ChartView option");
            medicalpatientpage.clickOnChartWithName(ConstantsFile.chartname);
            logger.info("Verifying Chart View is created");
            patientchartassert.assertEquals(medicalpatientpage.verifyChartView("Admin Patient Letter",false), true);
            logger.info("Clicking on the Chart View");
            medicalpatientpage.clickOnChartView();
            medicalpatientpage.clickOnChartWithName("System default");
            logger.info("Verifying Chart View is swicthed back to System Default");
            patientchartassert.assertEquals(medicalpatientpage.verifyChartView("Admin Patient Letter",true), true);
            logger.info("Logging Out");
            leftPanelpageobj.clickOnLogout();
    		
    		}
		
		@Test(description = "To verify that patient by age Report for demographics Module ",priority= 1)
		public void verifyChartAccessAuditReport() throws Exception{
			logger.info("logging into gmed application...");
			loginPageObj.loginToGmed();
			patientchartassert.assertEquals(loginPageObj.verifyHomePageIsLoaded(), true);
			logger.info("Clicking On Report Button...");
			leftPanelpageobj.clickOnReport("clickOnReport");
			logger.info("Clicking On Chart Access Audit Report");
			reportpageobj.clickOnPatientByAgeReport("Chart Access Audit Report Chart Access Audit Report");
			logger.info("verifying On Chart Access Audit Report page is displayed.");
			patientchartassert.assertEquals(reportpageobj.verifyReportPageIsDisplayed("Reports: Chart Access Audit Report"), true);
			logger.info("verifying Top Menu Items in Chart Access Audit Report");
			patientchartassert.assertEquals(reportpageobj.verifyTopMenuItemsInPatientAgeReport(), true);
			logger.info("Clicking on + icon");
			reportpageobj.clickOnPatientAddIcon();
			logger.info("Searching Patient to run the report");
			reportpageobj.searchPatientForReportRun();
			logger.info("verify On Chart Access Audit Report is displayed for patient.");
			patientchartassert.assertEquals(reportpageobj.verifyChartAccessAuditReport(), true);
			logger.info("Clicking on Next and Back button");
			reportpageobj.clickOnNextAndBackButtonInPatientAgeReport();
			logger.info("Clicking on Collapsed and Expand button");
			reportpageobj.clickOnCollspedAndExpandButton();
			logger.info("Clicking on Export button");
			reportpageobj.clickOnExportButton();
			logger.info("Clicking on Logout");
			leftPanelpageobj.clickOnLogout();
		}
		
		@AfterClass()
		public void classTearDown(){
			medicalpatientpage              = null;
			loginPageObj                    = null;
			leftPanelpageobj                = null;
			documentpage                    = null;
			appointmentpageobj              = null;
			taskpage                        = null;
			reportpageobj                   = null;
			logger.info("Exiting the classTearDown method for Patient chart test class \n\n");
		}


}
