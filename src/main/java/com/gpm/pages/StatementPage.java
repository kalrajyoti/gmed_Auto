package com.gpm.pages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.pages.AppointmentPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;

import static com.gmed.helper.DriverFactory.driver;

public class StatementPage extends BaseAbstractPage{
	/** Logger to log the StatementPage log messages */
	private static Logger logger                   = LogManager.getLogger(StatementPage.class);
	public static By search                                         = By.id("btnFiltersSearch_Table");
	public static By closeStatementButton                           = By.id("Popup_CloseButton");
	public static By statementData                                  = By.id("rpvViewer_ctl10");
	public static By outboxTab                                      = By.id("tabOutbox");
     SuperBillPage superbillobj = new SuperBillPage();
	
	/**
	 * This method is used to check that Prepay is visible & accessible  present in billing screen
	 * @return true if link is visible & accessible
	 */
	public boolean verifyStatementLink(){
		boolean isStatementTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.STATEMENTS);
		String tiitle = SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Manage Statements")){
			System.out.println("Manage Statements is open & it's accessible");
			isStatementTitle=true;
		}
		return isStatementTitle;
	}
	/**
	 * This method is used for searching patient in statement queue
	 */
	public void searchPatientInStatement(){
		SeleniumUtil.switchToParentFrame(Frames.STATEMENTSINSIDEPAGE);
		//SeleniumUtil.getElementWithFluentWait(billingGroupButton).click();
		SeleniumUtil.getElementWithFluentWait(By.id("txtBillingProviderAdd")).click();
		SeleniumUtil.waitForProgressBar(Frames.STATEMENTSINSIDEPAGE);
		superbillobj.selectProvider();
		SeleniumUtil.switchToParentFrame(Frames.STATEMENTSINSIDEPAGE);
		SeleniumUtil.getElementWithFluentWait(search).click();	
		SeleniumUtil.waitForProgressBar(Frames.STATEMENTSINSIDEPAGE);
	}
	/**
	 * This method is used for verify staement row is present for patient in statement queue
	 */
	public boolean verifyStatementRow(String imageName) throws FindFailed{
		sleep(5000);
		boolean isStatmentRowPresent=false;
		SeleniumUtil.switchToParentFrame(Frames.STATEMENTSINSIDEPAGE);
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			String completePatientName =ConstantsFile.MRN+" " +ConstantsFile.patientlastname+", "+ConstantsFile.patientfirstname;
			logger.info(("completename:"+completePatientName));
			/*	String completeProviderName=ConstantsFile.providercompletelastname+", "+ConstantsFile.providerfirstname;
			logger.info(("completeprovidername:"+completeProviderName));*/
			if(rowtext.contains(completePatientName)){
				logger.info("statment row is displayed");
				irows.click();
				//SeleniumUtil.waitForProgressBar(Frames.STATEMENTSINSIDEPAGE);
				SeleniumUtil.rightClick(irows);
				RClickOnStatement(imageName);
				isStatmentRowPresent=true;
				break;
			}
		}
		return isStatmentRowPresent;
	}
	/**
	 * This method is used for Right click on statement row  for patient in statement queue
	 */
	public void RClickOnStatement(String imageName) throws FindFailed{
		SeleniumUtil.clickOnImageWitScreenInSikuli(imageName);	
	}
	/**
	 * This method is used for verify preview statement  for patient in statement queue
	 */
	public boolean verifyPreviewStatementPage() {
		boolean isPatientStatementPopupOpened=false;
		sleep(9000);
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		String title =SeleniumUtil.getElementWithFluentWait(AppointmentPage.popUpTitle).getText();
		if(title.equalsIgnoreCase("Patient Statement")){
			System.out.println("Patient Statement popup is opened..");
			sleep(9000);
			SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
			SeleniumUtil.switchToFrame(driver, "panViewer_Frame");
			sleep(9000);
			WebElement titletext =SeleniumUtil.getElementWithFluentWait(statementData);
			if(titletext!=null){
				System.out.println("Patient statement data is present");
				SeleniumUtil.switchToParentFrame(Frames.LOGIN);
				SeleniumUtil.getElementWithFluentWait(closeStatementButton).click();
				SeleniumUtil.waitForProgressBar(Frames.LOGIN);

				isPatientStatementPopupOpened=true;
			}
		}
		return isPatientStatementPopupOpened;
	}
	/**
	 * This method is used to switch Into statement Inside Frame
	 * 
	 */
	public void switchToStatement(){
		SeleniumUtil.switchToParentFrame(Frames.STATEMENTSINSIDEPAGE);
	}
	/**
	 * This method is used for click on send  statement  for patient in statement queue
	 */
	public void clickOnSendStaement() throws FindFailed{
		String completePatientName =ConstantsFile.MRN+" " +ConstantsFile.patientlastname+", "+ConstantsFile.patientfirstname;
		logger.info(("completename:"+completePatientName));
		String completeProviderName=ConstantsFile.providercompletelastname+", "+ConstantsFile.providerfirstname;
		logger.info(("completeprovidername:"+completeProviderName));
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(ConstantsFile.patientlastname)){
				System.out.println("in send statement");
				irows.click();
				break;
			}
		}
		SeleniumUtil.getElementWithFluentWait(By.id("btnSend_SpanBGColor")).click();
		SeleniumUtil.waitForProgressBar(Frames.STATEMENTSINSIDEPAGE);
		SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
	}
	/**
	 * This method is used for click on Outbox tab present  in statement queue
	 */
	public void  clickOnOutBox(){
		SeleniumUtil.switchToParentFrame(Frames.STATEMENTS);
		SeleniumUtil.getElementWithFluentWait(outboxTab).click();
		SeleniumUtil.waitForProgressBar(Frames.STATEMENTS);
	}
	/**
	 * This method is used t verify statement is generated in outbox tab   present  in statement 
	 */
	public boolean verifyStatementIsGenerated(){
		boolean isRowPresentInOutBox=false;
		sleep(5000);
		SeleniumUtil.switchToParentFrame(Frames.OUTBOX);
		List<WebElement> totaltrrows = driver.findElements(AppointmentPage.totaltrtags);
		System.out.println(totaltrrows.size());
		String completeProviderName=ConstantsFile.providercompletelastname;
		logger.info(("completeprovidername:"+completeProviderName));
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(completeProviderName)){
				isRowPresentInOutBox=true;
				break;
			}
		}
		return isRowPresentInOutBox;
	}
}
