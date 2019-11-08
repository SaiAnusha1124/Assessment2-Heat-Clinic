package com.atmecs.extentreports;

import org.openqa.selenium.WebDriver;

import com.atmecs.constants.ConstantsFilePaths;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	/*
	 * This class is used for html reporting
	 */
	public static WebDriver driver;

	public static void reportLog(String testname, String Failuremsg) {

		ExtentHtmlReporter reporter = new ExtentHtmlReporter(ConstantsFilePaths.EXTENT_REPORTFILE);
		ExtentReports extent = new ExtentReports();

		extent.attachReporter(reporter);
		ExtentTest logger = extent.createTest(testname);
		logger.log(Status.INFO, testname);
		logger.log(Status.PASS, testname);
		extent.flush();
	}
}
