package testClasses;


import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import driverManager.DriverFactory;
import dsAlgoPageObjects.RegisterPageObj;
import utils.ConfigReader;

public class RegisterTests {
		WebDriver driver;
	    RegisterPageObj registerpage;

	
	 
	    @BeforeClass
	    public void setUp() {
	        // Step 1: Read browser name from config
	        String browser = ConfigReader.getBrowserType();  // Make sure this returns "chrome", "firefox", or "edge"

	        // Step 2: Initialize driver
	        driver = DriverFactory.initializeDriver(browser);

	        // Step 3: Initialize page object
	        registerpage = new RegisterPageObj(driver);

	        // Step 4: Navigate to URL
	        driver.get(ConfigReader.getUrl());
	    }




	    @Test
	    public void testValidRegistration() throws Exception {
	        registerpage.clickGetStartedButton();
	        registerpage.clickRegisterLink();
	        
			registerpage.fillRegistrationForm("Register",3);  
	        registerpage.clickRegisterButton();

	        Assert.assertTrue(registerpage.checkIfRegisterSuccessMsgIsDisplayed(), "Success message not displayed");
	        System.out.println("Success message: " + registerpage.successMsg());
	    }

	    @Test
	    public void testPasswordMismatchRegistration() throws Exception {
	        registerpage.clickGetStartedButton();
	        registerpage.clickRegisterLink();
	        registerpage.fillRegistrationForm("Register", 1); // 
	        registerpage.clickRegisterButton();

	        Assert.assertTrue(registerpage.isPasswordMismatchVisible(), "Mismatch error not displayed");
	        System.out.println("Password mismatch message: " + registerpage.getPasswordMismatchText());
	    }

	    @Test
	    public void testEmptyUsernameValidation() throws Exception {
	        registerpage.clickGetStartedButton();
	        registerpage.clickRegisterLink();
	        registerpage.fillRegistrationForm("Register",0 ); 
	        registerpage.clickRegisterButton();

	        String msg = registerpage.switchToElementAndGetValidationMessage();
	        System.out.println("Browser validation message: " + msg);
	        Assert.assertTrue(msg.contains("fill out this field"), "Expected browser validation not displayed");
	    }
	    
	    
	    @Test
	    public void testNumericPasswordValidation() throws Exception {
	        registerpage.clickGetStartedButton();
	        registerpage.clickRegisterLink();
	        registerpage.fillRegistrationForm("Register", 3); 
	        registerpage.clickRegisterButton();

	        Assert.assertTrue(registerpage.isPasswordMismatchVisible(), "Mismatch error not displayed");
	        System.out.println("Password mismatch message: " + registerpage.getPasswordMismatchText());
	    }
	    
	      @AfterClass
	      public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}


