package com.atmecs.testscripts;

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

public class TestFirstProduct extends TestBase {
	/*
	 * In this class,validating home page,selecting one product in products list
	 */
	Properties properties, properties1;
	static String actualhomepage, actualstock, productprice, actualextax, actualdescription, actualsubtotal,
			actualecotax, actualvat;
	ClickOnElementAction click = new ClickOnElementAction();
	SendKeysAction sendkeys = new SendKeysAction();
	ReadExcelFile readexcel = new ReadExcelFile();

	@DataProvider(parallel = true)
	public Object[][] inputValues() {
		Object data[][] = readexcel.readExcel("Sheet1", ConstantsFilePaths.TESTDATA_FILE1);
		return data;
	}

	@Test(dataProvider = "inputValues", priority = 1)
	/*
	 * Data provider is using to read the data from excel file
	 */
	public void testingHomePage(String firstproduct, String quantity) throws Exception {

		// locators are reading through LOCATOR_FILE
		properties = ReadLocatorsFile.loadProperty(ConstantsFilePaths.LOCATOR_FILE);
		// expected data are reading through TESTDATA_FILE
		properties1 = ReadLocatorsFile.loadProperty(ConstantsFilePaths.TESTDATA_FILE);
		try {
			actualhomepage = driver.findElement(By.xpath((properties.getProperty("loc-verify-homepage-content")))).getText();
			ValidationResult.validateData(actualhomepage, properties1.getProperty("expectedhomepage"),"Verifying Home Page Content");
			log.info("Successfully Validated Home Page");
			log.info("******************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-bttn-search"));
		sendkeys.sendKeys(driver, LocatorType.XPATH, properties.getProperty("loc-click-bttn-search"), firstproduct);
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-search"));
		log.info("Selected Iphone Product");
		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-iphone"));

		actualstock = driver.findElement(By.xpath((properties.getProperty("loc-verify-availability")))).getText();
		ValidationResult.validateData(actualstock, properties1.getProperty("expectedstock"),"Verifying availability stock");

		productprice = driver.findElement(By.xpath((properties.getProperty("loc-verify-productprice")))).getText();
		ValidationResult.validateData(productprice, properties1.getProperty("expectedprice"),"Verifying product price");

		actualextax = driver.findElement(By.xpath((properties.getProperty("loc-verify-extax")))).getText();
		ValidationResult.validateData(actualextax, properties1.getProperty("expectedextax"), "Verifying ex tax");

		sendkeys.sendKeys(driver, LocatorType.XPATH, properties.getProperty("loc-sendkeys-quantity"), quantity);

		actualdescription = driver.findElement(By.xpath((properties.getProperty("loc-verify-description")))).getText();
		ValidationResult.validateData(actualdescription, properties1.getProperty("expecteddescription"),"Verifying product description");

		click.clickElement(driver, LocatorType.XPATH, properties.getProperty("loc-click-addcart"));
		log.info("Added product to cart");
		log.info("Successfully selected and validate for first product");

	}
}