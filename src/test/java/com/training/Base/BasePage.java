package com.training.Base;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.io.Files;
import com.training.Utilities.Log4j2Utility;

public class BasePage {
	public static WebDriver driver;
	Logger mylogger=Log4j2Utility.getLogger(BasePage.class);
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterData(WebElement element, String data, String objectName) {
		if(element.isDisplayed()) {
		   element.clear();
			element.sendKeys(data);
			Log4j2Utility.logInfo("The "+data+" has been entered in "+objectName+" textbox.");
		}
		else {
			Log4j2Utility.logError("The "+objectName+" is not identified.");
		}
	}
	
	public void clickOnButtonOrBoxes(WebElement element, String objectName) {
		if(!element.isSelected()) {
			element.click();
			Log4j2Utility.logInfo("The "+objectName+" is clicked.");
		}
		else {
			Log4j2Utility.logError("The "+objectName+" is not visible.");
		}
	}
	
	public void clickOnButtonOrLinks(WebElement element, String objectName) {
		if(element.isDisplayed()) {
			element.click();
			Log4j2Utility.logInfo("The "+objectName+" is clicked.");
		}
		else {
			Log4j2Utility.logError("The "+objectName+" is not visible.");
		}
	}
	
	public void getErrorMessageDisplayed(WebElement element, String expectText){
		String actualText=element.getText();
		Log4j2Utility.logInfo(actualText);
		String expectedText=expectText;
		if(actualText.equals(expectedText)) {
			Log4j2Utility.logInfo("The actual and expected text are same.");
		}
		else {
			Log4j2Utility.logError("The actual and expected text are not same.");
		}
	}
	
	public void verifyElementIsDisplay(WebElement element, String dataExpected, String objectName) {
		if(element.isDisplayed()) {
			Log4j2Utility.logInfo("The "+objectName+" is displayed.");
			String actualText=element.getText();
		    Log4j2Utility.logInfo(actualText);
			String expectedText=dataExpected;
			if(actualText.equals(expectedText)) {
			Log4j2Utility.logInfo("The actual "+objectName+" is same as expected "+objectName+ ".");
			}
			else {
				Log4j2Utility.logError("The "+objectName+" is not displayed and expected text is not similar.");
			}
		}
	}
	
	public void verifyCheckedElementAreSelected(WebElement element, String objectName) {
		if(element.isSelected()) {
			Log4j2Utility.logInfo("The "+objectName+" check box is selected.");
		}
		else {
			Log4j2Utility.logError("The "+objectName+" check box is not selected.");
		}
	}
	
	public void waitForFewSeconds(int time) throws InterruptedException {
		Thread.sleep(time);
	}
	
	public void closeBrowser() {
		driver.close();
		Log4j2Utility.logInfo("All the testCases are passed so browser is closed.");
	}
	
