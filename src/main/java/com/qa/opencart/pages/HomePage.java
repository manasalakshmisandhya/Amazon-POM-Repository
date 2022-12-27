package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;

	private By Searchbox = By.id("twotabsearchtextbox");
	private By Logo = By.id("nav-logo-sprites");
	By loginnav=By.id("nav-link-accountList");
	By email = By.id("ap_email");
	By pswd = By.id("ap_password");
	By SignInBtn = By.id("signInSubmit");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public String getHomePageTitle() {
		String homepageTitle = driver.getTitle();
		System.out.println("Home Page title=" + homepageTitle);
		return homepageTitle;
	}

	public boolean isSerachboxExist() {
		return driver.findElement(Searchbox).isDisplayed();
	}

	public boolean islogoExist() {
		return driver.findElement(Logo).isDisplayed();
	}
	public LoginPage loginnavigation() {
		driver.findElement(loginnav).click();
		
		return new LoginPage(driver);
	}
}
