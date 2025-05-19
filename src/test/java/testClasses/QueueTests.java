package testClasses;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.GraphPageObj;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.LinkedListPageObj;
import dsAlgoPageObjects.QueuePageObj;
import dsAlgoPageObjects.RegisterPageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.TryEditorPage;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.exc.InvalidFormatException;
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

	@BeforeMethod
	public void setUp() throws InvalidFormatException, IOException, OpenXML4JException {
		 driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());
		
			try {
				String browser = ConfigReader.getBrowserType();
				driver = DriverFactory.initializeDriver(browser);
				signinpage = new SignInPageObj(driver);
				homepage = new HomePageObj(driver);
				tryEditorPage = new TryEditorPage(driver);
				queuepage = new QueuePageObj(driver); 
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
 
 //Implementation of Queue in Python
    @Test(priority=2)
    public void testNavigateToImplementationofQueueinPythonPage() {
        homepage.clickQueueGetStartedButton();
        Assert.assertEquals(homepage.getHomePageTitle(), "Queue");
    }

    @Test(priority=3)
    public void testImplementationofQueueinPythonPageNavigation() {
    	homepage.clickQueueGetStartedButton();
    	queuepage.clickImplementaionOfQueueLink();
        Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("implementation-lists/"));
    }
    
    @Test(priority=4)
    public void testTryHereButtonImplementationOfQueueInPython() {
    	homepage.clickQueueGetStartedButton();
    	queuepage.clickImplementaionOfQueueLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("tryEditor"));
    }

    @Test(priority = 5, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
   	public void testInvalidCodeRunImplementationofQueuePythonPage(String sheetName, int rowNum, String expectedAlertPart) throws Exception{
    	homepage.clickQueueGetStartedButton();
    	queuepage.clickImplementaionOfQueueLink();
   		 tryEditorPage.clickTryHereButton();
   		 tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
   		 tryEditorPage.clickRunButton();
   		 String alertMessage = tryEditorPage.getAlertText();
   		
   		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
   				"Alert message did not contain expected text. Actual: " + alertMessage);
   	}

   	
       
       @Test(priority= 6, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
       public void testValidCodeRunImplementationofQueuePythonPage(String sheetName, int rowNum, String expectedOutput) throws Exception {
    	   homepage.clickQueueGetStartedButton();
       	   queuepage.clickImplementaionOfQueueLink();
           tryEditorPage.clickTryHereButton();
           tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
           tryEditorPage.clickRunButton();
           String actualOutput = tryEditorPage.getOutputText();
   		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
           
           
       }
       
       @Test(priority = 7)
   	public void testPracticeQuestionsLinkImplementaionofQueuePythonPage() {
    	   homepage.clickQueueGetStartedButton();
       	   queuepage.clickImplementaionOfQueueLink();
       	queuepage.clickPracticeQuestionsLink();
   		Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("practice"));
   	}
       
//Implementation using Collections
       
       @Test(priority = 8)
       public void testNavigateToImplementationusingCollectionsPage() {
           homepage.clickQueueGetStartedButton();
           Assert.assertEquals(homepage.getHomePageTitle(), "Queue");
       }

       @Test(priority = 9)
       public void testImplementationusingCollectionsPageNavigation() {
       	homepage.clickQueueGetStartedButton();
       	queuepage.clickUsingCollectionsLink();
           Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("implementation-collections/"));
       }
       
       @Test(priority = 10)
       public void testTryHereButtonImplementationusingCollections() {
       	homepage.clickQueueGetStartedButton();
       	queuepage.clickUsingCollectionsLink();
           tryEditorPage.clickTryHereButton();
           Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("tryEditor"));
       }

       @Test(priority = 11, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
      	public void testInvalidCodeRunImplementationusingCollectionsPage(String sheetName, int rowNum, String expectedAlertPart) throws Exception{
       	homepage.clickQueueGetStartedButton();
       	queuepage.clickUsingCollectionsLink();
      		 tryEditorPage.clickTryHereButton();
      		 tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
      		 tryEditorPage.clickRunButton();
      		 String alertMessage = tryEditorPage.getAlertText();
      		
      		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
      				"Alert message did not contain expected text. Actual: " + alertMessage);
      	}

      	
          
          @Test(priority = 12, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
          public void testValidCodeRunImplementationusingCollectionsPage(String sheetName, int rowNum, String expectedOutput) throws Exception {
       	   homepage.clickQueueGetStartedButton();
          	   queuepage.clickUsingCollectionsLink();
              tryEditorPage.clickTryHereButton();
              tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
              tryEditorPage.clickRunButton();
              String actualOutput = tryEditorPage.getOutputText();
      		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
              
              
          }
          
          @Test(priority = 13)
      	public void testPracticeQuestionsLinkImplementaionusingCollectionsPage() {
       	   homepage.clickQueueGetStartedButton();
          	   queuepage.clickUsingCollectionsLink();
          	queuepage.clickPracticeQuestionsLink();
      		Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("practice"));
      	}
          
