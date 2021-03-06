package pageObjects.admin.WordPress;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.NewEditPostPageObject;
import common.PageGeneratorManager;
import pageUI.WordPress.PostAdminPageUI;

public class PostsAdminPageObject extends AbstractPage {
	WebDriver driver;

	public PostsAdminPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public NewEditPostPageObject clickToAddNewButton() {
		waitForElementClickable(driver, PostAdminPageUI.DROP_DOWN_BTN);
		clickToElement(driver, PostAdminPageUI.DROP_DOWN_BTN);
		waitForElementClickable(driver, PostAdminPageUI.CLASSIC_EDITOR_CREATE_POST);
		clickToElement(driver, PostAdminPageUI.CLASSIC_EDITOR_CREATE_POST);
		return PageGeneratorManager.getNewEditPostAdminPage(driver);
	}

	public void inputToSearchTextBox(String titlePost) {
		//clickToElement(driver, PostAdminPageUI.SEARCH_POSTS_TEXTBOX);
		waitForElementsVisible(driver, PostAdminPageUI.SEARCH_POSTS_TEXTBOX);
		sendkeyToElement(driver, PostAdminPageUI.SEARCH_POSTS_TEXTBOX, titlePost);

	}

	public void clickToSearchPostsButton() {
		waitForElementClickable(driver, PostAdminPageUI.SEARCH_POSTS_BTN);
		clickToElement(driver, PostAdminPageUI.SEARCH_POSTS_BTN);
	}

	public NewEditPostPageObject clickToDetailPostByTitleName(String title) {
		waitForElementsVisible(driver, PostAdminPageUI.ADMIN_POST_TITLE);
		hoverMouseToElement(driver, PostAdminPageUI.ADMIN_POST_TITLE);
		clickToElement(driver, PostAdminPageUI.CLASSIC_EDITOR_EDIT_POST);
		return PageGeneratorManager.getNewEditPostAdminPage(driver);
	}

}
