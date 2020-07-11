package pageUI.WordPress;

public class AbstractPageUI {
	public static final String POSTS_LINK= "//div[@class='wp-menu-name'and text()='Posts']";
	public static final String MEDIA_LINK = "//div[@class='wp-menu-name'and text()='Media']";
	public static final String LINKS_LINK = "//div[@class='wp-menu-name'and text()='Links']";
	public static final String PAGES_LINK = "//div[@class='wp-menu-name'and text()='Pages']";
	public static final String COMMENTS_LINK = "//div[@class='wp-menu-name'and contains(text(),'Comments')]";
	public static final String FEEDBACK_LINK = "//div[@class='wp-menu-name'and text()='Feedback']";
	public static final String PROFILE_LINK = "//div[@class='wp-menu-name'and text()='Profile']";
	public static final String TOOLS_LINK = "//div[@class='wp-menu-name'and text()='Tools']";
	public static final String SETTINGS_LINK = "//div[@class='wp-menu-name'and text()='Settings']";	
	//Dynamic locator
	public static final String DYNAMIC_PAGE_LINK = "//div[@class='wp-menu-name'and text()='%s']";	
	public static final String UPLOAD_FILE_TYPE="//input[@type='file']";
	public static final String MEDIA_PROGRESS_BAR_ICON="//div[@class='thumbnail']/div[@class='media-progress-bar']";
	public static final String ALL_UPLOADED_IMAGE = "//div[@class='thumbnail']//img";

}
