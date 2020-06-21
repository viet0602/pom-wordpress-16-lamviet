package com.wordpress.testdata;

import org.openqa.selenium.WebDriver;

import pageFactory.wordPress.DashBoardPageObjectFactory;
import pageObjects.WordPress.LoginPageObject;

public class PageGeneratorManager {
	public static LoginPageObject getLoginPage (WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static DashBoardPageObjectFactory getDashBoardPage (WebDriver driver) {
		return new DashBoardPageObjectFactory(driver);
	}

}
