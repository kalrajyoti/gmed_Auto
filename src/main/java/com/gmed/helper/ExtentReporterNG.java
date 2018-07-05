package com.gmed.helper;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG  implements IReporter {
	private static Logger logger             = LogManager.getLogger(ExtentReporterNG.class);
	private ExtentReports extent;
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		// TODO Auto-generated method stub
		logger.info("output directory" +outputDirectory);
		extent = new ExtentReports(outputDirectory + File.separator + "Dashboard.html", true);
		 
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
           // Map<String, ISuiteResult> res=suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
 
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
                
            }
        }
 
        extent.flush();
        extent.close();
    }
	
	 private void buildTestNodes(IResultMap tests, LogStatus status ) {
	        ExtentTest test;
	 
	        if (tests.size() > 0) {
	            for (ITestResult result : tests.getAllResults()) {
	                test = extent.startTest(result.getMethod().getMethodName());
	               
	                test.setStartedTime(getTime(result.getStartMillis()));
	                test.setEndedTime(getTime(result.getEndMillis()));
	                
	                for (String group : result.getMethod().getGroups())
	                    test.assignCategory(group);
	 
	                if (result.getThrowable() != null) {
	                  // test.log(status, (Throwable) result.getTestContext());
	                  // test.log(status, "Test" + status.toString().toLowerCase() + "ed" +result.getAttributeNames() + " " +test.getDescription());
	                   // test.getClass();
	                    //System.out.println("this test failed");
	                    //test.log(status, result.getClass());
	                   test.log(status, result.getName(), result.getThrowable());
	                }
	                else {
	                    test.log(status, "Test" + status.toString().toLowerCase() + "ed" );
	               }
	 
	                extent.endTest(test);
	            }
	        }
	    }
	


    private Date getTime(long millis) {
     Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);
    return calendar.getTime();        
}

}
