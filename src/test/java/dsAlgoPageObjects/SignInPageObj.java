package dsAlgoPageObjects;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Map;
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


	public void clickloginButton() {
		loginButton.click();
	}

	public boolean homePagemsg() {
		try {
			return homePagemsg.isDisplayed();
		} catch (Exception e) {
			return false;
		}
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

	public boolean ErrorMessage() {
		try {
			return errorMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void TakeScreenshot() throws IOException {
	    // Create a timestamped filename for uniqueness
	    String scrShot = "screenshot_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png";

	    // Take screenshot
	    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    // Define screenshot directory path
	    Path screenshotDir = Path.of(System.getProperty("user.dir")+ "/Screenshots");

	    // Create the directory if it doesn't exist
	    if (!Files.exists(screenshotDir)) {
	        Files.createDirectories(screenshotDir);
	    }

	    // Move the file to the destination
	    Path destination = screenshotDir.resolve(scrShot);
	    Files.move(screenshot.toPath(), destination);

	    LoggerLoad.info("Screenshot saved: " + destination.toString());
	}

	   
	}

