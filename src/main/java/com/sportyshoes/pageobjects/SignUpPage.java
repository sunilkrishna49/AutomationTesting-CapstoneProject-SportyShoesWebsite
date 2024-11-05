package com.sportyshoes.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actions.ActionDriver;

public class SignUpPage {
	
	WebDriver driver;
	ActionDriver actionDriver;
	
	//Constructor
	public SignUpPage(WebDriver driver) {
		this.driver =driver;
		 actionDriver = new ActionDriver(driver);
		PageFactory.initElements(driver, this);
	}
	
	//WebElements
	@FindBy(id="email")
	private WebElement emailField;
	
	@FindBy(id="password")
	private WebElement passwordField;
	
	@FindBy(id="name")
	private WebElement nameField;
	
	@FindBy(id="surname")
	private WebElement surnameField;
	
	@FindBy(id="signup")
	private WebElement signupButton;

	
	//Actions
	public void enterEmail(String email) {
		
		actionDriver.waitForElement(emailField);
		actionDriver.enterText(emailField, email);
		
	}
	public void enterPassword(String password) {
		actionDriver.waitForElement(passwordField);
		actionDriver.enterText(passwordField, password);
	}
	public void enterName(String name) {
		actionDriver.waitForElement(nameField);
		actionDriver.enterText(nameField, name);
	}
	public void enterSurname(String surname) {
		actionDriver.waitForElement(surnameField);
		actionDriver.enterText(surnameField, surname);
	}
	public void clickSignup() {
		actionDriver.click(signupButton);
	}
//	public boolean isSignUpSuccessful() {
//		actionDriver.waitForElement(nameField);
//	}
	

}
