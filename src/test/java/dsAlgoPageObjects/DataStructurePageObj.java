package dsAlgoPageObjects;


import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;
import utils.LoggerLoad;

public class DataStructurePageObj {
	  WebDriver driver;
	   String URL = ConfigReader.getUrl();
		String homeURL = ConfigReader.getUrlHome();
	public DataStructurePageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}

	@FindBy(xpath = "//a[@href='data-structures-introduction']") WebElement dsGetStartedBtn;
	@FindBy(xpath = "//a[@href='/data-structures-introduction/practice']") WebElement practiceQuestionsLink;
	@FindBy(xpath = "//a[@href='time-complexity']") WebElement timeComplexityLink;

	public void clickGetStartedBtn() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement getStartedButton = wait.until(ExpectedConditions
				.elementToBeClickable(dsGetStartedBtn));
		getStartedButton.click();
		LoggerLoad.info("DS Get Started Button Clicked");
	}

	public void clickPracticeQuestionsLink() {
		practiceQuestionsLink.click();
		LoggerLoad.info("Practice Questions link clicked");
	}

	public void getcurrentpageUrl() {
		String actualUrl = driver.getCurrentUrl();
		LoggerLoad.info("Current URL after login: " + actualUrl);
	}

	public WebElement getTimeComplexityLink() {
		return timeComplexityLink;
	}

	public void ClickTimeComplexityLink() {
		PageFactory.initElements(driver, this); 
		timeComplexityLink.click();
		LoggerLoad.info("Time Complexity Link Clicked");
	}

	public Boolean validateElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public String validatePageTitle() {
		return driver.getTitle();
	}

	public String getTextForElement(WebElement element) {
		return element.getText();
	}


}