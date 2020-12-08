package pageObjects.admin.WordPress;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class SettingsPageObject extends AbstractPage{
	WebDriver driver;
	public SettingsPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
