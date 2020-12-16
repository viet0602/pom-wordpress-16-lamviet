package pageObjects.user.WordPress;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import pageUI.WordPress.AbstractPageUI;

public class PostDetailPageObject extends AbstractPage {
	WebDriver driver;

	public PostDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}



	public boolean isCategoryNameDisplayed(String categoryName) {
		waitForElementVisible(driver, AbstractPageUI.DETAIL_PAGE_CATEGOGY, categoryName);
		return isElementDisplayed(driver, AbstractPageUI.DETAIL_PAGE_CATEGOGY, categoryName);
	}

	public boolean isTitleNameDisplayed(String title) {

		waitForElementVisible(driver, AbstractPageUI.DETAIL_PAGE_TITLE, title);
		return isElementDisplayed(driver, AbstractPageUI.DETAIL_PAGE_TITLE, title);
	}

	public boolean isContentDisplayed(String content) {

		waitForElementVisible(driver, AbstractPageUI.DETAIL_PAGE_CONTENT, content);
		return isElementDisplayed(driver, AbstractPageUI.DETAIL_PAGE_CONTENT, content);
	}

	public boolean isDateCreatedDisplayed(String today) {

		waitForElementVisible(driver, AbstractPageUI.DETAIL_PAGE_CREATED_DATE, today);
		return isElementDisplayed(driver, AbstractPageUI.DETAIL_PAGE_CREATED_DATE, today);
	}

	public boolean isPostImageDisplayed(String imageName) {
		imageName= imageName.split("\\.")[0];
		waitForElementVisible(driver, AbstractPageUI.DETAIL_PAGE_IMAGE, imageName);
		return isElementDisplayed(driver, AbstractPageUI.DETAIL_PAGE_IMAGE, imageName);
	}

	public boolean isAuthorDisplayed(String author) {

		waitForElementVisible(driver, AbstractPageUI.DETAIL_PAGE_AUTHOR, author);
		return isElementDisplayed(driver, AbstractPageUI.DETAIL_PAGE_AUTHOR, author);
	}

}
