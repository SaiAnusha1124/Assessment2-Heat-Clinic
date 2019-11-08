package com.atmecs.heatclinic.testscripts;

import com.atmecs.testbase.TestBase;
import java.util.Properties;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.atmecs.actions.ClickOnElementAction;
import com.atmecs.actions.SendKeysAction;
import com.atmecs.constants.ConstantsFilePaths;
import com.atmecs.extentreports.ExtentReport;
import com.atmecs.helpers.LocatorType;
import com.atmecs.utils.ReadExcelFile;
import com.atmecs.utils.ReadLocatorsFile;
import com.atmecs.validation.ValidationResult;

public class ValidateTabs extends TestBase {
	/*
	 * In this class,Validating all tabs present in Home page
	 */
	Properties properties, properties1;
	static String actualhomepage,actualhotsauce,actualmerchandise,actualclearance,actualnewtohotsauce,actualfaq;
	ClickOnElementAction click = new ClickOnElementAction();
	SendKeysAction sendkeys = new SendKeysAction();
	ReadExcelFile readexcel = new ReadExcelFile();

	@Test
	public void testingHomePageTabs() throws Exception {

		// locators are reading through LOCATOR_FILE
		properties = ReadLocatorsFile.loadProperty(ConstantsFilePaths.LOCATOR_FILE);
		// expected data are reading through TESTDATA_FILE 
		properties1 = ReadLocatorsFile.loadProperty(ConstantsFilePaths.TESTDATA_FILE);

		actualhomepage = driver.findElement(By.xpath((properties.getProperty("loc-verify-homepage")))).getText();
		ValidationResult.validateData(actualhomepage, properties1.getProperty("expectedhomepage"),"Verifying Home Page");
		log.info("Validated Home Page");
		
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-hotsauces"));
		actualhotsauce = driver.findElement(By.xpath((properties.getProperty("loc-click-hotsauces")))).getText();
		ValidationResult.validateData(actualhotsauce, properties1.getProperty("expectedhotsauce"),"Verifying Hot Sauce tab");
		
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-merchandise"));
		actualmerchandise = driver.findElement(By.xpath((properties.getProperty("loc-click-merchandise")))).getText();
		ValidationResult.validateData(actualmerchandise, properties1.getProperty("expectedmerchandise"),"Verifying Merchandise tab");

		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-clearance"));
		actualclearance = driver.findElement(By.xpath((properties.getProperty("loc-verify-clearance")))).getText();
		ValidationResult.validateData(actualclearance, properties1.getProperty("expectedclearance"),"Verifying Clearance tab");
		
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-newtohotsauce"));
		actualnewtohotsauce = driver.findElement(By.xpath((properties.getProperty("loc-verify-newtohotsauce")))).getText();
		ValidationResult.validateData(actualnewtohotsauce, properties1.getProperty("expectednewtohotsauce"),"Verifying New to Hot Sauce tab");
		
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-faq"));
		actualfaq = driver.findElement(By.xpath((properties.getProperty("loc-verify-faq")))).getText();
		ValidationResult.validateData(actualfaq, properties1.getProperty("expectedfaq"),"Verifying FAQ tab");
		log.info("Successfully Validated all tabs in a Home Page");
		ExtentReport.reportLog("testingHomePage", "failed");

	}

}
