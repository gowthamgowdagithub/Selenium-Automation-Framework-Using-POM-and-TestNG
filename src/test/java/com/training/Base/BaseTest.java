package com.training.Base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.training.Utilities.Constants;
import com.training.Utilities.Log4j2Utility;
import com.training.Utilities.PropertyUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	Logger mylogger=Log4j2Utility.getLogger(BaseTest.class);
	
	@BeforeMethod
	@Parameters("browserName")
	public void beforeMethodStart(@Optional("chrome") String BrowserName) {
		Log4j2Utility.logInfo("-------BeforeMethodStart-----");
		launchBrowser(BrowserName);
		String url=PropertyUtility.readDataFromFile(Constants.SalesForce_Application, "url");
		getUrl(url);
	}
	
	@AfterMethod
	public void afterMethodEnds() {
		Log4j2Utility.logInfo("--------AfterMethodsEnds------");
		closeAllTheBrowsers();
	}
	
		
		public void launchBrowser(String browserName){
			switch(browserName.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				Log4j2Utility.logInfo("The Chrome Browser is launched.");
				driver.manage().window().maximize();
				Log4j2Utility.logInfo("Window has been maximized.");
				break;
				
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
				Log4j2Utility.logInfo("The Edge Browser is launched.");
				driver.manage().window().maximize();
				Log4j2Utility.logInfo("Window has been maximized.");
				break;
				
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				Log4j2Utility.logInfo("The FireFox Browser is launched.");
				driver.manage().window().maximize();
				Log4j2Utility.logInfo("Window has been maximized.");
				break;
				
			default:
				Log4j2Utility.logError("The unknown browser is launched.");
			}
		}
		
		public void getUrl(String url){
			driver.get(url);
			Log4j2Utility.logInfo("The application url has been entered.");
		}
		
		public void closeBrowser() {
			driver.close();
			Log4j2Utility.logInfo("All the testCases are passed so browser is closed.");
		}
		
		public void closeAllTheBrowsers() {
			driver.quit();
			Log4j2Utility.logInfo("All the testCases are passed so browser is closed.");
		}
		
		public void takeScreenShotOfFullPage(String path) throws IOException {
			TakesScreenshot screenCapture=(TakesScreenshot)driver;
			File sourceFile=screenCapture.getScreenshotAs(OutputType.FILE);
			File destinationFile=new File(path);
			FileUtils.copyFile(sourceFile, destinationFile);
			//Files.copy(sourceFile, destinationFile);
			Log4j2Utility.logInfo("Screen Shot has been taken.");
		}
}
