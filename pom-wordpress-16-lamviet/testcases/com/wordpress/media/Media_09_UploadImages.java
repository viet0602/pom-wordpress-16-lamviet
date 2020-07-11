package com.wordpress.media;

import org.testng.annotations.Test;

import com.wordpress.testdata.PageGeneratorManager;

import common.AbstractTest;
import pageObjects.WordPress.DashBoardPageObject;
import pageObjects.WordPress.LinksPageObject;
import pageObjects.WordPress.LoginPageObject;
import pageObjects.WordPress.MediaPageObject;
import pageObjects.WordPress.PagesPageObject;
import pageObjects.WordPress.PostsPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Media_09_UploadImages extends AbstractTest {
	WebDriver driver;
	String pic1 = "DOG1.jpg";
	String pic2 = "DOG2.jpg";
	String pic4 = "DOG3.jpg";

	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPage.inputToEmailTextBox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPwTextBox("automationfc");
		loginPage.clickToContinueOrLoginButton();
		dashboardPage = new DashBoardPageObject(driver);
		Assert.assertTrue(dashboardPage.isHeaderTextDisplay());
	}

	@Test
	public void TC_01_Upload_Images() {

		// Post > Media
		mediaPage = (MediaPageObject) dashboardPage.clickToDynamicPageMenu(driver, "Media");

		mediaPage.clickToAddNewbutton();
		mediaPage.uploadMultiple(driver, pic1, pic2, pic4);
		// mediaPage.sleepInSecond(50);
		Assert.assertTrue(mediaPage.areFileUploadDisplay(driver, pic1, pic2, pic4));
	}

	/*
	 * @AfterClass public void afterClass() { driver.close(); }
	 */

	String loginUrl;
	LoginPageObject loginPage;
	DashBoardPageObject dashboardPage;
	PostsPageObject postPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;
	LinksPageObject linksPage;

}
