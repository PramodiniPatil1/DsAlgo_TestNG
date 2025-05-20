package testClasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import driverManager.DriverFactory;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.StackPageObj;
import dsAlgoPageObjects.TryEditorPage;
import utils.ConfigReader;



public class HomeTests {
	
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
	}
	
		@Test(priority = 1)
		public void DataStructureIntroductionGetstarted() throws Exception {
	        homepage.clickDsGetStartedButton();
			driver.getTitle();
		}
		
		@Test(priority = 2)
		public void ArrayGetStarted() throws Exception {
	        homepage.clickArrayGetStartedButton();
			driver.getTitle();
		}
		@Test(priority = 3)
		public void LinkedListGetStarted() throws Exception {
	        homepage.clickLinkedListGetStartedButton();
			driver.getTitle();
		}
		@Test(priority = 4)
		public void StackGetStarted() throws Exception {
	        homepage.clickStackGetStartedButton();
			driver.getTitle();
		}
		@Test(priority = 5)
		public void QueueGetStarted() throws Exception {
	        homepage.clickQueueGetStartedButton();
			driver.getTitle();
		}
		@Test(priority =6)
		public void TreeGetStarted() throws Exception {
	        homepage.clickTreeGetStartedButton();
			driver.getTitle();
		}
		
		@Test(priority = 7)
		public void GraphGetStarted() throws Exception {
	        homepage.clickGraphGetStartedButton();
			driver.getTitle();
		}
			
		@Test(priority = 1)
		public void DSDropdown() throws Exception {
	        homepage.clickDropdownArrow(driver);
			driver.getTitle();
		}
		public void ArrayDropdown() throws Exception {
			 homepage.clickDropdownArrow(driver);
	        homepage.clickDropdownArray();
			driver.getTitle();
		}
		
		public void LinkedListDropdown() throws Exception {
			 homepage.clickDropdownArrow(driver);
	        homepage.clickDropdownLinkedList();
			driver.getTitle();
		}
		public void StackDropdown() throws Exception {
			 homepage.clickDropdownArrow(driver);
	        homepage.clickDropdownStack();
			driver.getTitle();
		}
		public void QueueDropdown() throws Exception {
			 homepage.clickDropdownArrow(driver);
	        homepage.clickDropdownQueue();
			driver.getTitle();
		}
		public void TreeDropdown() throws Exception {
			 homepage.clickDropdownArrow(driver);
	        homepage.clickDropdownTree();
			driver.getTitle();
		}
		public void GraphDropdown() throws Exception {
			 homepage.clickDropdownArrow(driver);
	        homepage.clickDropdownGraph();
			driver.getTitle();
		}
	
		@AfterMethod
		public void tearDown() {
			DriverFactory.closeDriver();
		}
}