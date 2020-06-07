package pageObjects;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import pageUI.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	
	public void inputToEmailTextBox(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver,LoginPageUI.EMAIL_TEXTBOX , email);
		
	}
	public void clickToContinueOrLoginButton() {
		waitForElementVisible(driver, LoginPageUI.CONTINUE_ORLOGIN_BUTTOM);
		clickToElement(driver,LoginPageUI.CONTINUE_ORLOGIN_BUTTOM);
		
	}
	public void inputToPwTextBox(String pw) {
		waitForElementVisible(driver, LoginPageUI.PW_TEXTBOX);
		sendkeyToElement(driver,LoginPageUI.PW_TEXTBOX , pw);
	}
	public String getEmailOrPwErrorMsg() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_PW_ERROR_MSG);
		return getElementText(driver, LoginPageUI.EMAIL_PW_ERROR_MSG);
	}
	public String getLoginPageUrl() {
		return getCurrentUrl(driver);
	}


	public void openLoginPage(String loginUrl) {
		// TODO Auto-generated method stub
		openUrl(driver, loginUrl);
	}

}
