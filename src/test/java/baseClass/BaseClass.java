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

	WebDriver driver = DriverFactory.getDriver();
	
	//Added constructor 
	  public BaseClass() {
	        System.out.println("BaseClass Constructor Called");
	    }



	@BeforeSuite
	public void SetUp() throws IOException {

		   	 ConfigReader.loadConfig();
		    String browser = ConfigReader.getProperty("browser");
		   
		    DriverFactory.initializeDriver("chrome"); // Initialize WebDriver first
		    driver = DriverFactory.getDriver(); // Fetch the initialized driver
		    HomePageObj homepage = new HomePageObj(driver);
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