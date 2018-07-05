package com.gmed.utils;

import com.gmed.pages.LoginPage;
import com.gmed.sikuli.ResourceImage;
import com.google.common.base.Function;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static com.gmed.helper.DriverFactory.driver;
import static com.gmed.helper.DriverFactory.driver1;

public class SeleniumUtil {
	/** Logger to log the LoginPage log messages */
	private static Logger logger                = LogManager.getLogger(LoginPage.class);

	/**
	 * Method to switch on single Frame
	 * @param driver
	 * @param framename-
	 */

	public static void switchToFrame(WebDriver driver, String framename)
	{
		try
		{

			getElementWithFluentWait(By.id(framename));
			getIframe(driver,framename);
			driver.switchTo().frame(framename);
			System.out.println("Navigated to frame with name " + framename);

		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to navigate to frame  "+ framename );
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param numberOfWindows- when multiple windows are present on the same page
	 */
	public static void waitForNumberOfWindowsToEqual(final int numberOfWindows) 
	{
		{ 
			try{  
				new WebDriverWait(driver,20){}.until(new ExpectedCondition<Boolean>()
				{   
					@Override public Boolean apply(WebDriver driver) {
						return (driver.getWindowHandles().size() == numberOfWindows);}

				});
			}
			catch(TimeoutException  e)
			{
				System.out.println("yes No alert is no present on the page:" );
			}

		}
	}
	/**
	 * 
	 * @param numberOfWindows- when multiple windows are present on the same page
	 */
	public static void waitForNumberOfWindowsToEqualInReport(final int numberOfWindows) 
	{
		{ 
			try{  
				new WebDriverWait(driver,90){}.until(new ExpectedCondition<Boolean>()
				{   
					@Override public Boolean apply(WebDriver driver) {
						return (driver.getWindowHandles().size() == numberOfWindows);}

				});
			}
			catch(TimeoutException  e)
			{
				System.out.println("yes No alert is no present on the page:" );
			}

		}
	}


	/**
	 * method to get Frame Names in Page
	 * @param driver
	 * @param id
	 */
	public static void getIframe(final WebDriver driver, final String id) {
		final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Number of frames in a page :" + iframes.size());
		for (WebElement iframe : iframes) {
			//if (iframe.getAttribute("id").equals(id)) {
			System.out.println("Frame name :" + iframe.getAttribute("id"));
			// }
		}
	}
	/**
	 * Method for handling when there are multiple Frames(Frame inside a frame)
	 * @param ParentFrame
	 * @param ChildFrame
	 */
	public static void switchToChildFrame(String ParentFrame, String ChildFrame) {
		try {
			Thread.sleep(3000);
			getIframe(driver,ChildFrame);

			driver.switchTo().frame(ParentFrame).switchTo().frame(ChildFrame);
			System.out.println("Navigated to innerframe with id " + ChildFrame
					+ "which is present on frame with id" + ParentFrame);
		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " + ParentFrame
					+ " or " + ChildFrame + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to innerframe with id "
					+ ChildFrame + "which is present on frame with id"
					+ ParentFrame + e.getStackTrace());
		}
	}


	public static void rightClick(WebElement element) {
		try {
			Actions action = new Actions(driver).contextClick(element);
			action.build().perform();

			System.out.println("Sucessfully Right clicked on the element");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable "
					+ e.getStackTrace());
		}
	}


