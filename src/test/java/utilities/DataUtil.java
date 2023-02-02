package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import base.BaseTest;

public class DataUtil extends BaseTest {

	
	@DataProvider(name = "dp")
	public Object[][] getData(Method result) {

		
		String steetName = result.getName();

		int rows = excel.getRowCount(steetName);
		int cols = excel.getColumnCount(steetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData(steetName, colNum, rowNum);

			}
		}

		return data;

	}
	
}
