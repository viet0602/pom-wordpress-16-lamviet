package com.saucelab.sort;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.AbstractTest;
import pageObject.sauceLab.SaucePageObject;

public class Sort_Asc_Desc extends AbstractTest {

	WebDriver driver;
	SaucePageObject sortPage;

	@Parameters({ "browser", "url" })

	@BeforeClass
	public void beforeClass(String browserName, String urlvalue) {
		driver = getBrowserDriver(browserName, urlvalue);
		sortPage = new SaucePageObject(driver);

	}

	// Open page in page
	// https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/
	@Test
	public void TC_01_Sort_By_Name() {
		sortPage.selectItemInSortDropDown("Name (A to Z)");
		verifyTrue(sortPage.isNameSortAscending());
		sortPage.selectItemInSortDropDown("Name (Z to A)");
		verifyTrue(sortPage.isNameSortDescending());
	}

	@Test
	public void TC_02_Sort_By_Price() {
		sortPage.selectItemInSortDropDown("Price (low to high)");
		verifyTrue(sortPage.isPriceSortAscending());
		sortPage.selectItemInSortDropDown("Price (high to low)");
		verifyTrue(sortPage.isPriceSortDescending());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
