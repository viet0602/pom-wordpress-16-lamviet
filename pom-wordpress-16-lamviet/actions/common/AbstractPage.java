package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wordpress.testdata.PageGeneratorManager;

import pageObjects.WordPress.CommentsPageObject;
import pageObjects.WordPress.FeedbackPageObject;
import pageObjects.WordPress.LinksPageObject;
import pageObjects.WordPress.MediaPageObject;
import pageObjects.WordPress.PagesPageObject;
import pageObjects.WordPress.PostsPageObject;
import pageObjects.WordPress.ProfilePageObject;
import pageObjects.WordPress.SettingsPageObject;
import pageObjects.WordPress.ToolsPageObject;
import pageUI.WordPress.AbstractPageUI;

public abstract class AbstractPage {
	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void foward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void sendKeytoAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void waitAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, 15);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchWindowByTittle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			driver.switchTo().window(runWindow);
			String currentWindow = driver.getTitle();
			if (currentWindow.equals(title)) {
				break;
			}
		}
	}

	public boolean areAllWindowsWithoutParent(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(byXpath(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		findElementByXpath(driver, locator).click();
	}

	// Dynamic Locator
	public String castToObject(String locator, String... values) {
		return String.format(locator, (Object[]) values);
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		findElementByXpath(driver, castToObject(locator, values)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		findElementByXpath(driver, locator).sendKeys(value);
	}

	// Dynamic Locator
	public void sendkeyToElement(WebDriver driver, String locator, String key, String... values) {
		findElementByXpath(driver, castToObject(locator, values)).sendKeys(key);
	}

	public String getElementText(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText().trim();
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return findElementByXpath(driver, locator).getAttribute(attributeName);
	}

	public void selectValueInDropdown(WebDriver driver, String locator, String value) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByValue(value);
	}

	public String getSelectedValueInDropdown(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public void sleepInSecond(long timeouts) {
		try {
			Thread.sleep(timeouts * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void SelectItemInDropDownList(WebDriver driver, String parentXpath, String allItemXpath, String expectedValueItem) {
		// Click vào 1 thẻ cha để nó xổ ra tất cả các item con bên trong
		element = findElementByXpath(driver, allItemXpath);
		jsExecutor.executeScript("arguments[0].click();", element);
		sleepInSecond(1);

		// Wait cho tất cả các item được load lên
		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(allItemXpath)));
		// Lấy hết tất cả các item gán vào 1 cái list <WebElement> (findElements>
		elements = findElementsByXpath(driver, allItemXpath);
		// Dùng vòng lặp duyệt qua các item
		for (WebElement childElement : elements) {
			if (childElement.getText().equals(expectedValueItem)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);
					jsExecutor.executeScript("arguments[0].click();", childElement);
				}
				sleepInSecond(1);
				break;
			}
		}
	}

	public int countElementNumber(WebDriver driver, String locator) {
		elements = findElementsByXpath(driver, locator);
		return elements.size();
	}

	// Dynamic locator
	public int countElementNumber(WebDriver driver, String locator, String... values) {
		elements = findElementsByXpath(driver, castToObject(locator, values));
		return elements.size();
	}

	public void checkToCheckBox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckBox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isDisplayed();
	}

	// Dynamic Locator
	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();
	}

	public boolean isElementEnable(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();
	}

	public void switchToFrameOrIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(findElementByXpath(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}

	// Dynamic locator
	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... values) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, castToObject(locator, values)), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String expectedText) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match(" + expectedText + "')[0]");
		return textActual.equals(expectedText);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElementByXpath(driver, locator));
	}

	public void scrollToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, locator));
	}
	//Dynamic locator
	public void scrollToElementByJS(WebDriver driver, String locator, String...values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, castToObject(locator, values)));
	}
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeRemove + "');", findElementByXpath(driver, locator));
	}
	public void uploadMultiple(WebDriver driver, String...fileNames) {
		String filePath=System.getProperty("user.dir")+"\\uploadFiles\\";
		String fullFileName= "";
		for(String file:fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName=fullFileName.trim();
		sendkeyToElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fullFileName);
	}
	public boolean areFileUploadDisplay(WebDriver driver, String...fileNames) {
		boolean status = false;
		int number = fileNames.length;
		
		waitForElementsInvisible(driver, AbstractPageUI.MEDIA_PROGRESS_BAR_ICON);
		sleepInSecond(10);
		elements = findElementsByXpath(driver, AbstractPageUI.ALL_UPLOADED_IMAGE);
		System.out.println(elements);
		
		List<String> imageValues = new ArrayList<String>();
		//Lấy source value của nó = chứa tên hình
		int i=0;
		for(WebElement image : elements) {
			imageValues.add(image.getAttribute("src"));
			i++;
			if(i==number) {
				break;
			}
		}
		//verify File name matching
		for(String fileName : fileNames) {
			String[] files = fileName.split("\\.");
			fileName=files[0].toLowerCase();
			for(i=0; i<imageValues.size(); i++) {
				if(!imageValues.get(i).contains(fileName)) {
					status = false;
					if(i==imageValues.size()-1) {
						return status;
					}
					else {
						status=true;
						break;
					}
				}
			}
		}
		return status;
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof argumentes[0]" + ".naturalWitdth!='underfined' && arguments[0]" + ".naturalWidth>0", findElementByXpath(driver, locator));
		if (status) {
			return true;
		}
		return false;
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}
	public void waitForElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(locator)));
	}

	// Dynamic Locator
	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	// Dynamic Locator
	public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}
	public void waitForElementsInvisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}

	// Dynamic Locator
	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, values))));
	}
	// Common functions - Open page

	public PagesPageObject clickToPagesMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.PAGES_LINK);
		clickToElement(driver, AbstractPageUI.PAGES_LINK);
		return PageGeneratorManager.getPagesPage(driver);
	}

	public CommentsPageObject clickToCommentsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.COMMENTS_LINK);
		clickToElement(driver, AbstractPageUI.COMMENTS_LINK);
		return PageGeneratorManager.getCommentsPage(driver);
	}

	public FeedbackPageObject clickToFeedBackMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.FEEDBACK_LINK);
		clickToElement(driver, AbstractPageUI.FEEDBACK_LINK);
		return PageGeneratorManager.getFeedbackPage(driver);
	}

	public LinksPageObject clickToLinksMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.LINKS_LINK);
		clickToElement(driver, AbstractPageUI.LINKS_LINK);
		return PageGeneratorManager.getLinksPage(driver);
	}

	public MediaPageObject clickToMediaMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.MEDIA_LINK);
		clickToElement(driver, AbstractPageUI.MEDIA_LINK);
		return PageGeneratorManager.getMediaPage(driver);
	}

	public PostsPageObject clickToPostMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.POSTS_LINK);
		clickToElement(driver, AbstractPageUI.POSTS_LINK);
		return PageGeneratorManager.getPostsPage(driver);
	}

	public ProfilePageObject clickToProfileMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.PROFILE_LINK);
		clickToElement(driver, AbstractPageUI.PROFILE_LINK);
		return PageGeneratorManager.getProfilePage(driver);
	}

	public SettingsPageObject clickToSettingsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.SETTINGS_LINK);
		clickToElement(driver, AbstractPageUI.SETTINGS_LINK);
		return PageGeneratorManager.getSettingsPage(driver);
	}

	public ToolsPageObject clickToToolsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.TOOLS_LINK);
		clickToElement(driver, AbstractPageUI.TOOLS_LINK);
		return PageGeneratorManager.getToolsPage(driver);
	}

	// Dynamic locator : Apply app 10-15 pages
	public AbstractPage clickToDynamicPageMenu(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		if (pageName.equals("Posts")) {
			return PageGeneratorManager.getPostsPage(driver);
		} else if (pageName.equals("Pages")) {
			return PageGeneratorManager.getPagesPage(driver);
		} else if (pageName.equals("Media")) {
			return PageGeneratorManager.getMediaPage(driver);

		} else if (pageName.equals("Links")) {
			return PageGeneratorManager.getLinksPage(driver);
		} else {
			return PageGeneratorManager.getDashBoardPage(driver);
		}
	}

	// Dynamic locator: Apply for much page
	public void clickToDynamicMuchPageMenu(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
	}

	private Select select;
	private Actions action;
	private JavascriptExecutor jsExecutor;
	private WebDriverWait explicitWait;
	private WebElement element;
	private List<WebElement> elements;
	private long longTimeout = 30;
}
