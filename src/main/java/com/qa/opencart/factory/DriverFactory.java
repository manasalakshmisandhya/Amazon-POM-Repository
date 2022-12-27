package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	private static final Logger LOG = Logger.getLogger(DriverFactory.class);

	public WebDriver initDriver(Properties prop) {
		String Browser = prop.getProperty("browser").toLowerCase();
		System.out.println("Launching Browser" + Browser);
		LOG.info("Launching Browser" + Browser);
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);

		switch (Browser) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

		default:
			System.out.println("Plz pass right browser");
		LOG.error("Plz pass right browser");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;

		// mvn clean install -Denv="dev"
		// mvn clean install
		String envName = System.getenv("env");
		System.out.println("-----> Running test cases on environment: ----->" + envName);
		 LOG.info("-----> Running test cases on environment: ----->" + envName);
		if (envName == null) {
			System.out.println("No env is given..hence running it on prod env.....");
			try {
				ip = new FileInputStream(
						"D:\\JAVA WORKSPACE\\POMSeries\\src\\test\\resources\\config\\config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				switch (envName) {
				case "qa":
					ip = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
					break;

				case "prod":
					ip = new FileInputStream(
							"D:\\JAVA WORKSPACE\\POMSeries\\src\\test\\resources\\config\\config.properties");
					break;

				default:
					System.out.println("please pass the right env name...." + envName);
					// throw new FrameworkException(AppError.ENV_NOT_FOUND);
					break;
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public static String getScreenshot() {
		File ssfile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(ssfile, destination);

		} catch (IOException e) {
			printStackTrace(e);
		}
		return path;
	}

	private static void printStackTrace(IOException e) {
		// TODO Auto-generated method stub

	}

}
