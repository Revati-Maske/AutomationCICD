package FrameworkDesign.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNg {
	
	public static ExtentReports getReports() {
		String path=System.getProperty("user.dir")+"\\reports\\index.html";

		ExtentSparkReporter reporter=new ExtentSparkReporter(path);

		reporter.config().setReportName("Web Automation Result");

		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent=new ExtentReports();

		extent.attachReporter(reporter);

		extent.setSystemInfo("Tester", "Revati");
		return extent;
	}

}
