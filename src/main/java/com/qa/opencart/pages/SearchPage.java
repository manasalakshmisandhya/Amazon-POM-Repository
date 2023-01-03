package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eutil;
	
	private static final Logger LOG = Logger.getLogger(SearchPage.class);


	By Searchbox = By.id("twotabsearchtextbox");
	By Searchicon = By.id("nav-search-submit-button");

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);

	}

	public String pagetitle() {
		String title = eutil.waitForTitleIs(AppConstants.DEFAULT_TIMEOUT, AppConstants.HOME_PAGE_TITLE);
		System.out.println("Title after login" + title);
		LOG.info("Title after login"+title);
		return title;
	}

	public boolean isSearchboxExist() {
		return eutil.doEleIsDisplayed(Searchbox);
	}

	public SearchResultsPage performSearch(String productname) {
		System.out.println("Searching for product:"+productname);
		if(isSearchboxExist())
		{
			System.out.println("entrering product details");
			eutil.doSendKeysWithWait(Searchbox, AppConstants.DEFAULT_LONG_TIMEOUT, productname);
			eutil.doClick(Searchicon);
			return new SearchResultsPage(driver);
		}
		else {
			System.out.println("search field is not present in the page");
			LOG.warn("search box doesn't exist");
			return null;
		}
	}
}
	
	
	

