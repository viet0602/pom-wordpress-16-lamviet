package pageObjects.user.WordPress;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class HomeUserPageObject extends AbstractPage {
	WebDriver driver;

	public HomeUserPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
