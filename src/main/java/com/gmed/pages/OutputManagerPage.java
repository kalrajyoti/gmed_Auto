package com.gmed.pages;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;

import static com.gmed.helper.DriverFactory.driver;

import com.gmed.base.BaseAbstractPage;

import com.gmed.utils.ConstantsFile;
import com.gmed.utils.DateUtil;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;

public class OutputManagerPage extends BaseAbstractPage {
	/** Logger to log the OutputManagerPage log messages */
	private static Logger logger                           = LogManager.getLogger(OutputManagerPage.class);
	public static By mapUser                               = By.id("pnlUserTableAdd");
	public static By noOfCheckbox                          = By.xpath("//table[@id='tblServiceDocuments_Table']/tbody/tr[15]/descendant::img");
	public static By checkboxState                         = By.xpath("//table[@id='tblServiceDocuments_Table']/tbody/tr[15]/descendant::control");
	public static By checkboxState1                        = By.xpath("//table[@id='tblServiceDocuments_Table']/tbody/tr[15]/td/control");
	public static By sammaryTabTextValue                   = By.id("pnlSummary_Div");
	public static By chiefComplaintSection                 = By.id("txtChiefComplaint_Title");
	public static By providerSection                       = By.id("snpProvider_Title");
	public static By signatureProviderData                 = By.id("snpSignatureProvider_Data");
	public static By selectRecipient                       = By.id("btnSelectRecipients_SpanBGColor");
	public static By tasktotaltrtags                           =By.xpath(".//table[@id='tblTasksnpTask_Table']/tbody/tr");
	
   LeftPanelPage leftPanelObj=new LeftPanelPage();
	
   /**These are the variables which are used to store different data for Output Manager module*/
	public static String cleanupOperationText;
	public static String signOperationText;
	public static String printOperationText;
	public static String faxOperationText;
	public static String existingPatientfirstname;
	public static String existingPatientlastname;
	public static String publishToPortalOperationText;
	public static String documentText;
	public static String sendForSignatureOperationText;
	public static String sendForSignatureText;
	public static String sendForReviewOperationText;
	public static String sendForReviewText;
	
	/** These are the variables which are present on "Output Manager" sheet in the excel*/
	public static final String CLEAN_UP_TEXT 				                                  = "cleanupOperationText";
	public static final String SIGN_TEXT 				                                      = "signOperationText";
	public static final String PRINT_TEXT 				                                      = "printOperationText";
	public static final String FAX_TEXT 				                                      = "faxOperationText";
	public static final String PATIENT_FIRSTNAME 				                              = "patientfirstname";
	public static final String PATIENT_LASTNAME 				                              = "patientlastname";
	public static final String PUBLISH_TO_PORTAL_TEXT 				                          = "publishOperationText";
	public static final String DOCUMENT_TEXT 				                                  = "documentTextInPP";
	public static final String SEND_FOR_SIGNATURE_TEXT 				                          = "sendForSignatureOperationText";
	public static final String SIGNATURE_TEXT 				                                  = "signText";
	public static final String SEND_FOR_REVIEW_TEXT 				                          = "sendForReviewOperationText";
	public static final String REVIEW_TEXT 				                                      = "reviewText";
   
	/**contains the Output Manager page data*/
	public static Map<String, String> outputData;


	/**contains the demographics Chart page data*/
	public static Map<String, String> demographicsData;

