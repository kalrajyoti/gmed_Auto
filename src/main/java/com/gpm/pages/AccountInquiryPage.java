package com.gpm.pages;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import static com.gmed.helper.DriverFactory.driver;

import java.util.List;
public class AccountInquiryPage extends BaseAbstractPage{
	/** Logger to log the AccountInquiry log messages */
	private static Logger logger                   = LogManager.getLogger(AccountInquiryPage.class);
	public static By patientHyperLink              = By.id("lblNameValue");
	public static By panProfileCloseicon           = By.id("panProfile_Buttons_Close");
	public static By patientBalance                = By.id("lblPatientBalanceTitle");
	public static By patientBalanceValue           = By.id("lblPatientBalanceValue");
	public static By responsibleValue              = By.xpath("//table[@id='radGridCharge_ctl00']/tbody/tr[1]/td[7]");
	public static By accountAgingColum2            = By.id("tblAccountAging_ColumnIndex_2");
	public static By accountAgingColum3            = By.id("tblAccountAging_ColumnIndex_3");
	public static By accountAgingColum4            = By.id("tblAccountAging_ColumnIndex_4");
	public static By transferButton                = By.id("btnTransfer_SpanBGColor");
	public static By accountInquiryTitle           = By.className("PanelTitleBlue");
	
	/**
	 * This method is used to click on patient hyperlink present in account inquiry page.
	 */
	public void clickOnPatientHyperLink(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		sleep(10000);
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.getElementWithFluentWait(patientHyperLink).click();
	}
	/**
	 * This method is used to click on close button in account inquiry page.
	 */
	public void  clickOnCloseButton(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.getElementWithFluentWait(panProfileCloseicon).click();
	}
	/**
	 * This method is used to verify patient running balance in account inquiry page.
	 * 
	 */
	public boolean verifyPatientBalance(){
		boolean ispatientbalance=false;
		driver.switchTo().frame("panProfile_Frame");
		sleep(5000);
		String patienttext =SeleniumUtil.getElementWithFluentWait(patientBalance).getText();
		System.out.println(patienttext);
		String text =SeleniumUtil.getElementWithFluentWait(patientBalanceValue).getText();
		System.out.println(text);
		if(patienttext!=null && text!=null ){
			ispatientbalance=true;
		}
		return ispatientbalance;
	}
	/**
	 * This method is used to verify patient as responsible should be display in the charge tab present  in account inquiry page.
	 * 
	 */
	public boolean verifyChargesRow(){
		boolean isPatientResponsible=false;
		SeleniumUtil.switchToFrame(driver, "fraCharges_Frame");
		String textval=SeleniumUtil.getElementWithFluentWait(responsibleValue).getText();
		if(textval.equalsIgnoreCase("Patient")){
			System.out.println("patient is responsible for charges");
			isPatientResponsible=true;
		}
		return isPatientResponsible;	
	}
	/**
	 * This method is used to verify account aging columns  in account inquiry page.
	 *  
	 */
	public boolean verifyAccountAgingColums(){
		boolean isAgingColums=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		String col2 =SeleniumUtil.getElementWithFluentWait(accountAgingColum2).getText();
		String col3 =SeleniumUtil.getElementWithFluentWait(accountAgingColum3).getText();
		String col4 =SeleniumUtil.getElementWithFluentWait(accountAgingColum4).getText();
		if(col2.equalsIgnoreCase("31-60") && col3.equalsIgnoreCase("61-90") && col4.equalsIgnoreCase("Over 90")){
			System.out.println("All the aging colums are present");
			isAgingColums=true;
		}
		return isAgingColums;
	}
	/**
	 * This method is used to right click & select Transfer button option for the patient in account inquiry page.
	 * @throws FindFailed 
	 */
	public void TranferBalanceToPatient() throws FindFailed{
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		SeleniumUtil.switchToFrame(driver, "fraCharges_Frame");
		sleep(5000);
		List<WebElement> totaltrrows = driver.findElements(PostedChargePage.totalQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains("* QA PAY*") && rowtext.contains(ConstantsFile.providercompletelastname) ){
				System.out.println("charges row is present");
				SeleniumUtil.rightClick(irows);
				SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnTransfer");
				break;
			}

		}
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(transferButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to verify the title of account inquiry Page 
	 * 
	 */
	public boolean verifyAccountInquiryPage(){
		boolean isAccountInquiryPagePresent=false;
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
		String text = SeleniumUtil.getElementWithFluentWait(accountInquiryTitle).getText();
		System.out.println(text);
		if(text.equalsIgnoreCase("  Financial Inquiry")){
			System.out.println("Account Inquiry Page is opened");
			SeleniumUtil.getElementWithFluentWait(accountInquiryTitle).sendKeys(Keys.ESCAPE);
			isAccountInquiryPagePresent=true;
		}
		return isAccountInquiryPagePresent;
	}
}
