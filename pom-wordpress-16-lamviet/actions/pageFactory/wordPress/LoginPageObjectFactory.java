package pageFactory.wordPress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObjectFactory extends AbstractPageFactory {
	private WebDriver driver;
	@FindBy(how = How.ID, using = "usernameOrEmail")
	private WebElement EmailTextBox;

	@FindBy(how = How.ID, using = "password")
	private WebElement PWTextBox;

	@FindBy(how = How.XPATH, using = "//div[@class='login__form-action']/button")
	private WebElement LoginButton;

	@FindBy(how = How.XPATH, using = "//div[@class='form-input-validation is-error']/span")
	private WebElement EMAIL_PW_ERROR_MSG;

	public LoginPageObjectFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void inputToEmailTextBox(String email) {
		waitForElementVisible(driver, EmailTextBox);
		EmailTextBox.sendKeys(email);

	}

	public void clickToContinueOrLoginButton() {
		waitForElementVisible(driver, LoginButton);
		clickToElement(driver, LoginButton);

	}

	public void inputToPwTextBox(String pw) {
		waitForElementVisible(driver, PWTextBox);
		sendkeyToElement(driver, PWTextBox, pw);
	}

	public String getEmailOrPwErrorMsg() {
		waitForElementVisible(driver, EMAIL_PW_ERROR_MSG);
		return getElementText(driver, EMAIL_PW_ERROR_MSG);
	}

	public String getLoginPageUrl() {
		return getCurrentUrl(driver);
	}

	public void openLoginPage(String loginUrl) {
		// TODO Auto-generated method stub
		openUrl(driver, loginUrl);
	}

}
