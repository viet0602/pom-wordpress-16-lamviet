package com.wordpress.login;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wordpress.testdata.PageGeneratorManager;

import browserFactory.DriverManager;
import common.AbstractTest;
import pageObjects.WordPress.DashBoardPageObject;
import pageObjects.WordPress.LinksPageObject;
import pageObjects.WordPress.LoginPageObject;
import pageObjects.WordPress.MediaPageObject;
import pageObjects.WordPress.PagesPageObject;
import pageObjects.WordPress.PostsPageObject;

public class Login_08_Dynamic_Locator extends AbstractTest {
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
	public void TC_02_LessPage() {

		// Dashboard > Post
		postPage = (PostsPageObject) dashboardPage.clickToDynamicPageMenu(driver, "Posts");
		// Post > Media
		mediaPage = (MediaPageObject) postPage.clickToDynamicPageMenu(driver, "Media");

		// Media > Links
		linksPage = (LinksPageObject) mediaPage.clickToDynamicPageMenu(driver, "Links");

		// Links >Pages
		pagesPage = (PagesPageObject) linksPage.clickToDynamicPageMenu(driver, "Pages");
		// Pages > Post
		postPage = (PostsPageObject) pagesPage.clickToDynamicPageMenu(driver, "Posts");
		// Post > Links
		linksPage = (LinksPageObject) postPage.clickToDynamicPageMenu(driver, "Links");
	}

	@Test
	public void TC_03_MuchPage() {
		// Dashboard > Post
		dashboardPage.clickToDynamicMuchPageMenu(driver, "Posts");
		postPage = PageGeneratorManager.getPostsPage(driver);
		// Post > Media
		postPage.clickToDynamicMuchPageMenu(driver, "Media");
		mediaPage = PageGeneratorManager.getMediaPage(driver);
		// Media > Links
		mediaPage.clickToDynamicMuchPageMenu(driver, "Links");
		linksPage = PageGeneratorManager.getLinksPage(driver);
		// Links >Pages
		linksPage.clickToDynamicMuchPageMenu(driver, "Pages");
		pagesPage = PageGeneratorManager.getPagesPage(driver);
		// Pages > Post
		pagesPage.clickToDynamicMuchPageMenu(driver, "Posts");
		postPage = PageGeneratorManager.getPostsPage(driver);
		// Post > Links
		postPage.clickToDynamicMuchPageMenu(driver, "Links");
		linksPage = PageGeneratorManager.getLinksPage(driver);
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