	public static WebElement getElementWithFluentWait(By elementlocatio){
		FluentWait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(driver);
		//wait.pollingEvery(200, TimeUnit.SECONDS);
		//wait.withTimeout(10, TimeUnit.SECONDS);
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		wait.withTimeout(30, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		Function<EventFiringWebDriver, WebElement> function = new Function<EventFiringWebDriver, WebElement>() {
			public WebElement apply(EventFiringWebDriver driver) {
				return driver.findElement(elementlocatio);
			}
		};
		return wait.until(function);

	}
	public static void waitForProgressBar(String currFrameName){
       try{
		driver.switchTo().defaultContent();
		logger.info("waiting for progress bar...");
		long timeoutInSeconds = 90;
		new WebDriverWait(driver, timeoutInSeconds)
		.until(ExpectedConditions.invisibilityOfElementLocated(By.id("divFrameworkLoading_Message")));
		SeleniumUtil.switchToParentFrame(currFrameName);
       }
       catch (Exception e){
    	   System.out.println("Waited for too long time........");
       }
    		   
    		   
	}
	public static void clickOnImageWithTargetOffsetInSikuli(String imagename) throws FindFailed{
		Pattern pattern = new Pattern();
		Screen screen = new Screen(); 
		Pattern yImage1 = new Pattern(ResourceImage.named(imagename)).targetOffset(pattern.getTargetOffset());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		screen.wait(ResourceImage.named(imagename), 30.0);
		screen.click(screen.exists(yImage1,1), 1);	
	}
	public static void clickOnImageWitScreenInSikuli(String imagename) throws FindFailed{
		Screen screen = new Screen(); 
		screen.wait(ResourceImage.named(imagename), 30.0);
		screen.click(ResourceImage.named(imagename));

	}
	public static void doubleClickOnImageWitScreenInSikuli(String imagename) throws FindFailed{
		Screen screen = new Screen(); 
		screen.wait(ResourceImage.named(imagename), 30.0);
		screen.doubleClick(ResourceImage.named(imagename));

	}
	public static void scrolltoWebElement(WebElement locationvalue){
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", locationvalue);
	}


	public static void switchToParentFrame(String frameHierarchy) {
		try {
			Thread.sleep(5000);
			String[] array = frameHierarchy.split(">>");
			switchtoTopFrame();
			for(int j=0;j<array.length;j++){
				System.out.println("frame name: " +array[j]);
				driver.switchTo().frame(array[j]);
			}

		} catch (NoSuchFrameException e) {
			System.out.println("Unable to locate frame with id " +
					frameHierarchy	+ " or "  + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to navigate to innerframe with id "
					+ frameHierarchy  + e.getStackTrace());
		}
	}
	public static void switchtoTopFrame(){
		try{
			final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
			System.out.println("Number of frames in a page :" + iframes.size());
			for (WebElement iframe : iframes) {
				if (iframe.getAttribute("id").equals("fraDefault")) {
					System.out.println("Frame name :" + iframe.getAttribute("id"));
					return;
				}
			}
			driver.switchTo().parentFrame();
			switchtoTopFrame();

		}
		catch(Exception e){
			System.out.println("No such frame");
		}
	}
	public static WebElement highlightElement(WebDriver driver, By by)

	{ WebElement element = null;
	for (int i = 0; i < 10; i++)
	{
		element = driver.findElement(by);

		JavascriptExecutor js = (JavascriptExecutor)driver;
		// js.ExecuteScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 2px solid red;");
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red; border: 2px solid red;");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		//Thread.Sleep(1000);
		//js.ExecuteScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
	}
	return element;
	}
	/**
	 * This method is used to click on multiple pattern of same image in sikuli
	 * 
	 */
	public static void multiplePattern() throws FindFailed{

		//ImagePath.setBundlePath("D:\\verifyreporttestcase\\gmed-automation\\gGastro-mvn\\Images");
		ImagePath.setBundlePath(System.getProperty("user.dir") +"\\Images\\");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Screen s = new Screen();
		Iterator<Match> it = s.findAll("yesNo.png");


		while(it.hasNext()){

			it.next().doubleClick();
		}
	}
	public static HashMap<String,String>setFutureDate(){
		HashMap<String,String> dateMap = new HashMap<String,String>();
		Calendar calendar1 = Calendar.getInstance(TimeZone.getDefault());
		int month = calendar1.get(Calendar.MONTH) + 1;
		String monthvalue=Integer.toString(month);
		dateMap.put("month", monthvalue);
		int day = calendar1.get(Calendar.DATE)+1;
		String dayvalue=Integer.toString(day);
		dateMap.put("day", dayvalue);
		int year = calendar1.get(Calendar.YEAR);
		String yearvalue=Integer.toString(year);
		dateMap.put("year", yearvalue);
		return dateMap;

	}
	public static HashMap<String,String> getTimeInHourMinSecond(){
		HashMap<String,String> timeMap = new HashMap<String,String>();
		Date d = new Date();
		int hourvalue = d.getHours();
		String hour=Integer.toString(hourvalue);
		timeMap.put("HourKey", hour);
		int minvalue = d.getMinutes();
		String min=Integer.toString(minvalue);
		timeMap.put("minKey", min);
		int secvalue = d.getSeconds();
		String sec=Integer.toString(secvalue);
		timeMap.put("secKey", sec);
		return timeMap;
	}
	public static WebElement getElementWithFluentWaitInChrome(By elementlocatio){
		FluentWait<EventFiringWebDriver> wait = new FluentWait<EventFiringWebDriver>(driver1);
		//wait.pollingEvery(200, TimeUnit.SECONDS);
		//wait.withTimeout(10, TimeUnit.SECONDS);
		wait.pollingEvery(100, TimeUnit.MILLISECONDS);
		wait.withTimeout(30, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		Function<EventFiringWebDriver, WebElement> function = new Function<EventFiringWebDriver, WebElement>() {
			public WebElement apply(EventFiringWebDriver driver) {
				return driver.findElement(elementlocatio);
			}
		};
		return wait.until(function);

	}
	public static void scrolltoWebElementInChrome(WebElement locationvalue){
		((JavascriptExecutor) driver1).executeScript(
				"arguments[0].scrollIntoView();", locationvalue);
	}
	public static void  focusOnInternetExplorer(){
		((JavascriptExecutor) driver).executeScript("window.focus()"); 
	}
	public static void  focusOnChrome(){
		((JavascriptExecutor) driver1).executeScript("window.focus()"); 
	}
	
	//To Double Click on Element
	public static void doubleClick(WebElement element) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).doubleClick().perform();
		
			
			
			//Actions action = new Actions(driver).contextClick(element);
			//action.build().perform();

			// System.out.println("Sucessfully Right clicked on the element");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable "
					+ e.getStackTrace());
		}
	}
	
	//To wait Explicitly for Element
	public static void elewait(By xpath) {
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
	}
	
	/**
	 * This method is used for adding value in text box which have read only property
	 */
     public static void addTextBoxValue(String locvalue,String readOnlyValue){
    	 JavascriptExecutor js = (JavascriptExecutor) driver;	       
    	   js.executeScript("document.getElementById('"+locvalue+"').setAttribute('value',  '"+ readOnlyValue +"')");
     }
     /**
  	 * This method is used for removing read only value in text box which have read only property
  	 */
       public static void removeReadOnly(String locvalue,String readOnlyValue){
     	  JavascriptExecutor js = (JavascriptExecutor) driver;
 	        js.executeScript("document.getElementById('"+locvalue+"').removeAttribute('readonly', 'readonly')");
 	        js.executeScript("document.getElementById('"+locvalue+"').setAttribute('value',  '"+ readOnlyValue +"')");
       }
      
     
 	/**
 	 * This method is used to enter text using the Robot
 	 */
     public static void robot() {
 		
		try {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		robot.keyPress(KeyEvent.VK_E);
		robot.keyRelease(KeyEvent.VK_E);
		robot.keyPress(KeyEvent.VK_S);
		robot.keyRelease(KeyEvent.VK_S);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_T);
		robot.keyPress(KeyEvent.VK_I);
		robot.keyRelease(KeyEvent.VK_I);
		robot.keyPress(KeyEvent.VK_N);
		robot.keyRelease(KeyEvent.VK_N);
		robot.keyPress(KeyEvent.VK_G);
		robot.keyRelease(KeyEvent.VK_G);

		}
		catch(Exception e) {
			System.out.println("Robot Failure");
		}
   }
     
     /**
      * This method will return list of all the webelement which matches the xpath passed as parameter
      * @param by
      * @return
      */
     public static List<WebElement> getElements(By by) {
    	 List<WebElement> li = driver.findElements(by);
    	 return li;
     }
     
     /** The method is used to switch to FraDocument Frame from the Home page after click on Start Service at appointment 
     * @throws InterruptedException */
     
      public static void switchToFraDocumentFromHomePage() throws InterruptedException {
 	       final List<WebElement> iframes1 = driver.findElements(By.tagName("iframe"));
  	       for (WebElement iframe : iframes1) {
		
		if(iframe.getAttribute("id").startsWith("panChart") && iframe.getAttribute("id").length()>40) {
			SeleniumUtil.switchToFrame(driver,iframe.getAttribute("id"));
			break;
		}
	}
	Thread.sleep(2000);
	final List<WebElement> iframes2 = driver.findElements(By.tagName("iframe"));
		for (WebElement iframe : iframes2) {
			
		if(iframe.getAttribute("id").startsWith("fra") && (iframe.getAttribute("id").length()>40)) {
			SeleniumUtil.switchToFrame(driver,iframe.getAttribute("id"));
			break;
		}
	}
	
	SeleniumUtil.switchToFrame(driver, "fraDocument_Frame");
     
     }
      
      /**
       * The method will return count of element matching the xpath
       * @param by
       * @return
       */
       public static int getElementCount(By by) {
      	 return driver.findElements(by).size();
      	 
       }
     
}

