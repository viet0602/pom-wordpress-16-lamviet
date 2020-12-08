package browserFactory;

public class BrowserDriverFactory {
	public static DriverManager getBrowserDriver(String browserName) {
		DriverManager driverManager;
		switch (browserName) {
		case "chrome":
			driverManager = new ChromeDriverManager();
		case "firefox":
			driverManager = new FireFoxDriverManager();
		default:
			driverManager = new ChromeHeadLessDriverManager();
			break;
		}
		 return driverManager;
	}

}
