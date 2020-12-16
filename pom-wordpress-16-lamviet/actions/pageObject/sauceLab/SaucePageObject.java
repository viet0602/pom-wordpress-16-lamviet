package pageObject.sauceLab;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import pageUI.sauceLab.SaucePageUI;

public class SaucePageObject extends AbstractPage {
	WebDriver driver;

	public SaucePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInSortDropDown(String value) {
		waitForElementClickable(driver, SaucePageUI.SORT_OPTION);
		selectValueInDropdown(driver, SaucePageUI.SORT_OPTION, value);
	}

	public boolean isNameSortAscending() {
		waitForElementClickable(driver,SaucePageUI.PRODUCT_NAME );
		return isDataSortedAscending(driver,SaucePageUI.PRODUCT_NAME);
	}

	public boolean isNameSortDescending() {
		waitForElementClickable(driver,SaucePageUI.PRODUCT_NAME );
		return isDataSortedDescending(driver,SaucePageUI.PRODUCT_NAME);
	}

	public boolean isPriceSortAscending() {
		waitForElementClickable(driver, SaucePageUI.PRODUCT_PRICE);
		return isDataFloatSortedAscending(driver, SaucePageUI.PRODUCT_PRICE);
	}

	public boolean isPriceSortDescending() {
		waitForElementClickable(driver, SaucePageUI.PRODUCT_PRICE);
		return isDataFloatSortedDescending(driver, SaucePageUI.PRODUCT_PRICE);
	}


}