	public void closeAllTheBrowsers() {
		driver.quit();
		Log4j2Utility.logInfo("All the testCases are passed so browser is closed.");
	}
	
	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		Log4j2Utility.logInfo("Wait has been added.");
	}
	
	public void explicitWait(WebElement element, int time) {
		WebDriverWait wait=new WebDriverWait(driver,time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void performAction(WebElement element, String objectName) {
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
		Log4j2Utility.logInfo("The "+objectName+" has been selected or clicked.");
	}
	
	public void windowAlertHandle(WebElement element, String data, String objectName) {
		element.click();
		Log4j2Utility.logInfo("The "+objectName+" is clicked.");
		Alert al=driver.switchTo().alert();
		String alertText=al.getText();
		Log4j2Utility.logInfo(alertText);
		String ExpectedAlertText=data;
		if(alertText.equals(ExpectedAlertText)) {
			Log4j2Utility.logInfo("The alert text are same.");
		}
		else {
			Log4j2Utility.logError("The alert text are not similar.");
		}
		al.accept();
		Log4j2Utility.logInfo("The Window pop is accepted.");
	}
	
	public Alert switchToAlert() {
		Alert al=driver.switchTo().alert();
		return al;
	}
	
	public String getAlertText(Alert a) {
		Alert al=driver.switchTo().alert();
		String alertText=al.getText();
		Log4j2Utility.logInfo("The alert Text is: "+alertText);
		return alertText;
		
	}
	public void windowAlert() {
		Alert al=driver.switchTo().alert();
		String alertText=al.getText();
		Log4j2Utility.logInfo("The alert Text is: "+alertText);
		al.accept();
		Log4j2Utility.logInfo("The window alert has been accepted.");
	}
	
	public void promtAlertHandle(WebElement element, String textBoxData, String objectName) {
		element.click();
		Log4j2Utility.logInfo("The "+objectName+" pop up is clicked.");
		Alert alertHandle=driver.switchTo().alert();
		alertHandle.sendKeys(textBoxData);
		Log4j2Utility.logInfo("The data is entered inside the textbox.");
	}
	
	public void acceptAlert(Alert a) {
		Alert al=driver.switchTo().alert();
		al.accept();
		Log4j2Utility.logInfo("The promt alert pop up is accepted.");
	}
	
	public void dismissAlert(Alert a) {
		Alert al=driver.switchTo().alert();
		al.dismiss();
		Log4j2Utility.logInfo("The promt alert pop up is cancled.");
	}
	
	public void getAllLinksUsingTagName(String tagName) {
		Log4j2Utility.logInfo("List of links are:");
		List<WebElement>allItems=driver.findElements(By.tagName(tagName));
		for(WebElement element:allItems) {
			if(element.isDisplayed()) {
				String elementName=element.getText();
				Log4j2Utility.logInfo(elementName);
			}	
		}
	}
	
	public void getAllItemsUsingXpath(String tagName) {
		Log4j2Utility.logInfo("List of items are:");
		List<WebElement>allItems=driver.findElements(By.xpath(tagName));
		for(WebElement element:allItems) {
			if(element.isDisplayed()) {
				String elementName=element.getText();
				Log4j2Utility.logInfo(elementName);
			}	
		}
	}
	
	public void verifyPageTitle(String titleExpected) {
		String actualTitle=driver.getTitle();
		Log4j2Utility.logInfo(actualTitle);
		String expectedTitle=titleExpected;
		if(actualTitle.equals(expectedTitle)) {
			Log4j2Utility.logInfo("Both title are same.");
		}
		else {
			Log4j2Utility.logError("Both title are not same.");
		}
	}
	
	public void windowHandle(String windowName, String expectPageTitle) {
		String windowName1=driver.getWindowHandle();
		Log4j2Utility.logInfo("The parent window ID is: "+windowName1);
		String getPageTitle=driver.getTitle();
		String expectedPageTitle=expectPageTitle;
		if(getPageTitle.equals(expectedPageTitle)) {
			Log4j2Utility.logInfo("The title of open page title is: "+getPageTitle);
			Log4j2Utility.logInfo("The actual and expected page title are equal.");
		}
		else {
			Log4j2Utility.logError("The actual and expected page title are not equal");
		}	
	}
	
	public void windowHandles(String windowName, String expectPageTitle, String expectedPageUrl) {
		String parentWindow=driver.getWindowHandle();
		for (String allWindows : driver.getWindowHandles()) {
			if(!allWindows.equals(parentWindow)) {
				WebDriver childWindow=driver.switchTo().window(allWindows);
				Log4j2Utility.logInfo("The childWindows ID:"+childWindow);
				String getPageTitle=driver.getTitle();
				String expectedPageTitle=expectPageTitle;
				if(getPageTitle.equals(expectedPageTitle)) {
					Log4j2Utility.logInfo("The title of open page title is: "+getPageTitle);
					String currentUrl=driver.getCurrentUrl();
					String expectedUrl=expectedPageUrl;
					if(currentUrl.equals(expectedUrl)) {
						Log4j2Utility.logInfo("Both the url's are same: ");
						Log4j2Utility.logInfo("The current url is: "+currentUrl);
					}
					else {
						 Log4j2Utility.logError("Both the url's are not same");
					}
					Log4j2Utility.logInfo("The actual and expected page title are equal.");
				}
				else {
					Log4j2Utility.logError("The actual and expected page title are not equal");
				}
			}
		}			
	}

	public void handleOuterFrame(WebElement element, String objectName) {
		if(element.isDisplayed()) {
			driver.switchTo().frame(element);
			Log4j2Utility.logInfo("The"+ objectName+" frame is identified and entered inside that");
		}
		else {
			Log4j2Utility.logError("There is no "+objectName+" present in the DOM.");
		}
	}
	
	public void handleInnerFrame(WebElement element, String objectName) {
		if(element.isDisplayed()) {
			driver.switchTo().frame(element);
			Log4j2Utility.logInfo("The"+ objectName+" frame is identified and entered inside that");
		}
		else {
			Log4j2Utility.logError("There is no "+objectName+" present in the DOM.");
		}
	}
	
	public void handleGetInfoFromFrame(WebElement element, String expectedFrameText) {
		if(element.isDisplayed()) {
			String actualText=element.getText();
			String expectedInnerFrameText=expectedFrameText;
			if(actualText.equals(expectedInnerFrameText)) {
				Log4j2Utility.logInfo("The actual Text extrated from the frame is : "+actualText);
				Log4j2Utility.logInfo("Both actual and expected text are same as we expect.");
			}
		}
		else {
			Log4j2Utility.logError("Both actual and expected text are not same as we expect.");
		}
	}
	
	public void getBackToDOM() {
		driver.switchTo().defaultContent();
		Log4j2Utility.logInfo("It came back to DOM from the frame..");
	}
	
	public void autoComplete(WebElement element1, String path, String countryWantToSelect, String expectedCountry) {
		element1.sendKeys(countryWantToSelect);
		
		List<WebElement>allItems=driver.findElements(By.xpath(path));
		for (WebElement country : allItems) {
			Log4j2Utility.logInfo("The selected country is:"+country.getText());
			if(country.getText().equalsIgnoreCase(expectedCountry)) {
				country.click();
				break;
			}
		}
	}
	
	public void handleTables(WebElement allTable, String path1, String path2) {
		List<WebElement>allheader=allTable.findElements(By.xpath(path1));
		for(WebElement dataPresent1:allheader) {
			Log4j2Utility.logInfo("The list of header are:");
			Log4j2Utility.logInfo(dataPresent1.getText()+" ");	
		}
		List<WebElement>allData=allTable.findElements(By.xpath(path2));
		for(WebElement dataPresent2:allData) {
			Log4j2Utility.logInfo("The list of data's are:");
			Log4j2Utility.logInfo(dataPresent2.getText()+" ");	
		}
	}
	
	public void getVisibleText(WebElement element) {
		String text=element.getText();
		Log4j2Utility.logInfo("The list of visible elements are:");
		Log4j2Utility.logInfo(text);
	}
	
	public void fileUpload(WebElement element, String path) {
		element.sendKeys(path);
		Log4j2Utility.logInfo("The file has been uploaded.");
	}
	
	public String getPageTitle() {
		String name=driver.getTitle();
		Log4j2Utility.logInfo("The title is displayed.");
	 Log4j2Utility.logInfo("Current page title is: "+name);
	 return name;	
	}
	
	public void selectDataFromDropDownByVisibleText(WebElement element, String text) {
		Select select=new Select(element);
		select.selectByVisibleText(text);	
		Log4j2Utility.logInfo("The "+text+" has been selected form the dropdown.");
	}
	
	public void getCurrentURL(String expectedURL) {
		String currentUrl=driver.getCurrentUrl();
		Log4j2Utility.logInfo("The current url of the page is: "+currentUrl);
		String ExpectedUrl=expectedURL;
		if(currentUrl.equals(ExpectedUrl)) {
			Log4j2Utility.logInfo("The current and expected url are same.");
		}
		else {
			Log4j2Utility.logError("The current and expected url are not same.");
		}
	}
	
	public void selectDataFromDropDownUsingValue(WebElement element, String value) {
		Select select=new Select(element);
		select.selectByValue(value);	
		Log4j2Utility.logInfo("The "+value+" has been selected form the dropdown.");
	}
	
	public void selectDataFromDropDownUsingIndex(WebElement element, int index) {
		Select select=new Select(element);
		select.selectByIndex(index);	
		Log4j2Utility.logInfo("The "+index+" has been selected form the dropdown.");
	}
	
	public void getSelectedOption(WebElement element) {
		Select dropdown=new Select(element);
		WebElement ActualselectedOptions=dropdown.getFirstSelectedOption();
		Log4j2Utility.logInfo("The selected option is: "+ActualselectedOptions.getText());
	}
	
	public void getErrorMessage(WebElement element, String expected) {
		String actualText=element.getText();
		String expectedText=expected;
		Log4j2Utility.logInfo("The actual error message is: "+actualText);
		if(actualText.equals(expectedText)) {
			Log4j2Utility.logInfo("The actual and expected error message are same.");
		}
		else {
			Log4j2Utility.logError("The actual and expected error message are not same.");
		}
	}
	
	public void identifyRequiredElementFound(WebElement element, String expected) {
		String text=element.getText();
		Log4j2Utility.logInfo("The list of visible elements are:");
		Log4j2Utility.logInfo(text);
		String expectedText=expected;
		if(text.equalsIgnoreCase(expectedText)) {
			Log4j2Utility.logInfo("The element present inside the drop down.");
		}
		else {
			Log4j2Utility.logError("There is no such element found in drop down.");
		}
	}
	
	public void identifyItIsLinkOrNot(WebElement element, String objectname) {
		if("a".equals(element.getTagName())) {
			Log4j2Utility.logInfo("The "+objectname+" is a link.");
		}
		else {
			Log4j2Utility.logError("The "+objectname+" is not a link.");
		}
	}
	
	public void takeScreenShotOfSingleElement(WebElement element, String path) {
		File sourceFile=element.getScreenshotAs(OutputType.FILE);
		File destFile=new File(path);
		try {
			Files.copy(sourceFile, destFile);
		} catch (IOException e) {
			Log4j2Utility.logError("The element is not found");
			e.printStackTrace();
		}
	}
	
}



