
package testcases;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;

public class OpenAccountTest extends BaseTest {

	@Test (dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void openAccountTest(String customerName, String currency) throws InterruptedException {
		
		click("openAccBtn_CSS");
		select("customer_CSS", customerName);
		select("currenct_CSS", currency);
		click("processBtn_CSS");
		Thread.sleep(2000);

		alert();
//		Alert alert = driver.switchTo().alert();
//		Thread.sleep(2000);
//		Assert.assertTrue(alert.getText().contains("Account created successfully"), "Account not created successfully");
//		alert.accept();
	}
}
