package pageUI.WordPress;

public class AbstractPageUI {
	public static final String POSTS_LINK = "//div[@class='wp-menu-name'and text()='Posts']";
	public static final String MEDIA_LINK = "//div[@class='wp-menu-name'and text()='Media']";
	public static final String LINKS_LINK = "//div[@class='wp-menu-name'and text()='Links']";
	public static final String PAGES_LINK = "//div[@class='wp-menu-name'and text()='Pages']";
	public static final String COMMENTS_LINK = "//div[@class='wp-menu-name'and contains(text(),'Comments')]";
	public static final String FEEDBACK_LINK = "//div[@class='wp-menu-name'and text()='Feedback']";
	public static final String PROFILE_LINK = "//div[@class='wp-menu-name'and text()='Profile']";
	public static final String TOOLS_LINK = "//div[@class='wp-menu-name'and text()='Tools']";
	public static final String SETTINGS_LINK = "//div[@class='wp-menu-name'and text()='Settings']";
	public static final String DYNAMIC_PAGE_LINK = "//div[@class='wp-menu-name'and text()='%s']";
	public static final String UPLOAD_FILE_TYPE = "//input[@type='file']";
	public static final String MEDIA_PROGRESS_BAR_ICON = "//div[@class='thumbnail']/div[@class='media-progress-bar']";
	public static final String ALL_UPLOADED_IMAGE = "//div[@class='thumbnail']//img";
	public static final String DYNAMIC_ROW_VALUE_AT_COLUMN_NAME = "//td[@data-colname='%s']//a[text()='%s']";
	public static final String DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE = "//p[@class='post-categories']/a[text()='%s']/parent::p/following-sibling::h2[@class='post-title']/a[text()='%s']/parent::h2/following-sibling::p[@class='post-meta']/a[text()='%s']";
	public static final String DYNAMIC_TITLE_POST_WITH_IMAGE = "//a[@title='%s']/img[contains(@src,'%s')]";
	public static final String DYNAMIC_TITLE_POST_ADMIN_PAGE = "//h2[@class='post-title']//a[text()='%s']";
	public static final String DETAIL_PAGE_CATEGOGY = "//header[@class='post-header']//a[text()='%s']";
	public static final String DETAIL_PAGE_TITLE = "//header[@class='post-header']//h1[text()='%s']";
	public static final String DETAIL_PAGE_CONTENT = "//div[@class='post-content']/p[contains(text(),'%s')]";
	public static final String DETAIL_PAGE_CREATED_DATE = "//span[@class='post-meta-date']//a[(text()='%s' )]";
	public static final String DETAIL_PAGE_AUTHOR = "//span[@class='post-meta-author']/a[text()='%s']";
	public static final String DETAIL_PAGE_IMAGE = "//figure[@class='post-image clear-fix']//img[contains(@src,'%s')]";
	

}
