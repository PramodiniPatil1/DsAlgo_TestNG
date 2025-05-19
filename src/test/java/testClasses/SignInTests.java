package testClasses;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import driverManager.DriverFactory;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.RegisterPageObj;
import dsAlgoPageObjects.SignInPageObj;

import dsAlgoPageObjects.TryEditorPage;
import utils.ConfigReader;

public class SignInTests {
	WebDriver driver;
	SignInPageObj signinpage;
	HomePageObj homepage;
	TryEditorPage tryEditorPage;
	RegisterPageObj registerpage;
	@BeforeMethod
	public void setUp() throws IOException, OpenXML4JException {
		driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());
		signinpage = new SignInPageObj(driver);
		homepage = new HomePageObj(driver);
		tryEditorPage = new TryEditorPage(driver);
		registerpage= new RegisterPageObj(driver);
		
		
		driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
		homepage.clickSignInLink();

	}
	 @DataProvider(name = "validLogin")
	    public Object[][] validData() {
	        return new Object[][] {
	            {"Login", 0}
	        };
	    }

	    @DataProvider(name = "InvalidLogin")
	    public Object[][] mismatchData() {
	        return new Object[][] {
	            {"Login", 2}
	        };
	    }

	    @DataProvider(name = "emptydata")
	    public Object[][] emptyData() {
	        return new Object[][] {
	            {"Login", 1}
	        };
	    }
	    @Test(priority = 1, dataProvider = "validLogin")
		public void testValidLogin(String sheetName, int row) throws Exception {
			signinpage.EnterFromExcel(sheetName, row);
			signinpage.clickloginButton();

			Assert.assertTrue(signinpage.homePagemsg(), "Home page welcome message not displayed after login");
			System.out.println("Success message: " + signinpage.homePagemsg());
		}

		@Test(priority = 2, dataProvider = "InvalidLogin")
		public void testInvalidLogin(String sheetName, int row) throws Exception {
			signinpage.EnterFromExcel(sheetName, row);
			signinpage.clickloginButton();
			signinpage.TakeScreenshot();
		}

		@Test(priority = 3, dataProvider = "emptydata")
		public void testEmptyData(String sheetName, int row) throws Exception {
			signinpage.EnterFromExcel(sheetName, row);
			signinpage.clickloginButton();
			signinpage.TakeScreenshot();
		}
	
@AfterMethod
public void tearDown() {
	DriverFactory.closeDriver();
}
}