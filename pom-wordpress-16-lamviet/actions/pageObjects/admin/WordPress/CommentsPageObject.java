package pageObjects.admin.WordPress;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class CommentsPageObject extends AbstractPage {
	WebDriver driver;

	public CommentsPageObject(WebDriver driver) {
		this.driver = driver;
	}

}
