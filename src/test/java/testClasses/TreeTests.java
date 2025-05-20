package testClasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.ArrayPageObj;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.RegisterPageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.TreePageObj;
import dsAlgoPageObjects.TryEditorPage;
import utils.ConfigReader;
import utils.DataProviders;
import utils.LoggerLoad;
@Test(expectedExceptions = NumberFormatException.class)
public class TreeTests extends BaseClass {

	WebDriver driver;

	TryEditorPage tryEditorPage;
	HomePageObj homepage;
	RegisterPageObj registerpage;
	SignInPageObj signinpage;
	ArrayPageObj arraypage;
	TreePageObj treepage;

	@BeforeMethod
	public void setUpOnce() throws IOException {
		driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());

		tryEditorPage = new TryEditorPage(driver);
		homepage = new HomePageObj(driver);
		registerpage = new RegisterPageObj(driver);
		signinpage = new SignInPageObj(driver);
		treepage = new TreePageObj(driver);
		
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


	public void navigateToTreeTopic(String topicMethodName) {
		homepage.clickTreeGetStartedButton(); // Navigate from homepage to Tree module

		switch (topicMethodName) {
		case "OverviewOfTrees":
			treepage.clickOverviewOfTreesLink();
			break;
		case "Terminologies":
			treepage.clickTerminologiesLink();
			break;
		case "TypesOfTrees":
			treepage.clickTypesOfTreesLink();
			break;
		case "TreeTraversals":
			treepage.clickTreeTraversalsLink();
			break;
		case "TraversalsIllustration":
			treepage.clickTraversalsIllustrationsLink();
			break;
		case "BinaryTrees":
			treepage.clickBinaryTreesLink();
			break;
		case "TypesOfBinaryTrees":
			treepage.clickTypesofBinaryTreesLink();
			break;
		case "ImplementationOfBinaryTrees":
			treepage.clickImplementationOfBinaryTreesLink();
			break;
		case "ApplicationOfBinaryTrees":
			treepage.clickApplicationOfBinarytreesLink();
			break;
		case "BinarySearchTrees":
			treepage.clickBinarySearchTreesLink();
			break;
		case "ImplementationOfBST":
			treepage.clickImplementationofBTSLink();
			break;
		case "PracticeQuestions":
			treepage.clickPracticeQuestionsLink();
			break;
		default:

			throw new IllegalArgumentException("Unknown topic method: " + topicMethodName);
		}

		tryEditorPage.clickTryHereButton(); // Common for all topics
	}

	@Test(priority = 1, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void OverviewOfTreesLink(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToTreeTopic("OverviewOfTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority =2, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void OverviewOfTreesLink1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToTreeTopic("OverviewOfTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 3, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void clickTerminologiesLink(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToTreeTopic("Terminologies");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority =4, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void clickTerminologiesLink1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToTreeTopic("Terminologies");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 5, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void clickTypesOfTreesLink(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToTreeTopic("TypesOfTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority = 6, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void clickTypesOfTreesLink1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToTreeTopic("TypesOfTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 7, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void clickTreeTraversalsLink(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToTreeTopic("TreeTraversals");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority = 8, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void clickTreeTraversalsLink1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToTreeTopic("TreeTraversals");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 9, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void clickTraversalsIllustrationsLink(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToTreeTopic("TraversalsIllustration");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority = 10, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void clickTraversalsIllustrationsLink1(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
		navigateToTreeTopic("TraversalsIllustration");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 11, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void clickBinaryTreesLink(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToTreeTopic("BinaryTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority = 12, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void clickBinaryTreesLink1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToTreeTopic("BinaryTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 13, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void clickTypesofBinaryTreesLink(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToTreeTopic("TypesOfBinaryTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority = 14, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void clickTypesofBinaryTreesLink1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToTreeTopic("TypesOfBinaryTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 15, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void clickImplementationOfBinaryTreesLink(String sheetName, int rowNum, String expectedOutput)
			throws Exception {
		navigateToTreeTopic("ImplementationOfBinaryTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority = 16, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void clickImplementationOfBinaryTreesLink1(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
		navigateToTreeTopic("ImplementationOfBinaryTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 17, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void clickApplicationOfBinaryTreesLink(String sheetName, int rowNum, String expectedOutput)
			throws Exception {
		navigateToTreeTopic("ApplicationOfBinaryTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority = 18, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void clickApplicationOfBinaryTreesLink1(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
		navigateToTreeTopic("ApplicationOfBinaryTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 19, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void clickBinarySearchTreesLink(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToTreeTopic("BinarySearchTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority = 20, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void clickBinarySearchTreesLink1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToTreeTopic("BinarySearchTrees");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}

	@Test(priority = 21, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void clickImplementationofBTSLink(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToTreeTopic("ImplementationOfBST");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	 @Test(priority = 22, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void clickImplementationofBTSLink1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToTreeTopic("ImplementationOfBST");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String alertMessage = tryEditorPage.getAlertText();
		Assert.assertNotNull(alertMessage, "Expected alert was not present!");
		Assert.assertTrue(alertMessage.contains(expectedAlertPart));
	}



	@AfterMethod
	public void tearDown() {
		DriverFactory.closeDriver(); // This calls quit() and also removes the ThreadLocal

	}

}
