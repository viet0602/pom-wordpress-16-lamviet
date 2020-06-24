package browserFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import common.GlobalConstants;

public abstract class DriverManager {
	WebDriver driver;

	abstract void createDriver();

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public WebDriver getDriver() {
		if (driver == null) {
			createDriver();
		}
		driver.get(GlobalConstants.WORDPRESS_URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
		
	}
}
