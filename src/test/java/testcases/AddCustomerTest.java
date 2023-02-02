package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;


public class AddCustomerTest extends BaseTest {
	
	@Test (dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void addCustomerTest(String firstname, String lastname, String postcode ) throws InterruptedException {
		
		click("addCust_CSS");
		sendKeys("firstName_CSS",firstname);
		sendKeys("lastName_CSS",lastname);
		sendKeys("postcode_CSS",postcode);
		Thread.sleep(2000);
		click("addCustBtn_CSS");
		
		Thread.sleep(2000);
		
		alert();
//		Alert alert = driver.switchTo().alert();
//		
//		Assert.assertTrue(alert.getText().contains("Customer added successfully"), "Customer not added Successfully");
//		alert.accept();

		
	}
}
