package com.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/com/cucumber",
		glue = "FrameworkDesign.stepDefinition",
		tags = "@Regression",
		plugin = {"pretty", "html:target/cucumber-reports.html"},
		monochrome = true )
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
	// This class will be empty. It will be used only as a holder for the above annotations
	// and to run the tests using TestNG.
	// The CucumberOptions annotation is used to specify the location of the feature files,
	// step definitions, and other options for running the tests.

}
