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

public class Login_08_Dynamic_Locator extends AbstractTest {
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
		loginPage.clickToContinueOrLoginButton();
		dashboardPage = new DashBoardPageObject(driver);
		Assert.assertTrue(dashboardPage.isHeaderTextDisplay());

	}

	@Test
	public void TC_02_LessPage() {

		// Dashboard > Post
		postPage = (PostsAdminPageObject) dashboardPage.openMenuByPageName(driver, "Posts");
		// Post > Media
		mediaPage = (MediaPageObject) postPage.openMenuByPageName(driver, "Media");

		// Media > Links
		linksPage = (LinksPageObject) mediaPage.openMenuByPageName(driver, "Links");

		// Links >Pages
		pagesPage = (PagesPageObject) linksPage.openMenuByPageName(driver, "Pages");
		// Pages > Post
		postPage = (PostsAdminPageObject) pagesPage.openMenuByPageName(driver, "Posts");
		// Post > Links
		linksPage = (LinksPageObject) postPage.openMenuByPageName(driver, "Links");
	}

	@Test
	public void TC_03_MuchPage() {
		// Dashboard > Post
		dashboardPage.openMenuByDynamicPageName(driver, "Posts");
		postPage = PageGeneratorManager.getPostsAdminPage(driver);
		// Post > Media
		postPage.openMenuByDynamicPageName(driver, "Media");
		mediaPage = PageGeneratorManager.getMediaAdminPage(driver);
		// Media > Links
		mediaPage.openMenuByDynamicPageName(driver, "Links");
		linksPage = PageGeneratorManager.getLinksAdminPage(driver);
		// Links >Pages
		linksPage.openMenuByDynamicPageName(driver, "Pages");
		pagesPage = PageGeneratorManager.getPagesAdminPage(driver);
		// Pages > Post
		pagesPage.openMenuByDynamicPageName(driver, "Posts");
		postPage = PageGeneratorManager.getPostsAdminPage(driver);
		// Post > Links
		postPage.openMenuByDynamicPageName(driver, "Links");
		linksPage = PageGeneratorManager.getLinksAdminPage(driver);
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
