package com.gmed.pages;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;
import java.util.List;

import javax.swing.RepaintManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;

import com.gmed.AutoIT.GpinUserLogin;
import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.test.PrescriptionTest;
import com.gmed.test.ProfileTest;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.DateUtil;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;
import com.gmed.utils.StringUtility;

import static com.gmed.helper.DriverFactory.action;
public class PrescriptionPage extends BaseAbstractPage {
	/** Logger to log the PrescriptionPage log messages */
	private static Logger logger                = LogManager.getLogger(PrescriptionPage.class);
	public static By medicationPopupPatient             = By.id("pnlMedications_Image");
	public static By newbutton                          = By.id("btnNew_SpanBGColor");
	public static By prescribeCheckbox                  = By.id("rdoTypePrescribe");
	public static By medicationTextBox                  = By.id("txtMedication_TextBox");
	public static By medicationSearchButton             = By.id("txtMedicationSearch");
	public static By sigCheckbox                        = By.id("rdoSigStructured_Radio");
	public static By sigAddButton                       = By.id("txtSIGAdd");
	public static By medicationQuantity                 = By.id("txtQty_DecimalTextBox");
	public static By refilltextBox                       =By.id("txtRefills_IntegerTextBox");
	public static By toolbarbutton                      = By.className("ToolbarDropDownImage");
	public static By prescribeInProfile                  =By.className("PrescriptionFullName");
	public static By medicationStrengthTextBox          = By.id("ddlDose_Text");
	public static By recordExistingButton                =By.id("btnRecordExisting_SpanBGColor");
	public static By prescribeExistingCheckbox          = By.id("rdoTypeRecordExisting");
	public static By medicationList                     = By.xpath(".//table[@id='tblList_TableRoot']/tbody/tr");
	public static By medicationDoseDetail               = By.id("txtSIG_TextBox");
	public static By pharmacyTextbox                    = By.id("ddlPharmacy1_Text");
	public static By providerTextbox                    = By.id("ddlPrescribedBy_Text");
	public static By overrideButton                     = By.id("btnPrescribe_Center");
	public static By reviewMedicationText               = By.id("lblLastMedicationReconciliation");
	public static By currentMedicationCheckboxState     = By.id("chkNoCurrentMedications");
	public static By nocurrentMedicationCheckbox        = By.id("chkNoCurrentMedications_Render");
	public static By noMedicationText                   = By.id("tblListMedications_Message");
	public static By alphabetList                       = By.xpath(".//control[@type='AlphaSelector']");
	public static By medicationNameTextBox              = By.id("txtSearch_TextBox");
	public static By medicationDescription              = By.id("txtDescription_TextBox");
	public static By procedures                         = By.xpath(".//span[@type='ServiceType']"); 
	public static By procedures1                        = By.xpath(".//input[@type='checkbox']"); 
	public static By createdOnText                      = By.id("lblCreateOn");
	public String serviceTypeName                       = ".//*[starts-with(text(),'%s')]"; 
	public String serviceCheckbox                       = ".//*[starts-with(text(),'%s')]/preceding-sibling::*"; 
	public static By removeMedicationName               = By.id("txtNameRemove");
	public String medicationUnderFavorites              = ".//*[starts-with(text(),'%s')]/ancestor::*/following-sibling::*";
	public static By reportTablecontent                  = By.xpath("//div[@id='VisibleReportContentrpvViewer_ctl10']/descendant::table[14]/tbody/tr");
	public static By userDropDownMenu                    = By.id("txtUserDropDown");
	public static By reportcontentForMedication          = By.xpath("//div[@id='VisibleReportContentrpvViewer_ctl10']/descendant::table[12]/tbody/tr");
	public static By currentMedTextBox                   = By.id("tcNewMedications_0_txtName_TextBox");
	public static By currentMedSearchIcon                = By.id("tcNewMedications_0_txtNameSearch");
	public static By strengthTextBox                     = By.id("txtDose");
	public static By formTextBox                         = By.id("txtForm");
	public static By sigTextBox                          = By.id("txtSIG");
	public static By qtyTextBox                          = By.id("txtQty_DecimalTextBox");
	public static By refillsTextBox                      = By.id("txtRefills_IntegerTextBox");
	public static By providerTextBox                     = By.id("ddlPrescribedBy_Text");
	public static By reasonTextBox                       = By.id("txtReason_TextBox");
	public static By stopButton                          = By.id("btnStop_SpanBGColor");
	public static By headerRows                          = By.xpath(".//table[@id='tblRecentlyStoppedList_Table']/thead"); 
	public static By stoppedMedicationRows               = By.xpath(".//table[@id='tblRecentlyStoppedList_Table']/tbody/tr"); 
	public static By historyHeaderRows                   = By.xpath(".//table[@id='tblList_Table']/thead"); 
	public static By historyRowColums                    = By.xpath(".//tr[@id='tblList_headerRow']");
	public static By memberTextBox                       = By.id("txtUser_TextBox");
	public static By agentText                           = By.id("tblAgentOfCollection_div");
	
	
	/**
	 * This method is used for verify when user click on current medication section present in profile and click on prescribe button then current medication popup should open
	 * 
	 */
	public boolean verifyCurrentMedicationPopup(){
		boolean value=false;
		Profile.clickOnProfileInPatient();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		sleep(5000);
		WebElement currentmedication = SeleniumUtil.getElementWithFluentWait(medicationPopupPatient);
		SeleniumUtil.scrolltoWebElement(currentmedication);
		currentmedication.click();
		driver.switchTo().parentFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		WebElement prescriblebutton = SeleniumUtil.getElementWithFluentWait(newbutton);
		if(prescriblebutton.isDisplayed()){
			System.out.println("Right panel is opened");
			prescriblebutton.click();
			value=true;
		}
		else{
			value =false;
		}
		return value;
	}
	/**
	 * This method is used for verify when user clicks on prescribe button, prescribe  radio button should be selected by default for the patient
	 * 
	 */
	public boolean verifyPrescriptionDetailsPopup(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("verifing Prescribe radio button selected by default for the patient");
		WebElement prescriberadiobutton = SeleniumUtil.getElementWithFluentWait(prescribeCheckbox);
		logger.info("verifing Prescribe radio button selected by default for the patient");
		if(prescriberadiobutton.isSelected()){
			System.out.println("Prescribe radio button selected by default");
			ConstantsFile.prescriptionPopup=true;
		}
		else{
			System.out.println("Prescribe radio button is not selected by default");
			ConstantsFile.prescriptionPopup=false;
		}
		return ConstantsFile.prescriptionPopup;
	}
	/**
	 * This method is used for entering all the selected required details for the medication
	 * 
	 */
	public void createRequireDetailsInMedication(String medicationName){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);

