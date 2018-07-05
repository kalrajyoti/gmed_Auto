package com.gpm.pages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.SeleniumUtil;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;
public class InsuranceRefund extends BaseAbstractPage{
	/** Logger to log the InsuranceRefund log messages */
	private static Logger logger                   = LogManager.getLogger(InsuranceRefund.class);
	public static By insuranceRefund                                = By.id("btnNewInsuranceRefund_SpanBGColor");
	
	/**
	 * This method is used to click on insurance refund Button
	 */
	public void clickOnInsuranceRefund(){
		SeleniumUtil.getElementWithFluentWait(insuranceRefund).click();
		SeleniumUtil.waitForProgressBar(Frames.REFUND);
	}
	/**
	 * This method is used to verify Insurance Payment and Adjustment  Page is opened
	 */
	public boolean verifyInsuranceRefundPage(){
		boolean isInsuranceRefundTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.REFUNDINSIDE);
		String tiitle = SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Insurance Refund")){
			System.out.println("Insurance Refund page is opened");
			isInsuranceRefundTitle=true;
		}
		return isInsuranceRefundTitle;
	}
	/**
	 * This method is used to select insurance payment row of patient 
	 */
	public void selectInsuranceRow(){
		List <WebElement> rowsize=driver.findElements(InsurancePaymentPage.paymentrow);
		System.out.println(rowsize.size());
		for(WebElement irows:rowsize){
			String rowText= irows.getText();
			System.out.println(rowText);
			if(rowText.contains("$1,200.00")){
				WebElement insPayment1=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[1]/td[7]"));
				action.moveToElement(insPayment1).doubleClick().sendKeys("1200").build().perform();
			}
			else if(rowText.contains("$600.00")){
				WebElement insPayment2=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[2]/td[7]"));
				action.moveToElement(insPayment2).doubleClick().sendKeys("600").build().perform();
			}
		}
	}
	/**
	 * This method is used to switch in Refund Frame
	 * 
	 */
	public void switchToRefundFrame(){
		SeleniumUtil.switchToParentFrame(Frames.REFUND);
	}
	/**
	 * This method is used to verify Insurance Refund queue 
	 */
	public boolean verifyInsuranceRefundQueue(){
		sleep(3000);
		boolean isinsuranceQueuePresent=false;
		//SeleniumUtil.switchToParentFrame(Frames.PAYMENT);
		List<WebElement> totaltrrows = driver.findElements(InsurancePaymentPage.insuranceQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains("* QA PAY*") && rowtext.contains("CHK")){
				System.out.println("inurance refund row is present");
				isinsuranceQueuePresent=true;
				break;	
			}
		}
		return isinsuranceQueuePresent;
	}
}
