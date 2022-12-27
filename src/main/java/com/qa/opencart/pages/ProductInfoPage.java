package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eutil;
	
	private By imgcount= By.cssSelector("li.imageThumbnail");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eutil = new ElementUtil(driver);
	}

	public String getProductPageTitle() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.PAGE_DOWN).build().perform();
		String title = driver.getTitle();
		System.out.println(title);
		return title;
	}
	
	public int getImagesCount() {
		int nuofImages=eutil.waitForElementsToBeVisible(imgcount, AppConstants.DEFAULT_LONG_TIMEOUT).size();
		return nuofImages;
	}
	
	

}
