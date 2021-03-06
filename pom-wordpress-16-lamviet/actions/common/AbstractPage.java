package common;

import java.util.ArrayList;
import java.util.Collections;
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
import pageObjects.admin.WordPress.CommentsPageObject;
import pageObjects.admin.WordPress.DashBoardPageObject;
import pageObjects.admin.WordPress.FeedbackPageObject;
import pageObjects.admin.WordPress.LinksPageObject;
import pageObjects.admin.WordPress.MediaPageObject;
import pageObjects.admin.WordPress.PagesPageObject;
import pageObjects.admin.WordPress.PostsAdminPageObject;
import pageObjects.admin.WordPress.ProfilePageObject;
import pageObjects.admin.WordPress.SettingsPageObject;
import pageObjects.admin.WordPress.ToolsPageObject;
import pageObjects.user.WordPress.HomeUserPageObject;
import pageObjects.user.WordPress.PostDetailPageObject;
import pageObjects.user.WordPress.SearchResultPageObject;
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
		select.selectByVisibleText(value);
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

	public void SelectItemInDropDownList(WebDriver driver, String parentXpath, String allItemXpath,
			String expectedValueItem) {
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
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match(" + expectedText + "')[0]");
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
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElementByXpath(driver, locator));
	}

	// Dynamic locator
	public void clickToElementByJS(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", findElementByXpath(driver, castToObject(locator, values)));
	}

	public void scrollToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, locator));
	}

	// Dynamic locator
	public void scrollToElementByJS(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",
				findElementByXpath(driver, castToObject(locator, values)));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeRemove + "');",
				findElementByXpath(driver, locator));
	}

	public void uploadMultiple(WebDriver driver, String... fileNames) {
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + GlobalConstants.UP_FOLDER + file + "\n";
		}
		fullFileName = fullFileName.trim();
		sendkeyToElement(driver, AbstractPageUI.UPLOAD_FILE_TYPE, fullFileName);
	}

	public boolean areFileUploadDisplay(WebDriver driver, String... fileNames) {
		boolean status = false;
		int number = fileNames.length;

		waitForElementsInvisible(driver, AbstractPageUI.MEDIA_PROGRESS_BAR_ICON);
		sleepInSecond(5);
		elements = findElementsByXpath(driver, AbstractPageUI.ALL_UPLOADED_IMAGE);
		// Tạo array list chứa các
		List<String> imageValues = new ArrayList<String>();
		// Lấy source value của nó = chứa tên hình
		int i = 0;
		for (WebElement image : elements) {
			// System.out.print(image.getAttribute("src"));
			imageValues.add(image.getAttribute("src"));
			i++;
			if (i == number) {
				break;
			}
		}
		// verify File name matching
		for (String fileName : fileNames) {
			String[] files = fileName.split("\\.");
			fileName = files[0].toLowerCase();
			for (i = 0; i < imageValues.size(); i++) {
				if (!imageValues.get(i).contains(fileName)) {
					status = false;
					if (i == imageValues.size() - 1) {
						return status;
					}
				} else {
					status = true;
					break;
				}
			}
		}
		return status;
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor
				.executeScript(
						"return arguments[0].complete && typeof argumentes[0]"
								+ ".naturalWitdth!='underfined' && arguments[0]" + ".naturalWidth>0",
						findElementByXpath(driver, locator));
		if (status) {
			return true;
		}
		return false;
	}

	public boolean isImageLoaded(WebDriver driver, String locator, String... values) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor
				.executeScript(
						"return arguments[0].complete && typeof argumentes[0]"
								+ ".naturalWitdth!='underfined' && arguments[0]" + ".naturalWidth>0",
						findElementByXpath(driver, castToObject(locator, values)));

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

	public void waitForElementsInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		elements = findElementsByXpath(driver, locator);
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
		return PageGeneratorManager.getPagesAdminPage(driver);
	}

	public CommentsPageObject clickToCommentsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.COMMENTS_LINK);
		clickToElement(driver, AbstractPageUI.COMMENTS_LINK);
		return PageGeneratorManager.getCommentsAdminPage(driver);
	}

	public FeedbackPageObject clickToFeedBackMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.FEEDBACK_LINK);
		clickToElement(driver, AbstractPageUI.FEEDBACK_LINK);
		return PageGeneratorManager.getFeedbackAdminPage(driver);
	}

	public LinksPageObject clickToLinksMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.LINKS_LINK);
		clickToElement(driver, AbstractPageUI.LINKS_LINK);
		return PageGeneratorManager.getLinksAdminPage(driver);
	}

	public MediaPageObject clickToMediaMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.MEDIA_LINK);
		clickToElement(driver, AbstractPageUI.MEDIA_LINK);
		return PageGeneratorManager.getMediaAdminPage(driver);
	}

	public PostsAdminPageObject clickToPostMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.POSTS_LINK);
		clickToElement(driver, AbstractPageUI.POSTS_LINK);
		return PageGeneratorManager.getPostsAdminPage(driver);
	}

	public ProfilePageObject clickToProfileMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.PROFILE_LINK);
		clickToElement(driver, AbstractPageUI.PROFILE_LINK);
		return PageGeneratorManager.getProfileAdminPage(driver);
	}

	public SettingsPageObject clickToSettingsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.SETTINGS_LINK);
		clickToElement(driver, AbstractPageUI.SETTINGS_LINK);
		return PageGeneratorManager.getSettingsAdminPage(driver);
	}

	public ToolsPageObject clickToToolsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractPageUI.TOOLS_LINK);
		clickToElement(driver, AbstractPageUI.TOOLS_LINK);
		return PageGeneratorManager.getToolsAdminPage(driver);
	}

	// Dynamic locator : Apply app 10-15 pages
	public AbstractPage openMenuByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		if (pageName.equals("Posts")) {
			return PageGeneratorManager.getPostsAdminPage(driver);
		} else if (pageName.equals("Pages")) {
			return PageGeneratorManager.getPagesAdminPage(driver);
		} else if (pageName.equals("Media")) {
			return PageGeneratorManager.getMediaAdminPage(driver);

		} else if (pageName.equals("Links")) {
			return PageGeneratorManager.getLinksAdminPage(driver);
		} else {
			return PageGeneratorManager.getDashBoardAdminPage(driver);
		}
	}

	// Dynamic locator: Apply for much page
	public void openMenuByDynamicPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_PAGE_LINK, pageName);
	}

	public HomeUserPageObject openUserPage(WebDriver driver) {
		openUrl(driver, GlobalConstants.WORDPRESS_USER_URL);
		return PageGeneratorManager.getHomeUserPage(driver);
	}

	public DashBoardPageObject openAdminLoggedPage(WebDriver driver) {
		openUrl(driver, "Admin Page URL");
		return PageGeneratorManager.getDashBoardAdminPage(driver);
	}

	public SearchResultPageObject inputToSearchTextBoxAtUserPage(WebDriver driver, String titlePost) {
		waitForElementClickable(driver, AbstractPageUI.SEARCH_ICON_START);
		clickToElement(driver, AbstractPageUI.SEARCH_ICON_START);
		waitForElementVisible(driver, AbstractPageUI.SEARCH_INPUT_TEXTBOX);
		sendkeyToElement(driver, AbstractPageUI.SEARCH_INPUT_TEXTBOX, titlePost);
		waitForElementClickable(driver, AbstractPageUI.SEARCH_ICON_ENTER);
		clickToElement(driver, AbstractPageUI.SEARCH_ICON_ENTER);
		return PageGeneratorManager.getSearchResultUserPage(driver);
	}

	public boolean isValueDisplayAtColumnName(WebDriver driver, String columnName, String value) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, columnName, value);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_ROW_VALUE_AT_COLUMN_NAME, columnName, value);
	}

	public PostDetailPageObject clickToDetailPostByTitleName(WebDriver driver, String titleName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TITLE_POST_ADMIN_PAGE, titleName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_TITLE_POST_ADMIN_PAGE, titleName);
		return PageGeneratorManager.getPostDetailUserPage(driver);
	}

	public boolean isNewPostDisplayedOnLatestPost(WebDriver driver, String categoryName, String titlePost,
			String dateCreated) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoryName, titlePost,
				dateCreated);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoryName, titlePost,
				dateCreated);
	}

	public boolean isPostImageDisplayedTitleName(WebDriver driver, String titlePost, String imageName) {
		imageName = imageName.split("\\.")[0];
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TITLE_POST_WITH_IMAGE, titlePost, imageName);
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_TITLE_POST_WITH_IMAGE, titlePost, imageName);
		// && isImageLoaded(driver, AbstractPageUI.DYNAMIC_TITLE_POST_WITH_IMAGE,
		// titlePost, imageName);
	}

	public boolean isDataSortedAscending(WebDriver driver, String locator) {
		// Khai bao 1 array list
		ArrayList<String> arrayList = new ArrayList<>();
		// Tim tat ca element matching voi dieu kien (Name/Price)
		List<WebElement> elementList = findElementsByXpath(driver, locator);
		// Lay text cua Tung element add vao Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("-------Du lieu tren UI:------");
		for (String name : arrayList) {
			System.out.println(name);
		}
		// Copy qua 1 array list moi de Sort trong Code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		// Thuc hien Sort ASC
		Collections.sort(sortedList);
		System.out.println("-------Du lieu tren CODE:------");
		for (String name : sortedList) {
			System.out.println(name);
		}
		// Verify 2 array bang nhau - neu du lieu sort tren UI khong chinh xac thi ket
		// qua tra ve sai

		return sortedList.equals(arrayList);
	}

	public boolean isDataSortedDescending(WebDriver driver, String locator) {
		// Khai bao 1 array list
		ArrayList<String> arrayList = new ArrayList<>();
		// Tim tat ca element matching voi dieu kien (Name/Price)
		List<WebElement> elementList = findElementsByXpath(driver, locator);
		// Lay text cua Tung element add vao Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		System.out.println("-------Du lieu tren UI:------");
		for (String name : arrayList) {
			System.out.println(name);
		}
		// Copy qua 1 array list moi de Sort trong Code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		// Thuc hien Sort ASC
		Collections.sort(sortedList);
		
		System.out.println("-------Du lieu da sort ASC tren CODE:------");
		for (String name : sortedList) {
			System.out.println(name);
		}
		// Reverse data de sort DESC(Dung 1 trong 2 cach ben duoi)
		Collections.reverse(sortedList);
		// Collections.sort(arrayList.Collections.reverseOrder());
		System.out.println("-------Du lieu da sort DESC tren CODE:------");
		for (String name : sortedList) {
			System.out.println(name);
		}
		// Verify 2 array bang nhau - neu du lieu sort tren UI khong chinh xac thi ket
		// qua tra ve sai

		return sortedList.equals(arrayList);

	}
	public boolean isDataFloatSortedAscending(WebDriver driver, String locator) {
		// Khai bao 1 array list
		ArrayList<Float> arrayList = new ArrayList<>();
		// Tim tat ca element matching voi dieu kien (Name/Price)
		List<WebElement> elementList = findElementsByXpath(driver, locator);
		// Lay text cua Tung element add vao Array List
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}
		System.out.println("-------Du lieu tren UI:------");
		for (Float name : arrayList) {
			System.out.println(name);
		}
		// Copy qua 1 array list moi de Sort trong Code
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		// Thuc hien Sort ASC
		Collections.sort(sortedList);
		
		System.out.println("-------Du lieu da sort ASC tren CODE:------");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		return sortedList.equals(arrayList);
	}
	public boolean isDataFloatSortedDescending(WebDriver driver, String locator) {
		// Khai bao 1 array list
		ArrayList<Float> arrayList = new ArrayList<>();
		// Tim tat ca element matching voi dieu kien (Name/Price)
		List<WebElement> elementList = findElementsByXpath(driver, locator);
		// Lay text cua Tung element add vao Array List
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}
		System.out.println("-------Du lieu tren UI:------");
		for (Float name : arrayList) {
			System.out.println(name);
		}
		// Copy qua 1 array list moi de Sort trong Code
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		// Thuc hien Sort ASC
		Collections.sort(sortedList);
		
		System.out.println("-------Du lieu da sort ASC tren CODE:------");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		// Reverse data de sort DESC(Dung 1 trong 2 cach ben duoi)
		Collections.reverse(sortedList);
		// Collections.sort(arrayList.Collections.reverseOrder());
		System.out.println("-------Du lieu da sort DESC tren CODE:------");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		// Verify 2 array bang nhau - neu du lieu sort tren UI khong chinh xac thi ket
		// qua tra ve sai

		return sortedList.equals(arrayList);

	}

	private Select select;
	private Actions action;
	private JavascriptExecutor jsExecutor;
	private WebDriverWait explicitWait;
	private WebElement element;
	private List<WebElement> elements;
	private long longTimeout = 30;
}
