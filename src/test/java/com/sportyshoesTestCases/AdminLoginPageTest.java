package com.sportyshoesTestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.sportyshoes.pageobjects.AdminLoginPage;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;

public class AdminLoginPageTest extends BaseClass {
	
	private ExtentTest test;  // Declare ExtentTest for this test class

	@BeforeMethod
	public void adminLogin() {
		// Start Extent Test with detailed description
		test = extentReportManager.extent.createTest("Admin Login Test", "Verify the admin login functionality and access to Admin Dashboard.");
		
		// Log the start of the login test
		test.log(Status.INFO, "Navigating to Admin login page.");
		
		// Perform login
		login("admin", "admin@admin.com", "a");
		test.log(Status.INFO, "Admin credentials entered.");
	}

	@Test(description = "Verify Admin Dashboard Display After Login")
	public void verifyAdminLogin() {
		test.log(Status.INFO, "Checking if the Admin Dashboard is displayed.");

		AdminLoginPage adminLoginPage = new AdminLoginPage(driver);
		boolean isDashboardDisplayed = adminLoginPage.isAdminDashboardDisplayed();

		// Logging the assertion status with additional information
		if (isDashboardDisplayed) {
			test.log(Status.PASS, "Admin Dashboard displayed successfully.");
		} else {
			test.log(Status.FAIL, "Admin Dashboard is not displayed. Login might have failed.");
		}

		Assert.assertTrue(isDashboardDisplayed, "Dashboard is not displayed. Login might have failed");
	}

}
