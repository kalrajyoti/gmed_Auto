package com.gpm.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.pages.ConfigurationPage;
import com.gmed.utils.SeleniumUtil;

public class BillingAlert  extends BaseAbstractPage {
	/** Logger to log the BillingAlert log messages */
	private static Logger logger                = LogManager.getLogger(BillingAlert.class);
	public static By closeButton                = By.id("PopupMain_CloseButton");
	
	/**
	 * This method is used to click On Close button of billing alert 
	 * @return string
	 * 
	 */
	public void clickOnCloseButtonInBillingAlerts(){
		sleep(5000);
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		SeleniumUtil.getElementWithFluentWait(closeButton).click();
		SeleniumUtil.waitForProgressBar(Frames.LOGIN);
	}

}
