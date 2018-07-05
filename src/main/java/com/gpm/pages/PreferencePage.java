package com.gpm.pages;

import org.openqa.selenium.By;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.pages.ConfigurationPage;
import com.gmed.utils.SeleniumUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
public class PreferencePage extends BaseAbstractPage {
	/** Logger to log the PreferencePage log messages */
	private static Logger logger                                        =LogManager.getLogger(PreferencePage.class);
	public static By preferences                                        =By.id("btnPreferences_ImageContainer");
	public static By checkSsnCheckbox                                   =By.id("chkHideSsn");
	public static By enableSsnCheckbox                                   =By.id("chkHideSsn_Render");
	public static By enablePolicyCheckbox                                   =By.id("chkShowPolicyIdOnPatientSearch_Render");	
	public static By checkPolicyIDCheckbox                              =By.id("chkShowPolicyIdOnPatientSearch");
	public static By checkAccountNumberCheckbox                         =By.id("chkEnableAccountNumbers");
	public static By accountNumberCheckbox                         =By.id("chkEnableAccountNumbers_Render");
	

	/**
	 * This method is used to click on Preferences button present on configuration Page
	 * 
	 */
	public void clickOnPreferences(){
		//switching into left panel frame for click on preferences button in the configuration
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on configuration menu...");
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.configuration).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		//switching into configuration main page frame
		SeleniumUtil.switchToParentFrame(Frames.CONFIGURATION_MAINPAGE);
		logger.info("clicking on Preferences button...");
		SeleniumUtil.getElementWithFluentWait(preferences).click();	
		SeleniumUtil.waitForProgressBar(Frames.CONFIGURATION_MAINPAGE);
	}
	/**
	 * This method is used to verify  gPm/Enable Enterprise Scheduler checkboxs  are enabled in  Preferences page
	 * 
	 */
	public void verifyPatientGeneralSettings(){

		//switching into preferences main Page frame
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		String checkHideSsnCheckbox =SeleniumUtil.getElementWithFluentWait(checkSsnCheckbox).getAttribute("stateName");
		String checkPolicyCheckbox =SeleniumUtil.getElementWithFluentWait(checkPolicyIDCheckbox).getAttribute("stateName");
		if(checkHideSsnCheckbox.equalsIgnoreCase("checked") && checkPolicyCheckbox.equalsIgnoreCase("checked")){
			System.out.println("Patient SSN & Policy checkbox are  enabled");
			SeleniumUtil.getElementWithFluentWait(enableSsnCheckbox).click();

		}
		else if(checkHideSsnCheckbox.equalsIgnoreCase("unchecked") && checkPolicyCheckbox.equalsIgnoreCase("checked")){
			System.out.println("Patient SSN checkbox checkbox are not enabled");
			//SeleniumUtil.getElementWithFluentWait(enableSsnCheckbox).click();
		}
		else if(checkHideSsnCheckbox.equalsIgnoreCase("checked") && checkPolicyCheckbox.equalsIgnoreCase("unchecked")){
			System.out.println("PolicyID checkbox checkbox are not enabled");
			SeleniumUtil.getElementWithFluentWait(enableSsnCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(enablePolicyCheckbox).click();
		}
		else{
			System.out.println("Patient SSN checkbox checkbox are not enabled");
			SeleniumUtil.getElementWithFluentWait(checkSsnCheckbox).click();
			SeleniumUtil.getElementWithFluentWait(enablePolicyCheckbox).click();

		}				
	}
	/**
	 * This method is used to save  gPm settings Preferences page
	 * 
	 */
	public void clickOnSaveInPreferences(){
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.saveButton).click();
		SeleniumUtil.waitForProgressBar(Frames.USERPAGE);
	}
	/**
	 * This method is used to verify  gPm/Enable Enterprise Scheduler checkboxs  are enabled in  Preferences page
	 * 
	 */
	public boolean verifyPatientAccountNumberSettings(){
		boolean isPatientSettingsEnabled=false;
		//switching into preferences main Page frame
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		String checkHideSsnCheckbox =SeleniumUtil.getElementWithFluentWait(checkAccountNumberCheckbox).getAttribute("stateName");
		if(checkHideSsnCheckbox.equalsIgnoreCase("checked")){
			System.out.println("Account Number checkbox is  enabled");
			return isPatientSettingsEnabled=true;
		}
		else{
			System.out.println("Account Number checkbox checkbox are not enabled");
			SeleniumUtil.getElementWithFluentWait(accountNumberCheckbox).click();
			return isPatientSettingsEnabled=false;
		}				
	}
	/**
	 * This method is used to save  gPm settings Preferences page
	 * 
	 */
	public void disableAccountInPreferences(){
		SeleniumUtil.switchToParentFrame(Frames.USERPAGE);
		SeleniumUtil.getElementWithFluentWait(accountNumberCheckbox).click();
		SeleniumUtil.waitForProgressBar(Frames.USERPAGE);
	}


}
