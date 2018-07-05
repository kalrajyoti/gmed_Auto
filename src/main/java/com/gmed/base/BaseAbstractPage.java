package com.gmed.base;

import com.gmed.Frames.Frames;
import com.gmed.pages.DocumentPage;
import com.gmed.pages.ScanningPage;
import com.gmed.utils.SeleniumUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;

import static com.gmed.helper.DriverFactory.driver;


public abstract  class BaseAbstractPage {

	/** Logger to log the BaseAbstractPage log messages */
	private static Logger logger                             = LogManager.getLogger(BaseAbstractPage.class);

    protected void sleep(int millis){
		try{
			Thread.sleep(millis);
		} catch (InterruptedException ignored){
			//do nothing
		}
	}
	protected static void staticSleep(int millis){
		try{
			Thread.sleep(millis);
		} catch (InterruptedException ignored){
			//do nothing
		}
	}

	/**
	 * This method is used to Verify if the Text passed as parameter is same as return by application
     *
     * @param text
	 */
	public boolean verifyText(By id, String text) {
		return SeleniumUtil.getElementWithFluentWait(id).getText().trim().equalsIgnoreCase(text);
	}

	/**
	 * This method is used to enter text in the webelement
     *
     * @param by
	 * @param text
	 */
	public void enterText(By id, String text) {
		SeleniumUtil.getElementWithFluentWait(id).sendKeys(text);
	}

    /**
     * This method is used to extract the text of webelement from application
     */
    public String getElementText(By by) {
        return SeleniumUtil.getElementWithFluentWait(by).getText();
    }


    /**
     * This method is used to verify if the field is editable or not
     *
     * @param by
     * @return
     */
    public boolean verifyFieldEditable(By by) {
        logger.info("Checking that " + by.toString() + " is editable by entering TestData");
        String string = getElementText(by);
        enterText(by, "TestData");
        return verifyText(by, string);
    }

    /**
     * This method is used to click on any element
     *
     * @param by
     */
    public void click(By by) {
        SeleniumUtil.getElementWithFluentWait(by).click();
    }

    /*
     * This method is used to right click on element
     */
    public void rightClick(By by) {
        logger.info("Right-clicking on" + SeleniumUtil.getElementWithFluentWait(by).getText());
        WebElement element = SeleniumUtil.getElementWithFluentWait(by);
        SeleniumUtil.rightClick(element);
    }

    /**
     * This method is used to click on image using sikuli
     *
     * @param imagename
     * @return
     */
    public boolean clickOnImage(String imagename) {
        try {
			SeleniumUtil.clickOnImageWitScreenInSikuli(imagename);
			return true;
        } catch (FindFailed e) {
            return false;
        }
    }
    /**
     * This method is used to Switch to Pop Up Frame
     * @param framehierarchy TODO
     */
    public void switchToParentFrame(String framehierarchy) {
        SeleniumUtil.switchToParentFrame(framehierarchy);
    }

    public void switchToFrame(String frameName) {
        sleep(9000);
        SeleniumUtil.switchToFrame(driver, frameName);
        sleep(9000);
    }

    /**
     * This method is used to return the complete text in form of string array for all element having certain xpath 1860
     *
     * @param by
     * @return
     */
    public String[] getElementsText(By by) {
        return SeleniumUtil.getElements(by).stream().map(WebElement::getText).toArray(String[]::new);
    }

    /**
     * This method is used to click on List of WebElements Line no. 1939
     * Does not fail if element cannot be clicked.
     *
     * @param by
     */
    public void clickEachElement(By by) {
        try {
            SeleniumUtil.getElements(by).forEach(WebElement::click);
        } catch (Exception e) {
            System.out.println("Unable to Click on element");
        }
    }

    /**
     * The method is used to scroll the page upto the webelement based on the Locator passed
     *
     * @param by
     */
    public void scroll(By by) {
        SeleniumUtil.scrolltoWebElement(SeleniumUtil.getElementWithFluentWait(by));
    }

    /**
     * This method is used to get value of list of Elements
     *
     * @param by
     * @return
     */
    protected String[] getElementsValue(By by) {
        return SeleniumUtil.getElements(by).stream().map(element -> element.getAttribute("value")).toArray(String[]::new);
    }
    
    /**
     * The method is used to return attribute value of web element
       */
    public String getAttributeValue(By by, String attribute) {
    	return SeleniumUtil.getElementWithFluentWait(by).getAttribute(attribute);
    }
   
    /**
     * The method is used to clear the Text inside Text box
     */
    public void clearText(By by) {
    	SeleniumUtil.getElementWithFluentWait(by).clear();
    }
    
	/**
	 * This method is used to create a refresh Document Page
	 */
	public void refresh() {
		SeleniumUtil.getElementWithFluentWait(DocumentPage.refresh).click();
		
	}
    
 }
