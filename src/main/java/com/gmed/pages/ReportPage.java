package com.gmed.pages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.test.CQMReport;
import com.gmed.test.DemographicsTest;
import com.gmed.test.MIPSReport;
import com.gmed.test.TaskTest;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.DateUtil;
import com.gmed.utils.SeleniumUtil;

public class ReportPage extends BaseAbstractPage {
	/** Logger to log the ReportPage log messages */
	private static Logger logger                        = LogManager.getLogger(ReportPage.class);
	public static By reportTitle                               = By.id("tdHeaderText");
	public static By fromAgeTextBox                            = By.id("itbAgeFrom_IntegerTextBox");
	public static By toAgeTextBox                              = By.id("itbAgeTo_IntegerTextBox");
	public static By runReportButton                           = By.id("btnSave_SpanBGColor");
	public static By reportcontent                             = By.xpath("//div[@id='VisibleReportContentrpvViewer_ctl10']/div/table/tbody/tr");
	public static By runReportMenuInPatientByAge               = By.id("btnSave");
	public static By saveAsMenuInPatientByAge                  = By.id("btnSaveAs");
	public static By checkboxInPatientByAge                    = By.xpath("//control[@type='CheckBox']"); 
	public static By textboxInPatientByAge                     = By.id("rpvViewer_ctl06_ctl03_ctl00"); 
	public static By nextButtonInPatientByAge                  = By.id("rpvViewer_ctl06_ctl03_ctl01"); 
	public static By exportMenuInPatientByAge                  = By.xpath("//div[@id='rpvViewer_ctl06_ctl04_ctl00_Menu']/div/a"); 
	public static By ForwardButtonInPatientByAge               = By.id("rpvViewer_ctl06_ctl00_Next_ctl00_ctl00");
	public static By BackButtonInPatientByAge                  = By.id("rpvViewer_ctl06_ctl00_Previous_ctl00_ctl00"); 
	public static By exportDropDownInPatientByAge              = By.id("rpvViewer_ctl06_ctl04_ctl00_ButtonLink"); 
	public static By expandCollapseInPatientByAge              = By.id("btnHideParams_SpanBGColor"); 
	public static By zipCodeInZipAddressReport                 = By.id("txtCollection_TextBox"); 
	public static By runReportInMU                             = By.id("btnRunReport_SpanBGColor");
	public static By providerInMU                              = By.id("lblProvider");
	public static By reportingYearInMU                         = By.id("lblYear");
	public static By attestationInMU                           = By.id("lblAttestation");
	public static By dateRangeInMU                             = By.id("lblDateRange");
	public static By rememberSearchInMU                        = By.id("chkRememberSearch_LabelSpan");
	public static By reportFromMonth                           = By.id("dtbFrom_Month");
	public static By reportFromDay                             = By.id("dtbFrom_Day");
	public static By reportFromYear                            = By.id("dtbFrom_Year");
	public static By reportToMonth                             = By.id("dtbTo_Month");
	public static By reportToDay                               = By.id("dtbTo_Day");
	public static By reportToYear                              = By.id("dtbTo_Year");
	public static By destinationDropDown                       = By.id("txtDestinationDropDown");
	public static By folderTextBox                             = By.id("txtFolder_TextBox");
	public static By providerButton                            = By.id("tblProviderCollectionAdd");
	public static By runCQM                                    = By.id("btnRun_SpanBGColor");
	public static By newButtonForMips                          = By.id("btnReportsNew_SpanBGColor");
	public static By mipsReportingYear                         = By.id("ddlYear_Text");
	public static By mipsRecipient                             = By.id("tblRecipientCollectionAdd");
	public static By toolBarInMips                             = By.xpath(".//control[@type='ToolbarButton']"); 
	public static By mipsTabs                                  = By.xpath(".//control[@type='TabPage']"); 
	public static By measureCheckboxs                          = By.xpath(".//img[@class='CheckBox']"); 
	public static By refreshButton                             = By.className("MipsRefresh"); 
	public static By messageContent                            = By.id("tblList_div");
	//--
	public static By trtags                                    = By.xpath("//table[@id='tblList_TableRoot']/tbody/tr/td/div[@id='tblList_div']/table[@id='tblList_Table']/tbody/tr/td[1]");
	public static By chartAccessTextInReport                   = By.xpath("//table[@lang='en-US']/tbody/tr/td[text()='Chart Access Audit Report']");
	Profile profilepageobj =new Profile();
	/**
	 * This method is used for click Patient By Age Report present in Reports module from the application
	 * 
	 */
	public void clickOnPatientByAgeReport(String reportname){
		SeleniumUtil.switchToParentFrame(Frames.REPORT);
		SeleniumUtil.waitForProgressBar(Frames.REPORT);
		List<WebElement>  trtags =driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:trtags){
			String rowText=irows.getText();
			if(rowText.contains(reportname)){
				action.moveToElement(irows).doubleClick().build().perform();
				break;
			}
		}
	}
	/**
	 * This method is used for verify  Reports details
	 * 
	 */
	public boolean verifyReportPageIsDisplayed(String reportTitleName){
		boolean isReportTitlePresent=false;
		sleep(3000);
		SeleniumUtil.switchToParentFrame(Frames.REPORTINSIDE);
		SeleniumUtil.waitForProgressBar(Frames.REPORTINSIDE);
		String reporttitletext =SeleniumUtil.getElementWithFluentWait(reportTitle).getText();
		if(reporttitletext.equalsIgnoreCase(reportTitleName)){
			System.out.println("The title of the Report is displayed");
			isReportTitlePresent=true;
		}
		return isReportTitlePresent;
	}
	/**
	 * This method is used for enter valid Age to generate patient by Age Report
	 * 
	 */
	public void enterValidAgeInReport(){
		SeleniumUtil.switchToParentFrame(Frames.REPORTINSIDE);
		SeleniumUtil.getElementWithFluentWait(fromAgeTextBox).sendKeys(DemographicsTest.existingPatientFromAge);
		SeleniumUtil.getElementWithFluentWait(toAgeTextBox).sendKeys(DemographicsTest.existingPatientToAge);
		SeleniumUtil.getElementWithFluentWait(runReportButton).click();
		SeleniumUtil.waitForProgressBar(Frames.REPORTINSIDE);
	}
	/**
	 * This method is used for verify the valid data is generated form patient by Age Report
	 * 
	 */
	public boolean verifyRecords(String patientFirstName,String patientLastname){
		boolean isDataPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.REPORTGENERATION);
		List <WebElement> reporttext = driver.findElements(reportcontent);
		for(WebElement irows:reporttext){
			String patientname=irows.getText();
			if(patientname.contains(patientFirstName) && patientname.contains(patientLastname)){
				System.out.println("correct patient row is displayed");
				isDataPresent=true;
			}
		}
		/*for(WebElement irows:reporttext){
			if(reporttext.isEmpty()){
				System.out.println("No data is present");
				isDataPresent=false;
			}
			else{
				System.out.println("Data is Generated");
				isDataPresent=true;
			}
		}*/
		return isDataPresent;
	}
	/**
	 * This method is used for verify the top menu icon i.e save as ,run report & print icon are present in patient by Age Report
	 * 
	 */
	public boolean verifyTopMenuItemsInPatientAgeReport(){
		boolean isMenusPresent=false;
		String runRportValue =SeleniumUtil.getElementWithFluentWait(runReportMenuInPatientByAge).getText();
		String saveRportValue =SeleniumUtil.getElementWithFluentWait(saveAsMenuInPatientByAge).getText();
		if(runRportValue.equalsIgnoreCase("Run Report") && saveRportValue.equalsIgnoreCase("Save As")){
			System.out.println("Run Report && Save As button are present on top of page");
			isMenusPresent=true;
		}
		return isMenusPresent;

	}
	/**
	 * This method is used for verify the all the checkbox i.e patient name ,DOB should be chec are present in patient by Age Report
	 * 
	 */
	public boolean verifyDefaultCheckboxValueInPatientAgeReport(){
		boolean isCheckBoxSelectedByDefault=false;
		List<WebElement> checkboxes=driver.findElements(checkboxInPatientByAge);
		System.out.println(checkboxes.size());
		for(WebElement checkboxstate:checkboxes){
			String stateValue =checkboxstate.getAttribute("stateName");{
				if(stateValue.equalsIgnoreCase("checked")){
					System.out.println("all the checkbox are selected by default");
					isCheckBoxSelectedByDefault=true;
				}
			}
		}
		return isCheckBoxSelectedByDefault;

	}
	/**
	 * This method is used for click on forward & backword for opening the  data in patient by Age Report
	 * 
	 */
	public void clickOnNextAndBackButtonInPatientAgeReport(){
		SeleniumUtil.switchToParentFrame(Frames.REPORTGENERATION);
		try{
			SeleniumUtil.getElementWithFluentWait(ForwardButtonInPatientByAge).click();
			sleep(5000);
			SeleniumUtil.getElementWithFluentWait(BackButtonInPatientByAge).click();}
		catch(Exception e){
			System.out.println("No More Page in Report");
		}
	}
	/**
	 * This method is used for type any text in the report text box  in patient by Age Report
	 * 
	 */
	public void findTextInPatientAgeReport(String patientName){
		SeleniumUtil.switchToParentFrame(Frames.REPORTGENERATION);
		SeleniumUtil.waitForProgressBar(Frames.REPORTGENERATION);
		SeleniumUtil.getElementWithFluentWait(textboxInPatientByAge).sendKeys(patientName);
	}
	/**
	 * This method is used for verify Text is highlighted in patient by Age Report
	 * @throws FindFailed 
	 * 
	 */
	public boolean verifyHighlightedTextInPatientAgeReport() throws FindFailed{
		sleep(3000);
		SeleniumUtil.switchToParentFrame(Frames.REPORTGENERATION);
		boolean isColorMatched=false;
		try{
			SeleniumUtil.getElementWithFluentWait(nextButtonInPatientByAge).click();
			List<WebElement> headerColors=driver.findElements(reportcontent);
			System.out.println(headerColors.size());
			for(WebElement irows:headerColors){
				String rowText=irows.getCssValue("COLOR");
				System.out.println(rowText);

				if(rowText.contains("#000000")){
					System.out.println("Color Mathes");
					isColorMatched=true;
				}
				else
				{
					System.out.println("Color notM athes");
				}
			}
		}
		catch(Exception e){
			System.out.println("No Text is present");
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnOk.png");
			isColorMatched=false;
		}
		return isColorMatched;
	}
	/**
	 * This method is used for verify the Menus Items are present in patient by Age Report
	 * 
	 */
	public void clickOnExportButton(){
		SeleniumUtil.switchToParentFrame(Frames.REPORTGENERATION);
		SeleniumUtil.waitForProgressBar(Frames.REPORTGENERATION);
		SeleniumUtil.getElementWithFluentWait(exportDropDownInPatientByAge).click();
		List<WebElement> exportMenuItems=driver.findElements(exportMenuInPatientByAge);
		System.out.println(exportMenuItems.size());
		for(WebElement irows:exportMenuItems){
			String rowText=irows.getText();
			System.out.println(rowText);
			if(rowText.contains("CSV")){
				System.out.println("all the menu present");
				//irows.click();
				//GpinUserLogin.saveCSV();
			}
		}			
	}
	/**
	 * This method is used for clicking on Collspe & Expand Button  are present in patient by Age Report
	 * 
	 */
	public void clickOnCollspedAndExpandButton(){
		SeleniumUtil.switchToParentFrame(Frames.REPORTINSIDE);
		SeleniumUtil.getElementWithFluentWait(expandCollapseInPatientByAge).click();
		SeleniumUtil.getElementWithFluentWait(expandCollapseInPatientByAge).click();
	}
	/**
	 * This method is used for enter valid zip address to generate in patient by zip address report
	 * 
	 */
	public void enterValidZipCodeInReport(){
		SeleniumUtil.switchToParentFrame(Frames.REPORTINSIDE);
		SeleniumUtil.getElementWithFluentWait(zipCodeInZipAddressReport).sendKeys(DemographicsTest.existingPatientzipaddress);
		SeleniumUtil.getElementWithFluentWait(runReportButton).click();
		SeleniumUtil.waitForProgressBar(Frames.REPORTINSIDE);
	}

	/**
	 * This method is used Click on + icon to add patient for which report is to be pulled
	 * 
	 */
	public void clickOnPatientAddIcon() {
		SeleniumUtil.switchToParentFrame(Frames.REPORTINSIDE);
		SeleniumUtil.getElementWithFluentWait(By.id("txtPatientSimpleAdd")).click();
	}
	/**
	 * This method is used Enter Patient and Run Report
	 * 
	 */
	public void searchPatientForReportRun() {
		SeleniumUtil.switchToParentFrame(Frames.PATIENT_SEARCHING);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox ).sendKeys(ConstantsFile.patientchartpatientname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.searchbutton).click();
		SeleniumUtil.elewait(trtags);
		WebElement searchresult = SeleniumUtil.getElementWithFluentWait(trtags);
		SeleniumUtil.doubleClick(searchresult);
		SeleniumUtil.switchToParentFrame(Frames.REPORTINSIDE);
		SeleniumUtil.getElementWithFluentWait(runReportButton).click();
		SeleniumUtil.waitForProgressBar(Frames.REPORTINSIDE);
	}

	/**
	 * This method is used to verify Chart Access Audit Report is opened 
	 * 
	 */
	public boolean verifyChartAccessAuditReport() {
		SeleniumUtil.switchToParentFrame(Frames.REPORTGENERATION);
		if(SeleniumUtil.getElementWithFluentWait(chartAccessTextInReport).getText().equalsIgnoreCase("Chart Access Audit Report")){
			return true;
		}
		else {
			return false;
		}

	}
	/**
	 * This Method is used to switch in MU Frame
	 */
	public void switchToMuFrame(){
		SeleniumUtil.switchToParentFrame(Frames.MUREPORT);
		SeleniumUtil.waitForProgressBar(Frames.MUREPORT);
	}


	/**
	 * This method is used for verify MU Reports Title
	 * 
	 */
	public boolean verifyReportScreen(String reportName){
		boolean isReportTitlePresent=false;
		sleep(3000);	
		String reporttitletext =SeleniumUtil.getElementWithFluentWait(reportTitle).getText();
		if(reporttitletext.equalsIgnoreCase(reportName)){
			System.out.println("The title of the Report is displayed");
			isReportTitlePresent=true;
		}
		return isReportTitlePresent;
	}
	/**
	 * This method is used for verify all the fields in  MU Reports 
	 * 
	 */
	public boolean verifyMUReportFields(){
		boolean isAllFieldPresent=false;
		String runReportButton =SeleniumUtil.getElementWithFluentWait(runReportInMU).getText();
		String providerText    =SeleniumUtil.getElementWithFluentWait(providerInMU).getText();
		String reportYearText  =SeleniumUtil.getElementWithFluentWait(reportingYearInMU).getText();
		String attestationText =SeleniumUtil.getElementWithFluentWait(attestationInMU).getText();
		String dateRangeText   =SeleniumUtil.getElementWithFluentWait(dateRangeInMU).getText();
		String SearchText      =SeleniumUtil.getElementWithFluentWait(rememberSearchInMU).getText();
		if(runReportButton.equalsIgnoreCase("Run Report") && providerText.equalsIgnoreCase("Provider")  && reportYearText.equalsIgnoreCase("Reporting Period Year") && attestationText.equalsIgnoreCase("Attestation")  && dateRangeText.equalsIgnoreCase("Date Range") && SearchText.equalsIgnoreCase("Remember Search")){
			logger.info("all the fields are present in MU Report");
			isAllFieldPresent=true;
		}
		return isAllFieldPresent;	
	}
	/**
	 * This method is used for entering MU data for generating the MU Data
	 * @param providerfirstname will be name for generation MU data
	 * @param providerlastname  will be name for generation MU data
	 */
	public void enterMUDataForReport(String providerfirstname,String providerlastname){
		logger.info("Selecting provider for generating MU data");
		selectProvider(providerfirstname,providerlastname);
		SeleniumUtil.switchToParentFrame(Frames.MUREPORT);
		SeleniumUtil.waitForProgressBar(Frames.MUREPORT);
		logger.info("Selecting Reporting year for generating MU data");
		selectReportingYear();
		SeleniumUtil.getElementWithFluentWait(runReportInMU).click();
		sleep(10000);
		SeleniumUtil.waitForProgressBar(Frames.MUREPORT);
	}
	/**
	 * This method is used for selecting provider
	 * @param providerfirstname
	 * @param providerlastname
	 */
	public void selectProvider(String providerfirstname ,String providerlastname){
		SeleniumUtil.getElementWithFluentWait(Profile.providerDropDownInProfile).click();
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		searchProvider(providerfirstname,providerlastname);

	}
	/**
	 * This method is used for searching the specific provider in the search text box
	 * @param providerfirstname
	 * @param providerlastname
	 */
	public  void searchProvider(String providerfirstname,String providerlastname){
		boolean isProviderPresent=false;
		logger.info("Searching the provider with first name & last name..");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(providerfirstname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(providerlastname);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);

		logger.info("Finding the no of provider Rows in search result");
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
				if(rowText.contains(providerlastname)){
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
	/**
	 * This method is used for selecting the reporting year for MU data
	 */
	public static void  selectReportingYear(){
		SeleniumUtil.removeReadOnly("ddlYear_Text", "2016");  
	}
	/**
	 * This Method is used to verify MU data with all the Measures
	 * @return true if all the measures are present
	 */
	public String verifyMUData(){
		SeleniumUtil.switchToParentFrame(Frames.MUREPORTINSIDE);
		SeleniumUtil.waitForProgressBar(Frames.MUREPORTINSIDE);
		String isMuDataPresent=null;
		isMuDataPresent=	SeleniumUtil.getElementWithFluentWait(DocumentPage.allvisibletext).getText();
		System.out.println("Mu Content is:"+isMuDataPresent);
		return isMuDataPresent;
	}

	/**
	 * This Method is used to switch in cqm Frame
	 */
	public void switchToCQMFrame(){
		SeleniumUtil.switchToParentFrame(Frames.CQMREPORT);
		SeleniumUtil.waitForProgressBar(Frames.CQMREPORT);
	}
	/**
	 * This method is used to select Date Range From & To i.e 01/01/2015 to 31/12/2016
	 * 
	 */
	public void selectReportYear(){
		logger.info("select date range From ..");
		SeleniumUtil.getElementWithFluentWait(reportFromMonth).sendKeys("01");
		SeleniumUtil.getElementWithFluentWait(reportFromDay).sendKeys("01");
		SeleniumUtil.getElementWithFluentWait(reportFromYear).sendKeys("2016");

		logger.info("select date range To ..");
		SeleniumUtil.getElementWithFluentWait(reportToMonth).sendKeys("12");
		SeleniumUtil.getElementWithFluentWait(reportToDay).sendKeys("31");
		SeleniumUtil.getElementWithFluentWait(reportToYear).sendKeys("2016");
	}
	/**
	 * This method is used for 
	 */
	public void selectDestination(){
		logger.info("clicking on destination drop down..");
		WebElement sendButton = SeleniumUtil.getElementWithFluentWait(destinationDropDown);
		action.moveToElement(sendButton).click().build().perform();

		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);

		logger.info("searching the destination folder");
		SeleniumUtil.getElementWithFluentWait(folderTextBox).clear();
		SeleniumUtil.getElementWithFluentWait(folderTextBox).sendKeys(CQMReport.destinationFolder);

		logger.info("selecting the folder...");
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.selectbutton).click();

	}
	/**
	 * This method is used for adding provider for report
	 * @param providerfirstname
	 * @param providerlastname
	 */
	public void addProviderForReport(String providerfirstname,String providerlastname){
		logger.info("clicking on Provider Plus Button..");
		SeleniumUtil.getElementWithFluentWait(providerButton).click();

		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);

		logger.info("searching the provider for report...");
		searchProvider(providerfirstname,providerlastname);
	}
	/**
	 * This method is used for Selecting all Measures for CQM Report
	 */
	public void selectMeasures(){
		logger.info("Selecting all Measures for CQM Report...");
		profilepageobj.selectCheckbox();
	}
	/**
	 * This Method is used for adding Data Range ,Destination Folder ,Provider & Measure for CQM Report
	 * @param providerfirstname
	 * @param providerlastname
	 */
	public void addReportDetails(String providerfirstname,String providerlastname){
		switchToCQMFrame();
		logger.info("select date range From & To ..");
		selectReportYear();

		logger.info("selecting the destination folder");
		selectDestination();
		switchToCQMFrame();

		logger.info("selecting the Provider for Report");
		addProviderForReport(providerfirstname,providerlastname);
		switchToCQMFrame();

		logger.info("Selecting all Measures for CQM Report...");
		selectMeasures();

		logger.info("running the CQM Report...");
		SeleniumUtil.getElementWithFluentWait(runCQM).click();
	}
	/**
	 * This Method is used to verify CQM data with all the Measures
	 * @return true if all the measures are present
	 */
	public String verifyCQMData(){
		SeleniumUtil.switchToParentFrame(Frames.CQMREPORTINSIDE);
		SeleniumUtil.waitForProgressBar(Frames.CQMREPORTINSIDE);
		String isCQMDataPresent=null;
		isCQMDataPresent=	SeleniumUtil.getElementWithFluentWait(DocumentPage.allvisibletext).getText();
		System.out.println("CQM Content is:"+isCQMDataPresent);
		return isCQMDataPresent;
	}
	/**
	 * This Method is used to switch in MU Frame
	 */
	public void switchToMipsFrame(){
		SeleniumUtil.switchToParentFrame(Frames.MIPSREPORT);
		SeleniumUtil.waitForProgressBar(Frames.MIPSREPORT);
	}
	/**
	 * This method is used for clicking on various Tool bar present in Mips report
	 */
	public void clickOnMipsToolBar(String toolBarName){
		switchToMipsFrame();
		sleep(5000);
		List<WebElement> noOfMenus = driver.findElements(toolBarInMips);
		System.out.println("No Of ToolBar Menu is:" +noOfMenus);
		for(WebElement menuName:noOfMenus){
			String menuText=menuName.getText();
			System.out.println("Tab text is:"+menuText);
			if(menuText.equalsIgnoreCase(toolBarName)){
				menuName.click();
				break;
			}
		}

	}
	/**
	 * This method is used for giving random name for the mips report
	 */
	public void addReportName(){
		ConstantsFile.mipsReportName = MIPSReport.mipsReportName.concat(ConstantsFile.genData.generateRandomNumber(5));
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientnametextbox).sendKeys(ConstantsFile.mipsReportName);
	}
	/**
	 * This method is used to verify the reporting year should be current year.
	 * @return true if it's current year
	 */
	public boolean verifyReportingYear(){
		switchToMipsFrame();
		boolean isYearPresent=false;
		String currentYear= SeleniumUtil.getElementWithFluentWait(mipsReportingYear).getAttribute("value");
		int yearValue=DateUtil.getCurrentYear();	
		String currentYearForMips=Integer.toString(yearValue);
		System.out.println("current year is:"+Integer.toString(yearValue));
		if(currentYear.equalsIgnoreCase(currentYearForMips)){
			logger.info("current Year is selected..");
			isYearPresent=true;
		}
		return isYearPresent;
	}
	/**
	 * This method is used to verify message recipient should be added for MIPS report
	 */
	public void addMessageRecipient(){
		boolean isRecipientPresent=false;
		SeleniumUtil.getElementWithFluentWait(mipsRecipient).click();
		logger.info("searching  recipients...");
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(TaskPage.recipientTextBox).sendKeys(MIPSReport.messageRecipientsName);
		SeleniumUtil.getElementWithFluentWait(TaskPage.recipientSearchIcon).click();
		sleep(5000);

		List<WebElement> noOfRecipientsrow = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println("NUMBER OF RECIPIENTS IN THIS TABLE = " + noOfRecipientsrow.size());
		int row_num, col_num;
		row_num = 1;
		for (WebElement trElement : noOfRecipientsrow) {
			List<WebElement> td_collection = trElement.findElements(By.xpath("td"));
			System.out.println("NUMBER OF COLUMNS=" + td_collection.size());
			col_num = 1;
			for (WebElement tdElement : td_collection) {
				String rowText = tdElement.getText();
				System.out.println("row # " + row_num + ", col # " + col_num + "text=" + tdElement.getText());
				col_num++;
				if (rowText.equalsIgnoreCase(MIPSReport.messageRecipientsName)) {
					System.out.println("corrected user is selected");
					SeleniumUtil.doubleClick(tdElement);
					isRecipientPresent=true;
					break;
				}
			}
			if(isRecipientPresent){
				break;
			}
			else{
				row_num++;
			}
		}
	}

	/**
	 * This method is used for clicking on different MIPS Tabs
	 * @param tabName are Summary,Quality,ACI & IA tabs present in Mips
	 */
	public void clickOnMipsTab(String tabName){
		switchToMipsFrame();
		List<WebElement> noOfTabs = driver.findElements(mipsTabs);
		System.out.println("No Of Tabs are:" +noOfTabs);
		for(WebElement tab:noOfTabs){
			String tabText=tab.getText();
			System.out.println("Tab text is:"+tabText);
			if(tabText.equalsIgnoreCase(tabName)){
				tab.click();
				break;
			}
		}

	}
	/**
	 * This method is used to switch in Quality Frame
	 */
	public void switchInQualityFrame(){
		SeleniumUtil.switchToParentFrame(Frames.QUALITY);
		SeleniumUtil.waitForProgressBar(Frames.QUALITY);
	}
	/**
	 * This method is used to switch in ACI Frame
	 */
	public void switchInACIFrame(){
		SeleniumUtil.switchToParentFrame(Frames.ACI);
		SeleniumUtil.waitForProgressBar(Frames.ACI);
	}
	/**
	 * This method is used to switch in ACI Measure Frame
	 */
	public void switchIntoACIMeasureFrame(){
		switchInACIFrame();
		SeleniumUtil.switchToFrame(driver,"panViewer_Frame");
	}
	/**
	 * This method is used for select Measures in form of check box in ACI & IA Tab
	 */
	public void selectCheckBoxMeasures(){
		logger.info("selecting all checkbox buttons");
		List<WebElement> els1 = driver.findElements(measureCheckboxs);
		int totalsize =els1.size();
		int i=0;
		logger.info("size of checkbox are"+totalsize);
		for(WebElement e1:els1){
			if(totalsize<=4){
				action.sendKeys(e1,Keys.SPACE).doubleClick().build().perform();			
			}
			else if(totalsize>=105 && i<=5){
				els1.get(i).click();
				i++;
			}
		}
	}
	/**
	 * This method is used for clicking on Refresh button 
	 */
	public void clickOnRefresh(){
		sleep(6000);
		List<WebElement> els1 = driver.findElements(refreshButton);
		System.out.println("the size of refresh button");
		for(WebElement e1:els1){
			String rowText=e1.getText();
			if(rowText.contains("Click here to refresh")){
				e1.click();
				break;
			}

		}
		sleep(6000);
	}
	/**
	 * This method is used to verify Automatic message is generated in Message queue
	 * @return true if message is generated
	 */
	public boolean verifyAutoMaticMipsMessage(){
		SeleniumUtil.switchToParentFrame(Frames.MESSAGE);
		SeleniumUtil.waitForProgressBar(Frames.MESSAGE);
		boolean isMessageReceived=false;
		String messageSubject=SeleniumUtil.getElementWithFluentWait(messageContent).getText();
		System.out.println("Message Subject Is"+messageSubject);
		if(messageSubject.contains(ConstantsFile.mipsReportName)){
			System.out.println("Message should be same as received result ");
			isMessageReceived=true;
		}
		return isMessageReceived;
	}
	public void enterMedicationName(String medicationName){
		SeleniumUtil.getElementWithFluentWait(By.id("txtAdministeredMedication_TextBox")).sendKeys(medicationName);
		SeleniumUtil.getElementWithFluentWait(runReportButton).click();
		SeleniumUtil.waitForProgressBar(Frames.REPORTINSIDE);
	}
}
