package common;

import org.openqa.selenium.WebDriver;

import pageUI.WordPress.NewEditPostPageUI;

public class NewEditPostPageObject extends AbstractPage {
	WebDriver driver;

	public NewEditPostPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputTitleName(String title) {
		waitForElementsVisible(driver, NewEditPostPageUI.TITLE_INPUT_TEXTBOX);
		sendkeyToElement(driver, NewEditPostPageUI.TITLE_INPUT_TEXTBOX, title);

	}

	public void inputBodyPost(String content) {
		switchToFrameOrIframe(driver, NewEditPostPageUI.TINY_MCE_IFRAME);
		waitForElementsVisible(driver, NewEditPostPageUI.TINY_MCE_TEXTBOX);
		sendkeyToElement(driver, NewEditPostPageUI.TINY_MCE_TEXTBOX, content);
		switchToDefaultContent(driver);

	}

	public void selectCategoryCheckBox(String checkboxLabelText) {
		scrollToElementByJS(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
		waitForElementClickable(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
		clickToElementByJS(driver, NewEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
	}

	public void inputTagName(String tagName) {
		waitForElementsVisible(driver, NewEditPostPageUI.INPUT_TAG_TEXTBOX);
		sendkeyToElement(driver, NewEditPostPageUI.INPUT_TAG_TEXTBOX, tagName);
	}

	public void clickAddTagBtn() {
		waitForElementClickable(driver, NewEditPostPageUI.TAG_BTN);
		clickToElement(driver, NewEditPostPageUI.TAG_BTN);
	}

	public void clickToSetFeaturedImageText() {
		waitForElementClickable(driver, NewEditPostPageUI.SET_FEATURE_IMG_TEXT);
		clickToElement(driver, NewEditPostPageUI.SET_FEATURE_IMG_TEXT);
	}

	public void clickToUploadTab() {
		waitForElementsVisible(driver, NewEditPostPageUI.UPLOAD_TAB);
		clickToElement(driver, NewEditPostPageUI.UPLOAD_TAB);

	}

	public void clickToSetFeaturedImageButton() {
		waitForElementsVisible(driver, NewEditPostPageUI.IMG_ADD_BTN);
		clickToElement(driver, NewEditPostPageUI.IMG_ADD_BTN);
	}

	public void clickToSetFeatureImagetButton() {
		waitForElementsVisible(driver, NewEditPostPageUI.SET_FEATURED_IMAGE_BTN);
		clickToElement(driver, NewEditPostPageUI.SET_FEATURED_IMAGE_BTN);
	}

	public boolean isThumbnailImageDisplay(String imageName) {
		String[] files = imageName.split("\\.");
		waitForElementVisible(driver, NewEditPostPageUI.THUMBNAIL_IMG, files[0]);
		return isElementDisplayed(driver, NewEditPostPageUI.THUMBNAIL_IMG, files[0]);
	}

	public void clickToPublishButton() {
		// scrollToElementByJS(driver, NewEditPostPageUI.PUBLISH_BTN);
		waitForElementsVisible(driver, NewEditPostPageUI.PUBLISH_BTN);
		clickToElementByJS(driver, NewEditPostPageUI.PUBLISH_BTN);
	}

	public boolean isNewPostSuccessMessageDisplay() {
		waitForElementVisible(driver, NewEditPostPageUI.POST_PUBLISHED_TEXT);
		return isElementDisplayed(driver, NewEditPostPageUI.POST_PUBLISHED_TEXT);
	}

}
