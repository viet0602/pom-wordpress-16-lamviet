package pageObjects.WordPress;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class PostsPageObject extends AbstractPage {
	WebDriver driver;
	public PostsPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
