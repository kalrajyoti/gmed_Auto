package com.gpm.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gmed.Frames.DynamicFramePage;
import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.pages.AppointmentPage;
import com.gmed.pages.ConfigurationPage;
import com.gmed.pages.LoginPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;
import static com.gmed.helper.DriverFactory.driver;

import java.util.List;
import static com.gmed.helper.DriverFactory.action;
public class SuperBillPage extends BaseAbstractPage{
	/** Logger to log the SuperBillPage log messages */
	private static Logger logger                   = LogManager.getLogger(SuperBillPage.class);
	public static By DropDowns                                      = By.className("TextBoxDropDownButton");
	public static By ICD10                                          = By.xpath("//table[@id='ctl04_ssgDiagnoses']/tbody/tr[1]/td[3]");
	public static By description                                    = By.xpath("//table[@id='ctl04_ssgDiagnoses']/tbody/tr[1]/td[4]");
	public static By ICD9                                           = By.xpath("//table[@id='ctl04_ssgDiagnoses']/tbody/tr[1]/td[2]");
	public static By serviceLocation                                = By.id("txtLocation_TextBox");
	public static By billingProvider                                = By.id("txtBillingProvider_TextBox");
	public static By procedurecode                                  = By.xpath("//table[@id='ssgProfessional']/tbody/tr[1]/td[3]");
	public static By procedurecodeDescription                       = By.xpath("//table[@id='ssgProfessional']/tbody/tr[1]/td[4]");
	public static By postButton                                     = By.id("btnPost_SpanBGColor");
	public static By postPaymentButton                              = By.id("btnPostAndPayment_SpanBGColor");
	public static By continueButton                                 = By.id("btnContinue_SpanBGColor");
	/**
	 * this method is used to verify Superbill is opened in the bottom half of the page
	 */
	public boolean verifySuperbillPage(){
		boolean newbuttonval=false;
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		newbuttonval = SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton)!=null;
		if(newbuttonval){
			System.out.println("superbill page is open in the bottom half of the page");
			newbuttonval=true;
		}
		return newbuttonval;
	}
	/**
	 * This method is used for clicking on New button for creating a Follow up order
	 *
	 */
	public void clickOnNewButton(){
		 SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton).click();
		
	}
	/**
	 * This method is used to click on different drop down like service location drop down ,provider drop down while creating a new superbill.
	 * 
	 */
	public void clickOnDropDowns(int index){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
		List<WebElement> dropdown = driver.findElements(DropDowns);
		WebElement performingDropdown = dropdown.get(index);
		performingDropdown.click();	 
	}
	/**
	 * This method is used to verify the location during creation of new user
	 * @return true when locations are present in the location pop
	 * 
	 */
	public boolean selectLocation() {
		boolean locationvalue=false;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		logger.info("selecting the automation location" +ConstantsFile.Fulllocationname);
		WebElement locationvalues= SeleniumUtil.getElementWithFluentWait(By.xpath(".//*[contains(text(),'"+ ConstantsFile.Fulllocationname +"')]"));
		SeleniumUtil.scrolltoWebElement(locationvalues);
		locationvalues.click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(LoginPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		locationvalue=true;
		return locationvalue;
	}
	/**
	 * This method is used to enter diagnosis value while create a new superbill 
	 * 
	 * 
	 */
	public void enterDiagonisValues(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
		WebElement ICD10Value =SeleniumUtil.getElementWithFluentWait(ICD10);
		sleep(3000);
		action.moveToElement(ICD10Value).doubleClick().sendKeys("D12.0").build().perform();
		sleep(3000);
		WebElement descriptionvalue =SeleniumUtil.getElementWithFluentWait(description);
		descriptionvalue.click();
		sleep(3000);
		WebElement ICD10 =SeleniumUtil.getElementWithFluentWait(ICD9);
		action.moveToElement(ICD10).doubleClick().sendKeys("111.1").build().perform();
		sleep(5000);
	}
	/**
	 * This method is used to verify entered diagnosis value are populated correctly while creating a new superbill 
	 */
	public boolean verifydiagnosisValuesIsPopulated(){
		String icd9Txt = SeleniumUtil.getElementWithFluentWait(ICD9).getAttribute("data-initial");
		System.out.println("the icd9 text is :" +icd9Txt);
		String icd10Txt = SeleniumUtil.getElementWithFluentWait(ICD10).getAttribute("data-initial");
		System.out.println("the icd10 text is :" +icd10Txt);
		String descriptionval = SeleniumUtil.getElementWithFluentWait(description).getAttribute("data-initial");
		System.out.println("the description text is :" +descriptionval);
		if(icd9Txt !=null && icd10Txt!=null && descriptionval!=null){
			return ConstantsFile.diagnosisIsPopulated=true;
		}
		return ConstantsFile.diagnosisIsPopulated;
	}
	/**
	 * This method is used to verify entered location value are populated correctly while creating a new superbill 
	 */
	public boolean verifyServiceLocationIsPopulated(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		String serviceTxt = SeleniumUtil.getElementWithFluentWait(serviceLocation).getAttribute("value");
		System.out.println("the billing provider text is :" +serviceTxt);
		if(serviceTxt !=null){
			return ConstantsFile.serviceIsPopulated=true;
		}
		return ConstantsFile.serviceIsPopulated;
	}
	/**
	 * This method is used to select the provider associated with the user while creating a new superbill 
	 */
	public void selectProvider(){
		String providervalue=ConstantsFile.providercompletelastname;
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchtextbox).sendKeys(providervalue);
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.providersearchbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		sleep(5000);
		//SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow).click();
		List <WebElement> providernamevalues =driver.findElements(AppointmentPage.totaltrtags);
		System.out.println(providernamevalues);
		for(WebElement providersrow:providernamevalues){
			System.out.println(providersrow.getText());
			if(providersrow.getText().contains(providervalue))
				providersrow.click();
			SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		}
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.selectbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to verify entered provider value are populated correctly while creating a new superbill 
	 */
	public boolean verifyBillingProviderIsPopulated(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
		String billingProviderTxt = SeleniumUtil.getElementWithFluentWait(billingProvider).getAttribute("value");
		System.out.println("the billing provider text is :" +billingProviderTxt);
		if(billingProviderTxt !=null){
			return ConstantsFile.billingproviderIsPopulated=true;
		}
		return ConstantsFile.billingproviderIsPopulated;
	}
	/**
	 * This method is used to enter procedure code value  while creating a new superbill 
	 */
	public void enterProcedurecode(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		driver.switchTo().frame("panProfile_Frame");
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
		WebElement procedurecodevalue =SeleniumUtil.getElementWithFluentWait(procedurecode);
		sleep(3000);
		action.moveToElement(procedurecodevalue).doubleClick().sendKeys("00400").build().perform();
		sleep(3000);
		WebElement toltalcoltags4 =SeleniumUtil.getElementWithFluentWait(procedurecodeDescription);
		toltalcoltags4.click();	
	}
	/**
	 * This method is used to verify entered procedure code value are populated correctly while creating a new superbill 
	 */
	public boolean verifyProcedurecodeValueIsPopulated(){
		sleep(5000);
		String procedurecodeValue = SeleniumUtil.getElementWithFluentWait(procedurecode).getAttribute("data-initial");
		System.out.println("the icd9 text is :" +procedurecodeValue);
		String procdeduredescriptionval = SeleniumUtil.getElementWithFluentWait(procedurecodeDescription).getAttribute("data-initial");
		System.out.println("the description text is :" +procdeduredescriptionval);
		if(procedurecodeValue !=null && procdeduredescriptionval!=null){
			return ConstantsFile.procedurecodeIsPopulated=true;
		}
		return ConstantsFile.procedurecodeIsPopulated;
	}
	/**
	 * This method is used to click on posting the superbill 
	 */
	public void clickOnPostButton(){  
		SeleniumUtil.getElementWithFluentWait(postButton).click(); 
	}
	/**
	 * This method is used to switch into superbill frame  
	 */
	public void switchToSuperbillPage(){
		SeleniumUtil.switchToParentFrame(Frames.Superbill);
	}
	/**
	 * This method is used to select unposted row  
	 */
	public void selectUnpostedRow(){
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		WebElement row =SeleniumUtil.getElementWithFluentWait(AppointmentPage.patientrow);
		action.moveToElement(row).doubleClick().build().perform();
	}
	/**
	 * This method is used to click on post and payement button while creating a new  superbill 
	 */
	public void clickOnPostPayment(){
		sleep(5000);
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
		sleep(5000);
		SeleniumUtil.getElementWithFluentWait(postPaymentButton).click();
		sleep(9000);
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(continueButton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
	}
	/**
	 * This method is used to click on save button while creating a new  superbill 
	 */
	public void clickOnSaveButtonInSuperBill(){
		sleep(5000);
		DynamicFramePage.dynamicFrameForPanchart();
		DynamicFramePage.switchtoFraFrame();
		SeleniumUtil.switchToFrame(driver, "panProfile_Frame");
		SeleniumUtil.switchToFrame(driver, "DialogBox_Frame");
		SeleniumUtil.getElementWithFluentWait(AppointmentPage.savebutton);
	}
}
