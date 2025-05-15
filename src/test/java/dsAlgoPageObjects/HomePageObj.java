package dsAlgoPageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverManager.DriverFactory;
import utils.ConfigReader;
import utils.LoggerLoad;

public class HomePageObj {
	WebDriver driver = DriverFactory.getDriver();
	String URL = ConfigReader.getUrl();
	String homeURL = ConfigReader.getUrlHome();
	String expectedMessage = "You are not logged in";
	
	@FindBy(xpath = "//div[@class='content']/p")WebElement HomePageMessage;
	@FindBy(xpath = "//button[@class='btn']")WebElement GetStartedButton;
	@FindBy(xpath = "//div[@class='alert alert-primary']")WebElement RegisterMsg;
	
	@FindBy(css = "//a[@href='#']")WebElement dropdown;
	@FindBy(css = "a.dropdown-item[href='/array']")WebElement dropdownArray;
	@FindBy(css = "a.dropdown-item[href='/linked-list']")WebElement dropdownLinkedlist;
	@FindBy(css = "a.dropdown-item[href='/stack']")WebElement dropdownStack;
	@FindBy(css = "a.dropdown-item[href='/queue']")WebElement dropdownQueue;
	@FindBy(css = "a.dropdown-item[href='/tree']")WebElement dropdownTree;
	@FindBy(css = "a.dropdown-item[href='/graph']")WebElement dropdownGraph;
	// Get started button
	@FindBy(css = "a.btn-primary[href='data-structures-introduction']")WebElement dsIntroductionGetStartedBtn;
	@FindBy(css = "a[href='array']")WebElement arrayGetStartedBtn;
	@FindBy(css = "a[href='linked-list']")WebElement linkedListGetStartedBtn;
	@FindBy(css = "a[href='stack']")WebElement stackGetStartedBtn;
	@FindBy(css = "a[href='queue']")WebElement queueGetStartedBtn;
	@FindBy(css = "a[href='tree']")WebElement treeGetStartedBtn;
	@FindBy(css = "a[href='graph']")WebElement graphGetStartedBtn;
	
	@FindBy(xpath ="//div[@class='navbar-nav']//a[@href='/login']") WebElement SignInLink;
	@FindBy(xpath="//div[2]/ul/a[2]") WebElement RegisterLink;
	@FindBy(css = "div.alert.alert-primary")WebElement authenticationMessage;
	@FindBy(xpath ="//a[@class='nav-link dropdown-toggle']")List<WebElement> dropdownItems; 
	@FindBy(css = "div.alert.alert-primary[role='alert']")WebElement ActualauthenticationMessage;
	@FindBy(css = "a.dropdown-toggle")WebElement DropdownArrow;
	

	public HomePageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void openUrl() {
		driver.get(ConfigReader.getUrl());
		LoggerLoad.info("Inside openURL");
	}	
	public String getMessage() {
		String message = HomePageMessage.getText(); 
		return message;
	}
	public void clickGetStartedHomePageButton() {
			GetStartedButton.click();
	}
	public String getTitle() {
		return driver.getTitle();
	}
	public String registerSuccess() {
		return RegisterMsg.getText();
	}		
	public void openHomeUrl() {
		PageFactory.initElements(driver, this);
		driver.get(ConfigReader.getUrlHome());
		LoggerLoad.info("Inside HomeTests URL");
		GetStartedButton.click();
	}
	
	public String getAuthenticationMessage() {
		return authenticationMessage.getText();
	}
	public void ActualAuthenticationMessage(String string) {
		ActualauthenticationMessage.getText();
	}
	public String getHomePageTitle() {
		return driver.getTitle();
	}
	public void getUserName() {
		
		ConfigReader.getUserName();
		LoggerLoad.info("Username Selected from Property file");
	}
	public void getPassword() {
		
		ConfigReader.getPassword();
		LoggerLoad.info("Password Selected from Property file");
	}
	public void clickDropdownArrow(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(DropdownArrow));
			Actions actions = new Actions(driver);
			actions.moveToElement(DropdownArrow).perform();
			DropdownArrow.click();
			System.out.println("Dropdown Arrow clicked successfully.");
		    } catch (Exception e) {
		    LoggerLoad.info("Error clicking dropdown Dropdown Arrow: " + e.getMessage());
		     }
	     }

	public void clickDropdownArray() {
		dropdownArray.click();
		LoggerLoad.info("ArrayTests Dropdown selected");
	}

	public void clickDropdownLinkedList() {
		dropdownLinkedlist.click();
		LoggerLoad.info("LinkedList Dropdown selected");
	}

	public void clickDropdownStack() {
		dropdownStack.click();
		LoggerLoad.info("StackTests Dropdown selected");
	}

	public void clickDropdownQueue() {
		dropdownQueue.click();
		LoggerLoad.info("QueueTests Dropdown selected");
	}

	public void clickDropdownTree() {
		dropdownTree.click();
		LoggerLoad.info("TreeTests Dropdown selected");
	}

	public void clickDropdownGraph() {
		dropdownGraph.click();
		LoggerLoad.info("Graph Dropdown selected");
	}
	
	public void clickgetStartedButton(WebDriver driver) {
		LoggerLoad.info("Button clicked successfully.");
	}  

	public void clickDsGetStartedButton() {
		dsIntroductionGetStartedBtn.click();
		LoggerLoad.info("DS-Introduction Get Started Button ");
	}

	public void clickArrayGetStartedButton() {
		arrayGetStartedBtn.click();
		LoggerLoad.info("ArrayTests Get Started Button clicked");
	}

	public void clickLinkedListGetStartedButton() {
		linkedListGetStartedBtn.click();
		LoggerLoad.info("Linked List Get Started Button clicked");
	}

	public void clickStackGetStartedButton() {
		stackGetStartedBtn.click();
		LoggerLoad.info("StackTests Get Started Button clicked");
	}

	public void clickQueueGetStartedButton() {
		queueGetStartedBtn.click();
		LoggerLoad.info("QueueTests Get Started Button clicked");
	}

	public void clickTreeGetStartedButton() {
		treeGetStartedBtn.click();
		LoggerLoad.info("TreeTests Get Started Button clicked");
	}

	public void clickGraphGetStartedButton() {
		graphGetStartedBtn.click();
		LoggerLoad.info("Graph Get Started Button clicked");
	}
	
	public void clickRegisterLink() {
		RegisterLink.click();
		LoggerLoad.info("RegisterTests Link on Homepage clicked");
			}
	public void clickSignInLink() {
		 PageFactory.initElements(driver, this);
		SignInLink.click();
		LoggerLoad.info("Sign In Link on Homepage clicked");
	}
	
	
}
