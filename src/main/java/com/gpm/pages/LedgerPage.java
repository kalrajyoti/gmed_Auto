package com.gpm.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.utils.SeleniumUtil;


public class LedgerPage extends BaseAbstractPage{
	/** Logger to log the LedgerPage log messages */
	private static Logger logger                   = LogManager.getLogger(LedgerPage.class);
	public static By ledgerTab                     = By.id("tabLedger_Text");
	
	
	/**
	 * This method is used to click on ledger tab present in account inquiry tab
	 * 
	 */
	public void clickOnLedger(){
		sleep(3000);
		SeleniumUtil.switchToParentFrame(Frames.COLLECTIONINSIDE);
		SeleniumUtil.getElementWithFluentWait(ledgerTab).click();
		SeleniumUtil.waitForProgressBar(Frames.COLLECTIONINSIDE);
	}

}
