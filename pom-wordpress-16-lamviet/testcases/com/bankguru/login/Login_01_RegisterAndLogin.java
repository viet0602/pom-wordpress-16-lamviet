package com.bankguru.login;

import org.testng.annotations.Test;

import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_01_RegisterAndLogin {
	WebDriver driver;
	String rootFolderPath = System.getProperty("user.dir");
	LoginPageObject loginPage;
	HomePageObject homePage;
	RegisterPageObject registerPage;
	String userIDvalue, pwValue, loginPageUrl;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", rootFolderPath + "\\browerDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		loginPage = new LoginPageObject(driver);
		loginPageUrl = loginPage.getLoginUrl();
	}

	@Test
	public void TC_01_Register() {
		loginPage.clickToHereLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToEmailTextBox("viettesting" + randomNumber() + "@gmail.com");
		registerPage.clickToSubmitButton();
		userIDvalue = registerPage.getUserIDText();
		pwValue = registerPage.getPwText();
		registerPage.openLoginPage(loginPageUrl);
		loginPage = new LoginPageObject(driver);
		
	}

	@Test
	public void TC_02_Login() {
		loginPage.inputToUserIDTextBox(userIDvalue);
		loginPage.inputPwTextBox(pwValue);
		loginPage.clickToLoginButton();
		//Khởi tạo HomePage
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplay());
		
	}

	@AfterClass
	public void afterClass() {
		
	}

	public int randomNumber() {
		Random ran = new Random();
		return ran.nextInt(999999);
	}


}
