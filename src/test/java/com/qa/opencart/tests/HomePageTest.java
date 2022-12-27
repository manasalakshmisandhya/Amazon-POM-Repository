package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-20:Amazon homepage test")
@Story("US-10: Home page test features")
public class HomePageTest extends BaseTest {
	
@Description("Homepage title test")
@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
    public void homePageTitleTest() {
		String actualTitle = hp.getHomePageTitle();
		Assert.assertEquals(actualTitle,AppConstants.HOME_PAGE_TITLE);
	}
@Description("Homepage search test-- Owned section @manasa")
	@Severity(SeverityLevel.NORMAL)
@Test(priority=2)
	public void searchBoxTest() {
		Assert.assertTrue(hp.isSerachboxExist());
	}

@Description("Homepage search test-- Owned section @Sam")
@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void logoTest() {
		Assert.assertTrue(hp.islogoExist());
	}
}

