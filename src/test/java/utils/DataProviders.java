package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "ValidPythonCode")
    public static Object[][] validCode() {
        return new Object[][] {
            { "tryEditorCode", 0, "Python is fun!" }
        };
    }

    @DataProvider(name = "InvalidPythonCode")
    public static Object[][] invalidCode() {
        return new Object[][] {
            { "tryEditorCode", 1, "SyntaxError: bad input on line 1" }
        };
    }
}