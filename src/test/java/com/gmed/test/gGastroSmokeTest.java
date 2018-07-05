package com.gmed.test;





import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.AutoIT.GpinUserLogin;
import com.gmed.Frames.DynamicFramePage;
import com.gmed.helper.DriverFactory;
import com.gmed.pages.AppointmentPage;
import com.gmed.pages.ConfigurationPage;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.FaxingPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.OrdersPage;
import com.gmed.pages.OutputManagerPage;
import com.gmed.pages.PatientChart;
import com.gmed.pages.PatientInterviewForm;
import com.gmed.pages.PrescriptionPage;
import com.gmed.pages.Profile;
import com.gmed.pages.ReportPage;
import com.gmed.pages.ScanningPage;
import com.gmed.pages.TaskPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.GmedConfig;


public class gGastroSmokeTest extends BaseTestClass {
	/** Logger to log the gGastroSmokeTest log messages */
	private static Logger logger  = LogManager.getLogger(gGastroSmokeTest.class);
	private Assertion appontmentAssert = new Assertion();
	private Assertion queueAssert = new Assertion();
	private LoginPage loginPageObj;
	private PatientChart patientchartobj;
	private AppointmentPage appointmentpageobj;
	private PatientInterviewForm pifobj;
	private DynamicFramePage dynamicframeobj;
	private TaskPage taskobj;
	private OrdersPage orderobj;
	private ScanningPage scanningobj;
	private FaxingPage faxobj;
	private ReportPage reportobj;
	private DocumentPage documentobj;
	private OutputManagerPage outputManagerobj;
	//private MedicalChartPatientPage medicalpatientpage;
	//private AppointmentPage appointmentpageobj;
	private MedicalChartPage medicalchartpage;
	//private Queue_Management queue_management;
	private ConfigurationPage configpageobj;
	private Profile profileobj;
	private PrescriptionPage prescriptionobj;
	private GpinUserLogin gpinpageobj;
	private Profile profilepageobj;
	//private ColonscopyservicePage colscopypageobj;
	

	/** This method runs before the first test from the class runs */
	@BeforeClass
	public void initClass() throws Exception{
		loginPageObj= new LoginPage();
		//appointmentpageobj= new AppointmentPage();
		leftPanelpageobj = new LeftPanelPage();
		profileobj = new Profile();
		prescriptionobj = new PrescriptionPage();
		patientchartobj = new PatientChart();
		appointmentpageobj = new AppointmentPage();
		pifobj = new PatientInterviewForm();
		dynamicframeobj =new DynamicFramePage();
		taskobj = new TaskPage();
		orderobj = new OrdersPage();
		scanningobj = new ScanningPage();
		faxobj = new FaxingPage();
		documentobj=new DocumentPage();
		//medicalpatientpage = new MedicalChartPatientPage();
		medicalchartpage = new MedicalChartPage();
		gpinpageobj = new GpinUserLogin();
		//queue_management= new Queue_Management();
		configpageobj =new ConfigurationPage();
		reportobj = new ReportPage();
		outputManagerobj = new OutputManagerPage();
		profilepageobj = new Profile();
		//colscopypageobj =new ColonscopyservicePage();
			//String gPin =GmedConfig.getConfig("gPinURL");
			//getAnotherDriverInstance(gPin);
		    //openGpinURL(gPin);
		    //GpinUserLogin.logintoGpin();	
		    //Thread.sleep(7000);	
	}
	
