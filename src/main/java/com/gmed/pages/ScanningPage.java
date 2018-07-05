package com.gmed.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import static com.gmed.helper.DriverFactory.action;
import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.utils.SeleniumUtil.getElementCount;

import java.util.List;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.DateUtil;
import com.gmed.utils.SeleniumUtil;

public class ScanningPage extends BaseAbstractPage {
	/** Logger to log the ScanningPage log messages */
	private static Logger	logger				= LogManager.getLogger(ScanningPage.class);
	public static By		importButton		= By.id("btnImport_SpanBGColor");
	public static By		patientDropdown		= By.id("txtPatientDropDown");
	public static By		fileAsDropDown		= By.id("txtFileAsDropDown");
	public static By		nowLink				= By.id("btnToday");
	public static By		printButton			= By.id("btnPrint_SpanBGColor");
	public static By		faxButton			= By.id("btnFax_SpanBGColor");
	public static By		faxTextBox			= By.id("txtRecipientName_TextBox");
	public static By		faxnumberTextBox1	= By.id("ptbFaxNumber_00");
	public static By		faxnumberTextBox2	= By.id("ptbFaxNumber_01");
	public static By		faxnumberTextBox3	= By.id("ptbFaxNumber_02");
	public static By		taskButton			= By.id("tblTasksCollectionAdd");
	public static By		selfLinkInTask		= By.className("LabelClick");
	public static By		fileButton			= By.id("btnFile_SpanBGColor");
	public static By		addpatient			= By.id("txtPatientNameAdd");
	public static By		clearButton			= By.id("btnFiltersClear_TextSpan");
	public static By		searchButton		= By.id("btnFiltersSearch_TextSpan");
	public static By		firstScannedDoc		= By.xpath("//table[@id='tblList_Table']/tbody/tr[1]/td[3]");
	public static By		patientName			= By.id("txtPatientName_TextBox");
	public static By		historyDateCol		= By.id("tblList_ColumnIndex_0");
	public static By		historyUserCol		= By.id("tblList_ColumnIndex_1");
	public static By		historyActionCol	= By.id("tblList_ColumnIndex_2");
	public static By		historyDescCol		= By.id("tblList_ColumnIndex_3");
	public static By        rhtpnlDescription   = By.id("txtDescription_TextBox");
	public static By        rhtpnlFileAsDropdown = By.id("txtFileAsDropDown");
	public static By        fileAsProcedureNote  = By.xpath("//span[@class='rtIn' and text()='Procedure note']");
	public static By        rhtpnlDateNow        = By.id("btnToday");
	public static By        rhtpnlChart          = By.id("txtChartRemarks_TextBox");
	public static By		rhtpnlLocationDropdown	= By.id("txtLocationDropDown");
	public static By		firstLocationName		= By.xpath("//table[@id='tblList_Table']/tbody/tr[1]/td[2]");
	public static By		firstScannedDocLocation	= By.xpath("//table[@id='tblList_Table']/tbody/tr[1]/td[23]");

	public static By		addChartRemarks			= By.id("txtChartRemarksAdd");
	public static By		configureIcon			= By.xpath("//td[text()='Configure']");
	public static By		newButton				= By.id("btnNew_SpanBGColor");
	public static By		textBox					= By.id("txtName_TextBox");
	public static By		contentTextBox			= By.id("txtContent_TextBox");
	public static By		saveButton				= By.id("btnSave_SpanBGColor");
	public static By		serviceDropDown			= By.id("txtServiceDropDown");
	public static By		firstServiceOnPopUp		= By.xpath("//span[@class='rtIn']");
	public static By		serviceTextBox			= By.id("txtService_TextBox");
	public static By		procedureDropDown		= By.id("txtProcedureDropDown");
	public static By		firstProcedureOnPopUp	= By.xpath("//table[@id='tblList_Table']/tbody/tr[1]/td[4]");
	public static By		procedureTextBox		= By.id("txtProcedure_TextBox");
	public static By		dxstudyDropDown			= By.id("txtDxStudyDropDown");
	public static By		firstDxStudyOnPopUp		= By.xpath("//table[@id='tblList_Table']/tbody/tr[1]/td[4]");
	public static By		dxstudyTextBox			= By.id("txtDxStudy_TextBox");
	public static By		addSignOnOrders			= By.id("tblOrderCollectionAdd");
	public static By		ordersCheckboxOnPopup	= By.className("checkboxSelection");
	public static By		selectOnOrdersPopup		= By.id("btnSelect_SpanBGColor");
	public static By		ordersSection			= By.xpath("//table[@id='tblOrderCollection_Table']/tbody/tr");
	public static By		addSignOnDiagnoses		= By.id("tblDiagnosesCollectionAdd");
	public static By		diagnosesSection		= By.xpath("//table[@id='tblDiagnosesCollection_Table']/tbody/tr");

