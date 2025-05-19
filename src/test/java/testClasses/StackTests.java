package testClasses;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.StackPageObj;
import dsAlgoPageObjects.TryEditorPage;
import utils.ConfigReader;
import utils.DataProviders;

public class StackTests extends BaseClass {
	WebDriver driver;
	SignInPageObj signinpage;
	HomePageObj homepage;
	TryEditorPage tryEditorPage;
	StackPageObj stackPage;
	

	@BeforeMethod
	public void setUp() throws IOException, OpenXML4JException {
		driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());
		signinpage = new SignInPageObj(driver);
		homepage = new HomePageObj(driver);
		tryEditorPage = new TryEditorPage(driver);
		stackPage = new StackPageObj(driver);
		
		
		driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
		homepage.clickSignInLink();
		signinpage.EnterFromExcel("login", 0);
		signinpage.clickloginButton();
		
		driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
	}

	private void openTryEditorInsideOpsStack() {
		homepage.clickStackGetStartedButton();
		stackPage.ClickOperationsinstackLink();
		tryEditorPage.clickTryHereButton();
	}

	private void openTryEditorImplementation() {
		homepage.clickStackGetStartedButton();
		stackPage.ClickStackImplementationLink();
		tryEditorPage.clickTryHereButton();
	}
	private void openTryEditorApplications() {
		homepage.clickStackGetStartedButton();
		stackPage.StackApplicationslink();
		tryEditorPage.clickTryHereButton();
	}
	

	@Test(priority = 1, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeRunInsideOpsStack(String sheetName, int rowNum, String expectedOutput) throws Exception {
		openTryEditorInsideOpsStack();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
	}

	@Test(priority = 1, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeRunInsideOpsStack(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		openTryEditorInsideOpsStack();


		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 3, groups = "smoke")
	public void testPracticeQuestionsOpsStack() {
		homepage.clickStackGetStartedButton();
	    stackPage.ClickOperationsinstackLink();
		stackPage.clickPracticeQuestionsLink();
		Assert.assertTrue(stackPage.getcurrentpageUrl().endsWith("practice"), "Not on practice page!");
	}

	@Test(priority = 1, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeStackRepresentation(String sheetName, int rowNum, String expectedOutput) throws Exception {
		openTryEditorImplementation();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output mismatch in Stack Representation!");
	}

	@Test(priority = 1, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeStackRepresentation(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
		openTryEditorImplementation();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 6, groups = "smoke")
	public void testPracticeQuestionsImplemntation() {
		homepage.clickStackGetStartedButton();
		stackPage.ClickStackImplementationLink();
		stackPage.clickPracticeQuestionsLink();
		Assert.assertTrue(stackPage.getcurrentpageUrl().endsWith("practice"), "Not on practice page!");
	}

	@Test(priority = 1, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeRunInsideApplications(String sheetName, int rowNum, String expectedOutput) throws Exception {
		openTryEditorApplications();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
	}

	@Test(priority = 1, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeRunInsideApplications(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		openTryEditorApplications();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 3, groups = "smoke")
	public void testPracticeQuestionsApplications() {
		homepage.clickStackGetStartedButton();
	    stackPage.StackApplicationslink();
		stackPage.clickPracticeQuestionsLink();
		Assert.assertTrue(stackPage.getcurrentpageUrl().endsWith("practice"), "Not on practice page!");
	}
	
	

	@AfterMethod
	public void tearDown() {
		DriverFactory.closeDriver();
	}
}



