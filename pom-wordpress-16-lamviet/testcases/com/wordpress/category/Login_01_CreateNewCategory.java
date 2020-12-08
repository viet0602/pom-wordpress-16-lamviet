package com.wordpress.category;

import org.testng.annotations.Test;
import common.AbstractTest;
import common.NewEditPostPageObject;
import common.PageGeneratorManager;
import pageObjects.admin.WordPress.DashBoardPageObject;
import pageObjects.admin.WordPress.LoginPageObject;
import pageObjects.admin.WordPress.PostsAdminPageObject;
import pageObjects.user.WordPress.HomeUserPageObject;
import pageObjects.user.WordPress.PostDetailPageObject;
import pageObjects.user.WordPress.SearchResultPageObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Login_01_CreateNewCategory extends AbstractTest {

	String imageName = "image.jpg";
	int fakeNumber = randomNumber();
	String title = "[Vicky0602]Title_Live_Coding test" + fakeNumber;
	String content = "[Vicky0602]Content Live coding test" + fakeNumber;
	String tagName = "Vicky0602" + fakeNumber;
	String categoryName = "NEW LIVE CODING";
	String author = "Automation FC";
	String today = getWordPressToday();

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getLoginAdminPage(driver);
		loginPage.inputToEmailTextBox("automationeditor");
		loginPage.clickToContinueOrLoginButton();
		loginPage.inputToPwTextBox("automationfc");
		dashboardPage = loginPage.clickToContinueOrLoginButton();
		verifyTrue(dashboardPage.isHeaderTextDisplay());
	}

	@Test
	public void Post_01_Create_New_Post_At_Admin_Page() {
		dashboardPage.openMenuByPageName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
		newEditPostAdminPage = postAdminPage.clickToAddNewButton();
		newEditPostAdminPage.inputTitleName(title);
		newEditPostAdminPage.inputBodyPost(content);
		newEditPostAdminPage.selectCategoryCheckBox(categoryName);
		newEditPostAdminPage.inputTagName(tagName);
		newEditPostAdminPage.clickAddTagBtn();
		newEditPostAdminPage.clickToSetFeaturedImageText();
		newEditPostAdminPage.clickToUploadTab();
		newEditPostAdminPage.uploadMultiple(driver, imageName);
		verifyTrue(newEditPostAdminPage.areFileUploadDisplay(driver, imageName));
		newEditPostAdminPage.clickToSetFeatureImagetButton();
		verifyTrue(newEditPostAdminPage.isThumbnailImageDisplay(imageName));
		newEditPostAdminPage.clickToPublishButton();
		verifyTrue(newEditPostAdminPage.isNewPostSuccessMessageDisplay());

		// Search Post at admin page
		newEditPostAdminPage.openMenuByPageName(driver, "Posts");
		postAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
		postAdminPage.inputToSearchTextBox("");
		postAdminPage.clickToSearchPostsButton();
		verifyTrue(postAdminPage.isValueDisplayAtColumnName(driver, "Title", title));
		verifyTrue(postAdminPage.isValueDisplayAtColumnName(driver, "Author", author));
		verifyTrue(postAdminPage.isValueDisplayAtColumnName(driver, "Categories", categoryName));
		verifyTrue(postAdminPage.isValueDisplayAtColumnName(driver, "Tags", tagName));

		// Navigate At User Page
		homeUserPage = postAdminPage.openUserPage(driver);

		// Design in abstract page (reuse at search result page)
		verifyTrue(homeUserPage.isNewPostDisplayedOnLatestPost(driver, categoryName, title, today));
		verifyTrue(homeUserPage.isPostImageDisplayedTitleName(driver, title, imageName));

		// Go Post Detail At User Page
		postDetailUserPage = homeUserPage.clickToDetailPostByTitleName(driver, title);

		verifyTrue(postDetailUserPage.isCategoryNameDisplayed(driver, categoryName));
		verifyTrue(postDetailUserPage.isTitleNameDisplayed(driver, title));
		verifyTrue(postDetailUserPage.isContentDisplayed(driver, content));
		verifyTrue(postDetailUserPage.isDateCreatedDisplayed(driver, today));
		verifyTrue(postDetailUserPage.isPostImageDisplayed(driver, imageName));
		verifyTrue(postDetailUserPage.isAuthorDisplayed(driver, author));

		// Search Post at User Page
		searchResultUserPage = postDetailUserPage.inputToSearchTextBoxAtUserPage(driver, "title");
		// verifyTrue(searchResultUserPage.isNewPostDisplayedOnLatestPost("category", "title", "date created"));
		// verifyTrue(searchResultUserPage.isPostImageDisplayedTitleName("title", "NewPost.hpg"));

	}

