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
	
			driver.get(ConfigReader.getUrl());
			homepage.clickGetStartedHomePageButton();
	
	}
	

    @Test(priority=1)
    public void testUserSignIn() throws Exception {
        homepage.openHomeUrl();
        homepage.clickgetStartedButton(driver);
        homepage.clickSignInLink();
        signinpage.enterUsernameText("username");
        signinpage.enterPasswordText("password");
        signinpage.clickloginButton();
    }
 
 //Introduction link
    @Test(priority=2)
    public void testNavigateToIntroductionPage() {
        homepage.clickLinkedListGetStartedButton();
        Assert.assertEquals(homepage.getHomePageTitle(), "Linked List");
    }

    @Test(priority=3)
    public void testLLIntroductionPageNavigation() {
    	 homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickIntroductionLink();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("introduction/"));
    }
    
    @Test(priority=4)
    public void testTryHereButtonIntroduction() {
    	 homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickIntroductionLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("tryEditor"));
    }
    
    @Test(priority = 5, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeRunIntroductionPage(String sheetName, int rowNum, String expectedAlertPart) throws Exception{
    	 homepage.clickLinkedListGetStartedButton();
    	 linklistpage.clickIntroductionLink();
		 tryEditorPage.clickTryHereButton();
		 tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
		 tryEditorPage.clickRunButton();
		 String alertMessage = tryEditorPage.getAlertText();
		
		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
				"Alert message did not contain expected text. Actual: " + alertMessage);
	}

	
    
    @Test(priority= 6, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
    public void testValidCodeRunIntroductionPage(String sheetName, int rowNum, String expectedOutput) throws Exception {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickIntroductionLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();
        String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
        
        
    }
    
    @Test(priority = 7)
	public void testPracticeQuestionsLinkIntroductionPage() {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickIntroductionLink();
    	linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}
    
 //Creating Linked List
    
    @Test(priority=8)
    public void testCreatingLLPage() {
        homepage.clickLinkedListGetStartedButton();
        Assert.assertEquals(homepage.getHomePageTitle(), "Linked List");
    }

    @Test(priority=9)
    public void testCreatingLLPageNavigation() {
    	 homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickcreatingLinkedListLink();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("creating-linked-list/"));
    }
    
    @Test(priority=10)
    public void testTryHereButtonCreatingLL() {
    	 homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickcreatingLinkedListLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("tryEditor"));
    }
    
    @Test(priority = 11, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeCreatingLL(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
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
    
    @Test(priority= 12, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
    public void testValidCodeCreatingLL(String sheetName, int rowNum, String expectedOutput) throws Exception {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickcreatingLinkedListLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();
        String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
        
    }
    
    @Test(priority = 13)
	public void testPracticeQuestionsLinkcreatingLL() {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickcreatingLinkedListLink();
    	linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}
    
 //Types of Linked List
    
    @Test(priority=14)
    public void testTypesofLLPage() {
        homepage.clickLinkedListGetStartedButton();
        Assert.assertEquals(homepage.getHomePageTitle(), "Linked List");
    }

    @Test(priority=15)
    public void testTypesofLLPageNavigation() {
    	 homepage.clickLinkedListGetStartedButton();
    	 linklistpage.clickTypesOfLinkedListLink();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("types-of-linked-list/"));
    }
    
    @Test(priority=16)
    public void testTryHereButtonTypesofLL() {
    	 homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickTypesOfLinkedListLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("tryEditor"));
    }
    
    @Test(priority = 17, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeTypesofLL(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
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
    
    @Test(priority= 18, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
    public void testValidCodeTypesofLL(String sheetName, int rowNum, String expectedOutput) throws Exception {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickTypesOfLinkedListLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();
        String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
        
    }
    
    @Test(priority = 19)
	public void testPracticeQuestionsLinkTypesofLL() {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickTypesOfLinkedListLink();
    	linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}
    
    
 //Implement link List In Python  
    
    @Test(priority=20)
    public void implementLLPage() {
        homepage.clickLinkedListGetStartedButton();
        Assert.assertEquals(homepage.getHomePageTitle(), "Linked List");
    }

    @Test(priority=21)
    public void testImplementLLPageNavigation() {
    	 homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickImplementLLinPythonLink();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("implement-linked-list-in-python/"));
    }
    
    @Test(priority=22)
    public void testTryHereButtonImplementLL() {
    	 homepage.clickLinkedListGetStartedButton();
    	 linklistpage.clickImplementLLinPythonLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("tryEditor"));
    }
    
    @Test(priority = 23, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeImplementLL(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
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
    
    @Test(priority= 24, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
    public void testValidCodeImplementLL(String sheetName, int rowNum, String expectedOutput) throws Exception {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickImplementLLinPythonLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();
        String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
        
    }
    
    @Test(priority = 25)
	public void testPracticeQuestionsLinkImplementLL() {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickImplementLLinPythonLink();
    	linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}
    
 //Traversal   
    
    
    @Test(priority=26)
    public void traversalPage() {
        homepage.clickLinkedListGetStartedButton();
        Assert.assertEquals(homepage.getHomePageTitle(), "Linked List");
    }

    @Test(priority=27)
    public void testTraversalPageNavigation() {
    	 homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickTraversalLink();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("traversal/"));
    }
    
    @Test(priority=28)
    public void testTryHereButtonTraversal() {
    	 homepage.clickLinkedListGetStartedButton();
    	 linklistpage.clickTraversalLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("tryEditor"));
    }
    
    @Test(priority = 29, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeTraversal(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
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
    
    @Test(priority= 30, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
    public void testValidCodeTraversal(String sheetName, int rowNum, String expectedOutput) throws Exception {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickTraversalLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();
        String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
        
    }
    
    @Test(priority = 31)
	public void testPracticeQuestionsLinkTraversal() {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickTraversalLink();
    	linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}
    
  
//Insertion
    
    @Test(priority = 32)
    public void insertionPage() {
        homepage.clickLinkedListGetStartedButton();
        Assert.assertEquals(homepage.getHomePageTitle(), "Linked List");
    }

    @Test(priority = 33)
    public void testInsertionPageNavigation() {
    	 homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickInsertionLink();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("insertion-in-linked-list/"));
    }
    
    @Test(priority = 34)
    public void testTryHereButtonInsertion() {
    	 homepage.clickLinkedListGetStartedButton();
    	 linklistpage.clickInsertionLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("tryEditor"));
    }
    
    @Test(priority = 35, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeInsertion(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
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
    
    @Test(priority= 36, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
    public void testValidCodeInsertion(String sheetName, int rowNum, String expectedOutput) throws Exception {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickInsertionLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();
        String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
        
    }
    
    @Test(priority = 37)
	public void testPracticeQuestionsLinkInsertion() {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickInsertionLink();
    	linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}
    
  
  //Deletion
    
    @Test(priority = 38)
    public void deletionPage() {
        homepage.clickLinkedListGetStartedButton();
        Assert.assertEquals(homepage.getHomePageTitle(), "Linked List");
    }

    @Test(priority = 39)
    public void testDeletionPageNavigation() {
    	 homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickDeletionLink();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("deletion-in-linked-list/"));
    }
    
    @Test(priority = 40)
    public void testTryHereButtonDeletion() {
    	 homepage.clickLinkedListGetStartedButton();
    	 linklistpage.clickDeletionLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("tryEditor"));
    }
    
    @Test(priority = 41, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
	public void testInvalidCodeDeletion(String sheetName, int rowNum, String expectedAlertPart)
			throws Exception {
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
    
    @Test(priority = 42, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
    public void testValidCodeDeletion(String sheetName, int rowNum, String expectedOutput) throws Exception {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickDeletionLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
        tryEditorPage.clickRunButton();
        String actualOutput = tryEditorPage.getOutputText();
		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
        
    }
    
    @Test(priority = 43)
	public void testPracticeQuestionsLinkDeletion() {
    	homepage.clickLinkedListGetStartedButton();
    	linklistpage.clickDeletionLink();
    	linklistpage.clickPracticeQueLink();
		Assert.assertTrue(linklistpage.getCurrentPageUrl().endsWith("practice"));
	}
    
    @AfterMethod
	public void tearDown() {
	    if (DriverFactory.getDriver() != null) {
	        DriverFactory.getDriver().quit();
	        DriverFactory.closeDriver(); // a new static method that calls threadLocal.remove()
	    }
	}
}