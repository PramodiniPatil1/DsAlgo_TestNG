package dsAlgoPageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import baseClass.BaseClass;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import utils.ConfigReader;
import utils.ExcelRead;
import utils.LoggerLoad;
import java.util.List;
import java.util.Map;
public class RegisterPageObj extends BaseClass{
    WebDriver driver ;
	ExcelRead excelread = new ExcelRead();
	String URL = ConfigReader.getUrl();
	ConfigReader configReader = new ConfigReader();
   
   public RegisterPageObj(WebDriver driver) {
        this.driver = driver;
       PageFactory.initElements(driver, this);
   }
	
	@FindBy(xpath = "//button[@class='btn']")WebElement GetStartedButton;
    @FindBy(xpath = "//a[@href='/register']") WebElement registerLink;
    @FindBy(xpath = "//a[contains(@href, 'logout')]") WebElement signOutButton;
    @FindBy(name = "username")WebElement userNameField;
    @FindBy(name = "password1")WebElement passwordField;
    @FindBy(name = "password2")WebElement confirmPasswordField;
    @FindBy(xpath = "//input[@type='submit']")WebElement registerButton;
    @FindBy(xpath = "//div[contains(@class,'alert alert-primary')]")WebElement registerSuccessMsg;
    @FindBy(xpath = "//div[@class='alert alert-primary']")WebElement passwordMismatchOnRegPage;
    @FindBy(xpath = "//div[2]/a[@href='/login']")WebElement loginLink;
    @FindBy(xpath = "//div[@class='navbar-nav']//a[@href='/login']") WebElement signInLink;

    
    public void clickLoginLink() {
        loginLink.click();
    }
    public void clickSignInLink() {
        signInLink.click();
        LoggerLoad.info("SignInTests Link clicked");
    }
    public void openURL() {
    	driver.get(ConfigReader.getUrl());
	
	}
    public void navigateToRegisterPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        LoggerLoad.info("Clicked on RegisterTests link");
    }

    public void signOut() {
        signOutButton.click();
        LoggerLoad.info("Clicked on Sign Out");
    }
    public void clickRegisterButton() {
        registerButton.click();
        LoggerLoad.info("Clicked on RegisterTests button");
    }
     public void fillRegistrationForm(String sheetName, int rowIndex)
            throws IOException, OpenXML4JException, InterruptedException {
        List<Map<String, String>> testData = excelread.readExcelSheet(sheetName);  // Only pass sheet name
        LoggerLoad.info("RegisterTests test data: " + testData);

        if (rowIndex >= testData.size()) {
            LoggerLoad.error("Row index " + rowIndex + " is out of bounds for the sheet: " + sheetName);
            throw new IllegalArgumentException("Invalid row index: " + rowIndex);
        }
        Map<String, String> rowData = testData.get(rowIndex);
        String username = rowData.get("username");
        String password = rowData.get("password");
        String confirmPassword = rowData.get("password confirmation");
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
        LoggerLoad.info("Filled registration form with -> Username: " + username +
                ", Password: " + password + ", Confirm Password: " + confirmPassword);
       
		}
      
    public String displayPasswordMismatchError() {
		return passwordMismatchOnRegPage.getText();
	}
	public boolean checkIfRegisterSuccessMsgIsDisplayed() {
		return registerSuccessMsg.isDisplayed();
	}
	public String successMsg() {
		return registerSuccessMsg.getText();
	}
	public String activeElementBrowserValidation() 
	{
		return userNameField.getAttribute("validationMessage");
	}
    public String getPasswordMismatchText() {
        return passwordMismatchOnRegPage.getText();
    }
    public boolean isPasswordMismatchVisible() {
        return passwordMismatchOnRegPage.isDisplayed();
    }
	public void clickRegisterLink() {
		registerLink.click();
		LoggerLoad.info("RegisterTests Link clicked");
	}
	public void clickGetStartedButton() {
		GetStartedButton.click();
		
	}
	public String switchToElementAndGetValidationMessage() {
	    WebElement activeElement = null;
	    String actualAlertMsg = null;
	    try {
	        activeElement = driver.switchTo().activeElement();
	        actualAlertMsg = activeElement.getAttribute("validationMessage");
	        LoggerLoad.info("ValidationMessage: " + actualAlertMsg);
	    } catch (Exception e) {
	       
	        System.out.println("Stale element reference caught. Retrying..");
	        activeElement = driver.switchTo().activeElement();
	        actualAlertMsg = activeElement.getAttribute("validationMessage");
	        LoggerLoad.info("ValidationMessage: " + actualAlertMsg);
	    }
	   
	    return actualAlertMsg;
	}  
	
	
}

