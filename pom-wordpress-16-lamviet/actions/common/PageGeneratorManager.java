package common;

import org.openqa.selenium.WebDriver;

import pageObjects.admin.WordPress.CommentsPageObject;
import pageObjects.admin.WordPress.DashBoardPageObject;
import pageObjects.admin.WordPress.FeedbackPageObject;
import pageObjects.admin.WordPress.LinksPageObject;
import pageObjects.admin.WordPress.LoginPageObject;
import pageObjects.admin.WordPress.MediaPageObject;
import pageObjects.admin.WordPress.PagesPageObject;
import pageObjects.admin.WordPress.PostsAdminPageObject;
import pageObjects.admin.WordPress.ProfilePageObject;
import pageObjects.admin.WordPress.SettingsPageObject;
import pageObjects.admin.WordPress.ToolsPageObject;
import pageObjects.user.WordPress.HomeUserPageObject;
import pageObjects.user.WordPress.PostDetailPageObject;
import pageObjects.user.WordPress.SearchResultPageObject;

public class PageGeneratorManager {
	public static LoginPageObject getLoginAdminPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static DashBoardPageObject getDashBoardAdminPage(WebDriver driver) {
		return new DashBoardPageObject(driver);
	}

	public static PagesPageObject getPagesAdminPage(WebDriver driver) {
		return new PagesPageObject(driver);
	}

	public static FeedbackPageObject getFeedbackAdminPage(WebDriver driver) {
		return new FeedbackPageObject(driver);
	}

	public static MediaPageObject getMediaAdminPage(WebDriver driver) {
		return new MediaPageObject(driver);
	}

	public static PostsAdminPageObject getPostsAdminPage(WebDriver driver) {
		return new PostsAdminPageObject(driver);
	}

	public static CommentsPageObject getCommentsAdminPage(WebDriver driver) {
		return new CommentsPageObject(driver);
	}

	public static LinksPageObject getLinksAdminPage(WebDriver driver) {
		return new LinksPageObject(driver);
	}

	public static ProfilePageObject getProfileAdminPage(WebDriver driver) {
		return new ProfilePageObject(driver);
	}

	public static SettingsPageObject getSettingsAdminPage(WebDriver driver) {
		return new SettingsPageObject(driver);
	}

	public static ToolsPageObject getToolsAdminPage(WebDriver driver) {
		return new ToolsPageObject(driver);
	}

	public static NewEditPostPageObject getNewEditPostAdminPage(WebDriver driver) {
		return new NewEditPostPageObject(driver);
	}

	public static HomeUserPageObject getHomeUserPage(WebDriver driver) {
		return new HomeUserPageObject(driver);
	}

	public static PostDetailPageObject getPostDetailUserPage(WebDriver driver) {
		return new PostDetailPageObject(driver);
	}

	static SearchResultPageObject getSearchResultUserPage(WebDriver driver) {
		return new SearchResultPageObject(driver);
	}
	
}