	/**
	 * This method is used for for importing any pdf,image file from the
	 * scanning module present in queue management module from the application
	 * 
	 */
	public void clickOnImportButton() {
		sleep(15000);
		SeleniumUtil.switchToParentFrame(Frames.SCANNING);
		logger.info("clicking on import button");
		SeleniumUtil.waitForProgressBar(Frames.SCANNING);
		SeleniumUtil.getElementWithFluentWait(importButton).click();
		// SeleniumUtil.waitForProgressBar(Frames.SCANNING);
	}

	/**
	 * This method is used for for switch into scanning frame for scanning
	 * module
	 * 
	 */
	public void swithToScanning() {
		// SeleniumUtil.waitForProgressBar(Frames.SCANNINGPATIENT);
		SeleniumUtil.switchToParentFrame(Frames.SCANNINGPATIENT);
		SeleniumUtil.waitForProgressBar(Frames.SCANNINGPATIENT);
		SeleniumUtil.getElementWithFluentWait(patientDropdown).click();
		SeleniumUtil.waitForProgressBar(Frames.SCANNINGPATIENT);
	}

	/**
	 * This method is used for select the corresponding patient for the imported
	 * file from the scanning module present in queue management module from the
	 * application
	 * 
	 *
	 */
	public void selectPatient() {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(ConstantsFile.patientfirstname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(ConstantsFile.patientlastname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.searchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(3000);
		action.moveToElement(SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow));
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}

	/**
	 * This method is used for select the fileAs value for the imported file
	 * from the scanning module present in queue management module from the
	 * application
	 * 
	 */
	public void clickOnFileAs() {
		SeleniumUtil.switchToParentFrame(Frames.SCANNINGPATIENT);
		SeleniumUtil.getElementWithFluentWait(fileAsDropDown).click();
		SeleniumUtil.waitForProgressBar(Frames.SCANNINGPATIENT);
	}

	/**
	 * This method is used for verify the options are present when user clicks
	 * on the fileAs value menu drop down for the imported file from the
	 * scanning module present in queue management module from the application
	 * 
	 */
	public boolean verifyFileAsOptions() {
		boolean isFileAsOptionsArePresent = false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement value = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'Procedure note')]"));
		if (value != null) {
			System.out.println("options are displayed in fileAs drop down");
			value.click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
			isFileAsOptionsArePresent = true;
		}
		return isFileAsOptionsArePresent;
	}

	/**
	 * This method is used for clicking now link for the imported file from the
	 * scanning module present in queue management module from the application
	 * 
	 */
	public void clickOnNowLink() {
		SeleniumUtil.switchToParentFrame(Frames.SCANNINGPATIENT);
		SeleniumUtil.getElementWithFluentWait(nowLink).click();
		SeleniumUtil.waitForProgressBar(Frames.SCANNINGPATIENT);
	}

	/**
	 * This method is used for save the details for the imported file from the
	 * scanning module present in queue management module from the application
	 * 
	 * @throws Exception
	 */
	public void clickOnSave() {
		SeleniumUtil.switchToParentFrame(Frames.SCANNINGPATIENT);
		SeleniumUtil.getElementWithFluentWait(LoginPage.savebutton).click();
		SeleniumUtil.waitForProgressBar(Frames.SCANNINGPATIENT);

	}

	/**
	 * This method is used for verify the PDF is imported
	 * 
	 */
	public void verifyPDFIsImported() {
		sleep(15000);
		SeleniumUtil.switchToParentFrame(Frames.SCANNING);
		SeleniumUtil.waitForProgressBar(Frames.SCANNING);
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for (WebElement irows : totaltrrows) {
			String rowText = irows.getText();
			if (rowText.contains(ConstantsFile.Fulllocationname)) {
				System.out.println("pdf is imported");
				break;
			}
		}
	}

	/**
	 * This method is used for selecting the imported row which is in any
	 * pdf,image file format from the scanning module present in queue
	 * management module from the application
	 * 
	 */
	public void selectImportedRow() {
		SeleniumUtil.waitForProgressBar(Frames.SCANNING);
		sleep(15000);
		SeleniumUtil.switchToParentFrame(Frames.SCANNING);
		SeleniumUtil.waitForProgressBar(Frames.SCANNING);
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for (WebElement irows : totaltrrows) {
			irows.click();
			SeleniumUtil.waitForProgressBar(Frames.SCANNING);
			break;
		}
	}

	/**
	 * This method is used for for printing any pdf,image file from the scanning
	 * module present in queue management module from the application
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnPrintButton() throws FindFailed {
		sleep(3000);
		SeleniumUtil.switchToParentFrame(Frames.PRINT);
		SeleniumUtil.getElementWithFluentWait(printButton).click();
		// SeleniumUtil.waitForProgressBar(Frames.PRINT);
		sleep(10000);
		SeleniumUtil.clickOnImageWitScreenInSikuli("printbutton");
	}

	/**
	 * This method is used for click on Fax button
	 * 
	 */

	public void clickOnFaxButton() {
		sleep(10000);
		SeleniumUtil.switchToParentFrame(Frames.PRINT);
		SeleniumUtil.getElementWithFluentWait(faxButton).click();
		SeleniumUtil.waitForProgressBar(Frames.PRINT);
	}

	/**
	 * This method is used for whether fax window is open when user click on Fax
	 * button
	 * 
	 * 
	 */
	public boolean verifyFaxPopupWindow() {
		boolean isFaxpopuppresent = false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		WebElement faxtexbox = SeleniumUtil.getElementWithFluentWait(faxTextBox);
		if (faxtexbox != null) {
			System.out.println("Fax Pop up is opened");
			isFaxpopuppresent = true;
		}
		return isFaxpopuppresent;
	}

	/**
	 * This method is used for filling the required details for faxing any
	 * imported document
	 * 
	 */
	public void fillDetailsForFaxing() {
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(faxTextBox).sendKeys("test");
		SeleniumUtil.getElementWithFluentWait(faxnumberTextBox1).sendKeys("124");
		SeleniumUtil.getElementWithFluentWait(faxnumberTextBox2).sendKeys("567");
		SeleniumUtil.getElementWithFluentWait(faxnumberTextBox3).sendKeys("8693");
		SeleniumUtil.getElementWithFluentWait(faxButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}

	/**
	 * This method is used for clicking on Task for the imported file from the
	 * scanning module present in queue management module from the application
	 * 
	 */
	public void clickOnTask() {
		SeleniumUtil.switchToParentFrame(Frames.SCANNINGPATIENT);
		SeleniumUtil.getElementWithFluentWait(taskButton).click();
		SeleniumUtil.waitForProgressBar(Frames.SCANNINGPATIENT);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(selfLinkInTask).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}

	/**
	 * This method is used for verify the imported row which is in any pdf,image
	 * file format from the scanning module present in queue management module
	 * from the application
	 * 
	 */
	public boolean verifyImportedRow() {
		boolean isrowverified = false;
		SeleniumUtil.switchToParentFrame(Frames.SCANNING);
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		String datevalue = DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
		for (WebElement irows : totaltrrows) {
			String rowText = irows.getText();
			if (rowText.contains(ConstantsFile.patientfirstname) && rowText.contains("Procedure note") && rowText.contains(datevalue)) {
				System.out.println("All the correct details are present");
				isrowverified = true;
				break;
			}
		}
		return isrowverified;
	}

	/**
	 * This method is used for click on file button present in scanning module
	 * 
	 */
	public void clickOnFile() {
		SeleniumUtil.switchToParentFrame(Frames.SCANNINGPATIENT);
		SeleniumUtil.getElementWithFluentWait(fileButton).click();
		SeleniumUtil.waitForProgressBar(Frames.SCANNINGPATIENT);
	}

	/**
	 * This method is used for verify when user click on file button present in
	 * scanning module then it should disappear from the queue
	 * 
	 */
	public boolean verifyRow() {
		boolean isRowDisappear = false;
		SeleniumUtil.switchToParentFrame(Frames.SCANNING);
		String text = SeleniumUtil.getElementWithFluentWait(By.id("tblList_MessageBody")).getText();
		System.out.println(text);

		if ((text.contains("No matching document"))) {
			System.out.println("The document get disappear from the scanning queue");
			isRowDisappear = true;

		}

		return isRowDisappear;
	}

	/**
	 * This method is used for check file document should be present in
	 * processed folder in scanning module
	 * 
	 * @throws FindFailed
	 * 
	 */
	public void clickOnProcessed() throws FindFailed {
		SeleniumUtil.clickOnImageWitScreenInSikuli("processed");
	}

	/**
	 * This method is used for select patient in processed folder
	 * 
	 */
	public void selectPatientInProceeedFolder() {
		SeleniumUtil.switchToParentFrame(Frames.SCANNING);
		SeleniumUtil.getElementWithFluentWait(addpatient).click();
		SeleniumUtil.waitForProgressBar(Frames.SCANNING);
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(ConstantsFile.patientfirstname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(ConstantsFile.patientlastname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.searchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.PATIENT_SEARCHING);
		action.moveToElement(driver.findElement(AppointmentPage.patientrow));
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.PATIENT_SEARCHING);
	}

	/**
	 * This method is used for select the corresponding patient for the imported
	 * file from the scanning module present in queue management module from the
	 * application
	 * 
	 *
	 */
	public void selectPatient(String patientname) {
		click(ScanningPage.patientDropdown);
		switchToParentFrame(Frames.TOOLTIP);
		clickOnImage("clickOnYes1");
		sleep(5000);
		enterText(AppointmentPage.patientnametextbox, patientname);
		click(searchButton);
		sleep(3000);
		WebElement searchresult = SeleniumUtil.getElementWithFluentWait(MedicalChartPage.searchResult);
		SeleniumUtil.doubleClick(searchresult);
	}

	/**
	 * The method is used to Search on Scanned Page
	 */
	public void searchScannedRecords() {
		sleep(40000);
		switchToParentFrame(Frames.SCANNING);
		click(clearButton);
		click(searchButton);
		sleep(3000);
	}

	public void deleteOperations(String operation) {
		rightClick(ScanningPage.firstScannedDoc);
		clickOnImage("deleteItem");
		clickOnImage(operation);
		refresh();
		sleep(3000);

	}

	public void toContinue(LoginPage loginPageObj, LeftPanelPage leftPanelpageobj, String patientname) throws FindFailed {

		try {
			System.out.println("Before Search Search");
			click(searchButton);
			System.out.println("After Search");
			sleep(3000);
			
		} catch (Exception e) {
			logger.info("Logging into application");
			loginPageObj.loginToGmed();
			logger.info("Navigating to the Scanning Page");
			leftPanelpageobj.clickOnQueueManagement("clickOnScanning");
			logger.info("Searching the Scanned records in the Scanning Page");
			searchScannedRecords();
			logger.info("Selecting the First Scanned Records");
			selectImportedRow();
			SeleniumUtil.switchToParentFrame(Frames.SCANNINGPATIENT);
			logger.info("Unlinking previous patient if any and Linking the patient to the Scanned Records");
			selectPatient(patientname);
			clickOnSave();
			SeleniumUtil.switchToParentFrame(Frames.SCANNING);
		}

	}

	/**
	 * The method is used to click on History Icon available on Right click at scanned records
	 */
	public void clickHistoryOption() {
		rightClick(ScanningPage.firstScannedDoc);
		clickOnImage("clickOnHistory");
	}
	
	/** 
	 * The method is used to enter details in the Right Panel of Scanned records
	 * @return value is used for assertion.
	 */
	public String rhtpnlEnterDetails() {
		enterText(ScanningPage.rhtpnlDescription, "Desc");
		clickOnFileAs();
		switchToParentFrame(Frames.TOOLTIP);
		click(ScanningPage.fileAsProcedureNote);
		switchToParentFrame(Frames.SCANNINGPATIENT);
		clickOnNowLink();
		enterText(ScanningPage.rhtpnlChart, "Enter Chart Remarks");
		click(ScanningPage.rhtpnlLocationDropdown);
		switchToParentFrame(Frames.TOOLTIP);
		String str = getElementText(ScanningPage.firstLocationName);
		click(ScanningPage.firstLocationName);
		switchToParentFrame(Frames.SCANNINGPATIENT);
		clickOnSave();
		return str;
	}
	
	/**
	 * The Method is used to create/Configure the chart remark
	 * @param str
	 * @param str1
	 */
	public String createChartRemarks() {
		String str = ConstantsFile.genData.generateRandomString(8);
		click(ScanningPage.addChartRemarks);
		switchToParentFrame(Frames.TOOLTIP);
		click(ScanningPage.configureIcon);
		switchToParentFrame(Frames.TOOLTIP);
		click(ScanningPage.newButton);
		clickOnImage("clickOnRootItem");
		switchToParentFrame(Frames.CREATION);
		enterText(ScanningPage.textBox,str);
		enterText(ScanningPage.contentTextBox,str+str);
		click(ScanningPage.saveButton);
		return str;
	}

	/**
	 * The method is used to select the Configured Chart Remarks
	 * @param str
	 */
	public void selectConfiguredChartRemarks(String str) {
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(By.xpath("//span[text()='"+str+"']")));
	}
	
	/**
	 * The method is used to delete the configured Chart Remarks
	 * @param str
	 */
	public void deleteConfiguredChartRemarks(String str){
		click(ScanningPage.addChartRemarks);
		switchToParentFrame(Frames.TOOLTIP);
		click(ScanningPage.configureIcon);
		switchToParentFrame(Frames.TOOLTIP);
		rightClick(By.xpath("//span[text()='"+str+"']"));
		clickOnImage("deleteChart");
		clickOnImage("clickOnYes");
	}
	/**
	 * The method is used to select Service on the scanning document
	 * @return the string which is a service selected and later used for verification
	 */
	public String selectServiceOnScanning() {
		click(serviceDropDown);
		switchToParentFrame(Frames.TOOLTIP);
		String str = getElementText(firstServiceOnPopUp);
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(firstServiceOnPopUp));
		switchToParentFrame(Frames.SCANNINGPATIENT);
		clickOnSave();
		return str;
	}
	
	/**
	 * The method is used to select Procedure on the scanning document
	 * @return the string which is a Procedure selected and later used for verification
	 */
	public String selectProcedureOnScanning() {
		click(procedureDropDown);
		switchToParentFrame(Frames.TOOLTIP);
		String str = getElementText(firstProcedureOnPopUp);
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(firstProcedureOnPopUp));
		switchToParentFrame(Frames.CREATION);
		click(saveButton);
		switchToParentFrame(Frames.SCANNINGPATIENT);
		clickOnSave();
		return str;
	}
	
	/**
	 * The method is used to select DxStudy on the scanning document
	 * @return the string which is a DxStudy selected and later used for verification
	 */
	public String selectDxstudyOnScanning() {
		click(dxstudyDropDown);
		switchToParentFrame(Frames.TOOLTIP);
		String str = getElementText(firstDxStudyOnPopUp);
		SeleniumUtil.doubleClick(SeleniumUtil.getElementWithFluentWait(firstDxStudyOnPopUp));
		switchToParentFrame(Frames.SCANNINGPATIENT);
		clickOnSave();
		return str;
	}
	
	/**
	 * The method used to associated all the orders of the patient with the scanned doc
	 * @return is used to return count of orders selected and later used for verification
	 */
	public int selectOrdersOnScanning() {
	click(ScanningPage.addSignOnOrders);
	sleep(5000);
	switchToParentFrame(Frames.TOOLTIP);
	int count = getElementCount(ScanningPage.ordersCheckboxOnPopup);
	clickEachElement(ScanningPage.ordersCheckboxOnPopup);
	click(ScanningPage.selectOnOrdersPopup);
	switchToParentFrame(Frames.SCANNINGPATIENT);
	clickOnSave();
	return count;
	
	}
	
	/**
	 * The method used to associated all the Diagnoses of the patient with the scanned doc
	 * @return is used to return count of Diagnoses selected and later used for verification
	 */
	public int selectDiagonsesOnScanning() {
	click(ScanningPage.addSignOnDiagnoses);
	sleep(5000);
	switchToParentFrame(Frames.TOOLTIP);
	int count = getElementCount(ScanningPage.ordersCheckboxOnPopup);
	clickEachElement(ScanningPage.ordersCheckboxOnPopup);
	click(ScanningPage.selectOnOrdersPopup);
	switchToParentFrame(Frames.SCANNINGPATIENT);
	clickOnSave();
	return count;
	
	}

}
