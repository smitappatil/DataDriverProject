package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import extentlisteners.ExtentListeners;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.DbManager;
import utilities.ExcelReader;
import utilities.MonitoringMail;

public class BaseTest {

	/*
	 * webdriver, testng, apache poi, extent listener excel reader, monitoring mail
	 */

	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public static Logger log = Logger.getLogger(BaseTest.class);
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
	public static MonitoringMail mail = new MonitoringMail();

	
	
	
	public boolean isElementPresent(String locator) {

		try {
			if (locator.endsWith("ID")) {

				driver.findElement(By.id(OR.getProperty(locator)));

			} else if (locator.endsWith("XPATH")) {

				driver.findElement(By.xpath(OR.getProperty(locator)));

			} else if (locator.endsWith("CSS")) {

				driver.findElement(By.cssSelector(OR.getProperty(locator)));
			}

			log.info("Clicking on an Element : " + locator); // log4j

			ExtentListeners.test.info("Clicking on an Element : " + locator); // Extent report

			return true;
			
		} catch (Throwable t) {

			log.info("Error while clicking an Element : " + locator + " Error Message : " + t.getMessage());
			ExtentListeners.test
					.fail("Error while clicking an Element : " + locator + " Error Message : " + t.getMessage());
			
			return false;
		}

	}
	
	public void click(String locator) {

		try {
			if (locator.endsWith("ID")) {

				driver.findElement(By.id(OR.getProperty(locator))).click();

			} else if (locator.endsWith("XPATH")) {

				driver.findElement(By.xpath(OR.getProperty(locator))).click();

			} else if (locator.endsWith("CSS")) {

				driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			}

			log.info("Clicking on an Element : " + locator); // log4j

			ExtentListeners.test.info("Clicking on an Element : " + locator); // Extent report

		} catch (Throwable t) {

			log.info("Error while chicking an Element : " + locator + " Error Message : " + t.getMessage());
			ExtentListeners.test
					.fail("Error while chicking an Element : " + locator + " Error Message : " + t.getMessage());
			Assert.fail(t.getMessage());
		}

	}

	public void sendKeys(String locator, String value) {

		try {
			
			if (locator.endsWith("ID")) {
				
				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);

			} else if (locator.endsWith("XPATH")) {

				driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);

			} else if (locator.endsWith("CSS")) {

				driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			}

			log.info("Entered element : " + locator + " and value entered is : " + value);
			ExtentListeners.test.info("Entered element : " + locator + " and value entered is : " + value);

		} catch (Throwable t) {

			log.info("Error while entreing the value : " + value + " Error message : " + t.getMessage());
			ExtentListeners.test
					.fail("Error while entreing the value : " + value + " Error message : " + t.getMessage());
			Assert.fail(t.getMessage());
		}

	}
	
	

	public void select(String locator, String value) {

		Select select = null;
		
		try {
			
			if (locator.endsWith("ID")) {
				
				select = new Select(driver.findElement(By.id(OR.getProperty(locator))));

			} else if (locator.endsWith("XPATH")) {
				
				select = new Select(driver.findElement(By.xpath(OR.getProperty(locator))));

			} else if (locator.endsWith("CSS")) {

				select = new Select(driver.findElement(By.cssSelector(OR.getProperty(locator))));
				
			}

			select.selectByVisibleText(value);
			
			log.info("Entered element : " + locator + " and value entered is : " + value);
			ExtentListeners.test.info("Entered element : " + locator + " and value entered is : " + value);

		} catch (Throwable t) {

			log.info("Error while entreing the value : " + value + " Error message : " + t.getMessage());
			ExtentListeners.test
					.fail("Error while entreing the value : " + value + " Error message : " + t.getMessage());
			Assert.fail(t.getMessage());
		}

	}
	
	public void alert() throws InterruptedException {
		Alert alert = driver.switchTo().alert();
		Thread.sleep(2000);
		Assert.assertTrue(alert.getText().contains("Account created successfully"), "Account not created successfully");
		alert.accept();
		driver.switchTo().defaultContent();
	}

	@BeforeSuite
	public void setUp() {

		if (driver == null) {

			PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");

			log.info("Test Suite Started !!!");

			try {
				fis = new FileInputStream("./src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				OR.load(fis);
				log.info("OR Property File Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream("./src/test/resources/properties/config.properties");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config Property File Loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			if (config.getProperty("browser").equalsIgnoreCase("chrome")) {

				// for headless execution set chrome options as true.
				
				ChromeOptions options = new ChromeOptions();
				options.setHeadless(false);
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				log.info("Launching Chrome Browser !!!");

			} else if (config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Launching Firefox Browser !!!");

			}

			driver.get(config.getProperty("testsiteurl"));
			log.info("Navigating to URL : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts()
					.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
			wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));

			try {
				DbManager.setMysqlDbConnection();
				log.info("DB Connection established");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
//	@BeforeMethod
//	@BeforeTest
//	public void launchBrowser() {
//
//		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
//
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//			log.info("Launching Chrome Browser !!!");
//
//		} else if (config.getProperty("browser").equals("firefox")) {
//
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//			log.info("Launching Firefox Browser !!!");
//
//		}
//
//		driver.get(config.getProperty("testsiteurl"));
//		log.info("Navigating to URL : " + config.getProperty("testsiteurl"));
//		driver.manage().window().maximize();
//		driver.manage().timeouts()
//				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
//		wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));
//
//	}
//
//	//@AfterMethod
//	@AfterTest
//	public void closeBrowser() {
//
//		driver.quit();
//	}

	@AfterSuite
	public void tearDown() {

		driver.quit();
		
		System.out.println("DB Connection closed.");
		log.info("Test Suite Ended !!!");

	}

}
