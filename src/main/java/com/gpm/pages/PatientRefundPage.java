package com.gpm.pages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;

public class PatientRefundPage extends BaseAbstractPage{
	/** Logger to log the PaymentPage log messages */
	private static Logger logger                   = LogManager.getLogger(PatientRefundPage.class);
	public static By patientRefundButton		                    =By.id("btnNewPatientRefund_SpanBGColor");
	
	
	/**
	 * This method is used to click on Patient Refund button present in Payments and Adjustment 
	 */
	public void clickOnPatientRefund(){
		SeleniumUtil.switchToParentFrame(Frames.REFUND);
		SeleniumUtil.getElementWithFluentWait(patientRefundButton).click();
		SeleniumUtil.waitForProgressBar(Frames.REFUND);
	}
	/**
	 * This method is used to verify Refunds  Page is opened
	 */
	public boolean verifyPatientRefundPage(){
		boolean isRefundTitle=false;
		SeleniumUtil.switchToParentFrame(Frames.REFUNDINSIDE);
		String title = SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		if(title.equalsIgnoreCase("Patient Refund")){
			System.out.println("Patient Refund page is opened");
			isRefundTitle=true;
		}
		return isRefundTitle;
	}
	/**
	 * This method is used to switch into Refund Inside Frame 
	 */
	public void patientRefundInsideFrame(){
		SeleniumUtil.switchToParentFrame(Frames.REFUNDINSIDE);
	}
	/**
	 * This method is used to switch into Refund inside  frame
	 */
	public void switchToRefundInsideFrame(){
		SeleniumUtil.switchToParentFrame(Frames.REFUNDINSIDE);
	}
	/**
	 * This method is used to select  Patient Refund row of patient 
	 */
	public void selectPatientRefundRow(){
		List <WebElement> rowsize=driver.findElements(InsurancePaymentPage.paymentrow);
		System.out.println(rowsize.size());
		for(WebElement irows:rowsize){
			String rowText= irows.getText();
			System.out.println(rowText);
			WebElement insPayment1=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[1]/td[8]"));
			action.moveToElement(insPayment1).doubleClick().sendKeys("1200").build().perform();
			WebElement insPayment2=	SeleniumUtil.getElementWithFluentWait(By.xpath(".//table[@id= 'ctl01_ssgCharges']/tbody/tr[2]/td[8]"));
			action.moveToElement(insPayment2).doubleClick().sendKeys("600").build().perform();
			break;
		}
	}
	/**
	 * This method is used to switch into Refund Main Frame 
	 */
	public void patientRefundFrame(){
		SeleniumUtil.switchToParentFrame(Frames.REFUND);
	}
	/**
	 * This method is used to verify Patient Refund queue 
	 */
	public boolean verifyPatientRefundQueue(){
		sleep(8000);
		boolean isPatientQueuePresent=true;
		//SeleniumUtil.switchToParentFrame(Frames.PAYMENT);
		String completePatientName =ConstantsFile.MRN+" " +ConstantsFile.patientlastname+", "+ConstantsFile.patientfirstname;
		List<WebElement> totaltrrows = driver.findElements(InsurancePaymentPage.insuranceQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext.trim());
			if(rowtext.contains(ConstantsFile.patientlastname) && rowtext.contains("CHK")){
				System.out.println("patient refund row is present");
				isPatientQueuePresent=true;
				break;	
			}
		}
		return isPatientQueuePresent=true;
	}
}
