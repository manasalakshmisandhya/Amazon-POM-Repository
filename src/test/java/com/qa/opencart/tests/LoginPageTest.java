package com.qa.opencart.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@BeforeClass
	public void signinnavi() {
		lp = hp.loginnavigation();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String title = lp.LoginPagetitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void login() {
		sp = lp.enteremail(prop.getProperty("username"), prop.getProperty("password"));
		String title = sp.pagetitle();
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);

	}

	@Test(priority = 3)
	public void titletest() {
		String title = sp.pagetitle();
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}

	@Test(priority = 4)
	public void isSearchExistTest() {
		Assert.assertTrue(sp.isSearchboxExist());

	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] { { "Pokemon toy" },
				 };
				 
	}

	@Test(priority = 5, dependsOnMethods = "isSearchExistTest",  dataProvider = "getSearchData")
	public void searchTest(String key) {
		srp = sp.performSearch(key);
		Assert.assertTrue(srp.isSearchSuccessful());
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Pokemon toy", "Takaratomy Pokemon Sun And Moon Mini Action Figure (Multicolor)"},
				{ "flower plant", "Hbriyd black rose flower plant" },
				{ "Ecotribe apron", "Ecotribe Premium Cotton Kitchen Apron With Center Pocket, Free Size - Set of 1" },
				{"iphone11", "Apple iPhone 11 (128GB) - White"}};
	
	}

	@Test(priority = 6,  dataProvider = "getProductData")
	public void searchresultsTest(String key, String product) {
		srp = sp.performSearch(key);
		productinfo = srp.selectProduct(product);
		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>(handles);
		String pwid = handlesList.get(0);
		System.out.println(pwid);
		String cwid = handlesList.get(1);
		System.out.println(cwid);

		driver.switchTo().window(cwid);
		String actualProductTitle= productinfo.getProductPageTitle();
		if (actualProductTitle.contains(product)) {
			Assert.assertTrue(true);
		}
		driver.close();
		driver.switchTo().window(pwid);

	}
	
	@DataProvider
	public Object[][] getImagesCount() {
		return new Object[][] { { "Pokemon toy", "Takaratomy Pokemon Sun And Moon Mini Action Figure (Multicolor)",AppConstants.POKEMON_TOY_IMAGES },
				{ "flower plant", "Hbriyd black rose flower plant",AppConstants.PLANT_IMAGES },
				{ "Ecotribe apron", "Ecotribe Premium Cotton Kitchen Apron With Center Pocket, Free Size - Set of 1",AppConstants.APRON_IMAGES },
				{"iphone11", "Apple iPhone 11 (128GB) - White",AppConstants.IPHONE_11_IMAGES}};
	}
	
	@Test(priority = 7,  dataProvider = "getImagesCount")
	public void productImagesCountTest(String key, String product,int imagesCount) {
		srp = sp.performSearch(key);
		productinfo = srp.selectProduct(product);
		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>(handles);
		String pwid = handlesList.get(0);
		System.out.println(pwid);
		String cwid = handlesList.get(1);
		System.out.println(cwid);

		driver.switchTo().window(cwid);
		int actImgcount=productinfo.getImagesCount();
		System.out.println(actImgcount);
		Assert.assertEquals(actImgcount, imagesCount);
		driver.close();
		driver.switchTo().window(pwid); 

	}

}
