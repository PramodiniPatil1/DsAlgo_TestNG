package testClasses;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import driverManager.DriverFactory;
import dsAlgoPageObjects.ArrayPageObj;
import dsAlgoPageObjects.DataStructurePageObj;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.RegisterPageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.TryEditorPage;
import utils.ConfigReader;

public class ArrayTests extends BaseClass {
	
	
		WebDriver driver;
	
	    DataStructurePageObj dataStructurerpage;
	    TryEditorPage tryEditorPage;
	    HomePageObj homepage;
	    RegisterPageObj registerpage;
	    SignInPageObj signinpage;
	    ArrayPageObj arraypage;
	    @BeforeClass
	    public void setUpOnce() throws IOException {
	        driver = DriverFactory.initializeDriver(ConfigReader.getBrowserType());

	        dataStructurerpage = new DataStructurePageObj(driver);
	        tryEditorPage = new TryEditorPage(driver);
	        homepage = new HomePageObj(driver);
	        registerpage = new RegisterPageObj(driver);
	        signinpage = new SignInPageObj(driver);
	        arraypage = new ArrayPageObj(driver);
	        driver.get(ConfigReader.getUrl());
	        homepage.clickGetStartedHomePageButton();
	        homepage.clickSignInLink();
	        signinpage.EnterFromExcel("login", 0);
	        signinpage.clickloginButton();
	       
	        Assert.assertEquals(registerpage.successMsg(), "You are logged in", "Login failed or success message mismatch.");
	    }

	    @BeforeMethod
	    public void resetToHomeBeforeTest() {
	        driver.get(ConfigReader.getUrl());
	        homepage.clickGetStartedHomePageButton();
	    }


	    @DataProvider(name = "validCodeProvider")
	    public Object[][] validCodeData() throws IOException, OpenXML4JException {
	        return new Object[][] {
	            {"tryEditorCode", 0, "Python is fun!"}
	        };
	    }

	    @DataProvider(name = "invalidCodeProvider")
	    public Object[][] invalidCodeData() throws IOException, OpenXML4JException {
	        return new Object[][] {
	            {"tryEditorCode", 1, "SyntaxError"},
	            // You can add more rows from Excel as needed
	        };
	    }
	
	    
	   
	    
	    @DataProvider(name = "ValidArrayPracticeQns")
	    public Object[][] ValidArrayPracticeQns() throws IOException, OpenXML4JException {
	        return new Object[][] {
	            {"ArrayPracticeQnsQ1", 1, "SyntaxError"},
	            // You can add more rows from Excel as needed
	        };
	    }
	    
	    @DataProvider(name = "InValidArrayPracticeQns")
	    public Object[][] InValidArrayPracticeQns() throws IOException, OpenXML4JException {
	        return new Object[][] {
	            {"ArrayPracticeQnsQ1", 1, "SyntaxError"},
	            // You can add more rows from Excel as needed
	        };
	    }
	
	    
	    @DataProvider(name = "pythonCode1")
	    public Object[][] pythonCode1() throws IOException, OpenXML4JException {
	        return new Object[][] {
	            {"ArrayPracticeQnsQ1", 1, "SyntaxError"},
	            // You can add more rows from Excel as needed
	        };
	    }
	
	    @DataProvider(name = "invalidpythonCode1")
	    public Object[][] invalidpythonCode1() throws IOException, OpenXML4JException {
	        return new Object[][] {
	            {"ArrayPracticeQnsQ1", 1, "SyntaxError"},
	            // You can add more rows from Excel as needed
	        };
	    }
	    
	    @Test(priority = 1, dataProvider = "validCodeProvider")
public void  ArraysinPython(String sheetName, int rowNum, String expectedOutput) throws Exception { {
	    
	    	 homepage.clickArrayGetStartedButton();
	    	 arraypage.clickarraysInPythonLink();
	    	 tryEditorPage.clickTryHereButton();
	         tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
	         tryEditorPage.clickRunButton();
	         Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
	         
}
	    }

@Test(priority = 1, dataProvider = "InvalidCodeProvider")
public void  ArraysinPython1(String sheetName, int rowNum, String expectedOutput) throws Exception { {
	    
	    	 homepage.clickArrayGetStartedButton();
	    	 arraypage.clickarraysInPythonLink();
	    	 tryEditorPage.clickTryHereButton();
	         tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
	         tryEditorPage.clickRunButton();
	         Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
	         
}

}
	   
@Test(priority = 1, dataProvider = "validCodeProvider")
public void  ArraysUsingList(String sheetName, int rowNum, String expectedOutput) throws Exception { {
	    
	    	 homepage.clickArrayGetStartedButton();
	    	 arraypage.clickarraysUsingListLink();
	    	 tryEditorPage.clickTryHereButton();
	         tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
	         tryEditorPage.clickRunButton();
	         Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
	         
}


}	    
	 
@Test(priority = 1, dataProvider = "InvalidCodeProvider")
public void  ArraysUsingList1(String sheetName, int rowNum, String expectedOutput) throws Exception { {
	    
	    	 homepage.clickArrayGetStartedButton();
	    	 arraypage.clickarraysUsingListLink();
	    	 tryEditorPage.clickTryHereButton();
	         tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
	         tryEditorPage.clickRunButton();
	         Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
	         
}
}



@Test(priority = 1, dataProvider = "validCodeProvider")
public void  BasicOperationsinLists(String sheetName, int rowNum, String expectedOutput) throws Exception { {
	    
	    	 homepage.clickArrayGetStartedButton();
	    	 arraypage.clickbasicOperationsInListsLink();
	    	 tryEditorPage.clickTryHereButton();
	         tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
	         tryEditorPage.clickRunButton();
	         Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
	         
}

}


@Test(priority = 1, dataProvider = "InvalidCodeProvider")
public void  BasicOperationsinLists1(String sheetName, int rowNum, String expectedOutput) throws Exception { {
	    
	    	 homepage.clickArrayGetStartedButton();
	    	 arraypage.clickbasicOperationsInListsLink();
	    	 tryEditorPage.clickTryHereButton();
	         tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
	         tryEditorPage.clickRunButton();
	         Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
	         
}


}

@Test(priority = 1, dataProvider = "validCodeProvider")
public void  ApplicationsofArray(String sheetName, int rowNum, String expectedOutput) throws Exception { {
	    
	    	 homepage.clickArrayGetStartedButton();
	    	 arraypage.clickapplicationsOfArrayLink();
	    	 tryEditorPage.clickTryHereButton();
	         tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
	         tryEditorPage.clickRunButton();
	         Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
	         
}
}


@Test(priority = 1, dataProvider = "InvalidCodeProvider")
public void  ApplicationsofArray1(String sheetName, int rowNum, String expectedOutput) throws Exception { {
	    
	    	 homepage.clickArrayGetStartedButton();
	    	 arraypage.clickapplicationsOfArrayLink();
	    	 tryEditorPage.clickTryHereButton();
	         tryEditorPage.enterCodeFromExcel(sheetName, rowNum);
	         tryEditorPage.clickRunButton();
	         Assert.assertEquals(tryEditorPage.getOutputText(), expectedOutput, "Valid output did not match expected result.");
	         
}






}

@AfterClass
public void tearDown() {
    if (driver != null) {
        driver.quit();
    }
}
}
