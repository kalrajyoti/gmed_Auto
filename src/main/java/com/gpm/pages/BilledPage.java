package com.gpm.pages;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.action;
public class BilledPage extends BaseAbstractPage{
	/** Logger to log the BilledPage log messages */
	private static Logger logger                                    = LogManager.getLogger(BilledPage.class);
	public static By totalBilledClaimsQueue                         = By.xpath(".//table[@id='radGridClaims_ctl00']/tbody/tr");
	public static By footerText			                            = By.xpath(".//table[@id= 'radGridClaims_ctl00_Footer']/tbody/tr/td[3]");
	/**
	 * This method is used to switch into billed frame
	 */
	public void switchToBilledFrame(){
		SeleniumUtil.switchToParentFrame(Frames.BILLED);
	}
	/**
	 * This method is used to verify billed claimed queue
	 */
	public boolean verifyBilledClaimQueue(String menuName){
		//SeleniumUtil.switchToParentFrame(Frames.BILLED);
		boolean isBilledClaimQueuePresent=false;
		List<WebElement> totaltrrows = driver.findElements(totalBilledClaimsQueue);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowtext =irows.getText();
			System.out.println(rowtext);
			if(rowtext.contains(ConstantsFile.MRN)){
				System.out.println("billed claims rows are present");
				SeleniumUtil.rightClick(irows);
				sleep(3000);
				List<WebElement> totaltrrows1 = driver.findElements(By.xpath("//div[@id='radMenu_detached']/ul/li"));
				System.out.println(totaltrrows1.size());
				for(WebElement irows1:totaltrrows1){
					String rowtext1 =irows1.getText();
					System.out.println(rowtext1);
					if(rowtext1.contains(menuName)){
						WebElement e1= driver.findElement(By.xpath(".//*[contains(text(),'"+ menuName +"')]"));
						action.moveToElement(e1).click().build().perform();
						isBilledClaimQueuePresent=true;
						break;	
					}
				}
			}
		}
		return isBilledClaimQueuePresent;
	}
	/**
	 * This method is used to verify Encounter Page 
	 */
	public boolean verifyEncounterPage(){
		boolean	isEncounterTitle =false;
		SeleniumUtil.switchToParentFrame(Frames.BILLEDINSIDEPAGE);
		String tiitle = SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).getText();
		if(tiitle.equalsIgnoreCase("Encounter")){
			System.out.println("Encounter Page  is open");
			SeleniumUtil.getElementWithFluentWait(BillingPage.titleHeading).sendKeys(Keys.ESCAPE);
			isEncounterTitle=true;
		}

		return isEncounterTitle;
	}
	/**
	 * This method is used to verify Footer Text  
	 */
	public void verifyFooterText(){
		SeleniumUtil.switchToParentFrame(Frames.BILLED);
		String footer=SeleniumUtil.getElementWithFluentWait(footerText).getText();
		System.out.println(footer);
		if(footer.contains("Total Amount")){
			System.out.println("footer text is present");

		}
	}
	
}
