package com.atmecs.testscripts;

import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.atmecs.actions.ClickOnElementAction;
import com.atmecs.actions.SendKeysAction;
import com.atmecs.constants.ConstantsFilePaths;
import com.atmecs.extentreports.ExtentReport;
import com.atmecs.helpers.LocatorType;
import com.atmecs.testbase.TestBase;
import com.atmecs.utils.ReadExcelFile;
import com.atmecs.utils.ReadLocatorsFile;
import com.atmecs.validation.ValidationResult;

public class TestNegative extends TestBase {
	/*
	 * In this class,validating negative cases
	 */
	Properties properties, properties1;
	String actualnegativecase;
	ClickOnElementAction click = new ClickOnElementAction();
	SendKeysAction sendkeys = new SendKeysAction();
	ReadExcelFile readexcel = new ReadExcelFile();

	@DataProvider(parallel = true)
	public Object[][] inputValues() {
		/*
		 * In this class,validating negative cases product in products list
		 */
		Object data[][] = readexcel.readExcel("Sheet3", ConstantsFilePaths.TESTDATA_FILE1);
		return data;
	}

	@Test(dataProvider = "inputValues", priority = 1)
	public void testingHomePage(String NegativeProduct) throws Exception {

		// locators are reading through LOCATOR_FILE
		properties = ReadLocatorsFile.loadProperty(ConstantsFilePaths.LOCATOR_FILE);
		// expected data are reading through TESTDATA_FILE
		properties1 = ReadLocatorsFile.loadProperty(ConstantsFilePaths.TESTDATA_FILE);

		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-bttn-search"));
		sendkeys.sendKeys(driver, LocatorType.XPATH, properties.getProperty("loc-click-bttn-search"), NegativeProduct);
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-search"));
		log.info("Selected Chairs as Negative Product");

		actualnegativecase = driver.findElement(By.xpath((properties.getProperty("loc-verify-negativecase")))).getText();
		ValidationResult.validateData(actualnegativecase, properties1.getProperty("expectednegativecase"),"Verifying after removing grand total");
		ExtentReport.reportLog("TestNegative", "failed");
	}
}
