package com.sportyshoes.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserLoginPage {
	
	 WebDriver driver;
	
	public UserLoginPage(WebDriver driver) {
		this.driver = driver;
		
	}
	
	By emailField = By.id("email");
	By passwordField = By.id("password");
	By loginButton = By.xpath("//button[normalize-space()='Login']");
	By userDashboardText = By.id("dashboard");
	

	
	public void enterEmail(String email) {
		
		driver.findElement(emailField).sendKeys(email);
		
	}
	public void enterPassword(String password) {
		
		driver.findElement(passwordField).sendKeys(password);
		
	}
	public void clickLogin() {
		driver.findElement(loginButton).click();
		
	}
	public boolean isUserDashboardDisplayed() {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(userDashboardText));
	        return dashboardElement.isDisplayed();
			
		}catch(Exception e) {
			return false;
			
		}
		
	}

}
