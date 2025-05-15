package testClasses;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import driverManager.DriverFactory;
import dsAlgoPageObjects.GraphPageObj;
import dsAlgoPageObjects.HomePageObj;
import dsAlgoPageObjects.SignInPageObj;
import dsAlgoPageObjects.TryEditorPage;
import utils.LoggerLoad;

public class GraphTests {
    WebDriver driver;
    SignInPageObj signin;
    HomePageObj homepage;
    TryEditorPage tryEditorPage;
    GraphPageObj graphpage;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.getDriver(); // Make sure this returns a non-null valid WebDriver instance
        signin = new SignInPageObj(driver);
        homepage = new HomePageObj(driver);
        tryEditorPage = new TryEditorPage(driver);
        graphpage = new GraphPageObj(driver);  // Make sure driver is passed here
    }


    @Test(priority = 1)
    public void testUserSignIn() throws Exception {
        homepage.openHomeUrl();
        homepage.clickgetStartedButton(driver);
        homepage.clickSignInLink();
        signin.enterUsernameText("username");
        signin.enterPasswordText("password");
        signin.clickloginButton();
    }

    @Test(priority = 2)
    public void testNavigateToGraphPage() {
        homepage.clickGraphGetStartedButton();
        Assert.assertEquals(homepage.getHomePageTitle(), "Graph");
    }

    @Test(priority = 3)
    public void testInsideGraphPageNavigation() {
        graphpage.ClickInsideGraphkLink();
        Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("graph/"));
    }

    @Test(priority = 4)
    public void testTryHereButtonInsideGraph() {
        graphpage.ClickInsideGraphkLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("tryEditor"));
    }

    @Test(priority = 5)
    public void testEmptyCodeRunInsideGraph() {
        graphpage.ClickInsideGraphkLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.clickRunButton();
        Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("tryEditor"));
    }

    @Test(priority = 6)
    public void testValidCodeRunInsideGraph() {
        graphpage.ClickInsideGraphkLink();
        tryEditorPage.clickTryHereButton();
        try {
            tryEditorPage.enterCodeFromExcel("tryEditorCode", 0);
            tryEditorPage.clickRunButton();
        } catch (Exception e) {
            LoggerLoad.info("Error while entering code or clicking Run: " + e.getMessage());
        }
        graphpage.codeEditorOutput();
    }

    @Test(priority = 7)
    public void testInvalidCodeRunInsideGraph() {
        graphpage.ClickInsideGraphkLink();
        tryEditorPage.clickTryHereButton();
        try {
            tryEditorPage.enterCodeFromExcel("tryEditorCode", 1);
            tryEditorPage.clickRunButton();
        } catch (Exception e) {
            LoggerLoad.info("Error while entering code or clicking Run: " + e.getMessage());
        }
        String isAlertPresent = tryEditorPage.HandleAlert();
        assertTrue(isAlertPresent, "No alert displayed");
    }

    @Test(priority = 8)
    public void testPracticeQuestionsLinkInsideGraph() {
        graphpage.ClickInsideGraphkLink();
        graphpage.clickPracticeQuestionsLink();
        Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("practice"));
    }

    @Test(priority = 9)
    public void testGraphRepresentationNavigation() {
        graphpage.ClickGraphRepresentationLink();
        Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("graph-representations/"));
    }

    @Test(priority = 10)
    public void testTryHereGraphRepresentation() {
        graphpage.ClickGraphRepresentationLink();
        tryEditorPage.clickTryHereButton();
        Assert.assertTrue(graphpage.getcurrentpageUrl().contains("tryEditor"));
    }

    @Test(priority = 11)
    public void testEmptyCodeGraphRepresentation() {
        graphpage.ClickGraphRepresentationLink();
        tryEditorPage.clickTryHereButton();
        tryEditorPage.clickRunButton();
        Assert.assertTrue(graphpage.getcurrentpageUrl().contains("tryEditor"));
    }

    @Test(priority = 12)
    public void testValidCodeGraphRepresentation() {
        graphpage.ClickGraphRepresentationLink();
        tryEditorPage.clickTryHereButton();
        try {
            tryEditorPage.enterCodeFromExcel("tryEditorCode", 0);
            tryEditorPage.clickRunButton();
        } catch (Exception e) {
            LoggerLoad.info("Error while entering code or clicking Run: " + e.getMessage());
        }
        graphpage.codeEditorOutput();
    }

    @Test(priority = 13)
    public void testInvalidCodeGraphRepresentation() {
        graphpage.ClickGraphRepresentationLink();
        tryEditorPage.clickTryHereButton();
        try {
            tryEditorPage.enterCodeFromExcel("tryEditorCode", 1);
            tryEditorPage.clickRunButton();
        } catch (Exception e) {
            LoggerLoad.info("Error while entering code or clicking Run: " + e.getMessage());
        }
        String isAlertPresent = tryEditorPage.HandleAlert();
     
    }

    @Test(priority = 14)
    public void testPracticeQuestionsGraphRepresentation() {
        graphpage.ClickGraphRepresentationLink();
        graphpage.clickPracticeQuestionsLink();
        Assert.assertTrue(graphpage.getcurrentpageUrl().endsWith("practice"));
    }
}