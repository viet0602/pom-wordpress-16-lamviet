package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import pageUI.bankGuru.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {

	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, RegisterPageUI.SUBMIT_BTN);
		clickToElement(driver, RegisterPageUI.SUBMIT_BTN);
	}

	public void inputToEmailTextBox(String str) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, str);

	}

	public String getUserIDText() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID_REG);
		return getElementText(driver, RegisterPageUI.USER_ID_REG);
	}

	public String getPwText() {
		waitForElementVisible(driver, RegisterPageUI.PW_ID_REG);
		return getElementText(driver, RegisterPageUI.PW_ID_REG);
	}

	public void openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);
	}

}
