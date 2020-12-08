package com.wordpress.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browserFactory.DriverManager;
import common.AbstractTest;
import common.PageGeneratorManager;
import pageObjects.admin.WordPress.DashBoardPageObject;
import pageObjects.admin.WordPress.LinksPageObject;
import pageObjects.admin.WordPress.LoginPageObject;
import pageObjects.admin.WordPress.MediaPageObject;
import pageObjects.admin.WordPress.PagesPageObject;
import pageObjects.admin.WordPress.PostsAdminPageObject;

public class Login_07_WebDriver_LifeCycle extends AbstractTest {
	WebDriver driver;
	DriverManager driverManager;
	
	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		loginPage = PageGeneratorManager.getLoginAdminPage(driver);
	}

	
	@Test
	public void TC_01_LoginToSystem() {
		loginPage.inputToEmailTextBox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPwTextBox("automationfc");		
		dashboardPage =loginPage.clickToContinueOrLoginButton();
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
	PostsAdminPageObject postPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;
	LinksPageObject linksPage;
}
