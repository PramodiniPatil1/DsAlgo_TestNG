package driverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.LoggerLoad;
import java.time.Duration;

public class DriverFactory {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver initializeDriver(String browser) {
        if (tlDriver.get() == null) 
            switch (browser.toLowerCase()) {
                case "chrome":
                    tlDriver.set(new ChromeDriver());
                    break;
                case "firefox":
                    tlDriver.set(new FirefoxDriver());
                    break;
                case "edge":
                    tlDriver.set(new EdgeDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

           tlDriver.get().manage().window().maximize();
        tlDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        return tlDriver.get();
    }
    
    public static WebDriver getDriver() {
        return tlDriver.get();
    }
    
    public static void closeDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();  
            LoggerLoad.info("Browser closed successfully.");
        } else {
        	LoggerLoad.info("Driver is already null, nothing to close.");
        }
    }

	
}
