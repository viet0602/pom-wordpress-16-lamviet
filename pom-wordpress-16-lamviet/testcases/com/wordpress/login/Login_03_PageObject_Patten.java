package com.wordpress.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wordpress.testdata.PageGeneratorManager;

import common.AbstractPage;
import pageObjects.WordPress.DashBoardPageObject;
import pageObjects.WordPress.LoginPageObject;

public class Login_03_PageObject_Patten extends AbstractPage {
	WebDriver driver;
	String rootFolderPath = System.getProperty("user.dir");
	String loginUrl;
	LoginPageObject loginPage;
	DashBoardPageObject dashboardPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", rootFolderPath + "\\browerDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.wordpress.com/wp-admin");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginUrl = loginPage.getLoginPageUrl();
	}

	@Test
	public void Validate_01_EmptyEmail() {
		loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox("");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPwErrorMsg(), "Please enter a username or email address.");
	}

	@Test
	public void Validate_02_InvalidEmail() {
		loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox("123@123.123");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPwErrorMsg(), "Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExits() {	
		loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox( "lamviet1" + randomNumber() + "@gmail.com");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPwErrorMsg(), "User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPw() {
		loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPwTextBox("");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPwErrorMsg(), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PwLessThan6chars() {
		loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox( "automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPwTextBox("1234567");
		loginPage.clickToContinueOrLoginButton();
		Assert.assertEquals(loginPage.getEmailOrPwErrorMsg(), "Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPw() {		
		loginPage.openLoginPage(loginUrl);
		loginPage.inputToEmailTextBox( "automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPwTextBox("automationfc");
		loginPage.clickToContinueOrLoginButton();
		dashboardPage = new DashBoardPageObject(driver);
		Assert.assertTrue(dashboardPage.isHeaderTextDisplay());

	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);

	}

	@AfterClass
	public void afterClass() {
	}
}
