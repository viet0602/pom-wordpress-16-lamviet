package pageObjects.admin.WordPress;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class ProfilePageObject extends AbstractPage{
	WebDriver driver;
	public ProfilePageObject(WebDriver driver) {
		this.driver = driver;
	}
}
