package com.gmed.pages;

import static com.gmed.helper.DriverFactory.driver;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import static com.gmed.helper.DriverFactory.action;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.testng.ITestResult;
import org.testng.TestNG;

import com.gmed.Frames.Frames;
import com.gmed.base.BaseAbstractPage;
import com.gmed.test.MIPSReport;
import com.gmed.utils.ConstantsFile;
import com.gmed.utils.SeleniumUtil;

public class LeftPanelPage extends BaseAbstractPage {
	
	/** Logger to log the LoginPage log messages */
	private static Logger logger                = LogManager.getLogger(LeftPanelPage.class);
	public static By logoutbutton                             =By.id("btnLogout");
	public static By leftPanelMenuInPatientChart              =By.xpath(".//table[@id='tblMenuItemsChart_Table']/tbody/tr");
	public static By myactivity                               =By.id("tabMyActivities_Text");
	public static By queueExtender                            =By.id("tabQueues_Extender");
	public static By reportMenu                               =By.id("tabReportsMenu_Extender");
	public static By mainMenus                                =By.xpath(".//control[@type='Button']/table"); 
	public static By leftMainMenus                           = By.xpath(".//control[@type='GroupPanelPage']/table/tbody/tr/td[2]"); 
	/**
	 * This method is used for logout from the application
	 *
	 */
	public void clickOnLogout(){
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		System.out.println("click on logout ");
		SeleniumUtil.getElementWithFluentWait(logoutbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		sleep(2500);
	}
	
	
	/**This method is used for taking screenshot of Exception which occurs in form of Alerts/Separate window
	 * 
	 */
	public  void handleException(String currTestName ,ITestResult result){
		/**
		 * wait for number of windows to load
		 */
		SeleniumUtil.waitForNumberOfWindowsToEqual(2);
		String parentWindowHandler = driver.getWindowHandle();
		System.out.println("The window are :"+driver.getWindowHandles().size());// Store your parent window
		if(driver.getWindowHandles().size() == 2){
			System.out.println("Exception in form of alert is present in the application");
			String subWindowHandler = null; 
			/**
			 * get all window handles
			 */
			Set<String> handles = driver.getWindowHandles(); 
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()){
				subWindowHandler = iterator.next();
			}
			/**
			 * switch to popup window
			 */ 
			driver.switchTo().window(subWindowHandler);	
			String title =driver.getTitle();
			System.out.println(title);
			if (title.contains("Exception") || title.contains("Alert") ) 
			{
				System.out.println("Exception occured so  taking the screenshot of pop up window");
				String outputDIR = TestNG.getDefault().getOutputDirectory();
				try {
					sleep(4000);
					ConstantsFile.newFileNamePathForException = outputDIR + "\\ScreenShot\\" +currTestName +".jpg";
					BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					ImageIO.write(image, "png", new File( ConstantsFile.newFileNamePathForException));
					System.out.println("Exception screenshot is :"+ ConstantsFile.newFileNamePathForException);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			result.setStatus(ITestResult.FAILURE);
		}
		driver.switchTo().window(parentWindowHandler); 
	}
	/**
	 * This method is used to click any value from the left navigation panel present in the patient chart
	 * 
	 */
	public void clickLeftNavigationPanelValue(String navigationvalue){
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		List<WebElement> totaltrrows = driver.findElements(leftPanelMenuInPatientChart);
		System.out.println(totaltrrows.size());
		for(WebElement irows:totaltrrows){
			String rowText= irows.getText();
			if(rowText.contains(navigationvalue)){
				irows.click();
				SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
				break;
			}
		}
	}
	/**
	 * This method is used to click on my activities menu
	 * 
	 */
	public void clickOnMyActvities(){
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		SeleniumUtil.getElementWithFluentWait(myactivity).click();
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
		sleep(3000);	
	}
	/**
	 * This method is used for click any item present in queue management module from the application
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnQueueManagement(String value) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on queue management module ");
		WebElement queueManagementbutton = SeleniumUtil.getElementWithFluentWait(queueExtender);
		Actions action = new Actions(driver);
		action.moveToElement(queueManagementbutton).click().build().perform();
		SeleniumUtil.clickOnImageWitScreenInSikuli(value);
	
}
	/**
	 * This method is used for click any item present in queue management module from the application
	 * @throws FindFailed 
	 * 
	 */
	public void clickOnReport(String value) throws FindFailed{
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		logger.info("clicking on queue management module ");
		WebElement queueManagementbutton = SeleniumUtil.getElementWithFluentWait(reportMenu);
		action.moveToElement(queueManagementbutton).click().build().perform();
		sleep(5000);
		SeleniumUtil.clickOnImageWitScreenInSikuli(value);
	}
	public void clickOnNewInToolTip(){
		try{
		SeleniumUtil.switchToParentFrame(Frames.TOOLTIP);
		SeleniumUtil.getElementWithFluentWait(ConfigurationPage.newbutton).click();
		SeleniumUtil.waitForProgressBar(Frames.TOOLTIP);
		}
		catch(Exception e){
			System.out.println("No Existing service present");
		}
	}
	public static void handleSameMRN() throws FindFailed{
		/**
		 * wait for number of windows to load
		 */
		SeleniumUtil.waitForNumberOfWindowsToEqual(2);
		String parentWindowHandler = driver.getWindowHandle();
		System.out.println("The window are :"+driver.getWindowHandles().size());// Store your parent window
		if(driver.getWindowHandles().size() == 2){
			System.out.println("Yes No window");
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
		}
	}
	/**
	 * This method is used for clicking on No button in popup
	 */
	public void clickOnNo(){
		try {
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnNo");
		} catch (Exception e) {
			System.out.println("No popup present");
		}
	}
	/**
	 * This method is used for clicking on Yesy button in popup
	 */
	public void clickOnYesPopUp() throws FindFailed, Exception {
		try {
			SeleniumUtil.clickOnImageWitScreenInSikuli("clickOnYes");
		} catch (Exception e) {
			System.out.println("No popup present");
		}
	}
	/**
	 * This method is used to verify title of Popup window
	 *
	 */
	public boolean verifyToolTipTitle(String titleName){
		boolean ispopupdisplayed=false;
		SeleniumUtil.switchToParentFrame(Frames.LOGIN);
		WebElement tasktooltiptitle=SeleniumUtil.getElementWithFluentWait(TaskPage.PopupTitle);
		String tooltext = tasktooltiptitle.getText();
		if(tooltext.equalsIgnoreCase(titleName)){
			System.out.println("Tool tip Title  dispalyed");
			return ispopupdisplayed=true;
		}
		else{
			System.out.println("No  Tooltip present ");
			ispopupdisplayed=false;
		}
		return ispopupdisplayed;
	}
	/**
	 * This method is used for clicking on various Tool bar present in any module
	 */
	public void clickOnToolBarMenus(String toolBarName){
		sleep(5000);
		List<WebElement> noOfMenus = driver.findElements(ReportPage.toolBarInMips);
		System.out.println("No Of ToolBar Menu is:" +noOfMenus);
		for(WebElement menuName:noOfMenus){
			String menuText=menuName.getText();
			String altText=menuName.getAttribute("alt");
			System.out.println("Tool Bar text is:"+menuText);
			System.out.println("Alt text is:"+altText);
			if(menuText.equalsIgnoreCase(toolBarName)){
				menuName.click();
				break;
			}
			else if(altText!=null && altText.equalsIgnoreCase(toolBarName)){
				menuName.click();
				break;
			}
		}

	}
	/**
	 * This method is used for clicking on different  Tabs
	 * @param tabName in which user have to switch
	 */
	public void switchToDifferentTab(String tabName){	
		List<WebElement> noOfTabs = driver.findElements(ReportPage.mipsTabs);
		System.out.println("No Of Tabs are:" +noOfTabs);
		for(WebElement tab:noOfTabs){
			String tabText=tab.getText();
			System.out.println("Tab text is:"+tabText);
			if(tabText.equalsIgnoreCase(tabName)){
				tab.click();
				break;
			}
		}

	}
	/**
	 * This method is used for switching into left panel
	 */
	public void switchToLeftPanel(){
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		SeleniumUtil.waitForProgressBar(Frames.LEFTPANEL);
	}
	/**
	 * This method is used for clicking on various Tool bar menu present  in Left panel
	 */
	public void clickOnToolBar(String toolBarName){		
		List<WebElement> noOfMenus = driver.findElements(mainMenus);
		System.out.println("No Of ToolBar Menu is:" +noOfMenus.size());
		for(WebElement menuName:noOfMenus){
			String menuText=menuName.getText();
			System.out.println("Menu text is:"+menuText);
			if(menuText.equalsIgnoreCase(toolBarName)){
				menuName.click();
				break;
			}
		}

	}
	/**
	 * This method is used for clicking on various Tool bar menu present  in Left panel
	 */
	public void selectLeftMainMenu(String menuNames){
		SeleniumUtil.switchToParentFrame(Frames.LEFTPANEL);
		List<WebElement> noOfMenus = driver.findElements(leftMainMenus);
		System.out.println("No Of ToolBar Menu is:" +noOfMenus.size());
		for(WebElement menuName:noOfMenus){
			String menuText=menuName.getText();
			System.out.println("Menu text is:"+menuText);
			if(menuText.equalsIgnoreCase(menuNames)){
				menuName.click();
				break;
			}
		}

	}

}

	

