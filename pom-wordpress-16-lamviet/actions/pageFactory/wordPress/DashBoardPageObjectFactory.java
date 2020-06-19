package pageFactory.wordPress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPageObjectFactory extends AbstractPageFactory {
	private WebDriver driver;
	//public static final String HEADER_TEXT="//h1[contains(text(),'Dashboard')]";
	@FindBy(how = How.XPATH, using = "//h1[contains(text(),'Dashboard')]")
	private WebElement headerText;


	public DashBoardPageObjectFactory(WebDriver mappingDriver) {
		driver = mappingDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean isHeaderTextDisplay() {
		waitForElementVisible(driver, headerText);
		return isElementDisplayed(driver, headerText);
	}

	private boolean isElementDisplayed(WebDriver driver2, WebElement element) {
		// TODO Auto-generated method stub
		return element.isDisplayed();
	}
}
