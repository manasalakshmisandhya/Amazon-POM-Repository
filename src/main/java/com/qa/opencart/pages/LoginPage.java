package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utilities.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private ElementUtil eutil;
	private WebDriver driver;
	
	private static final Logger LOG = Logger.getLogger(LoginPage.class);


	By email = By.id("ap_email");
	By continuebtn = By.id("continue");
	By pswd = By.id("ap_password");
	By SigninBtn = By.id("signInSubmit");
	By rememberMe = By.name("rememberMe");
	By successfullogin = By.id("nav-link-accountList-nav-line-1");
	

	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eutil = new ElementUtil(driver);

	}
	public String LoginPagetitle() {
		String lptitle = eutil.waitForTitleIs(AppConstants.DEFAULT_LONG_TIMEOUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("LoginPAge title:"+lptitle);
		LOG.info("LoginPAge title:"+lptitle);
		return lptitle;
	}
	@Step("login test with username:{0} and password:{1}")
	public SearchPage enteremail(String username, String password) {
		
		System.out.println("entering email");
		LOG.warn("entering email");

		eutil.doSendKeysWithWait(email, AppConstants.DEFAULT_TIMEOUT, username);
		eutil.doClick(continuebtn);

		System.out.println("Entering password");
		LOG.warn("entering password");

		eutil.doSendKeysWithWait(pswd, AppConstants.DEFAULT_TIMEOUT, password);
		eutil.doClick(rememberMe);
		eutil.doClick(SigninBtn);
		
		return new SearchPage(driver);
 	}

	
	

	
	
	
	
		
	

}
