package com.training.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.training.Base.BasePage;

public class ForgetPasswordPage extends BasePage{
	
	public ForgetPasswordPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='un']")
	WebElement username;
	
	@FindBy(xpath = "//input[@id='continue']")
	WebElement continueButton;
	
	public void userNameField(String data) {
		explicitWait(username, 20);
		enterData(username, data, "UserName");
	}
	
	public WebDriver clickOnContinue() {
		explicitWait(continueButton, 20);
		clickOnButtonOrBoxes(continueButton, "Continue");
		return driver;
	}
	
	public void getForgetPageTitle() {
		getPageTitle();
	}

}
