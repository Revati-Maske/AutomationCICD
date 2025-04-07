package FrameworkDesignPageObjects.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import FrameworkDesign.resources.ExtentReportNg;

public class ExtentListeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ThreadLocal<ExtentTest> threadExtent = new ThreadLocal<ExtentTest>();
	
	
	ExtentReports extent = ExtentReportNg.getReports();
	public void onTestStart(ITestResult result) {
		test =extent.createTest(result.getMethod().getMethodName());
		threadExtent.set(test);
	}
	
	public void onTestSuccess(ITestResult result) {
		threadExtent.get().log(Status.PASS, "Test Passed");
	  }
	public void onTestFailure(ITestResult result) {
	    // not implemented
		threadExtent.get().fail(result.getThrowable());
		String filePath= null;
		// getting life to driver 
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// screenshot attach to report
		try {
			 filePath= getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		threadExtent.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }
	public void onFinish(ITestContext context) {
	    // not implemented
		extent.flush();
	  }


}
