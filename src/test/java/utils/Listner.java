package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import driverManager.DriverFactory;

public class Listner extends ExtentITestListenerClassAdapter implements ITestListener {

    // ExtentReports and ExtentTest objects
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

    	 DriverFactory.getDriver(); 

        System.out.println("Test Suite Started: " + context.getName());
        try {
            ExtendManager.extentTest();  // Initialize ExtentReports
            extent = ExtendManager.extent;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished: " + context.getName());
        ExtendManager.endReport(); // Flush the report
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getName());
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);  // Set in ThreadLocal for parallel execution safety
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        WebDriver driver = DriverFactory.getDriver();  // Get driver directly

        if (driver != null) {
            try {
                // Handle unexpected alert if present
            

                String scrShot =  new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Path screenshotDir = Path.of(System.getProperty("user.dir") + "/Screenshots");
                if (!Files.exists(screenshotDir)) {
                    Files.createDirectories(screenshotDir);
                }
                Files.copy(screenshots.toPath(), screenshotDir.resolve(scrShot + ".png"));
                LoggerLoad.info("Screenshot saved: " + scrShot + ".png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            LoggerLoad.info("WebDriver is null, cannot take screenshot.");
        }

        test.get().fail(result.getThrowable());
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
        test.get().skip("Test Skipped");
    }
}
