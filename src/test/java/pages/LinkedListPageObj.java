package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driverManager.DriverFactory;
import utils.ConfigReader;
import utils.LoggerLoad;

public class LinkedListPageObj {
	
	WebDriver driver = DriverFactory.getDriver();
	String URL = ConfigReader.getUrl();
	String homeURL = ConfigReader.getUrlHome();
	
	@FindBy(xpath = "//a[@href='introduction']")WebElement Introduction;
	@FindBy(xpath = "//a[@href='creating-linked-list']") WebElement creatingLinkedList;
	@FindBy(xpath = "//a[@href='types-of-linked-list']") WebElement TypesOfLinkedList;
	@FindBy(xpath = "//a[@href='implement-linked-list-in-python']") WebElement ImplementLLinPython;
	@FindBy(xpath = "//a[@href='traversal']") WebElement Traversal;
	@FindBy(xpath = "//a[@href='insertion-in-linked-list']") WebElement Insertion;
	@FindBy(xpath = "//a[@href='deletion-in-linked-list']")WebElement Deletion;
	@FindBy(xpath = "//a[@href='/linked-list/practice']") WebElement PracticeQueLinkedList;
	@FindBy(xpath = "//a[@href='/tryEditor']")WebElement TryHere;
	@FindBy(xpath = "//h4[@class='bg-secondary text-white']")WebElement LinkedListTitle;
	
	public LinkedListPageObj(WebDriver driver) {
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	}  
		
	public String getCurrentPageUrl()
	{
		System.out.println(driver.getCurrentUrl());
		return(driver.getCurrentUrl());
	}
	public void LinkedListTitle() {
		 	LoggerLoad.info("Linked List Page title" + LinkedListTitle);
	}

	public void clickIntroductionLink() {
		 Introduction.click();
		LoggerLoad.info("Introduction Link clicked");
	}
	public void clickcreatingLinkedListLink() {
		 creatingLinkedList.click();
		LoggerLoad.info("creating Linked List Link clicked");
	}
	
	public void clickTypesOfLinkedListLink() {
		 TypesOfLinkedList.click();
		LoggerLoad.info("Types Of Linked List Link clicked");
	}
	
	public void clickImplementLLinPythonLink() {
		 ImplementLLinPython.click();
		LoggerLoad.info("Implement Linked List in Python Link clicked");
	}
		
	public void clickTraversalLink() {
		 Traversal.click();
		LoggerLoad.info("Traversal Link clicked");
	}
	
	public void clickInsertionLink() {
		 Insertion.click();
		LoggerLoad.info("Insertion Link clicked");
	}
	public void clickDeletionLink() {
		 Deletion.click();
		LoggerLoad.info("Deletion Link clicked");
	}
	
	public void clickPracticeQueLink() {
		PracticeQueLinkedList.click();
		LoggerLoad.info("Practice Questions Link clicked");
	}
	
	

}
