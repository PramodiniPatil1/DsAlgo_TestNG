package testClasses;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.RegisterPageObj;
import utils.ConfigReader;
import utils.LoggerLoad;
@Listeners(utils.Listner.class)
public class RegisterTests extends BaseClass {
	WebDriver driver;
    RegisterPageObj registerpage;

    @BeforeMethod
    public void setUpRegister() {
    	
        driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());
        registerpage = new RegisterPageObj(driver);
        driver.get(ConfigReader.getUrl());
        registerpage.clickGetStartedButton();
        registerpage.clickRegisterLink();
    }

    @DataProvider(name = "validRegisterData")
    public Object[][] validData() {
        return new Object[][] {
            {"Register", 3}
        };
    }

    @DataProvider(name = "passwordMismatchData")
    public Object[][] mismatchData() {
        return new Object[][] {
            {"Register", 1}
        };
    }

    @DataProvider(name = "emptyUsernameData")
    public Object[][] emptyData() {
        return new Object[][] {
            {"Register", 0}
        };
    }

    @DataProvider(name = "numericPasswordData")
    public Object[][] numericPasswordData() {
        return new Object[][] {
            {"Register", 2}
        };
    }

    @Test(priority = 1,dataProvider = "validRegisterData")
    public void testValidRegistration(String sheetName, int row) throws Exception {
        registerpage.fillRegistrationForm(sheetName, row);
        registerpage.clickRegisterButton();

        String actualMsg = registerpage.successMsg();
        LoggerLoad.info("Success Message: " + actualMsg);
        Assert.assertEquals(actualMsg, "You are logged in", "Login success message mismatch!");

    }

    @Test(priority = 2,dataProvider = "passwordMismatchData")

    public void testPasswordMismatchRegistration(String sheetName, int row) throws Exception {
        registerpage.fillRegistrationForm(sheetName, row);
        registerpage.clickRegisterButton();
        Assert.assertTrue(registerpage.isPasswordMismatchVisible());
        
        System.out.println("Password mismatch message: " + registerpage.getPasswordMismatchText());
    }

    @Test(priority = 3,dataProvider = "emptyUsernameData")
    public void testEmptyUsernameValidation(String sheetName, int row) throws Exception {
        registerpage.fillRegistrationForm(sheetName, row);
        registerpage.clickRegisterButton();
        String msg = registerpage.switchToElementAndGetValidationMessage();
        System.out.println("Browser validation message: " + msg);
        Assert.assertTrue(msg.contains("fill out this field"));
    }

    @Test(priority = 4,dataProvider = "numericPasswordData")
    public void testNumericPasswordValidation(String sheetName, int row) throws Exception {
        registerpage.fillRegistrationForm(sheetName, row);
        registerpage.clickRegisterButton();
        Assert.assertTrue(registerpage.isPasswordMismatchVisible());
        System.out.println("Password mismatch message: " + registerpage.getPasswordMismatchText());
    }

	
    @AfterMethod
    public void tearDown() {
        DriverFactory.closeDriver(); // Quit the driver after screenshot
    }

}
