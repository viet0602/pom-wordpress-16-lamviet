package com.wordpress.testdata;

import org.openqa.selenium.WebDriver;

import pageObjects.WordPress.CommentsPageObject;
import pageObjects.WordPress.DashBoardPageObject;
import pageObjects.WordPress.FeedbackPageObject;
import pageObjects.WordPress.LinksPageObject;
import pageObjects.WordPress.LoginPageObject;
import pageObjects.WordPress.MediaPageObject;
import pageObjects.WordPress.PagesPageObject;
import pageObjects.WordPress.PostsPageObject;
import pageObjects.WordPress.ProfilePageObject;
import pageObjects.WordPress.SettingsPageObject;
import pageObjects.WordPress.ToolsPageObject;

public class PageGeneratorManager {
	public static LoginPageObject getLoginPage (WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static DashBoardPageObject getDashBoardPage (WebDriver driver) {
		return new DashBoardPageObject(driver);
	}
	public static PagesPageObject getPagesPage(WebDriver driver) {
		return new PagesPageObject(driver);
	}
	public static FeedbackPageObject getFeedbackPage(WebDriver driver) {
		return new FeedbackPageObject(driver);
	}
	public static MediaPageObject getMediaPage(WebDriver driver) {
		return new MediaPageObject(driver);
	}
	public static PostsPageObject getPostsPage(WebDriver driver) {
		return new PostsPageObject(driver);
	}
	public static CommentsPageObject getCommentsPage(WebDriver driver) {
		return new CommentsPageObject(driver);
	}
	public static LinksPageObject getLinksPage(WebDriver driver) {
		return new LinksPageObject(driver);
	}
	public static ProfilePageObject getProfilePage(WebDriver driver) {
		return new ProfilePageObject(driver);
	}
	public static SettingsPageObject getSettingsPage(WebDriver driver) {
		return new SettingsPageObject(driver);
	}
	public static ToolsPageObject getToolsPage(WebDriver driver) {
		return new ToolsPageObject(driver);
	}
}
