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
import com.gmed.pages.LoginPage;
import com.gmed.pages.ReportPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;
public class InsurancePaymentPage extends BaseAbstractPage {
	/** Logger to log the InsurancePaymentPage log messages */
	private static Logger logger                                    = LogManager.getLogger(InsurancePaymentPage.class);
	public static By insurancePaymentButton			                = By.id("btnInsurancePayment_SpanBGColor");
	public static By carrierDropDownForInsurance			        = By.id("txtCarrierDropDown");
	public static By carriertextboxValue			                = By.id("txtCarrier_TextBox");
	public static By paymentByCash                                  = By.id("ddlPaidBy_Text");
	public static By paymentAmountBox                               = By.id("dtbAmount_FormattedDecimalTextBox");
	public static By paymentrow			                            = By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr");
	public static By insuranceQueue                                 = By.xpath(".//table[@id='radGridPayment_ctl00']/tbody/tr");
	/**
	 * This method is used to click on insurance Payment button present in Payments and Adjustment 
	 */
	public void clickOnInsurancePayment(){
		SeleniumUtil.switchToParentFrame(Frames.PAYMENT);
		SeleniumUtil.getElementWithFluentWait(insurancePaymentButton).click();
		SeleniumUtil.waitForProgressBar(Frames.PAYMENT);
	}
	/**
	 * This method is used to verify Insurance Payment and Adjustment  Page is opened
	 */
	public boolean verifyInsurancePaymentPage(){
		boolean isInsurancePaymentTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPAYMENTINDIDEPAGE);
		String tiitle = SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Insurance Payment/Adjustment")){
			System.out.println("Insurance Payment/Adjustment page is opened");
			isInsurancePaymentTitle=true;
		}
		return isInsurancePaymentTitle;
	}
	/**
	 * This method is used to select carrier Type 
	 */
	public void selectCarrierType(){
		SeleniumUtil.getElementWithFluentWait(carrierDropDownForInsurance).click();
		SeleniumUtil.waitForProgressBar(Frames.PATIENTPAYMENTINDIDEPAGE);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to verify patient name is populated correctly
	 */
	public boolean verifyCarrierNameIsPopulalted(){
		boolean isCarrierIsPopulated=false;
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPAYMENTINDIDEPAGE);
		String carrierName = SeleniumUtil.getElementWithFluentWait(carriertextboxValue).getAttribute("value");
		if(carrierName.contains("* QA PAY*")){
			System.out.println("insurance carrier Name is Populated");
			isCarrierIsPopulated=true;
		}
		return isCarrierIsPopulated;
	}
	/**
	 * This method is used to select payment method for patient name
	 */
	public void selectPaidBy() throws FindFailed{
		logger.info("Click on Payment by Cash and choose  CASH Option");
		SeleniumUtil.clickOnImageWithTargetOffsetInSikuli("PaidBy");
		SeleniumUtil.clickOnImageWitScreenInSikuli("CashSelection");
	}
	/**
	 * This method is used to verify payment type is populated correctly 
	 * 
	 */
	public boolean verifyPaidByCash(){
		boolean isPaidByCash=false;
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPAYMENTINDIDEPAGE);
		String CashSelection = SeleniumUtil.getElementWithFluentWait(paymentByCash).getAttribute("value");
		if(CashSelection.equalsIgnoreCase("Cash")){
			System.out.println("Selected Payment Type is Cash");
			isPaidByCash=true;
		}
		return isPaidByCash;
	}
	/**
	 * This method is used to enter payment amount for patient
	 * 
	 */
	public void enterPaymentAmount(){
		SeleniumUtil.getElementWithFluentWait(paymentAmountBox).sendKeys("1800" );	
	}
	/**
	 * This method is used to select  payment row of patient 
	 */
	public void selectInsurancePaymentRow(){
		logger.info("Clicking on the payment cell");
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPAYMENTINDIDEPAGE);
		List <WebElement> rowsize=driver.findElements(paymentrow);
		System.out.println(rowsize.size());
		for(WebElement irows:rowsize){
			String rowText= irows.getText();
			System.out.println(rowText);
			if(rowText.contains("$1,200.00")){
				WebElement insPayment1=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[1]/td[7]"));
				action.moveToElement(insPayment1).doubleClick().sendKeys("1200").build().perform();
				WebElement adjustment1=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[1]/td[8]"));
				action.moveToElement(adjustment1).doubleClick().sendKeys("0").build().perform();	
			}
			else if(rowText.contains("$600.00")){
				WebElement insPayment2=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[2]/td[7]"));
				action.moveToElement(insPayment2).doubleClick().sendKeys("600").build().perform();
				WebElement adjustment2=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[2]/td[8]"));
				action.moveToElement(adjustment2).doubleClick().sendKeys("0").build().perform();	
			}
		}
	}
	/**
	 * This method is used to verify Insurance queue 
	 */
	public boolean verifyInsuranceQueue(){
		sleep(3000);
		boolean isinsuranceQueuePresent=false;
		//SeleniumUtil.switchToParentFrame(Frames.PAYMENT);
		List<WebElement> totaltrrows = driver.findElements(insuranceQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(ConstantsFile.patientlastname) && rowtext.contains(ConstantsFile.providercompletelastname)){
				System.out.println("inurance refund row is present");
				isinsuranceQueuePresent=true;
				break;	
			}
		}
		return isinsuranceQueuePresent;
	}

}
