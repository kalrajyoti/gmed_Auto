package com.gmed.pages;

import static com.gmed.helper.DriverFactory.action;
import static com.gmed.helper.DriverFactory.driver;


import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.testng.annotations.BeforeClass;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;



import com.gmed.utils.ConstantsFile;
import com.gmed.utils.DateUtil;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;


public class Profile extends BaseAbstractPage {
	/** Logger to log the Profile log messages */
	private static Logger logger                                    = LogManager.getLogger(Profile.class);
	public static By profileicon                                    = By.id("btnProfile_SpanBGColor");
	public static By problemvalue                                   = By.id("pnlDiagnosis_Image");
	public static By patientnametextbox                             = By.id("txtName_TextBox");
	public static By problemTextInPatient                           = By.className("tableGroupColumnsText");
	public static By medicationPopupPatient                         = By.id("pnlMedications_Image");
	public static By newbutton                                      = By.id("btnNew_SpanBGColor");
	public static By prescribeCheckbox                              = By.id("rdoTypePrescribe");
	public static By allergyInProfile                               = By.id("snpAllergy_Data");
	public static By textBoxarea                                    = By.id("txtDescription_TextBox");
	public static By condtionInProfile                              = By.id("snpIllness_Data");
	public static By immunizationInProfile                          = By.id("snpImmunization_Data");
	public static By immunizationTextBox                            = By.id("txtVaccine_TextBox");
	public static By diagonticData                                  = By.id("snpDiagnosticStudy_Data");
	public static By procedureDataText                                  = By.id("snpProcedure_Data");
	public static By searchIcon                                     = By.id("txtNameSearch");
	public static By patientNameInProfile                           = By.id("lblName");
	public static By patientDOBInProfile                            = By.id("lblDOB");
	public static By patientMRNInProfile                            = By.id("lblMRN");
	public static By patientSSNInProfile                            = By.id("lblSSG");
	public static By patientAddressInProfile                        = By.id("lblAddress2");
	public static By patientEmailInProfile                          = By.id("lblEmail");
	public static By insuranceRow                                   = By.xpath(".//tr[@id='ctl09_ctl00_tbrInsurance']/td");
	public static By menuIconsInProfile                             = By.xpath("//control[@id='tbrMain']");
	public static By toolBarMenuInProfile                           = By.xpath(".//control[@type='ToolbarButton']");
	public static By profileTabInFax                                = By.id("tabDistributeProfile");
	public static By addButtonInFax                                 = By.id("btnAdd_TextSpan");
	public static By selectRecordForFax                             = By.xpath(".//input[@class='checkbox']");
	public static By faxButtonInFax                                 = By.id("btnDistribute_SpanBGColor");
	public static By closePopUp                                     = By.id("Popup_CloseButton");
	public static By recipientName                                  = By.id("txtRecipientName_TextBox");
	public static By searchPatientInFaxing                          = By.id("txtSearchPatient_TextBox");
	public static By faxDigit1                                      = By.id("ptbFaxNumber_00");
	public static By faxDigit2                                      = By.id("ptbFaxNumber_01");
	public static By faxDigit3                                      = By.id("ptbFaxNumber_02");
	public static By faxButton                                      = By.id("btnFax_SpanBGColor");
	public static By pifTitle                                       = By.id("PopupFullSize_TitleContent");
	public static By closePif                                       = By.id("PopupFullSize_CloseButton");
	public static By closePopup                                     = By.id("Popup_CloseButton");
	public static By noneCheckbox                                   = By.id("chkDoesNotHaveDiagnoses");
	public static By reviewDiagnosisTitle                           = By.id("lblReviewedDiagnosis");
	public static By reviewDiagnosisTextBox                         = By.id("txtDescription_TextBox");
	public static By ICD9DiagnosisTextBox                           = By.id("txtIcdCode_TextBox");
	public static By reviewDiagnosisSearchBox                       = By.id("txtDescriptionSearch");
	public static By totaltrtagsForProfile                          = By.xpath(".//table[@id='tblUserlistSearch_Table']/tbody/tr");
	public static By totalDiagnosisForProfile                       = By.xpath(".//table[@id='tblListDiagnosis_Table']/tbody/tr");
	public static By existingRows                                   = By.className("rtIn");
	public static By yesButtonInService                             = By.id("btnYes_ImageContainer");
	public static By noneCheckboxState                              = By.id("chkDoesNotHaveDiagnoses");
	public static By patientChartTab                                = By.id("tabTree");
	public static By guidlinesTextBox                               = By.id("txtTitle_TextBox");
	public static By guidlinesFromAgeTextBox                        = By.id("itbAgeFrom_IntegerTextBox");
	public static By guidlinesToAgeTextBox                          = By.id("itbAgeTo_IntegerTextBox");
	public static By totalRecommandationsForProfile                 = By.xpath(".//table[@id='tblListRecommendation_Table']/tbody/tr");
	public static By totalFutureAppointmentForProfile               = By.xpath(".//table[@id='tblAppointments_Table']/tbody/tr");
	public static By recallSectionInProfile                         = By.id("pnlRecalls_Image");
	public static By providerDropDownInProfile                      = By.id("txtProviderDropDown");
	public static By totalRecallsForProfile                         = By.xpath(".//table[@id='tblRecalls_Table']/tbody/tr");
	public static By totalRecallQueue                               = By.xpath(".//table[@id='radRecalls_ctl00']/tbody/tr");
	public static By rightMenuForRecallQueue                        = By.xpath("//div[@id='radMenu_detached']/ul/li");
	public static By subMenuForRecallQueue                          = By.xpath("//div[@class='rmSlide']/ul/li");
	public static By closeRecallPopup                               = By.id("panProfile_Buttons_Close");
	public static By yesPopup                                       = By.id("btnYes_ImageContainer");
	public static By patientTab                                     = By.id("tabTree");
	public static By totalVitalSignQueue                            = By.xpath(".//table[@id='tblListVitalSigns_Table']/tbody/tr");
	public static By advanceDirectivesInProfile                     = By.id("pnlAdvancedDirectives_Image");
	public static By advanceDirectivesCheckBoxInProfile             = By.id("radNoAdvanced_Radio");
	public static By advanceDirectivesTextInProfile                 = By.id("lblAdvancedDirective");
	public static By advanceDirectivesNotesInProfile                = By.id("txtNotes_TextBox");
	public static By advanceDirectivesReviewButtonInProfile         = By.id("btnReview_SpanBGColor");
	public static By pifTextInProfile                               = By.id("lblPatientInterviewForm");
	public static By reasonTextBox                                  = By.id("txtReason_TextBox");
	public static By voidButton                                     = By.id("btnVoid_SpanBGColor");
	public static By totalMedicationForProfile                      = By.xpath(".//table[@id='tblListMedications_Table']/tbody/tr");
	public static By openOrderSectionInProfile                      = By.id("lblOpenOrder");
	public static By totalMedicalOrderForProfile                    = By.xpath(".//table[@id='tblOpenOrder_Table']/tbody/tr");
	public static By noAllergyCheckboxState                         = By.id("chkDoesNotHaveAllergies");
	public static By noAllergyCheckbox                              = By.id("chkDoesNotHaveAllergies_Render");
	public static By allergyMsgInProfile                            = By.id("snpAllergy_Msg");
	public static By noDrugAllergyCheckboxState                     = By.id("chkDoesNotHaveDrugsAllergies");
	public static By noDrugAllergyCheckbox                          = By.id("chkDoesNotHaveDrugsAllergies_Render");
	public static By snomedCodeTextBox                              = By.id("txtSnomedName_TextBox");
	public static By snomedNameSearch                               = By.id("txtSnomedNameSearch");
	public static By noACondtionCheckboxState                       = By.id("chkDoesNotHaveIllnesses");
	public static By noCondtionCheckbox                             = By.id("chkDoesNotHaveIllnesses_Render");
	public static By condtionMsgInProfile                           = By.id("snpIllness_Msg");
	public static By noImmunizationsCheckboxState                   = By.id("chkDoesNotHaveImmunizations");
	public static By noImmunizationsCheckbox                        = By.id("chkDoesNotHaveImmunizations_Render");
	public static By immunizationsMsgInProfile                      = By.id("snpImmunization_Msg");
	public static By cvxDropDown                                    = By.id("txtCvxCodeDropDown");
	public static By noDiagnosticStudiesCheckboxState               = By.id("chkDoesNotHaveDiagnosticStudies");
	public static By noDiagnosticStudiesCheckbox                    = By.id("chkDoesNotHaveDiagnosticStudies_Render");
	public static By diagnosticStudyMsgInProfile                    = By.id("snpDiagnosticStudy_Msg");
	public static By cptCodeSearchBox                               = By.id("txtCptCodeSearch");
	public static By searchCodeTextBox                              = By.id("txtNameSearchCoding_TextBox");
	public static By codingSearch                                   = By.id("txtNameSearchCodingSearch");
	public static By codingTableData                                = By.xpath(".//table[@id='tblSearchCoding_Table']/tbody/tr");
	public static By noProcedureCheckboxState                       = By.id("chkDoesNotHaveProcedures");
	public static By noProcedureCheckbox                            = By.id("chkDoesNotHaveProcedures_Render");
	public static By procedureMsgInProfile                          = By.id("snpProcedure_Msg");
	public static By diagnosisListInProfile                         = By.id("tblListDiagnosis");
	public static By guildlineListInProfile                         = By.id("tblListRecommendation");
	public static By futureAppointmentListInProfile                 = By.id("tblAppointments");
	public static By recallListInProfile                            = By.id("tblRecalls");
	public static By currentMedicationListInProfile                 = By.id("tblListMedications");
	public static By familySectionInProfile                         = By.id("pnlFamily_Div");
	public static By familyDataInProfile                            = By.id("lblNoFamilyHistoryOf");
	public static By marriageLength                                 = By.id("txtMarriageLength_TextBox");
	public static By spouseOccupation                               = By.id("txtSpouseOcc_TextBox");
	public static By noOfChildren                                   = By.id("txtNumberChildren_IntegerTextBox");
	public static By religionTextBox                                = By.id("txtReligion_TextBox");
	public static By hobbiesTextBox                                 = By.id("txtHobbies_TextBox");
	public static By occHistoryTextBox                              = By.id("txtOccHistory_TextBox");
	public static By notesTextBox                                   = By.id("txtNotes_TextBox");
	public static By generalSection                                 = By.id("lblGeneral");
	public static By generalData                                    = By.id("lblDescriptionSocial");
	public static By alcoholSectionInProfile                        = By.id("snpAlcohol");
	public static By socialHistoryPage                              = By.id("lblSocial");
	public static By totalAlcoholForProfile                         = By.xpath(".//table[@id='tblListAlcohol_Table']/tbody/tr");
	public static By alcoholPlusButton                              = By.id("btnAddAlcohol_ImageContainer");
	public static By tabaccoSectionInProfile                        = By.id("snpTobacco");
	public static By totalTabaccoForProfile                         = By.xpath(".//table[@id='tblListTobacco_Table']/tbody/tr");
	public static By tabaccoPlusButton                              = By.id("btnAddTobacco_ImageContainer");
	public static By tabaccoQuantityTextBox                         = By.id("txtQuantity_DecimalTextBox");
	public static By tabaccoDataForProfile                          = By.id("snpTobacco_Data");
	public static By drugSectionInProfile                           = By.id("snpDrug");
	public static By drugPlusButtonInProfile                        = By.id("btnAddDrug_ImageContainer");
	public static By totalDrugForProfile                            = By.xpath(".//table[@id='tblListDrug_Table']/tbody/tr");
	public static By exerciseSectionInProfile                       = By.id("snpExercise_Data");
	public static By totalExerciseForProfile                        = By.xpath(".//table[@id='tblListExercise_Table']/tbody/tr");
	public static By exercisePlusButtonInProfile                    = By.id("btnAddExercise_ImageContainer");
	public static By exerciseTypeTextBoxInProfile                   = By.id("txtType_TextBox");
	public static By quantityTextBoxInProfile                       = By.id("txtQuantity_TextBox");
	public static By frequencyTextBoxInProfile                       = By.id("txtFrequencyValue_TextBox");
	public static By caffeineSectionInProfile                       = By.id("snpCaffeine_Data");
	public static By caffeinePlusButtonInProfile                    = By.id("btnAddCaffeine_ImageContainer");
	public static By caffeineTextBoxInProfile                       = By.id("txtIntake_TextBox");
	public static By caffeineStoppedDate                            = By.id("txtStoppedTimeframe_TextBox");
	public static By totalCaffeineForProfile                        = By.xpath(".//table[@id='tblListCaffeine_Table']/tbody/tr");
	public static By sexualSectionInProfile                         = By.id("lblSexualDescription");
	public static By sexualPlusButtonInProfile                    = By.id("txtSexualAdd");
	public static By sexualTextBoxInProfile                       = By.id("txtPartners_TextBox");
	public static By sexualNotesInProfile                       = By.id("txtNotes_TextBox");
	
	

	/**contains the Profile page data*/
	public static Map<String, String> profileData;

	/**These are the variables which are used to store different data for medical chart module*/
	public static String existingProfilePatientDateOfBirth;
	public static String existingPatientAge;
	public static String existingProfilepatientSSN;
	public static String existingProfilepatientAddress;
	public static String existingProfilepatientMailId;
	public static String existingProfilePatientCompletename;
	public static String existingProfilePatientInsurance;
	public static String patientProfileMenuIcons;
	public static String patientProfilerecipientName;
	public static String patientProfilerfax0;
	public static String patientProfilerfax1;
	public static String patientProfilerfax2;
	public static String patientProfilerDiagnosisvalue;
	public static String patientProfilerDiagnosisICD10;
	public static String patientProfilerDiagnosisUserListvalue;
	public static String patientProfilerDiagnosisFromFirstVisit;
	public static String problemTextinProfile;
	public static String patientProfileGuidlinesName;
	public static String guidlineTextinProfile;
	public static String existingActivityForProfilePatient;
	public static String existingProfileProviderfirstname;
	public static String existingProfileProviderlastname;
	public static String existingProfileRecallType;
	public static String vitalSignWeightForProfilePatient;
	public static String vitalSignHeightForProfilePatient;
	public static String vitalSignHeightBMIForProfilePatient;
	public static String userNameForAdvanceDirectiveProfile;
	public static String notesForAdvanceDirectiveProfile;
	public static String completeTextForAdvanceDirectiveProfile;
	public static String currentDateForAdvanceDirectiveProfile;
	public static String halfTextForAdvanceDirectiveProfile;
	public static String halfTextForPIFProfile;
	public static String completeTextForPIFProfile;
	public static String medicationForProfile;
	public static String recordExistingMedicationForProfile;
	public static String medicationUsingUserListForProfile;
	public static String recordExistingUsingUserListForProfile;
	public static String addNewAllergyForPatient;
	public static String selectAllergyFormMyAllergies;
	public static String addNewMedicatiomInAllergyForPatient;
	public static String selectAllergyForSearchBox;
	public static String allergyValueFromFirstVisit;
	public static String noAllergyTextForProfile;
	public static String noDrugAllergyTextForProfile;
	public static String noCondtionTextForProfile;
	public static String addNewCondtionForPatient;
	public static String condtionValueFromFirstVisit;
	public static String noImmunizationTextForProfile;
	public static String addNewImmunizationForPatient;
	public static String searchExistingImmunization;
	public static String immunizationValueFromFirstVisit;
	public static String codingDescription;
	public static String dxStudiesValueFromFirstVisit;
	public static String searchdxStudiesProcedureName;
	public static String noDXStudiesTextForProfile;
	public static String cptCode;
	public static String dxStudiesProcedureName;
	public static String searchExistingProcedure;
	public static String procedureValueFromFirstVisit;
	public static String gmedLoaction;
	public static String medicationtext1;
	public static String medicationtext2;
	public static String medicationtext3;
	public static String medicationtext4;
	public static String medicationtext5;
	public static String medicationtext6;
	public static String medicationtext7;
	public static String medicationtext8;
	public static String allergyTextinProfile;
	public static String conditionTextinProfile;
	public static String immunizationTextinProfile;
	public static String dxStudiesTextinProfile;
	public static String familytextinProfile;
	public static String generalTextForProfile;
	public static String alcholToolTipTitle;
	public static String alcholQuantityForProfile;
	public static String alcholFrequencyForProfile;
	public static String alcholNoOfTimesForProfile;
	public static String alcholTextForProfile;
	public static String tabaccoToolTipTitle;
	public static String tabacooTextForProfile;
	public static String drugToolTipTitle;
	public static String drugTextForProfile;
	public static String exerciseToolTipTitle;
	public static String exerciseTextForProfile;
	public static String caffeineToolTipTitle;
	public static String caffeineTextForProfile;
	public static String caffeineTextForService;
	public static String sexualToolTipTitle;
	
