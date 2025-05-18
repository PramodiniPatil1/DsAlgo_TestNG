package testClasses;

import java.io.IOException;

<<<<<<< HEAD
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
=======
>>>>>>> 748eaf470a3d23fb1e513c3e998fb877b744e54c
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import driverManager.DriverFactory;
<<<<<<< HEAD
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

<<<<<<< HEAD
    @Test(priority=1)
    public void testUserSignIn() throws Exception {
        homepage.openHomeUrl();
        homepage.clickgetStartedButton(driver);
        homepage.clickSignInLink();
        signin.enterUsernameText("username");
        signin.enterPasswordText("password");
        signin.clickloginButton();
    }

    @Test(priority=2)
    public void testNavigateToGraphPage() {
        homepage.clickGraphGetStartedButton();
        Assert.assertEquals(homepage.getHomePageTitle(), "Graph");
    }

    @Test(priority=3)
    public void testInsideGraphPageNavigation() {
        graphpage.ClickInsideGraphkLink();
        Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("graph/"));
    }

    @Test(priority=4)
    public void testTryHereButtonInsideGraph() {
        graphpage.ClickInsideGraphkLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("tryEditor"));
    }
=======
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
>>>>>>> 9bf215d64a7f8ff003420b728c9e4a5e5bc1c531

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

<<<<<<< HEAD
    @Test(priority= 6)
    public void testValidCodeRunInsideGraph() {
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

    @Test(priority= 7)
    public void testInvalidCodeRunInsideGraph() {
        graphpage.ClickInsideGraphkLink();
        tryEditorPage.clickTryHereButton();
        try {
            tryEditorPage.enterCodeFromExcel("tryEditorCode", 1);
            tryEditorPage.clickRunButton();
        } catch (Exception e) {
            LoggerLoad.info("Error while entering code or clicking Run: " + e.getMessage());
        }
        String isAlertPresent = tryEditorPage.HandleAlert();
        assertTrue(isAlertPresent, "No alert displayed");
    }
=======
import dsAlgoPageObjects.*;
import utils.ConfigReader;
import utils.DataProviders;

public class GraphTests {

	WebDriver driver;
	SignInPageObj signinpage;
	HomePageObj homepage;
	TryEditorPage tryEditorPage;
	GraphPageObj graphpage;
	RegisterPageObj registerpage;

	@BeforeMethod
	public void setUp() throws IOException, OpenXML4JException {
		driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());
		signinpage = new SignInPageObj(driver);
		homepage = new HomePageObj(driver);
		tryEditorPage = new TryEditorPage(driver);
		graphpage = new GraphPageObj(driver);
		registerpage = new RegisterPageObj(driver);

		driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
		homepage.clickSignInLink();
		signinpage.EnterFromExcel("login", 0);
		signinpage.clickloginButton();

		Assert.assertEquals(registerpage.successMsg(), "You are logged in", "Login failed!");

		driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
	}

	private void openTryEditorInsideGraph() {
		homepage.clickGraphGetStartedButton();
		graphpage.ClickInsideGraphkLink();
		tryEditorPage.clickTryHereButton();
	}

	private void openTryEditorGraphRepresentation() {
		homepage.clickGraphGetStartedButton();
		graphpage.ClickGraphRepresentationLink();
		tryEditorPage.clickTryHereButton();
	}

	@Test(priority = 1, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeRunInsideGraph(String sheetName, int rowNum, String expectedOutput) throws Exception {
		openTryEditorInsideGraph();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
	}

	@Test(priority = 1, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeRunInsideGraph(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		openTryEditorInsideGraph();
>>>>>>> 748eaf470a3d23fb1e513c3e998fb877b744e54c

		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 3, groups = "smoke")
	public void testPracticeQuestionsLinkInsideGraph() {
		homepage.clickGraphGetStartedButton();
		graphpage.ClickInsideGraphkLink();
		graphpage.clickPracticeQuestionsLink();
		Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("practice"), "Not on practice page!");
	}

	@Test(priority = 1, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeGraphRepresentation(String sheetName, int rowNum, String expectedOutput) throws Exception {
		openTryEditorGraphRepresentation();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output mismatch in Graph Representation!");
	}

	@Test(priority = 1, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeGraphRepresentation(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
		openTryEditorGraphRepresentation();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 6, groups = "smoke")
	public void testPracticeQuestionsGraphRepresentation() {
		homepage.clickGraphGetStartedButton();
		graphpage.ClickGraphRepresentationLink();
		graphpage.clickPracticeQuestionsLink();
		Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("practice"), "Not on practice page!");
	}

<<<<<<< HEAD
    @Test(priority = 13)
    public void testInvalidCodeGraphRepresentation() {
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

    @Test(priority = 14)
    public void testPracticeQuestionsGraphRepresentation() {
        graphpage.ClickGraphRepresentationLink();
        graphpage.clickPracticeQuestionsLink();
        Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("practice"));
    }
=======
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
>>>>>>> 9bf215d64a7f8ff003420b728c9e4a5e5bc1c531
}
=======
	@AfterMethod
	public void tearDown() {
		DriverFactory.closeDriver();
	}
}
>>>>>>> 748eaf470a3d23fb1e513c3e998fb877b744e54c
