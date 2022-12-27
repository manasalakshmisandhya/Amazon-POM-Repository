package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eutil;

	By Products = By.cssSelector("img.s-image");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	public boolean isSearchSuccessful() {
		List<WebElement> searchlist = eutil.waitForElementsToBeVisible(Products, AppConstants.DEFAULT_LONG_TIMEOUT);
		int i = searchlist.size();
		System.out.println("Number of results:" + i);
		if (i > 0) {
			System.out.println("Search is success");
			return true;
		}
		return false;
	}

	public ProductInfoPage selectProduct(String productname) {
		String xpath="//span[text()='"+productname+"']";
		By product=By.xpath(xpath);
		eutil.doClick(product);
		return new ProductInfoPage(driver);
	}
}