	/** These are the variables which are present on "Profile" sheet in the excel*/
	
	public static final String PROFILE_PATIENT_DATEOFBIRTH 				                   = "dateofbirth";
	public static final String PROFILE_PATIENT_SSNNUMBER 				                   = "socialsecurity";
	public static final String PROFILE_PATIENT_ADDRESS 				                       = "address";
	public static final String PROFILE_PATIENT_MAILID 				                       = "mailId";
	public static final String PROFILE_PATIENT_COMPLETE 				                   = "patientcompletename";
	public static final String PROFILE_PATIENT_INSURANCE 				                   = "carriernames";
	public static final String PROFILE_PATIENT_MENUICONSNAME 				               = "menusicons";
	public static final String PROFILE_PATIENT_RECIPIENTNAME				               = "recipientName";
	public static final String PROFILE_PATIENT_FAX0				                           = "fax0";
	public static final String PROFILE_PATIENT_FAX1				                           = "fax1";
	public static final String PROFILE_PATIENT_FAX2				                           = "fax2";
	public static final String PROFILE_PATIENT_PROBLEM				                       = "problemvalue";
	public static final String PROFILE_PATIENT_PROBLEM_ICD10				               = "ICD10";
	public static final String PROFILE_PATIENT_PROBLEM_USERLISTVALUE				       = "addProblems";
	public static final String PROFILE_PATIENT_PROBLEM_USERLIST_FROM_SERVICE			   = "addDiagnosis";
	public static final String PROFILE_PATIENT_PROBLEM_TEXT				                   = "problemtextinprofile";
	public static final String PROFILE_PATIENT_GUIDLINE_NAME			                   = "guidlineName";
	public static final String PROFILE_PATIENT_GUIDLINE_TEXT				               = "guidlinetext";
	public static final String PROFILE_PATIENT_ACTIVITY 				                   = "activityvalue";
	public static final String PROFILE_PROVIDER_FIRSTNAME 				                   = "providerfirstname";
	public static final String PROFILE_PROVIDER_LASTNAME 				                   = "providerlastname";
	public static final String PROFILE_RECALL_TYPE 				                           = "recalltype";
	public static final String PROFILE_VITAL_WEIGHT 				                       = "vitalsignsweight";
	public static final String PROFILE_VITAL_HEIGHT 				                       = "vitalsignsheight";
	public static final String PROFILE_VITAL_BMI 				                           = "bmivalue";
	public static final String PROFILE_USER_NAME 				                           = "username";
	public static final String DIRECTIVE_NOTES_NAME 				                       = "advancedirectivenote";
	public static final String DIRECTIVE_COMPLETE_TEXT 				                       = "completetextforadvancedirective";
	public static final String DIRECTIVE_CURRENT_DATE 				                       = "currentdate";
	public static final String DIRECTIVE_HALF_TEXT 				                           = "advancereviewtext";
	public static final String PIF_HALF_TEXT 				                               = "pifreviewtext";
	public static final String PIF_COMPLETE_TEXT 				                           = "completetextforpif";
	public static final String PROFILE_MEDICATION_NAME 				                       = "medicationname";
	public static final String PROFILE_RECORDEXISTING_MEDICATIONNAME 				       = "recordingexistingmedication";
	public static final String PRESCRIBE_MEDICATION_USERLIST 				               = "userlistmedication";
	public static final String RECORDEXISTING_MEDICATION_USERLIST 				           = "recordingexistingmedicationusinglist";
	public static final String ADD_NEW_ALLERGY 				                               = "addnewcustomallergy";
	public static final String SELECT_MY_ALLERGY 				                           = "addallergiesfromMyallergy";
	public static final String SEARCH_ALLERGY 				                               = "searchallergy";
	public static final String ALLERGY_USERLIST_FROM_SERVICE			                   = "addallergyfromservice";
	public static final String PROFILE_NO_ALLERGIES 				                       = "noallergies";
	public static final String PROFILE_NO_DRUG_ALLERGIES 				                   = "nodrugallergies";
	public static final String ADD_NEW_MEDICATION_INALLERGIES 				               = "searchmedicationinallergy";
	public static final String PROFILE_NO_CONDTIONS 				                       = "nocondtions";
	public static final String ADD_NEW_CONDTION 				                           = "addcondtioninsearchbox";
	public static final String CONDTION_USERLIST_FROM_SERVICE			                   = "addcondtionfromservice";
	public static final String PROFILE_NO_IMMUNIZATION 				                       = "noimmunizations";
	public static final String ADD_NEW_IMMUNIZATION 				                       = "addnewcustomimmunization";
	public static final String SEARCH_EXISTING_IMMUNIZATION 				                   = "searchexistingimmunization";
	public static final String IMMUNIZATION_USERLIST_FROM_SERVICE			                   = "addimmunizationfromservice";
	public static final String CODING_DESCRIPTION 				                           = "codingdescription";
	public static final String DXSTUDIES_USERLIST_FROM_SERVICE			                   = "adddxstudiesfromservice";
	public static final String PROCEDURE_NAME 				                           = "searchusingsearchbox";
	public static final String PROFILE_NO_DXSTUDIES 				                       = "nodxstudies";
	public static final String PROFILE_CPT_CODE 				                           = "cptcode";
	public static final String DX_PROCEDURE_NAME 				                           = "procedurename";
	public static final String SEARCH_EXISTING_PROCEDURE				                   = "searchexistingprocedure";
	public static final String PROCEDURE_USERLIST_FROM_SERVICE			                   = "addprocedurefromservice";
	public static final String GMED_LOCATION			                                   = "location";
	public static final String MEDICATION_TEXT1			                                   = "medicationtext1";
	public static final String MEDICATION_TEXT2			                                   = "medicationtext2";
	public static final String MEDICATION_TEXT3			                                   = "medicationtext3";
	public static final String MEDICATION_TEXT4			                                   = "medicationtext4";
	public static final String MEDICATION_TEXT5			                                   = "medicationtext5";
	public static final String MEDICATION_TEXT6			                                   = "medicationtext6";
	public static final String MEDICATION_TEXT7			                                   = "medicationtext7";
	public static final String MEDICATION_TEXT8			                                   = "medicationtext8";
	public static final String PROFILE_ALLERGIES_TEXT				                   = "allergytextinprofile";
	public static final String PROFILE_CONDITION_TEXT				                   = "condtiontextinprofile";
	public static final String PROFILE_IMMUNIZATION_TEXT				                   = "immunizationtextinprofile";
	public static final String PROFILE_DXSTUDIES_TEXT				                   = "dxstudiestextinprofile";
	public static final String PROFILE_FAMILY_TEXT				                   = "familytextinprofile";
	public static final String GENERAL_TEXT_FOR_PROFILE                                    = "generaltext";
	public static final String ALCHOL_TITLE                                                = "alcholtitle";
	public static final String ALCHOL_QUANTITY_FOR_PROFILE                                 = "quantity";
	public static final String ALCHOL_FREQUENCY_FOR_PROFILE                                 = "frequency";
	public static final String ALCHOL_NOOFTIMES_FOR_PROFILE                                 = "frequencytimes";
	public static final String ALCHOL_TEXT_FOR_PROFILE                                    = "alcholtext";
	public static final String TABACCO_TITLE                                                = "tabaccotitle";
	public static final String TABACCO_TEXT_FOR_PROFILE                                    = "tabaccotext";
	public static final String DRUG_TITLE                                                  = "drugtitle";
	public static final String DRUG_TEXT_FOR_PROFILE                                       = "drugtext";
	public static final String EXERCISE_TITLE                                              = "exercisetitle";
	public static final String EXERCISE_TEXT_FOR_PROFILE                                    = "exercisetext";
	public static final String CAFFEINE_TITLE                                              = "caffeinetitle";
	public static final String CAFFEINE_TEXT_FOR_PROFILE                                    = "caffeinetext";
	public static final String CAFFEINE_TEXT_FOR_SERVICE                                    = "caffeinetextforservice";
	public static final String SEXUAL_TITLE                                                 = "sexualtitle";
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for DemographicsTest test class....");
		profileData                                                 = ExcelFileUtilty.readExcelSheet("Profile");
		DemographicsPage.demographicsData                           = ExcelFileUtilty.readExcelSheet("Demographics");
		DemographicsPage.existingPatientfirstname                   = DemographicsPage.demographicsData.get(DemographicsPage.PATIENT_FIRSTNAME);
		DemographicsPage.existingPatientlastname                    = DemographicsPage.demographicsData.get(DemographicsPage.PATIENT_LASTNAME);
		existingProfilePatientDateOfBirth                           = profileData.get(PROFILE_PATIENT_DATEOFBIRTH);
		existingPatientAge                                          = DemographicsPage.demographicsData.get(DemographicsPage.PATIENT_AGE);
		DemographicsPage.existingPatientrecordnumber                = DemographicsPage.demographicsData.get(DemographicsPage.PATIENT_RECORDNUMBER);
		existingProfilepatientSSN                                   = DemographicsPage.demographicsData.get(DemographicsPage.PATIENT_SSNNUMBER);
		existingProfilepatientAddress                               = profileData.get(PROFILE_PATIENT_ADDRESS);
		existingProfilepatientMailId                                = profileData.get(PROFILE_PATIENT_MAILID);
		existingProfilePatientCompletename                          = profileData.get(PROFILE_PATIENT_COMPLETE);
		existingProfilePatientInsurance                             = profileData.get(PROFILE_PATIENT_INSURANCE);
		patientProfileMenuIcons                                     = profileData.get(PROFILE_PATIENT_MENUICONSNAME);
		patientProfilerfax0                                         = profileData.get(PROFILE_PATIENT_FAX0);
		patientProfilerfax1                                         = profileData.get(PROFILE_PATIENT_FAX1);
		patientProfilerfax2                                         = profileData.get(PROFILE_PATIENT_FAX2);
		patientProfilerecipientName                                 = profileData.get(PROFILE_PATIENT_RECIPIENTNAME);
		patientProfilerDiagnosisvalue                               = profileData.get(PROFILE_PATIENT_PROBLEM);
		patientProfilerDiagnosisICD10                               = profileData.get(PROFILE_PATIENT_PROBLEM_ICD10);
		patientProfilerDiagnosisUserListvalue                       = profileData.get(PROFILE_PATIENT_PROBLEM_USERLISTVALUE);
		patientProfilerDiagnosisFromFirstVisit                      = profileData.get(PROFILE_PATIENT_PROBLEM_USERLIST_FROM_SERVICE);
		problemTextinProfile                                        = profileData.get(PROFILE_PATIENT_PROBLEM_TEXT);
		patientProfileGuidlinesName                                 = profileData.get(PROFILE_PATIENT_GUIDLINE_NAME);
		guidlineTextinProfile                                       = profileData.get(PROFILE_PATIENT_GUIDLINE_TEXT);
		DemographicsPage.existingPatientFromAge                     = DemographicsPage.demographicsData.get(DemographicsPage.PATIENT_FROM_AGE);
		DemographicsPage.existingPatientToAge                       = DemographicsPage.demographicsData.get(DemographicsPage.PATIENT_TO_AGE);
		existingActivityForProfilePatient                           = profileData.get(PROFILE_PATIENT_ACTIVITY);
		existingProfileProviderfirstname                            = profileData.get(PROFILE_PROVIDER_FIRSTNAME);
		existingProfileProviderlastname                             = profileData.get(PROFILE_PROVIDER_LASTNAME);
		existingProfileRecallType                                   = profileData.get(PROFILE_RECALL_TYPE);
		vitalSignWeightForProfilePatient                            = profileData.get(PROFILE_VITAL_WEIGHT);
		vitalSignHeightForProfilePatient                            = profileData.get(PROFILE_VITAL_HEIGHT);
		vitalSignHeightBMIForProfilePatient                         = profileData.get(PROFILE_VITAL_BMI);
		userNameForAdvanceDirectiveProfile                          = profileData.get(PROFILE_USER_NAME);
		notesForAdvanceDirectiveProfile                             = profileData.get(DIRECTIVE_NOTES_NAME);
		completeTextForAdvanceDirectiveProfile                      = profileData.get(DIRECTIVE_COMPLETE_TEXT);
		currentDateForAdvanceDirectiveProfile                       = profileData.get(DIRECTIVE_CURRENT_DATE);
		halfTextForAdvanceDirectiveProfile                          = profileData.get(DIRECTIVE_HALF_TEXT);
		halfTextForPIFProfile                                       = profileData.get(PIF_HALF_TEXT);
		completeTextForPIFProfile                                   = profileData.get(PIF_COMPLETE_TEXT);
		medicationForProfile                                        = profileData.get(PROFILE_MEDICATION_NAME);
		recordExistingMedicationForProfile                          = profileData.get(PROFILE_RECORDEXISTING_MEDICATIONNAME);
		medicationUsingUserListForProfile                           = profileData.get(PRESCRIBE_MEDICATION_USERLIST);
		recordExistingUsingUserListForProfile                       = profileData.get(RECORDEXISTING_MEDICATION_USERLIST);
		addNewAllergyForPatient                                     = profileData.get(ADD_NEW_ALLERGY);
		selectAllergyFormMyAllergies                                = profileData.get(SELECT_MY_ALLERGY);
		selectAllergyForSearchBox                                   = profileData.get(SEARCH_ALLERGY);
		allergyValueFromFirstVisit                                  = profileData.get(ALLERGY_USERLIST_FROM_SERVICE);
		noAllergyTextForProfile                                     = profileData.get(PROFILE_NO_ALLERGIES);
		noDrugAllergyTextForProfile                                 = profileData.get(PROFILE_NO_DRUG_ALLERGIES);
		addNewMedicatiomInAllergyForPatient                         = profileData.get(ADD_NEW_MEDICATION_INALLERGIES);
		noCondtionTextForProfile                                    = profileData.get(PROFILE_NO_CONDTIONS);
		addNewCondtionForPatient                                    = profileData.get(ADD_NEW_CONDTION);
		condtionValueFromFirstVisit                                 = profileData.get(CONDTION_USERLIST_FROM_SERVICE);
		noImmunizationTextForProfile                                = profileData.get(PROFILE_NO_IMMUNIZATION);
		addNewImmunizationForPatient                                = profileData.get(ADD_NEW_IMMUNIZATION);
		searchExistingImmunization                                  = profileData.get(SEARCH_EXISTING_IMMUNIZATION);
		immunizationValueFromFirstVisit                             = profileData.get(IMMUNIZATION_USERLIST_FROM_SERVICE);
		codingDescription                                           = profileData.get(CODING_DESCRIPTION);
		dxStudiesValueFromFirstVisit                                = profileData.get(DXSTUDIES_USERLIST_FROM_SERVICE);
		searchdxStudiesProcedureName                                = profileData.get(PROCEDURE_NAME);
		noDXStudiesTextForProfile                                   = profileData.get(PROFILE_NO_DXSTUDIES);
		cptCode                                                     = profileData.get(PROFILE_CPT_CODE);
		dxStudiesProcedureName                                      = profileData.get(DX_PROCEDURE_NAME);
		searchExistingProcedure                                     = profileData.get(SEARCH_EXISTING_PROCEDURE);
		procedureValueFromFirstVisit                                = profileData.get(PROCEDURE_USERLIST_FROM_SERVICE);
		gmedLoaction                                                = profileData.get(GMED_LOCATION);
		medicationtext1                                             = profileData.get(MEDICATION_TEXT1);
		medicationtext2                                             = profileData.get(MEDICATION_TEXT2);
		medicationtext3                                             = profileData.get(MEDICATION_TEXT3);
		medicationtext4                                             = profileData.get(MEDICATION_TEXT4);
		medicationtext5                                             = profileData.get(MEDICATION_TEXT5);
		medicationtext6                                             = profileData.get(MEDICATION_TEXT6);
		medicationtext7                                             = profileData.get(MEDICATION_TEXT7);
		medicationtext8                                             = profileData.get(MEDICATION_TEXT8);
		allergyTextinProfile                                        = profileData.get(PROFILE_ALLERGIES_TEXT);
		conditionTextinProfile                                      = profileData.get(PROFILE_CONDITION_TEXT);
		immunizationTextinProfile                                   = profileData.get(PROFILE_IMMUNIZATION_TEXT);
		dxStudiesTextinProfile                                      = profileData.get(PROFILE_DXSTUDIES_TEXT);
		familytextinProfile                                         = profileData.get(PROFILE_FAMILY_TEXT);
		generalTextForProfile                                       = profileData.get(GENERAL_TEXT_FOR_PROFILE);
		alcholToolTipTitle                                          = profileData.get(ALCHOL_TITLE);
		alcholQuantityForProfile                                    = profileData.get(ALCHOL_QUANTITY_FOR_PROFILE);
		alcholFrequencyForProfile                                   = profileData.get(ALCHOL_FREQUENCY_FOR_PROFILE);
		alcholNoOfTimesForProfile                                   = profileData.get(ALCHOL_NOOFTIMES_FOR_PROFILE);
		alcholTextForProfile                                        = profileData.get(ALCHOL_TEXT_FOR_PROFILE);
		tabaccoToolTipTitle                                         = profileData.get(TABACCO_TITLE);
		tabacooTextForProfile                                       = profileData.get(TABACCO_TEXT_FOR_PROFILE);
		drugTextForProfile                                          = profileData.get(DRUG_TEXT_FOR_PROFILE);
		drugToolTipTitle                                            = profileData.get(DRUG_TITLE);
		exerciseToolTipTitle                                        = profileData.get(EXERCISE_TITLE);
		exerciseTextForProfile                                      = profileData.get(EXERCISE_TEXT_FOR_PROFILE);
		caffeineToolTipTitle                                        = profileData.get(CAFFEINE_TITLE);
		caffeineTextForProfile                                      = profileData.get(CAFFEINE_TEXT_FOR_PROFILE);
		caffeineTextForService                                      = profileData.get(CAFFEINE_TEXT_FOR_SERVICE);
		sexualToolTipTitle                                          = profileData.get(SEXUAL_TITLE);
	}
	/**
	 * This method is used for clicking on profile present in patient chart
	 * @throws Exception
	 */
	public static void clickOnProfileInPatient(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.getElementWithFluentWait(profileicon).click();
	}


	/**
	 * This method is used for clicking on Diagnoses Section present in profile of  patient chart
	 * 
	 */
	public  boolean clickOnDiagnosesSectionInProfile() {
		clickOnProfileInPatient();
		sleep(12000);
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		WebElement problem = SeleniumUtil.getElementWithFluentWait(problemvalue);
		SeleniumUtil.scrolltoWebElement(problem);
		problem.click();
		sleep(10000);
		driver.switchTo().parentFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
		ConstantsFile.userlistpanelexist=true;
		return ConstantsFile.userlistpanelexist;
	}
	/**
	 * This method is used for verify Diagnoses user list is opened for selecting the value from the user list
	 * 
	 */

	public boolean verifyDiagnosesPanelListIsOpened() {
		if(ConstantsFile.userlistpanelexist=true){
			System.out.println("panel list is opened");
			SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys("D12.0");
			SeleniumUtil.getElementWithFluentWait(searchIcon).click();
			sleep(9000);
			ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("AddProfileData");
			String problemName =ConstantsFile.loginData.get(ConstantsFile.ADDPROBLEMINPROFILE);
			WebElement plussign =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ problemName +"')]"));
			logger.info("after  selecting the value ");
			Actions action = new Actions(driver);
			action.moveToElement(plussign).doubleClick().build().perform();
			ConstantsFile.isPanelListisOpened=true;
		}
		else{
			System.out.println("panel list is not opend");
			ConstantsFile.isPanelListisOpened=false;
		}
		return ConstantsFile.isPanelListisOpened;
	}
	/**
	 * This method is used for verify when user select any value from the diagnoses user list then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyProblemIsDocumented(){
		//clickOnProfileInPatient();
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String problemtext=SeleniumUtil.getElementWithFluentWait(problemTextInPatient).getText();
		if((!problemtext.isEmpty())){
			System.out.println("problem is documented");
			ConstantsFile.isproblemisDocumented=true;
		}
		else{
			System.out.println("problem is not documented");
			ConstantsFile.isproblemisDocumented=false;
		}
		return ConstantsFile.isproblemisDocumented;
	}
	/**
	 * This method is used for verify the allergy user list should open and any searched value should be clicked
	 * 
	 */
	public boolean verifyAllergyUserList() {
		clickOnProfileInPatient();
		sleep(12000);
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		WebElement allergy = SeleniumUtil.getElementWithFluentWait(allergyInProfile);
		SeleniumUtil.scrolltoWebElement(allergy);
		allergy.click();
		sleep(10000);
		driver.switchTo().parentFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
		sleep(5000);
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("AddProfileData");
		String allergyName =ConstantsFile.loginData.get(ConstantsFile.ADD_ALLERGIES_INPROFILE);
		WebElement allergyNameValue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ allergyName +"')]"));
		action.moveToElement(allergyNameValue).doubleClick().build().perform();
		ConstantsFile.rightuserlist=true;
		return ConstantsFile.rightuserlist;
	}
	/**
	 * This method is used for verify the allergy popup details
	 * 
	 */
	public boolean verifyAllergiesPopDetails() {
		try{
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String allergyName= SeleniumUtil.getElementWithFluentWait(textBoxarea).getText();
		if(allergyName!=null){
			System.out.println("Allergy Name field is present in the Allergies detail pop");
			SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			ConstantsFile.allergiesisdocumented=true;
		}
		}
		catch(Exception e){
			System.out.println("Allergy Name field is blank in the Allergies detail pop");
		}
		return ConstantsFile.allergiesisdocumented;
	}
	/**
	 * This method is used for verify when user select allergy value from the user list then it should document in profile of the created patient
	 * @throws Exception
	 */

	public boolean verifyAllergiesIsDocumented() throws Exception{
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String allergyvaluetext=SeleniumUtil.getElementWithFluentWait(allergyInProfile).getText();
		if((!allergyvaluetext.isEmpty())){
			System.out.println("Allergy is documented");
			ConstantsFile.allergiesisdocumented=true;
		}
		else{
			System.out.println("Allergy is not documented");
			ConstantsFile.allergiesisdocumented=false;
		}
		return ConstantsFile.allergiesisdocumented;
	}
	/**
	 * This method is used for verify the condition user list should open and any searched value should be clicked
	 * 
	 */
	public boolean verifyCondtionsUserList(){
		clickOnProfileInPatient();
		sleep(12000);
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		WebElement condtion = SeleniumUtil.getElementWithFluentWait(condtionInProfile);
		SeleniumUtil.scrolltoWebElement(condtion);
		condtion.click();
		driver.switchTo().parentFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
		sleep(5000);
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("AddProfileData");
		String condtionName =ConstantsFile.loginData.get(ConstantsFile.ADDCONDTIONSMINPROFILE);
		WebElement condtionNameValue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ condtionName +"')]"));
		if(condtionNameValue.isDisplayed()){
			System.out.println("right panel is opened");
			Actions action = new Actions(driver);
			action.moveToElement(condtionNameValue).doubleClick().build().perform();
			ConstantsFile.conditionuserlist=true;
		}
		else{
			System.out.println("right panel is not opened");
			ConstantsFile.conditionuserlist= false;
		}
		return ConstantsFile.conditionuserlist;
	}
	/**
	 * This method is used for verify the conditions popup details
	 * 
	 */
	public boolean verifyCondtionsPopDetails() {
		try{
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String condtionName=SeleniumUtil.getElementWithFluentWait(textBoxarea).getText();
		if(condtionName!=null){
			System.out.println("condtion field is present in the condtion detail pop");
			SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			ConstantsFile.condtionisdocumented=true;
		}
		}
		catch(Exception e){
			System.out.println("condtion field is blank in the condtion detail pop");
		}
		return ConstantsFile.condtionisdocumented;
	}
	/**
	 * This method is used for verify the Immunizations user list should open and any searched value should be clicked
	 *
	 */
	public boolean verifyImmunizationsUserList(){
		clickOnProfileInPatient();
		sleep(12000);
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		sleep(9000);
		WebElement immunization =SeleniumUtil.getElementWithFluentWait(immunizationInProfile);
		SeleniumUtil.scrolltoWebElement(immunization);
		immunization.click();
		sleep(5000);
		driver.switchTo().parentFrame();
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("AddProfileData");
		String immunizationName =ConstantsFile.loginData.get(ConstantsFile.ADDIMMUNIZATIONSPROFILE);
		WebElement immunizationValue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ immunizationName +"')]"));
		if(immunizationValue.isDisplayed()){
			System.out.println("right panel is opened");
			Actions action = new Actions(driver);
			action.moveToElement(immunizationValue).doubleClick().build().perform();
			ConstantsFile.immunizationuserlist=true;

		}
		else{
			System.out.println("right panel is not opened");
			ConstantsFile.immunizationuserlist=false;
		}
		return ConstantsFile.immunizationuserlist;
	}
	/**
	 * This method is used for verify the Immunizations popup details
	 * 
	 */
	public boolean verifyImmunizationsPopupDetails() {
		try{
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String VaccineName= SeleniumUtil.getElementWithFluentWait(immunizationTextBox).getText();
		if(VaccineName!=null){
			System.out.println("Immunizations field is present in the condtion detail pop");
			SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			ConstantsFile.immunizationsdocumented=true;
		}
		}
		catch(Exception e){
			System.out.println("Immunizations field is blank in the condtion detail pop");
		}
		return ConstantsFile.immunizationsdocumented;
	}
	/**
	 * This method is used for verify the DxStudies user list should open and any searched value should be clicked
	 * 
	 */
	public boolean verifyDxStudiesUserList() {
		clickOnProfileInPatient();
		sleep(12000);
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		sleep(9000);
		WebElement dxstudies = SeleniumUtil.getElementWithFluentWait(diagonticData);
		SeleniumUtil.scrolltoWebElement(dxstudies);
		dxstudies.click();
		driver.switchTo().parentFrame();
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
		sleep(5000);
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("AddProfileData");
		String dxName =ConstantsFile.loginData.get(ConstantsFile.ADDDXPROFILE);

		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(dxName);
		SeleniumUtil.getElementWithFluentWait(searchIcon).click();
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement dxNameValue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ dxName +"')]"));
		if(dxNameValue.isDisplayed()){
			System.out.println("right panel is opened");
			Actions action = new Actions(driver);
			action.moveToElement(dxNameValue).doubleClick().build().perform();
			ConstantsFile.dxstudiesuserlist=true;
		}
		else{
			System.out.println("right panel is not opened");
			ConstantsFile.dxstudiesuserlist=false;
		}
		return ConstantsFile.dxstudiesuserlist;
	}
	/**
	 * This method is used for verify the DxStudies popup details
	 * 
	 */
	public boolean verifyDxStudiesPopupDetails(){
		try{
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String diagnosticstudyName= SeleniumUtil.getElementWithFluentWait(textBoxarea).getText();
		if(diagnosticstudyName!=null){
			System.out.println("diagnostic Study field is present in the Diagnostic Study detail pop");
			SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			ConstantsFile.dxStudiesdocumented=true;
		}
		}
		catch(Exception e){
			System.out.println("diagnostic Study field is blank in the Diagnostic Study detail pop");
		}
		return ConstantsFile.dxStudiesdocumented;
	}
	/**
	 * This method is used for verify the Procedures user list should open and any searched value should be clicked
	 * 
	 */
	public boolean verifyProceduresUserList(){
		clickOnProfileInPatient();
		sleep(12000);
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		sleep(9000);
		WebElement procedures = SeleniumUtil.getElementWithFluentWait(procedureDataText);
		SeleniumUtil.scrolltoWebElement(procedures);
		procedures.click();
		driver.switchTo().parentFrame();
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
		sleep(5000);
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("AddProfileData");
		String procedureName =ConstantsFile.loginData.get(ConstantsFile.ADDPRECEDUREPROFILE);
		WebElement procedureNameValue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ procedureName +"')]"));
		if(procedureNameValue.isDisplayed()){
			System.out.println("right panel is opened");
			Actions action = new Actions(driver);
			action.moveToElement(procedureNameValue).doubleClick().build().perform();
			ConstantsFile.procedureuserlist=   true;
		}
		else{
			System.out.println("right panel is not opened");
			ConstantsFile.procedureuserlist=false;
		}
		return ConstantsFile.procedureuserlist;
	}
	public void switchToTooltip(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
	}
	/**
	 * This method is used for verify the Procedures popup details
	 *
	 */
	public boolean verifyProceduresPopupDetails() {
		try{
		String ProcedureName=SeleniumUtil.getElementWithFluentWait(textBoxarea).getText();
		if(ProcedureName!=null){
			System.out.println("Procedure field is present in the Procedure detail pop");
			SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			ConstantsFile.procedureisdocumented=true;
		}
		}
		catch(Exception e){
			System.out.println("Procedure Study field is blank in the Procedure detail pop");
		}
		return ConstantsFile.procedureisdocumented;
	}
	/**
	 * This method is used for switch Into Profile Frame 
	 *
	 */
	public void switchToProfileFrame(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");	
	}
	/**
	 * This method is used for verify the patient name Data in Profile Screen
	 *
	 */
	public boolean verifyPatientNameInProfileScreen() {
		boolean isPatientNamePresent=false;
		switchToProfileFrame();
		String patientNameData=SeleniumUtil.getElementWithFluentWait(patientNameInProfile).getText();
		if(patientNameData.equalsIgnoreCase(existingProfilePatientCompletename) ){
			System.out.println("patient Name is present in Profile Screen");
			isPatientNamePresent=true;
		}
		return isPatientNamePresent;		
	}
	/**
	 * This method is used for verify the patient date of birth  Data in Profile Screen
	 *
	 */
	public boolean verifyPatientDOBInProfileScreen() {
		boolean isPatientDOBPresent=false;
		//switchToProfileFrame();
		String patientDOBData=SeleniumUtil.getElementWithFluentWait(patientDOBInProfile).getText();
		if(patientDOBData.contains(existingProfilePatientDateOfBirth) && patientDOBData.contains(existingPatientAge) ){
			System.out.println("correct date of birth is present in Profile Screen");
			isPatientDOBPresent=true;
		}
		return isPatientDOBPresent;		
	}
	/**
	 * This method is used for verify the patient record number Data in Profile Screen
	 *
	 */
	public boolean verifyPatientRecordNumberInProfileScreen() {
		boolean isPatientRecordNumberPresent=false;
		//switchToProfileFrame();
		String patientMRNData=SeleniumUtil.getElementWithFluentWait(patientMRNInProfile).getText();
		if(patientMRNData.contains(DemographicsPage.existingPatientrecordnumber) ){
			System.out.println("correct record number is present in Profile Screen");
			isPatientRecordNumberPresent=true;
		}
		return isPatientRecordNumberPresent;		
	}
	/**
	 * This method is used for verify the patient SSN Data in Profile Screen
	 *
	 */
	public boolean verifyPatientSSNNumberInProfileScreen() {
		boolean isPatientSSNPresent=false;
		//switchToProfileFrame();
		String patientSSNData=SeleniumUtil.getElementWithFluentWait(patientSSNInProfile).getText();
		if(patientSSNData.contains(existingProfilepatientSSN) ){
			System.out.println("correct record number is present in Profile Screen");
			isPatientSSNPresent=true;
		}
		return isPatientSSNPresent;		
	}
	/**
	 * This method is used for verify the patient address Data in Profile Screen
	 *
	 */
	public boolean verifyPatientAddressInProfileScreen() {
		boolean isPatientAddressPresent=false;
		//switchToProfileFrame();
		String patientAddressData=SeleniumUtil.getElementWithFluentWait(patientAddressInProfile).getText();
		if(patientAddressData.contains(existingProfilepatientAddress) ){
			System.out.println("correct address is present in Profile Screen");
			isPatientAddressPresent=true;
		}
		return isPatientAddressPresent;		
	}
	/**
	 * This method is used for verify the patient mail Address Data in Profile Screen
	 *
	 */
	public boolean verifyPatientMailInProfileScreen() {
		boolean isPatientMailPresent=false;
		//switchToProfileFrame();
		String patientMailData=SeleniumUtil.getElementWithFluentWait(patientEmailInProfile).getText();
		if(patientMailData.contains(existingProfilepatientMailId) ){
			System.out.println("correct mail ID is present in Profile Screen");
			isPatientMailPresent=true;
		}
		return isPatientMailPresent;		
	}
	/**
	 * This method is used for verify the insurance Data in Profile Screen
	 *
	 */
	public boolean verifyPatientInsuranceDataInProfileScreen() {
		boolean isPatientInsurancePresent=false;
		List<WebElement> totalInsurancetrows = driver.findElements(insuranceRow);
		System.out.println("NUMBER OF INSURANCES= "+totalInsurancetrows.size());
		for(WebElement trElement : totalInsurancetrows)
		{
			String rowText=trElement.getText();
			System.out.println(rowText);
			if(rowText.equalsIgnoreCase(existingProfilePatientInsurance)){
				System.out.println("corrected patient insurance row is displayed");
				isPatientInsurancePresent=true;
				break;
			}    
		}
		return isPatientInsurancePresent;
	}	

	/**
	 * This method is used for verify the menus icons are present  in Profile Screen
	 *
	 */
	public boolean verifyMenuIcons(){
		boolean isMenuIconsPresent=false;
		switchToProfileFrame();
		String menusvalues =SeleniumUtil.getElementWithFluentWait(menuIconsInProfile).getAttribute("ids");
		System.out.println(menusvalues);
		if(menusvalues.equalsIgnoreCase(patientProfileMenuIcons)){
			System.out.println("all menu icons are present");
			isMenuIconsPresent=true;
		}
		return isMenuIconsPresent;
	}
	/**
	 * This method is used for clicking on different menu icons  are present  in Profile Screen
	 *
	 */
	public void clickOnMenusInProfileScreen(String menuName){
		switchToProfileFrame();
		List<WebElement> totalMenusInProfile =driver.findElements(toolBarMenuInProfile);
		System.out.println(totalMenusInProfile.size());
		for(WebElement menuElement1:totalMenusInProfile){
			String menuNameInProfile =menuElement1.getAttribute("title");
			System.out.println("menu name is"+menuNameInProfile);
			if(menuNameInProfile.equalsIgnoreCase(menuName)){
				menuElement1.click();
				break;
			}
		}
	}
	/**
	 * This method is used for verify fax window is opened when user click on fax menu in profile screen 
	 *
	 */
	public boolean verifyFaxwindowInProfileScreen(){
		boolean isFaxWindowOpened=false;
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
		String patientRecordTitle =SeleniumUtil.getElementWithFluentWait(TaskPage.PopupTitle).getText();
		if(patientRecordTitle.equalsIgnoreCase("Patient Record")){
			System.out.println("Patient Record window opened");
			isFaxWindowOpened=true;
		}
		return isFaxWindowOpened;
	}
	/**
	 * This method is used for clicking on profile tab present in Fax window
	 *
	 */
	public void clickOnProfileTabInFax(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(profileTabInFax).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}

	/**
	 * This method is used for select any checkbox value for faxing
	 *
	 */
	public void selectCheckBoxForFaxing(){
		SeleniumUtil.switchToFrame(driver, "fraDistributeProfile_Frame");
		List<WebElement> totalcheckboxTabs =driver.findElements(By.xpath(".//img[@class='CheckBox']"));
		System.out.println(totalcheckboxTabs.size());
		for(WebElement menuElement1:totalcheckboxTabs){
			String rowText =menuElement1.getAttribute("id");
			System.out.println(rowText);
			menuElement1.click();
			break;
		}

	}

	/**
	 * This method is used for click on add button when user select any checkbox value for faxing
	 *
	 */
	public void clickOnAddButton(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(addButtonInFax).click();
	}
	/**
	 * This method is used for selecting record for  faxing
	 *
	 */
	public void selectRecordForFaxing(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(By.className("rtChk")).click();
	}
	/**
	 * This method is used for click on Fax button for faxing
	 *
	 */
	public void clickOnFaxButton(){
		SeleniumUtil.getElementWithFluentWait(faxButtonInFax).click();

	}
	/**
	 * This method is used for click on Fax window for faxing
	 *
	 */
	public void clickOnFaxWindow(){
		SeleniumUtil.getElementWithFluentWait(faxButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for click on Fax button & adding faxing details for faxing
	 *
	 */
	public void addFaxDetailsForFaxing(){
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		SeleniumUtil.waitForProgressBar(Frames.CREATION);
		SeleniumUtil.getElementWithFluentWait(recipientName).sendKeys(patientProfilerecipientName);
		SeleniumUtil.getElementWithFluentWait(faxDigit1).sendKeys(patientProfilerfax0);
		SeleniumUtil.getElementWithFluentWait(faxDigit2).sendKeys(patientProfilerfax1);
		SeleniumUtil.getElementWithFluentWait(faxDigit3).sendKeys(patientProfilerfax2);
		clickOnFaxWindow();
	}
	/**
	 * This method is used for entering faxing details for faxing profile data
	 *
	 */
	public void enterFaxDetailsInProfileScreen(){
		clickOnProfileTabInFax();
		selectCheckBoxForFaxing();
		clickOnAddButton();
		selectRecordForFaxing();
		clickOnFaxButton();
	}
	/**
	 * This method is used for closing printing window 
	 *
	 */
	public void closePrintWindow(){
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.getElementWithFluentWait(closePopUp).click();
	}
	/**
	 * This method is used for click on Fax button for faxing
	 *
	 */
	public void clickOnFaxButtonInFaxingWindow(){
		SeleniumUtil.getElementWithFluentWait(faxButtonInFax).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for verify whether when user clicks on faxing button it should go in outbound faxing
	 * 
	 */
	public boolean selectOutBoundRowForExistingPatient(String patientfirstname){
		SeleniumUtil.waitForProgressBar(Frames.FAXING);
		sleep(9000);
		boolean faxrowpresent=false;
		SeleniumUtil.switchToParentFrame(Frames.FAXING);
		SeleniumUtil.waitForProgressBar(Frames.FAXING);
		List<WebElement> searchPatientrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF ROWS IN THIS TABLE = "+searchPatientrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : searchPatientrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.equalsIgnoreCase(patientfirstname)){
					System.out.println("corrected patient row is displayed");
					//tdElement.click();
					faxrowpresent=true;
					break;
				}

			} 
			row_num++;
		}
		return faxrowpresent;
	}
	/**
	 * This method is used for clicking on print button
	 * 
	 */
	public void clickOnPrintForProfile() throws FindFailed{
		SeleniumUtil.clickOnImageWitScreenInSikuli("printbutton");
	}
	/**
	 * This method is used for verify patient interview Form  is opened when user click on PIF menu in profile screen 
	 *
	 */
	public boolean verifyPIFInProfileScreen(){
		boolean isPIFPageOpened=false;
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
		String patientRecordTitle =SeleniumUtil.getElementWithFluentWait(pifTitle).getText();
		if(patientRecordTitle.contains("Patient Interview Form")){
			System.out.println("Patient interview Form is opened..");
			isPIFPageOpened=true;
		}
		return isPIFPageOpened;
	}
	/**
	 * This method is used to click on close button for closing the  PIF module
	 * 
	 */
	public void clickOnCloseButtonInProfile(){
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
		SeleniumUtil.getElementWithFluentWait(closePif).click();
	}
	/**
	 * This method is used to click on History drop down from Profile Module
	 * 
	 */
	public void clickOnHistoryInProfile(){
		switchToProfileFrame();
		WebElement sendButton = SeleniumUtil.getElementWithFluentWait(PrescriptionPage.toolbarbutton);
		action.moveToElement(sendButton).click().build().perform();
	}

	/**
	 * This method is used to click on different history buttons in Profile Module
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnHistoryValueInProfile(String imageName) throws FindFailed{
		SeleniumUtil.clickOnImageWitScreenInSikuli(imageName);	
	}
	/**
	 * This method is used for verify Profile/Task History is opened when user click on Profile History menu in profile screen 
	 *
	 */
	public boolean verifyHistoryPageInProfileScreen(String PageName){
		boolean isProfileHistoryOpened=false;
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
		String patientRecordTitle =SeleniumUtil.getElementWithFluentWait(TaskPage.PopupTitle).getText();
		if(patientRecordTitle.contains(PageName)){
			System.out.println("Profile/Task History window is opened..");
			//SeleniumUtil.getElementWithFluentWait(TaskPage.PopupTitle).sendKeys(Keys.ESCAPE);
			isProfileHistoryOpened=true;
		}
		return isProfileHistoryOpened;
	}
	/**
	 * This method is used for close the Profile/Task History pop up in profile screen 
	 *
	 */
	public void closePopupTitle(){
		SeleniumUtil.getElementWithFluentWait(closePopup).click();
	}
	/**
	 * This method is used for clicking on Diagnoses Section present in profile of  patient chart
	 * @throws Exception
	 */
	public  void clickOnProblemSectionInProfile() {
		switchToProfileFrame();
		sleep(5000);
		WebElement problem = SeleniumUtil.getElementWithFluentWait(problemvalue);
		SeleniumUtil.scrolltoWebElement(problem);
		problem.click();;
	}
	/**
	 * This method is used for click On None checkbox present in left panel when user click on problem section in  Profile screen 
	 *
	 */
	public void clickOnNoneCheckbox(){
		switchToDiagnosisListFrame();
		SeleniumUtil.getElementWithFluentWait(noneCheckbox).click();
	}
	/**
	 * This method is used for switching into diagnose user list frame in  Profile screen 
	 *
	 */
	public static void switchToDiagnosisListFrame(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		SeleniumUtil.switchToFrame(driver, "panList_Frame");
	}

	/**
	 * This method is used for verify when user clicks on None checkbox then some validation text should be display in profile screen 
	 *
	 */
	public boolean reviewedDiagnosisInProfileScreen(){
		boolean isReviewedProblem=false;
		String ReviewedProblemTitle =SeleniumUtil.getElementWithFluentWait(reviewDiagnosisTitle).getText();
		if(ReviewedProblemTitle.contains("Problems reconciliation last performed")){
			System.out.println("Problems reconciliation last performed Text Present..");
			//SeleniumUtil.getElementWithFluentWait(TaskPage.PopupTitle).sendKeys(Keys.ESCAPE);
			isReviewedProblem=true;
		}
		return isReviewedProblem;
	}
	/**
	 * This method is used for adding problem  in profile screen 
	 *
	 */
	public void addDiagnosisInProfileScreen(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
		SeleniumUtil.getElementWithFluentWait(reviewDiagnosisSearchBox).click();
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(patientProfilerDiagnosisvalue);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.searchbutton).click();
		sleep(5000);
		List<WebElement> searchdiagnosisrow = driver.findElements(totaltrtagsForProfile);
		System.out.println("NUMBER OF DIAGNOSIS IN THIS TABLE = "+searchdiagnosisrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : searchdiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.equalsIgnoreCase(patientProfilerDiagnosisvalue)){
					System.out.println("corrected patient row is displayed");
					SeleniumUtil.doubleClick(tdElement);
					break;
				}
			} 
			break;	       
		}

	}
	/**This method will verify if existing service  are created for patient in patient chart module ,then delete the existing created services
	 * 
	 * @throws FindFailed
	 */
	public void deleteExistingService() throws FindFailed{
		sleep(5000);
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		List<WebElement> totaltrrows = driver.findElements(existingRows);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row text is"+rowtext);
			if(rowtext.contains("First Visit") || rowtext.contains("Colonoscopy")){
				System.out.println("Service already  added in demographics");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("selectDelete");
				SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);	
				SeleniumUtil.getElementWithFluentWait(yesButtonInService).click();
				deleteExistingService();
			}
			else{
				System.out.println("No existing service are added");
			}	
		}	
	}
	/**This method will verify if existing problems  are added in profile module ,then delete the existing problem details
	 * 
	 * @throws FindFailed
	 */
	public void deleteExistingAddedProblemDetails() throws FindFailed{
		sleep(5000);
		switchToDiagnosisListFrame();
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row text is"+rowtext);
			if(rowtext.contains(patientProfilerDiagnosisvalue) || rowtext.contains(patientProfilerDiagnosisUserListvalue) || rowtext.contains(patientProfilerDiagnosisFromFirstVisit)){
				System.out.println("Diagnosis already  added in demographics");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("selectDelete");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");			
			}
			else{
				System.out.println("No existing problem are present");
			}

		}
	}
	/**
	 * This method is used for verify None checkbox is checked or unchecked in profile screen 
	 *
	 */
	public void verifyNoneCheckBoxInProfileScreen(){
		switchToDiagnosisListFrame();
		String checkNoneCheckBoxState =SeleniumUtil.getElementWithFluentWait(noneCheckboxState).getAttribute("stateName");
		if(checkNoneCheckBoxState.equalsIgnoreCase("unchecked")){
			System.out.println("None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noneCheckbox).click();
		}
		else{
			System.out.println("click None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noneCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(noneCheckbox).click();
		}	
	}
	/**
	 * This method is used for switching in Problem Frame in profile screen 
	 *
	 */
	public void switchToProblemFrame(){
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
	}

	/**
	 * This method is used for verify Diagnoses user list is opened for selecting the value from the user list
	 * 
	 */

	public void addDiagnosisFromUserList(){
		switchToUserListFrame();
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(patientProfilerDiagnosisICD10);
		SeleniumUtil.getElementWithFluentWait(searchIcon).click();
		sleep(9000);
		WebElement plussign =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ patientProfilerDiagnosisUserListvalue +"')]"));
		action.moveToElement(plussign).doubleClick().build().perform();
	}
	/**
	 * This method is used for switching in user list  Frame present in profile screen 
	 *
	 */
	public void switchToUserListFrame(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
	}
	/**
	 * This method is used for selecting impression value user list  from First visit service
	 *
	 */
	public void selectImpressionValueFromUserList(){
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserListDiagnosis_Frame");
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(patientProfilerDiagnosisFromFirstVisit);
		SeleniumUtil.getElementWithFluentWait(searchIcon).click();
		sleep(5000);
		WebElement plussign =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ patientProfilerDiagnosisFromFirstVisit +"')]"));
		action.moveToElement(plussign).doubleClick().build().perform();
	}
	/**
	 * This method is used for clicking on patient chart tab
	 *
	 */
	public void clickOnPatientChartTab(){
		DynamicFramePage.dynamicFrameForPanchart();
		SeleniumUtil.getElementWithFluentWait(patientChartTab).click();
	}
	/**
	 * This method is used for verify all the diagnoses value documented  either from user list,new Button or service user list then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyAllProblemsDocumentedInProfile(){
		//clickOnProfileInPatient();
		boolean isProblemAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		sleep(5000);
		String textIs=SeleniumUtil.getElementWithFluentWait(diagnosisListInProfile).getText();
		if(textIs.equalsIgnoreCase(problemTextinProfile)){
			System.out.println("corrected problems are added in profile"); 
			isProblemAdded=true;
		}
		return isProblemAdded;				
	}
	/**
	 * This method is used for creating new guideline for the   patient 
	 *
	 */
	public void createNewGuidlines(){
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.getElementWithFluentWait(guidlinesTextBox).sendKeys(patientProfileGuidlinesName);
		addDemographicsCriteria();
	}
	/**
	 * This method is used for adding demographics Criteria for new guideline for the   patient 
	 *
	 */
	public void addDemographicsCriteria(){
		SeleniumUtil.getElementWithFluentWait(guidlinesFromAgeTextBox).sendKeys(DemographicsPage.existingPatientFromAge);
		SeleniumUtil.getElementWithFluentWait(guidlinesFromAgeTextBox).sendKeys(Keys.TAB);
		SeleniumUtil.getElementWithFluentWait(guidlinesToAgeTextBox).sendKeys(Keys.TAB);
		SeleniumUtil.getElementWithFluentWait(guidlinesToAgeTextBox).sendKeys(DemographicsPage.existingPatientToAge);	

	}
	/**This method will verify if  recommendations  are added in profile module
	 * 
	 */
	public boolean verifyRecommendationsInProfile(){
		boolean isRecommendationsAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		sleep(5000);
		String guidlinevalue =SeleniumUtil.getElementWithFluentWait(guildlineListInProfile).getText();
		logger.info("guidline text is.. "+guidlinevalue);
		String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
		String text =guidlineTextinProfile+currentDate;
		if(guidlinevalue.equalsIgnoreCase(text)){
			logger.info("correct guilines are displayed");
			isRecommendationsAdded=true;

		}
		return isRecommendationsAdded;				
	}
	/**This method will verify if existing recommendations  are added in configuration module ,then delete the existing recommendations details
	 * 
	 * @throws FindFailed
	 */
	public void verifyAddedRecommandationsDetails() throws FindFailed{
		sleep(5000);
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row text is"+rowtext);
			if(rowtext.contains(patientProfileGuidlinesName) ){
				System.out.println("recommandations already  added in guidline module");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("selectDelete");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");			
			}
			else{
				System.out.println("No existing recommandations are present");
			}

		}

	}
	/**
	 * This method is used to set activity for the searched patient
	 * @throws Exception 
	 */
	public  void setExistingActivityForProfile() throws Exception{
		boolean isActivityPresent=false;
		//Click on Activity drop down text box
		Thread.sleep(3000);
		logger.info("clicking on activity drop down menu...");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.activitydropdown).click();
		SeleniumUtil.waitForProgressBar(Frames.APPOINTMENT_CREATION);
		//For switching in the activity drop down Frame  
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		List<WebElement> activitiesValues=driver.findElements(AppointmentPage.totaltrtags);
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : activitiesValues)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF ACTIVITIES="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(existingActivityForProfilePatient)){
					System.out.println("corrected future appointment is present in profile"); 
					isActivityPresent=true;
					tdElement.click();
					break;
				}

			} 
			if(isActivityPresent){
				break;
			}
			else{
				row_num++;
			}
		}
	}	
	/**This method is used to view future appointment in profile of patient in the medical chart  
	 * 
	 * 
	 */
	public boolean viewFutureAppointmentInProfile(){
		boolean isFutureAppointmentAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String futureAppointmentvalue =SeleniumUtil.getElementWithFluentWait(futureAppointmentListInProfile).getText();
		logger.info("future Appointment text is.. "+futureAppointmentvalue);
		String futureValue=DateUtil.getFutureDate();
		String text =futureValue+" "+existingActivityForProfilePatient+'\n'+gmedLoaction;
		System.out.println("text for appointment is"+text);
		if(futureAppointmentvalue.contains(text)){
			logger.info("correct Future appointment are displayed");
			isFutureAppointmentAdded=true;		
		}
		return isFutureAppointmentAdded;	
	}
	/**
	 * This method is used to click on recall section present in profile screen
	 */
	public void clickOnRecallSectionInProfile(){
		switchToProfileFrame();
		SeleniumUtil.getElementWithFluentWait(recallSectionInProfile).click();
		sleep(5000);
	}
	/**
	 * This method is used to switch into recall Frame for profile screen
	 */
	public void switchToRecallFrame(){
		switchToProfileFrame();
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
	}
	/**This method will verify if existing recall queue  are added in recall module ,then delete the existing recall details
	 * 
	 */
	public void deleteExistingRecall(String menuName ,String submenuName){
		boolean isElementVisible=false;
		switchToRecallFrame();
		List<WebElement> totaltrrows = driver.findElements(totalRecallQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(existingProfileRecallType)){
				System.out.println("recall queue are present");
				SeleniumUtil.rightClick(irows);
				sleep(3000);
				List<WebElement> totaltrrows1 = driver.findElements(rightMenuForRecallQueue);
				System.out.println(totaltrrows1.size());
				for(WebElement irows1:totaltrrows1){
					String rowtext1 =irows1.getText();
					System.out.println(rowtext1);
					if(rowtext1.contains(menuName)){
						WebElement e1= driver.findElement(By.xpath(".//*[contains(text(),'"+ menuName +"')]"));
						action.moveToElement(e1).build().perform();
						//SeleniumUtil.rightClick(e1);
						List<WebElement> totaltrrows11 = driver.findElements(subMenuForRecallQueue);
						System.out.println(totaltrrows11.size());
						for(WebElement irows11:totaltrrows11){
							String rowText1=irows11.getText();
							System.out.println(rowText1);
							if(rowText1.contains(submenuName)){
								WebElement e11= driver.findElement(By.xpath(".//*[contains(text(),'"+ submenuName +"')]"));
								e11.click();
								//action.moveToElement(e11).click().build().perform();
								sleep(8000);
								SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
								SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();
								isElementVisible=true;
								break;
							}
						}
					}
					if(isElementVisible){
						System.out.println("Element get visiable");
						break;
					}
					else{
						System.out.println("No existing row present");
					}
				}
				if(isElementVisible){
					System.out.println("Element get visiable");
					break;
				}
				else{
					System.out.println("Not Visable");
					continue;
				}
			}
		}
	}
	/**This method is used for creating new Recall  which will select provider and recall type
	 * 
	 */
	public void createNewRecall(){
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
		addProviderInRecall();
		selectRecallType();
		getCurrentRecallDate();
	}
	/**This method is used for selecting new provider for creating new Recall  for profile module
	 * 
	 */
	public void addProviderInRecall(){
		SeleniumUtil.getElementWithFluentWait(providerDropDownInProfile).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		searchProvider();
	}
	public void getCurrentRecallDate(){
		String recallDateValue=SeleniumUtil.getElementWithFluentWait(By.className("TextBox")).getText();
		System.out.println(recallDateValue);
		ConstantsFile.recallMonthValue=SeleniumUtil.getElementWithFluentWait(By.id("dtbRecallDate_Month")).getAttribute("value");
		System.out.println(ConstantsFile.recallMonthValue);
		ConstantsFile.recalldayValue=SeleniumUtil.getElementWithFluentWait(By.id("dtbRecallDate_Day")).getAttribute("value");
		System.out.println(ConstantsFile.recalldayValue);
		ConstantsFile.recallYearValue=SeleniumUtil.getElementWithFluentWait(By.id("dtbRecallDate_Year")).getAttribute("value");
		System.out.println(ConstantsFile.recallYearValue);
	}
	/**This method is used for selecting recall type for creating new Recall  for profile module
	 * 
	 */
	public void selectRecallType(){
		boolean isRecallTypePresent=false;
		switchToRecallFrame();
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
		SeleniumUtil.getElementWithFluentWait(OrdersPage.recallDropDown).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		List<WebElement>  trtags =driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF RECALL TYPE ROWS IN THIS TABLE = "+trtags.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : trtags)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.equalsIgnoreCase(existingProfileRecallType)){
					System.out.println("corrected recall row is present in profile"); 
					SeleniumUtil.doubleClick(tdElement);
					isRecallTypePresent=true;
					break;
				}	    
			} 
			if(isRecallTypePresent){
				switchToRecallFrame();
				SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
				break;
			}
			else{
				row_num++;
			}
		}

	}
	/**This method is used for searching the provider which is required for creating new Recall for profile module
	 * 
	 */
	public void searchProvider(){
		boolean isProviderPresent=false;
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(existingProfileProviderfirstname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(existingProfileProviderlastname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		List <WebElement> providernamevalues =driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF PROVIDER ROWS IN THIS TABLE = "+providernamevalues.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : providernamevalues)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(existingProfileProviderlastname)){
					System.out.println("corrected provider row is present in profile"); 
					SeleniumUtil.doubleClick(tdElement);
					isProviderPresent=true;
					break;
				}

			} 
			if(isProviderPresent){
				break;
			}
			else{
				row_num++;
			}
		}

	}
	/** This method is used for closing the recall screen
	 * 
	 */
	public void closeRecallScreenInProfile(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.getElementWithFluentWait(closeRecallPopup).click();	
	}
	/** This method is used for verifying the created recall is documented in the profile 
	 * 
	 */
	public boolean verifyRecallAddedInProfile(){
		boolean isRecallAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		sleep(5000);
		String recallvalue =SeleniumUtil.getElementWithFluentWait(recallListInProfile).getText();
		logger.info("recall text is.. "+recallvalue);
		String futureYear=DateUtil.getFutureYear();
		System.out.println(futureYear);
		String text =existingProfileRecallType+" "+futureYear/*+" "+ConstantsFile.recallMonthValue+"/"+ConstantsFile.recalldayValue+"/"+ConstantsFile.recallYearValue;*/;
		if(recallvalue.contains(text)){
			logger.info("correct recall  are displayed");
			isRecallAdded=true;

		}
		return isRecallAdded;
	}

	/**This method will verify if existing service  is added in Patient chart module ,then delete the existing services details
	 * 
	 * @throws FindFailed
	 */
	public void verifyAddedServicesDetails() throws FindFailed{
		sleep(5000);
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		List<WebElement> totaltrrows = driver.findElements(existingRows);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowtext =irows.getText();
			System.out.println("row text is"+rowtext);
			if(rowtext.contains("First Visit")|| rowtext.contains("Colonoscopy")){
				System.out.println("Service already added in patient chart");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("selectDelete");
				SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);	
				SeleniumUtil.getElementWithFluentWait(yesPopup).click();
				verifyAddedServicesDetails();
				break;
			}
			else{
				System.out.println("No existing service are present");
			}		
		}	
	}
	/** This method is used for clicking on PatientTab
	 * 
	 */
	public void clickOnPatientTab(){
		DynamicFramePage.dynamicFrameForPanchart();
		SeleniumUtil.getElementWithFluentWait(patientTab).click();
	}
	/** This method is used for verifying the added vital data  is documented in the profile 
	 * 
	 */
	public boolean verifyVitalSignAddedInProfile(){
		boolean isVitalSignAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		List<WebElement> addedVitalSignsrow = driver.findElements(totalVitalSignQueue);
		System.out.println("NUMBER OF VITAL SIGN DATA IN THIS TABLE = "+addedVitalSignsrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : addedVitalSignsrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.equalsIgnoreCase(vitalSignWeightForProfilePatient) || rowText.equalsIgnoreCase(vitalSignHeightForProfilePatient) || rowText.equalsIgnoreCase(vitalSignHeightBMIForProfilePatient)){
					System.out.println("corrected vital sign values are added in profile"); 
					isVitalSignAdded=true;
				}

			} 
			row_num++;
		}
		return isVitalSignAdded;
	}
	/**
	 * This method is used to click on Advance Directive section present in profile screen
	 */
	public void clickOnAdvanceDirectiveSectionInProfile(){
		switchToProfileFrame();
		SeleniumUtil.getElementWithFluentWait(advanceDirectivesInProfile).click();
		sleep(5000);
	}
	/**
	 * This method is used to select any radio button value present in Advance Directive section for profile screen
	 */
	public void selectAnyCheckboxValueForAdvanceDirective(){
		switchToDiagnosisListFrame();
		SeleniumUtil.getElementWithFluentWait(advanceDirectivesCheckBoxInProfile).click();
		reviewNotes();
	}
	/**
	 * This method is used to verify advance directive value is document for profile screen
	 */
	public boolean verifyAdvanceDirectiveValueAddedInProfile(){
		boolean isAdvanceDirectiveAdded=false;
		switchToProfileFrame();
		sleep(5000);
		String advanceDirectivevalue=SeleniumUtil.getElementWithFluentWait(advanceDirectivesTextInProfile).getText();
		String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
		System.out.println(advanceDirectivevalue);
		String text =halfTextForAdvanceDirectiveProfile+currentDate+" "+completeTextForAdvanceDirectiveProfile;
		if(advanceDirectivevalue.equalsIgnoreCase(text)){
			System.out.println("advance directive value is present");
			isAdvanceDirectiveAdded=true;
		}
		return isAdvanceDirectiveAdded;
	}

	public void reviewNotes(){
		SeleniumUtil.getElementWithFluentWait(advanceDirectivesNotesInProfile).clear();
		SeleniumUtil.getElementWithFluentWait(advanceDirectivesNotesInProfile).sendKeys(notesForAdvanceDirectiveProfile);
		SeleniumUtil.getElementWithFluentWait(advanceDirectivesReviewButtonInProfile).click();
	}
	/**
	 * This method is used to verify PID Data  is document for profile screen
	 */
	public boolean verifyPIFAddedInProfile(){
		boolean isPIFAdded=false;
		switchToProfileFrame();
		sleep(5000);
		String pifText=SeleniumUtil.getElementWithFluentWait(pifTextInProfile).getText();
		String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
		System.out.println(pifText);
		String text =halfTextForPIFProfile+currentDate+'\n'+completeTextForPIFProfile;
		if(pifText.equalsIgnoreCase(text)){
			System.out.println("PIF Data is added");
			isPIFAdded=true;
		}
		return isPIFAdded;	
	}
	/**
	 * This method is used to click on current medication section present in profile screen
	 */
	public void clickOnCurrentMedication(){
		switchToProfileFrame();
		WebElement currentmedication = SeleniumUtil.getElementWithFluentWait(medicationPopupPatient);
		SeleniumUtil.scrolltoWebElement(currentmedication);
		currentmedication.click();
	}

	/**This method will verify if existing medication  are added in profile module ,then void the existing mediation details
	 * 
	 * @throws FindFailed
	 */
	public void verifyAddedMedicationDetails() throws FindFailed{
		sleep(5000);
		switchToDiagnosisListFrame();
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowtext =irows.getText();
			System.out.println("row text is"+rowtext);
			if(rowtext.contains(medicationForProfile) || rowtext.contains(recordExistingMedicationForProfile) || rowtext.contains(medicationUsingUserListForProfile) || rowtext.contains(recordExistingUsingUserListForProfile)){
				System.out.println("medications already  added in profile");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("selectVoid");
				SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);	
				SeleniumUtil.getElementWithFluentWait(reasonTextBox).sendKeys("Not Required");
				SeleniumUtil.getElementWithFluentWait(voidButton).click();
				verifyAddedMedicationDetails();
				break;
			}
			else{
				System.out.println("No existing current medication are present");
			}

		}
	}
	/**This method will  verifying added record is displayed in R-Panel
	 * 
	 *
	 */
	public boolean verifyPrescribeMedicationInRPanel(String medicationName){
		sleep(5000);
		switchToDiagnosisListFrame();
		boolean isMedicationDocumented=false;
		List<WebElement> medicationrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF MEDICATION  DATA IN THIS TABLE = "+medicationrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : medicationrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(medicationName)){
					System.out.println("corrected medication values present"); 
					isMedicationDocumented=true;
					break;
				}

			} 
			if(isMedicationDocumented){
				break;
			}
			else{
				row_num++;
			}
		}
		return isMedicationDocumented;
	}
	/**
	 * This method will used for searching  any medication  using search box in User list 
	 */
	public void prescribeUsingUserList(String medicationName){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).clear();
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(medicationName);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchbutton).click();
	}
	/** This method is used for selecting any search medication from user list
	 * 
	 * @param medicationName
	 */
	public void selectMedicationFromUserList(String medicationName){
		boolean isValueSelected=false;
		sleep(8000);
		List<WebElement> medicationrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF MEDICATION  DATA IN THIS TABLE = "+medicationrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : medicationrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(medicationName)){
					System.out.println("corrected medication values present"); 
					SeleniumUtil.doubleClick(tdElement);
					isValueSelected=true;
					break;
				}

			} 
			if(isValueSelected){
				break;
			}
			else{
				row_num++;
			}
		}
	}
	/**
	 * This method will used for clicking on recording existing checkbox for record existing medication
	 */
	public boolean clickOnRecordingExistingMedicationCheckbox(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		logger.info("verifing Existing radio button selected by default for the patient");
		WebElement existingradiobutton = SeleniumUtil.getElementWithFluentWait(PrescriptionPage.prescribeExistingCheckbox);
		logger.info("verifing Existing radio button selected by default for the patient");
		if(existingradiobutton.isSelected()){
			System.out.println("Existing radio button selected by default");		
		}
		else{
			System.out.println("Prescribe radio button is not selected by default");
			existingradiobutton.click();
		}
		return ConstantsFile.prescriptionExistingPopup;
	}
	/**
	 * This method is used for verify when user select any value from the current medication user list then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyAllMedicationIsDocumentedInProfile(){
		//clickOnProfileInPatient();
		boolean isMedicationAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		List<WebElement> addeddiagnosisrow = driver.findElements(totalMedicationForProfile);
		System.out.println("NUMBER OF CURRENT MEDICATIONS IN THIS TABLE = "+addeddiagnosisrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : addeddiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(medicationForProfile) || rowText.contains(recordExistingMedicationForProfile) || rowText.contains(medicationUsingUserListForProfile)){
					System.out.println("corrected medication are added in profile"); 
					isMedicationAdded=true;
				}

			} 
			row_num++;
		}
		return isMedicationAdded;				
	}
	public void addRequiredDetails(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("selecting the SIG for medicine");
		SeleniumUtil.getElementWithFluentWait(PrescriptionPage.sigCheckbox).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(3000);
		SeleniumUtil.getElementWithFluentWait(PrescriptionPage.sigAddButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(3000);
		SeleniumUtil.getElementWithFluentWait(PrescriptionPage.sigAddButton).sendKeys(Keys.ESCAPE);
		SeleniumUtil.getElementWithFluentWait(PrescriptionPage.medicationQuantity).clear();
		SeleniumUtil.getElementWithFluentWait(PrescriptionPage.medicationQuantity).sendKeys("10");
		SeleniumUtil.getElementWithFluentWait(PrescriptionPage.refilltextBox).clear();
		SeleniumUtil.getElementWithFluentWait(PrescriptionPage.refilltextBox).sendKeys("2");
	}

	public void clickOnMedicalOrder(){
		switchToProfileFrame();
		SeleniumUtil.getElementWithFluentWait(openOrderSectionInProfile).click();

	}
	/**
	 * This method is used for verify when user select any value from the diagnoses user list then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyMedicalOrderIsDocumentedInProfile(){
		//clickOnProfileInPatient();
		boolean isOrderAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		List<WebElement> addeddiagnosisrow = driver.findElements(totalMedicalOrderForProfile);
		System.out.println("NUMBER OF Medical Order IN THIS TABLE = "+addeddiagnosisrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : addeddiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(ConstantsFile.orderIntialName)){
					System.out.println("corrected medical order are added in profile"); 
					isOrderAdded=true;
				}

			} 
			row_num++;
		}
		return isOrderAdded;				
	}
	/**
	 * This method is used to click on allergy section present in profile
	 * 
	 */
	public void clickOnAllergySectionInProfile(){
		switchToProfileFrame();
		WebElement allergy = SeleniumUtil.getElementWithFluentWait(allergyInProfile);
		SeleniumUtil.scrolltoWebElement(allergy);
		allergy.click();
	}
	/**This method will verify if existing allergy  are added in profile module ,then delete the existing allergy details
	 * 
	 * @throws FindFailed
	 */
	public void verifyAddedAllergyDetails() throws FindFailed{
		sleep(5000);
		switchToDiagnosisListFrame();
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowtext =irows.getText();
			System.out.println("row text is"+rowtext);
			if(rowtext.contains(addNewAllergyForPatient) || rowtext.contains(selectAllergyFormMyAllergies) || rowtext.contains(selectAllergyForSearchBox) ||rowtext.contains(addNewMedicatiomInAllergyForPatient) || rowtext.contains(allergyValueFromFirstVisit)){
				System.out.println("allergy already  added in demographics");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deleteUser");
				SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);	
				SeleniumUtil.getElementWithFluentWait(reasonTextBox).sendKeys("Not Required");
				SeleniumUtil.getElementWithFluentWait(MedicalChartPage.savebutton).click();
				verifyAddedMedicationDetails();	

			}
			else{
				System.out.println("No existing Allergies  are present");
			}

		}
	}
	/**
	 * This method is used for verify No allergy checkbox is checked or unchecked in profile screen 
	 *
	 */
	public void selectNoAllergyCheckbox(){
		switchToDiagnosisListFrame();
		String noAllergyCheckBoxState =SeleniumUtil.getElementWithFluentWait(noAllergyCheckboxState).getAttribute("stateName");
		if(noAllergyCheckBoxState.equalsIgnoreCase("unchecked")){
			System.out.println("None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noAllergyCheckbox).click();
		}
		else{
			System.out.println("click None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noAllergyCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(noAllergyCheckbox).click();
		}

	}
	/**
	 * This method is used for verify allergy text in profile screen 
	 *
	 */
	public boolean verifyAllergyTextInProfile(String allergyTextValue){
		boolean isNoAllergyAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String allergyText=SeleniumUtil.getElementWithFluentWait(allergyMsgInProfile).getText();
		if(allergyText.equalsIgnoreCase(allergyTextValue)){
			System.out.println( "correct allergy text is doucmented ");
			isNoAllergyAdded=true;
		}
		return isNoAllergyAdded;

	}
	/**
	 * This method is used for verify No drug allergy checkbox is checked or unchecked in profile screen 
	 *
	 */
	public void selectNoDrugAllergyCheckbox(){
		switchToDiagnosisListFrame();
		String noAllergyCheckBoxState =SeleniumUtil.getElementWithFluentWait(noDrugAllergyCheckboxState).getAttribute("stateName");
		if(noAllergyCheckBoxState.equalsIgnoreCase("unchecked")){
			System.out.println("None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noAllergyCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(noDrugAllergyCheckbox).click();
		}
		else{
			System.out.println("click None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noDrugAllergyCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(noDrugAllergyCheckbox).click();
		}
	}
	/**
	 * This method is used for adding allergy detail from new button present in bottom half  R-panel
	 *
	 */
	public void addAllergyDetails(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(textBoxarea).sendKeys(addNewAllergyForPatient);	
		addSeverityForAllergy();
		addManifestation();
	}
	/**
	 * This method is used for adding Severity value for allergy
	 */
	public void addSeverityForAllergy(){
		SeleniumUtil.addTextBoxValue("ddlSeverity_Text","Moderate");
	}
	/**
	 * This method is used for adding Manifestation value for allergy
	 */
	public void addManifestation(){
		SeleniumUtil.getElementWithFluentWait(By.id("txtSnomedCode2Search")).click();
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
		SeleniumUtil.getElementWithFluentWait(snomedCodeTextBox).sendKeys(patientProfilerDiagnosisvalue);
		SeleniumUtil.getElementWithFluentWait(snomedNameSearch).click();
		sleep(5000);
		List<WebElement> searchSnomedCoderow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF SNOMED CODE  IN THIS TABLE = "+searchSnomedCoderow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : searchSnomedCoderow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.equalsIgnoreCase(patientProfilerDiagnosisvalue)){
					System.out.println("corrected patient row is displayed");
					SeleniumUtil.doubleClick(tdElement);
					break;
				}

			} 
			break;

		}					
	}
	/**
	 * This method is used for adding allergy date for My Allergies user list present in R-Panel
	 */
	public void addAllergyDataFromMyAllergiesUserList(){
		switchToUserListFrame();
		WebElement allergyNameValue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ selectAllergyFormMyAllergies +"')]"));
		action.moveToElement(allergyNameValue).doubleClick().build().perform();
	}
	/**
	 * This method is used for searching allergy using allergy search box present in R-Panel
	 */
	public void searchAllergyUsingSearchBox(){
		switchToUserListFrame();
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(selectAllergyForSearchBox);
		SeleniumUtil.getElementWithFluentWait(searchIcon).click();
		sleep(9000);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement allergyvalue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ selectAllergyForSearchBox +"')]"));
		action.moveToElement(allergyvalue).doubleClick().build().perform();
	}
	/**
	 * This method is used for clicking on Medical search tab in allergy
	 */
	public void clickOnMedicalSearchTabInAllergy(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		SeleniumUtil.getElementWithFluentWait(By.id("tabSearch_Text")).click();
	}
	/**
	 * This method is used for adding medication from medical search tab
	 */
	public void addMedicationFromMedicalSearchTab(){
		boolean isMedicationPresent=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(addNewMedicatiomInAllergyForPatient);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchbutton).click();
		sleep(9000);
		List<WebElement> searchMedicationrow = driver.findElements(By.xpath(".//table[@id='tblAllergyGroups_Table']/tbody/tr"));
		System.out.println("NUMBER OF MEDICATION DATA IN THIS TABLE = "+searchMedicationrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : searchMedicationrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(addNewMedicatiomInAllergyForPatient)){
					System.out.println("corrected patient row is displayed");
					SeleniumUtil.doubleClick(tdElement);
					isMedicationPresent=true;
					break;
				}

			} 
			if(isMedicationPresent){
				break;
			}
			else{
				row_num++;
			}

		}			
	}
	/**
	 * This method is used for adding allergy value from user list present in first visit service
	 */
	public void selectAllergyValueFromUserListInService(){
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(allergyValueFromFirstVisit);
		SeleniumUtil.getElementWithFluentWait(searchIcon).click();
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement plussign =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ allergyValueFromFirstVisit +"')]"));
		action.moveToElement(plussign).doubleClick().build().perform();
	}
	/**
	 * This method is used for verify all the allergies value documented  either from user list,new Button or service user list then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyAllAllergiesDocumentedInProfile(){
		//clickOnProfileInPatient();
		boolean isAllergiesAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String allergyData=SeleniumUtil.getElementWithFluentWait(allergyInProfile).getText();
		System.out.println("allergy data text is"+allergyData);
		if(allergyData.equalsIgnoreCase(allergyTextinProfile)){
			logger.info("correct allergy data is added");
			isAllergiesAdded=true;
		}
		return isAllergiesAdded;				
	}
	/**
	 * This method is used to click on condition section present in profile screen
	 * 
	 */
	public void clickOnConditionSectionInProfile(){
		switchToProfileFrame();
		WebElement condtion = SeleniumUtil.getElementWithFluentWait(condtionInProfile);
		SeleniumUtil.scrolltoWebElement(condtion);
		condtion.click();
	}
	/**This method will verify if existing conditions  are added in profile module ,then delete the existing conditions details
	 * 
	 * @throws FindFailed
	 */
	public void verifyAddedCondtionsDetails() throws FindFailed{
		sleep(5000);
		switchToDiagnosisListFrame();
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains(addNewCondtionForPatient) || rowText.contains(condtionValueFromFirstVisit) || rowText.equalsIgnoreCase(patientProfilerDiagnosisvalue)){
				System.out.println("allergy already  added in demographics");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("selectDelete");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
                sleep(6000);
			}
			else{
				System.out.println("No existing conditions  are present");
			}

		}
	}
	/**
	 * This method is used for verify No condition checkbox is checked or unchecked in profile screen 
	 *
	 */
	public void selectNoCondtionCheckbox(){
		switchToDiagnosisListFrame();
		String noAllergyCheckBoxState =SeleniumUtil.getElementWithFluentWait(noACondtionCheckboxState).getAttribute("stateName");
		if(noAllergyCheckBoxState.equalsIgnoreCase("unchecked")){
			System.out.println("None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noCondtionCheckbox).click();
		}
		else{
			System.out.println("click None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noCondtionCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(noCondtionCheckbox).click();
		}
	}
	/**
	 * This method is used to verify when user click on no condition checkbox then it should be documented in profile screen
	 * @return true if correct condition text is present in profile
	 */
	public boolean verifyCondtionTextInProfile(){
		boolean isNocondtionAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String allergyText=SeleniumUtil.getElementWithFluentWait(condtionMsgInProfile).getText();
		if(allergyText.equalsIgnoreCase(noCondtionTextForProfile)){
			System.out.println( "correct condtion text is doucmented ");
			isNocondtionAdded=true;
		}
		return isNocondtionAdded;

	}
	/**
	 * This method is used for adding conditions data  in profile screen 
	 *
	 */
	public void addCondtionsInProfileScreen(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(reviewDiagnosisSearchBox).click();
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(patientProfilerDiagnosisvalue);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.searchbutton).click();
		sleep(5000);
		List<WebElement> searchdiagnosisrow = driver.findElements(totaltrtagsForProfile);
		System.out.println("NUMBER OF DIAGNOSIS IN THIS TABLE = "+searchdiagnosisrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : searchdiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.equalsIgnoreCase(patientProfilerDiagnosisvalue)){
					System.out.println("corrected patient row is displayed");
					SeleniumUtil.doubleClick(tdElement);
					break;
				}

			} 
			break;

		}					
	}
	/**
	 * This method is used for searching condition using search box present in user list
	 */
	public void searchCondtionUsingSearchBox(){
		switchToUserListFrame();
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(addNewCondtionForPatient);
		SeleniumUtil.getElementWithFluentWait(searchIcon).click();
		sleep(9000);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement allergyvalue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ addNewCondtionForPatient +"')]"));
		action.moveToElement(allergyvalue).doubleClick().build().perform();
	}
	/**
	 * This method is used for selecting condition value from user list present in first visit service
	 */
	public void selectCondtionValueFromUserListInService(){
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(condtionValueFromFirstVisit);
		SeleniumUtil.getElementWithFluentWait(searchIcon).click();
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement plussign =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ condtionValueFromFirstVisit +"')]"));
		action.moveToElement(plussign).doubleClick().build().perform();
	}
	/**
	 * This method is used for verify all the conditions value documented  either from user list,new Button or service user list then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyAllCondtionsDocumentedInProfile(){
		//clickOnProfileInPatient();
		boolean isconditionAdded1=false;
		boolean isconditionAdded2=false;
		boolean isconditionAdded3=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		List<WebElement> addeddiagnosisrow = driver.findElements(By.className("phe"));
		System.out.println("NUMBER OF DIAGNOSIS IN THIS TABLE = "+addeddiagnosisrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : addeddiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("span"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(addNewCondtionForPatient)){
					System.out.println("corrected condition are added in profile"); 
					isconditionAdded1=true;
					break;
				}
			} 
			row_num++;
		}
		for(WebElement trElement : addeddiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("span"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(condtionValueFromFirstVisit)){
					System.out.println("corrected procedure are added in profile"); 
					isconditionAdded2=true;
					break;
				}
			} 
			row_num++;
		}
		for(WebElement trElement : addeddiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("span"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(patientProfilerDiagnosisvalue)){
					System.out.println("corrected procedure are added in profile"); 
					isconditionAdded3=true;
				}
			} 
			row_num++;
		}
		if(isconditionAdded1 && isconditionAdded2 && isconditionAdded3)
			return true;
		else
			return false;
	}


	/**
	 * This method is used to click on Immunization section present in profile screen
	 */
	public void clickOnImmunizationSectionInProfile(){
		switchToProfileFrame();
		WebElement immunization =SeleniumUtil.getElementWithFluentWait(immunizationInProfile);
		SeleniumUtil.scrolltoWebElement(immunization);
		immunization.click();
	}
	/**This method will verify if existing immunization  are added in profile module ,then void the existing immunization details
	 * 
	 * @throws FindFailed
	 */
	public void verifyAddedImmunizationDetails() throws FindFailed{
		sleep(5000);
		switchToDiagnosisListFrame();
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains(addNewImmunizationForPatient) || rowText.contains(searchExistingImmunization) || rowText.contains(immunizationValueFromFirstVisit)){
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
	 * This method is used for verify No Immunization checkbox is checked or unchecked in profile screen 
	 *
	 */
	public void selectNoImmunizationsCheckbox(){
		switchToDiagnosisListFrame();

		String noAllergyCheckBoxState =SeleniumUtil.getElementWithFluentWait(noImmunizationsCheckboxState).getAttribute("stateName");
		if(noAllergyCheckBoxState.equalsIgnoreCase("unchecked")){
			System.out.println("None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noImmunizationsCheckbox).click();
		}
		else{
			System.out.println("click None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noImmunizationsCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(noImmunizationsCheckbox).click();
		}

	}
	/**
	 * This method is used to verify immunization text in profile screen
	 * @return true if correct text is present
	 */
	public boolean verifyImmunizationTextInProfile(){
		boolean isNoImmunizationAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String immunizationText=SeleniumUtil.getElementWithFluentWait(immunizationsMsgInProfile).getText();
		if(immunizationText.equalsIgnoreCase(noImmunizationTextForProfile)){
			System.out.println( "correct immunization text is doucmented ");
			isNoImmunizationAdded=true;
		}
		return isNoImmunizationAdded;

	}
	/**
	 * This method is used for adding Immunizations  in profile screen 
	 *
	 */
	public void addImmunizationsInProfileScreen(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		addCVXCodeForImmunization();				
	}
	/**
	 * This method is used for searching immunization value from uses list 
	 */
	public void searchImmunizationUsingSearchBox(){
		switchToUserListFrame();
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(searchExistingImmunization);
		SeleniumUtil.getElementWithFluentWait(searchIcon).click();
		sleep(9000);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement allergyvalue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ searchExistingImmunization +"')]"));
		action.moveToElement(allergyvalue).doubleClick().build().perform();
	}
	/**
	 * This method is used for selecting immunization value from used list present in first visit service
	 */
	public void selectImmunizationValueFromUserListInService(){
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");

		WebElement immunizationValue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ immunizationValueFromFirstVisit +"')]"));
		if(immunizationValue.isDisplayed()){
			System.out.println("right panel is opened");
			action.moveToElement(immunizationValue).doubleClick().build().perform();
		}
		else{
			System.out.println("right panel is not opened");
		}
	}
	/**
	 * This method is used for verify all the Immunization value documented  either from user list,new Button or service user list then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyAllImmunizationDocumentedInProfile(){
		//clickOnProfileInPatient();
		boolean isimmunizationAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String immunizationData=SeleniumUtil.getElementWithFluentWait(immunizationInProfile).getText();
		System.out.println("immunization data text is"+immunizationData);
		String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
		String immunizationTextValue=addNewImmunizationForPatient+", "+currentDate+'\n'+immunizationValueFromFirstVisit+'\n'+searchExistingImmunization;
		if(immunizationData.equalsIgnoreCase(immunizationTextValue)){
			System.out.println("correct immunization data is added");
			isimmunizationAdded=true;
		}
		return isimmunizationAdded;				
	}
	public boolean verifyImmunizationInRPanel(String immunizationName){
		switchToDiagnosisListFrame();
		boolean isMedicationDocumented=false;
		List<WebElement> medicationrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF MEDICATION  DATA IN THIS TABLE = "+medicationrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : medicationrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(immunizationName)){
					System.out.println("corrected medication values present"); 
					isMedicationDocumented=true;
					break;
				}

			} 
			if(isMedicationDocumented){
				break;
			}
			else{
				row_num++;
			}
		}
		return isMedicationDocumented;
	}

	public void addCVXCodeForImmunization(){
		boolean isImmunizationPresent=false;
		WebElement dropDown = SeleniumUtil.getElementWithFluentWait(cvxDropDown);
		action.moveToElement(dropDown).click().build().perform();
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
		List<WebElement> searchimmunizationrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF IMMUNIZATION IN THIS TABLE = "+searchimmunizationrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : searchimmunizationrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.equalsIgnoreCase(addNewImmunizationForPatient) || rowText.equalsIgnoreCase(addNewImmunizationForPatient)){
					System.out.println("corrected immunization row is displayed");
					SeleniumUtil.doubleClick(tdElement);
					isImmunizationPresent=true;
					break;
				}

			} 
			if(isImmunizationPresent){
				break;
			}
			else{
				row_num++;
			}

		}					
	}
	/**
	 * This method is used for click On Dx section present in profile section
	 * 
	 */
	public void clickOnDxStudiesSectionInProfile(){
		switchToProfileFrame();
		WebElement dxstudies = SeleniumUtil.getElementWithFluentWait(diagonticData);
		SeleniumUtil.scrolltoWebElement(dxstudies);
		dxstudies.click();
	}
	/**This method will verify if existing dx details  are added in profile module ,then delete the existing dx details
	 * 
	 * @throws FindFailed
	 */
	public void verifyAddedDxDetails() throws FindFailed{
		sleep(5000);
		switchToDiagnosisListFrame();
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains(codingDescription) ||  rowText.contains(dxStudiesValueFromFirstVisit) || rowText.contains(searchdxStudiesProcedureName)){
				System.out.println("immunization already  added in demographics");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("selectDelete");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
			}
			else{
				System.out.println("No existing dx details  are present");
			}

		}
	}
	/**
	 * This method is used for verify No dignostic checkbox is checked or unchecked in profile screen 
	 *
	 */
	public void selectNoDiagnosticStudiesCheckbox(){
		switchToDiagnosisListFrame();

		String noAllergyCheckBoxState =SeleniumUtil.getElementWithFluentWait(noDiagnosticStudiesCheckboxState).getAttribute("stateName");
		if(noAllergyCheckBoxState.equalsIgnoreCase("unchecked")){
			System.out.println("None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noDiagnosticStudiesCheckbox).click();
		}
		else{
			System.out.println("click None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noDiagnosticStudiesCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(noDiagnosticStudiesCheckbox).click();
		}
	}
	/**
	 * This method is used to verify dx studies text should be documented when user clicks on no diagnostic checkbox
	 * @return true if correct text is added
	 */
	public boolean verifyDXStudiesTextInProfile(){
		boolean isNoDXAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String DXText=SeleniumUtil.getElementWithFluentWait(diagnosticStudyMsgInProfile).getText();
		if(DXText.equalsIgnoreCase(noDXStudiesTextForProfile)){
			System.out.println( "correct dx studies text is doucmented ");
			isNoDXAdded=true;
		}
		return isNoDXAdded;

	}
	/**
	 * This method is used for adding dx studies data  in profile screen 
	 *
	 */
	public void addDxStudiesInProfileScreen(){
		boolean isDXStudiesDocumented=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(cptCodeSearchBox).click();
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.getElementWithFluentWait(searchCodeTextBox).sendKeys(cptCode);
		SeleniumUtil.getElementWithFluentWait(codingSearch).click();
		sleep(5000);
		List<WebElement> searchdiagnosisrow = driver.findElements(codingTableData);
		System.out.println("NUMBER OF CODING DATA IN THIS TABLE = "+searchdiagnosisrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : searchdiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.equalsIgnoreCase(codingDescription)){
					System.out.println("corrected coding row is displayed");
					SeleniumUtil.doubleClick(tdElement);
					isDXStudiesDocumented=true;
					break;
				}

			} 
			if(isDXStudiesDocumented){
				break;
			}
			else{
				row_num++;
			}

		}					
	}
	/**
	 * This method is used to verify Dx added in r panel when user add new dx value from user list
	 * @return true if dx value added in R Panel
	 */
	public boolean verifyDxAddedInRPanel(){
		switchToDiagnosisListFrame();
		boolean isDxDocumented=false;
		List<WebElement> medicationrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF DX STUDIES  DATA IN THIS TABLE = "+medicationrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : medicationrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(codingDescription)){
					System.out.println("corrected  values present"); 
					isDxDocumented=true;
					break;
				}

			} 
			if(isDxDocumented){
				break;
			}
			else{
				row_num++;
			}
		}
		return isDxDocumented;
	}
	/**
	 * This method is used for searching DX value from search box present in user list
	 * 
	 */
	public void searchDxUsingSearchBox(){
		switchToUserListFrame();
		List<WebElement> totaluserListItems11 =driver.findElements(By.xpath(".//div[@id='treGeneral']/ul/li"));
		System.out.println(totaluserListItems11.size());
		for(WebElement ele1:totaluserListItems11){
			String text1 =ele1.getText();
			System.out.println("text is"+text1);
			if((!(text1.equalsIgnoreCase("Favorites")))){
				List<WebElement> td_collection=driver.findElements(By.xpath(".//span[@class='rtPlus']"));
				System.out.println("NUMBER OF COLUMNS="+td_collection.size());
				for(WebElement ele11:td_collection){
					String text11= ele11.getText();
					System.out.println("text is111"+text11);
					ele11.click();

				}
			}

		}
		WebElement e11= driver.findElement(By.xpath(".//*[contains(text(),'"+ searchdxStudiesProcedureName +"')]"));
		action.moveToElement(e11).doubleClick().build().perform();

	}
	/**
	 * This method is used for selecting dx value from first visit service
	 */
	public void selectDxStudiesValueFromUserListInService(){
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");
		SeleniumUtil.getElementWithFluentWait(patientnametextbox).sendKeys(dxStudiesValueFromFirstVisit);
		SeleniumUtil.getElementWithFluentWait(searchIcon).click();
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement plussign =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ dxStudiesValueFromFirstVisit +"')]"));
		action.moveToElement(plussign).doubleClick().build().perform();
	}
	/**
	 * This method is used for verify all the diagnoses value documented  either from user list,new Button or service user list then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyAllDXStudiesDocumentedInProfile(){
		//clickOnProfileInPatient();
		boolean isDxDataAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String dxData=SeleniumUtil.getElementWithFluentWait(diagonticData).getText();
		System.out.println("DX data text is"+dxData);
		String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
		String dxTextValue=codingDescription+'\n'+dxStudiesProcedureName+", "+currentDate+'\n'+searchdxStudiesProcedureName+'\n'+dxStudiesValueFromFirstVisit;
		if(dxData.equalsIgnoreCase(dxTextValue)){
			System.out.println("correct immunization data is added");
			isDxDataAdded=true;
		}
		return isDxDataAdded;			
	}

	/**
	 * This method is used for clicking on Procedure Section present in profile of  patient chart
	 * 
	 */
	public void clickOnProcedureSectionInProfile(){
		switchToProfileFrame();
		WebElement procedures = SeleniumUtil.getElementWithFluentWait(procedureDataText);
		SeleniumUtil.scrolltoWebElement(procedures);
		procedures.click();

	}
	/**This method will verify if existing Procedure  are added in profile module ,then delete the existing procedure details
	 * 
	 * @throws FindFailed
	 */
	public void verifyAddedProcedureDetails() throws FindFailed{
		sleep(5000);
		switchToDiagnosisListFrame();
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains(codingDescription) ||  rowText.contains(procedureValueFromFirstVisit) ||  rowText.contains(searchExistingProcedure)){
				System.out.println("immunization already  added in demographics");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("selectDelete");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
			}
			else{
				System.out.println("No existing immunization  are present");
			}

		}
	}
	/**
	 * This method is used for searching Procedure using Procedure search box present in R-Panel
	 */
	public void searchProcedureUsingSearchBox(){
		switchToUserListFrame();
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(searchExistingProcedure);
		SeleniumUtil.getElementWithFluentWait(searchIcon).click();
		sleep(9000);
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		WebElement allergyvalue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ searchExistingProcedure +"')]"));
		action.moveToElement(allergyvalue).doubleClick().build().perform();
	}
	/**
	 * This method is used for verify None procedure checkbox is checked or unchecked in profile screen 
	 *
	 */
	public void selectNoProcedureCheckbox(){
		switchToDiagnosisListFrame();

		String noAllergyCheckBoxState =SeleniumUtil.getElementWithFluentWait(noProcedureCheckboxState).getAttribute("stateName");
		if(noAllergyCheckBoxState.equalsIgnoreCase("unchecked")){
			System.out.println("None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noProcedureCheckbox).click();
		}
		else{
			System.out.println("click None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(noProcedureCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(noProcedureCheckbox).click();
		}
	}
	/**
	 * This method is used to verify Procedure Data  is document for profile screen
	 */
	public boolean verifyProcedureTextInProfile(){
		boolean isprocedureAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String DXText=SeleniumUtil.getElementWithFluentWait(procedureMsgInProfile).getText();
		if(DXText.equalsIgnoreCase("No Prior Procedures")){
			System.out.println( "correct dx studies text is doucmented ");
			isprocedureAdded=true;
		}
		return isprocedureAdded;

	}
	/**
	 * This method is used for adding Procedure value from user list present in first visit service
	 */
	public void selectProcedureValueFromUserListInService(){
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		sleep(5000);
		SeleniumUtil.switchToFrame(driver, "fraUserList_Frame");

		WebElement immunizationValue =SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ procedureValueFromFirstVisit +"')]"));
		if(immunizationValue.isDisplayed()){
			System.out.println("right panel is opened");
			action.moveToElement(immunizationValue).doubleClick().build().perform();
		}
		else{
			System.out.println("right panel is not opened");
		}
	}
	/**
	 * This method is used for verify all the Procedure value documented  either from user list,new Button or service user list then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyAllProcedureDocumentedInProfile(){

		boolean isprocedureAdded1=false;
		boolean isprocedureAdded2=false;
		boolean isprocedureAdded3=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		List<WebElement> addeddiagnosisrow = driver.findElements(By.className("phe"));
		System.out.println("NUMBER OF DIAGNOSIS IN THIS TABLE = "+addeddiagnosisrow.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : addeddiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("span"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(codingDescription)){
					System.out.println("corrected procedure are added in profile"); 
					isprocedureAdded1=true;
					break;
				}
			} 
			row_num++;
		}
		for(WebElement trElement : addeddiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("span"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(searchExistingProcedure)){
					System.out.println("corrected procedure are added in profile"); 
					isprocedureAdded2=true;
					break;
				}
			} 
			row_num++;
		}
		for(WebElement trElement : addeddiagnosisrow)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("span"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(procedureValueFromFirstVisit)){
					System.out.println("corrected procedure are added in profile"); 
					isprocedureAdded3=true;
				}
			} 
			row_num++;
		}
		if(isprocedureAdded1 && isprocedureAdded2 && isprocedureAdded3)
			return true;
		else
			return false;
	}


	/**
	 * This method is used for click On Family section present in profile screen
	 * 
	 */
	public void clickOnFamilySectionInProfile(){
		switchToProfileFrame();
		WebElement procedures = SeleniumUtil.getElementWithFluentWait(familySectionInProfile);
		SeleniumUtil.scrolltoWebElement(procedures);
		procedures.click();

	}
	/**This method will verify if existing family details  are added in profile module ,then delete the existing family details
	 * 
	 * @throws FindFailed
	 */
	public void verifyAddedFamilyDetails() throws FindFailed{
		sleep(5000);
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains("Mother") ){
				System.out.println("family details already  added in demographics");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deleteUser");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
				break;
			}
			else{
				System.out.println("No existing family details  are present");
			}
		}
	}
	/**
	 * This method is used to select any checkbox value from family history
	 * @param indexvalue
	 */
	public void selectCheckbox(){
		sleep(5000);
		List<WebElement> els1 = driver.findElements(By.xpath(".//input[@type='checkbox']"));
		System.out.println(els1.size());
		for(WebElement e1:els1){
			System.out.println(!(e1.isSelected()));
			if(!(e1.isSelected())){
				action.sendKeys(e1,Keys.SPACE).click().perform();							
				break;
			}

			else{
				System.out.println("already selected");
				
				break;
			}

		}
	}
	/**
	 * This method is used for adding family memeber details
	 */
	public void addFamilyDetails(){

		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.addTextBoxValue("ddlRelationship_Text","Mother (72705000)");
		SeleniumUtil.addTextBoxValue("ddlHealthStatus_Text","Healthy");
	}
	/**
	 * This method is used for switching into family history frame
	 */
	public void switchToFamilyHistoryFrame(){
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
	}
	/**
	 * This method is used to verify all the family history data in profile screen
	 * @return true if correct data is present in profile
	 */
	public boolean verifyAllFamilyHistoryInProfile(){
		boolean isFamilyHistoryAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String text =SeleniumUtil.getElementWithFluentWait(familySectionInProfile).getText();
		System.out.println(text);
		System.out.println("excel data is:"+familytextinProfile);
		if(text.equalsIgnoreCase(familytextinProfile)){
			System.out.println("corrected data is displayed");
			isFamilyHistoryAdded=true;
		}
		return isFamilyHistoryAdded;

	}
	/**
	 * This method is used for adding general data in profile screen
	 */
	public void enterGeneralData(){
		SeleniumUtil.addTextBoxValue("ddlMaritalStatus_Text", "Married");
		SeleniumUtil.getElementWithFluentWait(marriageLength).clear();
		SeleniumUtil.getElementWithFluentWait(marriageLength).sendKeys("5");
		SeleniumUtil.getElementWithFluentWait(spouseOccupation).clear();
		SeleniumUtil.getElementWithFluentWait(spouseOccupation).sendKeys("engineer");
		SeleniumUtil.getElementWithFluentWait(noOfChildren).clear();
		SeleniumUtil.getElementWithFluentWait(noOfChildren).sendKeys("1");
		SeleniumUtil.getElementWithFluentWait(religionTextBox).clear();
		SeleniumUtil.getElementWithFluentWait(religionTextBox).sendKeys("hindu");
		SeleniumUtil.getElementWithFluentWait(hobbiesTextBox).clear();
		SeleniumUtil.getElementWithFluentWait(hobbiesTextBox).sendKeys("Music");
		SeleniumUtil.getElementWithFluentWait(occHistoryTextBox).clear();
		SeleniumUtil.getElementWithFluentWait(occHistoryTextBox).sendKeys("doctor");
		SeleniumUtil.getElementWithFluentWait(notesTextBox).clear();
		SeleniumUtil.getElementWithFluentWait(notesTextBox).sendKeys("this is test note");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();
	}
	/**
	 * This method is used to verify general data for profile screen
	 * @return
	 */
	public boolean verifyGeneralDataInProfile(){
		boolean isGeneralDataAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String text =SeleniumUtil.getElementWithFluentWait(generalData).getText();
		System.out.println(text);
		if(text.equalsIgnoreCase(generalTextForProfile)){
			System.out.println("corrected data is displayed");
			isGeneralDataAdded=true;
		}
		return isGeneralDataAdded;

	}
	/**
	 * This method is used for click On general section present in profile screen
	 * 
	 */
	public void clickOnGeneralSectionInProfile(){
		switchToProfileFrame();
		WebElement procedures = SeleniumUtil.getElementWithFluentWait(generalSection);
		SeleniumUtil.scrolltoWebElement(procedures);
		procedures.click();
	}
	/**
	 * This method is used to switch into social history frame
	 */
	public void switchToSocialHistoryFrame(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
	}
	/**
	 * This method is used for click On Alcohol section present in profile screen
	 * 
	 */
	public void clickOnAlcoholSectionInProfile(){
		switchToProfileFrame();
		WebElement procedures = SeleniumUtil.getElementWithFluentWait(alcoholSectionInProfile);
		SeleniumUtil.scrolltoWebElement(procedures);
		procedures.click();
	}
	/**
	 * This method is used to verify social history page is opened when user clicks on social history sections present in profile
	 * @return true if page is opened with social history title
	 */
	public boolean verifySocialHistoryScreen(){
		boolean isSocialPageOpened=false;
		sleep(3000);
		switchToSocialHistoryFrame();
		String socialHistoryText=SeleniumUtil.getElementWithFluentWait(socialHistoryPage).getText();
		if(socialHistoryText.equalsIgnoreCase("Social History")){
			logger.info("Social History Right menu is opened..");
			isSocialPageOpened=true;
		}
		return isSocialPageOpened;
	}
	/**
	 * This method is used if existing alchol data is present then delete it
	 * @throws FindFailed
	 */
	public void deleteAlcholExistingDetails() throws FindFailed{
		sleep(5000);
		switchToSocialHistoryFrame();
		List<WebElement> totaltrrows = driver.findElements(totalAlcoholForProfile);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains("Beer") || rowText.contains("Wine") ){
				System.out.println("data already  added in social history");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deleteUser");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
				
			}
			else{
				System.out.println("No existing Alchol  are present");
			}

		}
	}
	/**
	 * This method is used for clicking on alchol plus button
	 */
	public void clickOnAlcholPlusButton(){
		SeleniumUtil.getElementWithFluentWait(alcoholPlusButton).click();
	}
	/**
	 * This method is used to verify tool tip window is opened
	 * @param tooltipTitle
	 * @return true if page is opened with tooltipTitle  title passed as parameter
	 */
	public boolean verifyToolTipWindow(String tooltipTitle){
		boolean isWindowOpened=false;
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
		String textValue=SeleniumUtil.getElementWithFluentWait(MedicalChartPage.patientFormTitle).getText();
		if(textValue.equalsIgnoreCase(tooltipTitle)){
			logger.info("Tool tip window is opened");
			isWindowOpened=true;
		}
		return isWindowOpened;
	}
	/**
	 * This method is used for adding alcohol data for profile screen
	 * @param value is any alchol type value
	 */
	public void enterAlcoholData(String value){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(PatientInterviewForm.pifTextBox).sendKeys(value);
		SeleniumUtil.getElementWithFluentWait(PatientInterviewForm.quantityTextBox).sendKeys(alcholQuantityForProfile);
		SeleniumUtil.getElementWithFluentWait(PatientInterviewForm.freqTextBox).sendKeys(alcholFrequencyForProfile);
        SeleniumUtil.addTextBoxValue("ddlFrequency_Text", "Times / week");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();
	}
	/**
	 * This method is used to switch into social history service frame
	 */
	public void switchToSocialHistoryInServiceFrame(){
		 DynamicFramePage.verifyDynamicFrameForInterview();
			SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
			SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
	}
	/**
	 * This method is used to verify alcohol data is document for profile screen
	 * @return true if correct data is documented
	 */
	public boolean verifyAllAlcholHistoryDataInProfile(){
		boolean isAlcoholDataAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String text=SeleniumUtil.getElementWithFluentWait(DocumentPage.alcoholSection).getText();
		System.out.println(text);
		if(text.contains(alcholTextForProfile)){
			System.out.println("corrected alchol are added in profile"); 
			isAlcoholDataAdded=true;
		}
		return isAlcoholDataAdded;
	}
	/**
	 * This method is used for click On Tabacco section present in profile
	 * 
	 */
	public void clickOnTabaccoSectionInProfile(){
		switchToProfileFrame();
		WebElement tabaccosection = SeleniumUtil.getElementWithFluentWait(tabaccoSectionInProfile);
		SeleniumUtil.scrolltoWebElement(tabaccosection);
		tabaccosection.click();	
	}
	/**
	 * This method is used if existing tobacco details are added,then delete the existing tobacco details
	 * @throws FindFailed
	 */
	public void deleteTabaccoExistingDetails() throws FindFailed{
		sleep(5000);
		switchToSocialHistoryFrame();
		List<WebElement> totaltrrows = driver.findElements(totalTabaccoForProfile);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains("Cigar") || rowText.contains("Pipe") ){
				System.out.println("data already  added in social history");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deleteUser");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
				sleep(5000);
			}
			else{
				System.out.println("No existing tobacco  are present");
			}
		}
	}
	/**
	 * This method is used for clicking on tabacco plus button
	 */
	public void clickOnTabaccoPlusButton(){
		SeleniumUtil.getElementWithFluentWait(tabaccoPlusButton).click();
	}
	/**
	 * This method is used for adding tabacco details for profile screen
	 * @param value is any tabacco type
	 */
	public void enterTabaccoData(String value){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.addTextBoxValue("ddlType_Text", value);
		SeleniumUtil.getElementWithFluentWait(tabaccoQuantityTextBox).clear();
		SeleniumUtil.getElementWithFluentWait(tabaccoQuantityTextBox).sendKeys("3");
		SeleniumUtil.addTextBoxValue("ddlFrequency_Text", "Times / week");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();	
	}
	/**
	 * This method is used to verify corrected tabacco details are added in profile screen
	 * @return true if correct data is added
	 */
	public boolean verifyTabaccoHistoryDataInProfile(){
		boolean istabaccoDataAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String text=SeleniumUtil.getElementWithFluentWait(tabaccoDataForProfile).getText();
		System.out.println(text);
		if(text.contains(tabacooTextForProfile) /*&&  rowText.contains(alcholQuantityForProfile) &&  rowText.contains(alcholFrequencyForProfile) && rowText.contains(alcholNoOfTimesForProfile)*/){
			System.out.println("corrected tabacco detail are added in profile"); 
			istabaccoDataAdded=true;
		}


		return istabaccoDataAdded;
	}
	/**
	 * This method is used for click On Drug section present in profile
	 * 
	 */
	public void clickOnDrugSectionInProfile(){
		switchToProfileFrame();
		WebElement drugsection = SeleniumUtil.getElementWithFluentWait(drugSectionInProfile);
		SeleniumUtil.scrolltoWebElement(drugsection);
		drugsection.click();
	}
	/**
	 * This method is used for clicking on drug plus button
	 */
	public void clickOnDrugPlusButton(){
		SeleniumUtil.getElementWithFluentWait(drugPlusButtonInProfile).click();
	}
	/**
	 * This method is used for entering drug data
	 * @param value
	 */
	public void enterDrugData(String value){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(value); 
		SeleniumUtil.getElementWithFluentWait(quantityTextBoxInProfile).sendKeys("3");
		SeleniumUtil.getElementWithFluentWait(frequencyTextBoxInProfile).sendKeys("2");
		SeleniumUtil.addTextBoxValue("ddlFrequency_Text", "Times / week");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();
		
	}
	/**
	 * This method is used to verify corrected drug details are added in profile screen
	 * @return true if correct data is added
	 */
	public boolean verifyDrugDataInProfile(){
		boolean isDrugDataAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String text=SeleniumUtil.getElementWithFluentWait(DocumentPage.drugSection).getText();
		System.out.println(text);
		if(text.contains(drugTextForProfile) ){
			System.out.println("corrected Drug detail are added in profile"); 
			isDrugDataAdded=true;
		}


		return isDrugDataAdded;
	}
	/**
	 * This method is used for delete existing drug data
	 * @throws FindFailed
	 */
	public void deleteDrugExistingDetails() throws FindFailed{
		sleep(5000);
		switchToSocialHistoryFrame();
		List<WebElement> totaltrrows = driver.findElements(totalDrugForProfile);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains("Steroids") || rowText.contains("Stimulants") ){
				System.out.println("data already  added in social history");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deleteUser");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
				sleep(6000);
			}
			else{
				System.out.println("No existing Drug  are present");
			}

		}
	}
	/**
	 * This method is used for click On Exercise section present in profile
	 * 
	 */
	public void clickOnExerciseSectionInProfile(){
		switchToProfileFrame();
		WebElement exercisesection = SeleniumUtil.getElementWithFluentWait(exerciseSectionInProfile);
		SeleniumUtil.scrolltoWebElement(exercisesection);
		exercisesection.click();
	}
	/**
	 * This method is used for delete existing Exercise data
	 * @throws FindFailed
	 */
	public void deleteExerciseExistingDetails() throws FindFailed{
		sleep(5000);
		switchToSocialHistoryFrame();
		List<WebElement> totaltrrows = driver.findElements(totalExerciseForProfile);
		for(WebElement irows:totaltrrows){
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains("test Exercise") || rowText.contains("test Exercise From First Visit") ){
				System.out.println("data already  added in social history");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deleteUser");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
				sleep(6000);
			}
			else{
				System.out.println("No existing exercise  are present");
			}

		}
	}
	/**
	 * This method is used for clicking on Exercise plus button
	 */
	public void clickOnExercisePlusButton(){
		SeleniumUtil.getElementWithFluentWait(exercisePlusButtonInProfile).click();
	}
	/**
	 * This method is used for entering exercise data
	 * @param value
	 */
	public void enterExerciseData(String value){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(exerciseTypeTextBoxInProfile).sendKeys(value); 
		SeleniumUtil.getElementWithFluentWait(quantityTextBoxInProfile).sendKeys("3");
		SeleniumUtil.getElementWithFluentWait(frequencyTextBoxInProfile).sendKeys("2");
		SeleniumUtil.addTextBoxValue("ddlFrequency_Text", "Times / week");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();	
	}
	/**
	 * This method is used to verify corrected exercise details are added in profile screen
	 * @return true if correct data is added
	 */
	public boolean verifyExerciseDataInProfile(){
		boolean isExerciseDataAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		sleep(5000);
		String text=SeleniumUtil.getElementWithFluentWait(DocumentPage.exerciseSection).getText();
		System.out.println(text);
		if(text.contains(exerciseTextForProfile) ){
			System.out.println("corrected exercise detail are added in profile"); 
			isExerciseDataAdded=true;
		}
		return isExerciseDataAdded;
	}
	/**
	 * This method is used for click On Caffeine section present in profile
	 * 
	 */
	public void clickOnCaffeineSectionInProfile(){
		switchToProfileFrame();
		WebElement caffeinesection = SeleniumUtil.getElementWithFluentWait(caffeineSectionInProfile);
		SeleniumUtil.scrolltoWebElement(caffeinesection);
		caffeinesection.click();
	}
	/**
	 * This method is used for delete existing Caffeine data
	 * @throws FindFailed
	 */
	public void deleteCaffeineExistingDetails() throws FindFailed{
		sleep(5000);
		switchToSocialHistoryFrame();
		List<WebElement> totaltrrows = driver.findElements(totalCaffeineForProfile);
		for(WebElement irows:totaltrrows){
			String rowText =irows.getText();
			System.out.println("row text is"+rowText);
			if(rowText.contains("caffeine test") || rowText.contains("caffeine") ){
				System.out.println("data already  added in social history");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deleteUser");
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");	
				sleep(6000);
			}
			else{
				System.out.println("No existing caffeine  are present");
			}

		}
	}
	/**
	 * This method is used for clicking on Caffeine plus button
	 */
	public void clickOnCaffeinePlusButton(){
		SeleniumUtil.getElementWithFluentWait(caffeinePlusButtonInProfile).click();
	}
	/**
	 * This method is used for entering Caffeine data
	 * @param value
	 */
	public void enterCaffeineData(String value){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(caffeineTextBoxInProfile).sendKeys(value); 
		String currentDate=DateUtil.getCurrentDateInDateFormatted("MM/d/yyyy");
		SeleniumUtil.getElementWithFluentWait(caffeineStoppedDate).sendKeys(currentDate);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();	
	}
	/**
	 * This method is used to verify corrected Caffeine details are added in profile screen
	 * @return true if correct data is added
	 */
	public boolean verifyCaffeineDataInProfile(){
		boolean iscaffeineDataAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String text=SeleniumUtil.getElementWithFluentWait(DocumentPage.caffeineSection).getText();
		System.out.println(text);
		String currentDate=DateUtil.getCurrentDateInDateFormatted("MM/d/yyyy");
		String caffeineValue =caffeineTextForProfile+" "+currentDate+"."+" "+caffeineTextForService+" "+currentDate+".";
		System.out.println("caffeine data value is"+caffeineValue);
		if(text.contains(caffeineValue) ){
			System.out.println("corrected caffeine detail are added in profile"); 
			iscaffeineDataAdded=true;
		}
		return iscaffeineDataAdded;
	}
	/**
	 * This method is used for click On Sexual section present in profile
	 * 
	 */
	public void clickOnSexualSectionInProfile(){
		switchToProfileFrame();
		WebElement sexualsection = SeleniumUtil.getElementWithFluentWait(sexualSectionInProfile);
		SeleniumUtil.scrolltoWebElement(sexualsection);
		sexualsection.click();
	}
	/**
	 * This method is used for clicking on sexual plus button
	 */
	public void clickOnSexualPlusButton(){
		SeleniumUtil.getElementWithFluentWait(sexualPlusButtonInProfile).click();
		
	}
	/**
	 * This method is used for entering Caffeine data
	 * @param value
	 */
	public void enterSexualData(String value){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(sexualTextBoxInProfile).clear();
		SeleniumUtil.getElementWithFluentWait(sexualTextBoxInProfile).sendKeys(value); 
		SeleniumUtil.addTextBoxValue("ddlHIV_Text", "Negative");
		SeleniumUtil.getElementWithFluentWait(sexualNotesInProfile).sendKeys("this is sexual note");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();
		
	}
	public void saveSocialHistoryDetails(){
		switchToSocialHistoryFrame();
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();
	}
	/**
	 * This method is used to verify corrected Sexual details are added in profile screen
	 * @return true if correct data is added
	 */
	public boolean verifySexualDataInProfile(){
		boolean isSexualDataAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String text=SeleniumUtil.getElementWithFluentWait(sexualSectionInProfile).getText();
		System.out.println(text);
		if(text.contains("test") ){
			System.out.println("corrected sexual detail are added in profile"); 
			isSexualDataAdded=true;
		}
		return isSexualDataAdded;
	}
	public void savePopupDetails(){
		try{
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		}
		catch(Exception e){
			logger.info("no popup present..");
		}
	}
}