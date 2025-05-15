package dsAlgoPageObjects;

import java.time.Duration;
import org.openqa.selenium.Alert;
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

public class QueuePageObj {
	WebDriver driver = DriverFactory.getDriver();
	String URL = ConfigReader.getUrl();
	String homeURL = ConfigReader.getUrlHome();

	@FindBy(xpath = "//h4[@class='bg-secondary text-white']")WebElement QueuePageTitle;
	@FindBy(linkText = "Implementation of QueueTests in Python")WebElement ImplementaionOfQueueLink;
	@FindBy(linkText = "Implementation using collections.deque")WebElement UsingCollectionsLink;
	@FindBy(linkText = "Implementation using array")WebElement ImplementationUsingArrayLink;
	@FindBy(linkText = "QueueTests Operations")WebElement QueueOperationsLink;

	@FindBy(xpath = "//a[@href='/tryEditor']")WebElement QueuetryHere;
	@FindBy(xpath = "//*[@id='answer_form']/button")WebElement QueueRunButton;
	@FindBy(xpath = "//*[@id=\"answer_form\"]/div/div/div[6]")WebElement CodeEditor;
	@FindBy(xpath = "//pre[@id='output']")WebElement CodeEditorOutput;
	@FindBy(xpath = "//*[@id='content']/a")WebElement QueuePracticeQuestions;

	public QueuePageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void QueuePageTitle() {
		PageFactory.initElements(driver, this);
		QueuePageTitle.getText();
		LoggerLoad.info("QueueTests Page title" + QueuePageTitle);
	}

	public String getcurrentpageUrl() {
		System.out.println(driver.getCurrentUrl());
		return (driver.getCurrentUrl());
	}

	public void clickImplementaionOfQueueLink() {
		PageFactory.initElements(driver, this);
		ImplementaionOfQueueLink.click();
		LoggerLoad.info("Implementaion Of QueueTests Link is clicked");
	}

	public void clickUsingCollectionsLink() {
		PageFactory.initElements(driver, this);
		UsingCollectionsLink.click();
		LoggerLoad.info("Using Collections Link is clicked");
	}

	public void clickImplementatonUsingArraylink() {
		PageFactory.initElements(driver, this);
		ImplementationUsingArrayLink.click();
		LoggerLoad.info("Implementation Using ArrayTests Link is clicked");
	}

	public void clickQueueOperationslink() {
		PageFactory.initElements(driver, this);
		QueueOperationsLink.click();
		LoggerLoad.info("QueueTests Operations Link is clicked");
	}

	public void clickPracticeQuestionsLink() {
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement editorOutputElement = wait.until(ExpectedConditions.visibilityOf(QueuePracticeQuestions));
		QueuePracticeQuestions.click();
		LoggerLoad.info("Practice Questions Link clicked");
	}

	public String validatePageTitle() {
		return driver.getTitle();
	}

	public void clickTryHereButton() {
		PageFactory.initElements(driver, this);
		QueuetryHere.click();
		LoggerLoad.info("Try here button clicked");
	}

	public void clickRunButton() {
		PageFactory.initElements(driver, this);
		QueueRunButton.click();
		LoggerLoad.info("Run button clicked");
	}

	public void validateTextEditorBox() {

		String Code = "print('Python is super fun!')";
		PageFactory.initElements(driver, this);
		CodeEditor = driver.switchTo().activeElement();
		Actions actions = new Actions(driver);
		actions.moveToElement(CodeEditor).click().perform();
		CodeEditor.sendKeys(Code);
		clickRunButton();
		validateCodeEditorOutput();
	}

	public void validateCodeEditorOutput() {
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement editorOutputElement = wait.until(ExpectedConditions.visibilityOf(CodeEditorOutput));
		String message = CodeEditorOutput.getText();
		System.out.println("Code Printed Successfully " + message);
	}

	public void HandleAlert() {
		PageFactory.initElements(driver, this);
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		LoggerLoad.info("Alert message: " + alertMessage);
		alert.accept();
		System.out.println("Alert message:" + alertMessage);
	}

	public void InvalidCode() {
		String invalidCode = "This is a test message !";
		PageFactory.initElements(driver, this);
		CodeEditor = driver.switchTo().activeElement();
		Actions actions = new Actions(driver);
		actions.moveToElement(CodeEditor).click().perform();
		CodeEditor.sendKeys(invalidCode);
		clickRunButton();
		HandleAlert();

	}

}
