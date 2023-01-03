package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
DriverFactory df;
public WebDriver driver;
public Properties prop;

public HomePage hp;
public LoginPage lp;
public SearchPage sp;
public SearchResultsPage srp;
public ProductInfoPage productinfo;

 	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.initProp();
		driver = df.initDriver(prop);
 hp = new HomePage(driver);
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
		
	
}
