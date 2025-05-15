package dsAlgoPageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import driverManager.DriverFactory;
import utils.ConfigReader;
import utils.LoggerLoad;

public class TreePageObj {

	WebDriver driver = DriverFactory.getDriver();
	String URL = ConfigReader.getUrl();
	String homeURL = ConfigReader.getUrlHome();

	@FindBy(xpath = "//a[@href='overview-of-trees']")WebElement Overviewoftrees;
	@FindBy(xpath = "//a[@href='terminologies']")WebElement Terminologies;
	@FindBy(xpath = "//a[@href='types-of-trees']")WebElement TypesofTrees;
	@FindBy(xpath = "//a[@href='tree-traversals']")WebElement TreeTraversals;
	@FindBy(xpath = "//a[@href='traversals-illustration']")WebElement TraversalsIllustrations;
	@FindBy(xpath = "//a[@href='binary-trees']")WebElement BinaryTrees;
	@FindBy(xpath = "//a[@href='types-of-binary-trees']")WebElement TypesofBinaryTrees;
	@FindBy(xpath = "//a[@href='implementation-in-python']")WebElement ImplementationinPython;
	@FindBy(xpath = "//a[@href='binary-tree-traversals']")WebElement BinaryTreeTraversals;
	@FindBy(xpath = "//a[@href='implementation-of-binary-trees']")WebElement ImplementationOfBinaryTrees;
	@FindBy(xpath = "//a[@href='applications-of-binary-trees']")WebElement ApplicationOfBinarytrees;
	@FindBy(xpath = "//a[@href='binary-search-trees']")WebElement BinarySearchTrees;
	@FindBy(xpath = "//a[@href='implementation-of-bst']")WebElement ImplementationofBTS;
	@FindBy(xpath = "//*[@id='content']/a")WebElement PracticeQuestions;
	@FindBy(xpath = "//a[@href='/tryEditor']")WebElement tryHere;
	@FindBy(xpath = "//*[@id='answer_form']/button")WebElement RunButton;
	@FindBy(xpath = "//*[@id=\"answer_form\"]/div/div/div[6]")WebElement CodeEditor;
	@FindBy(xpath = "//pre[@id='output']")WebElement CodeEditorOutput;
	@FindBy(xpath = "//h4[@class='bg-secondary text-white']")WebElement TreePageTitle;

	public TreePageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void TreePageTitle() {
		String actualUrl = driver.getCurrentUrl();
	
		LoggerLoad.info("TreeTests Page title:" + driver.getCurrentUrl());
	}

	public String getcurrentpageUrl() {
			return driver.getCurrentUrl();
	}

	public void clickOverviewOfTreesLink() {
		Overviewoftrees.click();
		LoggerLoad.info("Overview of trees Link clicked");
	}

	public void clickTerminologiesLink() {
		Terminologies.click();
		LoggerLoad.info("Terminologies Link clicked");
	}

	public void clickTypesOfTreesLink() {
		TypesofTrees.click();
		LoggerLoad.info("Types of Trees Link clicked");
	}

	public void clickTreeTraversalsLink() {
		TreeTraversals.click();
		LoggerLoad.info("TreeTests Traversals Link clicked");
	}

	public void clickTraversalsIllustrationsLink() {

		TraversalsIllustrations.click();
		LoggerLoad.info("Traversals Illustrations Link clicked");
	}

	public void clickBinaryTreesLink() {

		BinaryTrees.click();
		LoggerLoad.info("Binary Trees Link clicked");
	}

	public void clickTypesofBinaryTreesLink() {

		TypesofBinaryTrees.click();
		LoggerLoad.info("Types of BinaryTrees Link clicked");
	}

	public void clickImplementationinPythonLink() {

		ImplementationinPython.click();
		LoggerLoad.info("Implementation in Python Link clicked");
	}

	public void clickBinaryTreeTraversalsLink() {

		BinaryTreeTraversals.click();
		LoggerLoad.info("Binary TreeTests Traversals Link clicked");
	}

	public void clickImplementationOfBinaryTreesLink() {

		ImplementationOfBinaryTrees.click();
		LoggerLoad.info("Implementation Of Binary Trees Link clicked");
	}

	public void clickApplicationOfBinarytreesLink() {

		ApplicationOfBinarytrees.click();
		LoggerLoad.info("Application Of Binarytrees Link clicked");
	}

	public void clickBinarySearchTreesLink() {

		BinarySearchTrees.click();
		LoggerLoad.info("Binary Search Trees Link clicked");
	}

	public void clickImplementationofBTSLink() {

		ImplementationofBTS.click();
		LoggerLoad.info("Implementation of BTS Link clicked");
	}

	public void clickPracticeQuestionsLink() {
		PracticeQuestions.click();
		LoggerLoad.info("Practice Questions Link clicked");
	}

	public void codeEditorOutput() {
		CodeEditorOutput.getText();
		LoggerLoad.info("Code editor Output is :" + CodeEditorOutput.getText());
	}

	public void PageScrollDown() {
		PageFactory.initElements(driver, this);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 10000);");
	}

}