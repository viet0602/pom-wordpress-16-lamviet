package dataTable_Jquery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class DatatablePageObject extends AbstractPage {
	WebDriver driver;

	public DatatablePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToColumnByName(String columnName, String value) {
		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_HEADER_COLUMN_TEXT, columnName);
		sendkeyToElement(driver, DataTablePageUI.DYNAMIC_HEADER_COLUMN_TEXT, value, columnName);
		sendKeyboardToElement(driver, DataTablePageUI.DYNAMIC_HEADER_COLUMN_TEXT, Keys.ENTER, columnName);
		sleepInSecond(5);
	}

	public boolean isOnlyOneRowDisplayed(String textValue) {
		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_ONLY_ONEROW_WITHTEXT, textValue);
		return (countElementNumber(driver, DataTablePageUI.DYNAMIC_ONLY_ONEROW_WITHTEXT, textValue) == 1) && (isElementDisplayed(driver, DataTablePageUI.DYNAMIC_ONLY_ONEROW_WITHTEXT, textValue));

	}

	public void clickToDynamicIconByCountryName(String iconName, String countryName) {

		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_EDIT_REMOVE_ICON_BY_COUNTRYNAME, countryName, iconName);
		clickToElement(driver, DataTablePageUI.DYNAMIC_EDIT_REMOVE_ICON_BY_COUNTRYNAME, countryName, iconName);

	}

	public void navigateToDynamicPageByIndex(String pageNumber) {
		// scrollToElementByJS(driver, DataTablePageUI.DYNAMIC_PAGE_SELECTED_BY_INDEX, pageNumber);
		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_PAGE_SELECTED_BY_INDEX, pageNumber);
		clickToElement(driver, DataTablePageUI.DYNAMIC_PAGE_SELECTED_BY_INDEX, pageNumber);

	}

	public boolean isPageActiveByIndex(String pageNumber) {
		// scrollToElementByJS(driver, DataTablePageUI.DYNAMIC_PAGE_ACTIVED_BY_INDEX, pageNumber);
		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_PAGE_ACTIVED_BY_INDEX, pageNumber);
		return isElementDisplayed(driver, DataTablePageUI.DYNAMIC_PAGE_ACTIVED_BY_INDEX, pageNumber);
	}

	public void inputToDynamicTextBoxAtRowNumber(String columnName, String rowNumber, String inputValue) {
		waitForElementInvisible(driver, DataTablePageUI.DYNAMIC_COLUMN_POSITION_INDEX, columnName);
		int columnPosition = countElementNumber(driver, DataTablePageUI.DYNAMIC_COLUMN_POSITION_INDEX, columnName) + 1;
		sendkeyToElement(driver, DataTablePageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, inputValue, String.valueOf(columnPosition));
	}

}
