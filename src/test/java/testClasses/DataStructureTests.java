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
import utils.DataProviders;

public class DataStructureTests extends BaseClass {

    WebDriver driver;
    DataStructurePageObj dataStructurerpage;
    TryEditorPage tryEditorPage;
    HomePageObj homepage;
    RegisterPageObj registerpage;
    SignInPageObj signinpage;

    @BeforeMethod
    public void setUpDataStructure() throws IOException {
        driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());
        registerpage = new RegisterPageObj(driver);
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
        Assert.assertEquals(registerpage.successMsg(), "You are logged in");
   
        driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
        
    }
    @Test(priority = 1, dataProvider = "ValidPythonCode",dataProviderClass = DataProviders.class)
        public void testValidOutputforTimeComplexity1(String sheetName, int rowNum, String expectedOutput) throws Exception {
        homepage.clickDsGetStartedButton();
        dataStructurerpage.ClickTimeComplexityLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();

        Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
    }

    @Test(priority = 1, dataProvider = "InvalidPythonCode",dataProviderClass = DataProviders.class)
    public void testInValidOutputforTimeComplexity(String sheetName, int rowNum, String expectedAlertPart) throws IOException, OpenXML4JException {
        homepage.clickDsGetStartedButton();
        dataStructurerpage.ClickTimeComplexityLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();
        String alertMessage = tryEditorPage.getAlertText();
 		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
 				"Alert message did not contain expected text. Actual: " + alertMessage);
 	}
	@AfterMethod
    public void tearDown() {
        DriverFactory.closeDriver();  // This calls quit() and also removes the ThreadLocal
    
    }
}
