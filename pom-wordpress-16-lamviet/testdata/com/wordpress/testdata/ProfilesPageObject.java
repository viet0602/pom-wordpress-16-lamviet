package com.wordpress.testdata;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class ProfilesPageObject extends AbstractPage {
	WebDriver driver;

	public ProfilesPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
