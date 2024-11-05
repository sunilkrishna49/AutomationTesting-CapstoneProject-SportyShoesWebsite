package com.base;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.reports.ExtentReportManager;
import com.sportyshoes.pageobjects.AdminLoginPage;
import com.sportyshoes.pageobjects.UserLoginPage;
import com.aventstack.extentreports.Status;

public class BaseClass {

    public static WebDriver driver;
    public static ExtentReportManager extentReportManager;
    private static final String BASE_URL = "http://localhost:9292"; // Ideally from a config file

    @BeforeSuite
    public void setUp() {
        extentReportManager = new ExtentReportManager();
        extentReportManager.onStart(null); // Initialize Extent Reports

        // Setup ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @BeforeMethod
    public void beforeTestSetup(ITestResult result) {
        // Create a unique test entry based on the test method name
    	String testName = result.getMethod().getMethodName();
    	
        extentReportManager.test = extentReportManager.extent.createTest("Executing Test: "+ testName);
        extentReportManager.test.log(Status.INFO,"Starting Test: "+ testName);
    }

    public void login(String role, String email, String password) {
        driver.get(BASE_URL + "/login");

        extentReportManager.test.log(Status.INFO, "Navigating to login page");

        if (role.equalsIgnoreCase("admin")) {
            AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
            adminLoginPage.enterEmail(email);
            adminLoginPage.enterPassword(password);
            adminLoginPage.clickLogin();
            extentReportManager.test.log(Status.INFO, "Logged in as Admin");
        } else {
            UserLoginPage userLoginPage = new UserLoginPage(driver);
            userLoginPage.enterEmail(email);
            userLoginPage.enterPassword(password);
            userLoginPage.clickLogin();
            extentReportManager.test.log(Status.INFO, "Logged in as User");
        }
        handleUnexpectedAlert(); // Handle any alert after login
    }
    
 // Method to handle unexpected alerts
 	private void handleUnexpectedAlert() {
 		try {
 			Alert alert = driver.switchTo().alert();
 			System.out.println("Unexpected alert found: " + alert.getText());
 			alert.accept();  // Accept or dismiss the alert as needed
 		} catch (NoAlertPresentException e) {
 			// No alert found, continue normally
 		}
 	}
    

    @AfterMethod
    public void afterTestTearDown(ITestResult result) {
        // Log test result in Extent Report
        if (result.getStatus() == ITestResult.FAILURE) {
            extentReportManager.test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentReportManager.test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        } else {
            extentReportManager.test.log(Status.PASS, "Test Passed");
        }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extentReportManager.onFinish(null); // Generate Extent Report
    }
}
