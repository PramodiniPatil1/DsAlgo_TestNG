package testClasses;


import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import pages.RegisterPageObj;
import driverManager.DriverFactory;
import utils.ConfigReader;

public class RegisterTests {
		WebDriver driver;
	    RegisterPageObj registerPage;

	
	    @BeforeClass
	    public void setUp() {
	        // Step 1: Initialize WebDriver
	        driver = DriverFactory.initializeDriver("chrome");

	        // Step 2: Initialize Page Object with the driver
	        registerPage = new RegisterPageObj(driver);

	        // Step 3: Navigate to the base URL
	        driver.get(ConfigReader.getUrl());
	    }


	    @Test(priority = 1)
	    public void testValidRegistration() throws Exception {
	        registerPage.clickGetStartedButton();
	        registerPage.clickRegisterLink();
	        
			registerPage.fillRegistrationForm("Register",3);  
	        registerPage.clickRegisterButton();

	        Assert.assertTrue(registerPage.checkIfRegisterSuccessMsgIsDisplayed(), "Success message not displayed");
	        System.out.println("Success message: " + registerPage.successMsg());
	    }

	    @Test(priority = 2)
	    public void testPasswordMismatchRegistration() throws Exception {
	        registerPage.clickGetStartedButton();
	        registerPage.clickRegisterLink();
	        registerPage.fillRegistrationForm("Register", 1); // 
	        registerPage.clickRegisterButton();

	        Assert.assertTrue(registerPage.isPasswordMismatchVisible(), "Mismatch error not displayed");
	        System.out.println("Password mismatch message: " + registerPage.getPasswordMismatchText());
	    }

	    @Test(priority = 3)
	    public void testEmptyUsernameValidation() throws Exception {
	        registerPage.clickGetStartedButton();
	        registerPage.clickRegisterLink();
	        registerPage.fillRegistrationForm("Register",0 ); 
	        registerPage.clickRegisterButton();

	        String msg = registerPage.switchToElementAndGetValidationMessage();
	        System.out.println("Browser validation message: " + msg);
	        Assert.assertTrue(msg.contains("fill out this field"), "Expected browser validation not displayed");
	    }
	    
	    
	    @Test(priority = 3)
	    public void testNumericPasswordValidation() throws Exception {
	        registerPage.clickGetStartedButton();
	        registerPage.clickRegisterLink();
	        registerPage.fillRegistrationForm("Register", 3); 
	        registerPage.clickRegisterButton();

	        Assert.assertTrue(registerPage.isPasswordMismatchVisible(), "Mismatch error not displayed");
	        System.out.println("Password mismatch message: " + registerPage.getPasswordMismatchText());
	    }
	    
	      @AfterClass
	      public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}


