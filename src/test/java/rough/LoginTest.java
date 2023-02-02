package rough;

import org.openqa.selenium.devtools.v102.debugger.model.Scope.Type;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;
import utilities.ExcelReader;

public class LoginTest extends BaseTest{

	
	//@Test (dataProvider = "dp")
	@Test (dataProviderClass = DataUtil.class , dataProvider = "dp")
	public void loginTest(String userName, String password) throws InterruptedException {
		
		
		sendKeys("username_ID",userName);
		click("nextBtn_XPATH");
	//	sendKeys("password_CSS", password);
		
	}
	
	
//	@DataProvider(name = "dp")
//	public Object[][] getData() {
//
//		
//		String steetName = "LoginTest";
//
//		int rows = excel.getRowCount(steetName);
//		int cols = excel.getColumnCount(steetName);
//
//		Object[][] data = new Object[rows - 1][cols];
//
//		for (int rowNum = 2; rowNum <= rows; rowNum++) {
//			for (int colNum = 0; colNum < cols; colNum++) {
//
//				data[rowNum - 2][colNum] = excel.getCellData(steetName, colNum, rowNum);
//
//			}
//		}
//
//		return data;
//
//	}
	
	
}
