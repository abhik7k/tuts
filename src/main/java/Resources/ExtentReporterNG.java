package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
    
    public static ExtentReports getExtentReportObject() {
        //code to get extent report object
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter report = new ExtentSparkReporter(path);
        report.config().setReportName("Web Automation Results");
        report.config().setDocumentTitle("Test Results");
        report.config().setTheme(Theme.DARK);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Tester", "Abhinav");
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Browser", "Chrome");
        extent.createTest(path);

        return extent;
    }
}
