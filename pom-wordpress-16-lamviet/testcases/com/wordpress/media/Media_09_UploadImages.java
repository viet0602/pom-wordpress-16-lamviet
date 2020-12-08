package com.wordpress.media;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.AbstractTest;
import common.PageGeneratorManager;
import pageObjects.admin.WordPress.DashBoardPageObject;
import pageObjects.admin.WordPress.LinksPageObject;
import pageObjects.admin.WordPress.LoginPageObject;
import pageObjects.admin.WordPress.MediaPageObject;
import pageObjects.admin.WordPress.PagesPageObject;
import pageObjects.admin.WordPress.PostsAdminPageObject;

public class Media_09_UploadImages extends AbstractTest {
	WebDriver driver;
	String pic1 = "DOG1.jpg";
	String pic2 = "DOG2.jpg";
	String pic3 = "DOG3.jpg";

	@Parameters("browser")

	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		loginPage = PageGeneratorManager.getLoginAdminPage(driver);
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
		mediaPage = (MediaPageObject) dashboardPage.openMenuByPageName(driver, "Media");

		mediaPage.clickToAddNewbutton();
		mediaPage.uploadMultiple(driver, pic1, pic2, pic3);

		Assert.assertTrue(mediaPage.areFileUploadDisplay(driver, pic1, pic2, pic3));
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
