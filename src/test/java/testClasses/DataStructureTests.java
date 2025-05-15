package testClasses;


import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.DataStructurePageObj;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.RegisterPageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.TryEditorPage;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.exc.InvalidFormatException;
import utils.ConfigReader;

public class DataStructureTests extends BaseClass {

    
    static DataStructurePageObj dataStructurerpage;
    static TryEditorPage tryEditorPage;
    static HomePageObj homepage;
    static RegisterPageObj registerpage;
    static SignInPageObj signinpage;

    @BeforeClass
    public void setUp() throws IOException {
        try {
			String browser = ConfigReader.getBrowserType();
			driver = DriverFactory.initializeDriver(browser);

			// Initialize page objects
			dataStructurerpage = new DataStructurePageObj(driver);
			tryEditorPage = new TryEditorPage(driver);
			homepage = new HomePageObj(driver);
			registerpage = new RegisterPageObj(driver);
			signinpage =new SignInPageObj(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Navigate and login
        driver.get(ConfigReader.getUrl());
        homepage.clickGetStartedHomePageButton();
       
        homepage.clickSignInLink();
        signinpage.EnterFromExcel("login", 0);
        signinpage.clickloginButton();
        Assert.assertEquals(registerpage.successMsg(), "You are logged in");
    }

    @Test(priority=1)
    public void testValidOutputforTimeComplexity() throws Exception {
    	 homepage.clickDsGetStartedButton();
        dataStructurerpage.ClickTimeComplexityLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel("tryEditorCode", 0);
        tryEditorPage.clickRunButton();
        Assert.assertEquals(tryEditorPage.getOutputText(), "Python is fun!");
    }

    @Test(priority=2)
    public void testInValidOutputforTimeComplexity() throws Exception {
    	 homepage.clickDsGetStartedButton();
        dataStructurerpage.ClickTimeComplexityLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel("tryEditorCode", 1);
        tryEditorPage.clickRunButton();

        String actualAlertText = tryEditorPage.HandleAlert();
        System.out.println("Actual Alert Text: " + actualAlertText);

//        String expectedAlertText = "SyntaxError: bad input on line 1";
//        Assert.assertTrue(actualAlertText.contains(expectedAlertText),
//            "Expected alert to contain: " + expectedAlertText  + actualAlertText);
//        System.out.println("Actual Alert Text: " + actualAlertText);

    }

    @AfterClass
    public void tearDown() {
      if (driver != null) {
          driver.quit();
      }
}
}