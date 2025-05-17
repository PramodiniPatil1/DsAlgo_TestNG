package testClasses;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.ArrayPageObj;
import dsAlgoPageObjects.DataStructurePageObj;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.RegisterPageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.TryEditorPage;
import utils.ConfigReader;

public class ArrayTests extends BaseClass {

	WebDriver driver;
	DataStructurePageObj dataStructurerpage;
	TryEditorPage tryEditorPage;
	HomePageObj homepage;
	RegisterPageObj registerpage;
	SignInPageObj signinpage;
	ArrayPageObj arraypage;

	@BeforeClass
	public void setUpOnce() throws IOException {
		driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());
		dataStructurerpage = new DataStructurePageObj(driver);
		tryEditorPage = new TryEditorPage(driver);
		homepage = new HomePageObj(driver);
		registerpage = new RegisterPageObj(driver);
		signinpage = new SignInPageObj(driver);
		arraypage = new ArrayPageObj(driver);
		driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
		homepage.clickSignInLink();
		signinpage.EnterFromExcel("login", 0);
		signinpage.clickloginButton();
		Assert.assertEquals(registerpage.successMsg(), "You are logged in",
				"Login failed or success message mismatch.");
	}

	@BeforeMethod
	public void resetToHomeBeforeTest() {
		driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
	}

	@DataProvider(name = "Pythoncode")
	public Object[][] CodeData() throws IOException, OpenXML4JException {
		return new Object[][] { { "tryEditorCode", 0, "Python is fun!" },
				{ "tryEditorCode", 1, "SyntaxError: bad input on line 1" }, };
	}

	@DataProvider(name = "Pythoncode1")
	public Object[][] CodeData1() throws IOException, OpenXML4JException {
		return new Object[][] { { "ArrayPracticeQnsQ1", 0, "Element Found" },
				{ "ArrayPracticeQnsQ1", 1, " SyntaxError: bad input on line 1" }, };
	}

	@DataProvider(name = "MaxConsecutiveOnesData")
	public Object[][] maxConsecutiveOnesData() throws IOException {
		return new Object[][] { { "pythonCode", 2, "2" }, // Valid
				{ "pythonCode", 3, "SyntaxError: bad input on line 1" } // Invalid
		};
	}

	@DataProvider(name = "EvenDigitsData")
	public Object[][] evenDigitsData() throws IOException {
		return new Object[][] { { "pythonCode", 0, "('Count of numbers with even number of digits:', 2)" }, // Valid
				{ "pythonCode", 1, "SyntaxError: bad input on line 1" } // Invalid
		};
	}

	@Test(priority = 1, dataProvider = "Pythoncode")
	public void ArraysinPython(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickArrayGetStartedButton();
		arraypage.clickarraysInPythonLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 2, dataProvider = "Pythoncode")
	public void ArraysinPython1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickArrayGetStartedButton();
		arraypage.clickarraysInPythonLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualAlertText = tryEditorPage.HandleAlert();
		Assert.assertTrue(actualAlertText.contains(expectedAlertPart));
	}

	@Test(priority = 3, dataProvider = "Pythoncode")
	public void ArraysUsingList(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickArrayGetStartedButton();
		arraypage.clickarraysUsingListLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 4, dataProvider = "Pythoncode")
	public void ArraysUsingList1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickArrayGetStartedButton();
		arraypage.clickarraysUsingListLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualAlertText = tryEditorPage.HandleAlert();
		Assert.assertTrue(actualAlertText.contains(expectedAlertPart));
	}

	@Test(priority = 5, dataProvider = "Pythoncode")
	public void BasicOperationsinLists(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickArrayGetStartedButton();
		arraypage.clickbasicOperationsInListsLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 6, dataProvider = "Pythoncode")
	public void BasicOperationsinLists1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickArrayGetStartedButton();
		arraypage.clickbasicOperationsInListsLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualAlertText = tryEditorPage.HandleAlert();
		Assert.assertTrue(actualAlertText.contains(expectedAlertPart));
	}

	@Test(priority = 7, dataProvider = "Pythoncode")
	public void ApplicationsofArray(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickArrayGetStartedButton();
		arraypage.clickapplicationsOfArrayLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 8, dataProvider = "Pythoncode")
	public void ApplicationsofArray1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickArrayGetStartedButton();
		arraypage.clickapplicationsOfArrayLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualAlertText = tryEditorPage.HandleAlert();
		Assert.assertTrue(actualAlertText.contains(expectedAlertPart));
	}

	public void navigateToPracticeQuestion(String questionLinkMethod) {
		homepage.clickArrayGetStartedButton();
		arraypage.clickarraysInPythonLink();;
		arraypage.clickPracticeQuestionsLink();
		switch (questionLinkMethod) {
		case "SearchTheArray":
			arraypage.clickSearchTheArrayLink();
			break;
		case "MaxConsecutiveOnes":
			arraypage.clickMaxConsecutiveOnes();
			break;
		case "EvenNumberOfDigits":
			arraypage.clickFindNumWithEvenNumOfDigits();
			break;
		case "SquaresofaSorted":
			arraypage.clickSquaresOfASortedArray();
			break;
		default:
			throw new IllegalArgumentException("Unknown question link method: " + questionLinkMethod);
		}
	}

	@Test(priority = 9, dataProvider = "Pythoncode1")
	public void SearchTheArray(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToPracticeQuestion("SearchTheArray");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 10, dataProvider = "Pythoncode1")
	public void SearchTheArray1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToPracticeQuestion("SearchTheArray");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualAlertText = tryEditorPage.HandleAlert();
		Assert.assertTrue(actualAlertText.contains(expectedAlertPart));
	}

	@Test(priority = 11, dataProvider = "MaxConsecutiveOnesData")
	public void MaxConsecutiveOnes(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToPracticeQuestion("MaxConsecutiveOnes");
		tryEditorPage.enterCodeFromExcel(sheetName,rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 12, dataProvider = "MaxConsecutiveOnesData")
	public void MaxConsecutiveOnes1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToPracticeQuestion("MaxConsecutiveOnes");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualAlertText = tryEditorPage.HandleAlert();
		Assert.assertTrue(actualAlertText.contains(expectedAlertPart));
	}

	@Test(priority = 13, dataProvider = "EvenDigitsData")
	public void EvenNumberOfDigits(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToPracticeQuestion("EvenNumberOfDigits");
		tryEditorPage.enterCodeFromExcel(sheetName,rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 14, dataProvider = "EvenDigitsData")
	public void EvenNumberOfDigits1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToPracticeQuestion("EvenNumberOfDigits");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualAlertText = tryEditorPage.HandleAlert();
		Assert.assertTrue(actualAlertText.contains(expectedAlertPart));
	}

	@Test(priority = 15, dataProvider = "Pythoncode")
	public void SquaresofaSorted(String sheetName, int rowNum, String expectedOutput) throws Exception {
		navigateToPracticeQuestion("SquaresofaSorted");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 16, dataProvider = "Pythoncode")
	public void SquaresofaSorted1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		navigateToPracticeQuestion("SquaresofaSorted");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		String actualAlertText = tryEditorPage.HandleAlert();
		Assert.assertTrue(actualAlertText.contains(expectedAlertPart));
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
