package com.gpm.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.ConstantsFile;

import static com.gmed.helper.DriverFactory.driver;

import java.util.List;

public class PostedChargePage extends BaseAbstractPage{
	/** Logger to log the PostedChargePage log messages */
	private static Logger logger                   = LogManager.getLogger(PostedChargePage.class);
	public static By totalQueue                    = By.xpath(".//table[@id='radGridCharge_ctl00']/tbody/tr");
	
	
	
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

}
