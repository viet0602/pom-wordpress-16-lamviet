package com.wordpress.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_01_Repeat_Yourself {
	WebDriver driver;
	String rootFolderPath = System.getProperty("user.dir");
	By emailTextboxBy = By.xpath("//input[@id='usernameOrEmail']");
	By passwordTextboxBy = By.xpath("//input[@id='password']");
	By loginButtonBy = By.xpath("//div[@class='login__form-action']/button");
	By emailErrorMessageBy = By.xpath("//div[@class='form-input-validation is-error']/span");
	By pwErrorMessageBy = By.xpath("//div[@class='form-input-validation is-error']/span");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", rootFolderPath + "\\browerDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://automationfc.wordpress.com/wp-admin");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://automationfc.wordpress.com/wp-admin");
	}

	@Test
	public void Validate_01_EmptyEmail() {
		driver.findElement(emailTextboxBy).sendKeys("");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(emailErrorMessageBy).getText().trim(), "Please enter a username or email address.");

	}

	@Test
	public void Validate_02_InvalidEmail() {
		driver.findElement(emailTextboxBy).sendKeys("123@123.123");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(emailErrorMessageBy).getText().trim(), "Please log in using your WordPress.com username instead of your email address.");

	}

	@Test
	public void Validate_03_EmailNotExits() {
		driver.findElement(emailTextboxBy).sendKeys("lamviet" + randomNumber() + "@gmail.com");
		driver.findElement(loginButtonBy).click();
		Assert.assertEquals(driver.findElement(emailErrorMessageBy).getText().trim(), "User does not exist. Would you like to create a new account?");

	}

	@Test
	public void Validate_04_EmptyPw() {
		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		driver.findElement(loginButtonBy).click();
		driver.findElement(passwordTextboxBy).sendKeys("");
		driver.findElement(loginButtonBy).click();

		Assert.assertEquals(driver.findElement(pwErrorMessageBy).getText().trim(), "Don't forget to enter your password.");

	}

	@Test
	public void Validate_05_PwLessThan6chars() {
		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		driver.findElement(loginButtonBy).click();
		driver.findElement(passwordTextboxBy).sendKeys("1234567");
		driver.findElement(loginButtonBy).click();

		Assert.assertEquals(driver.findElement(pwErrorMessageBy).getText().trim(), "Oops, that's not the right password. Please try again!");

	}

	@Test
	public void Validate_06_ValidPw() {
		driver.findElement(emailTextboxBy).sendKeys("automationeditor");
		driver.findElement(loginButtonBy).click();
		driver.findElement(passwordTextboxBy).sendKeys("automationfc");
		driver.findElement(loginButtonBy).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Dashboard')]")).isDisplayed());

	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
	}

}
