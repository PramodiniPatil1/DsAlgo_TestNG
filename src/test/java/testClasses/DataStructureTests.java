package testClasses;

import java.io.IOException;
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
    TestInstance testInstance;
    
    public DataStructureTests(TestInstance testInstance) {
    	this.testInstance = testInstance;
		
	}
    @BeforeMethod
    public void setUpDataStructure() throws IOException {
    	driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());          
    	
    	
   /* 	dataStructurerpage= new DataStructurePageObj(driver);
    	tryEditorPage=new TryEditorPage(driver);
    	homepage= new HomePageObj(driver);
    	registerpage= new RegisterPageObj(driver);
    	signinpage=new SignInPageObj(driver);   */
    	       
        driver.get(ConfigReader.getUrl());
        homepage.clickGetStartedHomePageButton();
        homepage.clickSignInLink();
        signinpage.EnterFromExcel("login", 0);
        signinpage.clickloginButton();

        Assert.assertEquals(registerpage.successMsg(), "You are logged in", "Login failed or success message mismatch.");
   
        driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
        
    }


    @Test(priority = 1, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
    
  
    public void testValidOutputforTimeComplexity1(String sheetName, int rowNum, String expectedOutput) throws Exception {
        homepage.clickDsGetStartedButton();
        dataStructurerpage.ClickTimeComplexityLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();

        Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
    }

    @Test(priority = 1, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
    public void testInValidOutputforTimeComplexity(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
        homepage.clickDsGetStartedButton();
        dataStructurerpage.ClickTimeComplexityLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();

        String alertMessage = tryEditorPage.getAlertText(); // Make sure this returns the actual alert text
        Assert.assertNotNull(alertMessage, "Expected alert was not present!");
        Assert.assertTrue(alertMessage.contains(expectedAlertPart),
            "Alert message did not contain expected text. Actual: " + alertMessage);

    }
    @AfterMethod
    public void tearDown() {
        DriverFactory.closeDriver();  // This calls quit() and also removes the ThreadLocal
    
    }
}