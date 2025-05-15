package dsAlgoPageObjects;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.ExcelRead;
import utils.LoggerLoad;

public class ArrayPageObj {
    WebDriver driver;
    ExcelRead excelReader = new ExcelRead();
    ConfigReader config = new ConfigReader();
   
    public ArrayPageObj(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); 
    }

    @FindBy(xpath = "//a[text()='Arrays in Python']")WebElement arraysInPythonLink;

  

    @FindBy(xpath = "//a[@href='arrays-using-list']")
    public WebElement arraysUsingListLink;

    @FindBy(xpath = "//a[@href='basic-operations-in-lists']")
    public WebElement basicOperationsInListsLink;

    @FindBy(xpath = "//a[@href='applications-of-array']")
    public WebElement applicationsOfArrayLink;

    @FindBy(linkText = "Practice Questions")
    public WebElement practiceQuestionsLink;

    @FindBy(xpath = "//a[@href='/question/1']")
    public WebElement searchTheArrayLink;

    @FindBy(xpath = "//a[@href='/question/2']")
    public WebElement maxConsecutiveOnes;

    @FindBy(xpath = "//a[@href='/question/3']")
    public WebElement findNumWithEvenNumOfDigits;

    @FindBy(xpath = "//a[@href='/question/4']")
    public WebElement squaresOfAsortedArray;

    @FindBy(xpath = "//button[text()='Run']")
    public WebElement runButton;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement submitButton;
 
    public void clickarraysInPythonLink() {
        arraysInPythonLink.click();
        LoggerLoad.info("Clicking Arrays in Python link");
    }

    public void clickarraysUsingListLink() {
        arraysUsingListLink.click();
        LoggerLoad.info("Clicking Arrays Using List link");
    }

    public void clickbasicOperationsInListsLink() {
        basicOperationsInListsLink.click();
        LoggerLoad.info("Clicking Basic Operations In Lists link");
    }

    public void clickapplicationsOfArrayLink() {
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	    WebElement link = wait.until(ExpectedConditions.elementToBeClickable(applicationsOfArrayLink));
    	    link.click();
    	
        LoggerLoad.info("Clicking Applications Of ArrayTests link");
    }

    public void clickPracticeQuestionsLink() {
        practiceQuestionsLink.click();
        LoggerLoad.info("Practice Questions link clicked");
    }

    public void clickSearchTheArrayLink() {
        searchTheArrayLink.click();
        LoggerLoad.info("Clicking Search the ArrayTests link");
    }

    public void clickMaxConsecutiveOnes() {
        maxConsecutiveOnes.click();
        LoggerLoad.info("Clicking Max Consecutive Ones link");
    }

    public void clickFindNumWithEvenNumOfDigits() {
        findNumWithEvenNumOfDigits.click();
        LoggerLoad.info("Clicking Find Numbers with Even Number of Digits link");
    }

    public void clickSquaresOfASortedArray() {
        squaresOfAsortedArray.click();
        LoggerLoad.info("Clicking Squares of a Sorted ArrayTests link");
    }

	


    
    
}

