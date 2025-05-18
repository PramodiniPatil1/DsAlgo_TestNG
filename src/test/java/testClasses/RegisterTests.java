package testClasses;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.RegisterPageObj;
import utils.ConfigReader;



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
            {"Register", 4}
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

    @Test(dataProvider = "validRegisterData")
    public void testValidRegistration(String sheetName, int row) throws Exception {
        registerpage.fillRegistrationForm(sheetName, row);
        registerpage.clickRegisterButton();

        Assert.assertTrue(registerpage.checkIfRegisterSuccessMsgIsDisplayed(), "Success message not displayed");
        System.out.println("Success message: " + registerpage.successMsg());
    }

    @Test(dataProvider = "passwordMismatchData")
    public void testPasswordMismatchRegistration(String sheetName, int row) throws Exception {
        registerpage.fillRegistrationForm(sheetName, row);
        registerpage.clickRegisterButton();


        Assert.assertTrue(registerpage.isPasswordMismatchVisible(), "Mismatch error not displayed");
        System.out.println("Password mismatch message: " + registerpage.getPasswordMismatchText());
    }

    @Test(dataProvider = "emptyUsernameData")
    public void testEmptyUsernameValidation(String sheetName, int row) throws Exception {
        registerpage.fillRegistrationForm(sheetName, row);
        registerpage.clickRegisterButton();

        String msg = registerpage.switchToElementAndGetValidationMessage();
        System.out.println("Browser validation message: " + msg);
        Assert.assertTrue(msg.contains("fill out this field"), "Expected browser validation not displayed");
    }

    @Test(dataProvider = "numericPasswordData")
    public void testNumericPasswordValidation(String sheetName, int row) throws Exception {
        registerpage.fillRegistrationForm(sheetName, row);
        registerpage.clickRegisterButton();

        Assert.assertTrue(registerpage.isPasswordMismatchVisible(), "Mismatch error not displayed");
        System.out.println("Password mismatch message: " + registerpage.getPasswordMismatchText());
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.closeDriver();
    }
}
