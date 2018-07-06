package com.gpm.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import com.gmed.Frames.DynamicFramePage;

import com.gmed.base.BaseAbstractPage;
import com.gmed.pages.DemographicsPage;
import com.gmed.pages.DocumentPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.ExcelFileUtilty;
import com.gmed.utils.SeleniumUtil;

import static com.gmed.helper.DriverFactory.driver;

import java.util.List;
import java.util.Map;

public class PostedChargePage extends BaseAbstractPage{
	/** Logger to log the PostedChargePage log messages */
	private static Logger logger                                   = LogManager.getLogger(PostedChargePage.class);
	public static By totalQueue                                    = By.xpath(".//table[@id='radGridCharge_ctl00']/tbody/tr");
	public static By billTextBox                                   = By.id("txtBillingNumber_TextBox");
	/**contains the Prescription page data*/
	public static Map<String, String> chargeData;


	/**contains the demographics Chart page data*/
	public static Map<String, String> demographicsData;

	/**contains the Profile page data*/
	public static Map<String, String> preData2;



	/**These are the variables which are used to store different data for Charges module*/
	public static String chargesHeaders;
	public static String billNumber;
	
	/** These are the variables which are present on "Billing" sheet in the excel*/
	public static final String CHARGE_HEADER 				                                   = "chargesColums";
	public static final String BILL_NUMBER 				                                       = "billNumber";
	@BeforeClass
	public void initClass() throws Exception{
		logger.info("inside the initClass method for charges test class....");
		chargeData                                                                     = ExcelFileUtilty.readExcelSheet("Billing");
		demographicsData                                                               = ExcelFileUtilty.readExcelSheet("Demographics");
		DemographicsPage.existingPatientfirstname                                      = demographicsData.get(DemographicsPage.PATIENT_FIRSTNAME);	
		DemographicsPage.existingPatientlastname                                       = demographicsData.get(DemographicsPage.PATIENT_LASTNAME);
		chargesHeaders                                                                 = chargeData.get(CHARGE_HEADER);
		billNumber                                                                     = chargeData.get(BILL_NUMBER);
	}
	
	/**
	 * This method is used to verify charge row 
	 */
	public boolean verifyChargesRow(){
		boolean isChargeRowPresent=false;
		List<WebElement> totaltrrows = driver.findElements(totalQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(ConstantsFile.patientlastnamewithoutIns) && rowtext.contains("Patient") && rowtext.contains(ConstantsFile.providercompletelastname) ){
				System.out.println("charges row is present");
				isChargeRowPresent=true;
				break;
			}

		}
		return isChargeRowPresent;
	}
	/**
	 * This method is used for switching into Account Inquiry Frame
	 */
	public void switchToAccountInquiryFrame(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
	}
	/**
	 * This method is used for switching into charge Frame
	 */
	public void switchToChargeFrame(){
		SeleniumUtil.switchToFrame(driver, "fraCharges_Frame");
	}
	/**
	 * This Method is used to verify Charges headers 
	 * @return true if all the headers are present
	 */
	public String verifyChargesHeaders(){
		String isHeaderPresent=null;
		isHeaderPresent=	SeleniumUtil.getElementWithFluentWait(DocumentPage.allvisibletext).getText();
		System.out.println("Mu Content is:"+isHeaderPresent);
		return isHeaderPresent;
	}
	/**
	 * this method is used for clicking on search filter
	 */
	public void clickOnSearchFilter(){
		SeleniumUtil.getElementWithFluentWait(CollectionPage.searchFilter).click();
	}
	/**
	 * This method is used for entering bill number
	 */
	public void enterBillNumber(){
		SeleniumUtil.getElementWithFluentWait(billTextBox).sendKeys(billNumber);
	}
	/**
	 * This method is used to verify charge row 
	 */
	public boolean verifyChargesQueue(){
		boolean isChargeRowPresent=false;
		List<WebElement> totaltrrows = driver.findElements(totalQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(billNumber) && rowtext.contains("automation provider")){
				System.out.println("charges row is present");
				isChargeRowPresent=true;
				break;
			}

		}
		return isChargeRowPresent;
	}

}
