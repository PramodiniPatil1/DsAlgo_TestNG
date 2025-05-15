package testClasses;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import driverManager.DriverFactory;
import dsAlgoPageObjects.GraphPageObj;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.RegisterPageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.TryEditorPage;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.exc.InvalidFormatException;
import utils.ConfigReader;
import utils.LoggerLoad;

public class GraphTests {
	WebDriver driver;
	SignInPageObj signinpage;
	HomePageObj homepage;
	TryEditorPage tryEditorPage;
	GraphPageObj graphpage;
	RegisterPageObj registerpage;

	@BeforeMethod
	public void setUp() throws InvalidFormatException, IOException, OpenXML4JException {
		 driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());
		
			try {
				String browser = ConfigReader.getBrowserType();
				driver = DriverFactory.initializeDriver(browser);
				signinpage = new SignInPageObj(driver);
				homepage = new HomePageObj(driver);
				tryEditorPage = new TryEditorPage(driver);
				graphpage = new GraphPageObj(driver); 
				registerpage=new RegisterPageObj(driver);
				
				// Make sure driver is passed here
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
	
	@Test(priority = 1)
	public void testValidCodeRunInsideGraph() {
		homepage.clickGraphGetStartedButton();
		graphpage.ClickInsideGraphkLink();
		tryEditorPage.clickTryHereButton();
		try {
			tryEditorPage.enterCodeFromExcel("tryEditorCode", 0);
			tryEditorPage.clickRunButton();
		} catch (Exception e) {
			LoggerLoad.info("Error while entering code or clicking Run: " + e.getMessage());
		}
		graphpage.codeEditorOutput();
	}

	@Test(priority = 2)
	public void testInvalidCodeRunInsideGraph() {
		homepage.clickGraphGetStartedButton();
		graphpage.ClickInsideGraphkLink();
		tryEditorPage.clickTryHereButton();
		try {
			tryEditorPage.enterCodeFromExcel("tryEditorCode", 1);
			tryEditorPage.clickRunButton();
		} catch (Exception e) {
			LoggerLoad.info("Error while entering code or clicking Run: " + e.getMessage());
		}
		String isAlertPresent = tryEditorPage.HandleAlert();
		;
	}

	@Test(priority = 3)
	public void testPracticeQuestionsLinkInsideGraph() {
		graphpage.ClickInsideGraphkLink();
		graphpage.clickPracticeQuestionsLink();
		Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("practice"));
	}

	

	@Test(priority = 4)
	public void testValidCodeGraphRepresentation() {
		homepage.clickGraphGetStartedButton();
		graphpage.ClickGraphRepresentationLink();
		tryEditorPage.clickTryHereButton();
		try {
			tryEditorPage.enterCodeFromExcel("tryEditorCode", 0);
			tryEditorPage.clickRunButton();
		} catch (Exception e) {
			LoggerLoad.info("Error while entering code or clicking Run: " + e.getMessage());
		}
		graphpage.codeEditorOutput();
	}

	@Test(priority = 5)
	public void testInvalidCodeGraphRepresentation() {
		homepage.clickGraphGetStartedButton();
		graphpage.ClickGraphRepresentationLink();
		tryEditorPage.clickTryHereButton();
		try {
			tryEditorPage.enterCodeFromExcel("tryEditorCode", 1);
			tryEditorPage.clickRunButton();
		} catch (Exception e) {
			LoggerLoad.info("Error while entering code or clicking Run: " + e.getMessage());
		}
		String isAlertPresent = tryEditorPage.HandleAlert();

	}

	@Test(priority = 6)
	public void testPracticeQuestionsGraphRepresentation() {
		graphpage.ClickGraphRepresentationLink();
		graphpage.clickPracticeQuestionsLink();
		Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("practice"));
	}
	
	@AfterMethod
	public void tearDown() {
	    if (DriverFactory.getDriver() != null) {
	        DriverFactory.getDriver().quit();
	        DriverFactory.closeDriver(); // a new static method that calls threadLocal.remove()
	    }
	}
}