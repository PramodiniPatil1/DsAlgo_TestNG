
package testClasses;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.QueuePageObj;
import dsAlgoPageObjects.RegisterPageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.TryEditorPage;
import utils.ConfigReader;
import utils.DataProviders;
import utils.LoggerLoad;


public class QueueTests extends BaseClass {

	WebDriver driver;
	SignInPageObj signinpage;
	HomePageObj homepage;
	TryEditorPage tryEditorPage;
	QueuePageObj queuepage;
	RegisterPageObj registerpage;

	@Test(expectedExceptions = NumberFormatException.class)

	@BeforeMethod
	public void setUp() throws IOException {
		driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());

		try {
			String browser = ConfigReader.getBrowserType();
			driver = DriverFactory.initializeDriver(browser);
			signinpage = new SignInPageObj(driver);
			homepage = new HomePageObj(driver);
			tryEditorPage = new TryEditorPage(driver);
			queuepage = new QueuePageObj(driver);
			registerpage = new RegisterPageObj(driver);

			// Make sure driver is passed here
		} catch (Exception e) {
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

	@Test(priority = 1, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeRunImplementationofQueuePythonPage(String sheetName, int rowNum,
			String expectedAlertPart) throws Exception {
		homepage.clickQueueGetStartedButton();
		queuepage.clickImplementaionOfQueueLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		driver.switchTo().alert().accept();
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 2, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeRunImplementationofQueuePythonPage(String sheetName, int rowNum, String expectedOutput)
			throws Exception {
		homepage.clickQueueGetStartedButton();
		queuepage.clickImplementaionOfQueueLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");

	}

	@Test(priority = 3, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeRunImplementationusingCollectionsPage(String sheetName, int rowNum,
			String expectedAlertPart) throws Exception {
		homepage.clickQueueGetStartedButton();
		queuepage.clickUsingCollectionsLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();

		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 4, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeRunImplementationusingCollectionsPage(String sheetName, int rowNum, String expectedOutput)
			throws Exception {
		homepage.clickQueueGetStartedButton();
		queuepage.clickUsingCollectionsLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");

	}

	@Test(priority = 5, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeRunImplementationusingArrayPage(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
		homepage.clickQueueGetStartedButton();
		queuepage.clickImplementatonUsingArraylink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();

		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 6, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeRunImplementationusingArrayPage(String sheetName, int rowNum, String expectedOutput)
			throws Exception {
		homepage.clickQueueGetStartedButton();
		queuepage.clickImplementatonUsingArraylink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
	}

	@Test(priority = 7, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeRunQueueOperationsPage(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
		homepage.clickQueueGetStartedButton();
		queuepage.clickQueueOperationslink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();

		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	@Test(priority = 8, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void testValidCodeRunQueueOperationsPage(String sheetName, int rowNum, String expectedOutput)
			throws Exception {
		homepage.clickQueueGetStartedButton();
		queuepage.clickQueueOperationslink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");

	}

	@Test(priority = 9)
	public void testPracticeQuestionsLinkQueueOperationsPage() {
		homepage.clickQueueGetStartedButton();
		queuepage.clickQueueOperationslink();
		queuepage.clickPracticeQuestionsLink();
		Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("practice"));
	}

	@AfterMethod
	public void tearDown() {
		DriverFactory.closeDriver();
	}
}
