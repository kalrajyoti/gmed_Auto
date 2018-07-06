package com.gmed.test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.AutoIT.GpinUserLogin;
import com.gmed.pages.AppointmentPage;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
//import com.gmed.pages.JavaMail1;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.PatientChart;
import com.gmed.patientportal.JavaMail;
import com.gmed.patientportal.PatientPortalLogin;
import com.gmed.utils.ConstantsFile;
import com.gpm.pages.BillingPage;
public class PatientPortalTestClass extends BaseTestClass1{
	private static Logger logger  = LogManager.getLogger(PatientPortalTestClass.class);
	private LoginPage loginPageObj;
	private GpinUserLogin gpinpageobj;
	private MedicalChartPage medicalchartobj;
	private PatientPortalLogin patientLogin;
	private JavaMail javamail;
	private PatientChart patientchartobj;
	private Assertion patientPortalAssert = new Assertion();
     private DocumentPage documentpageobj;
     private BillingPage billingobj;
	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		loginPageObj= new LoginPage();
		gpinpageobj = new GpinUserLogin();
		patientLogin= new PatientPortalLogin();
		javamail =new JavaMail();
		medicalchartobj = new MedicalChartPage();
		patientchartobj = new PatientChart();
		leftPanelpageobj = new LeftPanelPage();
		documentpageobj  = new DocumentPage();
		billingobj = new BillingPage();
	}
	@Test(description = "To verify patient is able to register in the patient portal ",groups = { "init" },priority=1)
	public void registerPatient() throws Exception{
		loginPageObj.loginToGmedForPatientPortal(ConstantsFile.loginData);
		patientPortalAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		medicalchartobj.createNewPatientWithoutInsurance();
		medicalchartobj.savePatientDetails();
		patientPortalAssert.assertEquals(patientchartobj.verifyPatientIsCreated(), true);
		medicalchartobj.switchToMedicalFrame();
		medicalchartobj.selectPatient();
		leftPanelpageobj.clickLeftNavigationPanelValue("Demographics");
		patientchartobj.enterValidEmailId();
		medicalchartobj.clickOnSaveButton();
		patientchartobj.clickOnPortalTab();
		patientPortalAssert.assertEquals(patientchartobj.verifySendPortalButtonIsEnabled(), true);
		patientchartobj.clickOnSendInvitation();
		documentpageobj.clickOnOkay();
		patientLogin.registerPatient(javamail.readmail());
		patientLogin.clickOnHome();
		patientPortalAssert.assertEquals(patientLogin.verifyHomePageIsLoaded(), true);
		leftPanelpageobj.clickOnLogout();
		patientLogin.clickOnLogOff();		

	}
	@Test(description = "To verify user is able to send message from patient portal & verify Inbound queue is present in gGastro",groups = { "init" },priority=2)
	public void createMessage() throws Exception{
		patientLogin.loginToPatientPortal();
		patientPortalAssert.assertEquals(patientLogin.verifyHomePageIsLoaded(), true);
		patientLogin.clickOnSendMessage();
		patientPortalAssert.assertEquals(patientLogin.verifyClinicalIsSelected(), true);	
		patientLogin.selectLocation();
		patientLogin.selectTestSubject();
		patientLogin.clickOnSendMessageButton();
		patientLogin.selectMessageFromLeftNavigation();
		patientLogin.clickOnSentMessageLeftNavigation();
		patientPortalAssert.assertEquals(patientLogin.verifySentMessageQueue(), true);	
		loginPageObj.loginToGmedForPatientPortal(ConstantsFile.loginData);
		leftPanelpageobj.clickOnQueueManagement("clickOnPatientPortal");
		patientLogin.switchToPatientPortalFrame();
		patientLogin.selectPatientInGmed();
		patientLogin.switchToPatientPortalFrame();
		billingobj.clickOnSearchButton();
		patientPortalAssert.assertEquals(patientLogin.verifyInboundQueueInPatientPortal(), true);
		patientLogin.clickOnLogOff();
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is able to Create Update Request from patient portal & verify Inbound queue under update request folder is present in gGastro",groups = { "patient_Portal" },dependsOnMethods = { "registerPatient" },priority=3)
	public void updateRequest() throws Exception{
		patientLogin.loginToPatientPortal();
		patientPortalAssert.assertEquals(patientLogin.verifyHomePageIsLoaded(), true);
		patientLogin.clickOnUpdateInformation();
		patientPortalAssert.assertEquals(patientLogin.verifyUpdateClinicalInformationPage(), true);
		patientLogin.selectAnyRaceCheckbox();
		patientLogin.selectEthnicityRadioButton();
		patientLogin.clickOnSendTopButton();
		patientPortalAssert.assertEquals(patientLogin.verifyHomePageIsLoaded(), true);
		loginPageObj.loginToGmedWithExistingUser(ConstantsFile.loginData);
		leftPanelpageobj.clickOnQueueManagement("clickOnPatientPortal");
		patientLogin.switchToPatientPortalFrame();
		patientLogin.selectInBoundFromUpdateFolder("Inbound");
		patientLogin.switchToPatientPortalFrame();
		patientLogin.selectPatientInGmed();
		patientLogin.switchToPatientPortalFrame();
		billingobj.clickOnSearchButton();
		patientPortalAssert.assertEquals(patientLogin.verifyInboundQueueInUpdateRequest(), true);
		patientLogin.clickOnAcceptAll();
		patientLogin.selectInBoundFromUpdateFolder("Processed");
		patientLogin.switchToPatientPortalFrame();
		patientLogin.selectPatientInGmed();
		patientLogin.switchToPatientPortalFrame();
		billingobj.clickOnSearchButton();
		patientPortalAssert.assertEquals(patientLogin.verifyInboundQueueInUpdateRequest(), true);
		patientLogin.clickOnLogOff();
		leftPanelpageobj.clickOnLogout();
	}


}
