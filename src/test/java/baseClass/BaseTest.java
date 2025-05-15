package baseClass;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import driverManager.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ConfigReader;
import utils.LoggerLoad;

public class BaseTest {
	WebDriver driver = DriverFactory.getDriver();
	ConfigReader configReader = new ConfigReader();



	@Before
	public void setUp() throws Exception {
		ConfigReader.loadConfig();

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
		
	}

	@After
	public void tearDown(Scenario scenario)  throws Exception  {

			        WebDriver driver = DriverFactory.getDriver();
	        	 if (driver != null) 
	        	            if (scenario.isFailed()) {
	        	String scrShot = "screenshot_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	 			File screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	 			Files.move(screenshots.toPath(), Path.of(
	 					"/Users/dineshdeshmukh/eclipse-workspace/NinjaGalaxy-dsAlgo/src/test/resources/Screenshots", scrShot + ".png"));
	 			LoggerLoad.info("Screenshot saved: " + scrShot + ".png");
	        }
	       
	        DriverFactory.closeDriver();
	    }		  
	 			
	}




