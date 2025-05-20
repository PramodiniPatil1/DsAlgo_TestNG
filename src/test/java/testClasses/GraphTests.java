package testClasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import baseClass.BaseClass;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import driverManager.DriverFactory;
import dsAlgoPageObjects.*;
import utils.ConfigReader;
import utils.DataProviders;

public class GraphTests extends BaseClass {

	WebDriver driver;
	SignInPageObj signinpage;
	HomePageObj homepage;
	TryEditorPage tryEditorPage;
	GraphPageObj graphpage;
	RegisterPageObj registerpage;


	@BeforeMethod
	public void setUp() throws IOException  {
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

		Assert.assertEquals(registerpage.successMsg(), "You are logged in");

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
		Assert.assertTrue(actualOutput.contains(expectedOutput));
	}

	@Test(priority = 2, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeRunInsideGraph(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		openTryEditorInsideGraph();

		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}
	@Test(priority = 3, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeGraphRepresentation(String sheetName, int rowNum, String expectedOutput) throws Exception {
		openTryEditorGraphRepresentation();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput));
	}

	@Test(priority = 4, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeGraphRepresentation(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
		openTryEditorGraphRepresentation();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 6, groups = "smoke")
	public void testPracticeQuestionsGraphRepresentation() {
		homepage.clickGraphGetStartedButton();
		graphpage.ClickGraphRepresentationLink();
		graphpage.clickPracticeQuestionsLink();
		Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("practice"));
	}
	@Test(priority = 7)
	public void testPracticeQuestionsLinkInsideGraph() {
		homepage.clickGraphGetStartedButton();
		graphpage.ClickInsideGraphkLink();
		graphpage.clickPracticeQuestionsLink();
		Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("practice"));
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.closeDriver();
	}
}
