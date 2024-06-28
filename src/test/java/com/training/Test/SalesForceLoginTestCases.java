package com.training.Test;
import org.testng.annotations.Test;
import com.training.Base.BaseTest;
import com.training.Pages.HomePage;
import com.training.Pages.LoginPage;
import com.training.Pages.CheckYourEmailPage;
import com.training.Pages.ForgetPasswordPage;
import com.training.Utilities.Constants;
import com.training.Utilities.PropertyUtility;


public class SalesForceLoginTestCases extends BaseTest{

	@Test()
	public void TestCase1() {
		String UserName=PropertyUtility.readDataFromFile(Constants.SalesForce_Application, "username");
		String expectedAlert="Please enter your password.";
		LoginPage loginpage=new LoginPage(driver);
		loginpage.usernameData(UserName);
		loginpage.passwordData("");
		driver=loginpage.clickButtonToLogin();
		loginpage.getTheDisplayedErrorMessageOnScreen(expectedAlert);
	}

	
	@Test()
	public void TestCase2() {
		String UserName=PropertyUtility.readDataFromFile(Constants.SalesForce_Application, "username");
		String Password=PropertyUtility.readDataFromFile(Constants.SalesForce_Application, "password");
		LoginPage loginpage=new LoginPage(driver);
		loginpage.usernameData(UserName);
		loginpage.passwordData(Password);
		driver=loginpage.clickButtonToLogin();
		HomePage homepage=new HomePage(driver);
		homepage.getHomepageTitle();
	}
	
	@Test()
	public void TestCase3() {
		String expectedData="katte@tekarch.com";
		String UserName=PropertyUtility.readDataFromFile(Constants.SalesForce_Application, "username");
		String Password=PropertyUtility.readDataFromFile(Constants.SalesForce_Application, "password");
		LoginPage loginpage=new LoginPage(driver);
		loginpage.usernameData(UserName);
		loginpage.passwordData(Password);
		loginpage.clickOnRememberMeCheckBox();
		driver=loginpage.clickButtonToLogin();
		HomePage homepage=new HomePage(driver);
		homepage.getHomepageTitle();
		homepage.clickOnUserMenuInHomePage();
		homepage.getHomepageTitle();
		driver=homepage.clickOnLogoutInUserMenu();
		loginpage.checkUserNameIsDisplayed(expectedData);
		loginpage.verifyTheCheckedElementInLoginPageIsSelected();	
	}
	
	@Test()
	public void TestCase4() {
		String expectedData="We’ve sent you an email with a link to finish resetting your password. Can’t find the email? Try checking your spam folder. If you still can’t log in, have us resend the email or contact your Salesforce administrator.";
		String UserName=PropertyUtility.readDataFromFile(Constants.SalesForce_Application, "username");
		LoginPage loginpage=new LoginPage(driver);
		loginpage.usernameData(UserName);
		loginpage.getThePageTitle();
		driver=loginpage.clickOnForgetPasswordLink();
		ForgetPasswordPage forgetpage=new ForgetPasswordPage(driver);
		forgetpage.userNameField(UserName);
		forgetpage.getForgetPageTitle();
		driver=forgetpage.clickOnContinue();
		CheckYourEmailPage check=new CheckYourEmailPage(driver);
		check.getCheckEmailPageTitle();
		check.getTheMessageDisplayOnScreen(expectedData);
	}
	
	@Test
	public void TestCase5() {
		String expectedText="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		LoginPage loginpage=new LoginPage(driver);
		loginpage.getThePageTitle();
		loginpage.usernameData("123");
		loginpage.passwordData("22131");
		loginpage.clickButtonToLogin();
		loginpage.getTheDisplayedErrorMessageOnScreen(expectedText);
	}
}
