package baseClass;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.lang.reflect.Method;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import driverManager.DriverFactory;
import dsAlgoPageObjects.HomePageObj;
import utils.ConfigReader;
import utils.LoggerLoad;

public class BaseClass {

	WebDriver driver = DriverFactory.getDriver();

	public BaseClass() {
		System.out.println("BaseClass Constructor Called");
	}

	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
//	public void SetUp() throws IOException {

	public void setUpSuite() throws IOException {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		ConfigReader.loadConfig();
		String browser = ConfigReader.getProperty("browser");

		DriverFactory.initializeDriver("chrome");
		driver = DriverFactory.getDriver();
		HomePageObj homepage = new HomePageObj(driver);
		System.out.println("BaseClass WebDriver: " + (driver != null));

		String url = ConfigReader.getProperty("Url");
		driver.get(url);
	}

	@BeforeMethod
	public void startTest(Method method) {

		DriverFactory.initializeDriver(ConfigReader.getProperty("browser"));
		WebDriver driver = DriverFactory.getDriver();

		String testName = method.getName();
		test = extent.createTest(testName);
	}

	@AfterMethod
	public void screenShot(ITestResult result) throws Exception {
		WebDriver driver = DriverFactory.getDriver();
		if (driver != null && result.getStatus() == ITestResult.FAILURE) {
			String scrShot = "screenshot_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Path screenshotDir = Path.of(System.getProperty("user.dir") + "/Screenshots");
			if (!Files.exists(screenshotDir)) {
				Files.createDirectories(screenshotDir);
			}

			Files.copy(screenshots.toPath(), screenshotDir.resolve(scrShot + ".png"));
			LoggerLoad.info("Screenshot saved: " + scrShot + ".png");
		}
	}

	@AfterSuite
	public void tearDown() {
		if (extent != null) {
			extent.flush(); // IMPORTANT: writes the report to disk
		}
		DriverFactory.closeDriver();
		driver.quit();
	}

}