package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class BankManagerLoginTest extends BaseTest{

	@Test
	public void bankManagerLogin() {
		
		click("bmlBtn_CSS");
		Assert.assertTrue(isElementPresent("addCust_CSS"), "Bank manager NOT logged in Successfully"); 
		
	}
}