	/**contains the Profile page data*/
	public static Map<String, String> profileData;
	public void initClass() throws Exception{
		logger.info("inside the initClass method for Prescription test class....");
		outputData                                                                     = ExcelFileUtilty.readExcelSheet("OutputManager");
		demographicsData                                                               = ExcelFileUtilty.readExcelSheet("Demographics");
		profileData                                                                    = ExcelFileUtilty.readExcelSheet("Profile");
		DemographicsPage.existingPatientfirstname                                      = demographicsData.get(DemographicsPage.PATIENT_FIRSTNAME);	
		DemographicsPage.existingPatientlastname                                       = demographicsData.get(DemographicsPage.PATIENT_LASTNAME);
		Profile.existingProfileProviderfirstname                                   = profileData.get(Profile.PROFILE_PROVIDER_FIRSTNAME);
		Profile.existingProfileProviderlastname                                    = profileData.get(Profile.PROFILE_PROVIDER_LASTNAME);
		Profile.userNameForAdvanceDirectiveProfile                                 = profileData.get(Profile.PROFILE_USER_NAME);
		cleanupOperationText                                                           = outputData.get(CLEAN_UP_TEXT) ; 
		signOperationText                                                              = outputData.get(SIGN_TEXT) ;
		printOperationText                                                             = outputData.get(PRINT_TEXT) ;
		faxOperationText                                                               = outputData.get(FAX_TEXT) ;
		existingPatientfirstname                                                       = outputData.get(PATIENT_FIRSTNAME);
		existingPatientlastname                                                        = outputData.get(PATIENT_LASTNAME);
		publishToPortalOperationText                                                   = outputData.get(PUBLISH_TO_PORTAL_TEXT);
		documentText                                                                   = outputData.get(DOCUMENT_TEXT);
		sendForSignatureOperationText                                                  = outputData.get(SEND_FOR_SIGNATURE_TEXT);
		sendForSignatureText                                                           = outputData.get(SIGNATURE_TEXT);
		sendForReviewOperationText                                                     = outputData.get(SEND_FOR_REVIEW_TEXT);
		sendForReviewText                                                              = outputData.get(REVIEW_TEXT);
   
   
	}
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
	public void clickOnProcedure(String procedurename) throws Exception{
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		SeleniumUtil.getElementWithFluentWait(Profile.newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERPAGE);
		SeleniumUtil.switchToParentFrame(Frames.POPUPLIST);
		WebElement FirstVisitvalue= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[starts-with(text(),'"+ procedurename + "')]"));
		SeleniumUtil.scrolltoWebElement(FirstVisitvalue);
		FirstVisitvalue.click();
		SeleniumUtil.waitForProgressBar(Frames.POPUPLIST);
		Thread.sleep(1000);
	}
	/**
	 * This method is used to click configure first visit setting in output manager 
	 * @throws FindFailed 
	 *
	 * 
	 */
	public void clickOnFirstVisitInService() throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		List<WebElement> totalrows = driver.findElements(By.xpath("//table[@id='tblServiceDocuments_Table']/tbody/tr"));
		System.out.println("table name size"+totalrows.get(14).getText());
		WebElement table = totalrows.get(14);
		List<WebElement> tdList = table.findElements(By.xpath(".//td/control"));
		System.out.println(tdList.size());
		int i=0;
		for(WebElement e4:tdList){ 
			SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
			System.out.println(e4.getAttribute("type"));
			String id1=e4.getAttribute("id");
			if(i<=1 ){
				System.out.println(e4.getText());
				System.out.println("id text"+id1);
				WebElement trrr =SeleniumUtil.getElementWithFluentWait(By.id(id1+"_Render"));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
				i++;
			}
			else if(i==2){
				WebElement trrr =SeleniumUtil.getElementWithFluentWait(By.id(id1));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
				SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
				WebElement activevalue=SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'My Local Printer')]"));
				activevalue.click();
				SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
				SeleniumUtil.getElementWithFluentWait(MedicalChartPage.selectbutton).click();
				SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
				SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
				i++;
			}
			else if(i==3){
				WebElement trrr =SeleniumUtil.getElementWithFluentWait(By.id(id1));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
				SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("selectProvider");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
			}
			else if(i==4){
				WebElement trrr =SeleniumUtil.getElementWithFluentWait(By.id(id1+"_Render"));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
				i++;
			}
			else if(i==5){	
				WebElement trrr =SeleniumUtil.getElementWithFluentWait(By.id("input_"+id1+"_Render"));
				trrr.click();
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
				i++;
			}
			else if(i==6){
				System.out.println("select button clicked 6 start");
				WebElement trrr =SeleniumUtil.getElementWithFluentWait(By.id("input_"+id1+"_Render"));
				trrr.click();	
				SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
			}
		}
	}
	/**
	 * This method is used to select user for configuring first visit setting in output manager 
	 * @throws FindFailed 
	 * 
	 */
	public void selectUSerForOutPutManager() throws Exception{
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		logger.info("Mapping the new user with created provider...");
		SeleniumUtil.getElementWithFluentWait(mapUser).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("search the logged in user by first name & last name");
		SeleniumUtil.getElementWithFluentWait(DocumentPage.patientnametextbox).sendKeys(ConstantsFile.userfirstname);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.patientnametextbox).sendKeys(Keys.SPACE);
		SeleniumUtil.getElementWithFluentWait(DocumentPage.patientnametextbox).sendKeys(ConstantsFile.usercompletelastname);
		logger.info("clicking on search button...");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.searchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		Thread.sleep(5000);
		logger.info("select the filtered row...");
		List<WebElement> checkbox = driver.findElements(By.className("checkboxSelection"));
		checkbox.get(1).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		logger.info("select the user to map with the created provider...");
		SeleniumUtil.getElementWithFluentWait(MedicalChartPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.USERCREATION);
		SeleniumUtil.getElementWithFluentWait(By.id("txtLocationsDropDown")).click();
		SeleniumUtil.waitForProgressBar(Frames.USERCREATION);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("select the mentioned location(Automation) in the excel");
		List<WebElement> locationlistvalue1 = driver.findElements(By.xpath(".//table[@id='tblList_Table']/tbody/tr"));
		System.out.println("The size of location are:"+locationlistvalue1.size());
		if(locationlistvalue1.size()>0){
			logger.info("selecting the automation location" +ConstantsFile.Fulllocationname);
			WebElement locationvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.Fulllocationname +"')]"));
			SeleniumUtil.scrolltoWebElement(locationvalues);
			locationvalues.click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		}
	}
	/**
	 * This method is used to switch into template frame
	 */
	public void switchToTemplateFrame(){
		DynamicFramePage.verifyDynamicFrameForInterview();
		SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
	}
	/**
	 * This method is used to switch into output manager Frame
	 */
	public void switchToOutputManagerFrame(){
		SeleniumUtil.switchToParentFrame(Frames.OUTPUT_CONFIGURATION);
		SeleniumUtil.waitForProgressBar(Frames.OUTPUT_CONFIGURATION);
	}
	/**
	 * This method is used to perform different operation from output manger
	 * @param documentName is template on which different operation should be performed
	 * @param methodName specify different operations
	 */
	public void selectTemplateDocument(String documentName,String methodName){
		boolean isOperationPerformed=false;
		List<WebElement> checkBoxs=driver.findElements(noOfCheckbox);
		System.out.println(checkBoxs.size());
		for(WebElement e1:checkBoxs){
			String rowText1=e1.getAttribute("id");
			System.out.println(rowText1);
			if(rowText1.contains(documentName) || methodName.equalsIgnoreCase("verifyFaxOperation")){
				List<WebElement> checkBoxStates=driver.findElements(checkboxState1);
				System.out.println(checkBoxStates.size());
				String rowText3;
				for(WebElement e3:checkBoxStates){
					 rowText3=e3.getAttribute("stateName");
					System.out.println(rowText3);
					
					
					if(rowText3 !=null &&  rowText3.equalsIgnoreCase("unchecked") && methodName.equalsIgnoreCase("verifyCleanUp")){
						SeleniumUtil.getElementWithFluentWait(By.id(rowText1)).click();
						isOperationPerformed=true;
						break;
						//SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton).click();			
					}
					else if(rowText3 !=null && rowText3.equalsIgnoreCase("unchecked") && methodName.equalsIgnoreCase("verifySignOperation")){
						SeleniumUtil.getElementWithFluentWait(By.id(rowText1)).click();
						isOperationPerformed=true;
						break;
					}
					else if( methodName.equalsIgnoreCase("verifyPrintOperation")){
						String rowText4=e3.getAttribute("id");
						System.out.println(rowText4);
						if(rowText4.startsWith("print")){
						SeleniumUtil.getElementWithFluentWait(By.id(rowText4)).click();
						SeleniumUtil.switchToParentFrame(Frames.MEDICATION_LIST);
						WebElement activevalue = SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'My Local Printer')]"));
						activevalue.click();
						SeleniumUtil.getElementWithFluentWait(MedicalChartPage.selectbutton).click();
						isOperationPerformed=true;
						break;
					}
					}
						else if( methodName.equalsIgnoreCase("verifyFaxOperation")){
							String rowText5=e3.getAttribute("id");
							System.out.println(rowText5);
							if(rowText5.startsWith("fax")){
							SeleniumUtil.getElementWithFluentWait(By.id(rowText5)).click();		
							isOperationPerformed=true;
							break;
						}
					}
						else if(rowText3 !=null && rowText3.equalsIgnoreCase("unchecked") && methodName.equalsIgnoreCase("verifyPublishToPortal")){
							SeleniumUtil.getElementWithFluentWait(By.id(rowText1)).click();
							isOperationPerformed=true;
							break;
						}
						else if(rowText3 !=null && rowText3.equalsIgnoreCase("unchecked") && (methodName.equalsIgnoreCase("verifySendForSignature") || methodName.equalsIgnoreCase("verifySendForReview"))){
							SeleniumUtil.getElementWithFluentWait(By.id(rowText1)).click();
							isOperationPerformed=true;
							break;
						}
				}
				
				
			}
			if(isOperationPerformed){
				break;
			}
			else{
				SeleniumUtil.switchToParentFrame(Frames.OUTPUT_CONFIGURATION);
				continue;
			}
		}
		
	}
	
	/**
	 * This method is used to verify correct Text is present when user perform any operation From output manager
	 * @return true if correct text is present
	 */
	public boolean verifySummaryTab(){
		boolean isCorrectTextPresennt=false;
		sleep(5000);
		String text= SeleniumUtil.getElementWithFluentWait(sammaryTabTextValue).getText();
		System.out.println(text);
		if(text.contains(OutputManagerPage.cleanupOperationText)){
			System.out.println("Clean up will Perform");
			SeleniumUtil.getElementWithFluentWait(DocumentPage.excuteButton).click();
			isCorrectTextPresennt=true;
		}
		else if(text.contains(OutputManagerPage.signOperationText)){
			System.out.println("Sign Operation will Perform");
			isCorrectTextPresennt=true;
		}
		else if(text.contains(OutputManagerPage.printOperationText)){
			System.out.println("Print Operation will Perform");
			SeleniumUtil.getElementWithFluentWait(DocumentPage.excuteButton).click();
			isCorrectTextPresennt=true;
		}
		else if(text.contains(OutputManagerPage.faxOperationText)){
			System.out.println("Print Operation will Perform");
			SeleniumUtil.getElementWithFluentWait(DocumentPage.excuteButton).click();
			isCorrectTextPresennt=true;
		}
		else if(text.contains(OutputManagerPage.publishToPortalOperationText)){
			System.out.println("Pulish to portal Operation will Perform");
			SeleniumUtil.getElementWithFluentWait(DocumentPage.excuteButton).click();
			isCorrectTextPresennt=true;
			
		}
		else if(text.contains(OutputManagerPage.sendForSignatureOperationText)){
			System.out.println("send For Signature Operation will Perform");
			SeleniumUtil.getElementWithFluentWait(DocumentPage.excuteButton).click();
			isCorrectTextPresennt=true;
			
		}
		else if(text.contains(OutputManagerPage.sendForReviewOperationText)){
			System.out.println("send For Review Operation will Perform");
			SeleniumUtil.getElementWithFluentWait(DocumentPage.excuteButton).click();
			isCorrectTextPresennt=true;
			
		}
		return isCorrectTextPresennt;
	}
	/**
	 * This method is  used to verify when user click on clean up operation from output manager then clean up operation should be performed
	 * @return true if value us undo cleanup
	 */
	public boolean verifyCleanUpOperation(){
		boolean isCleanUpOperationPerformed=false;
		List<WebElement> noOfMenus = driver.findElements(ReportPage.toolBarInMips);
		System.out.println("No Of ToolBar Menu is:" +noOfMenus.size());
		for(WebElement menuName:noOfMenus){
			String menuText=menuName.getText();
			String altText=menuName.getAttribute("alt");
			System.out.println("Tool Bar text is:"+menuText);
			System.out.println("Alt text is:"+altText);
			if(menuText.equalsIgnoreCase("Undo Cleanup")){
				System.out.println("Clean up Operation is performed");
				isCleanUpOperationPerformed=true;
				break;
			}
		
	}
		return isCleanUpOperationPerformed;
	
}
	/**
	 * This method is used to click on chief Complaint section present in first visit service
	 */

	public void clickOnChiefComplaint(){
		sleep(5000);
		WebElement row = SeleniumUtil.getElementWithFluentWait(chiefComplaintSection);
		SeleniumUtil.scrolltoWebElement(row);
		SeleniumUtil.doubleClick(row);
	}
	/**
	 * This method is used to click on Provider section present in first visit service
	 */
	public void clickOnProvider(){
		sleep(5000);
		WebElement row = SeleniumUtil.getElementWithFluentWait(providerSection);
		SeleniumUtil.scrolltoWebElement(row);
		row.click();
		
	}
	/**
	 * This method is used for adding provider details for first visit service
	 * @param ProviderFirstName
	 * @param ProviderLastName
	 */
	public void addProvider(String ProviderFirstName,String ProviderLastName){
		/* SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		 SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);*/
		    SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(ProviderFirstName);
		    SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(Keys.SPACE);
		    SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(ProviderLastName);
			SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchbutton).click();
			SeleniumUtil.elewait(MedicalChartPage.searchResult);
		    SeleniumUtil.getElementWithFluentWait(MedicalChartPage.searchResult).click();;
		   	SeleniumUtil.getElementWithFluentWait(AppointmentPage.selectbutton).click();
	}
	/**
	 * This method is used to verify document is electronically signed
	 * @return true if document is signed by provider
	 */
	public boolean verifyDocumentIsSigned(){
		boolean isDocumentSigned=false;
		String text =SeleniumUtil.getElementWithFluentWait(signatureProviderData).getText();
		System.out.println("Data is "+text);
		String currentDate=DateUtil.getCurrentDateInDateFormatted("M/d/yyyy");
		String signData=Profile.existingProfileProviderfirstname+" "+Profile.existingProfileProviderlastname+'\n'+" "+"Electronically signed on"+" "+currentDate;
		System.out.println("Sign Data is"+signData);
		if(text.contains(signData)){
			logger.info("Document is Electronically signed on by "+Profile.existingProfileProviderfirstname+" " +Profile.existingProfileProviderlastname);
			isDocumentSigned=true;
		}
		return isDocumentSigned;
	}
	/**
	 * This method is used to verify document is printed
	 * @return true if print operation is performed
	 */
	public boolean verifyPrintOperation(){
		boolean isDocumentPrinted=false;
		List<WebElement> totalrows=driver.findElements(AppointmentPage.totaltrtags);
		System.out.println(totalrows.size());
		for(WebElement printedRow:totalrows){
			String rowText=printedRow.getText();
			if(rowText.contains(Profile.userNameForAdvanceDirectiveProfile) && rowText.contains("Printed") && rowText.contains("Document: First Visit")){
				logger.info("document is printed");
				isDocumentPrinted=true;
				break;
			}
		}
		return isDocumentPrinted;
	}
	/**
	 * This method is used for selecting Provider for faxing
	 * @param providerFirstName
	 * @param providerLastName
	 */
	public void selectProviderForFaxOperation(String providerFirstName,String providerLastName){
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		leftPanelObj.switchToDifferentTab(" Provider ");
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		driver.switchTo().frame("fraProvider_Frame");
		addProvider(providerFirstName,providerLastName);
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		SeleniumUtil.getElementWithFluentWait(selectRecipient).click();
	}
	public void switchToProviderFrame(){
		SeleniumUtil.switchToParentFrame(Frames.CREATION);
		SeleniumUtil.waitForProgressBar(Frames.CREATION);
	
	}
	public void selectProvider(){
		List <WebElement>providersvalues =driver.findElements(AppointmentPage.totaltrtags);
		System.out.println(providersvalues.size());
		for(WebElement membersrow:providersvalues){
			String rowText=membersrow.getText();
			if(rowText.contains(Profile.existingProfileProviderfirstname) && (rowText.contains(Profile.existingProfileProviderlastname))){
				SeleniumUtil.doubleClick(membersrow);
				sleep(5000);
				break;
			
		}
			
	}
	
}
	public boolean verifySignatureOperation(){
		boolean isDocumentSendForSignature=false;
		SeleniumUtil.switchToParentFrame(Frames.HOME);
		List <WebElement>taskvalues =driver.findElements(tasktotaltrtags);
		System.out.println(taskvalues.size());
		for(WebElement membersrow:taskvalues){
			String rowText=membersrow.getText();
			System.out.println(rowText);
			String currentDate=DateUtil.getCurrentDateInDateFormatted("MM/dd");
			System.out.println(currentDate);
			
			if(rowText.contains(currentDate) && rowText.contains(OutputManagerPage.sendForSignatureText) && (rowText.contains(DemographicsPage.existingPatientfirstname)) && (rowText.contains(DemographicsPage.existingPatientlastname)) ){
				System.out.println("correct row presennt");
				isDocumentSendForSignature=true;
				break;
			
		}
			else if(rowText.contains(currentDate) && rowText.contains(OutputManagerPage.sendForReviewText) && (rowText.contains(DemographicsPage.existingPatientfirstname)) && (rowText.contains(DemographicsPage.existingPatientlastname)) ){
				System.out.println("correct row presennt");
				isDocumentSendForSignature=true;
				break;
			}
			
	}
		return isDocumentSendForSignature;
		
	}
}
