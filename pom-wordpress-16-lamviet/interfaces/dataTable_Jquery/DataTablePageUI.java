package dataTable_Jquery;

public class DataTablePageUI {
	public static final String DYNAMIC_HEADER_COLUMN_TEXT = "//div[@class='qgrd-header-text' and text()='%s' ]/parent::div/following-sibling::input";
	public static final String DYNAMIC_ONLY_ONEROW_WITHTEXT = "//tr[not(@style='display: none;')]//td[text()='%s']";
	public static final String DYNAMIC_EDIT_REMOVE_ICON_BY_COUNTRYNAME = "//td[@data-key='country' and text()='%s']/preceding-sibling::td//button[contains(@class,'%s')]";
	
	public static final String DYNAMIC_PAGE_BY_INDEX = "//a[@class='qgrd-pagination-page-link active' and text()='%s']";
	
}