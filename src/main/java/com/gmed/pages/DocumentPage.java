package com.gmed.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;


import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;

import com.gmed.utils.ConstantsFile;
import com.gmed.utils.DateUtil;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;
import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;

import java.awt.AWTException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DocumentPage extends BaseAbstractPage {
	/** Logger to log the DocumentPage log messages */
	private static Logger	logger							= LogManager.getLogger(DocumentPage.class);
	public static By		codingButton					= By.id("btnEm_SpanBGColor");
	public static By		impressionsection				= By.id("sectionDiagnosis");
	public static By		patientnametextbox				= By.id("txtName_TextBox");
	public static By		searchbutton					= By.id("txtNameSearch");
	public static By		sendButton						= By.className("ToolbarDropDownImage");
	public static By		chiefComplaintsection			= By.id("txtChiefComplaint_TextBox");
	public static By		chiefUserListHeading			= By.id("lblListItem");
	public static By		chiefUserListTextBox			= By.id("txtChiefComplaint_TextBox");
	public static By		historyOfPresentTextBox			= By.id("lblHpi");
	public static By		historyOfPresentIllnesssection	= By.id("sectionHpi_Title");
	public static By		hpiContent						= By.id("sectionHpi_content");
	public static By		VitalSignsection				= By.id("snpVitalSignsMultiple_Title");
	public static By		vitalListHeading				= By.id("lblVitals");
	public static By		weightValue1					= By.id("dtbVitalSignsWeightMajor_DecimalTextBox");
	public static By		weightValue2					= By.id("dtbVitalSignsWeightMajor_DecimalTextBox");
	public static By		heigthValue1					= By.id("dtbVitalSignsHeightMajor_DecimalTextBox");
	public static By		heigthValue2					= By.id("dtbVitalSignsHeightMinor_IntegerTextBox");
	public static By		bmiResult						= By.id("lblVitalBMIResult");
	public static By		vitalData						= By.className("VitalSignsTable");
	public static By		impressionsectionInFirstVisit	= By.id("sectionDiagnosis_content");
	public static By		diagnosisData					= By.id("sectionDiagnosis_content");
	public static By		planSection						= By.id("sectionOrder_content");
	public static By		planUserListData				= By.id("tabSectionUserList_Text");
	public static By		orderData						= By.id("sectionOrder_content");
	public static By		nppTextBox						= By.id("lblNPP");
	public static By		cleanUp							= By.id("btnCleanUp_SpanBGColor");
	public static By		processButton					= By.id("btnProcess_SpanBGColor");
	public static By		excutepassword					= By.id("txtPassword_TextBox");
	public static By		excuteButton					= By.id("btnExecute_SpanBGColor");
	public static By		indicationsectionInColonscopy	= By.id("snpIndication_Title");
	public static By		diagnosisTab					= By.id("tabListDiagnosis");
	public static By		indicationData					= By.id("snpIndication_Data");
	public static By		procedureSection				= By.id("sectionProcedureOverview_Colonoscopy_Title");
	public static By		colonType						= By.id("ribColonoscopyTypeInfoTitle_Label");
	public static By		findingButton					= By.id("snpProcedureFinding_Title");
	public static By		findingHeading					= By.id("tabUserListList_Text");
	public static By		findingribbonTextBox			= By.id("ribDescription_Input_TextBox");
	public static By		InterventionsgButton			= By.id("ribInterventions_InputAdd");
	public static By		sitesTextBox					= By.id("ribSites_Input_TextBox");
	public static By		findingType						= By.className("ProcedureFindingType");
	public static By		findingText						= By.className("ProcedureFindingNarrative");
	public static By		sampleUserList					= By.id("tabSpecimen_Text");
	public static By		iblTextBox						= By.id("lblDescription");
	public static By		specimenCheckbox1				= By.id("chkFindings_Colitis_Render");
	public static By		specimenCheckbox2				= By.id("chkFindings_ROCeliacSprue_Render");
	public static By		specimenCheckbox3				= By.id("chkTestsRequested_Virology_Render");
	public static By		patientPrep						= By.id("snpPrePatientPrepVerification_Title");
	public static By		painAssessment					= By.id("snpPainAssessmentPre_Title");
	public static By		iblTitle						= By.id("lblTitle");
	public static By		scoreTextBox					= By.id("txtScore_TextBox");
	public static By		timetracker						= By.id("snpTimeMarkersPre_Title");
	public static By		codingSection					= By.id("btnProcedureCoding_SpanBGColor");
	public static By		totalcodingtrtags				= By.xpath(".//table[@id='tblDiagnosticList_Table']/tbody/tr");
	public static By		authenticateButton				= By.id("btnAuthenticate_SpanBGColor");
	public static By		closeService					= By.className("TabCloseFocus");
	public static By		currentMedicationSection		= By.id("snpMedication_Data");
	public static By		medicationTab					= By.id("tabOrdersMedications");
	public static By		patientTab						= By.id("tabTree");
	public static By		allergySection					= By.id("snpAllergy_Data");
	public static By		conditionSection				= By.id("snpIllness_Data");
	public static By		immunizationSection				= By.id("snpImmunization_Data");
	public static By		DXSection						= By.id("snpDiagnosticStudy_Data");
	public static By		proceduresection				= By.id("snpProcedure_Data");
	public static By		familySection					= By.id("snpFamilyHistory_Text");
	public static By		alcoholSection					= By.id("snpAlcohol_Data");
	public static By		tobaccoSection					= By.id("snpTobacco_Data");
	public static By		drugSection						= By.id("snpDrug_Data");
	public static By		exerciseSection					= By.id("snpExercise_Data");
	public static By		caffeineSection					= By.id("snpCaffeine_Data");

	// ----
	public static By		docElement						= By.id("pnlTree");
	public static By		docElementTask					= By.className("rtIn");
	public static By		docElementChart					= By.xpath("//span[@class='rtIn']");
	public static By		quickNoteButton					= By.id("btnQuickNote_SpanBGColor");
	public static By		refresh							= By.id("btnRefresh_SpanBGColor");
	public static By		sectionList						= By.xpath("//table[@id='tblComponentList_Table']/tbody/tr/td[1]");
	public static String	quickNoteList1					= "//table[@id='tblList_Table']/tbody/tr[";
	public static String	quickNoteList2					= "]/td[1]";
	public static By		sectionAdditionalText			= By.id("AdditionalNotes_checkbox");
	public static By		publicRadioButton				= By.id("btnPublic_Radio");
	public static By		personalRadioButton				= By.id("btnPersonal_Radio");
	public static By		additionalNote					= By.id("txtNotes_Title");
	public static By		additionalNoteTextBox			= By.id("txtNotes_TextBox");
	public static By		allvisibletext					= By.tagName("body");
	public static By		patientName						= By.id("lblPatientName");
	public static By		accountnum						= By.id("lblRecordNumber");
	public static By		gender							= By.id("lblGender");
	public static By		dob								= By.id("lblDob");
	public static By		date							= By.id("lblDate");
	public static By		referringphysician				= By.id("snpReferringPhysician_Table");
	public static By		referringphysician1				= By.xpath("//table[@id='snpReferringPhysician_Table']/tbody/tr[2]/td[1]");
	public static By		referringphysician2				= By.xpath("//table[@id='snpReferringPhysician_Table']/tbody/tr[3]/td[1]");
	public static By		select1frompopupframe			= By.xpath("//table[@id='tblList_Table']/tbody/tr[1]/td/input[@class='checkboxSelection']");
	public static By		select2frompopupframe			= By.xpath("//table[@id='tblList_Table']/tbody/tr[2]/td/input[@class='checkboxSelection']");
	public static By		selectbtnpopupframe				= By.id("btnSelect_SpanBGColor");
	public static By		pcp								= By.id("snpPcp_Table");
	public static By		pcpselectfrompopupframe			= By.xpath("//table[@id='tblList_Table']/tbody/tr[1]/td[1]");
	public static By		pcp1							= By.xpath("//table[@id='snpPcp_Table']/tbody/tr[2]/td[1]");
	public static By		consulting						= By.id("snpConsulting_Table");
	public static By		consultant1						= By.xpath("//table[@id='snpConsulting_Table']/tbody/tr[2]/td[1]");
	public static By		consultant2						= By.xpath("//table[@id='snpConsulting_Table']/tbody/tr[3]/td[1]");
	public static By		anesthesiaprovider				= By.id("snpAnesthesiologist_Table");
	public static By		anesthesiaprovider1				= By.xpath("//table[@id='snpAnesthesiologist_Table']/tbody/tr[2]/td[1]");
	public static By		nurse							= By.id("snpNurse_Table");
	public static By		nurse1							= By.xpath("//table[@id='snpNurse_Table']/tbody/tr[2]/td[1]");
	public static By		nurse2							= By.xpath("//table[@id='snpNurse_Table']/tbody/tr[3]/td[1]");
	public static By		asaclass						= By.id("snpAsa_Row");
	public static By		asaradiobtnrhtpnl				= By.id("rdoAsa1_Radio");
	public static By		savebtnrhtpnl					= By.id("btnSave_SpanBGColor");
	public static By		staff							= By.id("snpStaff_Table");
	public static By		staff1							= By.xpath("//table[@id='snpStaff_Table']/tbody/tr[2]/td[1]");
	public static By		instruments						= By.id("snpInstrument_Data");
	public static By		instrumentsrhtpnlfold1			= By.xpath("//div[@id='treGeneral']/ul/li[@class='rtLI']/div/span[2]");
	public static By		instrumentsrhtpnlfold2			= By.xpath("//div[@id='treGeneral']/ul/li[@class='rtLI']/ul/li[@class='rtLI']/div/span[2]");
	public static By		instrumentsonrhtpnl				= By.xpath("//div[@id='treGeneral']/ul/li[@class='rtLI']/ul/li[@class='rtLI']/ul/li[@class='rtLI']/div/span[2]");
	public static By		newbutton						= By.id("btnNew_SpanBGColor");
	public static By		instrumentserialnum				= By.id("txtSerialNumber_TextBox");
	public static By		instrumenttypetext				= By.id("txtType_TextBox");
	public static By		livevideo						= By.id("tabCaptureVideo_Text");
	public static By		commonfindings					= By.id("tabCaptureFindingsCommon_Text");
	public static By		generalfindings					= By.id("tabCaptureFindingsGeneral_Text");
	public static By		specimens						= By.id("tabCaptureSpecimens_Text");
	public static By		timetracker1					= By.id("tabCaptureTimeTracker_Text");
	public static By		cancel							= By.id("btnCancel_Image");
	public static By		imagesondocument				= By.xpath("//div[@id='snpVideoCapture_Data']/table/tbody/tr[2]/td[@class='VCSI4']/img[@class='VCSI4']");
	public static By		rclickonimage					= By.xpath("//div[@id='snpVideoCapture_Data']/table/tbody/tr[2]/td[1]");
	public static By		imagesectionondoc				= By.id("snpVideoCapture_Data");
	public static By		imageinsert						= By.id("tblImages_div");
	public static int		imgcount1;
	public static int		imgcount2;
	public static By		hpisection						= By.id("sectionHpi_content");
	public static By		administmedication				= By.id("snpAdministeredMedication_Data");
	public static By		searchmedication				= By.id("txtMedicationSearch");
	public static By		searchmedicationtxtbox			= By.id("txtSearch_TextBox");
	public static By		searchMedication1				= By.id("txtSearchSearch");
	public static By		searchedmedication				= By.xpath("//table[@id='tblList_Table']//img");
	public static By		weightvalue						= By.xpath("//table[@class='vsmTable']/tbody/tr[2]/td[1]");
	public static By		heightvalue						= By.xpath("//table[@class='vsmTable']/tbody/tr[2]/td[2]");
	public static By		bmivalue						= By.xpath("//table[@class='vsmTable']/tbody/tr[2]/td[3]");
	public static By		physicalsection					= By.id("snpPhE_Data");
	public static By		allnormalphysical				= By.id("btnAllNormal_SpanBGColor");
	public static By		modifyphysical					= By.id("btnEdit_SpanBGColor");
	public static By		allnormaldata					= By.xpath("//div[@id='pnlEdit_Div']//span[@type='section']/span[@class='placeHolder']");
	public static By		physicalsectiondata				= By.xpath("//div[@id='snpPhE_Data']//td[@class='PhEN']");
	public static By		proceduresection1				= By.id("sectionProcedureOverview_Colonoscopy_content");
	public static By		colonprocoverviewelements		= By.xpath("//ul/li[1]/div/span[@class='rtIn']");
	public static By		narrativeprocpriorcolonsurg		= By.xpath("//span[@propertyName='PriorColonSurgery']");
	public static By		narrativeprocpriortimeframe		= By.xpath("//span[@propertyName='PriorColonoscopyTimeFrame']");
	public static By		narrativeprocriskassessment		= By.xpath("//span[@propertyName='RiskAssessmentInfo.Title']");
	public static By		narrativeprocinfotitle			= By.xpath("//span[@propertyName='ColonoscopyTypeInfo.Title']");
	public static By		narrativeprocanesthesiatype		= By.xpath("//span[@propertyName='Anesthesia.Type']");
	public static By		narrativeprocadministby			= By.xpath("//span[@propertyName='Anesthesia.AdministeredBy']");
	public static By		narrativeprocsitereached		= By.xpath("//span[@propertyName='SitesReached']");
	public static By		narrativeprocsiteintroduc		= By.xpath("//span[@propertyName='SiteOfIntroduction']");
	public static By		limitationsection				= By.id("snpLimitationComplication_Data");
	public static By		chknolimitationcheckbox			= By.id("chkNoLimitationsOrComplications_Render");
	public static By		findingssection					= By.id("snpProcedureFinding_Div");
	public static By		rhtpnlgeneraltab				= By.id("tabUserListTypes_Text");
	public static By		genrealtabitems					= By.xpath("//div[@id = 'treType_RadTreeView']//span[@class='rtIn']");
	public static By		findingsoverviewelments			= By.xpath("//table/tbody/tr[1]/td[1]");
	public static By		selecteditemsonfindings1		= By.xpath("//div/textarea[starts-with(@id,'rib')]");
	public static By		selecteditemsonfindings2		= By.xpath("//tbody/tr/td/input[starts-with(@id,'rib')]");
	public static By		elementtextonfindings			= By.className("ProcedureFindingNarrative");
	public static By		interventionsection				= By.id("snpProcedureIntervention_Data");
	public static By		impressionsection1				= By.id("snpProcedureImpression_Div");
	public static By		referraltitle					= By.id("txtTitle_TextBox");
	public static By		referralreason					= By.id("txtReason_TextBox");
	public static By		endoscopist						= By.id("snpProvider_Table");
	public static By		samplesection					= By.id("snpProcedureSpecimen_Data");
	public static By		samplespecimenstabrhtpnl		= By.id("tabSpecimen_Text");
	public static By		procspecimenbiopsy				= By.id("chkInterventions_Biopsy_Render");
	public static By		procspecimennormal				= By.id("chkFindings_Normal_Render");
	public static By		procspecimencomment				= By.id("txtComments_TextBox");
	public static By		pathologysection				= By.id("snpPathologySent_TextBox");
	public static By		thumbnails						= By.id("btnThumbnails_SpanBGColor");
	public static By		authenticator					= By.id("txtUserName_TextBox");
	public static By		authenticatorpassword			= By.id("txtPassword_TextBox");
	public static By		providersign					= By.xpath("//table[@id='snpSignatureProvider_Title']//img");
	public static By        lockicon					    = By.xpath("//span[@class='Label']/img");
	public static By        newOrderPopUp                   = By.id("editTemplate");
    public static By        popupCloseButton                = By.id("Popup_CloseButton");

    
    public static Map<String, String>	documentData;
    /**contains the Immunization page data*/
	public static Map<String, String> immunizationData;
	/**
	 * Following variable is used to store the value from the Application and
	 * later on used for comparison
	 */
	public static String				APPVALUE;
	public static String[]				STRARRAY;
	public static String existingImmunizationPatientfirstname;
	public static String existingImmunizationPatientlastname;
	public static String addNewImmunizationForPatient;
	public static final String PATIENT_FIRSTNAME 				                   = "patientfirstname";
	public static final String PATIENT_LASTNAME 				                   = "patientlastname";
	public static final String ADD_NEW_IMMUNIZATION 				               = "addnewcustomimmunization";

	/**
	 * These are the variables which are present on "Document" sheet in the
	 * excel
	 */

	public static String				FIELDS;
	public static String				PROCEDURE;
	public static String				PATIENTNAME;
	public static String				DOB;
	public static String				GENDER;
	public static String				ACCOUNTNUM;
	public static String				INSTRUMENTNAME;
	public static String				INSTRUMENTNUM;
	public static String				INSTRUMENTTYPE;
	public static String				IMAGE_DELETE;
	public static String				IMAGE_YES;
	public static String				LIVE_VIDEO;
	public static String				COMMON_FINDINGS;
	public static String				GENERAL_FINDINGS;
	public static String				SPECIMENS;
	public static String				TIME_TRACKER;
	public static String				IMAGE_REMOVE;
	public static String				ASACLASS_TEXT;
	public static String				ADMINISTERED_MEDICATION;
	public static String				WEIGHT;
	public static String				HEIGHT;
	public static String				LIMITATIONTEXT;
	public static String				REFERRALTITLE;
	public static String				IMAGE_REFERRAL;
	public static String				INTERVENTIONOPTION;
	public static String				FINDINGSOPTION;
	public static String				PATHOLOGYTEXT;
	public static String				AUTHENTICATOR;
	public static String				AUTHENTICATORPWD;
	public static String                UNLOCKPWD;
	public static String                IMPRESSIONTEXT;

	
	public void initClass() throws Exception {
		documentData = ExcelFileUtilty.readExcelSheet("Document");
		FIELDS = documentData.get("fields");
		PROCEDURE = documentData.get("procedure");
		PATIENTNAME = documentData.get("searchedpatient");
		DOB = documentData.get("dob");
		GENDER = documentData.get("gender");
		ACCOUNTNUM = documentData.get("accountnum");
		INSTRUMENTNAME = documentData.get("instrumentname");
		INSTRUMENTNUM = documentData.get("instrumentnum");
		INSTRUMENTTYPE = documentData.get("instrumenttype");
		IMAGE_DELETE = documentData.get("clickondelete");
		IMAGE_YES = documentData.get("clickonyes");
		LIVE_VIDEO = documentData.get("livevideo");
		COMMON_FINDINGS = documentData.get("commonfindings");
		GENERAL_FINDINGS = documentData.get("generalfindings");
		SPECIMENS = documentData.get("specimens");
		TIME_TRACKER = documentData.get("timetracker");
		IMAGE_REMOVE = documentData.get("clickremovefromdocument");
		ASACLASS_TEXT = documentData.get("asaclasstext");
		ADMINISTERED_MEDICATION = documentData.get("administeredmedication");
		WEIGHT = documentData.get("weight");
		HEIGHT = documentData.get("height");
		LIMITATIONTEXT = documentData.get("limitationtext");
		REFERRALTITLE = documentData.get("referraltitle");
		IMAGE_REFERRAL = documentData.get("referralimage");
		INTERVENTIONOPTION = documentData.get("interventiontext");
		FINDINGSOPTION = documentData.get("findingstext");
		PATHOLOGYTEXT = documentData.get("pathologytext");
		AUTHENTICATOR = documentData.get("authenticator");
		AUTHENTICATORPWD = documentData.get("authenticatorpwd");
		UNLOCKPWD = documentData.get("unlockpwd");
		IMPRESSIONTEXT = documentData.get("impressiontext");
		immunizationData                                 = ExcelFileUtilty.readExcelSheet("Immunization");
		existingImmunizationPatientfirstname             = immunizationData.get(PATIENT_FIRSTNAME);
		existingImmunizationPatientlastname              = immunizationData.get(PATIENT_LASTNAME);
		addNewImmunizationForPatient                     = immunizationData.get(ADD_NEW_IMMUNIZATION);
	}
	/**
	 * This method is used for click on first visit present in medical chart
	 * 
	 */
	public boolean verifyFirstVisitTab() {
		boolean codetext = false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		sleep(10000);
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		codetext = SeleniumUtil.getElementWithFluentWait(codingButton) != null;
		if (codetext) {
			System.out.println("First Visit Template is displated");
			codetext = true;
		}
		return codetext;
	}

	/**
	 * This method is used for click on indication section present in first
	 * visit template
	 * 
	 * @throws Exception
	 */
	public void clickOnIndication() throws AWTException, Exception {
		ConstantsFile.switchframevarible = true;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement impressionvalue1 = SeleniumUtil.getElementWithFluentWait(impressionsection);
		SeleniumUtil.scrolltoWebElement(impressionvalue1);
		try {
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnImpression");
		} catch (Exception e) {
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnDiagnostic");
		}
	}

	/**
	 * This method is used select the indication value form the user list
	 * 
	 */
	public boolean verifyIndicationValueFromUserList() {
		boolean isIndicationUserListPresent = false;
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserListDiagnosis_Frame");
		sleep(5000);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys("D10.0");
		SeleniumUtil.getElementWithFluentWait(searchbutton).click();
		isIndicationUserListPresent = true;
		sleep(5000);
		WebElement plussign = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'Benign')]"));
		action.moveToElement(plussign).doubleClick().build().perform();
		return isIndicationUserListPresent;
	}

	/**
	 * This method is used to click on coding present in first visit template
	 * 
	 */
	public void clickOnCoding() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.getElementWithFluentWait(codingButton).click();
	}

	/**
	 * This method is used for click on NPP and it will select the value from
	 * the NPP drop down
	 * 
	 * @throws FindFailed
	 * 
	 */
	public boolean clickOnNPP() throws FindFailed {
		boolean isNppvalueIspopulated = false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.addTextBoxValue("ddlNPP_Text", "Low to Moderate Severity (Level 2)");
		/*SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("H_NPI_DropDown");
		SeleniumUtil.clickOnImageWitScreenInSikuli("H_NPI_DropDown_Values");*/
		isNppvalueIspopulated = true;
		return isNppvalueIspopulated;
	}

	/**
	 * This method is used for click on export button present in process drop
	 * down
	 * 
	 * @throws InterruptedException
	 * @throws FindFailed
	 * 
	 */
	public void clickOnProcessDropDown() throws InterruptedException, FindFailed {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		List<WebElement> buttons = driver.findElements(sendButton);
		WebElement query_enquirymode = buttons.get(1);
		query_enquirymode.click();
		Thread.sleep(5000);
	}

	public void clickOnExportCodes() throws FindFailed {
		SeleniumUtil.clickOnImageWitScreenInSikuli("exportcode");
	}

	/**
	 * This method is used for click on NPP and it will select the value from
	 * the NPP drop down
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnOkay() throws FindFailed {
		SeleniumUtil.clickOnImageWitScreenInSikuli("okButton");
	}

	public void clickOnYesPopUp() throws FindFailed, Exception {
		try {
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
		} catch (Exception e) {
			System.out.println("No popup present");
		}
	}

	/**
	 * This method is used to click on chief complain section present in first
	 * visit service
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnChiefComplain() throws FindFailed {
		sleep(15000);
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement chiefComplaintvalue1 = SeleniumUtil.getElementWithFluentWait(chiefComplaintsection);
		SeleniumUtil.scrolltoWebElement(chiefComplaintvalue1);
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnChief");
	}

	/**
	 * This method is used to select chief user list is opened in first visit
	 * service
	 * 
	 */
	public boolean verifyChiefUserListIsOpened() {
		boolean isChiefUserListPresent = false;
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		String chiefUserList = SeleniumUtil.getElementWithFluentWait(chiefUserListHeading).getText();
		if (chiefUserList.equalsIgnoreCase("Chief Complaint")) {
			System.out.println("Chief Complaint Right panel is  displayed");
			isChiefUserListPresent = true;
		}
		return isChiefUserListPresent;
	}

	/**
	 * This method is used to select value from chief user list
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void selectChiefValueFromUserList() {
		sleep(5000);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys("gas");
		SeleniumUtil.getElementWithFluentWait(Profile.searchIcon).click();
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		sleep(5000);
		WebElement plussign = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'gas')]"));
		action.moveToElement(plussign).doubleClick().build().perform();
	}

	/**
	 * This method is used to verify chief value is documented in first visit
	 * service
	 * 
	 */
	public boolean verifyChiefValueIsDocumentedInUserList() {
		boolean isChiefValueDocumented = false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String chiefComplaintTextvalue = SeleniumUtil.getElementWithFluentWait(chiefUserListTextBox).getText();
		System.out.println("chief Value is"+chiefComplaintTextvalue);
		if (chiefComplaintTextvalue.isEmpty()) {
			System.out.println("chief Value is not documented");
		}
		else if(chiefComplaintTextvalue.equalsIgnoreCase("gas")) {
			System.out.println("chief Value is documented");
			isChiefValueDocumented = true;
		}
		return isChiefValueDocumented;
	}

	/**
	 * This method is used to click on History Of Present Illness section
	 * present in first visit service
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnHistoryOfPresentIllness() throws FindFailed {
		ConstantsFile.switchframevarible = true;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement historyOfPresentvalue1 = SeleniumUtil.getElementWithFluentWait(historyOfPresentIllnesssection);
		SeleniumUtil.scrolltoWebElement(historyOfPresentvalue1);
		SeleniumUtil.clickOnImageWitScreenInSikuli("HistoryOfPresentIllness");
	}

	/**
	 * This method is used to verify History Of Present Illness user list is
	 * opened in first visit service
	 * 
	 */
	public boolean verifyHistoryOfPresentIllnessUserListIsOpened() {
		boolean isHistoryOfPresentIllnessPresent = false;
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		String chiefUserList = SeleniumUtil.getElementWithFluentWait(historyOfPresentTextBox).getText();
		if (chiefUserList.equalsIgnoreCase("History of Present Illness")) {
			System.out.println("History of Present Illness Right panel is  displayed");
			isHistoryOfPresentIllnessPresent = true;
		}
		return isHistoryOfPresentIllnessPresent;
	}

	/**
	 * This method is used to select value from History Of Present Illness
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void selectHistoryOfPresntValueFromUserList() {
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys("Intro");
		SeleniumUtil.getElementWithFluentWait(Profile.searchIcon).click();
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		sleep(5000);
		WebElement plussign = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'Intro')]"));
		action.moveToElement(plussign).doubleClick().build().perform();
	}

	/**
	 * This method is used to verify History Of Present Illness value is
	 * documented in first visit service
	 * 
	 */
	public boolean verifyHistoryOfPresentIllnessIsDocumentedInUserList() {
		boolean isHistoryOfPresentIllnessDocumented = false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String chiefComplaintTextvalue = SeleniumUtil.getElementWithFluentWait(hpiContent).getText();
		if (chiefComplaintTextvalue.isEmpty()) {
			System.out.println("The selected item did not get populated in the History Of present Illness field.");
		} else {
			System.out.println("The selected item get populated in the History Of present Illness field");
			isHistoryOfPresentIllnessDocumented = true;
		}
		return isHistoryOfPresentIllnessDocumented;
	}

	/**
	 * This method is used to click on vital section present in first visit
	 * service
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnVital() throws FindFailed {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement chiefComplaintvalue1 = SeleniumUtil.getElementWithFluentWait(VitalSignsection);
		SeleniumUtil.scrolltoWebElement(chiefComplaintvalue1);
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnVitalSign");
	}

	/**
	 * This method is used to verify vital sign user list is opened in first
	 * visit service
	 * 
	 */
	public boolean verifyVitalSignUserListIsOpened() throws Exception {
		boolean isVitalSignPresent = false;
		Thread.sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		Thread.sleep(5000);
		String chiefUserList = SeleniumUtil.getElementWithFluentWait(vitalListHeading).getText();
		if (chiefUserList.equalsIgnoreCase("Vital Signs")) {
			System.out.println("Vital Signs Right panel is  displayed");
			isVitalSignPresent = true;
		}
		return isVitalSignPresent;
	}

	/**
	 * This method is used to enter vital details in vital section
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void EnterVitalInformation() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		WebElement vitalweighvalue1 = SeleniumUtil.getElementWithFluentWait(weightValue1);
		vitalweighvalue1.sendKeys("11");
		WebElement vitalweighvalue2 = SeleniumUtil.getElementWithFluentWait(weightValue2);
		vitalweighvalue2.sendKeys("2");
		WebElement vitalheightvalue1 = SeleniumUtil.getElementWithFluentWait(heigthValue1);
		vitalheightvalue1.sendKeys("4");
		WebElement vitalheightvalue2 = SeleniumUtil.getElementWithFluentWait(heigthValue2);
		vitalheightvalue2.sendKeys("7");
	}

	/**
	 * This method is used to verify BMI result is calculated in first visit
	 * service
	 * 
	 */
	public boolean verifyBMIResultIsCalculatedInUserList() {
		boolean isBMIcalculated = false;
		String chiefComplaintTextvalue = SeleniumUtil.getElementWithFluentWait(bmiResult).getText();
		if (chiefComplaintTextvalue.isEmpty()) {
			System.out.println("BMI is not calculated");
		} else {
			System.out.println("BMI is  calculated");
			isBMIcalculated = true;
		}
		return isBMIcalculated;
	}

	/**
	 * This method is used for save the details
	 * 
	 */
	public void clickOnSaveButtonInVitalSign() {
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.savebutton).click();
	}

	/**
	 * This method is used to verify Vital value is documented in first visit
	 * service
	 * 
	 */
	public boolean verifyVitalDataIsDocumented() {
		boolean isVitalSignDocumented = false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String chiefComplaintTextvalue = SeleniumUtil.getElementWithFluentWait(vitalData).getText();
		if (chiefComplaintTextvalue.isEmpty()) {
			System.out.println("The selected item did not get populated in vital sign field.");
		} else {
			System.out.println("The selected item get populated in the vital sign field");
			isVitalSignDocumented = true;
		}
		return isVitalSignDocumented;
	}

	/**
	 * This method is used to click on Impression section present in first visit
	 * service
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnImpression() throws FindFailed {
		ConstantsFile.switchframevarible = true;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement chiefComplaintvalue1 = SeleniumUtil.getElementWithFluentWait(impressionsectionInFirstVisit);
		SeleniumUtil.scrolltoWebElement(chiefComplaintvalue1);
		/*
		 * try{ SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnImpression");
		 * } catch(Exception e){
		 * SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnDiagnostic"); }
		 */

		SeleniumUtil.doubleClick(chiefComplaintvalue1);
	}

	public void selectIndicationValueFromUserList() {
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserListDiagnosis_Frame");
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys("D12.8");
		SeleniumUtil.getElementWithFluentWait(Profile.searchIcon).click();
		sleep(5000);
		WebElement plussign = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'Benign')]"));
		action.moveToElement(plussign).doubleClick().build().perform();
	}

	/**
	 * This method is used to verify Impression value is documented in first
	 * visit service
	 * 
	 */
	public boolean verifyImpressionValueIsDocumented() {
		boolean isImpressionDocumented = false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String chiefComplaintTextvalue = SeleniumUtil.getElementWithFluentWait(diagnosisData).getText();
		if (chiefComplaintTextvalue.isEmpty()) {
			System.out.println("The selected item did not get populated in the impression field.");
		} else {
			System.out.println("The selected item get populated in the impression field");
			isImpressionDocumented = true;
		}
		return isImpressionDocumented;
	}

	/**
	 * This method is used to click on plan section present in first visit
	 * service
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnPlan() throws FindFailed {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(By.id("sectionOrder_content"));
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used to verify History Of plan user list is opened in
	 * first visit service
	 * 
	 */
	public boolean verifyPlanUserListIsOpened() {
		boolean isPlanUserListPresent = false;
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraPlan_Frame");
		String planUserList = SeleniumUtil.getElementWithFluentWait(planUserListData).getText();
		System.out.println(planUserList.trim());
		String value = planUserList.trim();
		System.out.println(value);
		if (value.equalsIgnoreCase("My Orders")) {
			System.out.println("My Orders Right panel is  displayed");
			isPlanUserListPresent = true;
		}
		return isPlanUserListPresent;
	}

	/**
	 * This method is used to select plan value from the user list in first
	 * visit service
	 * 
	 * 
	 */
	public void selectPlanvalueFromUserList() {
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraSectionUserList_Frame");
		sleep(5000);
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys("Follow-up");
		SeleniumUtil.getElementWithFluentWait(Profile.searchIcon).click();
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		sleep(5000);
		WebElement plussign = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'Follow-up')]"));
		action.moveToElement(plussign).doubleClick().build().perform();
	}

	/**
	 * This method is used to verify order value is documented in first visit
	 * service
	 * 
	 */
	public boolean verifyOrderDataIsDocumented() {
		boolean isOrderDocumented = false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String chiefComplaintTextvalue = SeleniumUtil.getElementWithFluentWait(orderData).getText();
		if (chiefComplaintTextvalue.isEmpty()) {
			System.out.println("The selected item did not get populated in order field.");
		} else {
			System.out.println("The selected item get populated in the order field");
			isOrderDocumented = true;
		}
		return isOrderDocumented;
	}

	/**
	 * This method is used to verify coding popup is opened
	 * 
	 */
	public boolean verifyCodingPopupIsDisplayed() {
		boolean isCodingScreenDisplayed = false;
		sleep(5000);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String codingText = SeleniumUtil.getElementWithFluentWait(nppTextBox).getText();
		if (codingText.equalsIgnoreCase("Nature of Presenting Problem")) {
			System.out.println("The Coding details screen is displayed.");
			isCodingScreenDisplayed = true;
		}
		return isCodingScreenDisplayed;
	}

	/**
	 * This method is used to click on clean up section
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnCleanUp() {
		ConstantsFile.switchframevarible = true;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.getElementWithFluentWait(cleanUp).click();
	}

	/**
	 * This method is used to click on Process button
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnProcess() throws AWTException, Exception {
		ConstantsFile.switchframevarible = true;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.getElementWithFluentWait(processButton).click();
		sleep(5000);
	}

	public boolean verifyOutPutManagerScreenIsOpen() {
		boolean isOutputManagerScreenPresent = false;
		SeleniumUtil.waitForProgressBar(Frames.AUTHENTICATATION);
		SeleniumUtil.switchToParentFrame(Frames.AUTHENTICATATION);
		isOutputManagerScreenPresent = SeleniumUtil.getElementWithFluentWait(excutepassword) != null;
		if (isOutputManagerScreenPresent) {
			System.out.println("output manager screen is displayed");
			isOutputManagerScreenPresent = true;
		}
		return isOutputManagerScreenPresent;
	}

	/**
	 * This method is used to click on Execute button
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickonExecute() {
		SeleniumUtil.switchToParentFrame(Frames.AUTHENTICATATION);
		SeleniumUtil.getElementWithFluentWait(excutepassword).sendKeys("12");
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(excuteButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(10000);

	}

	/**
	 * This method is used to configuration output manager configuration for
	 * Colonscopy service
	 * 
	 * @throws Exception
	 */
	public void clickOnColonscopyInService() throws Exception {
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		List<WebElement> totalrows = driver.findElements(By.xpath("//table[@id='tblServiceDocuments_Table']/tbody/tr"));
		System.out.println("table name size" + totalrows.get(16).getText());
		WebElement table = totalrows.get(16);
		List<WebElement> tdList = table.findElements(By.xpath(".//td/control"));
		System.out.println(tdList.size());
		int i = 0;
		for (WebElement e4 : tdList) {
			System.out.println("starting of loop" + i);
			SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
			System.out.println(e4.getAttribute("type"));
			String id1 = e4.getAttribute("id");
			if (i <= 1) {
				System.out.println(e4.getText());
				System.out.println("id text" + id1);
				WebElement trrr = SeleniumUtil.getElementWithFluentWait(By.id(id1 + "_Render"));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
				i++;
			} else if (i == 2) {
				WebElement trrr = SeleniumUtil.getElementWithFluentWait(By.id(id1));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
				SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
				WebElement activevalue = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'My Local Printer')]"));
				activevalue.click();
				SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
				SeleniumUtil.getElementWithFluentWait(MedicalChartPage.selectbutton).click();
				SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
				SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
				i++;
			} else if (i == 3) {
				WebElement trrr = SeleniumUtil.getElementWithFluentWait(By.id(id1));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
				SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("selectProvider");
				i++;
			} else if (i == 4) {
				WebElement trrr = SeleniumUtil.getElementWithFluentWait(By.id(id1 + "_Render"));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
				i++;
			} else if (i == 5) {
				WebElement trrr = SeleniumUtil.getElementWithFluentWait(By.id("input_" + id1 + "_Render"));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
				i++;
			} else if (i == 6) {
				WebElement trrr = SeleniumUtil.getElementWithFluentWait(By.id("input_" + id1 + "_Render"));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
			}
		}
	}

	/**
	 * This method is used for click on indication section present in colonscopy
	 * visit template
	 * 
	 * @throws FindFailed
	 * 
	 * 
	 */
	public void clickOnIndicationInColonscopy() throws FindFailed {
		sleep(10000);
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement impressionvalue1 = SeleniumUtil.getElementWithFluentWait(indicationsectionInColonscopy);
		SeleniumUtil.scrolltoWebElement(impressionvalue1);
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnIndication");
	}

	/**
	 * This method is used to click on impression tab present in Colonscopy
	 * service
	 * 
	 */
	public void clickOnIndicationTabInImpression() {
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(6000);
		SeleniumUtil.getElementWithFluentWait(diagnosisTab).click();
	}

	/**
	 * This method is used to verify Indication UserList is opened in Colonscopy
	 * service
	 *
	 */
	public boolean verifyIndicationUserListIsOpen() {
		boolean isIndicationUserListPresent = false;
		sleep(5000);
		String indicationUserList = SeleniumUtil.getElementWithFluentWait(diagnosisTab).getText().trim();
		if (indicationUserList.equalsIgnoreCase("Indications")) {
			System.out.println("Indications Right panel is displayed");
			isIndicationUserListPresent = true;
		}
		return isIndicationUserListPresent;
	}

	/**
	 * This method is used to verify verify Indication value is documented in
	 * Colonscopy service
	 * 
	 */
	public boolean verifyIndicationValueIsDocumentedInUserList() {
		boolean isIndicationValueDocumented = false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String indicatTextvalue = SeleniumUtil.getElementWithFluentWait(indicationData).getText();
		if (indicatTextvalue.isEmpty()) {
			System.out.println("Indication Value is not documented");
		} else {
			System.out.println("Indication Value is documented");
			isIndicationValueDocumented = true;
		}
		return isIndicationValueDocumented;
	}

	/**
	 * This method is used for click on procedure overview section present in
	 * colonscopy template
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnProcedureOverview() throws FindFailed {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement procedureOverviewvalue = SeleniumUtil.getElementWithFluentWait(procedureSection);
		SeleniumUtil.scrolltoWebElement(procedureOverviewvalue);
		SeleniumUtil.doubleClickOnImageWitScreenInSikuli("clickOnProcedure");
		/*
		 * screen.wait(System.getProperty("user.dir") +
		 * "\\Images\\clickOnProcedure.png", 30.0);
		 * screen.doubleClick(System.getProperty("user.dir") +
		 * "\\Images\\clickOnProcedure.png");
		 */
	}

	/**
	 * This method is used to verify colonoscopy procedure overview tool tip is
	 * opened in Colonscopy service
	 * 
	 */
	public boolean verifyColonscopyProcedureOverviewRibbonIsOpen() {
		boolean isColonscopyProcedureOverviewRibbonPresent = false;
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		sleep(5000);
		String colonscopyProcedureOverviewUserList = SeleniumUtil.getElementWithFluentWait(colonType).getText();
		if (colonscopyProcedureOverviewUserList.equalsIgnoreCase("Colonoscopy Type")) {
			System.out.println("Colonoscopy Procedure Overview Ribbon is opened.");
			isColonscopyProcedureOverviewRibbonPresent = true;
		}
		return isColonscopyProcedureOverviewRibbonPresent;
	}

	/**
	 * This method is used select the colonscopy type present in procedure
	 * overview popup
	 * 
	 */
	public void selectColonTypeFromColonscopyProcedureOverview() {
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement colonscopyvalue = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'Diagnostic')]"));
		SeleniumUtil.scrolltoWebElement(colonscopyvalue);
		colonscopyvalue.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}

	/**
	 * This method is used select the Risk Assessment present in procedure
	 * overview popup
	 * 
	 * @throws Exception
	 */
	public void selectRiskAssessmentFromColonscopyProcedureOverview() {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement colonscopyvalue = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'average')]"));
		SeleniumUtil.scrolltoWebElement(colonscopyvalue);
		colonscopyvalue.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}

	/**
	 * This method is used to click on Finding section present in colonscopy
	 * template
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnFindings() throws FindFailed {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement findingvalues = SeleniumUtil.getElementWithFluentWait(findingButton);
		SeleniumUtil.scrolltoWebElement(findingvalues);
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnFinding");
	}

	/**
	 * This method is used to verify finding user is opened in Colonscopy
	 * service
	 * 
	 */
	public boolean verifyFindingUserListIsOpen() {
		boolean isFindingUserListPresent = false;
		sleep(9000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(9000);
		String findingUserList = SeleniumUtil.getElementWithFluentWait(findingHeading).getText().trim();
		if (findingUserList.equalsIgnoreCase("Common Findings")) {
			System.out.println("Common Findings Right panel is  displayed");
			isFindingUserListPresent = true;
		}
		return isFindingUserListPresent;
	}

	/**
	 * This method is used select the Finding value from the finding userlist
	 * 
	 */
	public void selectFindingValueFromUserList() {
		sleep(9000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(9000);
		SeleniumUtil.switchToFrame(driver, "fraUserListList_Frame");
		WebElement findingvalues = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'normal colonoscopy exam')]"));
		action.moveToElement(findingvalues).doubleClick().build().perform();
	}

	/**
	 * This method is used to verify finding Ribbon is opened in Colonscopy
	 * service
	 * 
	 */
	public boolean verifyFindingRibbonIsOpen() {
		boolean isFindingRibbonPresent = false;
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String chiefUserList = SeleniumUtil.getElementWithFluentWait(findingribbonTextBox).getText();
		if (chiefUserList.isEmpty()) {
			System.out.println("Finding Ribbon is not opened.");
			isFindingRibbonPresent = true;
		} else {
			System.out.println("Finding Ribbon is opened.");
			isFindingRibbonPresent = true;
		}
		return isFindingRibbonPresent;
	}

	/**
	 * This method is used select the Interventions value
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void selectInterventionsValueInAdditionalFindings() throws FindFailed {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		sleep(3000);
		WebElement interventionvalue = SeleniumUtil.getElementWithFluentWait(InterventionsgButton);
		action.moveToElement(interventionvalue).click().build().perform();
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("selectBioFromIntervention");
	}

	/**
	 * This method is used to verify Interventions Popup Is Opened in Colonscopy
	 * service
	 * 
	 */
	public boolean verifySelectInterventionsPopupIsOpened() {
		boolean isSelectedInterventionsPopupPresent = false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
		WebElement inventionsPopup = SeleniumUtil.getElementWithFluentWait(sitesTextBox);
		if (inventionsPopup != null) {
			System.out.println("the Ribbon corresponding to the selected item is  opened ");
			isSelectedInterventionsPopupPresent = true;
		} else {
			System.out.println("the Ribbon corresponding to the selected item is not opened ");
			isSelectedInterventionsPopupPresent = false;
		}
		return isSelectedInterventionsPopupPresent;
	}

	/**
	 * This method is used select items from Biopsy PopUp
	 * 
	 */
	public void selectItemFromBiopsyPopUp() {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		selectDeviceFromBiopsyPopUp();
	}

	/**
	 * This method is used select device from Biopsy PopUp
	 * 
	 */
	public void selectDeviceFromBiopsyPopUp() {
		driver.switchTo().frame("DialogBox_Frame");
		WebElement colonscopyvalue = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'snare')]"));
		SeleniumUtil.scrolltoWebElement(colonscopyvalue);
		colonscopyvalue.click();
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
	}

	/**
	 * This method is used to verify Finding type is documented in finding
	 * section present in Colonscopy service
	 *
	 */
	public boolean verifyFindingTypeIsDocumentedInUserList() {
		boolean isFindingValueDocumented = false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String chiefComplaintTextvalue = SeleniumUtil.getElementWithFluentWait(findingType).getText();
		if (chiefComplaintTextvalue.isEmpty()) {
			System.out.println("Finding Type is not documented");
		} else {
			System.out.println("Finding Type is documented");
			isFindingValueDocumented = true;
		}
		return isFindingValueDocumented;
	}

	/**
	 * This method is used to verify Finding text is documented in finding
	 * section present in Colonscopy service
	 * 
	 */
	public boolean verifyFindingTextIsDocumentedInUserList() {
		boolean isFindingTextDocumented = false;
		String findingTextvalue = SeleniumUtil.getElementWithFluentWait(findingText).getText();
		if (findingTextvalue.isEmpty()) {
			System.out.println("Finding text is not documented");
		} else {
			System.out.println("Finding text is documented");
			isFindingTextDocumented = true;
		}
		return isFindingTextDocumented;
	}

	/**
	 * This method is used to click on sample section present in Colonscopy
	 * service
	 * 
	 * @throws FindFailed
	 * @throws Exception
	 */
	public void clickOnSample() throws FindFailed {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement findingvalues = SeleniumUtil.getElementWithFluentWait(findingButton);
		SeleniumUtil.scrolltoWebElement(findingvalues);
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnSample");
	}

	/**
	 * This method is used to verify sample user list is opened in Colonscopy
	 * service
	 * 
	 */
	public boolean verifySampleUserListIsOpen() {
		sleep(9000);
		boolean isySampleUserListPresent = false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		String chiefUserList = SeleniumUtil.getElementWithFluentWait(sampleUserList).getText().trim();
		if (chiefUserList.equalsIgnoreCase("Specimens")) {
			System.out.println("Specimens Right panel is  displayed");
			isySampleUserListPresent = true;
		}
		return isySampleUserListPresent;
	}

	/**
	 * This method is used to click on new button present in sample user list
	 * 
	 * @throws Exception
	 */
	public void clickOnNewButtonInSampleUserList() {
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraSpecimens_Frame");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton).click();
	}

	/**
	 * This method is used to verify Procedure Speciman Popup Is Opened in
	 * Colonscopy service
	 * 
	 * @throws Exception
	 */
	public boolean verifyProcedureSpecimanPopupIsOpened() {
		boolean isProcedureSpecimanPopupPresent = false;
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String inventionsPopup = SeleniumUtil.getElementWithFluentWait(iblTextBox).getText();
		if (inventionsPopup.isEmpty()) {
			System.out.println("Procedure Speciman Popup is not opened");
		} else {
			System.out.println("Procedure Speciman Popup is opened");
			isProcedureSpecimanPopupPresent = true;
		}
		return isProcedureSpecimanPopupPresent;
	}

	/**
	 * This method is used to click on cecum section present in procedure
	 * Specimen image
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void enterProcedureSpecimenDetails() throws FindFailed {
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnCecum");
	}

	/**
	 * This method is used to select checkbox values in procedure Specimen image
	 * 
	 */
	public void selectRandomValuesInProcedureSpecimen() {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement checkboxvalue1 = SeleniumUtil.getElementWithFluentWait(specimenCheckbox1);
		checkboxvalue1.click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement checkboxvalue2 = SeleniumUtil.getElementWithFluentWait(specimenCheckbox2);
		checkboxvalue2.click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement checkboxvalue3 = SeleniumUtil.getElementWithFluentWait(specimenCheckbox3);
		checkboxvalue3.click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
	}

	/**
	 * This method is used to click on Thumbrails
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnThumbrails() throws FindFailed {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		List<WebElement> buttons = driver.findElements(PrescriptionPage.toolbarbutton);
		WebElement query_enquirymode = buttons.get(0);
		query_enquirymode.click();
		sleep(5000);
		SeleniumUtil.clickOnImageWitScreenInSikuli("preProcedureNursingNote");
	}

	/**
	 * This method is used to click on Time Tracker
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnTimeTracker() throws AWTException, Exception {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement findingvalues = SeleniumUtil.getElementWithFluentWait(timetracker);
		SeleniumUtil.scrolltoWebElement(findingvalues);
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnTimeMarker");
	}

	/**
	 * This method is used to verify Time tracker pop is opened
	 * 
	 */
	public boolean verifyTimeTrackerPopupIsOpened() {
		boolean isTimeTrackerPopupPresent = false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		String inventionsPopup = SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow).getText();
		if (inventionsPopup.isEmpty()) {
			System.out.println("Procedure Speciman Popup is not opened");
		} else {
			System.out.println("Procedure Speciman Popup is opened");
			isTimeTrackerPopupPresent = true;
		}
		return isTimeTrackerPopupPresent;
	}

	/**
	 * This method is used to click on Now button present in Time tracker pop
	 * 
	 * @throws Exception
	 */
	public void clickOnNowButtonInTimeTracker() {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		List<WebElement> tr_collection11 = driver.findElements(By.xpath("//table[@id='tblServiceTimeMarkers_Table']/tbody/tr"));
		System.out.println("NUMBER OF ROWS IN THIS TABLE = " + tr_collection11.size());
		int row_num, col_num;
		row_num = 1;
		col_num = 1;
		String id11 = null;
		for (WebElement trElement : tr_collection11) {
			List<WebElement> td_collection11 = trElement.findElements(By.xpath("//td/control[3]/table"));
			for (WebElement thirdElement : td_collection11) {
				System.out.println("row # " + row_num + ", col # " + col_num + "text=" + thirdElement.getText() + "id attribute # " + thirdElement.getAttribute("id"));
				String t1 = thirdElement.getAttribute("id");
				System.out.println(t1);
				String id1 = thirdElement.getAttribute("id");
				WebElement trrr = SeleniumUtil.getElementWithFluentWait(By.id(id1));
				trrr.click();
				SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
			}
			break;
		}
	}

	/**
	 * This method is used to click on patient preparation section present in
	 * colonscopy service
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnPatientPrep() throws FindFailed {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement findingvalues = SeleniumUtil.getElementWithFluentWait(patientPrep);
		SeleniumUtil.scrolltoWebElement(findingvalues);
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnPatientPrep");
	}

	/**
	 * This method is used to select multiple same image in patient preparation
	 * tool tip
	 * 
	 * @throws FindFailed
	 * @throws Exception
	 */
	public void selectValueInPatientPrep() throws FindFailed {
		SeleniumUtil.waitForProgressBar(Frames.PATIENPREPARATION);
		sleep(9000);
		SeleniumUtil.multiplePattern();
		SeleniumUtil.switchToParentFrame(Frames.PATIENPREPARATION);
		WebElement skincondtion = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'skin warm, dry and pink')]"));
		action.moveToElement(skincondtion).click().build().perform();
	}

	/**
	 * This method is used to click on pain Assessment section present in
	 * colonscopy service
	 * 
	 * @throws Exception
	 */
	public void clickOnPainAssessment() throws AWTException, Exception {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement findingvalues = SeleniumUtil.getElementWithFluentWait(painAssessment);
		SeleniumUtil.scrolltoWebElement(findingvalues);
		SeleniumUtil.clickOnImageWitScreenInSikuli("painAssessment");
	}

	/**
	 * This method is used to verify Pain Assessment user list is opened
	 * 
	 */
	public boolean verifyPainAssessmentUserListIsOpen() {
		boolean isPainAssessmentUserListPresent = false;
		sleep(9000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		String chiefUserList = SeleniumUtil.getElementWithFluentWait(iblTitle).getText().trim();
		if (chiefUserList.equalsIgnoreCase("Pain Assessments")) {
			System.out.println("Pain Assessments Right panel is  displayed");
			isPainAssessmentUserListPresent = true;
		}
		return isPainAssessmentUserListPresent;
	}

	/**
	 * This method is used to click on new item in Pain Assessment user list is
	 * opened
	 * 
	 */
	public void createNewIteminPainAssessment() {
		sleep(9000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(scoreTextBox).sendKeys("10 out of 7");
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}

	/**
	 * This method is used to click on coding present in first visit template
	 * 
	 */
	public void processCoding() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.getElementWithFluentWait(codingSection).click();
	}

	/**
	 * This method is used to verify in coding section diagnosis code should be
	 * populated as mentioned in indication section
	 * 
	 * @throws Exception
	 */
	public boolean verifyCodingData() {
		boolean isCodingDataPresent = false;
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		sleep(5000);
		List<WebElement> totaltrrows = driver.findElements(totalcodingtrtags);
		System.out.println(totaltrrows);
		for (WebElement irows : totaltrrows) {
			String rowtext = irows.getText();
			System.out.println(rowtext);
			if (rowtext.contains("Benign neoplasm of rectum") && rowtext.contains("D12.8")) {
				System.out.println("same diagnosis code is populated in coding section");
				isCodingDataPresent = true;
			}
		}
		return isCodingDataPresent;
	}

	public void clickOnSign() throws FindFailed {
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnSign");
	}

	public void clickOnAuthentication() {
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.getElementWithFluentWait(excutepassword).sendKeys("12");
		SeleniumUtil.getElementWithFluentWait(authenticateButton).click();
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
		sleep(10000);
	}

	public void clickOnCharges() throws FindFailed {
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnCharges");
	}

	public void closeColonoscopyService() {
		DynamicFramePage.dynamicFrameForPanchart();
		SeleniumUtil.getElementWithFluentWait(closeService).click();
	}

	// This method will verfy if the created colonoscpy is created succeefully
	// and if yes then is it displayed in Document page.
	public boolean verifyColonoscopycreated() {
		logger.info("Inside verifyColonoscopycreated method");
		DynamicFramePage.switchtoFraFrame();
		String text = SeleniumUtil.getElementWithFluentWait(docElement).getText();
		if (text.contains("Colonoscopy")) {
			System.out.println("Pass");
			return true;
		}
		System.out.println("Fail");
		return false;

	}

	/**
	 * This method is deleting the Quick note created
	 * 
	 * @param viewtext
	 */
	public void deleteQuickNote(String viewtext) {
		logger.info("Inside the delete quicknote method");
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		int i = 1;
		while (!(SeleniumUtil.getElementWithFluentWait(By.xpath(quickNoteList1 + i + quickNoteList2)).getText().contains(viewtext))) {
			System.out.println(SeleniumUtil.getElementWithFluentWait(By.xpath(quickNoteList1 + i + quickNoteList2)).getText());
			i++;
		}
		WebElement quicknote = SeleniumUtil.getElementWithFluentWait(By.xpath(quickNoteList1 + i + quickNoteList2));
		SeleniumUtil.rightClick(quicknote);
		try {
			SeleniumUtil.clickOnImageWitScreenInSikuli("deleteUser");
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to click on Quick Note Tab in document bar
	 */
	public void clickOnQuickNote() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		sleep(1000);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.quickNoteButton).click();
	}

	/**
	 * This method is used to Click on Specific Quick Note passed as parameter
	 * from the Dropdown
	 * 
	 * @param imagename
	 */
	public boolean clickOnQuickNoteItems(String imagename) {
		try {
			SeleniumUtil.clickOnImageWitScreenInSikuli(imagename);
			return true;
		} catch (FindFailed e) {
			return false;
		}
	}

	/**
	 * This method is used to create a quick Note
	 */
	public void creatQuickNote(By radiobutton, String quicknotename) {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		sleep(1000);
		// SeleniumUtil.getElementWithFluentWait(By.id("tblComponentList_checkbox")).click();
		List<WebElement> ele = driver.findElements(DocumentPage.sectionList);
		Iterator<WebElement> itr = ele.iterator();
		while (itr.hasNext()) {
			itr.next().click();
		}
		SeleniumUtil.getElementWithFluentWait(sectionAdditionalText).click();
		SeleniumUtil.getElementWithFluentWait(radiobutton).click();
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.patientnametextbox).sendKeys(quicknotename);
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.savebutton).click();
	}

	/**
	 * This method is used to click on section present in document
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnSections(By section, String imagename) {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement findingvalues = SeleniumUtil.getElementWithFluentWait(section);
		SeleniumUtil.scrolltoWebElement(findingvalues);
		try {
			SeleniumUtil.clickOnImageWitScreenInSikuli(imagename);
		} catch (Exception e) {

		}

	}

	/**
	 * This method is used to switch to document page after opening of any new
	 * document say Colonoscopy
	 */
	public void switchToFraDocumentFrame() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
	}

	/*
	 * public void getElement(By by) { try { WebElement element =
	 * SeleniumUtil.getElementWithFluentWait(by); } catch (Exception e) {
	 * System.out.println("In Catch"); } }
	 */

	/**
	 * This method is used to select single Staff Details in the Document
	 */
	public void selectSingleStaffDetails(By by1, By by2, By by3) {
		click(by1);
		switchToParentFrame(Frames.TOOLTIP);
		click(by2);
		click(by3);
	}

	/**
	 * This method is used to select MUltiple Staff Details in the Document
	 */
	public void selectMultipleStaffDetails(By by1, By by2, By by3, By by4) {
		click(by1);
		switchToParentFrame(Frames.TOOLTIP);
		click(by2);
		click(by3);
		click(by4);
	}

	/**
	 * This method is used to select ASA Class in the document
	 */
	public void selectASAClass(By by1, By by2, By by3) {
		click(by1);
		switchToFrame("panUserList_Frame");
		click(by2);
		click(by3);

	}

	/**
	 * This method is used to deleted Staff Details from the Document followed
	 * by confirmation pop up
	 * 
	 */
	public void deleteStaffDetails(By by, String delete, String yes) {
		rightClick(by);
		clickOnImage(delete);
		clickOnImage(yes);
	}

	/**
	 * This method is used to deleted Staff Details from the Document with no
	 * confirmation pop up
	 * 
	 */
	public void deleteStaffDetails(By by, String delete) {
		rightClick(by);
		clickOnImage(delete);

	}

	/**
	 * This method is used to select instrument from the right Panel into the
	 * document
	 * 
	 * @return
	 */
	public String selectInstrument() {
		click(DocumentPage.instruments);
		switchToFrame("panUserList_Frame");
		switchToFrame("fraUserList_Frame");
		click(DocumentPage.instrumentsrhtpnlfold1);
		sleep(3000);
		click(DocumentPage.instrumentsrhtpnlfold2);
		sleep(3000);
		String text = SeleniumUtil.getElementWithFluentWait(DocumentPage.instrumentsonrhtpnl).getText();
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(DocumentPage.instrumentsonrhtpnl));
		return text;
	}

	/**
	 * This method is used create new Instrument from the righ Panel
	 */

	public void createNewInstrument(String str1, String str2, String str3) {
		click(DocumentPage.newbutton);
		sleep(3000);
		switchToParentFrame(Frames.TOOLTIP);
		enterText(DocumentPage.patientnametextbox, str1);
		enterText(DocumentPage.instrumentserialnum, str2);
		enterText(DocumentPage.instrumenttypetext, str3);
		click(DocumentPage.savebtnrhtpnl);
	}

	/**
	 * This method is used for clicking on medication section present in first
	 * visit service
	 */
	public void clickOnMedicationInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(currentMedicationSection);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used for clicking on record existing medication present in
	 * R-Panel when user clicks on medication section in first visit service
	 */
	public void selectRecordExistingForService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		SeleniumUtil.getElementWithFluentWait(PrescriptionPage.recordExistingButton).click();
	}

	/**
	 * This method is used for selecting medication tab when user clicks on plan
	 * section in first visit service
	 */
	public void clickOnMedicationTabInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		SeleniumUtil.switchToFrame(driver, "fraPlan_Frame");
		SeleniumUtil.getElementWithFluentWait(medicationTab).click();
	}

	/**
	 * This method is used for clicking on prescribe button when user clicks on
	 * plan section in first visit service
	 */
	public void clickOnPrescribeInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		SeleniumUtil.switchToFrame(driver, "fraPlan_Frame");
		SeleniumUtil.switchToFrame(driver, "fraOrdersMedications_Frame");
		WebElement prescriblebutton = SeleniumUtil.getElementWithFluentWait(Profile.newbutton);
		prescriblebutton.click();
	}

	/**
	 * This method is used for selecting patient chart tab
	 */
	public void closeService() {
		DynamicFramePage.dynamicFrameForPanchart();
		SeleniumUtil.getElementWithFluentWait(patientTab).click();
	}

	/**
	 * This method is used for clicking on allergy section in first visit
	 * service
	 */
	public void clickOnAllergySectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(allergySection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used for clicking on condition section present in first
	 * visit service
	 */
	public void clickOnCondtionSectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(conditionSection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used to verify Image count
	 * 
	 * @param by
	 * @param count
	 */
	public boolean verifyImageCount(By by) {
		List<WebElement> li = driver.findElements(by);
		if (imgcount1 == 0) {
			imgcount1 = li.size();

		} else {
			imgcount2 = li.size();
			imgcount2++;
		}
		return imgcount1 == imgcount2;

	}

	public void insertImageOnDocument(String str) {
		click(DocumentPage.imagesectionondoc);
		SeleniumUtil.switchToParentFrame(Frames.IMAGEFRAME);
		click(DocumentPage.imageinsert);
		clickOnImage(str);
		sleep(3000);

	}

	/**
	 * This method is used to select an HPI on document from the right
	 * Panel @return @throws
	 */
	public String selectHPIOnDocument() {

		click(hpisection);
		sleep(3000);
		SeleniumUtil.robot();
		switchToFrame("panUserList_Frame");
		switchToFrame("fraUserList_Frame");
		click(DocumentPage.instrumentsrhtpnlfold1);
		sleep(3000);
		String text = SeleniumUtil.getElementWithFluentWait(DocumentPage.instrumentsrhtpnlfold2).getText();
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(DocumentPage.instrumentsrhtpnlfold2));
		try {
			switchToParentFrame(Frames.TOOLTIP);
			click(savebtnrhtpnl);
		} catch (Exception e) {
			System.out.println("No Pop Up Present");
		}
		switchToFraDocumentFrame();
		return text;
	}

	/**
	 * This method is used for clicking on immunization section present in first
	 * visit service
	 */
	public void clickOnImmunizationSectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(immunizationSection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used for clicking on DX Studies section present in first
	 * visit service
	 */
	public void clickOnDXStudiesSectionInService() throws FindFailed {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(DXSection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used for clicking on Procedure section present in first
	 * visit service
	 */

	public void clickOnProcedureSectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(proceduresection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used for clicking on Family section present in first visit
	 * service
	 */
	public void clickOnFamilySectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(familySection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used for clicking on alchol section present in first visit
	 * service
	 */
	public void clickOnAlcholSectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(alcoholSection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used for clicking on Tabacco section present in first
	 * visit service
	 */
	public void clickOnTabaccoSectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(tobaccoSection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used for clicking on Drug section present in first visit
	 * service
	 */
	public void clickOnDrugSectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(drugSection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);

	}

	/**
	 * This method is used for clicking on exercise section present in first
	 * visit service
	 */
	public void clickOnExerciseSectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(exerciseSection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used for clicking on caffeine section present in first
	 * visit service
	 */
	public void clickOnCaffeineSectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(caffeineSection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used for clicking on Sexual section present in first visit
	 * service
	 */
	public void clickOnSexualSectionInService() {
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		WebElement row = SeleniumUtil.getElementWithFluentWait(caffeineSection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}

	/**
	 * This method is used to enter Administered Medication in the Colonoscopy
	 */

	public void addAdministeredMedication(String str) {
		click(DocumentPage.administmedication);
		switchToFrame("panUserList_Frame");
		switchToFrame("panList_Frame");
		click(DocumentPage.newbutton);
		sleep(3000);
		switchToParentFrame(Frames.TOOLTIP);
		click(DocumentPage.searchmedication);
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		enterText(DocumentPage.searchmedicationtxtbox, str);
		click(DocumentPage.searchMedication1);
		sleep(6000);
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(DocumentPage.searchedmedication));
		switchToParentFrame(Frames.TOOLTIP);
		click(DocumentPage.savebtnrhtpnl);
	}

	/**
	 * This method is used to add indication in the document
	 */

	public String addIndications() {
		click(DocumentPage.indicationData);
		switchToFrame("panUserList_Frame");
		click(By.id("tabListDiagnosis_Text"));
		sleep(3000);
		switchToFrame("fraUserListDiagnosis_Frame");
		switchToFrame("fraUserList_Frame");
		click(DocumentPage.instrumentsrhtpnlfold1);
		sleep(3000);
		click(DocumentPage.instrumentsrhtpnlfold2);
		sleep(3000);
		String str = getElementText(instrumentsonrhtpnl);
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(DocumentPage.instrumentsonrhtpnl));
		switchToParentFrame(Frames.TOOLTIP);
		click(DocumentPage.savebtnrhtpnl);
		return str;
	}

	/**
	 * This method is used to add vital sign to document
	 */
	public void addVitalSign(String weight, String height) {
		click(DocumentPage.VitalSignsection);
		switchToFrame("panUserList_Frame");
		enterText(DocumentPage.weightValue1, weight);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.heigthValue1).clear();
		enterText(DocumentPage.heigthValue1, height);
		click(DocumentPage.savebtnrhtpnl);
	}

	/**
	 * This method is used to add the Physical data in the document and return
	 * the All normal data for comparison
	 * 
	 * @return
	 */
	public String[] addPhysicalSection() {
		click(DocumentPage.physicalsection);
		switchToFrame("panUserList_Frame");
		click(DocumentPage.modifyphysical);
		switchToParentFrame(Frames.TOOLTIP);
		click(DocumentPage.allnormalphysical);
		String[] str = getElementsText(DocumentPage.allnormaldata);
		click(DocumentPage.savebtnrhtpnl);
		return str;
	}

	/**
	 * This method is used to add narrative in the Procedure section of Document
	 * 
	 * @return
	 */
	public String[] addNarrativeToDocument() {
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(DocumentPage.proceduresection1));
		switchToParentFrame(Frames.TOOLTIP);
		String[] str = getElementsText(DocumentPage.colonprocoverviewelements);
		clickEachElement(DocumentPage.colonprocoverviewelements);
		click(DocumentPage.savebtnrhtpnl);
		return str;
	}

	/**
	 * This method is used to Check the No Limitation Check box on Right Panel
	 */
	public void checkNoLimitationCheckBox() {
		switchToFrame("panUserList_Frame");
		switchToFrame("panList_Frame");
		click(DocumentPage.chknolimitationcheckbox);
	}

	/**
	 * This method is used to Add findings to Document and return the element
	 * added to findings through pop up
	 * 
	 * @param by
	 * @return
	 */
	public String[] addFindingsToDocument(By by) {
		if (by == DocumentPage.selecteditemsonfindings1) {
			switchToFrame("panUserList_Frame");
			click(DocumentPage.rhtpnlgeneraltab);
			switchToFrame("fraUserListTypes_Frame");
			click(DocumentPage.genrealtabitems);
			switchToParentFrame(Frames.TOOLTIP);
			clickEachElement(DocumentPage.colonprocoverviewelements);
			clickEachElement(DocumentPage.findingsoverviewelments);
			return getElementsText(by);
		} else {
			String[] str = getElementsValue(by);
			click(DocumentPage.savebtnrhtpnl);
			return str;
		}

	}

	/**
	 * This method is used to add the Intervention in the document and returns
	 * the added intervention as String Array
	 * 
	 * @param by
	 * @return
	 */
	public String[] addInterventionSection(By by) {
		if (by == DocumentPage.selecteditemsonfindings1) {
			switchToFrame("panUserList_Frame");
			switchToFrame("fraUserList_Frame");
			click(DocumentPage.instrumentsrhtpnlfold1);
			sleep(3000);
			SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(DocumentPage.instrumentsrhtpnlfold2));
			switchToParentFrame(Frames.TOOLTIP);
			clickEachElement(DocumentPage.colonprocoverviewelements);
			clickEachElement(DocumentPage.findingsoverviewelments);
			return getElementsText(DocumentPage.selecteditemsonfindings1);
		} else {
			String[] str = getElementsValue(by);
			click(DocumentPage.savebtnrhtpnl);
			return str;
		}
	}

	/**
	 * This method is used to add referral order in plan section of Document
	 */
	public void addReferralOrder(String str1, String str2) {
		switchToFrame("panUserList_Frame");
		switchToFrame("fraPlan_Frame");
		click(By.id("btnNew_SpanBGColor"));
		clickOnImage(str2);
		switchToParentFrame(Frames.TOOLTIP);
		enterText(DocumentPage.referraltitle, str1);
		enterText(DocumentPage.referralreason, "Reason");
		click(DocumentPage.savebtnrhtpnl);
	}

	public void addSample(String str) {
		selectSingleStaffDetails(DocumentPage.endoscopist, DocumentPage.select1frompopupframe, DocumentPage.selectbtnpopupframe);
		switchToFraDocumentFrame();
		scroll(DocumentPage.samplesection);
		click(DocumentPage.samplesection);
		switchToFrame("panUserList_Frame");
		click(DocumentPage.samplespecimenstabrhtpnl);
		switchToFrame("fraSpecimens_Frame");
		click(DocumentPage.newbutton);
		switchToParentFrame(Frames.TOOLTIP);
		click(DocumentPage.procspecimenbiopsy);
		click(DocumentPage.procspecimennormal);
		enterText(DocumentPage.procspecimencomment, str);
		click(DocumentPage.savebtnrhtpnl);
	}

	/**
	 * This method is used to add the additional NOte to document
	 * 
	 * @return
	 */
	public String addAdditionalNote() {
		scroll(additionalNoteTextBox);
		click(additionalNoteTextBox);
		SeleniumUtil.robot();
		switchToFrame("panUserList_Frame");
		String str = getElementText(instrumentsrhtpnlfold1);
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(instrumentsrhtpnlfold1));
		return str;
	}

	public void addEndoscopist() {
		click(DocumentPage.endoscopist);
		switchToParentFrame(Frames.TOOLTIP);
		enterText(DocumentPage.searchmedicationtxtbox, "Strom Chaser");
		click(DocumentPage.searchMedication1);
		click(DocumentPage.select1frompopupframe);
		click(DocumentPage.selectbtnpopupframe);
	}

	public boolean verifyElementCount(By by) {
		List<WebElement> li = driver.findElements(by);
		return li.size() > 0;

	}

	/**
	 * The method is used to sign the document
	 */
	public void signDocument(String user, String pwd) {
		click(DocumentPage.thumbnails);
		clickOnImage("clickOnSign1");
		clickOnImage("clickOnSelected");
		switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.authenticator).clear();
		enterText(DocumentPage.authenticator, user);
		click(DocumentPage.authenticatorpassword);
		enterText(DocumentPage.authenticatorpassword, pwd);
		click(DocumentPage.authenticateButton);
	}
	/**
	 * This method is used to switch in Immunization Frame
	 */
	public void switchInImunizationFrame(){
		
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		SeleniumUtil.switchToFrame(driver, "panList_Frame");
	}
	/**
	 * This method is used to verify Immunization details are added in service
	 * @return ture if immunization details are added
	 */
	public boolean verifyImmunizationDetailInService(){
		boolean isImmunizationDataPresent=false;
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		String text =SeleniumUtil.getElementWithFluentWait(immunizationSection).getText();
		String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
		String immunizationTextValue=addNewImmunizationForPatient+", "+currentDate;
		if(text.equalsIgnoreCase(immunizationTextValue)){
			System.out.println("correct immunization details are added in service..");
			isImmunizationDataPresent=true;
		}
		return isImmunizationDataPresent;
		
	}
	/**This method will verify if existing immunization  are added in document module ,then delete the existing immunization details
	 * 
	 * @throws FindFailed
	 */
	public void deleteAddedImmunizationDetails() throws FindFailed{
		sleep(5000);
		
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains(addNewImmunizationForPatient)){
				System.out.println("immunization already  added in demographics");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deleteUser");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
			}
			else{
				System.out.println("No existing immunization  are present");
			}

		}
	}
	
	/**
	 * This method will lock selected doc from the thumbnails
	 */
	
	public void lockSelectedDoc() {
	click(DocumentPage.thumbnails);
	clickOnImage("clickOnLockIcon");
	clickOnImage("clickOnSelected");
	sleep(3000);
	refresh();
	sleep(6000);
	}
	
	/**
	 * The method will lock all the document
	 */
		public void lockAllDoc() {
		sleep(3000);
		clickOnImage("clickOnUnlockIcon");
		sleep(3000);
		clickOnImage("clickOnLockAllIcon");
		sleep(15000);
		clickOnImage("clickOnGIReferralLetter");
		refresh();
	}
	
	/**
	 * The method will unlock all the document
	 */
	
	public void unlockAllDoc(String pwd) {
		clickOnImage("clickOnUnlockAllIcon");
		clickOnImage("clickOnYes");
		switchToParentFrame(Frames.TOOLTIP);
		enterText(DocumentPage.authenticatorpassword, pwd);
		click(DocumentPage.authenticateButton);
		switchToFraDocumentFrame();
		refresh();
		
	}
	
	/**
	 * The Method is used to open New Order from Plan Section
	 */
	public void openNewOrder() {
	scroll(DocumentPage.planSection);
	click(DocumentPage.planSection);
	switchToFrame("panUserList_Frame");
	switchToFrame("fraPlan_Frame");
	click(By.id("btnNew_SpanBGColor"));
	clickOnImage("clickOnReferral");
	switchToParentFrame(Frames.TOOLTIP);
	}
}
