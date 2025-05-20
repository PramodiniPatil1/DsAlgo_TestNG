package testClasses;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import driverManager.DriverFactory;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.LinkedListPageObj;
import dsAlgoPageObjects.RegisterPageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.TryEditorPage;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.exc.InvalidFormatException;
import utils.ConfigReader;
import utils.DataProviders;
import utils.LoggerLoad;


public class LinkListTests {
	WebDriver driver;
	SignInPageObj signinpage;
	HomePageObj homepage;
	TryEditorPage tryEditorPage;
	LinkedListPageObj linklistpage;
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
			linklistpage = new LinkedListPageObj(driver);
			registerpage = new RegisterPageObj(driver);

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
		String actualMsg = registerpage.successMsg();
		LoggerLoad.info("Success Message: " + actualMsg);
		Assert.assertEquals(actualMsg, "You are logged in", "Login success message mismatch!");

		driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
	}
//Introduction link

	@Test(priority = 1, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeRunIntroductionPage(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickIntroductionLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();

		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 2, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeRunIntroductionPage(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickIntroductionLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");

	}

	@Test(priority = 3, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeCreatingLL(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickcreatingLinkedListLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);

	}

	@Test(priority = 4, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeCreatingLL(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickcreatingLinkedListLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");

	}

	@Test(priority = 5, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeTypesofLL(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickTypesOfLinkedListLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 6, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeTypesofLL(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickTypesOfLinkedListLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");

	}

	@Test(priority = 7)
	public void testPracticeQuestionsLinkTypesofLL() {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickTypesOfLinkedListLink();
		linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}

	@Test(priority = 8, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeImplementLL(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickImplementLLinPythonLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 25, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeImplementLL(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickImplementLLinPythonLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");

	}

	@Test(priority = 9)
	public void testPracticeQuestionsLinkImplementLL() {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickImplementLLinPythonLink();
		linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}

	@Test(priority = 10, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeTraversal(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickTraversalLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 11, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeTraversal(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickTraversalLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");

	}

	@Test(priority = 12)
	public void testPracticeQuestionsLinkTraversal() {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickTraversalLink();
		linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));

	}

	@Test(priority = 13, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeInsertion(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickInsertionLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 14, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeInsertion(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickInsertionLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");

	}

	@Test(priority = 15)
	public void testPracticeQuestionsLinkInsertion() {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickInsertionLink();
		linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}

	@Test(priority = 16, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeDeletion(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickDeletionLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 17, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeDeletion(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickDeletionLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");

	}

	@Test(priority = 18)
	public void testPracticeQuestionsLinkDeletion() {
		homepage.clickLinkedListGetStartedButton();
		linklistpage.clickDeletionLink();
		linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.closeDriver();
	}

}
