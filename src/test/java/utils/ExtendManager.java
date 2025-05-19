package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;

public class ExtendManager {

    static ExtentReports extent;

    public static void extentTest() throws IOException {
        extent = new ExtentReports();

        // Load extent config file
        final File CONF = new File(System.getProperty("user.dir") + "/src/test/resources/config/extent-config.xml");

        // Ensure the report folder exists
        String reportPath = System.getProperty("user.dir") + "/target/ExtentReport/";
        File reportDir = new File(reportPath);
        if (!reportDir.exists()) {
            reportDir.mkdirs();  // create directories if not present
        }

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath + "Spark.html");
        spark.loadXMLConfig(CONF);

        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("DSALGO Project");
        spark.config().setReportName("DSALGO Extent Report");

        extent.attachReporter(spark);
    }

    public static void endReport() {
        extent.flush();
    }
}
