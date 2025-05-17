package testClasses;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.*;
import utils.ConfigReader;


public class DataStructureTests extends BaseClass {

    WebDriver driver;
    DataStructurePageObj dataStructurerpage;
    TryEditorPage tryEditorPage;
    HomePageObj homepage;
    RegisterPageObj registerpage;
    SignInPageObj signinpage;

    @BeforeClass
    public void setUpOnce() throws IOException {
        driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());

        dataStructurerpage = new DataStructurePageObj(driver);
        tryEditorPage = new TryEditorPage(driver);
        homepage = new HomePageObj(driver);
        registerpage = new RegisterPageObj(driver);
        signinpage = new SignInPageObj(driver);

        driver.get(ConfigReader.getUrl());
        homepage.clickGetStartedHomePageButton();
        homepage.clickSignInLink();
        signinpage.EnterFromExcel("login", 0);
        signinpage.clickloginButton();

        Assert.assertEquals(registerpage.successMsg(), "You are logged in", "Login failed or success message mismatch.");
    }

    @BeforeMethod
    public void resetToHomeBeforeTest() {
        driver.get(ConfigReader.getUrl());
        homepage.clickGetStartedHomePageButton();
    }

    @DataProvider(name = "validCodeProvider")
    public Object[][] validCodeData() throws IOException, OpenXML4JException {
        return new Object[][] {
            {"tryEditorCode", 0, "Python is fun!"}
        };
    }

    @DataProvider(name = "invalidCodeProvider")
    public Object[][] invalidCodeData() throws IOException, OpenXML4JException {
        return new Object[][] {
            {"tryEditorCode", 1, "SyntaxError"},
            // You can add more rows from Excel as needed
        };
    }

    @Test(priority = 1, dataProvider = "validCodeProvider")
    public void testValidOutputforTimeComplexity1(String sheetName, int rowNum, String expectedOutput) throws Exception {
        homepage.clickDsGetStartedButton();
        dataStructurerpage.ClickTimeComplexityLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();

        Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
    }

    @Test(priority = 2, dataProvider = "invalidCodeProvider")
    public void testInValidOutputforTimeComplexity(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
        homepage.clickDsGetStartedButton();
        dataStructurerpage.ClickTimeComplexityLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();

        String actualAlertText = tryEditorPage.HandleAlert();
        System.out.println("Actual Alert Text: " + actualAlertText);

        Assert.assertTrue(actualAlertText.contains(expectedAlertPart),
            "Expected alert to contain '" + expectedAlertPart + "', but got: " + actualAlertText);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
