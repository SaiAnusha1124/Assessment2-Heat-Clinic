package com.atmecs.heatclinic.testscripts;

import java.util.Properties;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.atmecs.actions.ClickOnElementAction;
import com.atmecs.actions.SendKeysAction;
import com.atmecs.constants.ConstantsFilePaths;
import com.atmecs.helpers.LocatorType;
import com.atmecs.testbase.TestBase;
import com.atmecs.utils.ReadExcelFile;
import com.atmecs.utils.ReadLocatorsFile;
import com.atmecs.validation.ValidationResult;

public class SelectProduct extends TestBase {
	/*
	 * In this Class,Selecting one Product 
	 * Validating on that product details
	 */
	Properties properties, properties1;
	static String actualshirtsize,actualname,actualshirtcolor,actualprice,actualtotal,actualtotalafterupdate;
	ClickOnElementAction click = new ClickOnElementAction();
	SendKeysAction sendkeys = new SendKeysAction();
	ReadExcelFile readexcel = new ReadExcelFile();

	@DataProvider(parallel = true)
	public Object[][] inputValues() {
		/*
		 * Data provider is using to read the data from excel file
		 */
		Object data[][] = readexcel.readExcel("Sheet4", ConstantsFilePaths.TESTDATA_FILE1);
		return data;
	}
	@Test(dataProvider = "inputValues", priority = 1)
	public void testingHomePage(String name,String quantity) throws Exception {
		
		// locators are reading through LOCATOR_FILE 
		properties = ReadLocatorsFile.loadProperty(ConstantsFilePaths.LOCATOR_FILE);
		// expected data are reading through TESTDATA_FILE 
		properties1 = ReadLocatorsFile.loadProperty(ConstantsFilePaths.TESTDATA_FILE);

		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-mens"));
		log.info("Clicked Mens Products");
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-shirt"));
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-redcolor"));
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-size"));
		log.info("Selected Shirt,Color and size");
		sendkeys.sendKeys(driver, LocatorType.XPATH, properties.getProperty("loc-sendkey-name"), name);
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-buynow"));
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-cart"));
		log.info("Product added into cart");
		actualshirtsize = driver.findElement(By.xpath((properties.getProperty("loc-verify-shirtsize")))).getText();
		ValidationResult.validateData(actualshirtsize, properties1.getProperty("expectedshirtsize"),"Verifying Shirt Size");
		
		actualname = driver.findElement(By.xpath((properties.getProperty("loc-verify-name")))).getText();
		ValidationResult.validateData(actualname, properties1.getProperty("expectedname"),"Verifying Cutomer Name");
		
		actualshirtcolor = driver.findElement(By.xpath((properties.getProperty("loc-verify-shirtcolor")))).getText();
		ValidationResult.validateData(actualshirtcolor, properties1.getProperty("expectedshirtcolor"),"Verifying Shirt Color");
		
		actualprice = driver.findElement(By.xpath((properties.getProperty("loc-verify-price")))).getText();
		ValidationResult.validateData(actualprice, properties1.getProperty("expectedprice"),"Verifying Shirt Price");
		
		actualtotal = driver.findElement(By.xpath((properties.getProperty("loc-verify-total")))).getText();
		ValidationResult.validateData(actualtotal, properties1.getProperty("expectedtotal"),"Verifying Total Amount");
		log.info("Validated Product Details");
		driver.findElement(By.xpath("loc-sendkey-quantity")).clear();
		sendkeys.sendKeys(driver, LocatorType.XPATH, properties.getProperty("loc-sendkey-quantity"), quantity);
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-update"));
		log.info("Successfully updated the quantity");
		
		actualtotalafterupdate = driver.findElement(By.xpath((properties.getProperty("loc-verify-totalafterupdate")))).getText();
		ValidationResult.validateData(actualtotalafterupdate, properties1.getProperty("expectedtotalafterupdate"),"Verifying Total Amount after updating");
		log.info("Completed Selecting Shirt and validating all details");
	}
}
