package baseClass;

import org.openqa.selenium.WebDriver;

import dsAlgoPageObjects.*;
import io.cucumber.java.Scenario;

public class TestInstance {

		WebDriver driver;
		DataStructurePageObj dataStructurerpage;
	    TryEditorPage tryEditorPage;
	    HomePageObj homepage;
	    RegisterPageObj registerpage;
	    SignInPageObj signinpage;
	
	public DataStructurePageObj getDataStructurerpage() {
			return dataStructurerpage;
		}
		
		public TryEditorPage getTryEditorPage() {
			return tryEditorPage;
		}		

		public HomePageObj getHomepage() {
			return homepage;
		}	

		public RegisterPageObj getRegisterpage() {
			return registerpage;
		}		

		public SignInPageObj getSigninpage() {
			return signinpage;
		}
		public WebDriver getDriver() {
			return driver;
		}
		public TestInstance() {
		
		}
		public void IntializePageObj(WebDriver driver, Scenario s) {
			dataStructurerpage= new DataStructurePageObj(driver,s);
	    	tryEditorPage=new TryEditorPage(driver,s);
	    	homepage= new HomePageObj(driver,s);
	    	registerpage= new RegisterPageObj(driver,s);
	    	signinpage=new SignInPageObj(driver,s);
		}
}
