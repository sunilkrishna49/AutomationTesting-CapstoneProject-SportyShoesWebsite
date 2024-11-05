package com.sportyshoesTestCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.sportyshoes.pageobjects.SignUpPage;

public class SignUpPageTest extends BaseClass {
	

	
	@Test
	public void signupPageTest() {
		
		driver.get("http://localhost:9292/signup");
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.enterEmail("user15@gmail.com");
		signUpPage.enterPassword("password");
		signUpPage.enterName("user15");
		signUpPage.enterSurname("userFifteen");
		signUpPage.clickSignup();
		
	}

}
