package pageObjects.admin.WordPress;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import pageUI.WordPress.MediaPageUI;

public class MediaPageObject extends AbstractPage {
	WebDriver driver;

	public MediaPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToAddNewbutton() {
		waitForElementClickable(driver, MediaPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, MediaPageUI.ADD_NEW_BUTTON);
		System.out.println(MediaPageUI.ADD_NEW_BUTTON);
	}

}
