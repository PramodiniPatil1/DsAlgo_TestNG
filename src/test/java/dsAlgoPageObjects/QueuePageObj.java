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
import utils.ConfigReader;
import utils.LoggerLoad;

public class QueuePageObj {
	WebDriver driver;
	String URL = ConfigReader.getUrl();
	String homeURL = ConfigReader.getUrlHome();

	@FindBy(xpath = "//h4[@class='bg-secondary text-white']") WebElement QueuePageTitle;
	@FindBy(linkText = "Implementation of Queue in Python") WebElement ImplementaionOfQueueLink;
	@FindBy(linkText = "Implementation using collections.deque") WebElement UsingCollectionsLink;
	@FindBy(linkText = "Implementation using array") WebElement ImplementationUsingArrayLink;
	@FindBy(linkText = "Queue Operations") WebElement QueueOperationsLink;

	@FindBy(xpath = "//a[@href='/tryEditor']") WebElement QueuetryHere;
	@FindBy(xpath = "//*[@id='answer_form']/button") WebElement QueueRunButton;
	@FindBy(xpath = "//pre[@id='output']") WebElement CodeEditorOutput;
	@FindBy(xpath = "//*[@id='content']/a") WebElement QueuePracticeQuestions;

	public QueuePageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void QueuePageTitle() {
		QueuePageTitle.getText();
		LoggerLoad.info("QueueTests Page title" + QueuePageTitle);
	}

	public String getcurrentpageUrl() {
		try {
			System.out.println(driver.getCurrentUrl());
			return driver.getCurrentUrl();
		} catch (org.openqa.selenium.NoSuchWindowException e) {
			LoggerLoad.error("Browser window was closed unexpectedly.");
			throw e;
		}
	}

	public void clickImplementaionOfQueueLink() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(ImplementaionOfQueueLink));
		ImplementaionOfQueueLink.click();
		LoggerLoad.info("Implementaion Of QueueTests Link is clicked");
	}

	public void clickUsingCollectionsLink() {
		UsingCollectionsLink.click();
		LoggerLoad.info("Using Collections Link is clicked");
	}

	public void clickImplementatonUsingArraylink() {
		ImplementationUsingArrayLink.click();
		LoggerLoad.info("Implementation Using ArrayTests Link is clicked");
	}

	public void clickQueueOperationslink() {
		QueueOperationsLink.click();
		LoggerLoad.info("QueueTests Operations Link is clicked");
	}

	public void clickPracticeQuestionsLink() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(QueuePracticeQuestions));
		QueuePracticeQuestions.click();
		LoggerLoad.info("Practice Questions Link clicked");
	}

	public String validatePageTitle() {
		return driver.getTitle();
	}

	public void clickTryHereButton() {
		QueuetryHere.click();
		LoggerLoad.info("Try here button clicked");
	}

	public void clickRunButton() {
		QueueRunButton.click();
		LoggerLoad.info("Run button clicked");
	}

	public void validateTextEditorBox() {
		String Code = "print('Python is super fun!')";
		WebElement CodeEditor = driver.switchTo().activeElement();
		Actions actions = new Actions(driver);
		actions.moveToElement(CodeEditor).click().sendKeys(Code).perform();
		clickRunButton();
		validateCodeEditorOutput();
	}

	public void validateCodeEditorOutput() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(CodeEditorOutput));
		String message = CodeEditorOutput.getText();
		System.out.println("Code Printed Successfully " + message);
	}

	public void HandleAlert() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String alertMessage = alert.getText();
			LoggerLoad.info("Alert message: " + alertMessage);
			alert.accept();
			System.out.println("Alert message:" + alertMessage);
		} catch (org.openqa.selenium.NoAlertPresentException e) {
			LoggerLoad.warn("No alert present to handle.");
		}
	}

	public void InvalidCode() {
		String invalidCode = "This is a test message !";
		WebElement CodeEditor = driver.switchTo().activeElement();
		Actions actions = new Actions(driver);
		actions.moveToElement(CodeEditor).click().sendKeys(invalidCode).perform();
		clickRunButton();
		HandleAlert();
	}
}
