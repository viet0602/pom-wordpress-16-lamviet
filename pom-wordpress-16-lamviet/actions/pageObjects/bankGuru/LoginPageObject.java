package pageObjects.bankGuru;
import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import pageUI.bankGuru.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver2) {
		this.driver = driver2;
	}

	public String getLoginUrl() {
		return getCurrentUrl(driver);
	}

	public void clickToHereLink() {
		waitForElementClickable(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
	}

	public void inputToUserIDTextBox(String userIDvalue) {
		waitForElementVisible(driver, LoginPageUI.USER_ID);
		sendkeyToElement(driver, LoginPageUI.USER_ID, userIDvalue);

	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BTN);
		clickToElement(driver, LoginPageUI.LOGIN_BTN);

	}

	public void inputPwTextBox(String pwValue) {
		waitForElementVisible(driver, LoginPageUI.PW);
		sendkeyToElement(driver, LoginPageUI.PW, pwValue);

	}
}
