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
import static com.gmed.helper.DriverFactory.action;

public class PaymentPage extends BaseAbstractPage{
	/** Logger to log the PaymentPage log messages */
	private static Logger logger                   = LogManager.getLogger(PaymentPage.class);
	public static By patientPaymentButton	                        = By.id("btnPatientPaymentAdjustment_SpanBGColor");
	public static By paymentByCash                                  = By.id("ddlPaidBy_Text");
	public static By paymentAmountBox                               = By.id("dtbAmount_FormattedDecimalTextBox");
	public static By adjustmentCodeDropDown                         = By.id("txtAdjustmentCodeDropDown");
	public static By paymentrow			                            = By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr");
	public static By insuranceQueue                                 = By.xpath(".//table[@id='radGridPayment_ctl00']/tbody/tr");
	/**
	 * This method is used to click on Patient Payment button present in Payments and Adjustment 
	 */
	public void clickOnPatientPayment(){
		SeleniumUtil.switchToParentFrame(Frames.PAYMENT);
		SeleniumUtil.getElementWithFluentWait(patientPaymentButton).click();
		SeleniumUtil.waitForProgressBar(Frames.PAYMENT);
	}
	/**
	 * This method is used to verify Payments and Adjustment  Page is opened
	 */
	public boolean verifyPatientPaymentPage(){
		boolean isPatientPaymentTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPAYMENTINDIDEPAGE);
		String tiitle = SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Patient Payment/Adjustment")){
			System.out.println("Patient Payment/Adjustment page is opened");
			isPatientPaymentTitle=true;
		}
		return isPatientPaymentTitle;
	}
	/**
	 * This method is used to switch into patient payment inside frame 
	 */
	public void switchToPatientPaymentInsideFrame(){
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPAYMENTINDIDEPAGE);
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
	 * This method is used to enter adjustment amount for patient 
	 */
	public void enterAdjustmentAmount(){
		SeleniumUtil.getElementWithFluentWait(adjustmentCodeDropDown).click();
		SeleniumUtil.waitForProgressBar(Frames.PATIENTPAYMENTINDIDEPAGE);
	}
	/**
	 * This method is used to select  adjustment code for patient 
	 */
	public void selectAdjustmentCode(){
		logger.info("Clickinig on Adjustment code drop down");
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		List<WebElement> totalAdjustmentCode= driver.findElements(AppointmentPage.totaltrtags);	
		System.out.println(totalAdjustmentCode.size());
		for(WebElement tRows:totalAdjustmentCode){
			String listOfElement=	tRows.getText();
			if(listOfElement.equalsIgnoreCase("MA adj MA Adjustment")){
				action.moveToElement(tRows).doubleClick().build().perform();
				break;
			}
		}
	}
	/**
	 * This method is used to click on show all charge checkbox while doing any payment/refund 
	 */
	public void showAllchargesCheckbox(){
		SeleniumUtil.getElementWithFluentWait(By.id("chkShowAllCharges_Render")).click();
		//SeleniumUtil.waitForProgressBar(Frames.PATIENTPAYMENTINDIDEPAGE);
	}
	/**
	 * This method is used to select  patient payment row of patient 
	 */
	public void selectPatientPaymentRow(){
		logger.info("Clicking on the payment cell");
		SeleniumUtil.switchToParentFrame(Frames.PATIENTPAYMENTINDIDEPAGE);
		List <WebElement> rowsize=driver.findElements(paymentrow);
		System.out.println(rowsize.size());
		for(WebElement irows:rowsize){
			String rowText= irows.getText();
			System.out.println(rowText);
			if(rowText.contains("$1,200.00")){
				WebElement insPayment1=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[1]/td[8]"));
				action.moveToElement(insPayment1).doubleClick().sendKeys("1200").build().perform();
				WebElement adjustment1=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[1]/td[9]"));
				action.moveToElement(adjustment1).doubleClick().sendKeys("0").build().perform();	
			}
			else if(rowText.contains("$600.00")){
				WebElement insPayment2=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[2]/td[8]"));
				action.moveToElement(insPayment2).doubleClick().sendKeys("600").build().perform();
				WebElement adjustment2=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[2]/td[9]"));
				action.moveToElement(adjustment2).doubleClick().sendKeys("0").build().perform();	
			}
		}
	}
	/**
	 * This method is used to switch into patient payment Main frame 
	 */
	public void switchToPatientPaymentFrame(){
		SeleniumUtil.switchToParentFrame(Frames.PAYMENT);
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
	/**
	 * This method is used to switch into Payment main frame
	 */
	public void switchToPaymentFrame(){
		SeleniumUtil.switchToParentFrame(Frames.PAYMENT);
	}
	
}