//	@Test
//	public void Post_02_Edit_Post_At_Admin_Page() {
//		// Navigate to Admin Site
//		dashboardPage = searchResultUserPage.openAdminLoggedPage(driver);
//		dashboardPage.openMenuByPageName(driver, "Posts");
//		postAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
//
//		// Search Post At Admin Page
//		postAdminPage.inputToSearchTextBox("");
//		postAdminPage.clickToSearchPostsButton();
//		verifyTrue(postAdminPage.isOnlyOnceRowDisplay("title", "author", "category", "tag"));
//
//		// click to Post Detail
//		newEditPostAdminPage = postAdminPage.clickToDetailPostByTitleName("title");
//
//		// Edit Post
//		newEditPostAdminPage.inputTitleName("");
//		newEditPostAdminPage.inputBodyPost("");
//		newEditPostAdminPage.deselectCategory("");
//		newEditPostAdminPage.selectCategory("");
//		newEditPostAdminPage.inputTagName("");
//		newEditPostAdminPage.clickAddButton();
//		newEditPostAdminPage.clickToDeleteTagIconWithName("tag new name");
//
//		newEditPostAdminPage.clickToUpdateButton();
//		verifyTrue(newEditPostAdminPage.isSuccessMessageDisplayedWithValue("Post Updated"));
//
//		// Search Post At Admin
//		newEditPostAdminPage.openMenuByPageName(driver, "Posts");
//		postAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
//		postAdminPage.inputToSearchTextBox("");
//		postAdminPage.clickToSearchPostsButton();
//		verifyTrue(postAdminPage.isOnlyOnceRowDisplay("edit_title", "author", "edit_category", "edit_tag"));
//
//		// Navigate To User Site
//
//		homeUserPage = postAdminPage.openUserPage(driver);
//		// Design in abstract page (reuse at search result page)
//		verifyTrue(homeUserPage.isNewPostDisplayedOnLatestPost("edit_category", "edit_title", "date created"));
//		verifyTrue(homeUserPage.isPostImageDisplayedTitleName("edit_title", "NewPost.hpg"));
//		// Go Post Detail At User Page
//		homeUserPage = postAdminPage.clickToDetailPostByTitleName("edit_title");
//
//		verifyTrue(postDetailUserPage.isCategoryNameDisplayed("edit_category"));
//		verifyTrue(postDetailUserPage.isTitleNameDisplayed("edit_title"));
//		verifyTrue(postDetailUserPage.isContentDisplayed("edit_content"));
//		verifyTrue(postDetailUserPage.isDateCreatedDisplayed("date created"));
//		verifyTrue(postDetailUserPage.isPostImageDisplayed("NewPost.jpg"));
//		verifyTrue(postDetailUserPage.isAuthorDisplayed("author"));
//
//		// Search Post at User Page
//
//		searchResultUserPage = postDetailUserPage.inputToSearchTextBoxAtUserPage(driver, "edit_title");
//		verifyTrue(searchResultUserPage.isNewPostDisplayedOnLatestPost("edit_category", "edit_title", "date created"));
//		verifyTrue(searchResultUserPage.isPostImageDisplayedTitleName("edit_title", "NewPost.hpg"));
//
//	}
//
//	@Test
//	public void Post_03_Delete_Post_At_Admin_Page() {
//		// Navigate to Admin Site
//		dashboardPage = searchResultUserPage.openAdminLoggedPage(driver);
//		dashboardPage.openMenuByPageName(driver, "Posts");
//		postAdminPage = PageGeneratorManager.getPostsAdminPage(driver);
//
//		// Search Post At Admin Page
//		postAdminPage.inputToSearchTextBox("");
//		postAdminPage.clickToSearchPostsButton();
//		verifyTrue(postAdminPage.isOnlyOnceRowDisplay("edit_title", "author", "edit_category", "edit_tag"));
//
//		// click to Post Detail
//		newEditPostAdminPage = postAdminPage.clickToDetailPostByTitleName("title");
//
//		postAdminPage = newEditPostAdminPage.clickToMoveToTrashButton();
//		// create in AbstractPage
//		verifyTrue(postAdminPage.isSuccessMessageDisplayWithValue("1 post moved to the Trash."));
//		// Search Post At Admin Page
//		postAdminPage.inputToSearchTextBox("");
//		postAdminPage.clickToSearchPostsButton();
//		verifyFalse(postAdminPage.isOnlyOnceRowDisplay("edit_title", "author", "edit_category", "edit_tag"));
//		verifyTrue(postAdminPage.isNoPostFoundMessageDisplay("No posts found"));
//		// Navigate To User Site
//
//		homeUserPage = postAdminPage.openUserPage(driver);
//		// Design in abstract page (reuse at search result page)
//		verifyTrue(homeUserPage.isNewPostDisplayedOnLatestPost("edit_category", "edit_title", "date created"));
//		verifyTrue(homeUserPage.isPostImageDisplayedTitleName("edit_title", "NewPost.hpg"));
//		// Search Post at User Page
//		searchResultUserPage = postDetailUserPage.inputToSearchTextBoxAtUserPage(driver, "edit_title");
//		verifyFalse(searchResultUserPage.isNewPostDisplayedOnLatestPost("edit_category", "edit_title", "date created"));
//		verifyFalse(searchResultUserPage.isPostImageDisplayedTitleName("edit_title", "NewPost.hpg"));
//	}

	@AfterClass
	public void afterClass() {
	}

	WebDriver driver;
	LoginPageObject loginPage;
	DashBoardPageObject dashboardPage;
	PostsAdminPageObject postAdminPage;
	NewEditPostPageObject newEditPostAdminPage;
	HomeUserPageObject homeUserPage;
	PostDetailPageObject postDetailUserPage;
	SearchResultPageObject searchResultUserPage;
}
