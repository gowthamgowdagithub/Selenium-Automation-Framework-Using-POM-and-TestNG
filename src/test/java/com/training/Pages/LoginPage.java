package com.training.Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.training.Base.BasePage;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css = "input#username")
	WebElement userName;
	
	@FindBy(css = "input#password")
	WebElement password;
	
	@FindBy(id = "Login")
	WebElement login;
	
	@FindBy(css = "div#error")
	WebElement error;
	
	@FindBy(xpath = "//label[text()='Remember me']")
	WebElement rememberME;
	
	@FindBy(xpath = "//div[@id='username_container']/div")
	WebElement usernameDisplay;
	
	@FindBy(css = "input#rememberUn")
	WebElement rememberMeChecked;
	
	@FindBy(xpath = "//a[text()='Forgot Your Password?']")
	WebElement forgetPassword;
	
	@FindBy(xpath = "//div[@id='error']")	
	WebElement errorForWrongUserAndPassword;
	
	public void usernameData(String data) {
		explicitWait(userName, 20);
		enterData(userName, data, "UserName");
	}
	
	public void passwordData(String data) {
		explicitWait(password, 20);
		enterData(password, data, "Password");
	}
	
	public WebDriver clickButtonToLogin() {
		explicitWait(login, 20);
		clickOnButtonOrBoxes(login, "Login");
		return driver;
	}
	
	public void getTheDisplayedErrorMessageOnScreen(String data) {
		explicitWait(error, 10);
		getErrorMessageDisplayed(error, data);
	}
	
	public void clickOnRememberMeCheckBox() {
		explicitWait(rememberME, 20);
	clickOnButtonOrBoxes(rememberME, "Remember Me.");	
	}
	
	public void checkUserNameIsDisplayed(String data) {
		explicitWait(usernameDisplay, 20);
		verifyElementIsDisplay(usernameDisplay, data, "User Name Display");
	}
	
	public String getThePageTitle() {
		return getPageTitle();
	}
	
	public void getErrorForWrongUserAndPassword(String data) {
		explicitWait(errorForWrongUserAndPassword, 10);
	getErrorMessageDisplayed(errorForWrongUserAndPassword, data);	
	}
	
	public WebDriver clickOnForgetPasswordLink() {
		explicitWait(forgetPassword, 10);
		clickOnButtonOrLinks(forgetPassword, "Forget password Link");
		return driver;
	}
	
	public void verifyTheCheckedElementInLoginPageIsSelected() {
		explicitWait(rememberMeChecked, 10);
		verifyCheckedElementAreSelected(rememberMeChecked, "Remember Me checkBox is selected");
	}
	
	public void login_ToSalesForce(String userName, String password) {
		usernameData(userName);
		passwordData(password);
		clickButtonToLogin();
	}
}
