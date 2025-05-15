package dsAlgoPageObjects;

import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
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

public class StackPageObj {
	WebDriver driver = DriverFactory.getDriver();
	String URL = ConfigReader.getUrl();
	String homeURL = ConfigReader.getUrlHome();
	@FindBy(xpath = "//h4[@class='bg-secondary text-white']") 
	WebElement stackPageTitle;

	@FindBy(xpath = "/html/body/div[2]/ul[1]/a") 
	WebElement Operationsinstack;

	@FindBy(xpath = "//a[@href='implementation']") 
	WebElement StackImplementation;

	@FindBy(xpath = "//a[@href='stack-applications']") 
	WebElement StackApplications;

	@FindBy(xpath = "//*[@id='content']/a") 
	WebElement PracticeQuestions;

	@FindBy(xpath = "//a[@href='/tryEditor']") 
	WebElement StacktryHere;

	@FindBy(xpath = "//*[@id='answer_form']/button") 
	WebElement StackRunButton;

	@FindBy(xpath = "//*[@id=\"answer_form\"]/div/div/div[6]") 
	WebElement CodeEditor;

	@FindBy(xpath = "//pre[@id='output']") 
	WebElement CodeEditorOutput;


	public StackPageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public String getcurrentpageUrl() {
		System.out.println(driver.getCurrentUrl());
		return (driver.getCurrentUrl());
	}

	public void ClickOperationsinstackLink() {

		Operationsinstack.click();
		LoggerLoad.info("Operations in stack Link is clicked");
	}

	public String validatePageTitle() {
		return driver.getTitle();
	}

	public void ClickStackImplementationLink() {

		StackImplementation.click();
		LoggerLoad.info("StackTests Implementation Link is clicked");
	}

	public void StackApplicationslink() {
		StackApplications.click();
		LoggerLoad.info("StackTests Applications Link is clicked");
	}

	public void clickPracticeQuestionsLink() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement editorOutputElement = wait.until(ExpectedConditions.visibilityOf(PracticeQuestions));
		PracticeQuestions.click();
		LoggerLoad.info("Practice Questions Link clicked");
	}

	public void clickTryHereButton() {
		StacktryHere.click();
		LoggerLoad.info("Try here button is clicked");
	}

	public void clickRunButton() {
		StackRunButton.click();
		LoggerLoad.info("Run button is clicked");
	}

	public void validateTextEditorBox() {
		String Code = "print('Python is fun!')";
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

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	public void acceptAlertIfPresent() {
		if (isAlertPresent()) {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
	}

	public void verifyErrorAlert() {
		if (!isAlertPresent()) {
			throw new AssertionError("Expected an alert due to invalid code, but none appeared.");
		}
		acceptAlertIfPresent(); 
	}
}
