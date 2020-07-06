package dataTable_Jquery;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.AbstractTest;

public class DataTable_Jquery extends AbstractTest {

	WebDriver driver;
	DatatablePageObject dataTablePage;

	@Parameters({ "browser", "url" })

	@BeforeClass
	public void beforeClass(String browserName, String urlvalue) {
		driver = getBrowserDriver(browserName, urlvalue);
		dataTablePage = new DatatablePageObject(driver);

	}

	// Open page in page https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/
	@Test
	public void TC_01_Input_To_Column_By_Name() {
		dataTablePage.inputToColumnByName("Country", "Angola");
		dataTablePage.refresh(driver);
		Assert.assertTrue(dataTablePage.isOnlyOneRowDisplayed("Angola"));
	}

	@Test
	public void TC_02_Edit_Delete_Icon_By_Country_Name() {
		dataTablePage.refresh(driver);

		dataTablePage.clickToDynamicIconByCountryName("remove", "Aruba");
		dataTablePage.clickToDynamicIconByCountryName( "edit","Angola");

	}

	@Test
	public void TC_03_Paging_By_Page_Index() {
		dataTablePage.refresh(driver);
		dataTablePage.navigateToDynamicPageByIndex("11");
		Assert.assertTrue(dataTablePage.isPageActiveByIndex("11"));
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
