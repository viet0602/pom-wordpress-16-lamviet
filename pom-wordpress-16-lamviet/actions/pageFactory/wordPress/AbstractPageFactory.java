package pageFactory.wordPress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPageFactory {
	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}

	public void sendkeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}

	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText().trim();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void waitForElementVisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	private WebDriverWait explicitWait;
	private long longTimeout = 30;
}