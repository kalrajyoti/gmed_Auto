package com.gmed.test;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.sikuli.script.FindFailed;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.gmed.AutoIT.GpinUserLogin;
import com.gmed.base.BaseTestClass;
import com.gmed.pages.AppointmentPage;
import com.gmed.pages.ConfigurationPage;
import com.gmed.pages.DemographicsPage;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.LeftPanelPage;
import com.gmed.pages.LoginPage;
import com.gmed.pages.MedicalChartPage;
import com.gmed.pages.PatientChart;
import com.gmed.utils.ConstantsFile;
import com.gpm.pages.AccountInquiryPage;
import com.gpm.pages.BilledPage;
import com.gpm.pages.BillingPage;
import com.gpm.pages.CollectionPage;
import com.gpm.pages.InsurancePaymentPage;
import com.gpm.pages.InsuranceRefund;
import com.gpm.pages.LedgerPage;
import com.gpm.pages.PatientRefundPage;
import com.gpm.pages.PaymentPage;
import com.gpm.pages.PostedChargePage;
import com.gpm.pages.PrepayPage;
import com.gpm.pages.StatementPage;
import com.gpm.pages.SuperBillPage;
import com.gpm.pages.UnbilledClaimPage;
 public class GpmSmokeTest extends BaseTestClass {
	/** Logger to log the GpmSmokeTest log messages */
	private static Logger logger  = LogManager.getLogger(GpmSmokeTest.class); 
	private Assertion gPmAssert = new Assertion();
	private LoginPage loginPageObj;
	private GpinUserLogin gpinpageobj;
	private ConfigurationPage configpageobj;
	private MedicalChartPage medicalchartpage;
	private BillingPage billingobj;
	private PatientChart patientchartobj;
	private AccountInquiryPage accountinquirypageobj;
	private SuperBillPage  superbillobj;
	private PostedChargePage postedchargeobj;
	private CollectionPage  collectionpageobj;
	private DocumentPage documentobj;
	private LedgerPage ledgerobj;
	private AppointmentPage appointmentpageobj;
	private StatementPage statementpageobj;
	private UnbilledClaimPage unbilledclaimobj;
	private PaymentPage paymentpageobj;
	private BilledPage billedpageobj;
	private InsurancePaymentPage insurancepageobj;
	private PatientRefundPage patientrefundpageobj;
	private InsuranceRefund insurancerefundpageobj;
	private PrepayPage prepayobj;
	private DemographicsPage demographicspageobj;
	@BeforeClass
	public void initClass() throws Exception{
		loginPageObj = new LoginPage();
		gpinpageobj = new GpinUserLogin();
		configpageobj = new ConfigurationPage();
		medicalchartpage = new MedicalChartPage();
		billingobj = new BillingPage();
		patientchartobj = new PatientChart();
		accountinquirypageobj = new AccountInquiryPage();
		superbillobj = new SuperBillPage();
		postedchargeobj = new PostedChargePage();
		collectionpageobj = new CollectionPage();
		documentobj = new DocumentPage();
		ledgerobj = new LedgerPage();
		appointmentpageobj = new AppointmentPage();
		statementpageobj = new StatementPage();
		unbilledclaimobj = new UnbilledClaimPage();
		paymentpageobj = new PaymentPage();
		billedpageobj = new BilledPage();
		insurancepageobj = new InsurancePaymentPage();
		patientrefundpageobj = new PatientRefundPage();
		insurancerefundpageobj = new InsuranceRefund();
		prepayobj =  new PrepayPage();
		leftPanelpageobj = new LeftPanelPage();
		demographicspageobj = new DemographicsPage();
	}
	@Test(description = "To verify user is able to login as gPin user & Enable gPm settings ",groups = { "gPM_Smoke" },priority=1)
	public void verifyGpmIsEnabled() throws Exception{
		logger.info("Getting gpin Username & password...");
		gPmAssert.assertEquals(GpinUserLogin.verifygPinCredinaials(), true);
		logger.info("Getting gpin Page Title...");
		gPmAssert.assertEquals(gpinpageobj.verifygPinPage(), true);
		logger.info("Logging into the application...");
		loginPageObj.loginToGmedWithGpin(ConstantsFile.loginData);
		gPmAssert.assertEquals(loginPageObj.verifyHomePageTitle(), true);
		configpageobj.clickOnPreferences();
		configpageobj.enableGpm();
		gPmAssert.assertEquals(configpageobj.verifyGpmEnableSettings(), true);
		configpageobj.clickOnSaveInPreferences();
		logger.info("creating logging detaits for user...");
		configpageobj.enterLoginDetailsForUserCreation();
		logger.info("creating system admin role  for user...");
		configpageobj.createNewRole();
		configpageobj.selectCreatedRole();
		gPmAssert.assertEquals(configpageobj.verfiyRoleList(), true);
		logger.info("creating new Location for user...");
		configpageobj.createNewLocation();
		configpageobj.selectLocation();
		gPmAssert.assertEquals(configpageobj.verifyLocationPopup(), true);
		logger.info("click on save button...");
		loginPageObj.clickOnSaveButton();
		gPmAssert.assertEquals(configpageobj.verifyUserIsCreated(), true);
		configpageobj.clickOnBackInConfiguration();
		configpageobj.checkLocationSettings();
		configpageobj.selectBUForLocation();
		//configpageobj.checkLocationSettings();
		configpageobj.selectBillingType();
		configpageobj.clickOnSaveButton();
		leftPanelpageobj.clickOnLogout();	
	}
	@Test(description = "To verify user is able to create a new provider ",groups = { "gPM_Smoke" },dependsOnMethods = { "verifyGpmIsEnabled" },priority=2)
	public void createProvider() throws Exception{
		logger.info("Logging into the application with the created user...");
		loginPageObj.loggedInWithNewUser();
		logger.info("creating new provider for the user...");
		configpageobj.createNewProviderandAssociate();
		configpageobj.selectBUForProvider();
		configpageobj.selectIdentificationForProvider();
		medicalchartpage.clickOnSaveButton();
		configpageobj.selectBillingAddressForProvider();
		medicalchartpage.clickOnSaveButton();
		configpageobj.selectSignatureForProvider();
		GpinUserLogin.openProvider();
		loginPageObj.selectSavebutton();
		gPmAssert.assertEquals(configpageobj.verifyUserIsMappedWithProvider(), true);
		configpageobj.clickOnSaveForProvider();
		leftPanelpageobj.clickOnLogout();	
	}
		@Test(description = "To verify Menu Items available for user navigation when gPM is enabled ",groups = { "gPM_Smoke" },dependsOnMethods = { "verifyGpmIsEnabled" },priority=3)
		public void verifyBillingEntryScreen() throws Exception{
			logger.info("Logging into the application with the created user...");
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Unposted Charges");
			gPmAssert.assertEquals(billingobj.verifySuperbillLink(), true);
			billingobj.clickOnBillingLeftNavigation("Posted Charges");
			gPmAssert.assertEquals(billingobj.verifyPostedChargesLink(), true);
			billingobj.clickOnBillingLeftNavigation("Unbilled Claims");
			gPmAssert.assertEquals(billingobj.verifyUnbilledClaimLink(), true);
			billingobj.clickOnBillingLeftNavigation("Billed Claims");
			gPmAssert.assertEquals(billingobj.verifyBilledClaimLink(), true);
			billingobj.clickOnBillingLeftNavigation("Payments and Adjustments");
			gPmAssert.assertEquals(billingobj.verifyPaymentLink(), true);
			billingobj.clickOnBillingLeftNavigation("My Claims");
			gPmAssert.assertEquals(billingobj.verifyMyClaimsLink(), true);
			billingobj.clickOnBillingLeftNavigation("Online Payments");
			gPmAssert.assertEquals(billingobj.verifyMyClaimsLink(), true);
			billingobj.clickOnBillingLeftNavigation("Refunds");
			gPmAssert.assertEquals(billingobj.verifyRefundsLink(), true);
			billingobj.clickOnBillingLeftNavigation("Manage Batches");
			gPmAssert.assertEquals(billingobj.verifyBatchLink(), true);
			billingobj.clickOnBillingLeftNavigation("Sent Claims");
			gPmAssert.assertEquals(billingobj.verifySentClaimLink(), true);
			billingobj.clickOnBillingLeftNavigation("Collections");
			gPmAssert.assertEquals(billingobj.verifyCollectionsLink(), true);
			billingobj.clickOnBillingLeftNavigation("Patients with Credits");
			gPmAssert.assertEquals(billingobj.verifyPatientswithCreditsLink(), true);
			billingobj.clickOnBillingLeftNavigation("Prepay");
			gPmAssert.assertEquals(billingobj.verifyPrepayLink(), true);
			billingobj.clickOnBillingLeftNavigation("Manage ERA");
			//gPmAssert.assertEquals(billingobj.verifyManageERALink(), true);
			leftPanelpageobj.clickOnLogout();		
		}
		@Test(description = "To verify user is able to create a test Patient with insurance Details  ",groups = { "gPM_Smoke" },dependsOnMethods = { "verifyGpmIsEnabled" },priority=4)
		public void createTestPatientwithInsurance() throws Exception{
			logger.info("Logging into the application with the created user...");
			loginPageObj.loggedInWithNewUser();
			MedicalChartPage.createNewPatient();
			medicalchartpage.createPatientAddress();
			medicalchartpage.clickOnInsuranceTab();
			billingobj.switchToInsuranceFrame();
			demographicspageobj.enterPrimaryInsuranceDetails();
			billingobj.switchToInsuranceFrame();
			demographicspageobj.copySocialSecurityNumber();
			medicalchartpage.clickOnBillingTab();
			medicalchartpage.enableBillingSettings();
			medicalchartpage.selectBillingDropDown();
			medicalchartpage.savePatientDetails();
			gPmAssert.assertEquals(medicalchartpage.verifyPatientIsCreated(), true);
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify user is able to create a test Patient without insurance Details  ",groups = { "gPM_Smoke" },dependsOnMethods = { "verifyGpmIsEnabled" },priority=5)
		public void createTestPatient() throws Exception{
			logger.info("Logging into the application with the created user...");
			loginPageObj.loggedInWithNewUser();
			MedicalChartPage.createNewPatientWithoutInsurance();
			medicalchartpage.savePatientDetails();
			gPmAssert.assertEquals(medicalchartpage.verifyPatientIsCreated(), true);
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To Verify Patient's Financial account inquiry shows patient's demographic information, account balances and  Tabs to view charges, payments, refunds, ledger and statement of the patient",groups = { "gPM_Smoke" },dependsOnMethods = { "verifyGpmIsEnabled" ,"createProvider","createTestPatient"},priority=6)
		public void verifyAccountInquiry() throws Exception{
			logger.info("Logging into the application with the created user...");
			loginPageObj.loggedInWithNewUser();
			medicalchartpage.searchPatientNamewithoutInsMedical();
			gPmAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
			patientchartobj.clickOnBillingDropDown();
			patientchartobj.clickOnAccountInquiry();
			accountinquirypageobj.clickOnPatientHyperLink();
			gPmAssert.assertEquals(patientchartobj.verifyPatientInfoTab(), true);
			accountinquirypageobj.clickOnCloseButton();
			patientchartobj.clickOnBillingDropDown();
			patientchartobj.clickOnSuperBill();
			gPmAssert.assertEquals(superbillobj.verifySuperbillPage(), true);
			superbillobj.clickOnNewButton();
			superbillobj.clickOnDropDowns(6);
			superbillobj.selectLocation();
			superbillobj.enterDiagonisValues();
			gPmAssert.assertEquals(superbillobj.verifydiagnosisValuesIsPopulated(), true); 
			gPmAssert.assertEquals(superbillobj.verifyServiceLocationIsPopulated(), true);
			superbillobj.clickOnDropDowns(4);
			superbillobj.selectProvider();
			gPmAssert.assertEquals(superbillobj.verifyBillingProviderIsPopulated(), true);
			superbillobj.enterProcedurecode();
			gPmAssert.assertEquals(superbillobj.verifyProcedurecodeValueIsPopulated(), true);
			superbillobj.clickOnPostButton(); 
			patientchartobj.clickOnBillingDropDown();
			patientchartobj.clickOnAccountInquiry();
			gPmAssert.assertEquals(accountinquirypageobj.verifyPatientBalance(), true);
			gPmAssert.assertEquals(accountinquirypageobj.verifyChargesRow(), true);
			gPmAssert.assertEquals(accountinquirypageobj.verifyAccountAgingColums(), true);
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that superbill queue should show list of posted and unposted queue ",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance" },priority=7)
		public void verifyListOfPostedCharges() throws Exception{
			logger.info("Logging into the application with the created user...");
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Posted Charges");
			gPmAssert.assertEquals(billingobj.verifyPostedChargesLink(), true);
			gPmAssert.assertEquals(postedchargeobj.verifyChargesRow(), true);
			leftPanelpageobj.clickOnLogout();	
		}
		@Test(description = "To verify that user is able to see CollectionList ",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance" },priority=8)
		public void verifyCollectionList() throws FindFailed, Exception{
			loginPageObj.loggedInWithNewUser();
			medicalchartpage.searchPatientNamewithoutInsMedical();
			gPmAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
			patientchartobj.clickOnBillingDropDown();
			patientchartobj.clickOnAccountInquiry();
			//appointmentpageobj.clickOnCloseButtonInBillingAlerts();
			accountinquirypageobj.clickOnPatientHyperLink();
			billingobj.clickOnBillingTabInPatient();
			gPmAssert.assertEquals(collectionpageobj.verifySuppressCollectionisUnchecked(), true);
			collectionpageobj.clickOnFlagForCollection();
			documentobj.clickOnYesPopUp();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Collections");
			gPmAssert.assertEquals(billingobj.verifyCollectionsLink(), true);
			collectionpageobj.searchPatientWithoutIns();
			collectionpageobj.switchToCollectionFrame();
			billingobj.clickOnSearchButton();
			collectionpageobj.clickOnExpand();
			gPmAssert.assertEquals(collectionpageobj.verifyCollectionQueue("View Account"), true);
			//appointmentpageobj.clickOnCloseButtonInBillingAlerts();
			ledgerobj.clickOnLedger();
			gPmAssert.assertEquals(collectionpageobj.verifyCollectionQueue(), true);
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that loggedin user is able to create new appointment for the Colonoscopy",groups = { "gPM_Smoke" },dependsOnMethods = {"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance" },priority=9)
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
			gPmAssert.assertEquals(appointmentpageobj.verifySchdulerModuleIsOpened(), true);
			appointmentpageobj.clickOnNewButton();
			//ConstantsFile.appointmentData=ExcelFileUtilty.readExcelSheet("AppointmentCreationColonoscopy");
			appointmentpageobj.clickonPatientNameDropDown();
			gPmAssert.assertEquals(appointmentpageobj.verifyPatientScreenInAppointment(), true);
			appointmentpageobj.searchPatientName();
			gPmAssert.assertEquals(appointmentpageobj.verifyPatientIsSearched(), true);
			billingobj.clickOnCloseButtonInBillingAlerts();
			appointmentpageobj.setTime();
			appointmentpageobj.setActivity("Colonoscopy");
			appointmentpageobj.setProvider();
			gPmAssert.assertTrue(appointmentpageobj.verifyRequiredFieldvaldation());
			appointmentpageobj.clickOnSaveButtonofUserPage();
			gPmAssert.assertEquals(appointmentpageobj.checkAppointmentText("Colonoscopy"), ConstantsFile.patientfirstname+" "+ConstantsFile.patientlastname+'\n'+ConstantsFile.MRN+ " "+ConstantsFile.ColonIntialname+" "+ConstantsFile.providerfirstname+" "+ConstantsFile.providercompletelastname+" "+ConstantsFile.Fulllocationname, "created fresh appointment");
			//appointmentpageobj.selectRow();
			//appointmentpageobj.clickOnStartbutton();
			leftPanelpageobj.clickOnLogout();		
}
		@Test(description = "To verify that loggedin user is able to check in  created new appointment for the Colonoscopy",groups = { "gPM_Smoke" },dependsOnMethods = {"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance" },priority=10)
		public void checkInService() throws Exception{
			logger.info("Logging into the application with the created user...");
			loginPageObj.loggedInWithNewUser();
			appointmentpageobj.clickOnScheduler();
			gPmAssert.assertEquals(appointmentpageobj.verifySchdulerModuleIsOpened(), true);
			appointmentpageobj.checkInAppointmentRow("Colonoscopy");
			gPmAssert.assertEquals(appointmentpageobj.verifyCheckInPageIsOpened(), true);
			appointmentpageobj.clickOnPayment();
			gPmAssert.assertEquals(appointmentpageobj.verifyCoPaymentPopupIsOpened(), true);
			appointmentpageobj.enterCopaymentAmount();
			appointmentpageobj.selectPaymentMethod();
			gPmAssert.assertEquals(appointmentpageobj.verifyPaymentMethodIsPopulated(), true); 
			medicalchartpage.clickOnSaveButton();
			appointmentpageobj.clickOnCheckInButton();
			gPmAssert.assertEquals(appointmentpageobj.verifyAppointmentStatus(), true); 
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Unposted Charges");
			superbillobj.switchToSuperbillPage();
			billingobj.searchPatientInBilling();
			superbillobj.switchToSuperbillPage();
			billingobj.clickOnSearchButton();
			gPmAssert.assertEquals(appointmentpageobj.verifySuperbillQueue(), true);
			appointmentpageobj.clickOnScheduler();
			appointmentpageobj.selectAppointmentRow("Colonoscopy");
			documentobj.clickOnIndicationInColonscopy();
			documentobj.clickOnIndicationTabInImpression();
			gPmAssert.assertEquals(documentobj.verifyIndicationUserListIsOpen(), true);
			documentobj.selectIndicationValueFromUserList();
			gPmAssert.assertEquals(documentobj.verifyIndicationValueIsDocumentedInUserList(), true);
			documentobj.processCoding();
			gPmAssert.assertEquals(documentobj.verifyCodingData(), true);
			documentobj.clickOnProcessDropDown();
			documentobj.clickOnSign();
			documentobj.clickOnAuthentication();
			documentobj.clickOnProcessDropDown();
			documentobj.clickOnCharges();
			documentobj.clickOnYesPopUp();
			documentobj.clickOnOkay();
			documentobj.closeColonoscopyService();
			patientchartobj.clickOnBillingDropDown();
			patientchartobj.clickOnSuperBill();
			superbillobj.selectUnpostedRow();
			billingobj.clickOnCloseButtonInBillingAlerts();
			superbillobj.clickOnPostPayment();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Payments and Adjustments");
			billingobj.switchToPaymentPage();
			billingobj.searchPatientInBilling();
			billingobj.switchToPaymentPage();
			billingobj.clickOnSearchButton();
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that View List of UnPosted and Posted superbills ",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance" },priority=11)
		public void verifyListOfSuperBills() throws Exception{
			logger.info("Logging into the application with the created user...");
			loginPageObj.loggedInWithNewUser();
			medicalchartpage.searchPatientNameInMedical();
			gPmAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
			patientchartobj.clickOnBillingDropDown();
			patientchartobj.clickOnSuperBill();
			gPmAssert.assertEquals(superbillobj.verifySuperbillPage(), true);
			superbillobj.clickOnNewButton();
			billingobj.clickOnCloseButtonInBillingAlerts();
			superbillobj.clickOnSaveButtonInSuperBill();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Unposted Charges");
			gPmAssert.assertEquals(billingobj.verifySuperbillLink(), true);
			superbillobj.switchToSuperbillPage();
			billingobj.searchPatientInBilling();
			superbillobj.switchToSuperbillPage();
			billingobj.clickOnSearchButton();
			gPmAssert.assertEquals(appointmentpageobj.verifySuperbillQueue(), true);
			leftPanelpageobj.clickOnLogout();	
		}
		@Test(description = "To verify that unbilled charges queue for the patient with patient balance and unbilled charges",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance","checkInService" },priority=12)
		public void verifyUnbilledCharges() throws FindFailed{
			loginPageObj.loggedInWithNewUser();
			medicalchartpage.searchPatientNameInMedical();
			gPmAssert.assertEquals(patientchartobj.verifyPatientChartIsOpened(), true);
			patientchartobj.clickOnBillingDropDown();
			patientchartobj.clickOnAccountInquiry();
			//gPmAssert.assertEquals(accountinquirypageobj.verifyChargesRow(), true);
			billingobj.clickOnCloseButtonInBillingAlerts();
			accountinquirypageobj.TranferBalanceToPatient();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Statements");
			gPmAssert.assertEquals(statementpageobj.verifyStatementLink(), true);
			statementpageobj.searchPatientInStatement();
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that for the patient user is able to send Statement ",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance","checkInService" },priority=13)
		public void verifySendStatements() throws FindFailed, Exception{
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Statements");
			gPmAssert.assertEquals(statementpageobj.verifyStatementLink(), true);
			statementpageobj.searchPatientInStatement();
			//billingobj.clickOnSearchButton();
			gPmAssert.assertEquals(statementpageobj.verifyStatementRow("clickOnPreviewStatement"), true);
			gPmAssert.assertEquals(statementpageobj.verifyPreviewStatementPage(), true);
			gPmAssert.assertEquals(statementpageobj.verifyStatementRow("clickOnViewAccount"), true);
			billingobj.clickOnCloseButtonInBillingAlerts();
			statementpageobj.switchToStatement();
			gPmAssert.assertEquals(accountinquirypageobj.verifyAccountInquiryPage(), true);
			gPmAssert.assertEquals(statementpageobj.verifyStatementRow("clickOnHold1"), true);
			gPmAssert.assertEquals(statementpageobj.verifyStatementRow("clickOnRemoveHold"), true);
			statementpageobj.clickOnSendStaement();
			statementpageobj.clickOnOutBox();
			gPmAssert.assertEquals(statementpageobj.verifyStatementIsGenerated(), true);
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that unbilled claims queue ",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance","checkInService" },priority=14)
		public void verifyUnbilledClaims() throws FindFailed, Exception{
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Unbilled Claims");
			gPmAssert.assertEquals(billingobj.verifyUnbilledClaimLink(), true);
			billingobj.searchPatientInBilling();
			gPmAssert.assertEquals(unbilledclaimobj.verifyElecronicTab(), true);
			unbilledclaimobj.clickOnprofessionalTab();
			gPmAssert.assertEquals(unbilledclaimobj.verifyCMSTab(), true);
			unbilledclaimobj.clickOnElectronic();
			unbilledclaimobj.selectUnBilledRow("clickOnSendPaper");
			unbilledclaimobj.clickOnprofessionalTab();
			unbilledclaimobj.selectUnBilledRow("sendToElectronic");
			unbilledclaimobj.clickOnElectronic();
			unbilledclaimobj.selectUnBilledRow("clickOnReady");
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that user is able to see list of sent claims queue ",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance","checkInService"},priority=15)
		public void verifyListOfSentClaims() throws FindFailed, Exception{
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Unbilled Claims");
			gPmAssert.assertEquals(billingobj.verifyUnbilledClaimLink(), true);
			billingobj.searchPatientInBilling();
			unbilledclaimobj.switchToUnBilledFrame();
			billingobj.clickOnSearchButton();
			unbilledclaimobj.summitClaim();
			billingobj.clickOnBillingLeftNavigation("Sent Claims");
			gPmAssert.assertEquals(billingobj.verifySentClaimLink(), true);
			gPmAssert.assertEquals(unbilledclaimobj.verifySentClaimQueue(), true);
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that user is able to do payment against charges ",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance","checkInService" },priority=16)
		public void verifyPaymentAdjustment() throws FindFailed, Exception{
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Payments and Adjustments");
			gPmAssert.assertEquals(billingobj.verifyPaymentLink(), true);
			logger.info("Clicking on Patient Payments/Adjustment button ...");
			paymentpageobj.clickOnPatientPayment();
			gPmAssert.assertEquals(paymentpageobj.verifyPatientPaymentPage(), true);
			billingobj.searchPatient();
			paymentpageobj.switchToPatientPaymentInsideFrame();
			gPmAssert.assertEquals(billingobj.verifyPatientNameIsPopulalted(), true);
			billingobj.clickOnCloseButtonInBillingAlerts();
			paymentpageobj.selectPaidBy();
			gPmAssert.assertEquals(paymentpageobj.verifyPaidByCash(),true);
			paymentpageobj.enterPaymentAmount();
			paymentpageobj.enterAdjustmentAmount();
			paymentpageobj.selectAdjustmentCode();
			paymentpageobj.switchToPatientPaymentInsideFrame();
			paymentpageobj.showAllchargesCheckbox();
			paymentpageobj.selectPatientPaymentRow();
			appointmentpageobj.clickOnSaveButtonofUserPage();
			paymentpageobj.switchToPatientPaymentFrame();
			billingobj.searchPatientInBilling();
			paymentpageobj.switchToPatientPaymentFrame();
			billingobj.clickOnSearchButton();
			paymentpageobj.switchToPatientPaymentFrame();
			gPmAssert.assertEquals(paymentpageobj.verifyInsuranceQueue(), true);
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that user can View Billed Claims both electronic and paper ",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyListOfSentClaims"},priority=18)
		public void verifyListOfBilledClaims() throws FindFailed, Exception{
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Billed Claims");
			billedpageobj.switchToBilledFrame();
			gPmAssert.assertEquals(billingobj.verifyBilledClaimLink(), true);
			billingobj.searchPatientInBilling();
			billedpageobj.switchToBilledFrame();
			billingobj.clickOnSearchButton();
			billedpageobj.switchToBilledFrame();
			gPmAssert.assertEquals(billedpageobj.verifyBilledClaimQueue("Modify Encounter"), true);
			billingobj.clickOnCloseButtonInBillingAlerts();
			gPmAssert.assertEquals(billedpageobj.verifyEncounterPage(), true);
			billedpageobj.switchToBilledFrame();
			gPmAssert.assertEquals(billedpageobj.verifyBilledClaimQueue("View Account"), true);
			billingobj.clickOnCloseButtonInBillingAlerts();
			billedpageobj.switchToBilledFrame();
			gPmAssert.assertEquals(accountinquirypageobj.verifyAccountInquiryPage(), true);
			billedpageobj.verifyFooterText();
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that user is able to Create / Modify an Insurance Payment in the View List of Payments",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance","checkInService"},priority=17)
		public void verifyInsurancePayment() throws FindFailed, Exception{
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Payments and Adjustments");
			gPmAssert.assertEquals(billingobj.verifyPaymentLink(), true);
			logger.info("Clicking on insurance Payments/Adjustment button ...");
			insurancepageobj.clickOnInsurancePayment();
			gPmAssert.assertEquals(insurancepageobj.verifyInsurancePaymentPage(), true);
			insurancepageobj.selectCarrierType();
			gPmAssert.assertEquals(insurancepageobj.verifyCarrierNameIsPopulalted(), true);
			insurancepageobj.selectPaidBy();
			gPmAssert.assertEquals(insurancepageobj.verifyPaidByCash(),true);
			insurancepageobj.enterPaymentAmount();
			billingobj.searchPatientForInsurancePaymentAndRefund();
			billingobj.clickOnCloseButtonInBillingAlerts();
			paymentpageobj.switchToPatientPaymentInsideFrame();
			paymentpageobj.showAllchargesCheckbox();
			insurancepageobj.selectInsurancePaymentRow();;
			appointmentpageobj.clickOnSaveButtonofUserPage();
			paymentpageobj.switchToPaymentFrame();
			billingobj.searchPatientInBilling();
			paymentpageobj.switchToPatientPaymentFrame();
			billingobj.clickOnSearchButton();
			paymentpageobj.switchToPaymentFrame();
			gPmAssert.assertEquals(insurancepageobj.verifyInsuranceQueue(), true);
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that user is able to Create / Modify patient Refund in View List of Refunds ",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance","checkInService"},priority=19)
		public void verifyPatientRefund() throws FindFailed, Exception{
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Refunds");
			gPmAssert.assertEquals(billingobj.verifyRefundsLink(), true);
			patientrefundpageobj.clickOnPatientRefund();
			gPmAssert.assertEquals(patientrefundpageobj.verifyPatientRefundPage(), true);
			patientrefundpageobj.patientRefundInsideFrame();
			billingobj.searchPatient();
			billingobj.clickOnCloseButtonInBillingAlerts();
			patientrefundpageobj.switchToRefundInsideFrame();
			insurancepageobj.enterPaymentAmount();
			patientrefundpageobj.patientRefundInsideFrame();
			paymentpageobj.showAllchargesCheckbox();
			patientrefundpageobj.selectPatientRefundRow();
			patientrefundpageobj.patientRefundInsideFrame();
			appointmentpageobj.applyButton();
			patientrefundpageobj.patientRefundFrame();
			billingobj.searchPatientInBilling();
			patientrefundpageobj.patientRefundFrame();
			billingobj.clickOnSearchButton();
			gPmAssert.assertEquals(patientrefundpageobj.verifyPatientRefundQueue(), true);
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that user is able to Create / Modify Insurance Refund in View List of Refunds",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance","checkInService"},priority=20)
		public void verifyInsuranceRefund() throws FindFailed, Exception{
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Refunds");
			gPmAssert.assertEquals(billingobj.verifyRefundsLink(), true);
			insurancerefundpageobj.clickOnInsuranceRefund();
			gPmAssert.assertEquals(insurancerefundpageobj.verifyInsuranceRefundPage(), true);
			insurancepageobj.selectCarrierType();
			patientrefundpageobj.switchToRefundInsideFrame();
			insurancepageobj.enterPaymentAmount();
			billingobj.searchPatientForInsurancePaymentAndRefund();
			billingobj.clickOnCloseButtonInBillingAlerts();
			patientrefundpageobj.switchToRefundInsideFrame();
			paymentpageobj.showAllchargesCheckbox();
			insurancerefundpageobj.selectInsuranceRow();
			appointmentpageobj.clickOnSaveButtonofUserPage();
			insurancerefundpageobj.switchToRefundFrame();
			billingobj.searchPatientInBilling();
			insurancerefundpageobj.switchToRefundFrame();
			billingobj.clickOnSearchButton();
			gPmAssert.assertEquals(insurancerefundpageobj.verifyInsuranceRefundQueue(), true);
			leftPanelpageobj.clickOnLogout();
		}
		@Test(description = "To verify that user is able to see prepay List ",groups = { "gPM_Smoke" },dependsOnMethods ={"verifyGpmIsEnabled","createProvider","createTestPatientwithInsurance","checkInService"},priority=21)
		public void verifyPrePayList() throws FindFailed, Exception{
			loginPageObj.loggedInWithNewUser();
			billingobj.clickOnBilling();
			billingobj.clickOnBillingLeftNavigation("Prepay");
			gPmAssert.assertEquals(billingobj.verifyPrepayLink(), true);
			prepayobj.clickOnNewButtonInPrePay();
			gPmAssert.assertEquals(prepayobj.verifyPrepayPage(), true);
			billingobj.searchPatient();
			prepayobj.switchToPrepayInsideFrame();
			gPmAssert.assertEquals(billingobj.verifyPatientNameIsPopulalted(), true);
			prepayobj.switchToPrepayInsideFrame();
			billingobj.clickOnCloseButtonInBillingAlerts();
			prepayobj.switchToPrepayInsideFrame();
			prepayobj.clickOnPrepayAmount();
			prepayobj.selectAppintment();
			prepayobj.switchToPrepayInsideFrame();
			appointmentpageobj.clickOnSaveButtonofUserPage();
			prepayobj.switchToPrepayFrame();
			billingobj.searchPatientInBilling();
			prepayobj.switchToPrepayFrame();
			billingobj.clickOnSearchButton();
			gPmAssert.assertEquals(prepayobj.verifyPrepayQueue(), true);
			leftPanelpageobj.clickOnLogout();
		}

}
