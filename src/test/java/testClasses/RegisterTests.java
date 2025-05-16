package testClasses;


import org.testng.Assert;
import org.testng.annotations.*;
import baseClass.BaseClass;
import dsAlgoPageObjects.RegisterPageObj;
;

public class RegisterTests extends BaseClass{
	    RegisterPageObj registerpage;

	    @BeforeClass
	    public void setUpRegister() {
	        registerpage = new RegisterPageObj(driver); // Just this is enough
	       
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


