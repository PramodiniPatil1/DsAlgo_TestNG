package pages;

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

public class GraphPageObj {
    WebDriver driver;
    String URL = ConfigReader.getUrl();
    String homeURL = ConfigReader.getUrlHome();

    @FindBy(xpath = "//h4[@class='bg-secondary text-white']")
    WebElement GraphPageTitle;

    @FindBy(xpath = "//a[@href='graph']")
    WebElement InsideGraphLink;

    @FindBy(xpath = "//a[@href='graph-representations']")
    WebElement GraphRepresentationsLink;

    @FindBy(xpath = "//a[@href='/graph/practice']")
    WebElement GraphPracticeQuestionsLink;

    @FindBy(xpath = "//a[@href='/tryEditor']")
    WebElement GraphTryHereLink;

    @FindBy(xpath = "//button[text()='Run']")
    WebElement GraphRunButtonLink;

    @FindBy(xpath = "//*[@id=\"answer_form\"]/div/div/div[6]")
    WebElement CodeEditor;

    @FindBy(xpath = "//pre[@id='output']")
    WebElement CodeEditorOutput;

    public GraphPageObj(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // Initialize elements only once here
    }

    public void GraphTitle() {
        String title = GraphPageTitle.getText();
        LoggerLoad.info("Page title is " + title);
    }

    public void GraphPageTitle() {
        String url = ConfigReader.getProperty("GraphPageUrl");
        LoggerLoad.info("Graph Page title: " + url);
    }

    public String getcurrentpageUrl() {
        String currentUrl = driver.getCurrentUrl();
        System.out.println(currentUrl);
        return currentUrl;
    }

    public void ClickInsideGraphkLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(InsideGraphLink)).click();
        LoggerLoad.info("Inside Graph Link is clicked");
    }

    public void ClickGraphRepresentationLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(GraphRepresentationsLink)).click();
        LoggerLoad.info("Graph Representations Link is clicked");
    }

    public void clickPracticeQuestionsLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(GraphPracticeQuestionsLink)).click();
        LoggerLoad.info("Practice Questions Link clicked");
    }

    public String validatePageTitle() {
        return driver.getTitle();
    }

    public void codeEditorOutput() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(CodeEditorOutput));
        String output = CodeEditorOutput.getText();
        LoggerLoad.info("Code editor Output is: " + output);
    }

    public void clickGraphTryHereButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(GraphTryHereLink)).click();
        LoggerLoad.info("Try here button is clicked");
    }

    public void clickGraphRunButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(GraphRunButtonLink)).click();
        LoggerLoad.info("Run button is clicked");
    }

    public void validateTextEditorBox() {
        String Code = "print('Python is fun!')";
        CodeEditor = driver.switchTo().activeElement();
        Actions actions = new Actions(driver);
        actions.moveToElement(CodeEditor).click().perform();
        CodeEditor.sendKeys(Code);
        clickGraphRunButton();
        validateCodeEditorOutput();
    }

    public void validateCodeEditorOutput() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(CodeEditorOutput));
        String message = CodeEditorOutput.getText();
        System.out.println("Code Printed Successfully: " + message);
    }

    public void HandleAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        LoggerLoad.info("Alert message: " + alertMessage);
        alert.accept();
        System.out.println("Alert message: " + alertMessage);
    }

    public void InvalidCode() {
        String invalidCode = "This is a test message !";
        CodeEditor = driver.switchTo().activeElement();
        Actions actions = new Actions(driver);
        actions.moveToElement(CodeEditor).click().perform();
        CodeEditor.sendKeys(invalidCode);
        clickGraphRunButton();
        HandleAlert();
    }
}
