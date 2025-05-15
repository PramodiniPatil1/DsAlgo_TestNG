package dsAlgoPageObjects;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigReader;
import utils.ExcelRead;
import utils.LoggerLoad;

public class SignInPageObj {
	WebDriver driver;
	ExcelRead excelread = new ExcelRead();
	ConfigReader configReader = new ConfigReader();

	@FindBy(xpath = "//input[@name ='username']")WebElement UsernameTextBox;
	@FindBy(xpath = "//input[@name ='password']")WebElement PasswordTextBox;
	@FindBy(xpath = "//input[@value='Login']")WebElement loginButton;
	@FindBy(xpath = "/html/body/div[2]")WebElement homePagemsg;
	@FindBy(xpath = "//*[@class='alert alert-primary']")WebElement errorMessage;

	public SignInPageObj(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUsernameText(String Username) {
		String name = configReader.getUserName();
		UsernameTextBox.sendKeys(name);
	}

	public void enterPasswordText(String password) {
		String pwd = configReader.getPassword();
		PasswordTextBox.sendKeys(pwd);
	}

	public void clickloginButton() {
		loginButton.click();
	}

	public void homePagemsg() {
		homePagemsg.getText();
		LoggerLoad.info(homePagemsg.getText());
	}

	public void EnterFromExcel(String sheetname, int row) throws IOException {

		List<Map<String, String>> testData;
		testData = excelread.readExcelSheet(sheetname);

		if (row >= testData.size()) {
			LoggerLoad.info("Row index " + row + " is out of bounds for the sheet: " + sheetname);
			throw new IllegalArgumentException("Invalid row index: " + row);
		}
		Map<String, String> rowData = testData.get(row);
		String Username = rowData.get("Username");
		String Password = rowData.get("Password");
		UsernameTextBox.sendKeys(Username);
		PasswordTextBox.sendKeys(Password);
		LoggerLoad.info("Entered data from Excel");

	}

	public void ErrorMessage() {
		String Msg = errorMessage.getText();
		LoggerLoad.info("Error Message is: " + Msg);
	}

	public void TakeScreenshot() throws IOException {
	    // Add milliseconds to the timestamp to make filename more unique
	    String scr = "screenshot_" + new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
	    
	    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    Path destination = Path.of(
	        "/Users/dineshdeshmukh/eclipse-workspace/NinjaGalaxy-dsAlgo/src/test/resources/Screenshots", 
	        scr + ".png"
	    );

	    // Move with replace option to avoid FileAlreadyExistsException
	    Files.move(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

	    LoggerLoad.info("Screenshot saved: " + scr + ".png");
	    LoggerLoad.info("Error Message is displayed");
	}
}
