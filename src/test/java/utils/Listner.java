package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import baseClass.BaseClass;

public class Listner implements ITestListener {
	
	WebDriver driver;
	BaseClass base = new BaseClass();
	
	
	    @Override
	    public void onTestStart(ITestResult result) {
	        System.out.println("Test Started: " + result.getName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        System.out.println("Test Passed: " + result.getName());
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        System.out.println("Test Failed: " + result.getName());
	        try {
				base.screenShot(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        LoggerLoad.info("Screenshot taken for failed test");
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        System.out.println("Test Skipped: " + result.getName());
	    }

	    @Override
	    public void onStart(org.testng.ITestContext context) {
	        System.out.println("Test Suite Started: " + context.getName());
	    }

	    @Override
	    public void onFinish(org.testng.ITestContext context) {
	        System.out.println("Test Suite Finished: " + context.getName());
	    }
	}


