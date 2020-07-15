package com.wordpress.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wordpress.testdata.PageGeneratorManager;

import browserFactory.BrowserDriverFactory;
import browserFactory.DriverManager;
import common.AbstractTest;
import pageObjects.WordPress.DashBoardPageObject;
import pageObjects.WordPress.LoginPageObject;

public class Login_10_Assert_Verify extends AbstractTest{
	WebDriver driver;
	DriverManager driverManager;
	
	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginUrl = loginPage.getLoginPageUrl();
	}

	@BeforeMethod
	public void beforeMethod() {
		loginPage.openLoginPage(loginUrl);
	}

	@Test
	public void Validate_01_EmptyEmail() {
		// loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox("");
		loginPage.clickToContinueOrLoginButton();
		verifyEquals(loginPage.getEmailOrPwErrorMsg(), "Please enter a username or email address.");
	}

	@Test
	public void Validate_02_InvalidEmail() {
		// loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox("123@123.123");
		loginPage.clickToContinueOrLoginButton();
		verifyEquals(loginPage.getEmailOrPwErrorMsg(), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExits() {
		// loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox("lamviet1" + randomNumber() + "@gmail.com");
		loginPage.clickToContinueOrLoginButton();
		verifyEquals(loginPage.getEmailOrPwErrorMsg(), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPw() {
		// loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPwTextBox("");
		loginPage.clickToContinueOrLoginButton();
		verifyEquals(loginPage.getEmailOrPwErrorMsg(), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PwLessThan6chars() {
		// loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPwTextBox("1234567");
		loginPage.clickToContinueOrLoginButton();
		verifyEquals(loginPage.getEmailOrPwErrorMsg(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPw() {
		// loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPwTextBox("automationfc");
		loginPage.clickToContinueOrLoginButton();
		dashboardPage = new DashBoardPageObject(driver);
		verifyTrue(dashboardPage.isHeaderTextDisplay());

	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
	String loginUrl;
	LoginPageObject loginPage;
	DashBoardPageObject dashboardPage;
}
