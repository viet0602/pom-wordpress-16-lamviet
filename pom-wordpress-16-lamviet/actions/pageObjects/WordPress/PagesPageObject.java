package pageObjects.WordPress;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class PagesPageObject extends AbstractPage{
	WebDriver driver;

	public PagesPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
