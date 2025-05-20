package testClasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.ArrayPageObj;
import dsAlgoPageObjects.DataStructurePageObj;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.RegisterPageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.TryEditorPage;
import utils.ConfigReader;
import utils.DataProviders;
import utils.LoggerLoad;

public class ArrayTests extends BaseClass {

	WebDriver driver;
	DataStructurePageObj dataStructurerpage;
	TryEditorPage tryEditorPage;
	HomePageObj homepage;
	RegisterPageObj registerpage;
	SignInPageObj signinpage;
	ArrayPageObj arraypage;

	@BeforeMethod
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
		String actualMsg = registerpage.successMsg();
		LoggerLoad.info("Success Message: " + actualMsg);
		Assert.assertEquals(actualMsg, "You are logged in", "Login success message mismatch!");

		driver.get(ConfigReader.getUrl());
		homepage.clickGetStartedHomePageButton();
	}

	@DataProvider(name = "Pythoncode1")
	public Object[][] validCodePracticeQns() throws IOException, OpenXML4JException {
		return new Object[][] { { "ArrayPracticeQnsQ1", 0, "Element Found" }, };// valid for array

	}

	@DataProvider(name = "Pythoncode2")
	public Object[][] invalidCodePracticeQns() throws IOException, OpenXML4JException {
		return new Object[][] { { "ArrayPracticeQnsQ1", 1, "SyntaxError: bad input on line 2" }, };// invalid
	}

	@DataProvider(name = "MaxConsecutiveOnesData")
	public Object[][] maxConsecutiveOnesData() throws IOException {
		return new Object[][] { { "pythonCode1", 2, "2" }, // Valid

		};
	}

	@DataProvider(name = "MaxConsecutiveOnesData1")
	public Object[][] maxConsecutiveOnesData1() throws IOException {
		return new Object[][] { { "pythonCode1", 3, "SyntaxError: bad input on line 1" } // Invalid
		};
	}

	@DataProvider(name = "EvenNumberOfDigits")
	public Object[][] evenDigitsData() throws IOException {
		return new Object[][] { { "pythonCode1", 0, "('Count of numbers with even number of digits:', 2)" }, // Valid

		};
	}

	@DataProvider(name = "EvenNumberOfDigits1")
	public Object[][] evenDigitsData1() throws IOException {
		return new Object[][] { // Valid
				{ "pythonCode1", 1, "SyntaxError: bad input on line 1" } // Invalid
		};
	}

	@Test(priority = 1, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)

	public void ArraysinPython(String sheetName, int rowNum, String expectedOutput)  throws IOException, OpenXML4JException {
		homepage.clickArrayGetStartedButton();
		arraypage.clickarraysInPythonLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 2, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)

	public void ArraysinPython1(String sheetName, int rowNum, String expectedAlertPart) throws Exception {
		homepage.clickArrayGetStartedButton();
		arraypage.clickarraysInPythonLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
	
		 tryEditorPage.clickRunButton();
 		 String alertMessage = tryEditorPage.getAlertText();
 		
 		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
 				"Alert message did not contain expected text. Actual: " + alertMessage);
 	}


	@Test(priority = 3, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)

	public void ArraysUsingList(String sheetName, int rowNum, String expectedOutput)  throws IOException, OpenXML4JException {
		homepage.clickArrayGetStartedButton();
		arraypage.clickarraysUsingListLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 4, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)

	public void ArraysUsingList1(String sheetName, int rowNum, String expectedAlertPart) throws IOException, OpenXML4JException {
		homepage.clickArrayGetStartedButton();
		arraypage.clickarraysUsingListLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		 tryEditorPage.clickRunButton();
 		 String alertMessage = tryEditorPage.getAlertText();
 		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
 				"Alert message did not contain expected text. Actual: " + alertMessage);
 	}


	@Test(priority = 5, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)

	public void BasicOperationsinLists(String sheetName, int rowNum, String expectedOutput)  throws IOException, OpenXML4JException {
		homepage.clickArrayGetStartedButton();
		arraypage.clickbasicOperationsInListsLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 6, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)

	public void BasicOperationsinLists1(String sheetName, int rowNum, String expectedAlertPart) throws IOException, OpenXML4JException {
		homepage.clickArrayGetStartedButton();
		arraypage.clickbasicOperationsInListsLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
 		 String alertMessage = tryEditorPage.getAlertText();
 		
 		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
 				"Alert message did not contain expected text. Actual: " + alertMessage);
 	}


	@Test(priority = 7, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)

	public void ApplicationsofArray(String sheetName, int rowNum, String expectedOutput) throws Exception {
		homepage.clickArrayGetStartedButton();
		arraypage.clickapplicationsOfArrayLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 8, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)

	public void ApplicationsofArray1(String sheetName, int rowNum, String expectedAlertPart)  throws IOException, OpenXML4JException {
		homepage.clickArrayGetStartedButton();
		arraypage.clickapplicationsOfArrayLink();
		tryEditorPage.clickTryHereButton();
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		 tryEditorPage.clickRunButton();
 		 String alertMessage = tryEditorPage.getAlertText();
 		
 		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
 				"Alert message did not contain expected text. Actual: " + alertMessage);
 	}


	public void navigateToPracticeQuestion(String questionLinkMethod) {
		homepage.clickArrayGetStartedButton();
		arraypage.clickarraysInPythonLink();
		;
		arraypage.clickPracticeQuestionsLink();
		switch (questionLinkMethod) {
		case "SearchTheArray":
			arraypage.clickSearchTheArrayLink();
			break;
		case "MaxConsecutiveOnes":
			arraypage.clickMaxConsecutiveOnes();
			break;
		case "EvenNumberOfDigits":
			arraypage.clickFindNumWithEvenNumOfDigits();;
			break;
		case "SquaresofaSorted":
			arraypage.clickSquaresOfASortedArray();
			break;
		default:
			throw new IllegalArgumentException("Unknown question link method: " + questionLinkMethod);
		}
	}

	@Test(priority = 9, groups = "positive", dataProvider = "Pythoncode1")
	public void SearchTheArray(String sheetName, int rowNum, String expectedOutput) throws IOException, OpenXML4JException {
		navigateToPracticeQuestion("SearchTheArray");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 10, groups = "negative", dataProvider = "Pythoncode2")
	public void SearchTheArray1(String sheetName, int rowNum, String expectedAlertPart)  throws IOException, OpenXML4JException {
		navigateToPracticeQuestion("SearchTheArray");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
 		 String alertMessage = tryEditorPage.getAlertText();
 		
 		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
 				"Alert message did not contain expected text. Actual: " + alertMessage);
 	}


	@Test(priority = 11, groups = "positive", dataProvider = "MaxConsecutiveOnesData")
	public void MaxConsecutiveOnes(String sheetName, int rowNum, String expectedOutput) throws IOException, OpenXML4JException {
		navigateToPracticeQuestion("MaxConsecutiveOnes");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 12, groups = "negative", dataProvider = "MaxConsecutiveOnesData1")
	public void MaxConsecutiveOnes1(String sheetName, int rowNum, String expectedAlertPart)  throws IOException, OpenXML4JException {
		navigateToPracticeQuestion("MaxConsecutiveOnes");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		 tryEditorPage.clickRunButton();
 		 String alertMessage = tryEditorPage.getAlertText();
 		
 		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
 				"Alert message did not contain expected text. Actual: " + alertMessage);
 	}


	@Test(priority = 13, groups = "positive", dataProvider = "EvenNumberOfDigits")
	public void EvenNumberOfDigits(String sheetName, int rowNum, String expectedOutput)  throws IOException, OpenXML4JException {
		navigateToPracticeQuestion("EvenNumberOfDigits");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 14, groups = "negative", dataProvider = "EvenNumberOfDigits1")
	public void EvenNumberOfDigits1(String sheetName, int rowNum, String expectedAlertPart)  throws IOException, OpenXML4JException {
		navigateToPracticeQuestion("EvenNumberOfDigits");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		 tryEditorPage.clickRunButton();
 		 String alertMessage = tryEditorPage.getAlertText();
 		
 		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
 				"Alert message did not contain expected text. Actual: " + alertMessage);
 	}


	@Test(priority = 15, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
	public void SquaresofaSorted(String sheetName, int rowNum, String expectedOutput)  throws IOException, OpenXML4JException {
		navigateToPracticeQuestion("SquaresofaSorted");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput);
	}

	@Test(priority = 16, dataProvider = "InValidPythonCode", dataProviderClass = DataProviders.class)
	public void SquaresofaSorted1(String sheetName, int rowNum, String expectedAlertPart) throws IOException, OpenXML4JException {
		navigateToPracticeQuestion("SquaresofaSorted");
		tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		tryEditorPage.clickRunButton();
		 tryEditorPage.clickRunButton();
 		 String alertMessage = tryEditorPage.getAlertText();
 		 Assert.assertTrue(alertMessage.contains(expectedAlertPart),
 				"Alert message did not contain expected text. Actual: " + alertMessage);
 	}



	@AfterMethod
	public void tearDown() {
		DriverFactory.closeDriver(); // This calls quit() and also removes the ThreadLocal

	}
}
