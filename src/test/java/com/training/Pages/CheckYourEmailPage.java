package com.training.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.training.Base.BasePage;

public class CheckYourEmailPage extends BasePage {
	
	public CheckYourEmailPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@class='message']")
	WebElement message;
	
	
	public void getTheMessageDisplayOnScreen(String data) {
		explicitWait(message, 20);
		getErrorMessageDisplayed(message, data);
	}
	
	public void getCheckEmailPageTitle() {
		getPageTitle();
	}


}
