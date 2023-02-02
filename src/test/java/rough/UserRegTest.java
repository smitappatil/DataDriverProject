package rough;

import org.testng.annotations.Test;

import base.BaseTest;
import utilities.DataUtil;

public class UserRegTest extends BaseTest {
	
	@Test (dataProviderClass = DataUtil.class , dataProvider = "dp")
	public void userRegTest(String firstname, String lastname, String age) {
		
		System.out.println("firstname : "+ firstname);
		System.out.println("lastname : "+ lastname);
		System.out.println("age : "+ age);
		
	}

	
	
	
	
}
