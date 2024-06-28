package com.training.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.training.Base.BasePage;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "contentWrapper")
	WebElement home;
	
	@FindBy(css = "div[id=userNavButton]")
	WebElement userMenu;
	
	@FindBy(xpath = "//a[starts-with(@title,'Log')]")
	WebElement logout;
	
	public WebDriver getHomepageTitle() {
		getPageTitle();
		return driver;
	}
	
	public void getUserMenuPageTitle() {
		getPageTitle();
	}
	
	public WebDriver clickOnUserMenuInHomePage() {
		explicitWait(userMenu, 20);
		clickOnButtonOrBoxes(userMenu, "User Menu Drop Down");
		return driver;
	}
	
	public WebDriver clickOnLogoutInUserMenu() {
		explicitWait(logout, 20);
		clickOnButtonOrBoxes(logout, "Logout");
		return driver;
	}

}
