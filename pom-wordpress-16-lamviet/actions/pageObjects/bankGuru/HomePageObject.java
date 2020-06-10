package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import pageUI.bankGuru.HomePageUI;

public class HomePageObject extends AbstractPage {
	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver= driver;
	}

	public boolean isWelcomeMessageDisplay() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.WELCOME_TEXT);
		return isElementDisplayed(driver, HomePageUI.WELCOME_TEXT);
	}

}
