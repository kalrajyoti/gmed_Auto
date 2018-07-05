package com.gmed.AutoIT;


import com.gmed.utils.ConstantsFile;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.io.IOException;


import static com.gmed.helper.DriverFactory.driver1;

public class GpinUserLogin {
	/** Logger to log the gPin User Login log messages */
	private static Logger logger             = LogManager.getLogger(GpinUserLogin.class);
	private static By usernametext            = By.xpath("//*[@id='box']/div/input[1]");
	private static By passwordtext            = By.xpath("//*[@id='inputPin']");
	/**
	 * This method is used to get the location of script exe file
	 */
	public static void logintoGpin(){

		try {
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"/basicauth.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to get the location of test script exe file
	 */
	public static void openProvider(){
		try {
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"/test.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to get the location of printfile script exe file
	 */
	public static void clickOnPrint(){
		try {
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"/printfile.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void uploadPDF(){
		try{
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"/uploadfile.exe");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void importXml(){
		try{
			Runtime.getRuntime().exec(System.getProperty("user.dir")+"/importPatient.exe");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used for get the user name and password key present in window authencation pop up
	 * @return true when username and password are accessible with gPin URL
	 * @throws Exception 
	 */
	public static boolean verifygPinCredinaials() throws Exception{
		Thread.sleep(1500);
		ConstantsFile.usernamevalue = driver1.findElement(usernametext).getAttribute("value");
		logger.info("username value :"+ConstantsFile.usernamevalue);
		ConstantsFile.passwordvalue = driver1.findElement(passwordtext).getAttribute("value");
		logger.info("password value :"+ConstantsFile.passwordvalue);
		System.out.println(ConstantsFile.passwordvalue);
		//driver1.close();
		ConstantsFile.popupvalue=true;
		return ConstantsFile.popupvalue;
	}
	/**
	 * This method is used for the verification for gPin Page should be loaded/present
	 * @return  true  when gPin Page present
	 */
	public boolean verifygPinPage(){

		String gPinTitle = driver1.getTitle();
		if(gPinTitle.equalsIgnoreCase("gPin")){
			driver1.close();
			return true;
		}
		else{
			System.out.println("Access is denied" +gPinTitle);
			return false;
		}
	}



}
