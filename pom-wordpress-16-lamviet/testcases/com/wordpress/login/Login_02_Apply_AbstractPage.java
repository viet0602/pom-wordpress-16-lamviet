package com.wordpress.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.AbstractPage;

public class Login_02_Apply_AbstractPage extends AbstractPage {
	WebDriver driver;
	String rootFolderPath = System.getProperty("user.dir");

	String emailTextbox = "//input[@id='usernameOrEmail']";
	String pwTextbox = "//input[@id='password']";
	String loginButton = "//div[@class='login__form-action']/button";
	String emailErrorMessage = "//div[@class='form-input-validation is-error']/span";
	String pwErrorMessage = "//div[@class='form-input-validation is-error']/span";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", rootFolderPath + "\\browerDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://automationfc.wordpress.com/wp-admin");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		// driver.get("https://automationfc.wordpress.com/wp-admin");
		openUrl(driver, "https://automationfc.wordpress.com/wp-admin");
	}

	@Test
	public void Validate_01_EmptyEmail() {
		// driver.findElement(emailTextboxBy).sendKeys("");
		sendkeyToElement(driver, emailTextbox, "");
		// driver.findElement(loginButtonBy).click();
		clickToElement(driver, loginButton);
		// Assert.assertEquals(driver.findElement(emailErrorMessageBy).getText().trim(), "Please enter a username or email address.");
		Assert.assertEquals(getElementText(driver, emailErrorMessage), "Please enter a username or email address.");

	}

	@Test
	public void Validate_02_InvalidEmail() {
		// driver.findElement(emailTextboxBy).sendKeys("123@123.123");
		sendkeyToElement(driver, emailTextbox, "123@123.123");
		// driver.findElement(loginButtonBy).click();
		clickToElement(driver, loginButton);
		// Assert.assertEquals(driver.findElement(emailErrorMessageBy).getText().trim(), "Please log in using your WordPress.com username instead of your email address.");
		Assert.assertEquals(getElementText(driver, emailErrorMessage), "Please log in using your WordPress.com username instead of your email address.");

	}

	@Test
	public void Validate_03_EmailNotExits() {
		// driver.findElement(emailTextboxBy).sendKeys("lamviet" + randomNumber() + "@gmail.com");
		sendkeyToElement(driver, emailTextbox, "lamviet" + randomNumber() + "@gmail.com");
		// driver.findElement(loginButtonBy).click();
		clickToElement(driver, loginButton);
		// Assert.assertEquals(driver.findElement(emailErrorMessageBy).getText().trim(), "User does not exist. Would you like to create a new account?");
		Assert.assertEquals(getElementText(driver, emailErrorMessage), "User does not exist. Would you like to create a new account?");

	}

	@Test
	public void Validate_04_EmptyPw() {
		// driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		sendkeyToElement(driver, emailTextbox, "automationeditor");
		// driver.findElement(loginButtonBy).click();
		clickToElement(driver, loginButton);
		// driver.findElement(passwordTextboxBy).sendKeys("");
		sendkeyToElement(driver, pwTextbox, "");
		// driver.findElement(loginButtonBy).click();
		clickToElement(driver, loginButton);

		// Assert.assertEquals(driver.findElement(pwErrorMessageBy).getText().trim(), "Don't forget to enter your password.");
		Assert.assertEquals(getElementText(driver, pwErrorMessage), "Don't forget to enter your password.");

	}

	@Test
	public void Validate_05_PwLessThan6chars() {
		// driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		sendkeyToElement(driver, emailTextbox, "automationeditor");
		// driver.findElement(loginButtonBy).click();
		clickToElement(driver, loginButton);
		// driver.findElement(passwordTextboxBy).sendKeys("1234567");
		sendkeyToElement(driver, pwTextbox, "1234567");
		// driver.findElement(loginButtonBy).click();
		clickToElement(driver, loginButton);

		// Assert.assertEquals(driver.findElement(pwErrorMessageBy).getText().trim(), "Oops, that's not the right password. Please try again!");
		Assert.assertEquals(getElementText(driver, pwErrorMessage), "Oops, that's not the right password. Please try again!");

	}

	@Test
	public void Validate_06_ValidPw() {
		// driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		sendkeyToElement(driver, emailTextbox, "automationeditor");
		// driver.findElement(loginButtonBy).click();
		clickToElement(driver, loginButton);
		// driver.findElement(passwordTextboxBy).sendKeys("automationfc");
		sendkeyToElement(driver, pwTextbox, "automationfc");
		// driver.findElement(loginButtonBy).click();
		clickToElement(driver, loginButton);
		// Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]")).isDisplayed());
		Assert.assertTrue(isElementDisplayed(driver, "//h1[contains(text(),'Dashboard')]"));

	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);

	}

	@AfterClass
	public void afterClass() {
	}

}
