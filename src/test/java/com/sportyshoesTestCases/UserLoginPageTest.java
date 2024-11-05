package com.sportyshoesTestCases;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.actions.ActionDriver;
import com.base.BaseClass;
import com.sportyshoes.pageobjects.UserLoginPage;


public class UserLoginPageTest extends BaseClass {
	
	
	@BeforeMethod
	public void userLogin() {
		login("user","user4@gmail.com","password");
	}
	
	@Test
	public void verifyUserLogin()  {
		
		UserLoginPage userLoginPage = new UserLoginPage(driver);	
		boolean isDashboardDisplayed = userLoginPage.isUserDashboardDisplayed();
		System.out.println(isDashboardDisplayed);
		Assert.assertTrue(isDashboardDisplayed, "Dashboard is not displayed. Login might have failed");
		
		
	}
	

}