	static boolean createuser=false;
	@Test(description = "To verify user is able to login as gPin user  ",groups = { "gGastro_smoke" },priority=1)
	public void loginToGPin() throws Exception{
		logger.info("Getting gpin Username & password...");
		appontmentAssert.assertEquals(GpinUserLogin.verifygPinCredinaials(), true);
		//appontmentAssert.assertEquals(GpinUserLogin.verifygPinCredinaials(), true);
		logger.info("Getting gpin Page Title...");
		createuser=gpinpageobj.verifygPinPage();
		appontmentAssert.assertEquals(createuser,true);
		logger.info("Logging into the application...");
		loginPageObj.loginToGmedWithGpin(ConstantsFile.loginData);
		appontmentAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		logger.info("creating logging detaits for user...");
		configpageobj.enterLoginDetailsForUserCreation();
		logger.info("creating system admin role  for user...");
		configpageobj.createNewRole();
		configpageobj.selectCreatedRole();
		appontmentAssert.assertEquals(configpageobj.verfiyRoleList(), true);
		logger.info("creating new Location for user...");
		configpageobj.createNewLocation();
		configpageobj.selectLocation();
		appontmentAssert.assertEquals(configpageobj.verifyLocationPopup(), true);
		logger.info("click on save button...");
		loginPageObj.clickOnSaveButton();
		appontmentAssert.assertEquals(configpageobj.verifyUserIsCreated(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify creation of new user with sys admin role ",groups = { "gGastro_smoke" },dependsOnMethods =  { "loginToGPin" },priority=2)
	public void createUser(){
		appontmentAssert.assertEquals(createuser, true);
	}
	@Test(description = "To verify user is able to create a new provider ",groups = { "gGastro_smoke" },dependsOnMethods = { "loginToGPin" },priority=3)
	public void createProvider() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		logger.info("creating ne provider for the user...");
		configpageobj.createNewProviderandAssociate();
		configpageobj.selectSignatureForProvider();
		GpinUserLogin.openProvider();
		loginPageObj.selectSavebutton();
		appontmentAssert.assertEquals(configpageobj.verifyUserIsMappedWithProvider(), true);
		configpageobj.clickOnSaveForProvider();
		//appontmentAssert.assertEquals(loginPageObj.verifyProviderIsCreated(), true);
		leftPanelpageobj.clickOnLogout();
	}

	@Test(description = "To verify user is able to create a test Patient  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin" },priority=4)
	public void createTestPatientFromMedChart() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		MedicalChartPage.createNewPatient();
		medicalchartpage.savePatientDetails();
		appontmentAssert.assertEquals(medicalchartpage.verifyPatientIsCreated(), true);
		leftPanelpageobj.clickOnLogout();
	}
    @Test(description = "To verify user is able to Add Problem/Diagnosis in profile section  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=5)
	public void addDiagnosesSectionInProfile() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		profileobj.clickOnDiagnosesSectionInProfile();
		appontmentAssert.assertEquals(profileobj.verifyDiagnosesPanelListIsOpened(), true);	
		profileobj.savePopupDetails();
		appontmentAssert.assertEquals(profileobj.verifyProblemIsDocumented(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is able to Add Current Medications in profile section  ",groups ={"gGastro_smoke"},dependsOnMethods = {"loginToGPin","createTestPatientFromMedChart" },priority=6)
	public void addCurrentMedicationsInProfile() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		appontmentAssert.assertEquals(prescriptionobj.verifyCurrentMedicationPopup(), true);
		appontmentAssert.assertEquals(prescriptionobj.verifyPrescriptionDetailsPopup(), true);
		prescriptionobj.createRequireDetailsInMedication("Keflex");
		prescriptionobj.selectMethod("clickOnHandwritten");
		appontmentAssert.assertEquals(prescriptionobj.verifyMedicationIsDocumented(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is able to Add  Allergies in profile section  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=7)
	public void addAllergiesvalueInProfile() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		appontmentAssert.assertEquals(profileobj.verifyAllergyUserList(), true);
		appontmentAssert.assertEquals(profileobj.verifyAllergiesPopDetails(), true);
		appontmentAssert.assertEquals(profileobj.verifyAllergiesIsDocumented(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is able to Add Condition Details in profile section  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=8)
	public void addConditionsInProfile() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		appontmentAssert.assertEquals(profileobj.verifyCondtionsUserList(), true);
		appontmentAssert.assertEquals(profileobj.verifyCondtionsPopDetails(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is able to Add Immunizations in profile section  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart"},priority=9)
	public void addImmunizationsInProfile() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		appontmentAssert.assertEquals(profileobj.verifyImmunizationsUserList(), true);
		appontmentAssert.assertEquals(profileobj.verifyImmunizationsPopupDetails(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is able to Add DX Studies in profile section  ",groups ={"gGastro_smoke"},dependsOnMethods = {"loginToGPin","createTestPatientFromMedChart"},priority=10)
	public void addDxStudiesInProfile() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		appontmentAssert.assertEquals(profileobj.verifyDxStudiesUserList(), true);
		appontmentAssert.assertEquals(profileobj.verifyDxStudiesPopupDetails(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is able to Add Current Medications in profile section  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=11)
	public void addProceduresInProfile() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		appontmentAssert.assertEquals(profileobj.verifyProceduresUserList(), true);
		profileobj.switchToTooltip();
		appontmentAssert.assertEquals(profileobj.verifyProceduresPopupDetails(), true);
		leftPanelpageobj.clickOnLogout();
	}

	@Test(description = "To verify that loggedin user is able to create new appointment for the Colonoscopy",groups ={"gGastro_smoke"},dependsOnMethods = {"loginToGPin","createTestPatientFromMedChart" },priority=12)
	public void createAppointmentForColonoscopy() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		configpageobj.clickOnActvities();
		configpageobj.clickOnnewButtonOnActivitiesPage();
		configpageobj.createNewColonscopyActivity();
		configpageobj.selectLocationInSchuduler();
		configpageobj.clickOnSaveButton();
		configpageobj.clickOnTemplate();
		configpageobj.configTemplateForColonscopy();
		configpageobj.configPreProcedureNursingNoteForColonscopy();
		appointmentpageobj.clickOnScheduler();
		appontmentAssert.assertEquals(appointmentpageobj.verifySchdulerModuleIsOpened(), true);
		appointmentpageobj.clickOnNewButton();
		//ConstantsFile.appointmentData=ExcelFileUtilty.readExcelSheet("AppointmentCreationColonoscopy");
		appointmentpageobj.clickonPatientNameDropDown();
		appontmentAssert.assertEquals(appointmentpageobj.verifyPatientScreenInAppointment(), true);
		appointmentpageobj.searchPatientName();
		appontmentAssert.assertEquals(appointmentpageobj.verifyPatientIsSearched(), true);
		appointmentpageobj.setTime();
		appointmentpageobj.setActivity("Colonoscopy");
		appointmentpageobj.setProvider();
		appontmentAssert.assertTrue(appointmentpageobj.verifyRequiredFieldvaldation());
		appointmentpageobj.clickOnSaveButtonofUserPage();
		appontmentAssert.assertEquals(appointmentpageobj.checkAppointmentText("Colonoscopy"), ConstantsFile.patientfirstname+" "+ConstantsFile.patientlastname+'\n'+ConstantsFile.MRN+ " "+ConstantsFile.ColonIntialname+" "+ConstantsFile.providerfirstname+" "+ConstantsFile.providercompletelastname+" "+ConstantsFile.Fulllocationname, "created fresh appointment");
		//appointmentpageobj.selectRow();
		//appointmentpageobj.clickOnStartbutton();
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that loggedin user is able to create new appointment for the first visit",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=13)
	public void createAppointmentForFirstVisit() throws Exception{
		logger.info("Username & Password entered in the Username and Password text box");
		loginPageObj.loggedInWithNewUser();
		configpageobj.clickOnActvities();
		configpageobj.clickOnnewButtonOnActivitiesPage();
		configpageobj.createNewFirstVisitActivity();
		configpageobj.selectLocationInSchuduler();
		configpageobj.clickOnSaveButton();
		configpageobj.clickOnTemplate();
		configpageobj.configTemplateForFirstVisit();
		//configpageobj.configPreProcedureNursingNoteForColonscopy();
		//ConstantsFile.appointmentData=ExcelFileUtilty.readExcelSheet("AppointmentCreationFirstVisit");
		appointmentpageobj.clickOnScheduler();
		appontmentAssert.assertEquals(appointmentpageobj.verifySchdulerModuleIsOpened(), true);
		appointmentpageobj.clickOnNewButton();
		appointmentpageobj.clickonPatientNameDropDown();
		appontmentAssert.assertEquals(appointmentpageobj.verifyPatientScreenInAppointment(), true);
		appointmentpageobj.searchPatientName();
		appontmentAssert.assertEquals(appointmentpageobj.verifyPatientIsSearched(), true);
		appointmentpageobj.setTime();
		appointmentpageobj.setActivity("First Visit");
		appointmentpageobj.setProvider();
		appontmentAssert.assertTrue(appointmentpageobj.verifyRequiredFieldvaldation());
		appointmentpageobj.clickOnSaveButtonofUserPage();
		appontmentAssert.assertEquals(appointmentpageobj.checkAppointmentText("first visit"), ConstantsFile.patientfirstname+" "+ConstantsFile.patientlastname+'\n'+ConstantsFile.MRN+ " "+ConstantsFile.FirstVisitIntialname+" "+ConstantsFile.providerfirstname+" "+ConstantsFile.providercompletelastname+" "+ConstantsFile.Fulllocationname, "created fresh appointment");
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that loggedin user is able to create new appointment for the first visit",groups ={"gGastro_smoke"},dependsOnMethods = {"loginToGPin" },priority=14)
	public void configPatientInterviewForm() throws Exception{
		logger.info("Username & Password entered in the Username and Password text box");
		loginPageObj.loggedInWithNewUser();
		pifobj.launchPIF();
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify that loggedin user is able to create new appointment for the first visit",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=15)
	public void createFutureAppointForColonscopy() throws Exception{
		logger.info("Username & Password entered in the Username and Password text box");
		loginPageObj.loggedInWithNewUser();
		//ConstantsFile.appointmentData=ExcelFileUtilty.readExcelSheet("AppointmentCreationColonoscopy");
		appointmentpageobj.clickOnScheduler();
		appontmentAssert.assertEquals(appointmentpageobj.verifySchdulerModuleIsOpened(), true);
		appointmentpageobj.clickOnNewButton();
		appointmentpageobj.clickonPatientNameDropDown();
		appontmentAssert.assertEquals(appointmentpageobj.verifyPatientScreenInAppointment(), true);
		appointmentpageobj.searchPatientName();
		appontmentAssert.assertEquals(appointmentpageobj.verifyPatientIsSearched(), true);
		AppointmentPage.setFutureDate();
		appointmentpageobj.setTime();
		appointmentpageobj.setActivity("Colonoscopy");
		appointmentpageobj.setProvider();
		appontmentAssert.assertEquals(appointmentpageobj.verifyRequiredFieldvaldation(), true);
		appointmentpageobj.clickOnSaveButtonofUserPage();
		appointmentpageobj.clickOnSearchFilter();
		appointmentpageobj.selectFuturevalue();
		appointmentpageobj.clickOnFindButton();
		appontmentAssert.assertEquals(appointmentpageobj.checkAppointmentText("Colonoscopy"), ConstantsFile.patientfirstname+" "+ConstantsFile.patientlastname+'\n'+ConstantsFile.MRN+ " "+ConstantsFile.ColonIntialname+" "+ConstantsFile.providerfirstname+" "+ConstantsFile.providercompletelastname+" "+ConstantsFile.Fulllocationname, "created fresh appointment");
		//appointmentpageobj.selectRow();
		appointmentpageobj.clickOnViewChart(ConstantsFile.patientfirstname,ConstantsFile.patientlastname);
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		leftPanelpageobj.clickLeftNavigationPanelValue("Profile");
		Profile.clickOnProfileInPatient();
		dynamicframeobj.switchInDymFrameForAppointment();
		appontmentAssert.assertEquals(appointmentpageobj.viewFutureAppointment(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is able to prescribe Medications on left navigation panel   ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=16)
	public void verifyPrescribeMedication() throws Exception{
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		appontmentAssert.assertEquals(prescriptionobj.verifyprescriptionRPanel(), true);
		profilepageobj.switchToSocialHistoryFrame();
		leftPanelpageobj.clickOnToolBarMenus("Prescribe");
		appontmentAssert.assertEquals(prescriptionobj.verifyPrescriptionDetailsPopup(), true);
		prescriptionobj.createRequireDetailsInMedication("Keflex");
		appontmentAssert.assertEquals(prescriptionobj.verifyMedicationDetailIsPopulated(), true);
		prescriptionobj.selectMethod("clickOnHandwritten");
		appontmentAssert.assertEquals(prescriptionobj.verifyMedicationIsDocumented(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is able to prescribe Medications on left navigation panel   ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=17)
	public void verifyRecordExistingMedication() throws Exception{
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		leftPanelpageobj.clickLeftNavigationPanelValue("Prescriptions");
		appontmentAssert.assertEquals(prescriptionobj.verifyPrescriptionRPanelandClickOnExisting(), true);
		appontmentAssert.assertEquals(prescriptionobj.verifyPrescriptionDetailsPopupForExistingMedication(), true);
		prescriptionobj.createRequireDetailsInMedication("Keflex");
		appontmentAssert.assertEquals(prescriptionobj.verifyMedicationDetailIsPopulated(), true);
		medicalchartpage.clickOnSaveButton();
		appontmentAssert.assertEquals(prescriptionobj.verifyExistingMedicationIsDocumented(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is add Task from th patient chart ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=18)
	public void addTask() throws Exception{
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		taskobj.clickOnTask();
		appontmentAssert.assertEquals(taskobj.verifyTaskPopupIsDisplayed(), true);
		taskobj.clickOnSubject();
		taskobj.clickOnSelfLink();
		appontmentAssert.assertEquals(taskobj.verifyloggedInUserNameIsPopulatedInSelfLink(), true);
		taskobj.clickOnSendbutton();
		leftPanelpageobj.clickOnMyActvities();
		appontmentAssert.assertEquals(taskobj.verifyTaskSubjectInTaskSection(), true);
		leftPanelpageobj.clickOnLogout();
	}
	@Test(description = "To verify user is create order from thw patient chart ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=19)
	public void createOrder() throws Exception{
		loginPageObj.loggedInWithNewUser();
		medicalchartpage.searchPatientNameInMedical();
		appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		leftPanelpageobj.clickLeftNavigationPanelValue("Orders");
		appontmentAssert.assertEquals(orderobj.verifyOrderQueueIsDisplayed(), true);
		orderobj.clickOnNewButton();
		appontmentAssert.assertEquals(orderobj.verifyOrderScreen(), true);
		orderobj.clickOnFollowupOnLeftpanel("Follow Up");
		appontmentAssert.assertEquals(orderobj.verifyFollowupOrderScreen(), true);
		orderobj.clickOnRecallType();
		orderobj.clickOnTitle();
		orderobj.clickOnTrackOrder();
		orderobj.selectFutureDate();
	    medicalchartpage.clickOnSaveButton();
		leftPanelpageobj.clickOnQueueManagement("clickOnOrder");
		//queueAssert.assertEquals(queue_management.verifyOrderInQueue(), true);
		leftPanelpageobj.clickOnLogout();
	}
	 @Test(description = "To verify user is import PDF from thw patient chart ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=20)
	 public void verifyImportPDF() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 medicalchartpage.searchPatientNameInMedical();
		 appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		 patientchartobj.clickOnImportFile();
		 GpinUserLogin.uploadPDF();
	///check this	 //medicalpatientpage.verifyPDFIsImportedFromPatientChart();
		 leftPanelpageobj.clickOnLogout();
	 }

	@Test(description = "To verify user is import scan document from the scanning queue ",groups ={"gGastro_smoke"},dependsOnMethods = {"loginToGPin","createTestPatientFromMedChart" },priority=21)
	 public void ImportDocumentForScanning() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		 scanningobj.clickOnImportButton();
		 GpinUserLogin.uploadPDF();
		 scanningobj.swithToScanning();
		 scanningobj.selectPatient();
		 scanningobj.clickOnFileAs();
		 appontmentAssert.assertEquals(scanningobj.verifyFileAsOptions(), true);
		 scanningobj.clickOnNowLink();
		 scanningobj.clickOnSave();
		 scanningobj.verifyPDFIsImported();
		 leftPanelpageobj.clickOnLogout();
	 }
	@Test(description = "To verify user is print scan document from the scanning queue ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart"},priority=22)
	 public void printScannedDocument() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		 scanningobj.selectImportedRow();
		 scanningobj.clickOnPrintButton();
		 GpinUserLogin.clickOnPrint();
		 Thread.sleep(7000);
		 leftPanelpageobj.clickOnLogout();
	 }
	 @Test(description = "To verify user is fax scan document from the scanning queue ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart"},priority=23)
	 public void faxImportedDocument() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		 scanningobj.selectImportedRow();
		 scanningobj.clickOnFaxButton();
		 appontmentAssert.assertEquals(scanningobj.verifyFaxPopupWindow(), true);
		 scanningobj.fillDetailsForFaxing();
		 leftPanelpageobj.clickOnQueueManagement("clickOnOutboundFaxing");
		 appontmentAssert.assertEquals(faxobj.selectOutBoundRow(), true);
		 //queue_management.selectOutBoundRow();
		 leftPanelpageobj.clickOnLogout();
	 }
	 @Test(description = "To verify user is able to create External Task s from the scanning  ",groups ={"gGastro_smoke"},dependsOnMethods = {"loginToGPin","createTestPatientFromMedChart"},priority=24)
	 public void createExternalTaskforImportedDoc() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		 scanningobj.selectImportedRow();
		 scanningobj.clickOnTask();
		 appontmentAssert.assertEquals(patientchartobj.verifyloggedInUserNameIsPopulatedInSelfLink(), true);
		 patientchartobj.clickOnSendForSignature();
		 scanningobj.clickOnSave();
		 appontmentAssert.assertEquals(scanningobj.verifyImportedRow(), true);
		 leftPanelpageobj.clickOnMyActvities();
		 appontmentAssert.assertEquals(taskobj.verifyTaskSubjectInTaskSection(), true);
		 taskobj.selectTask();
		 taskobj.clickOnSignAndCompleteOnTask();
		 //appontmentAssert.assertEquals(queue_management.verifyTaskIsSigned(), true);
		 //queue_management.verifyTaskIsSigned();
		 leftPanelpageobj.clickOnLogout();
	 }
	 @Test(description = "To verify user is able to verify Report  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin"},priority=25)
	 public void verifyReports() throws Exception{
		 String gPinurl =GmedConfig.getConfig("gPinURL");
		 DriverFactory.getAnotherDriverInstance(gPinurl);
		 DriverFactory. openGpinURL(gPinurl);
		 GpinUserLogin.logintoGpin();
		 appontmentAssert.assertEquals(GpinUserLogin.verifygPinCredinaials(), true);
		 gpinpageobj.verifygPinPage();
		 loginPageObj.loginToGmedWithGpin(ConstantsFile.loginData);
		 appontmentAssert.assertEquals(loginPageObj.verifyHomePageIsLoaded(), true);
		 configpageobj.clickOnReport();
		 appontmentAssert.assertEquals(configpageobj.verifyReportIsPublish(), true);
		 leftPanelpageobj.clickOnLogout();
		 loginPageObj.loggedInWithNewUser();
		 appontmentAssert.assertEquals(loginPageObj.verifyHomePageIsLoaded(), true);
		 leftPanelpageobj.clickOnReport("clickOnReport");
		 reportobj.clickOnPatientByAgeReport("Patient By Age Patient By Age");
		 appontmentAssert.assertEquals(reportobj.verifyReportPageIsDisplayed("Reports: Patient By Age"), true);
		 reportobj.enterValidAgeInReport();
		 //appontmentAssert.assertEquals(reportobj.verifyRecords(), true);
		 leftPanelpageobj.clickOnLogout();

	 }
	 @Test(description = "To verify user is able file a imported document",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart"},priority=26)
	 public void fileScannedDcoument() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
		 scanningobj.selectImportedRow();
		 scanningobj.clickOnFile();
		 appontmentAssert.assertEquals(scanningobj.verifyRow(), true);
		 scanningobj.clickOnProcessed();
		 scanningobj.selectPatientInProceeedFolder();
		 appontmentAssert.assertEquals(scanningobj.verifyImportedRow(), true);
		 leftPanelpageobj.clickOnLogout();

	 }
	 @Test(description = "To verify user is able to verify Report  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart","faxImportedDocument"},priority=27)
	 public void verifyOutBoxFaxingQueue() throws Exception{

		 loginPageObj.loggedInWithNewUser();
		 leftPanelpageobj.clickOnQueueManagement("clickOnOutboundFaxing");
		 faxobj.switchToOutboundFaxing();
		 faxobj.clickOnPatientButton();
		 faxobj.selectPatient();
		 //appontmentAssert.assertEquals(queue_management.selectOutBoundRow(), true);
		 leftPanelpageobj.clickOnLogout();

	 }

	 @Test(description = "To verify user is able to verify Report  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart"},priority=28)
	 public void exportCodes() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 medicalchartpage.searchPatientNameInMedical();
		 appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		 patientchartobj.clickOnProcedure("First Visit");
		 appontmentAssert.assertEquals(documentobj.verifyFirstVisitTab(), true);
		 documentobj.clickOnIndication();
		 appontmentAssert.assertEquals(documentobj.verifyIndicationValueFromUserList(), true);
		 documentobj.clickOnCoding();
		 appontmentAssert.assertEquals(documentobj.clickOnNPP(), true);
		 medicalchartpage.clickOnSaveButton();
		 documentobj.clickOnProcessDropDown();
		 documentobj.clickOnExportCodes();
		 documentobj.clickOnOkay();
		 leftPanelpageobj.clickOnLogout();

	 }
	 @Test(description = "To verify user is able to verify Report  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart"},priority=29)
	 public void gQuicExceptionReport() throws Exception{

		 loginPageObj.loggedInWithNewUser();
		 medicalchartpage.searchPatientNameInMedical();
		 appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
		 patientchartobj.clickOnProcedure("Colonoscopy");
		 configpageobj.clickOnGquicReport();
		 appontmentAssert.assertEquals(configpageobj.verifyGquicExceptionReportPage(), true);
		 //appontmentAssert.assertEquals(configpageobj.verifyReportContent(), true);
		 leftPanelpageobj.clickOnLogout();

	 }
	 @Test(description = "To verify user is able to Enter /Modify Medical History Sections in PIF",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=30)
	 public void verifyMedicalHistoryInPIF() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 medicalchartpage.searchPatientNameInMedical();
		 appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true); 
		 patientchartobj.clickOnProcedure("Colonoscopy");
		 leftPanelpageobj.clickOnNewInToolTip();
		 pifobj.clickOnPIF(); 
		 pifobj.clickOnallergySectionInPif(); 
		 appontmentAssert.assertEquals(pifobj.verifyallergyUserListInPIF(), true); 
		 appontmentAssert.assertEquals(pifobj.verifyAllergyPopupInPif(), true); 
		 pifobj.clickOnImmunizationSectionInPif();
		 appontmentAssert.assertEquals(pifobj.verifyImmunizationUserListInPIF(), true);
		 appontmentAssert.assertEquals(pifobj.verifyImmunizationsPopupDetails(), true);
		 pifobj.clickOnDiagnosticSectionInPif();
		 appontmentAssert.assertEquals(pifobj.verifyDiagnosticUserListInPIF(), true);
		 appontmentAssert.assertEquals(pifobj.verifyDxStudiesPopupDetails(), true);
		 pifobj.clickOnPresentMedicalConditionSectionInPif();
		 appontmentAssert.assertEquals(pifobj.verifyPresentMedicationUserListInPIF(), true);
		 //appontmentAssert.assertEquals(medicalpatientpage.verifyCondtionsPopDetails(), true); 
		 pifobj.clickOnPrviousProcedureSectionInPif();
		 appontmentAssert.assertEquals(pifobj.verifyPreviousUserListInPIF(), true);
		 pifobj.switchToProcedurePopupFrameInPif();
		 appontmentAssert.assertEquals(pifobj.verifyProceduresPopupDetails(), true);
		 pifobj.clickOnCloseButtonInMedical();
		 leftPanelpageobj.clickOnLogout();
	 }
	 @Test(description = "To verify user is able to Enter /Modify Medical History Sections in PIF",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=31)
	 public void verifySocialHistoryInPIF() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 medicalchartpage.searchPatientNameInMedical();
		 appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true); 
		 patientchartobj.clickOnProcedure("First Visit");
		 leftPanelpageobj.clickOnNewInToolTip();
		 pifobj.clickOnPIF(); 
		 //medicalpatientpage.clickOnOccupationInPif();
		 pifobj.clickOnMaritalStatus();
		 pifobj.clickOnAlcoholSectionInPif();
		 pifobj.EnterAlcholDetailsInPopup();
		 medicalchartpage.clickOnSaveButton();
		 pifobj.clickOnCaffeineSectionInPif();
		 pifobj.EnterIntakeDetailsInPopup();
		 medicalchartpage.clickOnSaveButton();
		 pifobj.clickOnTabaccoSmokingStatus();
		 pifobj.clickOnDrugSectionInPif();
		 pifobj.EnterDrugDetailsInPopup();
		 medicalchartpage.clickOnSaveButton();
		 pifobj.clickOnExerciseSectionInPif();
		 pifobj.EnterExerciseDetailsInPopup();
		 medicalchartpage.clickOnSaveButton();
		 pifobj.clickOnCloseButtonInSocial();
		 //appontmentAssert.assertEquals(medicalpatientpage.verifyOccupationalisDocumented(), true);
		 // appontmentAssert.assertEquals(medicalpatientpage.verifyMaritalStatusisDocumented(), true);
		appontmentAssert.assertEquals(pifobj.verifyAlocholisDocumented(), true);
		 appontmentAssert.assertEquals(pifobj.verifyCaffeineisDocumented(), true);
		 appontmentAssert.assertEquals(pifobj.verifyTobaccoisDocumented(), true);
		 appontmentAssert.assertEquals(pifobj.verifyDrugisDocumented(), true);
		 appontmentAssert.assertEquals(pifobj.verifyExerciseisDocumented(), true);
		 leftPanelpageobj.clickOnLogout();	
	 }
	 @Test(description = "To verify user is able to Enter /Modify Family History Sections in PIF",groups ={"gGastro_smoke"},dependsOnMethods = {"loginToGPin","createTestPatientFromMedChart"},priority=32)
	 public void verifyFamilyHistoryInPIF() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 medicalchartpage.searchPatientNameInMedical();
		 appontmentAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true); 
		 patientchartobj.clickOnProcedure("Colonoscopy");
		 leftPanelpageobj.clickOnNewInToolTip();
		 pifobj.clickOnPIF(); 
		 pifobj.clickOnFamilyMedicalHistorySectionInPif("clickOnsister");
		 pifobj.clickOnFamilyMedicalHistorySectionInPif("addDiagnosis");
		 //pifobj.clickOnNoFamilyHistorySectionInPif();
		 pifobj.AddDiagnosticUserListInPIF();
		 pifobj.clickOnCloseButtonInSocial();
		 leftPanelpageobj.clickOnLogout();
	 }
 @Test(description = "To verify user is able to config new template  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart" },priority=33)
	 public void startFirstVisitService() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		 configpageobj.clickOnOutPutManager();
		 outputManagerobj.clickOnProcedure("First Visit");
		 outputManagerobj.clickOnFirstVisitInService();
		 outputManagerobj.selectUSerForOutPutManager();
		 loginPageObj.clickOnSaveButton();
		 appointmentpageobj.clickOnScheduler();
		 appontmentAssert.assertEquals(appointmentpageobj.verifySchdulerModuleIsOpened(), true);
		 appointmentpageobj.selectAppointmentRow("First_visit");
		 leftPanelpageobj.clickOnNewInToolTip();
		 documentobj.clickOnChiefComplain();
		 appontmentAssert.assertEquals(documentobj.verifyChiefUserListIsOpened(), true);
		 documentobj.selectChiefValueFromUserList();
		 appontmentAssert.assertEquals(documentobj.verifyChiefValueIsDocumentedInUserList(), true);
		 documentobj.clickOnHistoryOfPresentIllness();
		 appontmentAssert.assertEquals(documentobj.verifyHistoryOfPresentIllnessUserListIsOpened(), true);
		 documentobj.selectHistoryOfPresntValueFromUserList();
		 appontmentAssert.assertEquals(documentobj.verifyHistoryOfPresentIllnessIsDocumentedInUserList(), true);
		 documentobj.clickOnVital();
		 appontmentAssert.assertEquals(documentobj.verifyVitalSignUserListIsOpened(), true);
		 documentobj.EnterVitalInformation();
		 appontmentAssert.assertEquals(documentobj.verifyBMIResultIsCalculatedInUserList(), true);
		 documentobj.clickOnSaveButtonInVitalSign();
		 appontmentAssert.assertEquals(documentobj.verifyVitalDataIsDocumented(), true);
		 documentobj.clickOnImpression();
		 documentobj.selectIndicationValueFromUserList();
		 appontmentAssert.assertEquals(documentobj.verifyImpressionValueIsDocumented(), true);
		 //medicalpatientpage.clickOnSaveButton();
		 documentobj.clickOnPlan();
		 appontmentAssert.assertEquals(documentobj.verifyPlanUserListIsOpened(), true);
		 documentobj.selectPlanvalueFromUserList();
		 appontmentAssert.assertEquals(documentobj.verifyOrderDataIsDocumented(), true);
		 documentobj.clickOnCoding();
		 appontmentAssert.assertEquals(documentobj.verifyCodingPopupIsDisplayed(), true);
		 documentobj.clickOnNPP();
		 medicalchartpage.clickOnSaveButton();
		 documentobj.clickOnCleanUp();
		 documentobj.clickOnProcess();
		 appontmentAssert.assertEquals(documentobj.verifyOutPutManagerScreenIsOpen(), true);
		 documentobj.clickonExecute();
		 GpinUserLogin.clickOnPrint();
		 leftPanelpageobj.clickOnMyActvities();
		 appontmentAssert.assertEquals(taskobj.verifyTaskForFirstVisitInTaskSection(), true);
		 leftPanelpageobj.clickOnQueueManagement("clickOnOutboundFaxing");
		 faxobj.switchToOutboundFaxing();
		 faxobj.clickOnPatientButton();
		 faxobj.selectPatient();
		 appontmentAssert.assertEquals(faxobj.selectOutBoundRowForService(), true);
		 leftPanelpageobj.clickOnLogout();
	 }
	@Test(description = "To verify user is able to config new template  ",groups ={"gGastro_smoke"},dependsOnMethods = { "loginToGPin","createTestPatientFromMedChart"  },priority=34)
	 public void startColonscopyService() throws Exception{
		 loginPageObj.loggedInWithNewUser();
		configpageobj.clickOnOutPutManager();
		 outputManagerobj.clickOnProcedure("Colonoscopy");
		// configpageobj.clickOnnewButtonOnActivitiesPage();
		 documentobj.clickOnColonscopyInService();
		 outputManagerobj.selectUSerForOutPutManager();
	     loginPageObj.clickOnSaveButton();
		 appointmentpageobj.clickOnScheduler();
		 appontmentAssert.assertEquals(appointmentpageobj.verifySchdulerModuleIsOpened(), true);
		 appointmentpageobj.selectAppointmentRow("Colonoscopy");
		 leftPanelpageobj.clickOnNewInToolTip();
		 documentobj.clickOnIndicationInColonscopy();
		 documentobj.clickOnIndicationTabInImpression();
		 appontmentAssert.assertEquals(documentobj.verifyIndicationUserListIsOpen(), true);
		 documentobj.selectIndicationValueFromUserList();
		 appontmentAssert.assertEquals(documentobj.verifyIndicationValueIsDocumentedInUserList(), true);
		 documentobj.clickOnProcedureOverview();
		 appontmentAssert.assertEquals(documentobj.verifyColonscopyProcedureOverviewRibbonIsOpen(), true);
		 documentobj.selectColonTypeFromColonscopyProcedureOverview();
		 documentobj.selectRiskAssessmentFromColonscopyProcedureOverview();
		 medicalchartpage.clickOnSaveButton();
		 documentobj.clickOnFindings();
		 appontmentAssert.assertEquals(documentobj.verifyFindingUserListIsOpen(), true);
		 documentobj.selectFindingValueFromUserList();
		 appontmentAssert.assertEquals(documentobj.verifyFindingRibbonIsOpen(), true);
		 documentobj.selectInterventionsValueInAdditionalFindings();
		 appontmentAssert.assertEquals(documentobj.verifySelectInterventionsPopupIsOpened(), true);
		 documentobj.selectItemFromBiopsyPopUp();
		 medicalchartpage.clickOnSaveButton();
		 appontmentAssert.assertEquals(documentobj.verifyFindingTypeIsDocumentedInUserList(), true);
		 appontmentAssert.assertEquals(documentobj.verifyFindingTextIsDocumentedInUserList(), true);
		 documentobj.clickOnSample();
		 appontmentAssert.assertEquals(documentobj.verifySampleUserListIsOpen(), true);
		 documentobj.clickOnNewButtonInSampleUserList();
		 appontmentAssert.assertEquals(documentobj.verifyProcedureSpecimanPopupIsOpened(), true);
		 documentobj.enterProcedureSpecimenDetails();
		 documentobj.selectRandomValuesInProcedureSpecimen();
		 medicalchartpage.clickOnSaveButton();
		 //appontmentAssert.assertEquals(colscopypageobj.verifySampledataIsDocumentedInUserList(), true);
		 documentobj.clickOnThumbrails();
		 documentobj.clickOnTimeTracker();
		 appontmentAssert.assertEquals(documentobj.verifyTimeTrackerPopupIsOpened(), true);
		 documentobj.clickOnNowButtonInTimeTracker();
		 medicalchartpage.clickOnSaveButton();
		 //colscopypageobj.clickOnPatientPrep();
		 documentobj.clickOnPatientPrep();
		 documentobj.selectValueInPatientPrep();
		 medicalchartpage.clickOnSaveButton();
		 documentobj.clickOnPainAssessment();
		 appontmentAssert.assertEquals(documentobj.verifyPainAssessmentUserListIsOpen(), true);
		 documentobj.createNewIteminPainAssessment();
		 documentobj.clickOnProcess();
		 appontmentAssert.assertEquals(documentobj.verifyOutPutManagerScreenIsOpen(), true);
		 documentobj.clickonExecute();
		 GpinUserLogin.clickOnPrint();
		 leftPanelpageobj.clickOnQueueManagement("clickOnOutboundFaxing");
		 faxobj.switchToOutboundFaxing();
		 faxobj.clickOnPatientButton();
		 faxobj.selectPatient();
		 appontmentAssert.assertEquals(faxobj.selectOutBoundRowForService(), true);
		 leftPanelpageobj.clickOnLogout();
	 }
}
