package baseClass;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import driverManager.DriverFactory;
import dsAlgoPageObjects.HomePageObj;
import utils.ConfigReader;

public class BaseClass {

    protected WebDriver driver;
    protected HomePageObj homepage;

    public BaseClass() {
        // Do NOT initialize driver or homepage here!
    }

    @BeforeSuite
    public void setUp() throws IOException {
        ConfigReader.loadConfig();
        String browser = ConfigReader.getProperty("browser");

        // Initialize WebDriver first
        DriverFactory.initializeDriver(browser);
        driver = DriverFactory.getDriver();

        if (driver == null) {
            throw new IllegalStateException("WebDriver is null after initialization!");
        }

        // Now initialize page objects with driver
        homepage = new HomePageObj(driver);

        // Navigate to the URL
        String url = ConfigReader.getProperty("Url");
        driver.get(url);
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        DriverFactory.closeDriver();
    }
}
