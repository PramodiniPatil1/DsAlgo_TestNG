package baseClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;
import driverManager.DriverFactory;
import dsAlgoPageObjects.HomePageObj;
import utils.ConfigReader;
import utils.LoggerLoad;

public class BaseClass {

	WebDriver driver;
	HomePageObj homepage = new HomePageObj(driver);

	public BaseClass() {
        this.driver = DriverFactory.getDriver(); // Ensure WebDriver is initialized
        if (this.driver == null) {
            throw new IllegalStateException("WebDriver is null! Ensure it is initialized before passing.");
        }
        this.homepage = new HomePageObj(driver); // Pass initialized driver
    }


	@BeforeSuite
	public void SetUp() throws IOException {
/*		ConfigReader.loadConfig();

		String browser = ConfigReader.getProperty("browser");

		if (browser == null || browser.isEmpty()) {
			throw new IllegalArgumentException("Browser type not declared in config properties file");
		}
		LoggerLoad.info("Initializing WebDriver...");
		DriverFactory.initializeDriver(browser);
		driver = DriverFactory.getDriver();

		String url = ConfigReader.getProperty("Url");
		if (url == null || url.isEmpty()) {
			throw new IllegalArgumentException("URL not declared in config properties file");
		}
		LoggerLoad.info("Navigating to URL: " + url);
		driver.get(url);

	} */
	
	
		    ConfigReader.loadConfig();
		    String browser = ConfigReader.getProperty("browser");

		    DriverFactory.initializeDriver("chrome"); // Initialize WebDriver first
		    driver = DriverFactory.getDriver(); // Fetch the initialized driver

		    System.out.println("BaseClass WebDriver: " + (driver != null)); // Debugging
		    String url = ConfigReader.getProperty("Url");
		    driver.get(url);
		}


	public void screenShot(ITestResult result) throws Exception {
	    if (driver != null && result.getStatus() == ITestResult.FAILURE) {
	        String scrShot = "screenshot_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	        File screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        Path screenshotDir = Path.of("C:\\Users\\onlin\\eclipse-workspace\\DsAlgo_TestNG\\src\\test\\resources\\Screenshots");
	        if (!Files.exists(screenshotDir)) {
	            Files.createDirectories(screenshotDir);
	        }

	        Files.copy(screenshots.toPath(), screenshotDir.resolve(scrShot + ".png"));
	        LoggerLoad.info("Screenshot saved: " + scrShot + ".png");
	    }
	}


	@AfterSuite
	public void tearDown() {
		DriverFactory.closeDriver();
		driver.quit();
	}

}