//Implementation Using Array            
         
          @Test(priority = 14)
          public void testNavigateToImplementationusingArrayPage() {
              homepage.clickQueueGetStartedButton();
              Assert.assertEquals(homepage.getHomePageTitle(), "Queue");
          }

          @Test(priority = 15)
          public void testImplementationusingArrayPageNavigation() {
          	homepage.clickQueueGetStartedButton();
          	queuepage.clickImplementatonUsingArraylink();
              Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("Implementation-array/"));
          }
          
          @Test(priority = 16)
          public void testTryHereButtonImplementationusingArray() {
          	homepage.clickQueueGetStartedButton();
          	queuepage.clickImplementatonUsingArraylink();
              tryEditorPage.clickTryHereButton();
              Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("tryEditor"));
          }

          @Test(priority = 17, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
         	public void testInvalidCodeRunImplementationusingArrayPage(String sheetName, int rowNum, String expectedAlertPart) throws Exception{
          		 homepage.clickQueueGetStartedButton();
          		 queuepage.clickImplementatonUsingArraylink();
         		 tryEditorPage.clickTryHereButton();
         		 tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
         		 tryEditorPage.clickRunButton();
         		 String alertMessage = tryEditorPage.getAlertText();
         		
         		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
         				"Alert message did not contain expected text. Actual: " + alertMessage);
         	}

         	
             
             @Test(priority= 18, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
             public void testValidCodeRunImplementationusingArrayPage(String sheetName, int rowNum, String expectedOutput) throws Exception {
          	     homepage.clickQueueGetStartedButton();
             	 queuepage.clickImplementatonUsingArraylink();
                 tryEditorPage.clickTryHereButton();
                 tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
                 tryEditorPage.clickRunButton();
                 String actualOutput = tryEditorPage.getOutputText();
         		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
                 
                 
             }
             
             @Test(priority = 19)
         	public void testPracticeQuestionsLinkImplementaionusingArrayPage() {
          	   homepage.clickQueueGetStartedButton();
             	   queuepage.clickImplementatonUsingArraylink();
             	queuepage.clickPracticeQuestionsLink();
         		Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("practice"));
         	}
             
             
 //Queue Operations
             
             @Test(priority = 20)
             public void testNavigateToQueueOperationsPage() {
                 homepage.clickQueueGetStartedButton();
                 Assert.assertEquals(homepage.getHomePageTitle(), "Queue");
             }

             @Test(priority = 21)
             public void testQueueOperationsPageNavigation() {
             	homepage.clickQueueGetStartedButton();
             	queuepage.clickQueueOperationslink();
                 Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("QueueOp/"));
             }
             
             @Test(priority = 22)
             public void testTryHereButtonQueueOperations() {
             	homepage.clickQueueGetStartedButton();
             	queuepage.clickQueueOperationslink();
                 tryEditorPage.clickTryHereButton();
                 Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("tryEditor"));
             }

             @Test(priority = 23, dataProvider = "InvalidPythonCode", dataProviderClass = DataProviders.class)
            	public void testInvalidCodeRunQueueOperationsPage(String sheetName, int rowNum, String expectedAlertPart) throws Exception{
             	homepage.clickQueueGetStartedButton();
             	queuepage.clickQueueOperationslink();
            		 tryEditorPage.clickTryHereButton();
            		 tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
            		 tryEditorPage.clickRunButton();
            		 String alertMessage = tryEditorPage.getAlertText();
            		
            		Assert.assertTrue(alertMessage.contains(expectedAlertPart),
            				"Alert message did not contain expected text. Actual: " + alertMessage);
            	}

            	
                
                @Test(priority = 24, dataProvider = "ValidPythonCode", dataProviderClass = DataProviders.class)
                public void testValidCodeRunQueueOperationsPage(String sheetName, int rowNum, String expectedOutput) throws Exception {
             	    homepage.clickQueueGetStartedButton();
                    queuepage.clickQueueOperationslink();
                    tryEditorPage.clickTryHereButton();
                    tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
                    tryEditorPage.clickRunButton();
                    String actualOutput = tryEditorPage.getOutputText();
            		Assert.assertTrue(actualOutput.contains(expectedOutput), "Expected output not found!");
                    
                    
                }
                
                @Test(priority = 25)
            	public void testPracticeQuestionsLinkQueueOperationsPage() {
             	   homepage.clickQueueGetStartedButton();
                	queuepage.clickQueueOperationslink();
                	queuepage.clickPracticeQuestionsLink();
            		Assert.assertTrue(queuepage.getcurrentpageUrl().endsWith("practice"));
            	}
          
       
	 @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}