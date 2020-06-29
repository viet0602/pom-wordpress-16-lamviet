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
import pageObjects.WordPress.LinksPageObject;
import pageObjects.WordPress.LoginPageObject;
import pageObjects.WordPress.MediaPageObject;
import pageObjects.WordPress.PagesPageObject;
import pageObjects.WordPress.PostsPageObject;

public class Login_07_WebDriver_LifeCycle extends AbstractTest {
	WebDriver driver;
	DriverManager driverManager;
	
	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	
	@Test
	public void TC_01_LoginToSystem() {
		loginPage.inputToEmailTextBox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPwTextBox("automationfc");
		loginPage.clickToContinueOrLoginButton();
		dashboardPage = new DashBoardPageObject(driver);
		Assert.assertTrue(dashboardPage.isHeaderTextDisplay());

	}
	@Test
	public void TC_02_NavigateToPage() {
		
		//Dashboard > Post
		postPage = dashboardPage.clickToPostMenu(driver);
		//Post > Media
		mediaPage = postPage.clickToMediaMenu(driver);
		
		//Media > Links
		linksPage = mediaPage.clickToLinksMenu(driver);
		
		//Links >Pages
		pagesPage = linksPage.clickToPagesMenu(driver);
		//Pages > Post
		postPage = pagesPage.clickToPostMenu(driver);
		//Post > Links
		linksPage = postPage.clickToLinksMenu(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}
	String loginUrl;
	LoginPageObject loginPage;
	DashBoardPageObject dashboardPage;
	PostsPageObject postPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;
	LinksPageObject linksPage;
}