		logger.info("clicking on medication section present for the patient");
		SeleniumUtil.getElementWithFluentWait(medicationTextBox).sendKeys(medicationName);
		sleep(2000);
		SeleniumUtil.getElementWithFluentWait(medicationSearchButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);

		logger.info("searching the medication ...");
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		WebElement medicationvalue =SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow);


		if(medicationvalue.isDisplayed()){
			action.moveToElement(medicationvalue).doubleClick().build().perform();
		}
		else{
			System.out.println("The search medication is not in the list:");
		}

		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("selecting the SIG for medicine");
		SeleniumUtil.getElementWithFluentWait(sigCheckbox).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(3000);

		SeleniumUtil.getElementWithFluentWait(sigAddButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(3000);
		SeleniumUtil.getElementWithFluentWait(sigAddButton).sendKeys(Keys.ESCAPE);
		SeleniumUtil.getElementWithFluentWait(medicationQuantity).sendKeys("10");
		SeleniumUtil.getElementWithFluentWait(refilltextBox).sendKeys("2");
	}
	/**
	 * This method is used for click on Handwritten  for the medication
	 * @throws FindFailed 
	 * 
	 */
	public void selectMethod(String methodName) throws FindFailed{
		WebElement sendButton = SeleniumUtil.getElementWithFluentWait(toolbarbutton);
		action.moveToElement(sendButton).click().build().perform();
		sleep(10000);
		SeleniumUtil.clickOnImageWitScreenInSikuli(methodName);	
	}
	/**
	 * This method is used for verify when user prescribe any medication then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyMedicationIsDocumented(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		String prescribetext=SeleniumUtil.getElementWithFluentWait(prescribeInProfile).getText();
		if((!prescribetext.isEmpty())){
			System.out.println("problem is documented");
			ConstantsFile.medicationdatapopulated=true;
		}
		else{
			System.out.println("problem is not documented");
			ConstantsFile.medicationdatapopulated=false;
		}
		return ConstantsFile.medicationdatapopulated;
	}
	/**
	 * This method is used for verify The prescription right panel should open & Prescription button should be enable 
	 * 
	 */

	public boolean verifyprescriptionRPanel(){
		boolean verifyprescriptionRpanel=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		WebElement prescriblebutton = SeleniumUtil.getElementWithFluentWait(newbutton);
		if(prescriblebutton.isEnabled()){
			System.out.println("Right panel is opened & Prescription button is  enabled ");
			//prescriblebutton.click();
			verifyprescriptionRpanel=true;
		}
		else{
			System.out.println("Prescription button is disabled");
			verifyprescriptionRpanel =false;
		}
		return verifyprescriptionRpanel;
	}
	/**
	 * This method is used for verify all the selected required details for the medication should be populated
	 * 
	 */

	public boolean verifyMedicationDetailIsPopulated(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		String medicationdata= SeleniumUtil.getElementWithFluentWait(medicationTextBox).getText();
		String strengthdata = SeleniumUtil.getElementWithFluentWait(medicationStrengthTextBox).getText();
		if(medicationdata!=null && strengthdata!=null){
			ConstantsFile.medicationdatapopulated=true;
		}
		else{
			System.out.println("medication field and stregth field are blank");
			ConstantsFile.medicationdatapopulated=false;;
		}
		return ConstantsFile.medicationdatapopulated;
	}
	/**
	 * This method is used for verify The prescription right panel should open and it should click on existing button
	 * 
	 */

	public boolean verifyPrescriptionRPanelandClickOnExisting(){
		boolean verifyprescriptionRpanelvalue=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		WebElement recordexistingbutton = SeleniumUtil.getElementWithFluentWait(recordExistingButton);
		if(recordexistingbutton.isDisplayed()){
			System.out.println("Right panel is opened");
			recordexistingbutton.click();
			verifyprescriptionRpanelvalue=true;
		}
		else{
			verifyprescriptionRpanelvalue =false;
		}
		return verifyprescriptionRpanelvalue;
	}
	/**
	 * This method is used for verify when user clicks on Existing button, Existing radio button should be selected by default for the patient
	 * 
	 */
	public boolean verifyPrescriptionDetailsPopupForExistingMedication(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("verifing Existing radio button selected by default for the patient");
		WebElement existingradiobutton = SeleniumUtil.getElementWithFluentWait(prescribeExistingCheckbox);
		logger.info("verifing Existing radio button selected by default for the patient");
		if(existingradiobutton.isSelected()){
			System.out.println("Existing radio button selected by default");
			ConstantsFile.prescriptionExistingPopup=true;
		}
		else{
			System.out.println("Prescribe radio button is not selected by default");
			ConstantsFile.prescriptionExistingPopup=false;
		}
		return ConstantsFile.prescriptionExistingPopup;
	}
	/**
	 * This method is used for verify when user prescribe any existing medication then it should document in profile of the created patient
	 * 
	 */
	public boolean verifyExistingMedicationIsDocumented(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		String prescribetext=SeleniumUtil.getElementWithFluentWait(prescribeInProfile).getText();
		if((!prescribetext.isEmpty())){
			System.out.println("Existing medication is documented");
			ConstantsFile.medicationdatapopulated=true;
		}
		else{
			System.out.println("Existing medication is not documented");
			ConstantsFile.medicationdatapopulated=false;
		}
		return ConstantsFile.medicationdatapopulated;
	}
	/**
	 * This method is used for verify when user click on current medication section present in profile then R panel should display for prescribing any mediation
	 * 
	 */
	public boolean verifyRPanelForPrescription(){
		boolean value=false;
		Profile.switchToDiagnosisListFrame();
		WebElement prescriblebutton = SeleniumUtil.getElementWithFluentWait(newbutton);
		if(prescriblebutton.isDisplayed()){
			System.out.println("Right panel is opened");
			value=true;
		}
		else{
			value =false;
		}
		return value;
	}
	/**
	 * This method is used to enter medication name in the text box of presciption tool tip window
	 * @param medicationName any medication like keflex
	 */
	public void enterMedicationName(String medicationName){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("enter the medication Name in Medication Text box");
		SeleniumUtil.getElementWithFluentWait(medicationTextBox).sendKeys(medicationName);
		sleep(2000);
		logger.info("clicking on search button for searching the medication");
		SeleniumUtil.getElementWithFluentWait(medicationSearchButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used for searching medication from DataBase /My Medication when user search any medication in the text box
	 * @param name is any medication which need to be search
	 */
	public void searchMedicationFromList(String name){
		boolean isDataPresent=false;
		List<WebElement> activitiesValues=driver.findElements(medicationList);
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : activitiesValues)
		{
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF Rows="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(rowText.contains(name)){
					System.out.println("corrected data present"); 
					WebElement plussign1 = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+name +"')]"));
					action.moveToElement(plussign1).doubleClick().build().perform();
					isDataPresent=true;
					break;
				}

			} 
			if(isDataPresent){
				break;
			}
			else{
				row_num++;
			}
		}
	}
	/**
	 * This method is used to click on Structured SIG for medicine
	 */
	public void clickOnStructuredInSIG(){
		logger.info("selecting the  Structured SIG for medicine");
		SeleniumUtil.getElementWithFluentWait(sigCheckbox).click();
	}
	/**
	 * This method is used to select dose details from ribbons
	 * @param action how the medication should be used i.e Administer ,Apply,Drink etc
	 * @param dose is quantity of medication i.e 1,2 etc
	 * @param Unit is amount of medication i.e small amount,drop etc
	 * @param route is way of taking medication i.e by mouth,into left ear etc
	 * @param timing is no of times in day the medication should take i.e single dose,Once in day etc
	 */
	public void selectQuanityForMedicationRibbon(String action ,String dose, String Unit ,String route,String timing ){
		logger.info("clicking on  medication ribbon");
		SeleniumUtil.getElementWithFluentWait(sigAddButton).click();
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);

		logger.info("selecting the action ,dose,unit ,route & Timing for medication..");
		WebElement plussign1 = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+action +"')]"));
		WebElement plussign2 = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+dose +"')]"));
		WebElement plussign3 = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+Unit +"')]"));
		WebElement plussign4 = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+route +"')]"));
		WebElement plussign5 = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+timing +"')]"));
		plussign1.click();
		plussign2.click();
		plussign3.click();
		plussign4.click();
		plussign4.click();
		plussign5.click();

		logger.info("closing the medication ribbon");
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(sigAddButton).sendKeys(Keys.ESCAPE);

		logger.info("medication dose details..");
		ConstantsFile.doseDetails=SeleniumUtil.getElementWithFluentWait(medicationDoseDetail).getText();
		System.out.println("dose details are"+ConstantsFile.doseDetails);
	}
	/**
	 * This method is used to select pharmacy if any pharmacy is not present for prescribing medication
	 * @throws FindFailed 
	 */
	@SuppressWarnings("unused")
	public void selectPharmacy() throws FindFailed{
		logger.info("checking the pharmacy details are present for prescription");
		ConstantsFile.pharmacyValue=SeleniumUtil.getElementWithFluentWait(pharmacyTextbox).getText();	

		if( ConstantsFile.pharmacyValue.isEmpty()){
			logger.info("clicking on search for searching the pharmacy..");
			WebElement pharmacyButton = SeleniumUtil.getElementWithFluentWait(By.id("ddlPharmacy1_Text"));
			action.moveToElement(pharmacyButton).click().build().perform();
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnSearch");
			SeleniumUtil.switchToParentFrame(Frames.CREATION);
			SeleniumUtil.waitForProgressBar(Frames.CREATION);

			logger.info("selecting the any pharmacy..");
			WebElement value=SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow);
			SeleniumUtil.doubleClick(value);
		}

		else{
			System.out.println("Pharmacy already selected");
		}
	}
	/**
	 * This method is used for entering all the selected required details for the medication
	 * @throws FindFailed 
	 * 
	 */
	public void createRequireDetailsForPrescription(String medicationName) throws FindFailed{
		logger.info("entering the medication");
		enterMedicationName(medicationName);
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);
		logger.info("Selecting the medication");
		searchMedicationFromList(medicationName);
		checkDoseAlert();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);		
		logger.info("selecting the  Structured SIG for medicine");
		clickOnStructuredInSIG();
		logger.info("selecting the dose details form medication ribbon");
		selectQuanityForMedicationRibbon(PrescriptionTest.actionForMedication,PrescriptionTest.doseForMedication,PrescriptionTest.unitForMedication,PrescriptionTest.routeForMedication,PrescriptionTest.timingForMedication);
		logger.info("selecting the pharmacy if not present..");
		selectPharmacy();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("selecting the quanitiy & refill details");
		SeleniumUtil.getElementWithFluentWait(medicationQuantity).sendKeys(PrescriptionTest.quantityForMedication);
		SeleniumUtil.getElementWithFluentWait(refilltextBox).sendKeys(PrescriptionTest.refillForMedication);
	}
	/**
	 * This method is used to verify if provider is associated with the user ,then it should be already present while prescribing any medication
	 * @return true if provider is present
	 */
	public boolean verifyProvider(){
		boolean isProviderDocument=false;
		logger.info("getting the provider value");
		ConstantsFile.providerValue=SeleniumUtil.getElementWithFluentWait(providerTextbox).getAttribute("value");
		System.out.println("provider value"+ ConstantsFile.providerValue);
		if(! ConstantsFile.providerValue.equalsIgnoreCase(PrescriptionTest.providercomName) ){
			logger.info("Provider is documented");
			isProviderDocument=true;
		}
		return isProviderDocument;
	}
	/**
	 * This method is used to verify if any dose alert is present  ,then override it
	 */
	public boolean checkDoseAlert(){
		boolean isDoseAlertPresent=false;
		try{
			SeleniumUtil.switchToParentFrame(Frames.CREATION);
			SeleniumUtil.waitForProgressBar(Frames.CREATION);
			String doseText=SeleniumUtil.getElementWithFluentWait(ReportPage.messageContent).getText();
			System.out.println("dose text is"+doseText);
			if(!doseText.isEmpty()){
				SeleniumUtil.getElementWithFluentWait(Profile.reasonTextBox).sendKeys("override it");
				SeleniumUtil.getElementWithFluentWait(overrideButton).click();
				isDoseAlertPresent=true;
			}
		}
		catch(Exception e){
			System.out.println("No Alert is present..");
		}
		return isDoseAlertPresent;
	}
	/**
	 * This method is used to verify prescription is document in R panel with medication name ,dose details & current detail
	 * @return true if  prescription is document in R panel 
	 */
	public boolean verifyPrescriptionIsDocumented(String completeName){
		boolean isMedicationPrescibe=false;
		String prescriptionText=SeleniumUtil.getElementWithFluentWait(ReportPage.messageContent).getText();
		System.out.println(prescriptionText);
		String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
		String currentValue = completeName+"\n"+ConstantsFile.doseDetails+" "+currentDate;
		if(prescriptionText.contains(currentValue)){
			System.out.println("medication is correctly prescribe");
			isMedicationPrescibe=true;
		}
		return isMedicationPrescibe;
	}
	/**
	 * This method is used to verify E-prescription page is opened when user clicks on e-Prescription option from
	 * Queue Management Module
	 * @return true if correct page opened
	 */
	public boolean verifyEPrescriptionPage(){
		boolean isPageOpened=false;
		SeleniumUtil.switchToParentFrame(Frames.EPRESCRIPTION);
		SeleniumUtil.waitForProgressBar(Frames.EPRESCRIPTION);
		String pageTitle=SeleniumUtil.getElementWithFluentWait(ReportPage.reportTitle).getText();
		if(pageTitle.equalsIgnoreCase("E-Prescriptions")){
			logger.info("E-Prescriptions Page is Opened");
			isPageOpened=true;
		}
		return isPageOpened;
	}
	/**
	 * This method is used to verify E prescription queue should be generated when user prescribe any medication by Send Method
	 * @return true if queue is present in E-prescription queue
	 */
	public boolean verifyEPrescriptionQueue(){
		boolean isRowPresent=false;
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row Text"+rowtext);
			String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
			if(rowtext.contains(currentDate) && rowtext.contains(ConstantsFile.providerValue) && rowtext.contains(PrescriptionTest.existingPatientfirstname) && rowtext.contains(PrescriptionTest.ketekMedication)) {
				logger.info("correct queue is generated in the e-prescription queue");
				isRowPresent=true;
				break;
			}

		}
		return isRowPresent;

	}
	/**
	 * This method is used to click on print button
	 * @throws FindFailed
	 */
	public void clickPrint() throws FindFailed{
		SeleniumUtil.clickOnImageWitScreenInSikuli("printbutton");
		GpinUserLogin.clickOnPrint();
		sleep(12000);

	}
	/**This method will verify if existing medication  are added in profile module ,then void the existing mediation details
	 * 
	 * @throws FindFailed
	 */
	public boolean rClickPrescriptionMedication() throws FindFailed{
		boolean isRowPresent=false;
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row Text"+rowtext);

			if(rowtext.contains(PrescriptionTest.keflexMedication) ) {
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("selectVoid");
				SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);	
				SeleniumUtil.getElementWithFluentWait(Profile.reasonTextBox).sendKeys("Not Required");
				SeleniumUtil.getElementWithFluentWait(Profile.voidButton).click();
				break;
			}

		}
		return isRowPresent;

	}
	/**
	 * This method is used for verify when user clicks on None checkbox then some validation text should be display in profile screen 
	 *
	 */
	public boolean verifyMedicationReconciliationReviewed(){
		boolean isReviewedProblem=false;
		///Get the data to create new user present in excel
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		ConstantsFile.completeUserName =ConstantsFile.loginData.get(ConstantsFile.COMPLETE_USERNAME);
		String ReviewedmedicationText =SeleniumUtil.getElementWithFluentWait(reviewMedicationText).getText();
		System.out.println("medication value is"+ReviewedmedicationText);
		String currentDate=DateUtil.getCurrentDateInDateFormatted("MM/dd/yyyy");
		String currentValue = "Medication reconciliation last performed:"+" "+currentDate+" by "+ConstantsFile.completeUserName;
		System.out.println("Current value is"+currentValue);
		if(ReviewedmedicationText.contains(currentValue)){
			System.out.println("Medication reconciliation last performed Text Present..");
			isReviewedProblem=true;
		}
		return isReviewedProblem;
	}
	/**
	 * This method is used to verify E prescription queue should be generated when user prescribe any medication by Send Method
	 * @return true if queue is present in E-prescription queue
	 */
	public boolean verifyEPrescriptionOutboundQueue(){
		boolean isRowPresent=false;
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row Text"+rowtext);
			String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
			if(rowtext.contains(currentDate) && rowtext.contains(ConstantsFile.providerValue) && rowtext.contains(PrescriptionTest.ketekMedication)) {
				logger.info("correct queue is generated in the e-prescription queue");
				isRowPresent=true;
				break;
			}

		}
		return isRowPresent;

	}
	/**
	 * This method is used for verify No allergy checkbox is checked or unchecked in profile screen 
	 *
	 */
	public void selectNoCurrentMedicationCheckbox(){
		String noAllergyCheckBoxState =SeleniumUtil.getElementWithFluentWait(currentMedicationCheckboxState).getAttribute("stateName");
		if(noAllergyCheckBoxState.equalsIgnoreCase("unchecked")){
			System.out.println("None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(nocurrentMedicationCheckbox).click();
		}
		else{
			System.out.println("click None Checkbox is unchecked..");
			SeleniumUtil.getElementWithFluentWait(nocurrentMedicationCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(nocurrentMedicationCheckbox).click();
		}

	}
	/**
	 * This method is used for verify allergy text in profile screen 
	 *
	 */
	public boolean verifyCurrentMedicationTextInProfile(){
		boolean isNomedicationTextAdded=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		String currentMedicationText=SeleniumUtil.getElementWithFluentWait(noMedicationText).getText();
		System.out.println("current Medication text is"+currentMedicationText);
		if(currentMedicationText.equalsIgnoreCase("Patient Takes No Medications")){
			System.out.println( "correct  text is doucmented ");
			isNomedicationTextAdded=true;
		}
		return isNomedicationTextAdded;

	}
	/**
	 * This method is used for clicking on search icon for searching any medication
	 */
	public void clickOnSearchIcon(){
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("clicking on search button for searching the medication");
		SeleniumUtil.getElementWithFluentWait(medicationSearchButton).click();
		SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
		SeleniumUtil.waitForProgressBar(Frames.MEDICATION_LIST);

	}
	/**
	 * This method is used for searching medication by alphabets
	 * @param alphabet
	 */
	public void searchMedicationByAlaphabet(String alphabet){
		sleep(5000);
		List<WebElement> noOfAlphabets = driver.findElements(alphabetList);	
		System.out.println("No Of Alphabets is:" +noOfAlphabets.size());
		for(WebElement menuName:noOfAlphabets){
			String menuText=menuName.getText();
			System.out.println("Tool Bar text is:"+menuText);
			if(menuText.contains(alphabet)){
				WebElement plussign1 = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'"+alphabet +"')]"));
				plussign1.click();
				break;
			}

		}

	}
	/**
	 * This method is used to verify all the rows starts with selected alphabets
	 * @param alphabet
	 */
	public boolean verifyRow(String alphabet){
		sleep(3000);
		boolean isAlphabetSearced=false;
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row is"+rowtext);
			if(rowtext.startsWith(alphabet)){
				System.out.println("all the row starts with"+alphabet);
				isAlphabetSearced=true;
			}
		}
		return isAlphabetSearced;

	}
	/**
	 * This method is used for closing the medication window
	 */
	public void closeMedicationWindow(){
		SeleniumUtil.getElementWithFluentWait(medicationNameTextBox).sendKeys(Keys.ESCAPE);
	}
	/**
	 * This method is used for searching medication with name
	 */
	public void searchMedicationByName(){
		SeleniumUtil.getElementWithFluentWait(medicationNameTextBox).sendKeys(PrescriptionTest.keflexMedication);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchbutton).click();
		sleep(5000);
	}
	public void switchToPrescriptionRightPanelFrame(){
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");
	}
	public void switchToConfigureMyMedicatioFrame(){
		SeleniumUtil.switchToFrame(driver, "fraFavorites_Frame");

	}
	public void switchToTabFrame(){
		SeleniumUtil.switchToFrame(driver, "fraSearch_Frame");

	}
	/**
	 * This method is used for searching medicine in configure my medication tab
	 * @param medicationName
	 */
	public void searchMedicineInConfigureMyMedicationTab(String medicationName){
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(medicationName);
		SeleniumUtil.getElementWithFluentWait(Profile.searchIcon).click();

	}
	/**
	 * This method is used to verify all the rows starts with selected alphabets
	 * @param alphabet
	 * @throws FindFailed 
	 */
	public boolean rClickInSearchTab(String alphabetName,String imageName) throws FindFailed{
		sleep(3000);
		boolean isAlphabetSearced=false;
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row is"+rowtext);
			if(rowtext.startsWith(alphabetName)){
				ConstantsFile.deleteMedicationName =irows.getText();
				System.out.println("row is"+ConstantsFile.deleteMedicationName);
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli(imageName);
				isAlphabetSearced=true;
				break;
			}
		}
		return isAlphabetSearced;

	}
	/**
	 * This method is used to modify medication when user R click & select Modify
	 */
	public void modifyMedication(){
		String medicationNameText=SeleniumUtil.getElementWithFluentWait(medicationDescription).getText();
		ConstantsFile.medicationName = medicationNameText.concat(ConstantsFile.genData.generateRandomNumber(3));
		SeleniumUtil.getElementWithFluentWait(medicationDescription).clear();
		SeleniumUtil.getElementWithFluentWait(medicationDescription).sendKeys(ConstantsFile.medicationName);
	}

	/**
	 * This method is used to verify correct modified rows added in the row
	 * @param alphabet
	 */
	public boolean verifyRclickRow(){
		sleep(3000);
		boolean isAlphabetSearced=false;
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row is"+rowtext);
			if(rowtext.startsWith( ConstantsFile.medicationName)){
				System.out.println("correct modify item is present"+ConstantsFile.medicationName);
				isAlphabetSearced=true;
				break;
			}
		}
		return isAlphabetSearced;

	}
	/**
	 * This method is used to switch into right click Frame
	 */
	public void switchToRclickFrame(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panUserList_Frame");
		switchToPrescriptionRightPanelFrame();

	}
	/**
	 * This method is used to verify all the rows starts with selected alphabets
	 * @param alphabet
	 * @throws FindFailed 
	 */
	public boolean verifyDeletedMedication() throws FindFailed{
		sleep(3000);
		boolean isAlphabetSearced=false;
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row is"+rowtext);
			if(!rowtext.equalsIgnoreCase(ConstantsFile.deleteMedicationName)){
				System.out.println("Medication is deleted..");
				isAlphabetSearced=true;
				break;
			}
		}
		return isAlphabetSearced;

	}
	/**
	 * This method is used for when user r click & select Context menu in the prescription ,then it should select the service
	 * 
	 */
	public void clickOnProcedure(String procedurename){
		List<WebElement> totalCheckBoxs = driver.findElements(procedures);
		System.out.println("No Of Tabs are:" +totalCheckBoxs.size());
		for(WebElement checkbox:totalCheckBoxs){
			String checkboxText=checkbox.getText();
			System.out.println("checkbox text is:"+checkboxText);	
			if(checkboxText.equalsIgnoreCase(procedurename)){
				WebElement serviceType=	SeleniumUtil.getElementWithFluentWait(StringUtility.DynamicXpath(serviceTypeName, procedurename));
				WebElement serviceTypeCheckbox=	SeleniumUtil.getElementWithFluentWait((StringUtility.DynamicXpath(serviceCheckbox, procedurename)));
				String checkBoxValue=serviceTypeCheckbox.getAttribute("CHECKED");
				String servieValue=serviceType.getText();
				if(servieValue.equalsIgnoreCase(procedurename) && checkBoxValue!=null){
					System.out.println("Checkbox value is already checked");
				}
				else if(servieValue.equalsIgnoreCase(procedurename) && checkBoxValue==null){
					checkbox.click();
					checkbox.sendKeys(Keys.SPACE);
				}
				break;
			}
		}
	}

	/**
	 * This method is used to verify System should open the Properties window with the Created on & Service Type details	
	 * @return true if correct Property Details are Added
	 */
	public boolean verifyPropertyDetails(){
		boolean isPropertyDetailsAdded=false;
		String createdText=SeleniumUtil.getElementWithFluentWait(createdOnText).getText();
		System.out.println("Created text"+createdText);
		String serviceText=SeleniumUtil.getElementWithFluentWait(ReportPage.messageContent).getText();
		System.out.println("Created text"+serviceText);
		if(createdText.startsWith("Created on") && serviceText.contains(ProfileTest.existingActivityForProfilePatient)){
			logger.info("Context & property menu have correct properties");
			SeleniumUtil.getElementWithFluentWait(createdOnText).sendKeys(Keys.ESCAPE);
			isPropertyDetailsAdded=true;

		}
		return isPropertyDetailsAdded;
	}
	/**
	 * This method is used to remove medication Name
	 */
	public void removeMedicationName(){
		SeleniumUtil.getElementWithFluentWait(removeMedicationName).click();
	}
	/**
	 * This method is used to verify when user rclick "Add to Favorites ,then medication should be added under Favorites Option"
	 * @param medicationName
	 * @return boolean value
	 */
	public boolean verifyMedicationIsAdded(String medicationName){
		boolean isValueAdded=false;
		List<WebElement> nodes =driver.findElements(By.className("rtIn"));
		System.out.println(nodes.size());
		for(WebElement node:nodes){
			String rowText=node.getText();
			System.out.println(rowText);
			if(rowText.equalsIgnoreCase(medicationName)){
				List<WebElement> medicationUnderFavorites1=driver.findElements(StringUtility.DynamicXpath(medicationUnderFavorites, medicationName));
				System.out.println(medicationUnderFavorites1.size());
				for(WebElement node1:medicationUnderFavorites1){
					String rowText1=node1.getText();
					System.out.println(rowText1);
					String subStringMedication =ConstantsFile.deleteMedicationName.substring(0, 6);
					if(rowText1.contains(subStringMedication)){
						System.out.println("value is added in favorties");
						isValueAdded=true;
						break;
					}
					else{
						System.out.println("value is not added");
					}
				}
			}
			break;
		}
		return isValueAdded;
	}

	/**This method will verify if existing allergy  are added in profile module ,then delete the existing allergy details
	 * 
	 * @throws FindFailed
	 */
	public void deleteAddedAllergyDetails() throws FindFailed{
		sleep(5000);
		Profile.switchToDiagnosisListFrame();
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			System.out.println("the rows are:"+irows.getText());
			String rowtext =irows.getText();
			System.out.println("row text is"+rowtext);
			if( rowtext.contains(ProfileTest.selectAllergyFormMyAllergies)){
				System.out.println("allergy already  added in demographics");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("deleteUser");
				SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);	
				SeleniumUtil.getElementWithFluentWait(Profile.reasonTextBox).sendKeys("Not Required");
				SeleniumUtil.getElementWithFluentWait(MedicalChartPage.savebutton).click();
				deleteAddedAllergyDetails();	

			}
			else{
				System.out.println("No existing Allergies  are present");
			}

		}
	}
	/**
	 * This method is used for adding necessary details for prescribing any medication
	 */
	public void selectQuantity(){
		SeleniumUtil.getElementWithFluentWait(sigAddButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(3000);
		SeleniumUtil.getElementWithFluentWait(sigAddButton).sendKeys(Keys.ESCAPE);
		SeleniumUtil.getElementWithFluentWait(medicationQuantity).sendKeys("10");
		SeleniumUtil.getElementWithFluentWait(refilltextBox).sendKeys("2");
	}
	/**
	 * This method is used to verify if any dose alert is present  ,then override it
	 */
	public boolean checkDrugAllergyAlerts(){
		boolean isDoseAlertPresent=false;
		try{
			//SeleniumUtil.switchToParentFrame(Frames.CREATION);
			//SeleniumUtil.waitForProgressBar(Frames.CREATION);
			String doseText=SeleniumUtil.getElementWithFluentWait(ReportPage.messageContent).getText();
			System.out.println("dose text is"+doseText);
			if(doseText.contains("ALLERGY ALERT!")){
				System.out.println("Drug Allergy Interation is present");
				SeleniumUtil.getElementWithFluentWait(Profile.reasonTextBox).sendKeys("override it");
				SeleniumUtil.getElementWithFluentWait(overrideButton).click();
				isDoseAlertPresent=true;
			}
			else if(doseText.contains("DRUG INTERACTION ALERT!")){
				System.out.println("Drug Drug Interation is present");
				SeleniumUtil.switchToParentFrame(Frames.LOGIN);
				SeleniumUtil.getElementWithFluentWait(Profile.closePopUp).click();
				SeleniumUtil.getElementWithFluentWait(Profile.closePopUp).click();
				try {
					SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
				} catch (Exception e) {
					System.out.println("No popup present");
				}
				isDoseAlertPresent=true;
			}
			else if(doseText.contains("DOSE ALERT!")){
				SeleniumUtil.getElementWithFluentWait(Profile.reasonTextBox).sendKeys("override it");
				SeleniumUtil.getElementWithFluentWait(overrideButton).click();
				isDoseAlertPresent=true;
			}

		}
		catch(Exception e){
			System.out.println("No Alert is present..");
		}
		return isDoseAlertPresent;

	}
	/**
	 * This method is used for clicking on user drop down menu
	 */
	public void clickUserDropDown(){
		logger.info("clicking on user drop down menu...");
		SeleniumUtil.getElementWithFluentWait(userDropDownMenu).click();
	}
	public void switchToPopup3Frame(){
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
	}
	/**
	 * This method is used to click on Run Report Button
	 */
	public void runReport(){
		SeleniumUtil.switchToParentFrame(Frames.REPORTINSIDE);
		SeleniumUtil.getElementWithFluentWait(ReportPage.runReportButton).click();
		SeleniumUtil.waitForProgressBar(Frames.REPORTINSIDE);
	}
	/**
	 * This method is used for searching the user with first name & Last Name
	 */
	public void searchUser(){
		logger.info("search the logged in user by first name & last name");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.searchusertextbox).sendKeys(ProfileTest.userNameForAdvanceDirectiveProfile);
		logger.info("clicking on search button...");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.searchbutton).click();
		sleep(3000);
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.filteredrrow).click();
	}
	/**
	 * This method is used for verify the valid data is generated form Clinical Interactions Activity
	 * 
	 */
	public boolean verifyRecords(){
		boolean isAllergyTotalValue=false;
		SeleniumUtil.switchToParentFrame(Frames.REPORTGENERATION);
		List <WebElement> reporttext = driver.findElements(reportTablecontent);
		System.out.println("NUMBER OF ROWS IN THIS TABLE = "+reporttext.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : reporttext){
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{  
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if(row_num==4 && col_num==3 ){
					System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
					int result=0;
					result = Integer.parseInt(rowText);
					if(result>=1){
						System.out.println("Total Allergy value "+result);
						isAllergyTotalValue=true;
						break;
					}
				}
			}
			if(isAllergyTotalValue){
				break;
			}
			else{
				row_num++;
			}
		}
		return isAllergyTotalValue;
	}
	/**
	 * This method is used to select medication Name in medication per patient report
	 * @param medicationName
	 */
	public void selectMedicationForMedicationPerPatientReport(String medicationName){
		SeleniumUtil.getElementWithFluentWait(medicationTextBox).sendKeys(medicationName);
	}
	/**
	 * This method is used for verify the valid data is generated form Clinical Interactions Activity
	 * 
	 */
	public boolean verifyRecordsForMedicationPerPatientReport(String patientcompletename){
		boolean isMedicationPerPatient=false;
		List <WebElement> reporttext = driver.findElements(reportcontentForMedication);
		System.out.println("NUMBER OF ROWS IN THIS TABLE = "+reporttext.size());
		int row_num,col_num;
		row_num=1;
		for(WebElement trElement : reporttext){
			List<WebElement> td_collection=trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS="+td_collection.size());
			col_num=1;
			for(WebElement tdElement : td_collection)
			{  
				String rowText=tdElement.getText();
				System.out.println("row # "+row_num+", col # "+col_num+ "text="+tdElement.getText());
				col_num++;
				if( rowText.equalsIgnoreCase(patientcompletename) ){

					System.out.println("correct patient present");
					isMedicationPerPatient=true;
					break;
				}

			}
			if(isMedicationPerPatient){
				break;
			}
			else{
				row_num++;
			}
		}
		return isMedicationPerPatient;
	}
	/**
	 * This method is used for setting warning level present in RX
	 * @param warningLevel
	 */
	public void selectWarningLevelForDrugInteration(String warningLevel){
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		SeleniumUtil.waitForProgressBar(Frames.CREATION);
		SeleniumUtil.removeReadOnly("ddlWarningLevel_Text",warningLevel);
		SeleniumUtil.getElementWithFluentWait(ReportPage.runReportButton).click();
	}
	/**
	 * This method is used for adding medication Name for current medication
	 */
	public void enterCurrentMedData(){
		SeleniumUtil.getElementWithFluentWait(currentMedTextBox).sendKeys("Amoxicillin");
		SeleniumUtil.getElementWithFluentWait(currentMedSearchIcon).click();
	}
	/**
	 * This method is used to verify current medication is document in R panel with medication name 
	 * @return true if  prescription is document in R panel 
	 */
	public boolean verifyCurrentMedIsDocumented(String completeName){
		boolean isMedicationPrescibe=false;
		String prescriptionText=SeleniumUtil.getElementWithFluentWait(ReportPage.messageContent).getText();
		System.out.println(prescriptionText);
		if(prescriptionText.contains(completeName)){
			System.out.println("Current medication is correctly documented");
			isMedicationPrescibe=true;
		}
		return isMedicationPrescibe;
	}

	/**
	 * This method is used to verify all the rows starts with selected alphabets
	 * @param alphabet
	 * @throws FindFailed 
	 */
	public boolean rClickMedicationOptions(String alphabetName,String imageName) throws FindFailed{
		sleep(3000);
		boolean isAlphabetSearced=false;
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row is"+rowtext);
			if(rowtext.startsWith(alphabetName)){
				String rowText =irows.getText();
				System.out.println("row is"+rowText);
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli(imageName);
				isAlphabetSearced=true;
				break;
			}
		}
		return isAlphabetSearced;

	}
	/**
	 * This method is used to verify rclick properties i.e Renew
	 * @return true if all properties are correct
	 */
	public boolean verifyRClickProperties(){
		boolean isRenewPropertyPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		String medicationTextbox =SeleniumUtil.getElementWithFluentWait(medicationTextBox).getAttribute("readOnly");
		Boolean strengthText =SeleniumUtil.getElementWithFluentWait(strengthTextBox).isEnabled();
		Boolean formText =SeleniumUtil.getElementWithFluentWait(formTextBox).isEnabled();
		String sigText =SeleniumUtil.getElementWithFluentWait(sigTextBox).getText();
		Boolean quantity =SeleniumUtil.getElementWithFluentWait(qtyTextBox).isEnabled();
		Boolean refill =SeleniumUtil.getElementWithFluentWait(refillsTextBox).isEnabled();
		String providerValue =SeleniumUtil.getElementWithFluentWait(providerTextBox).getAttribute("value");
		if(medicationTextbox.equalsIgnoreCase("True") && strengthText && formText && !(sigText.isEmpty()) && quantity && refill && providerValue.contains(PrescriptionTest.providercompleteName)){
			System.out.println("All the correct properties are present for renew options");
			isRenewPropertyPresent=true;
		}
		return isRenewPropertyPresent;
	}
	/**
	 * This method is used to checking medication status in History
	 * @return
	 */
	public boolean checkMedicationStatusInHistory(){
		boolean checkMedicationStatus=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		ConstantsFile.loginData = ExcelFileUtilty.readExcelSheet("Login");
		ConstantsFile.completeUserName =ConstantsFile.loginData.get(ConstantsFile.COMPLETE_USERNAME);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println("row is"+rowtext);
			if(rowtext.contains("Renewed") && rowtext.contains(PrescriptionTest.providercompleteName) && rowtext.contains(ConstantsFile.completeUserName)){
				System.out.println("correct status is present");
				checkMedicationStatus=true;
			}
		}
		return checkMedicationStatus;
	}
	/**
	 * This method is used for switching into interaction alert popup frames
	 */
	public void switchToInteractionAlertFrame(){
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		SeleniumUtil.waitForProgressBar(Frames.CREATION);
	}
	/**
	 * This method is used for selecting reason for stopping medication
	 */
	public void stopMedication(){
		SeleniumUtil.getElementWithFluentWait(reasonTextBox).sendKeys("Not Required Now");
		SeleniumUtil.getElementWithFluentWait(stopButton).click();
	}
	/**
	 * This method is used to verify when user stop any medication,then it should present in stopped medication header present in right panel
	 * @return true if stopped medication present in rpanel under stopped medication section
	 */
	public boolean verifyStoppedMedication(){
		boolean isMedicationStopped=false;
		List<WebElement> noofheaders=driver.findElements(headerRows);
		System.out.println(noofheaders.size());	
		for(WebElement irows:noofheaders){
			String rowtext =irows.getText();
			System.out.println("row is"+rowtext);
			if(rowtext.contains("Recently Stopped")){
				List<WebElement> noOfRows=driver.findElements(stoppedMedicationRows);
				System.out.println(noOfRows.size());
				for(WebElement irows1:noOfRows){
					String rowtext1 =irows1.getText();
					System.out.println("row is"+rowtext1);
					if(rowtext1.contains(PrescriptionTest.sronyxMedication)){
						System.out.println("Correct medication is documented");
						isMedicationStopped=true;
					}
				}
			}
		}
		return isMedicationStopped;
}
	
	/**
	 * This Method is used to verify all the history columns are present
	 * @return true if all columns are present
	 */
	public String verifyHistoryData(){
	
		String isHistoryHeaderPresent=null;
		isHistoryHeaderPresent=	SeleniumUtil.getElementWithFluentWait(DocumentPage.allvisibletext).getText();
		System.out.println("History colums are"+isHistoryHeaderPresent);
		return isHistoryHeaderPresent;
	}
	/**
	 * This method is used to select create member for the prescription module
	 */
	public void selectPrescriptionMember(){
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(PrescriptionTest.memberFirstName);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(PrescriptionTest.memberLastName);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchbutton).click();
		sleep(5000);
		List <WebElement>membernamevalues =driver.findElements(AppointmentPage.totaltrtags);
		System.out.println(membernamevalues.size());
		for(WebElement membersrow:membernamevalues){
			System.out.println(membersrow.getText());
			if(membersrow.getText().contains(PrescriptionTest.memberLastName) && (membersrow.getText().contains(PrescriptionTest.memberFirstName))){
				SeleniumUtil.doubleClick(membersrow);
				sleep(5000);
				break;
			
		}
	
	}
	
}
/**
 * This method is used to verify unprescribed user have correctly agent of prescribe user
 * @return true if correct value is present
 */
	public boolean verifyPrecondtionAgentDetails(){
		boolean isCorrectDetailsPresent=false;
		String associatedUser=SeleniumUtil.getElementWithFluentWait(memberTextBox).getAttribute("value");
		System.out.println(associatedUser);
		String agentOf =SeleniumUtil.getElementWithFluentWait(agentText).getText();
		System.out.println(agentOf);
		if(associatedUser.equalsIgnoreCase(PrescriptionTest.unprescribeCompleteName)  && agentOf.contains(PrescriptionTest.providercomName) ){
			System.out.println("correct agent is associated with member");
			//SeleniumUtil.getElementWithFluentWait(ConfigurationPage.backButton).click();	
			isCorrectDetailsPresent=true;
		}
		return isCorrectDetailsPresent;
	}
}