package pageObjects.admin.WordPress;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import pageUI.WordPress.DashBoardPageUI;

public class DashBoardPageObject extends AbstractPage {
	WebDriver driver;

	public DashBoardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isHeaderTextDisplay() {
		waitForElementVisible(driver, DashBoardPageUI.HEADER_TEXT);
		return isElementDisplayed(driver, DashBoardPageUI.HEADER_TEXT);
	}
}
