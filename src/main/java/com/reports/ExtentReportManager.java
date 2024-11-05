package com.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.nio.file.Files;
import java.nio.file.Paths;


import java.io.File;
import java.io.IOException;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;  
    public ExtentReports extent;  
    public ExtentTest test; 

    @Override
    public void onStart(ITestContext context) {
        createDirectories();
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myReport.html");
        sparkReporter.config().setDocumentTitle("Automation Report"); 
        sparkReporter.config().setReportName("Functional Testing"); 
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Tester");
        extent.setSystemInfo("OS", "Windows10");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Start a new test in the report with the actual test method name
        test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        String screenshotPath = takeScreenshot(result.getMethod().getMethodName());
        test.addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public String takeScreenshot(String testName) {
        String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + ".png";
        try {
            TakesScreenshot driver = null;
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }

    public void createDirectories() {
        new File(System.getProperty("user.dir") + "/reports").mkdirs();
        new File(System.getProperty("user.dir") + "/screenshots").mkdirs();
    }
}
